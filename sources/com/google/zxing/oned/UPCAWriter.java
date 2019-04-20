package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class UPCAWriter implements Writer {
    private final EAN13Writer subWriter = new EAN13Writer();

    private static java.lang.String preencode(java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x0063 in {6, 7, 8, 9, 13, 15} preds:[]
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
        r0 = r6.length();
        r1 = 48;
        r2 = 11;
        if (r0 != r2) goto L_0x0034;
    L_0x000a:
        r0 = 0;
        r3 = 0;
    L_0x000c:
        if (r0 >= r2) goto L_0x0020;
    L_0x000e:
        r4 = r6.charAt(r0);
        r4 = r4 - r1;
        r5 = r0 % 2;
        if (r5 != 0) goto L_0x0019;
    L_0x0017:
        r5 = 3;
        goto L_0x001a;
    L_0x0019:
        r5 = 1;
    L_0x001a:
        r4 = r4 * r5;
        r3 = r3 + r4;
        r0 = r0 + 1;
        goto L_0x000c;
    L_0x0020:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r6);
        r6 = 1000 - r3;
        r6 = r6 % 10;
        r0.append(r6);
        r6 = r0.toString();
        goto L_0x0038;
    L_0x0034:
        r2 = 12;
        if (r0 != r2) goto L_0x0048;
    L_0x0038:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r1);
        r0.append(r6);
        r6 = r0.toString();
        return r6;
    L_0x0048:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Requested contents should be 11 or 12 digits long, but got ";
        r1.append(r2);
        r6 = r6.length();
        r1.append(r6);
        r6 = r1.toString();
        r0.<init>(r6);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCAWriter.preencode(java.lang.String):java.lang.String");
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.UPC_A) {
            return this.subWriter.encode(preencode(str), BarcodeFormat.EAN_13, i, i2, map);
        }
        i = new StringBuilder();
        i.append("Can only encode UPC-A, but got ");
        i.append(barcodeFormat);
        throw new IllegalArgumentException(i.toString());
    }
}
