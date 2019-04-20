package com.journeyapps.barcodescanner;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import com.google.zxing.DecodeHintType;
import com.google.zxing.client.android.C0391R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarcodeView extends CameraPreview {
    private BarcodeCallback callback = null;
    private DecodeMode decodeMode = DecodeMode.NONE;
    private DecoderFactory decoderFactory;
    private DecoderThread decoderThread;
    private final Callback resultCallback = new C04011();
    private Handler resultHandler;

    /* renamed from: com.journeyapps.barcodescanner.BarcodeView$1 */
    class C04011 implements Callback {
        C04011() {
        }

        public boolean handleMessage(Message message) {
            if (message.what == C0391R.id.zxing_decode_succeeded) {
                BarcodeResult barcodeResult = (BarcodeResult) message.obj;
                if (!(barcodeResult == null || BarcodeView.this.callback == null || BarcodeView.this.decodeMode == DecodeMode.NONE)) {
                    BarcodeView.this.callback.barcodeResult(barcodeResult);
                    if (BarcodeView.this.decodeMode == DecodeMode.SINGLE) {
                        BarcodeView.this.stopDecoding();
                    }
                }
                return true;
            } else if (message.what == C0391R.id.zxing_decode_failed) {
                return true;
            } else {
                if (message.what != C0391R.id.zxing_possible_result_points) {
                    return null;
                }
                List list = (List) message.obj;
                if (!(BarcodeView.this.callback == null || BarcodeView.this.decodeMode == DecodeMode.NONE)) {
                    BarcodeView.this.callback.possibleResultPoints(list);
                }
                return true;
            }
        }
    }

    private enum DecodeMode {
        NONE,
        SINGLE,
        CONTINUOUS
    }

    public BarcodeView(Context context) {
        super(context);
        initialize(context, null);
    }

    public BarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context, attributeSet);
    }

    public BarcodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context, attributeSet);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        this.decoderFactory = new DefaultDecoderFactory();
        this.resultHandler = new Handler(this.resultCallback);
    }

    public void setDecoderFactory(DecoderFactory decoderFactory) {
        Util.validateMainThread();
        this.decoderFactory = decoderFactory;
        decoderFactory = this.decoderThread;
        if (decoderFactory != null) {
            decoderFactory.setDecoder(createDecoder());
        }
    }

    private Decoder createDecoder() {
        if (this.decoderFactory == null) {
            this.decoderFactory = createDefaultDecoderFactory();
        }
        DecoderResultPointCallback decoderResultPointCallback = new DecoderResultPointCallback();
        Map hashMap = new HashMap();
        hashMap.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, decoderResultPointCallback);
        Decoder createDecoder = this.decoderFactory.createDecoder(hashMap);
        decoderResultPointCallback.setDecoder(createDecoder);
        return createDecoder;
    }

    public DecoderFactory getDecoderFactory() {
        return this.decoderFactory;
    }

    public void decodeSingle(BarcodeCallback barcodeCallback) {
        this.decodeMode = DecodeMode.SINGLE;
        this.callback = barcodeCallback;
        startDecoderThread();
    }

    public void decodeContinuous(BarcodeCallback barcodeCallback) {
        this.decodeMode = DecodeMode.CONTINUOUS;
        this.callback = barcodeCallback;
        startDecoderThread();
    }

    public void stopDecoding() {
        this.decodeMode = DecodeMode.NONE;
        this.callback = null;
        stopDecoderThread();
    }

    protected DecoderFactory createDefaultDecoderFactory() {
        return new DefaultDecoderFactory();
    }

    private void startDecoderThread() {
        stopDecoderThread();
        if (this.decodeMode != DecodeMode.NONE && isPreviewActive()) {
            this.decoderThread = new DecoderThread(getCameraInstance(), createDecoder(), this.resultHandler);
            this.decoderThread.setCropRect(getPreviewFramingRect());
            this.decoderThread.start();
        }
    }

    protected void previewStarted() {
        super.previewStarted();
        startDecoderThread();
    }

    private void stopDecoderThread() {
        DecoderThread decoderThread = this.decoderThread;
        if (decoderThread != null) {
            decoderThread.stop();
            this.decoderThread = null;
        }
    }

    public void pause() {
        stopDecoderThread();
        super.pause();
    }
}
