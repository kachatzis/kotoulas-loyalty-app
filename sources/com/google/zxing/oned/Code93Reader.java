package com.google.zxing.oned;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import okhttp3.internal.http.StatusLine;

public final class Code93Reader extends OneDReader {
    private static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    private static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
    private static final int ASTERISK_ENCODING = CHARACTER_ENCODINGS[47];
    private static final int[] CHARACTER_ENCODINGS = new int[]{276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, StatusLine.HTTP_PERM_REDIRECT, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
    private final int[] counters = new int[6];
    private final StringBuilder decodeRowResult = new StringBuilder(20);

    private static void checkOneChecksum(java.lang.CharSequence r6, int r7, int r8) throws com.google.zxing.ChecksumException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:11:0x002d in {4, 5, 8, 10} preds:[]
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
        r0 = r7 + -1;
        r1 = 1;
        r2 = 0;
        r3 = 1;
    L_0x0005:
        if (r0 < 0) goto L_0x001b;
    L_0x0007:
        r4 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
        r5 = r6.charAt(r0);
        r4 = r4.indexOf(r5);
        r4 = r4 * r3;
        r2 = r2 + r4;
        r3 = r3 + r1;
        if (r3 <= r8) goto L_0x0018;
    L_0x0017:
        r3 = 1;
    L_0x0018:
        r0 = r0 + -1;
        goto L_0x0005;
    L_0x001b:
        r6 = r6.charAt(r7);
        r7 = ALPHABET;
        r2 = r2 % 47;
        r7 = r7[r2];
        if (r6 != r7) goto L_0x0028;
    L_0x0027:
        return;
    L_0x0028:
        r6 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code93Reader.checkOneChecksum(java.lang.CharSequence, int, int):void");
    }

