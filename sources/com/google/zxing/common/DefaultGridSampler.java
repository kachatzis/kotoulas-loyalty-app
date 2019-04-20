package com.google.zxing.common;

import com.google.zxing.NotFoundException;

public final class DefaultGridSampler extends GridSampler {
    public com.google.zxing.common.BitMatrix sampleGrid(com.google.zxing.common.BitMatrix r9, int r10, int r11, com.google.zxing.common.PerspectiveTransform r12) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x0053 in {6, 12, 13, 15, 16, 17, 19} preds:[]
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
        r8 = this;
        if (r10 <= 0) goto L_0x004e;
    L_0x0002:
        if (r11 <= 0) goto L_0x004e;
    L_0x0004:
        r0 = new com.google.zxing.common.BitMatrix;
        r0.<init>(r10, r11);
        r10 = r10 * 2;
        r10 = new float[r10];
        r1 = 0;
        r2 = 0;
    L_0x000f:
        if (r2 >= r11) goto L_0x004d;
    L_0x0011:
        r3 = r10.length;
        r4 = (float) r2;
        r5 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r4 = r4 + r5;
        r6 = 0;
    L_0x0017:
        if (r6 >= r3) goto L_0x0026;
    L_0x0019:
        r7 = r6 / 2;
        r7 = (float) r7;
        r7 = r7 + r5;
        r10[r6] = r7;
        r7 = r6 + 1;
        r10[r7] = r4;
        r6 = r6 + 2;
        goto L_0x0017;
    L_0x0026:
        r12.transformPoints(r10);
        com.google.zxing.common.GridSampler.checkAndNudgePoints(r9, r10);
        r4 = 0;
    L_0x002d:
        if (r4 >= r3) goto L_0x004a;
    L_0x002f:
        r5 = r10[r4];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0045 }
        r5 = (int) r5;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0045 }
        r6 = r4 + 1;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0045 }
        r6 = r10[r6];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0045 }
        r6 = (int) r6;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0045 }
        r5 = r9.get(r5, r6);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0045 }
        if (r5 == 0) goto L_0x0042;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0045 }
    L_0x003d:
        r5 = r4 / 2;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0045 }
        r0.set(r5, r2);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0045 }
    L_0x0042:
        r4 = r4 + 2;
        goto L_0x002d;
    L_0x0045:
        r9 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r9;
    L_0x004a:
        r2 = r2 + 1;
        goto L_0x000f;
    L_0x004d:
        return r0;
    L_0x004e:
        r9 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.DefaultGridSampler.sampleGrid(com.google.zxing.common.BitMatrix, int, int, com.google.zxing.common.PerspectiveTransform):com.google.zxing.common.BitMatrix");
    }

    public BitMatrix sampleGrid(BitMatrix bitMatrix, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws NotFoundException {
        DefaultGridSampler defaultGridSampler = this;
        BitMatrix bitMatrix2 = bitMatrix;
        int i3 = i;
        int i4 = i2;
        return sampleGrid(bitMatrix, i, i2, PerspectiveTransform.quadrilateralToQuadrilateral(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }
}
