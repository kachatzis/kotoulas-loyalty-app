package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;

public final class AddressBookAUResultParser extends ResultParser {
    public AddressBookParsedResult parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        AddressBookParsedResult addressBookParsedResult = null;
        if (massagedText.contains("MEMORY")) {
            if (massagedText.contains("\r\n")) {
                String matchSinglePrefixedField = ResultParser.matchSinglePrefixedField("NAME1:", massagedText, '\r', true);
                String matchSinglePrefixedField2 = ResultParser.matchSinglePrefixedField("NAME2:", massagedText, '\r', true);
                String[] matchMultipleValuePrefix = matchMultipleValuePrefix("TEL", 3, massagedText, true);
                String[] matchMultipleValuePrefix2 = matchMultipleValuePrefix("MAIL", 3, massagedText, true);
                String matchSinglePrefixedField3 = ResultParser.matchSinglePrefixedField("MEMORY:", massagedText, '\r', false);
                if (ResultParser.matchSinglePrefixedField("ADD:", massagedText, '\r', true) != null) {
                    addressBookParsedResult = new String[]{ResultParser.matchSinglePrefixedField("ADD:", massagedText, '\r', true)};
                }
                return new AddressBookParsedResult(ResultParser.maybeWrap(matchSinglePrefixedField), null, matchSinglePrefixedField2, matchMultipleValuePrefix, null, matchMultipleValuePrefix2, null, null, matchSinglePrefixedField3, addressBookParsedResult, null, null, null, null, null, null);
            }
        }
        return null;
    }

    private static String[] matchMultipleValuePrefix(String str, int i, String str2, boolean z) {
        List list = null;
        for (int i2 = 1; i2 <= i; i2++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(i2);
            stringBuilder.append(':');
            String matchSinglePrefixedField = ResultParser.matchSinglePrefixedField(stringBuilder.toString(), str2, '\r', z);
            if (matchSinglePrefixedField == null) {
                break;
            }
            if (list == null) {
                list = new ArrayList(i);
            }
            list.add(matchSinglePrefixedField);
        }
        if (list == null) {
            return null;
        }
        return (String[]) list.toArray(new String[list.size()]);
    }
}
