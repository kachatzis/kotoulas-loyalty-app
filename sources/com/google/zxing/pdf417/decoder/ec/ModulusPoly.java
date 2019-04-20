package com.google.zxing.pdf417.decoder.ec;

final class ModulusPoly {
    private final int[] coefficients;
    private final ModulusGF field;

    ModulusPoly(com.google.zxing.pdf417.decoder.ec.ModulusGF r5, int[] r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:18:0x0039 in {10, 12, 13, 14, 15, 17} preds:[]
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
        r4 = this;
        r4.<init>();
        r0 = r6.length;
        if (r0 == 0) goto L_0x0033;
    L_0x0006:
        r4.field = r5;
        r5 = r6.length;
        r0 = 1;
        if (r5 <= r0) goto L_0x0030;
    L_0x000c:
        r1 = 0;
        r2 = r6[r1];
        if (r2 != 0) goto L_0x0030;
    L_0x0011:
        r2 = 1;
    L_0x0012:
        if (r2 >= r5) goto L_0x001b;
    L_0x0014:
        r3 = r6[r2];
        if (r3 != 0) goto L_0x001b;
    L_0x0018:
        r2 = r2 + 1;
        goto L_0x0012;
    L_0x001b:
        if (r2 != r5) goto L_0x0024;
    L_0x001d:
        r5 = new int[r0];
        r5[r1] = r1;
        r4.coefficients = r5;
        goto L_0x0032;
    L_0x0024:
        r5 = r5 - r2;
        r5 = new int[r5];
        r4.coefficients = r5;
        r5 = r4.coefficients;
        r0 = r5.length;
        java.lang.System.arraycopy(r6, r2, r5, r1, r0);
        goto L_0x0032;
    L_0x0030:
        r4.coefficients = r6;
    L_0x0032:
        return;
    L_0x0033:
        r5 = new java.lang.IllegalArgumentException;
        r5.<init>();
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.ec.ModulusPoly.<init>(com.google.zxing.pdf417.decoder.ec.ModulusGF, int[]):void");
    }

    com.google.zxing.pdf417.decoder.ec.ModulusPoly add(com.google.zxing.pdf417.decoder.ec.ModulusPoly r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:19:0x0052 in {4, 7, 10, 14, 16, 18} preds:[]
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
        r8 = this;
        r0 = r8.field;
        r1 = r9.field;
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x004a;
    L_0x000a:
        r0 = r8.isZero();
        if (r0 == 0) goto L_0x0011;
    L_0x0010:
        return r9;
    L_0x0011:
        r0 = r9.isZero();
        if (r0 == 0) goto L_0x0018;
    L_0x0017:
        return r8;
    L_0x0018:
        r0 = r8.coefficients;
        r9 = r9.coefficients;
        r1 = r0.length;
        r2 = r9.length;
        if (r1 <= r2) goto L_0x0023;
    L_0x0020:
        r7 = r0;
        r0 = r9;
        r9 = r7;
    L_0x0023:
        r1 = r9.length;
        r1 = new int[r1];
        r2 = r9.length;
        r3 = r0.length;
        r2 = r2 - r3;
        r3 = 0;
        java.lang.System.arraycopy(r9, r3, r1, r3, r2);
        r3 = r2;
    L_0x002e:
        r4 = r9.length;
        if (r3 >= r4) goto L_0x0042;
    L_0x0031:
        r4 = r8.field;
        r5 = r3 - r2;
        r5 = r0[r5];
        r6 = r9[r3];
        r4 = r4.add(r5, r6);
        r1[r3] = r4;
        r3 = r3 + 1;
        goto L_0x002e;
    L_0x0042:
        r9 = new com.google.zxing.pdf417.decoder.ec.ModulusPoly;
        r0 = r8.field;
        r9.<init>(r0, r1);
        return r9;
    L_0x004a:
        r9 = new java.lang.IllegalArgumentException;
        r0 = "ModulusPolys do not have same ModulusGF field";
        r9.<init>(r0);
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.ec.ModulusPoly.add(com.google.zxing.pdf417.decoder.ec.ModulusPoly):com.google.zxing.pdf417.decoder.ec.ModulusPoly");
    }

