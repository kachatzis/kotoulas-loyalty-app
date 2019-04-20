package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Map;

public final class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

    static void append8BitBytes(java.lang.String r3, com.google.zxing.common.BitArray r4, java.lang.String r5) throws com.google.zxing.WriterException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:9:0x001a in {4, 5, 8} preds:[]
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
        r3 = r3.getBytes(r5);	 Catch:{ UnsupportedEncodingException -> 0x0013 }
        r5 = r3.length;
        r0 = 0;
    L_0x0006:
        if (r0 >= r5) goto L_0x0012;
    L_0x0008:
        r1 = r3[r0];
        r2 = 8;
        r4.appendBits(r1, r2);
        r0 = r0 + 1;
        goto L_0x0006;
    L_0x0012:
        return;
    L_0x0013:
        r3 = move-exception;
        r4 = new com.google.zxing.WriterException;
        r4.<init>(r3);
        throw r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.append8BitBytes(java.lang.String, com.google.zxing.common.BitArray, java.lang.String):void");
    }

    static void appendKanjiBytes(java.lang.String r6, com.google.zxing.common.BitArray r7) throws com.google.zxing.WriterException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x0055 in {8, 13, 14, 16, 18, 19, 22} preds:[]
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
        r0 = "Shift_JIS";	 Catch:{ UnsupportedEncodingException -> 0x004e }
        r6 = r6.getBytes(r0);	 Catch:{ UnsupportedEncodingException -> 0x004e }
        r0 = r6.length;
        r1 = 0;
    L_0x0008:
        if (r1 >= r0) goto L_0x004d;
    L_0x000a:
        r2 = r6[r1];
        r2 = r2 & 255;
        r3 = r1 + 1;
        r3 = r6[r3];
        r3 = r3 & 255;
        r2 = r2 << 8;
        r2 = r2 | r3;
        r3 = 33088; // 0x8140 float:4.6366E-41 double:1.63476E-319;
        r4 = -1;
        if (r2 < r3) goto L_0x0024;
    L_0x001d:
        r5 = 40956; // 0x9ffc float:5.7392E-41 double:2.0235E-319;
        if (r2 > r5) goto L_0x0024;
    L_0x0022:
        r2 = r2 - r3;
        goto L_0x0034;
    L_0x0024:
        r3 = 57408; // 0xe040 float:8.0446E-41 double:2.83633E-319;
        if (r2 < r3) goto L_0x0033;
    L_0x0029:
        r3 = 60351; // 0xebbf float:8.457E-41 double:2.98174E-319;
        if (r2 > r3) goto L_0x0033;
    L_0x002e:
        r3 = 49472; // 0xc140 float:6.9325E-41 double:2.44424E-319;
        r2 = r2 - r3;
        goto L_0x0034;
    L_0x0033:
        r2 = -1;
    L_0x0034:
        if (r2 == r4) goto L_0x0045;
    L_0x0036:
        r3 = r2 >> 8;
        r3 = r3 * 192;
        r2 = r2 & 255;
        r3 = r3 + r2;
        r2 = 13;
        r7.appendBits(r3, r2);
        r1 = r1 + 2;
        goto L_0x0008;
    L_0x0045:
        r6 = new com.google.zxing.WriterException;
        r7 = "Invalid byte sequence";
        r6.<init>(r7);
        throw r6;
    L_0x004d:
        return;
    L_0x004e:
        r6 = move-exception;
        r7 = new com.google.zxing.WriterException;
        r7.<init>(r6);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.appendKanjiBytes(java.lang.String, com.google.zxing.common.BitArray):void");
    }

    private static com.google.zxing.qrcode.decoder.Version chooseVersion(int r4, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r5) throws com.google.zxing.WriterException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:9:0x0028 in {5, 6, 8} preds:[]
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
        r0 = 1;
    L_0x0001:
        r1 = 40;
        if (r0 > r1) goto L_0x0020;
    L_0x0005:
        r1 = com.google.zxing.qrcode.decoder.Version.getVersionForNumber(r0);
        r2 = r1.getTotalCodewords();
        r3 = r1.getECBlocksForLevel(r5);
        r3 = r3.getTotalECCodewords();
        r2 = r2 - r3;
        r3 = r4 + 7;
        r3 = r3 / 8;
        if (r2 < r3) goto L_0x001d;
    L_0x001c:
        return r1;
    L_0x001d:
        r0 = r0 + 1;
        goto L_0x0001;
    L_0x0020:
        r4 = new com.google.zxing.WriterException;
        r5 = "Data too big";
        r4.<init>(r5);
        throw r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.chooseVersion(int, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel):com.google.zxing.qrcode.decoder.Version");
    }

    static com.google.zxing.common.BitArray interleaveWithECBytes(com.google.zxing.common.BitArray r17, int r18, int r19, int r20) throws com.google.zxing.WriterException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:33:0x00df in {4, 14, 15, 22, 23, 26, 28, 30, 32} preds:[]
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
        r6 = r18;
        r7 = r19;
        r8 = r20;
        r0 = r17.getSizeInBytes();
        if (r0 != r7) goto L_0x00d7;
    L_0x000c:
        r9 = new java.util.ArrayList;
        r9.<init>(r8);
        r10 = 0;
        r11 = 0;
        r12 = 0;
        r13 = 0;
        r14 = 0;
    L_0x0016:
        if (r11 >= r8) goto L_0x0052;
    L_0x0018:
        r0 = 1;
        r15 = new int[r0];
        r5 = new int[r0];
        r0 = r18;
        r1 = r19;
        r2 = r20;
        r3 = r11;
        r4 = r15;
        r16 = r5;
        getNumDataBytesAndNumECBytesForBlockID(r0, r1, r2, r3, r4, r5);
        r0 = r15[r10];
        r1 = new byte[r0];
        r2 = r12 * 8;
        r3 = r17;
        r3.toBytes(r2, r1, r10, r0);
        r2 = r16[r10];
        r2 = generateECBytes(r1, r2);
        r4 = new com.google.zxing.qrcode.encoder.BlockPair;
        r4.<init>(r1, r2);
        r9.add(r4);
        r13 = java.lang.Math.max(r13, r0);
        r0 = r2.length;
        r14 = java.lang.Math.max(r14, r0);
        r0 = r15[r10];
        r12 = r12 + r0;
        r11 = r11 + 1;
        goto L_0x0016;
    L_0x0052:
        if (r7 != r12) goto L_0x00cf;
    L_0x0054:
        r0 = new com.google.zxing.common.BitArray;
        r0.<init>();
        r1 = 0;
    L_0x005a:
        r2 = 8;
        if (r1 >= r13) goto L_0x007e;
    L_0x005e:
        r3 = r9.iterator();
    L_0x0062:
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x007b;
    L_0x0068:
        r4 = r3.next();
        r4 = (com.google.zxing.qrcode.encoder.BlockPair) r4;
        r4 = r4.getDataBytes();
        r5 = r4.length;
        if (r1 >= r5) goto L_0x0062;
    L_0x0075:
        r4 = r4[r1];
        r0.appendBits(r4, r2);
        goto L_0x0062;
    L_0x007b:
        r1 = r1 + 1;
        goto L_0x005a;
    L_0x007e:
        if (r10 >= r14) goto L_0x00a0;
    L_0x0080:
        r1 = r9.iterator();
    L_0x0084:
        r3 = r1.hasNext();
        if (r3 == 0) goto L_0x009d;
    L_0x008a:
        r3 = r1.next();
        r3 = (com.google.zxing.qrcode.encoder.BlockPair) r3;
        r3 = r3.getErrorCorrectionBytes();
        r4 = r3.length;
        if (r10 >= r4) goto L_0x0084;
    L_0x0097:
        r3 = r3[r10];
        r0.appendBits(r3, r2);
        goto L_0x0084;
    L_0x009d:
        r10 = r10 + 1;
        goto L_0x007e;
    L_0x00a0:
        r1 = r0.getSizeInBytes();
        if (r6 != r1) goto L_0x00a7;
    L_0x00a6:
        return r0;
    L_0x00a7:
        r1 = new com.google.zxing.WriterException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Interleaving error: ";
        r2.append(r3);
        r2.append(r6);
        r3 = " and ";
        r2.append(r3);
        r0 = r0.getSizeInBytes();
        r2.append(r0);
        r0 = " differ.";
        r2.append(r0);
        r0 = r2.toString();
        r1.<init>(r0);
        throw r1;
    L_0x00cf:
        r0 = new com.google.zxing.WriterException;
        r1 = "Data bytes does not match offset";
        r0.<init>(r1);
        throw r0;
    L_0x00d7:
        r0 = new com.google.zxing.WriterException;
        r1 = "Number of bits and data bytes does not match";
        r0.<init>(r1);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.interleaveWithECBytes(com.google.zxing.common.BitArray, int, int, int):com.google.zxing.common.BitArray");
    }

    static void terminateBits(int r4, com.google.zxing.common.BitArray r5) throws com.google.zxing.WriterException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:26:0x0073 in {7, 11, 16, 17, 18, 21, 23, 25} preds:[]
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
        r0 = r4 * 8;
        r1 = r5.getSize();
        if (r1 > r0) goto L_0x0050;
    L_0x0008:
        r1 = 0;
        r2 = 0;
    L_0x000a:
        r3 = 4;
        if (r2 >= r3) goto L_0x0019;
    L_0x000d:
        r3 = r5.getSize();
        if (r3 >= r0) goto L_0x0019;
    L_0x0013:
        r5.appendBit(r1);
        r2 = r2 + 1;
        goto L_0x000a;
    L_0x0019:
        r2 = r5.getSize();
        r2 = r2 & 7;
        r3 = 8;
        if (r2 <= 0) goto L_0x002b;
    L_0x0023:
        if (r2 >= r3) goto L_0x002b;
    L_0x0025:
        r5.appendBit(r1);
        r2 = r2 + 1;
        goto L_0x0023;
    L_0x002b:
        r2 = r5.getSizeInBytes();
        r4 = r4 - r2;
    L_0x0030:
        if (r1 >= r4) goto L_0x0041;
    L_0x0032:
        r2 = r1 & 1;
        if (r2 != 0) goto L_0x0039;
    L_0x0036:
        r2 = 236; // 0xec float:3.31E-43 double:1.166E-321;
        goto L_0x003b;
    L_0x0039:
        r2 = 17;
    L_0x003b:
        r5.appendBits(r2, r3);
        r1 = r1 + 1;
        goto L_0x0030;
    L_0x0041:
        r4 = r5.getSize();
        if (r4 != r0) goto L_0x0048;
    L_0x0047:
        return;
    L_0x0048:
        r4 = new com.google.zxing.WriterException;
        r5 = "Bits size does not equal capacity";
        r4.<init>(r5);
        throw r4;
    L_0x0050:
        r4 = new com.google.zxing.WriterException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "data bits cannot fit in the QR Code";
        r1.append(r2);
        r5 = r5.getSize();
        r1.append(r5);
        r5 = " > ";
        r1.append(r5);
        r1.append(r0);
        r5 = r1.toString();
        r4.<init>(r5);
        throw r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.terminateBits(int, com.google.zxing.common.BitArray):void");
    }

    private Encoder() {
    }

    private static int calculateMaskPenalty(ByteMatrix byteMatrix) {
        return ((MaskUtil.applyMaskPenaltyRule1(byteMatrix) + MaskUtil.applyMaskPenaltyRule2(byteMatrix)) + MaskUtil.applyMaskPenaltyRule3(byteMatrix)) + MaskUtil.applyMaskPenaltyRule4(byteMatrix);
    }

    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        return encode(str, errorCorrectionLevel, null);
    }

    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel, Map<EncodeHintType, ?> map) throws WriterException {
        map = map == null ? null : (String) map.get(EncodeHintType.CHARACTER_SET);
        if (map == null) {
            map = DEFAULT_BYTE_MODE_ENCODING;
        }
        Mode chooseMode = chooseMode(str, map);
        BitArray bitArray = new BitArray();
        if (chooseMode == Mode.BYTE && !DEFAULT_BYTE_MODE_ENCODING.equals(map)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(map);
            if (characterSetECIByName != null) {
                appendECI(characterSetECIByName, bitArray);
            }
        }
        appendModeInfo(chooseMode, bitArray);
        BitArray bitArray2 = new BitArray();
        appendBytes(str, chooseMode, bitArray2, map);
        map = chooseVersion((bitArray.getSize() + chooseMode.getCharacterCountBits(chooseVersion((bitArray.getSize() + chooseMode.getCharacterCountBits(Version.getVersionForNumber(1))) + bitArray2.getSize(), errorCorrectionLevel))) + bitArray2.getSize(), errorCorrectionLevel);
        BitArray bitArray3 = new BitArray();
        bitArray3.appendBitArray(bitArray);
        appendLengthInfo(chooseMode == Mode.BYTE ? bitArray2.getSizeInBytes() : str.length(), map, chooseMode, bitArray3);
        bitArray3.appendBitArray(bitArray2);
        str = map.getECBlocksForLevel(errorCorrectionLevel);
        int totalCodewords = map.getTotalCodewords() - str.getTotalECCodewords();
        terminateBits(totalCodewords, bitArray3);
        str = interleaveWithECBytes(bitArray3, map.getTotalCodewords(), totalCodewords, str.getNumBlocks());
        QRCode qRCode = new QRCode();
        qRCode.setECLevel(errorCorrectionLevel);
        qRCode.setMode(chooseMode);
        qRCode.setVersion(map);
        int dimensionForVersion = map.getDimensionForVersion();
        ByteMatrix byteMatrix = new ByteMatrix(dimensionForVersion, dimensionForVersion);
        dimensionForVersion = chooseMaskPattern(str, errorCorrectionLevel, map, byteMatrix);
        qRCode.setMaskPattern(dimensionForVersion);
        MatrixUtil.buildMatrix(str, errorCorrectionLevel, map, dimensionForVersion, byteMatrix);
        qRCode.setMatrix(byteMatrix);
        return qRCode;
    }

    static int getAlphanumericCode(int i) {
        int[] iArr = ALPHANUMERIC_TABLE;
        return i < iArr.length ? iArr[i] : -1;
    }

    public static Mode chooseMode(String str) {
        return chooseMode(str, null);
    }

    private static Mode chooseMode(String str, String str2) {
        if ("Shift_JIS".equals(str2) != null) {
            return isOnlyDoubleByteKanji(str) != null ? Mode.KANJI : Mode.BYTE;
        }
        Object obj = null;
        Object obj2 = null;
        for (str2 = null; str2 < str.length(); str2++) {
            char charAt = str.charAt(str2);
            if (charAt >= '0' && charAt <= '9') {
                obj2 = 1;
            } else if (getAlphanumericCode(charAt) == -1) {
                return Mode.BYTE;
            } else {
                obj = 1;
            }
        }
        if (obj != null) {
            return Mode.ALPHANUMERIC;
        }
        if (obj2 != null) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    private static boolean isOnlyDoubleByteKanji(java.lang.String r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        r1 = "Shift_JIS";	 Catch:{ UnsupportedEncodingException -> 0x002b }
        r5 = r5.getBytes(r1);	 Catch:{ UnsupportedEncodingException -> 0x002b }
        r1 = r5.length;
        r2 = r1 % 2;
        if (r2 == 0) goto L_0x000d;
    L_0x000c:
        return r0;
    L_0x000d:
        r2 = 0;
    L_0x000e:
        if (r2 >= r1) goto L_0x0029;
    L_0x0010:
        r3 = r5[r2];
        r3 = r3 & 255;
        r4 = 129; // 0x81 float:1.81E-43 double:6.37E-322;
        if (r3 < r4) goto L_0x001c;
    L_0x0018:
        r4 = 159; // 0x9f float:2.23E-43 double:7.86E-322;
        if (r3 <= r4) goto L_0x0025;
    L_0x001c:
        r4 = 224; // 0xe0 float:3.14E-43 double:1.107E-321;
        if (r3 < r4) goto L_0x0028;
    L_0x0020:
        r4 = 235; // 0xeb float:3.3E-43 double:1.16E-321;
        if (r3 <= r4) goto L_0x0025;
    L_0x0024:
        goto L_0x0028;
    L_0x0025:
        r2 = r2 + 2;
        goto L_0x000e;
    L_0x0028:
        return r0;
    L_0x0029:
        r5 = 1;
        return r5;
    L_0x002b:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.isOnlyDoubleByteKanji(java.lang.String):boolean");
    }

    private static int chooseMaskPattern(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, ByteMatrix byteMatrix) throws WriterException {
        int i = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i2 = -1;
        for (int i3 = 0; i3 < 8; i3++) {
            MatrixUtil.buildMatrix(bitArray, errorCorrectionLevel, version, i3, byteMatrix);
            int calculateMaskPenalty = calculateMaskPenalty(byteMatrix);
            if (calculateMaskPenalty < i) {
                i2 = i3;
                i = calculateMaskPenalty;
            }
        }
        return i2;
    }

    static void getNumDataBytesAndNumECBytesForBlockID(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 < i3) {
            int i5 = i % i3;
            int i6 = i3 - i5;
            int i7 = i / i3;
            int i8 = i7 + 1;
            i2 /= i3;
            int i9 = i2 + 1;
            i7 -= i2;
            i8 -= i9;
            if (i7 != i8) {
                throw new WriterException("EC bytes mismatch");
            } else if (i3 != i6 + i5) {
                throw new WriterException("RS blocks mismatch");
            } else if (i != ((i2 + i7) * i6) + ((i9 + i8) * i5)) {
                throw new WriterException("Total bytes mismatch");
            } else if (i4 < i6) {
                iArr[0] = i2;
                iArr2[0] = i7;
                return;
            } else {
                iArr[0] = i9;
                iArr2[0] = i8;
                return;
            }
        }
        throw new WriterException("Block ID too large");
    }

    static byte[] generateECBytes(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[(length + i)];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(iArr, i);
        bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) iArr[length + i3];
        }
        return bArr;
    }

    static void appendModeInfo(Mode mode, BitArray bitArray) {
        bitArray.appendBits(mode.getBits(), 4);
    }

    static void appendLengthInfo(int i, Version version, Mode mode, BitArray bitArray) throws WriterException {
        version = mode.getCharacterCountBits(version);
        int i2 = 1 << version;
        if (i < i2) {
            bitArray.appendBits(i, version);
            return;
        }
        bitArray = new StringBuilder();
        bitArray.append(i);
        bitArray.append(" is bigger than ");
        bitArray.append(i2 - 1);
        throw new WriterException(bitArray.toString());
    }

    static void appendBytes(String str, Mode mode, BitArray bitArray, String str2) throws WriterException {
        switch (mode) {
            case NUMERIC:
                appendNumericBytes(str, bitArray);
                return;
            case ALPHANUMERIC:
                appendAlphanumericBytes(str, bitArray);
                return;
            case BYTE:
                append8BitBytes(str, bitArray, str2);
                return;
            case KANJI:
                appendKanjiBytes(str, bitArray);
                return;
            default:
                bitArray = new StringBuilder();
                bitArray.append("Invalid mode: ");
                bitArray.append(mode);
                throw new WriterException(bitArray.toString());
        }
    }

    static void appendNumericBytes(CharSequence charSequence, BitArray bitArray) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - 48;
            int i2 = i + 2;
            if (i2 < length) {
                bitArray.appendBits(((charAt * 100) + ((charSequence.charAt(i + 1) - 48) * 10)) + (charSequence.charAt(i2) - 48), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    bitArray.appendBits((charAt * 10) + (charSequence.charAt(i) - 48), 7);
                    i = i2;
                } else {
                    bitArray.appendBits(charAt, 4);
                }
            }
        }
    }

    static void appendAlphanumericBytes(CharSequence charSequence, BitArray bitArray) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int alphanumericCode = getAlphanumericCode(charSequence.charAt(i));
            if (alphanumericCode != -1) {
                int i2 = i + 1;
                if (i2 < length) {
                    i2 = getAlphanumericCode(charSequence.charAt(i2));
                    if (i2 != -1) {
                        bitArray.appendBits((alphanumericCode * 45) + i2, 11);
                        i += 2;
                    } else {
                        throw new WriterException();
                    }
                }
                bitArray.appendBits(alphanumericCode, 6);
                i = i2;
            } else {
                throw new WriterException();
            }
        }
    }

    private static void appendECI(CharacterSetECI characterSetECI, BitArray bitArray) {
        bitArray.appendBits(Mode.ECI.getBits(), 4);
        bitArray.appendBits(characterSetECI.getValue(), 8);
    }
}
