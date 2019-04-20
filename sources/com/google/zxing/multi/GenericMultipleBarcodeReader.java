package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    private static final int MAX_DEPTH = 4;
    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader delegate;

    public GenericMultipleBarcodeReader(Reader reader) {
        this.delegate = reader;
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) throws NotFoundException {
        return decodeMultiple(binaryBitmap, null);
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        List arrayList = new ArrayList();
        doDecodeMultiple(binaryBitmap, map, arrayList, 0, 0, 0);
        if (arrayList.isEmpty() == null) {
            return (Result[]) arrayList.toArray(new Result[arrayList.size()]);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void doDecodeMultiple(com.google.zxing.BinaryBitmap r20, java.util.Map<com.google.zxing.DecodeHintType, ?> r21, java.util.List<com.google.zxing.Result> r22, int r23, int r24, int r25) {
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
        r19 = this;
        r0 = r20;
        r8 = r23;
        r9 = r24;
        r10 = r25;
        r1 = 4;
        if (r10 <= r1) goto L_0x000c;
    L_0x000b:
        return;
    L_0x000c:
        r11 = r19;
        r1 = r11.delegate;	 Catch:{ ReaderException -> 0x010b }
        r12 = r21;	 Catch:{ ReaderException -> 0x010b }
        r1 = r1.decode(r0, r12);	 Catch:{ ReaderException -> 0x010b }
        r2 = r22.iterator();
    L_0x001a:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x0036;
    L_0x0020:
        r3 = r2.next();
        r3 = (com.google.zxing.Result) r3;
        r3 = r3.getText();
        r4 = r1.getText();
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x001a;
    L_0x0034:
        r2 = 1;
        goto L_0x0037;
    L_0x0036:
        r2 = 0;
    L_0x0037:
        if (r2 != 0) goto L_0x0043;
    L_0x0039:
        r2 = translateResultPoints(r1, r8, r9);
        r15 = r22;
        r15.add(r2);
        goto L_0x0045;
    L_0x0043:
        r15 = r22;
    L_0x0045:
        r1 = r1.getResultPoints();
        if (r1 == 0) goto L_0x010a;
    L_0x004b:
        r2 = r1.length;
        if (r2 != 0) goto L_0x0050;
    L_0x004e:
        goto L_0x010a;
    L_0x0050:
        r7 = r20.getWidth();
        r6 = r20.getHeight();
        r2 = (float) r7;
        r3 = (float) r6;
        r4 = r1.length;
        r5 = 0;
        r5 = r3;
        r13 = 0;
        r14 = 0;
        r3 = r2;
        r2 = 0;
    L_0x0061:
        if (r2 >= r4) goto L_0x008b;
    L_0x0063:
        r16 = r1[r2];
        if (r16 != 0) goto L_0x0068;
    L_0x0067:
        goto L_0x0088;
    L_0x0068:
        r17 = r16.getX();
        r16 = r16.getY();
        r18 = (r17 > r3 ? 1 : (r17 == r3 ? 0 : -1));
        if (r18 >= 0) goto L_0x0076;
    L_0x0074:
        r3 = r17;
    L_0x0076:
        r18 = (r16 > r5 ? 1 : (r16 == r5 ? 0 : -1));
        if (r18 >= 0) goto L_0x007c;
    L_0x007a:
        r5 = r16;
    L_0x007c:
        r18 = (r17 > r13 ? 1 : (r17 == r13 ? 0 : -1));
        if (r18 <= 0) goto L_0x0082;
    L_0x0080:
        r13 = r17;
    L_0x0082:
        r17 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1));
        if (r17 <= 0) goto L_0x0088;
    L_0x0086:
        r14 = r16;
    L_0x0088:
        r2 = r2 + 1;
        goto L_0x0061;
    L_0x008b:
        r16 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r1 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1));
        if (r1 <= 0) goto L_0x00ad;
    L_0x0091:
        r1 = (int) r3;
        r2 = 0;
        r3 = r0.crop(r2, r2, r1, r6);
        r17 = r10 + 1;
        r1 = r19;
        r2 = r3;
        r3 = r21;
        r4 = r22;
        r11 = r5;
        r5 = r23;
        r12 = r6;
        r6 = r24;
        r15 = r7;
        r7 = r17;
        r1.doDecodeMultiple(r2, r3, r4, r5, r6, r7);
        goto L_0x00b0;
    L_0x00ad:
        r11 = r5;
        r12 = r6;
        r15 = r7;
    L_0x00b0:
        r1 = (r11 > r16 ? 1 : (r11 == r16 ? 0 : -1));
        if (r1 <= 0) goto L_0x00ca;
    L_0x00b4:
        r1 = (int) r11;
        r2 = 0;
        r3 = r0.crop(r2, r2, r15, r1);
        r7 = r10 + 1;
        r1 = r19;
        r2 = r3;
        r3 = r21;
        r4 = r22;
        r5 = r23;
        r6 = r24;
        r1.doDecodeMultiple(r2, r3, r4, r5, r6, r7);
    L_0x00ca:
        r7 = r15 + -100;
        r1 = (float) r7;
        r1 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1));
        if (r1 >= 0) goto L_0x00e9;
    L_0x00d1:
        r1 = (int) r13;
        r7 = r15 - r1;
        r2 = 0;
        r3 = r0.crop(r1, r2, r7, r12);
        r5 = r8 + r1;
        r7 = r10 + 1;
        r1 = r19;
        r2 = r3;
        r3 = r21;
        r4 = r22;
        r6 = r24;
        r1.doDecodeMultiple(r2, r3, r4, r5, r6, r7);
    L_0x00e9:
        r6 = r12 + -100;
        r1 = (float) r6;
        r1 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1));
        if (r1 >= 0) goto L_0x0109;
    L_0x00f0:
        r1 = (int) r14;
        r6 = r12 - r1;
        r2 = 0;
        r2 = r0.crop(r2, r1, r15, r6);
        r5 = r9 + r1;
        r0 = 1;
        r6 = r10 + 1;
        r0 = r19;
        r1 = r2;
        r2 = r21;
        r3 = r22;
        r4 = r23;
        r0.doDecodeMultiple(r1, r2, r3, r4, r5, r6);
    L_0x0109:
        return;
    L_0x010a:
        return;
    L_0x010b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.multi.GenericMultipleBarcodeReader.doDecodeMultiple(com.google.zxing.BinaryBitmap, java.util.Map, java.util.List, int, int, int):void");
    }

    private static Result translateResultPoints(Result result, int i, int i2) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints == null) {
            return result;
        }
        ResultPoint[] resultPointArr = new ResultPoint[resultPoints.length];
        for (int i3 = 0; i3 < resultPoints.length; i3++) {
            ResultPoint resultPoint = resultPoints[i3];
            if (resultPoint != null) {
                resultPointArr[i3] = new ResultPoint(resultPoint.getX() + ((float) i), resultPoint.getY() + ((float) i2));
            }
        }
        i = new Result(result.getText(), result.getRawBytes(), resultPointArr, result.getBarcodeFormat());
        i.putAllMetadata(result.getResultMetadata());
        return i;
    }
}
