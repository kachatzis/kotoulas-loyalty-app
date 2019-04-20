package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Version;

final class MatrixUtil {
    private static final int[][] POSITION_ADJUSTMENT_PATTERN = new int[][]{new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE = new int[][]{new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};
    private static final int[][] POSITION_DETECTION_PATTERN = new int[][]{new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] TYPE_INFO_COORDINATES = new int[][]{new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};
    private static final int TYPE_INFO_MASK_PATTERN = 21522;
    private static final int TYPE_INFO_POLY = 1335;
    private static final int VERSION_INFO_POLY = 7973;

    static int calculateBCHCode(int r2, int r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:8:0x0021 in {4, 5, 7} preds:[]
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
        if (r3 == 0) goto L_0x0019;
    L_0x0002:
        r0 = findMSBSet(r3);
        r1 = r0 + -1;
        r2 = r2 << r1;
    L_0x0009:
        r1 = findMSBSet(r2);
        if (r1 < r0) goto L_0x0018;
    L_0x000f:
        r1 = findMSBSet(r2);
        r1 = r1 - r0;
        r1 = r3 << r1;
        r2 = r2 ^ r1;
        goto L_0x0009;
    L_0x0018:
        return r2;
    L_0x0019:
        r2 = new java.lang.IllegalArgumentException;
        r3 = "0 polynomial";
        r2.<init>(r3);
        throw r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.MatrixUtil.calculateBCHCode(int, int):int");
    }

    static void embedDataBits(com.google.zxing.common.BitArray r11, int r12, com.google.zxing.qrcode.encoder.ByteMatrix r13) throws com.google.zxing.WriterException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:33:0x0084 in {4, 13, 16, 17, 22, 23, 24, 25, 26, 27, 30, 32} preds:[]
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
        r0 = r13.getWidth();
        r1 = 1;
        r0 = r0 - r1;
        r2 = r13.getHeight();
        r2 = r2 - r1;
        r3 = -1;
        r4 = 0;
        r5 = r2;
        r2 = 0;
        r6 = -1;
    L_0x0010:
        if (r0 <= 0) goto L_0x005a;
    L_0x0012:
        r7 = 6;
        if (r0 != r7) goto L_0x0017;
    L_0x0015:
        r0 = r0 + -1;
    L_0x0017:
        if (r5 < 0) goto L_0x0055;
    L_0x0019:
        r7 = r13.getHeight();
        if (r5 >= r7) goto L_0x0055;
    L_0x001f:
        r7 = r2;
        r2 = 0;
    L_0x0021:
        r8 = 2;
        if (r2 >= r8) goto L_0x0052;
    L_0x0024:
        r8 = r0 - r2;
        r9 = r13.get(r8, r5);
        r9 = isEmpty(r9);
        if (r9 != 0) goto L_0x0031;
    L_0x0030:
        goto L_0x004f;
    L_0x0031:
        r9 = r11.getSize();
        if (r7 >= r9) goto L_0x003e;
    L_0x0037:
        r9 = r11.get(r7);
        r7 = r7 + 1;
        goto L_0x003f;
    L_0x003e:
        r9 = 0;
    L_0x003f:
        if (r12 == r3) goto L_0x004c;
    L_0x0041:
        r10 = com.google.zxing.qrcode.encoder.MaskUtil.getDataMaskBit(r12, r8, r5);
        if (r10 == 0) goto L_0x004c;
    L_0x0047:
        if (r9 != 0) goto L_0x004b;
    L_0x0049:
        r9 = 1;
        goto L_0x004c;
    L_0x004b:
        r9 = 0;
    L_0x004c:
        r13.set(r8, r5, r9);
    L_0x004f:
        r2 = r2 + 1;
        goto L_0x0021;
    L_0x0052:
        r5 = r5 + r6;
        r2 = r7;
        goto L_0x0017;
    L_0x0055:
        r6 = -r6;
        r5 = r5 + r6;
        r0 = r0 + -2;
        goto L_0x0010;
    L_0x005a:
        r12 = r11.getSize();
        if (r2 != r12) goto L_0x0061;
    L_0x0060:
        return;
    L_0x0061:
        r12 = new com.google.zxing.WriterException;
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r0 = "Not all bits consumed: ";
        r13.append(r0);
        r13.append(r2);
        r0 = 47;
        r13.append(r0);
        r11 = r11.getSize();
        r13.append(r11);
        r11 = r13.toString();
        r12.<init>(r11);
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.MatrixUtil.embedDataBits(com.google.zxing.common.BitArray, int, com.google.zxing.qrcode.encoder.ByteMatrix):void");
    }

