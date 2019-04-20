package com.google.zxing.datamatrix.encoder;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;

public final class ErrorCorrection {
    private static final int[] ALOG = new int[255];
    private static final int[][] FACTORS;
    private static final int[] FACTOR_SETS = new int[]{5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[] LOG = new int[256];
    private static final int MODULO_VALUE = 301;

    private static java.lang.String createECCBlock(java.lang.CharSequence r11, int r12, int r13, int r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:36:0x00a0 in {5, 6, 7, 11, 20, 21, 22, 26, 27, 28, 31, 33, 35} preds:[]
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
        r0 = 0;
        r1 = 0;
    L_0x0002:
        r2 = FACTOR_SETS;
        r3 = r2.length;
        if (r1 >= r3) goto L_0x000f;
    L_0x0007:
        r2 = r2[r1];
        if (r2 != r14) goto L_0x000c;
    L_0x000b:
        goto L_0x0010;
    L_0x000c:
        r1 = r1 + 1;
        goto L_0x0002;
    L_0x000f:
        r1 = -1;
    L_0x0010:
        if (r1 < 0) goto L_0x0089;
    L_0x0012:
        r2 = FACTORS;
        r1 = r2[r1];
        r2 = new char[r14];
        r3 = 0;
    L_0x0019:
        if (r3 >= r14) goto L_0x0020;
    L_0x001b:
        r2[r3] = r0;
        r3 = r3 + 1;
        goto L_0x0019;
    L_0x0020:
        r3 = r12;
    L_0x0021:
        r4 = r12 + r13;
        if (r3 >= r4) goto L_0x0075;
    L_0x0025:
        r4 = r14 + -1;
        r5 = r2[r4];
        r6 = r11.charAt(r3);
        r5 = r5 ^ r6;
    L_0x002e:
        if (r4 <= 0) goto L_0x0057;
    L_0x0030:
        if (r5 == 0) goto L_0x004e;
    L_0x0032:
        r6 = r1[r4];
        if (r6 == 0) goto L_0x004e;
    L_0x0036:
        r6 = r4 + -1;
        r6 = r2[r6];
        r7 = ALOG;
        r8 = LOG;
        r9 = r8[r5];
        r10 = r1[r4];
        r8 = r8[r10];
        r9 = r9 + r8;
        r9 = r9 % 255;
        r7 = r7[r9];
        r6 = r6 ^ r7;
        r6 = (char) r6;
        r2[r4] = r6;
        goto L_0x0054;
    L_0x004e:
        r6 = r4 + -1;
        r6 = r2[r6];
        r2[r4] = r6;
    L_0x0054:
        r4 = r4 + -1;
        goto L_0x002e;
    L_0x0057:
        if (r5 == 0) goto L_0x0070;
    L_0x0059:
        r4 = r1[r0];
        if (r4 == 0) goto L_0x0070;
    L_0x005d:
        r4 = ALOG;
        r6 = LOG;
        r5 = r6[r5];
        r7 = r1[r0];
        r6 = r6[r7];
        r5 = r5 + r6;
        r5 = r5 % 255;
        r4 = r4[r5];
        r4 = (char) r4;
        r2[r0] = r4;
        goto L_0x0072;
    L_0x0070:
        r2[r0] = r0;
    L_0x0072:
        r3 = r3 + 1;
        goto L_0x0021;
    L_0x0075:
        r11 = new char[r14];
    L_0x0077:
        if (r0 >= r14) goto L_0x0084;
    L_0x0079:
        r12 = r14 - r0;
        r12 = r12 + -1;
        r12 = r2[r12];
        r11[r0] = r12;
        r0 = r0 + 1;
        goto L_0x0077;
    L_0x0084:
        r11 = java.lang.String.valueOf(r11);
        return r11;
    L_0x0089:
        r11 = new java.lang.IllegalArgumentException;
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = "Illegal number of error correction codewords specified: ";
        r12.append(r13);
        r12.append(r14);
        r12 = r12.toString();
        r11.<init>(r12);
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.ErrorCorrection.createECCBlock(java.lang.CharSequence, int, int, int):java.lang.String");
    }

    public static java.lang.String encodeECC200(java.lang.String r11, com.google.zxing.datamatrix.encoder.SymbolInfo r12) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:26:0x00aa in {4, 9, 10, 16, 20, 21, 23, 25} preds:[]
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
        r0 = r11.length();
        r1 = r12.getDataCapacity();
        if (r0 != r1) goto L_0x00a2;
    L_0x000a:
        r0 = new java.lang.StringBuilder;
        r1 = r12.getDataCapacity();
        r2 = r12.getErrorCodewords();
        r1 = r1 + r2;
        r0.<init>(r1);
        r0.append(r11);
        r1 = r12.getInterleavedBlockCount();
        r2 = 1;
        if (r1 != r2) goto L_0x002f;
    L_0x0022:
        r12 = r12.getErrorCodewords();
        r11 = createECCBlock(r11, r12);
        r0.append(r11);
        goto L_0x009d;
    L_0x002f:
        r2 = r0.capacity();
        r0.setLength(r2);
        r2 = new int[r1];
        r3 = new int[r1];
        r4 = new int[r1];
        r5 = 0;
        r6 = 0;
    L_0x003e:
        if (r6 >= r1) goto L_0x005d;
    L_0x0040:
        r7 = r6 + 1;
        r8 = r12.getDataLengthForInterleavedBlock(r7);
        r2[r6] = r8;
        r8 = r12.getErrorLengthForInterleavedBlock(r7);
        r3[r6] = r8;
        r4[r6] = r5;
        if (r6 <= 0) goto L_0x005b;
    L_0x0052:
        r8 = r6 + -1;
        r8 = r4[r8];
        r9 = r2[r6];
        r8 = r8 + r9;
        r4[r6] = r8;
    L_0x005b:
        r6 = r7;
        goto L_0x003e;
    L_0x005d:
        r4 = 0;
    L_0x005e:
        if (r4 >= r1) goto L_0x009d;
    L_0x0060:
        r6 = new java.lang.StringBuilder;
        r7 = r2[r4];
        r6.<init>(r7);
        r7 = r4;
    L_0x0068:
        r8 = r12.getDataCapacity();
        if (r7 >= r8) goto L_0x0077;
    L_0x006e:
        r8 = r11.charAt(r7);
        r6.append(r8);
        r7 = r7 + r1;
        goto L_0x0068;
    L_0x0077:
        r6 = r6.toString();
        r7 = r3[r4];
        r6 = createECCBlock(r6, r7);
        r7 = r4;
        r8 = 0;
    L_0x0083:
        r9 = r3[r4];
        r9 = r9 * r1;
        if (r7 >= r9) goto L_0x009a;
    L_0x0089:
        r9 = r12.getDataCapacity();
        r9 = r9 + r7;
        r10 = r8 + 1;
        r8 = r6.charAt(r8);
        r0.setCharAt(r9, r8);
        r7 = r7 + r1;
        r8 = r10;
        goto L_0x0083;
    L_0x009a:
        r4 = r4 + 1;
        goto L_0x005e;
    L_0x009d:
        r11 = r0.toString();
        return r11;
    L_0x00a2:
        r11 = new java.lang.IllegalArgumentException;
        r12 = "The number of codewords does not match the selected symbol";
        r11.<init>(r12);
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.ErrorCorrection.encodeECC200(java.lang.String, com.google.zxing.datamatrix.encoder.SymbolInfo):java.lang.String");
    }

