package okhttp3.internal.cache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class CacheInterceptor implements Interceptor {
    final InternalCache cache;

    public CacheInterceptor(InternalCache internalCache) {
        this.cache = internalCache;
    }

    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r6) throws java.io.IOException {
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
        r5 = this;
        r0 = r5.cache;
        if (r0 == 0) goto L_0x000d;
    L_0x0004:
        r1 = r6.request();
        r0 = r0.get(r1);
        goto L_0x000e;
    L_0x000d:
        r0 = 0;
    L_0x000e:
        r1 = java.lang.System.currentTimeMillis();
        r3 = new okhttp3.internal.cache.CacheStrategy$Factory;
        r4 = r6.request();
        r3.<init>(r1, r4, r0);
        r1 = r3.get();
        r2 = r1.networkRequest;
        r3 = r1.cacheResponse;
        r4 = r5.cache;
        if (r4 == 0) goto L_0x002a;
    L_0x0027:
        r4.trackResponse(r1);
    L_0x002a:
        if (r0 == 0) goto L_0x0035;
    L_0x002c:
        if (r3 != 0) goto L_0x0035;
    L_0x002e:
        r1 = r0.body();
        okhttp3.internal.Util.closeQuietly(r1);
    L_0x0035:
        if (r2 != 0) goto L_0x0071;
    L_0x0037:
        if (r3 != 0) goto L_0x0071;
    L_0x0039:
        r0 = new okhttp3.Response$Builder;
        r0.<init>();
        r6 = r6.request();
        r6 = r0.request(r6);
        r0 = okhttp3.Protocol.HTTP_1_1;
        r6 = r6.protocol(r0);
        r0 = 504; // 0x1f8 float:7.06E-43 double:2.49E-321;
        r6 = r6.code(r0);
        r0 = "Unsatisfiable Request (only-if-cached)";
        r6 = r6.message(r0);
        r0 = okhttp3.internal.Util.EMPTY_RESPONSE;
        r6 = r6.body(r0);
        r0 = -1;
        r6 = r6.sentRequestAtMillis(r0);
        r0 = java.lang.System.currentTimeMillis();
        r6 = r6.receivedResponseAtMillis(r0);
        r6 = r6.build();
        return r6;
    L_0x0071:
        if (r2 != 0) goto L_0x0084;
    L_0x0073:
        r6 = r3.newBuilder();
        r0 = stripBody(r3);
        r6 = r6.cacheResponse(r0);
        r6 = r6.build();
        return r6;
    L_0x0084:
        r6 = r6.proceed(r2);	 Catch:{ all -> 0x0131 }
        if (r6 != 0) goto L_0x0093;
    L_0x008a:
        if (r0 == 0) goto L_0x0093;
    L_0x008c:
        r0 = r0.body();
        okhttp3.internal.Util.closeQuietly(r0);
    L_0x0093:
        if (r3 == 0) goto L_0x00ee;
    L_0x0095:
        r0 = r6.code();
        r1 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r0 != r1) goto L_0x00e7;
    L_0x009d:
        r0 = r3.newBuilder();
        r1 = r3.headers();
        r2 = r6.headers();
        r1 = combine(r1, r2);
        r0 = r0.headers(r1);
        r1 = r6.sentRequestAtMillis();
        r0 = r0.sentRequestAtMillis(r1);
        r1 = r6.receivedResponseAtMillis();
        r0 = r0.receivedResponseAtMillis(r1);
        r1 = stripBody(r3);
        r0 = r0.cacheResponse(r1);
        r1 = stripBody(r6);
        r0 = r0.networkResponse(r1);
        r0 = r0.build();
        r6 = r6.body();
        r6.close();
        r6 = r5.cache;
        r6.trackConditionalCacheHit();
        r6 = r5.cache;
        r6.update(r3, r0);
        return r0;
    L_0x00e7:
        r0 = r3.body();
        okhttp3.internal.Util.closeQuietly(r0);
    L_0x00ee:
        r0 = r6.newBuilder();
        r1 = stripBody(r3);
        r0 = r0.cacheResponse(r1);
        r6 = stripBody(r6);
        r6 = r0.networkResponse(r6);
        r6 = r6.build();
        r0 = r5.cache;
        if (r0 == 0) goto L_0x0130;
    L_0x010a:
        r0 = okhttp3.internal.http.HttpHeaders.hasBody(r6);
        if (r0 == 0) goto L_0x0121;
    L_0x0110:
        r0 = okhttp3.internal.cache.CacheStrategy.isCacheable(r6, r2);
        if (r0 == 0) goto L_0x0121;
    L_0x0116:
        r0 = r5.cache;
        r0 = r0.put(r6);
        r6 = r5.cacheWritingResponse(r0, r6);
        return r6;
    L_0x0121:
        r0 = r2.method();
        r0 = okhttp3.internal.http.HttpMethod.invalidatesCache(r0);
        if (r0 == 0) goto L_0x0130;
    L_0x012b:
        r0 = r5.cache;	 Catch:{ IOException -> 0x0130 }
        r0.remove(r2);	 Catch:{ IOException -> 0x0130 }
    L_0x0130:
        return r6;
    L_0x0131:
        r6 = move-exception;
        if (r0 == 0) goto L_0x013b;
    L_0x0134:
        r0 = r0.body();
        okhttp3.internal.Util.closeQuietly(r0);
    L_0x013b:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    private static Response stripBody(Response response) {
        return (response == null || response.body() == null) ? response : response.newBuilder().body(null).build();
    }

    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws IOException {
        if (cacheRequest == null) {
            return response;
        }
        Sink body = cacheRequest.body();
        if (body == null) {
            return response;
        }
        final BufferedSource source = response.body().source();
        final BufferedSink buffer = Okio.buffer(body);
        return response.newBuilder().body(new RealResponseBody(response.headers(), Okio.buffer(new Source() {
            boolean cacheRequestClosed;

            public long read(Buffer buffer, long j) throws IOException {
                try {
                    j = source.read(buffer, j);
                    if (j == -1) {
                        if (this.cacheRequestClosed == null) {
                            this.cacheRequestClosed = true;
                            buffer.close();
                        }
                        return -1;
                    }
                    buffer.copyTo(buffer.buffer(), buffer.size() - j, j);
                    buffer.emitCompleteSegments();
                    return j;
                } catch (Buffer buffer2) {
                    if (this.cacheRequestClosed == null) {
                        this.cacheRequestClosed = true;
                        cacheRequest.abort();
                    }
                    throw buffer2;
                }
            }

            public Timeout timeout() {
                return source.timeout();
            }

            public void close() throws IOException {
                if (!(this.cacheRequestClosed || Util.discard(this, 100, TimeUnit.MILLISECONDS))) {
                    this.cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                source.close();
            }
        }))).build();
    }

    private static Headers combine(Headers headers, Headers headers2) {
        Builder builder = new Builder();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            if (!"Warning".equalsIgnoreCase(name) || !value.startsWith("1")) {
                if (!isEndToEnd(name) || headers2.get(name) == null) {
                    Internal.instance.addLenient(builder, name, value);
                }
            }
        }
        headers = headers2.size();
        for (int i2 = 0; i2 < headers; i2++) {
            String name2 = headers2.name(i2);
            if (!"Content-Length".equalsIgnoreCase(name2)) {
                if (isEndToEnd(name2)) {
                    Internal.instance.addLenient(builder, name2, headers2.value(i2));
                }
            }
        }
        return builder.build();
    }

    static boolean isEndToEnd(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str) != null) ? null : true;
    }
}
