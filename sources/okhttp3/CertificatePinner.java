package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new Builder().build();
    @Nullable
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    public static final class Builder {
        private final List<Pin> pins = new ArrayList();

        public okhttp3.CertificatePinner.Builder add(java.lang.String r6, java.lang.String... r7) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:7:0x001e in {3, 4, 6} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r5 = this;
            if (r6 == 0) goto L_0x0016;
        L_0x0002:
            r0 = r7.length;
            r1 = 0;
        L_0x0004:
            if (r1 >= r0) goto L_0x0015;
        L_0x0006:
            r2 = r7[r1];
            r3 = r5.pins;
            r4 = new okhttp3.CertificatePinner$Pin;
            r4.<init>(r6, r2);
            r3.add(r4);
            r1 = r1 + 1;
            goto L_0x0004;
        L_0x0015:
            return r5;
        L_0x0016:
            r6 = new java.lang.NullPointerException;
            r7 = "pattern == null";
            r6.<init>(r7);
            throw r6;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.CertificatePinner.Builder.add(java.lang.String, java.lang.String[]):okhttp3.CertificatePinner$Builder");
        }

        public CertificatePinner build() {
            return new CertificatePinner(new LinkedHashSet(this.pins), null);
        }
    }

    static final class Pin {
        private static final String WILDCARD = "*.";
        final String canonicalHostname;
        final ByteString hash;
        final String hashAlgorithm;
        final String pattern;

        Pin(String str, String str2) {
            StringBuilder stringBuilder;
            this.pattern = str;
            if (str.startsWith(WILDCARD)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("http://");
                stringBuilder.append(str.substring(2));
                str = HttpUrl.parse(stringBuilder.toString()).host();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("http://");
                stringBuilder.append(str);
                str = HttpUrl.parse(stringBuilder.toString()).host();
            }
            this.canonicalHostname = str;
            if (str2.startsWith("sha1/") != null) {
                this.hashAlgorithm = "sha1/";
                this.hash = ByteString.decodeBase64(str2.substring(5));
            } else if (str2.startsWith("sha256/") != null) {
                this.hashAlgorithm = "sha256/";
                this.hash = ByteString.decodeBase64(str2.substring(7));
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("pins must start with 'sha256/' or 'sha1/': ");
                stringBuilder.append(str2);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
            if (this.hash == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("pins must be base64: ");
                stringBuilder.append(str2);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }

        boolean matches(String str) {
            if (!this.pattern.startsWith(WILDCARD)) {
                return str.equals(this.canonicalHostname);
            }
            int indexOf = str.indexOf(46) + 1;
            String str2 = this.canonicalHostname;
            return str.regionMatches(false, indexOf, str2, 0, str2.length());
        }

        public boolean equals(Object obj) {
            if (obj instanceof Pin) {
                Pin pin = (Pin) obj;
                if (this.pattern.equals(pin.pattern) && this.hashAlgorithm.equals(pin.hashAlgorithm) && this.hash.equals(pin.hash) != null) {
                    return true;
                }
            }
            return null;
        }

        public int hashCode() {
            return ((((527 + this.pattern.hashCode()) * 31) + this.hashAlgorithm.hashCode()) * 31) + this.hash.hashCode();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.hashAlgorithm);
            stringBuilder.append(this.hash.base64());
            return stringBuilder.toString();
        }
    }

    public void check(java.lang.String r13, java.util.List<java.security.cert.Certificate> r14) throws javax.net.ssl.SSLPeerUnverifiedException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:36:0x00d8 in {2, 5, 13, 16, 20, 23, 24, 26, 27, 30, 33, 35} preds:[]
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
        r12 = this;
        r0 = r12.findMatchingPins(r13);
        r1 = r0.isEmpty();
        if (r1 == 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r1 = r12.certificateChainCleaner;
        if (r1 == 0) goto L_0x0013;
    L_0x000f:
        r14 = r1.clean(r14, r13);
    L_0x0013:
        r1 = r14.size();
        r2 = 0;
        r3 = 0;
    L_0x0019:
        if (r3 >= r1) goto L_0x006f;
    L_0x001b:
        r4 = r14.get(r3);
        r4 = (java.security.cert.X509Certificate) r4;
        r5 = r0.size();
        r6 = 0;
        r7 = r6;
        r8 = r7;
        r6 = 0;
    L_0x0029:
        if (r6 >= r5) goto L_0x006c;
    L_0x002b:
        r9 = r0.get(r6);
        r9 = (okhttp3.CertificatePinner.Pin) r9;
        r10 = r9.hashAlgorithm;
        r11 = "sha256/";
        r10 = r10.equals(r11);
        if (r10 == 0) goto L_0x004a;
    L_0x003b:
        if (r7 != 0) goto L_0x0041;
    L_0x003d:
        r7 = sha256(r4);
    L_0x0041:
        r9 = r9.hash;
        r9 = r9.equals(r7);
        if (r9 == 0) goto L_0x0063;
    L_0x0049:
        return;
    L_0x004a:
        r10 = r9.hashAlgorithm;
        r11 = "sha1/";
        r10 = r10.equals(r11);
        if (r10 == 0) goto L_0x0066;
    L_0x0054:
        if (r8 != 0) goto L_0x005a;
    L_0x0056:
        r8 = sha1(r4);
    L_0x005a:
        r9 = r9.hash;
        r9 = r9.equals(r8);
        if (r9 == 0) goto L_0x0063;
    L_0x0062:
        return;
    L_0x0063:
        r6 = r6 + 1;
        goto L_0x0029;
    L_0x0066:
        r13 = new java.lang.AssertionError;
        r13.<init>();
        throw r13;
    L_0x006c:
        r3 = r3 + 1;
        goto L_0x0019;
    L_0x006f:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "Certificate pinning failure!";
        r1.append(r3);
        r3 = "\n  Peer certificate chain:";
        r1.append(r3);
        r3 = r14.size();
        r4 = 0;
    L_0x0083:
        if (r4 >= r3) goto L_0x00aa;
    L_0x0085:
        r5 = r14.get(r4);
        r5 = (java.security.cert.X509Certificate) r5;
        r6 = "\n    ";
        r1.append(r6);
        r6 = pin(r5);
        r1.append(r6);
        r6 = ": ";
        r1.append(r6);
        r5 = r5.getSubjectDN();
        r5 = r5.getName();
        r1.append(r5);
        r4 = r4 + 1;
        goto L_0x0083;
    L_0x00aa:
        r14 = "\n  Pinned certificates for ";
        r1.append(r14);
        r1.append(r13);
        r13 = ":";
        r1.append(r13);
        r13 = r0.size();
    L_0x00bb:
        if (r2 >= r13) goto L_0x00ce;
    L_0x00bd:
        r14 = r0.get(r2);
        r14 = (okhttp3.CertificatePinner.Pin) r14;
        r3 = "\n    ";
        r1.append(r3);
        r1.append(r14);
        r2 = r2 + 1;
        goto L_0x00bb;
    L_0x00ce:
        r13 = new javax.net.ssl.SSLPeerUnverifiedException;
        r14 = r1.toString();
        r13.<init>(r14);
        throw r13;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.CertificatePinner.check(java.lang.String, java.util.List):void");
    }

    CertificatePinner(Set<Pin> set, @Nullable CertificateChainCleaner certificateChainCleaner) {
        this.pins = set;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (obj instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            if (Util.equal(this.certificateChainCleaner, certificatePinner.certificateChainCleaner) && this.pins.equals(certificatePinner.pins) != null) {
                return z;
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
        return ((certificateChainCleaner != null ? certificateChainCleaner.hashCode() : 0) * 31) + this.pins.hashCode();
    }

    public void check(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        check(str, Arrays.asList(certificateArr));
    }

    List<Pin> findMatchingPins(String str) {
        List<Pin> emptyList = Collections.emptyList();
        for (Pin pin : this.pins) {
            if (pin.matches(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList();
                }
                emptyList.add(pin);
            }
        }
        return emptyList;
    }

    CertificatePinner withCertificateChainCleaner(CertificateChainCleaner certificateChainCleaner) {
        if (Util.equal(this.certificateChainCleaner, certificateChainCleaner)) {
            return this;
        }
        return new CertificatePinner(this.pins, certificateChainCleaner);
    }

    public static String pin(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("sha256/");
            stringBuilder.append(sha256((X509Certificate) certificate).base64());
            return stringBuilder.toString();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    static ByteString sha1(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha1();
    }

    static ByteString sha256(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha256();
    }
}
