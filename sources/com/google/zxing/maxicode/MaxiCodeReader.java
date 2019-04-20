package com.google.zxing.maxicode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.maxicode.decoder.Decoder;
import java.util.Map;

public final class MaxiCodeReader implements Reader {
    private static final int MATRIX_HEIGHT = 33;
    private static final int MATRIX_WIDTH = 30;
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    private static com.google.zxing.common.BitMatrix extractPureBits(com.google.zxing.common.BitMatrix r14) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:14:0x004a in {8, 9, 10, 11, 13} preds:[]
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
        r0 = r14.getEnclosingRectangle();
        if (r0 == 0) goto L_0x0045;
    L_0x0006:
        r1 = 0;
        r2 = r0[r1];
        r3 = 1;
        r3 = r0[r3];
        r4 = 2;
        r5 = r0[r4];
        r6 = 3;
        r0 = r0[r6];
        r6 = new com.google.zxing.common.BitMatrix;
        r7 = 33;
        r8 = 30;
        r6.<init>(r8, r7);
        r9 = 0;
    L_0x001c:
        if (r9 >= r7) goto L_0x0044;
    L_0x001e:
        r10 = r9 * r0;
        r11 = r0 / 2;
        r10 = r10 + r11;
        r10 = r10 / r7;
        r10 = r10 + r3;
        r11 = 0;
    L_0x0026:
        if (r11 >= r8) goto L_0x0041;
    L_0x0028:
        r12 = r11 * r5;
        r13 = r5 / 2;
        r12 = r12 + r13;
        r13 = r9 & 1;
        r13 = r13 * r5;
        r13 = r13 / r4;
        r12 = r12 + r13;
        r12 = r12 / r8;
        r12 = r12 + r2;
        r12 = r14.get(r12, r10);
        if (r12 == 0) goto L_0x003e;
    L_0x003b:
        r6.set(r11, r9);
    L_0x003e:
        r11 = r11 + 1;
        goto L_0x0026;
    L_0x0041:
        r9 = r9 + 1;
        goto L_0x001c;
    L_0x0044:
        return r6;
    L_0x0045:
        r14 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r14;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.maxicode.MaxiCodeReader.extractPureBits(com.google.zxing.common.BitMatrix):com.google.zxing.common.BitMatrix");
    }

    public void reset() {
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return decode(binaryBitmap, null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            throw NotFoundException.getNotFoundInstance();
        }
        binaryBitmap = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()), map);
        Result result = new Result(binaryBitmap.getText(), binaryBitmap.getRawBytes(), NO_POINTS, BarcodeFormat.MAXICODE);
        binaryBitmap = binaryBitmap.getECLevel();
        if (binaryBitmap != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, binaryBitmap);
        }
        return result;
    }
}
