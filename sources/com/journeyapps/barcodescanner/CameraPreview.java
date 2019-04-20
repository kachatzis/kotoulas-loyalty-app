package com.journeyapps.barcodescanner;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.google.zxing.client.android.C0391R;
import com.journeyapps.barcodescanner.camera.CameraInstance;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import com.journeyapps.barcodescanner.camera.CameraSurface;
import com.journeyapps.barcodescanner.camera.CenterCropStrategy;
import com.journeyapps.barcodescanner.camera.DisplayConfiguration;
import com.journeyapps.barcodescanner.camera.FitCenterStrategy;
import com.journeyapps.barcodescanner.camera.FitXYStrategy;
import com.journeyapps.barcodescanner.camera.PreviewScalingStrategy;
import java.util.ArrayList;
import java.util.List;

public class CameraPreview extends ViewGroup {
    private static final int ROTATION_LISTENER_DELAY_MS = 250;
    private static final String TAG = "CameraPreview";
    private CameraInstance cameraInstance;
    private CameraSettings cameraSettings = new CameraSettings();
    private Size containerSize;
    private Size currentSurfaceSize;
    private DisplayConfiguration displayConfiguration;
    private final StateListener fireState = new C05985();
    private Rect framingRect = null;
    private Size framingRectSize = null;
    private double marginFraction = 0.1d;
    private int openedOrientation = -1;
    private boolean previewActive = false;
    private Rect previewFramingRect = null;
    private PreviewScalingStrategy previewScalingStrategy = null;
    private Size previewSize;
    private RotationCallback rotationCallback = new C05974();
    private RotationListener rotationListener;
    private final Callback stateCallback = new C04043();
    private Handler stateHandler;
    private List<StateListener> stateListeners = new ArrayList();
    private final SurfaceHolder.Callback surfaceCallback = new C04032();
    private Rect surfaceRect;
    private SurfaceView surfaceView;
    private TextureView textureView;
    private boolean torchOn = false;
    private boolean useTextureView = false;
    private WindowManager windowManager;

    /* renamed from: com.journeyapps.barcodescanner.CameraPreview$1 */
    class C04021 implements SurfaceTextureListener {
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return false;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        C04021() {
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            CameraPreview.this.currentSurfaceSize = new Size(i, i2);
            CameraPreview.this.startPreviewIfReady();
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.CameraPreview$2 */
    class C04032 implements SurfaceHolder.Callback {
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
        }

        C04032() {
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            CameraPreview.this.currentSurfaceSize = null;
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (surfaceHolder == null) {
                Log.e(CameraPreview.TAG, "*** WARNING *** surfaceChanged() gave us a null surface!");
                return;
            }
            CameraPreview.this.currentSurfaceSize = new Size(i2, i3);
            CameraPreview.this.startPreviewIfReady();
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.CameraPreview$3 */
    class C04043 implements Callback {
        C04043() {
        }

        public boolean handleMessage(Message message) {
            if (message.what == C0391R.id.zxing_prewiew_size_ready) {
                CameraPreview.this.previewSized((Size) message.obj);
                return true;
            }
            if (message.what == C0391R.id.zxing_camera_error) {
                Exception exception = (Exception) message.obj;
                if (CameraPreview.this.isActive()) {
                    CameraPreview.this.pause();
                    CameraPreview.this.fireState.cameraError(exception);
                }
            }
            return null;
        }
    }

    public interface StateListener {
        void cameraError(Exception exception);

        void previewSized();

        void previewStarted();

        void previewStopped();
    }

    /* renamed from: com.journeyapps.barcodescanner.CameraPreview$4 */
    class C05974 implements RotationCallback {

        /* renamed from: com.journeyapps.barcodescanner.CameraPreview$4$1 */
        class C04051 implements Runnable {
            C04051() {
            }

            public void run() {
                CameraPreview.this.rotationChanged();
            }
        }

        C05974() {
        }

        public void onRotationChanged(int i) {
            CameraPreview.this.stateHandler.postDelayed(new C04051(), 250);
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.CameraPreview$5 */
    class C05985 implements StateListener {
        C05985() {
        }

        public void previewSized() {
            for (StateListener previewSized : CameraPreview.this.stateListeners) {
                previewSized.previewSized();
            }
        }

        public void previewStarted() {
            for (StateListener previewStarted : CameraPreview.this.stateListeners) {
                previewStarted.previewStarted();
            }
        }

        public void previewStopped() {
            for (StateListener previewStopped : CameraPreview.this.stateListeners) {
                previewStopped.previewStopped();
            }
        }

        public void cameraError(Exception exception) {
            for (StateListener cameraError : CameraPreview.this.stateListeners) {
                cameraError.cameraError(exception);
            }
        }
    }

    protected void previewStarted() {
    }

    @TargetApi(14)
    private SurfaceTextureListener surfaceTextureListener() {
        return new C04021();
    }

    public CameraPreview(Context context) {
        super(context);
        initialize(context, null, 0, 0);
    }

    public CameraPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context, attributeSet, 0, 0);
    }

    public CameraPreview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context, attributeSet, i, 0);
    }

