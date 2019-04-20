package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.util.Log;
import com.journeyapps.barcodescanner.Size;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class PreviewScalingStrategy {
    private static final String TAG = "PreviewScalingStrategy";

    protected float getScore(Size size, Size size2) {
        return 0.5f;
    }

    public abstract Rect scalePreview(Size size, Size size2);

    public Size getBestPreviewSize(List<Size> list, Size size) {
        list = getBestPreviewOrder(list, size);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Viewfinder size: ");
        stringBuilder.append(size);
        Log.i(str, stringBuilder.toString());
        size = TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Preview in order of preference: ");
        stringBuilder2.append(list);
        Log.i(size, stringBuilder2.toString());
        return (Size) list.get(null);
    }

    public List<Size> getBestPreviewOrder(List<Size> list, final Size size) {
        if (size == null) {
            return list;
        }
        Collections.sort(list, new Comparator<Size>() {
            public int compare(Size size, Size size2) {
                return Float.compare(PreviewScalingStrategy.this.getScore(size2, size), PreviewScalingStrategy.this.getScore(size, size));
            }
        });
        return list;
    }
}
