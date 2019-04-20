package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class TelResultParser extends ResultParser {
    public TelParsedResult parse(Result result) {
        result = ResultParser.getMassagedText(result);
        if (!result.startsWith("tel:") && !result.startsWith("TEL:")) {
            return null;
        }
        String stringBuilder;
        if (result.startsWith("TEL:")) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("tel:");
            stringBuilder2.append(result.substring(4));
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder = result;
        }
        int indexOf = result.indexOf(63, 4);
        return new TelParsedResult(indexOf < 0 ? result.substring(4) : result.substring(4, indexOf), stringBuilder, null);
    }
}
