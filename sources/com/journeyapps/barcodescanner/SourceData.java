package com.journeyapps.barcodescanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import com.google.zxing.PlanarYUVLuminanceSource;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class SourceData {
    private Rect cropRect;
    private byte[] data;
    private int dataHeight;
    private int dataWidth;
    private int imageFormat;
    private int rotation;

    public SourceData(byte[] bArr, int i, int i2, int i3, int i4) {
        this.data = bArr;
        this.dataWidth = i;
        this.dataHeight = i2;
        this.rotation = i4;
        this.imageFormat = i3;
    }

    public Rect getCropRect() {
        return this.cropRect;
    }

    public void setCropRect(Rect rect) {
        this.cropRect = rect;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getDataWidth() {
        return this.dataWidth;
    }

    public int getDataHeight() {
        return this.dataHeight;
    }

    public boolean isRotated() {
        return this.rotation % 180 != 0;
    }

    public int getImageFormat() {
        return this.imageFormat;
    }

    public PlanarYUVLuminanceSource createSource() {
        byte[] rotateCameraPreview = rotateCameraPreview(this.rotation, this.data, this.dataWidth, this.dataHeight);
        if (isRotated()) {
            return new PlanarYUVLuminanceSource(rotateCameraPreview, this.dataHeight, this.dataWidth, this.cropRect.left, this.cropRect.top, this.cropRect.width(), this.cropRect.height(), false);
        }
        return new PlanarYUVLuminanceSource(rotateCameraPreview, this.dataWidth, this.dataHeight, this.cropRect.left, this.cropRect.top, this.cropRect.width(), this.cropRect.height(), false);
    }

    public Bitmap getBitmap() {
        return getBitmap(1);
    }

    public Bitmap getBitmap(int i) {
        return getBitmap(this.cropRect, i);
    }

    private Bitmap getBitmap(Rect rect, int i) {
        if (isRotated()) {
            rect = new Rect(rect.top, rect.left, rect.bottom, rect.right);
        }
        YuvImage yuvImage = new YuvImage(this.data, this.imageFormat, this.dataWidth, this.dataHeight, null);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(rect, 90, byteArrayOutputStream);
        rect = byteArrayOutputStream.toByteArray();
        Options options = new Options();
        options.inSampleSize = i;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(rect, 0, rect.length, options);
        if (this.rotation == null) {
            return decodeByteArray;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) this.rotation);
        return Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, false);
    }

    public static byte[] rotateCameraPreview(int i, byte[] bArr, int i2, int i3) {
        if (i == 0) {
            return bArr;
        }
        if (i == 90) {
            return rotateCW(bArr, i2, i3);
        }
        if (i != 180) {
            return i != 270 ? bArr : rotateCCW(bArr, i2, i3);
        } else {
            return rotate180(bArr, i2, i3);
        }
    }

    public static byte[] rotateCW(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[(i * i2)];
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            for (int i5 = i2 - 1; i5 >= 0; i5--) {
                bArr2[i3] = bArr[(i5 * i) + i4];
                i3++;
            }
        }
        return bArr2;
    }

    public static byte[] rotate180(byte[] bArr, int i, int i2) {
        i *= i2;
        i2 = new byte[i];
        int i3 = i - 1;
        for (int i4 = 0; i4 < i; i4++) {
            i2[i3] = bArr[i4];
            i3--;
        }
        return i2;
    }

    public static byte[] rotateCCW(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[i3];
        i3--;
        for (int i4 = 0; i4 < i; i4++) {
            for (int i5 = i2 - 1; i5 >= 0; i5--) {
                bArr2[i3] = bArr[(i5 * i) + i4];
                i3--;
            }
        }
        return bArr2;
    }
}
