package com.google.zxing.oned;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = new int[][]{new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int decodeCode(com.google.zxing.common.BitArray r3, int[] r4, int r5) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:11:0x0026 in {5, 6, 8, 10} preds:[]
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
        com.google.zxing.oned.OneDReader.recordPattern(r3, r5, r4);
        r3 = 1048576000; // 0x3e800000 float:0.25 double:5.180653787E-315;
        r5 = -1;
        r0 = 0;
    L_0x0007:
        r1 = CODE_PATTERNS;
        r2 = r1.length;
        if (r0 >= r2) goto L_0x001e;
    L_0x000c:
        r1 = r1[r0];
        r2 = 1060320051; // 0x3f333333 float:0.7 double:5.23867711E-315;
        r1 = com.google.zxing.oned.OneDReader.patternMatchVariance(r4, r1, r2);
        r2 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r2 >= 0) goto L_0x001b;
    L_0x0019:
        r5 = r0;
        r3 = r1;
    L_0x001b:
        r0 = r0 + 1;
        goto L_0x0007;
    L_0x001e:
        if (r5 < 0) goto L_0x0021;
    L_0x0020:
        return r5;
    L_0x0021:
        r3 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeCode(com.google.zxing.common.BitArray, int[], int):int");
    }

    private static int[] findStartPattern(com.google.zxing.common.BitArray r15) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:26:0x007d in {4, 12, 13, 19, 20, 21, 22, 23, 25} preds:[]
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
        r0 = r15.getSize();
        r1 = 0;
        r2 = r15.getNextSet(r1);
        r3 = 6;
        r3 = new int[r3];
        r4 = r3.length;
        r7 = r2;
        r5 = 0;
        r6 = 0;
    L_0x0010:
        if (r2 >= r0) goto L_0x0078;
    L_0x0012:
        r8 = r15.get(r2);
        r8 = r8 ^ r5;
        r9 = 1;
        if (r8 == 0) goto L_0x0020;
    L_0x001a:
        r8 = r3[r6];
        r8 = r8 + r9;
        r3[r6] = r8;
        goto L_0x0075;
    L_0x0020:
        r8 = r4 + -1;
        if (r6 != r8) goto L_0x006f;
    L_0x0024:
        r10 = 1048576000; // 0x3e800000 float:0.25 double:5.180653787E-315;
        r11 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r12 = -1;
    L_0x0029:
        r13 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r11 > r13) goto L_0x0041;
    L_0x002d:
        r13 = CODE_PATTERNS;
        r13 = r13[r11];
        r14 = 1060320051; // 0x3f333333 float:0.7 double:5.23867711E-315;
        r13 = com.google.zxing.oned.OneDReader.patternMatchVariance(r3, r13, r14);
        r14 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1));
        if (r14 >= 0) goto L_0x003e;
    L_0x003c:
        r12 = r11;
        r10 = r13;
    L_0x003e:
        r11 = r11 + 1;
        goto L_0x0029;
    L_0x0041:
        r10 = 2;
        if (r12 < 0) goto L_0x005d;
    L_0x0044:
        r11 = r2 - r7;
        r11 = r11 / r10;
        r11 = r7 - r11;
        r11 = java.lang.Math.max(r1, r11);
        r11 = r15.isRange(r11, r7, r1);
        if (r11 == 0) goto L_0x005d;
    L_0x0053:
        r15 = 3;
        r15 = new int[r15];
        r15[r1] = r7;
        r15[r9] = r2;
        r15[r10] = r12;
        return r15;
    L_0x005d:
        r11 = r3[r1];
        r12 = r3[r9];
        r11 = r11 + r12;
        r7 = r7 + r11;
        r11 = r4 + -2;
        java.lang.System.arraycopy(r3, r10, r3, r1, r11);
        r3[r11] = r1;
        r3[r8] = r1;
        r6 = r6 + -1;
        goto L_0x0071;
    L_0x006f:
        r6 = r6 + 1;
    L_0x0071:
        r3[r6] = r9;
        r5 = r5 ^ 1;
    L_0x0075:
        r2 = r2 + 1;
        goto L_0x0010;
    L_0x0078:
        r15 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r15;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.findStartPattern(com.google.zxing.common.BitArray):int[]");
    }

    public com.google.zxing.Result decodeRow(int r25, com.google.zxing.common.BitArray r26, java.util.Map<com.google.zxing.DecodeHintType, ?> r27) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:142:0x026a in {4, 5, 9, 10, 11, 12, 17, 19, 22, 26, 28, 32, 33, 34, 37, 38, 39, 41, 44, 48, 49, 52, 55, 56, 57, 58, 59, 60, 61, 62, 65, 66, 67, 69, 72, 76, 77, 78, 81, 84, 85, 86, 87, 88, 89, 90, 95, 96, 98, 101, 105, 106, 107, 108, 109, 110, 114, 115, 116, 117, 128, 129, 130, 133, 135, 137, 139, 141} preds:[]
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
        r24 = this;
        r0 = r26;
        r1 = r27;
        r2 = 1;
        r3 = 0;
        if (r1 == 0) goto L_0x0012;
    L_0x0008:
        r4 = com.google.zxing.DecodeHintType.ASSUME_GS1;
        r1 = r1.containsKey(r4);
        if (r1 == 0) goto L_0x0012;
    L_0x0010:
        r1 = 1;
        goto L_0x0013;
    L_0x0012:
        r1 = 0;
    L_0x0013:
        r4 = findStartPattern(r26);
        r5 = 2;
        r6 = r4[r5];
        r7 = new java.util.ArrayList;
        r8 = 20;
        r7.<init>(r8);
        r9 = (byte) r6;
        r9 = java.lang.Byte.valueOf(r9);
        r7.add(r9);
        switch(r6) {
            case 103: goto L_0x0037;
            case 104: goto L_0x0034;
            case 105: goto L_0x0031;
            default: goto L_0x002c;
        };
    L_0x002c:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
    L_0x0031:
        r12 = 99;
        goto L_0x0039;
    L_0x0034:
        r12 = 100;
        goto L_0x0039;
    L_0x0037:
        r12 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
    L_0x0039:
        r13 = new java.lang.StringBuilder;
        r13.<init>(r8);
        r8 = r4[r3];
        r14 = r4[r2];
        r15 = 6;
        r15 = new int[r15];
        r17 = r6;
        r9 = r12;
        r2 = 0;
        r5 = 0;
        r6 = 0;
        r16 = 0;
        r18 = 0;
        r19 = 1;
        r12 = r8;
        r8 = r14;
        r14 = 0;
    L_0x0054:
        if (r6 != 0) goto L_0x01d4;
    L_0x0056:
        r2 = decodeCode(r0, r15, r8);
        r12 = (byte) r2;
        r12 = java.lang.Byte.valueOf(r12);
        r7.add(r12);
        r12 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r2 == r12) goto L_0x0068;
    L_0x0066:
        r19 = 1;
    L_0x0068:
        if (r2 == r12) goto L_0x0070;
    L_0x006a:
        r18 = r18 + 1;
        r20 = r18 * r2;
        r17 = r17 + r20;
    L_0x0070:
        r11 = r15.length;
        r21 = r8;
        r10 = 0;
    L_0x0074:
        if (r10 >= r11) goto L_0x007d;
    L_0x0076:
        r22 = r15[r10];
        r21 = r21 + r22;
        r10 = r10 + 1;
        goto L_0x0074;
    L_0x007d:
        switch(r2) {
            case 103: goto L_0x0089;
            case 104: goto L_0x0089;
            case 105: goto L_0x0089;
            default: goto L_0x0080;
        };
    L_0x0080:
        r10 = 96;
        switch(r9) {
            case 99: goto L_0x017a;
            case 100: goto L_0x0111;
            case 101: goto L_0x008e;
            default: goto L_0x0085;
        };
    L_0x0085:
        r10 = 100;
        goto L_0x01b7;
    L_0x0089:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
    L_0x008e:
        r11 = 64;
        if (r2 >= r11) goto L_0x00a9;
    L_0x0092:
        if (r3 != r5) goto L_0x009b;
    L_0x0094:
        r3 = r2 + 32;
        r3 = (char) r3;
        r13.append(r3);
        goto L_0x00a3;
    L_0x009b:
        r3 = r2 + 32;
        r3 = r3 + 128;
        r3 = (char) r3;
        r13.append(r3);
    L_0x00a3:
        r3 = 0;
        r10 = 100;
        r11 = 0;
        goto L_0x01b9;
    L_0x00a9:
        if (r2 >= r10) goto L_0x00c0;
    L_0x00ab:
        if (r3 != r5) goto L_0x00b4;
    L_0x00ad:
        r3 = r2 + -64;
        r3 = (char) r3;
        r13.append(r3);
        goto L_0x00ba;
    L_0x00b4:
        r3 = r2 + 64;
        r3 = (char) r3;
        r13.append(r3);
    L_0x00ba:
        r3 = 0;
        r10 = 100;
        r11 = 0;
        goto L_0x01b9;
    L_0x00c0:
        if (r2 == r12) goto L_0x00c4;
    L_0x00c2:
        r19 = 0;
    L_0x00c4:
        if (r2 == r12) goto L_0x0107;
    L_0x00c6:
        switch(r2) {
            case 96: goto L_0x0103;
            case 97: goto L_0x0103;
            case 98: goto L_0x00fe;
            case 99: goto L_0x00f9;
            case 100: goto L_0x00f4;
            case 101: goto L_0x00de;
            case 102: goto L_0x00ca;
            default: goto L_0x00c9;
        };
    L_0x00c9:
        goto L_0x0103;
    L_0x00ca:
        if (r1 == 0) goto L_0x0103;
    L_0x00cc:
        r10 = r13.length();
        if (r10 != 0) goto L_0x00d8;
    L_0x00d2:
        r10 = "]C1";
        r13.append(r10);
        goto L_0x0103;
    L_0x00d8:
        r10 = 29;
        r13.append(r10);
        goto L_0x0103;
    L_0x00de:
        if (r5 != 0) goto L_0x00e7;
    L_0x00e0:
        if (r3 == 0) goto L_0x00e7;
    L_0x00e2:
        r10 = r9;
        r3 = 0;
        r5 = 1;
        r9 = 0;
        goto L_0x010b;
    L_0x00e7:
        if (r5 == 0) goto L_0x00f0;
    L_0x00e9:
        if (r3 == 0) goto L_0x00f0;
    L_0x00eb:
        r10 = r9;
        r3 = 0;
        r5 = 0;
        r9 = 0;
        goto L_0x010b;
    L_0x00f0:
        r10 = r9;
        r3 = 0;
        r9 = 1;
        goto L_0x010b;
    L_0x00f4:
        r9 = r3;
        r3 = 0;
        r10 = 100;
        goto L_0x010b;
    L_0x00f9:
        r9 = r3;
        r3 = 0;
        r10 = 99;
        goto L_0x010b;
    L_0x00fe:
        r9 = r3;
        r3 = 1;
        r10 = 100;
        goto L_0x010b;
    L_0x0103:
        r10 = r9;
        r9 = r3;
        r3 = 0;
        goto L_0x010b;
    L_0x0107:
        r10 = r9;
        r6 = 1;
        r9 = r3;
        r3 = 0;
    L_0x010b:
        r11 = r9;
        r9 = r10;
        r10 = 100;
        goto L_0x01b9;
    L_0x0111:
        if (r2 >= r10) goto L_0x012a;
    L_0x0113:
        if (r3 != r5) goto L_0x011c;
    L_0x0115:
        r3 = r2 + 32;
        r3 = (char) r3;
        r13.append(r3);
        goto L_0x0124;
    L_0x011c:
        r3 = r2 + 32;
        r3 = r3 + 128;
        r3 = (char) r3;
        r13.append(r3);
    L_0x0124:
        r3 = 0;
        r10 = 100;
        r11 = 0;
        goto L_0x01b9;
    L_0x012a:
        if (r2 == r12) goto L_0x012e;
    L_0x012c:
        r19 = 0;
    L_0x012e:
        if (r2 == r12) goto L_0x0171;
    L_0x0130:
        switch(r2) {
            case 96: goto L_0x016d;
            case 97: goto L_0x016d;
            case 98: goto L_0x0168;
            case 99: goto L_0x0163;
            case 100: goto L_0x014d;
            case 101: goto L_0x0148;
            case 102: goto L_0x0134;
            default: goto L_0x0133;
        };
    L_0x0133:
        goto L_0x016d;
    L_0x0134:
        if (r1 == 0) goto L_0x016d;
    L_0x0136:
        r10 = r13.length();
        if (r10 != 0) goto L_0x0142;
    L_0x013c:
        r10 = "]C1";
        r13.append(r10);
        goto L_0x016d;
    L_0x0142:
        r10 = 29;
        r13.append(r10);
        goto L_0x016d;
    L_0x0148:
        r9 = r3;
        r3 = 0;
        r10 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0175;
    L_0x014d:
        if (r5 != 0) goto L_0x0156;
    L_0x014f:
        if (r3 == 0) goto L_0x0156;
    L_0x0151:
        r10 = r9;
        r3 = 0;
        r5 = 1;
        r9 = 0;
        goto L_0x0175;
    L_0x0156:
        if (r5 == 0) goto L_0x015f;
    L_0x0158:
        if (r3 == 0) goto L_0x015f;
    L_0x015a:
        r10 = r9;
        r3 = 0;
        r5 = 0;
        r9 = 0;
        goto L_0x0175;
    L_0x015f:
        r10 = r9;
        r3 = 0;
        r9 = 1;
        goto L_0x0175;
    L_0x0163:
        r9 = r3;
        r3 = 0;
        r10 = 99;
        goto L_0x0175;
    L_0x0168:
        r9 = r3;
        r3 = 1;
        r10 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0175;
    L_0x016d:
        r10 = r9;
        r9 = r3;
        r3 = 0;
        goto L_0x0175;
    L_0x0171:
        r10 = r9;
        r6 = 1;
        r9 = r3;
        r3 = 0;
    L_0x0175:
        r11 = r9;
        r9 = r10;
        r10 = 100;
        goto L_0x01b9;
    L_0x017a:
        r10 = 100;
        if (r2 >= r10) goto L_0x018b;
    L_0x017e:
        r11 = 10;
        if (r2 >= r11) goto L_0x0187;
    L_0x0182:
        r11 = 48;
        r13.append(r11);
    L_0x0187:
        r13.append(r2);
        goto L_0x01b7;
    L_0x018b:
        if (r2 == r12) goto L_0x018f;
    L_0x018d:
        r19 = 0;
    L_0x018f:
        if (r2 == r12) goto L_0x01b3;
    L_0x0191:
        switch(r2) {
            case 100: goto L_0x01ae;
            case 101: goto L_0x01a9;
            case 102: goto L_0x0195;
            default: goto L_0x0194;
        };
    L_0x0194:
        goto L_0x01b7;
    L_0x0195:
        if (r1 == 0) goto L_0x01b7;
    L_0x0197:
        r11 = r13.length();
        if (r11 != 0) goto L_0x01a3;
    L_0x019d:
        r11 = "]C1";
        r13.append(r11);
        goto L_0x01b7;
    L_0x01a3:
        r11 = 29;
        r13.append(r11);
        goto L_0x01b7;
    L_0x01a9:
        r11 = r3;
        r3 = 0;
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x01b9;
    L_0x01ae:
        r11 = r3;
        r3 = 0;
        r9 = 100;
        goto L_0x01b9;
    L_0x01b3:
        r11 = r3;
        r3 = 0;
        r6 = 1;
        goto L_0x01b9;
    L_0x01b7:
        r11 = r3;
        r3 = 0;
    L_0x01b9:
        if (r14 == 0) goto L_0x01c5;
    L_0x01bb:
        r14 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r9 != r14) goto L_0x01c2;
    L_0x01bf:
        r9 = 100;
        goto L_0x01c7;
    L_0x01c2:
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x01c7;
    L_0x01c5:
        r14 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
    L_0x01c7:
        r14 = r3;
        r12 = r8;
        r3 = r11;
        r8 = r21;
        r23 = r16;
        r16 = r2;
        r2 = r23;
        goto L_0x0054;
    L_0x01d4:
        r1 = r8 - r12;
        r3 = r0.getNextUnset(r8);
        r5 = r26.getSize();
        r6 = r3 - r12;
        r8 = 2;
        r6 = r6 / r8;
        r6 = r6 + r3;
        r5 = java.lang.Math.min(r5, r6);
        r6 = 0;
        r0 = r0.isRange(r3, r5, r6);
        if (r0 == 0) goto L_0x0265;
    L_0x01ee:
        r18 = r18 * r2;
        r17 = r17 - r18;
        r0 = r17 % 103;
        if (r0 != r2) goto L_0x0260;
    L_0x01f6:
        r0 = r13.length();
        if (r0 == 0) goto L_0x025b;
    L_0x01fc:
        if (r0 <= 0) goto L_0x0212;
    L_0x01fe:
        if (r19 == 0) goto L_0x0212;
    L_0x0200:
        r2 = 99;
        if (r9 != r2) goto L_0x020b;
    L_0x0204:
        r2 = r0 + -2;
        r13.delete(r2, r0);
        r0 = 1;
        goto L_0x0213;
    L_0x020b:
        r2 = r0 + -1;
        r13.delete(r2, r0);
        r0 = 1;
        goto L_0x0213;
    L_0x0212:
        r0 = 1;
    L_0x0213:
        r2 = r4[r0];
        r0 = 0;
        r3 = r4[r0];
        r2 = r2 + r3;
        r0 = (float) r2;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r0 = r0 / r2;
        r3 = (float) r12;
        r1 = (float) r1;
        r1 = r1 / r2;
        r3 = r3 + r1;
        r1 = r7.size();
        r2 = new byte[r1];
        r4 = 0;
    L_0x0228:
        if (r4 >= r1) goto L_0x0239;
    L_0x022a:
        r5 = r7.get(r4);
        r5 = (java.lang.Byte) r5;
        r5 = r5.byteValue();
        r2[r4] = r5;
        r4 = r4 + 1;
        goto L_0x0228;
    L_0x0239:
        r1 = new com.google.zxing.Result;
        r4 = r13.toString();
        r5 = 2;
        r5 = new com.google.zxing.ResultPoint[r5];
        r6 = new com.google.zxing.ResultPoint;
        r7 = r25;
        r7 = (float) r7;
        r6.<init>(r0, r7);
        r0 = 0;
        r5[r0] = r6;
        r0 = new com.google.zxing.ResultPoint;
        r0.<init>(r3, r7);
        r3 = 1;
        r5[r3] = r0;
        r0 = com.google.zxing.BarcodeFormat.CODE_128;
        r1.<init>(r4, r2, r5, r0);
        return r1;
    L_0x025b:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0260:
        r0 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r0;
    L_0x0265:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }
}
