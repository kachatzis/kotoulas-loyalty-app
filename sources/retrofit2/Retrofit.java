package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.CallAdapter.Factory;

public final class Retrofit {
    final List<Factory> adapterFactories;
    final HttpUrl baseUrl;
    final Call.Factory callFactory;
    @Nullable
    final Executor callbackExecutor;
    final List<Converter.Factory> converterFactories;
    private final Map<Method, ServiceMethod<?, ?>> serviceMethodCache = new ConcurrentHashMap();
    final boolean validateEagerly;

    public static final class Builder {
        private final List<Factory> adapterFactories;
        private HttpUrl baseUrl;
        @Nullable
        private Call.Factory callFactory;
        @Nullable
        private Executor callbackExecutor;
        private final List<Converter.Factory> converterFactories;
        private final Platform platform;
        private boolean validateEagerly;

        Builder(Platform platform) {
            this.converterFactories = new ArrayList();
            this.adapterFactories = new ArrayList();
            this.platform = platform;
            this.converterFactories.add(new BuiltInConverters());
        }

        public Builder() {
            this(Platform.get());
        }

        Builder(Retrofit retrofit) {
            this.converterFactories = new ArrayList();
            this.adapterFactories = new ArrayList();
            this.platform = Platform.get();
            this.callFactory = retrofit.callFactory;
            this.baseUrl = retrofit.baseUrl;
            this.converterFactories.addAll(retrofit.converterFactories);
            this.adapterFactories.addAll(retrofit.adapterFactories);
            List list = this.adapterFactories;
            list.remove(list.size() - 1);
            this.callbackExecutor = retrofit.callbackExecutor;
            this.validateEagerly = retrofit.validateEagerly;
        }

        public Builder client(OkHttpClient okHttpClient) {
            return callFactory((Call.Factory) Utils.checkNotNull(okHttpClient, "client == null"));
        }

        public Builder callFactory(Call.Factory factory) {
            this.callFactory = (Call.Factory) Utils.checkNotNull(factory, "factory == null");
            return this;
        }

