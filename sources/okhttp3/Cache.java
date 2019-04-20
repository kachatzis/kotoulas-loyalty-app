package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;
import okhttp3.Response.Builder;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.cache.DiskLruCache.Editor;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;

public final class Cache implements Closeable, Flushable {
    private static final int ENTRY_BODY = 1;
    private static final int ENTRY_COUNT = 2;
    private static final int ENTRY_METADATA = 0;
    private static final int VERSION = 201105;
    final DiskLruCache cache;
    private int hitCount;
    final InternalCache internalCache;
    private int networkCount;
    private int requestCount;
    int writeAbortCount;
    int writeSuccessCount;

    /* renamed from: okhttp3.Cache$2 */
    class C04312 implements Iterator<String> {
        boolean canRemove;
        final Iterator<Snapshot> delegate = Cache.this.cache.snapshots();
        @Nullable
        String nextUrl;

        C04312() throws IOException {
        }

        public boolean hasNext() {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r4 = this;
            r0 = r4.nextUrl;
            r1 = 1;
            if (r0 == 0) goto L_0x0006;
        L_0x0005:
            return r1;
        L_0x0006:
            r0 = 0;
            r4.canRemove = r0;
        L_0x0009:
            r2 = r4.delegate;
            r2 = r2.hasNext();
            if (r2 == 0) goto L_0x0034;
        L_0x0011:
            r2 = r4.delegate;
            r2 = r2.next();
            r2 = (okhttp3.internal.cache.DiskLruCache.Snapshot) r2;
            r3 = r2.getSource(r0);	 Catch:{ IOException -> 0x0030, all -> 0x002b }
            r3 = okio.Okio.buffer(r3);	 Catch:{ IOException -> 0x0030, all -> 0x002b }
            r3 = r3.readUtf8LineStrict();	 Catch:{ IOException -> 0x0030, all -> 0x002b }
            r4.nextUrl = r3;	 Catch:{ IOException -> 0x0030, all -> 0x002b }
            r2.close();
            return r1;
        L_0x002b:
            r0 = move-exception;
            r2.close();
            throw r0;
        L_0x0030:
            r2.close();
            goto L_0x0009;
        L_0x0034:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache.2.hasNext():boolean");
        }

        public String next() {
            if (hasNext()) {
                String str = this.nextUrl;
                this.nextUrl = null;
                this.canRemove = true;
                return str;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.canRemove) {
                this.delegate.remove();
                return;
            }
            throw new IllegalStateException("remove() before next()");
        }
    }

    private static final class Entry {
        private static final String RECEIVED_MILLIS;
        private static final String SENT_MILLIS;
        private final int code;
        @Nullable
        private final Handshake handshake;
        private final String message;
        private final Protocol protocol;
        private final long receivedResponseMillis;
        private final String requestMethod;
        private final Headers responseHeaders;
        private final long sentRequestMillis;
        private final String url;
        private final Headers varyHeaders;

