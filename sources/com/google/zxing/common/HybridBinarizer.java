package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;

public final class HybridBinarizer extends GlobalHistogramBinarizer {
    private static final int BLOCK_SIZE = 8;
    private static final int BLOCK_SIZE_MASK = 7;
    private static final int BLOCK_SIZE_POWER = 3;
    private static final int MINIMUM_DIMENSION = 40;
    private static final int MIN_DYNAMIC_RANGE = 24;
    private BitMatrix matrix;

    private static int cap(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public HybridBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    public BitMatrix getBlackMatrix() throws NotFoundException {
        BitMatrix bitMatrix = this.matrix;
        if (bitMatrix != null) {
            return bitMatrix;
        }
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        if (width < 40 || height < 40) {
            this.matrix = super.getBlackMatrix();
        } else {
            byte[] matrix = luminanceSource.getMatrix();
            int i = width >> 3;
            int i2 = (width & 7) != 0 ? i + 1 : i;
            i = height >> 3;
            int i3 = (height & 7) != 0 ? i + 1 : i;
            int[][] calculateBlackPoints = calculateBlackPoints(matrix, i2, i3, width, height);
            bitMatrix = new BitMatrix(width, height);
            calculateThresholdForBlock(matrix, i2, i3, width, height, calculateBlackPoints, bitMatrix);
            this.matrix = bitMatrix;
        }
        return this.matrix;
    }

    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new HybridBinarizer(luminanceSource);
    }

    private static void calculateThresholdForBlock(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, BitMatrix bitMatrix) {
        int i5 = i;
        int i6 = i2;
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = i7 << 3;
            int i9 = i4 - 8;
            if (i8 > i9) {
                i8 = i9;
            }
            for (i9 = 0; i9 < i5; i9++) {
                int i10 = i9 << 3;
                int i11 = i3 - 8;
                if (i10 <= i11) {
                    i11 = i10;
                }
                i10 = cap(i9, 2, i5 - 3);
                int cap = cap(i7, 2, i6 - 3);
                int i12 = 0;
                for (int i13 = -2; i13 <= 2; i13++) {
                    int[] iArr2 = iArr[cap + i13];
                    i12 += (((iArr2[i10 - 2] + iArr2[i10 - 1]) + iArr2[i10]) + iArr2[i10 + 1]) + iArr2[i10 + 2];
                }
                thresholdBlock(bArr, i11, i8, i12 / 25, i3, bitMatrix);
            }
        }
    }

    private static void thresholdBlock(byte[] bArr, int i, int i2, int i3, int i4, BitMatrix bitMatrix) {
        int i5 = (i2 * i4) + i;
        int i6 = 0;
        while (i6 < 8) {
            for (int i7 = 0; i7 < 8; i7++) {
                if ((bArr[i5 + i7] & 255) <= i3) {
                    bitMatrix.set(i + i7, i2 + i6);
                }
            }
            i6++;
            i5 += i4;
        }
    }

    private static int[][] calculateBlackPoints(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = i;
        int i6 = i2;
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{i6, i5});
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = i7 << 3;
            int i9 = i4 - 8;
            if (i8 > i9) {
                i8 = i9;
            }
            i9 = 0;
            while (i9 < i5) {
                int i10;
                int i11 = i9 << 3;
                int i12 = i3 - 8;
                if (i11 > i12) {
                    i11 = i12;
                }
                int i13 = (i8 * i3) + i11;
                i12 = 0;
                int i14 = 0;
                int i15 = 0;
                int i16 = 255;
                while (i12 < 8) {
                    int i17 = i14;
                    for (i14 = 0; i14 < 8; i14++) {
                        i10 = bArr[i13 + i14] & 255;
                        i17 += i10;
                        if (i10 < i16) {
                            i16 = i10;
                        }
                        if (i10 > i15) {
                            i15 = i10;
                        }
                    }
                    if (i15 - i16 <= 24) {
                        i14 = i17;
                        i12++;
                        i13 += i3;
                    }
                    while (true) {
                        i12++;
                        i13 += i3;
                        if (i12 >= 8) {
                            break;
                        }
                        for (i10 = 0; i10 < 8; i10++) {
                            i17 += bArr[i13 + i10] & 255;
                        }
                    }
                    i14 = i17;
                    i12++;
                    i13 += i3;
                }
                i10 = i14 >> 6;
                if (i15 - i16 <= 24) {
                    i10 = i16 / 2;
                    if (i7 > 0 && i9 > 0) {
                        i11 = i7 - 1;
                        i15 = i9 - 1;
                        i11 = ((iArr[i11][i9] + (iArr[i7][i15] * 2)) + iArr[i11][i15]) / 4;
                        if (i16 < i11) {
                            i10 = i11;
                        }
                    }
                }
                iArr[i7][i9] = i10;
                i9++;
            }
        }
        return iArr;
    }
}
