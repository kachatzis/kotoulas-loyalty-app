package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class URIResultParser extends ResultParser {
    private static final Pattern URL_WITHOUT_PROTOCOL_PATTERN = Pattern.compile("([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,}(:\\d{1,5})?(/|\\?|$)");
    private static final Pattern URL_WITH_PROTOCOL_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+-.]+:");

    public URIParsedResult parse(Result result) {
        result = ResultParser.getMassagedText(result);
        if (!result.startsWith("URL:")) {
            if (!result.startsWith("URI:")) {
                result = result.trim();
                return isBasicallyValidURI(result) ? new URIParsedResult(result, null) : null;
            }
        }
        return new URIParsedResult(result.substring(4).trim(), null);
    }

    static boolean isBasicallyValidURI(String str) {
        boolean z = false;
        if (str.contains(" ")) {
            return false;
        }
        Matcher matcher = URL_WITH_PROTOCOL_PATTERN.matcher(str);
        if (matcher.find() && matcher.start() == 0) {
            return true;
        }
        str = URL_WITHOUT_PROTOCOL_PATTERN.matcher(str);
        if (str.find() && str.start() == null) {
            z = true;
        }
        return z;
    }
}
