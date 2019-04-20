package com.journeyapps.barcodescanner;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.client.android.C0391R;
import com.google.zxing.client.android.InactivityTimer;
import com.google.zxing.client.android.Intents.Scan;
import com.journeyapps.barcodescanner.CameraPreview.StateListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class CaptureManager {
    private static final long DELAY_BEEP = 150;
    private static final String SAVED_ORIENTATION_LOCK = "SAVED_ORIENTATION_LOCK";
    private static final String TAG = "CaptureManager";
    private static int cameraPermissionReqCode = 250;
    private Activity activity;
    private boolean askedPermission = false;
    private CompoundBarcodeView barcodeView;
    private BeepManager beepManager;
    private BarcodeCallback callback = new C05991();
    private boolean destroyed = false;
    private Handler handler;
    private InactivityTimer inactivityTimer;
    private int orientationLock = -1;
    private boolean returnBarcodeImagePath = false;
    private final StateListener stateListener = new C06002();

    /* renamed from: com.journeyapps.barcodescanner.CaptureManager$3 */
    class C04073 implements Runnable {
        C04073() {
        }

        public void run() {
            Log.d(CaptureManager.TAG, "Finishing due to inactivity");
            CaptureManager.this.finish();
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.CaptureManager$4 */
    class C04084 implements OnClickListener {
        C04084() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            CaptureManager.this.finish();
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.CaptureManager$5 */
    class C04095 implements OnCancelListener {
        C04095() {
        }

        public void onCancel(DialogInterface dialogInterface) {
            CaptureManager.this.finish();
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.CaptureManager$1 */
    class C05991 implements BarcodeCallback {
        public void possibleResultPoints(List<ResultPoint> list) {
        }

        C05991() {
        }

        public void barcodeResult(final BarcodeResult barcodeResult) {
            CaptureManager.this.barcodeView.pause();
            CaptureManager.this.beepManager.playBeepSoundAndVibrate();
            CaptureManager.this.handler.postDelayed(new Runnable() {
                public void run() {
                    CaptureManager.this.returnResult(barcodeResult);
                }
            }, 150);
        }
    }

    /* renamed from: com.journeyapps.barcodescanner.CaptureManager$2 */
    class C06002 implements StateListener {
        public void previewSized() {
        }

        public void previewStarted() {
        }

        public void previewStopped() {
        }

        C06002() {
        }

        public void cameraError(Exception exception) {
            CaptureManager.this.displayFrameworkBugMessageAndExit();
        }
    }

    public CaptureManager(Activity activity, CompoundBarcodeView compoundBarcodeView) {
        this.activity = activity;
        this.barcodeView = compoundBarcodeView;
        compoundBarcodeView.getBarcodeView().addStateListener(this.stateListener);
        this.handler = new Handler();
        this.inactivityTimer = new InactivityTimer(activity, new C04073());
        this.beepManager = new BeepManager(activity);
    }

    public void initializeFromIntent(Intent intent, Bundle bundle) {
        this.activity.getWindow().addFlags(128);
        if (bundle != null) {
            this.orientationLock = bundle.getInt(SAVED_ORIENTATION_LOCK, -1);
        }
        if (intent != null) {
            if (this.orientationLock == -1 && intent.getBooleanExtra(Scan.ORIENTATION_LOCKED, true) != null) {
                lockOrientation();
            }
            if (Scan.ACTION.equals(intent.getAction()) != null) {
                this.barcodeView.initializeFromIntent(intent);
            }
            if (intent.getBooleanExtra(Scan.BEEP_ENABLED, true) == null) {
                this.beepManager.setBeepEnabled(false);
                this.beepManager.updatePrefs();
            }
            if (intent.getBooleanExtra(Scan.BARCODE_IMAGE_ENABLED, false) != null) {
                this.returnBarcodeImagePath = true;
            }
        }
    }

    protected void lockOrientation() {
        if (this.orientationLock == -1) {
            int rotation = this.activity.getWindowManager().getDefaultDisplay().getRotation();
            int i = this.activity.getResources().getConfiguration().orientation;
            int i2 = 0;
            if (i == 2) {
                if (rotation != 0) {
                    if (rotation != 1) {
                        i2 = 8;
                    }
                }
            } else if (i == 1) {
                if (rotation != 0) {
                    if (rotation != 3) {
                        i2 = 9;
                    }
                }
                i2 = 1;
            }
            this.orientationLock = i2;
        }
        this.activity.setRequestedOrientation(this.orientationLock);
    }

    public void decode() {
        this.barcodeView.decodeSingle(this.callback);
    }

    public void onResume() {
        if (VERSION.SDK_INT >= 23) {
            openCameraWithPermission();
        } else {
            this.barcodeView.resume();
        }
        this.beepManager.updatePrefs();
        this.inactivityTimer.start();
    }

    @TargetApi(23)
    private void openCameraWithPermission() {
        if (ContextCompat.checkSelfPermission(this.activity, "android.permission.CAMERA") == 0) {
            this.barcodeView.resume();
        } else if (!this.askedPermission) {
            ActivityCompat.requestPermissions(this.activity, new String[]{"android.permission.CAMERA"}, cameraPermissionReqCode);
            this.askedPermission = true;
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != cameraPermissionReqCode) {
            return;
        }
        if (iArr.length <= 0 || iArr[0] != 0) {
            displayFrameworkBugMessageAndExit();
        } else {
            this.barcodeView.resume();
        }
    }

    public void onPause() {
        this.barcodeView.pause();
        this.inactivityTimer.cancel();
        this.beepManager.close();
    }

    public void onDestroy() {
        this.destroyed = true;
        this.inactivityTimer.cancel();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(SAVED_ORIENTATION_LOCK, this.orientationLock);
    }

    public static Intent resultIntent(BarcodeResult barcodeResult, String str) {
        Intent intent = new Intent(Scan.ACTION);
        intent.addFlags(524288);
        intent.putExtra(Scan.RESULT, barcodeResult.toString());
        intent.putExtra(Scan.RESULT_FORMAT, barcodeResult.getBarcodeFormat().toString());
        byte[] rawBytes = barcodeResult.getRawBytes();
        if (rawBytes != null && rawBytes.length > 0) {
            intent.putExtra(Scan.RESULT_BYTES, rawBytes);
        }
        barcodeResult = barcodeResult.getResultMetadata();
        if (barcodeResult != null) {
            if (barcodeResult.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
                intent.putExtra(Scan.RESULT_UPC_EAN_EXTENSION, barcodeResult.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
            }
            Number number = (Number) barcodeResult.get(ResultMetadataType.ORIENTATION);
            if (number != null) {
                intent.putExtra(Scan.RESULT_ORIENTATION, number.intValue());
            }
            String str2 = (String) barcodeResult.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
            if (str2 != null) {
                intent.putExtra(Scan.RESULT_ERROR_CORRECTION_LEVEL, str2);
            }
            Iterable<byte[]> iterable = (Iterable) barcodeResult.get(ResultMetadataType.BYTE_SEGMENTS);
            if (iterable != null) {
                int i = 0;
                for (byte[] bArr : iterable) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(Scan.RESULT_BYTE_SEGMENTS_PREFIX);
                    stringBuilder.append(i);
                    intent.putExtra(stringBuilder.toString(), bArr);
                    i++;
                }
            }
        }
        if (str != null) {
            intent.putExtra(Scan.RESULT_BARCODE_IMAGE_PATH, str);
        }
        return intent;
    }

    private String getBarcodeImagePath(BarcodeResult barcodeResult) {
        if (this.returnBarcodeImagePath) {
            barcodeResult = barcodeResult.getBitmap();
            try {
                File createTempFile = File.createTempFile("barcodeimage", ".jpg", this.activity.getCacheDir());
                OutputStream fileOutputStream = new FileOutputStream(createTempFile);
                barcodeResult.compress(CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
                return createTempFile.getAbsolutePath();
            } catch (BarcodeResult barcodeResult2) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unable to create temporary file and store bitmap! ");
                stringBuilder.append(barcodeResult2);
                Log.w(str, stringBuilder.toString());
            }
        }
        return null;
    }

    private void finish() {
        this.activity.finish();
    }

    protected void returnResult(BarcodeResult barcodeResult) {
        this.activity.setResult(-1, resultIntent(barcodeResult, getBarcodeImagePath(barcodeResult)));
        finish();
    }

    protected void displayFrameworkBugMessageAndExit() {
        if (!this.activity.isFinishing()) {
            if (!this.destroyed) {
                Builder builder = new Builder(this.activity);
                builder.setTitle(this.activity.getString(C0391R.string.zxing_app_name));
                builder.setMessage(this.activity.getString(C0391R.string.zxing_msg_camera_framework_bug));
                builder.setPositiveButton(C0391R.string.zxing_button_ok, new C04084());
                builder.setOnCancelListener(new C04095());
                builder.show();
            }
        }
    }

    public static int getCameraPermissionReqCode() {
        return cameraPermissionReqCode;
    }

    public static void setCameraPermissionReqCode(int i) {
        cameraPermissionReqCode = i;
    }
}
