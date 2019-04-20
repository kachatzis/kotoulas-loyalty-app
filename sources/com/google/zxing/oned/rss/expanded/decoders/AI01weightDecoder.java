package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

abstract class AI01weightDecoder extends AI01decoder {
    protected abstract void addWeightCode(StringBuilder stringBuilder, int i);

    protected abstract int checkWeight(int i);

    AI01weightDecoder(BitArray bitArray) {
        super(bitArray);
    }

    protected final void encodeCompressedWeight(StringBuilder stringBuilder, int i, int i2) {
        i = getGeneralDecoder().extractNumericValueFromBitArray(i, i2);
        addWeightCode(stringBuilder, i);
        i = checkWeight(i);
        i2 = 100000;
        for (int i3 = 0; i3 < 5; i3++) {
            if (i / i2 == 0) {
                stringBuilder.append('0');
            }
            i2 /= 10;
        }
        stringBuilder.append(i);
    }
}
