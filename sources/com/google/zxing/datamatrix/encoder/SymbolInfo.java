package com.google.zxing.datamatrix.encoder;

public class SymbolInfo {
    static final SymbolInfo[] PROD_SYMBOLS = new SymbolInfo[]{new SymbolInfo(false, 3, 5, 8, 8, 1), new SymbolInfo(false, 5, 7, 10, 10, 1), new SymbolInfo(true, 5, 7, 16, 6, 1), new SymbolInfo(false, 8, 10, 12, 12, 1), new SymbolInfo(true, 10, 11, 14, 6, 2), new SymbolInfo(false, 12, 12, 14, 14, 1), new SymbolInfo(true, 16, 14, 24, 10, 1), new SymbolInfo(false, 18, 14, 16, 16, 1), new SymbolInfo(false, 22, 18, 18, 18, 1), new SymbolInfo(true, 22, 18, 16, 10, 2), new SymbolInfo(false, 30, 20, 20, 20, 1), new SymbolInfo(true, 32, 24, 16, 14, 2), new SymbolInfo(false, 36, 24, 22, 22, 1), new SymbolInfo(false, 44, 28, 24, 24, 1), new SymbolInfo(true, 49, 28, 22, 14, 2), new SymbolInfo(false, 62, 36, 14, 14, 4), new SymbolInfo(false, 86, 42, 16, 16, 4), new SymbolInfo(false, 114, 48, 18, 18, 4), new SymbolInfo(false, 144, 56, 20, 20, 4), new SymbolInfo(false, 174, 68, 22, 22, 4), new SymbolInfo(false, 204, 84, 24, 24, 4, 102, 42), new SymbolInfo(false, 280, 112, 14, 14, 16, 140, 56), new SymbolInfo(false, 368, 144, 16, 16, 16, 92, 36), new SymbolInfo(false, 456, 192, 18, 18, 16, 114, 48), new SymbolInfo(false, 576, 224, 20, 20, 16, 144, 56), new SymbolInfo(false, 696, 272, 22, 22, 16, 174, 68), new SymbolInfo(false, 816, 336, 24, 24, 16, 136, 56), new SymbolInfo(false, 1050, 408, 18, 18, 36, 175, 68), new SymbolInfo(false, 1304, 496, 20, 20, 36, 163, 62), new DataMatrixSymbolInfo144()};
    private static SymbolInfo[] symbols = PROD_SYMBOLS;
    private final int dataCapacity;
    private final int dataRegions;
    private final int errorCodewords;
    public final int matrixHeight;
    public final int matrixWidth;
    private final boolean rectangular;
    private final int rsBlockData;
    private final int rsBlockError;

    public static com.google.zxing.datamatrix.encoder.SymbolInfo lookup(int r6, com.google.zxing.datamatrix.encoder.SymbolShapeHint r7, com.google.zxing.Dimension r8, com.google.zxing.Dimension r9, boolean r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:33:0x006b in {6, 11, 17, 23, 26, 27, 30, 32} preds:[]
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
        r0 = symbols;
        r1 = r0.length;
        r2 = 0;
    L_0x0004:
        if (r2 >= r1) goto L_0x0050;
    L_0x0006:
        r3 = r0[r2];
        r4 = com.google.zxing.datamatrix.encoder.SymbolShapeHint.FORCE_SQUARE;
        if (r7 != r4) goto L_0x0011;
    L_0x000c:
        r4 = r3.rectangular;
        if (r4 == 0) goto L_0x0011;
    L_0x0010:
        goto L_0x004d;
    L_0x0011:
        r4 = com.google.zxing.datamatrix.encoder.SymbolShapeHint.FORCE_RECTANGLE;
        if (r7 != r4) goto L_0x001a;
    L_0x0015:
        r4 = r3.rectangular;
        if (r4 != 0) goto L_0x001a;
    L_0x0019:
        goto L_0x004d;
    L_0x001a:
        if (r8 == 0) goto L_0x0031;
    L_0x001c:
        r4 = r3.getSymbolWidth();
        r5 = r8.getWidth();
        if (r4 < r5) goto L_0x004d;
    L_0x0026:
        r4 = r3.getSymbolHeight();
        r5 = r8.getHeight();
        if (r4 >= r5) goto L_0x0031;
    L_0x0030:
        goto L_0x004d;
    L_0x0031:
        if (r9 == 0) goto L_0x0048;
    L_0x0033:
        r4 = r3.getSymbolWidth();
        r5 = r9.getWidth();
        if (r4 > r5) goto L_0x004d;
    L_0x003d:
        r4 = r3.getSymbolHeight();
        r5 = r9.getHeight();
        if (r4 <= r5) goto L_0x0048;
    L_0x0047:
        goto L_0x004d;
    L_0x0048:
        r4 = r3.dataCapacity;
        if (r6 > r4) goto L_0x004d;
    L_0x004c:
        return r3;
    L_0x004d:
        r2 = r2 + 1;
        goto L_0x0004;
    L_0x0050:
        if (r10 != 0) goto L_0x0054;
    L_0x0052:
        r6 = 0;
        return r6;
    L_0x0054:
        r7 = new java.lang.IllegalArgumentException;
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "Can't find a symbol arrangement that matches the message. Data codewords: ";
        r8.append(r9);
        r8.append(r6);
        r6 = r8.toString();
        r7.<init>(r6);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.SymbolInfo.lookup(int, com.google.zxing.datamatrix.encoder.SymbolShapeHint, com.google.zxing.Dimension, com.google.zxing.Dimension, boolean):com.google.zxing.datamatrix.encoder.SymbolInfo");
    }

