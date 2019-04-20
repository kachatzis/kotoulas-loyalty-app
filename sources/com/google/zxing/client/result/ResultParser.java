package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class ResultParser {
    private static final Pattern AMPERSAND = Pattern.compile("&");
    private static final String BYTE_ORDER_MARK = "﻿";
    private static final Pattern DIGITS = Pattern.compile("\\d+");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final ResultParser[] PARSERS = new ResultParser[]{new BookmarkDoCoMoResultParser(), new AddressBookDoCoMoResultParser(), new EmailDoCoMoResultParser(), new AddressBookAUResultParser(), new VCardResultParser(), new BizcardResultParser(), new VEventResultParser(), new EmailAddressResultParser(), new SMTPResultParser(), new TelResultParser(), new SMSMMSResultParser(), new SMSTOMMSTOResultParser(), new GeoResultParser(), new WifiResultParser(), new URLTOResultParser(), new URIResultParser(), new ISBNResultParser(), new ProductResultParser(), new ExpandedProductResultParser(), new VINResultParser()};

    protected static int parseHexDigit(char c) {
        return (c < '0' || c > '9') ? (c < 'a' || c > 'f') ? (c < 'A' || c > 'F') ? -1 : (c - 65) + 10 : (c - 97) + 10 : c - 48;
    }

    public abstract ParsedResult parse(Result result);

    protected static String getMassagedText(Result result) {
        result = result.getText();
        return result.startsWith(BYTE_ORDER_MARK) ? result.substring(1) : result;
    }

    public static ParsedResult parseResult(Result result) {
        for (ResultParser parse : PARSERS) {
            ParsedResult parse2 = parse.parse(result);
            if (parse2 != null) {
                return parse2;
            }
        }
        return new TextParsedResult(result.getText(), null);
    }

    protected static void maybeAppend(String str, StringBuilder stringBuilder) {
        if (str != null) {
            stringBuilder.append('\n');
            stringBuilder.append(str);
        }
    }

    protected static void maybeAppend(String[] strArr, StringBuilder stringBuilder) {
        if (strArr != null) {
            for (String str : strArr) {
                stringBuilder.append('\n');
                stringBuilder.append(str);
            }
        }
    }

    protected static String[] maybeWrap(String str) {
        if (str == null) {
            return null;
        }
        return new String[]{str};
    }

    protected static String unescapeBackslash(String str) {
        int indexOf = str.indexOf(92);
        if (indexOf < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder(length - 1);
        stringBuilder.append(str.toCharArray(), 0, indexOf);
        Object obj = null;
        while (indexOf < length) {
            char charAt = str.charAt(indexOf);
            if (obj == null) {
                if (charAt == '\\') {
                    obj = 1;
                    indexOf++;
                }
            }
            stringBuilder.append(charAt);
            obj = null;
            indexOf++;
        }
        return stringBuilder.toString();
    }

    protected static boolean isStringOfDigits(CharSequence charSequence, int i) {
        return (charSequence == null || i <= 0 || i != charSequence.length() || DIGITS.matcher(charSequence).matches() == null) ? null : true;
    }

    protected static boolean isSubstringOfDigits(CharSequence charSequence, int i, int i2) {
        boolean z = false;
        if (charSequence != null) {
            if (i2 > 0) {
                i2 += i;
                if (charSequence.length() >= i2 && DIGITS.matcher(charSequence.subSequence(i, i2)).matches() != null) {
                    z = true;
                }
                return z;
            }
        }
        return false;
    }

    static Map<String, String> parseNameValuePairs(String str) {
        int indexOf = str.indexOf(63);
        if (indexOf < 0) {
            return null;
        }
        Map<String, String> hashMap = new HashMap(3);
        for (CharSequence appendKeyValue : AMPERSAND.split(str.substring(indexOf + 1))) {
            appendKeyValue(appendKeyValue, hashMap);
        }
        return hashMap;
    }

    private static void appendKeyValue(java.lang.CharSequence r2, java.util.Map<java.lang.String, java.lang.String> r3) {
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
        r0 = EQUALS;
        r1 = 2;
        r2 = r0.split(r2, r1);
        r0 = r2.length;
        if (r0 != r1) goto L_0x0017;
    L_0x000a:
        r0 = 0;
        r0 = r2[r0];
        r1 = 1;
        r2 = r2[r1];
        r2 = urlDecode(r2);	 Catch:{ IllegalArgumentException -> 0x0017 }
        r3.put(r0, r2);	 Catch:{ IllegalArgumentException -> 0x0017 }
    L_0x0017:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.ResultParser.appendKeyValue(java.lang.CharSequence, java.util.Map):void");
    }

    static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (String str2) {
            throw new IllegalStateException(str2);
        }
    }

    static String[] matchPrefixedField(String str, String str2, char c, boolean z) {
        int length = str2.length();
        List list = null;
        int i;
        for (int i2 = 0; i2 < length; i2 = i) {
            i2 = str2.indexOf(str, i2);
            if (i2 < 0) {
                break;
            }
            i2 += str.length();
            Object obj = 1;
            i = i2;
            while (obj != null) {
                i = str2.indexOf(c, i);
                if (i < 0) {
                    i = str2.length();
                    obj = null;
                } else if (countPrecedingBackslashes(str2, i) % 2 != 0) {
                    i++;
                } else {
                    if (list == null) {
                        list = new ArrayList(3);
                    }
                    String unescapeBackslash = unescapeBackslash(str2.substring(i2, i));
                    if (z) {
                        unescapeBackslash = unescapeBackslash.trim();
                    }
                    if (!unescapeBackslash.isEmpty()) {
                        list.add(unescapeBackslash);
                    }
                    i++;
                    obj = null;
                }
            }
        }
        if (list != null) {
            if (list.isEmpty() == null) {
                return (String[]) list.toArray(new String[list.size()]);
            }
        }
        return null;
    }

    private static int countPrecedingBackslashes(CharSequence charSequence, int i) {
        i--;
        int i2 = 0;
        while (i >= 0 && charSequence.charAt(i) == '\\') {
            i2++;
            i--;
        }
        return i2;
    }

    static String matchSinglePrefixedField(String str, String str2, char c, boolean z) {
        str = matchPrefixedField(str, str2, c, z);
        if (str == null) {
            return null;
        }
        return str[null];
    }
}
