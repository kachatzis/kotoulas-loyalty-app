package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import java.util.Map;

public final class QRCodeWriter implements Writer {
    private static final int QUIET_ZONE_SIZE = 4;

    private static com.google.zxing.common.BitMatrix renderResult(com.google.zxing.qrcode.encoder.QRCode r9, int r10, int r11, int r12) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:14:0x0055 in {8, 9, 10, 11, 13} preds:[]
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
        if (r9 == 0) goto L_0x004f;
    L_0x0006:
        r0 = r9.getWidth();
        r1 = r9.getHeight();
        r12 = r12 * 2;
        r2 = r0 + r12;
        r12 = r12 + r1;
        r10 = java.lang.Math.max(r10, r2);
        r11 = java.lang.Math.max(r11, r12);
        r2 = r10 / r2;
        r12 = r11 / r12;
        r12 = java.lang.Math.min(r2, r12);
        r2 = r0 * r12;
        r2 = r10 - r2;
        r2 = r2 / 2;
        r3 = r1 * r12;
        r3 = r11 - r3;
        r3 = r3 / 2;
        r4 = new com.google.zxing.common.BitMatrix;
        r4.<init>(r10, r11);
        r10 = 0;
        r11 = 0;
    L_0x0036:
        if (r11 >= r1) goto L_0x004e;
    L_0x0038:
        r6 = r2;
        r5 = 0;
    L_0x003a:
        if (r5 >= r0) goto L_0x004a;
    L_0x003c:
        r7 = r9.get(r5, r11);
        r8 = 1;
        if (r7 != r8) goto L_0x0046;
    L_0x0043:
        r4.setRegion(r6, r3, r12, r12);
    L_0x0046:
        r5 = r5 + 1;
        r6 = r6 + r12;
        goto L_0x003a;
    L_0x004a:
        r11 = r11 + 1;
        r3 = r3 + r12;
        goto L_0x0036;
    L_0x004e:
        return r4;
    L_0x004f:
        r9 = new java.lang.IllegalStateException;
        r9.<init>();
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.QRCodeWriter.renderResult(com.google.zxing.qrcode.encoder.QRCode, int, int, int):com.google.zxing.common.BitMatrix");
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.QR_CODE) {
            i = new StringBuilder();
            i.append("Can only encode QR_CODE, but got ");
            i.append(barcodeFormat);
            throw new IllegalArgumentException(i.toString());
        } else if (i < 0 || i2 < 0) {
            barcodeFormat = new StringBuilder();
            barcodeFormat.append("Requested dimensions are too small: ");
            barcodeFormat.append(i);
            barcodeFormat.append(120);
            barcodeFormat.append(i2);
            throw new IllegalArgumentException(barcodeFormat.toString());
        } else {
            barcodeFormat = ErrorCorrectionLevel.L;
            int i3 = 4;
            if (map != null) {
                Enum enumR = (ErrorCorrectionLevel) map.get(EncodeHintType.ERROR_CORRECTION);
                if (enumR != null) {
                    barcodeFormat = enumR;
                }
                Integer num = (Integer) map.get(EncodeHintType.MARGIN);
                if (num != null) {
                    i3 = num.intValue();
                }
            }
            return renderResult(Encoder.encode(str, barcodeFormat, map), i, i2, i3);
        }
    }
}
