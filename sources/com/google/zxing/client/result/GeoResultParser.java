package com.google.zxing.client.result;

import java.util.regex.Pattern;

public final class GeoResultParser extends ResultParser {
    private static final Pattern GEO_URL_PATTERN = Pattern.compile("geo:([\\-0-9.]+),([\\-0-9.]+)(?:,([\\-0-9.]+))?(?:\\?(.*))?", 2);

    public com.google.zxing.client.result.GeoParsedResult parse(com.google.zxing.Result r13) {
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
        r12 = this;
        r13 = com.google.zxing.client.result.ResultParser.getMassagedText(r13);
        r0 = GEO_URL_PATTERN;
        r13 = r0.matcher(r13);
        r0 = r13.matches();
        r1 = 0;
        if (r0 != 0) goto L_0x0012;
    L_0x0011:
        return r1;
    L_0x0012:
        r0 = 4;
        r9 = r13.group(r0);
        r0 = 1;
        r0 = r13.group(r0);	 Catch:{ NumberFormatException -> 0x0070 }
        r3 = java.lang.Double.parseDouble(r0);	 Catch:{ NumberFormatException -> 0x0070 }
        r5 = 4636033603912859648; // 0x4056800000000000 float:0.0 double:90.0;	 Catch:{ NumberFormatException -> 0x0070 }
        r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));	 Catch:{ NumberFormatException -> 0x0070 }
        if (r0 > 0) goto L_0x006f;	 Catch:{ NumberFormatException -> 0x0070 }
    L_0x0029:
        r5 = -4587338432941916160; // 0xc056800000000000 float:0.0 double:-90.0;	 Catch:{ NumberFormatException -> 0x0070 }
        r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));	 Catch:{ NumberFormatException -> 0x0070 }
        if (r0 >= 0) goto L_0x0033;	 Catch:{ NumberFormatException -> 0x0070 }
    L_0x0032:
        goto L_0x006f;	 Catch:{ NumberFormatException -> 0x0070 }
    L_0x0033:
        r0 = 2;	 Catch:{ NumberFormatException -> 0x0070 }
        r0 = r13.group(r0);	 Catch:{ NumberFormatException -> 0x0070 }
        r5 = java.lang.Double.parseDouble(r0);	 Catch:{ NumberFormatException -> 0x0070 }
        r7 = 4640537203540230144; // 0x4066800000000000 float:0.0 double:180.0;	 Catch:{ NumberFormatException -> 0x0070 }
        r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));	 Catch:{ NumberFormatException -> 0x0070 }
        if (r0 > 0) goto L_0x006e;	 Catch:{ NumberFormatException -> 0x0070 }
    L_0x0045:
        r7 = -4582834833314545664; // 0xc066800000000000 float:0.0 double:-180.0;	 Catch:{ NumberFormatException -> 0x0070 }
        r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));	 Catch:{ NumberFormatException -> 0x0070 }
        if (r0 >= 0) goto L_0x004f;	 Catch:{ NumberFormatException -> 0x0070 }
    L_0x004e:
        goto L_0x006e;	 Catch:{ NumberFormatException -> 0x0070 }
    L_0x004f:
        r0 = 3;	 Catch:{ NumberFormatException -> 0x0070 }
        r2 = r13.group(r0);	 Catch:{ NumberFormatException -> 0x0070 }
        r7 = 0;	 Catch:{ NumberFormatException -> 0x0070 }
        if (r2 != 0) goto L_0x0059;	 Catch:{ NumberFormatException -> 0x0070 }
    L_0x0058:
        goto L_0x0067;	 Catch:{ NumberFormatException -> 0x0070 }
    L_0x0059:
        r13 = r13.group(r0);	 Catch:{ NumberFormatException -> 0x0070 }
        r10 = java.lang.Double.parseDouble(r13);	 Catch:{ NumberFormatException -> 0x0070 }
        r13 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1));
        if (r13 >= 0) goto L_0x0066;
    L_0x0065:
        return r1;
    L_0x0066:
        r7 = r10;
    L_0x0067:
        r13 = new com.google.zxing.client.result.GeoParsedResult;
        r2 = r13;
        r2.<init>(r3, r5, r7, r9);
        return r13;
    L_0x006e:
        return r1;
    L_0x006f:
        return r1;
    L_0x0070:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.GeoResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.GeoParsedResult");
    }
}
