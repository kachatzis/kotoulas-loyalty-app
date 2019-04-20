package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

public final class ISBNResultParser extends ResultParser {
    public ISBNParsedResult parse(Result result) {
        if (result.getBarcodeFormat() != BarcodeFormat.EAN_13) {
            return null;
        }
        result = ResultParser.getMassagedText(result);
        if (result.length() != 13) {
            return null;
        }
        if (result.startsWith("978") || result.startsWith("979")) {
            return new ISBNParsedResult(result);
        }
        return null;
    }
}
