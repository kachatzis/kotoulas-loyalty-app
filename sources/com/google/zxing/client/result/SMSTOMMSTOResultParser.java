package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class SMSTOMMSTOResultParser extends ResultParser {
    public SMSParsedResult parse(Result result) {
        result = ResultParser.getMassagedText(result);
        if (!result.startsWith("smsto:") && !result.startsWith("SMSTO:") && !result.startsWith("mmsto:") && !result.startsWith("MMSTO:")) {
            return null;
        }
        String substring;
        String substring2 = result.substring(6);
        int indexOf = substring2.indexOf(58);
        if (indexOf >= 0) {
            substring = substring2.substring(indexOf + 1);
            substring2 = substring2.substring(0, indexOf);
        } else {
            substring = null;
        }
        return new SMSParsedResult(substring2, null, null, substring);
    }
}
