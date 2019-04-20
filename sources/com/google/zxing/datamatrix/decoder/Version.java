package com.google.zxing.datamatrix.decoder;

public final class Version {
    private static final Version[] VERSIONS = buildVersions();
    private final int dataRegionSizeColumns;
    private final int dataRegionSizeRows;
    private final ECBlocks ecBlocks;
    private final int symbolSizeColumns;
    private final int symbolSizeRows;
    private final int totalCodewords;
    private final int versionNumber;

    static final class ECB {
        private final int count;
        private final int dataCodewords;

        private ECB(int i, int i2) {
            this.count = i;
            this.dataCodewords = i2;
        }

        int getCount() {
            return this.count;
        }

        int getDataCodewords() {
            return this.dataCodewords;
        }
    }

    static final class ECBlocks {
        private final ECB[] ecBlocks;
        private final int ecCodewords;

        private ECBlocks(int i, ECB ecb) {
            this.ecCodewords = i;
            this.ecBlocks = new ECB[]{ecb};
        }

        private ECBlocks(int i, ECB ecb, ECB ecb2) {
            this.ecCodewords = i;
            this.ecBlocks = new ECB[]{ecb, ecb2};
        }

        int getECCodewords() {
            return this.ecCodewords;
        }

        ECB[] getECBlocks() {
            return this.ecBlocks;
        }
    }

    public static com.google.zxing.datamatrix.decoder.Version getVersionForDimensions(int r5, int r6) throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x0026 in {10, 11, 13, 15} preds:[]
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
        r0 = r5 & 1;
        if (r0 != 0) goto L_0x0021;
    L_0x0004:
        r0 = r6 & 1;
        if (r0 != 0) goto L_0x0021;
    L_0x0008:
        r0 = VERSIONS;
        r1 = r0.length;
        r2 = 0;
    L_0x000c:
        if (r2 >= r1) goto L_0x001c;
    L_0x000e:
        r3 = r0[r2];
        r4 = r3.symbolSizeRows;
        if (r4 != r5) goto L_0x0019;
    L_0x0014:
        r4 = r3.symbolSizeColumns;
        if (r4 != r6) goto L_0x0019;
    L_0x0018:
        return r3;
    L_0x0019:
        r2 = r2 + 1;
        goto L_0x000c;
    L_0x001c:
        r5 = com.google.zxing.FormatException.getFormatInstance();
        throw r5;
    L_0x0021:
        r5 = com.google.zxing.FormatException.getFormatInstance();
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.Version.getVersionForDimensions(int, int):com.google.zxing.datamatrix.decoder.Version");
    }

    private Version(int i, int i2, int i3, int i4, int i5, ECBlocks eCBlocks) {
        this.versionNumber = i;
        this.symbolSizeRows = i2;
        this.symbolSizeColumns = i3;
        this.dataRegionSizeRows = i4;
        this.dataRegionSizeColumns = i5;
        this.ecBlocks = eCBlocks;
        i = eCBlocks.getECCodewords();
        i5 = 0;
        for (ECBlocks eCBlocks2 : eCBlocks2.getECBlocks()) {
            i5 += eCBlocks2.getCount() * (eCBlocks2.getDataCodewords() + i);
        }
        this.totalCodewords = i5;
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }

    public int getSymbolSizeRows() {
        return this.symbolSizeRows;
    }

    public int getSymbolSizeColumns() {
        return this.symbolSizeColumns;
    }

    public int getDataRegionSizeRows() {
        return this.dataRegionSizeRows;
    }

    public int getDataRegionSizeColumns() {
        return this.dataRegionSizeColumns;
    }

    public int getTotalCodewords() {
        return this.totalCodewords;
    }

    ECBlocks getECBlocks() {
        return this.ecBlocks;
    }

    public String toString() {
        return String.valueOf(this.versionNumber);
    }

    private static Version[] buildVersions() {
        r0 = new Version[30];
        r0[5] = new Version(6, 20, 20, 18, 18, new ECBlocks(18, new ECB(1, 22)));
        r0[6] = new Version(7, 22, 22, 20, 20, new ECBlocks(20, new ECB(1, 30)));
        r0[7] = new Version(8, 24, 24, 22, 22, new ECBlocks(24, new ECB(1, 36)));
        r0[8] = new Version(9, 26, 26, 24, 24, new ECBlocks(28, new ECB(1, 44)));
        r0[9] = new Version(10, 32, 32, 14, 14, new ECBlocks(36, new ECB(1, 62)));
        r0[10] = new Version(11, 36, 36, 16, 16, new ECBlocks(42, new ECB(1, 86)));
        r0[11] = new Version(12, 40, 40, 18, 18, new ECBlocks(48, new ECB(1, 114)));
        r0[12] = new Version(13, 44, 44, 20, 20, new ECBlocks(56, new ECB(1, 144)));
        r0[13] = new Version(14, 48, 48, 22, 22, new ECBlocks(68, new ECB(1, 174)));
        r0[14] = new Version(15, 52, 52, 24, 24, new ECBlocks(42, new ECB(2, 102)));
        r0[15] = new Version(16, 64, 64, 14, 14, new ECBlocks(56, new ECB(2, 140)));
        r0[16] = new Version(17, 72, 72, 16, 16, new ECBlocks(36, new ECB(4, 92)));
        r0[17] = new Version(18, 80, 80, 18, 18, new ECBlocks(48, new ECB(4, 114)));
        r0[18] = new Version(19, 88, 88, 20, 20, new ECBlocks(56, new ECB(4, 144)));
        r0[19] = new Version(20, 96, 96, 22, 22, new ECBlocks(68, new ECB(4, 174)));
        r0[20] = new Version(21, 104, 104, 24, 24, new ECBlocks(56, new ECB(6, 136)));
        r0[21] = new Version(22, 120, 120, 18, 18, new ECBlocks(68, new ECB(6, 175)));
        r0[22] = new Version(23, 132, 132, 20, 20, new ECBlocks(62, new ECB(8, 163)));
        r0[23] = new Version(24, 144, 144, 22, 22, new ECBlocks(62, new ECB(8, 156), new ECB(2, 155)));
        r0[24] = new Version(25, 8, 18, 6, 16, new ECBlocks(7, new ECB(1, 5)));
        r0[25] = new Version(26, 8, 32, 6, 14, new ECBlocks(11, new ECB(1, 10)));
        r0[26] = new Version(27, 12, 26, 10, 24, new ECBlocks(14, new ECB(1, 16)));
        r0[27] = new Version(28, 12, 36, 10, 16, new ECBlocks(18, new ECB(1, 22)));
        r0[28] = new Version(29, 16, 36, 14, 16, new ECBlocks(24, new ECB(1, 32)));
        r0[29] = new Version(30, 16, 48, 14, 22, new ECBlocks(28, new ECB(1, 49)));
        return r0;
    }
}
