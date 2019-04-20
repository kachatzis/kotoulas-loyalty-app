package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;

public final class Cookie {
    private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
    private final String domain;
    private final long expiresAt;
    private final boolean hostOnly;
    private final boolean httpOnly;
    private final String name;
    private final String path;
    private final boolean persistent;
    private final boolean secure;
    private final String value;

    public static final class Builder {
        String domain;
        long expiresAt = HttpDate.MAX_DATE;
        boolean hostOnly;
        boolean httpOnly;
        String name;
        String path = "/";
        boolean persistent;
        boolean secure;
        String value;

        public Builder name(String str) {
            if (str == null) {
                throw new NullPointerException("name == null");
            } else if (str.trim().equals(str)) {
                this.name = str;
                return this;
            } else {
                throw new IllegalArgumentException("name is not trimmed");
            }
        }

        public Builder value(String str) {
            if (str == null) {
                throw new NullPointerException("value == null");
            } else if (str.trim().equals(str)) {
                this.value = str;
                return this;
            } else {
                throw new IllegalArgumentException("value is not trimmed");
            }
        }

        public Builder expiresAt(long j) {
            if (j <= 0) {
                j = Long.MIN_VALUE;
            }
            if (j > HttpDate.MAX_DATE) {
                j = HttpDate.MAX_DATE;
            }
            this.expiresAt = j;
            this.persistent = 1;
            return this;
        }

        public Builder domain(String str) {
            return domain(str, false);
        }

        public Builder hostOnlyDomain(String str) {
            return domain(str, true);
        }

