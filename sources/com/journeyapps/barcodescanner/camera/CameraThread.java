package com.journeyapps.barcodescanner.camera;

import android.os.Handler;
import android.os.HandlerThread;

class CameraThread {
    private static final String TAG = "CameraThread";
    private static CameraThread instance;
    private final Object LOCK = new Object();
    private Handler handler;
    private int openCount = 0;
    private HandlerThread thread;

    public static CameraThread getInstance() {
        if (instance == null) {
            instance = new CameraThread();
        }
        return instance;
    }

    private CameraThread() {
    }

    protected void enqueue(Runnable runnable) {
        synchronized (this.LOCK) {
            checkRunning();
            this.handler.post(runnable);
        }
    }

    protected void enqueueDelayed(Runnable runnable, long j) {
        synchronized (this.LOCK) {
            checkRunning();
            this.handler.postDelayed(runnable, j);
        }
    }

    private void checkRunning() {
        synchronized (this.LOCK) {
            if (this.handler == null) {
                if (this.openCount > 0) {
                    this.thread = new HandlerThread(TAG);
                    this.thread.start();
                    this.handler = new Handler(this.thread.getLooper());
                } else {
                    throw new IllegalStateException("CameraThread is not open");
                }
            }
        }
    }

    private void quit() {
        synchronized (this.LOCK) {
            this.thread.quit();
            this.thread = null;
            this.handler = null;
        }
    }

    protected void decrementInstances() {
        synchronized (this.LOCK) {
            this.openCount--;
            if (this.openCount == 0) {
                quit();
            }
        }
    }

    protected void incrementAndEnqueue(Runnable runnable) {
        synchronized (this.LOCK) {
            this.openCount++;
            enqueue(runnable);
        }
    }
}