    com.google.zxing.pdf417.decoder.ec.ModulusPoly[] divide(com.google.zxing.pdf417.decoder.ec.ModulusPoly r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x007a in {9, 11, 13, 15} preds:[]
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
        r7 = this;
        r0 = r7.field;
        r1 = r8.field;
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0072;
    L_0x000a:
        r0 = r8.isZero();
        if (r0 != 0) goto L_0x006a;
    L_0x0010:
        r0 = r7.field;
        r0 = r0.getZero();
        r1 = r8.getDegree();
        r1 = r8.getCoefficient(r1);
        r2 = r7.field;
        r1 = r2.inverse(r1);
        r2 = r0;
        r0 = r7;
    L_0x0026:
        r3 = r0.getDegree();
        r4 = r8.getDegree();
        if (r3 < r4) goto L_0x0060;
    L_0x0030:
        r3 = r0.isZero();
        if (r3 != 0) goto L_0x0060;
    L_0x0036:
        r3 = r0.getDegree();
        r4 = r8.getDegree();
        r3 = r3 - r4;
        r4 = r7.field;
        r5 = r0.getDegree();
        r5 = r0.getCoefficient(r5);
        r4 = r4.multiply(r5, r1);
        r5 = r8.multiplyByMonomial(r3, r4);
        r6 = r7.field;
        r3 = r6.buildMonomial(r3, r4);
        r2 = r2.add(r3);
        r0 = r0.subtract(r5);
        goto L_0x0026;
    L_0x0060:
        r8 = 2;
        r8 = new com.google.zxing.pdf417.decoder.ec.ModulusPoly[r8];
        r1 = 0;
        r8[r1] = r2;
        r1 = 1;
        r8[r1] = r0;
        return r8;
    L_0x006a:
        r8 = new java.lang.IllegalArgumentException;
        r0 = "Divide by 0";
        r8.<init>(r0);
        throw r8;
    L_0x0072:
        r8 = new java.lang.IllegalArgumentException;
        r0 = "ModulusPolys do not have same ModulusGF field";
        r8.<init>(r0);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.ec.ModulusPoly.divide(com.google.zxing.pdf417.decoder.ec.ModulusPoly):com.google.zxing.pdf417.decoder.ec.ModulusPoly[]");
    }

    com.google.zxing.pdf417.decoder.ec.ModulusPoly multiply(com.google.zxing.pdf417.decoder.ec.ModulusPoly r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:19:0x005b in {6, 11, 12, 14, 16, 18} preds:[]
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
        r12 = this;
        r0 = r12.field;
        r1 = r13.field;
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0053;
    L_0x000a:
        r0 = r12.isZero();
        if (r0 != 0) goto L_0x004c;
    L_0x0010:
        r0 = r13.isZero();
        if (r0 == 0) goto L_0x0017;
    L_0x0016:
        goto L_0x004c;
    L_0x0017:
        r0 = r12.coefficients;
        r1 = r0.length;
        r13 = r13.coefficients;
        r2 = r13.length;
        r3 = r1 + r2;
        r3 = r3 + -1;
        r3 = new int[r3];
        r4 = 0;
        r5 = 0;
    L_0x0025:
        if (r5 >= r1) goto L_0x0044;
    L_0x0027:
        r6 = r0[r5];
        r7 = 0;
    L_0x002a:
        if (r7 >= r2) goto L_0x0041;
    L_0x002c:
        r8 = r5 + r7;
        r9 = r12.field;
        r10 = r3[r8];
        r11 = r13[r7];
        r11 = r9.multiply(r6, r11);
        r9 = r9.add(r10, r11);
        r3[r8] = r9;
        r7 = r7 + 1;
        goto L_0x002a;
    L_0x0041:
        r5 = r5 + 1;
        goto L_0x0025;
    L_0x0044:
        r13 = new com.google.zxing.pdf417.decoder.ec.ModulusPoly;
        r0 = r12.field;
        r13.<init>(r0, r3);
        return r13;
    L_0x004c:
        r13 = r12.field;
        r13 = r13.getZero();
        return r13;
    L_0x0053:
        r13 = new java.lang.IllegalArgumentException;
        r0 = "ModulusPolys do not have same ModulusGF field";
        r13.<init>(r0);
        throw r13;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.ec.ModulusPoly.multiply(com.google.zxing.pdf417.decoder.ec.ModulusPoly):com.google.zxing.pdf417.decoder.ec.ModulusPoly");
    }

