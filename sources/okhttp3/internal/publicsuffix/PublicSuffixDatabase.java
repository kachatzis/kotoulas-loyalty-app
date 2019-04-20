package okhttp3.internal.publicsuffix;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.GzipSource;
import okio.Okio;

public final class PublicSuffixDatabase {
    private static final String[] EMPTY_RULE = new String[0];
    private static final byte EXCEPTION_MARKER = (byte) 33;
    private static final String[] PREVAILING_RULE = new String[]{"*"};
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private static final byte[] WILDCARD_LABEL = new byte[]{(byte) 42};
    private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private byte[] publicSuffixExceptionListBytes;
    private byte[] publicSuffixListBytes;
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);

    private java.lang.String[] findMatchingRule(java.lang.String[] r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:66:0x00c2 in {4, 6, 15, 21, 22, 23, 31, 32, 33, 39, 40, 41, 44, 48, 50, 51, 53, 54, 57, 58, 59, 62, 65} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = r7.listRead;
        r0 = r0.get();
        r1 = 0;
        r2 = 1;
        if (r0 != 0) goto L_0x0016;
    L_0x000a:
        r0 = r7.listRead;
        r0 = r0.compareAndSet(r1, r2);
        if (r0 == 0) goto L_0x0016;
    L_0x0012:
        r7.readTheList();
        goto L_0x001b;
    L_0x0016:
        r0 = r7.readCompleteLatch;	 Catch:{ InterruptedException -> 0x001b }
        r0.await();	 Catch:{ InterruptedException -> 0x001b }
    L_0x001b:
        monitor-enter(r7);
        r0 = r7.publicSuffixListBytes;	 Catch:{ all -> 0x00bf }
        if (r0 == 0) goto L_0x00b7;	 Catch:{ all -> 0x00bf }
    L_0x0020:
        monitor-exit(r7);	 Catch:{ all -> 0x00bf }
        r0 = r8.length;
        r0 = new byte[r0][];
        r3 = 0;
    L_0x0025:
        r4 = r8.length;
        if (r3 >= r4) goto L_0x0035;
    L_0x0028:
        r4 = r8[r3];
        r5 = okhttp3.internal.Util.UTF_8;
        r4 = r4.getBytes(r5);
        r0[r3] = r4;
        r3 = r3 + 1;
        goto L_0x0025;
    L_0x0035:
        r8 = 0;
    L_0x0036:
        r3 = r0.length;
        r4 = 0;
        if (r8 >= r3) goto L_0x0046;
    L_0x003a:
        r3 = r7.publicSuffixListBytes;
        r3 = binarySearchBytes(r3, r0, r8);
        if (r3 == 0) goto L_0x0043;
    L_0x0042:
        goto L_0x0047;
    L_0x0043:
        r8 = r8 + 1;
        goto L_0x0036;
    L_0x0046:
        r3 = r4;
    L_0x0047:
        r8 = r0.length;
        if (r8 <= r2) goto L_0x0065;
    L_0x004a:
        r8 = r0.clone();
        r8 = (byte[][]) r8;
        r5 = 0;
    L_0x0051:
        r6 = r8.length;
        r6 = r6 - r2;
        if (r5 >= r6) goto L_0x0065;
    L_0x0055:
        r6 = WILDCARD_LABEL;
        r8[r5] = r6;
        r6 = r7.publicSuffixListBytes;
        r6 = binarySearchBytes(r6, r8, r5);
        if (r6 == 0) goto L_0x0062;
    L_0x0061:
        goto L_0x0066;
    L_0x0062:
        r5 = r5 + 1;
        goto L_0x0051;
    L_0x0065:
        r6 = r4;
    L_0x0066:
        if (r6 == 0) goto L_0x0078;
    L_0x0068:
        r8 = r0.length;
        r8 = r8 - r2;
        if (r1 >= r8) goto L_0x0078;
    L_0x006c:
        r8 = r7.publicSuffixExceptionListBytes;
        r8 = binarySearchBytes(r8, r0, r1);
        if (r8 == 0) goto L_0x0075;
    L_0x0074:
        goto L_0x0079;
    L_0x0075:
        r1 = r1 + 1;
        goto L_0x0068;
    L_0x0078:
        r8 = r4;
    L_0x0079:
        if (r8 == 0) goto L_0x0093;
    L_0x007b:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "!";
        r0.append(r1);
        r0.append(r8);
        r8 = r0.toString();
        r0 = "\\.";
        r8 = r8.split(r0);
        return r8;
    L_0x0093:
        if (r3 != 0) goto L_0x009a;
    L_0x0095:
        if (r6 != 0) goto L_0x009a;
    L_0x0097:
        r8 = PREVAILING_RULE;
        return r8;
    L_0x009a:
        if (r3 == 0) goto L_0x00a3;
    L_0x009c:
        r8 = "\\.";
        r8 = r3.split(r8);
        goto L_0x00a5;
    L_0x00a3:
        r8 = EMPTY_RULE;
    L_0x00a5:
        if (r6 == 0) goto L_0x00ae;
    L_0x00a7:
        r0 = "\\.";
        r0 = r6.split(r0);
        goto L_0x00b0;
    L_0x00ae:
        r0 = EMPTY_RULE;
    L_0x00b0:
        r1 = r8.length;
        r2 = r0.length;
        if (r1 <= r2) goto L_0x00b5;
    L_0x00b4:
        goto L_0x00b6;
    L_0x00b5:
        r8 = r0;
    L_0x00b6:
        return r8;
    L_0x00b7:
        r8 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x00bf }
        r0 = "Unable to load publicsuffixes.gz resource from the classpath.";	 Catch:{ all -> 0x00bf }
        r8.<init>(r0);	 Catch:{ all -> 0x00bf }
        throw r8;	 Catch:{ all -> 0x00bf }
    L_0x00bf:
        r8 = move-exception;	 Catch:{ all -> 0x00bf }
        monitor-exit(r7);	 Catch:{ all -> 0x00bf }
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.findMatchingRule(java.lang.String[]):java.lang.String[]");
    }

    public java.lang.String getEffectiveTldPlusOne(java.lang.String r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:19:0x0063 in {6, 9, 10, 14, 16, 18} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r6 = this;
        if (r7 == 0) goto L_0x005b;
    L_0x0002:
        r0 = java.net.IDN.toUnicode(r7);
        r1 = "\\.";
        r0 = r0.split(r1);
        r1 = r6.findMatchingRule(r0);
        r2 = r0.length;
        r3 = r1.length;
        r4 = 33;
        r5 = 0;
        if (r2 != r3) goto L_0x0021;
    L_0x0017:
        r2 = r1[r5];
        r2 = r2.charAt(r5);
        if (r2 == r4) goto L_0x0021;
    L_0x001f:
        r7 = 0;
        return r7;
    L_0x0021:
        r2 = r1[r5];
        r2 = r2.charAt(r5);
        if (r2 != r4) goto L_0x002d;
    L_0x0029:
        r0 = r0.length;
        r1 = r1.length;
        r0 = r0 - r1;
        goto L_0x0032;
    L_0x002d:
        r0 = r0.length;
        r1 = r1.length;
        r1 = r1 + 1;
        r0 = r0 - r1;
    L_0x0032:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "\\.";
        r7 = r7.split(r2);
    L_0x003d:
        r2 = r7.length;
        if (r0 >= r2) goto L_0x004d;
    L_0x0040:
        r2 = r7[r0];
        r1.append(r2);
        r2 = 46;
        r1.append(r2);
        r0 = r0 + 1;
        goto L_0x003d;
    L_0x004d:
        r7 = r1.length();
        r7 = r7 + -1;
        r1.deleteCharAt(r7);
        r7 = r1.toString();
        return r7;
    L_0x005b:
        r7 = new java.lang.NullPointerException;
        r0 = "domain == null";
        r7.<init>(r0);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.getEffectiveTldPlusOne(java.lang.String):java.lang.String");
    }

    public static PublicSuffixDatabase get() {
        return instance;
    }

    private static String binarySearchBytes(byte[] bArr, byte[][] bArr2, int i) {
        byte[] bArr3 = bArr;
        byte[][] bArr4 = bArr2;
        int length = bArr3.length;
        int i2 = 0;
        while (i2 < length) {
            int i3;
            int i4 = (i2 + length) / 2;
            while (i4 > -1 && bArr3[i4] != (byte) 10) {
                i4--;
            }
            i4++;
            int i5 = 1;
            while (true) {
                i3 = i4 + i5;
                if (bArr3[i3] == (byte) 10) {
                    break;
                }
                i5++;
            }
            int i6 = i3 - i4;
            int i7 = i;
            Object obj = null;
            int i8 = 0;
            int i9 = 0;
            while (true) {
                int i10;
                if (obj != null) {
                    obj = null;
                    i10 = 46;
                } else {
                    i10 = bArr4[i7][i8] & 255;
                }
                i10 -= bArr3[i4 + i9] & 255;
                if (i10 != 0) {
                    break;
                }
                i9++;
                i8++;
                if (i9 == i6) {
                    break;
                } else if (bArr4[i7].length == i8) {
                    if (i7 == bArr4.length - 1) {
                        break;
                    }
                    i7++;
                    obj = 1;
                    i8 = -1;
                }
            }
            if (i10 < 0) {
                length = i4 - 1;
            } else if (i10 > 0) {
                i2 = i3 + 1;
            } else {
                int i11 = i6 - i9;
                int length2 = bArr4[i7].length - i8;
                while (true) {
                    i7++;
                    if (i7 >= bArr4.length) {
                        break;
                    }
                    length2 += bArr4[i7].length;
                }
                if (length2 < i11) {
                    length = i4 - 1;
                } else if (length2 <= i11) {
                    return new String(bArr3, i4, i6, Util.UTF_8);
                } else {
                    i2 = i3 + 1;
                }
            }
        }
        return null;
    }

    private void readTheList() {
        byte[] bArr;
        InputStream resourceAsStream = PublicSuffixDatabase.class.getClassLoader().getResourceAsStream(PUBLIC_SUFFIX_RESOURCE);
        byte[] bArr2 = null;
        if (resourceAsStream != null) {
            Closeable buffer = Okio.buffer(new GzipSource(Okio.source(resourceAsStream)));
            byte[] bArr3;
            try {
                bArr3 = new byte[buffer.readInt()];
                buffer.readFully(bArr3);
                bArr = new byte[buffer.readInt()];
                buffer.readFully(bArr);
                bArr2 = bArr3;
            } catch (IOException e) {
                bArr3 = e;
                bArr = Platform.get();
                bArr.log(5, "Failed to read public suffix list", bArr3);
                bArr = null;
                synchronized (this) {
                    this.publicSuffixListBytes = bArr2;
                    this.publicSuffixExceptionListBytes = bArr;
                }
                this.readCompleteLatch.countDown();
            } finally {
                Util.closeQuietly(buffer);
            }
        } else {
            bArr = null;
        }
        synchronized (this) {
            this.publicSuffixListBytes = bArr2;
            this.publicSuffixExceptionListBytes = bArr;
        }
        this.readCompleteLatch.countDown();
    }

    void setListBytes(byte[] bArr, byte[] bArr2) {
        this.publicSuffixListBytes = bArr;
        this.publicSuffixExceptionListBytes = bArr2;
        this.listRead.set(1);
        this.readCompleteLatch.countDown();
    }
}
