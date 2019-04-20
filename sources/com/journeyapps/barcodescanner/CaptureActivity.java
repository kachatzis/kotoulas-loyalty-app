package com.journeyapps.barcodescanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import com.google.zxing.client.android.C0391R;

public class CaptureActivity extends Activity {
    private CompoundBarcodeView barcodeScannerView;
    private CaptureManager capture;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.barcodeScannerView = initializeContent();
        this.capture = new CaptureManager(this, this.barcodeScannerView);
        this.capture.initializeFromIntent(getIntent(), bundle);
        this.capture.decode();
    }

    protected CompoundBarcodeView initializeContent() {
        setContentView(C0391R.layout.zxing_capture);
        return (CompoundBarcodeView) findViewById(C0391R.id.zxing_barcode_scanner);
    }

    protected void onResume() {
        super.onResume();
        this.capture.onResume();
    }

    protected void onPause() {
        super.onPause();
        this.capture.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.capture.onDestroy();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.capture.onSaveInstanceState(bundle);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.capture.onRequestPermissionsResult(i, strArr, iArr);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!this.barcodeScannerView.onKeyDown(i, keyEvent)) {
            if (super.onKeyDown(i, keyEvent) == 0) {
                return false;
            }
        }
        return true;
    }
}
