package com.journeyapps.barcodescanner;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public class BarcodeEncoder {
    private static final int BLACK = -16777216;
    private static final int WHITE = -1;

    public Bitmap createBitmap(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] iArr = new int[(width * height)];
        for (int i = 0; i < height; i++) {
            int i2 = i * width;
            for (int i3 = 0; i3 < width; i3++) {
                iArr[i2 + i3] = bitMatrix.get(i3, i) ? -16777216 : -1;
            }
        }
        bitMatrix = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bitMatrix.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitMatrix;
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        try {
            return new MultiFormatWriter().encode(str, barcodeFormat, i, i2);
        } catch (String str2) {
            throw str2;
        } catch (Throwable e) {
            throw new WriterException(e);
        }
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        try {
            return new MultiFormatWriter().encode(str, barcodeFormat, i, i2, map);
        } catch (String str2) {
            throw str2;
        } catch (Throwable e) {
            throw new WriterException(e);
        }
    }

    public Bitmap encodeBitmap(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return createBitmap(encode(str, barcodeFormat, i, i2));
    }

    public Bitmap encodeBitmap(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        return createBitmap(encode(str, barcodeFormat, i, i2, map));
    }
}