        Entry(okio.Source r9) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:32:0x00e9 in {4, 7, 10, 11, 14, 21, 22, 23, 25, 26, 28, 31} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r8 = this;
            r8.<init>();
            r0 = okio.Okio.buffer(r9);	 Catch:{ all -> 0x00e4 }
            r1 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00e4 }
            r8.url = r1;	 Catch:{ all -> 0x00e4 }
            r1 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00e4 }
            r8.requestMethod = r1;	 Catch:{ all -> 0x00e4 }
            r1 = new okhttp3.Headers$Builder;	 Catch:{ all -> 0x00e4 }
            r1.<init>();	 Catch:{ all -> 0x00e4 }
            r2 = okhttp3.Cache.readInt(r0);	 Catch:{ all -> 0x00e4 }
            r3 = 0;	 Catch:{ all -> 0x00e4 }
            r4 = 0;	 Catch:{ all -> 0x00e4 }
        L_0x001e:
            if (r4 >= r2) goto L_0x002a;	 Catch:{ all -> 0x00e4 }
        L_0x0020:
            r5 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00e4 }
            r1.addLenient(r5);	 Catch:{ all -> 0x00e4 }
            r4 = r4 + 1;	 Catch:{ all -> 0x00e4 }
            goto L_0x001e;	 Catch:{ all -> 0x00e4 }
        L_0x002a:
            r1 = r1.build();	 Catch:{ all -> 0x00e4 }
            r8.varyHeaders = r1;	 Catch:{ all -> 0x00e4 }
            r1 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00e4 }
            r1 = okhttp3.internal.http.StatusLine.parse(r1);	 Catch:{ all -> 0x00e4 }
            r2 = r1.protocol;	 Catch:{ all -> 0x00e4 }
            r8.protocol = r2;	 Catch:{ all -> 0x00e4 }
            r2 = r1.code;	 Catch:{ all -> 0x00e4 }
            r8.code = r2;	 Catch:{ all -> 0x00e4 }
            r1 = r1.message;	 Catch:{ all -> 0x00e4 }
            r8.message = r1;	 Catch:{ all -> 0x00e4 }
            r1 = new okhttp3.Headers$Builder;	 Catch:{ all -> 0x00e4 }
            r1.<init>();	 Catch:{ all -> 0x00e4 }
            r2 = okhttp3.Cache.readInt(r0);	 Catch:{ all -> 0x00e4 }
        L_0x004d:
            if (r3 >= r2) goto L_0x0059;	 Catch:{ all -> 0x00e4 }
        L_0x004f:
            r4 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00e4 }
            r1.addLenient(r4);	 Catch:{ all -> 0x00e4 }
            r3 = r3 + 1;	 Catch:{ all -> 0x00e4 }
            goto L_0x004d;	 Catch:{ all -> 0x00e4 }
        L_0x0059:
            r2 = SENT_MILLIS;	 Catch:{ all -> 0x00e4 }
            r2 = r1.get(r2);	 Catch:{ all -> 0x00e4 }
            r3 = RECEIVED_MILLIS;	 Catch:{ all -> 0x00e4 }
            r3 = r1.get(r3);	 Catch:{ all -> 0x00e4 }
            r4 = SENT_MILLIS;	 Catch:{ all -> 0x00e4 }
            r1.removeAll(r4);	 Catch:{ all -> 0x00e4 }
            r4 = RECEIVED_MILLIS;	 Catch:{ all -> 0x00e4 }
            r1.removeAll(r4);	 Catch:{ all -> 0x00e4 }
            r4 = 0;	 Catch:{ all -> 0x00e4 }
            if (r2 == 0) goto L_0x0078;	 Catch:{ all -> 0x00e4 }
        L_0x0073:
            r6 = java.lang.Long.parseLong(r2);	 Catch:{ all -> 0x00e4 }
            goto L_0x0079;	 Catch:{ all -> 0x00e4 }
        L_0x0078:
            r6 = r4;	 Catch:{ all -> 0x00e4 }
        L_0x0079:
            r8.sentRequestMillis = r6;	 Catch:{ all -> 0x00e4 }
            if (r3 == 0) goto L_0x0081;	 Catch:{ all -> 0x00e4 }
        L_0x007d:
            r4 = java.lang.Long.parseLong(r3);	 Catch:{ all -> 0x00e4 }
        L_0x0081:
            r8.receivedResponseMillis = r4;	 Catch:{ all -> 0x00e4 }
            r1 = r1.build();	 Catch:{ all -> 0x00e4 }
            r8.responseHeaders = r1;	 Catch:{ all -> 0x00e4 }
            r1 = r8.isHttps();	 Catch:{ all -> 0x00e4 }
            if (r1 == 0) goto L_0x00dd;	 Catch:{ all -> 0x00e4 }
        L_0x008f:
            r1 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00e4 }
            r2 = r1.length();	 Catch:{ all -> 0x00e4 }
            if (r2 > 0) goto L_0x00c1;	 Catch:{ all -> 0x00e4 }
        L_0x0099:
            r1 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00e4 }
            r1 = okhttp3.CipherSuite.forJavaName(r1);	 Catch:{ all -> 0x00e4 }
            r2 = r8.readCertificateList(r0);	 Catch:{ all -> 0x00e4 }
            r3 = r8.readCertificateList(r0);	 Catch:{ all -> 0x00e4 }
            r4 = r0.exhausted();	 Catch:{ all -> 0x00e4 }
            if (r4 != 0) goto L_0x00b8;	 Catch:{ all -> 0x00e4 }
        L_0x00af:
            r0 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00e4 }
            r0 = okhttp3.TlsVersion.forJavaName(r0);	 Catch:{ all -> 0x00e4 }
            goto L_0x00ba;	 Catch:{ all -> 0x00e4 }
        L_0x00b8:
            r0 = okhttp3.TlsVersion.SSL_3_0;	 Catch:{ all -> 0x00e4 }
        L_0x00ba:
            r0 = okhttp3.Handshake.get(r0, r1, r2, r3);	 Catch:{ all -> 0x00e4 }
            r8.handshake = r0;	 Catch:{ all -> 0x00e4 }
            goto L_0x00e0;	 Catch:{ all -> 0x00e4 }
        L_0x00c1:
            r0 = new java.io.IOException;	 Catch:{ all -> 0x00e4 }
            r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e4 }
            r2.<init>();	 Catch:{ all -> 0x00e4 }
            r3 = "expected \"\" but was \"";	 Catch:{ all -> 0x00e4 }
            r2.append(r3);	 Catch:{ all -> 0x00e4 }
            r2.append(r1);	 Catch:{ all -> 0x00e4 }
            r1 = "\"";	 Catch:{ all -> 0x00e4 }
            r2.append(r1);	 Catch:{ all -> 0x00e4 }
            r1 = r2.toString();	 Catch:{ all -> 0x00e4 }
            r0.<init>(r1);	 Catch:{ all -> 0x00e4 }
            throw r0;	 Catch:{ all -> 0x00e4 }
        L_0x00dd:
            r0 = 0;	 Catch:{ all -> 0x00e4 }
            r8.handshake = r0;	 Catch:{ all -> 0x00e4 }
        L_0x00e0:
            r9.close();
            return;
        L_0x00e4:
            r0 = move-exception;
            r9.close();
            throw r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache.Entry.<init>(okio.Source):void");
        }

        private java.util.List<java.security.cert.Certificate> readCertificateList(okio.BufferedSource r7) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:13:0x0044 in {3, 8, 9, 12} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r6 = this;
            r0 = okhttp3.Cache.readInt(r7);
            r1 = -1;
            if (r0 != r1) goto L_0x000c;
        L_0x0007:
            r7 = java.util.Collections.emptyList();
            return r7;
        L_0x000c:
            r1 = "X.509";	 Catch:{ CertificateException -> 0x0039 }
            r1 = java.security.cert.CertificateFactory.getInstance(r1);	 Catch:{ CertificateException -> 0x0039 }
            r2 = new java.util.ArrayList;	 Catch:{ CertificateException -> 0x0039 }
            r2.<init>(r0);	 Catch:{ CertificateException -> 0x0039 }
            r3 = 0;	 Catch:{ CertificateException -> 0x0039 }
        L_0x0018:
            if (r3 >= r0) goto L_0x0038;	 Catch:{ CertificateException -> 0x0039 }
        L_0x001a:
            r4 = r7.readUtf8LineStrict();	 Catch:{ CertificateException -> 0x0039 }
            r5 = new okio.Buffer;	 Catch:{ CertificateException -> 0x0039 }
            r5.<init>();	 Catch:{ CertificateException -> 0x0039 }
            r4 = okio.ByteString.decodeBase64(r4);	 Catch:{ CertificateException -> 0x0039 }
            r5.write(r4);	 Catch:{ CertificateException -> 0x0039 }
            r4 = r5.inputStream();	 Catch:{ CertificateException -> 0x0039 }
            r4 = r1.generateCertificate(r4);	 Catch:{ CertificateException -> 0x0039 }
            r2.add(r4);	 Catch:{ CertificateException -> 0x0039 }
            r3 = r3 + 1;
            goto L_0x0018;
        L_0x0038:
            return r2;
        L_0x0039:
            r7 = move-exception;
            r0 = new java.io.IOException;
            r7 = r7.getMessage();
            r0.<init>(r7);
            throw r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache.Entry.readCertificateList(okio.BufferedSource):java.util.List<java.security.cert.Certificate>");
        }

        private void writeCertList(okio.BufferedSink r5, java.util.List<java.security.cert.Certificate> r6) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:9:0x003d in {4, 5, 8} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r4 = this;
            r0 = r6.size();	 Catch:{ CertificateEncodingException -> 0x0032 }
            r0 = (long) r0;	 Catch:{ CertificateEncodingException -> 0x0032 }
            r0 = r5.writeDecimalLong(r0);	 Catch:{ CertificateEncodingException -> 0x0032 }
            r1 = 10;	 Catch:{ CertificateEncodingException -> 0x0032 }
            r0.writeByte(r1);	 Catch:{ CertificateEncodingException -> 0x0032 }
            r0 = 0;	 Catch:{ CertificateEncodingException -> 0x0032 }
            r2 = r6.size();	 Catch:{ CertificateEncodingException -> 0x0032 }
        L_0x0013:
            if (r0 >= r2) goto L_0x0031;	 Catch:{ CertificateEncodingException -> 0x0032 }
        L_0x0015:
            r3 = r6.get(r0);	 Catch:{ CertificateEncodingException -> 0x0032 }
            r3 = (java.security.cert.Certificate) r3;	 Catch:{ CertificateEncodingException -> 0x0032 }
            r3 = r3.getEncoded();	 Catch:{ CertificateEncodingException -> 0x0032 }
            r3 = okio.ByteString.of(r3);	 Catch:{ CertificateEncodingException -> 0x0032 }
            r3 = r3.base64();	 Catch:{ CertificateEncodingException -> 0x0032 }
            r3 = r5.writeUtf8(r3);	 Catch:{ CertificateEncodingException -> 0x0032 }
            r3.writeByte(r1);	 Catch:{ CertificateEncodingException -> 0x0032 }
            r0 = r0 + 1;
            goto L_0x0013;
        L_0x0031:
            return;
        L_0x0032:
            r5 = move-exception;
            r6 = new java.io.IOException;
            r5 = r5.getMessage();
            r6.<init>(r5);
            throw r6;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache.Entry.writeCertList(okio.BufferedSink, java.util.List):void");
        }

        static {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Platform.get().getPrefix());
            stringBuilder.append("-Sent-Millis");
            SENT_MILLIS = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(Platform.get().getPrefix());
            stringBuilder.append("-Received-Millis");
            RECEIVED_MILLIS = stringBuilder.toString();
        }

        Entry(Response response) {
            this.url = response.request().url().toString();
            this.varyHeaders = HttpHeaders.varyHeaders(response);
            this.requestMethod = response.request().method();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.responseHeaders = response.headers();
            this.handshake = response.handshake();
            this.sentRequestMillis = response.sentRequestAtMillis();
            this.receivedResponseMillis = response.receivedResponseAtMillis();
        }

        public void writeTo(Editor editor) throws IOException {
            int i = 0;
            editor = Okio.buffer(editor.newSink(0));
            editor.writeUtf8(this.url).writeByte(10);
            editor.writeUtf8(this.requestMethod).writeByte(10);
            editor.writeDecimalLong((long) this.varyHeaders.size()).writeByte(10);
            int size = this.varyHeaders.size();
            for (int i2 = 0; i2 < size; i2++) {
                editor.writeUtf8(this.varyHeaders.name(i2)).writeUtf8(": ").writeUtf8(this.varyHeaders.value(i2)).writeByte(10);
            }
            editor.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString()).writeByte(10);
            editor.writeDecimalLong((long) (this.responseHeaders.size() + 2)).writeByte(10);
            size = this.responseHeaders.size();
            while (i < size) {
                editor.writeUtf8(this.responseHeaders.name(i)).writeUtf8(": ").writeUtf8(this.responseHeaders.value(i)).writeByte(10);
                i++;
            }
            editor.writeUtf8(SENT_MILLIS).writeUtf8(": ").writeDecimalLong(this.sentRequestMillis).writeByte(10);
            editor.writeUtf8(RECEIVED_MILLIS).writeUtf8(": ").writeDecimalLong(this.receivedResponseMillis).writeByte(10);
            if (isHttps()) {
                editor.writeByte(10);
                editor.writeUtf8(this.handshake.cipherSuite().javaName()).writeByte(10);
                writeCertList(editor, this.handshake.peerCertificates());
                writeCertList(editor, this.handshake.localCertificates());
                editor.writeUtf8(this.handshake.tlsVersion().javaName()).writeByte(10);
            }
            editor.close();
        }

        private boolean isHttps() {
            return this.url.startsWith("https://");
        }

        public boolean matches(Request request, Response response) {
            return (this.url.equals(request.url().toString()) && this.requestMethod.equals(request.method()) && HttpHeaders.varyMatches(response, this.varyHeaders, request) != null) ? true : null;
        }

        public Response response(Snapshot snapshot) {
            String str = this.responseHeaders.get("Content-Type");
            String str2 = this.responseHeaders.get("Content-Length");
            return new Builder().request(new Request.Builder().url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build()).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new CacheResponseBody(snapshot, str, str2)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
        }
    }

    /* renamed from: okhttp3.Cache$1 */
    class C06151 implements InternalCache {
        C06151() {
        }

        public Response get(Request request) throws IOException {
            return Cache.this.get(request);
        }

        public CacheRequest put(Response response) throws IOException {
            return Cache.this.put(response);
        }

        public void remove(Request request) throws IOException {
            Cache.this.remove(request);
        }

        public void update(Response response, Response response2) {
            Cache.this.update(response, response2);
        }

        public void trackConditionalCacheHit() {
            Cache.this.trackConditionalCacheHit();
        }

        public void trackResponse(CacheStrategy cacheStrategy) {
            Cache.this.trackResponse(cacheStrategy);
        }
    }

    private final class CacheRequestImpl implements CacheRequest {
        private Sink body;
        private Sink cacheOut;
        boolean done;
        private final Editor editor;

        CacheRequestImpl(final Editor editor) {
            this.editor = editor;
            this.cacheOut = editor.newSink(1);
            this.body = new ForwardingSink(this.cacheOut, Cache.this) {
                public void close() throws IOException {
                    synchronized (Cache.this) {
                        if (CacheRequestImpl.this.done) {
                            return;
                        }
                        CacheRequestImpl.this.done = true;
                        Cache cache = Cache.this;
                        cache.writeSuccessCount++;
                        super.close();
                        editor.commit();
                    }
                }
            };
        }

        public void abort() {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r4 = this;
            r0 = okhttp3.Cache.this;
            monitor-enter(r0);
            r1 = r4.done;	 Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x0009;	 Catch:{ all -> 0x001f }
        L_0x0007:
            monitor-exit(r0);	 Catch:{ all -> 0x001f }
            return;	 Catch:{ all -> 0x001f }
        L_0x0009:
            r1 = 1;	 Catch:{ all -> 0x001f }
            r4.done = r1;	 Catch:{ all -> 0x001f }
            r2 = okhttp3.Cache.this;	 Catch:{ all -> 0x001f }
            r3 = r2.writeAbortCount;	 Catch:{ all -> 0x001f }
            r3 = r3 + r1;	 Catch:{ all -> 0x001f }
            r2.writeAbortCount = r3;	 Catch:{ all -> 0x001f }
            monitor-exit(r0);	 Catch:{ all -> 0x001f }
            r0 = r4.cacheOut;
            okhttp3.internal.Util.closeQuietly(r0);
            r0 = r4.editor;	 Catch:{ IOException -> 0x001e }
            r0.abort();	 Catch:{ IOException -> 0x001e }
        L_0x001e:
            return;
        L_0x001f:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x001f }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache.CacheRequestImpl.abort():void");
        }

        public Sink body() {
            return this.body;
        }
    }

    private static class CacheResponseBody extends ResponseBody {
        private final BufferedSource bodySource;
        @Nullable
        private final String contentLength;
        @Nullable
        private final String contentType;
        final Snapshot snapshot;

        CacheResponseBody(final Snapshot snapshot, String str, String str2) {
            this.snapshot = snapshot;
            this.contentType = str;
            this.contentLength = str2;
            this.bodySource = Okio.buffer(new ForwardingSource(snapshot.getSource(1)) {
                public void close() throws IOException {
                    snapshot.close();
                    super.close();
                }
            });
        }

        public MediaType contentType() {
            String str = this.contentType;
            return str != null ? MediaType.parse(str) : null;
        }

        public long contentLength() {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r3 = this;
            r0 = -1;
            r2 = r3.contentLength;	 Catch:{ NumberFormatException -> 0x000d }
            if (r2 == 0) goto L_0x000c;	 Catch:{ NumberFormatException -> 0x000d }
        L_0x0006:
            r2 = r3.contentLength;	 Catch:{ NumberFormatException -> 0x000d }
            r0 = java.lang.Long.parseLong(r2);	 Catch:{ NumberFormatException -> 0x000d }
        L_0x000c:
            return r0;
        L_0x000d:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache.CacheResponseBody.contentLength():long");
        }

        public BufferedSource source() {
            return this.bodySource;
        }
    }

    public Cache(File file, long j) {
        this(file, j, FileSystem.SYSTEM);
    }

    Cache(File file, long j, FileSystem fileSystem) {
        this.internalCache = new C06151();
        this.cache = DiskLruCache.create(fileSystem, file, VERSION, 2, j);
    }

    public static String key(HttpUrl httpUrl) {
        return ByteString.encodeUtf8(httpUrl.toString()).md5().hex();
    }

    @javax.annotation.Nullable
    okhttp3.Response get(okhttp3.Request r5) {
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
        r4 = this;
        r0 = r5.url();
        r0 = key(r0);
        r1 = 0;
        r2 = r4.cache;	 Catch:{ IOException -> 0x0033 }
        r0 = r2.get(r0);	 Catch:{ IOException -> 0x0033 }
        if (r0 != 0) goto L_0x0012;
    L_0x0011:
        return r1;
    L_0x0012:
        r2 = new okhttp3.Cache$Entry;	 Catch:{ IOException -> 0x002f }
        r3 = 0;	 Catch:{ IOException -> 0x002f }
        r3 = r0.getSource(r3);	 Catch:{ IOException -> 0x002f }
        r2.<init>(r3);	 Catch:{ IOException -> 0x002f }
        r0 = r2.response(r0);
        r5 = r2.matches(r5, r0);
        if (r5 != 0) goto L_0x002e;
    L_0x0026:
        r5 = r0.body();
        okhttp3.internal.Util.closeQuietly(r5);
        return r1;
    L_0x002e:
        return r0;
    L_0x002f:
        okhttp3.internal.Util.closeQuietly(r0);
        return r1;
    L_0x0033:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache.get(okhttp3.Request):okhttp3.Response");
    }

    @javax.annotation.Nullable
    okhttp3.internal.cache.CacheRequest put(okhttp3.Response r4) {
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
        r3 = this;
        r0 = r4.request();
        r0 = r0.method();
        r1 = r4.request();
        r1 = r1.method();
        r1 = okhttp3.internal.http.HttpMethod.invalidatesCache(r1);
        r2 = 0;
        if (r1 == 0) goto L_0x001f;
    L_0x0017:
        r4 = r4.request();	 Catch:{ IOException -> 0x001e }
        r3.remove(r4);	 Catch:{ IOException -> 0x001e }
    L_0x001e:
        return r2;
    L_0x001f:
        r1 = "GET";
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x0028;
    L_0x0027:
        return r2;
    L_0x0028:
        r0 = okhttp3.internal.http.HttpHeaders.hasVaryAll(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x002e:
        return r2;
    L_0x002f:
        r0 = new okhttp3.Cache$Entry;
        r0.<init>(r4);
        r1 = r3.cache;	 Catch:{ IOException -> 0x0052 }
        r4 = r4.request();	 Catch:{ IOException -> 0x0052 }
        r4 = r4.url();	 Catch:{ IOException -> 0x0052 }
        r4 = key(r4);	 Catch:{ IOException -> 0x0052 }
        r4 = r1.edit(r4);	 Catch:{ IOException -> 0x0052 }
        if (r4 != 0) goto L_0x0049;
    L_0x0048:
        return r2;
    L_0x0049:
        r0.writeTo(r4);	 Catch:{ IOException -> 0x0053 }
        r0 = new okhttp3.Cache$CacheRequestImpl;	 Catch:{ IOException -> 0x0053 }
        r0.<init>(r4);	 Catch:{ IOException -> 0x0053 }
        return r0;
    L_0x0052:
        r4 = r2;
    L_0x0053:
        r3.abortQuietly(r4);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache.put(okhttp3.Response):okhttp3.internal.cache.CacheRequest");
    }

    void remove(Request request) throws IOException {
        this.cache.remove(key(request.url()));
    }

    void update(okhttp3.Response r2, okhttp3.Response r3) {
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
        r1 = this;
        r0 = new okhttp3.Cache$Entry;
        r0.<init>(r3);
        r2 = r2.body();
        r2 = (okhttp3.Cache.CacheResponseBody) r2;
        r2 = r2.snapshot;
        r2 = r2.edit();	 Catch:{ IOException -> 0x001a }
        if (r2 == 0) goto L_0x001e;
    L_0x0013:
        r0.writeTo(r2);	 Catch:{ IOException -> 0x001b }
        r2.commit();	 Catch:{ IOException -> 0x001b }
        goto L_0x001e;
    L_0x001a:
        r2 = 0;
    L_0x001b:
        r1.abortQuietly(r2);
    L_0x001e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache.update(okhttp3.Response, okhttp3.Response):void");
    }

    private void abortQuietly(@javax.annotation.Nullable okhttp3.internal.cache.DiskLruCache.Editor r1) {
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
        r0 = this;
        if (r1 == 0) goto L_0x0005;
    L_0x0002:
        r1.abort();	 Catch:{ IOException -> 0x0005 }
    L_0x0005:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache.abortQuietly(okhttp3.internal.cache.DiskLruCache$Editor):void");
    }

    public void initialize() throws IOException {
        this.cache.initialize();
    }

    public void delete() throws IOException {
        this.cache.delete();
    }

    public void evictAll() throws IOException {
        this.cache.evictAll();
    }

    public Iterator<String> urls() throws IOException {
        return new C04312();
    }

    public synchronized int writeAbortCount() {
        return this.writeAbortCount;
    }

    public synchronized int writeSuccessCount() {
        return this.writeSuccessCount;
    }

    public long size() throws IOException {
        return this.cache.size();
    }

    public long maxSize() {
        return this.cache.getMaxSize();
    }

    public void flush() throws IOException {
        this.cache.flush();
    }

    public void close() throws IOException {
        this.cache.close();
    }

    public File directory() {
        return this.cache.getDirectory();
    }

    public boolean isClosed() {
        return this.cache.isClosed();
    }

    synchronized void trackResponse(CacheStrategy cacheStrategy) {
        this.requestCount++;
        if (cacheStrategy.networkRequest != null) {
            this.networkCount++;
        } else if (cacheStrategy.cacheResponse != null) {
            this.hitCount++;
        }
    }

    synchronized void trackConditionalCacheHit() {
        this.hitCount++;
    }

    public synchronized int networkCount() {
        return this.networkCount;
    }

    public synchronized int hitCount() {
        return this.hitCount;
    }

    public synchronized int requestCount() {
        return this.requestCount;
    }

    static int readInt(BufferedSource bufferedSource) throws IOException {
        try {
            long readDecimalLong = bufferedSource.readDecimalLong();
            bufferedSource = bufferedSource.readUtf8LineStrict();
            if (readDecimalLong >= 0 && readDecimalLong <= 2147483647L && bufferedSource.isEmpty()) {
                return (int) readDecimalLong;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("expected an int but was \"");
            stringBuilder.append(readDecimalLong);
            stringBuilder.append(bufferedSource);
            stringBuilder.append("\"");
            throw new IOException(stringBuilder.toString());
        } catch (BufferedSource bufferedSource2) {
            throw new IOException(bufferedSource2.getMessage());
        }
    }
}
