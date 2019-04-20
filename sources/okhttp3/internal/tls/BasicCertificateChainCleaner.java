package okhttp3.internal.tls;

public final class BasicCertificateChainCleaner extends CertificateChainCleaner {
    private static final int MAX_SIGNERS = 9;
    private final TrustRootIndex trustRootIndex;

    public java.util.List<java.security.cert.Certificate> clean(java.util.List<java.security.cert.Certificate> r7, java.lang.String r8) throws javax.net.ssl.SSLPeerUnverifiedException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:27:0x0093 in {8, 9, 12, 13, 19, 20, 22, 24, 26} preds:[]
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
        r6 = this;
        r8 = new java.util.ArrayDeque;
        r8.<init>(r7);
        r7 = new java.util.ArrayList;
        r7.<init>();
        r0 = r8.removeFirst();
        r7.add(r0);
        r0 = 0;
        r1 = 0;
    L_0x0013:
        r2 = 9;
        if (r0 >= r2) goto L_0x007c;
    L_0x0017:
        r2 = r7.size();
        r3 = 1;
        r2 = r2 - r3;
        r2 = r7.get(r2);
        r2 = (java.security.cert.X509Certificate) r2;
        r4 = r6.trustRootIndex;
        r4 = r4.findByIssuerAndSignature(r2);
        if (r4 == 0) goto L_0x0043;
    L_0x002b:
        r1 = r7.size();
        if (r1 > r3) goto L_0x0037;
    L_0x0031:
        r1 = r2.equals(r4);
        if (r1 != 0) goto L_0x003a;
    L_0x0037:
        r7.add(r4);
    L_0x003a:
        r1 = r6.verifySignature(r4, r4);
        if (r1 == 0) goto L_0x0041;
    L_0x0040:
        return r7;
    L_0x0041:
        r1 = 1;
        goto L_0x005f;
    L_0x0043:
        r3 = r8.iterator();
    L_0x0047:
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x0062;
    L_0x004d:
        r4 = r3.next();
        r4 = (java.security.cert.X509Certificate) r4;
        r5 = r6.verifySignature(r2, r4);
        if (r5 == 0) goto L_0x0047;
    L_0x0059:
        r3.remove();
        r7.add(r4);
    L_0x005f:
        r0 = r0 + 1;
        goto L_0x0013;
    L_0x0062:
        if (r1 == 0) goto L_0x0065;
    L_0x0064:
        return r7;
    L_0x0065:
        r7 = new javax.net.ssl.SSLPeerUnverifiedException;
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r0 = "Failed to find a trusted cert that signed ";
        r8.append(r0);
        r8.append(r2);
        r8 = r8.toString();
        r7.<init>(r8);
        throw r7;
    L_0x007c:
        r8 = new javax.net.ssl.SSLPeerUnverifiedException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Certificate chain too long: ";
        r0.append(r1);
        r0.append(r7);
        r7 = r0.toString();
        r8.<init>(r7);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.BasicCertificateChainCleaner.clean(java.util.List, java.lang.String):java.util.List<java.security.cert.Certificate>");
    }

    public BasicCertificateChainCleaner(TrustRootIndex trustRootIndex) {
        this.trustRootIndex = trustRootIndex;
    }

    private boolean verifySignature(java.security.cert.X509Certificate r3, java.security.cert.X509Certificate r4) {
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
        r2 = this;
        r0 = r3.getIssuerDN();
        r1 = r4.getSubjectDN();
        r0 = r0.equals(r1);
        r1 = 0;
        if (r0 != 0) goto L_0x0010;
    L_0x000f:
        return r1;
    L_0x0010:
        r4 = r4.getPublicKey();	 Catch:{ GeneralSecurityException -> 0x0019 }
        r3.verify(r4);	 Catch:{ GeneralSecurityException -> 0x0019 }
        r3 = 1;
        return r3;
    L_0x0019:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.BasicCertificateChainCleaner.verifySignature(java.security.cert.X509Certificate, java.security.cert.X509Certificate):boolean");
    }

    public int hashCode() {
        return this.trustRootIndex.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BasicCertificateChainCleaner) || ((BasicCertificateChainCleaner) obj).trustRootIndex.equals(this.trustRootIndex) == null) {
            z = false;
        }
        return z;
    }
}
