package com.google.zxing.client.result;

public final class ExpandedProductResultParser extends ResultParser {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.client.result.ExpandedProductParsedResult parse(com.google.zxing.Result r25) {
        /*
        r24 = this;
        r0 = r25.getBarcodeFormat();
        r1 = com.google.zxing.BarcodeFormat.RSS_EXPANDED;
        r2 = 0;
        if (r0 == r1) goto L_0x000a;
    L_0x0009:
        return r2;
    L_0x000a:
        r4 = com.google.zxing.client.result.ResultParser.getMassagedText(r25);
        r0 = new java.util.HashMap;
        r0.<init>();
        r5 = r2;
        r6 = r5;
        r7 = r6;
        r8 = r7;
        r9 = r8;
        r10 = r9;
        r11 = r10;
        r12 = r11;
        r13 = r12;
        r14 = r13;
        r15 = r14;
        r16 = r15;
        r17 = r16;
        r3 = 0;
    L_0x0023:
        r1 = r4.length();
        if (r3 >= r1) goto L_0x028b;
    L_0x0029:
        r1 = findAIvalue(r3, r4);
        if (r1 != 0) goto L_0x0030;
    L_0x002f:
        return r2;
    L_0x0030:
        r18 = r1.length();
        r19 = 2;
        r18 = r18 + 2;
        r3 = r3 + r18;
        r2 = findValue(r3, r4);
        r20 = r2.length();
        r3 = r3 + r20;
        r20 = -1;
        r21 = r3;
        r3 = r1.hashCode();
        r22 = r15;
        r15 = 1570; // 0x622 float:2.2E-42 double:7.757E-321;
        r23 = r14;
        r14 = 3;
        if (r3 == r15) goto L_0x01f9;
    L_0x0055:
        r15 = 1572; // 0x624 float:2.203E-42 double:7.767E-321;
        if (r3 == r15) goto L_0x01ef;
    L_0x0059:
        r15 = 1574; // 0x626 float:2.206E-42 double:7.777E-321;
        if (r3 == r15) goto L_0x01e5;
    L_0x005d:
        switch(r3) {
            case 1536: goto L_0x01db;
            case 1537: goto L_0x01d1;
            default: goto L_0x0060;
        };
    L_0x0060:
        switch(r3) {
            case 1567: goto L_0x01c7;
            case 1568: goto L_0x01bd;
            default: goto L_0x0063;
        };
    L_0x0063:
        switch(r3) {
            case 1567966: goto L_0x01b3;
            case 1567967: goto L_0x01a8;
            case 1567968: goto L_0x019d;
            case 1567969: goto L_0x0191;
            case 1567970: goto L_0x0185;
            case 1567971: goto L_0x0179;
            case 1567972: goto L_0x016d;
            case 1567973: goto L_0x0161;
            case 1567974: goto L_0x0155;
            case 1567975: goto L_0x0149;
            default: goto L_0x0066;
        };
    L_0x0066:
        switch(r3) {
            case 1568927: goto L_0x013d;
            case 1568928: goto L_0x0131;
            case 1568929: goto L_0x0125;
            case 1568930: goto L_0x0119;
            case 1568931: goto L_0x010d;
            case 1568932: goto L_0x0101;
            case 1568933: goto L_0x00f5;
            case 1568934: goto L_0x00e9;
            case 1568935: goto L_0x00dd;
            case 1568936: goto L_0x00d1;
            default: goto L_0x0069;
        };
    L_0x0069:
        switch(r3) {
            case 1575716: goto L_0x00c5;
            case 1575717: goto L_0x00b9;
            case 1575718: goto L_0x00ad;
            case 1575719: goto L_0x00a1;
            default: goto L_0x006c;
        };
    L_0x006c:
        switch(r3) {
            case 1575747: goto L_0x0095;
            case 1575748: goto L_0x0089;
            case 1575749: goto L_0x007d;
            case 1575750: goto L_0x0071;
            default: goto L_0x006f;
        };
    L_0x006f:
        goto L_0x0203;
    L_0x0071:
        r3 = "3933";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0079:
        r3 = 34;
        goto L_0x0204;
    L_0x007d:
        r3 = "3932";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0085:
        r3 = 33;
        goto L_0x0204;
    L_0x0089:
        r3 = "3931";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0091:
        r3 = 32;
        goto L_0x0204;
    L_0x0095:
        r3 = "3930";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x009d:
        r3 = 31;
        goto L_0x0204;
    L_0x00a1:
        r3 = "3923";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x00a9:
        r3 = 30;
        goto L_0x0204;
    L_0x00ad:
        r3 = "3922";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x00b5:
        r3 = 29;
        goto L_0x0204;
    L_0x00b9:
        r3 = "3921";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x00c1:
        r3 = 28;
        goto L_0x0204;
    L_0x00c5:
        r3 = "3920";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x00cd:
        r3 = 27;
        goto L_0x0204;
    L_0x00d1:
        r3 = "3209";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x00d9:
        r3 = 26;
        goto L_0x0204;
    L_0x00dd:
        r3 = "3208";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x00e5:
        r3 = 25;
        goto L_0x0204;
    L_0x00e9:
        r3 = "3207";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x00f1:
        r3 = 24;
        goto L_0x0204;
    L_0x00f5:
        r3 = "3206";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x00fd:
        r3 = 23;
        goto L_0x0204;
    L_0x0101:
        r3 = "3205";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0109:
        r3 = 22;
        goto L_0x0204;
    L_0x010d:
        r3 = "3204";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0115:
        r3 = 21;
        goto L_0x0204;
    L_0x0119:
        r3 = "3203";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0121:
        r3 = 20;
        goto L_0x0204;
    L_0x0125:
        r3 = "3202";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x012d:
        r3 = 19;
        goto L_0x0204;
    L_0x0131:
        r3 = "3201";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0139:
        r3 = 18;
        goto L_0x0204;
    L_0x013d:
        r3 = "3200";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0145:
        r3 = 17;
        goto L_0x0204;
    L_0x0149:
        r3 = "3109";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0151:
        r3 = 16;
        goto L_0x0204;
    L_0x0155:
        r3 = "3108";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x015d:
        r3 = 15;
        goto L_0x0204;
    L_0x0161:
        r3 = "3107";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0169:
        r3 = 14;
        goto L_0x0204;
    L_0x016d:
        r3 = "3106";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0175:
        r3 = 13;
        goto L_0x0204;
    L_0x0179:
        r3 = "3105";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0181:
        r3 = 12;
        goto L_0x0204;
    L_0x0185:
        r3 = "3104";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x018d:
        r3 = 11;
        goto L_0x0204;
    L_0x0191:
        r3 = "3103";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0199:
        r3 = 10;
        goto L_0x0204;
    L_0x019d:
        r3 = "3102";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x01a5:
        r3 = 9;
        goto L_0x0204;
    L_0x01a8:
        r3 = "3101";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x01b0:
        r3 = 8;
        goto L_0x0204;
    L_0x01b3:
        r3 = "3100";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x01bb:
        r3 = 7;
        goto L_0x0204;
    L_0x01bd:
        r3 = "11";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x01c5:
        r3 = 3;
        goto L_0x0204;
    L_0x01c7:
        r3 = "10";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x01cf:
        r3 = 2;
        goto L_0x0204;
    L_0x01d1:
        r3 = "01";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x01d9:
        r3 = 1;
        goto L_0x0204;
    L_0x01db:
        r3 = "00";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x01e3:
        r3 = 0;
        goto L_0x0204;
    L_0x01e5:
        r3 = "17";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x01ed:
        r3 = 6;
        goto L_0x0204;
    L_0x01ef:
        r3 = "15";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x01f7:
        r3 = 5;
        goto L_0x0204;
    L_0x01f9:
        r3 = "13";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0203;
    L_0x0201:
        r3 = 4;
        goto L_0x0204;
    L_0x0203:
        r3 = -1;
    L_0x0204:
        switch(r3) {
            case 0: goto L_0x0280;
            case 1: goto L_0x0279;
            case 2: goto L_0x0272;
            case 3: goto L_0x026b;
            case 4: goto L_0x0264;
            case 5: goto L_0x025d;
            case 6: goto L_0x0256;
            case 7: goto L_0x0249;
            case 8: goto L_0x0249;
            case 9: goto L_0x0249;
            case 10: goto L_0x0249;
            case 11: goto L_0x0249;
            case 12: goto L_0x0249;
            case 13: goto L_0x0249;
            case 14: goto L_0x0249;
            case 15: goto L_0x0249;
            case 16: goto L_0x0249;
            case 17: goto L_0x023c;
            case 18: goto L_0x023c;
            case 19: goto L_0x023c;
            case 20: goto L_0x023c;
            case 21: goto L_0x023c;
            case 22: goto L_0x023c;
            case 23: goto L_0x023c;
            case 24: goto L_0x023c;
            case 25: goto L_0x023c;
            case 26: goto L_0x023c;
            case 27: goto L_0x0230;
            case 28: goto L_0x0230;
            case 29: goto L_0x0230;
            case 30: goto L_0x0230;
            case 31: goto L_0x0211;
            case 32: goto L_0x0211;
            case 33: goto L_0x0211;
            case 34: goto L_0x0211;
            default: goto L_0x0207;
        };
    L_0x0207:
        r3 = 0;
        r0.put(r1, r2);
        r15 = r22;
        r14 = r23;
        goto L_0x0286;
    L_0x0211:
        r3 = r2.length();
        r15 = 4;
        if (r3 >= r15) goto L_0x021a;
    L_0x0218:
        r3 = 0;
        return r3;
    L_0x021a:
        r3 = 0;
        r15 = r2.substring(r14);
        r3 = 0;
        r2 = r2.substring(r3, r14);
        r1 = r1.substring(r14);
        r16 = r1;
        r17 = r2;
        r14 = r23;
        goto L_0x0286;
    L_0x0230:
        r3 = 0;
        r1 = r1.substring(r14);
        r16 = r1;
        r15 = r2;
        r14 = r23;
        goto L_0x0286;
    L_0x023c:
        r3 = 0;
        r12 = "LB";
        r1 = r1.substring(r14);
        r14 = r1;
        r13 = r12;
        r15 = r22;
        r12 = r2;
        goto L_0x0286;
    L_0x0249:
        r3 = 0;
        r12 = "KG";
        r1 = r1.substring(r14);
        r14 = r1;
        r13 = r12;
        r15 = r22;
        r12 = r2;
        goto L_0x0286;
    L_0x0256:
        r3 = 0;
        r11 = r2;
        r15 = r22;
        r14 = r23;
        goto L_0x0286;
    L_0x025d:
        r3 = 0;
        r10 = r2;
        r15 = r22;
        r14 = r23;
        goto L_0x0286;
    L_0x0264:
        r3 = 0;
        r9 = r2;
        r15 = r22;
        r14 = r23;
        goto L_0x0286;
    L_0x026b:
        r3 = 0;
        r8 = r2;
        r15 = r22;
        r14 = r23;
        goto L_0x0286;
    L_0x0272:
        r3 = 0;
        r7 = r2;
        r15 = r22;
        r14 = r23;
        goto L_0x0286;
    L_0x0279:
        r3 = 0;
        r5 = r2;
        r15 = r22;
        r14 = r23;
        goto L_0x0286;
    L_0x0280:
        r3 = 0;
        r6 = r2;
        r15 = r22;
        r14 = r23;
    L_0x0286:
        r3 = r21;
        r2 = 0;
        goto L_0x0023;
    L_0x028b:
        r23 = r14;
        r22 = r15;
        r1 = new com.google.zxing.client.result.ExpandedProductParsedResult;
        r3 = r1;
        r18 = r0;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.ExpandedProductResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.ExpandedProductParsedResult");
    }

    private static String findAIvalue(int i, String str) {
        if (str.charAt(i) != '(') {
            return null;
        }
        i = str.substring(i + 1);
        str = new StringBuilder();
        for (int i2 = 0; i2 < i.length(); i2++) {
            char charAt = i.charAt(i2);
            if (charAt == ')') {
                return str.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            str.append(charAt);
        }
        return str.toString();
    }

    private static String findValue(int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        i = str.substring(i);
        for (str = null; str < i.length(); str++) {
            char charAt = i.charAt(str);
            if (charAt == '(') {
                if (findAIvalue(str, i) != null) {
                    break;
                }
                stringBuilder.append('(');
            } else {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }
}
