package com.google.zxing.client.result;

import java.util.List;

public final class VEventResultParser extends ResultParser {
    public com.google.zxing.client.result.CalendarParsedResult parse(com.google.zxing.Result r18) {
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
        r17 = this;
        r0 = com.google.zxing.client.result.ResultParser.getMassagedText(r18);
        r1 = "BEGIN:VEVENT";
        r1 = r0.indexOf(r1);
        r2 = 0;
        if (r1 >= 0) goto L_0x000e;
    L_0x000d:
        return r2;
    L_0x000e:
        r1 = "SUMMARY";
        r3 = 1;
        r5 = matchSingleVCardPrefixedField(r1, r0, r3);
        r1 = "DTSTART";
        r6 = matchSingleVCardPrefixedField(r1, r0, r3);
        if (r6 != 0) goto L_0x001e;
    L_0x001d:
        return r2;
    L_0x001e:
        r1 = "DTEND";
        r7 = matchSingleVCardPrefixedField(r1, r0, r3);
        r1 = "DURATION";
        r8 = matchSingleVCardPrefixedField(r1, r0, r3);
        r1 = "LOCATION";
        r9 = matchSingleVCardPrefixedField(r1, r0, r3);
        r1 = "ORGANIZER";
        r1 = matchSingleVCardPrefixedField(r1, r0, r3);
        r10 = stripMailto(r1);
        r1 = "ATTENDEE";
        r11 = matchVCardPrefixedField(r1, r0, r3);
        r1 = 0;
        if (r11 == 0) goto L_0x0052;
    L_0x0043:
        r4 = 0;
    L_0x0044:
        r12 = r11.length;
        if (r4 >= r12) goto L_0x0052;
    L_0x0047:
        r12 = r11[r4];
        r12 = stripMailto(r12);
        r11[r4] = r12;
        r4 = r4 + 1;
        goto L_0x0044;
    L_0x0052:
        r4 = "DESCRIPTION";
        r12 = matchSingleVCardPrefixedField(r4, r0, r3);
        r4 = "GEO";
        r0 = matchSingleVCardPrefixedField(r4, r0, r3);
        r13 = 9221120237041090560; // 0x7ff8000000000000 float:0.0 double:NaN;
        if (r0 != 0) goto L_0x0064;
    L_0x0062:
        r15 = r13;
        goto L_0x007f;
    L_0x0064:
        r4 = 59;
        r4 = r0.indexOf(r4);
        if (r4 >= 0) goto L_0x006d;
    L_0x006c:
        return r2;
    L_0x006d:
        r1 = r0.substring(r1, r4);	 Catch:{ NumberFormatException -> 0x0087 }
        r13 = java.lang.Double.parseDouble(r1);	 Catch:{ NumberFormatException -> 0x0087 }
        r4 = r4 + r3;	 Catch:{ NumberFormatException -> 0x0087 }
        r0 = r0.substring(r4);	 Catch:{ NumberFormatException -> 0x0087 }
        r0 = java.lang.Double.parseDouble(r0);	 Catch:{ NumberFormatException -> 0x0087 }
        r15 = r0;
    L_0x007f:
        r0 = new com.google.zxing.client.result.CalendarParsedResult;	 Catch:{ IllegalArgumentException -> 0x0086 }
        r4 = r0;	 Catch:{ IllegalArgumentException -> 0x0086 }
        r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r15);	 Catch:{ IllegalArgumentException -> 0x0086 }
        return r0;
    L_0x0086:
        return r2;
    L_0x0087:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.VEventResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.CalendarParsedResult");
    }

    private static String matchSingleVCardPrefixedField(CharSequence charSequence, String str, boolean z) {
        charSequence = VCardResultParser.matchSingleVCardPrefixedField(charSequence, str, z, false);
        if (charSequence != null) {
            if (charSequence.isEmpty() == null) {
                return (String) charSequence.get(0);
            }
        }
        return null;
    }

    private static String[] matchVCardPrefixedField(CharSequence charSequence, String str, boolean z) {
        charSequence = VCardResultParser.matchVCardPrefixedField(charSequence, str, z, false);
        if (charSequence != null) {
            if (charSequence.isEmpty() == null) {
                str = charSequence.size();
                z = new String[str];
                for (int i = 0; i < str; i++) {
                    z[i] = (String) ((List) charSequence.get(i)).get(0);
                }
                return z;
            }
        }
        return null;
    }

    private static String stripMailto(String str) {
        if (str != null) {
            return (str.startsWith("mailto:") || str.startsWith("MAILTO:")) ? str.substring(7) : str;
        } else {
            return str;
        }
    }
}
