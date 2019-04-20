package okio;

public final class Utf8 {
    public static long size(java.lang.String r9, int r10, int r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:40:0x00bb in {9, 12, 17, 20, 21, 27, 28, 29, 30, 31, 33, 35, 37, 39} preds:[]
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
        if (r9 == 0) goto L_0x00b3;
    L_0x0002:
        if (r10 < 0) goto L_0x009c;
    L_0x0004:
        if (r11 < r10) goto L_0x007d;
    L_0x0006:
        r0 = r9.length();
        if (r11 > r0) goto L_0x005a;
    L_0x000c:
        r0 = 0;
    L_0x000e:
        if (r10 >= r11) goto L_0x0059;
    L_0x0010:
        r2 = r9.charAt(r10);
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r4 = 1;
        if (r2 >= r3) goto L_0x001e;
    L_0x001a:
        r0 = r0 + r4;
        r10 = r10 + 1;
        goto L_0x000e;
    L_0x001e:
        r3 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        if (r2 >= r3) goto L_0x0028;
    L_0x0022:
        r2 = 2;
        r0 = r0 + r2;
        r10 = r10 + 1;
        goto L_0x000e;
    L_0x0028:
        r3 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r2 < r3) goto L_0x0053;
    L_0x002d:
        r3 = 57343; // 0xdfff float:8.0355E-41 double:2.8331E-319;
        if (r2 <= r3) goto L_0x0033;
    L_0x0032:
        goto L_0x0053;
    L_0x0033:
        r6 = r10 + 1;
        if (r6 >= r11) goto L_0x003c;
    L_0x0037:
        r7 = r9.charAt(r6);
        goto L_0x003d;
    L_0x003c:
        r7 = 0;
    L_0x003d:
        r8 = 56319; // 0xdbff float:7.892E-41 double:2.78253E-319;
        if (r2 > r8) goto L_0x0050;
    L_0x0042:
        r2 = 56320; // 0xdc00 float:7.8921E-41 double:2.7826E-319;
        if (r7 < r2) goto L_0x0050;
    L_0x0047:
        if (r7 <= r3) goto L_0x004a;
    L_0x0049:
        goto L_0x0050;
    L_0x004a:
        r2 = 4;
        r0 = r0 + r2;
        r10 = r10 + 2;
        goto L_0x000e;
    L_0x0050:
        r0 = r0 + r4;
        r10 = r6;
        goto L_0x000e;
    L_0x0053:
        r2 = 3;
        r0 = r0 + r2;
        r10 = r10 + 1;
        goto L_0x000e;
    L_0x0059:
        return r0;
    L_0x005a:
        r10 = new java.lang.IllegalArgumentException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "endIndex > string.length: ";
        r0.append(r1);
        r0.append(r11);
        r11 = " > ";
        r0.append(r11);
        r9 = r9.length();
        r0.append(r9);
        r9 = r0.toString();
        r10.<init>(r9);
        throw r10;
    L_0x007d:
        r9 = new java.lang.IllegalArgumentException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "endIndex < beginIndex: ";
        r0.append(r1);
        r0.append(r11);
        r11 = " < ";
        r0.append(r11);
        r0.append(r10);
        r10 = r0.toString();
        r9.<init>(r10);
        throw r9;
    L_0x009c:
        r9 = new java.lang.IllegalArgumentException;
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r0 = "beginIndex < 0: ";
        r11.append(r0);
        r11.append(r10);
        r10 = r11.toString();
        r9.<init>(r10);
        throw r9;
    L_0x00b3:
        r9 = new java.lang.IllegalArgumentException;
        r10 = "string == null";
        r9.<init>(r10);
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Utf8.size(java.lang.String, int, int):long");
    }

    private Utf8() {
    }

    public static long size(String str) {
        return size(str, 0, str.length());
    }
}
