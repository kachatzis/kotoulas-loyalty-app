package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

final class BitMatrixParser {
    private final BitMatrix mappingBitMatrix;
    private final BitMatrix readMappingMatrix;
    private final Version version;

    com.google.zxing.common.BitMatrix extractDataRegion(com.google.zxing.common.BitMatrix r17) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x0073 in {12, 13, 14, 15, 16, 17, 19} preds:[]
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
        r16 = this;
        r0 = r16;
        r1 = r0.version;
        r1 = r1.getSymbolSizeRows();
        r2 = r0.version;
        r2 = r2.getSymbolSizeColumns();
        r3 = r17.getHeight();
        if (r3 != r1) goto L_0x006b;
    L_0x0014:
        r3 = r0.version;
        r3 = r3.getDataRegionSizeRows();
        r4 = r0.version;
        r4 = r4.getDataRegionSizeColumns();
        r1 = r1 / r3;
        r2 = r2 / r4;
        r5 = r1 * r3;
        r6 = r2 * r4;
        r7 = new com.google.zxing.common.BitMatrix;
        r7.<init>(r6, r5);
        r6 = 0;
    L_0x002c:
        if (r6 >= r1) goto L_0x006a;
    L_0x002e:
        r8 = r6 * r3;
        r9 = 0;
    L_0x0031:
        if (r9 >= r2) goto L_0x0065;
    L_0x0033:
        r10 = r9 * r4;
        r11 = 0;
    L_0x0036:
        if (r11 >= r3) goto L_0x0060;
    L_0x0038:
        r12 = r3 + 2;
        r12 = r12 * r6;
        r12 = r12 + 1;
        r12 = r12 + r11;
        r13 = r8 + r11;
        r14 = 0;
    L_0x0042:
        if (r14 >= r4) goto L_0x005b;
    L_0x0044:
        r15 = r4 + 2;
        r15 = r15 * r9;
        r15 = r15 + 1;
        r15 = r15 + r14;
        r5 = r17;
        r15 = r5.get(r15, r12);
        if (r15 == 0) goto L_0x0058;
    L_0x0053:
        r15 = r10 + r14;
        r7.set(r15, r13);
    L_0x0058:
        r14 = r14 + 1;
        goto L_0x0042;
    L_0x005b:
        r5 = r17;
        r11 = r11 + 1;
        goto L_0x0036;
    L_0x0060:
        r5 = r17;
        r9 = r9 + 1;
        goto L_0x0031;
    L_0x0065:
        r5 = r17;
        r6 = r6 + 1;
        goto L_0x002c;
    L_0x006a:
        return r7;
    L_0x006b:
        r1 = new java.lang.IllegalArgumentException;
        r2 = "Dimension of bitMarix must match the version size";
        r1.<init>(r2);
        throw r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.BitMatrixParser.extractDataRegion(com.google.zxing.common.BitMatrix):com.google.zxing.common.BitMatrix");
    }

    byte[] readCodewords() throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:52:0x00df in {5, 12, 20, 26, 31, 34, 40, 43, 44, 49, 51} preds:[]
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
        r14 = this;
        r0 = r14.version;
        r0 = r0.getTotalCodewords();
        r0 = new byte[r0];
        r1 = r14.mappingBitMatrix;
        r1 = r1.getHeight();
        r2 = r14.mappingBitMatrix;
        r2 = r2.getWidth();
        r3 = 4;
        r4 = 0;
        r4 = 4;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
    L_0x001d:
        r11 = 1;
        if (r4 != r1) goto L_0x0035;
    L_0x0020:
        if (r5 != 0) goto L_0x0035;
    L_0x0022:
        if (r6 != 0) goto L_0x0035;
    L_0x0024:
        r6 = r7 + 1;
        r12 = r14.readCorner1(r1, r2);
        r12 = (byte) r12;
        r0[r7] = r12;
        r4 = r4 + -2;
        r5 = r5 + 2;
        r7 = r6;
        r6 = 1;
        goto L_0x00cd;
    L_0x0035:
        r12 = r1 + -2;
        if (r4 != r12) goto L_0x0052;
    L_0x0039:
        if (r5 != 0) goto L_0x0052;
    L_0x003b:
        r13 = r2 & 3;
        if (r13 == 0) goto L_0x0052;
    L_0x003f:
        if (r8 != 0) goto L_0x0052;
    L_0x0041:
        r8 = r7 + 1;
        r12 = r14.readCorner2(r1, r2);
        r12 = (byte) r12;
        r0[r7] = r12;
        r4 = r4 + -2;
        r5 = r5 + 2;
        r7 = r8;
        r8 = 1;
        goto L_0x00cd;
    L_0x0052:
        r13 = r1 + 4;
        if (r4 != r13) goto L_0x006f;
    L_0x0056:
        r13 = 2;
        if (r5 != r13) goto L_0x006f;
    L_0x0059:
        r13 = r2 & 7;
        if (r13 != 0) goto L_0x006f;
    L_0x005d:
        if (r9 != 0) goto L_0x006f;
    L_0x005f:
        r9 = r7 + 1;
        r12 = r14.readCorner3(r1, r2);
        r12 = (byte) r12;
        r0[r7] = r12;
        r4 = r4 + -2;
        r5 = r5 + 2;
        r7 = r9;
        r9 = 1;
        goto L_0x00cd;
    L_0x006f:
        if (r4 != r12) goto L_0x0089;
    L_0x0071:
        if (r5 != 0) goto L_0x0089;
    L_0x0073:
        r12 = r2 & 7;
        if (r12 != r3) goto L_0x0089;
    L_0x0077:
        if (r10 != 0) goto L_0x0089;
    L_0x0079:
        r10 = r7 + 1;
        r12 = r14.readCorner4(r1, r2);
        r12 = (byte) r12;
        r0[r7] = r12;
        r4 = r4 + -2;
        r5 = r5 + 2;
        r7 = r10;
        r10 = 1;
        goto L_0x00cd;
    L_0x0089:
        if (r4 >= r1) goto L_0x009f;
    L_0x008b:
        if (r5 < 0) goto L_0x009f;
    L_0x008d:
        r11 = r14.readMappingMatrix;
        r11 = r11.get(r5, r4);
        if (r11 != 0) goto L_0x009f;
    L_0x0095:
        r11 = r7 + 1;
        r12 = r14.readUtah(r4, r5, r1, r2);
        r12 = (byte) r12;
        r0[r7] = r12;
        r7 = r11;
    L_0x009f:
        r4 = r4 + -2;
        r5 = r5 + 2;
        if (r4 < 0) goto L_0x00a7;
    L_0x00a5:
        if (r5 < r2) goto L_0x0089;
    L_0x00a7:
        r4 = r4 + 1;
        r5 = r5 + 3;
    L_0x00ab:
        if (r4 < 0) goto L_0x00c1;
    L_0x00ad:
        if (r5 >= r2) goto L_0x00c1;
    L_0x00af:
        r11 = r14.readMappingMatrix;
        r11 = r11.get(r5, r4);
        if (r11 != 0) goto L_0x00c1;
    L_0x00b7:
        r11 = r7 + 1;
        r12 = r14.readUtah(r4, r5, r1, r2);
        r12 = (byte) r12;
        r0[r7] = r12;
        r7 = r11;
    L_0x00c1:
        r4 = r4 + 2;
        r5 = r5 + -2;
        if (r4 >= r1) goto L_0x00c9;
    L_0x00c7:
        if (r5 >= 0) goto L_0x00ab;
    L_0x00c9:
        r4 = r4 + 3;
        r5 = r5 + 1;
    L_0x00cd:
        if (r4 < r1) goto L_0x001d;
    L_0x00cf:
        if (r5 < r2) goto L_0x001d;
    L_0x00d1:
        r1 = r14.version;
        r1 = r1.getTotalCodewords();
        if (r7 != r1) goto L_0x00da;
    L_0x00d9:
        return r0;
    L_0x00da:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.BitMatrixParser.readCodewords():byte[]");
    }

    BitMatrixParser(BitMatrix bitMatrix) throws FormatException {
        int height = bitMatrix.getHeight();
        if (height < 8 || height > 144 || (height & 1) != 0) {
            throw FormatException.getFormatInstance();
        }
        this.version = readVersion(bitMatrix);
        this.mappingBitMatrix = extractDataRegion(bitMatrix);
        this.readMappingMatrix = new BitMatrix(this.mappingBitMatrix.getWidth(), this.mappingBitMatrix.getHeight());
    }

    Version getVersion() {
        return this.version;
    }

    private static Version readVersion(BitMatrix bitMatrix) throws FormatException {
        return Version.getVersionForDimensions(bitMatrix.getHeight(), bitMatrix.getWidth());
    }

    boolean readModule(int i, int i2, int i3, int i4) {
        if (i < 0) {
            i += i3;
            i2 += 4 - ((i3 + 4) & 7);
        }
        if (i2 < 0) {
            i2 += i4;
            i += 4 - ((i4 + 4) & 7);
        }
        this.readMappingMatrix.set(i2, i);
        return this.mappingBitMatrix.get(i2, i);
    }

    int readUtah(int i, int i2, int i3, int i4) {
        int i5 = i - 2;
        int i6 = i2 - 2;
        int i7 = (readModule(i5, i6, i3, i4) ? 1 : 0) << 1;
        int i8 = i2 - 1;
        if (readModule(i5, i8, i3, i4)) {
            i7 |= 1;
        }
        i5 = i7 << 1;
        i7 = i - 1;
        if (readModule(i7, i6, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (readModule(i7, i8, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (readModule(i7, i2, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (readModule(i, i6, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (readModule(i, i8, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        return readModule(i, i2, i3, i4) != 0 ? i5 | 1 : i5;
    }

    int readCorner1(int i, int i2) {
        int i3 = i - 1;
        int i4 = (readModule(i3, 0, i, i2) ? 1 : 0) << 1;
        if (readModule(i3, 1, i, i2)) {
            i4 |= 1;
        }
        i4 <<= 1;
        if (readModule(i3, 2, i, i2)) {
            i4 |= 1;
        }
        i3 = i4 << 1;
        if (readModule(0, i2 - 2, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        i4 = i2 - 1;
        if (readModule(0, i4, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(1, i4, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(2, i4, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        return readModule(3, i4, i, i2) != 0 ? i3 | 1 : i3;
    }

    int readCorner2(int i, int i2) {
        int i3 = (readModule(i + -3, 0, i, i2) ? 1 : 0) << 1;
        if (readModule(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(i - 1, 0, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 4, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 3, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 2, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        int i4 = i2 - 1;
        if (readModule(0, i4, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        return readModule(1, i4, i, i2) != 0 ? i3 | 1 : i3;
    }

    int readCorner3(int i, int i2) {
        int i3 = i - 1;
        int i4 = (readModule(i3, 0, i, i2) ? 1 : 0) << 1;
        int i5 = i2 - 1;
        if (readModule(i3, i5, i, i2)) {
            i4 |= 1;
        }
        i3 = i4 << 1;
        i4 = i2 - 3;
        if (readModule(0, i4, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        int i6 = i2 - 2;
        if (readModule(0, i6, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i5, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(1, i4, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(1, i6, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        return readModule(1, i5, i, i2) != 0 ? i3 | 1 : i3;
    }

    int readCorner4(int i, int i2) {
        int i3 = (readModule(i + -3, 0, i, i2) ? 1 : 0) << 1;
        if (readModule(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(i - 1, 0, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 2, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        int i4 = i2 - 1;
        if (readModule(0, i4, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(1, i4, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(2, i4, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        return readModule(3, i4, i, i2) != 0 ? i3 | 1 : i3;
    }
}
