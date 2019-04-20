package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

final class BitMatrixParser {
    private final BitMatrix bitMatrix;
    private boolean mirror;
    private FormatInformation parsedFormatInfo;
    private Version parsedVersion;

    byte[] readCodewords() throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:31:0x008c in {4, 8, 9, 17, 18, 21, 22, 23, 24, 25, 28, 30} preds:[]
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
        r17 = this;
        r0 = r17;
        r1 = r17.readFormatInformation();
        r2 = r17.readVersion();
        r1 = r1.getDataMask();
        r1 = com.google.zxing.qrcode.decoder.DataMask.forReference(r1);
        r3 = r0.bitMatrix;
        r3 = r3.getHeight();
        r4 = r0.bitMatrix;
        r1.unmaskBitMatrix(r4, r3);
        r1 = r2.buildFunctionPattern();
        r4 = r2.getTotalCodewords();
        r4 = new byte[r4];
        r5 = r3 + -1;
        r7 = 1;
        r7 = r5;
        r8 = 0;
        r9 = 1;
        r10 = 0;
        r11 = 0;
    L_0x002f:
        if (r7 <= 0) goto L_0x0080;
    L_0x0031:
        r12 = 6;
        if (r7 != r12) goto L_0x0036;
    L_0x0034:
        r7 = r7 + -1;
    L_0x0036:
        r12 = r11;
        r11 = r10;
        r10 = r8;
        r8 = 0;
    L_0x003a:
        if (r8 >= r3) goto L_0x0078;
    L_0x003c:
        if (r9 == 0) goto L_0x0041;
    L_0x003e:
        r13 = r5 - r8;
        goto L_0x0042;
    L_0x0041:
        r13 = r8;
    L_0x0042:
        r14 = r12;
        r12 = r11;
        r11 = r10;
        r10 = 0;
    L_0x0046:
        r15 = 2;
        if (r10 >= r15) goto L_0x0072;
    L_0x0049:
        r15 = r7 - r10;
        r16 = r1.get(r15, r13);
        if (r16 != 0) goto L_0x006f;
    L_0x0051:
        r12 = r12 + 1;
        r14 = r14 << 1;
        r6 = r0.bitMatrix;
        r6 = r6.get(r15, r13);
        if (r6 == 0) goto L_0x0060;
    L_0x005d:
        r6 = r14 | 1;
        goto L_0x0061;
    L_0x0060:
        r6 = r14;
    L_0x0061:
        r14 = 8;
        if (r12 != r14) goto L_0x006e;
    L_0x0065:
        r12 = r11 + 1;
        r6 = (byte) r6;
        r4[r11] = r6;
        r11 = r12;
        r12 = 0;
        r14 = 0;
        goto L_0x006f;
    L_0x006e:
        r14 = r6;
    L_0x006f:
        r10 = r10 + 1;
        goto L_0x0046;
    L_0x0072:
        r8 = r8 + 1;
        r10 = r11;
        r11 = r12;
        r12 = r14;
        goto L_0x003a;
    L_0x0078:
        r9 = r9 ^ 1;
        r7 = r7 + -2;
        r8 = r10;
        r10 = r11;
        r11 = r12;
        goto L_0x002f;
    L_0x0080:
        r1 = r2.getTotalCodewords();
        if (r8 != r1) goto L_0x0087;
    L_0x0086:
        return r4;
    L_0x0087:
        r1 = com.google.zxing.FormatException.getFormatInstance();
        throw r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.BitMatrixParser.readCodewords():byte[]");
    }

    com.google.zxing.qrcode.decoder.FormatInformation readFormatInformation() throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:21:0x0059 in {2, 6, 9, 12, 15, 18, 20} preds:[]
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
        r6 = this;
        r0 = r6.parsedFormatInfo;
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        r0 = 0;
        r1 = 0;
        r2 = 0;
    L_0x0008:
        r3 = 6;
        r4 = 8;
        if (r1 >= r3) goto L_0x0014;
    L_0x000d:
        r2 = r6.copyBit(r1, r4, r2);
        r1 = r1 + 1;
        goto L_0x0008;
    L_0x0014:
        r1 = 7;
        r2 = r6.copyBit(r1, r4, r2);
        r2 = r6.copyBit(r4, r4, r2);
        r1 = r6.copyBit(r4, r1, r2);
        r2 = 5;
    L_0x0022:
        if (r2 < 0) goto L_0x002b;
    L_0x0024:
        r1 = r6.copyBit(r4, r2, r1);
        r2 = r2 + -1;
        goto L_0x0022;
    L_0x002b:
        r2 = r6.bitMatrix;
        r2 = r2.getHeight();
        r3 = r2 + -7;
        r5 = r2 + -1;
    L_0x0035:
        if (r5 < r3) goto L_0x003e;
    L_0x0037:
        r0 = r6.copyBit(r4, r5, r0);
        r5 = r5 + -1;
        goto L_0x0035;
    L_0x003e:
        r3 = r2 + -8;
    L_0x0040:
        if (r3 >= r2) goto L_0x0049;
    L_0x0042:
        r0 = r6.copyBit(r3, r4, r0);
        r3 = r3 + 1;
        goto L_0x0040;
    L_0x0049:
        r0 = com.google.zxing.qrcode.decoder.FormatInformation.decodeFormatInformation(r1, r0);
        r6.parsedFormatInfo = r0;
        r0 = r6.parsedFormatInfo;
        if (r0 == 0) goto L_0x0054;
    L_0x0053:
        return r0;
    L_0x0054:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.BitMatrixParser.readFormatInformation():com.google.zxing.qrcode.decoder.FormatInformation");
    }

    com.google.zxing.qrcode.decoder.Version readVersion() throws com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:32:0x0060 in {2, 6, 11, 12, 18, 22, 23, 29, 31} preds:[]
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
        r7 = this;
        r0 = r7.parsedVersion;
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        r0 = r7.bitMatrix;
        r0 = r0.getHeight();
        r1 = r0 + -17;
        r1 = r1 / 4;
        r2 = 6;
        if (r1 > r2) goto L_0x0017;
    L_0x0012:
        r0 = com.google.zxing.qrcode.decoder.Version.getVersionForNumber(r1);
        return r0;
    L_0x0017:
        r1 = r0 + -11;
        r2 = 5;
        r3 = 0;
        r4 = 5;
        r5 = 0;
    L_0x001d:
        if (r4 < 0) goto L_0x002d;
    L_0x001f:
        r6 = r0 + -9;
    L_0x0021:
        if (r6 < r1) goto L_0x002a;
    L_0x0023:
        r5 = r7.copyBit(r6, r4, r5);
        r6 = r6 + -1;
        goto L_0x0021;
    L_0x002a:
        r4 = r4 + -1;
        goto L_0x001d;
    L_0x002d:
        r4 = com.google.zxing.qrcode.decoder.Version.decodeVersionInformation(r5);
        if (r4 == 0) goto L_0x003c;
    L_0x0033:
        r5 = r4.getDimensionForVersion();
        if (r5 != r0) goto L_0x003c;
    L_0x0039:
        r7.parsedVersion = r4;
        return r4;
    L_0x003c:
        if (r2 < 0) goto L_0x004c;
    L_0x003e:
        r4 = r0 + -9;
    L_0x0040:
        if (r4 < r1) goto L_0x0049;
    L_0x0042:
        r3 = r7.copyBit(r2, r4, r3);
        r4 = r4 + -1;
        goto L_0x0040;
    L_0x0049:
        r2 = r2 + -1;
        goto L_0x003c;
    L_0x004c:
        r1 = com.google.zxing.qrcode.decoder.Version.decodeVersionInformation(r3);
        if (r1 == 0) goto L_0x005b;
    L_0x0052:
        r2 = r1.getDimensionForVersion();
        if (r2 != r0) goto L_0x005b;
    L_0x0058:
        r7.parsedVersion = r1;
        return r1;
    L_0x005b:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.BitMatrixParser.readVersion():com.google.zxing.qrcode.decoder.Version");
    }

    BitMatrixParser(BitMatrix bitMatrix) throws FormatException {
        int height = bitMatrix.getHeight();
        if (height < 21 || (height & 3) != 1) {
            throw FormatException.getFormatInstance();
        }
        this.bitMatrix = bitMatrix;
    }

    private int copyBit(int i, int i2, int i3) {
        return (this.mirror ? this.bitMatrix.get(i2, i) : this.bitMatrix.get(i, i2)) != 0 ? (i3 << 1) | 1 : i3 << 1;
    }

    void remask() {
        FormatInformation formatInformation = this.parsedFormatInfo;
        if (formatInformation != null) {
            DataMask.forReference(formatInformation.getDataMask()).unmaskBitMatrix(this.bitMatrix, this.bitMatrix.getHeight());
        }
    }

    void setMirror(boolean z) {
        this.parsedVersion = null;
        this.parsedFormatInfo = null;
        this.mirror = z;
    }

    void mirror() {
        int i = 0;
        while (i < this.bitMatrix.getWidth()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < this.bitMatrix.getHeight(); i3++) {
                if (this.bitMatrix.get(i, i3) != this.bitMatrix.get(i3, i)) {
                    this.bitMatrix.flip(i3, i);
                    this.bitMatrix.flip(i, i3);
                }
            }
            i = i2;
        }
    }
}