    private void initialize(Context context, AttributeSet attributeSet, int i, int i2) {
        if (getBackground() == 0) {
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        initializeAttributes(attributeSet);
        this.windowManager = (WindowManager) context.getSystemService("window");
        this.stateHandler = new Handler(this.stateCallback);
        this.rotationListener = new RotationListener();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setupSurfaceView();
    }

    protected void initializeAttributes(AttributeSet attributeSet) {
        attributeSet = getContext().obtainStyledAttributes(attributeSet, C0391R.styleable.zxing_camera_preview);
        int dimension = (int) attributeSet.getDimension(C0391R.styleable.zxing_camera_preview_zxing_framing_rect_width, -1.0f);
        int dimension2 = (int) attributeSet.getDimension(C0391R.styleable.zxing_camera_preview_zxing_framing_rect_height, -1.0f);
        if (dimension > 0 && dimension2 > 0) {
            this.framingRectSize = new Size(dimension, dimension2);
        }
        this.useTextureView = attributeSet.getBoolean(C0391R.styleable.zxing_camera_preview_zxing_use_texture_view, true);
        dimension = attributeSet.getInteger(C0391R.styleable.zxing_camera_preview_zxing_preview_scaling_strategy, -1);
        if (dimension == 1) {
            this.previewScalingStrategy = new CenterCropStrategy();
        } else if (dimension == 2) {
            this.previewScalingStrategy = new FitCenterStrategy();
        } else if (dimension == 3) {
            this.previewScalingStrategy = new FitXYStrategy();
        }
        attributeSet.recycle();
    }

    private void rotationChanged() {
        if (isActive() && getDisplayRotation() != this.openedOrientation) {
            pause();
            resume();
        }
    }

    private void setupSurfaceView() {
        if (!this.useTextureView || VERSION.SDK_INT < 14) {
            this.surfaceView = new SurfaceView(getContext());
            if (VERSION.SDK_INT < 11) {
                this.surfaceView.getHolder().setType(3);
            }
            this.surfaceView.getHolder().addCallback(this.surfaceCallback);
            addView(this.surfaceView);
            return;
        }
        this.textureView = new TextureView(getContext());
        this.textureView.setSurfaceTextureListener(surfaceTextureListener());
        addView(this.textureView);
    }

    public void addStateListener(StateListener stateListener) {
        this.stateListeners.add(stateListener);
    }

    private void calculateFrames() {
        if (this.containerSize != null) {
            Size size = this.previewSize;
            if (!(size == null || this.displayConfiguration == null)) {
                int i = size.width;
                int i2 = this.previewSize.height;
                int i3 = this.containerSize.width;
                int i4 = this.containerSize.height;
                this.surfaceRect = this.displayConfiguration.scalePreview(this.previewSize);
                this.framingRect = calculateFramingRect(new Rect(0, 0, i3, i4), this.surfaceRect);
                Rect rect = new Rect(this.framingRect);
                rect.offset(-this.surfaceRect.left, -this.surfaceRect.top);
                this.previewFramingRect = new Rect((rect.left * i) / this.surfaceRect.width(), (rect.top * i2) / this.surfaceRect.height(), (rect.right * i) / this.surfaceRect.width(), (rect.bottom * i2) / this.surfaceRect.height());
                if (this.previewFramingRect.width() > 0) {
                    if (this.previewFramingRect.height() > 0) {
                        this.fireState.previewSized();
                        return;
                    }
                }
                this.previewFramingRect = null;
                this.framingRect = null;
                Log.w(TAG, "Preview frame is too small");
                return;
            }
        }
        this.previewFramingRect = null;
        this.framingRect = null;
        this.surfaceRect = null;
        throw new IllegalStateException("containerSize or previewSize is not set yet");
    }

    public void setTorch(boolean z) {
        this.torchOn = z;
        CameraInstance cameraInstance = this.cameraInstance;
        if (cameraInstance != null) {
            cameraInstance.setTorch(z);
        }
    }

    private void containerSized(Size size) {
        this.containerSize = size;
        CameraInstance cameraInstance = this.cameraInstance;
        if (cameraInstance != null && cameraInstance.getDisplayConfiguration() == null) {
            this.displayConfiguration = new DisplayConfiguration(getDisplayRotation(), size);
            this.displayConfiguration.setPreviewScalingStrategy(getPreviewScalingStrategy());
            this.cameraInstance.setDisplayConfiguration(this.displayConfiguration);
            this.cameraInstance.configureCamera();
            size = this.torchOn;
            if (size != null) {
                this.cameraInstance.setTorch(size);
            }
        }
    }

    public void setPreviewScalingStrategy(PreviewScalingStrategy previewScalingStrategy) {
        this.previewScalingStrategy = previewScalingStrategy;
    }

    public PreviewScalingStrategy getPreviewScalingStrategy() {
        PreviewScalingStrategy previewScalingStrategy = this.previewScalingStrategy;
        if (previewScalingStrategy != null) {
            return previewScalingStrategy;
        }
        if (this.textureView != null) {
            return new CenterCropStrategy();
        }
        return new FitCenterStrategy();
    }

    private void previewSized(Size size) {
        this.previewSize = size;
        if (this.containerSize != null) {
            calculateFrames();
            requestLayout();
            startPreviewIfReady();
        }
    }

    protected Matrix calculateTextureTransform(Size size, Size size2) {
        float f = ((float) size.width) / ((float) size.height);
        float f2 = ((float) size2.width) / ((float) size2.height);
        size2 = 1065353216;
        if (f < f2) {
            size2 = f2 / f;
            f = 1.0f;
        } else {
            f /= f2;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(size2, f);
        matrix.postTranslate((((float) size.width) - (((float) size.width) * size2)) / 2.0f, (((float) size.height) - (((float) size.height) * f)) / 1073741824);
        return matrix;
    }

    private void startPreviewIfReady() {
        Size size = this.currentSurfaceSize;
        if (size != null && this.previewSize != null) {
            Rect rect = this.surfaceRect;
            if (rect == null) {
                return;
            }
            if (this.surfaceView != null && size.equals(new Size(rect.width(), this.surfaceRect.height()))) {
                startCameraPreview(new CameraSurface(this.surfaceView.getHolder()));
            } else if (this.textureView != null && VERSION.SDK_INT >= 14 && this.textureView.getSurfaceTexture() != null) {
                if (this.previewSize != null) {
                    this.textureView.setTransform(calculateTextureTransform(new Size(this.textureView.getWidth(), this.textureView.getHeight()), this.previewSize));
                }
                startCameraPreview(new CameraSurface(this.textureView.getSurfaceTexture()));
            }
        }
    }

    @SuppressLint({"DrawAllocation"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        containerSized(new Size(i3 - i, i4 - i2));
        z = this.surfaceView;
        if (z) {
            i2 = this.surfaceRect;
            if (i2 == 0) {
                z.layout(0, 0, getWidth(), getHeight());
            } else {
                z.layout(i2.left, this.surfaceRect.top, this.surfaceRect.right, this.surfaceRect.bottom);
            }
        } else if (this.textureView && VERSION.SDK_INT >= true) {
            this.textureView.layout(0, 0, getWidth(), getHeight());
        }
    }

    public Rect getFramingRect() {
        return this.framingRect;
    }

    public Rect getPreviewFramingRect() {
        return this.previewFramingRect;
    }

    public CameraSettings getCameraSettings() {
        return this.cameraSettings;
    }

    public void setCameraSettings(CameraSettings cameraSettings) {
        this.cameraSettings = cameraSettings;
    }

    public void resume() {
        Util.validateMainThread();
        Log.d(TAG, "resume()");
        initCamera();
        if (this.currentSurfaceSize != null) {
            startPreviewIfReady();
        } else {
            SurfaceView surfaceView = this.surfaceView;
            if (surfaceView != null) {
                surfaceView.getHolder().addCallback(this.surfaceCallback);
            } else if (this.textureView != null && VERSION.SDK_INT >= 14) {
                this.textureView.setSurfaceTextureListener(surfaceTextureListener());
            }
        }
        requestLayout();
        this.rotationListener.listen(getContext(), this.rotationCallback);
    }

    public void pause() {
        Util.validateMainThread();
        Log.d(TAG, "pause()");
        this.openedOrientation = -1;
        CameraInstance cameraInstance = this.cameraInstance;
        if (cameraInstance != null) {
            cameraInstance.close();
            this.cameraInstance = null;
            this.previewActive = false;
        }
        if (this.currentSurfaceSize == null) {
            SurfaceView surfaceView = this.surfaceView;
            if (surfaceView != null) {
                surfaceView.getHolder().removeCallback(this.surfaceCallback);
            }
        }
        if (this.currentSurfaceSize == null && this.textureView != null && VERSION.SDK_INT >= 14) {
            this.textureView.setSurfaceTextureListener(null);
        }
        this.containerSize = null;
        this.previewSize = null;
        this.previewFramingRect = null;
        this.rotationListener.stop();
        this.fireState.previewStopped();
    }

    public Size getFramingRectSize() {
        return this.framingRectSize;
    }

    public void setFramingRectSize(Size size) {
        this.framingRectSize = size;
    }

    public double getMarginFraction() {
        return this.marginFraction;
    }

    public void setMarginFraction(double d) {
        if (d < 0.5d) {
            this.marginFraction = d;
            return;
        }
        throw new IllegalArgumentException("The margin fraction must be less than 0.5");
    }

    public boolean isUseTextureView() {
        return this.useTextureView;
    }

    public void setUseTextureView(boolean z) {
        this.useTextureView = z;
    }

    protected boolean isActive() {
        return this.cameraInstance != null;
    }

    private int getDisplayRotation() {
        return this.windowManager.getDefaultDisplay().getRotation();
    }

    private void initCamera() {
        if (this.cameraInstance != null) {
            Log.w(TAG, "initCamera called twice");
            return;
        }
        this.cameraInstance = new CameraInstance(getContext());
        this.cameraInstance.setCameraSettings(this.cameraSettings);
        this.cameraInstance.setReadyHandler(this.stateHandler);
        this.cameraInstance.open();
        this.openedOrientation = getDisplayRotation();
    }

    private void startCameraPreview(CameraSurface cameraSurface) {
        if (!this.previewActive && this.cameraInstance != null) {
            Log.i(TAG, "Starting preview");
            this.cameraInstance.setSurface(cameraSurface);
            this.cameraInstance.startPreview();
            this.previewActive = true;
            previewStarted();
            this.fireState.previewStarted();
        }
    }

    public CameraInstance getCameraInstance() {
        return this.cameraInstance;
    }

    public boolean isPreviewActive() {
        return this.previewActive;
    }

    protected Rect calculateFramingRect(Rect rect, Rect rect2) {
        Rect rect3 = new Rect(rect);
        rect3.intersect(rect2);
        if (this.framingRectSize != null) {
            rect3.inset(Math.max(0, (rect3.width() - this.framingRectSize.width) / 2), Math.max(0, (rect3.height() - this.framingRectSize.height) / 2));
            return rect3;
        }
        double width = (double) rect3.width();
        double d = this.marginFraction;
        Double.isNaN(width);
        width *= d;
        d = (double) rect3.height();
        double d2 = this.marginFraction;
        Double.isNaN(d);
        rect = (int) Math.min(width, d * d2);
        rect3.inset(rect, rect);
        if (rect3.height() > rect3.width()) {
            rect3.inset(0, (rect3.height() - rect3.width()) / 2);
        }
        return rect3;
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Parcelable bundle = new Bundle();
        bundle.putParcelable("super", onSaveInstanceState);
        bundle.putBoolean("torch", this.torchOn);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable("super"));
            setTorch(bundle.getBoolean("torch"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
