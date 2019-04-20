package com.google.zxing.datamatrix.decoder;

final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    static com.google.zxing.datamatrix.decoder.DataBlock[] getDataBlocks(byte[] r13, com.google.zxing.datamatrix.decoder.Version r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:45:0x00c3 in {2, 8, 9, 14, 15, 18, 19, 21, 22, 25, 31, 32, 36, 37, 38, 39, 42, 44} preds:[]
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
        r0 = r14.getECBlocks();
        r1 = r0.getECBlocks();
        r2 = r1.length;
        r3 = 0;
        r4 = 0;
        r5 = 0;
    L_0x000c:
        if (r4 >= r2) goto L_0x0018;
    L_0x000e:
        r6 = r1[r4];
        r6 = r6.getCount();
        r5 = r5 + r6;
        r4 = r4 + 1;
        goto L_0x000c;
    L_0x0018:
        r2 = new com.google.zxing.datamatrix.decoder.DataBlock[r5];
        r4 = r1.length;
        r5 = 0;
        r6 = 0;
    L_0x001d:
        if (r5 >= r4) goto L_0x0045;
    L_0x001f:
        r7 = r1[r5];
        r8 = r6;
        r6 = 0;
    L_0x0023:
        r9 = r7.getCount();
        if (r6 >= r9) goto L_0x0041;
    L_0x0029:
        r9 = r7.getDataCodewords();
        r10 = r0.getECCodewords();
        r10 = r10 + r9;
        r11 = r8 + 1;
        r12 = new com.google.zxing.datamatrix.decoder.DataBlock;
        r10 = new byte[r10];
        r12.<init>(r9, r10);
        r2[r8] = r12;
        r6 = r6 + 1;
        r8 = r11;
        goto L_0x0023;
    L_0x0041:
        r5 = r5 + 1;
        r6 = r8;
        goto L_0x001d;
    L_0x0045:
        r1 = r2[r3];
        r1 = r1.codewords;
        r1 = r1.length;
        r0 = r0.getECCodewords();
        r1 = r1 - r0;
        r0 = r1 + -1;
        r4 = 0;
        r5 = 0;
    L_0x0053:
        if (r4 >= r0) goto L_0x006b;
    L_0x0055:
        r7 = r5;
        r5 = 0;
    L_0x0057:
        if (r5 >= r6) goto L_0x0067;
    L_0x0059:
        r8 = r2[r5];
        r8 = r8.codewords;
        r9 = r7 + 1;
        r7 = r13[r7];
        r8[r4] = r7;
        r5 = r5 + 1;
        r7 = r9;
        goto L_0x0057;
    L_0x0067:
        r4 = r4 + 1;
        r5 = r7;
        goto L_0x0053;
    L_0x006b:
        r14 = r14.getVersionNumber();
        r4 = 24;
        if (r14 != r4) goto L_0x0075;
    L_0x0073:
        r14 = 1;
        goto L_0x0076;
    L_0x0075:
        r14 = 0;
    L_0x0076:
        if (r14 == 0) goto L_0x007b;
    L_0x0078:
        r4 = 8;
        goto L_0x007c;
    L_0x007b:
        r4 = r6;
    L_0x007c:
        r7 = r5;
        r5 = 0;
    L_0x007e:
        if (r5 >= r4) goto L_0x008e;
    L_0x0080:
        r8 = r2[r5];
        r8 = r8.codewords;
        r9 = r7 + 1;
        r7 = r13[r7];
        r8[r0] = r7;
        r5 = r5 + 1;
        r7 = r9;
        goto L_0x007e;
    L_0x008e:
        r0 = r2[r3];
        r0 = r0.codewords;
        r0 = r0.length;
    L_0x0093:
        if (r1 >= r0) goto L_0x00b9;
    L_0x0095:
        r4 = 0;
    L_0x0096:
        if (r4 >= r6) goto L_0x00b6;
    L_0x0098:
        if (r14 == 0) goto L_0x009e;
    L_0x009a:
        r5 = r4 + 8;
        r5 = r5 % r6;
        goto L_0x009f;
    L_0x009e:
        r5 = r4;
    L_0x009f:
        if (r14 == 0) goto L_0x00a7;
    L_0x00a1:
        r8 = 7;
        if (r5 <= r8) goto L_0x00a7;
    L_0x00a4:
        r8 = r1 + -1;
        goto L_0x00a8;
    L_0x00a7:
        r8 = r1;
    L_0x00a8:
        r5 = r2[r5];
        r5 = r5.codewords;
        r9 = r7 + 1;
        r7 = r13[r7];
        r5[r8] = r7;
        r4 = r4 + 1;
        r7 = r9;
        goto L_0x0096;
    L_0x00b6:
        r1 = r1 + 1;
        goto L_0x0093;
    L_0x00b9:
        r13 = r13.length;
        if (r7 != r13) goto L_0x00bd;
    L_0x00bc:
        return r2;
    L_0x00bd:
        r13 = new java.lang.IllegalArgumentException;
        r13.<init>();
        throw r13;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DataBlock.getDataBlocks(byte[], com.google.zxing.datamatrix.decoder.Version):com.google.zxing.datamatrix.decoder.DataBlock[]");
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
