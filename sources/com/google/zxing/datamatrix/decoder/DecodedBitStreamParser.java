package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.DecoderResult;
import java.util.ArrayList;
import java.util.List;

final class DecodedBitStreamParser {
    private static final char[] C40_BASIC_SET_CHARS = new char[]{'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] C40_SHIFT2_SET_CHARS = new char[]{'!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_'};
    private static final char[] TEXT_BASIC_SET_CHARS = new char[]{'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] TEXT_SHIFT2_SET_CHARS = C40_SHIFT2_SET_CHARS;
    private static final char[] TEXT_SHIFT3_SET_CHARS = new char[]{'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', ''};

    private enum Mode {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    private static com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode decodeAsciiSegment(com.google.zxing.common.BitSource r5, java.lang.StringBuilder r6, java.lang.StringBuilder r7) throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:74:0x00b7 in {6, 8, 12, 17, 18, 22, 26, 29, 34, 37, 40, 43, 47, 51, 55, 58, 65, 67, 71, 73} preds:[]
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
        r1 = 0;
    L_0x0002:
        r2 = 8;
        r2 = r5.readBits(r2);
        if (r2 == 0) goto L_0x00b2;
    L_0x000a:
        r3 = 1;
        r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r2 > r4) goto L_0x001b;
    L_0x000f:
        if (r1 == 0) goto L_0x0013;
    L_0x0011:
        r2 = r2 + 128;
    L_0x0013:
        r2 = r2 - r3;
        r5 = (char) r2;
        r6.append(r5);
        r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE;
        return r5;
    L_0x001b:
        r4 = 129; // 0x81 float:1.81E-43 double:6.37E-322;
        if (r2 != r4) goto L_0x0022;
    L_0x001f:
        r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.PAD_ENCODE;
        return r5;
    L_0x0022:
        r4 = 229; // 0xe5 float:3.21E-43 double:1.13E-321;
        if (r2 > r4) goto L_0x0036;
    L_0x0026:
        r2 = r2 + -130;
        r3 = 10;
        if (r2 >= r3) goto L_0x0031;
    L_0x002c:
        r3 = 48;
        r6.append(r3);
    L_0x0031:
        r6.append(r2);
        goto L_0x00a9;
    L_0x0036:
        r4 = 230; // 0xe6 float:3.22E-43 double:1.136E-321;
        if (r2 != r4) goto L_0x003d;
    L_0x003a:
        r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.C40_ENCODE;
        return r5;
    L_0x003d:
        r4 = 231; // 0xe7 float:3.24E-43 double:1.14E-321;
        if (r2 != r4) goto L_0x0044;
    L_0x0041:
        r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.BASE256_ENCODE;
        return r5;
    L_0x0044:
        r4 = 232; // 0xe8 float:3.25E-43 double:1.146E-321;
        if (r2 != r4) goto L_0x004e;
    L_0x0048:
        r2 = 29;
        r6.append(r2);
        goto L_0x00a9;
    L_0x004e:
        r4 = 233; // 0xe9 float:3.27E-43 double:1.15E-321;
        if (r2 == r4) goto L_0x00a9;
    L_0x0052:
        r4 = 234; // 0xea float:3.28E-43 double:1.156E-321;
        if (r2 != r4) goto L_0x0057;
    L_0x0056:
        goto L_0x00a9;
    L_0x0057:
        r4 = 235; // 0xeb float:3.3E-43 double:1.16E-321;
        if (r2 != r4) goto L_0x005d;
    L_0x005b:
        r1 = 1;
        goto L_0x00a9;
    L_0x005d:
        r3 = 236; // 0xec float:3.31E-43 double:1.166E-321;
        if (r2 != r3) goto L_0x006c;
    L_0x0061:
        r2 = "[)>\u001e05\u001d";
        r6.append(r2);
        r2 = "\u001e\u0004";
        r7.insert(r0, r2);
        goto L_0x00a9;
    L_0x006c:
        r3 = 237; // 0xed float:3.32E-43 double:1.17E-321;
        if (r2 != r3) goto L_0x007b;
    L_0x0070:
        r2 = "[)>\u001e06\u001d";
        r6.append(r2);
        r2 = "\u001e\u0004";
        r7.insert(r0, r2);
        goto L_0x00a9;
    L_0x007b:
        r3 = 238; // 0xee float:3.34E-43 double:1.176E-321;
        if (r2 != r3) goto L_0x0082;
    L_0x007f:
        r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ANSIX12_ENCODE;
        return r5;
    L_0x0082:
        r3 = 239; // 0xef float:3.35E-43 double:1.18E-321;
        if (r2 != r3) goto L_0x0089;
    L_0x0086:
        r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.TEXT_ENCODE;
        return r5;
    L_0x0089:
        r3 = 240; // 0xf0 float:3.36E-43 double:1.186E-321;
        if (r2 != r3) goto L_0x0090;
    L_0x008d:
        r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.EDIFACT_ENCODE;
        return r5;
    L_0x0090:
        r3 = 241; // 0xf1 float:3.38E-43 double:1.19E-321;
        if (r2 != r3) goto L_0x0095;
    L_0x0094:
        goto L_0x00a9;
    L_0x0095:
        r3 = 242; // 0xf2 float:3.39E-43 double:1.196E-321;
        if (r2 < r3) goto L_0x00a9;
    L_0x0099:
        r3 = 254; // 0xfe float:3.56E-43 double:1.255E-321;
        if (r2 != r3) goto L_0x00a4;
    L_0x009d:
        r2 = r5.available();
        if (r2 != 0) goto L_0x00a4;
    L_0x00a3:
        goto L_0x00a9;
    L_0x00a4:
        r5 = com.google.zxing.FormatException.getFormatInstance();
        throw r5;
    L_0x00a9:
        r2 = r5.available();
        if (r2 > 0) goto L_0x0002;
    L_0x00af:
        r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE;
        return r5;
    L_0x00b2:
        r5 = com.google.zxing.FormatException.getFormatInstance();
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeAsciiSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder, java.lang.StringBuilder):com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode");
    }

