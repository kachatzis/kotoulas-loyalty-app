package com.journeyapps.barcodescanner.camera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build.VERSION;
import android.view.SurfaceHolder;
import java.io.IOException;

public class CameraSurface {
    private SurfaceHolder surfaceHolder;
    private SurfaceTexture surfaceTexture;

    public CameraSurface(SurfaceHolder surfaceHolder) {
        if (surfaceHolder != null) {
            this.surfaceHolder = surfaceHolder;
            return;
        }
        throw new IllegalArgumentException("surfaceHolder may not be null");
    }

    public CameraSurface(SurfaceTexture surfaceTexture) {
        if (surfaceTexture != null) {
            this.surfaceTexture = surfaceTexture;
            return;
        }
        throw new IllegalArgumentException("surfaceTexture may not be null");
    }

    public SurfaceHolder getSurfaceHolder() {
        return this.surfaceHolder;
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.surfaceTexture;
    }

    public void setPreview(Camera camera) throws IOException {
        SurfaceHolder surfaceHolder = this.surfaceHolder;
        if (surfaceHolder != null) {
            camera.setPreviewDisplay(surfaceHolder);
        } else if (VERSION.SDK_INT >= 11) {
            camera.setPreviewTexture(this.surfaceTexture);
        } else {
            throw new IllegalStateException("SurfaceTexture not supported.");
        }
    }
}
