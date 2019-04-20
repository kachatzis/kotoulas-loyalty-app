package com.google.zxing.common;

public final class BitSource {
    private int bitOffset;
    private int byteOffset;
    private final byte[] bytes;

    public int readBits(int r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:24:0x0071 in {10, 11, 14, 15, 18, 20, 21, 23} preds:[]
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
        r9 = this;
        r0 = 1;
        if (r10 < r0) goto L_0x0067;
    L_0x0003:
        r1 = 32;
        if (r10 > r1) goto L_0x0067;
    L_0x0007:
        r1 = r9.available();
        if (r10 > r1) goto L_0x0067;
    L_0x000d:
        r1 = r9.bitOffset;
        r2 = 0;
        r3 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r4 = 8;
        if (r1 <= 0) goto L_0x003c;
    L_0x0016:
        r1 = 8 - r1;
        if (r10 >= r1) goto L_0x001c;
    L_0x001a:
        r5 = r10;
        goto L_0x001d;
    L_0x001c:
        r5 = r1;
    L_0x001d:
        r1 = r1 - r5;
        r6 = 8 - r5;
        r6 = r3 >> r6;
        r6 = r6 << r1;
        r7 = r9.bytes;
        r8 = r9.byteOffset;
        r7 = r7[r8];
        r6 = r6 & r7;
        r1 = r6 >> r1;
        r10 = r10 - r5;
        r6 = r9.bitOffset;
        r6 = r6 + r5;
        r9.bitOffset = r6;
        r5 = r9.bitOffset;
        if (r5 != r4) goto L_0x003d;
    L_0x0036:
        r9.bitOffset = r2;
        r8 = r8 + r0;
        r9.byteOffset = r8;
        goto L_0x003d;
    L_0x003c:
        r1 = 0;
    L_0x003d:
        if (r10 <= 0) goto L_0x0066;
    L_0x003f:
        if (r10 < r4) goto L_0x0051;
    L_0x0041:
        r1 = r1 << 8;
        r2 = r9.bytes;
        r5 = r9.byteOffset;
        r2 = r2[r5];
        r2 = r2 & r3;
        r1 = r1 | r2;
        r5 = r5 + r0;
        r9.byteOffset = r5;
        r10 = r10 + -8;
        goto L_0x003f;
    L_0x0051:
        if (r10 <= 0) goto L_0x0066;
    L_0x0053:
        r4 = r4 - r10;
        r0 = r3 >> r4;
        r0 = r0 << r4;
        r1 = r1 << r10;
        r2 = r9.bytes;
        r3 = r9.byteOffset;
        r2 = r2[r3];
        r0 = r0 & r2;
        r0 = r0 >> r4;
        r1 = r1 | r0;
        r0 = r9.bitOffset;
        r0 = r0 + r10;
        r9.bitOffset = r0;
    L_0x0066:
        return r1;
    L_0x0067:
        r0 = new java.lang.IllegalArgumentException;
        r10 = java.lang.String.valueOf(r10);
        r0.<init>(r10);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.BitSource.readBits(int):int");
    }

    public BitSource(byte[] bArr) {
        this.bytes = bArr;
    }

    public int getBitOffset() {
        return this.bitOffset;
    }

    public int getByteOffset() {
        return this.byteOffset;
    }

    public int available() {
        return ((this.bytes.length - this.byteOffset) * 8) - this.bitOffset;
    }
}
