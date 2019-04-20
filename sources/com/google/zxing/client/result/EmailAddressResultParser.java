package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Pattern;

public final class EmailAddressResultParser extends ResultParser {
    private static final Pattern COMMA = Pattern.compile(",");

    public EmailAddressParsedResult parse(Result result) {
        String str;
        String[] strArr;
        String[] strArr2;
        String[] strArr3;
        String str2;
        result = ResultParser.getMassagedText(result);
        EmailAddressParsedResult emailAddressParsedResult = null;
        if (!result.startsWith("mailto:")) {
            if (!result.startsWith("MAILTO:")) {
                if (EmailDoCoMoResultParser.isBasicallyValidEmailAddress(result)) {
                    return new EmailAddressParsedResult(result);
                }
                return null;
            }
        }
        String substring = result.substring(7);
        int indexOf = substring.indexOf(63);
        if (indexOf >= 0) {
            substring = substring.substring(0, indexOf);
        }
        CharSequence urlDecode = ResultParser.urlDecode(substring);
        String[] split = !urlDecode.isEmpty() ? COMMA.split(urlDecode) : null;
        result = ResultParser.parseNameValuePairs(result);
        if (result != null) {
            String str3;
            if (split == null) {
                str3 = (String) result.get("to");
                if (str3 != null) {
                    split = COMMA.split(str3);
                }
            }
            str3 = (String) result.get("cc");
            String[] split2 = str3 != null ? COMMA.split(str3) : null;
            String str4 = (String) result.get("bcc");
            if (str4 != null) {
                emailAddressParsedResult = COMMA.split(str4);
            }
            str = (String) result.get("body");
            strArr = split;
            strArr2 = emailAddressParsedResult;
            strArr3 = split2;
            str2 = (String) result.get("subject");
        } else {
            strArr = split;
            strArr3 = null;
            strArr2 = strArr3;
            str2 = strArr2;
            str = str2;
        }
        return new EmailAddressParsedResult(strArr, strArr3, strArr2, str2, str);
    }
}
