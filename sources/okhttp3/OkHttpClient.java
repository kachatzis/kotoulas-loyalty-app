package okhttp3;

import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call.Factory;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;

public class OkHttpClient implements Cloneable, Factory, WebSocket.Factory {
    static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS = Util.immutableList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT);
    static final List<Protocol> DEFAULT_PROTOCOLS = Util.immutableList(Protocol.HTTP_2, Protocol.HTTP_1_1);
    final Authenticator authenticator;
    @Nullable
    final Cache cache;
    @Nullable
    final CertificateChainCleaner certificateChainCleaner;
    final CertificatePinner certificatePinner;
    final int connectTimeout;
    final ConnectionPool connectionPool;
    final List<ConnectionSpec> connectionSpecs;
    final CookieJar cookieJar;
    final Dispatcher dispatcher;
    final Dns dns;
    final EventListener.Factory eventListenerFactory;
    final boolean followRedirects;
    final boolean followSslRedirects;
    final HostnameVerifier hostnameVerifier;
    final List<Interceptor> interceptors;
    @Nullable
    final InternalCache internalCache;
    final List<Interceptor> networkInterceptors;
    final int pingInterval;
    final List<Protocol> protocols;
    @Nullable
    final Proxy proxy;
    final Authenticator proxyAuthenticator;
    final ProxySelector proxySelector;
    final int readTimeout;
    final boolean retryOnConnectionFailure;
    final SocketFactory socketFactory;
    @Nullable
    final SSLSocketFactory sslSocketFactory;
    final int writeTimeout;

    public static final class Builder {
        Authenticator authenticator;
        @Nullable
        Cache cache;
        @Nullable
        CertificateChainCleaner certificateChainCleaner;
        CertificatePinner certificatePinner;
        int connectTimeout;
        ConnectionPool connectionPool;
        List<ConnectionSpec> connectionSpecs;
        CookieJar cookieJar;
        Dispatcher dispatcher;
        Dns dns;
        EventListener.Factory eventListenerFactory;
        boolean followRedirects;
        boolean followSslRedirects;
        HostnameVerifier hostnameVerifier;
        final List<Interceptor> interceptors;
        @Nullable
        InternalCache internalCache;
        final List<Interceptor> networkInterceptors;
        int pingInterval;
        List<Protocol> protocols;
        @Nullable
        Proxy proxy;
        Authenticator proxyAuthenticator;
        ProxySelector proxySelector;
        int readTimeout;
        boolean retryOnConnectionFailure;
        SocketFactory socketFactory;
        @Nullable
        SSLSocketFactory sslSocketFactory;
        int writeTimeout;

        public Builder() {
            this.interceptors = new ArrayList();
            this.networkInterceptors = new ArrayList();
            this.dispatcher = new Dispatcher();
            this.protocols = OkHttpClient.DEFAULT_PROTOCOLS;
            this.connectionSpecs = OkHttpClient.DEFAULT_CONNECTION_SPECS;
            this.eventListenerFactory = EventListener.factory(EventListener.NONE);
            this.proxySelector = ProxySelector.getDefault();
            this.cookieJar = CookieJar.NO_COOKIES;
            this.socketFactory = SocketFactory.getDefault();
            this.hostnameVerifier = OkHostnameVerifier.INSTANCE;
            this.certificatePinner = CertificatePinner.DEFAULT;
            this.proxyAuthenticator = Authenticator.NONE;
            this.authenticator = Authenticator.NONE;
            this.connectionPool = new ConnectionPool();
            this.dns = Dns.SYSTEM;
            this.followSslRedirects = true;
            this.followRedirects = true;
            this.retryOnConnectionFailure = true;
            this.connectTimeout = 10000;
            this.readTimeout = 10000;
            this.writeTimeout = 10000;
            this.pingInterval = 0;
        }

        Builder(OkHttpClient okHttpClient) {
            this.interceptors = new ArrayList();
            this.networkInterceptors = new ArrayList();
            this.dispatcher = okHttpClient.dispatcher;
            this.proxy = okHttpClient.proxy;
            this.protocols = okHttpClient.protocols;
            this.connectionSpecs = okHttpClient.connectionSpecs;
            this.interceptors.addAll(okHttpClient.interceptors);
            this.networkInterceptors.addAll(okHttpClient.networkInterceptors);
            this.eventListenerFactory = okHttpClient.eventListenerFactory;
            this.proxySelector = okHttpClient.proxySelector;
            this.cookieJar = okHttpClient.cookieJar;
            this.internalCache = okHttpClient.internalCache;
            this.cache = okHttpClient.cache;
            this.socketFactory = okHttpClient.socketFactory;
            this.sslSocketFactory = okHttpClient.sslSocketFactory;
            this.certificateChainCleaner = okHttpClient.certificateChainCleaner;
            this.hostnameVerifier = okHttpClient.hostnameVerifier;
            this.certificatePinner = okHttpClient.certificatePinner;
            this.proxyAuthenticator = okHttpClient.proxyAuthenticator;
            this.authenticator = okHttpClient.authenticator;
            this.connectionPool = okHttpClient.connectionPool;
            this.dns = okHttpClient.dns;
            this.followSslRedirects = okHttpClient.followSslRedirects;
            this.followRedirects = okHttpClient.followRedirects;
            this.retryOnConnectionFailure = okHttpClient.retryOnConnectionFailure;
            this.connectTimeout = okHttpClient.connectTimeout;
            this.readTimeout = okHttpClient.readTimeout;
            this.writeTimeout = okHttpClient.writeTimeout;
            this.pingInterval = okHttpClient.pingInterval;
        }

        public Builder connectTimeout(long j, TimeUnit timeUnit) {
            this.connectTimeout = checkDuration("timeout", j, timeUnit);
            return this;
        }

        public Builder readTimeout(long j, TimeUnit timeUnit) {
            this.readTimeout = checkDuration("timeout", j, timeUnit);
            return this;
        }

        public Builder writeTimeout(long j, TimeUnit timeUnit) {
            this.writeTimeout = checkDuration("timeout", j, timeUnit);
            return this;
        }

        public Builder pingInterval(long j, TimeUnit timeUnit) {
            this.pingInterval = checkDuration("interval", j, timeUnit);
            return this;
        }

        private static int checkDuration(String str, long j, TimeUnit timeUnit) {
            StringBuilder stringBuilder;
            if (j < 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(" < 0");
                throw new IllegalArgumentException(stringBuilder.toString());
            } else if (timeUnit != null) {
                long toMillis = timeUnit.toMillis(j);
                if (toMillis <= 2147483647L) {
                    if (toMillis == 0) {
                        if (j > 0) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(str);
                            stringBuilder.append(" too small.");
                            throw new IllegalArgumentException(stringBuilder.toString());
                        }
                    }
                    return (int) toMillis;
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(" too large.");
                throw new IllegalArgumentException(stringBuilder.toString());
            } else {
                throw new NullPointerException("unit == null");
            }
        }

        public Builder proxy(@Nullable Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        public Builder proxySelector(ProxySelector proxySelector) {
            this.proxySelector = proxySelector;
            return this;
        }

        public Builder cookieJar(CookieJar cookieJar) {
            if (cookieJar != null) {
                this.cookieJar = cookieJar;
                return this;
            }
            throw new NullPointerException("cookieJar == null");
        }

        void setInternalCache(@Nullable InternalCache internalCache) {
            this.internalCache = internalCache;
            this.cache = null;
        }

        public Builder cache(@Nullable Cache cache) {
            this.cache = cache;
            this.internalCache = null;
            return this;
        }

        public Builder dns(Dns dns) {
            if (dns != null) {
                this.dns = dns;
                return this;
            }
            throw new NullPointerException("dns == null");
        }

        public Builder socketFactory(SocketFactory socketFactory) {
            if (socketFactory != null) {
                this.socketFactory = socketFactory;
                return this;
            }
            throw new NullPointerException("socketFactory == null");
        }

        public Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            if (sSLSocketFactory != null) {
                X509TrustManager trustManager = Platform.get().trustManager(sSLSocketFactory);
                if (trustManager != null) {
                    this.sslSocketFactory = sSLSocketFactory;
                    this.certificateChainCleaner = CertificateChainCleaner.get(trustManager);
                    return this;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unable to extract the trust manager on ");
                stringBuilder.append(Platform.get());
                stringBuilder.append(", sslSocketFactory is ");
                stringBuilder.append(sSLSocketFactory.getClass());
                throw new IllegalStateException(stringBuilder.toString());
            }
            throw new NullPointerException("sslSocketFactory == null");
        }

        public Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            } else if (x509TrustManager != null) {
                this.sslSocketFactory = sSLSocketFactory;
                this.certificateChainCleaner = CertificateChainCleaner.get(x509TrustManager);
                return this;
            } else {
                throw new NullPointerException("trustManager == null");
            }
        }

        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier != null) {
                this.hostnameVerifier = hostnameVerifier;
                return this;
            }
            throw new NullPointerException("hostnameVerifier == null");
        }

        public Builder certificatePinner(CertificatePinner certificatePinner) {
            if (certificatePinner != null) {
                this.certificatePinner = certificatePinner;
                return this;
            }
            throw new NullPointerException("certificatePinner == null");
        }

        public Builder authenticator(Authenticator authenticator) {
            if (authenticator != null) {
                this.authenticator = authenticator;
                return this;
            }
            throw new NullPointerException("authenticator == null");
        }

        public Builder proxyAuthenticator(Authenticator authenticator) {
            if (authenticator != null) {
                this.proxyAuthenticator = authenticator;
                return this;
            }
            throw new NullPointerException("proxyAuthenticator == null");
        }

        public Builder connectionPool(ConnectionPool connectionPool) {
            if (connectionPool != null) {
                this.connectionPool = connectionPool;
                return this;
            }
            throw new NullPointerException("connectionPool == null");
        }

        public Builder followSslRedirects(boolean z) {
            this.followSslRedirects = z;
            return this;
        }

        public Builder followRedirects(boolean z) {
            this.followRedirects = z;
            return this;
        }

        public Builder retryOnConnectionFailure(boolean z) {
            this.retryOnConnectionFailure = z;
            return this;
        }

        public Builder dispatcher(Dispatcher dispatcher) {
            if (dispatcher != null) {
                this.dispatcher = dispatcher;
                return this;
            }
            throw new IllegalArgumentException("dispatcher == null");
        }

        public Builder protocols(List<Protocol> list) {
            List arrayList = new ArrayList(list);
            StringBuilder stringBuilder;
            if (arrayList.contains(Protocol.HTTP_1_1) == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("protocols doesn't contain http/1.1: ");
                stringBuilder.append(arrayList);
                throw new IllegalArgumentException(stringBuilder.toString());
            } else if (arrayList.contains(Protocol.HTTP_1_0) != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("protocols must not contain http/1.0: ");
                stringBuilder.append(arrayList);
                throw new IllegalArgumentException(stringBuilder.toString());
            } else if (arrayList.contains(null) == null) {
                arrayList.remove(Protocol.SPDY_3);
                this.protocols = Collections.unmodifiableList(arrayList);
                return this;
            } else {
                throw new IllegalArgumentException("protocols must not contain null");
            }
        }

        public Builder connectionSpecs(List<ConnectionSpec> list) {
            this.connectionSpecs = Util.immutableList((List) list);
            return this;
        }

        public List<Interceptor> interceptors() {
            return this.interceptors;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        public List<Interceptor> networkInterceptors() {
            return this.networkInterceptors;
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            this.networkInterceptors.add(interceptor);
            return this;
        }

        Builder eventListener(EventListener eventListener) {
            if (eventListener != null) {
                this.eventListenerFactory = EventListener.factory(eventListener);
                return this;
            }
            throw new NullPointerException("eventListener == null");
        }

        Builder eventListenerFactory(EventListener.Factory factory) {
            if (factory != null) {
                this.eventListenerFactory = factory;
                return this;
            }
            throw new NullPointerException("eventListenerFactory == null");
        }

        public OkHttpClient build() {
            return new OkHttpClient(this);
        }
    }

    /* renamed from: okhttp3.OkHttpClient$1 */
    class C06201 extends Internal {
        C06201() {
        }

        public void addLenient(okhttp3.Headers.Builder builder, String str) {
            builder.addLenient(str);
        }

        public void addLenient(okhttp3.Headers.Builder builder, String str, String str2) {
            builder.addLenient(str, str2);
        }

        public void setCache(Builder builder, InternalCache internalCache) {
            builder.setInternalCache(internalCache);
        }

        public boolean connectionBecameIdle(ConnectionPool connectionPool, RealConnection realConnection) {
            return connectionPool.connectionBecameIdle(realConnection);
        }

        public RealConnection get(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation, Route route) {
            return connectionPool.get(address, streamAllocation, route);
        }

        public boolean equalsNonHost(Address address, Address address2) {
            return address.equalsNonHost(address2);
        }

        public Socket deduplicate(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation) {
            return connectionPool.deduplicate(address, streamAllocation);
        }

        public void put(ConnectionPool connectionPool, RealConnection realConnection) {
            connectionPool.put(realConnection);
        }

        public RouteDatabase routeDatabase(ConnectionPool connectionPool) {
            return connectionPool.routeDatabase;
        }

        public int code(okhttp3.Response.Builder builder) {
            return builder.code;
        }

        public void apply(ConnectionSpec connectionSpec, SSLSocket sSLSocket, boolean z) {
            connectionSpec.apply(sSLSocket, z);
        }

        public HttpUrl getHttpUrlChecked(String str) throws MalformedURLException, UnknownHostException {
            return HttpUrl.getChecked(str);
        }

        public StreamAllocation streamAllocation(Call call) {
            return ((RealCall) call).streamAllocation();
        }

        public Call newWebSocketCall(OkHttpClient okHttpClient, Request request) {
            return new RealCall(okHttpClient, request, true);
        }
    }

    static {
        Internal.instance = new C06201();
    }

    public OkHttpClient() {
        this(new Builder());
    }

    OkHttpClient(Builder builder) {
        this.dispatcher = builder.dispatcher;
        this.proxy = builder.proxy;
        this.protocols = builder.protocols;
        this.connectionSpecs = builder.connectionSpecs;
        this.interceptors = Util.immutableList(builder.interceptors);
        this.networkInterceptors = Util.immutableList(builder.networkInterceptors);
        this.eventListenerFactory = builder.eventListenerFactory;
        this.proxySelector = builder.proxySelector;
        this.cookieJar = builder.cookieJar;
        this.cache = builder.cache;
        this.internalCache = builder.internalCache;
        this.socketFactory = builder.socketFactory;
        Object obj = null;
        for (ConnectionSpec connectionSpec : this.connectionSpecs) {
            if (obj == null) {
                if (!connectionSpec.isTls()) {
                    obj = null;
                }
            }
            obj = 1;
        }
        if (builder.sslSocketFactory == null) {
            if (obj != null) {
                X509TrustManager systemDefaultTrustManager = systemDefaultTrustManager();
                this.sslSocketFactory = systemDefaultSslSocketFactory(systemDefaultTrustManager);
                this.certificateChainCleaner = CertificateChainCleaner.get(systemDefaultTrustManager);
                this.hostnameVerifier = builder.hostnameVerifier;
                this.certificatePinner = builder.certificatePinner.withCertificateChainCleaner(this.certificateChainCleaner);
                this.proxyAuthenticator = builder.proxyAuthenticator;
                this.authenticator = builder.authenticator;
                this.connectionPool = builder.connectionPool;
                this.dns = builder.dns;
                this.followSslRedirects = builder.followSslRedirects;
                this.followRedirects = builder.followRedirects;
                this.retryOnConnectionFailure = builder.retryOnConnectionFailure;
                this.connectTimeout = builder.connectTimeout;
                this.readTimeout = builder.readTimeout;
                this.writeTimeout = builder.writeTimeout;
                this.pingInterval = builder.pingInterval;
            }
        }
        this.sslSocketFactory = builder.sslSocketFactory;
        this.certificateChainCleaner = builder.certificateChainCleaner;
        this.hostnameVerifier = builder.hostnameVerifier;
        this.certificatePinner = builder.certificatePinner.withCertificateChainCleaner(this.certificateChainCleaner);
        this.proxyAuthenticator = builder.proxyAuthenticator;
        this.authenticator = builder.authenticator;
        this.connectionPool = builder.connectionPool;
        this.dns = builder.dns;
        this.followSslRedirects = builder.followSslRedirects;
        this.followRedirects = builder.followRedirects;
        this.retryOnConnectionFailure = builder.retryOnConnectionFailure;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.pingInterval = builder.pingInterval;
    }

    private javax.net.ssl.X509TrustManager systemDefaultTrustManager() {
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
        r0 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm();	 Catch:{ GeneralSecurityException -> 0x003d }
        r0 = javax.net.ssl.TrustManagerFactory.getInstance(r0);	 Catch:{ GeneralSecurityException -> 0x003d }
        r1 = 0;	 Catch:{ GeneralSecurityException -> 0x003d }
        r1 = (java.security.KeyStore) r1;	 Catch:{ GeneralSecurityException -> 0x003d }
        r0.init(r1);	 Catch:{ GeneralSecurityException -> 0x003d }
        r0 = r0.getTrustManagers();	 Catch:{ GeneralSecurityException -> 0x003d }
        r1 = r0.length;	 Catch:{ GeneralSecurityException -> 0x003d }
        r2 = 1;	 Catch:{ GeneralSecurityException -> 0x003d }
        if (r1 != r2) goto L_0x0022;	 Catch:{ GeneralSecurityException -> 0x003d }
    L_0x0016:
        r1 = 0;	 Catch:{ GeneralSecurityException -> 0x003d }
        r2 = r0[r1];	 Catch:{ GeneralSecurityException -> 0x003d }
        r2 = r2 instanceof javax.net.ssl.X509TrustManager;	 Catch:{ GeneralSecurityException -> 0x003d }
        if (r2 == 0) goto L_0x0022;	 Catch:{ GeneralSecurityException -> 0x003d }
    L_0x001d:
        r0 = r0[r1];	 Catch:{ GeneralSecurityException -> 0x003d }
        r0 = (javax.net.ssl.X509TrustManager) r0;	 Catch:{ GeneralSecurityException -> 0x003d }
        return r0;	 Catch:{ GeneralSecurityException -> 0x003d }
    L_0x0022:
        r1 = new java.lang.IllegalStateException;	 Catch:{ GeneralSecurityException -> 0x003d }
        r2 = new java.lang.StringBuilder;	 Catch:{ GeneralSecurityException -> 0x003d }
        r2.<init>();	 Catch:{ GeneralSecurityException -> 0x003d }
        r3 = "Unexpected default trust managers:";	 Catch:{ GeneralSecurityException -> 0x003d }
        r2.append(r3);	 Catch:{ GeneralSecurityException -> 0x003d }
        r0 = java.util.Arrays.toString(r0);	 Catch:{ GeneralSecurityException -> 0x003d }
        r2.append(r0);	 Catch:{ GeneralSecurityException -> 0x003d }
        r0 = r2.toString();	 Catch:{ GeneralSecurityException -> 0x003d }
        r1.<init>(r0);	 Catch:{ GeneralSecurityException -> 0x003d }
        throw r1;	 Catch:{ GeneralSecurityException -> 0x003d }
    L_0x003d:
        r0 = new java.lang.AssertionError;
        r0.<init>();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.OkHttpClient.systemDefaultTrustManager():javax.net.ssl.X509TrustManager");
    }

    private javax.net.ssl.SSLSocketFactory systemDefaultSslSocketFactory(javax.net.ssl.X509TrustManager r4) {
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
        r3 = this;
        r0 = "TLS";	 Catch:{ GeneralSecurityException -> 0x0015 }
        r0 = javax.net.ssl.SSLContext.getInstance(r0);	 Catch:{ GeneralSecurityException -> 0x0015 }
        r1 = 1;	 Catch:{ GeneralSecurityException -> 0x0015 }
        r1 = new javax.net.ssl.TrustManager[r1];	 Catch:{ GeneralSecurityException -> 0x0015 }
        r2 = 0;	 Catch:{ GeneralSecurityException -> 0x0015 }
        r1[r2] = r4;	 Catch:{ GeneralSecurityException -> 0x0015 }
        r4 = 0;	 Catch:{ GeneralSecurityException -> 0x0015 }
        r0.init(r4, r1, r4);	 Catch:{ GeneralSecurityException -> 0x0015 }
        r4 = r0.getSocketFactory();	 Catch:{ GeneralSecurityException -> 0x0015 }
        return r4;
    L_0x0015:
        r4 = new java.lang.AssertionError;
        r4.<init>();
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.OkHttpClient.systemDefaultSslSocketFactory(javax.net.ssl.X509TrustManager):javax.net.ssl.SSLSocketFactory");
    }

    public int connectTimeoutMillis() {
        return this.connectTimeout;
    }

    public int readTimeoutMillis() {
        return this.readTimeout;
    }

    public int writeTimeoutMillis() {
        return this.writeTimeout;
    }

    public int pingIntervalMillis() {
        return this.pingInterval;
    }

    public Proxy proxy() {
        return this.proxy;
    }

    public ProxySelector proxySelector() {
        return this.proxySelector;
    }

    public CookieJar cookieJar() {
        return this.cookieJar;
    }

    public Cache cache() {
        return this.cache;
    }

    InternalCache internalCache() {
        Cache cache = this.cache;
        return cache != null ? cache.internalCache : this.internalCache;
    }

    public Dns dns() {
        return this.dns;
    }

    public SocketFactory socketFactory() {
        return this.socketFactory;
    }

    public SSLSocketFactory sslSocketFactory() {
        return this.sslSocketFactory;
    }

    public HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }

    public CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }

    public Authenticator authenticator() {
        return this.authenticator;
    }

    public Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    public ConnectionPool connectionPool() {
        return this.connectionPool;
    }

    public boolean followSslRedirects() {
        return this.followSslRedirects;
    }

    public boolean followRedirects() {
        return this.followRedirects;
    }

    public boolean retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    public Dispatcher dispatcher() {
        return this.dispatcher;
    }

    public List<Protocol> protocols() {
        return this.protocols;
    }

    public List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }

    public List<Interceptor> interceptors() {
        return this.interceptors;
    }

    public List<Interceptor> networkInterceptors() {
        return this.networkInterceptors;
    }

    EventListener.Factory eventListenerFactory() {
        return this.eventListenerFactory;
    }

    public Call newCall(Request request) {
        return new RealCall(this, request, false);
    }

    public WebSocket newWebSocket(Request request, WebSocketListener webSocketListener) {
        WebSocket realWebSocket = new RealWebSocket(request, webSocketListener, new Random());
        realWebSocket.connect(this);
        return realWebSocket;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }
}
