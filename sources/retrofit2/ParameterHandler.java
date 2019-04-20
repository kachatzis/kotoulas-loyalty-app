package retrofit2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Map;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.RequestBody;

abstract class ParameterHandler<T> {

    /* renamed from: retrofit2.ParameterHandler$1 */
    class C06511 extends ParameterHandler<Iterable<T>> {
        C06511() {
        }

        void apply(RequestBuilder requestBuilder, @Nullable Iterable<T> iterable) throws IOException {
            if (iterable != null) {
                for (T apply : iterable) {
                    ParameterHandler.this.apply(requestBuilder, apply);
                }
            }
        }
    }

    /* renamed from: retrofit2.ParameterHandler$2 */
    class C06522 extends ParameterHandler<Object> {
        C06522() {
        }

        void apply(RequestBuilder requestBuilder, @Nullable Object obj) throws IOException {
            if (obj != null) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    ParameterHandler.this.apply(requestBuilder, Array.get(obj, i));
                }
            }
        }
    }

    static final class Body<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;

        Body(Converter<T, RequestBody> converter) {
            this.converter = converter;
        }

        void apply(RequestBuilder requestBuilder, @Nullable T t) {
            if (t != null) {
                try {
                    requestBuilder.setBody((RequestBody) this.converter.convert(t));
                    return;
                } catch (RequestBuilder requestBuilder2) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unable to convert ");
                    stringBuilder.append(t);
                    stringBuilder.append(" to RequestBody");
                    throw new RuntimeException(stringBuilder.toString(), requestBuilder2);
                }
            }
            throw new IllegalArgumentException("Body parameter value must not be null.");
        }
    }

    static final class Field<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        Field(String str, Converter<T, String> converter, boolean z) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
            this.encoded = z;
        }

        void apply(RequestBuilder requestBuilder, @Nullable T t) throws IOException {
            if (t != null) {
                String str = (String) this.valueConverter.convert(t);
                if (str != null) {
                    requestBuilder.addFormField(this.name, str, this.encoded);
                }
            }
        }
    }

    static final class FieldMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Converter<T, String> valueConverter;

        void apply(retrofit2.RequestBuilder r4, @javax.annotation.Nullable java.util.Map<java.lang.String, T> r5) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x0097 in {10, 12, 14, 16, 17, 19} preds:[]
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
            r3 = this;
            if (r5 == 0) goto L_0x008f;
        L_0x0002:
            r5 = r5.entrySet();
            r5 = r5.iterator();
        L_0x000a:
            r0 = r5.hasNext();
            if (r0 == 0) goto L_0x008e;
        L_0x0010:
            r0 = r5.next();
            r0 = (java.util.Map.Entry) r0;
            r1 = r0.getKey();
            r1 = (java.lang.String) r1;
            if (r1 == 0) goto L_0x0086;
        L_0x001e:
            r0 = r0.getValue();
            if (r0 == 0) goto L_0x006a;
        L_0x0024:
            r2 = r3.valueConverter;
            r2 = r2.convert(r0);
            r2 = (java.lang.String) r2;
            if (r2 == 0) goto L_0x0034;
        L_0x002e:
            r0 = r3.encoded;
            r4.addFormField(r1, r2, r0);
            goto L_0x000a;
        L_0x0034:
            r4 = new java.lang.IllegalArgumentException;
            r5 = new java.lang.StringBuilder;
            r5.<init>();
            r2 = "Field map value '";
            r5.append(r2);
            r5.append(r0);
            r0 = "' converted to null by ";
            r5.append(r0);
            r0 = r3.valueConverter;
            r0 = r0.getClass();
            r0 = r0.getName();
            r5.append(r0);
            r0 = " for key '";
            r5.append(r0);
            r5.append(r1);
            r0 = "'.";
            r5.append(r0);
            r5 = r5.toString();
            r4.<init>(r5);
            throw r4;
        L_0x006a:
            r4 = new java.lang.IllegalArgumentException;
            r5 = new java.lang.StringBuilder;
            r5.<init>();
            r0 = "Field map contained null value for key '";
            r5.append(r0);
            r5.append(r1);
            r0 = "'.";
            r5.append(r0);
            r5 = r5.toString();
            r4.<init>(r5);
            throw r4;
        L_0x0086:
            r4 = new java.lang.IllegalArgumentException;
            r5 = "Field map contained null key.";
            r4.<init>(r5);
            throw r4;
        L_0x008e:
            return;
        L_0x008f:
            r4 = new java.lang.IllegalArgumentException;
            r5 = "Field map was null.";
            r4.<init>(r5);
            throw r4;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: retrofit2.ParameterHandler.FieldMap.apply(retrofit2.RequestBuilder, java.util.Map):void");
        }

        FieldMap(Converter<T, String> converter, boolean z) {
            this.valueConverter = converter;
            this.encoded = z;
        }
    }

    static final class Header<T> extends ParameterHandler<T> {
        private final String name;
        private final Converter<T, String> valueConverter;

        Header(String str, Converter<T, String> converter) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
        }

        void apply(RequestBuilder requestBuilder, @Nullable T t) throws IOException {
            if (t != null) {
                String str = (String) this.valueConverter.convert(t);
                if (str != null) {
                    requestBuilder.addHeader(this.name, str);
                }
            }
        }
    }

    static final class HeaderMap<T> extends ParameterHandler<Map<String, T>> {
        private final Converter<T, String> valueConverter;

        void apply(retrofit2.RequestBuilder r4, @javax.annotation.Nullable java.util.Map<java.lang.String, T> r5) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x005d in {8, 10, 12, 13, 15} preds:[]
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
            r3 = this;
            if (r5 == 0) goto L_0x0055;
        L_0x0002:
            r5 = r5.entrySet();
            r5 = r5.iterator();
        L_0x000a:
            r0 = r5.hasNext();
            if (r0 == 0) goto L_0x0054;
        L_0x0010:
            r0 = r5.next();
            r0 = (java.util.Map.Entry) r0;
            r1 = r0.getKey();
            r1 = (java.lang.String) r1;
            if (r1 == 0) goto L_0x004c;
        L_0x001e:
            r0 = r0.getValue();
            if (r0 == 0) goto L_0x0030;
        L_0x0024:
            r2 = r3.valueConverter;
            r0 = r2.convert(r0);
            r0 = (java.lang.String) r0;
            r4.addHeader(r1, r0);
            goto L_0x000a;
        L_0x0030:
            r4 = new java.lang.IllegalArgumentException;
            r5 = new java.lang.StringBuilder;
            r5.<init>();
            r0 = "Header map contained null value for key '";
            r5.append(r0);
            r5.append(r1);
            r0 = "'.";
            r5.append(r0);
            r5 = r5.toString();
            r4.<init>(r5);
            throw r4;
        L_0x004c:
            r4 = new java.lang.IllegalArgumentException;
            r5 = "Header map contained null key.";
            r4.<init>(r5);
            throw r4;
        L_0x0054:
            return;
        L_0x0055:
            r4 = new java.lang.IllegalArgumentException;
            r5 = "Header map was null.";
            r4.<init>(r5);
            throw r4;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: retrofit2.ParameterHandler.HeaderMap.apply(retrofit2.RequestBuilder, java.util.Map):void");
        }

        HeaderMap(Converter<T, String> converter) {
            this.valueConverter = converter;
        }
    }

    static final class Part<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;
        private final Headers headers;

        Part(Headers headers, Converter<T, RequestBody> converter) {
            this.headers = headers;
            this.converter = converter;
        }

        void apply(RequestBuilder requestBuilder, @Nullable T t) {
            if (t != null) {
                try {
                    requestBuilder.addPart(this.headers, (RequestBody) this.converter.convert(t));
                } catch (RequestBuilder requestBuilder2) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unable to convert ");
                    stringBuilder.append(t);
                    stringBuilder.append(" to RequestBody");
                    throw new RuntimeException(stringBuilder.toString(), requestBuilder2);
                }
            }
        }
    }

    static final class PartMap<T> extends ParameterHandler<Map<String, T>> {
        private final String transferEncoding;
        private final Converter<T, RequestBody> valueConverter;

        void apply(retrofit2.RequestBuilder r7, @javax.annotation.Nullable java.util.Map<java.lang.String, T> r8) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x008c in {8, 10, 12, 13, 15} preds:[]
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
            if (r8 == 0) goto L_0x0084;
        L_0x0002:
            r8 = r8.entrySet();
            r8 = r8.iterator();
        L_0x000a:
            r0 = r8.hasNext();
            if (r0 == 0) goto L_0x0083;
        L_0x0010:
            r0 = r8.next();
            r0 = (java.util.Map.Entry) r0;
            r1 = r0.getKey();
            r1 = (java.lang.String) r1;
            if (r1 == 0) goto L_0x007b;
        L_0x001e:
            r0 = r0.getValue();
            if (r0 == 0) goto L_0x005f;
        L_0x0024:
            r2 = 4;
            r2 = new java.lang.String[r2];
            r3 = 0;
            r4 = "Content-Disposition";
            r2[r3] = r4;
            r3 = 1;
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "form-data; name=\"";
            r4.append(r5);
            r4.append(r1);
            r1 = "\"";
            r4.append(r1);
            r1 = r4.toString();
            r2[r3] = r1;
            r1 = 2;
            r3 = "Content-Transfer-Encoding";
            r2[r1] = r3;
            r1 = 3;
            r3 = r6.transferEncoding;
            r2[r1] = r3;
            r1 = okhttp3.Headers.of(r2);
            r2 = r6.valueConverter;
            r0 = r2.convert(r0);
            r0 = (okhttp3.RequestBody) r0;
            r7.addPart(r1, r0);
            goto L_0x000a;
        L_0x005f:
            r7 = new java.lang.IllegalArgumentException;
            r8 = new java.lang.StringBuilder;
            r8.<init>();
            r0 = "Part map contained null value for key '";
            r8.append(r0);
            r8.append(r1);
            r0 = "'.";
            r8.append(r0);
            r8 = r8.toString();
            r7.<init>(r8);
            throw r7;
        L_0x007b:
            r7 = new java.lang.IllegalArgumentException;
            r8 = "Part map contained null key.";
            r7.<init>(r8);
            throw r7;
        L_0x0083:
            return;
        L_0x0084:
            r7 = new java.lang.IllegalArgumentException;
            r8 = "Part map was null.";
            r7.<init>(r8);
            throw r7;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: retrofit2.ParameterHandler.PartMap.apply(retrofit2.RequestBuilder, java.util.Map):void");
        }

        PartMap(Converter<T, RequestBody> converter, String str) {
            this.valueConverter = converter;
            this.transferEncoding = str;
        }
    }

    static final class Path<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        Path(String str, Converter<T, String> converter, boolean z) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
            this.encoded = z;
        }

        void apply(RequestBuilder requestBuilder, @Nullable T t) throws IOException {
            if (t != null) {
                requestBuilder.addPathParam(this.name, (String) this.valueConverter.convert(t), this.encoded);
                return;
            }
            t = new StringBuilder();
            t.append("Path parameter \"");
            t.append(this.name);
            t.append("\" value must not be null.");
            throw new IllegalArgumentException(t.toString());
        }
    }

    static final class Query<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        Query(String str, Converter<T, String> converter, boolean z) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
            this.encoded = z;
        }

        void apply(RequestBuilder requestBuilder, @Nullable T t) throws IOException {
            if (t != null) {
                String str = (String) this.valueConverter.convert(t);
                if (str != null) {
                    requestBuilder.addQueryParam(this.name, str, this.encoded);
                }
            }
        }
    }

    static final class QueryMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Converter<T, String> valueConverter;

        void apply(retrofit2.RequestBuilder r4, @javax.annotation.Nullable java.util.Map<java.lang.String, T> r5) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x0097 in {10, 12, 14, 16, 17, 19} preds:[]
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
            r3 = this;
            if (r5 == 0) goto L_0x008f;
        L_0x0002:
            r5 = r5.entrySet();
            r5 = r5.iterator();
        L_0x000a:
            r0 = r5.hasNext();
            if (r0 == 0) goto L_0x008e;
        L_0x0010:
            r0 = r5.next();
            r0 = (java.util.Map.Entry) r0;
            r1 = r0.getKey();
            r1 = (java.lang.String) r1;
            if (r1 == 0) goto L_0x0086;
        L_0x001e:
            r0 = r0.getValue();
            if (r0 == 0) goto L_0x006a;
        L_0x0024:
            r2 = r3.valueConverter;
            r2 = r2.convert(r0);
            r2 = (java.lang.String) r2;
            if (r2 == 0) goto L_0x0034;
        L_0x002e:
            r0 = r3.encoded;
            r4.addQueryParam(r1, r2, r0);
            goto L_0x000a;
        L_0x0034:
            r4 = new java.lang.IllegalArgumentException;
            r5 = new java.lang.StringBuilder;
            r5.<init>();
            r2 = "Query map value '";
            r5.append(r2);
            r5.append(r0);
            r0 = "' converted to null by ";
            r5.append(r0);
            r0 = r3.valueConverter;
            r0 = r0.getClass();
            r0 = r0.getName();
            r5.append(r0);
            r0 = " for key '";
            r5.append(r0);
            r5.append(r1);
            r0 = "'.";
            r5.append(r0);
            r5 = r5.toString();
            r4.<init>(r5);
            throw r4;
        L_0x006a:
            r4 = new java.lang.IllegalArgumentException;
            r5 = new java.lang.StringBuilder;
            r5.<init>();
            r0 = "Query map contained null value for key '";
            r5.append(r0);
            r5.append(r1);
            r0 = "'.";
            r5.append(r0);
            r5 = r5.toString();
            r4.<init>(r5);
            throw r4;
        L_0x0086:
            r4 = new java.lang.IllegalArgumentException;
            r5 = "Query map contained null key.";
            r4.<init>(r5);
            throw r4;
        L_0x008e:
            return;
        L_0x008f:
            r4 = new java.lang.IllegalArgumentException;
            r5 = "Query map was null.";
            r4.<init>(r5);
            throw r4;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: retrofit2.ParameterHandler.QueryMap.apply(retrofit2.RequestBuilder, java.util.Map):void");
        }

        QueryMap(Converter<T, String> converter, boolean z) {
            this.valueConverter = converter;
            this.encoded = z;
        }
    }

    static final class QueryName<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final Converter<T, String> nameConverter;

        QueryName(Converter<T, String> converter, boolean z) {
            this.nameConverter = converter;
            this.encoded = z;
        }

        void apply(RequestBuilder requestBuilder, @Nullable T t) throws IOException {
            if (t != null) {
                requestBuilder.addQueryParam((String) this.nameConverter.convert(t), null, this.encoded);
            }
        }
    }

    static final class RawPart extends ParameterHandler<okhttp3.MultipartBody.Part> {
        static final RawPart INSTANCE = new RawPart();

        private RawPart() {
        }

        void apply(RequestBuilder requestBuilder, @Nullable okhttp3.MultipartBody.Part part) throws IOException {
            if (part != null) {
                requestBuilder.addPart(part);
            }
        }
    }

    static final class RelativeUrl extends ParameterHandler<Object> {
        RelativeUrl() {
        }

        void apply(RequestBuilder requestBuilder, @Nullable Object obj) {
            Utils.checkNotNull(obj, "@Url parameter is null.");
            requestBuilder.setRelativeUrl(obj);
        }
    }

    abstract void apply(RequestBuilder requestBuilder, @Nullable T t) throws IOException;

    ParameterHandler() {
    }

    final ParameterHandler<Iterable<T>> iterable() {
        return new C06511();
    }

    final ParameterHandler<Object> array() {
        return new C06522();
    }
}
