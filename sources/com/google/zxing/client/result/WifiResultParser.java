package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class WifiResultParser extends ResultParser {
    public WifiParsedResult parse(Result result) {
        result = ResultParser.getMassagedText(result);
        if (!result.startsWith("WIFI:")) {
            return null;
        }
        String matchSinglePrefixedField = ResultParser.matchSinglePrefixedField("S:", result, ';', false);
        if (matchSinglePrefixedField != null) {
            if (!matchSinglePrefixedField.isEmpty()) {
                String matchSinglePrefixedField2 = ResultParser.matchSinglePrefixedField("P:", result, ';', false);
                String matchSinglePrefixedField3 = ResultParser.matchSinglePrefixedField("T:", result, ';', false);
                if (matchSinglePrefixedField3 == null) {
                    matchSinglePrefixedField3 = "nopass";
                }
                return new WifiParsedResult(matchSinglePrefixedField3, matchSinglePrefixedField, matchSinglePrefixedField2, Boolean.parseBoolean(ResultParser.matchSinglePrefixedField("H:", result, ';', false)));
            }
        }
        return null;
    }
}
