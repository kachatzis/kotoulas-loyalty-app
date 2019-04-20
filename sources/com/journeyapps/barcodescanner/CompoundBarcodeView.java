package com.journeyapps.barcodescanner;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.C0391R;
import com.google.zxing.client.android.DecodeFormatManager;
import com.google.zxing.client.android.DecodeHintManager;
import com.google.zxing.client.android.Intents.Scan;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CompoundBarcodeView extends FrameLayout {
    private BarcodeView barcodeView;
    private TextView statusView;
    private TorchListener torchListener;
    private ViewfinderView viewFinder;

    public interface TorchListener {
        void onTorchOff();

        void onTorchOn();
    }

    private class WrappedCallback implements BarcodeCallback {
        private BarcodeCallback delegate;

        public WrappedCallback(BarcodeCallback barcodeCallback) {
            this.delegate = barcodeCallback;
        }

        public void barcodeResult(BarcodeResult barcodeResult) {
            this.delegate.barcodeResult(barcodeResult);
        }

        public void possibleResultPoints(List<ResultPoint> list) {
            for (ResultPoint addPossibleResultPoint : list) {
                CompoundBarcodeView.this.viewFinder.addPossibleResultPoint(addPossibleResultPoint);
            }
            this.delegate.possibleResultPoints(list);
        }
    }

    public CompoundBarcodeView(Context context) {
        super(context);
        initialize();
    }

    public CompoundBarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(attributeSet);
    }

    public CompoundBarcodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(attributeSet);
    }

    private void initialize(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0391R.styleable.zxing_view);
        int resourceId = obtainStyledAttributes.getResourceId(C0391R.styleable.zxing_view_zxing_scanner_layout, C0391R.layout.zxing_barcode_scanner);
        obtainStyledAttributes.recycle();
        inflate(getContext(), resourceId, this);
        this.barcodeView = (BarcodeView) findViewById(C0391R.id.zxing_barcode_surface);
        BarcodeView barcodeView = this.barcodeView;
        if (barcodeView != null) {
            barcodeView.initializeAttributes(attributeSet);
            this.viewFinder = (ViewfinderView) findViewById(C0391R.id.zxing_viewfinder_view);
            attributeSet = this.viewFinder;
            if (attributeSet != null) {
                attributeSet.setCameraPreview(this.barcodeView);
                this.statusView = (TextView) findViewById(C0391R.id.zxing_status_view);
                return;
            }
            throw new IllegalArgumentException("There is no a com.journeyapps.barcodescanner.ViewfinderView on provided layout with the id \"zxing_viewfinder_view\".");
        }
        throw new IllegalArgumentException("There is no a com.journeyapps.barcodescanner.BarcodeView on provided layout with the id \"zxing_barcode_surface\".");
    }

    private void initialize() {
        initialize(null);
    }

    public void initializeFromIntent(Intent intent) {
        Collection parseDecodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
        Map parseDecodeHints = DecodeHintManager.parseDecodeHints(intent);
        CameraSettings cameraSettings = new CameraSettings();
        if (intent.hasExtra(Scan.CAMERA_ID)) {
            int intExtra = intent.getIntExtra(Scan.CAMERA_ID, -1);
            if (intExtra >= 0) {
                cameraSettings.setRequestedCameraId(intExtra);
            }
        }
        String stringExtra = intent.getStringExtra(Scan.PROMPT_MESSAGE);
        if (stringExtra != null) {
            setStatusText(stringExtra);
        }
        intent = intent.getStringExtra(Scan.CHARACTER_SET);
        new MultiFormatReader().setHints(parseDecodeHints);
        this.barcodeView.setCameraSettings(cameraSettings);
        this.barcodeView.setDecoderFactory(new DefaultDecoderFactory(parseDecodeFormats, parseDecodeHints, intent));
    }

    public void setStatusText(String str) {
        TextView textView = this.statusView;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void pause() {
        this.barcodeView.pause();
    }

    public void resume() {
        this.barcodeView.resume();
    }

    public BarcodeView getBarcodeView() {
        return (BarcodeView) findViewById(C0391R.id.zxing_barcode_surface);
    }

    public ViewfinderView getViewFinder() {
        return this.viewFinder;
    }

    public TextView getStatusView() {
        return this.statusView;
    }

    public void decodeSingle(BarcodeCallback barcodeCallback) {
        this.barcodeView.decodeSingle(new WrappedCallback(barcodeCallback));
    }

    public void decodeContinuous(BarcodeCallback barcodeCallback) {
        this.barcodeView.decodeContinuous(new WrappedCallback(barcodeCallback));
    }

    public void setTorchOn() {
        this.barcodeView.setTorch(true);
        TorchListener torchListener = this.torchListener;
        if (torchListener != null) {
            torchListener.onTorchOn();
        }
    }

    public void setTorchOff() {
        this.barcodeView.setTorch(false);
        TorchListener torchListener = this.torchListener;
        if (torchListener != null) {
            torchListener.onTorchOff();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 27 || i == 80) {
            return true;
        }
        switch (i) {
            case 24:
                setTorchOn();
                return true;
            case 25:
                setTorchOff();
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    public void setTorchListener(TorchListener torchListener) {
        this.torchListener = torchListener;
    }
}
