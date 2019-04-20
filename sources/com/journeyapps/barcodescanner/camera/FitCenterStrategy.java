package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.util.Log;
import com.journeyapps.barcodescanner.Size;

public class FitCenterStrategy extends PreviewScalingStrategy {
    private static final String TAG = "FitCenterStrategy";

    protected float getScore(Size size, Size size2) {
        if (size.width > 0) {
            if (size.height > 0) {
                Size scaleFit = size.scaleFit(size2);
                float f = (((float) scaleFit.width) * 1.0f) / ((float) size.width);
                if (f > 1.0f) {
                    f = (float) Math.pow((double) (1065353216 / f), 1.1d);
                }
                size = ((((float) size2.width) * 1065353216) / ((float) scaleFit.width)) * ((((float) size2.height) * 1065353216) / ((float) scaleFit.height));
                return f * (((1.0f / size) / size) / size);
            }
        }
        return null;
    }

    public Rect scalePreview(Size size, Size size2) {
        Size scaleFit = size.scaleFit(size2);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Preview: ");
        stringBuilder.append(size);
        stringBuilder.append("; Scaled: ");
        stringBuilder.append(scaleFit);
        stringBuilder.append("; Want: ");
        stringBuilder.append(size2);
        Log.i(str, stringBuilder.toString());
        size = (scaleFit.width - size2.width) / 2;
        int i = (scaleFit.height - size2.height) / 2;
        return new Rect(-size, -i, scaleFit.width - size, scaleFit.height - i);
    }
}
