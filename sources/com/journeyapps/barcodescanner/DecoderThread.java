package com.journeyapps.barcodescanner;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.android.C0391R;
import com.journeyapps.barcodescanner.camera.CameraInstance;
import com.journeyapps.barcodescanner.camera.PreviewCallback;

public class DecoderThread {
    private static final String TAG = "DecoderThread";
    private final Object LOCK = new Object();
    private final Callback callback = new C04101();
    private CameraInstance cameraInstance;
    private Rect cropRect;
    private Decoder decoder;
    private Handler handler;
    private final PreviewCallback previewCallback = new C06012();
    private Handler resultHandler;
    private boolean running = false;
    private HandlerThread thread;

    /* renamed from: com.journeyapps.barcodescanner.DecoderThread$1 */
    class C04101 implements Callback {
        C04101() {
        }

        public boolean handleMessage(Message message) {
            if (message.what == C0391R.id.zxing_decode) {
                DecoderThread.this.decode((SourceData) message.obj);
            }
            return true;
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.DecoderThread$2 */
    class C06012 implements PreviewCallback {
        C06012() {
        }

        public void onPreview(SourceData sourceData) {
            synchronized (DecoderThread.this.LOCK) {
                if (DecoderThread.this.running) {
                    DecoderThread.this.handler.obtainMessage(C0391R.id.zxing_decode, sourceData).sendToTarget();
                }
            }
        }
    }

    public DecoderThread(CameraInstance cameraInstance, Decoder decoder, Handler handler) {
        Util.validateMainThread();
        this.cameraInstance = cameraInstance;
        this.decoder = decoder;
        this.resultHandler = handler;
    }

    public Decoder getDecoder() {
        return this.decoder;
    }

    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    public Rect getCropRect() {
        return this.cropRect;
    }

    public void setCropRect(Rect rect) {
        this.cropRect = rect;
    }

    public void start() {
        Util.validateMainThread();
        this.thread = new HandlerThread(TAG);
        this.thread.start();
        this.handler = new Handler(this.thread.getLooper(), this.callback);
        this.running = true;
        requestNextPreview();
    }

    public void stop() {
        Util.validateMainThread();
        synchronized (this.LOCK) {
            this.running = false;
            this.handler.removeCallbacksAndMessages(null);
            this.thread.quit();
        }
    }

    private void requestNextPreview() {
        if (this.cameraInstance.isOpen()) {
            this.cameraInstance.requestPreview(this.previewCallback);
        }
    }

    protected LuminanceSource createSource(SourceData sourceData) {
        if (this.cropRect == null) {
            return null;
        }
        return sourceData.createSource();
    }

    private void decode(SourceData sourceData) {
        long currentTimeMillis = System.currentTimeMillis();
        sourceData.setCropRect(this.cropRect);
        LuminanceSource createSource = createSource(sourceData);
        Result decode = createSource != null ? this.decoder.decode(createSource) : null;
        if (decode != null) {
            long currentTimeMillis2 = System.currentTimeMillis();
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Found barcode in ");
            stringBuilder.append(currentTimeMillis2 - currentTimeMillis);
            stringBuilder.append(" ms");
            Log.d(str, stringBuilder.toString());
            if (this.resultHandler != null) {
                sourceData = Message.obtain(this.resultHandler, C0391R.id.zxing_decode_succeeded, new BarcodeResult(decode, sourceData));
                sourceData.setData(new Bundle());
                sourceData.sendToTarget();
            }
        } else {
            sourceData = this.resultHandler;
            if (sourceData != null) {
                Message.obtain(sourceData, C0391R.id.zxing_decode_failed).sendToTarget();
            }
        }
        if (this.resultHandler != null) {
            Message.obtain(this.resultHandler, C0391R.id.zxing_possible_result_points, this.decoder.getPossibleResultPoints()).sendToTarget();
        }
        requestNextPreview();
    }
}
