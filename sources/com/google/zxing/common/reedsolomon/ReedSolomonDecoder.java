package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder {
    private final GenericGF field;

    private int[] findErrorLocations(com.google.zxing.common.reedsolomon.GenericGFPoly r6) throws com.google.zxing.common.reedsolomon.ReedSolomonException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x003b in {3, 10, 11, 13, 15} preds:[]
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
        r1 = 0;
        r2 = 1;
        if (r0 != r2) goto L_0x0011;
    L_0x0008:
        r0 = new int[r2];
        r6 = r6.getCoefficient(r2);
        r0[r1] = r6;
        return r0;
    L_0x0011:
        r3 = new int[r0];
    L_0x0013:
        r4 = r5.field;
        r4 = r4.getSize();
        if (r2 >= r4) goto L_0x0030;
    L_0x001b:
        if (r1 >= r0) goto L_0x0030;
    L_0x001d:
        r4 = r6.evaluateAt(r2);
        if (r4 != 0) goto L_0x002d;
    L_0x0023:
        r4 = r5.field;
        r4 = r4.inverse(r2);
        r3[r1] = r4;
        r1 = r1 + 1;
    L_0x002d:
        r2 = r2 + 1;
        goto L_0x0013;
    L_0x0030:
        if (r1 != r0) goto L_0x0033;
    L_0x0032:
        return r3;
    L_0x0033:
        r6 = new com.google.zxing.common.reedsolomon.ReedSolomonException;
        r0 = "Error locator degree does not match number of roots";
        r6.<init>(r0);
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.reedsolomon.ReedSolomonDecoder.findErrorLocations(com.google.zxing.common.reedsolomon.GenericGFPoly):int[]");
    }

    private com.google.zxing.common.reedsolomon.GenericGFPoly[] runEuclideanAlgorithm(com.google.zxing.common.reedsolomon.GenericGFPoly r10, com.google.zxing.common.reedsolomon.GenericGFPoly r11, int r12) throws com.google.zxing.common.reedsolomon.ReedSolomonException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:27:0x00ca in {2, 13, 16, 18, 20, 24, 26} preds:[]
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
        if (r2 < r4) goto L_0x00a5;
    L_0x0028:
        r2 = r10.isZero();
        if (r2 != 0) goto L_0x009d;
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
        r2 = r2.addOrSubtract(r6);
        r4 = r10.multiplyByMonomial(r4, r5);
        r11 = r11.addOrSubtract(r4);
        goto L_0x0042;
    L_0x007c:
        r2 = r2.multiply(r0);
        r1 = r2.addOrSubtract(r1);
        r2 = r11.getDegree();
        r3 = r10.getDegree();
        if (r2 >= r3) goto L_0x0095;
    L_0x008e:
        r7 = r11;
        r11 = r10;
        r10 = r7;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x001f;
    L_0x0095:
        r10 = new java.lang.IllegalStateException;
        r11 = "Division algorithm failed to reduce polynomial?";
        r10.<init>(r11);
        throw r10;
    L_0x009d:
        r10 = new com.google.zxing.common.reedsolomon.ReedSolomonException;
        r11 = "r_{i-1} was zero";
        r10.<init>(r11);
        throw r10;
    L_0x00a5:
        r11 = 0;
        r12 = r0.getCoefficient(r11);
        if (r12 == 0) goto L_0x00c2;
    L_0x00ac:
        r1 = r9.field;
        r12 = r1.inverse(r12);
        r0 = r0.multiply(r12);
        r10 = r10.multiply(r12);
        r12 = new com.google.zxing.common.reedsolomon.GenericGFPoly[r3];
        r12[r11] = r0;
        r11 = 1;
        r12[r11] = r10;
        return r12;
    L_0x00c2:
        r10 = new com.google.zxing.common.reedsolomon.ReedSolomonException;
        r11 = "sigmaTilde(0) was zero";
        r10.<init>(r11);
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.reedsolomon.ReedSolomonDecoder.runEuclideanAlgorithm(com.google.zxing.common.reedsolomon.GenericGFPoly, com.google.zxing.common.reedsolomon.GenericGFPoly, int):com.google.zxing.common.reedsolomon.GenericGFPoly[]");
    }

    public ReedSolomonDecoder(GenericGF genericGF) {
        this.field = genericGF;
    }

    public void decode(int[] iArr, int i) throws ReedSolomonException {
        GenericGFPoly genericGFPoly = new GenericGFPoly(this.field, iArr);
        int[] iArr2 = new int[i];
        int i2 = 0;
        Object obj = 1;
        for (int i3 = 0; i3 < i; i3++) {
            GenericGF genericGF = this.field;
            int evaluateAt = genericGFPoly.evaluateAt(genericGF.exp(genericGF.getGeneratorBase() + i3));
            iArr2[(iArr2.length - 1) - i3] = evaluateAt;
            if (evaluateAt != 0) {
                obj = null;
            }
        }
        if (obj == null) {
            i = runEuclideanAlgorithm(this.field.buildMonomial(i, 1), new GenericGFPoly(this.field, iArr2), i);
            genericGFPoly = i[0];
            i = i[1];
            int[] findErrorLocations = findErrorLocations(genericGFPoly);
            i = findErrorMagnitudes(i, findErrorLocations);
            while (i2 < findErrorLocations.length) {
                int length = (iArr.length - 1) - this.field.log(findErrorLocations[i2]);
                if (length >= 0) {
                    iArr[length] = GenericGF.addOrSubtract(iArr[length], i[i2]);
                    i2++;
                } else {
                    throw new ReedSolomonException("Bad error location");
                }
            }
        }
    }

    private int[] findErrorMagnitudes(GenericGFPoly genericGFPoly, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int inverse = this.field.inverse(iArr[i]);
            int i2 = 1;
            for (int i3 = 0; i3 < length; i3++) {
                if (i != i3) {
                    int multiply = this.field.multiply(iArr[i3], inverse);
                    i2 = this.field.multiply(i2, (multiply & 1) == 0 ? multiply | 1 : multiply & -2);
                }
            }
            iArr2[i] = this.field.multiply(genericGFPoly.evaluateAt(inverse), this.field.inverse(i2));
            if (this.field.getGeneratorBase() != 0) {
                iArr2[i] = this.field.multiply(iArr2[i], inverse);
            }
        }
        return iArr2;
    }
}
