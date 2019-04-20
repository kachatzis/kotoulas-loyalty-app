package com.google.zxing.qrcode;

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
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import com.google.zxing.qrcode.detector.Detector;
import java.util.Map;

public class QRCodeReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    private static com.google.zxing.common.BitMatrix extractPureBits(com.google.zxing.common.BitMatrix r11) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:43:0x00a2 in {8, 16, 18, 22, 24, 31, 32, 33, 34, 36, 38, 40, 42} preds:[]
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
        if (r0 == 0) goto L_0x009d;
    L_0x000a:
        if (r1 == 0) goto L_0x009d;
    L_0x000c:
        r2 = moduleSize(r0, r11);
        r3 = 1;
        r4 = r0[r3];
        r5 = r1[r3];
        r6 = 0;
        r0 = r0[r6];
        r1 = r1[r6];
        if (r0 >= r1) goto L_0x0098;
    L_0x001c:
        if (r4 >= r5) goto L_0x0098;
    L_0x001e:
        r7 = r5 - r4;
        r8 = r1 - r0;
        if (r7 == r8) goto L_0x0026;
    L_0x0024:
        r1 = r0 + r7;
    L_0x0026:
        r8 = r1 - r0;
        r8 = r8 + r3;
        r8 = (float) r8;
        r8 = r8 / r2;
        r8 = java.lang.Math.round(r8);
        r7 = r7 + r3;
        r3 = (float) r7;
        r3 = r3 / r2;
        r3 = java.lang.Math.round(r3);
        if (r8 <= 0) goto L_0x0093;
    L_0x0038:
        if (r3 <= 0) goto L_0x0093;
    L_0x003a:
        if (r3 != r8) goto L_0x008e;
    L_0x003c:
        r7 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = r2 / r7;
        r7 = (int) r7;
        r4 = r4 + r7;
        r0 = r0 + r7;
        r9 = r8 + -1;
        r9 = (float) r9;
        r9 = r9 * r2;
        r9 = (int) r9;
        r9 = r9 + r0;
        r9 = r9 - r1;
        if (r9 <= 0) goto L_0x0056;
    L_0x004d:
        if (r9 > r7) goto L_0x0051;
    L_0x004f:
        r0 = r0 - r9;
        goto L_0x0056;
    L_0x0051:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
    L_0x0056:
        r1 = r3 + -1;
        r1 = (float) r1;
        r1 = r1 * r2;
        r1 = (int) r1;
        r1 = r1 + r4;
        r1 = r1 - r5;
        if (r1 <= 0) goto L_0x0069;
    L_0x0060:
        if (r1 > r7) goto L_0x0064;
    L_0x0062:
        r4 = r4 - r1;
        goto L_0x0069;
    L_0x0064:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
    L_0x0069:
        r1 = new com.google.zxing.common.BitMatrix;
        r1.<init>(r8, r3);
        r5 = 0;
    L_0x006f:
        if (r5 >= r3) goto L_0x008d;
    L_0x0071:
        r7 = (float) r5;
        r7 = r7 * r2;
        r7 = (int) r7;
        r7 = r7 + r4;
        r9 = 0;
    L_0x0077:
        if (r9 >= r8) goto L_0x008a;
    L_0x0079:
        r10 = (float) r9;
        r10 = r10 * r2;
        r10 = (int) r10;
        r10 = r10 + r0;
        r10 = r11.get(r10, r7);
        if (r10 == 0) goto L_0x0087;
    L_0x0084:
        r1.set(r9, r5);
    L_0x0087:
        r9 = r9 + 1;
        goto L_0x0077;
    L_0x008a:
        r5 = r5 + 1;
        goto L_0x006f;
    L_0x008d:
        return r1;
    L_0x008e:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
    L_0x0093:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
    L_0x0098:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
    L_0x009d:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.QRCodeReader.extractPureBits(com.google.zxing.common.BitMatrix):com.google.zxing.common.BitMatrix");
    }

    private static float moduleSize(int[] r8, com.google.zxing.common.BitMatrix r9) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x0037 in {7, 8, 9, 13, 15} preds:[]
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
        r0 = r9.getHeight();
        r1 = r9.getWidth();
        r2 = 0;
        r3 = r8[r2];
        r4 = 1;
        r5 = r8[r4];
        r6 = 0;
    L_0x000f:
        if (r3 >= r1) goto L_0x0026;
    L_0x0011:
        if (r5 >= r0) goto L_0x0026;
    L_0x0013:
        r7 = r9.get(r3, r5);
        if (r4 == r7) goto L_0x0021;
    L_0x0019:
        r6 = r6 + 1;
        r7 = 5;
        if (r6 != r7) goto L_0x001f;
    L_0x001e:
        goto L_0x0026;
    L_0x001f:
        r4 = r4 ^ 1;
    L_0x0021:
        r3 = r3 + 1;
        r5 = r5 + 1;
        goto L_0x000f;
    L_0x0026:
        if (r3 == r1) goto L_0x0032;
    L_0x0028:
        if (r5 == r0) goto L_0x0032;
    L_0x002a:
        r8 = r8[r2];
        r3 = r3 - r8;
        r8 = (float) r3;
        r9 = 1088421888; // 0x40e00000 float:7.0 double:5.37751863E-315;
        r8 = r8 / r9;
        return r8;
    L_0x0032:
        r8 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.QRCodeReader.moduleSize(int[], com.google.zxing.common.BitMatrix):float");
    }

    public void reset() {
    }

    protected final Decoder getDecoder() {
        return this.decoder;
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return decode(binaryBitmap, null);
    }

    public final Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            binaryBitmap = new Detector(binaryBitmap.getBlackMatrix()).detect(map);
            Map<DecodeHintType, ?> decode = this.decoder.decode(binaryBitmap.getBits(), (Map) map);
            map = binaryBitmap.getPoints();
            binaryBitmap = decode;
        } else {
            binaryBitmap = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()), (Map) map);
            map = NO_POINTS;
        }
        if (binaryBitmap.getOther() instanceof QRCodeDecoderMetaData) {
            ((QRCodeDecoderMetaData) binaryBitmap.getOther()).applyMirroredCorrection(map);
        }
        Result result = new Result(binaryBitmap.getText(), binaryBitmap.getRawBytes(), map, BarcodeFormat.QR_CODE);
        map = binaryBitmap.getByteSegments();
        if (map != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
        }
        map = binaryBitmap.getECLevel();
        if (map != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
        }
        if (binaryBitmap.hasStructuredAppend() != null) {
            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(binaryBitmap.getStructuredAppendSequenceNumber()));
            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(binaryBitmap.getStructuredAppendParity()));
        }
        return result;
    }
}
