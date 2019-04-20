package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class ITFWriter extends OneDimensionalCodeWriter {
    private static final int[] END_PATTERN = new int[]{3, 1, 1};
    private static final int[] START_PATTERN = new int[]{1, 1, 1, 1};

    public boolean[] encode(java.lang.String r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:17:0x007d in {9, 10, 12, 14, 16} preds:[]
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
        r12 = this;
        r0 = r13.length();
        r1 = r0 % 2;
        if (r1 != 0) goto L_0x0075;
    L_0x0008:
        r1 = 80;
        if (r0 > r1) goto L_0x005e;
    L_0x000c:
        r1 = r0 * 9;
        r1 = r1 + 9;
        r1 = new boolean[r1];
        r2 = START_PATTERN;
        r3 = 0;
        r4 = 1;
        r2 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r1, r3, r2, r4);
        r5 = r2;
        r2 = 0;
    L_0x001c:
        if (r2 >= r0) goto L_0x0058;
    L_0x001e:
        r6 = r13.charAt(r2);
        r7 = 10;
        r6 = java.lang.Character.digit(r6, r7);
        r8 = r2 + 1;
        r8 = r13.charAt(r8);
        r7 = java.lang.Character.digit(r8, r7);
        r8 = 18;
        r8 = new int[r8];
        r9 = 0;
    L_0x0037:
        r10 = 5;
        if (r9 >= r10) goto L_0x0050;
    L_0x003a:
        r10 = r9 * 2;
        r11 = com.google.zxing.oned.ITFReader.PATTERNS;
        r11 = r11[r6];
        r11 = r11[r9];
        r8[r10] = r11;
        r10 = r10 + r4;
        r11 = com.google.zxing.oned.ITFReader.PATTERNS;
        r11 = r11[r7];
        r11 = r11[r9];
        r8[r10] = r11;
        r9 = r9 + 1;
        goto L_0x0037;
    L_0x0050:
        r6 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r1, r5, r8, r4);
        r5 = r5 + r6;
        r2 = r2 + 2;
        goto L_0x001c;
    L_0x0058:
        r13 = END_PATTERN;
        com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r1, r5, r13, r4);
        return r1;
    L_0x005e:
        r13 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Requested contents should be less than 80 digits long, but got ";
        r1.append(r2);
        r1.append(r0);
        r0 = r1.toString();
        r13.<init>(r0);
        throw r13;
    L_0x0075:
        r13 = new java.lang.IllegalArgumentException;
        r0 = "The lenght of the input should be even";
        r13.<init>(r0);
        throw r13;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.ITFWriter.encode(java.lang.String):boolean[]");
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.ITF) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        i = new StringBuilder();
        i.append("Can only encode ITF, but got ");
        i.append(barcodeFormat);
        throw new IllegalArgumentException(i.toString());
    }
}
