package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.util.Log;
import com.journeyapps.barcodescanner.Size;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LegacyPreviewScalingStrategy extends PreviewScalingStrategy {
    private static final String TAG = "LegacyPreviewScalingStrategy";

    public Size getBestPreviewSize(List<Size> list, final Size size) {
        if (size == null) {
            return (Size) list.get(0);
        }
        Collections.sort(list, new Comparator<Size>() {
            public int compare(Size size, Size size2) {
                int i = LegacyPreviewScalingStrategy.scale(size, size).width - size.width;
                int i2 = LegacyPreviewScalingStrategy.scale(size2, size).width - size2.width;
                if (i == 0 && i2 == 0) {
                    return size.compareTo(size2);
                }
                if (i == 0) {
                    return -1;
                }
                if (i2 == 0) {
                    return 1;
                }
                if (i < 0 && i2 < 0) {
                    return size.compareTo(size2);
                }
                if (i <= 0 || i2 <= 0) {
                    return i < 0 ? -1 : 1;
                } else {
                    return -size.compareTo(size2);
                }
            }
        });
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
        return (Size) list.get(0);
    }

    public static Size scale(Size size, Size size2) {
        Size scale;
        if (size2.fitsIn(size)) {
            while (true) {
                scale = size.scale(2, 3);
                Size scale2 = size.scale(1, 2);
                if (!size2.fitsIn(scale2)) {
                    break;
                }
                size = scale2;
            }
            return size2.fitsIn(scale) != null ? scale : size;
        } else {
            do {
                scale = size.scale(3, 2);
                size = size.scale(2, 1);
                if (size2.fitsIn(scale)) {
                    return scale;
                }
            } while (!size2.fitsIn(size));
            return size;
        }
    }

    public Rect scalePreview(Size size, Size size2) {
        Size scale = scale(size, size2);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Preview: ");
        stringBuilder.append(size);
        stringBuilder.append("; Scaled: ");
        stringBuilder.append(scale);
        stringBuilder.append("; Want: ");
        stringBuilder.append(size2);
        Log.i(str, stringBuilder.toString());
        size = (scale.width - size2.width) / 2;
        int i = (scale.height - size2.height) / 2;
        return new Rect(-size, -i, scale.width - size, scale.height - i);
    }
}
