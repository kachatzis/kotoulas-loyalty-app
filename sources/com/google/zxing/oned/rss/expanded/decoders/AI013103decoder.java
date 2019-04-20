package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AI013103decoder extends AI013x0xDecoder {
    protected int checkWeight(int i) {
        return i;
    }

    AI013103decoder(BitArray bitArray) {
        super(bitArray);
    }

    protected void addWeightCode(StringBuilder stringBuilder, int i) {
        stringBuilder.append("(3103)");
    }
}
