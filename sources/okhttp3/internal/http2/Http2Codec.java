package okhttp3.internal.http2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okio.ByteString;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class Http2Codec implements HttpCodec {
    private static final ByteString CONNECTION = ByteString.encodeUtf8("connection");
    private static final ByteString ENCODING = ByteString.encodeUtf8("encoding");
    private static final ByteString HOST = ByteString.encodeUtf8("host");
    private static final List<ByteString> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE, Header.TARGET_METHOD, Header.TARGET_PATH, Header.TARGET_SCHEME, Header.TARGET_AUTHORITY);
    private static final List<ByteString> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE);
    private static final ByteString KEEP_ALIVE = ByteString.encodeUtf8("keep-alive");
    private static final ByteString PROXY_CONNECTION = ByteString.encodeUtf8("proxy-connection");
    private static final ByteString TE = ByteString.encodeUtf8("te");
    private static final ByteString TRANSFER_ENCODING = ByteString.encodeUtf8("transfer-encoding");
    private static final ByteString UPGRADE = ByteString.encodeUtf8("upgrade");
    private final OkHttpClient client;
    private final Http2Connection connection;
    private Http2Stream stream;
    final StreamAllocation streamAllocation;

    class StreamFinishingSource extends ForwardingSource {
        StreamFinishingSource(Source source) {
            super(source);
        }

        public void close() throws IOException {
            Http2Codec.this.streamAllocation.streamFinished(false, Http2Codec.this);
            super.close();
        }
    }

    public static okhttp3.Response.Builder readHttp2HeadersList(java.util.List<okhttp3.internal.http2.Header> r8) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x008b in {7, 10, 13, 14, 17, 19} preds:[]
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
        r0 = new okhttp3.Headers$Builder;
        r0.<init>();
        r1 = r8.size();
        r2 = 0;
        r3 = 0;
        r4 = r0;
        r0 = r2;
    L_0x000d:
        if (r3 >= r1) goto L_0x0061;
    L_0x000f:
        r5 = r8.get(r3);
        r5 = (okhttp3.internal.http2.Header) r5;
        if (r5 != 0) goto L_0x0027;
    L_0x0017:
        if (r0 == 0) goto L_0x005e;
    L_0x0019:
        r5 = r0.code;
        r6 = 100;
        if (r5 != r6) goto L_0x005e;
    L_0x001f:
        r0 = new okhttp3.Headers$Builder;
        r0.<init>();
        r4 = r0;
        r0 = r2;
        goto L_0x005e;
    L_0x0027:
        r6 = r5.name;
        r5 = r5.value;
        r5 = r5.utf8();
        r7 = okhttp3.internal.http2.Header.RESPONSE_STATUS;
        r7 = r6.equals(r7);
        if (r7 == 0) goto L_0x004d;
    L_0x0037:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r6 = "HTTP/1.1 ";
        r0.append(r6);
        r0.append(r5);
        r0 = r0.toString();
        r0 = okhttp3.internal.http.StatusLine.parse(r0);
        goto L_0x005e;
    L_0x004d:
        r7 = HTTP_2_SKIPPED_RESPONSE_HEADERS;
        r7 = r7.contains(r6);
        if (r7 != 0) goto L_0x005e;
    L_0x0055:
        r7 = okhttp3.internal.Internal.instance;
        r6 = r6.utf8();
        r7.addLenient(r4, r6, r5);
    L_0x005e:
        r3 = r3 + 1;
        goto L_0x000d;
    L_0x0061:
        if (r0 == 0) goto L_0x0083;
    L_0x0063:
        r8 = new okhttp3.Response$Builder;
        r8.<init>();
        r1 = okhttp3.Protocol.HTTP_2;
        r8 = r8.protocol(r1);
        r1 = r0.code;
        r8 = r8.code(r1);
        r0 = r0.message;
        r8 = r8.message(r0);
        r0 = r4.build();
        r8 = r8.headers(r0);
        return r8;
    L_0x0083:
        r8 = new java.net.ProtocolException;
        r0 = "Expected ':status' header not present";
        r8.<init>(r0);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Codec.readHttp2HeadersList(java.util.List):okhttp3.Response$Builder");
    }

    public Http2Codec(OkHttpClient okHttpClient, StreamAllocation streamAllocation, Http2Connection http2Connection) {
        this.client = okHttpClient;
        this.streamAllocation = streamAllocation;
        this.connection = http2Connection;
    }

    public Sink createRequestBody(Request request, long j) {
        return this.stream.getSink();
    }

    public void writeRequestHeaders(Request request) throws IOException {
        if (this.stream == null) {
            this.stream = this.connection.newStream(http2HeadersList(request), request.body() != null);
            this.stream.readTimeout().timeout((long) this.client.readTimeoutMillis(), TimeUnit.MILLISECONDS);
            this.stream.writeTimeout().timeout((long) this.client.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public void flushRequest() throws IOException {
        this.connection.flush();
    }

    public void finishRequest() throws IOException {
        this.stream.getSink().close();
    }

    public Builder readResponseHeaders(boolean z) throws IOException {
        Builder readHttp2HeadersList = readHttp2HeadersList(this.stream.takeResponseHeaders());
        return (z && Internal.instance.code(readHttp2HeadersList)) ? false : readHttp2HeadersList;
    }

    public static List<Header> http2HeadersList(Request request) {
        Headers headers = request.headers();
        List<Header> arrayList = new ArrayList(headers.size() + 4);
        arrayList.add(new Header(Header.TARGET_METHOD, request.method()));
        arrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        String header = request.header("Host");
        if (header != null) {
            arrayList.add(new Header(Header.TARGET_AUTHORITY, header));
        }
        arrayList.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
        int size = headers.size();
        for (request = null; request < size; request++) {
            ByteString encodeUtf8 = ByteString.encodeUtf8(headers.name(request).toLowerCase(Locale.US));
            if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(encodeUtf8)) {
                arrayList.add(new Header(encodeUtf8, headers.value(request)));
            }
        }
        return arrayList;
    }

    public ResponseBody openResponseBody(Response response) throws IOException {
        return new RealResponseBody(response.headers(), Okio.buffer(new StreamFinishingSource(this.stream.getSource())));
    }

    public void cancel() {
        Http2Stream http2Stream = this.stream;
        if (http2Stream != null) {
            http2Stream.closeLater(ErrorCode.CANCEL);
        }
    }
}
