package com.google.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;

public final class ReedSolomonEncoder {
    private final List<GenericGFPoly> cachedGenerators = new ArrayList();
    private final GenericGF field;

    public void encode(int[] r7, int r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:12:0x0048 in {5, 7, 9, 11} preds:[]
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
        r6 = this;
        if (r8 == 0) goto L_0x0040;
    L_0x0002:
        r0 = r7.length;
        r0 = r0 - r8;
        if (r0 <= 0) goto L_0x0038;
    L_0x0006:
        r1 = r6.buildGenerator(r8);
        r2 = new int[r0];
        r3 = 0;
        java.lang.System.arraycopy(r7, r3, r2, r3, r0);
        r4 = new com.google.zxing.common.reedsolomon.GenericGFPoly;
        r5 = r6.field;
        r4.<init>(r5, r2);
        r2 = 1;
        r4 = r4.multiplyByMonomial(r8, r2);
        r1 = r4.divide(r1);
        r1 = r1[r2];
        r1 = r1.getCoefficients();
        r2 = r1.length;
        r8 = r8 - r2;
        r2 = 0;
    L_0x0029:
        if (r2 >= r8) goto L_0x0032;
    L_0x002b:
        r4 = r0 + r2;
        r7[r4] = r3;
        r2 = r2 + 1;
        goto L_0x0029;
    L_0x0032:
        r0 = r0 + r8;
        r8 = r1.length;
        java.lang.System.arraycopy(r1, r3, r7, r0, r8);
        return;
    L_0x0038:
        r7 = new java.lang.IllegalArgumentException;
        r8 = "No data bytes provided";
        r7.<init>(r8);
        throw r7;
    L_0x0040:
        r7 = new java.lang.IllegalArgumentException;
        r8 = "No error correction bytes";
        r7.<init>(r8);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.reedsolomon.ReedSolomonEncoder.encode(int[], int):void");
    }

    public ReedSolomonEncoder(GenericGF genericGF) {
        this.field = genericGF;
        this.cachedGenerators.add(new GenericGFPoly(genericGF, new int[]{1}));
    }

    private GenericGFPoly buildGenerator(int i) {
        if (i >= this.cachedGenerators.size()) {
            List list = this.cachedGenerators;
            GenericGFPoly genericGFPoly = (GenericGFPoly) list.get(list.size() - 1);
            for (int size = this.cachedGenerators.size(); size <= i; size++) {
                genericGFPoly = genericGFPoly.multiply(new GenericGFPoly(this.field, new int[]{1, r4.exp((size - 1) + this.field.getGeneratorBase())}));
                this.cachedGenerators.add(genericGFPoly);
            }
        }
        return (GenericGFPoly) this.cachedGenerators.get(i);
    }
}
