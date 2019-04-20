package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class UPCEANReader extends OneDReader {
    static final int[][] L_AND_G_PATTERNS = new int[20][];
    static final int[][] L_PATTERNS = new int[][]{new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
    private static final float MAX_AVG_VARIANCE = 0.48f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;
    static final int[] MIDDLE_PATTERN = new int[]{1, 1, 1, 1, 1};
    static final int[] START_END_PATTERN = new int[]{1, 1, 1};
    private final StringBuilder decodeRowStringBuffer = new StringBuilder(20);
    private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();
    private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();

    static int decodeDigit(com.google.zxing.common.BitArray r4, int[] r5, int r6, int[][] r7) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:10:0x0025 in {4, 5, 7, 9} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        com.google.zxing.oned.OneDReader.recordPattern(r4, r6, r5);
        r4 = r7.length;
        r6 = 1056293519; // 0x3ef5c28f float:0.48 double:5.218783397E-315;
        r0 = -1;
        r1 = 0;
    L_0x0009:
        if (r1 >= r4) goto L_0x001d;
    L_0x000b:
        r2 = r7[r1];
        r3 = 1060320051; // 0x3f333333 float:0.7 double:5.23867711E-315;
        r2 = com.google.zxing.oned.OneDReader.patternMatchVariance(r5, r2, r3);
        r3 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r3 >= 0) goto L_0x001a;
    L_0x0018:
        r0 = r1;
        r6 = r2;
    L_0x001a:
        r1 = r1 + 1;
        goto L_0x0009;
    L_0x001d:
        if (r0 < 0) goto L_0x0020;
    L_0x001f:
        return r0;
    L_0x0020:
        r4 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCEANReader.decodeDigit(com.google.zxing.common.BitArray, int[], int, int[][]):int");
    }

    private static int[] findGuardPattern(com.google.zxing.common.BitArray r10, int r11, boolean r12, int[] r13, int[] r14) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:25:0x0060 in {2, 3, 8, 14, 15, 16, 19, 20, 21, 22, 24} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r0 = r13.length;
        r1 = r10.getSize();
        if (r12 == 0) goto L_0x000c;
    L_0x0007:
        r11 = r10.getNextUnset(r11);
        goto L_0x0010;
    L_0x000c:
        r11 = r10.getNextSet(r11);
    L_0x0010:
        r2 = 0;
        r4 = r11;
        r3 = 0;
    L_0x0013:
        if (r11 >= r1) goto L_0x005b;
    L_0x0015:
        r5 = r10.get(r11);
        r5 = r5 ^ r12;
        r6 = 1;
        if (r5 == 0) goto L_0x0023;
    L_0x001d:
        r5 = r14[r3];
        r5 = r5 + r6;
        r14[r3] = r5;
        goto L_0x0058;
    L_0x0023:
        r5 = r0 + -1;
        if (r3 != r5) goto L_0x004f;
    L_0x0027:
        r7 = 1060320051; // 0x3f333333 float:0.7 double:5.23867711E-315;
        r7 = com.google.zxing.oned.OneDReader.patternMatchVariance(r14, r13, r7);
        r8 = 1056293519; // 0x3ef5c28f float:0.48 double:5.218783397E-315;
        r9 = 2;
        r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1));
        if (r7 >= 0) goto L_0x003d;
    L_0x0036:
        r10 = new int[r9];
        r10[r2] = r4;
        r10[r6] = r11;
        return r10;
    L_0x003d:
        r7 = r14[r2];
        r8 = r14[r6];
        r7 = r7 + r8;
        r4 = r4 + r7;
        r7 = r0 + -2;
        java.lang.System.arraycopy(r14, r9, r14, r2, r7);
        r14[r7] = r2;
        r14[r5] = r2;
        r3 = r3 + -1;
        goto L_0x0051;
    L_0x004f:
        r3 = r3 + 1;
    L_0x0051:
        r14[r3] = r6;
        if (r12 != 0) goto L_0x0056;
    L_0x0055:
        goto L_0x0057;
    L_0x0056:
        r6 = 0;
    L_0x0057:
        r12 = r6;
    L_0x0058:
        r11 = r11 + 1;
        goto L_0x0013;
    L_0x005b:
        r10 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCEANReader.findGuardPattern(com.google.zxing.common.BitArray, int, boolean, int[], int[]):int[]");
    }

    protected abstract int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder stringBuilder) throws NotFoundException;

    public com.google.zxing.Result decodeRow(int r12, com.google.zxing.common.BitArray r13, int[] r14, java.util.Map<com.google.zxing.DecodeHintType, ?> r15) throws com.google.zxing.NotFoundException, com.google.zxing.ChecksumException, com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:55:0x010d in {2, 3, 6, 9, 12, 24, 25, 27, 28, 34, 35, 36, 38, 40, 44, 47, 48, 50, 52, 54} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r11 = this;
        r0 = 0;
        if (r15 != 0) goto L_0x0005;
    L_0x0003:
        r1 = r0;
        goto L_0x000d;
    L_0x0005:
        r1 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        r1 = r15.get(r1);
        r1 = (com.google.zxing.ResultPointCallback) r1;
    L_0x000d:
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = 1;
        r4 = 0;
        if (r1 == 0) goto L_0x0023;
    L_0x0013:
        r5 = new com.google.zxing.ResultPoint;
        r6 = r14[r4];
        r7 = r14[r3];
        r6 = r6 + r7;
        r6 = (float) r6;
        r6 = r6 / r2;
        r7 = (float) r12;
        r5.<init>(r6, r7);
        r1.foundPossibleResultPoint(r5);
    L_0x0023:
        r5 = r11.decodeRowStringBuffer;
        r5.setLength(r4);
        r6 = r11.decodeMiddle(r13, r14, r5);
        if (r1 == 0) goto L_0x0038;
    L_0x002e:
        r7 = new com.google.zxing.ResultPoint;
        r8 = (float) r6;
        r9 = (float) r12;
        r7.<init>(r8, r9);
        r1.foundPossibleResultPoint(r7);
    L_0x0038:
        r6 = r11.decodeEnd(r13, r6);
        if (r1 == 0) goto L_0x004e;
    L_0x003e:
        r7 = new com.google.zxing.ResultPoint;
        r8 = r6[r4];
        r9 = r6[r3];
        r8 = r8 + r9;
        r8 = (float) r8;
        r8 = r8 / r2;
        r9 = (float) r12;
        r7.<init>(r8, r9);
        r1.foundPossibleResultPoint(r7);
    L_0x004e:
        r1 = r6[r3];
        r7 = r6[r4];
        r7 = r1 - r7;
        r7 = r7 + r1;
        r8 = r13.getSize();
        if (r7 >= r8) goto L_0x0108;
    L_0x005b:
        r1 = r13.isRange(r1, r7, r4);
        if (r1 == 0) goto L_0x0108;
    L_0x0061:
        r1 = r5.toString();
        r5 = r1.length();
        r7 = 8;
        if (r5 < r7) goto L_0x0103;
    L_0x006d:
        r5 = r11.checkChecksum(r1);
        if (r5 == 0) goto L_0x00fe;
    L_0x0073:
        r5 = r14[r3];
        r14 = r14[r4];
        r5 = r5 + r14;
        r14 = (float) r5;
        r14 = r14 / r2;
        r5 = r6[r3];
        r7 = r6[r4];
        r5 = r5 + r7;
        r5 = (float) r5;
        r5 = r5 / r2;
        r2 = r11.getBarcodeFormat();
        r7 = new com.google.zxing.Result;
        r8 = 2;
        r8 = new com.google.zxing.ResultPoint[r8];
        r9 = new com.google.zxing.ResultPoint;
        r10 = (float) r12;
        r9.<init>(r14, r10);
        r8[r4] = r9;
        r14 = new com.google.zxing.ResultPoint;
        r14.<init>(r5, r10);
        r8[r3] = r14;
        r7.<init>(r1, r0, r8, r2);
        r14 = r11.extensionReader;	 Catch:{ ReaderException -> 0x00c4 }
        r5 = r6[r3];	 Catch:{ ReaderException -> 0x00c4 }
        r12 = r14.decodeRow(r12, r13, r5);	 Catch:{ ReaderException -> 0x00c4 }
        r13 = com.google.zxing.ResultMetadataType.UPC_EAN_EXTENSION;	 Catch:{ ReaderException -> 0x00c4 }
        r14 = r12.getText();	 Catch:{ ReaderException -> 0x00c4 }
        r7.putMetadata(r13, r14);	 Catch:{ ReaderException -> 0x00c4 }
        r13 = r12.getResultMetadata();	 Catch:{ ReaderException -> 0x00c4 }
        r7.putAllMetadata(r13);	 Catch:{ ReaderException -> 0x00c4 }
        r13 = r12.getResultPoints();	 Catch:{ ReaderException -> 0x00c4 }
        r7.addResultPoints(r13);	 Catch:{ ReaderException -> 0x00c4 }
        r12 = r12.getText();	 Catch:{ ReaderException -> 0x00c4 }
        r12 = r12.length();	 Catch:{ ReaderException -> 0x00c4 }
        goto L_0x00c5;
    L_0x00c4:
        r12 = 0;
    L_0x00c5:
        if (r15 != 0) goto L_0x00c8;
    L_0x00c7:
        goto L_0x00d1;
    L_0x00c8:
        r13 = com.google.zxing.DecodeHintType.ALLOWED_EAN_EXTENSIONS;
        r13 = r15.get(r13);
        r0 = r13;
        r0 = (int[]) r0;
    L_0x00d1:
        if (r0 == 0) goto L_0x00e8;
    L_0x00d3:
        r13 = r0.length;
        r14 = 0;
    L_0x00d5:
        if (r14 >= r13) goto L_0x00df;
    L_0x00d7:
        r15 = r0[r14];
        if (r12 != r15) goto L_0x00dc;
    L_0x00db:
        goto L_0x00e0;
    L_0x00dc:
        r14 = r14 + 1;
        goto L_0x00d5;
    L_0x00df:
        r3 = 0;
    L_0x00e0:
        if (r3 == 0) goto L_0x00e3;
    L_0x00e2:
        goto L_0x00e8;
    L_0x00e3:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
    L_0x00e8:
        r12 = com.google.zxing.BarcodeFormat.EAN_13;
        if (r2 == r12) goto L_0x00f0;
    L_0x00ec:
        r12 = com.google.zxing.BarcodeFormat.UPC_A;
        if (r2 != r12) goto L_0x00fd;
    L_0x00f0:
        r12 = r11.eanManSupport;
        r12 = r12.lookupCountryIdentifier(r1);
        if (r12 == 0) goto L_0x00fd;
    L_0x00f8:
        r13 = com.google.zxing.ResultMetadataType.POSSIBLE_COUNTRY;
        r7.putMetadata(r13, r12);
    L_0x00fd:
        return r7;
    L_0x00fe:
        r12 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r12;
    L_0x0103:
        r12 = com.google.zxing.FormatException.getFormatInstance();
        throw r12;
    L_0x0108:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCEANReader.decodeRow(int, com.google.zxing.common.BitArray, int[], java.util.Map):com.google.zxing.Result");
    }

    abstract BarcodeFormat getBarcodeFormat();

    static {
        int i = 10;
        System.arraycopy(L_PATTERNS, 0, L_AND_G_PATTERNS, 0, 10);
        while (i < 20) {
            int[] iArr = L_PATTERNS[i - 10];
            int[] iArr2 = new int[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = iArr[(iArr.length - i2) - 1];
            }
            L_AND_G_PATTERNS[i] = iArr2;
            i++;
        }
    }

    protected UPCEANReader() {
    }

    static int[] findStartGuardPattern(BitArray bitArray) throws NotFoundException {
        int[] iArr = new int[START_END_PATTERN.length];
        int[] iArr2 = null;
        boolean z = false;
        int i = 0;
        while (!z) {
            Arrays.fill(iArr, 0, START_END_PATTERN.length, 0);
            iArr2 = findGuardPattern(bitArray, i, false, START_END_PATTERN, iArr);
            i = iArr2[0];
            int i2 = iArr2[1];
            int i3 = i - (i2 - i);
            if (i3 >= 0) {
                z = bitArray.isRange(i3, i, false);
            }
            i = i2;
        }
        return iArr2;
    }

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return decodeRow(i, bitArray, findStartGuardPattern(bitArray), map);
    }

    boolean checkChecksum(String str) throws FormatException {
        return checkStandardUPCEANChecksum(str);
    }

    static boolean checkStandardUPCEANChecksum(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        boolean z = false;
        if (length == 0) {
            return false;
        }
        int i = 0;
        for (int i2 = length - 2; i2 >= 0; i2 -= 2) {
            int charAt = charSequence.charAt(i2) - 48;
            if (charAt < 0 || charAt > 9) {
                throw FormatException.getFormatInstance();
            }
            i += charAt;
        }
        i *= 3;
        for (length--; length >= 0; length -= 2) {
            charAt = charSequence.charAt(length) - 48;
            if (charAt < 0 || charAt > 9) {
                throw FormatException.getFormatInstance();
            }
            i += charAt;
        }
        if (i % 10 == 0) {
            z = true;
        }
        return z;
    }

    int[] decodeEnd(BitArray bitArray, int i) throws NotFoundException {
        return findGuardPattern(bitArray, i, false, START_END_PATTERN);
    }

    static int[] findGuardPattern(BitArray bitArray, int i, boolean z, int[] iArr) throws NotFoundException {
        return findGuardPattern(bitArray, i, z, iArr, new int[iArr.length]);
    }
}
