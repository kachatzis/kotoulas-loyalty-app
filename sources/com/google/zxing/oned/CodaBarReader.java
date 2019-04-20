package com.google.zxing.oned;

import com.google.zxing.NotFoundException;

public final class CodaBarReader extends OneDReader {
    static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    private static final String ALPHABET_STRING = "0123456789-$:/.+ABCD";
    static final int[] CHARACTER_ENCODINGS = new int[]{3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final float MAX_ACCEPTABLE = 2.0f;
    private static final int MIN_CHARACTER_LENGTH = 3;
    private static final float PADDING = 1.5f;
    private static final char[] STARTEND_ENCODING = new char[]{'A', 'B', 'C', 'D'};
    private int counterLength = 0;
    private int[] counters = new int[80];
    private final StringBuilder decodeRowResult = new StringBuilder(20);

    private int findStartPattern() throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:18:0x003d in {10, 13, 14, 15, 17} preds:[]
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
        r5 = this;
        r0 = 1;
        r1 = 1;
    L_0x0002:
        r2 = r5.counterLength;
        if (r1 >= r2) goto L_0x0038;
    L_0x0006:
        r2 = r5.toNarrowWidePattern(r1);
        r3 = -1;
        if (r2 == r3) goto L_0x0035;
    L_0x000d:
        r3 = STARTEND_ENCODING;
        r4 = ALPHABET;
        r2 = r4[r2];
        r2 = arrayContains(r3, r2);
        if (r2 == 0) goto L_0x0035;
    L_0x0019:
        r2 = 0;
        r2 = r1;
        r3 = 0;
    L_0x001c:
        r4 = r1 + 7;
        if (r2 >= r4) goto L_0x0028;
    L_0x0020:
        r4 = r5.counters;
        r4 = r4[r2];
        r3 = r3 + r4;
        r2 = r2 + 1;
        goto L_0x001c;
    L_0x0028:
        if (r1 == r0) goto L_0x0034;
    L_0x002a:
        r2 = r5.counters;
        r4 = r1 + -1;
        r2 = r2[r4];
        r3 = r3 / 2;
        if (r2 < r3) goto L_0x0035;
    L_0x0034:
        return r1;
    L_0x0035:
        r1 = r1 + 2;
        goto L_0x0002;
    L_0x0038:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.CodaBarReader.findStartPattern():int");
    }

