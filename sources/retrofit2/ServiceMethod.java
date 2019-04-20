package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import okhttp3.Call.Factory;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Url;

final class ServiceMethod<R, T> {
    static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);
    static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    private final HttpUrl baseUrl;
    final CallAdapter<R, T> callAdapter;
    final Factory callFactory;
    private final MediaType contentType;
    private final boolean hasBody;
    private final Headers headers;
    private final String httpMethod;
    private final boolean isFormEncoded;
    private final boolean isMultipart;
    private final ParameterHandler<?>[] parameterHandlers;
    private final String relativeUrl;
    private final Converter<ResponseBody, R> responseConverter;

    static final class Builder<T, R> {
        CallAdapter<T, R> callAdapter;
        MediaType contentType;
        boolean gotBody;
        boolean gotField;
        boolean gotPart;
        boolean gotPath;
        boolean gotQuery;
        boolean gotUrl;
        boolean hasBody;
        Headers headers;
        String httpMethod;
        boolean isFormEncoded;
        boolean isMultipart;
        final Method method;
        final Annotation[] methodAnnotations;
        final Annotation[][] parameterAnnotationsArray;
        ParameterHandler<?>[] parameterHandlers;
        final Type[] parameterTypes;
        String relativeUrl;
        Set<String> relativeUrlParamNames;
        Converter<ResponseBody, T> responseConverter;
        Type responseType;
        final Retrofit retrofit;

        private retrofit2.ParameterHandler<?> parseParameter(int r6, java.lang.reflect.Type r7, java.lang.annotation.Annotation[] r8) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:14:0x002b in {4, 6, 7, 9, 11, 13} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
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
            r0 = r8.length;
            r1 = 0;
            r2 = 0;
            r3 = r2;
            r2 = 0;
        L_0x0005:
            if (r2 >= r0) goto L_0x001f;
        L_0x0007:
            r4 = r8[r2];
            r4 = r5.parseParameterAnnotation(r6, r7, r8, r4);
            if (r4 != 0) goto L_0x0010;
        L_0x000f:
            goto L_0x0013;
        L_0x0010:
            if (r3 != 0) goto L_0x0016;
        L_0x0012:
            r3 = r4;
        L_0x0013:
            r2 = r2 + 1;
            goto L_0x0005;
        L_0x0016:
            r7 = new java.lang.Object[r1];
            r8 = "Multiple Retrofit annotations found, only one allowed.";
            r6 = r5.parameterError(r6, r8, r7);
            throw r6;
        L_0x001f:
            if (r3 == 0) goto L_0x0022;
        L_0x0021:
            return r3;
        L_0x0022:
            r7 = new java.lang.Object[r1];
            r8 = "No Retrofit annotation found.";
            r6 = r5.parameterError(r6, r8, r7);
            throw r6;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: retrofit2.ServiceMethod.Builder.parseParameter(int, java.lang.reflect.Type, java.lang.annotation.Annotation[]):retrofit2.ParameterHandler<?>");
        }

        public retrofit2.ServiceMethod build() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:70:0x0114 in {6, 15, 17, 19, 27, 29, 31, 36, 38, 47, 49, 54, 56, 61, 63, 65, 67, 69} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r6 = this;
            r0 = r6.createCallAdapter();
            r6.callAdapter = r0;
            r0 = r6.callAdapter;
            r0 = r0.responseType();
            r6.responseType = r0;
            r0 = r6.responseType;
            r1 = retrofit2.Response.class;
            r2 = 0;
            if (r0 == r1) goto L_0x00ed;
        L_0x0015:
            r1 = okhttp3.Response.class;
            if (r0 == r1) goto L_0x00ed;
        L_0x0019:
            r0 = r6.createResponseConverter();
            r6.responseConverter = r0;
            r0 = r6.methodAnnotations;
            r1 = r0.length;
            r3 = 0;
        L_0x0023:
            if (r3 >= r1) goto L_0x002d;
        L_0x0025:
            r4 = r0[r3];
            r6.parseMethodAnnotation(r4);
            r3 = r3 + 1;
            goto L_0x0023;
        L_0x002d:
            r0 = r6.httpMethod;
            if (r0 == 0) goto L_0x00e4;
        L_0x0031:
            r0 = r6.hasBody;
            if (r0 != 0) goto L_0x0050;
        L_0x0035:
            r0 = r6.isMultipart;
            if (r0 != 0) goto L_0x0047;
        L_0x0039:
            r0 = r6.isFormEncoded;
            if (r0 != 0) goto L_0x003e;
        L_0x003d:
            goto L_0x0050;
        L_0x003e:
            r0 = new java.lang.Object[r2];
            r1 = "FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).";
            r0 = r6.methodError(r1, r0);
            throw r0;
        L_0x0047:
            r0 = new java.lang.Object[r2];
            r1 = "Multipart can only be specified on HTTP methods with request body (e.g., @POST).";
            r0 = r6.methodError(r1, r0);
            throw r0;
        L_0x0050:
            r0 = r6.parameterAnnotationsArray;
            r0 = r0.length;
            r1 = new retrofit2.ParameterHandler[r0];
            r6.parameterHandlers = r1;
            r1 = 0;
        L_0x0058:
            r3 = 1;
            if (r1 >= r0) goto L_0x008a;
        L_0x005b:
            r4 = r6.parameterTypes;
            r4 = r4[r1];
            r5 = retrofit2.Utils.hasUnresolvableType(r4);
            if (r5 != 0) goto L_0x007f;
        L_0x0065:
            r3 = r6.parameterAnnotationsArray;
            r3 = r3[r1];
            if (r3 == 0) goto L_0x0076;
        L_0x006b:
            r5 = r6.parameterHandlers;
            r3 = r6.parseParameter(r1, r4, r3);
            r5[r1] = r3;
            r1 = r1 + 1;
            goto L_0x0058;
        L_0x0076:
            r0 = new java.lang.Object[r2];
            r2 = "No Retrofit annotation found.";
            r0 = r6.parameterError(r1, r2, r0);
            throw r0;
        L_0x007f:
            r0 = new java.lang.Object[r3];
            r0[r2] = r4;
            r2 = "Parameter type must not include a type variable or wildcard: %s";
            r0 = r6.parameterError(r1, r2, r0);
            throw r0;
        L_0x008a:
            r0 = r6.relativeUrl;
            if (r0 != 0) goto L_0x00a0;
        L_0x008e:
            r0 = r6.gotUrl;
            if (r0 == 0) goto L_0x0093;
        L_0x0092:
            goto L_0x00a0;
        L_0x0093:
            r0 = new java.lang.Object[r3];
            r1 = r6.httpMethod;
            r0[r2] = r1;
            r1 = "Missing either @%s URL or @Url parameter.";
            r0 = r6.methodError(r1, r0);
            throw r0;
        L_0x00a0:
            r0 = r6.isFormEncoded;
            if (r0 != 0) goto L_0x00ba;
        L_0x00a4:
            r0 = r6.isMultipart;
            if (r0 != 0) goto L_0x00ba;
        L_0x00a8:
            r0 = r6.hasBody;
            if (r0 != 0) goto L_0x00ba;
        L_0x00ac:
            r0 = r6.gotBody;
            if (r0 != 0) goto L_0x00b1;
        L_0x00b0:
            goto L_0x00ba;
        L_0x00b1:
            r0 = new java.lang.Object[r2];
            r1 = "Non-body HTTP method cannot contain @Body.";
            r0 = r6.methodError(r1, r0);
            throw r0;
        L_0x00ba:
            r0 = r6.isFormEncoded;
            if (r0 == 0) goto L_0x00cc;
        L_0x00be:
            r0 = r6.gotField;
            if (r0 == 0) goto L_0x00c3;
        L_0x00c2:
            goto L_0x00cc;
        L_0x00c3:
            r0 = new java.lang.Object[r2];
            r1 = "Form-encoded method must contain at least one @Field.";
            r0 = r6.methodError(r1, r0);
            throw r0;
        L_0x00cc:
            r0 = r6.isMultipart;
            if (r0 == 0) goto L_0x00de;
        L_0x00d0:
            r0 = r6.gotPart;
            if (r0 == 0) goto L_0x00d5;
        L_0x00d4:
            goto L_0x00de;
        L_0x00d5:
            r0 = new java.lang.Object[r2];
            r1 = "Multipart method must contain at least one @Part.";
            r0 = r6.methodError(r1, r0);
            throw r0;
        L_0x00de:
            r0 = new retrofit2.ServiceMethod;
            r0.<init>(r6);
            return r0;
        L_0x00e4:
            r0 = new java.lang.Object[r2];
            r1 = "HTTP method annotation is required (e.g., @GET, @POST, etc.).";
            r0 = r6.methodError(r1, r0);
            throw r0;
        L_0x00ed:
            r0 = new java.lang.StringBuilder;
            r0.<init>();
            r1 = "'";
            r0.append(r1);
            r1 = r6.responseType;
            r1 = retrofit2.Utils.getRawType(r1);
            r1 = r1.getName();
            r0.append(r1);
            r1 = "' is not a valid response body type. Did you mean ResponseBody?";
            r0.append(r1);
            r0 = r0.toString();
            r1 = new java.lang.Object[r2];
            r0 = r6.methodError(r0, r1);
            throw r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: retrofit2.ServiceMethod.Builder.build():retrofit2.ServiceMethod");
        }

        Builder(Retrofit retrofit, Method method) {
            this.retrofit = retrofit;
            this.method = method;
            this.methodAnnotations = method.getAnnotations();
            this.parameterTypes = method.getGenericParameterTypes();
            this.parameterAnnotationsArray = method.getParameterAnnotations();
        }

        private CallAdapter<T, R> createCallAdapter() {
            Type genericReturnType = this.method.getGenericReturnType();
            if (Utils.hasUnresolvableType(genericReturnType)) {
                throw methodError("Method return type must not include a type variable or wildcard: %s", genericReturnType);
            } else if (genericReturnType != Void.TYPE) {
                try {
                    genericReturnType = this.retrofit.callAdapter(genericReturnType, this.method.getAnnotations());
                    return genericReturnType;
                } catch (Throwable e) {
                    throw methodError(e, "Unable to create call adapter for %s", genericReturnType);
                }
            } else {
                throw methodError("Service methods cannot return void.", new Object[0]);
            }
        }

        private void parseMethodAnnotation(Annotation annotation) {
            if (annotation instanceof DELETE) {
                parseHttpMethodAndPath("DELETE", ((DELETE) annotation).value(), false);
            } else if (annotation instanceof GET) {
                parseHttpMethodAndPath("GET", ((GET) annotation).value(), false);
            } else if (annotation instanceof HEAD) {
                parseHttpMethodAndPath("HEAD", ((HEAD) annotation).value(), false);
                if (Void.class.equals(this.responseType) == null) {
                    throw methodError("HEAD method must use Void as response type.", new Object[0]);
                }
            } else if (annotation instanceof PATCH) {
                parseHttpMethodAndPath("PATCH", ((PATCH) annotation).value(), true);
            } else if (annotation instanceof POST) {
                parseHttpMethodAndPath("POST", ((POST) annotation).value(), true);
            } else if (annotation instanceof PUT) {
                parseHttpMethodAndPath("PUT", ((PUT) annotation).value(), true);
            } else if (annotation instanceof OPTIONS) {
                parseHttpMethodAndPath("OPTIONS", ((OPTIONS) annotation).value(), false);
            } else if (annotation instanceof HTTP) {
                HTTP http = (HTTP) annotation;
                parseHttpMethodAndPath(http.method(), http.path(), http.hasBody());
            } else if (annotation instanceof retrofit2.http.Headers) {
                annotation = ((retrofit2.http.Headers) annotation).value();
                if (annotation.length != 0) {
                    this.headers = parseHeaders(annotation);
                } else {
                    throw methodError("@Headers annotation is empty.", new Object[0]);
                }
            } else if (annotation instanceof Multipart) {
                if (this.isFormEncoded == null) {
                    this.isMultipart = true;
                } else {
                    throw methodError("Only one encoding annotation is allowed.", new Object[0]);
                }
            } else if ((annotation instanceof FormUrlEncoded) == null) {
            } else {
                if (this.isMultipart == null) {
                    this.isFormEncoded = true;
                } else {
                    throw methodError("Only one encoding annotation is allowed.", new Object[0]);
                }
            }
        }

        private void parseHttpMethodAndPath(String str, String str2, boolean z) {
            if (this.httpMethod == null) {
                this.httpMethod = str;
                this.hasBody = z;
                if (str2.isEmpty() == null) {
                    str = str2.indexOf(63);
                    if (str != true && str < str2.length() - true) {
                        if (ServiceMethod.PARAM_URL_REGEX.matcher(str2.substring(str + 1)).find()) {
                            throw methodError("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", new Object[]{str2.substring(str + 1)});
                        }
                    }
                    this.relativeUrl = str2;
                    this.relativeUrlParamNames = ServiceMethod.parsePathParameters(str2);
                    return;
                }
                return;
            }
            throw methodError("Only one HTTP method is allowed. Found: %s and %s.", new Object[]{this.httpMethod, str});
        }

        private Headers parseHeaders(String[] strArr) {
            okhttp3.Headers.Builder builder = new okhttp3.Headers.Builder();
            for (String str : strArr) {
                String str2;
                int indexOf = str2.indexOf(58);
                if (indexOf == -1 || indexOf == 0 || indexOf == str2.length() - 1) {
                    throw methodError("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str2);
                }
                String substring = str2.substring(0, indexOf);
                str2 = str2.substring(indexOf + 1).trim();
                if ("Content-Type".equalsIgnoreCase(substring)) {
                    MediaType parse = MediaType.parse(str2);
                    if (parse != null) {
                        this.contentType = parse;
                    } else {
                        throw methodError("Malformed content type: %s", str2);
                    }
                }
                builder.add(substring, str2);
            }
            return builder.build();
        }

        private ParameterHandler<?> parseParameterAnnotation(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (annotation instanceof Url) {
                if (this.gotUrl != null) {
                    throw parameterError(i, "Multiple @Url method annotations found.", new Object[0]);
                } else if (this.gotPath != null) {
                    throw parameterError(i, "@Path parameters may not be used with @Url.", new Object[0]);
                } else if (this.gotQuery != null) {
                    throw parameterError(i, "A @Url parameter must not come after a @Query", new Object[0]);
                } else if (this.relativeUrl == null) {
                    this.gotUrl = true;
                    if (!(type == HttpUrl.class || type == String.class || type == URI.class)) {
                        if ((type instanceof Class) == null || "android.net.Uri".equals(((Class) type).getName()) == null) {
                            throw parameterError(i, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
                        }
                    }
                    return new RelativeUrl();
                } else {
                    throw parameterError(i, "@Url cannot be used with @%s URL", new Object[]{this.httpMethod});
                }
            } else if (annotation instanceof Path) {
                if (this.gotQuery) {
                    throw parameterError(i, "A @Path parameter must not come after a @Query.", new Object[0]);
                } else if (this.gotUrl) {
                    throw parameterError(i, "@Path parameters may not be used with @Url.", new Object[0]);
                } else if (this.relativeUrl != null) {
                    this.gotPath = true;
                    Path path = (Path) annotation;
                    r0 = path.value();
                    validatePathName(i, r0);
                    return new Path(r0, this.retrofit.stringConverter(type, annotationArr), path.encoded());
                } else {
                    throw parameterError(i, "@Path can only be used with relative url on @%s", new Object[]{this.httpMethod});
                }
            } else if (annotation instanceof Query) {
                Query query = (Query) annotation;
                r0 = query.value();
                annotation = query.encoded();
                r3 = Utils.getRawType(type);
                this.gotQuery = true;
                if (Iterable.class.isAssignableFrom(r3)) {
                    if (type instanceof ParameterizedType) {
                        return new Query(r0, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), annotation).iterable();
                    }
                    type = new StringBuilder();
                    type.append(r3.getSimpleName());
                    type.append(" must include generic type (e.g., ");
                    type.append(r3.getSimpleName());
                    type.append("<String>)");
                    throw parameterError(i, type.toString(), new Object[0]);
                } else if (r3.isArray() == 0) {
                    return new Query(r0, this.retrofit.stringConverter(type, annotationArr), annotation);
                } else {
                    return new Query(r0, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(r3.getComponentType()), annotationArr), annotation).array();
                }
            } else if (annotation instanceof QueryName) {
                annotation = ((QueryName) annotation).encoded();
                r0 = Utils.getRawType(type);
                this.gotQuery = true;
                if (Iterable.class.isAssignableFrom(r0)) {
                    if (type instanceof ParameterizedType) {
                        return new QueryName(this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), annotation).iterable();
                    }
                    type = new StringBuilder();
                    type.append(r0.getSimpleName());
                    type.append(" must include generic type (e.g., ");
                    type.append(r0.getSimpleName());
                    type.append("<String>)");
                    throw parameterError(i, type.toString(), new Object[0]);
                } else if (r0.isArray() == 0) {
                    return new QueryName(this.retrofit.stringConverter(type, annotationArr), annotation);
                } else {
                    return new QueryName(this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(r0.getComponentType()), annotationArr), annotation).array();
                }
            } else if (annotation instanceof QueryMap) {
                r0 = Utils.getRawType(type);
                if (Map.class.isAssignableFrom(r0)) {
                    type = Utils.getSupertype(type, r0, Map.class);
                    if (type instanceof ParameterizedType) {
                        r9 = (ParameterizedType) type;
                        r0 = Utils.getParameterUpperBound(0, r9);
                        if (String.class == r0) {
                            return new QueryMap(this.retrofit.stringConverter(Utils.getParameterUpperBound(1, r9), annotationArr), ((QueryMap) annotation).encoded());
                        }
                        type = new StringBuilder();
                        type.append("@QueryMap keys must be of type String: ");
                        type.append(r0);
                        throw parameterError(i, type.toString(), new Object[0]);
                    }
                    throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw parameterError(i, "@QueryMap parameter type must be Map.", new Object[0]);
            } else if (annotation instanceof Header) {
                annotation = ((Header) annotation).value();
                r0 = Utils.getRawType(type);
                if (Iterable.class.isAssignableFrom(r0)) {
                    if (type instanceof ParameterizedType) {
                        return new Header(annotation, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr)).iterable();
                    }
                    type = new StringBuilder();
                    type.append(r0.getSimpleName());
                    type.append(" must include generic type (e.g., ");
                    type.append(r0.getSimpleName());
                    type.append("<String>)");
                    throw parameterError(i, type.toString(), new Object[0]);
                } else if (r0.isArray() == 0) {
                    return new Header(annotation, this.retrofit.stringConverter(type, annotationArr));
                } else {
                    return new Header(annotation, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(r0.getComponentType()), annotationArr)).array();
                }
            } else if (annotation instanceof HeaderMap) {
                annotation = Utils.getRawType(type);
                if (Map.class.isAssignableFrom(annotation)) {
                    type = Utils.getSupertype(type, annotation, Map.class);
                    if ((type instanceof ParameterizedType) != null) {
                        r9 = (ParameterizedType) type;
                        Class parameterUpperBound = Utils.getParameterUpperBound(0, r9);
                        if (String.class == parameterUpperBound) {
                            return new HeaderMap(this.retrofit.stringConverter(Utils.getParameterUpperBound(1, r9), annotationArr));
                        }
                        type = new StringBuilder();
                        type.append("@HeaderMap keys must be of type String: ");
                        type.append(parameterUpperBound);
                        throw parameterError(i, type.toString(), new Object[0]);
                    }
                    throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw parameterError(i, "@HeaderMap parameter type must be Map.", new Object[0]);
            } else if (annotation instanceof Field) {
                if (this.isFormEncoded) {
                    Field field = (Field) annotation;
                    r0 = field.value();
                    annotation = field.encoded();
                    this.gotField = true;
                    Class rawType = Utils.getRawType(type);
                    if (Iterable.class.isAssignableFrom(rawType)) {
                        if (type instanceof ParameterizedType) {
                            return new Field(r0, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), annotation).iterable();
                        }
                        type = new StringBuilder();
                        type.append(rawType.getSimpleName());
                        type.append(" must include generic type (e.g., ");
                        type.append(rawType.getSimpleName());
                        type.append("<String>)");
                        throw parameterError(i, type.toString(), new Object[0]);
                    } else if (rawType.isArray() == 0) {
                        return new Field(r0, this.retrofit.stringConverter(type, annotationArr), annotation);
                    } else {
                        return new Field(r0, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType.getComponentType()), annotationArr), annotation).array();
                    }
                }
                throw parameterError(i, "@Field parameters can only be used with form encoding.", new Object[0]);
            } else if (annotation instanceof FieldMap) {
                if (this.isFormEncoded) {
                    r0 = Utils.getRawType(type);
                    if (Map.class.isAssignableFrom(r0)) {
                        type = Utils.getSupertype(type, r0, Map.class);
                        if (type instanceof ParameterizedType) {
                            r9 = (ParameterizedType) type;
                            r0 = Utils.getParameterUpperBound(0, r9);
                            if (String.class == r0) {
                                i = this.retrofit.stringConverter(Utils.getParameterUpperBound(1, r9), annotationArr);
                                this.gotField = true;
                                return new FieldMap(i, ((FieldMap) annotation).encoded());
                            }
                            type = new StringBuilder();
                            type.append("@FieldMap keys must be of type String: ");
                            type.append(r0);
                            throw parameterError(i, type.toString(), new Object[0]);
                        }
                        throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw parameterError(i, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                throw parameterError(i, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
            } else if (annotation instanceof Part) {
                if (this.isMultipart) {
                    Part part = (Part) annotation;
                    this.gotPart = true;
                    r0 = part.value();
                    r3 = Utils.getRawType(type);
                    if (!r0.isEmpty()) {
                        String[] strArr = new String[4];
                        strArr[0] = "Content-Disposition";
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("form-data; name=\"");
                        stringBuilder.append(r0);
                        stringBuilder.append("\"");
                        strArr[1] = stringBuilder.toString();
                        strArr[2] = "Content-Transfer-Encoding";
                        strArr[3] = part.encoding();
                        annotation = Headers.of(strArr);
                        if (Iterable.class.isAssignableFrom(r3)) {
                            if (type instanceof ParameterizedType) {
                                type = Utils.getParameterUpperBound(0, (ParameterizedType) type);
                                if (!MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(type))) {
                                    return new Part(annotation, this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations)).iterable();
                                }
                                throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                            }
                            type = new StringBuilder();
                            type.append(r3.getSimpleName());
                            type.append(" must include generic type (e.g., ");
                            type.append(r3.getSimpleName());
                            type.append("<String>)");
                            throw parameterError(i, type.toString(), new Object[0]);
                        } else if (r3.isArray()) {
                            type = ServiceMethod.boxIfPrimitive(r3.getComponentType());
                            if (!MultipartBody.Part.class.isAssignableFrom(type)) {
                                return new Part(annotation, this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations)).array();
                            }
                            throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        } else if (!MultipartBody.Part.class.isAssignableFrom(r3)) {
                            return new Part(annotation, this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations));
                        } else {
                            throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        }
                    } else if (Iterable.class.isAssignableFrom(r3) != null) {
                        if ((type instanceof ParameterizedType) == null) {
                            type = new StringBuilder();
                            type.append(r3.getSimpleName());
                            type.append(" must include generic type (e.g., ");
                            type.append(r3.getSimpleName());
                            type.append("<String>)");
                            throw parameterError(i, type.toString(), new Object[0]);
                        } else if (MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(Utils.getParameterUpperBound(0, (ParameterizedType) type))) != null) {
                            return RawPart.INSTANCE.iterable();
                        } else {
                            throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        }
                    } else if (r3.isArray() != null) {
                        if (MultipartBody.Part.class.isAssignableFrom(r3.getComponentType()) != null) {
                            return RawPart.INSTANCE.array();
                        }
                        throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    } else if (MultipartBody.Part.class.isAssignableFrom(r3) != null) {
                        return RawPart.INSTANCE;
                    } else {
                        throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                }
                throw parameterError(i, "@Part parameters can only be used with multipart encoding.", new Object[0]);
            } else if (annotation instanceof PartMap) {
                if (this.isMultipart) {
                    this.gotPart = true;
                    r0 = Utils.getRawType(type);
                    if (Map.class.isAssignableFrom(r0)) {
                        type = Utils.getSupertype(type, r0, Map.class);
                        if (type instanceof ParameterizedType) {
                            r9 = (ParameterizedType) type;
                            r0 = Utils.getParameterUpperBound(0, r9);
                            if (String.class == r0) {
                                type = Utils.getParameterUpperBound(1, r9);
                                if (!MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(type))) {
                                    return new PartMap(this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations), ((PartMap) annotation).encoding());
                                }
                                throw parameterError(i, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                            }
                            type = new StringBuilder();
                            type.append("@PartMap keys must be of type String: ");
                            type.append(r0);
                            throw parameterError(i, type.toString(), new Object[0]);
                        }
                        throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw parameterError(i, "@PartMap parameter type must be Map.", new Object[0]);
                }
                throw parameterError(i, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
            } else if ((annotation instanceof Body) == null) {
                return 0;
            } else {
                if (this.isFormEncoded != null || this.isMultipart != null) {
                    throw parameterError(i, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                } else if (this.gotBody == null) {
                    try {
                        i = this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations);
                        this.gotBody = true;
                        return new Body(i);
                    } catch (Annotation[] annotationArr2) {
                        throw parameterError(annotationArr2, i, "Unable to create @Body converter for %s", new Object[]{type});
                    }
                } else {
                    throw parameterError(i, "Multiple @Body method annotations found.", new Object[0]);
                }
            }
        }

        private void validatePathName(int i, String str) {
            if (!ServiceMethod.PARAM_NAME_REGEX.matcher(str).matches()) {
                throw parameterError(i, "@Path parameter name must match %s. Found: %s", ServiceMethod.PARAM_URL_REGEX.pattern(), str);
            } else if (!this.relativeUrlParamNames.contains(str)) {
                throw parameterError(i, "URL \"%s\" does not contain \"{%s}\".", this.relativeUrl, str);
            }
        }

        private Converter<ResponseBody, T> createResponseConverter() {
            try {
                return this.retrofit.responseBodyConverter(this.responseType, this.method.getAnnotations());
            } catch (Throwable e) {
                throw methodError(e, "Unable to create converter for %s", this.responseType);
            }
        }

        private RuntimeException methodError(String str, Object... objArr) {
            return methodError(null, str, objArr);
        }

        private RuntimeException methodError(Throwable th, String str, Object... objArr) {
            str = String.format(str, objArr);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("\n    for method ");
            stringBuilder.append(this.method.getDeclaringClass().getSimpleName());
            stringBuilder.append(".");
            stringBuilder.append(this.method.getName());
            return new IllegalArgumentException(stringBuilder.toString(), th);
        }

        private RuntimeException parameterError(Throwable th, int i, String str, Object... objArr) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" (parameter #");
            stringBuilder.append(i + 1);
            stringBuilder.append(")");
            return methodError(th, stringBuilder.toString(), objArr);
        }

        private RuntimeException parameterError(int i, String str, Object... objArr) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" (parameter #");
            stringBuilder.append(i + 1);
            stringBuilder.append(")");
            return methodError(stringBuilder.toString(), objArr);
        }
    }

    okhttp3.Request toRequest(@javax.annotation.Nullable java.lang.Object... r11) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:12:0x0057 in {2, 3, 7, 9, 11} preds:[]
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
        r10 = this;
        r9 = new retrofit2.RequestBuilder;
        r1 = r10.httpMethod;
        r2 = r10.baseUrl;
        r3 = r10.relativeUrl;
        r4 = r10.headers;
        r5 = r10.contentType;
        r6 = r10.hasBody;
        r7 = r10.isFormEncoded;
        r8 = r10.isMultipart;
        r0 = r9;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8);
        r0 = r10.parameterHandlers;
        r1 = 0;
        if (r11 == 0) goto L_0x001d;
    L_0x001b:
        r2 = r11.length;
        goto L_0x001e;
    L_0x001d:
        r2 = 0;
    L_0x001e:
        r3 = r0.length;
        if (r2 != r3) goto L_0x0032;
    L_0x0021:
        if (r1 >= r2) goto L_0x002d;
    L_0x0023:
        r3 = r0[r1];
        r4 = r11[r1];
        r3.apply(r9, r4);
        r1 = r1 + 1;
        goto L_0x0021;
    L_0x002d:
        r11 = r9.build();
        return r11;
    L_0x0032:
        r11 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "Argument count (";
        r1.append(r3);
        r1.append(r2);
        r2 = ") doesn't match expected count (";
        r1.append(r2);
        r0 = r0.length;
        r1.append(r0);
        r0 = ")";
        r1.append(r0);
        r0 = r1.toString();
        r11.<init>(r0);
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.ServiceMethod.toRequest(java.lang.Object[]):okhttp3.Request");
    }

    ServiceMethod(Builder<R, T> builder) {
        this.callFactory = builder.retrofit.callFactory();
        this.callAdapter = builder.callAdapter;
        this.baseUrl = builder.retrofit.baseUrl();
        this.responseConverter = builder.responseConverter;
        this.httpMethod = builder.httpMethod;
        this.relativeUrl = builder.relativeUrl;
        this.headers = builder.headers;
        this.contentType = builder.contentType;
        this.hasBody = builder.hasBody;
        this.isFormEncoded = builder.isFormEncoded;
        this.isMultipart = builder.isMultipart;
        this.parameterHandlers = builder.parameterHandlers;
    }

    R toResponse(ResponseBody responseBody) throws IOException {
        return this.responseConverter.convert(responseBody);
    }

    static Set<String> parsePathParameters(String str) {
        str = PARAM_URL_REGEX.matcher(str);
        Set<String> linkedHashSet = new LinkedHashSet();
        while (str.find()) {
            linkedHashSet.add(str.group(1));
        }
        return linkedHashSet;
    }

    static Class<?> boxIfPrimitive(Class<?> cls) {
        if (Boolean.TYPE == cls) {
            return Boolean.class;
        }
        if (Byte.TYPE == cls) {
            return Byte.class;
        }
        if (Character.TYPE == cls) {
            return Character.class;
        }
        if (Double.TYPE == cls) {
            return Double.class;
        }
        if (Float.TYPE == cls) {
            return Float.class;
        }
        if (Integer.TYPE == cls) {
            return Integer.class;
        }
        if (Long.TYPE == cls) {
            return Long.class;
        }
        return Short.TYPE == cls ? Short.class : cls;
    }
}