    public static void overrideSymbolSet(SymbolInfo[] symbolInfoArr) {
        symbols = symbolInfoArr;
    }

    public SymbolInfo(boolean z, int i, int i2, int i3, int i4, int i5) {
        this(z, i, i2, i3, i4, i5, i, i2);
    }

    SymbolInfo(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.rectangular = z;
        this.dataCapacity = i;
        this.errorCodewords = i2;
        this.matrixWidth = i3;
        this.matrixHeight = i4;
        this.dataRegions = i5;
        this.rsBlockData = i6;
        this.rsBlockError = i7;
    }

    public static SymbolInfo lookup(int i) {
        return lookup(i, SymbolShapeHint.FORCE_NONE, true);
    }

    public static SymbolInfo lookup(int i, SymbolShapeHint symbolShapeHint) {
        return lookup(i, symbolShapeHint, true);
    }

    public static SymbolInfo lookup(int i, boolean z, boolean z2) {
        return lookup(i, z ? SymbolShapeHint.FORCE_NONE : SymbolShapeHint.FORCE_SQUARE, z2);
    }

    private static SymbolInfo lookup(int i, SymbolShapeHint symbolShapeHint, boolean z) {
        return lookup(i, symbolShapeHint, null, null, z);
    }

    final int getHorizontalDataRegions() {
        int i = this.dataRegions;
        if (i == 4) {
            return 2;
        }
        if (i == 16) {
            return 4;
        }
        if (i == 36) {
            return 6;
        }
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    final int getVerticalDataRegions() {
        int i = this.dataRegions;
        if (i == 4) {
            return 2;
        }
        if (i == 16) {
            return 4;
        }
        if (i == 36) {
            return 6;
        }
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 1;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    public final int getSymbolDataWidth() {
        return getHorizontalDataRegions() * this.matrixWidth;
    }

    public final int getSymbolDataHeight() {
        return getVerticalDataRegions() * this.matrixHeight;
    }

    public final int getSymbolWidth() {
        return getSymbolDataWidth() + (getHorizontalDataRegions() * 2);
    }

    public final int getSymbolHeight() {
        return getSymbolDataHeight() + (getVerticalDataRegions() * 2);
    }

    public int getCodewordCount() {
        return this.dataCapacity + this.errorCodewords;
    }

    public int getInterleavedBlockCount() {
        return this.dataCapacity / this.rsBlockData;
    }

    public final int getDataCapacity() {
        return this.dataCapacity;
    }

    public final int getErrorCodewords() {
        return this.errorCodewords;
    }

    public int getDataLengthForInterleavedBlock(int i) {
        return this.rsBlockData;
    }

    public final int getErrorLengthForInterleavedBlock(int i) {
        return this.rsBlockError;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.rectangular ? "Rectangular Symbol:" : "Square Symbol:");
        stringBuilder.append(" data region ");
        stringBuilder.append(this.matrixWidth);
        stringBuilder.append('x');
        stringBuilder.append(this.matrixHeight);
        stringBuilder.append(", symbol size ");
        stringBuilder.append(getSymbolWidth());
        stringBuilder.append('x');
        stringBuilder.append(getSymbolHeight());
        stringBuilder.append(", symbol data size ");
        stringBuilder.append(getSymbolDataWidth());
        stringBuilder.append('x');
        stringBuilder.append(getSymbolDataHeight());
        stringBuilder.append(", codewords ");
        stringBuilder.append(this.dataCapacity);
        stringBuilder.append('+');
        stringBuilder.append(this.errorCodewords);
        return stringBuilder.toString();
    }
}
