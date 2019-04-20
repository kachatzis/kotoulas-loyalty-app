package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class Code39Writer extends OneDimensionalCodeWriter {
    public boolean[] encode(java.lang.String r12) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:19:0x00a9 in {8, 9, 11, 14, 16, 18} preds:[]
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
        r11 = this;
        r0 = r12.length();
        r1 = 80;
        if (r0 > r1) goto L_0x0092;
    L_0x0008:
        r1 = 9;
        r1 = new int[r1];
        r2 = r0 + 25;
        r3 = 0;
        r4 = r2;
        r2 = 0;
    L_0x0011:
        if (r2 >= r0) goto L_0x004c;
    L_0x0013:
        r5 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
        r6 = r12.charAt(r2);
        r5 = r5.indexOf(r6);
        if (r5 < 0) goto L_0x0035;
    L_0x001f:
        r6 = com.google.zxing.oned.Code39Reader.CHARACTER_ENCODINGS;
        r5 = r6[r5];
        toIntArray(r5, r1);
        r5 = r1.length;
        r6 = r4;
        r4 = 0;
    L_0x0029:
        if (r4 >= r5) goto L_0x0031;
    L_0x002b:
        r7 = r1[r4];
        r6 = r6 + r7;
        r4 = r4 + 1;
        goto L_0x0029;
    L_0x0031:
        r2 = r2 + 1;
        r4 = r6;
        goto L_0x0011;
    L_0x0035:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Bad contents: ";
        r1.append(r2);
        r1.append(r12);
        r12 = r1.toString();
        r0.<init>(r12);
        throw r0;
    L_0x004c:
        r2 = new boolean[r4];
        r4 = com.google.zxing.oned.Code39Reader.CHARACTER_ENCODINGS;
        r5 = 39;
        r4 = r4[r5];
        toIntArray(r4, r1);
        r4 = 1;
        r6 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r2, r3, r1, r4);
        r7 = new int[r4];
        r7[r3] = r4;
        r8 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r2, r6, r7, r3);
        r6 = r6 + r8;
        r8 = r6;
        r6 = 0;
    L_0x0067:
        if (r6 >= r0) goto L_0x0087;
    L_0x0069:
        r9 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
        r10 = r12.charAt(r6);
        r9 = r9.indexOf(r10);
        r10 = com.google.zxing.oned.Code39Reader.CHARACTER_ENCODINGS;
        r9 = r10[r9];
        toIntArray(r9, r1);
        r9 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r2, r8, r1, r4);
        r8 = r8 + r9;
        r9 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r2, r8, r7, r3);
        r8 = r8 + r9;
        r6 = r6 + 1;
        goto L_0x0067;
    L_0x0087:
        r12 = com.google.zxing.oned.Code39Reader.CHARACTER_ENCODINGS;
        r12 = r12[r5];
        toIntArray(r12, r1);
        com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r2, r8, r1, r4);
        return r2;
    L_0x0092:
        r12 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Requested contents should be less than 80 digits long, but got ";
        r1.append(r2);
        r1.append(r0);
        r0 = r1.toString();
        r12.<init>(r0);
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code39Writer.encode(java.lang.String):boolean[]");
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_39) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        i = new StringBuilder();
        i.append("Can only encode CODE_39, but got ");
        i.append(barcodeFormat);
        throw new IllegalArgumentException(i.toString());
    }

    private static void toIntArray(int i, int[] iArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            int i3 = 1;
            if (((1 << (8 - i2)) & i) != 0) {
                i3 = 2;
            }
            iArr[i2] = i3;
        }
    }
}