        public Builder baseUrl(String str) {
            Utils.checkNotNull(str, "baseUrl == null");
            HttpUrl parse = HttpUrl.parse(str);
            if (parse != null) {
                return baseUrl(parse);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Illegal URL: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }

        public Builder baseUrl(HttpUrl httpUrl) {
            Utils.checkNotNull(httpUrl, "baseUrl == null");
            List pathSegments = httpUrl.pathSegments();
            if ("".equals(pathSegments.get(pathSegments.size() - 1))) {
                this.baseUrl = httpUrl;
                return this;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("baseUrl must end in /: ");
            stringBuilder.append(httpUrl);
            throw new IllegalArgumentException(stringBuilder.toString());
        }

        public Builder addConverterFactory(Converter.Factory factory) {
            this.converterFactories.add(Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Builder addCallAdapterFactory(Factory factory) {
            this.adapterFactories.add(Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Builder callbackExecutor(Executor executor) {
            this.callbackExecutor = (Executor) Utils.checkNotNull(executor, "executor == null");
            return this;
        }

        public Builder validateEagerly(boolean z) {
            this.validateEagerly = z;
            return this;
        }

        public Retrofit build() {
            if (this.baseUrl != null) {
                Call.Factory factory = this.callFactory;
                Call.Factory okHttpClient = factory == null ? new OkHttpClient() : factory;
                Executor executor = this.callbackExecutor;
                Executor defaultCallbackExecutor = executor == null ? this.platform.defaultCallbackExecutor() : executor;
                List arrayList = new ArrayList(this.adapterFactories);
                arrayList.add(this.platform.defaultCallAdapterFactory(defaultCallbackExecutor));
                return new Retrofit(okHttpClient, this.baseUrl, new ArrayList(this.converterFactories), arrayList, defaultCallbackExecutor, this.validateEagerly);
            }
            throw new IllegalStateException("Base URL required.");
        }
    }

    public retrofit2.CallAdapter<?, ?> nextCallAdapter(@javax.annotation.Nullable retrofit2.CallAdapter.Factory r5, java.lang.reflect.Type r6, java.lang.annotation.Annotation[] r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:17:0x0098 in {4, 5, 10, 11, 14, 16} preds:[]
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
        r0 = "returnType == null";
        retrofit2.Utils.checkNotNull(r6, r0);
        r0 = "annotations == null";
        retrofit2.Utils.checkNotNull(r7, r0);
        r0 = r4.adapterFactories;
        r0 = r0.indexOf(r5);
        r0 = r0 + 1;
        r1 = r4.adapterFactories;
        r1 = r1.size();
        r2 = r0;
    L_0x0019:
        if (r2 >= r1) goto L_0x002d;
    L_0x001b:
        r3 = r4.adapterFactories;
        r3 = r3.get(r2);
        r3 = (retrofit2.CallAdapter.Factory) r3;
        r3 = r3.get(r6, r7, r4);
        if (r3 == 0) goto L_0x002a;
    L_0x0029:
        return r3;
    L_0x002a:
        r2 = r2 + 1;
        goto L_0x0019;
    L_0x002d:
        r7 = new java.lang.StringBuilder;
        r1 = "Could not locate call adapter for ";
        r7.<init>(r1);
        r7.append(r6);
        r6 = ".\n";
        r7.append(r6);
        if (r5 == 0) goto L_0x0066;
    L_0x003e:
        r5 = "  Skipped:";
        r7.append(r5);
        r5 = 0;
    L_0x0044:
        if (r5 >= r0) goto L_0x0061;
    L_0x0046:
        r6 = "\n   * ";
        r7.append(r6);
        r6 = r4.adapterFactories;
        r6 = r6.get(r5);
        r6 = (retrofit2.CallAdapter.Factory) r6;
        r6 = r6.getClass();
        r6 = r6.getName();
        r7.append(r6);
        r5 = r5 + 1;
        goto L_0x0044;
    L_0x0061:
        r5 = 10;
        r7.append(r5);
    L_0x0066:
        r5 = "  Tried:";
        r7.append(r5);
        r5 = r4.adapterFactories;
        r5 = r5.size();
    L_0x0071:
        if (r0 >= r5) goto L_0x008e;
    L_0x0073:
        r6 = "\n   * ";
        r7.append(r6);
        r6 = r4.adapterFactories;
        r6 = r6.get(r0);
        r6 = (retrofit2.CallAdapter.Factory) r6;
        r6 = r6.getClass();
        r6 = r6.getName();
        r7.append(r6);
        r0 = r0 + 1;
        goto L_0x0071;
    L_0x008e:
        r5 = new java.lang.IllegalArgumentException;
        r6 = r7.toString();
        r5.<init>(r6);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.Retrofit.nextCallAdapter(retrofit2.CallAdapter$Factory, java.lang.reflect.Type, java.lang.annotation.Annotation[]):retrofit2.CallAdapter<?, ?>");
    }

    public <T> retrofit2.Converter<T, okhttp3.RequestBody> nextRequestBodyConverter(@javax.annotation.Nullable retrofit2.Converter.Factory r5, java.lang.reflect.Type r6, java.lang.annotation.Annotation[] r7, java.lang.annotation.Annotation[] r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:17:0x009d in {4, 5, 10, 11, 14, 16} preds:[]
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
        r0 = "type == null";
        retrofit2.Utils.checkNotNull(r6, r0);
        r0 = "parameterAnnotations == null";
        retrofit2.Utils.checkNotNull(r7, r0);
        r0 = "methodAnnotations == null";
        retrofit2.Utils.checkNotNull(r8, r0);
        r0 = r4.converterFactories;
        r0 = r0.indexOf(r5);
        r0 = r0 + 1;
        r1 = r4.converterFactories;
        r1 = r1.size();
        r2 = r0;
    L_0x001e:
        if (r2 >= r1) goto L_0x0032;
    L_0x0020:
        r3 = r4.converterFactories;
        r3 = r3.get(r2);
        r3 = (retrofit2.Converter.Factory) r3;
        r3 = r3.requestBodyConverter(r6, r7, r8, r4);
        if (r3 == 0) goto L_0x002f;
    L_0x002e:
        return r3;
    L_0x002f:
        r2 = r2 + 1;
        goto L_0x001e;
    L_0x0032:
        r7 = new java.lang.StringBuilder;
        r8 = "Could not locate RequestBody converter for ";
        r7.<init>(r8);
        r7.append(r6);
        r6 = ".\n";
        r7.append(r6);
        if (r5 == 0) goto L_0x006b;
    L_0x0043:
        r5 = "  Skipped:";
        r7.append(r5);
        r5 = 0;
    L_0x0049:
        if (r5 >= r0) goto L_0x0066;
    L_0x004b:
        r6 = "\n   * ";
        r7.append(r6);
        r6 = r4.converterFactories;
        r6 = r6.get(r5);
        r6 = (retrofit2.Converter.Factory) r6;
        r6 = r6.getClass();
        r6 = r6.getName();
        r7.append(r6);
        r5 = r5 + 1;
        goto L_0x0049;
    L_0x0066:
        r5 = 10;
        r7.append(r5);
    L_0x006b:
        r5 = "  Tried:";
        r7.append(r5);
        r5 = r4.converterFactories;
        r5 = r5.size();
    L_0x0076:
        if (r0 >= r5) goto L_0x0093;
    L_0x0078:
        r6 = "\n   * ";
        r7.append(r6);
        r6 = r4.converterFactories;
        r6 = r6.get(r0);
        r6 = (retrofit2.Converter.Factory) r6;
        r6 = r6.getClass();
        r6 = r6.getName();
        r7.append(r6);
        r0 = r0 + 1;
        goto L_0x0076;
    L_0x0093:
        r5 = new java.lang.IllegalArgumentException;
        r6 = r7.toString();
        r5.<init>(r6);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.Retrofit.nextRequestBodyConverter(retrofit2.Converter$Factory, java.lang.reflect.Type, java.lang.annotation.Annotation[], java.lang.annotation.Annotation[]):retrofit2.Converter<T, okhttp3.RequestBody>");
    }

    public <T> retrofit2.Converter<okhttp3.ResponseBody, T> nextResponseBodyConverter(@javax.annotation.Nullable retrofit2.Converter.Factory r5, java.lang.reflect.Type r6, java.lang.annotation.Annotation[] r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:17:0x0098 in {4, 5, 10, 11, 14, 16} preds:[]
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
        r0 = "type == null";
        retrofit2.Utils.checkNotNull(r6, r0);
        r0 = "annotations == null";
        retrofit2.Utils.checkNotNull(r7, r0);
        r0 = r4.converterFactories;
        r0 = r0.indexOf(r5);
        r0 = r0 + 1;
        r1 = r4.converterFactories;
        r1 = r1.size();
        r2 = r0;
    L_0x0019:
        if (r2 >= r1) goto L_0x002d;
    L_0x001b:
        r3 = r4.converterFactories;
        r3 = r3.get(r2);
        r3 = (retrofit2.Converter.Factory) r3;
        r3 = r3.responseBodyConverter(r6, r7, r4);
        if (r3 == 0) goto L_0x002a;
    L_0x0029:
        return r3;
    L_0x002a:
        r2 = r2 + 1;
        goto L_0x0019;
    L_0x002d:
        r7 = new java.lang.StringBuilder;
        r1 = "Could not locate ResponseBody converter for ";
        r7.<init>(r1);
        r7.append(r6);
        r6 = ".\n";
        r7.append(r6);
        if (r5 == 0) goto L_0x0066;
    L_0x003e:
        r5 = "  Skipped:";
        r7.append(r5);
        r5 = 0;
    L_0x0044:
        if (r5 >= r0) goto L_0x0061;
    L_0x0046:
        r6 = "\n   * ";
        r7.append(r6);
        r6 = r4.converterFactories;
        r6 = r6.get(r5);
        r6 = (retrofit2.Converter.Factory) r6;
        r6 = r6.getClass();
        r6 = r6.getName();
        r7.append(r6);
        r5 = r5 + 1;
        goto L_0x0044;
    L_0x0061:
        r5 = 10;
        r7.append(r5);
    L_0x0066:
        r5 = "  Tried:";
        r7.append(r5);
        r5 = r4.converterFactories;
        r5 = r5.size();
    L_0x0071:
        if (r0 >= r5) goto L_0x008e;
    L_0x0073:
        r6 = "\n   * ";
        r7.append(r6);
        r6 = r4.converterFactories;
        r6 = r6.get(r0);
        r6 = (retrofit2.Converter.Factory) r6;
        r6 = r6.getClass();
        r6 = r6.getName();
        r7.append(r6);
        r0 = r0 + 1;
        goto L_0x0071;
    L_0x008e:
        r5 = new java.lang.IllegalArgumentException;
        r6 = r7.toString();
        r5.<init>(r6);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.Retrofit.nextResponseBodyConverter(retrofit2.Converter$Factory, java.lang.reflect.Type, java.lang.annotation.Annotation[]):retrofit2.Converter<okhttp3.ResponseBody, T>");
    }

    Retrofit(Call.Factory factory, HttpUrl httpUrl, List<Converter.Factory> list, List<Factory> list2, @Nullable Executor executor, boolean z) {
        this.callFactory = factory;
        this.baseUrl = httpUrl;
        this.converterFactories = Collections.unmodifiableList(list);
        this.adapterFactories = Collections.unmodifiableList(list2);
        this.callbackExecutor = executor;
        this.validateEagerly = z;
    }

    public <T> T create(final Class<T> cls) {
        Utils.validateServiceInterface(cls);
        if (this.validateEagerly) {
            eagerlyValidateMethods(cls);
        }
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            private final Platform platform = Platform.get();

            public Object invoke(Object obj, Method method, @Nullable Object[] objArr) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, objArr);
                }
                if (this.platform.isDefaultMethod(method)) {
                    return this.platform.invokeDefaultMethod(method, cls, obj, objArr);
                }
                obj = Retrofit.this.loadServiceMethod(method);
                return obj.callAdapter.adapt(new OkHttpCall(obj, objArr));
            }
        });
    }

