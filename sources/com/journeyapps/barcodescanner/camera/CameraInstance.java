package com.journeyapps.barcodescanner.camera;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.zxing.client.android.C0391R;
import com.journeyapps.barcodescanner.Size;
import com.journeyapps.barcodescanner.Util;

public class CameraInstance {
    private static final String TAG = "CameraInstance";
    private CameraManager cameraManager;
    private CameraSettings cameraSettings = new CameraSettings();
    private CameraThread cameraThread;
    private Runnable closer = new C04206();
    private Runnable configure = new C04184();
    private DisplayConfiguration displayConfiguration;
    private boolean open = false;
    private Runnable opener = new C04173();
    private Runnable previewStarter = new C04195();
    private Handler readyHandler;
    private CameraSurface surface;

    /* renamed from: com.journeyapps.barcodescanner.camera.CameraInstance$3 */
    class C04173 implements Runnable {
        C04173() {
        }

        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Opening camera");
                CameraInstance.this.cameraManager.open();
            } catch (Throwable e) {
                CameraInstance.this.notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to open camera", e);
            }
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.camera.CameraInstance$4 */
    class C04184 implements Runnable {
        C04184() {
        }

        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Configuring camera");
                CameraInstance.this.cameraManager.configure();
                if (CameraInstance.this.readyHandler != null) {
                    CameraInstance.this.readyHandler.obtainMessage(C0391R.id.zxing_prewiew_size_ready, CameraInstance.this.getPreviewSize()).sendToTarget();
                }
            } catch (Throwable e) {
                CameraInstance.this.notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to configure camera", e);
            }
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.camera.CameraInstance$5 */
    class C04195 implements Runnable {
        C04195() {
        }

        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Starting preview");
                CameraInstance.this.cameraManager.setPreviewDisplay(CameraInstance.this.surface);
                CameraInstance.this.cameraManager.startPreview();
            } catch (Throwable e) {
                CameraInstance.this.notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to start preview", e);
            }
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.camera.CameraInstance$6 */
    class C04206 implements Runnable {
        C04206() {
        }

        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Closing camera");
                CameraInstance.this.cameraManager.stopPreview();
                CameraInstance.this.cameraManager.close();
            } catch (Throwable e) {
                Log.e(CameraInstance.TAG, "Failed to close camera", e);
            }
            CameraInstance.this.cameraThread.decrementInstances();
        }
    }

    public CameraInstance(Context context) {
        Util.validateMainThread();
        this.cameraThread = CameraThread.getInstance();
        this.cameraManager = new CameraManager(context);
        this.cameraManager.setCameraSettings(this.cameraSettings);
    }

    public void setDisplayConfiguration(DisplayConfiguration displayConfiguration) {
        this.displayConfiguration = displayConfiguration;
        this.cameraManager.setDisplayConfiguration(displayConfiguration);
    }

    public DisplayConfiguration getDisplayConfiguration() {
        return this.displayConfiguration;
    }

    public void setReadyHandler(Handler handler) {
        this.readyHandler = handler;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        setSurface(new CameraSurface(surfaceHolder));
    }

    public void setSurface(CameraSurface cameraSurface) {
        this.surface = cameraSurface;
    }

    public CameraSettings getCameraSettings() {
        return this.cameraSettings;
    }

    public void setCameraSettings(CameraSettings cameraSettings) {
        if (!this.open) {
            this.cameraSettings = cameraSettings;
            this.cameraManager.setCameraSettings(cameraSettings);
        }
    }

    private Size getPreviewSize() {
        return this.cameraManager.getPreviewSize();
    }

    public int getCameraRotation() {
        return this.cameraManager.getCameraRotation();
    }

    public void open() {
        Util.validateMainThread();
        this.open = true;
        this.cameraThread.incrementAndEnqueue(this.opener);
    }

    public void configureCamera() {
        Util.validateMainThread();
        validateOpen();
        this.cameraThread.enqueue(this.configure);
    }

    public void startPreview() {
        Util.validateMainThread();
        validateOpen();
        this.cameraThread.enqueue(this.previewStarter);
    }

    public void setTorch(final boolean z) {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(new Runnable() {
                public void run() {
                    CameraInstance.this.cameraManager.setTorch(z);
                }
            });
        }
    }

    public void close() {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(this.closer);
        }
        this.open = false;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void requestPreview(final PreviewCallback previewCallback) {
        validateOpen();
        this.cameraThread.enqueue(new Runnable() {
            public void run() {
                CameraInstance.this.cameraManager.requestPreviewFrame(previewCallback);
            }
        });
    }

    private void validateOpen() {
        if (!this.open) {
            throw new IllegalStateException("CameraInstance is not open");
        }
    }

    private void notifyError(Exception exception) {
        Handler handler = this.readyHandler;
        if (handler != null) {
            handler.obtainMessage(C0391R.id.zxing_camera_error, exception).sendToTarget();
        }
    }
}
