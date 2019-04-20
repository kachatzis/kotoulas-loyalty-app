package retrofit2;

import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.FormBody.Builder;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Part;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;

final class RequestBuilder {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String PATH_SEGMENT_ALWAYS_ENCODE_SET = " \"<>^`{}|\\?#";
    private final HttpUrl baseUrl;
    @Nullable
    private RequestBody body;
    @Nullable
    private MediaType contentType;
    @Nullable
    private Builder formBuilder;
    private final boolean hasBody;
    private final String method;
    @Nullable
    private MultipartBody.Builder multipartBuilder;
    @Nullable
    private String relativeUrl;
    private final Request.Builder requestBuilder = new Request.Builder();
    @Nullable
    private HttpUrl.Builder urlBuilder;

    private static class ContentTypeOverridingRequestBody extends RequestBody {
        private final MediaType contentType;
        private final RequestBody delegate;

        ContentTypeOverridingRequestBody(RequestBody requestBody, MediaType mediaType) {
            this.delegate = requestBody;
            this.contentType = mediaType;
        }

        public MediaType contentType() {
            return this.contentType;
        }

        public long contentLength() throws IOException {
            return this.delegate.contentLength();
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
            this.delegate.writeTo(bufferedSink);
        }
    }

    RequestBuilder(String str, HttpUrl httpUrl, @Nullable String str2, @Nullable Headers headers, @Nullable MediaType mediaType, boolean z, boolean z2, boolean z3) {
        this.method = str;
        this.baseUrl = httpUrl;
        this.relativeUrl = str2;
        this.contentType = mediaType;
        this.hasBody = z;
        if (headers != null) {
            this.requestBuilder.headers(headers);
        }
        if (z2) {
            this.formBuilder = new Builder();
        } else if (z3) {
            this.multipartBuilder = new MultipartBody.Builder();
            this.multipartBuilder.setType(MultipartBody.FORM);
        }
    }

    void setRelativeUrl(Object obj) {
        this.relativeUrl = obj.toString();
    }

    void addHeader(String str, String str2) {
        if ("Content-Type".equalsIgnoreCase(str)) {
            str = MediaType.parse(str2);
            if (str != null) {
                this.contentType = str;
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Malformed content type: ");
            stringBuilder.append(str2);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.requestBuilder.addHeader(str, str2);
    }

    void addPathParam(String str, String str2, boolean z) {
        String str3 = this.relativeUrl;
        if (str3 != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{");
            stringBuilder.append(str);
            stringBuilder.append("}");
            this.relativeUrl = str3.replace(stringBuilder.toString(), canonicalizeForPath(str2, z));
            return;
        }
        throw new AssertionError();
    }

    private static String canonicalizeForPath(String str, boolean z) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt >= 32 && codePointAt < 127 && PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(codePointAt) == -1) {
                if (!z) {
                    if (codePointAt != 47) {
                        if (codePointAt == 37) {
                        }
                    }
                }
                i += Character.charCount(codePointAt);
            }
            Buffer buffer = new Buffer();
            buffer.writeUtf8(str, 0, i);
            canonicalizeForPath(buffer, str, i, length, z);
            return buffer.readUtf8();
        }
        return str;
    }

    private static void canonicalizeForPath(Buffer buffer, String str, int i, int i2, boolean z) {
        Buffer buffer2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (z) {
                if (!(codePointAt == 9 || codePointAt == 10 || codePointAt == 12)) {
                    if (codePointAt == 13) {
                    }
                }
                i += Character.charCount(codePointAt);
            }
            if (codePointAt >= 32 && codePointAt < 127 && PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(codePointAt) == -1) {
                if (!z) {
                    if (codePointAt != 47) {
                        if (codePointAt == 37) {
                        }
                    }
                }
                buffer.writeUtf8CodePoint(codePointAt);
                i += Character.charCount(codePointAt);
            }
            if (buffer2 == null) {
                buffer2 = new Buffer();
            }
            buffer2.writeUtf8CodePoint(codePointAt);
            while (!buffer2.exhausted()) {
                int readByte = buffer2.readByte() & 255;
                buffer.writeByte(37);
                buffer.writeByte(HEX_DIGITS[(readByte >> 4) & 15]);
                buffer.writeByte(HEX_DIGITS[readByte & 15]);
            }
            i += Character.charCount(codePointAt);
        }
    }

    void addQueryParam(String str, @Nullable String str2, boolean z) {
        String str3 = this.relativeUrl;
        if (str3 != null) {
            this.urlBuilder = this.baseUrl.newBuilder(str3);
            if (this.urlBuilder != null) {
                this.relativeUrl = null;
            } else {
                str2 = new StringBuilder();
                str2.append("Malformed URL. Base: ");
                str2.append(this.baseUrl);
                str2.append(", Relative: ");
                str2.append(this.relativeUrl);
                throw new IllegalArgumentException(str2.toString());
            }
        }
        if (z) {
            this.urlBuilder.addEncodedQueryParameter(str, str2);
        } else {
            this.urlBuilder.addQueryParameter(str, str2);
        }
    }

    void addFormField(String str, String str2, boolean z) {
        if (z) {
            this.formBuilder.addEncoded(str, str2);
        } else {
            this.formBuilder.add(str, str2);
        }
    }

    void addPart(Headers headers, RequestBody requestBody) {
        this.multipartBuilder.addPart(headers, requestBody);
    }

    void addPart(Part part) {
        this.multipartBuilder.addPart(part);
    }

    void setBody(RequestBody requestBody) {
        this.body = requestBody;
    }

    Request build() {
        HttpUrl build;
        HttpUrl.Builder builder = this.urlBuilder;
        if (builder != null) {
            build = builder.build();
        } else {
            build = this.baseUrl.resolve(this.relativeUrl);
            if (build == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Malformed URL. Base: ");
                stringBuilder.append(this.baseUrl);
                stringBuilder.append(", Relative: ");
                stringBuilder.append(this.relativeUrl);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
        RequestBody requestBody = this.body;
        if (requestBody == null) {
            Builder builder2 = this.formBuilder;
            if (builder2 != null) {
                requestBody = builder2.build();
            } else {
                MultipartBody.Builder builder3 = this.multipartBuilder;
                if (builder3 != null) {
                    requestBody = builder3.build();
                } else if (this.hasBody) {
                    requestBody = RequestBody.create(null, new byte[0]);
                }
            }
        }
        MediaType mediaType = this.contentType;
        if (mediaType != null) {
            if (requestBody != null) {
                requestBody = new ContentTypeOverridingRequestBody(requestBody, mediaType);
            } else {
                this.requestBuilder.addHeader("Content-Type", mediaType.toString());
            }
        }
        return this.requestBuilder.url(build).method(this.method, requestBody).build();
    }
}
