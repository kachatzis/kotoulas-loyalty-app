package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;

final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', '+', '-', '.', '/', ':'};
    private static final int GB2312_SUBSET = 1;

    static com.google.zxing.common.DecoderResult decode(byte[] r17, com.google.zxing.qrcode.decoder.Version r18, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r19, java.util.Map<com.google.zxing.DecodeHintType, ?> r20) throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:63:0x00f7 in {4, 5, 12, 17, 19, 24, 26, 31, 32, 35, 38, 41, 44, 46, 47, 48, 53, 54, 56, 57, 59, 60, 62} preds:[]
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
        r0 = r18;
        r7 = new com.google.zxing.common.BitSource;
        r8 = r17;
        r7.<init>(r8);
        r9 = new java.lang.StringBuilder;
        r1 = 50;
        r9.<init>(r1);
        r10 = new java.util.ArrayList;
        r11 = 1;
        r10.<init>(r11);
        r1 = 0;
        r2 = -1;
        r12 = 0;
        r14 = r12;
        r13 = 0;
        r15 = -1;
        r16 = -1;
    L_0x001e:
        r1 = r7.available();	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r2 = 4;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r1 >= r2) goto L_0x0029;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x0025:
        r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r6 = r1;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        goto L_0x0032;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x0029:
        r1 = r7.readBits(r2);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r1 = com.google.zxing.qrcode.decoder.Mode.forBits(r1);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r6 = r1;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x0032:
        r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r6 == r1) goto L_0x00c8;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x0036:
        r1 = com.google.zxing.qrcode.decoder.Mode.FNC1_FIRST_POSITION;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r6 == r1) goto L_0x00c5;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x003a:
        r1 = com.google.zxing.qrcode.decoder.Mode.FNC1_SECOND_POSITION;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r6 != r1) goto L_0x0040;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x003e:
        goto L_0x00c5;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x0040:
        r1 = com.google.zxing.qrcode.decoder.Mode.STRUCTURED_APPEND;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r6 != r1) goto L_0x0061;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x0044:
        r1 = r7.available();	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r2 = 16;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r1 < r2) goto L_0x005c;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x004c:
        r1 = 8;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r2 = r7.readBits(r1);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r1 = r7.readBits(r1);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r16 = r1;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r15 = r2;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        goto L_0x00c9;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x005c:
        r0 = com.google.zxing.FormatException.getFormatInstance();	 Catch:{ IllegalArgumentException -> 0x00f2 }
        throw r0;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x0061:
        r1 = com.google.zxing.qrcode.decoder.Mode.ECI;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r6 != r1) goto L_0x0077;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x0065:
        r1 = parseECIValue(r7);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r14 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r1);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r14 == 0) goto L_0x0072;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x006f:
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        goto L_0x00c9;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x0072:
        r0 = com.google.zxing.FormatException.getFormatInstance();	 Catch:{ IllegalArgumentException -> 0x00f2 }
        throw r0;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x0077:
        r1 = com.google.zxing.qrcode.decoder.Mode.HANZI;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r6 != r1) goto L_0x008e;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x007b:
        r1 = r7.readBits(r2);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r2 = r6.getCharacterCountBits(r0);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r2 = r7.readBits(r2);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r1 != r11) goto L_0x008c;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x0089:
        decodeHanziSegment(r7, r9, r2);	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x008c:
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        goto L_0x00c9;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x008e:
        r1 = r6.getCharacterCountBits(r0);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r3 = r7.readBits(r1);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r1 = com.google.zxing.qrcode.decoder.Mode.NUMERIC;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r6 != r1) goto L_0x009f;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x009a:
        decodeNumericSegment(r7, r9, r3);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        goto L_0x00c9;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x009f:
        r1 = com.google.zxing.qrcode.decoder.Mode.ALPHANUMERIC;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r6 != r1) goto L_0x00a8;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x00a3:
        decodeAlphanumericSegment(r7, r9, r3, r13);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        goto L_0x00c9;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x00a8:
        r1 = com.google.zxing.qrcode.decoder.Mode.BYTE;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r6 != r1) goto L_0x00b7;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x00ac:
        r1 = r7;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r2 = r9;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r4 = r14;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r5 = r10;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r6 = r20;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        decodeByteSegment(r1, r2, r3, r4, r5, r6);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        goto L_0x00c9;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x00b7:
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r1 = com.google.zxing.qrcode.decoder.Mode.KANJI;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r11 != r1) goto L_0x00c0;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x00bc:
        decodeKanjiSegment(r7, r9, r3);	 Catch:{ IllegalArgumentException -> 0x00f2 }
        goto L_0x00c9;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x00c0:
        r0 = com.google.zxing.FormatException.getFormatInstance();	 Catch:{ IllegalArgumentException -> 0x00f2 }
        throw r0;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x00c5:
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        r13 = 1;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        goto L_0x00c9;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x00c8:
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00f2 }
    L_0x00c9:
        r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;	 Catch:{ IllegalArgumentException -> 0x00f2 }
        if (r11 != r1) goto L_0x00ef;
    L_0x00cd:
        r7 = new com.google.zxing.common.DecoderResult;
        r2 = r9.toString();
        r0 = r10.isEmpty();
        if (r0 == 0) goto L_0x00db;
    L_0x00d9:
        r3 = r12;
        goto L_0x00dc;
    L_0x00db:
        r3 = r10;
    L_0x00dc:
        if (r19 != 0) goto L_0x00e0;
    L_0x00de:
        r4 = r12;
        goto L_0x00e5;
    L_0x00e0:
        r0 = r19.toString();
        r4 = r0;
    L_0x00e5:
        r0 = r7;
        r1 = r17;
        r5 = r15;
        r6 = r16;
        r0.<init>(r1, r2, r3, r4, r5, r6);
        return r7;
    L_0x00ef:
        r11 = 1;
        goto L_0x001e;
    L_0x00f2:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decode(byte[], com.google.zxing.qrcode.decoder.Version, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):com.google.zxing.common.DecoderResult");
    }

    private static void decodeByteSegment(com.google.zxing.common.BitSource r3, java.lang.StringBuilder r4, int r5, com.google.zxing.common.CharacterSetECI r6, java.util.Collection<byte[]> r7, java.util.Map<com.google.zxing.DecodeHintType, ?> r8) throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x003a in {4, 6, 7, 11, 13, 15} preds:[]
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
        r0 = r5 * 8;
        r1 = r3.available();
        if (r0 > r1) goto L_0x0035;
    L_0x0008:
        r0 = new byte[r5];
        r1 = 0;
    L_0x000b:
        if (r1 >= r5) goto L_0x0019;
    L_0x000d:
        r2 = 8;
        r2 = r3.readBits(r2);
        r2 = (byte) r2;
        r0[r1] = r2;
        r1 = r1 + 1;
        goto L_0x000b;
    L_0x0019:
        if (r6 != 0) goto L_0x0020;
    L_0x001b:
        r3 = com.google.zxing.common.StringUtils.guessEncoding(r0, r8);
        goto L_0x0024;
    L_0x0020:
        r3 = r6.name();
    L_0x0024:
        r5 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0030 }
        r5.<init>(r0, r3);	 Catch:{ UnsupportedEncodingException -> 0x0030 }
        r4.append(r5);	 Catch:{ UnsupportedEncodingException -> 0x0030 }
        r7.add(r0);
        return;
    L_0x0030:
        r3 = com.google.zxing.FormatException.getFormatInstance();
        throw r3;
    L_0x0035:
        r3 = com.google.zxing.FormatException.getFormatInstance();
        throw r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decodeByteSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder, int, com.google.zxing.common.CharacterSetECI, java.util.Collection, java.util.Map):void");
    }

    private static void decodeHanziSegment(com.google.zxing.common.BitSource r4, java.lang.StringBuilder r5, int r6) throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x0051 in {6, 7, 8, 11, 13, 15} preds:[]
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
        r0 = r6 * 13;
        r1 = r4.available();
        if (r0 > r1) goto L_0x004c;
    L_0x0008:
        r0 = r6 * 2;
        r0 = new byte[r0];
        r1 = 0;
    L_0x000d:
        if (r6 <= 0) goto L_0x003c;
    L_0x000f:
        r2 = 13;
        r2 = r4.readBits(r2);
        r3 = r2 / 96;
        r3 = r3 << 8;
        r2 = r2 % 96;
        r2 = r2 | r3;
        r3 = 959; // 0x3bf float:1.344E-42 double:4.74E-321;
        if (r2 >= r3) goto L_0x0025;
    L_0x0020:
        r3 = 41377; // 0xa1a1 float:5.7982E-41 double:2.0443E-319;
        r2 = r2 + r3;
        goto L_0x0029;
    L_0x0025:
        r3 = 42657; // 0xa6a1 float:5.9775E-41 double:2.10754E-319;
        r2 = r2 + r3;
    L_0x0029:
        r3 = r2 >> 8;
        r3 = r3 & 255;
        r3 = (byte) r3;
        r0[r1] = r3;
        r3 = r1 + 1;
        r2 = r2 & 255;
        r2 = (byte) r2;
        r0[r3] = r2;
        r1 = r1 + 2;
        r6 = r6 + -1;
        goto L_0x000d;
    L_0x003c:
        r4 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0047 }
        r6 = "GB2312";	 Catch:{ UnsupportedEncodingException -> 0x0047 }
        r4.<init>(r0, r6);	 Catch:{ UnsupportedEncodingException -> 0x0047 }
        r5.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x0047 }
        return;
    L_0x0047:
        r4 = com.google.zxing.FormatException.getFormatInstance();
        throw r4;
    L_0x004c:
        r4 = com.google.zxing.FormatException.getFormatInstance();
        throw r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decodeHanziSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder, int):void");
    }

    private static void decodeKanjiSegment(com.google.zxing.common.BitSource r4, java.lang.StringBuilder r5, int r6) throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x004d in {6, 7, 8, 11, 13, 15} preds:[]
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
        r0 = r6 * 13;
        r1 = r4.available();
        if (r0 > r1) goto L_0x0048;
    L_0x0008:
        r0 = r6 * 2;
        r0 = new byte[r0];
        r1 = 0;
    L_0x000d:
        if (r6 <= 0) goto L_0x0038;
    L_0x000f:
        r2 = 13;
        r2 = r4.readBits(r2);
        r3 = r2 / 192;
        r3 = r3 << 8;
        r2 = r2 % 192;
        r2 = r2 | r3;
        r3 = 7936; // 0x1f00 float:1.1121E-41 double:3.921E-320;
        if (r2 >= r3) goto L_0x0025;
    L_0x0020:
        r3 = 33088; // 0x8140 float:4.6366E-41 double:1.63476E-319;
        r2 = r2 + r3;
        goto L_0x0029;
    L_0x0025:
        r3 = 49472; // 0xc140 float:6.9325E-41 double:2.44424E-319;
        r2 = r2 + r3;
    L_0x0029:
        r3 = r2 >> 8;
        r3 = (byte) r3;
        r0[r1] = r3;
        r3 = r1 + 1;
        r2 = (byte) r2;
        r0[r3] = r2;
        r1 = r1 + 2;
        r6 = r6 + -1;
        goto L_0x000d;
    L_0x0038:
        r4 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0043 }
        r6 = "SJIS";	 Catch:{ UnsupportedEncodingException -> 0x0043 }
        r4.<init>(r0, r6);	 Catch:{ UnsupportedEncodingException -> 0x0043 }
        r5.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x0043 }
        return;
    L_0x0043:
        r4 = com.google.zxing.FormatException.getFormatInstance();
        throw r4;
    L_0x0048:
        r4 = com.google.zxing.FormatException.getFormatInstance();
        throw r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decodeKanjiSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder, int):void");
    }

    private DecodedBitStreamParser() {
    }

    private static char toAlphaNumericChar(int i) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw FormatException.getFormatInstance();
    }

    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuilder stringBuilder, int i, boolean z) throws FormatException {
        while (i > 1) {
            if (bitSource.available() >= 11) {
                int readBits = bitSource.readBits(11);
                stringBuilder.append(toAlphaNumericChar(readBits / 45));
                stringBuilder.append(toAlphaNumericChar(readBits % 45));
                i -= 2;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i == 1) {
            if (bitSource.available() >= 6) {
                stringBuilder.append(toAlphaNumericChar(bitSource.readBits(6)));
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (z) {
            for (int length = stringBuilder.length(); length < stringBuilder.length(); length++) {
                if (stringBuilder.charAt(length) == 37) {
                    if (length < stringBuilder.length() - 1) {
                        bitSource = length + 1;
                        if (stringBuilder.charAt(bitSource)) {
                            stringBuilder.deleteCharAt(bitSource);
                        }
                    }
                    stringBuilder.setCharAt(length, '\u001d');
                }
            }
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuilder stringBuilder, int i) throws FormatException {
        while (i >= 3) {
            if (bitSource.available() >= 10) {
                int readBits = bitSource.readBits(10);
                if (readBits < 1000) {
                    stringBuilder.append(toAlphaNumericChar(readBits / 100));
                    stringBuilder.append(toAlphaNumericChar((readBits / 10) % 10));
                    stringBuilder.append(toAlphaNumericChar(readBits % 10));
                    i -= 3;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            throw FormatException.getFormatInstance();
        }
        if (i == 2) {
            if (bitSource.available() >= 7) {
                bitSource = bitSource.readBits(7);
                if (bitSource < 100) {
                    stringBuilder.append(toAlphaNumericChar(bitSource / 10));
                    stringBuilder.append(toAlphaNumericChar(bitSource % 10));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        } else if (i != 1) {
        } else {
            if (bitSource.available() >= 4) {
                bitSource = bitSource.readBits(4);
                if (bitSource < 10) {
                    stringBuilder.append(toAlphaNumericChar(bitSource));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        }
    }

    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int readBits = bitSource.readBits(8);
        if ((readBits & 128) == 0) {
            return readBits & 127;
        }
        if ((readBits & 192) == 128) {
            return bitSource.readBits(8) | ((readBits & 63) << 8);
        }
        if ((readBits & 224) == 192) {
            return bitSource.readBits(16) | ((readBits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }
}
