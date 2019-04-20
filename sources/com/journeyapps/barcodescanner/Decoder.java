package com.journeyapps.barcodescanner;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.HybridBinarizer;
import java.util.ArrayList;
import java.util.List;

public class Decoder implements ResultPointCallback {
    private List<ResultPoint> possibleResultPoints = new ArrayList();
    private Reader reader;

    public Decoder(Reader reader) {
        this.reader = reader;
    }

    protected Reader getReader() {
        return this.reader;
    }

    public Result decode(LuminanceSource luminanceSource) {
        return decode(toBitmap(luminanceSource));
    }

    protected BinaryBitmap toBitmap(LuminanceSource luminanceSource) {
        return new BinaryBitmap(new HybridBinarizer(luminanceSource));
    }

    protected com.google.zxing.Result decode(com.google.zxing.BinaryBitmap r2) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r1 = this;
        r0 = r1.possibleResultPoints;
        r0.clear();
        r0 = r1.reader;	 Catch:{ Exception -> 0x0027, all -> 0x0020 }
        r0 = r0 instanceof com.google.zxing.MultiFormatReader;	 Catch:{ Exception -> 0x0027, all -> 0x0020 }
        if (r0 == 0) goto L_0x0019;	 Catch:{ Exception -> 0x0027, all -> 0x0020 }
    L_0x000b:
        r0 = r1.reader;	 Catch:{ Exception -> 0x0027, all -> 0x0020 }
        r0 = (com.google.zxing.MultiFormatReader) r0;	 Catch:{ Exception -> 0x0027, all -> 0x0020 }
        r2 = r0.decodeWithState(r2);	 Catch:{ Exception -> 0x0027, all -> 0x0020 }
    L_0x0013:
        r0 = r1.reader;
        r0.reset();
        return r2;
    L_0x0019:
        r0 = r1.reader;	 Catch:{ Exception -> 0x0027, all -> 0x0020 }
        r2 = r0.decode(r2);	 Catch:{ Exception -> 0x0027, all -> 0x0020 }
        goto L_0x0013;
    L_0x0020:
        r2 = move-exception;
        r0 = r1.reader;
        r0.reset();
        throw r2;
    L_0x0027:
        r2 = 0;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.journeyapps.barcodescanner.Decoder.decode(com.google.zxing.BinaryBitmap):com.google.zxing.Result");
    }

    public List<ResultPoint> getPossibleResultPoints() {
        return new ArrayList(this.possibleResultPoints);
    }

    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        this.possibleResultPoints.add(resultPoint);
    }
}