    static {
        r0 = new int[16][];
        int i = 0;
        r0[0] = new int[]{228, 48, 15, 111, 62};
        int i2 = 1;
        r0[1] = new int[]{23, 68, 144, 134, 240, 92, 254};
        r0[2] = new int[]{28, 24, 185, 166, 223, 248, 116, 255, 110, 61};
        r0[3] = new int[]{175, 138, 205, 12, 194, 168, 39, 245, 60, 97, 120};
        r0[4] = new int[]{41, 153, 158, 91, 61, 42, 142, 213, 97, 178, 100, 242};
        r0[5] = new int[]{156, 97, 192, 252, 95, 9, 157, 119, 138, 45, 18, 186, 83, 185};
        r0[6] = new int[]{83, 195, 100, 39, 188, 75, 66, 61, 241, 213, 109, 129, 94, 254, 225, 48, 90, 188};
        r0[7] = new int[]{15, 195, 244, 9, 233, 71, 168, 2, 188, 160, 153, 145, 253, 79, 108, 82, 27, 174, 186, 172};
        r0[8] = new int[]{52, 190, 88, 205, 109, 39, 176, 21, 155, 197, 251, 223, 155, 21, 5, 172, 254, 124, 12, 181, 184, 96, 50, 193};
        r0[9] = new int[]{211, 231, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, 249, 121, 17, 138, 110, 213, 141, 136, 120, 151, 233, 168, 93, 255};
        r0[10] = new int[]{245, 127, 242, 218, 130, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 162, 181, 102, 120, 84, 179, 220, 251, 80, 182, 229, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, 225, 98, 81, 112};
        r0[11] = new int[]{77, 193, 137, 31, 19, 38, 22, 153, 247, 105, 122, 2, 245, 133, 242, 8, 175, 95, 100, 9, 167, 105, 214, 111, 57, 121, 21, 1, 253, 57, 54, 101, 248, 202, 69, 50, 150, 177, 226, 5, 9, 5};
        r0[12] = new int[]{245, 132, 172, 223, 96, 32, 117, 22, 238, 133, 238, 231, 205, 188, 237, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 66, 138, 186, 240, 82, 44, 176, 87, 187, 147, 160, 175, 69, 213, 92, 253, 225, 19};
        r0[13] = new int[]{175, 9, 223, 238, 12, 17, 220, 208, 100, 29, 175, 170, 230, 192, 215, 235, 150, 159, 36, 223, 38, Callback.DEFAULT_DRAG_ANIMATION_DURATION, 132, 54, 228, 146, 218, 234, 117, 203, 29, 232, 144, 238, 22, 150, 201, 117, 62, 207, 164, 13, 137, 245, 127, 67, 247, 28, 155, 43, 203, 107, 233, 53, 143, 46};
        r0[14] = new int[]{242, 93, 169, 50, 144, 210, 39, 118, 202, 188, 201, 189, 143, 108, 196, 37, 185, 112, 134, 230, 245, 63, 197, 190, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 106, 185, 221, 175, 64, 114, 71, 161, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, 130, 188, 17, 163, 31, 176, 170, 4, 107, 232, 7, 94, 166, 224, 124, 86, 47, 11, 204};
        r0[15] = new int[]{220, 228, 173, 89, 251, 149, 159, 56, 89, 33, 147, 244, 154, 36, 73, 127, 213, 136, 248, 180, 234, 197, 158, 177, 68, 122, 93, 213, 15, 160, 227, 236, 66, 139, 153, 185, 202, 167, 179, 25, 220, 232, 96, 210, 231, 136, 223, 239, 181, 241, 59, 52, 172, 25, 49, 232, 211, 189, 64, 54, 108, 153, 132, 63, 96, 103, 82, 186};
        FACTORS = r0;
        while (i < 255) {
            ALOG[i] = i2;
            LOG[i2] = i;
            i2 *= 2;
            if (i2 >= 256) {
                i2 ^= MODULO_VALUE;
            }
            i++;
        }
    }

    private ErrorCorrection() {
    }

    private static String createECCBlock(CharSequence charSequence, int i) {
        return createECCBlock(charSequence, 0, charSequence.length(), i);
    }
}
