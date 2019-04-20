package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Connection.Builder;
import okhttp3.internal.http2.Http2Connection.Listener;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket.Streams;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public final class RealConnection extends Listener implements Connection {
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    public int allocationLimit = 1;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    private final ConnectionPool connectionPool;
    private Handshake handshake;
    private Http2Connection http2Connection;
    public long idleAtNanos = Long.MAX_VALUE;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    public int successCount;

    private void connectTunnel(int r5, int r6, int r7) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:9:0x003d in {5, 6, 8} preds:[]
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
        r4 = this;
        r0 = r4.createTunnelRequest();
        r1 = r0.url();
        r2 = 0;
    L_0x0009:
        r2 = r2 + 1;
        r3 = 21;
        if (r2 > r3) goto L_0x0026;
    L_0x000f:
        r4.connectSocket(r5, r6);
        r0 = r4.createTunnel(r6, r7, r0, r1);
        if (r0 != 0) goto L_0x0019;
    L_0x0018:
        return;
    L_0x0019:
        r3 = r4.rawSocket;
        okhttp3.internal.Util.closeQuietly(r3);
        r3 = 0;
        r4.rawSocket = r3;
        r4.sink = r3;
        r4.source = r3;
        goto L_0x0009;
    L_0x0026:
        r5 = new java.net.ProtocolException;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "Too many tunnel connections attempted: ";
        r6.append(r7);
        r6.append(r3);
        r6 = r6.toString();
        r5.<init>(r6);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connectTunnel(int, int, int):void");
    }

    private okhttp3.Request createTunnel(int r8, int r9, okhttp3.Request r10, okhttp3.HttpUrl r11) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:25:0x00e3 in {3, 12, 13, 15, 17, 22, 24} preds:[]
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
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "CONNECT ";
        r0.append(r1);
        r1 = 1;
        r11 = okhttp3.internal.Util.hostHeader(r11, r1);
        r0.append(r11);
        r11 = " HTTP/1.1";
        r0.append(r11);
        r11 = r0.toString();
    L_0x001b:
        r0 = new okhttp3.internal.http1.Http1Codec;
        r1 = r7.source;
        r2 = r7.sink;
        r3 = 0;
        r0.<init>(r3, r3, r1, r2);
        r1 = r7.source;
        r1 = r1.timeout();
        r4 = (long) r8;
        r2 = java.util.concurrent.TimeUnit.MILLISECONDS;
        r1.timeout(r4, r2);
        r1 = r7.sink;
        r1 = r1.timeout();
        r4 = (long) r9;
        r2 = java.util.concurrent.TimeUnit.MILLISECONDS;
        r1.timeout(r4, r2);
        r1 = r10.headers();
        r0.writeRequest(r1, r11);
        r0.finishRequest();
        r1 = 0;
        r1 = r0.readResponseHeaders(r1);
        r10 = r1.request(r10);
        r10 = r10.build();
        r1 = okhttp3.internal.http.HttpHeaders.contentLength(r10);
        r4 = -1;
        r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1));
        if (r6 != 0) goto L_0x0060;
    L_0x005e:
        r1 = 0;
    L_0x0060:
        r0 = r0.newFixedLengthSource(r1);
        r1 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r2 = java.util.concurrent.TimeUnit.MILLISECONDS;
        okhttp3.internal.Util.skipAll(r0, r1, r2);
        r0.close();
        r0 = r10.code();
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 == r1) goto L_0x00c2;
    L_0x0077:
        r1 = 407; // 0x197 float:5.7E-43 double:2.01E-321;
        if (r0 != r1) goto L_0x00a7;
    L_0x007b:
        r0 = r7.route;
        r0 = r0.address();
        r0 = r0.proxyAuthenticator();
        r1 = r7.route;
        r0 = r0.authenticate(r1, r10);
        if (r0 == 0) goto L_0x009f;
    L_0x008d:
        r1 = "close";
        r2 = "Connection";
        r10 = r10.header(r2);
        r10 = r1.equalsIgnoreCase(r10);
        if (r10 == 0) goto L_0x009c;
    L_0x009b:
        return r0;
    L_0x009c:
        r10 = r0;
        goto L_0x001b;
    L_0x009f:
        r8 = new java.io.IOException;
        r9 = "Failed to authenticate with proxy";
        r8.<init>(r9);
        throw r8;
    L_0x00a7:
        r8 = new java.io.IOException;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r11 = "Unexpected response code for CONNECT: ";
        r9.append(r11);
        r10 = r10.code();
        r9.append(r10);
        r9 = r9.toString();
        r8.<init>(r9);
        throw r8;
    L_0x00c2:
        r8 = r7.source;
        r8 = r8.buffer();
        r8 = r8.exhausted();
        if (r8 == 0) goto L_0x00db;
    L_0x00ce:
        r8 = r7.sink;
        r8 = r8.buffer();
        r8 = r8.exhausted();
        if (r8 == 0) goto L_0x00db;
    L_0x00da:
        return r3;
    L_0x00db:
        r8 = new java.io.IOException;
        r9 = "TLS tunnel buffered too many bytes!";
        r8.<init>(r9);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.createTunnel(int, int, okhttp3.Request, okhttp3.HttpUrl):okhttp3.Request");
    }

    public void connect(int r6, int r7, int r8, boolean r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:44:0x00cd in {8, 10, 12, 17, 18, 27, 30, 31, 35, 36, 40, 41, 43} preds:[]
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
        r5 = this;
        r0 = r5.protocol;
        if (r0 != 0) goto L_0x00c5;
    L_0x0004:
        r0 = r5.route;
        r0 = r0.address();
        r0 = r0.connectionSpecs();
        r1 = new okhttp3.internal.connection.ConnectionSpecSelector;
        r1.<init>(r0);
        r2 = r5.route;
        r2 = r2.address();
        r2 = r2.sslSocketFactory();
        if (r2 != 0) goto L_0x006e;
    L_0x001f:
        r2 = okhttp3.ConnectionSpec.CLEARTEXT;
        r0 = r0.contains(r2);
        if (r0 == 0) goto L_0x0061;
    L_0x0027:
        r0 = r5.route;
        r0 = r0.address();
        r0 = r0.url();
        r0 = r0.host();
        r2 = okhttp3.internal.platform.Platform.get();
        r2 = r2.isCleartextTrafficPermitted(r0);
        if (r2 == 0) goto L_0x0040;
    L_0x003f:
        goto L_0x006e;
    L_0x0040:
        r6 = new okhttp3.internal.connection.RouteException;
        r7 = new java.net.UnknownServiceException;
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "CLEARTEXT communication to ";
        r8.append(r9);
        r8.append(r0);
        r9 = " not permitted by network security policy";
        r8.append(r9);
        r8 = r8.toString();
        r7.<init>(r8);
        r6.<init>(r7);
        throw r6;
    L_0x0061:
        r6 = new okhttp3.internal.connection.RouteException;
        r7 = new java.net.UnknownServiceException;
        r8 = "CLEARTEXT communication not enabled for client";
        r7.<init>(r8);
        r6.<init>(r7);
        throw r6;
    L_0x006e:
        r0 = 0;
        r2 = r0;
    L_0x0070:
        r3 = r5.route;	 Catch:{ IOException -> 0x0097 }
        r3 = r3.requiresTunnel();	 Catch:{ IOException -> 0x0097 }
        if (r3 == 0) goto L_0x007c;	 Catch:{ IOException -> 0x0097 }
    L_0x0078:
        r5.connectTunnel(r6, r7, r8);	 Catch:{ IOException -> 0x0097 }
        goto L_0x007f;	 Catch:{ IOException -> 0x0097 }
    L_0x007c:
        r5.connectSocket(r6, r7);	 Catch:{ IOException -> 0x0097 }
    L_0x007f:
        r5.establishProtocol(r1);	 Catch:{ IOException -> 0x0097 }
        r6 = r5.http2Connection;
        if (r6 == 0) goto L_0x0096;
    L_0x0086:
        r6 = r5.connectionPool;
        monitor-enter(r6);
        r7 = r5.http2Connection;	 Catch:{ all -> 0x0093 }
        r7 = r7.maxConcurrentStreams();	 Catch:{ all -> 0x0093 }
        r5.allocationLimit = r7;	 Catch:{ all -> 0x0093 }
        monitor-exit(r6);	 Catch:{ all -> 0x0093 }
        goto L_0x0096;	 Catch:{ all -> 0x0093 }
    L_0x0093:
        r7 = move-exception;	 Catch:{ all -> 0x0093 }
        monitor-exit(r6);	 Catch:{ all -> 0x0093 }
        throw r7;
    L_0x0096:
        return;
    L_0x0097:
        r3 = move-exception;
        r4 = r5.socket;
        okhttp3.internal.Util.closeQuietly(r4);
        r4 = r5.rawSocket;
        okhttp3.internal.Util.closeQuietly(r4);
        r5.socket = r0;
        r5.rawSocket = r0;
        r5.source = r0;
        r5.sink = r0;
        r5.handshake = r0;
        r5.protocol = r0;
        r5.http2Connection = r0;
        if (r2 != 0) goto L_0x00b8;
    L_0x00b2:
        r2 = new okhttp3.internal.connection.RouteException;
        r2.<init>(r3);
        goto L_0x00bb;
    L_0x00b8:
        r2.addConnectException(r3);
    L_0x00bb:
        if (r9 == 0) goto L_0x00c4;
    L_0x00bd:
        r3 = r1.connectionFailed(r3);
        if (r3 == 0) goto L_0x00c4;
    L_0x00c3:
        goto L_0x0070;
    L_0x00c4:
        throw r2;
    L_0x00c5:
        r6 = new java.lang.IllegalStateException;
        r7 = "already connected";
        r6.<init>(r7);
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connect(int, int, int, boolean):void");
    }

    public RealConnection(ConnectionPool connectionPool, Route route) {
        this.connectionPool = connectionPool;
        this.route = route;
    }

    public static RealConnection testConnection(ConnectionPool connectionPool, Route route, Socket socket, long j) {
        RealConnection realConnection = new RealConnection(connectionPool, route);
        realConnection.socket = socket;
        realConnection.idleAtNanos = j;
        return realConnection;
    }

    private void connectSocket(int i, int i2) throws IOException {
        Socket socket;
        Proxy proxy = this.route.proxy();
        Address address = this.route.address();
        if (proxy.type() != Type.DIRECT) {
            if (proxy.type() != Type.HTTP) {
                socket = new Socket(proxy);
                this.rawSocket = socket;
                this.rawSocket.setSoTimeout(i2);
                Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), i);
                this.source = Okio.buffer(Okio.source(this.rawSocket));
                this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            }
        }
        socket = address.socketFactory().createSocket();
        this.rawSocket = socket;
        this.rawSocket.setSoTimeout(i2);
        try {
            Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), i);
            try {
                this.source = Okio.buffer(Okio.source(this.rawSocket));
                this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            } catch (int i3) {
                if (NPE_THROW_WITH_NULL.equals(i3.getMessage()) != 0) {
                    throw new IOException(i3);
                }
            }
        } catch (int i32) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to connect to ");
            stringBuilder.append(this.route.socketAddress());
            i2 = new ConnectException(stringBuilder.toString());
            i2.initCause(i32);
            throw i2;
        }
    }

    private void establishProtocol(ConnectionSpecSelector connectionSpecSelector) throws IOException {
        if (this.route.address().sslSocketFactory() == null) {
            this.protocol = Protocol.HTTP_1_1;
            this.socket = this.rawSocket;
            return;
        }
        connectTls(connectionSpecSelector);
        if (this.protocol == Protocol.HTTP_2) {
            this.socket.setSoTimeout(0);
            this.http2Connection = new Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).listener(this).build();
            this.http2Connection.start();
        }
    }

    private void connectTls(ConnectionSpecSelector connectionSpecSelector) throws IOException {
        Address address = this.route.address();
        String str = null;
        try {
            Socket socket = (SSLSocket) address.sslSocketFactory().createSocket(this.rawSocket, address.url().host(), address.url().port(), true);
            try {
                connectionSpecSelector = connectionSpecSelector.configureSecureSocket(socket);
                if (connectionSpecSelector.supportsTlsExtensions()) {
                    Platform.get().configureTlsExtensions(socket, address.url().host(), address.protocols());
                }
                socket.startHandshake();
                Handshake handshake = Handshake.get(socket.getSession());
                if (address.hostnameVerifier().verify(address.url().host(), socket.getSession())) {
                    address.certificatePinner().check(address.url().host(), handshake.peerCertificates());
                    if (connectionSpecSelector.supportsTlsExtensions() != null) {
                        str = Platform.get().getSelectedProtocol(socket);
                    }
                    this.socket = socket;
                    this.source = Okio.buffer(Okio.source(this.socket));
                    this.sink = Okio.buffer(Okio.sink(this.socket));
                    this.handshake = handshake;
                    if (str != null) {
                        connectionSpecSelector = Protocol.get(str);
                    } else {
                        connectionSpecSelector = Protocol.HTTP_1_1;
                    }
                    this.protocol = connectionSpecSelector;
                    if (socket != null) {
                        Platform.get().afterHandshake(socket);
                        return;
                    }
                    return;
                }
                X509Certificate x509Certificate = (X509Certificate) handshake.peerCertificates().get(0);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Hostname ");
                stringBuilder.append(address.url().host());
                stringBuilder.append(" not verified:\n    certificate: ");
                stringBuilder.append(CertificatePinner.pin(x509Certificate));
                stringBuilder.append("\n    DN: ");
                stringBuilder.append(x509Certificate.getSubjectDN().getName());
                stringBuilder.append("\n    subjectAltNames: ");
                stringBuilder.append(OkHostnameVerifier.allSubjectAltNames(x509Certificate));
                throw new SSLPeerUnverifiedException(stringBuilder.toString());
            } catch (AssertionError e) {
                connectionSpecSelector = e;
                str = socket;
                try {
                    if (Util.isAndroidGetsocknameError(connectionSpecSelector)) {
                        throw new IOException(connectionSpecSelector);
                    }
                    throw connectionSpecSelector;
                } catch (Throwable th) {
                    connectionSpecSelector = th;
                    socket = str;
                    if (socket != null) {
                        Platform.get().afterHandshake(socket);
                    }
                    Util.closeQuietly(socket);
                    throw connectionSpecSelector;
                }
            } catch (Throwable th2) {
                connectionSpecSelector = th2;
                if (socket != null) {
                    Platform.get().afterHandshake(socket);
                }
                Util.closeQuietly(socket);
                throw connectionSpecSelector;
            }
        } catch (AssertionError e2) {
            connectionSpecSelector = e2;
            if (Util.isAndroidGetsocknameError(connectionSpecSelector)) {
                throw new IOException(connectionSpecSelector);
            }
            throw connectionSpecSelector;
        }
    }

    private Request createTunnelRequest() {
        return new Request.Builder().url(this.route.address().url()).header("Host", Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
    }

    public boolean isEligible(okhttp3.Address r5, @javax.annotation.Nullable okhttp3.Route r6) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = r4.allocations;
        r0 = r0.size();
        r1 = r4.allocationLimit;
        r2 = 0;
        if (r0 >= r1) goto L_0x00a6;
    L_0x000b:
        r0 = r4.noNewStreams;
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        goto L_0x00a6;
    L_0x0011:
        r0 = okhttp3.internal.Internal.instance;
        r1 = r4.route;
        r1 = r1.address();
        r0 = r0.equalsNonHost(r1, r5);
        if (r0 != 0) goto L_0x0020;
    L_0x001f:
        return r2;
    L_0x0020:
        r0 = r5.url();
        r0 = r0.host();
        r1 = r4.route();
        r1 = r1.address();
        r1 = r1.url();
        r1 = r1.host();
        r0 = r0.equals(r1);
        r1 = 1;
        if (r0 == 0) goto L_0x0040;
    L_0x003f:
        return r1;
    L_0x0040:
        r0 = r4.http2Connection;
        if (r0 != 0) goto L_0x0045;
    L_0x0044:
        return r2;
    L_0x0045:
        if (r6 != 0) goto L_0x0048;
    L_0x0047:
        return r2;
    L_0x0048:
        r0 = r6.proxy();
        r0 = r0.type();
        r3 = java.net.Proxy.Type.DIRECT;
        if (r0 == r3) goto L_0x0055;
    L_0x0054:
        return r2;
    L_0x0055:
        r0 = r4.route;
        r0 = r0.proxy();
        r0 = r0.type();
        r3 = java.net.Proxy.Type.DIRECT;
        if (r0 == r3) goto L_0x0064;
    L_0x0063:
        return r2;
    L_0x0064:
        r0 = r4.route;
        r0 = r0.socketAddress();
        r3 = r6.socketAddress();
        r0 = r0.equals(r3);
        if (r0 != 0) goto L_0x0075;
    L_0x0074:
        return r2;
    L_0x0075:
        r6 = r6.address();
        r6 = r6.hostnameVerifier();
        r0 = okhttp3.internal.tls.OkHostnameVerifier.INSTANCE;
        if (r6 == r0) goto L_0x0082;
    L_0x0081:
        return r2;
    L_0x0082:
        r6 = r5.url();
        r6 = r4.supportsUrl(r6);
        if (r6 != 0) goto L_0x008d;
    L_0x008c:
        return r2;
    L_0x008d:
        r6 = r5.certificatePinner();	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        r5 = r5.url();	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        r5 = r5.host();	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        r0 = r4.handshake();	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        r0 = r0.peerCertificates();	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        r6.check(r5, r0);	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        return r1;
    L_0x00a5:
        return r2;
    L_0x00a6:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.isEligible(okhttp3.Address, okhttp3.Route):boolean");
    }

    public boolean supportsUrl(HttpUrl httpUrl) {
        if (httpUrl.port() != this.route.address().url().port()) {
            return false;
        }
        boolean z = true;
        if (httpUrl.host().equals(this.route.address().url().host())) {
            return true;
        }
        if (this.handshake == null || OkHostnameVerifier.INSTANCE.verify(httpUrl.host(), (X509Certificate) this.handshake.peerCertificates().get(0)) == null) {
            z = false;
        }
        return z;
    }

    public HttpCodec newCodec(OkHttpClient okHttpClient, StreamAllocation streamAllocation) throws SocketException {
        Http2Connection http2Connection = this.http2Connection;
        if (http2Connection != null) {
            return new Http2Codec(okHttpClient, streamAllocation, http2Connection);
        }
        this.socket.setSoTimeout(okHttpClient.readTimeoutMillis());
        this.source.timeout().timeout((long) okHttpClient.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.sink.timeout().timeout((long) okHttpClient.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        return new Http1Codec(okHttpClient, streamAllocation, this.source, this.sink);
    }

    public Streams newWebSocketStreams(StreamAllocation streamAllocation) {
        final StreamAllocation streamAllocation2 = streamAllocation;
        return new Streams(true, this.source, this.sink) {
            public void close() throws IOException {
                StreamAllocation streamAllocation = streamAllocation2;
                streamAllocation.streamFinished(true, streamAllocation.codec());
            }
        };
    }

    public Route route() {
        return this.route;
    }

    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }

    public Socket socket() {
        return this.socket;
    }

    public boolean isHealthy(boolean r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = r4.socket;
        r0 = r0.isClosed();
        r1 = 0;
        if (r0 != 0) goto L_0x004f;
    L_0x0009:
        r0 = r4.socket;
        r0 = r0.isInputShutdown();
        if (r0 != 0) goto L_0x004f;
    L_0x0011:
        r0 = r4.socket;
        r0 = r0.isOutputShutdown();
        if (r0 == 0) goto L_0x001a;
    L_0x0019:
        goto L_0x004f;
    L_0x001a:
        r0 = r4.http2Connection;
        r2 = 1;
        if (r0 == 0) goto L_0x0025;
    L_0x001f:
        r5 = r0.isShutdown();
        r5 = r5 ^ r2;
        return r5;
    L_0x0025:
        if (r5 == 0) goto L_0x004e;
    L_0x0027:
        r5 = r4.socket;	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
        r5 = r5.getSoTimeout();	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
        r0 = r4.socket;	 Catch:{ all -> 0x0046 }
        r0.setSoTimeout(r2);	 Catch:{ all -> 0x0046 }
        r0 = r4.source;	 Catch:{ all -> 0x0046 }
        r0 = r0.exhausted();	 Catch:{ all -> 0x0046 }
        if (r0 == 0) goto L_0x0040;
    L_0x003a:
        r0 = r4.socket;	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
        r0.setSoTimeout(r5);	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
        return r1;	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
    L_0x0040:
        r0 = r4.socket;	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
        r0.setSoTimeout(r5);	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
        return r2;	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
    L_0x0046:
        r0 = move-exception;	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
        r3 = r4.socket;	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
        r3.setSoTimeout(r5);	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x004e, IOException -> 0x004d }
    L_0x004d:
        return r1;
    L_0x004e:
        return r2;
    L_0x004f:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.isHealthy(boolean):boolean");
    }

    public void onStream(Http2Stream http2Stream) throws IOException {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }

    public void onSettings(Http2Connection http2Connection) {
        synchronized (this.connectionPool) {
            this.allocationLimit = http2Connection.maxConcurrentStreams();
        }
    }

    public Handshake handshake() {
        return this.handshake;
    }

    public boolean isMultiplexed() {
        return this.http2Connection != null;
    }

    public Protocol protocol() {
        return this.protocol;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Connection{");
        stringBuilder.append(this.route.address().url().host());
        stringBuilder.append(":");
        stringBuilder.append(this.route.address().url().port());
        stringBuilder.append(", proxy=");
        stringBuilder.append(this.route.proxy());
        stringBuilder.append(" hostAddress=");
        stringBuilder.append(this.route.socketAddress());
        stringBuilder.append(" cipherSuite=");
        Handshake handshake = this.handshake;
        stringBuilder.append(handshake != null ? handshake.cipherSuite() : "none");
        stringBuilder.append(" protocol=");
        stringBuilder.append(this.protocol);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
