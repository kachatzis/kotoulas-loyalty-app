package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class ITFReader extends OneDReader {
    private static final int[] DEFAULT_ALLOWED_LENGTHS = new int[]{6, 8, 10, 12, 14};
    private static final int[] END_PATTERN_REVERSED = new int[]{1, 1, 3};
    private static final float MAX_AVG_VARIANCE = 0.38f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.78f;
    /* renamed from: N */
    private static final int f27N = 1;
    static final int[][] PATTERNS = new int[][]{new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};
    private static final int[] START_PATTERN = new int[]{1, 1, 1, 1};
    /* renamed from: W */
    private static final int f28W = 3;
    private int narrowLineWidth = -1;

    private static int decodeDigit(int[] r6) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:10:0x0026 in {4, 5, 7, 9} preds:[]
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
        r0 = PATTERNS;
        r0 = r0.length;
        r1 = 1052938076; // 0x3ec28f5c float:0.38 double:5.202205305E-315;
        r2 = -1;
        r3 = 0;
    L_0x0008:
        if (r3 >= r0) goto L_0x001e;
    L_0x000a:
        r4 = PATTERNS;
        r4 = r4[r3];
        r5 = 1061662228; // 0x3f47ae14 float:0.78 double:5.245308343E-315;
        r4 = com.google.zxing.oned.OneDReader.patternMatchVariance(r6, r4, r5);
        r5 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1));
        if (r5 >= 0) goto L_0x001b;
    L_0x0019:
        r2 = r3;
        r1 = r4;
    L_0x001b:
        r3 = r3 + 1;
        goto L_0x0008;
    L_0x001e:
        if (r2 < 0) goto L_0x0021;
    L_0x0020:
        return r2;
    L_0x0021:
        r6 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.ITFReader.decodeDigit(int[]):int");
    }

    private static int[] findGuardPattern(com.google.zxing.common.BitArray r12, int r13, int[] r14) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:17:0x0055 in {4, 10, 11, 12, 13, 14, 16} preds:[]
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
        r0 = r14.length;
        r1 = new int[r0];
        r2 = r12.getSize();
        r3 = 0;
        r6 = r13;
        r4 = 0;
        r5 = 0;
    L_0x000b:
        if (r13 >= r2) goto L_0x0050;
    L_0x000d:
        r7 = r12.get(r13);
        r7 = r7 ^ r4;
        r8 = 1;
        if (r7 == 0) goto L_0x001b;
    L_0x0015:
        r7 = r1[r5];
        r7 = r7 + r8;
        r1[r5] = r7;
        goto L_0x004d;
    L_0x001b:
        r7 = r0 + -1;
        if (r5 != r7) goto L_0x0047;
    L_0x001f:
        r9 = 1061662228; // 0x3f47ae14 float:0.78 double:5.245308343E-315;
        r9 = com.google.zxing.oned.OneDReader.patternMatchVariance(r1, r14, r9);
        r10 = 1052938076; // 0x3ec28f5c float:0.38 double:5.202205305E-315;
        r11 = 2;
        r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1));
        if (r9 >= 0) goto L_0x0035;
    L_0x002e:
        r12 = new int[r11];
        r12[r3] = r6;
        r12[r8] = r13;
        return r12;
    L_0x0035:
        r9 = r1[r3];
        r10 = r1[r8];
        r9 = r9 + r10;
        r6 = r6 + r9;
        r9 = r0 + -2;
        java.lang.System.arraycopy(r1, r11, r1, r3, r9);
        r1[r9] = r3;
        r1[r7] = r3;
        r5 = r5 + -1;
        goto L_0x0049;
    L_0x0047:
        r5 = r5 + 1;
    L_0x0049:
        r1[r5] = r8;
        r4 = r4 ^ 1;
    L_0x004d:
        r13 = r13 + 1;
        goto L_0x000b;
    L_0x0050:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.ITFReader.findGuardPattern(com.google.zxing.common.BitArray, int, int[]):int[]");
    }

    private void validateQuietZone(com.google.zxing.common.BitArray r3, int r4) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:15:0x0022 in {2, 3, 9, 10, 12, 14} preds:[]
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
        r2 = this;
        r0 = r2.narrowLineWidth;
        r0 = r0 * 10;
        if (r0 >= r4) goto L_0x0007;
    L_0x0006:
        goto L_0x0008;
    L_0x0007:
        r0 = r4;
    L_0x0008:
        r4 = r4 + -1;
    L_0x000a:
        if (r0 <= 0) goto L_0x001a;
    L_0x000c:
        if (r4 < 0) goto L_0x001a;
    L_0x000e:
        r1 = r3.get(r4);
        if (r1 == 0) goto L_0x0015;
    L_0x0014:
        goto L_0x001a;
    L_0x0015:
        r0 = r0 + -1;
        r4 = r4 + -1;
        goto L_0x000a;
    L_0x001a:
        if (r0 != 0) goto L_0x001d;
    L_0x001c:
        return;
    L_0x001d:
        r3 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.ITFReader.validateQuietZone(com.google.zxing.common.BitArray, int):void");
    }

    public com.google.zxing.Result decodeRow(int r11, com.google.zxing.common.BitArray r12, java.util.Map<com.google.zxing.DecodeHintType, ?> r13) throws com.google.zxing.FormatException, com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x006f in {2, 3, 5, 10, 12, 13, 14, 17, 20, 22} preds:[]
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
        r0 = r10.decodeStart(r12);
        r1 = r10.decodeEnd(r12);
        r2 = new java.lang.StringBuilder;
        r3 = 20;
        r2.<init>(r3);
        r3 = 1;
        r4 = r0[r3];
        r5 = 0;
        r6 = r1[r5];
        decodeMiddle(r12, r4, r6, r2);
        r12 = r2.toString();
        r2 = 0;
        if (r13 == 0) goto L_0x0028;
    L_0x001f:
        r4 = com.google.zxing.DecodeHintType.ALLOWED_LENGTHS;
        r13 = r13.get(r4);
        r13 = (int[]) r13;
        goto L_0x0029;
    L_0x0028:
        r13 = r2;
    L_0x0029:
        if (r13 != 0) goto L_0x002d;
    L_0x002b:
        r13 = DEFAULT_ALLOWED_LENGTHS;
    L_0x002d:
        r4 = r12.length();
        r6 = r13.length;
        r7 = 0;
        r8 = 0;
    L_0x0034:
        if (r7 >= r6) goto L_0x0042;
    L_0x0036:
        r9 = r13[r7];
        if (r4 != r9) goto L_0x003c;
    L_0x003a:
        r13 = 1;
        goto L_0x0043;
    L_0x003c:
        if (r9 <= r8) goto L_0x003f;
    L_0x003e:
        r8 = r9;
    L_0x003f:
        r7 = r7 + 1;
        goto L_0x0034;
    L_0x0042:
        r13 = 0;
    L_0x0043:
        if (r13 != 0) goto L_0x0048;
    L_0x0045:
        if (r4 <= r8) goto L_0x0048;
    L_0x0047:
        r13 = 1;
    L_0x0048:
        if (r13 == 0) goto L_0x006a;
    L_0x004a:
        r13 = new com.google.zxing.Result;
        r4 = 2;
        r4 = new com.google.zxing.ResultPoint[r4];
        r6 = new com.google.zxing.ResultPoint;
        r0 = r0[r3];
        r0 = (float) r0;
        r11 = (float) r11;
        r6.<init>(r0, r11);
        r4[r5] = r6;
        r0 = new com.google.zxing.ResultPoint;
        r1 = r1[r5];
        r1 = (float) r1;
        r0.<init>(r1, r11);
        r4[r3] = r0;
        r11 = com.google.zxing.BarcodeFormat.ITF;
        r13.<init>(r12, r2, r4, r11);
        return r13;
    L_0x006a:
        r11 = com.google.zxing.FormatException.getFormatInstance();
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.ITFReader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }

    private static void decodeMiddle(BitArray bitArray, int i, int i2, StringBuilder stringBuilder) throws NotFoundException {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i < i2) {
            OneDReader.recordPattern(bitArray, i, iArr);
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = i3 * 2;
                iArr2[i3] = iArr[i4];
                iArr3[i3] = iArr[i4 + 1];
            }
            stringBuilder.append((char) (decodeDigit(iArr2) + 48));
            stringBuilder.append((char) (decodeDigit(iArr3) + 48));
            for (int i42 : iArr) {
                i += i42;
            }
        }
    }

    int[] decodeStart(BitArray bitArray) throws NotFoundException {
        int[] findGuardPattern = findGuardPattern(bitArray, skipWhiteSpace(bitArray), START_PATTERN);
        this.narrowLineWidth = (findGuardPattern[1] - findGuardPattern[0]) / 4;
        validateQuietZone(bitArray, findGuardPattern[0]);
        return findGuardPattern;
    }

    private static int skipWhiteSpace(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        bitArray = bitArray.getNextSet(0);
        if (bitArray != size) {
            return bitArray;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    int[] decodeEnd(BitArray bitArray) throws NotFoundException {
        bitArray.reverse();
        try {
            int[] findGuardPattern = findGuardPattern(bitArray, skipWhiteSpace(bitArray), END_PATTERN_REVERSED);
            validateQuietZone(bitArray, findGuardPattern[0]);
            int i = findGuardPattern[0];
            findGuardPattern[0] = bitArray.getSize() - findGuardPattern[1];
            findGuardPattern[1] = bitArray.getSize() - i;
            return findGuardPattern;
        } finally {
            bitArray.reverse();
        }
    }
}
