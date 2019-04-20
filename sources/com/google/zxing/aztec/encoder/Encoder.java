package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int MAX_NB_BITS = 32;
    private static final int MAX_NB_BITS_COMPACT = 4;
    private static final int[] WORD_SIZE = new int[]{4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    public static com.google.zxing.aztec.encoder.AztecCode encode(byte[] r18, int r19, int r20) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:98:0x0200 in {3, 4, 7, 14, 16, 18, 20, 25, 26, 28, 29, 32, 35, 40, 43, 46, 47, 53, 54, 57, 61, 62, 69, 72, 75, 78, 79, 80, 81, 84, 90, 91, 93, 94, 95, 97} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r0 = new com.google.zxing.aztec.encoder.HighLevelEncoder;
        r1 = r18;
        r0.<init>(r1);
        r0 = r0.encode();
        r1 = r0.getSize();
        r1 = r1 * r19;
        r1 = r1 / 100;
        r1 = r1 + 11;
        r2 = r0.getSize();
        r2 = r2 + r1;
        r3 = 32;
        r4 = 4;
        r5 = 0;
        r6 = 1;
        if (r20 == 0) goto L_0x0076;
    L_0x0021:
        if (r20 >= 0) goto L_0x0025;
    L_0x0023:
        r2 = 1;
        goto L_0x0026;
    L_0x0025:
        r2 = 0;
    L_0x0026:
        r7 = java.lang.Math.abs(r20);
        if (r2 == 0) goto L_0x002d;
    L_0x002c:
        r3 = 4;
    L_0x002d:
        if (r7 > r3) goto L_0x0062;
    L_0x002f:
        r3 = totalBitsInLayer(r7, r2);
        r8 = WORD_SIZE;
        r8 = r8[r7];
        r9 = r3 % r8;
        r9 = r3 - r9;
        r0 = stuffBits(r0, r8);
        r10 = r0.getSize();
        r10 = r10 + r1;
        if (r10 > r9) goto L_0x005a;
    L_0x0046:
        if (r2 == 0) goto L_0x00b9;
    L_0x0048:
        r1 = r0.getSize();
        r9 = r8 * 64;
        if (r1 > r9) goto L_0x0052;
    L_0x0050:
        goto L_0x00b9;
    L_0x0052:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Data to large for user specified layer";
        r0.<init>(r1);
        throw r0;
    L_0x005a:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Data to large for user specified layer";
        r0.<init>(r1);
        throw r0;
    L_0x0062:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.Object[r6];
        r2 = java.lang.Integer.valueOf(r20);
        r1[r5] = r2;
        r2 = "Illegal value %s for layers";
        r1 = java.lang.String.format(r2, r1);
        r0.<init>(r1);
        throw r0;
    L_0x0076:
        r7 = 0;
        r9 = r7;
        r7 = 0;
        r8 = 0;
    L_0x007a:
        if (r7 > r3) goto L_0x01f8;
    L_0x007c:
        r10 = 3;
        if (r7 > r10) goto L_0x0081;
    L_0x007f:
        r10 = 1;
        goto L_0x0082;
    L_0x0081:
        r10 = 0;
    L_0x0082:
        if (r10 == 0) goto L_0x0087;
    L_0x0084:
        r11 = r7 + 1;
        goto L_0x0088;
    L_0x0087:
        r11 = r7;
    L_0x0088:
        r12 = totalBitsInLayer(r11, r10);
        if (r2 <= r12) goto L_0x0091;
    L_0x008e:
        r10 = 1;
        goto L_0x01f1;
    L_0x0091:
        r13 = WORD_SIZE;
        r14 = r13[r11];
        if (r8 == r14) goto L_0x009d;
    L_0x0097:
        r8 = r13[r11];
        r9 = stuffBits(r0, r8);
    L_0x009d:
        r13 = r12 % r8;
        r13 = r12 - r13;
        if (r10 == 0) goto L_0x00ae;
    L_0x00a3:
        r14 = r9.getSize();
        r15 = r8 * 64;
        if (r14 <= r15) goto L_0x00ae;
    L_0x00ab:
        r10 = 1;
        goto L_0x01f1;
    L_0x00ae:
        r14 = r9.getSize();
        r14 = r14 + r1;
        if (r14 > r13) goto L_0x01f0;
    L_0x00b5:
        r0 = r9;
        r2 = r10;
        r7 = r11;
        r3 = r12;
    L_0x00b9:
        r1 = generateCheckWords(r0, r3, r8);
        r0 = r0.getSize();
        r0 = r0 / r8;
        r3 = generateModeMessage(r2, r7, r0);
        if (r2 == 0) goto L_0x00cd;
    L_0x00c8:
        r8 = r7 * 4;
        r8 = r8 + 11;
        goto L_0x00d1;
    L_0x00cd:
        r8 = r7 * 4;
        r8 = r8 + 14;
    L_0x00d1:
        r9 = new int[r8];
        r10 = 2;
        if (r2 == 0) goto L_0x00e1;
    L_0x00d6:
        r11 = 0;
    L_0x00d7:
        r12 = r9.length;
        if (r11 >= r12) goto L_0x00df;
    L_0x00da:
        r9[r11] = r11;
        r11 = r11 + 1;
        goto L_0x00d7;
    L_0x00df:
        r11 = r8;
        goto L_0x0107;
    L_0x00e1:
        r11 = r8 + 1;
        r12 = r8 / 2;
        r13 = r12 + -1;
        r13 = r13 / 15;
        r13 = r13 * 2;
        r11 = r11 + r13;
        r13 = r11 / 2;
        r14 = 0;
    L_0x00ef:
        if (r14 >= r12) goto L_0x0107;
    L_0x00f1:
        r15 = r14 / 15;
        r15 = r15 + r14;
        r16 = r12 - r14;
        r16 = r16 + -1;
        r17 = r13 - r15;
        r17 = r17 + -1;
        r9[r16] = r17;
        r16 = r12 + r14;
        r15 = r15 + r13;
        r15 = r15 + r6;
        r9[r16] = r15;
        r14 = r14 + 1;
        goto L_0x00ef;
    L_0x0107:
        r12 = new com.google.zxing.common.BitMatrix;
        r12.<init>(r11);
        r13 = 0;
        r14 = 0;
    L_0x010e:
        if (r13 >= r7) goto L_0x01a4;
    L_0x0110:
        if (r2 == 0) goto L_0x0119;
    L_0x0112:
        r15 = r7 - r13;
        r15 = r15 * 4;
        r15 = r15 + 9;
        goto L_0x011f;
    L_0x0119:
        r15 = r7 - r13;
        r15 = r15 * 4;
        r15 = r15 + 12;
    L_0x011f:
        r4 = 0;
    L_0x0120:
        if (r4 >= r15) goto L_0x0199;
    L_0x0122:
        r16 = r4 * 2;
    L_0x0124:
        if (r5 >= r10) goto L_0x0193;
    L_0x0126:
        r17 = r14 + r16;
        r6 = r17 + r5;
        r6 = r1.get(r6);
        if (r6 == 0) goto L_0x013c;
    L_0x0130:
        r6 = r13 * 2;
        r17 = r6 + r5;
        r10 = r9[r17];
        r6 = r6 + r4;
        r6 = r9[r6];
        r12.set(r10, r6);
    L_0x013c:
        r6 = r15 * 2;
        r6 = r6 + r14;
        r6 = r6 + r16;
        r6 = r6 + r5;
        r6 = r1.get(r6);
        if (r6 == 0) goto L_0x0159;
    L_0x0148:
        r6 = r13 * 2;
        r10 = r6 + r4;
        r10 = r9[r10];
        r17 = r8 + -1;
        r17 = r17 - r6;
        r17 = r17 - r5;
        r6 = r9[r17];
        r12.set(r10, r6);
    L_0x0159:
        r6 = r15 * 4;
        r6 = r6 + r14;
        r6 = r6 + r16;
        r6 = r6 + r5;
        r6 = r1.get(r6);
        if (r6 == 0) goto L_0x0174;
    L_0x0165:
        r6 = r8 + -1;
        r10 = r13 * 2;
        r6 = r6 - r10;
        r10 = r6 - r5;
        r10 = r9[r10];
        r6 = r6 - r4;
        r6 = r9[r6];
        r12.set(r10, r6);
    L_0x0174:
        r6 = r15 * 6;
        r6 = r6 + r14;
        r6 = r6 + r16;
        r6 = r6 + r5;
        r6 = r1.get(r6);
        if (r6 == 0) goto L_0x018e;
    L_0x0180:
        r6 = r8 + -1;
        r10 = r13 * 2;
        r6 = r6 - r10;
        r6 = r6 - r4;
        r6 = r9[r6];
        r10 = r10 + r5;
        r10 = r9[r10];
        r12.set(r6, r10);
    L_0x018e:
        r5 = r5 + 1;
        r6 = 1;
        r10 = 2;
        goto L_0x0124;
    L_0x0193:
        r4 = r4 + 1;
        r5 = 0;
        r6 = 1;
        r10 = 2;
        goto L_0x0120;
    L_0x0199:
        r15 = r15 * 8;
        r14 = r14 + r15;
        r13 = r13 + 1;
        r4 = 4;
        r5 = 0;
        r6 = 1;
        r10 = 2;
        goto L_0x010e;
    L_0x01a4:
        drawModeMessage(r12, r2, r11, r3);
        if (r2 == 0) goto L_0x01b0;
    L_0x01a9:
        r1 = r11 / 2;
        r3 = 5;
        drawBullsEye(r12, r1, r3);
        goto L_0x01db;
    L_0x01b0:
        r1 = r11 / 2;
        r3 = 7;
        drawBullsEye(r12, r1, r3);
        r3 = 0;
        r4 = 0;
        r5 = 2;
    L_0x01b9:
        r6 = r8 / 2;
        r10 = 1;
        r6 = r6 - r10;
        if (r3 >= r6) goto L_0x01db;
    L_0x01bf:
        r6 = r1 & 1;
    L_0x01c1:
        if (r6 >= r11) goto L_0x01d6;
    L_0x01c3:
        r9 = r1 - r4;
        r12.set(r9, r6);
        r13 = r1 + r4;
        r12.set(r13, r6);
        r12.set(r6, r9);
        r12.set(r6, r13);
        r6 = r6 + 2;
        goto L_0x01c1;
    L_0x01d6:
        r3 = r3 + 15;
        r4 = r4 + 16;
        goto L_0x01b9;
    L_0x01db:
        r1 = new com.google.zxing.aztec.encoder.AztecCode;
        r1.<init>();
        r1.setCompact(r2);
        r1.setSize(r11);
        r1.setLayers(r7);
        r1.setCodeWords(r0);
        r1.setMatrix(r12);
        return r1;
    L_0x01f0:
        r10 = 1;
    L_0x01f1:
        r7 = r7 + 1;
        r4 = 4;
        r5 = 0;
        r6 = 1;
        goto L_0x007a;
    L_0x01f8:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Data too large for an Aztec code";
        r0.<init>(r1);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.encoder.Encoder.encode(byte[], int, int):com.google.zxing.aztec.encoder.AztecCode");
    }

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i * 16)) * i;
    }

    private Encoder() {
    }

    public static AztecCode encode(byte[] bArr) {
        return encode(bArr, 33, 0);
    }

    private static void drawBullsEye(BitMatrix bitMatrix, int i, int i2) {
        int i3;
        int i4;
        for (i3 = 0; i3 < i2; i3 += 2) {
            i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 > i6) {
                    break;
                }
                bitMatrix.set(i5, i4);
                bitMatrix.set(i5, i6);
                bitMatrix.set(i4, i5);
                bitMatrix.set(i6, i5);
                i5++;
            }
        }
        i3 = i - i2;
        bitMatrix.set(i3, i3);
        i4 = i3 + 1;
        bitMatrix.set(i4, i3);
        bitMatrix.set(i3, i4);
        i += i2;
        bitMatrix.set(i, i3);
        bitMatrix.set(i, i4);
        bitMatrix.set(i, i - 1);
    }

    static BitArray generateModeMessage(boolean z, int i, int i2) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.appendBits(i - 1, true);
            bitArray.appendBits(i2 - 1, true);
            return generateCheckWords(bitArray, true, 4);
        }
        bitArray.appendBits(i - 1, true);
        bitArray.appendBits(i2 - 1, true);
        return generateCheckWords(bitArray, true, 4);
    }

    private static void drawModeMessage(BitMatrix bitMatrix, boolean z, int i, BitArray bitArray) {
        i /= 2;
        boolean z2 = false;
        if (z) {
            while (z2 < true) {
                z = (i - 3) + z2;
                if (bitArray.get(z2)) {
                    bitMatrix.set(z, i - 5);
                }
                if (bitArray.get(z2 + 7)) {
                    bitMatrix.set(i + 5, z);
                }
                if (bitArray.get(20 - z2)) {
                    bitMatrix.set(z, i + 5);
                }
                if (bitArray.get(27 - z2)) {
                    bitMatrix.set(i - 5, z);
                }
                z2++;
            }
            return;
        }
        while (z2 < true) {
            z = ((i - 5) + z2) + (z2 / 5);
            if (bitArray.get(z2)) {
                bitMatrix.set(z, i - 7);
            }
            if (bitArray.get(z2 + 10)) {
                bitMatrix.set(i + 7, z);
            }
            if (bitArray.get(29 - z2)) {
                bitMatrix.set(z, i + 7);
            }
            if (bitArray.get(39 - z2)) {
                bitMatrix.set(i - 7, z);
            }
            z2++;
        }
    }

    private static BitArray generateCheckWords(BitArray bitArray, int i, int i2) {
        int size = bitArray.getSize() / i2;
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(i2));
        int i3 = i / i2;
        bitArray = bitsToWords(bitArray, i2, i3);
        reedSolomonEncoder.encode(bitArray, i3 - size);
        i %= i2;
        BitArray bitArray2 = new BitArray();
        int i4 = 0;
        bitArray2.appendBits(0, i);
        i = bitArray.length;
        while (i4 < i) {
            bitArray2.appendBits(bitArray[i4], i2);
            i4++;
        }
        return bitArray2;
    }

    private static int[] bitsToWords(BitArray bitArray, int i, int i2) {
        i2 = new int[i2];
        int size = bitArray.getSize() / i;
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4 |= bitArray.get((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
            }
            i2[i3] = i4;
        }
        return i2;
    }

    private static GenericGF getGF(int i) {
        if (i == 4) {
            return GenericGF.AZTEC_PARAM;
        }
        if (i == 6) {
            return GenericGF.AZTEC_DATA_6;
        }
        if (i == 8) {
            return GenericGF.AZTEC_DATA_8;
        }
        if (i == 10) {
            return GenericGF.AZTEC_DATA_10;
        }
        if (i == 12) {
            return GenericGF.AZTEC_DATA_12;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported word size ");
        stringBuilder.append(i);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    static BitArray stuffBits(BitArray bitArray, int i) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < size) {
            int i4;
            int i5 = 0;
            for (i4 = 0; i4 < i; i4++) {
                int i6 = i3 + i4;
                if (i6 >= size || bitArray.get(i6)) {
                    i5 |= 1 << ((i - 1) - i4);
                }
            }
            i4 = i5 & i2;
            if (i4 == i2) {
                bitArray2.appendBits(i4, i);
                i3--;
            } else if (i4 == 0) {
                bitArray2.appendBits(i5 | 1, i);
                i3--;
            } else {
                bitArray2.appendBits(i5, i);
            }
            i3 += i;
        }
        return bitArray2;
    }
}