    private void eagerlyValidateMethods(Class<?> cls) {
        Platform platform = Platform.get();
        for (Method method : cls.getDeclaredMethods()) {
            if (!platform.isDefaultMethod(method)) {
                loadServiceMethod(method);
            }
        }
    }

    ServiceMethod<?, ?> loadServiceMethod(Method method) {
        ServiceMethod<?, ?> serviceMethod = (ServiceMethod) this.serviceMethodCache.get(method);
        if (serviceMethod != null) {
            return serviceMethod;
        }
        ServiceMethod<?, ?> serviceMethod2;
        synchronized (this.serviceMethodCache) {
            serviceMethod2 = (ServiceMethod) this.serviceMethodCache.get(method);
            if (serviceMethod2 == null) {
                serviceMethod2 = new Builder(this, method).build();
                this.serviceMethodCache.put(method, serviceMethod2);
            }
        }
        return serviceMethod2;
    }

    public Call.Factory callFactory() {
        return this.callFactory;
    }

    public HttpUrl baseUrl() {
        return this.baseUrl;
    }

    public List<Factory> callAdapterFactories() {
        return this.adapterFactories;
    }

    public CallAdapter<?, ?> callAdapter(Type type, Annotation[] annotationArr) {
        return nextCallAdapter(null, type, annotationArr);
    }

    public List<Converter.Factory> converterFactories() {
        return this.converterFactories;
    }

    public <T> Converter<T, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return nextRequestBodyConverter(null, type, annotationArr, annotationArr2);
    }

    public <T> Converter<ResponseBody, T> responseBodyConverter(Type type, Annotation[] annotationArr) {
        return nextResponseBodyConverter(null, type, annotationArr);
    }

    public <T> Converter<T, String> stringConverter(Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int size = this.converterFactories.size();
        for (int i = 0; i < size; i++) {
            Converter<T, String> stringConverter = ((Converter.Factory) this.converterFactories.get(i)).stringConverter(type, annotationArr, this);
            if (stringConverter != null) {
                return stringConverter;
            }
        }
        return ToStringConverter.INSTANCE;
    }

    @Nullable
    public Executor callbackExecutor() {
        return this.callbackExecutor;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }
}
