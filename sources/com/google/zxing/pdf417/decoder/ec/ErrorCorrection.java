package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.ChecksumException;

public final class ErrorCorrection {
    private final ModulusGF field = ModulusGF.PDF417_GF;

    private int[] findErrorLocations(com.google.zxing.pdf417.decoder.ec.ModulusPoly r6) throws com.google.zxing.ChecksumException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:12:0x002d in {6, 7, 9, 11} preds:[]
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
        r5 = this;
        r0 = r6.getDegree();
        r1 = new int[r0];
        r2 = 1;
        r3 = 0;
    L_0x0008:
        r4 = r5.field;
        r4 = r4.getSize();
        if (r2 >= r4) goto L_0x0025;
    L_0x0010:
        if (r3 >= r0) goto L_0x0025;
    L_0x0012:
        r4 = r6.evaluateAt(r2);
        if (r4 != 0) goto L_0x0022;
    L_0x0018:
        r4 = r5.field;
        r4 = r4.inverse(r2);
        r1[r3] = r4;
        r3 = r3 + 1;
    L_0x0022:
        r2 = r2 + 1;
        goto L_0x0008;
    L_0x0025:
        if (r3 != r0) goto L_0x0028;
    L_0x0027:
        return r1;
    L_0x0028:
        r6 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.ec.ErrorCorrection.findErrorLocations(com.google.zxing.pdf417.decoder.ec.ModulusPoly):int[]");
    }

    private com.google.zxing.pdf417.decoder.ec.ModulusPoly[] runEuclideanAlgorithm(com.google.zxing.pdf417.decoder.ec.ModulusPoly r10, com.google.zxing.pdf417.decoder.ec.ModulusPoly r11, int r12) throws com.google.zxing.ChecksumException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x00b6 in {2, 13, 14, 16, 20, 22} preds:[]
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
        r0 = r10.getDegree();
        r1 = r11.getDegree();
        if (r0 >= r1) goto L_0x000d;
    L_0x000a:
        r7 = r11;
        r11 = r10;
        r10 = r7;
    L_0x000d:
        r0 = r9.field;
        r0 = r0.getZero();
        r1 = r9.field;
        r1 = r1.getOne();
        r7 = r11;
        r11 = r10;
        r10 = r7;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x001f:
        r2 = r10.getDegree();
        r3 = 2;
        r4 = r12 / 2;
        if (r2 < r4) goto L_0x0094;
    L_0x0028:
        r2 = r10.isZero();
        if (r2 != 0) goto L_0x008f;
    L_0x002e:
        r2 = r9.field;
        r2 = r2.getZero();
        r3 = r10.getDegree();
        r3 = r10.getCoefficient(r3);
        r4 = r9.field;
        r3 = r4.inverse(r3);
    L_0x0042:
        r4 = r11.getDegree();
        r5 = r10.getDegree();
        if (r4 < r5) goto L_0x007c;
    L_0x004c:
        r4 = r11.isZero();
        if (r4 != 0) goto L_0x007c;
    L_0x0052:
        r4 = r11.getDegree();
        r5 = r10.getDegree();
        r4 = r4 - r5;
        r5 = r9.field;
        r6 = r11.getDegree();
        r6 = r11.getCoefficient(r6);
        r5 = r5.multiply(r6, r3);
        r6 = r9.field;
        r6 = r6.buildMonomial(r4, r5);
        r2 = r2.add(r6);
        r4 = r10.multiplyByMonomial(r4, r5);
        r11 = r11.subtract(r4);
        goto L_0x0042;
    L_0x007c:
        r2 = r2.multiply(r0);
        r1 = r2.subtract(r1);
        r1 = r1.negative();
        r7 = r11;
        r11 = r10;
        r10 = r7;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x001f;
    L_0x008f:
        r10 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r10;
    L_0x0094:
        r11 = 0;
        r12 = r0.getCoefficient(r11);
        if (r12 == 0) goto L_0x00b1;
    L_0x009b:
        r1 = r9.field;
        r12 = r1.inverse(r12);
        r0 = r0.multiply(r12);
        r10 = r10.multiply(r12);
        r12 = new com.google.zxing.pdf417.decoder.ec.ModulusPoly[r3];
        r12[r11] = r0;
        r11 = 1;
        r12[r11] = r10;
        return r12;
    L_0x00b1:
        r10 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.ec.ErrorCorrection.runEuclideanAlgorithm(com.google.zxing.pdf417.decoder.ec.ModulusPoly, com.google.zxing.pdf417.decoder.ec.ModulusPoly, int):com.google.zxing.pdf417.decoder.ec.ModulusPoly[]");
    }

    public int decode(int[] iArr, int i, int[] iArr2) throws ChecksumException {
        ModulusPoly modulusPoly = new ModulusPoly(this.field, iArr);
        int[] iArr3 = new int[i];
        int i2 = 0;
        Object obj = null;
        for (int i3 = i; i3 > 0; i3--) {
            int evaluateAt = modulusPoly.evaluateAt(this.field.exp(i3));
            iArr3[i - i3] = evaluateAt;
            if (evaluateAt != 0) {
                obj = 1;
            }
        }
        if (obj == null) {
            return 0;
        }
        modulusPoly = this.field.getOne();
        if (iArr2 != null) {
            ModulusPoly modulusPoly2 = modulusPoly;
            for (int evaluateAt2 : iArr2) {
                evaluateAt2 = this.field.exp((iArr.length - 1) - evaluateAt2);
                modulusPoly2 = modulusPoly2.multiply(new ModulusPoly(this.field, new int[]{this.field.subtract(0, evaluateAt2), 1}));
            }
        }
        i = runEuclideanAlgorithm(this.field.buildMonomial(i, 1), new ModulusPoly(this.field, iArr3), i);
        iArr2 = i[0];
        i = i[1];
        int[] findErrorLocations = findErrorLocations(iArr2);
        i = findErrorMagnitudes(i, iArr2, findErrorLocations);
        while (i2 < findErrorLocations.length) {
            iArr2 = (iArr.length - 1) - this.field.log(findErrorLocations[i2]);
            if (iArr2 >= null) {
                iArr[iArr2] = this.field.subtract(iArr[iArr2], i[i2]);
                i2++;
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
        return findErrorLocations.length;
    }

    private int[] findErrorMagnitudes(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, int[] iArr) {
        int degree = modulusPoly2.getDegree();
        int[] iArr2 = new int[degree];
        for (int i = 1; i <= degree; i++) {
            iArr2[degree - i] = this.field.multiply(i, modulusPoly2.getCoefficient(i));
        }
        modulusPoly2 = new ModulusPoly(this.field, iArr2);
        degree = iArr.length;
        iArr2 = new int[degree];
        for (int i2 = 0; i2 < degree; i2++) {
            int inverse = this.field.inverse(iArr[i2]);
            iArr2[i2] = this.field.multiply(this.field.subtract(0, modulusPoly.evaluateAt(inverse)), this.field.inverse(modulusPoly2.evaluateAt(inverse)));
        }
        return iArr2;
    }
}
