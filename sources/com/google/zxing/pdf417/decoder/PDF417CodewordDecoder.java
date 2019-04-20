package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.lang.reflect.Array;

final class PDF417CodewordDecoder {
    private static final float[][] RATIOS_TABLE = ((float[][]) Array.newInstance(float.class, new int[]{PDF417Common.SYMBOL_TABLE.length, 8}));

    static {
        for (int i = 0; i < PDF417Common.SYMBOL_TABLE.length; i++) {
            int i2 = PDF417Common.SYMBOL_TABLE[i];
            int i3 = i2 & 1;
            int i4 = i2;
            i2 = 0;
            while (i2 < 8) {
                int i5;
                float f = 0.0f;
                while (true) {
                    i5 = i4 & 1;
                    if (i5 != i3) {
                        break;
                    }
                    f += 1.0f;
                    i4 >>= 1;
                }
                RATIOS_TABLE[i][(8 - i2) - 1] = f / 17.0f;
                i2++;
                i3 = i5;
            }
        }
    }

    private PDF417CodewordDecoder() {
    }

    static int getDecodedValue(int[] iArr) {
        int decodedCodewordValue = getDecodedCodewordValue(sampleBitCounts(iArr));
        if (decodedCodewordValue != -1) {
            return decodedCodewordValue;
        }
        return getClosestDecodedValue(iArr);
    }

    private static int[] sampleBitCounts(int[] iArr) {
        float bitCountSum = (float) PDF417Common.getBitCountSum(iArr);
        int[] iArr2 = new int[8];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 17; i3++) {
            if (((float) (iArr[i2] + i)) <= (bitCountSum / 34.0f) + ((((float) i3) * bitCountSum) / 17.0f)) {
                i += iArr[i2];
                i2++;
            }
            iArr2[i2] = iArr2[i2] + 1;
        }
        return iArr2;
    }

    private static int getDecodedCodewordValue(int[] iArr) {
        iArr = getBitValue(iArr);
        return PDF417Common.getCodeword(iArr) == -1 ? -1 : iArr;
    }

    private static int getBitValue(int[] iArr) {
        long j = 0;
        int i = 0;
        while (i < iArr.length) {
            long j2 = j;
            for (int i2 = 0; i2 < iArr[i]; i2++) {
                int i3 = 1;
                j2 <<= 1;
                if (i % 2 != 0) {
                    i3 = 0;
                }
                j2 |= (long) i3;
            }
            i++;
            j = j2;
        }
        return (int) j;
    }

    private static int getClosestDecodedValue(int[] iArr) {
        int bitCountSum = PDF417Common.getBitCountSum(iArr);
        float[] fArr = new float[8];
        for (int i = 0; i < fArr.length; i++) {
            fArr[i] = ((float) iArr[i]) / ((float) bitCountSum);
        }
        bitCountSum = -1;
        iArr = null;
        float f = Float.MAX_VALUE;
        while (true) {
            float[][] fArr2 = RATIOS_TABLE;
            if (iArr >= fArr2.length) {
                return bitCountSum;
            }
            float[] fArr3 = fArr2[iArr];
            float f2 = 0.0f;
            for (int i2 = 0; i2 < 8; i2++) {
                float f3 = fArr3[i2] - fArr[i2];
                f2 += f3 * f3;
                if (f2 >= f) {
                    break;
                }
            }
            if (f2 < f) {
                bitCountSum = PDF417Common.SYMBOL_TABLE[iArr];
                f = f2;
            }
            iArr++;
        }
    }
}
