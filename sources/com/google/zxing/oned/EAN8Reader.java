package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN8Reader extends UPCEANReader {
    private final int[] decodeMiddleCounters = new int[4];

    protected int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder stringBuilder) throws NotFoundException {
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int i = iArr[1];
        iArr = null;
        while (iArr < 4 && i < size) {
            stringBuilder.append((char) (UPCEANReader.decodeDigit(bitArray, iArr2, i, L_PATTERNS) + 48));
            int i2 = i;
            for (int i3 : iArr2) {
                i2 += i3;
            }
            iArr++;
            i = i2;
        }
        int i4 = UPCEANReader.findGuardPattern(bitArray, i, true, MIDDLE_PATTERN)[1];
        iArr = null;
        while (iArr < 4 && i4 < size) {
            stringBuilder.append((char) (UPCEANReader.decodeDigit(bitArray, iArr2, i4, L_PATTERNS) + 48));
            i2 = i4;
            for (int i32 : iArr2) {
                i2 += i32;
            }
            iArr++;
            i4 = i2;
        }
        return i4;
    }

    BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.EAN_8;
    }
}