    com.google.zxing.pdf417.decoder.ec.ModulusPoly multiplyByMonomial(int r5, int r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:11:0x0031 in {3, 6, 8, 10} preds:[]
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
        r4 = this;
        if (r5 < 0) goto L_0x002b;
    L_0x0002:
        if (r6 != 0) goto L_0x000b;
    L_0x0004:
        r5 = r4.field;
        r5 = r5.getZero();
        return r5;
    L_0x000b:
        r0 = r4.coefficients;
        r0 = r0.length;
        r5 = r5 + r0;
        r5 = new int[r5];
        r1 = 0;
    L_0x0012:
        if (r1 >= r0) goto L_0x0023;
    L_0x0014:
        r2 = r4.field;
        r3 = r4.coefficients;
        r3 = r3[r1];
        r2 = r2.multiply(r3, r6);
        r5[r1] = r2;
        r1 = r1 + 1;
        goto L_0x0012;
    L_0x0023:
        r6 = new com.google.zxing.pdf417.decoder.ec.ModulusPoly;
        r0 = r4.field;
        r6.<init>(r0, r5);
        return r6;
    L_0x002b:
        r5 = new java.lang.IllegalArgumentException;
        r5.<init>();
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.ec.ModulusPoly.multiplyByMonomial(int, int):com.google.zxing.pdf417.decoder.ec.ModulusPoly");
    }

    int[] getCoefficients() {
        return this.coefficients;
    }

    int getDegree() {
        return this.coefficients.length - 1;
    }

    boolean isZero() {
        return this.coefficients[0] == 0;
    }

    int getCoefficient(int i) {
        int[] iArr = this.coefficients;
        return iArr[(iArr.length - 1) - i];
    }

    int evaluateAt(int i) {
        int i2 = 0;
        if (i == 0) {
            return getCoefficient(0);
        }
        int[] iArr = this.coefficients;
        int length = iArr.length;
        int i3 = 1;
        if (i == 1) {
            i = iArr.length;
            length = 0;
            while (i2 < i) {
                length = this.field.add(length, iArr[i2]);
                i2++;
            }
            return length;
        }
        i2 = iArr[0];
        while (i3 < length) {
            ModulusGF modulusGF = this.field;
            i2 = modulusGF.add(modulusGF.multiply(i, i2), this.coefficients[i3]);
            i3++;
        }
        return i2;
    }

    ModulusPoly subtract(ModulusPoly modulusPoly) {
        if (!this.field.equals(modulusPoly.field)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (modulusPoly.isZero()) {
            return this;
        } else {
            return add(modulusPoly.negative());
        }
    }

    ModulusPoly negative() {
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = this.field.subtract(0, this.coefficients[i]);
        }
        return new ModulusPoly(this.field, iArr);
    }

    ModulusPoly multiply(int i) {
        if (i == 0) {
            return this.field.getZero();
        }
        if (i == 1) {
            return this;
        }
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.field.multiply(this.coefficients[i2], i);
        }
        return new ModulusPoly(this.field, iArr);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getDegree() * 8);
        for (int degree = getDegree(); degree >= 0; degree--) {
            int coefficient = getCoefficient(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    stringBuilder.append(" - ");
                    coefficient = -coefficient;
                } else if (stringBuilder.length() > 0) {
                    stringBuilder.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    stringBuilder.append(coefficient);
                }
                if (degree != 0) {
                    if (degree == 1) {
                        stringBuilder.append('x');
                    } else {
                        stringBuilder.append("x^");
                        stringBuilder.append(degree);
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
