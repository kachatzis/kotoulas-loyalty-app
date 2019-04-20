package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import com.journeyapps.barcodescanner.Size;

public class FitXYStrategy extends PreviewScalingStrategy {
    private static final String TAG = "FitXYStrategy";

    private static float absRatio(float f) {
        return f < 1.0f ? 1.0f / f : f;
    }

    protected float getScore(Size size, Size size2) {
        if (size.width > 0) {
            if (size.height > 0) {
                float absRatio = (1.0f / absRatio((((float) size.width) * 1.0f) / ((float) size2.width))) / absRatio((((float) size.height) * 1.0f) / ((float) size2.height));
                size = absRatio(((((float) size.width) * 1.0f) / ((float) size.height)) / ((((float) size2.width) * 1065353216) / ((float) size2.height)));
                return absRatio * (((1.0f / size) / size) / size);
            }
        }
        return null;
    }

    public Rect scalePreview(Size size, Size size2) {
        return new Rect(0, 0, size2.width, size2.height);
    }
}
