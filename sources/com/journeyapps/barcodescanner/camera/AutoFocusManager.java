package com.journeyapps.barcodescanner.camera;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;

public final class AutoFocusManager {
    private static final long AUTO_FOCUS_INTERVAL_MS = 2000;
    private static final Collection<String> FOCUS_MODES_CALLING_AF = new ArrayList(2);
    private static final String TAG = "AutoFocusManager";
    private int MESSAGE_FOCUS = 1;
    private final AutoFocusCallback autoFocusCallback = new C04142();
    private final Camera camera;
    private final Callback focusHandlerCallback = new C04121();
    private boolean focusing;
    private Handler handler = new Handler(this.focusHandlerCallback);
    private boolean stopped;
    private final boolean useAutoFocus;

    /* renamed from: com.journeyapps.barcodescanner.camera.AutoFocusManager$1 */
    class C04121 implements Callback {
        C04121() {
        }

        public boolean handleMessage(Message message) {
            if (message.what != AutoFocusManager.this.MESSAGE_FOCUS) {
                return null;
            }
            AutoFocusManager.this.focus();
            return true;
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.camera.AutoFocusManager$2 */
    class C04142 implements AutoFocusCallback {

        /* renamed from: com.journeyapps.barcodescanner.camera.AutoFocusManager$2$1 */
        class C04131 implements Runnable {
            C04131() {
            }

            public void run() {
                AutoFocusManager.this.focusing = false;
                AutoFocusManager.this.autoFocusAgainLater();
            }
        }

        C04142() {
        }

        public void onAutoFocus(boolean z, Camera camera) {
            AutoFocusManager.this.handler.post(new C04131());
        }
    }

    static {
        FOCUS_MODES_CALLING_AF.add("auto");
        FOCUS_MODES_CALLING_AF.add("macro");
    }

    public AutoFocusManager(Camera camera, CameraSettings cameraSettings) {
        boolean z = true;
        this.camera = camera;
        camera = camera.getParameters().getFocusMode();
        if (cameraSettings.isAutoFocusEnabled() == null || FOCUS_MODES_CALLING_AF.contains(camera) == null) {
            z = false;
        }
        this.useAutoFocus = z;
        cameraSettings = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Current focus mode '");
        stringBuilder.append(camera);
        stringBuilder.append("'; use auto focus? ");
        stringBuilder.append(this.useAutoFocus);
        Log.i(cameraSettings, stringBuilder.toString());
        start();
    }

    private synchronized void autoFocusAgainLater() {
        if (!(this.stopped || this.handler.hasMessages(this.MESSAGE_FOCUS))) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(this.MESSAGE_FOCUS), AUTO_FOCUS_INTERVAL_MS);
        }
    }

    public void start() {
        this.stopped = false;
        focus();
    }

    private void focus() {
        if (this.useAutoFocus && !this.stopped && !this.focusing) {
            try {
                this.camera.autoFocus(this.autoFocusCallback);
                this.focusing = true;
            } catch (Throwable e) {
                Log.w(TAG, "Unexpected exception while focusing", e);
                autoFocusAgainLater();
            }
        }
    }

    private void cancelOutstandingTask() {
        this.handler.removeMessages(this.MESSAGE_FOCUS);
    }

    public void stop() {
        this.stopped = true;
        this.focusing = false;
        cancelOutstandingTask();
        if (this.useAutoFocus) {
            try {
                this.camera.cancelAutoFocus();
            } catch (Throwable e) {
                Log.w(TAG, "Unexpected exception while cancelling focusing", e);
            }
        }
    }
}
