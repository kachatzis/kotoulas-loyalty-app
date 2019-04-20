package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class BookmarkDoCoMoResultParser extends AbstractDoCoMoResultParser {
    public URIParsedResult parse(Result result) {
        result = result.getText();
        URIParsedResult uRIParsedResult = null;
        if (!result.startsWith("MEBKM:")) {
            return null;
        }
        String matchSingleDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("TITLE:", result, true);
        result = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("URL:", result, true);
        if (result == null) {
            return null;
        }
        result = result[0];
        if (URIResultParser.isBasicallyValidURI(result)) {
            uRIParsedResult = new URIParsedResult(result, matchSingleDoCoMoPrefixedField);
        }
        return uRIParsedResult;
    }
}
