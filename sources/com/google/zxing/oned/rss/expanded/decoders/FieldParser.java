package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;

final class FieldParser {
    private static final Object[][] FOUR_DIGIT_DATA_LENGTH;
    private static final Object[][] THREE_DIGIT_DATA_LENGTH;
    private static final Object[][] THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
    private static final Object[][] TWO_DIGIT_DATA_LENGTH;
    private static final Object VARIABLE_LENGTH = new Object();

    static java.lang.String parseFieldsInGeneralPurpose(java.lang.String r10) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:64:0x010b in {3, 14, 16, 17, 27, 29, 30, 39, 41, 42, 52, 54, 55, 57, 59, 61, 63} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r0 = r10.isEmpty();
        if (r0 == 0) goto L_0x0008;
    L_0x0006:
        r10 = 0;
        return r10;
    L_0x0008:
        r0 = r10.length();
        r1 = 2;
        if (r0 < r1) goto L_0x0106;
    L_0x000f:
        r0 = 0;
        r2 = r10.substring(r0, r1);
        r3 = TWO_DIGIT_DATA_LENGTH;
        r4 = r3.length;
        r5 = 0;
    L_0x0018:
        r6 = 1;
        if (r5 >= r4) goto L_0x0048;
    L_0x001b:
        r7 = r3[r5];
        r8 = r7[r0];
        r8 = r8.equals(r2);
        if (r8 == 0) goto L_0x0045;
    L_0x0025:
        r0 = r7[r6];
        r2 = VARIABLE_LENGTH;
        if (r0 != r2) goto L_0x0038;
    L_0x002b:
        r0 = r7[r1];
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r10 = processVariableAI(r1, r0, r10);
        return r10;
    L_0x0038:
        r0 = r7[r6];
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r10 = processFixedAI(r1, r0, r10);
        return r10;
    L_0x0045:
        r5 = r5 + 1;
        goto L_0x0018;
    L_0x0048:
        r2 = r10.length();
        r3 = 3;
        if (r2 < r3) goto L_0x0101;
    L_0x004f:
        r2 = r10.substring(r0, r3);
        r4 = THREE_DIGIT_DATA_LENGTH;
        r5 = r4.length;
        r7 = 0;
    L_0x0057:
        if (r7 >= r5) goto L_0x0086;
    L_0x0059:
        r8 = r4[r7];
        r9 = r8[r0];
        r9 = r9.equals(r2);
        if (r9 == 0) goto L_0x0083;
    L_0x0063:
        r0 = r8[r6];
        r2 = VARIABLE_LENGTH;
        if (r0 != r2) goto L_0x0076;
    L_0x0069:
        r0 = r8[r1];
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r10 = processVariableAI(r3, r0, r10);
        return r10;
    L_0x0076:
        r0 = r8[r6];
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r10 = processFixedAI(r3, r0, r10);
        return r10;
    L_0x0083:
        r7 = r7 + 1;
        goto L_0x0057;
    L_0x0086:
        r3 = THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
        r4 = r3.length;
        r5 = 0;
    L_0x008a:
        r7 = 4;
        if (r5 >= r4) goto L_0x00ba;
    L_0x008d:
        r8 = r3[r5];
        r9 = r8[r0];
        r9 = r9.equals(r2);
        if (r9 == 0) goto L_0x00b7;
    L_0x0097:
        r0 = r8[r6];
        r2 = VARIABLE_LENGTH;
        if (r0 != r2) goto L_0x00aa;
    L_0x009d:
        r0 = r8[r1];
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r10 = processVariableAI(r7, r0, r10);
        return r10;
    L_0x00aa:
        r0 = r8[r6];
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r10 = processFixedAI(r7, r0, r10);
        return r10;
    L_0x00b7:
        r5 = r5 + 1;
        goto L_0x008a;
    L_0x00ba:
        r2 = r10.length();
        if (r2 < r7) goto L_0x00fc;
    L_0x00c0:
        r2 = r10.substring(r0, r7);
        r3 = FOUR_DIGIT_DATA_LENGTH;
        r4 = r3.length;
        r5 = 0;
    L_0x00c8:
        if (r5 >= r4) goto L_0x00f7;
    L_0x00ca:
        r8 = r3[r5];
        r9 = r8[r0];
        r9 = r9.equals(r2);
        if (r9 == 0) goto L_0x00f4;
    L_0x00d4:
        r0 = r8[r6];
        r2 = VARIABLE_LENGTH;
        if (r0 != r2) goto L_0x00e7;
    L_0x00da:
        r0 = r8[r1];
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r10 = processVariableAI(r7, r0, r10);
        return r10;
    L_0x00e7:
        r0 = r8[r6];
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r10 = processFixedAI(r7, r0, r10);
        return r10;
    L_0x00f4:
        r5 = r5 + 1;
        goto L_0x00c8;
    L_0x00f7:
        r10 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r10;
    L_0x00fc:
        r10 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r10;
    L_0x0101:
        r10 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r10;
    L_0x0106:
        r10 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.decoders.FieldParser.parseFieldsInGeneralPurpose(java.lang.String):java.lang.String");
    }

    static {
        r0 = new Object[24][];
        r0[0] = new Object[]{"00", Integer.valueOf(18)};
        r0[1] = new Object[]{"01", Integer.valueOf(14)};
        r0[2] = new Object[]{"02", Integer.valueOf(14)};
        r0[3] = new Object[]{"10", VARIABLE_LENGTH, Integer.valueOf(20)};
        r0[4] = new Object[]{"11", Integer.valueOf(6)};
        r0[5] = new Object[]{"12", Integer.valueOf(6)};
        r0[6] = new Object[]{"13", Integer.valueOf(6)};
        r0[7] = new Object[]{"15", Integer.valueOf(6)};
        r0[8] = new Object[]{"17", Integer.valueOf(6)};
        r0[9] = new Object[]{"20", Integer.valueOf(2)};
        r0[10] = new Object[]{"21", VARIABLE_LENGTH, Integer.valueOf(20)};
        r0[11] = new Object[]{"22", VARIABLE_LENGTH, Integer.valueOf(29)};
        r0[12] = new Object[]{"30", VARIABLE_LENGTH, Integer.valueOf(8)};
        r0[13] = new Object[]{"37", VARIABLE_LENGTH, Integer.valueOf(8)};
        r0[14] = new Object[]{"90", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[15] = new Object[]{"91", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[16] = new Object[]{"92", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[17] = new Object[]{"93", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[18] = new Object[]{"94", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[19] = new Object[]{"95", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[20] = new Object[]{"96", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[21] = new Object[]{"97", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[22] = new Object[]{"98", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[23] = new Object[]{"99", VARIABLE_LENGTH, Integer.valueOf(30)};
        TWO_DIGIT_DATA_LENGTH = r0;
        r0 = new Object[23][];
        r0[0] = new Object[]{"240", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[1] = new Object[]{"241", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[2] = new Object[]{"242", VARIABLE_LENGTH, Integer.valueOf(6)};
        r0[3] = new Object[]{"250", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[4] = new Object[]{"251", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[5] = new Object[]{"253", VARIABLE_LENGTH, Integer.valueOf(17)};
        r0[6] = new Object[]{"254", VARIABLE_LENGTH, Integer.valueOf(20)};
        r0[7] = new Object[]{"400", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[8] = new Object[]{"401", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[9] = new Object[]{"402", Integer.valueOf(17)};
        r0[10] = new Object[]{"403", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[11] = new Object[]{"410", Integer.valueOf(13)};
        r0[12] = new Object[]{"411", Integer.valueOf(13)};
        r0[13] = new Object[]{"412", Integer.valueOf(13)};
        r0[14] = new Object[]{"413", Integer.valueOf(13)};
        r0[15] = new Object[]{"414", Integer.valueOf(13)};
        r0[16] = new Object[]{"420", VARIABLE_LENGTH, Integer.valueOf(20)};
        r0[17] = new Object[]{"421", VARIABLE_LENGTH, Integer.valueOf(15)};
        r0[18] = new Object[]{"422", Integer.valueOf(3)};
        r0[19] = new Object[]{"423", VARIABLE_LENGTH, Integer.valueOf(15)};
        r0[20] = new Object[]{"424", Integer.valueOf(3)};
        r0[21] = new Object[]{"425", Integer.valueOf(3)};
        r0[22] = new Object[]{"426", Integer.valueOf(3)};
        THREE_DIGIT_DATA_LENGTH = r0;
        r0 = new Object[57][];
        r0[0] = new Object[]{"310", Integer.valueOf(6)};
        r0[1] = new Object[]{"311", Integer.valueOf(6)};
        r0[2] = new Object[]{"312", Integer.valueOf(6)};
        r0[3] = new Object[]{"313", Integer.valueOf(6)};
        r0[4] = new Object[]{"314", Integer.valueOf(6)};
        r0[5] = new Object[]{"315", Integer.valueOf(6)};
        r0[6] = new Object[]{"316", Integer.valueOf(6)};
        r0[7] = new Object[]{"320", Integer.valueOf(6)};
        r0[8] = new Object[]{"321", Integer.valueOf(6)};
        r0[9] = new Object[]{"322", Integer.valueOf(6)};
        r0[10] = new Object[]{"323", Integer.valueOf(6)};
        r0[11] = new Object[]{"324", Integer.valueOf(6)};
        r0[12] = new Object[]{"325", Integer.valueOf(6)};
        r0[13] = new Object[]{"326", Integer.valueOf(6)};
        r0[14] = new Object[]{"327", Integer.valueOf(6)};
        r0[15] = new Object[]{"328", Integer.valueOf(6)};
        r0[16] = new Object[]{"329", Integer.valueOf(6)};
        r0[17] = new Object[]{"330", Integer.valueOf(6)};
        r0[18] = new Object[]{"331", Integer.valueOf(6)};
        r0[19] = new Object[]{"332", Integer.valueOf(6)};
        r0[20] = new Object[]{"333", Integer.valueOf(6)};
        r0[21] = new Object[]{"334", Integer.valueOf(6)};
        r0[22] = new Object[]{"335", Integer.valueOf(6)};
        r0[23] = new Object[]{"336", Integer.valueOf(6)};
        r0[24] = new Object[]{"340", Integer.valueOf(6)};
        r0[25] = new Object[]{"341", Integer.valueOf(6)};
        r0[26] = new Object[]{"342", Integer.valueOf(6)};
        r0[27] = new Object[]{"343", Integer.valueOf(6)};
        r0[28] = new Object[]{"344", Integer.valueOf(6)};
        r0[29] = new Object[]{"345", Integer.valueOf(6)};
        r0[30] = new Object[]{"346", Integer.valueOf(6)};
        r0[31] = new Object[]{"347", Integer.valueOf(6)};
        r0[32] = new Object[]{"348", Integer.valueOf(6)};
        r0[33] = new Object[]{"349", Integer.valueOf(6)};
        r0[34] = new Object[]{"350", Integer.valueOf(6)};
        r0[35] = new Object[]{"351", Integer.valueOf(6)};
        r0[36] = new Object[]{"352", Integer.valueOf(6)};
        r0[37] = new Object[]{"353", Integer.valueOf(6)};
        r0[38] = new Object[]{"354", Integer.valueOf(6)};
        r0[39] = new Object[]{"355", Integer.valueOf(6)};
        r0[40] = new Object[]{"356", Integer.valueOf(6)};
        r0[41] = new Object[]{"357", Integer.valueOf(6)};
        r0[42] = new Object[]{"360", Integer.valueOf(6)};
        r0[43] = new Object[]{"361", Integer.valueOf(6)};
        r0[44] = new Object[]{"362", Integer.valueOf(6)};
        r0[45] = new Object[]{"363", Integer.valueOf(6)};
        r0[46] = new Object[]{"364", Integer.valueOf(6)};
        r0[47] = new Object[]{"365", Integer.valueOf(6)};
        r0[48] = new Object[]{"366", Integer.valueOf(6)};
        r0[49] = new Object[]{"367", Integer.valueOf(6)};
        r0[50] = new Object[]{"368", Integer.valueOf(6)};
        r0[51] = new Object[]{"369", Integer.valueOf(6)};
        r0[52] = new Object[]{"390", VARIABLE_LENGTH, Integer.valueOf(15)};
        r0[53] = new Object[]{"391", VARIABLE_LENGTH, Integer.valueOf(18)};
        r0[54] = new Object[]{"392", VARIABLE_LENGTH, Integer.valueOf(15)};
        r0[55] = new Object[]{"393", VARIABLE_LENGTH, Integer.valueOf(18)};
        r0[56] = new Object[]{"703", VARIABLE_LENGTH, Integer.valueOf(30)};
        THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH = r0;
        r0 = new Object[18][];
        r0[0] = new Object[]{"7001", Integer.valueOf(13)};
        r0[1] = new Object[]{"7002", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[2] = new Object[]{"7003", Integer.valueOf(10)};
        r0[3] = new Object[]{"8001", Integer.valueOf(14)};
        r0[4] = new Object[]{"8002", VARIABLE_LENGTH, Integer.valueOf(20)};
        r0[5] = new Object[]{"8003", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[6] = new Object[]{"8004", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[7] = new Object[]{"8005", Integer.valueOf(6)};
        r0[8] = new Object[]{"8006", Integer.valueOf(18)};
        r0[9] = new Object[]{"8007", VARIABLE_LENGTH, Integer.valueOf(30)};
        r0[10] = new Object[]{"8008", VARIABLE_LENGTH, Integer.valueOf(12)};
        r0[11] = new Object[]{"8018", Integer.valueOf(18)};
        r0[12] = new Object[]{"8020", VARIABLE_LENGTH, Integer.valueOf(25)};
        r0[13] = new Object[]{"8100", Integer.valueOf(6)};
        r0[14] = new Object[]{"8101", Integer.valueOf(10)};
        r0[15] = new Object[]{"8102", Integer.valueOf(2)};
        r0[16] = new Object[]{"8110", VARIABLE_LENGTH, Integer.valueOf(70)};
        r0[17] = new Object[]{"8200", VARIABLE_LENGTH, Integer.valueOf(70)};
        FOUR_DIGIT_DATA_LENGTH = r0;
    }

    private FieldParser() {
    }

    private static String processFixedAI(int i, int i2, String str) throws NotFoundException {
        if (str.length() >= i) {
            String substring = str.substring(0, i);
            i2 += i;
            if (str.length() >= i2) {
                i = str.substring(i, i2);
                i2 = str.substring(i2);
                str = new StringBuilder();
                str.append('(');
                str.append(substring);
                str.append(')');
                str.append(i);
                i = str.toString();
                i2 = parseFieldsInGeneralPurpose(i2);
                if (i2 == 0) {
                    return i;
                }
                str = new StringBuilder();
                str.append(i);
                str.append(i2);
                return str.toString();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static String processVariableAI(int i, int i2, String str) throws NotFoundException {
        String substring = str.substring(0, i);
        i2 += i;
        if (str.length() < i2) {
            i2 = str.length();
        }
        i = str.substring(i, i2);
        i2 = str.substring(i2);
        str = new StringBuilder();
        str.append('(');
        str.append(substring);
        str.append(')');
        str.append(i);
        i = str.toString();
        i2 = parseFieldsInGeneralPurpose(i2);
        if (i2 == 0) {
            return i;
        }
        str = new StringBuilder();
        str.append(i);
        str.append(i2);
        return str.toString();
    }
}
