package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okio.BufferedSink;
import okio.Okio;

public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    public Response intercept(Chain chain) throws IOException {
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        HttpCodec httpStream = realInterceptorChain.httpStream();
        StreamAllocation streamAllocation = realInterceptorChain.streamAllocation();
        RealConnection realConnection = (RealConnection) realInterceptorChain.connection();
        chain = realInterceptorChain.request();
        long currentTimeMillis = System.currentTimeMillis();
        httpStream.writeRequestHeaders(chain);
        Builder builder = null;
        if (HttpMethod.permitsRequestBody(chain.method()) && chain.body() != null) {
            if ("100-continue".equalsIgnoreCase(chain.header("Expect"))) {
                httpStream.flushRequest();
                builder = httpStream.readResponseHeaders(true);
            }
            if (builder == null) {
                BufferedSink buffer = Okio.buffer(httpStream.createRequestBody(chain, chain.body().contentLength()));
                chain.body().writeTo(buffer);
                buffer.close();
            } else if (!realConnection.isMultiplexed()) {
                streamAllocation.noNewStreams();
            }
        }
        httpStream.finishRequest();
        if (builder == null) {
            builder = httpStream.readResponseHeaders(false);
        }
        chain = builder.request(chain).handshake(streamAllocation.connection().handshake()).sentRequestAtMillis(currentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        int code = chain.code();
        if (this.forWebSocket && code == 101) {
            chain = chain.newBuilder().body(Util.EMPTY_RESPONSE).build();
        } else {
            chain = chain.newBuilder().body(httpStream.openResponseBody(chain)).build();
        }
        if ("close".equalsIgnoreCase(chain.request().header("Connection")) || "close".equalsIgnoreCase(chain.header("Connection"))) {
            streamAllocation.noNewStreams();
        }
        if ((code != 204 && code != 205) || chain.body().contentLength() <= 0) {
            return chain;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP ");
        stringBuilder.append(code);
        stringBuilder.append(" had non-zero Content-Length: ");
        stringBuilder.append(chain.body().contentLength());
        throw new ProtocolException(stringBuilder.toString());
    }
}
