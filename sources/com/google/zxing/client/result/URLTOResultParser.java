package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class URLTOResultParser extends ResultParser {
    public URIParsedResult parse(Result result) {
        result = ResultParser.getMassagedText(result);
        String str = null;
        if (!result.startsWith("urlto:") && !result.startsWith("URLTO:")) {
            return null;
        }
        int indexOf = result.indexOf(58, 6);
        if (indexOf < 0) {
            return null;
        }
        if (indexOf > 6) {
            str = result.substring(6, indexOf);
        }
        return new URIParsedResult(result.substring(indexOf + 1), str);
    }
}
