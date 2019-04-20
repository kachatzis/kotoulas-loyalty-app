package com.google.zxing.client.android;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.google.zxing.DecodeHintType;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class DecodeHintManager {
    private static final Pattern COMMA = Pattern.compile(",");
    private static final String TAG = "DecodeHintManager";

    private DecodeHintManager() {
    }

    private static Map<String, String> splitQuery(String str) {
        Map<String, String> hashMap = new HashMap();
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '&') {
                i++;
            } else {
                int indexOf = str.indexOf(38, i);
                int indexOf2 = str.indexOf(61, i);
                if (indexOf < 0) {
                    Object obj;
                    if (indexOf2 < 0) {
                        str = Uri.decode(str.substring(i).replace('+', ' '));
                        obj = "";
                    } else {
                        String decode = Uri.decode(str.substring(i, indexOf2).replace('+', ' '));
                        obj = Uri.decode(str.substring(indexOf2 + 1).replace('+', ' '));
                        str = decode;
                    }
                    if (!hashMap.containsKey(str)) {
                        hashMap.put(str, obj);
                    }
                    return hashMap;
                }
                String decode2;
                if (indexOf2 >= 0) {
                    if (indexOf2 <= indexOf) {
                        decode2 = Uri.decode(str.substring(i, indexOf2).replace('+', ' '));
                        String decode3 = Uri.decode(str.substring(indexOf2 + 1, indexOf).replace('+', ' '));
                        if (!hashMap.containsKey(decode2)) {
                            hashMap.put(decode2, decode3);
                        }
                        i = indexOf + 1;
                    }
                }
                decode2 = Uri.decode(str.substring(i, indexOf).replace('+', ' '));
                if (!hashMap.containsKey(decode2)) {
                    hashMap.put(decode2, "");
                }
                i = indexOf + 1;
            }
        }
        return hashMap;
    }

    static java.util.Map<com.google.zxing.DecodeHintType, ?> parseDecodeHints(android.net.Uri r12) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r12 = r12.getEncodedQuery();
        r0 = 0;
        if (r12 == 0) goto L_0x0161;
    L_0x0007:
        r1 = r12.isEmpty();
        if (r1 == 0) goto L_0x000f;
    L_0x000d:
        goto L_0x0161;
    L_0x000f:
        r12 = splitQuery(r12);
        r1 = new java.util.EnumMap;
        r2 = com.google.zxing.DecodeHintType.class;
        r1.<init>(r2);
        r2 = com.google.zxing.DecodeHintType.values();
        r3 = r2.length;
        r4 = 0;
        r5 = 0;
    L_0x0021:
        if (r5 >= r3) goto L_0x014a;
    L_0x0023:
        r6 = r2[r5];
        r7 = com.google.zxing.DecodeHintType.CHARACTER_SET;
        if (r6 == r7) goto L_0x0146;
    L_0x0029:
        r7 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        if (r6 == r7) goto L_0x0146;
    L_0x002d:
        r7 = com.google.zxing.DecodeHintType.POSSIBLE_FORMATS;
        if (r6 != r7) goto L_0x0033;
    L_0x0031:
        goto L_0x0146;
    L_0x0033:
        r7 = r6.name();
        r7 = r12.get(r7);
        r7 = (java.lang.String) r7;
        if (r7 != 0) goto L_0x0041;
    L_0x003f:
        goto L_0x0146;
    L_0x0041:
        r8 = r6.getValueType();
        r9 = java.lang.Object.class;
        r8 = r8.equals(r9);
        if (r8 == 0) goto L_0x0052;
    L_0x004d:
        r1.put(r6, r7);
        goto L_0x0146;
    L_0x0052:
        r8 = r6.getValueType();
        r9 = java.lang.Void.class;
        r8 = r8.equals(r9);
        if (r8 == 0) goto L_0x0065;
    L_0x005e:
        r7 = java.lang.Boolean.TRUE;
        r1.put(r6, r7);
        goto L_0x0146;
    L_0x0065:
        r8 = r6.getValueType();
        r9 = java.lang.String.class;
        r8 = r8.equals(r9);
        if (r8 == 0) goto L_0x0076;
    L_0x0071:
        r1.put(r6, r7);
        goto L_0x0146;
    L_0x0076:
        r8 = r6.getValueType();
        r9 = java.lang.Boolean.class;
        r8 = r8.equals(r9);
        if (r8 == 0) goto L_0x00b6;
    L_0x0082:
        r8 = r7.isEmpty();
        if (r8 == 0) goto L_0x008f;
    L_0x0088:
        r7 = java.lang.Boolean.TRUE;
        r1.put(r6, r7);
        goto L_0x0146;
    L_0x008f:
        r8 = "0";
        r8 = r8.equals(r7);
        if (r8 != 0) goto L_0x00af;
    L_0x0097:
        r8 = "false";
        r8 = r8.equalsIgnoreCase(r7);
        if (r8 != 0) goto L_0x00af;
    L_0x009f:
        r8 = "no";
        r7 = r8.equalsIgnoreCase(r7);
        if (r7 == 0) goto L_0x00a8;
    L_0x00a7:
        goto L_0x00af;
    L_0x00a8:
        r7 = java.lang.Boolean.TRUE;
        r1.put(r6, r7);
        goto L_0x0146;
    L_0x00af:
        r7 = java.lang.Boolean.FALSE;
        r1.put(r6, r7);
        goto L_0x0146;
    L_0x00b6:
        r8 = r6.getValueType();
        r9 = int[].class;
        r8 = r8.equals(r9);
        if (r8 == 0) goto L_0x0124;
    L_0x00c2:
        r8 = r7.isEmpty();
        if (r8 != 0) goto L_0x00e0;
    L_0x00c8:
        r8 = r7.length();
        r8 = r8 + -1;
        r8 = r7.charAt(r8);
        r9 = 44;
        if (r8 != r9) goto L_0x00e0;
    L_0x00d6:
        r8 = r7.length();
        r8 = r8 + -1;
        r7 = r7.substring(r4, r8);
    L_0x00e0:
        r8 = COMMA;
        r7 = r8.split(r7);
        r8 = r7.length;
        r8 = new int[r8];
        r9 = 0;
    L_0x00ea:
        r10 = r7.length;
        if (r9 >= r10) goto L_0x011e;
    L_0x00ed:
        r10 = r7[r9];	 Catch:{ NumberFormatException -> 0x00f8 }
        r10 = java.lang.Integer.parseInt(r10);	 Catch:{ NumberFormatException -> 0x00f8 }
        r8[r9] = r10;	 Catch:{ NumberFormatException -> 0x00f8 }
        r9 = r9 + 1;
        goto L_0x00ea;
    L_0x00f8:
        r8 = TAG;
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r11 = "Skipping array of integers hint ";
        r10.append(r11);
        r10.append(r6);
        r11 = " due to invalid numeric value: '";
        r10.append(r11);
        r7 = r7[r9];
        r10.append(r7);
        r7 = 39;
        r10.append(r7);
        r7 = r10.toString();
        android.util.Log.w(r8, r7);
        r8 = r0;
    L_0x011e:
        if (r8 == 0) goto L_0x0146;
    L_0x0120:
        r1.put(r6, r8);
        goto L_0x0146;
    L_0x0124:
        r7 = TAG;
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "Unsupported hint type '";
        r8.append(r9);
        r8.append(r6);
        r9 = "' of type ";
        r8.append(r9);
        r6 = r6.getValueType();
        r8.append(r6);
        r6 = r8.toString();
        android.util.Log.w(r7, r6);
    L_0x0146:
        r5 = r5 + 1;
        goto L_0x0021;
    L_0x014a:
        r12 = TAG;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "Hints from the URI: ";
        r0.append(r2);
        r0.append(r1);
        r0 = r0.toString();
        android.util.Log.i(r12, r0);
        return r1;
    L_0x0161:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.android.DecodeHintManager.parseDecodeHints(android.net.Uri):java.util.Map<com.google.zxing.DecodeHintType, ?>");
    }

    public static Map<DecodeHintType, Object> parseDecodeHints(Intent intent) {
        intent = intent.getExtras();
        if (intent != null) {
            if (!intent.isEmpty()) {
                Map<DecodeHintType, Object> enumMap = new EnumMap(DecodeHintType.class);
                for (DecodeHintType decodeHintType : DecodeHintType.values()) {
                    if (!(decodeHintType == DecodeHintType.CHARACTER_SET || decodeHintType == DecodeHintType.NEED_RESULT_POINT_CALLBACK)) {
                        if (decodeHintType != DecodeHintType.POSSIBLE_FORMATS) {
                            String name = decodeHintType.name();
                            if (intent.containsKey(name)) {
                                if (decodeHintType.getValueType().equals(Void.class)) {
                                    enumMap.put(decodeHintType, Boolean.TRUE);
                                } else {
                                    Object obj = intent.get(name);
                                    if (decodeHintType.getValueType().isInstance(obj)) {
                                        enumMap.put(decodeHintType, obj);
                                    } else {
                                        String str = TAG;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("Ignoring hint ");
                                        stringBuilder.append(decodeHintType);
                                        stringBuilder.append(" because it is not assignable from ");
                                        stringBuilder.append(obj);
                                        Log.w(str, stringBuilder.toString());
                                    }
                                }
                            }
                        }
                    }
                }
                intent = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Hints from the Intent: ");
                stringBuilder2.append(enumMap);
                Log.i(intent, stringBuilder2.toString());
                return enumMap;
            }
        }
        return null;
    }
}
