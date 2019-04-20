package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Pattern;

public final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser {
    private static final Pattern ATEXT_ALPHANUMERIC = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

    public EmailAddressParsedResult parse(Result result) {
        result = ResultParser.getMassagedText(result);
        if (!result.startsWith("MATMSG:")) {
            return null;
        }
        String[] matchDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("TO:", result, true);
        if (matchDoCoMoPrefixedField == null) {
            return null;
        }
        for (String isBasicallyValidEmailAddress : matchDoCoMoPrefixedField) {
            if (!isBasicallyValidEmailAddress(isBasicallyValidEmailAddress)) {
                return null;
            }
        }
        return new EmailAddressParsedResult(matchDoCoMoPrefixedField, null, null, AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("SUB:", result, false), AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("BODY:", result, false));
    }

    static boolean isBasicallyValidEmailAddress(String str) {
        return (str == null || !ATEXT_ALPHANUMERIC.matcher(str).matches() || str.indexOf(64) < null) ? null : true;
    }
}
