package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class EAN8Writer extends UPCEANWriter {
    private static final int CODE_WIDTH = 67;

    public boolean[] encode(java.lang.String r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:14:0x0070 in {5, 9, 11, 13} preds:[]
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
        r0 = r8.length();
        r1 = 8;
        if (r0 != r1) goto L_0x0055;
    L_0x0008:
        r0 = 67;
        r0 = new boolean[r0];
        r1 = com.google.zxing.oned.UPCEANReader.START_END_PATTERN;
        r2 = 0;
        r3 = 1;
        r1 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r0, r2, r1, r3);
        r1 = r1 + r2;
        r4 = r1;
        r1 = 0;
    L_0x0017:
        r5 = 3;
        if (r1 > r5) goto L_0x002f;
    L_0x001a:
        r5 = r1 + 1;
        r1 = r8.substring(r1, r5);
        r1 = java.lang.Integer.parseInt(r1);
        r6 = com.google.zxing.oned.UPCEANReader.L_PATTERNS;
        r1 = r6[r1];
        r1 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r0, r4, r1, r2);
        r4 = r4 + r1;
        r1 = r5;
        goto L_0x0017;
    L_0x002f:
        r1 = com.google.zxing.oned.UPCEANReader.MIDDLE_PATTERN;
        r1 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r0, r4, r1, r2);
        r4 = r4 + r1;
        r1 = 4;
    L_0x0037:
        r2 = 7;
        if (r1 > r2) goto L_0x004f;
    L_0x003a:
        r2 = r1 + 1;
        r1 = r8.substring(r1, r2);
        r1 = java.lang.Integer.parseInt(r1);
        r5 = com.google.zxing.oned.UPCEANReader.L_PATTERNS;
        r1 = r5[r1];
        r1 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r0, r4, r1, r3);
        r4 = r4 + r1;
        r1 = r2;
        goto L_0x0037;
    L_0x004f:
        r8 = com.google.zxing.oned.UPCEANReader.START_END_PATTERN;
        com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r0, r4, r8, r3);
        return r0;
    L_0x0055:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Requested contents should be 8 digits long, but got ";
        r1.append(r2);
        r8 = r8.length();
        r1.append(r8);
        r8 = r1.toString();
        r0.<init>(r8);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.EAN8Writer.encode(java.lang.String):boolean[]");
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.EAN_8) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        i = new StringBuilder();
        i.append("Can only encode EAN_8, but got ");
        i.append(barcodeFormat);
        throw new IllegalArgumentException(i.toString());
    }
}
