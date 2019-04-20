package com.google.zxing.oned;

public final class CodaBarWriter extends OneDimensionalCodeWriter {
    private static final char[] ALT_START_END_CHARS = new char[]{'T', 'N', '*', 'E'};
    private static final char[] CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED = new char[]{'/', ':', '+', '.'};
    private static final char DEFAULT_GUARD = START_END_CHARS[0];
    private static final char[] START_END_CHARS = new char[]{'A', 'B', 'C', 'D'};

    public boolean[] encode(java.lang.String r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:79:0x0194 in {2, 6, 8, 11, 13, 16, 26, 29, 31, 32, 33, 40, 49, 50, 51, 52, 53, 59, 60, 61, 68, 69, 70, 73, 74, 75, 76, 78} preds:[]
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
        r10 = this;
        r0 = r11.length();
        r1 = 0;
        r2 = 1;
        r3 = 2;
        if (r0 >= r3) goto L_0x0021;
    L_0x0009:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r3 = DEFAULT_GUARD;
        r0.append(r3);
        r0.append(r11);
        r11 = DEFAULT_GUARD;
        r0.append(r11);
        r11 = r0.toString();
        goto L_0x00a0;
    L_0x0021:
        r0 = r11.charAt(r1);
        r0 = java.lang.Character.toUpperCase(r0);
        r3 = r11.length();
        r3 = r3 - r2;
        r3 = r11.charAt(r3);
        r3 = java.lang.Character.toUpperCase(r3);
        r4 = START_END_CHARS;
        r4 = com.google.zxing.oned.CodaBarReader.arrayContains(r4, r0);
        r5 = START_END_CHARS;
        r5 = com.google.zxing.oned.CodaBarReader.arrayContains(r5, r3);
        r6 = ALT_START_END_CHARS;
        r0 = com.google.zxing.oned.CodaBarReader.arrayContains(r6, r0);
        r6 = ALT_START_END_CHARS;
        r3 = com.google.zxing.oned.CodaBarReader.arrayContains(r6, r3);
        if (r4 == 0) goto L_0x006a;
    L_0x0050:
        if (r5 == 0) goto L_0x0053;
    L_0x0052:
        goto L_0x00a0;
    L_0x0053:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Invalid start/end guards: ";
        r1.append(r2);
        r1.append(r11);
        r11 = r1.toString();
        r0.<init>(r11);
        throw r0;
    L_0x006a:
        if (r0 == 0) goto L_0x0086;
    L_0x006c:
        if (r3 == 0) goto L_0x006f;
    L_0x006e:
        goto L_0x00a0;
    L_0x006f:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Invalid start/end guards: ";
        r1.append(r2);
        r1.append(r11);
        r11 = r1.toString();
        r0.<init>(r11);
        throw r0;
    L_0x0086:
        if (r5 != 0) goto L_0x017d;
    L_0x0088:
        if (r3 != 0) goto L_0x017d;
    L_0x008a:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r3 = DEFAULT_GUARD;
        r0.append(r3);
        r0.append(r11);
        r11 = DEFAULT_GUARD;
        r0.append(r11);
        r11 = r0.toString();
    L_0x00a0:
        r0 = 20;
        r0 = 1;
        r3 = 20;
    L_0x00a5:
        r4 = r11.length();
        r4 = r4 - r2;
        if (r0 >= r4) goto L_0x00fb;
    L_0x00ac:
        r4 = r11.charAt(r0);
        r4 = java.lang.Character.isDigit(r4);
        if (r4 != 0) goto L_0x00f6;
    L_0x00b6:
        r4 = r11.charAt(r0);
        r5 = 45;
        if (r4 == r5) goto L_0x00f6;
    L_0x00be:
        r4 = r11.charAt(r0);
        r5 = 36;
        if (r4 != r5) goto L_0x00c7;
    L_0x00c6:
        goto L_0x00f6;
    L_0x00c7:
        r4 = CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED;
        r5 = r11.charAt(r0);
        r4 = com.google.zxing.oned.CodaBarReader.arrayContains(r4, r5);
        if (r4 == 0) goto L_0x00d6;
    L_0x00d3:
        r3 = r3 + 10;
        goto L_0x00f8;
    L_0x00d6:
        r1 = new java.lang.IllegalArgumentException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Cannot encode : '";
        r2.append(r3);
        r11 = r11.charAt(r0);
        r2.append(r11);
        r11 = 39;
        r2.append(r11);
        r11 = r2.toString();
        r1.<init>(r11);
        throw r1;
    L_0x00f6:
        r3 = r3 + 9;
    L_0x00f8:
        r0 = r0 + 1;
        goto L_0x00a5;
    L_0x00fb:
        r0 = r11.length();
        r0 = r0 - r2;
        r3 = r3 + r0;
        r0 = new boolean[r3];
        r3 = 0;
        r4 = 0;
    L_0x0105:
        r5 = r11.length();
        if (r3 >= r5) goto L_0x017c;
    L_0x010b:
        r5 = r11.charAt(r3);
        r5 = java.lang.Character.toUpperCase(r5);
        if (r3 == 0) goto L_0x011c;
    L_0x0115:
        r6 = r11.length();
        r6 = r6 - r2;
        if (r3 != r6) goto L_0x0138;
    L_0x011c:
        r6 = 42;
        if (r5 == r6) goto L_0x0136;
    L_0x0120:
        r6 = 69;
        if (r5 == r6) goto L_0x0133;
    L_0x0124:
        r6 = 78;
        if (r5 == r6) goto L_0x0130;
    L_0x0128:
        r6 = 84;
        if (r5 == r6) goto L_0x012d;
    L_0x012c:
        goto L_0x0138;
    L_0x012d:
        r5 = 65;
        goto L_0x0138;
    L_0x0130:
        r5 = 66;
        goto L_0x0138;
    L_0x0133:
        r5 = 68;
        goto L_0x0138;
    L_0x0136:
        r5 = 67;
    L_0x0138:
        r6 = 0;
    L_0x0139:
        r7 = com.google.zxing.oned.CodaBarReader.ALPHABET;
        r7 = r7.length;
        if (r6 >= r7) goto L_0x014c;
    L_0x013e:
        r7 = com.google.zxing.oned.CodaBarReader.ALPHABET;
        r7 = r7[r6];
        if (r5 != r7) goto L_0x0149;
    L_0x0144:
        r5 = com.google.zxing.oned.CodaBarReader.CHARACTER_ENCODINGS;
        r5 = r5[r6];
        goto L_0x014d;
    L_0x0149:
        r6 = r6 + 1;
        goto L_0x0139;
    L_0x014c:
        r5 = 0;
    L_0x014d:
        r6 = r4;
        r4 = 0;
        r7 = 1;
        r8 = 0;
    L_0x0151:
        r9 = 7;
        if (r4 >= r9) goto L_0x016b;
    L_0x0154:
        r0[r6] = r7;
        r6 = r6 + 1;
        r9 = 6 - r4;
        r9 = r5 >> r9;
        r9 = r9 & r2;
        if (r9 == 0) goto L_0x0165;
    L_0x015f:
        if (r8 != r2) goto L_0x0162;
    L_0x0161:
        goto L_0x0165;
    L_0x0162:
        r8 = r8 + 1;
        goto L_0x0151;
    L_0x0165:
        r7 = r7 ^ 1;
        r4 = r4 + 1;
        r8 = 0;
        goto L_0x0151;
    L_0x016b:
        r4 = r11.length();
        r4 = r4 - r2;
        if (r3 >= r4) goto L_0x0178;
    L_0x0172:
        r0[r6] = r1;
        r6 = r6 + 1;
        r4 = r6;
        goto L_0x0179;
    L_0x0178:
        r4 = r6;
    L_0x0179:
        r3 = r3 + 1;
        goto L_0x0105;
    L_0x017c:
        return r0;
    L_0x017d:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Invalid start/end guards: ";
        r1.append(r2);
        r1.append(r11);
        r11 = r1.toString();
        r0.<init>(r11);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.CodaBarWriter.encode(java.lang.String):boolean[]");
    }
}
