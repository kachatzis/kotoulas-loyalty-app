package com.journeyapps.barcodescanner;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.WindowManager;

public class RotationListener {
    private RotationCallback callback;
    private int lastRotation;
    private OrientationEventListener orientationEventListener;
    private WindowManager windowManager;

    public void listen(Context context, RotationCallback rotationCallback) {
        stop();
        context = context.getApplicationContext();
        this.callback = rotationCallback;
        this.windowManager = (WindowManager) context.getSystemService("window");
        this.orientationEventListener = new OrientationEventListener(context, 3) {
            public void onOrientationChanged(int i) {
                i = RotationListener.this.windowManager;
                RotationCallback access$100 = RotationListener.this.callback;
                if (RotationListener.this.windowManager != null && access$100 != null) {
                    i = i.getDefaultDisplay().getRotation();
                    if (i != RotationListener.this.lastRotation) {
                        RotationListener.this.lastRotation = i;
                        access$100.onRotationChanged(i);
                    }
                }
            }
        };
        this.orientationEventListener.enable();
        this.lastRotation = this.windowManager.getDefaultDisplay().getRotation();
    }

    public void stop() {
        OrientationEventListener orientationEventListener = this.orientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
        this.orientationEventListener = null;
        this.windowManager = null;
        this.callback = null;
    }
}
