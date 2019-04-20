package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.security.cert.CertificateException;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import okhttp3.ConnectionSpec;

public final class ConnectionSpecSelector {
    private final List<ConnectionSpec> connectionSpecs;
    private boolean isFallback;
    private boolean isFallbackPossible;
    private int nextModeIndex = 0;

    public okhttp3.ConnectionSpec configureSecureSocket(javax.net.ssl.SSLSocket r5) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:12:0x0064 in {4, 5, 6, 9, 11} preds:[]
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
        r0 = r4.nextModeIndex;
        r1 = r4.connectionSpecs;
        r1 = r1.size();
    L_0x0008:
        if (r0 >= r1) goto L_0x0020;
    L_0x000a:
        r2 = r4.connectionSpecs;
        r2 = r2.get(r0);
        r2 = (okhttp3.ConnectionSpec) r2;
        r3 = r2.isCompatible(r5);
        if (r3 == 0) goto L_0x001d;
    L_0x0018:
        r0 = r0 + 1;
        r4.nextModeIndex = r0;
        goto L_0x0021;
    L_0x001d:
        r0 = r0 + 1;
        goto L_0x0008;
    L_0x0020:
        r2 = 0;
    L_0x0021:
        if (r2 == 0) goto L_0x0031;
    L_0x0023:
        r0 = r4.isFallbackPossible(r5);
        r4.isFallbackPossible = r0;
        r0 = okhttp3.internal.Internal.instance;
        r1 = r4.isFallback;
        r0.apply(r2, r5, r1);
        return r2;
    L_0x0031:
        r0 = new java.net.UnknownServiceException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Unable to find acceptable protocols. isFallback=";
        r1.append(r2);
        r2 = r4.isFallback;
        r1.append(r2);
        r2 = ", modes=";
        r1.append(r2);
        r2 = r4.connectionSpecs;
        r1.append(r2);
        r2 = ", supported protocols=";
        r1.append(r2);
        r5 = r5.getEnabledProtocols();
        r5 = java.util.Arrays.toString(r5);
        r1.append(r5);
        r5 = r1.toString();
        r0.<init>(r5);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.ConnectionSpecSelector.configureSecureSocket(javax.net.ssl.SSLSocket):okhttp3.ConnectionSpec");
    }

    public ConnectionSpecSelector(List<ConnectionSpec> list) {
        this.connectionSpecs = list;
    }

    public boolean connectionFailed(IOException iOException) {
        boolean z = true;
        this.isFallback = true;
        if (!this.isFallbackPossible || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z2 = iOException instanceof SSLHandshakeException;
        if ((z2 && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        if (!z2) {
            if ((iOException instanceof SSLProtocolException) == null) {
                z = false;
            }
        }
        return z;
    }

    private boolean isFallbackPossible(SSLSocket sSLSocket) {
        for (int i = this.nextModeIndex; i < this.connectionSpecs.size(); i++) {
            if (((ConnectionSpec) this.connectionSpecs.get(i)).isCompatible(sSLSocket)) {
                return true;
            }
        }
        return null;
    }
}
