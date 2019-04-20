package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;

final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900 = new BigInteger[16];
    private static final int LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final char[] MIXED_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '&', '\r', '\t', ',', ':', '#', '-', '.', '$', '/', '+', '%', '*', '=', '^'};
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final char[] PUNCT_CHARS = new char[]{';', '<', '>', '@', '[', '\\', ']', '_', '`', '~', '!', '\r', '\t', ',', ':', '\n', '-', '.', '$', '/', '\"', '|', '*', '(', ')', '?', '{', '}', '\''};
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static com.google.zxing.common.DecoderResult decode(int[] r7, java.lang.String r8) throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:29:0x008e in {7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 20, 22, 26, 28} preds:[]
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
        r0 = new java.lang.StringBuilder;
        r1 = r7.length;
        r2 = 2;
        r1 = r1 * 2;
        r0.<init>(r1);
        r1 = DEFAULT_ENCODING;
        r3 = 1;
        r3 = r7[r3];
        r4 = new com.google.zxing.pdf417.PDF417ResultMetadata;
        r4.<init>();
    L_0x0013:
        r5 = 0;
        r5 = r7[r5];
        if (r2 >= r5) goto L_0x0075;
    L_0x0018:
        r5 = 913; // 0x391 float:1.28E-42 double:4.51E-321;
        if (r3 == r5) goto L_0x005c;
    L_0x001c:
        switch(r3) {
            case 900: goto L_0x0057;
            case 901: goto L_0x0052;
            case 902: goto L_0x004d;
            default: goto L_0x001f;
        };
    L_0x001f:
        switch(r3) {
            case 922: goto L_0x0048;
            case 923: goto L_0x0048;
            case 924: goto L_0x0052;
            case 925: goto L_0x0045;
            case 926: goto L_0x0042;
            case 927: goto L_0x002e;
            case 928: goto L_0x0029;
            default: goto L_0x0022;
        };
    L_0x0022:
        r2 = r2 + -1;
        r2 = textCompaction(r7, r2, r0);
        goto L_0x0065;
    L_0x0029:
        r2 = decodeMacroBlock(r7, r2, r4);
        goto L_0x0065;
    L_0x002e:
        r1 = r2 + 1;
        r2 = r7[r2];
        r2 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r2);
        r2 = r2.name();
        r2 = java.nio.charset.Charset.forName(r2);
        r6 = r2;
        r2 = r1;
        r1 = r6;
        goto L_0x0065;
    L_0x0042:
        r2 = r2 + 2;
        goto L_0x0065;
    L_0x0045:
        r2 = r2 + 1;
        goto L_0x0065;
    L_0x0048:
        r7 = com.google.zxing.FormatException.getFormatInstance();
        throw r7;
    L_0x004d:
        r2 = numericCompaction(r7, r2, r0);
        goto L_0x0065;
    L_0x0052:
        r2 = byteCompaction(r3, r7, r1, r2, r0);
        goto L_0x0065;
    L_0x0057:
        r2 = textCompaction(r7, r2, r0);
        goto L_0x0065;
    L_0x005c:
        r3 = r2 + 1;
        r2 = r7[r2];
        r2 = (char) r2;
        r0.append(r2);
        r2 = r3;
    L_0x0065:
        r3 = r7.length;
        if (r2 >= r3) goto L_0x0070;
    L_0x0068:
        r3 = r2 + 1;
        r2 = r7[r2];
        r6 = r3;
        r3 = r2;
        r2 = r6;
        goto L_0x0013;
    L_0x0070:
        r7 = com.google.zxing.FormatException.getFormatInstance();
        throw r7;
    L_0x0075:
        r7 = r0.length();
        if (r7 == 0) goto L_0x0089;
    L_0x007b:
        r7 = new com.google.zxing.common.DecoderResult;
        r0 = r0.toString();
        r1 = 0;
        r7.<init>(r1, r0, r1, r8);
        r7.setOther(r4);
        return r7;
    L_0x0089:
        r7 = com.google.zxing.FormatException.getFormatInstance();
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decode(int[], java.lang.String):com.google.zxing.common.DecoderResult");
    }

    private static java.lang.String decodeBase900toBase10(int[] r6, int r7) throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:10:0x0037 in {3, 7, 9} preds:[]
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
        r0 = java.math.BigInteger.ZERO;
        r1 = 0;
        r2 = r0;
        r0 = 0;
    L_0x0005:
        r3 = 1;
        if (r0 >= r7) goto L_0x0021;
    L_0x0008:
        r4 = EXP900;
        r5 = r7 - r0;
        r5 = r5 - r3;
        r3 = r4[r5];
        r4 = r6[r0];
        r4 = (long) r4;
        r4 = java.math.BigInteger.valueOf(r4);
        r3 = r3.multiply(r4);
        r2 = r2.add(r3);
        r0 = r0 + 1;
        goto L_0x0005;
    L_0x0021:
        r6 = r2.toString();
        r7 = r6.charAt(r1);
        r0 = 49;
        if (r7 != r0) goto L_0x0032;
    L_0x002d:
        r6 = r6.substring(r3);
        return r6;
    L_0x0032:
        r6 = com.google.zxing.FormatException.getFormatInstance();
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeBase900toBase10(int[], int):java.lang.String");
    }

    private static int decodeMacroBlock(int[] r8, int r9, com.google.zxing.pdf417.PDF417ResultMetadata r10) throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:25:0x007f in {4, 13, 15, 17, 18, 21, 22, 24} preds:[]
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
        r0 = r9 + 2;
        r1 = 0;
        r2 = r8[r1];
        if (r0 > r2) goto L_0x007a;
    L_0x0007:
        r0 = 2;
        r2 = new int[r0];
        r3 = r9;
        r9 = 0;
    L_0x000c:
        if (r9 >= r0) goto L_0x0017;
    L_0x000e:
        r4 = r8[r3];
        r2[r9] = r4;
        r9 = r9 + 1;
        r3 = r3 + 1;
        goto L_0x000c;
    L_0x0017:
        r9 = decodeBase900toBase10(r2, r0);
        r9 = java.lang.Integer.parseInt(r9);
        r10.setSegmentIndex(r9);
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r0 = textCompaction(r8, r3, r9);
        r9 = r9.toString();
        r10.setFileId(r9);
        r9 = r8[r0];
        r2 = 923; // 0x39b float:1.293E-42 double:4.56E-321;
        r3 = 922; // 0x39a float:1.292E-42 double:4.555E-321;
        r4 = 1;
        if (r9 != r2) goto L_0x0070;
    L_0x003b:
        r0 = r0 + 1;
        r9 = r8[r1];
        r9 = r9 - r0;
        r9 = new int[r9];
        r2 = 0;
        r5 = 0;
    L_0x0044:
        r6 = r8[r1];
        if (r0 >= r6) goto L_0x0068;
    L_0x0048:
        if (r2 != 0) goto L_0x0068;
    L_0x004a:
        r6 = r0 + 1;
        r0 = r8[r0];
        r7 = 900; // 0x384 float:1.261E-42 double:4.447E-321;
        if (r0 >= r7) goto L_0x0059;
    L_0x0052:
        r7 = r5 + 1;
        r9[r5] = r0;
        r0 = r6;
        r5 = r7;
        goto L_0x0044;
    L_0x0059:
        if (r0 != r3) goto L_0x0063;
    L_0x005b:
        r10.setLastSegment(r4);
        r6 = r6 + 1;
        r0 = r6;
        r2 = 1;
        goto L_0x0044;
    L_0x0063:
        r8 = com.google.zxing.FormatException.getFormatInstance();
        throw r8;
    L_0x0068:
        r8 = java.util.Arrays.copyOf(r9, r5);
        r10.setOptionalData(r8);
        goto L_0x0079;
    L_0x0070:
        r8 = r8[r0];
        if (r8 != r3) goto L_0x0079;
    L_0x0074:
        r10.setLastSegment(r4);
        r0 = r0 + 1;
    L_0x0079:
        return r0;
    L_0x007a:
        r8 = com.google.zxing.FormatException.getFormatInstance();
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeMacroBlock(int[], int, com.google.zxing.pdf417.PDF417ResultMetadata):int");
    }

    static {
        EXP900[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        EXP900[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr = EXP900;
            if (i < bigIntegerArr.length) {
                bigIntegerArr[i] = bigIntegerArr[i - 1].multiply(valueOf);
                i++;
            } else {
                return;
            }
        }
    }

    private DecodedBitStreamParser() {
    }

    private static int textCompaction(int[] iArr, int i, StringBuilder stringBuilder) {
        int[] iArr2 = new int[((iArr[0] - i) * 2)];
        int[] iArr3 = new int[((iArr[0] - i) * 2)];
        Object obj = null;
        int i2 = 0;
        while (i < iArr[0] && r3 == null) {
            int i3 = i + 1;
            i = iArr[i];
            if (i < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i2] = i / 30;
                iArr2[i2 + 1] = i % 30;
                i2 += 2;
                i = i3;
            } else if (i != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i != 928) {
                    switch (i) {
                        case TEXT_COMPACTION_MODE_LATCH /*900*/:
                            i = i2 + 1;
                            iArr2[i2] = TEXT_COMPACTION_MODE_LATCH;
                            i2 = i;
                            i = i3;
                            continue;
                        case BYTE_COMPACTION_MODE_LATCH /*901*/:
                        case NUMERIC_COMPACTION_MODE_LATCH /*902*/:
                            break;
                        default:
                            switch (i) {
                                case MACRO_PDF417_TERMINATOR /*922*/:
                                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                                case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                                    break;
                                default:
                                    i = i3;
                                    continue;
                                    continue;
                            }
                    }
                }
                i = i3 - 1;
                obj = 1;
            } else {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
        }
        decodeTextCompaction(iArr2, iArr3, i2, stringBuilder);
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeTextCompaction(int[] r16, int[] r17, int r18, java.lang.StringBuilder r19) {
        /*
        r0 = r19;
        r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
        r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
        r3 = 0;
        r4 = r1;
        r5 = r2;
        r2 = 0;
        r1 = r18;
    L_0x000c:
        if (r2 >= r1) goto L_0x0133;
    L_0x000e:
        r6 = r16[r2];
        r7 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.C03971.f18x45bba1d;
        r8 = r4.ordinal();
        r7 = r7[r8];
        r8 = 28;
        r9 = 27;
        r10 = 32;
        r11 = 913; // 0x391 float:1.28E-42 double:4.51E-321;
        r12 = 900; // 0x384 float:1.261E-42 double:4.447E-321;
        r13 = 29;
        r14 = 26;
        switch(r7) {
            case 1: goto L_0x00fa;
            case 2: goto L_0x00c6;
            case 3: goto L_0x0086;
            case 4: goto L_0x0066;
            case 5: goto L_0x004c;
            case 6: goto L_0x002b;
            default: goto L_0x0029;
        };
    L_0x0029:
        goto L_0x0129;
    L_0x002b:
        if (r6 >= r13) goto L_0x0035;
    L_0x002d:
        r4 = PUNCT_CHARS;
        r4 = r4[r6];
        r6 = r4;
        r4 = r5;
        goto L_0x012a;
    L_0x0035:
        if (r6 != r13) goto L_0x003c;
    L_0x0037:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
        r6 = 0;
        goto L_0x012a;
    L_0x003c:
        if (r6 != r11) goto L_0x0045;
    L_0x003e:
        r4 = r17[r2];
        r4 = (char) r4;
        r0.append(r4);
        goto L_0x0063;
    L_0x0045:
        if (r6 != r12) goto L_0x0063;
    L_0x0047:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
        r6 = 0;
        goto L_0x012a;
    L_0x004c:
        if (r6 >= r14) goto L_0x0055;
    L_0x004e:
        r6 = r6 + 65;
        r4 = (char) r6;
        r6 = r4;
        r4 = r5;
        goto L_0x012a;
    L_0x0055:
        if (r6 != r14) goto L_0x005c;
    L_0x0057:
        r4 = r5;
        r6 = 32;
        goto L_0x012a;
    L_0x005c:
        if (r6 != r12) goto L_0x0063;
    L_0x005e:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
        r6 = 0;
        goto L_0x012a;
    L_0x0063:
        r4 = r5;
        goto L_0x0129;
    L_0x0066:
        if (r6 >= r13) goto L_0x006e;
    L_0x0068:
        r7 = PUNCT_CHARS;
        r6 = r7[r6];
        goto L_0x012a;
    L_0x006e:
        if (r6 != r13) goto L_0x0075;
    L_0x0070:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
        r6 = 0;
        goto L_0x012a;
    L_0x0075:
        if (r6 != r11) goto L_0x007f;
    L_0x0077:
        r6 = r17[r2];
        r6 = (char) r6;
        r0.append(r6);
        goto L_0x0129;
    L_0x007f:
        if (r6 != r12) goto L_0x0129;
    L_0x0081:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
        r6 = 0;
        goto L_0x012a;
    L_0x0086:
        r7 = 25;
        if (r6 >= r7) goto L_0x0090;
    L_0x008a:
        r7 = MIXED_CHARS;
        r6 = r7[r6];
        goto L_0x012a;
    L_0x0090:
        if (r6 != r7) goto L_0x0097;
    L_0x0092:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT;
        r6 = 0;
        goto L_0x012a;
    L_0x0097:
        if (r6 != r14) goto L_0x009d;
    L_0x0099:
        r6 = 32;
        goto L_0x012a;
    L_0x009d:
        if (r6 != r9) goto L_0x00a4;
    L_0x009f:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER;
        r6 = 0;
        goto L_0x012a;
    L_0x00a4:
        if (r6 != r8) goto L_0x00ab;
    L_0x00a6:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
        r6 = 0;
        goto L_0x012a;
    L_0x00ab:
        if (r6 != r13) goto L_0x00b5;
    L_0x00ad:
        r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT;
        r6 = 0;
        r15 = r5;
        r5 = r4;
        r4 = r15;
        goto L_0x012a;
    L_0x00b5:
        if (r6 != r11) goto L_0x00bf;
    L_0x00b7:
        r6 = r17[r2];
        r6 = (char) r6;
        r0.append(r6);
        goto L_0x0129;
    L_0x00bf:
        if (r6 != r12) goto L_0x0129;
    L_0x00c1:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
        r6 = 0;
        goto L_0x012a;
    L_0x00c6:
        if (r6 >= r14) goto L_0x00cd;
    L_0x00c8:
        r6 = r6 + 97;
        r6 = (char) r6;
        goto L_0x012a;
    L_0x00cd:
        if (r6 != r14) goto L_0x00d3;
    L_0x00cf:
        r6 = 32;
        goto L_0x012a;
    L_0x00d3:
        if (r6 != r9) goto L_0x00dc;
    L_0x00d5:
        r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT;
        r6 = 0;
        r15 = r5;
        r5 = r4;
        r4 = r15;
        goto L_0x012a;
    L_0x00dc:
        if (r6 != r8) goto L_0x00e2;
    L_0x00de:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED;
        r6 = 0;
        goto L_0x012a;
    L_0x00e2:
        if (r6 != r13) goto L_0x00eb;
    L_0x00e4:
        r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT;
        r6 = 0;
        r15 = r5;
        r5 = r4;
        r4 = r15;
        goto L_0x012a;
    L_0x00eb:
        if (r6 != r11) goto L_0x00f4;
    L_0x00ed:
        r6 = r17[r2];
        r6 = (char) r6;
        r0.append(r6);
        goto L_0x0129;
    L_0x00f4:
        if (r6 != r12) goto L_0x0129;
    L_0x00f6:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
        r6 = 0;
        goto L_0x012a;
    L_0x00fa:
        if (r6 >= r14) goto L_0x0100;
    L_0x00fc:
        r6 = r6 + 65;
        r6 = (char) r6;
        goto L_0x012a;
    L_0x0100:
        if (r6 != r14) goto L_0x0105;
    L_0x0102:
        r6 = 32;
        goto L_0x012a;
    L_0x0105:
        if (r6 != r9) goto L_0x010b;
    L_0x0107:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER;
        r6 = 0;
        goto L_0x012a;
    L_0x010b:
        if (r6 != r8) goto L_0x0111;
    L_0x010d:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED;
        r6 = 0;
        goto L_0x012a;
    L_0x0111:
        if (r6 != r13) goto L_0x011a;
    L_0x0113:
        r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT;
        r6 = 0;
        r15 = r5;
        r5 = r4;
        r4 = r15;
        goto L_0x012a;
    L_0x011a:
        if (r6 != r11) goto L_0x0123;
    L_0x011c:
        r6 = r17[r2];
        r6 = (char) r6;
        r0.append(r6);
        goto L_0x0129;
    L_0x0123:
        if (r6 != r12) goto L_0x0129;
    L_0x0125:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
        r6 = 0;
        goto L_0x012a;
    L_0x0129:
        r6 = 0;
    L_0x012a:
        if (r6 == 0) goto L_0x012f;
    L_0x012c:
        r0.append(r6);
    L_0x012f:
        r2 = r2 + 1;
        goto L_0x000c;
    L_0x0133:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeTextCompaction(int[], int[], int, java.lang.StringBuilder):void");
    }

    private static int byteCompaction(int i, int[] iArr, Charset charset, int i2, StringBuilder stringBuilder) {
        int i3;
        int i4 = i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i5 = MACRO_PDF417_TERMINATOR;
        int i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
        int i7 = 928;
        long j = 900;
        int i8 = 0;
        if (i4 == BYTE_COMPACTION_MODE_LATCH) {
            int[] iArr2 = new int[6];
            i3 = i2 + 1;
            int i9 = iArr[i2];
            Object obj = null;
            int i10 = 0;
            long j2 = 0;
            while (i3 < iArr[0] && r13 == null) {
                int i11 = i10 + 1;
                iArr2[i10] = i9;
                j2 = (j2 * j) + ((long) i9);
                int i12 = i3 + 1;
                i9 = iArr[i3];
                if (!(i9 == TEXT_COMPACTION_MODE_LATCH || i9 == BYTE_COMPACTION_MODE_LATCH || i9 == NUMERIC_COMPACTION_MODE_LATCH || i9 == BYTE_COMPACTION_MODE_LATCH_6 || i9 == 928 || i9 == r3)) {
                    if (i9 != i5) {
                        if (i11 % 5 != 0 || i11 <= 0) {
                            i3 = i12;
                            i10 = i11;
                            i5 = MACRO_PDF417_TERMINATOR;
                            i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                            j = 900;
                        } else {
                            int i13 = 0;
                            while (i13 < 6) {
                                byteArrayOutputStream.write((byte) ((int) (j2 >> ((5 - i13) * 8))));
                                i13++;
                                i5 = MACRO_PDF417_TERMINATOR;
                                i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                            }
                            i3 = i12;
                            j = 900;
                            i10 = 0;
                            j2 = 0;
                        }
                    }
                }
                i3 = i12 - 1;
                i10 = i11;
                i5 = MACRO_PDF417_TERMINATOR;
                i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                j = 900;
                obj = 1;
            }
            if (i3 != iArr[0] || i9 >= TEXT_COMPACTION_MODE_LATCH) {
                i5 = i10;
            } else {
                i5 = i10 + 1;
                iArr2[i10] = i9;
            }
            while (i8 < i5) {
                byteArrayOutputStream.write((byte) iArr2[i8]);
                i8++;
            }
        } else if (i4 == BYTE_COMPACTION_MODE_LATCH_6) {
            i4 = i2;
            i5 = 0;
            j = 0;
            Object obj2 = null;
            while (i4 < iArr[0] && r14 == null) {
                i6 = i4 + 1;
                i4 = iArr[i4];
                if (i4 < TEXT_COMPACTION_MODE_LATCH) {
                    i5++;
                    j = (j * 900) + ((long) i4);
                    i4 = i6;
                } else {
                    if (i4 != TEXT_COMPACTION_MODE_LATCH && i4 != BYTE_COMPACTION_MODE_LATCH && i4 != NUMERIC_COMPACTION_MODE_LATCH && i4 != BYTE_COMPACTION_MODE_LATCH_6 && i4 != r4) {
                        if (i4 != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                            if (i4 == MACRO_PDF417_TERMINATOR) {
                                i4 = i6 - 1;
                                obj2 = 1;
                            } else {
                                i4 = i6;
                            }
                        }
                    }
                    i4 = i6 - 1;
                    obj2 = 1;
                }
                if (i5 % 5 == 0 && i5 > 0) {
                    i5 = 0;
                    for (i6 = 6; i5 < i6; i6 = 6) {
                        byteArrayOutputStream.write((byte) ((int) (j >> ((5 - i5) * 8))));
                        i5++;
                    }
                    i5 = 0;
                    j = 0;
                }
                i7 = 928;
            }
            i3 = i4;
        } else {
            i3 = i2;
        }
        stringBuilder.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i3;
    }

    private static int numericCompaction(int[] iArr, int i, StringBuilder stringBuilder) throws FormatException {
        int[] iArr2 = new int[15];
        Object obj = null;
        int i2 = 0;
        while (i < iArr[0] && r2 == null) {
            int i3 = i + 1;
            i = iArr[i];
            if (i3 == iArr[0]) {
                obj = 1;
            }
            if (i < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i2] = i;
                i2++;
            } else if (i == TEXT_COMPACTION_MODE_LATCH || i == BYTE_COMPACTION_MODE_LATCH || i == BYTE_COMPACTION_MODE_LATCH_6 || i == 928 || i == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i == MACRO_PDF417_TERMINATOR) {
                i3--;
                obj = 1;
            }
            if ((i2 % 15 == 0 || i == NUMERIC_COMPACTION_MODE_LATCH || r2 != null) && i2 > 0) {
                stringBuilder.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }
}
