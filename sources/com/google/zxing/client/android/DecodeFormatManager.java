package com.google.zxing.client.android;

import android.content.Intent;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.Intents.Scan;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public final class DecodeFormatManager {
    static final Set<BarcodeFormat> AZTEC_FORMATS = EnumSet.of(BarcodeFormat.AZTEC);
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    static final Set<BarcodeFormat> DATA_MATRIX_FORMATS = EnumSet.of(BarcodeFormat.DATA_MATRIX);
    private static final Map<String, Set<BarcodeFormat>> FORMATS_FOR_MODE = new HashMap();
    static final Set<BarcodeFormat> INDUSTRIAL_FORMATS = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
    private static final Set<BarcodeFormat> ONE_D_FORMATS = EnumSet.copyOf(PRODUCT_FORMATS);
    static final Set<BarcodeFormat> PDF417_FORMATS = EnumSet.of(BarcodeFormat.PDF_417);
    static final Set<BarcodeFormat> PRODUCT_FORMATS = EnumSet.of(BarcodeFormat.UPC_A, new BarcodeFormat[]{BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED});
    static final Set<BarcodeFormat> QR_CODE_FORMATS = EnumSet.of(BarcodeFormat.QR_CODE);

    static {
        ONE_D_FORMATS.addAll(INDUSTRIAL_FORMATS);
        FORMATS_FOR_MODE.put(Scan.ONE_D_MODE, ONE_D_FORMATS);
        FORMATS_FOR_MODE.put(Scan.PRODUCT_MODE, PRODUCT_FORMATS);
        FORMATS_FOR_MODE.put(Scan.QR_CODE_MODE, QR_CODE_FORMATS);
        FORMATS_FOR_MODE.put(Scan.DATA_MATRIX_MODE, DATA_MATRIX_FORMATS);
        FORMATS_FOR_MODE.put(Scan.AZTEC_MODE, AZTEC_FORMATS);
        FORMATS_FOR_MODE.put(Scan.PDF417_MODE, PDF417_FORMATS);
    }

    private DecodeFormatManager() {
    }

    public static Set<BarcodeFormat> parseDecodeFormats(Intent intent) {
        CharSequence stringExtra = intent.getStringExtra(Scan.FORMATS);
        return parseDecodeFormats(stringExtra != null ? Arrays.asList(COMMA_PATTERN.split(stringExtra)) : null, intent.getStringExtra(Scan.MODE));
    }

    private static java.util.Set<com.google.zxing.BarcodeFormat> parseDecodeFormats(java.lang.Iterable<java.lang.String> r2, java.lang.String r3) {
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
        if (r2 == 0) goto L_0x0021;
    L_0x0002:
        r0 = com.google.zxing.BarcodeFormat.class;
        r0 = java.util.EnumSet.noneOf(r0);
        r2 = r2.iterator();	 Catch:{ IllegalArgumentException -> 0x0021 }
    L_0x000c:
        r1 = r2.hasNext();	 Catch:{ IllegalArgumentException -> 0x0021 }
        if (r1 == 0) goto L_0x0020;	 Catch:{ IllegalArgumentException -> 0x0021 }
    L_0x0012:
        r1 = r2.next();	 Catch:{ IllegalArgumentException -> 0x0021 }
        r1 = (java.lang.String) r1;	 Catch:{ IllegalArgumentException -> 0x0021 }
        r1 = com.google.zxing.BarcodeFormat.valueOf(r1);	 Catch:{ IllegalArgumentException -> 0x0021 }
        r0.add(r1);	 Catch:{ IllegalArgumentException -> 0x0021 }
        goto L_0x000c;
    L_0x0020:
        return r0;
    L_0x0021:
        if (r3 == 0) goto L_0x002c;
    L_0x0023:
        r2 = FORMATS_FOR_MODE;
        r2 = r2.get(r3);
        r2 = (java.util.Set) r2;
        return r2;
    L_0x002c:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.android.DecodeFormatManager.parseDecodeFormats(java.lang.Iterable, java.lang.String):java.util.Set<com.google.zxing.BarcodeFormat>");
    }
}
