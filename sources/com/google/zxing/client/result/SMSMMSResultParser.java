package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class SMSMMSResultParser extends ResultParser {
    public SMSParsedResult parse(Result result) {
        result = ResultParser.getMassagedText(result);
        String str = null;
        if (!result.startsWith("sms:") && !result.startsWith("SMS:") && !result.startsWith("mms:") && !result.startsWith("MMS:")) {
            return null;
        }
        String str2;
        int i;
        List arrayList;
        List arrayList2;
        int i2;
        int indexOf;
        Map parseNameValuePairs = ResultParser.parseNameValuePairs(result);
        Object obj = null;
        if (parseNameValuePairs == null || parseNameValuePairs.isEmpty()) {
            str2 = null;
        } else {
            str = (String) parseNameValuePairs.get("subject");
            str2 = (String) parseNameValuePairs.get("body");
            obj = 1;
        }
        int indexOf2 = result.indexOf(63, 4);
        if (indexOf2 >= 0) {
            if (obj != null) {
                result = result.substring(4, indexOf2);
                i = -1;
                arrayList = new ArrayList(1);
                arrayList2 = new ArrayList(1);
                while (true) {
                    i2 = i + 1;
                    indexOf = result.indexOf(44, i2);
                    if (indexOf <= i) {
                        addNumberVia(arrayList, arrayList2, result.substring(i2, indexOf));
                        i = indexOf;
                    } else {
                        addNumberVia(arrayList, arrayList2, result.substring(i2));
                        return new SMSParsedResult((String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), str, str2);
                    }
                }
            }
        }
        result = result.substring(4);
        i = -1;
        arrayList = new ArrayList(1);
        arrayList2 = new ArrayList(1);
        while (true) {
            i2 = i + 1;
            indexOf = result.indexOf(44, i2);
            if (indexOf <= i) {
                addNumberVia(arrayList, arrayList2, result.substring(i2));
                return new SMSParsedResult((String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), str, str2);
            }
            addNumberVia(arrayList, arrayList2, result.substring(i2, indexOf));
            i = indexOf;
        }
    }

    private static void addNumberVia(Collection<String> collection, Collection<String> collection2, String str) {
        int indexOf = str.indexOf(59);
        Object obj = null;
        if (indexOf < 0) {
            collection.add(str);
            collection2.add(null);
            return;
        }
        collection.add(str.substring(0, indexOf));
        collection = str.substring(indexOf + 1);
        if (collection.startsWith("via=") != null) {
            obj = collection.substring(4);
        }
        collection2.add(obj);
    }
}