    static int findMSBSet(int i) {
        int i2 = 0;
        while (i != 0) {
            i >>>= 1;
            i2++;
        }
        return i2;
    }

    private static boolean isEmpty(int i) {
        return i == -1;
    }

    private MatrixUtil() {
    }

    static void clearMatrix(ByteMatrix byteMatrix) {
        byteMatrix.clear((byte) -1);
    }

    static void buildMatrix(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, int i, ByteMatrix byteMatrix) throws WriterException {
        clearMatrix(byteMatrix);
        embedBasicPatterns(version, byteMatrix);
        embedTypeInfo(errorCorrectionLevel, i, byteMatrix);
        maybeEmbedVersionInfo(version, byteMatrix);
        embedDataBits(bitArray, i, byteMatrix);
    }

    static void embedBasicPatterns(Version version, ByteMatrix byteMatrix) throws WriterException {
        embedPositionDetectionPatternsAndSeparators(byteMatrix);
        embedDarkDotAtLeftBottomCorner(byteMatrix);
        maybeEmbedPositionAdjustmentPatterns(version, byteMatrix);
        embedTimingPatterns(byteMatrix);
    }

    static void embedTypeInfo(ErrorCorrectionLevel errorCorrectionLevel, int i, ByteMatrix byteMatrix) throws WriterException {
        BitArray bitArray = new BitArray();
        makeTypeInfoBits(errorCorrectionLevel, i, bitArray);
        for (i = 0; i < bitArray.getSize(); i++) {
            boolean z = bitArray.get((bitArray.getSize() - 1) - i);
            int[][] iArr = TYPE_INFO_COORDINATES;
            byteMatrix.set(iArr[i][null], iArr[i][1], z);
            if (i < 8) {
                byteMatrix.set((byteMatrix.getWidth() - i) - 1, 8, z);
            } else {
                byteMatrix.set(8, (byteMatrix.getHeight() - 7) + (i - 8), z);
            }
        }
    }

    static void maybeEmbedVersionInfo(Version version, ByteMatrix byteMatrix) throws WriterException {
        if (version.getVersionNumber() >= 7) {
            BitArray bitArray = new BitArray();
            makeVersionInfoBits(version, bitArray);
            int i = null;
            int i2 = 17;
            while (i < 6) {
                int i3 = i2;
                for (i2 = 0; i2 < 3; i2++) {
                    boolean z = bitArray.get(i3);
                    i3--;
                    byteMatrix.set(i, (byteMatrix.getHeight() - 11) + i2, z);
                    byteMatrix.set((byteMatrix.getHeight() - 11) + i2, i, z);
                }
                i++;
                i2 = i3;
            }
        }
    }

    static void makeTypeInfoBits(ErrorCorrectionLevel errorCorrectionLevel, int i, BitArray bitArray) throws WriterException {
        if (QRCode.isValidMaskPattern(i)) {
            errorCorrectionLevel = (errorCorrectionLevel.getBits() << 3) | i;
            bitArray.appendBits(errorCorrectionLevel, 5);
            bitArray.appendBits(calculateBCHCode(errorCorrectionLevel, TYPE_INFO_POLY), 10);
            errorCorrectionLevel = new BitArray();
            errorCorrectionLevel.appendBits(TYPE_INFO_MASK_PATTERN, 15);
            bitArray.xor(errorCorrectionLevel);
            if (bitArray.getSize() != 15) {
                i = new StringBuilder();
                i.append("should not happen but we got: ");
                i.append(bitArray.getSize());
                throw new WriterException(i.toString());
            }
            return;
        }
        throw new WriterException("Invalid mask pattern");
    }

