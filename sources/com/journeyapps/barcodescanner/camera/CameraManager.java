package com.journeyapps.barcodescanner.camera;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.zxing.client.android.AmbientLightManager;
import com.google.zxing.client.android.camera.CameraConfigurationUtils;
import com.google.zxing.client.android.camera.open.OpenCameraInterface;
import com.journeyapps.barcodescanner.Size;
import com.journeyapps.barcodescanner.SourceData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class CameraManager {
    private static final String TAG = "CameraManager";
    private AmbientLightManager ambientLightManager;
    private AutoFocusManager autoFocusManager;
    private Camera camera;
    private CameraInfo cameraInfo;
    private final CameraPreviewCallback cameraPreviewCallback;
    private Context context;
    private String defaultParameters;
    private DisplayConfiguration displayConfiguration;
    private Size previewSize;
    private boolean previewing;
    private Size requestedPreviewSize;
    private int rotationDegrees = -1;
    private CameraSettings settings = new CameraSettings();

    private final class CameraPreviewCallback implements PreviewCallback {
        private PreviewCallback callback;
        private Size resolution;

        public void setResolution(Size size) {
            this.resolution = size;
        }

        public void setCallback(PreviewCallback previewCallback) {
            this.callback = previewCallback;
        }

        public void onPreviewFrame(byte[] bArr, Camera camera) {
            Size size = this.resolution;
            PreviewCallback previewCallback = this.callback;
            if (size == null || previewCallback == null) {
                Log.d(CameraManager.TAG, "Got preview callback, but no handler or resolution available");
                return;
            }
            byte[] bArr2 = bArr;
            previewCallback.onPreview(new SourceData(bArr2, size.width, size.height, camera.getParameters().getPreviewFormat(), CameraManager.this.getCameraRotation()));
        }
    }

    public CameraManager(Context context) {
        this.context = context;
        this.cameraPreviewCallback = new CameraPreviewCallback();
    }

    public void open() {
        this.camera = OpenCameraInterface.open(this.settings.getRequestedCameraId());
        if (this.camera != null) {
            int cameraId = OpenCameraInterface.getCameraId(this.settings.getRequestedCameraId());
            this.cameraInfo = new CameraInfo();
            Camera.getCameraInfo(cameraId, this.cameraInfo);
            return;
        }
        throw new RuntimeException("Failed to open camera");
    }

    public void configure() {
        if (this.camera != null) {
            setParameters();
            return;
        }
        throw new RuntimeException("Camera not open");
    }

    public void setPreviewDisplay(SurfaceHolder surfaceHolder) throws IOException {
        setPreviewDisplay(new CameraSurface(surfaceHolder));
    }

    public void setPreviewDisplay(CameraSurface cameraSurface) throws IOException {
        cameraSurface.setPreview(this.camera);
    }

    public void startPreview() {
        Camera camera = this.camera;
        if (camera != null && !this.previewing) {
            camera.startPreview();
            this.previewing = true;
            this.autoFocusManager = new AutoFocusManager(this.camera, this.settings);
            this.ambientLightManager = new AmbientLightManager(this.context, this, this.settings);
            this.ambientLightManager.start();
        }
    }

    public void stopPreview() {
        AutoFocusManager autoFocusManager = this.autoFocusManager;
        if (autoFocusManager != null) {
            autoFocusManager.stop();
            this.autoFocusManager = null;
        }
        AmbientLightManager ambientLightManager = this.ambientLightManager;
        if (ambientLightManager != null) {
            ambientLightManager.stop();
            this.ambientLightManager = null;
        }
        Camera camera = this.camera;
        if (camera != null && this.previewing) {
            camera.stopPreview();
            this.cameraPreviewCallback.setCallback(null);
            this.previewing = false;
        }
    }

    public void close() {
        Camera camera = this.camera;
        if (camera != null) {
            camera.release();
            this.camera = null;
        }
    }

    public boolean isCameraRotated() {
        int i = this.rotationDegrees;
        if (i != -1) {
            return i % 180 != 0;
        } else {
            throw new IllegalStateException("Rotation not calculated yet. Call configure() first.");
        }
    }

    public int getCameraRotation() {
        return this.rotationDegrees;
    }

    private Parameters getDefaultCameraParameters() {
        Parameters parameters = this.camera.getParameters();
        String str = this.defaultParameters;
        if (str == null) {
            this.defaultParameters = parameters.flatten();
        } else {
            parameters.unflatten(str);
        }
        return parameters;
    }

    private void setDesiredParameters(boolean z) {
        Parameters defaultCameraParameters = getDefaultCameraParameters();
        if (defaultCameraParameters == null) {
            Log.w(TAG, "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Initial camera parameters: ");
        stringBuilder.append(defaultCameraParameters.flatten());
        Log.i(str, stringBuilder.toString());
        if (z) {
            Log.w(TAG, "In camera config safe mode -- most settings will not be honored");
        }
        CameraConfigurationUtils.setFocus(defaultCameraParameters, this.settings.getFocusMode(), z);
        if (!z) {
            CameraConfigurationUtils.setTorch(defaultCameraParameters, false);
            if (this.settings.isScanInverted()) {
                CameraConfigurationUtils.setInvertColor(defaultCameraParameters);
            }
            if (this.settings.isBarcodeSceneModeEnabled()) {
                CameraConfigurationUtils.setBarcodeSceneMode(defaultCameraParameters);
            }
            if (this.settings.isMeteringEnabled() && VERSION.SDK_INT >= true) {
                CameraConfigurationUtils.setVideoStabilization(defaultCameraParameters);
                CameraConfigurationUtils.setFocusArea(defaultCameraParameters);
                CameraConfigurationUtils.setMetering(defaultCameraParameters);
            }
        }
        z = getPreviewSizes(defaultCameraParameters);
        if (z.size() == 0) {
            this.requestedPreviewSize = false;
        } else {
            this.requestedPreviewSize = this.displayConfiguration.getBestPreviewSize(z, isCameraRotated());
            defaultCameraParameters.setPreviewSize(this.requestedPreviewSize.width, this.requestedPreviewSize.height);
        }
        if (Build.DEVICE.equals("glass-1")) {
            CameraConfigurationUtils.setBestPreviewFPS(defaultCameraParameters);
        }
        z = TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Final camera parameters: ");
        stringBuilder2.append(defaultCameraParameters.flatten());
        Log.i(z, stringBuilder2.toString());
        this.camera.setParameters(defaultCameraParameters);
    }

    private static List<Size> getPreviewSizes(Parameters parameters) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Size> arrayList = new ArrayList();
        if (supportedPreviewSizes == null) {
            parameters = parameters.getPreviewSize();
            if (parameters != null) {
                arrayList.add(new Size(parameters.width, parameters.height));
            }
            return arrayList;
        }
        for (Camera.Size size : supportedPreviewSizes) {
            arrayList.add(new Size(size.width, size.height));
        }
        return arrayList;
    }

    private int calculateDisplayRotation() {
        int i;
        int i2 = 0;
        switch (this.displayConfiguration.getRotation()) {
            case 0:
                break;
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = 270;
                break;
            default:
                break;
        }
        if (this.cameraInfo.facing == 1) {
            i = (360 - ((this.cameraInfo.orientation + i2) % 360)) % 360;
        } else {
            i = ((this.cameraInfo.orientation - i2) + 360) % 360;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Camera Display Orientation: ");
        stringBuilder.append(i);
        Log.i(str, stringBuilder.toString());
        return i;
    }

    private void setCameraDisplayOrientation(int i) {
        this.camera.setDisplayOrientation(i);
    }

    private void setParameters() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = r3.calculateDisplayRotation();	 Catch:{ Exception -> 0x000c }
        r3.rotationDegrees = r0;	 Catch:{ Exception -> 0x000c }
        r0 = r3.rotationDegrees;	 Catch:{ Exception -> 0x000c }
        r3.setCameraDisplayOrientation(r0);	 Catch:{ Exception -> 0x000c }
        goto L_0x0013;
    L_0x000c:
        r0 = TAG;
        r1 = "Failed to set rotation.";
        android.util.Log.w(r0, r1);
    L_0x0013:
        r0 = 0;
        r3.setDesiredParameters(r0);	 Catch:{ Exception -> 0x0018 }
        goto L_0x0024;
    L_0x0018:
        r0 = 1;
        r3.setDesiredParameters(r0);	 Catch:{ Exception -> 0x001d }
        goto L_0x0024;
    L_0x001d:
        r0 = TAG;
        r1 = "Camera rejected even safe-mode parameters! No configuration";
        android.util.Log.w(r0, r1);
    L_0x0024:
        r0 = r3.camera;
        r0 = r0.getParameters();
        r0 = r0.getPreviewSize();
        if (r0 != 0) goto L_0x0035;
    L_0x0030:
        r0 = r3.requestedPreviewSize;
        r3.previewSize = r0;
        goto L_0x0040;
    L_0x0035:
        r1 = new com.journeyapps.barcodescanner.Size;
        r2 = r0.width;
        r0 = r0.height;
        r1.<init>(r2, r0);
        r3.previewSize = r1;
    L_0x0040:
        r0 = r3.cameraPreviewCallback;
        r1 = r3.previewSize;
        r0.setResolution(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.journeyapps.barcodescanner.camera.CameraManager.setParameters():void");
    }

    public boolean isOpen() {
        return this.camera != null;
    }

    public Size getNaturalPreviewSize() {
        return this.previewSize;
    }

    public Size getPreviewSize() {
        if (this.previewSize == null) {
            return null;
        }
        if (isCameraRotated()) {
            return this.previewSize.rotate();
        }
        return this.previewSize;
    }

    public void requestPreviewFrame(PreviewCallback previewCallback) {
        Camera camera = this.camera;
        if (camera != null && this.previewing) {
            this.cameraPreviewCallback.setCallback(previewCallback);
            camera.setOneShotPreviewCallback(this.cameraPreviewCallback);
        }
    }

    public CameraSettings getCameraSettings() {
        return this.settings;
    }

    public void setCameraSettings(CameraSettings cameraSettings) {
        this.settings = cameraSettings;
    }

    public DisplayConfiguration getDisplayConfiguration() {
        return this.displayConfiguration;
    }

    public void setDisplayConfiguration(DisplayConfiguration displayConfiguration) {
        this.displayConfiguration = displayConfiguration;
    }

    public void setTorch(boolean z) {
        if (this.camera != null && z != isTorchOn()) {
            AutoFocusManager autoFocusManager = this.autoFocusManager;
            if (autoFocusManager != null) {
                autoFocusManager.stop();
            }
            Parameters parameters = this.camera.getParameters();
            CameraConfigurationUtils.setTorch(parameters, z);
            if (this.settings.isExposureEnabled()) {
                CameraConfigurationUtils.setBestExposure(parameters, z);
            }
            this.camera.setParameters(parameters);
            z = this.autoFocusManager;
            if (z) {
                z.start();
            }
        }
    }

    public boolean isTorchOn() {
        Parameters parameters = this.camera.getParameters();
        boolean z = false;
        if (parameters == null) {
            return false;
        }
        String flashMode = parameters.getFlashMode();
        if (flashMode != null && ("on".equals(flashMode) || "torch".equals(flashMode))) {
            z = true;
        }
        return z;
    }
}
