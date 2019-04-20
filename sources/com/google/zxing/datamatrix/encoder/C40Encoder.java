package com.google.zxing.datamatrix.encoder;

class C40Encoder implements Encoder {
    public int getEncodingMode() {
        return 1;
    }

    void handleEOD(com.google.zxing.datamatrix.encoder.EncoderContext r7, java.lang.StringBuilder r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:31:0x007c in {5, 8, 14, 17, 18, 22, 25, 26, 28, 30} preds:[]
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
        r6 = this;
        r0 = r8.length();
        r1 = 3;
        r0 = r0 / r1;
        r2 = 2;
        r0 = r0 * 2;
        r3 = r8.length();
        r3 = r3 % r1;
        r4 = r7.getCodewordCount();
        r4 = r4 + r0;
        r7.updateSymbolInfo(r4);
        r0 = r7.getSymbolInfo();
        r0 = r0.getDataCapacity();
        r0 = r0 - r4;
        r4 = 0;
        r5 = 254; // 0xfe float:3.56E-43 double:1.255E-321;
        if (r3 != r2) goto L_0x003b;
    L_0x0024:
        r8.append(r4);
    L_0x0027:
        r0 = r8.length();
        if (r0 < r1) goto L_0x0031;
    L_0x002d:
        writeNextTriplet(r7, r8);
        goto L_0x0027;
    L_0x0031:
        r8 = r7.hasMoreCharacters();
        if (r8 == 0) goto L_0x0070;
    L_0x0037:
        r7.writeCodeword(r5);
        goto L_0x0070;
    L_0x003b:
        r2 = 1;
        if (r0 != r2) goto L_0x0059;
    L_0x003e:
        if (r3 != r2) goto L_0x0059;
    L_0x0040:
        r0 = r8.length();
        if (r0 < r1) goto L_0x004a;
    L_0x0046:
        writeNextTriplet(r7, r8);
        goto L_0x0040;
    L_0x004a:
        r8 = r7.hasMoreCharacters();
        if (r8 == 0) goto L_0x0053;
    L_0x0050:
        r7.writeCodeword(r5);
    L_0x0053:
        r8 = r7.pos;
        r8 = r8 - r2;
        r7.pos = r8;
        goto L_0x0070;
    L_0x0059:
        if (r3 != 0) goto L_0x0074;
    L_0x005b:
        r2 = r8.length();
        if (r2 < r1) goto L_0x0065;
    L_0x0061:
        writeNextTriplet(r7, r8);
        goto L_0x005b;
    L_0x0065:
        if (r0 > 0) goto L_0x006d;
    L_0x0067:
        r8 = r7.hasMoreCharacters();
        if (r8 == 0) goto L_0x0070;
    L_0x006d:
        r7.writeCodeword(r5);
    L_0x0070:
        r7.signalEncoderChange(r4);
        return;
    L_0x0074:
        r7 = new java.lang.IllegalStateException;
        r8 = "Unexpected case. Please report!";
        r7.<init>(r8);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.C40Encoder.handleEOD(com.google.zxing.datamatrix.encoder.EncoderContext, java.lang.StringBuilder):void");
    }

    C40Encoder() {
    }

    public void encode(EncoderContext encoderContext) {
        StringBuilder stringBuilder = new StringBuilder();
        while (encoderContext.hasMoreCharacters()) {
            char currentChar = encoderContext.getCurrentChar();
            encoderContext.pos++;
            int encodeChar = encodeChar(currentChar, stringBuilder);
            int codewordCount = encoderContext.getCodewordCount() + ((stringBuilder.length() / 3) * 2);
            encoderContext.updateSymbolInfo(codewordCount);
            int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - codewordCount;
            if (!encoderContext.hasMoreCharacters()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                if (stringBuilder.length() % 3 == 2 && (dataCapacity < 2 || dataCapacity > 2)) {
                    encodeChar = backtrackOneCharacter(encoderContext, stringBuilder, stringBuilder2, encodeChar);
                }
                while (stringBuilder.length() % 3 == 1 && ((encodeChar <= 3 && dataCapacity != 1) || encodeChar > 3)) {
                    encodeChar = backtrackOneCharacter(encoderContext, stringBuilder, stringBuilder2, encodeChar);
                }
            } else if (stringBuilder.length() % 3 == 0) {
                encodeChar = HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode());
                if (encodeChar != getEncodingMode()) {
                    encoderContext.signalEncoderChange(encodeChar);
                    break;
                }
            }
        }
        handleEOD(encoderContext, stringBuilder);
    }

    private int backtrackOneCharacter(EncoderContext encoderContext, StringBuilder stringBuilder, StringBuilder stringBuilder2, int i) {
        int length = stringBuilder.length();
        stringBuilder.delete(length - i, length);
        encoderContext.pos--;
        stringBuilder = encodeChar(encoderContext.getCurrentChar(), stringBuilder2);
        encoderContext.resetSymbolInfo();
        return stringBuilder;
    }

    static void writeNextTriplet(EncoderContext encoderContext, StringBuilder stringBuilder) {
        encoderContext.writeCodewords(encodeToCodewords(stringBuilder, 0));
        stringBuilder.delete(0, 3);
    }

    int encodeChar(char c, StringBuilder stringBuilder) {
        if (c == ' ') {
            stringBuilder.append('\u0003');
            return 1;
        } else if (c >= '0' && c <= '9') {
            stringBuilder.append((char) ((c - '0') + 4));
            return 1;
        } else if (c >= 'A' && c <= 'Z') {
            stringBuilder.append((char) ((c - 'A') + 14));
            return 1;
        } else if (c >= '\u0000' && c <= '\u001f') {
            stringBuilder.append('\u0000');
            stringBuilder.append(c);
            return 2;
        } else if (c >= '!' && c <= '/') {
            stringBuilder.append('\u0001');
            stringBuilder.append((char) (c - '!'));
            return 2;
        } else if (c >= ':' && c <= '@') {
            stringBuilder.append('\u0001');
            stringBuilder.append((char) ((c - ':') + 15));
            return 2;
        } else if (c >= '[' && c <= '_') {
            stringBuilder.append('\u0001');
            stringBuilder.append((char) ((c - '[') + 22));
            return 2;
        } else if (c >= '`' && c <= '') {
            stringBuilder.append('\u0002');
            stringBuilder.append((char) (c - '`'));
            return 2;
        } else if (c >= '') {
            stringBuilder.append("\u0001\u001e");
            return encodeChar((char) (c - ''), stringBuilder) + '\u0002';
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Illegal character: ");
            stringBuilder2.append(c);
            throw new IllegalArgumentException(stringBuilder2.toString());
        }
    }

    private static String encodeToCodewords(CharSequence charSequence, int i) {
        char charAt = (char) (((((charSequence.charAt(i) * 1600) + (charSequence.charAt(i + 1) * 40)) + charSequence.charAt(i + 2)) + 1) % 256);
        return new String(new char[]{(char) (r0 / 256), charAt});
    }
}