    static void makeVersionInfoBits(Version version, BitArray bitArray) throws WriterException {
        bitArray.appendBits(version.getVersionNumber(), 6);
        bitArray.appendBits(calculateBCHCode(version.getVersionNumber(), VERSION_INFO_POLY), 12);
        if (bitArray.getSize() != 18) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("should not happen but we got: ");
            stringBuilder.append(bitArray.getSize());
            throw new WriterException(stringBuilder.toString());
        }
    }

    private static void embedTimingPatterns(ByteMatrix byteMatrix) {
        int i = 8;
        while (i < byteMatrix.getWidth() - 8) {
            int i2 = i + 1;
            int i3 = i2 % 2;
            if (isEmpty(byteMatrix.get(i, 6))) {
                byteMatrix.set(i, 6, i3);
            }
            if (isEmpty(byteMatrix.get(6, i))) {
                byteMatrix.set(6, i, i3);
            }
            i = i2;
        }
    }

    private static void embedDarkDotAtLeftBottomCorner(ByteMatrix byteMatrix) throws WriterException {
        if (byteMatrix.get(8, byteMatrix.getHeight() - 8) != (byte) 0) {
            byteMatrix.set(8, byteMatrix.getHeight() - 8, 1);
            return;
        }
        throw new WriterException();
    }

    private static void embedHorizontalSeparationPattern(int i, int i2, ByteMatrix byteMatrix) throws WriterException {
        int i3 = 0;
        while (i3 < 8) {
            int i4 = i + i3;
            if (isEmpty(byteMatrix.get(i4, i2))) {
                byteMatrix.set(i4, i2, 0);
                i3++;
            } else {
                throw new WriterException();
            }
        }
    }

    private static void embedVerticalSeparationPattern(int i, int i2, ByteMatrix byteMatrix) throws WriterException {
        int i3 = 0;
        while (i3 < 7) {
            int i4 = i2 + i3;
            if (isEmpty(byteMatrix.get(i, i4))) {
                byteMatrix.set(i, i4, 0);
                i3++;
            } else {
                throw new WriterException();
            }
        }
    }

    private static void embedPositionAdjustmentPattern(int i, int i2, ByteMatrix byteMatrix) {
        for (int i3 = 0; i3 < 5; i3++) {
            for (int i4 = 0; i4 < 5; i4++) {
                byteMatrix.set(i + i4, i2 + i3, POSITION_ADJUSTMENT_PATTERN[i3][i4]);
            }
        }
    }

    private static void embedPositionDetectionPattern(int i, int i2, ByteMatrix byteMatrix) {
        for (int i3 = 0; i3 < 7; i3++) {
            for (int i4 = 0; i4 < 7; i4++) {
                byteMatrix.set(i + i4, i2 + i3, POSITION_DETECTION_PATTERN[i3][i4]);
            }
        }
    }

    private static void embedPositionDetectionPatternsAndSeparators(ByteMatrix byteMatrix) throws WriterException {
        int length = POSITION_DETECTION_PATTERN[0].length;
        embedPositionDetectionPattern(0, 0, byteMatrix);
        embedPositionDetectionPattern(byteMatrix.getWidth() - length, 0, byteMatrix);
        embedPositionDetectionPattern(0, byteMatrix.getWidth() - length, byteMatrix);
        embedHorizontalSeparationPattern(0, 7, byteMatrix);
        embedHorizontalSeparationPattern(byteMatrix.getWidth() - 8, 7, byteMatrix);
        embedHorizontalSeparationPattern(0, byteMatrix.getWidth() - 8, byteMatrix);
        embedVerticalSeparationPattern(7, 0, byteMatrix);
        embedVerticalSeparationPattern((byteMatrix.getHeight() - 7) - 1, 0, byteMatrix);
        embedVerticalSeparationPattern(7, byteMatrix.getHeight() - 7, byteMatrix);
    }

    private static void maybeEmbedPositionAdjustmentPatterns(Version version, ByteMatrix byteMatrix) {
        if (version.getVersionNumber() >= 2) {
            version = version.getVersionNumber() - 1;
            int[][] iArr = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE;
            int[] iArr2 = iArr[version];
            version = iArr[version].length;
            for (int i = 0; i < version; i++) {
                for (int i2 = 0; i2 < version; i2++) {
                    int i3 = iArr2[i];
                    int i4 = iArr2[i2];
                    if (i4 != -1) {
                        if (i3 != -1) {
                            if (isEmpty(byteMatrix.get(i4, i3))) {
                                embedPositionAdjustmentPattern(i4 - 2, i3 - 2, byteMatrix);
                            }
                        }
                    }
                }
            }
        }
    }
}