        private Builder domain(String str, boolean z) {
            if (str != null) {
                String domainToAscii = Util.domainToAscii(str);
                if (domainToAscii != null) {
                    this.domain = domainToAscii;
                    this.hostOnly = z;
                    return this;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("unexpected domain: ");
                stringBuilder.append(str);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
            throw new NullPointerException("domain == null");
        }

        public Builder path(String str) {
            if (str.startsWith("/")) {
                this.path = str;
                return this;
            }
            throw new IllegalArgumentException("path must start with '/'");
        }

        public Builder secure() {
            this.secure = true;
            return this;
        }

        public Builder httpOnly() {
            this.httpOnly = true;
            return this;
        }

        public Cookie build() {
            return new Cookie(this);
        }
    }

    private static long parseExpires(java.lang.String r12, int r13, int r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:58:0x0127 in {7, 11, 15, 19, 20, 25, 29, 45, 47, 49, 51, 53, 55, 57} preds:[]
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
        r13 = dateCharacterOffset(r12, r13, r14, r0);
        r1 = TIME_PATTERN;
        r1 = r1.matcher(r12);
        r2 = -1;
        r3 = -1;
        r4 = -1;
        r5 = -1;
        r6 = -1;
        r7 = -1;
        r8 = -1;
    L_0x0012:
        r9 = 2;
        r10 = 1;
        if (r13 >= r14) goto L_0x00a7;
    L_0x0016:
        r11 = r13 + 1;
        r11 = dateCharacterOffset(r12, r11, r14, r10);
        r1.region(r13, r11);
        if (r4 != r2) goto L_0x004a;
    L_0x0021:
        r13 = TIME_PATTERN;
        r13 = r1.usePattern(r13);
        r13 = r13.matches();
        if (r13 == 0) goto L_0x004a;
    L_0x002d:
        r13 = r1.group(r10);
        r13 = java.lang.Integer.parseInt(r13);
        r4 = r1.group(r9);
        r4 = java.lang.Integer.parseInt(r4);
        r7 = 3;
        r7 = r1.group(r7);
        r7 = java.lang.Integer.parseInt(r7);
        r8 = r7;
        r7 = r4;
        r4 = r13;
        goto L_0x009f;
    L_0x004a:
        if (r5 != r2) goto L_0x0062;
    L_0x004c:
        r13 = DAY_OF_MONTH_PATTERN;
        r13 = r1.usePattern(r13);
        r13 = r13.matches();
        if (r13 == 0) goto L_0x0062;
    L_0x0058:
        r13 = r1.group(r10);
        r13 = java.lang.Integer.parseInt(r13);
        r5 = r13;
        goto L_0x009f;
    L_0x0062:
        if (r6 != r2) goto L_0x0088;
    L_0x0064:
        r13 = MONTH_PATTERN;
        r13 = r1.usePattern(r13);
        r13 = r13.matches();
        if (r13 == 0) goto L_0x0088;
    L_0x0070:
        r13 = r1.group(r10);
        r6 = java.util.Locale.US;
        r13 = r13.toLowerCase(r6);
        r6 = MONTH_PATTERN;
        r6 = r6.pattern();
        r13 = r6.indexOf(r13);
        r13 = r13 / 4;
        r6 = r13;
        goto L_0x009f;
    L_0x0088:
        if (r3 != r2) goto L_0x009f;
    L_0x008a:
        r13 = YEAR_PATTERN;
        r13 = r1.usePattern(r13);
        r13 = r13.matches();
        if (r13 == 0) goto L_0x009f;
    L_0x0096:
        r13 = r1.group(r10);
        r13 = java.lang.Integer.parseInt(r13);
        r3 = r13;
    L_0x009f:
        r11 = r11 + 1;
        r13 = dateCharacterOffset(r12, r11, r14, r0);
        goto L_0x0012;
    L_0x00a7:
        r12 = 70;
        if (r3 < r12) goto L_0x00b1;
    L_0x00ab:
        r12 = 99;
        if (r3 > r12) goto L_0x00b1;
    L_0x00af:
        r3 = r3 + 1900;
    L_0x00b1:
        if (r3 < 0) goto L_0x00b9;
    L_0x00b3:
        r12 = 69;
        if (r3 > r12) goto L_0x00b9;
    L_0x00b7:
        r3 = r3 + 2000;
    L_0x00b9:
        r12 = 1601; // 0x641 float:2.243E-42 double:7.91E-321;
        if (r3 < r12) goto L_0x0121;
    L_0x00bd:
        if (r6 == r2) goto L_0x011b;
    L_0x00bf:
        if (r5 < r10) goto L_0x0115;
    L_0x00c1:
        r12 = 31;
        if (r5 > r12) goto L_0x0115;
    L_0x00c5:
        if (r4 < 0) goto L_0x010f;
    L_0x00c7:
        r12 = 23;
        if (r4 > r12) goto L_0x010f;
    L_0x00cb:
        if (r7 < 0) goto L_0x0109;
    L_0x00cd:
        r12 = 59;
        if (r7 > r12) goto L_0x0109;
    L_0x00d1:
        if (r8 < 0) goto L_0x0103;
    L_0x00d3:
        if (r8 > r12) goto L_0x0103;
    L_0x00d5:
        r12 = new java.util.GregorianCalendar;
        r13 = okhttp3.internal.Util.UTC;
        r12.<init>(r13);
        r12.setLenient(r0);
        r12.set(r10, r3);
        r6 = r6 - r10;
        r12.set(r9, r6);
        r13 = 5;
        r12.set(r13, r5);
        r13 = 11;
        r12.set(r13, r4);
        r13 = 12;
        r12.set(r13, r7);
        r13 = 13;
        r12.set(r13, r8);
        r13 = 14;
        r12.set(r13, r0);
        r12 = r12.getTimeInMillis();
        return r12;
    L_0x0103:
        r12 = new java.lang.IllegalArgumentException;
        r12.<init>();
        throw r12;
    L_0x0109:
        r12 = new java.lang.IllegalArgumentException;
        r12.<init>();
        throw r12;
    L_0x010f:
        r12 = new java.lang.IllegalArgumentException;
        r12.<init>();
        throw r12;
    L_0x0115:
        r12 = new java.lang.IllegalArgumentException;
        r12.<init>();
        throw r12;
    L_0x011b:
        r12 = new java.lang.IllegalArgumentException;
        r12.<init>();
        throw r12;
    L_0x0121:
        r12 = new java.lang.IllegalArgumentException;
        r12.<init>();
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cookie.parseExpires(java.lang.String, int, int):long");
    }

    private Cookie(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.name = str;
        this.value = str2;
        this.expiresAt = j;
        this.domain = str3;
        this.path = str4;
        this.secure = z;
        this.httpOnly = z2;
        this.hostOnly = z3;
        this.persistent = z4;
    }

    Cookie(Builder builder) {
        if (builder.name == null) {
            throw new NullPointerException("builder.name == null");
        } else if (builder.value == null) {
            throw new NullPointerException("builder.value == null");
        } else if (builder.domain != null) {
            this.name = builder.name;
            this.value = builder.value;
            this.expiresAt = builder.expiresAt;
            this.domain = builder.domain;
            this.path = builder.path;
            this.secure = builder.secure;
            this.httpOnly = builder.httpOnly;
            this.persistent = builder.persistent;
            this.hostOnly = builder.hostOnly;
        } else {
            throw new NullPointerException("builder.domain == null");
        }
    }

    public String name() {
        return this.name;
    }

    public String value() {
        return this.value;
    }

    public boolean persistent() {
        return this.persistent;
    }

    public long expiresAt() {
        return this.expiresAt;
    }

    public boolean hostOnly() {
        return this.hostOnly;
    }

    public String domain() {
        return this.domain;
    }

    public String path() {
        return this.path;
    }

    public boolean httpOnly() {
        return this.httpOnly;
    }

    public boolean secure() {
        return this.secure;
    }

    public boolean matches(HttpUrl httpUrl) {
        boolean equals;
        if (this.hostOnly) {
            equals = httpUrl.host().equals(this.domain);
        } else {
            equals = domainMatch(httpUrl.host(), this.domain);
        }
        if (!equals || !pathMatch(httpUrl, this.path)) {
            return false;
        }
        if (this.secure && httpUrl.isHttps() == null) {
            return false;
        }
        return true;
    }

    private static boolean domainMatch(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        if (str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == 46 && Util.verifyAsIpAddress(str) == null) {
            return true;
        }
        return null;
    }

    private static boolean pathMatch(HttpUrl httpUrl, String str) {
        httpUrl = httpUrl.encodedPath();
        if (httpUrl.equals(str)) {
            return true;
        }
        if (httpUrl.startsWith(str) && (str.endsWith("/") || httpUrl.charAt(str.length()) == 47)) {
            return true;
        }
        return null;
    }

    @Nullable
    public static Cookie parse(HttpUrl httpUrl, String str) {
        return parse(System.currentTimeMillis(), httpUrl, str);
    }

    @javax.annotation.Nullable
    static okhttp3.Cookie parse(long r24, okhttp3.HttpUrl r26, java.lang.String r27) {
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
        r0 = r27;
        r1 = r27.length();
        r2 = 59;
        r3 = 0;
        r4 = okhttp3.internal.Util.delimiterOffset(r0, r3, r1, r2);
        r5 = 61;
        r6 = okhttp3.internal.Util.delimiterOffset(r0, r3, r4, r5);
        r7 = 0;
        if (r6 != r4) goto L_0x0017;
    L_0x0016:
        return r7;
    L_0x0017:
        r9 = okhttp3.internal.Util.trimSubstring(r0, r3, r6);
        r8 = r9.isEmpty();
        if (r8 != 0) goto L_0x0151;
    L_0x0021:
        r8 = okhttp3.internal.Util.indexOfControlOrNonAscii(r9);
        r10 = -1;
        if (r8 == r10) goto L_0x002a;
    L_0x0028:
        goto L_0x0151;
    L_0x002a:
        r8 = 1;
        r6 = r6 + r8;
        r6 = okhttp3.internal.Util.trimSubstring(r0, r6, r4);
        r11 = okhttp3.internal.Util.indexOfControlOrNonAscii(r6);
        if (r11 == r10) goto L_0x0037;
    L_0x0036:
        return r7;
    L_0x0037:
        r4 = r4 + r8;
        r10 = -1;
        r12 = 253402300799999; // 0xe677d21fdbff float:-1.71647681E11 double:1.251973714024093E-309;
        r8 = r7;
        r19 = r8;
        r14 = r10;
        r21 = r12;
        r17 = 0;
        r18 = 0;
        r20 = 1;
        r23 = 0;
    L_0x004d:
        if (r4 >= r1) goto L_0x00c3;
    L_0x004f:
        r7 = okhttp3.internal.Util.delimiterOffset(r0, r4, r1, r2);
        r2 = okhttp3.internal.Util.delimiterOffset(r0, r4, r7, r5);
        r4 = okhttp3.internal.Util.trimSubstring(r0, r4, r2);
        if (r2 >= r7) goto L_0x0064;
    L_0x005d:
        r2 = r2 + 1;
        r2 = okhttp3.internal.Util.trimSubstring(r0, r2, r7);
        goto L_0x0066;
    L_0x0064:
        r2 = "";
    L_0x0066:
        r5 = "expires";
        r5 = r4.equalsIgnoreCase(r5);
        if (r5 == 0) goto L_0x007b;
    L_0x006e:
        r4 = r2.length();	 Catch:{ IllegalArgumentException -> 0x00bb }
        r4 = parseExpires(r2, r3, r4);	 Catch:{ IllegalArgumentException -> 0x00bb }
        r21 = r4;
        r23 = 1;
        goto L_0x00bb;
    L_0x007b:
        r5 = "max-age";
        r5 = r4.equalsIgnoreCase(r5);
        if (r5 == 0) goto L_0x008b;
    L_0x0083:
        r4 = parseMaxAge(r2);	 Catch:{  }
        r14 = r4;
        r23 = 1;
        goto L_0x00bb;
    L_0x008b:
        r5 = "domain";
        r5 = r4.equalsIgnoreCase(r5);
        if (r5 == 0) goto L_0x009b;
    L_0x0093:
        r2 = parseDomain(r2);	 Catch:{ IllegalArgumentException -> 0x00bb }
        r8 = r2;
        r20 = 0;
        goto L_0x00bb;
    L_0x009b:
        r5 = "path";
        r5 = r4.equalsIgnoreCase(r5);
        if (r5 == 0) goto L_0x00a6;
    L_0x00a3:
        r19 = r2;
        goto L_0x00bb;
    L_0x00a6:
        r2 = "secure";
        r2 = r4.equalsIgnoreCase(r2);
        if (r2 == 0) goto L_0x00b1;
    L_0x00ae:
        r17 = 1;
        goto L_0x00bb;
    L_0x00b1:
        r2 = "httponly";
        r2 = r4.equalsIgnoreCase(r2);
        if (r2 == 0) goto L_0x00bb;
    L_0x00b9:
        r18 = 1;
    L_0x00bb:
        r4 = r7 + 1;
        r2 = 59;
        r5 = 61;
        r7 = 0;
        goto L_0x004d;
    L_0x00c3:
        r0 = -9223372036854775808;
        r2 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1));
        if (r2 != 0) goto L_0x00cb;
    L_0x00c9:
        r11 = r0;
        goto L_0x00f3;
    L_0x00cb:
        r0 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1));
        if (r0 == 0) goto L_0x00f1;
    L_0x00cf:
        r0 = 9223372036854775; // 0x20c49ba5e353f7 float:-3.943512E-16 double:4.663754807431093E-308;
        r2 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1));
        if (r2 > 0) goto L_0x00dd;
    L_0x00d8:
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r14 = r14 * r0;
        goto L_0x00e2;
    L_0x00dd:
        r14 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
    L_0x00e2:
        r0 = r24 + r14;
        r2 = (r0 > r24 ? 1 : (r0 == r24 ? 0 : -1));
        if (r2 < 0) goto L_0x00ef;
    L_0x00e8:
        r2 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1));
        if (r2 <= 0) goto L_0x00ed;
    L_0x00ec:
        goto L_0x00ef;
    L_0x00ed:
        r11 = r0;
        goto L_0x00f3;
    L_0x00ef:
        r11 = r12;
        goto L_0x00f3;
    L_0x00f1:
        r11 = r21;
    L_0x00f3:
        r0 = r26.host();
        if (r8 != 0) goto L_0x00fc;
    L_0x00f9:
        r13 = r0;
        r1 = 0;
        goto L_0x0106;
    L_0x00fc:
        r1 = domainMatch(r0, r8);
        if (r1 != 0) goto L_0x0104;
    L_0x0102:
        r1 = 0;
        return r1;
    L_0x0104:
        r1 = 0;
        r13 = r8;
    L_0x0106:
        r0 = r0.length();
        r2 = r13.length();
        if (r0 == r2) goto L_0x011e;
    L_0x0110:
        r0 = okhttp3.internal.publicsuffix.PublicSuffixDatabase.get();
        r0 = r0.getEffectiveTldPlusOne(r13);
        if (r0 != 0) goto L_0x011b;
    L_0x011a:
        return r1;
    L_0x011b:
        r7 = r19;
        goto L_0x0120;
    L_0x011e:
        r7 = r19;
    L_0x0120:
        if (r7 == 0) goto L_0x012d;
    L_0x0122:
        r0 = "/";
        r0 = r7.startsWith(r0);
        if (r0 != 0) goto L_0x012b;
    L_0x012a:
        goto L_0x012d;
    L_0x012b:
        r14 = r7;
        goto L_0x0141;
    L_0x012d:
        r0 = r26.encodedPath();
        r1 = 47;
        r1 = r0.lastIndexOf(r1);
        if (r1 == 0) goto L_0x013e;
    L_0x0139:
        r0 = r0.substring(r3, r1);
        goto L_0x0140;
    L_0x013e:
        r0 = "/";
    L_0x0140:
        r14 = r0;
    L_0x0141:
        r0 = new okhttp3.Cookie;
        r8 = r0;
        r10 = r6;
        r15 = r17;
        r16 = r18;
        r17 = r20;
        r18 = r23;
        r8.<init>(r9, r10, r11, r13, r14, r15, r16, r17, r18);
        return r0;
    L_0x0151:
        r0 = r7;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cookie.parse(long, okhttp3.HttpUrl, java.lang.String):okhttp3.Cookie");
    }

    private static int dateCharacterOffset(String str, int i, int i2, boolean z) {
        while (i < i2) {
            int i3;
            char charAt = str.charAt(i);
            if ((charAt >= ' ' || charAt == '\t') && charAt < '' && ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'z') && (charAt < 'A' || charAt > 'Z')))) {
                if (charAt != ':') {
                    i3 = 0;
                    if (i3 == (z ^ 1)) {
                        return i;
                    }
                    i++;
                }
            }
            i3 = 1;
            if (i3 == (z ^ 1)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static long parseMaxAge(String str) {
        long j = Long.MIN_VALUE;
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 0) {
                j = parseLong;
            }
            return j;
        } catch (NumberFormatException e) {
            if (str.matches("-?\\d+")) {
                if (str.startsWith("-") == null) {
                    j = Long.MAX_VALUE;
                }
                return j;
            }
            throw e;
        }
    }

    private static String parseDomain(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        str = Util.domainToAscii(str);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException();
    }

    public static List<Cookie> parseAll(HttpUrl httpUrl, Headers headers) {
        headers = headers.values("Set-Cookie");
        int size = headers.size();
        List list = null;
        for (int i = 0; i < size; i++) {
            Cookie parse = parse(httpUrl, (String) headers.get(i));
            if (parse != null) {
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(parse);
            }
        }
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    public String toString() {
        return toString(false);
    }

    String toString(boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.name);
        stringBuilder.append('=');
        stringBuilder.append(this.value);
        if (this.persistent) {
            if (this.expiresAt == Long.MIN_VALUE) {
                stringBuilder.append("; max-age=0");
            } else {
                stringBuilder.append("; expires=");
                stringBuilder.append(HttpDate.format(new Date(this.expiresAt)));
            }
        }
        if (!this.hostOnly) {
            stringBuilder.append("; domain=");
            if (z) {
                stringBuilder.append(".");
            }
            stringBuilder.append(this.domain);
        }
        stringBuilder.append("; path=");
        stringBuilder.append(this.path);
        if (this.secure) {
            stringBuilder.append("; secure");
        }
        if (this.httpOnly) {
            stringBuilder.append("; httponly");
        }
        return stringBuilder.toString();
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = false;
        if (!(obj instanceof Cookie)) {
            return false;
        }
        Cookie cookie = (Cookie) obj;
        if (cookie.name.equals(this.name) && cookie.value.equals(this.value) && cookie.domain.equals(this.domain) && cookie.path.equals(this.path) && cookie.expiresAt == this.expiresAt && cookie.secure == this.secure && cookie.httpOnly == this.httpOnly && cookie.persistent == this.persistent && cookie.hostOnly == this.hostOnly) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = (((((((527 + this.name.hashCode()) * 31) + this.value.hashCode()) * 31) + this.domain.hashCode()) * 31) + this.path.hashCode()) * 31;
        long j = this.expiresAt;
        return ((((((((hashCode + ((int) (j ^ (j >>> 32)))) * 31) + (this.secure ^ 1)) * 31) + (this.httpOnly ^ 1)) * 31) + (this.persistent ^ 1)) * 31) + (this.hostOnly ^ 1);
    }
}