    private int[] findAsteriskPattern(com.google.zxing.common.BitArray r14) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:17:0x0058 in {4, 10, 11, 12, 13, 14, 16} preds:[]
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
        r13 = this;
        r0 = r14.getSize();
        r1 = 0;
        r2 = r14.getNextSet(r1);
        r3 = r13.counters;
        java.util.Arrays.fill(r3, r1);
        r3 = r13.counters;
        r4 = r3.length;
        r7 = r2;
        r5 = 0;
        r6 = 0;
    L_0x0014:
        if (r2 >= r0) goto L_0x0053;
    L_0x0016:
        r8 = r14.get(r2);
        r8 = r8 ^ r5;
        r9 = 1;
        if (r8 == 0) goto L_0x0024;
    L_0x001e:
        r8 = r3[r6];
        r8 = r8 + r9;
        r3[r6] = r8;
        goto L_0x0050;
    L_0x0024:
        r8 = r4 + -1;
        if (r6 != r8) goto L_0x004a;
    L_0x0028:
        r10 = toPattern(r3);
        r11 = ASTERISK_ENCODING;
        r12 = 2;
        if (r10 != r11) goto L_0x0038;
    L_0x0031:
        r14 = new int[r12];
        r14[r1] = r7;
        r14[r9] = r2;
        return r14;
    L_0x0038:
        r10 = r3[r1];
        r11 = r3[r9];
        r10 = r10 + r11;
        r7 = r7 + r10;
        r10 = r4 + -2;
        java.lang.System.arraycopy(r3, r12, r3, r1, r10);
        r3[r10] = r1;
        r3[r8] = r1;
        r6 = r6 + -1;
        goto L_0x004c;
    L_0x004a:
        r6 = r6 + 1;
    L_0x004c:
        r3[r6] = r9;
        r5 = r5 ^ 1;
    L_0x0050:
        r2 = r2 + 1;
        goto L_0x0014;
    L_0x0053:
        r14 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r14;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code93Reader.findAsteriskPattern(com.google.zxing.common.BitArray):int[]");
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code93Reader.patternToChar(int):char");
    }

    public com.google.zxing.Result decodeRow(int r12, com.google.zxing.common.BitArray r13, java.util.Map<com.google.zxing.DecodeHintType, ?> r14) throws com.google.zxing.NotFoundException, com.google.zxing.ChecksumException, com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:25:0x00a7 in {5, 10, 17, 19, 21, 22, 24} preds:[]
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
        r14 = r11.findAsteriskPattern(r13);
        r0 = 1;
        r1 = r14[r0];
        r1 = r13.getNextSet(r1);
        r2 = r13.getSize();
        r3 = r11.counters;
        r4 = 0;
        java.util.Arrays.fill(r3, r4);
        r5 = r11.decodeRowResult;
        r5.setLength(r4);
    L_0x001a:
        com.google.zxing.oned.OneDReader.recordPattern(r13, r1, r3);
        r6 = toPattern(r3);
        if (r6 < 0) goto L_0x00a2;
    L_0x0023:
        r6 = patternToChar(r6);
        r5.append(r6);
        r7 = r3.length;
        r9 = r1;
        r8 = 0;
    L_0x002d:
        if (r8 >= r7) goto L_0x0035;
    L_0x002f:
        r10 = r3[r8];
        r9 = r9 + r10;
        r8 = r8 + 1;
        goto L_0x002d;
    L_0x0035:
        r7 = r13.getNextSet(r9);
        r8 = 42;
        if (r6 != r8) goto L_0x009f;
    L_0x003d:
        r6 = r5.length();
        r6 = r6 - r0;
        r5.deleteCharAt(r6);
        r6 = r3.length;
        r8 = 0;
        r9 = 0;
    L_0x0048:
        if (r8 >= r6) goto L_0x0050;
    L_0x004a:
        r10 = r3[r8];
        r9 = r9 + r10;
        r8 = r8 + 1;
        goto L_0x0048;
    L_0x0050:
        if (r7 == r2) goto L_0x009a;
    L_0x0052:
        r13 = r13.get(r7);
        if (r13 == 0) goto L_0x009a;
    L_0x0058:
        r13 = r5.length();
        r2 = 2;
        if (r13 < r2) goto L_0x0095;
    L_0x005f:
        checkChecksums(r5);
        r13 = r5.length();
        r13 = r13 - r2;
        r5.setLength(r13);
        r13 = decodeExtended(r5);
        r3 = r14[r0];
        r14 = r14[r4];
        r3 = r3 + r14;
        r14 = (float) r3;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r14 = r14 / r3;
        r1 = (float) r1;
        r5 = (float) r9;
        r5 = r5 / r3;
        r1 = r1 + r5;
        r3 = new com.google.zxing.Result;
        r5 = 0;
        r2 = new com.google.zxing.ResultPoint[r2];
        r6 = new com.google.zxing.ResultPoint;
        r12 = (float) r12;
        r6.<init>(r14, r12);
        r2[r4] = r6;
        r14 = new com.google.zxing.ResultPoint;
        r14.<init>(r1, r12);
        r2[r0] = r14;
        r12 = com.google.zxing.BarcodeFormat.CODE_93;
        r3.<init>(r13, r5, r2, r12);
        return r3;
    L_0x0095:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
    L_0x009a:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
    L_0x009f:
        r1 = r7;
        goto L_0x001a;
    L_0x00a2:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code93Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }

    private static int toPattern(int[] iArr) {
        int round;
        int length = iArr.length;
        int i = 0;
        for (int round2 : iArr) {
            i += round2;
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            round2 = Math.round((((float) iArr[i2]) * 9.0f) / ((float) i));
            if (round2 >= 1) {
                if (round2 <= 4) {
                    if ((i2 & 1) == 0) {
                        int i4 = i3;
                        for (i3 = 0; i3 < round2; i3++) {
                            i4 = (i4 << 1) | 1;
                        }
                        i3 = i4;
                    } else {
                        i3 <<= round2;
                    }
                    i2++;
                }
            }
            return -1;
        }
        return i3;
    }

    private static String decodeExtended(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        StringBuilder stringBuilder = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 'a' || charAt > 'd') {
                stringBuilder.append(charAt);
            } else if (i < length - 1) {
                i++;
                char charAt2 = charSequence.charAt(i);
                switch (charAt) {
                    case 'a':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 - 64);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                        break;
                    case 'b':
                        if (charAt2 < 'A' || charAt2 > 'E') {
                            if (charAt2 < 'F' || charAt2 > 'J') {
                                if (charAt2 < 'K' || charAt2 > 'O') {
                                    if (charAt2 < 'P' || charAt2 > 'S') {
                                        if (charAt2 >= 'T' && charAt2 <= 'Z') {
                                            charAt = '';
                                            break;
                                        }
                                        throw FormatException.getFormatInstance();
                                    }
                                    charAt = (char) (charAt2 + 43);
                                    break;
                                }
                                charAt = (char) (charAt2 + 16);
                                break;
                            }
                            charAt = (char) (charAt2 - 11);
                            break;
                        }
                        charAt = (char) (charAt2 - 38);
                        break;
                        break;
                    case 'c':
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            charAt = (char) (charAt2 - 32);
                            break;
                        } else if (charAt2 == 'Z') {
                            charAt = ':';
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    case 'd':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 + 32);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                    default:
                        charAt = '\u0000';
                        break;
                }
                stringBuilder.append(charAt);
            } else {
                throw FormatException.getFormatInstance();
            }
            i++;
        }
        return stringBuilder.toString();
    }

    private static void checkChecksums(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        checkOneChecksum(charSequence, length - 2, 20);
        checkOneChecksum(charSequence, length - 1, 15);
    }
}
