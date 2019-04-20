package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.util.Log;
import com.journeyapps.barcodescanner.Size;

public class CenterCropStrategy extends PreviewScalingStrategy {
    private static final String TAG = "CenterCropStrategy";

    protected float getScore(Size size, Size size2) {
        if (size.width > 0) {
            if (size.height > 0) {
                Size scaleCrop = size.scaleCrop(size2);
                float f = (((float) scaleCrop.width) * 1.0f) / ((float) size.width);
                if (f > 1.0f) {
                    f = (float) Math.pow((double) (1065353216 / f), 1.1d);
                }
                size = ((((float) scaleCrop.width) * 1065353216) / ((float) size2.width)) + ((((float) scaleCrop.height) * 1.0f) / ((float) size2.height));
                return f * ((1.0f / size) / size);
            }
        }
        return null;
    }

    public Rect scalePreview(Size size, Size size2) {
        Size scaleCrop = size.scaleCrop(size2);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Preview: ");
        stringBuilder.append(size);
        stringBuilder.append("; Scaled: ");
        stringBuilder.append(scaleCrop);
        stringBuilder.append("; Want: ");
        stringBuilder.append(size2);
        Log.i(str, stringBuilder.toString());
        size = (scaleCrop.width - size2.width) / 2;
        int i = (scaleCrop.height - size2.height) / 2;
        return new Rect(-size, -i, scaleCrop.width - size, scaleCrop.height - i);
    }
}
