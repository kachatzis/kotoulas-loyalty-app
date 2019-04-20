package com.google.zxing.multi.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.qrcode.QRCodeReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class QRCodeMultiReader extends QRCodeReader implements MultipleBarcodeReader {
    private static final Result[] EMPTY_RESULT_ARRAY = new Result[0];
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];

    private static final class SAComparator implements Comparator<Result>, Serializable {
        private SAComparator() {
        }

        public int compare(Result result, Result result2) {
            result = ((Integer) result.getResultMetadata().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
            result2 = ((Integer) result2.getResultMetadata().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
            if (result < result2) {
                return -1;
            }
            return result > result2 ? 1 : null;
        }
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) throws NotFoundException {
        return decodeMultiple(binaryBitmap, null);
    }

    public com.google.zxing.Result[] decodeMultiple(com.google.zxing.BinaryBitmap r10, java.util.Map<com.google.zxing.DecodeHintType, ?> r11) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r9 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = new com.google.zxing.multi.qrcode.detector.MultiDetector;
        r10 = r10.getBlackMatrix();
        r1.<init>(r10);
        r10 = r1.detectMulti(r11);
        r1 = r10.length;
        r2 = 0;
    L_0x0014:
        if (r2 >= r1) goto L_0x0084;
    L_0x0016:
        r3 = r10[r2];
        r4 = r9.getDecoder();	 Catch:{ ReaderException -> 0x0081 }
        r5 = r3.getBits();	 Catch:{ ReaderException -> 0x0081 }
        r4 = r4.decode(r5, r11);	 Catch:{ ReaderException -> 0x0081 }
        r3 = r3.getPoints();	 Catch:{ ReaderException -> 0x0081 }
        r5 = r4.getOther();	 Catch:{ ReaderException -> 0x0081 }
        r5 = r5 instanceof com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData;	 Catch:{ ReaderException -> 0x0081 }
        if (r5 == 0) goto L_0x0039;	 Catch:{ ReaderException -> 0x0081 }
    L_0x0030:
        r5 = r4.getOther();	 Catch:{ ReaderException -> 0x0081 }
        r5 = (com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData) r5;	 Catch:{ ReaderException -> 0x0081 }
        r5.applyMirroredCorrection(r3);	 Catch:{ ReaderException -> 0x0081 }
    L_0x0039:
        r5 = new com.google.zxing.Result;	 Catch:{ ReaderException -> 0x0081 }
        r6 = r4.getText();	 Catch:{ ReaderException -> 0x0081 }
        r7 = r4.getRawBytes();	 Catch:{ ReaderException -> 0x0081 }
        r8 = com.google.zxing.BarcodeFormat.QR_CODE;	 Catch:{ ReaderException -> 0x0081 }
        r5.<init>(r6, r7, r3, r8);	 Catch:{ ReaderException -> 0x0081 }
        r3 = r4.getByteSegments();	 Catch:{ ReaderException -> 0x0081 }
        if (r3 == 0) goto L_0x0053;	 Catch:{ ReaderException -> 0x0081 }
    L_0x004e:
        r6 = com.google.zxing.ResultMetadataType.BYTE_SEGMENTS;	 Catch:{ ReaderException -> 0x0081 }
        r5.putMetadata(r6, r3);	 Catch:{ ReaderException -> 0x0081 }
    L_0x0053:
        r3 = r4.getECLevel();	 Catch:{ ReaderException -> 0x0081 }
        if (r3 == 0) goto L_0x005e;	 Catch:{ ReaderException -> 0x0081 }
    L_0x0059:
        r6 = com.google.zxing.ResultMetadataType.ERROR_CORRECTION_LEVEL;	 Catch:{ ReaderException -> 0x0081 }
        r5.putMetadata(r6, r3);	 Catch:{ ReaderException -> 0x0081 }
    L_0x005e:
        r3 = r4.hasStructuredAppend();	 Catch:{ ReaderException -> 0x0081 }
        if (r3 == 0) goto L_0x007e;	 Catch:{ ReaderException -> 0x0081 }
    L_0x0064:
        r3 = com.google.zxing.ResultMetadataType.STRUCTURED_APPEND_SEQUENCE;	 Catch:{ ReaderException -> 0x0081 }
        r6 = r4.getStructuredAppendSequenceNumber();	 Catch:{ ReaderException -> 0x0081 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ ReaderException -> 0x0081 }
        r5.putMetadata(r3, r6);	 Catch:{ ReaderException -> 0x0081 }
        r3 = com.google.zxing.ResultMetadataType.STRUCTURED_APPEND_PARITY;	 Catch:{ ReaderException -> 0x0081 }
        r4 = r4.getStructuredAppendParity();	 Catch:{ ReaderException -> 0x0081 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ ReaderException -> 0x0081 }
        r5.putMetadata(r3, r4);	 Catch:{ ReaderException -> 0x0081 }
    L_0x007e:
        r0.add(r5);	 Catch:{ ReaderException -> 0x0081 }
    L_0x0081:
        r2 = r2 + 1;
        goto L_0x0014;
    L_0x0084:
        r10 = r0.isEmpty();
        if (r10 == 0) goto L_0x008d;
    L_0x008a:
        r10 = EMPTY_RESULT_ARRAY;
        return r10;
    L_0x008d:
        r10 = processStructuredAppend(r0);
        r11 = r10.size();
        r11 = new com.google.zxing.Result[r11];
        r10 = r10.toArray(r11);
        r10 = (com.google.zxing.Result[]) r10;
        return r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.multi.qrcode.QRCodeMultiReader.decodeMultiple(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result[]");
    }

    private static List<Result> processStructuredAppend(List<Result> list) {
        Object obj;
        for (Result resultMetadata : list) {
            Result resultMetadata2;
            if (resultMetadata2.getResultMetadata().containsKey(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj == null) {
            return list;
        }
        List<Result> arrayList = new ArrayList();
        List<Result> arrayList2 = new ArrayList();
        for (Result result : list) {
            arrayList.add(result);
            if (result.getResultMetadata().containsKey(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)) {
                arrayList2.add(result);
            }
        }
        Collections.sort(arrayList2, new SAComparator());
        list = new StringBuilder();
        int i = 0;
        int i2 = 0;
        for (Result result2 : arrayList2) {
            list.append(result2.getText());
            i += result2.getRawBytes().length;
            if (result2.getResultMetadata().containsKey(ResultMetadataType.BYTE_SEGMENTS)) {
                for (byte[] length : (Iterable) result2.getResultMetadata().get(ResultMetadataType.BYTE_SEGMENTS)) {
                    i2 += length.length;
                }
            }
        }
        Object obj2 = new byte[i];
        Object obj3 = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        for (Result result3 : arrayList2) {
            System.arraycopy(result3.getRawBytes(), 0, obj2, i3, result3.getRawBytes().length);
            i3 += result3.getRawBytes().length;
            if (result3.getResultMetadata().containsKey(ResultMetadataType.BYTE_SEGMENTS)) {
                for (byte[] bArr : (Iterable) result3.getResultMetadata().get(ResultMetadataType.BYTE_SEGMENTS)) {
                    System.arraycopy(bArr, 0, obj3, i4, bArr.length);
                    i4 += bArr.length;
                }
            }
        }
        resultMetadata2 = new Result(list.toString(), obj2, NO_POINTS, BarcodeFormat.QR_CODE);
        if (i2 > 0) {
            list = new ArrayList();
            list.add(obj3);
            resultMetadata2.putMetadata(ResultMetadataType.BYTE_SEGMENTS, list);
        }
        arrayList.add(resultMetadata2);
        return arrayList;
    }
}
