package com.google.zxing.integration.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.google.zxing.client.android.Intents.Scan;
import com.journeyapps.barcodescanner.CaptureActivity;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class IntentIntegrator {
    public static final Collection<String> ALL_CODE_TYPES = null;
    public static final Collection<String> DATA_MATRIX_TYPES = Collections.singleton("DATA_MATRIX");
    public static final Collection<String> ONE_D_CODE_TYPES = list("UPC_A", "UPC_E", "EAN_8", "EAN_13", "CODE_39", "CODE_93", "CODE_128", "ITF", "RSS_14", "RSS_EXPANDED");
    public static final Collection<String> PRODUCT_CODE_TYPES = list("UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14");
    public static final Collection<String> QR_CODE_TYPES = Collections.singleton("QR_CODE");
    public static final int REQUEST_CODE = 49374;
    private static final String TAG = "IntentIntegrator";
    private final Activity activity;
    private Class<?> captureActivity;
    private Collection<String> desiredBarcodeFormats;
    private Fragment fragment;
    private final Map<String, Object> moreExtras = new HashMap(3);
    private android.support.v4.app.Fragment supportFragment;

    protected Class<?> getDefaultCaptureActivity() {
        return CaptureActivity.class;
    }

    public IntentIntegrator(Activity activity) {
        this.activity = activity;
    }

    public Class<?> getCaptureActivity() {
        if (this.captureActivity == null) {
            this.captureActivity = getDefaultCaptureActivity();
        }
        return this.captureActivity;
    }

    public IntentIntegrator setCaptureActivity(Class<?> cls) {
        this.captureActivity = cls;
        return this;
    }

    public static IntentIntegrator forSupportFragment(android.support.v4.app.Fragment fragment) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(fragment.getActivity());
        intentIntegrator.supportFragment = fragment;
        return intentIntegrator;
    }

    @TargetApi(11)
    public static IntentIntegrator forFragment(Fragment fragment) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(fragment.getActivity());
        intentIntegrator.fragment = fragment;
        return intentIntegrator;
    }

    public Map<String, ?> getMoreExtras() {
        return this.moreExtras;
    }

    public final IntentIntegrator addExtra(String str, Object obj) {
        this.moreExtras.put(str, obj);
        return this;
    }

    public final IntentIntegrator setPrompt(String str) {
        if (str != null) {
            addExtra(Scan.PROMPT_MESSAGE, str);
        }
        return this;
    }

    public IntentIntegrator setOrientationLocked(boolean z) {
        addExtra(Scan.ORIENTATION_LOCKED, Boolean.valueOf(z));
        return this;
    }

    public IntentIntegrator setCameraId(int i) {
        if (i >= 0) {
            addExtra(Scan.CAMERA_ID, Integer.valueOf(i));
        }
        return this;
    }

    public IntentIntegrator setBeepEnabled(boolean z) {
        addExtra(Scan.BEEP_ENABLED, Boolean.valueOf(z));
        return this;
    }

    public IntentIntegrator setBarcodeImageEnabled(boolean z) {
        addExtra(Scan.BARCODE_IMAGE_ENABLED, Boolean.valueOf(z));
        return this;
    }

    public IntentIntegrator setDesiredBarcodeFormats(Collection<String> collection) {
        this.desiredBarcodeFormats = collection;
        return this;
    }

    public final void initiateScan() {
        startActivityForResult(createScanIntent(), REQUEST_CODE);
    }

    public Intent createScanIntent() {
        Intent intent = new Intent(this.activity, getCaptureActivity());
        intent.setAction(Scan.ACTION);
        if (this.desiredBarcodeFormats != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String str : this.desiredBarcodeFormats) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(',');
                }
                stringBuilder.append(str);
            }
            intent.putExtra(Scan.FORMATS, stringBuilder.toString());
        }
        intent.addFlags(67108864);
        intent.addFlags(524288);
        attachMoreExtras(intent);
        return intent;
    }

    public final void initiateScan(Collection<String> collection) {
        setDesiredBarcodeFormats(collection);
        initiateScan();
    }

    protected void startActivityForResult(Intent intent, int i) {
        if (this.fragment == null) {
            android.support.v4.app.Fragment fragment = this.supportFragment;
            if (fragment != null) {
                fragment.startActivityForResult(intent, i);
            } else {
                this.activity.startActivityForResult(intent, i);
            }
        } else if (VERSION.SDK_INT >= 11) {
            this.fragment.startActivityForResult(intent, i);
        }
    }

    protected void startActivity(Intent intent) {
        if (this.fragment == null) {
            android.support.v4.app.Fragment fragment = this.supportFragment;
            if (fragment != null) {
                fragment.startActivity(intent);
            } else {
                this.activity.startActivity(intent);
            }
        } else if (VERSION.SDK_INT >= 11) {
            this.fragment.startActivity(intent);
        }
    }

    public static IntentResult parseActivityResult(int i, int i2, Intent intent) {
        IntentResult intentResult = null;
        if (i != REQUEST_CODE) {
            return null;
        }
        if (i2 != -1) {
            return new IntentResult();
        }
        String stringExtra = intent.getStringExtra(Scan.RESULT);
        String stringExtra2 = intent.getStringExtra(Scan.RESULT_FORMAT);
        byte[] byteArrayExtra = intent.getByteArrayExtra(Scan.RESULT_BYTES);
        i = intent.getIntExtra(Scan.RESULT_ORIENTATION, Integer.MIN_VALUE);
        if (i != Integer.MIN_VALUE) {
            intentResult = Integer.valueOf(i);
        }
        return new IntentResult(stringExtra, stringExtra2, byteArrayExtra, intentResult, intent.getStringExtra(Scan.RESULT_ERROR_CORRECTION_LEVEL), intent.getStringExtra(Scan.RESULT_BARCODE_IMAGE_PATH));
    }

    private static List<String> list(String... strArr) {
        return Collections.unmodifiableList(Arrays.asList(strArr));
    }

    private void attachMoreExtras(Intent intent) {
        for (Entry entry : this.moreExtras.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Integer) {
                intent.putExtra(str, (Integer) value);
            } else if (value instanceof Long) {
                intent.putExtra(str, (Long) value);
            } else if (value instanceof Boolean) {
                intent.putExtra(str, (Boolean) value);
            } else if (value instanceof Double) {
                intent.putExtra(str, (Double) value);
            } else if (value instanceof Float) {
                intent.putExtra(str, (Float) value);
            } else if (value instanceof Bundle) {
                intent.putExtra(str, (Bundle) value);
            } else {
                intent.putExtra(str, value.toString());
            }
        }
    }
}
