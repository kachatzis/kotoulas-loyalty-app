package okhttp3.internal.tls;

import javax.security.auth.x500.X500Principal;

final class DistinguishedNameParser {
    private int beg;
    private char[] chars;
    private int cur;
    private final String dn;
    private int end;
    private final int length = this.dn.length();
    private int pos;

    private java.lang.String hexAV() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:41:0x00c1 in {11, 19, 24, 25, 26, 34, 36, 38, 40} preds:[]
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
        r5 = this;
        r0 = r5.pos;
        r1 = r0 + 4;
        r2 = r5.length;
        if (r1 >= r2) goto L_0x00a8;
    L_0x0008:
        r5.beg = r0;
        r0 = r0 + 1;
        r5.pos = r0;
    L_0x000e:
        r0 = r5.pos;
        r1 = r5.length;
        if (r0 == r1) goto L_0x005f;
    L_0x0014:
        r1 = r5.chars;
        r2 = r1[r0];
        r3 = 43;
        if (r2 == r3) goto L_0x005f;
    L_0x001c:
        r2 = r1[r0];
        r3 = 44;
        if (r2 == r3) goto L_0x005f;
    L_0x0022:
        r2 = r1[r0];
        r3 = 59;
        if (r2 != r3) goto L_0x0029;
    L_0x0028:
        goto L_0x005f;
    L_0x0029:
        r2 = r1[r0];
        r3 = 32;
        if (r2 != r3) goto L_0x0046;
    L_0x002f:
        r5.end = r0;
        r0 = r0 + 1;
        r5.pos = r0;
    L_0x0035:
        r0 = r5.pos;
        r1 = r5.length;
        if (r0 >= r1) goto L_0x0063;
    L_0x003b:
        r1 = r5.chars;
        r1 = r1[r0];
        if (r1 != r3) goto L_0x0063;
    L_0x0041:
        r0 = r0 + 1;
        r5.pos = r0;
        goto L_0x0035;
    L_0x0046:
        r2 = r1[r0];
        r4 = 65;
        if (r2 < r4) goto L_0x0058;
    L_0x004c:
        r2 = r1[r0];
        r4 = 70;
        if (r2 > r4) goto L_0x0058;
    L_0x0052:
        r2 = r1[r0];
        r2 = r2 + r3;
        r2 = (char) r2;
        r1[r0] = r2;
    L_0x0058:
        r0 = r5.pos;
        r0 = r0 + 1;
        r5.pos = r0;
        goto L_0x000e;
    L_0x005f:
        r0 = r5.pos;
        r5.end = r0;
    L_0x0063:
        r0 = r5.end;
        r1 = r5.beg;
        r0 = r0 - r1;
        r2 = 5;
        if (r0 < r2) goto L_0x008f;
    L_0x006b:
        r2 = r0 & 1;
        if (r2 == 0) goto L_0x008f;
    L_0x006f:
        r2 = r0 / 2;
        r2 = new byte[r2];
        r3 = 0;
        r1 = r1 + 1;
    L_0x0076:
        r4 = r2.length;
        if (r3 >= r4) goto L_0x0085;
    L_0x0079:
        r4 = r5.getByte(r1);
        r4 = (byte) r4;
        r2[r3] = r4;
        r1 = r1 + 2;
        r3 = r3 + 1;
        goto L_0x0076;
    L_0x0085:
        r1 = new java.lang.String;
        r2 = r5.chars;
        r3 = r5.beg;
        r1.<init>(r2, r3, r0);
        return r1;
    L_0x008f:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Unexpected end of DN: ";
        r1.append(r2);
        r2 = r5.dn;
        r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00a8:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Unexpected end of DN: ";
        r1.append(r2);
        r2 = r5.dn;
        r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.DistinguishedNameParser.hexAV():java.lang.String");
    }

    private java.lang.String nextAT() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:62:0x0109 in {4, 8, 16, 27, 32, 34, 40, 48, 52, 56, 57, 59, 61} preds:[]
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
        r5 = this;
    L_0x0000:
        r0 = r5.pos;
        r1 = r5.length;
        r2 = 32;
        if (r0 >= r1) goto L_0x0013;
    L_0x0008:
        r1 = r5.chars;
        r1 = r1[r0];
        if (r1 != r2) goto L_0x0013;
    L_0x000e:
        r0 = r0 + 1;
        r5.pos = r0;
        goto L_0x0000;
    L_0x0013:
        r0 = r5.pos;
        r1 = r5.length;
        if (r0 != r1) goto L_0x001b;
    L_0x0019:
        r0 = 0;
        return r0;
    L_0x001b:
        r5.beg = r0;
        r0 = r0 + 1;
        r5.pos = r0;
    L_0x0021:
        r0 = r5.pos;
        r1 = r5.length;
        r3 = 61;
        if (r0 >= r1) goto L_0x0038;
    L_0x0029:
        r1 = r5.chars;
        r4 = r1[r0];
        if (r4 == r3) goto L_0x0038;
    L_0x002f:
        r1 = r1[r0];
        if (r1 == r2) goto L_0x0038;
    L_0x0033:
        r0 = r0 + 1;
        r5.pos = r0;
        goto L_0x0021;
    L_0x0038:
        r0 = r5.pos;
        r1 = r5.length;
        if (r0 >= r1) goto L_0x00f0;
    L_0x003e:
        r5.end = r0;
        r1 = r5.chars;
        r0 = r1[r0];
        if (r0 != r2) goto L_0x0081;
    L_0x0046:
        r0 = r5.pos;
        r1 = r5.length;
        if (r0 >= r1) goto L_0x005b;
    L_0x004c:
        r1 = r5.chars;
        r4 = r1[r0];
        if (r4 == r3) goto L_0x005b;
    L_0x0052:
        r1 = r1[r0];
        if (r1 != r2) goto L_0x005b;
    L_0x0056:
        r0 = r0 + 1;
        r5.pos = r0;
        goto L_0x0046;
    L_0x005b:
        r0 = r5.chars;
        r1 = r5.pos;
        r0 = r0[r1];
        if (r0 != r3) goto L_0x0068;
    L_0x0063:
        r0 = r5.length;
        if (r1 == r0) goto L_0x0068;
    L_0x0067:
        goto L_0x0081;
    L_0x0068:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Unexpected end of DN: ";
        r1.append(r2);
        r2 = r5.dn;
        r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0081:
        r0 = r5.pos;
        r0 = r0 + 1;
        r5.pos = r0;
    L_0x0087:
        r0 = r5.pos;
        r1 = r5.length;
        if (r0 >= r1) goto L_0x0098;
    L_0x008d:
        r1 = r5.chars;
        r1 = r1[r0];
        if (r1 != r2) goto L_0x0098;
    L_0x0093:
        r0 = r0 + 1;
        r5.pos = r0;
        goto L_0x0087;
    L_0x0098:
        r0 = r5.end;
        r1 = r5.beg;
        r0 = r0 - r1;
        r2 = 4;
        if (r0 <= r2) goto L_0x00e3;
    L_0x00a0:
        r0 = r5.chars;
        r3 = r1 + 3;
        r3 = r0[r3];
        r4 = 46;
        if (r3 != r4) goto L_0x00e3;
    L_0x00aa:
        r3 = r0[r1];
        r4 = 79;
        if (r3 == r4) goto L_0x00b6;
    L_0x00b0:
        r0 = r0[r1];
        r1 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        if (r0 != r1) goto L_0x00e3;
    L_0x00b6:
        r0 = r5.chars;
        r1 = r5.beg;
        r3 = r1 + 1;
        r3 = r0[r3];
        r4 = 73;
        if (r3 == r4) goto L_0x00ca;
    L_0x00c2:
        r1 = r1 + 1;
        r0 = r0[r1];
        r1 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r0 != r1) goto L_0x00e3;
    L_0x00ca:
        r0 = r5.chars;
        r1 = r5.beg;
        r3 = r1 + 2;
        r3 = r0[r3];
        r4 = 68;
        if (r3 == r4) goto L_0x00de;
    L_0x00d6:
        r1 = r1 + 2;
        r0 = r0[r1];
        r1 = 100;
        if (r0 != r1) goto L_0x00e3;
    L_0x00de:
        r0 = r5.beg;
        r0 = r0 + r2;
        r5.beg = r0;
    L_0x00e3:
        r0 = new java.lang.String;
        r1 = r5.chars;
        r2 = r5.beg;
        r3 = r5.end;
        r3 = r3 - r2;
        r0.<init>(r1, r2, r3);
        return r0;
    L_0x00f0:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Unexpected end of DN: ";
        r1.append(r2);
        r2 = r5.dn;
        r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.DistinguishedNameParser.nextAT():java.lang.String");
    }

    private java.lang.String quotedAV() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x007b in {10, 12, 15, 16, 17, 19} preds:[]
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
        r4 = this;
        r0 = r4.pos;
        r0 = r0 + 1;
        r4.pos = r0;
        r0 = r4.pos;
        r4.beg = r0;
        r0 = r4.beg;
        r4.end = r0;
    L_0x000e:
        r0 = r4.pos;
        r1 = r4.length;
        if (r0 == r1) goto L_0x0062;
    L_0x0014:
        r1 = r4.chars;
        r2 = r1[r0];
        r3 = 34;
        if (r2 != r3) goto L_0x0040;
    L_0x001c:
        r0 = r0 + 1;
        r4.pos = r0;
    L_0x0020:
        r0 = r4.pos;
        r1 = r4.length;
        if (r0 >= r1) goto L_0x0033;
    L_0x0026:
        r1 = r4.chars;
        r1 = r1[r0];
        r2 = 32;
        if (r1 != r2) goto L_0x0033;
    L_0x002e:
        r0 = r0 + 1;
        r4.pos = r0;
        goto L_0x0020;
    L_0x0033:
        r0 = new java.lang.String;
        r1 = r4.chars;
        r2 = r4.beg;
        r3 = r4.end;
        r3 = r3 - r2;
        r0.<init>(r1, r2, r3);
        return r0;
    L_0x0040:
        r2 = r1[r0];
        r3 = 92;
        if (r2 != r3) goto L_0x004f;
    L_0x0046:
        r0 = r4.end;
        r2 = r4.getEscaped();
        r1[r0] = r2;
        goto L_0x0055;
    L_0x004f:
        r2 = r4.end;
        r0 = r1[r0];
        r1[r2] = r0;
    L_0x0055:
        r0 = r4.pos;
        r0 = r0 + 1;
        r4.pos = r0;
        r0 = r4.end;
        r0 = r0 + 1;
        r4.end = r0;
        goto L_0x000e;
    L_0x0062:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Unexpected end of DN: ";
        r1.append(r2);
        r2 = r4.dn;
        r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.DistinguishedNameParser.quotedAV():java.lang.String");
    }

    public java.lang.String findMostSpecific(java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:32:0x009a in {2, 5, 8, 9, 10, 13, 16, 21, 24, 26, 29, 31} preds:[]
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
        r5 = this;
        r0 = 0;
        r5.pos = r0;
        r5.beg = r0;
        r5.end = r0;
        r5.cur = r0;
        r0 = r5.dn;
        r0 = r0.toCharArray();
        r5.chars = r0;
        r0 = r5.nextAT();
        r1 = 0;
        if (r0 != 0) goto L_0x0019;
    L_0x0018:
        return r1;
    L_0x0019:
        r2 = "";
        r3 = r5.pos;
        r4 = r5.length;
        if (r3 != r4) goto L_0x0022;
    L_0x0021:
        return r1;
    L_0x0022:
        r4 = r5.chars;
        r3 = r4[r3];
        switch(r3) {
            case 34: goto L_0x0033;
            case 35: goto L_0x002e;
            case 43: goto L_0x0037;
            case 44: goto L_0x0037;
            case 59: goto L_0x0037;
            default: goto L_0x0029;
        };
    L_0x0029:
        r2 = r5.escapedAV();
        goto L_0x0037;
    L_0x002e:
        r2 = r5.hexAV();
        goto L_0x0037;
    L_0x0033:
        r2 = r5.quotedAV();
    L_0x0037:
        r0 = r6.equalsIgnoreCase(r0);
        if (r0 == 0) goto L_0x003e;
    L_0x003d:
        return r2;
    L_0x003e:
        r0 = r5.pos;
        r2 = r5.length;
        if (r0 < r2) goto L_0x0045;
    L_0x0044:
        return r1;
    L_0x0045:
        r2 = r5.chars;
        r3 = r2[r0];
        r4 = 44;
        if (r3 == r4) goto L_0x0074;
    L_0x004d:
        r3 = r2[r0];
        r4 = 59;
        if (r3 != r4) goto L_0x0054;
    L_0x0053:
        goto L_0x0074;
    L_0x0054:
        r0 = r2[r0];
        r2 = 43;
        if (r0 != r2) goto L_0x005b;
    L_0x005a:
        goto L_0x0074;
    L_0x005b:
        r6 = new java.lang.IllegalStateException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Malformed DN: ";
        r0.append(r1);
        r1 = r5.dn;
        r0.append(r1);
        r0 = r0.toString();
        r6.<init>(r0);
        throw r6;
    L_0x0074:
        r0 = r5.pos;
        r0 = r0 + 1;
        r5.pos = r0;
        r0 = r5.nextAT();
        if (r0 == 0) goto L_0x0081;
    L_0x0080:
        goto L_0x0019;
    L_0x0081:
        r6 = new java.lang.IllegalStateException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Malformed DN: ";
        r0.append(r1);
        r1 = r5.dn;
        r0.append(r1);
        r0 = r0.toString();
        r6.<init>(r0);
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.DistinguishedNameParser.findMostSpecific(java.lang.String):java.lang.String");
    }

    DistinguishedNameParser(X500Principal x500Principal) {
        this.dn = x500Principal.getName("RFC2253");
    }

    private String escapedAV() {
        int i = this.pos;
        this.beg = i;
        this.end = i;
        while (true) {
            i = this.pos;
            if (i >= this.length) {
                char[] cArr = this.chars;
                int i2 = this.beg;
                return new String(cArr, i2, this.end - i2);
            }
            cArr = this.chars;
            char c = cArr[i];
            if (c != ' ') {
                if (c != ';') {
                    if (c != '\\') {
                        switch (c) {
                            case '+':
                            case ',':
                                break;
                            default:
                                i2 = this.end;
                                this.end = i2 + 1;
                                cArr[i2] = cArr[i];
                                this.pos = i + 1;
                                continue;
                        }
                    } else {
                        i = this.end;
                        this.end = i + 1;
                        cArr[i] = getEscaped();
                        this.pos++;
                    }
                }
                cArr = this.chars;
                i2 = this.beg;
                return new String(cArr, i2, this.end - i2);
            }
            i2 = this.end;
            this.cur = i2;
            this.pos = i + 1;
            this.end = i2 + 1;
            cArr[i2] = ' ';
            while (true) {
                i = this.pos;
                if (i < this.length) {
                    cArr = this.chars;
                    if (cArr[i] == ' ') {
                        i2 = this.end;
                        this.end = i2 + 1;
                        cArr[i2] = ' ';
                        this.pos = i + 1;
                    }
                }
                i = this.pos;
                if (i != this.length) {
                    cArr = this.chars;
                    if (!(cArr[i] == ',' || cArr[i] == '+' || cArr[i] == ';')) {
                    }
                }
                cArr = this.chars;
                i2 = this.beg;
                return new String(cArr, i2, this.cur - i2);
            }
        }
    }

    private char getEscaped() {
        this.pos++;
        int i = this.pos;
        if (i != this.length) {
            char c = this.chars[i];
            if (!(c == ' ' || c == '%' || c == '\\' || c == '_')) {
                switch (c) {
                    case '\"':
                    case '#':
                        break;
                    default:
                        switch (c) {
                            case '*':
                            case '+':
                            case ',':
                                break;
                            default:
                                switch (c) {
                                    case ';':
                                    case '<':
                                    case '=':
                                    case '>':
                                        break;
                                    default:
                                        return getUTF8();
                                }
                        }
                }
            }
            return this.chars[this.pos];
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected end of DN: ");
        stringBuilder.append(this.dn);
        throw new IllegalStateException(stringBuilder.toString());
    }

    private char getUTF8() {
        int i = getByte(this.pos);
        this.pos++;
        if (i < 128) {
            return (char) i;
        }
        if (i < 192 || i > 247) {
            return '?';
        }
        int i2;
        if (i <= 223) {
            i &= 31;
            i2 = 1;
        } else if (i <= 239) {
            i2 = 2;
            i &= 15;
        } else {
            i2 = 3;
            i &= 7;
        }
        int i3 = 0;
        while (i3 < i2) {
            this.pos++;
            int i4 = this.pos;
            if (i4 != this.length) {
                if (this.chars[i4] == '\\') {
                    this.pos = i4 + 1;
                    i4 = getByte(this.pos);
                    this.pos++;
                    if ((i4 & 192) != 128) {
                        return '?';
                    }
                    i = (i << 6) + (i4 & 63);
                    i3++;
                }
            }
            return '?';
        }
        return (char) i;
    }

    private int getByte(int i) {
        int i2 = i + 1;
        StringBuilder stringBuilder;
        if (i2 < this.length) {
            i = this.chars[i];
            if (i >= 48 && i <= 57) {
                i -= 48;
            } else if (i >= 97 && i <= 102) {
                i -= 87;
            } else if (i < 65 || i > 70) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Malformed DN: ");
                stringBuilder.append(this.dn);
                throw new IllegalStateException(stringBuilder.toString());
            } else {
                i -= 55;
            }
            char c = this.chars[i2];
            if (c >= '0' && c <= '9') {
                i2 = c - 48;
            } else if (c >= 'a' && c <= 'f') {
                i2 = c - 87;
            } else if (c < 'A' || c > 'F') {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Malformed DN: ");
                stringBuilder.append(this.dn);
                throw new IllegalStateException(stringBuilder.toString());
            } else {
                i2 = c - 55;
            }
            return (i << 4) + i2;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("Malformed DN: ");
        stringBuilder.append(this.dn);
        throw new IllegalStateException(stringBuilder.toString());
    }
}
