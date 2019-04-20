package com.google.zxing.client.result;

import java.util.regex.Pattern;

public final class VINResultParser extends ResultParser {
    private static final Pattern AZ09 = Pattern.compile("[A-Z0-9]{17}");
    private static final Pattern IOQ = Pattern.compile("[IOQ]");

    public com.google.zxing.client.result.VINParsedResult parse(com.google.zxing.Result r14) {
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
        r13 = this;
        r0 = r14.getBarcodeFormat();
        r1 = com.google.zxing.BarcodeFormat.CODE_39;
        r2 = 0;
        if (r0 == r1) goto L_0x000a;
    L_0x0009:
        return r2;
    L_0x000a:
        r14 = r14.getText();
        r0 = IOQ;
        r14 = r0.matcher(r14);
        r0 = "";
        r14 = r14.replaceAll(r0);
        r4 = r14.trim();
        r14 = AZ09;
        r14 = r14.matcher(r4);
        r14 = r14.matches();
        if (r14 != 0) goto L_0x002b;
    L_0x002a:
        return r2;
    L_0x002b:
        r14 = checkChecksum(r4);	 Catch:{ IllegalArgumentException -> 0x0069 }
        if (r14 != 0) goto L_0x0032;	 Catch:{ IllegalArgumentException -> 0x0069 }
    L_0x0031:
        return r2;	 Catch:{ IllegalArgumentException -> 0x0069 }
    L_0x0032:
        r14 = 0;	 Catch:{ IllegalArgumentException -> 0x0069 }
        r0 = 3;	 Catch:{ IllegalArgumentException -> 0x0069 }
        r5 = r4.substring(r14, r0);	 Catch:{ IllegalArgumentException -> 0x0069 }
        r14 = new com.google.zxing.client.result.VINParsedResult;	 Catch:{ IllegalArgumentException -> 0x0069 }
        r1 = 9;	 Catch:{ IllegalArgumentException -> 0x0069 }
        r6 = r4.substring(r0, r1);	 Catch:{ IllegalArgumentException -> 0x0069 }
        r3 = 17;	 Catch:{ IllegalArgumentException -> 0x0069 }
        r7 = r4.substring(r1, r3);	 Catch:{ IllegalArgumentException -> 0x0069 }
        r8 = countryCode(r5);	 Catch:{ IllegalArgumentException -> 0x0069 }
        r3 = 8;	 Catch:{ IllegalArgumentException -> 0x0069 }
        r9 = r4.substring(r0, r3);	 Catch:{ IllegalArgumentException -> 0x0069 }
        r0 = r4.charAt(r1);	 Catch:{ IllegalArgumentException -> 0x0069 }
        r10 = modelYear(r0);	 Catch:{ IllegalArgumentException -> 0x0069 }
        r0 = 10;	 Catch:{ IllegalArgumentException -> 0x0069 }
        r11 = r4.charAt(r0);	 Catch:{ IllegalArgumentException -> 0x0069 }
        r0 = 11;	 Catch:{ IllegalArgumentException -> 0x0069 }
        r12 = r4.substring(r0);	 Catch:{ IllegalArgumentException -> 0x0069 }
        r3 = r14;	 Catch:{ IllegalArgumentException -> 0x0069 }
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12);	 Catch:{ IllegalArgumentException -> 0x0069 }
        return r14;
    L_0x0069:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.VINResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.VINParsedResult");
    }

    private static boolean checkChecksum(CharSequence charSequence) {
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            int i3 = i + 1;
            i2 += vinPositionWeight(i3) * vinCharValue(charSequence.charAt(i));
            i = i3;
        }
        if (charSequence.charAt(8) == checkChar(i2 % 11)) {
            return true;
        }
        return false;
    }

    private static int vinCharValue(char c) {
        if (c >= 'A' && c <= 'I') {
            return (c - 'A') + 1;
        }
        if (c >= 'J' && c <= 'R') {
            return (c - 'J') + 1;
        }
        if (c >= 'S' && c <= 'Z') {
            return (c - 'S') + 2;
        }
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        throw new IllegalArgumentException();
    }

    private static int vinPositionWeight(int i) {
        if (i >= 1 && i <= 7) {
            return 9 - i;
        }
        if (i == 8) {
            return 10;
        }
        if (i == 9) {
            return 0;
        }
        if (i >= 10 && i <= 17) {
            return 19 - i;
        }
        throw new IllegalArgumentException();
    }

    private static char checkChar(int i) {
        if (i < 10) {
            return (char) (i + 48);
        }
        if (i == 10) {
            return 'X';
        }
        throw new IllegalArgumentException();
    }

    private static int modelYear(char c) {
        if (c >= 'E' && c <= 'H') {
            return (c - 'E') + 1984;
        }
        if (c >= 'J' && c <= 'N') {
            return (c - 'J') + 1988;
        }
        if (c == 'P') {
            return '߉';
        }
        if (c >= 'R' && c <= 'T') {
            return (c - 'R') + 1994;
        }
        if (c >= 'V' && c <= 'Y') {
            return (c - 'V') + 1997;
        }
        if (c >= '1' && c <= '9') {
            return (c - '1') + 2001;
        }
        if (c >= 'A' && c <= 'D') {
            return (c - 'A') + 2010;
        }
        throw new IllegalArgumentException();
    }

    private static String countryCode(CharSequence charSequence) {
        char charAt = charSequence.charAt(0);
        charSequence = charSequence.charAt(1);
        if (charAt != '9') {
            if (charAt != 'S') {
                if (charAt != 'Z') {
                    switch (charAt) {
                        case '1':
                        case '4':
                        case '5':
                            return "US";
                        case '2':
                            return "CA";
                        case '3':
                            if (charSequence >= 65 && charSequence <= 87) {
                                return "MX";
                            }
                        default:
                            switch (charAt) {
                                case 'J':
                                    if (charSequence >= 65 && charSequence <= 84) {
                                        return "JP";
                                    }
                                case 'K':
                                    if (charSequence >= 76 && charSequence <= 82) {
                                        return "KO";
                                    }
                                case 'L':
                                    return "CN";
                                case 'M':
                                    if (charSequence >= 65 && charSequence <= 69) {
                                        return "IN";
                                    }
                                default:
                                    switch (charAt) {
                                        case 'V':
                                            if (charSequence >= 70 && charSequence <= 82) {
                                                return "FR";
                                            }
                                            if (charSequence >= 83 && charSequence <= 87) {
                                                return "ES";
                                            }
                                            break;
                                        case 'W':
                                            return "DE";
                                        case 'X':
                                            if (charSequence == 48 || (charSequence >= 51 && charSequence <= 57)) {
                                                return "RU";
                                            }
                                        default:
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else if (charSequence >= 65 && charSequence <= 82) {
                    return "IT";
                }
            } else if (charSequence >= 65 && charSequence <= 77) {
                return "UK";
            } else {
                if (charSequence >= 78 && charSequence <= 84) {
                    return "DE";
                }
            }
        } else if ((charSequence >= 65 && charSequence <= 69) || (charSequence >= 51 && charSequence <= 57)) {
            return "BR";
        }
        return null;
    }
}
