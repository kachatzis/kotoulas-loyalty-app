package okhttp3.internal.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.connection.StreamAllocation;

public final class RetryAndFollowUpInterceptor implements Interceptor {
    private static final int MAX_FOLLOW_UPS = 20;
    private Object callStackTrace;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private final boolean forWebSocket;
    private StreamAllocation streamAllocation;

    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r10) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:52:0x0124 in {6, 7, 12, 13, 20, 23, 25, 27, 29, 36, 37, 40, 41, 45, 47, 49, 51} preds:[]
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
        r9 = this;
        r0 = r10.request();
        r1 = new okhttp3.internal.connection.StreamAllocation;
        r2 = r9.client;
        r2 = r2.connectionPool();
        r3 = r0.url();
        r3 = r9.createAddress(r3);
        r4 = r9.callStackTrace;
        r1.<init>(r2, r3, r4);
        r9.streamAllocation = r1;
        r1 = 0;
        r2 = 0;
        r3 = r2;
        r4 = 0;
    L_0x001f:
        r5 = r9.canceled;
        if (r5 != 0) goto L_0x0117;
    L_0x0023:
        r5 = r10;	 Catch:{ RouteException -> 0x00fa, IOException -> 0x00e9 }
        r5 = (okhttp3.internal.http.RealInterceptorChain) r5;	 Catch:{ RouteException -> 0x00fa, IOException -> 0x00e9 }
        r6 = r9.streamAllocation;	 Catch:{ RouteException -> 0x00fa, IOException -> 0x00e9 }
        r0 = r5.proceed(r0, r6, r2, r2);	 Catch:{ RouteException -> 0x00fa, IOException -> 0x00e9 }
        if (r3 == 0) goto L_0x0048;
    L_0x002e:
        r0 = r0.newBuilder();
        r3 = r3.newBuilder();
        r3 = r3.body(r2);
        r3 = r3.build();
        r0 = r0.priorResponse(r3);
        r0 = r0.build();
        r3 = r0;
        goto L_0x0049;
    L_0x0048:
        r3 = r0;
    L_0x0049:
        r0 = r9.followUpRequest(r3);
        if (r0 != 0) goto L_0x0059;
    L_0x004f:
        r10 = r9.forWebSocket;
        if (r10 != 0) goto L_0x0058;
    L_0x0053:
        r10 = r9.streamAllocation;
        r10.release();
    L_0x0058:
        return r3;
    L_0x0059:
        r5 = r3.body();
        okhttp3.internal.Util.closeQuietly(r5);
        r4 = r4 + 1;
        r5 = 20;
        if (r4 > r5) goto L_0x00cb;
    L_0x0066:
        r5 = r0.body();
        r5 = r5 instanceof okhttp3.internal.http.UnrepeatableRequestBody;
        if (r5 != 0) goto L_0x00ba;
    L_0x006e:
        r5 = r0.url();
        r5 = r9.sameConnection(r3, r5);
        if (r5 != 0) goto L_0x0095;
    L_0x0078:
        r5 = r9.streamAllocation;
        r5.release();
        r5 = new okhttp3.internal.connection.StreamAllocation;
        r6 = r9.client;
        r6 = r6.connectionPool();
        r7 = r0.url();
        r7 = r9.createAddress(r7);
        r8 = r9.callStackTrace;
        r5.<init>(r6, r7, r8);
        r9.streamAllocation = r5;
        goto L_0x001f;
    L_0x0095:
        r5 = r9.streamAllocation;
        r5 = r5.codec();
        if (r5 != 0) goto L_0x009e;
    L_0x009d:
        goto L_0x001f;
    L_0x009e:
        r10 = new java.lang.IllegalStateException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Closing the body of ";
        r0.append(r1);
        r0.append(r3);
        r1 = " didn't close its backing stream. Bad interceptor?";
        r0.append(r1);
        r0 = r0.toString();
        r10.<init>(r0);
        throw r10;
    L_0x00ba:
        r10 = r9.streamAllocation;
        r10.release();
        r10 = new java.net.HttpRetryException;
        r0 = r3.code();
        r1 = "Cannot retry streamed HTTP body";
        r10.<init>(r1, r0);
        throw r10;
    L_0x00cb:
        r10 = r9.streamAllocation;
        r10.release();
        r10 = new java.net.ProtocolException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Too many follow-up requests: ";
        r0.append(r1);
        r0.append(r4);
        r0 = r0.toString();
        r10.<init>(r0);
        throw r10;
    L_0x00e7:
        r10 = move-exception;
        goto L_0x010c;
    L_0x00e9:
        r5 = move-exception;
        r6 = r5 instanceof okhttp3.internal.http2.ConnectionShutdownException;	 Catch:{ all -> 0x00e7 }
        if (r6 != 0) goto L_0x00f0;	 Catch:{ all -> 0x00e7 }
    L_0x00ee:
        r6 = 1;	 Catch:{ all -> 0x00e7 }
        goto L_0x00f1;	 Catch:{ all -> 0x00e7 }
    L_0x00f0:
        r6 = 0;	 Catch:{ all -> 0x00e7 }
    L_0x00f1:
        r6 = r9.recover(r5, r6, r0);	 Catch:{ all -> 0x00e7 }
        if (r6 == 0) goto L_0x00f9;	 Catch:{ all -> 0x00e7 }
    L_0x00f7:
        goto L_0x001f;	 Catch:{ all -> 0x00e7 }
    L_0x00f9:
        throw r5;	 Catch:{ all -> 0x00e7 }
    L_0x00fa:
        r5 = move-exception;	 Catch:{ all -> 0x00e7 }
        r6 = r5.getLastConnectException();	 Catch:{ all -> 0x00e7 }
        r6 = r9.recover(r6, r1, r0);	 Catch:{ all -> 0x00e7 }
        if (r6 == 0) goto L_0x0107;	 Catch:{ all -> 0x00e7 }
    L_0x0105:
        goto L_0x001f;	 Catch:{ all -> 0x00e7 }
    L_0x0107:
        r10 = r5.getLastConnectException();	 Catch:{ all -> 0x00e7 }
        throw r10;	 Catch:{ all -> 0x00e7 }
    L_0x010c:
        r0 = r9.streamAllocation;
        r0.streamFailed(r2);
        r0 = r9.streamAllocation;
        r0.release();
        throw r10;
    L_0x0117:
        r10 = r9.streamAllocation;
        r10.release();
        r10 = new java.io.IOException;
        r0 = "Canceled";
        r10.<init>(r0);
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient, boolean z) {
        this.client = okHttpClient;
        this.forWebSocket = z;
    }

    public void cancel() {
        this.canceled = true;
        StreamAllocation streamAllocation = this.streamAllocation;
        if (streamAllocation != null) {
            streamAllocation.cancel();
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCallStackTrace(Object obj) {
        this.callStackTrace = obj;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }

    private Address createAddress(HttpUrl httpUrl) {
        HostnameVerifier hostnameVerifier;
        SSLSocketFactory sSLSocketFactory;
        CertificatePinner certificatePinner;
        RetryAndFollowUpInterceptor retryAndFollowUpInterceptor = this;
        if (httpUrl.isHttps()) {
            SSLSocketFactory sslSocketFactory = retryAndFollowUpInterceptor.client.sslSocketFactory();
            hostnameVerifier = retryAndFollowUpInterceptor.client.hostnameVerifier();
            sSLSocketFactory = sslSocketFactory;
            certificatePinner = retryAndFollowUpInterceptor.client.certificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = sSLSocketFactory;
            certificatePinner = hostnameVerifier;
        }
        return new Address(httpUrl.host(), httpUrl.port(), retryAndFollowUpInterceptor.client.dns(), retryAndFollowUpInterceptor.client.socketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, retryAndFollowUpInterceptor.client.proxyAuthenticator(), retryAndFollowUpInterceptor.client.proxy(), retryAndFollowUpInterceptor.client.protocols(), retryAndFollowUpInterceptor.client.connectionSpecs(), retryAndFollowUpInterceptor.client.proxySelector());
    }

    private boolean recover(IOException iOException, boolean z, Request request) {
        this.streamAllocation.streamFailed(iOException);
        if (!this.client.retryOnConnectionFailure()) {
            return false;
        }
        if ((z && (request.body() instanceof UnrepeatableRequestBody) != null) || isRecoverable(iOException, z) == null || this.streamAllocation.hasMoreRoutes() == null) {
            return false;
        }
        return true;
    }

    private boolean isRecoverable(IOException iOException, boolean z) {
        boolean z2 = false;
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!((iOException instanceof SocketTimeoutException) == null || z)) {
                z2 = true;
            }
            return z2;
        } else if ((!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && (iOException instanceof SSLPeerUnverifiedException) == null) {
            return true;
        } else {
            return false;
        }
    }

    private Request followUpRequest(Response response) throws IOException {
        if (response != null) {
            Connection connection = this.streamAllocation.connection();
            RequestBody requestBody = null;
            Route route = connection != null ? connection.route() : null;
            int code = response.code();
            String method = response.request().method();
            switch (code) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                case StatusLine.HTTP_TEMP_REDIRECT /*307*/:
                case StatusLine.HTTP_PERM_REDIRECT /*308*/:
                    if (!(method.equals("GET") || method.equals("HEAD"))) {
                        return null;
                    }
                case 401:
                    return this.client.authenticator().authenticate(route, response);
                case 407:
                    Proxy proxy;
                    if (route != null) {
                        proxy = route.proxy();
                    } else {
                        proxy = this.client.proxy();
                    }
                    if (proxy.type() == Type.HTTP) {
                        return this.client.proxyAuthenticator().authenticate(route, response);
                    }
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                case 408:
                    if (response.request().body() instanceof UnrepeatableRequestBody) {
                        return null;
                    }
                    return response.request();
                default:
                    return null;
            }
            if (!this.client.followRedirects()) {
                return null;
            }
            String header = response.header("Location");
            if (header == null) {
                return null;
            }
            HttpUrl resolve = response.request().url().resolve(header);
            if (resolve == null) {
                return null;
            }
            if (!resolve.scheme().equals(response.request().url().scheme()) && !this.client.followSslRedirects()) {
                return null;
            }
            Builder newBuilder = response.request().newBuilder();
            if (HttpMethod.permitsRequestBody(method)) {
                boolean redirectsWithBody = HttpMethod.redirectsWithBody(method);
                if (HttpMethod.redirectsToGet(method)) {
                    newBuilder.method("GET", null);
                } else {
                    if (redirectsWithBody) {
                        requestBody = response.request().body();
                    }
                    newBuilder.method(method, requestBody);
                }
                if (!redirectsWithBody) {
                    newBuilder.removeHeader("Transfer-Encoding");
                    newBuilder.removeHeader("Content-Length");
                    newBuilder.removeHeader("Content-Type");
                }
            }
            if (sameConnection(response, resolve) == null) {
                newBuilder.removeHeader("Authorization");
            }
            return newBuilder.url(resolve).build();
        }
        throw new IllegalStateException();
    }

    private boolean sameConnection(Response response, HttpUrl httpUrl) {
        response = response.request().url();
        return (response.host().equals(httpUrl.host()) && response.port() == httpUrl.port() && response.scheme().equals(httpUrl.scheme()) != null) ? true : null;
    }
}
