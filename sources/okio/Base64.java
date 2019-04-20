package okio;

final class Base64 {
    private static final byte[] MAP = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] URL_MAP = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};

    private static java.lang.String encode(byte[] r8, byte[] r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:14:0x00bb in {2, 5, 6, 7, 10, 13} preds:[]
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
        r0 = r8.length;
        r0 = r0 + 2;
        r0 = r0 / 3;
        r0 = r0 * 4;
        r0 = new byte[r0];
        r1 = r8.length;
        r2 = r8.length;
        r2 = r2 % 3;
        r1 = r1 - r2;
        r2 = 0;
        r3 = 0;
    L_0x0010:
        if (r2 >= r1) goto L_0x0055;
    L_0x0012:
        r4 = r3 + 1;
        r5 = r8[r2];
        r5 = r5 & 255;
        r5 = r5 >> 2;
        r5 = r9[r5];
        r0[r3] = r5;
        r3 = r4 + 1;
        r5 = r8[r2];
        r5 = r5 & 3;
        r5 = r5 << 4;
        r6 = r2 + 1;
        r7 = r8[r6];
        r7 = r7 & 255;
        r7 = r7 >> 4;
        r5 = r5 | r7;
        r5 = r9[r5];
        r0[r4] = r5;
        r4 = r3 + 1;
        r5 = r8[r6];
        r5 = r5 & 15;
        r5 = r5 << 2;
        r6 = r2 + 2;
        r7 = r8[r6];
        r7 = r7 & 255;
        r7 = r7 >> 6;
        r5 = r5 | r7;
        r5 = r9[r5];
        r0[r3] = r5;
        r3 = r4 + 1;
        r5 = r8[r6];
        r5 = r5 & 63;
        r5 = r9[r5];
        r0[r4] = r5;
        r2 = r2 + 3;
        goto L_0x0010;
    L_0x0055:
        r2 = r8.length;
        r2 = r2 % 3;
        r4 = 61;
        switch(r2) {
            case 1: goto L_0x008e;
            case 2: goto L_0x005e;
            default: goto L_0x005d;
        };
    L_0x005d:
        goto L_0x00ac;
    L_0x005e:
        r2 = r3 + 1;
        r5 = r8[r1];
        r5 = r5 & 255;
        r5 = r5 >> 2;
        r5 = r9[r5];
        r0[r3] = r5;
        r3 = r2 + 1;
        r5 = r8[r1];
        r5 = r5 & 3;
        r5 = r5 << 4;
        r1 = r1 + 1;
        r6 = r8[r1];
        r6 = r6 & 255;
        r6 = r6 >> 4;
        r5 = r5 | r6;
        r5 = r9[r5];
        r0[r2] = r5;
        r2 = r3 + 1;
        r8 = r8[r1];
        r8 = r8 & 15;
        r8 = r8 << 2;
        r8 = r9[r8];
        r0[r3] = r8;
        r0[r2] = r4;
        goto L_0x00ac;
    L_0x008e:
        r2 = r3 + 1;
        r5 = r8[r1];
        r5 = r5 & 255;
        r5 = r5 >> 2;
        r5 = r9[r5];
        r0[r3] = r5;
        r3 = r2 + 1;
        r8 = r8[r1];
        r8 = r8 & 3;
        r8 = r8 << 4;
        r8 = r9[r8];
        r0[r2] = r8;
        r8 = r3 + 1;
        r0[r3] = r4;
        r0[r8] = r4;
    L_0x00ac:
        r8 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x00b4 }
        r9 = "US-ASCII";	 Catch:{ UnsupportedEncodingException -> 0x00b4 }
        r8.<init>(r0, r9);	 Catch:{ UnsupportedEncodingException -> 0x00b4 }
        return r8;
    L_0x00b4:
        r8 = move-exception;
        r9 = new java.lang.AssertionError;
        r9.<init>(r8);
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Base64.encode(byte[], byte[]):java.lang.String");
    }

    private Base64() {
    }

    public static byte[] decode(String str) {
        int length = str.length();
        while (length > 0) {
            char charAt = str.charAt(length - 1);
            if (charAt != '=' && charAt != '\n' && charAt != '\r' && charAt != ' ' && charAt != '\t') {
                break;
            }
            length--;
        }
        Object obj = new byte[((int) ((((long) length) * 6) / 8))];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int i5;
            char charAt2 = str.charAt(i4);
            if (charAt2 >= 'A' && charAt2 <= 'Z') {
                i5 = charAt2 - 65;
            } else if (charAt2 >= 'a' && charAt2 <= 'z') {
                i5 = charAt2 - 71;
            } else if (charAt2 < '0' || charAt2 > '9') {
                if (charAt2 != '+') {
                    if (charAt2 != '-') {
                        if (charAt2 != '/') {
                            if (charAt2 != '_') {
                                if (!(charAt2 == '\n' || charAt2 == '\r' || charAt2 == ' ')) {
                                    if (charAt2 != '\t') {
                                        return null;
                                    }
                                }
                            }
                        }
                        i5 = 63;
                    }
                }
                i5 = 62;
            } else {
                i5 = charAt2 + 4;
            }
            i2 = (i2 << 6) | ((byte) i5);
            i++;
            if (i % 4 == 0) {
                int i6 = i3 + 1;
                obj[i3] = (byte) (i2 >> 16);
                i3 = i6 + 1;
                obj[i6] = (byte) (i2 >> 8);
                i6 = i3 + 1;
                obj[i3] = (byte) i2;
                i3 = i6;
            }
        }
        i %= 4;
        if (i == 1) {
            return null;
        }
        if (i == 2) {
            length = i3 + 1;
            obj[i3] = (byte) ((i2 << 12) >> 16);
            i3 = length;
        } else if (i == 3) {
            str = i2 << 6;
            length = i3 + 1;
            obj[i3] = (byte) (str >> 16);
            i3 = length + 1;
            obj[length] = (byte) (str >> 8);
        }
        if (i3 == obj.length) {
            return obj;
        }
        str = new byte[i3];
        System.arraycopy(obj, 0, str, 0, i3);
        return str;
    }

    public static String encode(byte[] bArr) {
        return encode(bArr, MAP);
    }

    public static String encodeUrl(byte[] bArr) {
        return encode(bArr, URL_MAP);
    }
}
