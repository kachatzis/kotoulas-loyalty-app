package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = 'ñ';
    private static final char ESCAPE_FNC_2 = 'ò';
    private static final char ESCAPE_FNC_3 = 'ó';
    private static final char ESCAPE_FNC_4 = 'ô';

    public boolean[] encode(java.lang.String r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:58:0x00fd in {10, 13, 14, 19, 20, 23, 24, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 43, 49, 50, 54, 55, 57} preds:[]
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
        r12 = this;
        r0 = r13.length();
        r1 = 1;
        if (r0 < r1) goto L_0x00e6;
    L_0x0007:
        r2 = 80;
        if (r0 > r2) goto L_0x00e6;
    L_0x000b:
        r2 = 0;
        r3 = 0;
    L_0x000d:
        r4 = 32;
        if (r3 >= r0) goto L_0x0038;
    L_0x0011:
        r5 = r13.charAt(r3);
        if (r5 < r4) goto L_0x001b;
    L_0x0017:
        r4 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        if (r5 <= r4) goto L_0x0035;
    L_0x001b:
        switch(r5) {
            case 241: goto L_0x0035;
            case 242: goto L_0x0035;
            case 243: goto L_0x0035;
            case 244: goto L_0x0035;
            default: goto L_0x001e;
        };
    L_0x001e:
        r13 = new java.lang.IllegalArgumentException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Bad character in input: ";
        r0.append(r1);
        r0.append(r5);
        r0 = r0.toString();
        r13.<init>(r0);
        throw r13;
    L_0x0035:
        r3 = r3 + 1;
        goto L_0x000d;
    L_0x0038:
        r3 = new java.util.ArrayList;
        r3.<init>();
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 1;
    L_0x0041:
        if (r5 >= r0) goto L_0x009d;
    L_0x0043:
        r9 = 99;
        if (r7 != r9) goto L_0x0049;
    L_0x0047:
        r10 = 2;
        goto L_0x004a;
    L_0x0049:
        r10 = 4;
    L_0x004a:
        r10 = isDigits(r13, r5, r10);
        r11 = 100;
        if (r10 == 0) goto L_0x0053;
    L_0x0052:
        goto L_0x0055;
    L_0x0053:
        r9 = 100;
    L_0x0055:
        if (r9 != r7) goto L_0x007e;
    L_0x0057:
        r9 = r13.charAt(r5);
        switch(r9) {
            case 241: goto L_0x006d;
            case 242: goto L_0x006a;
            case 243: goto L_0x0067;
            case 244: goto L_0x007c;
            default: goto L_0x005e;
        };
    L_0x005e:
        if (r7 != r11) goto L_0x0070;
    L_0x0060:
        r9 = r13.charAt(r5);
        r11 = r9 + -32;
        goto L_0x007c;
    L_0x0067:
        r11 = 96;
        goto L_0x007c;
    L_0x006a:
        r11 = 97;
        goto L_0x007c;
    L_0x006d:
        r11 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        goto L_0x007c;
    L_0x0070:
        r9 = r5 + 2;
        r9 = r13.substring(r5, r9);
        r11 = java.lang.Integer.parseInt(r9);
        r5 = r5 + 1;
    L_0x007c:
        r5 = r5 + r1;
        goto L_0x008e;
    L_0x007e:
        if (r7 != 0) goto L_0x008c;
    L_0x0080:
        if (r9 != r11) goto L_0x0087;
    L_0x0082:
        r7 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r11 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        goto L_0x008d;
    L_0x0087:
        r7 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        r11 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        goto L_0x008d;
    L_0x008c:
        r11 = r9;
    L_0x008d:
        r7 = r9;
    L_0x008e:
        r9 = com.google.zxing.oned.Code128Reader.CODE_PATTERNS;
        r9 = r9[r11];
        r3.add(r9);
        r11 = r11 * r8;
        r6 = r6 + r11;
        if (r5 == 0) goto L_0x0041;
    L_0x009a:
        r8 = r8 + 1;
        goto L_0x0041;
    L_0x009d:
        r6 = r6 % 103;
        r13 = com.google.zxing.oned.Code128Reader.CODE_PATTERNS;
        r13 = r13[r6];
        r3.add(r13);
        r13 = com.google.zxing.oned.Code128Reader.CODE_PATTERNS;
        r0 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        r13 = r13[r0];
        r3.add(r13);
        r13 = r3.iterator();
        r0 = 0;
    L_0x00b4:
        r4 = r13.hasNext();
        if (r4 == 0) goto L_0x00cd;
    L_0x00ba:
        r4 = r13.next();
        r4 = (int[]) r4;
        r5 = r4.length;
        r6 = r0;
        r0 = 0;
    L_0x00c3:
        if (r0 >= r5) goto L_0x00cb;
    L_0x00c5:
        r7 = r4[r0];
        r6 = r6 + r7;
        r0 = r0 + 1;
        goto L_0x00c3;
    L_0x00cb:
        r0 = r6;
        goto L_0x00b4;
    L_0x00cd:
        r13 = new boolean[r0];
        r0 = r3.iterator();
    L_0x00d3:
        r3 = r0.hasNext();
        if (r3 == 0) goto L_0x00e5;
    L_0x00d9:
        r3 = r0.next();
        r3 = (int[]) r3;
        r3 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r13, r2, r3, r1);
        r2 = r2 + r3;
        goto L_0x00d3;
    L_0x00e5:
        return r13;
    L_0x00e6:
        r13 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Contents length should be between 1 and 80 characters, but got ";
        r1.append(r2);
        r1.append(r0);
        r0 = r1.toString();
        r13.<init>(r0);
        throw r13;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Writer.encode(java.lang.String):boolean[]");
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        i = new StringBuilder();
        i.append("Can only encode CODE_128, but got ");
        i.append(barcodeFormat);
        throw new IllegalArgumentException(i.toString());
    }

    private static boolean isDigits(CharSequence charSequence, int i, int i2) {
        boolean z;
        i2 += i;
        int length = charSequence.length();
        while (true) {
            z = false;
            if (i < i2 && i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt < '0' || charAt > '9') {
                    if (charAt != ESCAPE_FNC_1) {
                        return false;
                    }
                    i2++;
                }
                i++;
            } else if (i2 <= length) {
                z = true;
            }
        }
        if (i2 <= length) {
            z = true;
        }
        return z;
    }
}