    private void setCounters(com.google.zxing.common.BitArray r7) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:13:0x002e in {6, 7, 8, 10, 12} preds:[]
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
        r6 = this;
        r0 = 0;
        r6.counterLength = r0;
        r1 = r7.getNextUnset(r0);
        r2 = r7.getSize();
        if (r1 >= r2) goto L_0x0029;
    L_0x000d:
        r3 = 1;
        r4 = 1;
    L_0x000f:
        if (r1 >= r2) goto L_0x0025;
    L_0x0011:
        r5 = r7.get(r1);
        r5 = r5 ^ r4;
        if (r5 == 0) goto L_0x001b;
    L_0x0018:
        r0 = r0 + 1;
        goto L_0x0022;
    L_0x001b:
        r6.counterAppend(r0);
        r0 = r4 ^ 1;
        r4 = r0;
        r0 = 1;
    L_0x0022:
        r1 = r1 + 1;
        goto L_0x000f;
    L_0x0025:
        r6.counterAppend(r0);
        return;
    L_0x0029:
        r7 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.CodaBarReader.setCounters(com.google.zxing.common.BitArray):void");
    }

    public com.google.zxing.Result decodeRow(int r11, com.google.zxing.common.BitArray r12, java.util.Map<com.google.zxing.DecodeHintType, ?> r13) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:50:0x0109 in {7, 9, 12, 17, 19, 23, 32, 33, 36, 39, 41, 43, 45, 47, 49} preds:[]
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
        r10 = this;
        r0 = r10.counters;
        r1 = 0;
        java.util.Arrays.fill(r0, r1);
        r10.setCounters(r12);
        r12 = r10.findStartPattern();
        r0 = r10.decodeRowResult;
        r0.setLength(r1);
        r0 = r12;
    L_0x0013:
        r2 = r10.toNarrowWidePattern(r0);
        r3 = -1;
        if (r2 == r3) goto L_0x0104;
    L_0x001a:
        r4 = r10.decodeRowResult;
        r5 = (char) r2;
        r4.append(r5);
        r0 = r0 + 8;
        r4 = r10.decodeRowResult;
        r4 = r4.length();
        r5 = 1;
        if (r4 <= r5) goto L_0x0038;
    L_0x002b:
        r4 = STARTEND_ENCODING;
        r6 = ALPHABET;
        r2 = r6[r2];
        r2 = arrayContains(r4, r2);
        if (r2 == 0) goto L_0x0038;
    L_0x0037:
        goto L_0x003c;
    L_0x0038:
        r2 = r10.counterLength;
        if (r0 < r2) goto L_0x0013;
    L_0x003c:
        r2 = r10.counters;
        r4 = r0 + -1;
        r2 = r2[r4];
        r6 = -8;
        r7 = 0;
    L_0x0044:
        if (r6 >= r3) goto L_0x0050;
    L_0x0046:
        r8 = r10.counters;
        r9 = r0 + r6;
        r8 = r8[r9];
        r7 = r7 + r8;
        r6 = r6 + 1;
        goto L_0x0044;
    L_0x0050:
        r3 = r10.counterLength;
        r6 = 2;
        if (r0 >= r3) goto L_0x005e;
    L_0x0055:
        r7 = r7 / r6;
        if (r2 < r7) goto L_0x0059;
    L_0x0058:
        goto L_0x005e;
    L_0x0059:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
    L_0x005e:
        r10.validatePattern(r12);
        r0 = 0;
    L_0x0062:
        r2 = r10.decodeRowResult;
        r2 = r2.length();
        if (r0 >= r2) goto L_0x007a;
    L_0x006a:
        r2 = r10.decodeRowResult;
        r3 = ALPHABET;
        r7 = r2.charAt(r0);
        r3 = r3[r7];
        r2.setCharAt(r0, r3);
        r0 = r0 + 1;
        goto L_0x0062;
    L_0x007a:
        r0 = r10.decodeRowResult;
        r0 = r0.charAt(r1);
        r2 = STARTEND_ENCODING;
        r0 = arrayContains(r2, r0);
        if (r0 == 0) goto L_0x00ff;
    L_0x0088:
        r0 = r10.decodeRowResult;
        r2 = r0.length();
        r2 = r2 - r5;
        r0 = r0.charAt(r2);
        r2 = STARTEND_ENCODING;
        r0 = arrayContains(r2, r0);
        if (r0 == 0) goto L_0x00fa;
    L_0x009b:
        r0 = r10.decodeRowResult;
        r0 = r0.length();
        r2 = 3;
        if (r0 <= r2) goto L_0x00f5;
    L_0x00a4:
        if (r13 == 0) goto L_0x00ae;
    L_0x00a6:
        r0 = com.google.zxing.DecodeHintType.RETURN_CODABAR_START_END;
        r13 = r13.containsKey(r0);
        if (r13 != 0) goto L_0x00bd;
    L_0x00ae:
        r13 = r10.decodeRowResult;
        r0 = r13.length();
        r0 = r0 - r5;
        r13.deleteCharAt(r0);
        r13 = r10.decodeRowResult;
        r13.deleteCharAt(r1);
    L_0x00bd:
        r13 = 0;
        r0 = 0;
    L_0x00bf:
        if (r13 >= r12) goto L_0x00c9;
    L_0x00c1:
        r2 = r10.counters;
        r2 = r2[r13];
        r0 = r0 + r2;
        r13 = r13 + 1;
        goto L_0x00bf;
    L_0x00c9:
        r13 = (float) r0;
    L_0x00ca:
        if (r12 >= r4) goto L_0x00d4;
    L_0x00cc:
        r2 = r10.counters;
        r2 = r2[r12];
        r0 = r0 + r2;
        r12 = r12 + 1;
        goto L_0x00ca;
    L_0x00d4:
        r12 = (float) r0;
        r0 = new com.google.zxing.Result;
        r2 = r10.decodeRowResult;
        r2 = r2.toString();
        r3 = 0;
        r4 = new com.google.zxing.ResultPoint[r6];
        r6 = new com.google.zxing.ResultPoint;
        r11 = (float) r11;
        r6.<init>(r13, r11);
        r4[r1] = r6;
        r13 = new com.google.zxing.ResultPoint;
        r13.<init>(r12, r11);
        r4[r5] = r13;
        r11 = com.google.zxing.BarcodeFormat.CODABAR;
        r0.<init>(r2, r3, r4, r11);
        return r0;
    L_0x00f5:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
    L_0x00fa:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
    L_0x00ff:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
    L_0x0104:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.CodaBarReader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }

    void validatePattern(int i) throws NotFoundException {
        int i2;
        int[] iArr = new int[]{0, 0, 0, 0};
        int[] iArr2 = new int[]{0, 0, 0, 0};
        int length = this.decodeRowResult.length() - 1;
        int i3 = 0;
        int i4 = i;
        int i5 = 0;
        while (true) {
            int i6 = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(i5)];
            for (int i7 = 6; i7 >= 0; i7--) {
                int i8 = (i7 & 1) + ((i6 & 1) * 2);
                iArr[i8] = iArr[i8] + this.counters[i4 + i7];
                iArr2[i8] = iArr2[i8] + 1;
                i6 >>= 1;
            }
            if (i5 >= length) {
                break;
            }
            i4 += 8;
            i5++;
        }
        float[] fArr = new float[4];
        float[] fArr2 = new float[4];
        for (i2 = 0; i2 < 2; i2++) {
            fArr2[i2] = 0.0f;
            i5 = i2 + 2;
            fArr2[i5] = ((((float) iArr[i2]) / ((float) iArr2[i2])) + (((float) iArr[i5]) / ((float) iArr2[i5]))) / MAX_ACCEPTABLE;
            fArr[i2] = fArr2[i5];
            fArr[i5] = ((((float) iArr[i5]) * MAX_ACCEPTABLE) + PADDING) / ((float) iArr2[i5]);
        }
        loop3:
        while (true) {
            int i9 = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(i3)];
            i2 = 6;
            while (i2 >= 0) {
                int i10 = (i2 & 1) + ((i9 & 1) * 2);
                float f = (float) this.counters[i + i2];
                if (f >= fArr2[i10] && f <= fArr[i10]) {
                    i9 >>= 1;
                    i2--;
                }
            }
            if (i3 < length) {
                i += 8;
                i3++;
            } else {
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void counterAppend(int i) {
        Object obj = this.counters;
        int i2 = this.counterLength;
        obj[i2] = i;
        this.counterLength = i2 + 1;
        i = this.counterLength;
        if (i >= obj.length) {
            Object obj2 = new int[(i * 2)];
            System.arraycopy(obj, 0, obj2, 0, i);
            this.counters = obj2;
        }
    }

    static boolean arrayContains(char[] cArr, char c) {
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c2 == c) {
                    return 1;
                }
            }
        }
        return false;
    }

    private int toNarrowWidePattern(int i) {
        int i2 = i + 7;
        if (i2 >= this.counterLength) {
            return -1;
        }
        int i3;
        int[] iArr = this.counters;
        int i4 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i5 = 0;
        int i6 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i7 = 0;
        for (i3 = i; i3 < i2; i3 += 2) {
            int i8 = iArr[i3];
            if (i8 < i6) {
                i6 = i8;
            }
            if (i8 > i7) {
                i7 = i8;
            }
        }
        i6 = (i6 + i7) / 2;
        i7 = 0;
        for (i3 = i + 1; i3 < i2; i3 += 2) {
            i8 = iArr[i3];
            if (i8 < i4) {
                i4 = i8;
            }
            if (i8 > i7) {
                i7 = i8;
            }
        }
        i2 = (i4 + i7) / 2;
        i3 = 128;
        i7 = 0;
        for (i4 = 0; i4 < 7; i4++) {
            i3 >>= 1;
            if (iArr[i + i4] > ((i4 & 1) == 0 ? i6 : i2)) {
                i7 |= i3;
            }
        }
        while (true) {
            i = CHARACTER_ENCODINGS;
            if (i5 >= i.length) {
                return -1;
            }
            if (i[i5] == i7) {
                return i5;
            }
            i5++;
        }
    }
}
