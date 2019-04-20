package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.aztec.encoder.Encoder;
import com.google.zxing.common.BitMatrix;
import java.nio.charset.Charset;
import java.util.Map;

public final class AztecWriter implements Writer {
    private static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");

    private static com.google.zxing.common.BitMatrix renderResult(com.google.zxing.aztec.encoder.AztecCode r9, int r10, int r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:14:0x004f in {8, 9, 10, 11, 13} preds:[]
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
        r9 = r9.getMatrix();
        if (r9 == 0) goto L_0x0049;
    L_0x0006:
        r0 = r9.getWidth();
        r1 = r9.getHeight();
        r10 = java.lang.Math.max(r10, r0);
        r11 = java.lang.Math.max(r11, r1);
        r2 = r10 / r0;
        r3 = r11 / r1;
        r2 = java.lang.Math.min(r2, r3);
        r3 = r0 * r2;
        r3 = r10 - r3;
        r3 = r3 / 2;
        r4 = r1 * r2;
        r4 = r11 - r4;
        r4 = r4 / 2;
        r5 = new com.google.zxing.common.BitMatrix;
        r5.<init>(r10, r11);
        r10 = 0;
        r11 = 0;
    L_0x0031:
        if (r11 >= r1) goto L_0x0048;
    L_0x0033:
        r7 = r3;
        r6 = 0;
    L_0x0035:
        if (r6 >= r0) goto L_0x0044;
    L_0x0037:
        r8 = r9.get(r6, r11);
        if (r8 == 0) goto L_0x0040;
    L_0x003d:
        r5.setRegion(r7, r4, r2, r2);
    L_0x0040:
        r6 = r6 + 1;
        r7 = r7 + r2;
        goto L_0x0035;
    L_0x0044:
        r11 = r11 + 1;
        r4 = r4 + r2;
        goto L_0x0031;
    L_0x0048:
        return r5;
    L_0x0049:
        r9 = new java.lang.IllegalStateException;
        r9.<init>();
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.AztecWriter.renderResult(com.google.zxing.aztec.encoder.AztecCode, int, int):com.google.zxing.common.BitMatrix");
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) {
        return encode(str, barcodeFormat, i, i2, null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        Number number;
        Charset charset;
        int i3;
        int i4;
        Number number2 = null;
        String str2 = map == null ? null : (String) map.get(EncodeHintType.CHARACTER_SET);
        if (map == null) {
            number = null;
        } else {
            number = (Number) map.get(EncodeHintType.ERROR_CORRECTION);
        }
        if (map != null) {
            number2 = (Number) map.get(EncodeHintType.AZTEC_LAYERS);
        }
        if (str2 == null) {
            charset = DEFAULT_CHARSET;
        } else {
            charset = Charset.forName(str2);
        }
        if (number == null) {
            i3 = 33;
        } else {
            i3 = number.intValue();
        }
        if (number2 == null) {
            i4 = 0;
        } else {
            i4 = number2.intValue();
        }
        return encode(str, barcodeFormat, i, i2, charset, i3, i4);
    }

    private static BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Charset charset, int i3, int i4) {
        if (barcodeFormat == BarcodeFormat.AZTEC) {
            return renderResult(Encoder.encode(str.getBytes(charset), i3, i4), i, i2);
        }
        i = new StringBuilder();
        i.append("Can only encode AZTEC, but got ");
        i.append(barcodeFormat);
        throw new IllegalArgumentException(i.toString());
    }
}
