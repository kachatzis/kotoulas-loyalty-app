package com.google.zxing.oned;

import com.google.zxing.FormatException;

public final class Code39Reader extends OneDReader {
    private static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
    private static final int ASTERISK_ENCODING = CHARACTER_ENCODINGS[39];
    static final int[] CHARACTER_ENCODINGS = new int[]{52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, CallbackHandler.MSG_ROUTE_CHANGED, 67, 322, 19, 274, 82, 7, CallbackHandler.MSG_ROUTE_SELECTED, 70, 22, 385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 168, 162, 138, 42};
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    private static int[] findAsteriskPattern(com.google.zxing.common.BitArray r12, int[] r13) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:19:0x0060 in {4, 12, 13, 14, 15, 16, 18} preds:[]
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
        r0 = r12.getSize();
        r1 = 0;
        r2 = r12.getNextSet(r1);
        r3 = r13.length;
        r6 = r2;
        r4 = 0;
        r5 = 0;
    L_0x000d:
        if (r2 >= r0) goto L_0x005b;
    L_0x000f:
        r7 = r12.get(r2);
        r7 = r7 ^ r4;
        r8 = 1;
        if (r7 == 0) goto L_0x001d;
    L_0x0017:
        r7 = r13[r5];
        r7 = r7 + r8;
        r13[r5] = r7;
        goto L_0x0058;
    L_0x001d:
        r7 = r3 + -1;
        if (r5 != r7) goto L_0x0052;
    L_0x0021:
        r9 = toNarrowWidePattern(r13);
        r10 = ASTERISK_ENCODING;
        r11 = 2;
        if (r9 != r10) goto L_0x0040;
    L_0x002a:
        r9 = r2 - r6;
        r9 = r9 / r11;
        r9 = r6 - r9;
        r9 = java.lang.Math.max(r1, r9);
        r9 = r12.isRange(r9, r6, r1);
        if (r9 == 0) goto L_0x0040;
    L_0x0039:
        r12 = new int[r11];
        r12[r1] = r6;
        r12[r8] = r2;
        return r12;
    L_0x0040:
        r9 = r13[r1];
        r10 = r13[r8];
        r9 = r9 + r10;
        r6 = r6 + r9;
        r9 = r3 + -2;
        java.lang.System.arraycopy(r13, r11, r13, r1, r9);
        r13[r9] = r1;
        r13[r7] = r1;
        r5 = r5 + -1;
        goto L_0x0054;
    L_0x0052:
        r5 = r5 + 1;
    L_0x0054:
        r13[r5] = r8;
        r4 = r4 ^ 1;
    L_0x0058:
        r2 = r2 + 1;
        goto L_0x000d;
    L_0x005b:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code39Reader.findAsteriskPattern(com.google.zxing.common.BitArray, int[]):int[]");
    }

    private static char patternToChar(int r3) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:10:0x0017 in {6, 7, 9} preds:[]
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
        r0 = 0;
    L_0x0001:
        r1 = CHARACTER_ENCODINGS;
        r2 = r1.length;
        if (r0 >= r2) goto L_0x0012;
    L_0x0006:
        r1 = r1[r0];
        if (r1 != r3) goto L_0x000f;
    L_0x000a:
        r3 = ALPHABET;
        r3 = r3[r0];
        return r3;
    L_0x000f:
        r0 = r0 + 1;
        goto L_0x0001;
    L_0x0012:
        r3 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code39Reader.patternToChar(int):char");
    }

    public com.google.zxing.Result decodeRow(int r12, com.google.zxing.common.BitArray r13, java.util.Map<com.google.zxing.DecodeHintType, ?> r14) throws com.google.zxing.NotFoundException, com.google.zxing.ChecksumException, com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:41:0x00d9 in {5, 10, 15, 17, 22, 25, 27, 32, 33, 35, 37, 38, 40} preds:[]
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
        r11 = this;
        r14 = r11.counters;
        r0 = 0;
        java.util.Arrays.fill(r14, r0);
        r1 = r11.decodeRowResult;
        r1.setLength(r0);
        r2 = findAsteriskPattern(r13, r14);
        r3 = 1;
        r4 = r2[r3];
        r4 = r13.getNextSet(r4);
        r5 = r13.getSize();
    L_0x001a:
        com.google.zxing.oned.OneDReader.recordPattern(r13, r4, r14);
        r6 = toNarrowWidePattern(r14);
        if (r6 < 0) goto L_0x00d4;
    L_0x0023:
        r6 = patternToChar(r6);
        r1.append(r6);
        r7 = r14.length;
        r9 = r4;
        r8 = 0;
    L_0x002d:
        if (r8 >= r7) goto L_0x0035;
    L_0x002f:
        r10 = r14[r8];
        r9 = r9 + r10;
        r8 = r8 + 1;
        goto L_0x002d;
    L_0x0035:
        r7 = r13.getNextSet(r9);
        r8 = 42;
        if (r6 != r8) goto L_0x00d1;
    L_0x003d:
        r13 = r1.length();
        r13 = r13 - r3;
        r1.setLength(r13);
        r13 = r14.length;
        r6 = 0;
        r8 = 0;
    L_0x0048:
        if (r6 >= r13) goto L_0x0050;
    L_0x004a:
        r9 = r14[r6];
        r8 = r8 + r9;
        r6 = r6 + 1;
        goto L_0x0048;
    L_0x0050:
        r13 = r7 - r4;
        r13 = r13 - r8;
        r14 = 2;
        if (r7 == r5) goto L_0x0060;
    L_0x0056:
        r13 = r13 * 2;
        if (r13 < r8) goto L_0x005b;
    L_0x005a:
        goto L_0x0060;
    L_0x005b:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
    L_0x0060:
        r13 = r11.usingCheckDigit;
        if (r13 == 0) goto L_0x0092;
    L_0x0064:
        r13 = r1.length();
        r13 = r13 - r3;
        r5 = 0;
        r6 = 0;
    L_0x006b:
        if (r5 >= r13) goto L_0x007d;
    L_0x006d:
        r7 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
        r9 = r11.decodeRowResult;
        r9 = r9.charAt(r5);
        r7 = r7.indexOf(r9);
        r6 = r6 + r7;
        r5 = r5 + 1;
        goto L_0x006b;
    L_0x007d:
        r5 = r1.charAt(r13);
        r7 = ALPHABET;
        r6 = r6 % 43;
        r6 = r7[r6];
        if (r5 != r6) goto L_0x008d;
    L_0x0089:
        r1.setLength(r13);
        goto L_0x0092;
    L_0x008d:
        r12 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r12;
    L_0x0092:
        r13 = r1.length();
        if (r13 == 0) goto L_0x00cc;
    L_0x0098:
        r13 = r11.extendedMode;
        if (r13 == 0) goto L_0x00a1;
    L_0x009c:
        r13 = decodeExtended(r1);
        goto L_0x00a5;
    L_0x00a1:
        r13 = r1.toString();
    L_0x00a5:
        r1 = r2[r3];
        r2 = r2[r0];
        r1 = r1 + r2;
        r1 = (float) r1;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r1 = r1 / r2;
        r4 = (float) r4;
        r5 = (float) r8;
        r5 = r5 / r2;
        r4 = r4 + r5;
        r2 = new com.google.zxing.Result;
        r5 = 0;
        r14 = new com.google.zxing.ResultPoint[r14];
        r6 = new com.google.zxing.ResultPoint;
        r12 = (float) r12;
        r6.<init>(r1, r12);
        r14[r0] = r6;
        r0 = new com.google.zxing.ResultPoint;
        r0.<init>(r4, r12);
        r14[r3] = r0;
        r12 = com.google.zxing.BarcodeFormat.CODE_39;
        r2.<init>(r13, r5, r14, r12);
        return r2;
    L_0x00cc:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
    L_0x00d1:
        r4 = r7;
        goto L_0x001a;
    L_0x00d4:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code39Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean z) {
        this(z, false);
    }

    public Code39Reader(boolean z, boolean z2) {
        this.usingCheckDigit = z;
        this.extendedMode = z2;
        this.decodeRowResult = new StringBuilder(true);
        this.counters = new int[true];
    }

    private static int toNarrowWidePattern(int[] iArr) {
        int i;
        int length = iArr.length;
        int i2 = 0;
        while (true) {
            int i3 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            for (int i4 : iArr) {
                if (i4 < i3 && i4 > r2) {
                    i3 = i4;
                }
            }
            int i5 = 0;
            i = 0;
            int i42 = 0;
            for (i2 = 0; i2 < length; i2++) {
                int i6 = iArr[i2];
                if (i6 > i3) {
                    i |= 1 << ((length - 1) - i2);
                    i5++;
                    i42 += i6;
                }
            }
            if (i5 == 3) {
                break;
            } else if (i5 <= 3) {
                return -1;
            } else {
                i2 = i3;
            }
        }
        for (int i7 = 0; i7 < length && i5 > 0; i7++) {
            i2 = iArr[i7];
            if (i2 > i3) {
                i5--;
                if (i2 * 2 >= i42) {
                    return -1;
                }
            }
        }
        return i;
    }

    private static String decodeExtended(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        StringBuilder stringBuilder = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (!(charAt == '+' || charAt == '$' || charAt == '%')) {
                if (charAt != '/') {
                    stringBuilder.append(charAt);
                    i++;
                }
            }
            i++;
            char charAt2 = charSequence.charAt(i);
            if (charAt != '+') {
                if (charAt != '/') {
                    switch (charAt) {
                        case '$':
                            if (charAt2 >= 'A' && charAt2 <= 'Z') {
                                charAt = (char) (charAt2 - 64);
                                break;
                            }
                            throw FormatException.getFormatInstance();
                            break;
                        case '%':
                            if (charAt2 < 'A' || charAt2 > 'E') {
                                if (charAt2 >= 'F' && charAt2 <= 'W') {
                                    charAt = (char) (charAt2 - 11);
                                    break;
                                }
                                throw FormatException.getFormatInstance();
                            }
                            charAt = (char) (charAt2 - 38);
                            break;
                            break;
                        default:
                            charAt = '\u0000';
                            break;
                    }
                } else if (charAt2 >= 'A' && charAt2 <= 'O') {
                    charAt = (char) (charAt2 - 32);
                } else if (charAt2 == 'Z') {
                    charAt = ':';
                } else {
                    throw FormatException.getFormatInstance();
                }
            } else if (charAt2 < 'A' || charAt2 > 'Z') {
                throw FormatException.getFormatInstance();
            } else {
                charAt = (char) (charAt2 + 32);
            }
            stringBuilder.append(charAt);
            i++;
        }
        return stringBuilder.toString();
    }
}