    private static void decodeBase256Segment(com.google.zxing.common.BitSource r7, java.lang.StringBuilder r8, java.util.Collection<byte[]> r9) throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:24:0x007d in {2, 5, 6, 12, 14, 18, 21, 23} preds:[]
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
        r0 = r7.getByteOffset();
        r0 = r0 + 1;
        r1 = 8;
        r2 = r7.readBits(r1);
        r3 = r0 + 1;
        r0 = unrandomize255State(r2, r0);
        if (r0 != 0) goto L_0x001a;
    L_0x0014:
        r0 = r7.available();
        r0 = r0 / r1;
        goto L_0x002f;
    L_0x001a:
        r2 = 250; // 0xfa float:3.5E-43 double:1.235E-321;
        if (r0 >= r2) goto L_0x001f;
    L_0x001e:
        goto L_0x002f;
    L_0x001f:
        r0 = r0 + -249;
        r0 = r0 * 250;
        r2 = r7.readBits(r1);
        r4 = r3 + 1;
        r2 = unrandomize255State(r2, r3);
        r0 = r0 + r2;
        r3 = r4;
    L_0x002f:
        if (r0 < 0) goto L_0x0078;
    L_0x0031:
        r2 = new byte[r0];
        r4 = 0;
    L_0x0034:
        if (r4 >= r0) goto L_0x0052;
    L_0x0036:
        r5 = r7.available();
        if (r5 < r1) goto L_0x004d;
    L_0x003c:
        r5 = r7.readBits(r1);
        r6 = r3 + 1;
        r3 = unrandomize255State(r5, r3);
        r3 = (byte) r3;
        r2[r4] = r3;
        r4 = r4 + 1;
        r3 = r6;
        goto L_0x0034;
    L_0x004d:
        r7 = com.google.zxing.FormatException.getFormatInstance();
        throw r7;
    L_0x0052:
        r9.add(r2);
        r7 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0060 }
        r9 = "ISO8859_1";	 Catch:{ UnsupportedEncodingException -> 0x0060 }
        r7.<init>(r2, r9);	 Catch:{ UnsupportedEncodingException -> 0x0060 }
        r8.append(r7);	 Catch:{ UnsupportedEncodingException -> 0x0060 }
        return;
    L_0x0060:
        r7 = move-exception;
        r8 = new java.lang.IllegalStateException;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r0 = "Platform does not support required encoding: ";
        r9.append(r0);
        r9.append(r7);
        r7 = r9.toString();
        r8.<init>(r7);
        throw r8;
    L_0x0078:
        r7 = com.google.zxing.FormatException.getFormatInstance();
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeBase256Segment(com.google.zxing.common.BitSource, java.lang.StringBuilder, java.util.Collection):void");
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(byte[] bArr) throws FormatException {
        String stringBuilder;
        BitSource bitSource = new BitSource(bArr);
        StringBuilder stringBuilder2 = new StringBuilder(100);
        CharSequence stringBuilder3 = new StringBuilder(0);
        List arrayList = new ArrayList(1);
        Mode mode = Mode.ASCII_ENCODE;
        do {
            if (mode == Mode.ASCII_ENCODE) {
                mode = decodeAsciiSegment(bitSource, stringBuilder2, stringBuilder3);
            } else {
                switch (mode) {
                    case C40_ENCODE:
                        decodeC40Segment(bitSource, stringBuilder2);
                        break;
                    case TEXT_ENCODE:
                        decodeTextSegment(bitSource, stringBuilder2);
                        break;
                    case ANSIX12_ENCODE:
                        decodeAnsiX12Segment(bitSource, stringBuilder2);
                        break;
                    case EDIFACT_ENCODE:
                        decodeEdifactSegment(bitSource, stringBuilder2);
                        break;
                    case BASE256_ENCODE:
                        decodeBase256Segment(bitSource, stringBuilder2, arrayList);
                        break;
                    default:
                        throw FormatException.getFormatInstance();
                }
                mode = Mode.ASCII_ENCODE;
            }
            if (mode != Mode.PAD_ENCODE) {
            }
            if (stringBuilder3.length() > 0) {
                stringBuilder2.append(stringBuilder3);
            }
            stringBuilder = stringBuilder2.toString();
            if (arrayList.isEmpty()) {
                arrayList = null;
            }
            return new DecoderResult(bArr, stringBuilder, arrayList, null);
        } while (bitSource.available() > 0);
        if (stringBuilder3.length() > 0) {
            stringBuilder2.append(stringBuilder3);
        }
        stringBuilder = stringBuilder2.toString();
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        return new DecoderResult(bArr, stringBuilder, arrayList, null);
    }

    private static void decodeC40Segment(BitSource bitSource, StringBuilder stringBuilder) throws FormatException {
        int[] iArr = new int[3];
        Object obj = null;
        int i = 0;
        while (bitSource.available() != 8) {
            int readBits = bitSource.readBits(8);
            if (readBits != 254) {
                parseTwoBytes(readBits, bitSource.readBits(8), iArr);
                Object obj2 = obj;
                for (int i2 = 0; i2 < 3; i2++) {
                    int i3 = iArr[i2];
                    switch (i) {
                        case 0:
                            if (i3 < 3) {
                                i = i3 + 1;
                                break;
                            }
                            char[] cArr = C40_BASIC_SET_CHARS;
                            if (i3 < cArr.length) {
                                char c = cArr[i3];
                                if (obj2 == null) {
                                    stringBuilder.append(c);
                                    break;
                                }
                                stringBuilder.append((char) (c + 128));
                                obj2 = null;
                                break;
                            }
                            throw FormatException.getFormatInstance();
                        case 1:
                            if (obj2 != null) {
                                stringBuilder.append((char) (i3 + 128));
                                obj2 = null;
                            } else {
                                stringBuilder.append((char) i3);
                            }
                            i = 0;
                            break;
                        case 2:
                            char[] cArr2 = C40_SHIFT2_SET_CHARS;
                            if (i3 < cArr2.length) {
                                char c2 = cArr2[i3];
                                if (obj2 != null) {
                                    stringBuilder.append((char) (c2 + 128));
                                    obj2 = null;
                                } else {
                                    stringBuilder.append(c2);
                                }
                            } else if (i3 == 27) {
                                stringBuilder.append('\u001d');
                            } else if (i3 == 30) {
                                obj2 = 1;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            i = 0;
                            break;
                        case 3:
                            if (obj2 != null) {
                                stringBuilder.append((char) (i3 + 224));
                                obj2 = null;
                            } else {
                                stringBuilder.append((char) (i3 + 96));
                            }
                            i = 0;
                            break;
                        default:
                            throw FormatException.getFormatInstance();
                    }
                }
                if (bitSource.available() > 0) {
                    obj = obj2;
                } else {
                    return;
                }
            }
            return;
        }
    }

    private static void decodeTextSegment(BitSource bitSource, StringBuilder stringBuilder) throws FormatException {
        int[] iArr = new int[3];
        Object obj = null;
        int i = 0;
        while (bitSource.available() != 8) {
            int readBits = bitSource.readBits(8);
            if (readBits != 254) {
                parseTwoBytes(readBits, bitSource.readBits(8), iArr);
                Object obj2 = obj;
                for (int i2 = 0; i2 < 3; i2++) {
                    int i3 = iArr[i2];
                    char[] cArr;
                    char c;
                    switch (i) {
                        case 0:
                            if (i3 < 3) {
                                i = i3 + 1;
                                break;
                            }
                            char[] cArr2 = TEXT_BASIC_SET_CHARS;
                            if (i3 < cArr2.length) {
                                char c2 = cArr2[i3];
                                if (obj2 == null) {
                                    stringBuilder.append(c2);
                                    break;
                                }
                                stringBuilder.append((char) (c2 + 128));
                                obj2 = null;
                                break;
                            }
                            throw FormatException.getFormatInstance();
                        case 1:
                            if (obj2 != null) {
                                stringBuilder.append((char) (i3 + 128));
                                obj2 = null;
                            } else {
                                stringBuilder.append((char) i3);
                            }
                            i = 0;
                            break;
                        case 2:
                            cArr = TEXT_SHIFT2_SET_CHARS;
                            if (i3 < cArr.length) {
                                c = cArr[i3];
                                if (obj2 != null) {
                                    stringBuilder.append((char) (c + 128));
                                    obj2 = null;
                                } else {
                                    stringBuilder.append(c);
                                }
                            } else if (i3 == 27) {
                                stringBuilder.append('\u001d');
                            } else if (i3 == 30) {
                                obj2 = 1;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            i = 0;
                            break;
                        case 3:
                            cArr = TEXT_SHIFT3_SET_CHARS;
                            if (i3 < cArr.length) {
                                c = cArr[i3];
                                if (obj2 != null) {
                                    stringBuilder.append((char) (c + 128));
                                    obj2 = null;
                                } else {
                                    stringBuilder.append(c);
                                }
                                i = 0;
                                break;
                            }
                            throw FormatException.getFormatInstance();
                        default:
                            throw FormatException.getFormatInstance();
                    }
                }
                if (bitSource.available() > 0) {
                    obj = obj2;
                } else {
                    return;
                }
            }
            return;
        }
    }

    private static void decodeAnsiX12Segment(BitSource bitSource, StringBuilder stringBuilder) throws FormatException {
        int[] iArr = new int[3];
        while (bitSource.available() != 8) {
            int readBits = bitSource.readBits(8);
            if (readBits != 254) {
                parseTwoBytes(readBits, bitSource.readBits(8), iArr);
                for (readBits = 0; readBits < 3; readBits++) {
                    int i = iArr[readBits];
                    if (i == 0) {
                        stringBuilder.append('\r');
                    } else if (i == 1) {
                        stringBuilder.append('*');
                    } else if (i == 2) {
                        stringBuilder.append('>');
                    } else if (i == 3) {
                        stringBuilder.append(' ');
                    } else if (i < 14) {
                        stringBuilder.append((char) (i + 44));
                    } else if (i < 40) {
                        stringBuilder.append((char) (i + 51));
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                if (bitSource.available() <= 0) {
                    return;
                }
            }
            return;
        }
    }

    private static void parseTwoBytes(int i, int i2, int[] iArr) {
        i = ((i << 8) + i2) - 1;
        int i3 = i / 1600;
        iArr[0] = i3;
        i -= i3 * 1600;
        i3 = i / 40;
        iArr[1] = i3;
        iArr[2] = i - (i3 * 40);
    }

    private static void decodeEdifactSegment(BitSource bitSource, StringBuilder stringBuilder) {
        while (bitSource.available() > 16) {
            for (int i = 0; i < 4; i++) {
                int readBits = bitSource.readBits(6);
                if (readBits == 31) {
                    stringBuilder = 8 - bitSource.getBitOffset();
                    if (stringBuilder != 8) {
                        bitSource.readBits(stringBuilder);
                    }
                    return;
                }
                if ((readBits & 32) == 0) {
                    readBits |= 64;
                }
                stringBuilder.append((char) readBits);
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static int unrandomize255State(int i, int i2) {
        i -= ((i2 * 149) % 255) + 1;
        return i >= 0 ? i : i + 256;
    }
}
