package com.google.zxing.aztec.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;

public final class Decoder {
    private static final String[] DIGIT_TABLE = new String[]{"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};
    private static final String[] LOWER_TABLE = new String[]{"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = new String[]{"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = new String[]{"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] UPPER_TABLE = new String[]{"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private AztecDetectorResult ddata;

    private enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    private boolean[] correctBits(boolean[] r13) throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:55:0x00b5 in {2, 5, 8, 9, 14, 24, 25, 26, 28, 35, 40, 41, 42, 45, 46, 47, 48, 49, 52, 54} preds:[]
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
        r12 = this;
        r0 = r12.ddata;
        r0 = r0.getNbLayers();
        r1 = 8;
        r2 = 2;
        if (r0 > r2) goto L_0x000f;
    L_0x000b:
        r1 = 6;
        r0 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_6;
        goto L_0x002d;
    L_0x000f:
        r0 = r12.ddata;
        r0 = r0.getNbLayers();
        if (r0 > r1) goto L_0x001a;
    L_0x0017:
        r0 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_8;
        goto L_0x002d;
    L_0x001a:
        r0 = r12.ddata;
        r0 = r0.getNbLayers();
        r1 = 22;
        if (r0 > r1) goto L_0x0029;
    L_0x0024:
        r1 = 10;
        r0 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_10;
        goto L_0x002d;
    L_0x0029:
        r1 = 12;
        r0 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_12;
    L_0x002d:
        r2 = r12.ddata;
        r2 = r2.getNbDatablocks();
        r3 = r13.length;
        r3 = r3 / r1;
        if (r3 < r2) goto L_0x00b0;
    L_0x0037:
        r4 = r13.length;
        r4 = r4 % r1;
        r5 = r3 - r2;
        r6 = new int[r3];
        r7 = 0;
        r8 = r4;
        r4 = 0;
    L_0x0040:
        if (r4 >= r3) goto L_0x004c;
    L_0x0042:
        r9 = readCode(r13, r8, r1);
        r6[r4] = r9;
        r4 = r4 + 1;
        r8 = r8 + r1;
        goto L_0x0040;
    L_0x004c:
        r13 = new com.google.zxing.common.reedsolomon.ReedSolomonDecoder;	 Catch:{ ReedSolomonException -> 0x00aa }
        r13.<init>(r0);	 Catch:{ ReedSolomonException -> 0x00aa }
        r13.decode(r6, r5);	 Catch:{ ReedSolomonException -> 0x00aa }
        r13 = 1;
        r0 = r13 << r1;
        r0 = r0 - r13;
        r3 = 0;
        r4 = 0;
    L_0x005a:
        if (r3 >= r2) goto L_0x0072;
    L_0x005c:
        r5 = r6[r3];
        if (r5 == 0) goto L_0x006d;
    L_0x0060:
        if (r5 == r0) goto L_0x006d;
    L_0x0062:
        if (r5 == r13) goto L_0x0068;
    L_0x0064:
        r8 = r0 + -1;
        if (r5 != r8) goto L_0x006a;
    L_0x0068:
        r4 = r4 + 1;
    L_0x006a:
        r3 = r3 + 1;
        goto L_0x005a;
    L_0x006d:
        r13 = com.google.zxing.FormatException.getFormatInstance();
        throw r13;
    L_0x0072:
        r3 = r2 * r1;
        r3 = r3 - r4;
        r3 = new boolean[r3];
        r4 = 0;
        r5 = 0;
    L_0x0079:
        if (r4 >= r2) goto L_0x00a9;
    L_0x007b:
        r8 = r6[r4];
        if (r8 == r13) goto L_0x0098;
    L_0x007f:
        r9 = r0 + -1;
        if (r8 != r9) goto L_0x0084;
    L_0x0083:
        goto L_0x0098;
    L_0x0084:
        r9 = r1 + -1;
    L_0x0086:
        if (r9 < 0) goto L_0x00a6;
    L_0x0088:
        r10 = r5 + 1;
        r11 = r13 << r9;
        r11 = r11 & r8;
        if (r11 == 0) goto L_0x0091;
    L_0x008f:
        r11 = 1;
        goto L_0x0092;
    L_0x0091:
        r11 = 0;
    L_0x0092:
        r3[r5] = r11;
        r9 = r9 + -1;
        r5 = r10;
        goto L_0x0086;
    L_0x0098:
        r9 = r5 + r1;
        r9 = r9 - r13;
        if (r8 <= r13) goto L_0x009f;
    L_0x009d:
        r8 = 1;
        goto L_0x00a0;
    L_0x009f:
        r8 = 0;
    L_0x00a0:
        java.util.Arrays.fill(r3, r5, r9, r8);
        r8 = r1 + -1;
        r5 = r5 + r8;
    L_0x00a6:
        r4 = r4 + 1;
        goto L_0x0079;
    L_0x00a9:
        return r3;
    L_0x00aa:
        r13 = move-exception;
        r13 = com.google.zxing.FormatException.getFormatInstance(r13);
        throw r13;
    L_0x00b0:
        r13 = com.google.zxing.FormatException.getFormatInstance();
        throw r13;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.decoder.Decoder.correctBits(boolean[]):boolean[]");
    }

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i * 16)) * i;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        return new DecoderResult(null, getEncodedData(correctBits(extractBits(aztecDetectorResult.getBits()))), null, null);
    }

    public static String highLevelDecode(boolean[] zArr) {
        return getEncodedData(zArr);
    }

    private static String getEncodedData(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        Table table2 = Table.UPPER;
        StringBuilder stringBuilder = new StringBuilder(20);
        Table table3 = table;
        int i = 0;
        while (i < length) {
            int i2;
            if (table2 != Table.BINARY) {
                i2 = table2 == Table.DIGIT ? 4 : 5;
                if (length - i < i2) {
                    break;
                }
                int readCode = readCode(zArr, i, i2);
                i += i2;
                String character = getCharacter(table2, readCode);
                if (character.startsWith("CTRL_")) {
                    Table table4 = getTable(character.charAt(5));
                    if (character.charAt(6) == 'L') {
                        table2 = table4;
                        table3 = table2;
                    } else {
                        table2 = table4;
                    }
                } else {
                    stringBuilder.append(character);
                    table2 = table3;
                }
            } else if (length - i < 5) {
                break;
            } else {
                int readCode2 = readCode(zArr, i, 5);
                i += 5;
                if (readCode2 == 0) {
                    if (length - i < 11) {
                        break;
                    }
                    readCode2 = readCode(zArr, i, 11) + 31;
                    i += 11;
                }
                i2 = i;
                for (i = 0; i < readCode2; i++) {
                    if (length - i2 < 8) {
                        i = length;
                        break;
                    }
                    stringBuilder.append((char) readCode(zArr, i2, 8));
                    i2 += 8;
                }
                i = i2;
                table2 = table3;
            }
        }
        return stringBuilder.toString();
    }

    private static Table getTable(char c) {
        if (c == 'B') {
            return Table.BINARY;
        }
        if (c == 'D') {
            return Table.DIGIT;
        }
        if (c == 'P') {
            return Table.PUNCT;
        }
        switch (c) {
            case 'L':
                return Table.LOWER;
            case 'M':
                return Table.MIXED;
            default:
                return Table.UPPER;
        }
    }

    private static String getCharacter(Table table, int i) {
        switch (table) {
            case UPPER:
                return UPPER_TABLE[i];
            case LOWER:
                return LOWER_TABLE[i];
            case MIXED:
                return MIXED_TABLE[i];
            case PUNCT:
                return PUNCT_TABLE[i];
            case DIGIT:
                return DIGIT_TABLE[i];
            default:
                throw new IllegalStateException("Bad table");
        }
    }

    boolean[] extractBits(BitMatrix bitMatrix) {
        int i;
        int i2;
        int i3;
        BitMatrix bitMatrix2 = bitMatrix;
        boolean isCompact = this.ddata.isCompact();
        int nbLayers = this.ddata.getNbLayers();
        int i4 = isCompact ? (nbLayers * 4) + 11 : (nbLayers * 4) + 14;
        int[] iArr = new int[i4];
        boolean[] zArr = new boolean[totalBitsInLayer(nbLayers, isCompact)];
        int i5 = 2;
        if (isCompact) {
            for (i = 0; i < iArr.length; i++) {
                iArr[i] = i;
            }
        } else {
            i2 = i4 / 2;
            i = ((i4 + 1) + (((i2 - 1) / 15) * 2)) / 2;
            for (i3 = 0; i3 < i2; i3++) {
                int i6 = (i3 / 15) + i3;
                iArr[(i2 - i3) - 1] = (i - i6) - 1;
                iArr[i2 + i3] = (i6 + i) + 1;
            }
        }
        i = 0;
        i2 = 0;
        while (i < nbLayers) {
            int i7;
            boolean z;
            Decoder decoder;
            i3 = isCompact ? ((nbLayers - i) * 4) + 9 : ((nbLayers - i) * 4) + 12;
            i6 = i * 2;
            int i8 = (i4 - 1) - i6;
            int i9 = 0;
            while (i9 < i3) {
                int i10 = i9 * 2;
                int i11 = 0;
                for (i5 = 
/*
Method generation error in method: com.google.zxing.aztec.decoder.Decoder.extractBits(com.google.zxing.common.BitMatrix):boolean[], dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r8_2 'i5' int) = (r8_1 'i5' int), (r8_8 'i5' int) binds: {(r8_1 'i5' int)=B:18:0x0068, (r8_8 'i5' int)=B:23:0x00c6} in method: com.google.zxing.aztec.decoder.Decoder.extractBits(com.google.zxing.common.BitMatrix):boolean[], dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:219)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:219)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:187)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 26 more

*/

                private static int readCode(boolean[] zArr, int i, int i2) {
                    int i3 = 0;
                    for (int i4 = i; i4 < i + i2; i4++) {
                        i3 <<= 1;
                        if (zArr[i4]) {
                            i3 |= 1;
                        }
                    }
                    return i3;
                }
            }
