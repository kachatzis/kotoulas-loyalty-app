package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VCardResultParser extends ResultParser {
    private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final Pattern NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final Pattern SEMICOLON_OR_COMMA = Pattern.compile("[;,]");
    private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
    private static final Pattern VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");

    public AddressBookParsedResult parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        Matcher matcher = BEGIN_VCARD.matcher(massagedText);
        if (matcher.find()) {
            if (matcher.start() == 0) {
                String[] strArr;
                String[] strArr2;
                Collection matchVCardPrefixedField = matchVCardPrefixedField("FN", massagedText, true, false);
                if (matchVCardPrefixedField == null) {
                    matchVCardPrefixedField = matchVCardPrefixedField("N", massagedText, true, false);
                    formatNames(matchVCardPrefixedField);
                }
                List matchSingleVCardPrefixedField = matchSingleVCardPrefixedField("NICKNAME", massagedText, true, false);
                if (matchSingleVCardPrefixedField == null) {
                    strArr = null;
                } else {
                    strArr = COMMA.split((CharSequence) matchSingleVCardPrefixedField.get(0));
                }
                Collection matchVCardPrefixedField2 = matchVCardPrefixedField("TEL", massagedText, true, false);
                Collection matchVCardPrefixedField3 = matchVCardPrefixedField("EMAIL", massagedText, true, false);
                List matchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("NOTE", massagedText, false, false);
                Collection matchVCardPrefixedField4 = matchVCardPrefixedField("ADR", massagedText, true, true);
                List matchSingleVCardPrefixedField3 = matchSingleVCardPrefixedField("ORG", massagedText, true, true);
                List matchSingleVCardPrefixedField4 = matchSingleVCardPrefixedField("BDAY", massagedText, true, false);
                List list = (matchSingleVCardPrefixedField4 == null || isLikeVCardDate((CharSequence) matchSingleVCardPrefixedField4.get(0))) ? matchSingleVCardPrefixedField4 : null;
                List matchSingleVCardPrefixedField5 = matchSingleVCardPrefixedField("TITLE", massagedText, true, false);
                Collection matchVCardPrefixedField5 = matchVCardPrefixedField("URL", massagedText, true, false);
                List matchSingleVCardPrefixedField6 = matchSingleVCardPrefixedField("IMPP", massagedText, true, false);
                List matchSingleVCardPrefixedField7 = matchSingleVCardPrefixedField("GEO", massagedText, true, false);
                if (matchSingleVCardPrefixedField7 == null) {
                    strArr2 = null;
                } else {
                    strArr2 = SEMICOLON_OR_COMMA.split((CharSequence) matchSingleVCardPrefixedField7.get(0));
                }
                String[] strArr3 = (strArr2 == null || strArr2.length == 2) ? strArr2 : null;
                return new AddressBookParsedResult(toPrimaryValues(matchVCardPrefixedField), strArr, null, toPrimaryValues(matchVCardPrefixedField2), toTypes(matchVCardPrefixedField2), toPrimaryValues(matchVCardPrefixedField3), toTypes(matchVCardPrefixedField3), toPrimaryValue(matchSingleVCardPrefixedField6), toPrimaryValue(matchSingleVCardPrefixedField2), toPrimaryValues(matchVCardPrefixedField4), toTypes(matchVCardPrefixedField4), toPrimaryValue(matchSingleVCardPrefixedField3), toPrimaryValue(list), toPrimaryValue(matchSingleVCardPrefixedField5), toPrimaryValues(matchVCardPrefixedField5), strArr3);
            }
        }
        return null;
    }

    static List<List<String>> matchVCardPrefixedField(CharSequence charSequence, String str, boolean z, boolean z2) {
        String str2 = str;
        int length = str.length();
        int i = 0;
        int i2 = 0;
        List<List<String>> list = null;
        while (i2 < length) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("(?:^|\n)");
            stringBuilder.append(charSequence);
            stringBuilder.append("(?:;([^:]*))?:");
            Matcher matcher = Pattern.compile(stringBuilder.toString(), 2).matcher(str2);
            if (i2 > 0) {
                i2--;
            }
            if (!matcher.find(i2)) {
                break;
            }
            List list2;
            Object obj;
            String str3;
            i2 = matcher.end(i);
            CharSequence group = matcher.group(1);
            if (group != null) {
                String[] split = SEMICOLON.split(group);
                int length2 = split.length;
                int i3 = 0;
                list2 = null;
                obj = null;
                str3 = null;
                while (i3 < length2) {
                    CharSequence charSequence2 = split[i3];
                    if (list2 == null) {
                        list2 = new ArrayList(1);
                    }
                    list2.add(charSequence2);
                    String[] split2 = EQUALS.split(charSequence2, 2);
                    if (split2.length > 1) {
                        String str4 = split2[i];
                        String str5 = split2[1];
                        if ("ENCODING".equalsIgnoreCase(str4) && "QUOTED-PRINTABLE".equalsIgnoreCase(str5)) {
                            obj = 1;
                        } else if ("CHARSET".equalsIgnoreCase(str4)) {
                            str3 = str5;
                        }
                    }
                    i3++;
                    i = 0;
                }
            } else {
                list2 = null;
                obj = null;
                str3 = null;
            }
            int i4 = i2;
            while (true) {
                i4 = str2.indexOf(10, i4);
                if (i4 >= 0) {
                    if (i4 < str.length() - 1) {
                        i = i4 + 1;
                        if (str2.charAt(i) == ' ' || str2.charAt(i) == '\t') {
                            i4 += 2;
                        }
                    }
                    if (obj == null || ((i4 < 1 || str2.charAt(i4 - 1) != '=') && (i4 < 2 || str2.charAt(i4 - 2) != '='))) {
                        break;
                    }
                    i4++;
                } else {
                    break;
                }
            }
            if (i4 < 0) {
                i4 = length;
            } else if (i4 > i2) {
                Object decodeQuotedPrintable;
                if (list == null) {
                    list = new ArrayList(1);
                }
                if (i4 >= 1 && str2.charAt(i4 - 1) == '\r') {
                    i4--;
                }
                CharSequence substring = str2.substring(i2, i4);
                if (z) {
                    substring = substring.trim();
                }
                if (obj != null) {
                    decodeQuotedPrintable = decodeQuotedPrintable(substring, str3);
                    if (z2) {
                        decodeQuotedPrintable = UNESCAPED_SEMICOLONS.matcher(decodeQuotedPrintable).replaceAll("\n").trim();
                    }
                } else {
                    if (z2) {
                        substring = UNESCAPED_SEMICOLONS.matcher(substring).replaceAll("\n").trim();
                    }
                    decodeQuotedPrintable = VCARD_ESCAPES.matcher(NEWLINE_ESCAPE.matcher(CR_LF_SPACE_TAB.matcher(substring).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                }
                if (list2 == null) {
                    List arrayList = new ArrayList(1);
                    arrayList.add(decodeQuotedPrintable);
                    list.add(arrayList);
                } else {
                    list2.add(0, decodeQuotedPrintable);
                    list.add(list2);
                }
                i4++;
            } else {
                i4++;
            }
            i2 = i4;
            i = 0;
        }
        return list;
    }

    private static String decodeQuotedPrintable(CharSequence charSequence, String str) {
        int length = charSequence.length();
        StringBuilder stringBuilder = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (!(charAt == '\n' || charAt == '\r')) {
                if (charAt != '=') {
                    maybeAppendFragment(byteArrayOutputStream, str, stringBuilder);
                    stringBuilder.append(charAt);
                } else if (i < length - 2) {
                    charAt = charSequence.charAt(i + 1);
                    if (!(charAt == '\r' || charAt == '\n')) {
                        i += 2;
                        char charAt2 = charSequence.charAt(i);
                        int parseHexDigit = ResultParser.parseHexDigit(charAt);
                        int parseHexDigit2 = ResultParser.parseHexDigit(charAt2);
                        if (parseHexDigit >= 0 && parseHexDigit2 >= 0) {
                            byteArrayOutputStream.write((parseHexDigit << 4) + parseHexDigit2);
                        }
                    }
                }
            }
            i++;
        }
        maybeAppendFragment(byteArrayOutputStream, str, stringBuilder);
        return stringBuilder.toString();
    }

    private static void maybeAppendFragment(java.io.ByteArrayOutputStream r2, java.lang.String r3, java.lang.StringBuilder r4) {
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
        r0 = r2.size();
        if (r0 <= 0) goto L_0x0030;
    L_0x0006:
        r0 = r2.toByteArray();
        if (r3 != 0) goto L_0x0018;
    L_0x000c:
        r3 = new java.lang.String;
        r1 = "UTF-8";
        r1 = java.nio.charset.Charset.forName(r1);
        r3.<init>(r0, r1);
        goto L_0x002a;
    L_0x0018:
        r1 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x001f }
        r1.<init>(r0, r3);	 Catch:{ UnsupportedEncodingException -> 0x001f }
        r3 = r1;
        goto L_0x002a;
    L_0x001f:
        r3 = new java.lang.String;
        r1 = "UTF-8";
        r1 = java.nio.charset.Charset.forName(r1);
        r3.<init>(r0, r1);
    L_0x002a:
        r2.reset();
        r4.append(r3);
    L_0x0030:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.VCardResultParser.maybeAppendFragment(java.io.ByteArrayOutputStream, java.lang.String, java.lang.StringBuilder):void");
    }

    static List<String> matchSingleVCardPrefixedField(CharSequence charSequence, String str, boolean z, boolean z2) {
        charSequence = matchVCardPrefixedField(charSequence, str, z, z2);
        if (charSequence != null) {
            if (charSequence.isEmpty() == null) {
                return (List) charSequence.get(null);
            }
        }
        return null;
    }

    private static String toPrimaryValue(List<String> list) {
        if (list != null) {
            if (!list.isEmpty()) {
                return (String) list.get(0);
            }
        }
        return null;
    }

    private static String[] toPrimaryValues(Collection<List<String>> collection) {
        if (collection != null) {
            if (!collection.isEmpty()) {
                List arrayList = new ArrayList(collection.size());
                for (List list : collection) {
                    String str = (String) list.get(0);
                    if (!(str == null || str.isEmpty())) {
                        arrayList.add(str);
                    }
                }
                return (String[]) arrayList.toArray(new String[collection.size()]);
            }
        }
        return null;
    }

    private static String[] toTypes(Collection<List<String>> collection) {
        if (collection != null) {
            if (!collection.isEmpty()) {
                List arrayList = new ArrayList(collection.size());
                for (List list : collection) {
                    Object obj;
                    int i = 1;
                    while (i < list.size()) {
                        String str = (String) list.get(i);
                        int indexOf = str.indexOf(61);
                        if (indexOf < 0) {
                            obj = str;
                            break;
                        } else if ("TYPE".equalsIgnoreCase(str.substring(0, indexOf))) {
                            obj = str.substring(indexOf + 1);
                            break;
                        } else {
                            i++;
                        }
                    }
                    obj = null;
                    arrayList.add(obj);
                }
                return (String[]) arrayList.toArray(new String[collection.size()]);
            }
        }
        return null;
    }

    private static boolean isLikeVCardDate(CharSequence charSequence) {
        if (charSequence != null) {
            if (VCARD_LIKE_DATE.matcher(charSequence).matches() == null) {
                return null;
            }
        }
        return true;
    }

    private static void formatNames(Iterable<List<String>> iterable) {
        if (iterable != null) {
            for (List list : iterable) {
                String str = (String) list.get(0);
                String[] strArr = new String[5];
                int i = 0;
                int i2 = 0;
                while (i < strArr.length - 1) {
                    int indexOf = str.indexOf(59, i2);
                    if (indexOf < 0) {
                        break;
                    }
                    strArr[i] = str.substring(i2, indexOf);
                    i++;
                    i2 = indexOf + 1;
                }
                strArr[i] = str.substring(i2);
                StringBuilder stringBuilder = new StringBuilder(100);
                maybeAppendComponent(strArr, 3, stringBuilder);
                maybeAppendComponent(strArr, 1, stringBuilder);
                maybeAppendComponent(strArr, 2, stringBuilder);
                maybeAppendComponent(strArr, 0, stringBuilder);
                maybeAppendComponent(strArr, 4, stringBuilder);
                list.set(0, stringBuilder.toString().trim());
            }
        }
    }

    private static void maybeAppendComponent(String[] strArr, int i, StringBuilder stringBuilder) {
        if (strArr[i] != null && !strArr[i].isEmpty()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(strArr[i]);
        }
    }
}
