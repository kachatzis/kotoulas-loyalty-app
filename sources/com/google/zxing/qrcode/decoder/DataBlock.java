package com.google.zxing.qrcode.decoder;

final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    static com.google.zxing.qrcode.decoder.DataBlock[] getDataBlocks(byte[] r11, com.google.zxing.qrcode.decoder.Version r12, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:39:0x00bd in {4, 10, 11, 16, 17, 22, 23, 26, 32, 33, 34, 35, 36, 38} preds:[]
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
        r0 = r11.length;
        r1 = r12.getTotalCodewords();
        if (r0 != r1) goto L_0x00b7;
    L_0x0007:
        r12 = r12.getECBlocksForLevel(r13);
        r13 = r12.getECBlocks();
        r0 = r13.length;
        r1 = 0;
        r2 = 0;
        r3 = 0;
    L_0x0013:
        if (r2 >= r0) goto L_0x001f;
    L_0x0015:
        r4 = r13[r2];
        r4 = r4.getCount();
        r3 = r3 + r4;
        r2 = r2 + 1;
        goto L_0x0013;
    L_0x001f:
        r0 = new com.google.zxing.qrcode.decoder.DataBlock[r3];
        r2 = r13.length;
        r3 = 0;
        r4 = 0;
    L_0x0024:
        if (r3 >= r2) goto L_0x004c;
    L_0x0026:
        r5 = r13[r3];
        r6 = r4;
        r4 = 0;
    L_0x002a:
        r7 = r5.getCount();
        if (r4 >= r7) goto L_0x0048;
    L_0x0030:
        r7 = r5.getDataCodewords();
        r8 = r12.getECCodewordsPerBlock();
        r8 = r8 + r7;
        r9 = r6 + 1;
        r10 = new com.google.zxing.qrcode.decoder.DataBlock;
        r8 = new byte[r8];
        r10.<init>(r7, r8);
        r0[r6] = r10;
        r4 = r4 + 1;
        r6 = r9;
        goto L_0x002a;
    L_0x0048:
        r3 = r3 + 1;
        r4 = r6;
        goto L_0x0024;
    L_0x004c:
        r13 = r0[r1];
        r13 = r13.codewords;
        r13 = r13.length;
        r2 = r0.length;
        r2 = r2 + -1;
    L_0x0054:
        if (r2 < 0) goto L_0x0061;
    L_0x0056:
        r3 = r0[r2];
        r3 = r3.codewords;
        r3 = r3.length;
        if (r3 != r13) goto L_0x005e;
    L_0x005d:
        goto L_0x0061;
    L_0x005e:
        r2 = r2 + -1;
        goto L_0x0054;
    L_0x0061:
        r2 = r2 + 1;
        r12 = r12.getECCodewordsPerBlock();
        r13 = r13 - r12;
        r12 = 0;
        r3 = 0;
    L_0x006a:
        if (r12 >= r13) goto L_0x0082;
    L_0x006c:
        r5 = r3;
        r3 = 0;
    L_0x006e:
        if (r3 >= r4) goto L_0x007e;
    L_0x0070:
        r6 = r0[r3];
        r6 = r6.codewords;
        r7 = r5 + 1;
        r5 = r11[r5];
        r6[r12] = r5;
        r3 = r3 + 1;
        r5 = r7;
        goto L_0x006e;
    L_0x007e:
        r12 = r12 + 1;
        r3 = r5;
        goto L_0x006a;
    L_0x0082:
        r12 = r2;
    L_0x0083:
        if (r12 >= r4) goto L_0x0093;
    L_0x0085:
        r5 = r0[r12];
        r5 = r5.codewords;
        r6 = r3 + 1;
        r3 = r11[r3];
        r5[r13] = r3;
        r12 = r12 + 1;
        r3 = r6;
        goto L_0x0083;
    L_0x0093:
        r12 = r0[r1];
        r12 = r12.codewords;
        r12 = r12.length;
    L_0x0098:
        if (r13 >= r12) goto L_0x00b6;
    L_0x009a:
        r5 = r3;
        r3 = 0;
    L_0x009c:
        if (r3 >= r4) goto L_0x00b2;
    L_0x009e:
        if (r3 >= r2) goto L_0x00a2;
    L_0x00a0:
        r6 = r13;
        goto L_0x00a4;
    L_0x00a2:
        r6 = r13 + 1;
    L_0x00a4:
        r7 = r0[r3];
        r7 = r7.codewords;
        r8 = r5 + 1;
        r5 = r11[r5];
        r7[r6] = r5;
        r3 = r3 + 1;
        r5 = r8;
        goto L_0x009c;
    L_0x00b2:
        r13 = r13 + 1;
        r3 = r5;
        goto L_0x0098;
    L_0x00b6:
        return r0;
    L_0x00b7:
        r11 = new java.lang.IllegalArgumentException;
        r11.<init>();
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DataBlock.getDataBlocks(byte[], com.google.zxing.qrcode.decoder.Version, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel):com.google.zxing.qrcode.decoder.DataBlock[]");
    }

    private DataBlock(int i, byte[] bArr) {
        this.numDataCodewords = i;
        this.codewords = bArr;
    }

    int getNumDataCodewords() {
        return this.numDataCodewords;
    }

    byte[] getCodewords() {
        return this.codewords;
    }
}
