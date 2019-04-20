package com.google.zxing.datamatrix;

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
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;
import java.util.Map;

public final class DataMatrixReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    private static com.google.zxing.common.BitMatrix extractPureBits(com.google.zxing.common.BitMatrix r11) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x0053 in {12, 13, 14, 15, 17, 19} preds:[]
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
        r0 = r11.getTopLeftOnBit();
        r1 = r11.getBottomRightOnBit();
        if (r0 == 0) goto L_0x004e;
    L_0x000a:
        if (r1 == 0) goto L_0x004e;
    L_0x000c:
        r2 = moduleSize(r0, r11);
        r3 = 1;
        r4 = r0[r3];
        r5 = r1[r3];
        r6 = 0;
        r0 = r0[r6];
        r1 = r1[r6];
        r1 = r1 - r0;
        r1 = r1 + r3;
        r1 = r1 / r2;
        r5 = r5 - r4;
        r5 = r5 + r3;
        r5 = r5 / r2;
        if (r1 <= 0) goto L_0x0049;
    L_0x0022:
        if (r5 <= 0) goto L_0x0049;
    L_0x0024:
        r3 = r2 / 2;
        r4 = r4 + r3;
        r0 = r0 + r3;
        r3 = new com.google.zxing.common.BitMatrix;
        r3.<init>(r1, r5);
        r7 = 0;
    L_0x002e:
        if (r7 >= r5) goto L_0x0048;
    L_0x0030:
        r8 = r7 * r2;
        r8 = r8 + r4;
        r9 = 0;
    L_0x0034:
        if (r9 >= r1) goto L_0x0045;
    L_0x0036:
        r10 = r9 * r2;
        r10 = r10 + r0;
        r10 = r11.get(r10, r8);
        if (r10 == 0) goto L_0x0042;
    L_0x003f:
        r3.set(r9, r7);
    L_0x0042:
        r9 = r9 + 1;
        goto L_0x0034;
    L_0x0045:
        r7 = r7 + 1;
        goto L_0x002e;
    L_0x0048:
        return r3;
    L_0x0049:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
    L_0x004e:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.DataMatrixReader.extractPureBits(com.google.zxing.common.BitMatrix):com.google.zxing.common.BitMatrix");
    }

    private static int moduleSize(int[] r5, com.google.zxing.common.BitMatrix r6) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:13:0x0027 in {4, 8, 10, 12} preds:[]
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
        r0 = r6.getWidth();
        r1 = 0;
        r2 = r5[r1];
        r3 = 1;
        r3 = r5[r3];
    L_0x000a:
        if (r2 >= r0) goto L_0x0015;
    L_0x000c:
        r4 = r6.get(r2, r3);
        if (r4 == 0) goto L_0x0015;
    L_0x0012:
        r2 = r2 + 1;
        goto L_0x000a;
    L_0x0015:
        if (r2 == r0) goto L_0x0022;
    L_0x0017:
        r5 = r5[r1];
        r2 = r2 - r5;
        if (r2 == 0) goto L_0x001d;
    L_0x001c:
        return r2;
    L_0x001d:
        r5 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r5;
    L_0x0022:
        r5 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.DataMatrixReader.moduleSize(int[], com.google.zxing.common.BitMatrix):int");
    }

    public void reset() {
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return decode(binaryBitmap, null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        if (map == null || map.containsKey(DecodeHintType.PURE_BARCODE) == null) {
            binaryBitmap = new Detector(binaryBitmap.getBlackMatrix()).detect();
            Map<DecodeHintType, ?> decode = this.decoder.decode(binaryBitmap.getBits());
            map = binaryBitmap.getPoints();
            binaryBitmap = decode;
        } else {
            binaryBitmap = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()));
            map = NO_POINTS;
        }
        Result result = new Result(binaryBitmap.getText(), binaryBitmap.getRawBytes(), map, BarcodeFormat.DATA_MATRIX);
        map = binaryBitmap.getByteSegments();
        if (map != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
        }
        binaryBitmap = binaryBitmap.getECLevel();
        if (binaryBitmap != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, binaryBitmap);
        }
        return result;
    }
}
