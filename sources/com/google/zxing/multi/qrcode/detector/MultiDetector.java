package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.detector.Detector;

public final class MultiDetector extends Detector {
    private static final DetectorResult[] EMPTY_DETECTOR_RESULTS = new DetectorResult[0];

    public com.google.zxing.common.DetectorResult[] detectMulti(java.util.Map<com.google.zxing.DecodeHintType, ?> r5) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x004c in {2, 3, 10, 11, 15, 17, 19} preds:[]
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
        r4 = this;
        r0 = r4.getImage();
        if (r5 != 0) goto L_0x0008;
    L_0x0006:
        r1 = 0;
        goto L_0x0010;
    L_0x0008:
        r1 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        r1 = r5.get(r1);
        r1 = (com.google.zxing.ResultPointCallback) r1;
    L_0x0010:
        r2 = new com.google.zxing.multi.qrcode.detector.MultiFinderPatternFinder;
        r2.<init>(r0, r1);
        r5 = r2.findMulti(r5);
        r0 = r5.length;
        if (r0 == 0) goto L_0x0047;
    L_0x001c:
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r5.length;
        r2 = 0;
    L_0x0023:
        if (r2 >= r1) goto L_0x0031;
    L_0x0025:
        r3 = r5[r2];
        r3 = r4.processFinderPatternInfo(r3);	 Catch:{ ReaderException -> 0x002e }
        r0.add(r3);	 Catch:{ ReaderException -> 0x002e }
    L_0x002e:
        r2 = r2 + 1;
        goto L_0x0023;
    L_0x0031:
        r5 = r0.isEmpty();
        if (r5 == 0) goto L_0x003a;
    L_0x0037:
        r5 = EMPTY_DETECTOR_RESULTS;
        return r5;
    L_0x003a:
        r5 = r0.size();
        r5 = new com.google.zxing.common.DetectorResult[r5];
        r5 = r0.toArray(r5);
        r5 = (com.google.zxing.common.DetectorResult[]) r5;
        return r5;
    L_0x0047:
        r5 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.multi.qrcode.detector.MultiDetector.detectMulti(java.util.Map):com.google.zxing.common.DetectorResult[]");
    }

    public MultiDetector(BitMatrix bitMatrix) {
        super(bitMatrix);
    }
}
