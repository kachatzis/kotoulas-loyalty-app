package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public final class Gson {
    static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
    static final boolean DEFAULT_ESCAPE_HTML = true;
    static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
    static final boolean DEFAULT_LENIENT = false;
    static final boolean DEFAULT_PRETTY_PRINT = false;
    static final boolean DEFAULT_SERIALIZE_NULLS = false;
    static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
    private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
    private static final TypeToken<?> NULL_KEY_SURROGATE = TypeToken.get(Object.class);
    private final ThreadLocal<Map<TypeToken<?>, FutureTypeAdapter<?>>> calls;
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final List<TypeAdapterFactory> factories;
    private final FieldNamingStrategy fieldNamingStrategy;
    private final boolean generateNonExecutableJson;
    private final boolean htmlSafe;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private final boolean lenient;
    private final boolean prettyPrinting;
    private final boolean serializeNulls;
    private final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache;

    /* renamed from: com.google.gson.Gson$1 */
    class C05531 extends TypeAdapter<Number> {
        C05531() {
        }

        public Double read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Double.valueOf(jsonReader.nextDouble());
            }
            jsonReader.nextNull();
            return null;
        }

        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
                return;
            }
            Gson.checkValidFloatingPoint(number.doubleValue());
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.Gson$2 */
    class C05542 extends TypeAdapter<Number> {
        C05542() {
        }

        public Float read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Float.valueOf((float) jsonReader.nextDouble());
            }
            jsonReader.nextNull();
            return null;
        }

        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
                return;
            }
            Gson.checkValidFloatingPoint((double) number.floatValue());
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.Gson$3 */
    static class C05553 extends TypeAdapter<Number> {
        C05553() {
        }

        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Long.valueOf(jsonReader.nextLong());
            }
            jsonReader.nextNull();
            return null;
        }

        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(number.toString());
            }
        }
    }

    static class FutureTypeAdapter<T> extends TypeAdapter<T> {
        private TypeAdapter<T> delegate;

        FutureTypeAdapter() {
        }

        public void setDelegate(TypeAdapter<T> typeAdapter) {
            if (this.delegate == null) {
                this.delegate = typeAdapter;
                return;
            }
            throw new AssertionError();
        }

        public T read(JsonReader jsonReader) throws IOException {
            TypeAdapter typeAdapter = this.delegate;
            if (typeAdapter != null) {
                return typeAdapter.read(jsonReader);
            }
            throw new IllegalStateException();
        }

        public void write(JsonWriter jsonWriter, T t) throws IOException {
            TypeAdapter typeAdapter = this.delegate;
            if (typeAdapter != null) {
                typeAdapter.write(jsonWriter, t);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public <T> com.google.gson.TypeAdapter<T> getAdapter(com.google.gson.reflect.TypeToken<T> r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:32:0x0086 in {2, 3, 6, 9, 12, 22, 23, 26, 30, 31} preds:[]
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
        r5 = this;
        r0 = r5.typeTokenCache;
        if (r6 != 0) goto L_0x0007;
    L_0x0004:
        r1 = NULL_KEY_SURROGATE;
        goto L_0x0008;
    L_0x0007:
        r1 = r6;
    L_0x0008:
        r0 = r0.get(r1);
        r0 = (com.google.gson.TypeAdapter) r0;
        if (r0 == 0) goto L_0x0011;
    L_0x0010:
        return r0;
    L_0x0011:
        r0 = r5.calls;
        r0 = r0.get();
        r0 = (java.util.Map) r0;
        r1 = 0;
        if (r0 != 0) goto L_0x0027;
    L_0x001c:
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = r5.calls;
        r1.set(r0);
        r1 = 1;
    L_0x0027:
        r2 = r0.get(r6);
        r2 = (com.google.gson.Gson.FutureTypeAdapter) r2;
        if (r2 == 0) goto L_0x0030;
    L_0x002f:
        return r2;
    L_0x0030:
        r2 = new com.google.gson.Gson$FutureTypeAdapter;	 Catch:{ all -> 0x007a }
        r2.<init>();	 Catch:{ all -> 0x007a }
        r0.put(r6, r2);	 Catch:{ all -> 0x007a }
        r3 = r5.factories;	 Catch:{ all -> 0x007a }
        r3 = r3.iterator();	 Catch:{ all -> 0x007a }
    L_0x003e:
        r4 = r3.hasNext();	 Catch:{ all -> 0x007a }
        if (r4 == 0) goto L_0x0063;	 Catch:{ all -> 0x007a }
    L_0x0044:
        r4 = r3.next();	 Catch:{ all -> 0x007a }
        r4 = (com.google.gson.TypeAdapterFactory) r4;	 Catch:{ all -> 0x007a }
        r4 = r4.create(r5, r6);	 Catch:{ all -> 0x007a }
        if (r4 == 0) goto L_0x003e;	 Catch:{ all -> 0x007a }
    L_0x0050:
        r2.setDelegate(r4);	 Catch:{ all -> 0x007a }
        r2 = r5.typeTokenCache;	 Catch:{ all -> 0x007a }
        r2.put(r6, r4);	 Catch:{ all -> 0x007a }
        r0.remove(r6);
        if (r1 == 0) goto L_0x0062;
    L_0x005d:
        r6 = r5.calls;
        r6.remove();
    L_0x0062:
        return r4;
    L_0x0063:
        r2 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x007a }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007a }
        r3.<init>();	 Catch:{ all -> 0x007a }
        r4 = "GSON cannot handle ";	 Catch:{ all -> 0x007a }
        r3.append(r4);	 Catch:{ all -> 0x007a }
        r3.append(r6);	 Catch:{ all -> 0x007a }
        r3 = r3.toString();	 Catch:{ all -> 0x007a }
        r2.<init>(r3);	 Catch:{ all -> 0x007a }
        throw r2;	 Catch:{ all -> 0x007a }
    L_0x007a:
        r2 = move-exception;
        r0.remove(r6);
        if (r1 == 0) goto L_0x0085;
    L_0x0080:
        r6 = r5.calls;
        r6.remove();
    L_0x0085:
        throw r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.Gson.getAdapter(com.google.gson.reflect.TypeToken):com.google.gson.TypeAdapter<T>");
    }

    public <T> com.google.gson.TypeAdapter<T> getDelegateAdapter(com.google.gson.TypeAdapterFactory r4, com.google.gson.reflect.TypeToken<T> r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:15:0x0041 in {2, 9, 12, 14} preds:[]
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
        r3 = this;
        r0 = r3.factories;
        r0 = r0.contains(r4);
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        r4 = r3.jsonAdapterFactory;
    L_0x000a:
        r0 = 0;
        r1 = r3.factories;
        r1 = r1.iterator();
    L_0x0011:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x002a;
    L_0x0017:
        r2 = r1.next();
        r2 = (com.google.gson.TypeAdapterFactory) r2;
        if (r0 != 0) goto L_0x0023;
    L_0x001f:
        if (r2 != r4) goto L_0x0011;
    L_0x0021:
        r0 = 1;
        goto L_0x0011;
    L_0x0023:
        r2 = r2.create(r3, r5);
        if (r2 == 0) goto L_0x0011;
    L_0x0029:
        return r2;
    L_0x002a:
        r4 = new java.lang.IllegalArgumentException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "GSON cannot serialize ";
        r0.append(r1);
        r0.append(r5);
        r5 = r0.toString();
        r4.<init>(r5);
        throw r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.Gson.getDelegateAdapter(com.google.gson.TypeAdapterFactory, com.google.gson.reflect.TypeToken):com.google.gson.TypeAdapter<T>");
    }

    public Gson() {
        this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, DEFAULT_ESCAPE_HTML, false, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    Gson(Excluder excluder, FieldNamingStrategy fieldNamingStrategy, Map<Type, InstanceCreator<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, LongSerializationPolicy longSerializationPolicy, List<TypeAdapterFactory> list) {
        this.calls = new ThreadLocal();
        this.typeTokenCache = new ConcurrentHashMap();
        this.constructorConstructor = new ConstructorConstructor(map);
        this.excluder = excluder;
        this.fieldNamingStrategy = fieldNamingStrategy;
        this.serializeNulls = z;
        this.generateNonExecutableJson = z3;
        this.htmlSafe = z4;
        this.prettyPrinting = z5;
        this.lenient = z6;
        map = new ArrayList();
        map.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        map.add(ObjectTypeAdapter.FACTORY);
        map.add(excluder);
        map.addAll(list);
        map.add(TypeAdapters.STRING_FACTORY);
        map.add(TypeAdapters.INTEGER_FACTORY);
        map.add(TypeAdapters.BOOLEAN_FACTORY);
        map.add(TypeAdapters.BYTE_FACTORY);
        map.add(TypeAdapters.SHORT_FACTORY);
        z = longAdapter(longSerializationPolicy);
        map.add(TypeAdapters.newFactory(Long.TYPE, Long.class, z));
        map.add(TypeAdapters.newFactory(Double.TYPE, Double.class, doubleAdapter(z7)));
        map.add(TypeAdapters.newFactory(Float.TYPE, Float.class, floatAdapter(z7)));
        map.add(TypeAdapters.NUMBER_FACTORY);
        map.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
        map.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
        map.add(TypeAdapters.newFactory(AtomicLong.class, atomicLongAdapter(z)));
        map.add(TypeAdapters.newFactory(AtomicLongArray.class, atomicLongArrayAdapter(z)));
        map.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
        map.add(TypeAdapters.CHARACTER_FACTORY);
        map.add(TypeAdapters.STRING_BUILDER_FACTORY);
        map.add(TypeAdapters.STRING_BUFFER_FACTORY);
        map.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
        map.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
        map.add(TypeAdapters.URL_FACTORY);
        map.add(TypeAdapters.URI_FACTORY);
        map.add(TypeAdapters.UUID_FACTORY);
        map.add(TypeAdapters.CURRENCY_FACTORY);
        map.add(TypeAdapters.LOCALE_FACTORY);
        map.add(TypeAdapters.INET_ADDRESS_FACTORY);
        map.add(TypeAdapters.BIT_SET_FACTORY);
        map.add(DateTypeAdapter.FACTORY);
        map.add(TypeAdapters.CALENDAR_FACTORY);
        map.add(TimeTypeAdapter.FACTORY);
        map.add(SqlDateTypeAdapter.FACTORY);
        map.add(TypeAdapters.TIMESTAMP_FACTORY);
        map.add(ArrayTypeAdapter.FACTORY);
        map.add(TypeAdapters.CLASS_FACTORY);
        map.add(new CollectionTypeAdapterFactory(this.constructorConstructor));
        map.add(new MapTypeAdapterFactory(this.constructorConstructor, z2));
        this.jsonAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(this.constructorConstructor);
        map.add(this.jsonAdapterFactory);
        map.add(TypeAdapters.ENUM_FACTORY);
        map.add(new ReflectiveTypeAdapterFactory(this.constructorConstructor, fieldNamingStrategy, excluder, this.jsonAdapterFactory));
        this.factories = Collections.unmodifiableList(map);
    }

    public Excluder excluder() {
        return this.excluder;
    }

    public FieldNamingStrategy fieldNamingStrategy() {
        return this.fieldNamingStrategy;
    }

    public boolean serializeNulls() {
        return this.serializeNulls;
    }

    public boolean htmlSafe() {
        return this.htmlSafe;
    }

    private TypeAdapter<Number> doubleAdapter(boolean z) {
        if (z) {
            return TypeAdapters.DOUBLE;
        }
        return new C05531();
    }

    private TypeAdapter<Number> floatAdapter(boolean z) {
        if (z) {
            return TypeAdapters.FLOAT;
        }
        return new C05542();
    }

    static void checkValidFloatingPoint(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(d);
            stringBuilder.append(" is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private static TypeAdapter<Number> longAdapter(LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == LongSerializationPolicy.DEFAULT) {
            return TypeAdapters.LONG;
        }
        return new C05553();
    }

    private static TypeAdapter<AtomicLong> atomicLongAdapter(final TypeAdapter<Number> typeAdapter) {
        return new TypeAdapter<AtomicLong>() {
            public void write(JsonWriter jsonWriter, AtomicLong atomicLong) throws IOException {
                typeAdapter.write(jsonWriter, Long.valueOf(atomicLong.get()));
            }

            public AtomicLong read(JsonReader jsonReader) throws IOException {
                return new AtomicLong(((Number) typeAdapter.read(jsonReader)).longValue());
            }
        }.nullSafe();
    }

    private static TypeAdapter<AtomicLongArray> atomicLongArrayAdapter(final TypeAdapter<Number> typeAdapter) {
        return new TypeAdapter<AtomicLongArray>() {
            public void write(JsonWriter jsonWriter, AtomicLongArray atomicLongArray) throws IOException {
                jsonWriter.beginArray();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    typeAdapter.write(jsonWriter, Long.valueOf(atomicLongArray.get(i)));
                }
                jsonWriter.endArray();
            }

            public AtomicLongArray read(JsonReader jsonReader) throws IOException {
                List arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(Long.valueOf(((Number) typeAdapter.read(jsonReader)).longValue()));
                }
                jsonReader.endArray();
                jsonReader = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(jsonReader);
                for (int i = 0; i < jsonReader; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }
        }.nullSafe();
    }

    public <T> TypeAdapter<T> getAdapter(Class<T> cls) {
        return getAdapter(TypeToken.get((Class) cls));
    }

    public JsonElement toJsonTree(Object obj) {
        if (obj == null) {
            return JsonNull.INSTANCE;
        }
        return toJsonTree(obj, obj.getClass());
    }

    public JsonElement toJsonTree(Object obj, Type type) {
        JsonWriter jsonTreeWriter = new JsonTreeWriter();
        toJson(obj, type, jsonTreeWriter);
        return jsonTreeWriter.get();
    }

    public String toJson(Object obj) {
        if (obj == null) {
            return toJson(JsonNull.INSTANCE);
        }
        return toJson(obj, obj.getClass());
    }

    public String toJson(Object obj, Type type) {
        Appendable stringWriter = new StringWriter();
        toJson(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void toJson(Object obj, Appendable appendable) throws JsonIOException {
        if (obj != null) {
            toJson(obj, obj.getClass(), appendable);
        } else {
            toJson(JsonNull.INSTANCE, appendable);
        }
    }

    public void toJson(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            toJson(obj, type, newJsonWriter(Streams.writerForAppendable(appendable)));
        } catch (Throwable e) {
            throw new JsonIOException(e);
        }
    }

    public void toJson(Object obj, Type type, JsonWriter jsonWriter) throws JsonIOException {
        type = getAdapter(TypeToken.get(type));
        boolean isLenient = jsonWriter.isLenient();
        jsonWriter.setLenient(DEFAULT_ESCAPE_HTML);
        boolean isHtmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.htmlSafe);
        boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.serializeNulls);
        try {
            type.write(jsonWriter, obj);
            jsonWriter.setLenient(isLenient);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        } catch (Throwable e) {
            throw new JsonIOException(e);
        } catch (Throwable th) {
            jsonWriter.setLenient(isLenient);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        }
    }

    public String toJson(JsonElement jsonElement) {
        Appendable stringWriter = new StringWriter();
        toJson(jsonElement, stringWriter);
        return stringWriter.toString();
    }

    public void toJson(JsonElement jsonElement, Appendable appendable) throws JsonIOException {
        try {
            toJson(jsonElement, newJsonWriter(Streams.writerForAppendable(appendable)));
        } catch (Throwable e) {
            throw new JsonIOException(e);
        }
    }

    public JsonWriter newJsonWriter(Writer writer) throws IOException {
        if (this.generateNonExecutableJson) {
            writer.write(JSON_NON_EXECUTABLE_PREFIX);
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        if (this.prettyPrinting != null) {
            jsonWriter.setIndent("  ");
        }
        jsonWriter.setSerializeNulls(this.serializeNulls);
        return jsonWriter;
    }

    public JsonReader newJsonReader(Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(this.lenient);
        return jsonReader;
    }

    public void toJson(JsonElement jsonElement, JsonWriter jsonWriter) throws JsonIOException {
        boolean isLenient = jsonWriter.isLenient();
        jsonWriter.setLenient(DEFAULT_ESCAPE_HTML);
        boolean isHtmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.htmlSafe);
        boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.serializeNulls);
        try {
            Streams.write(jsonElement, jsonWriter);
            jsonWriter.setLenient(isLenient);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        } catch (Throwable e) {
            throw new JsonIOException(e);
        } catch (Throwable th) {
            jsonWriter.setLenient(isLenient);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        }
    }

    public <T> T fromJson(String str, Class<T> cls) throws JsonSyntaxException {
        return Primitives.wrap(cls).cast(fromJson(str, (Type) cls));
    }

    public <T> T fromJson(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return fromJson(new StringReader(str), type);
    }

    public <T> T fromJson(Reader reader, Class<T> cls) throws JsonSyntaxException, JsonIOException {
        JsonReader newJsonReader = newJsonReader(reader);
        Object fromJson = fromJson(newJsonReader, (Type) cls);
        assertFullConsumption(fromJson, newJsonReader);
        return Primitives.wrap(cls).cast(fromJson);
    }

    public <T> T fromJson(Reader reader, Type type) throws JsonIOException, JsonSyntaxException {
        JsonReader newJsonReader = newJsonReader(reader);
        type = fromJson(newJsonReader, type);
        assertFullConsumption(type, newJsonReader);
        return type;
    }

    private static void assertFullConsumption(Object obj, JsonReader jsonReader) {
        if (obj != null) {
            try {
                if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            } catch (Throwable e2) {
                throw new JsonIOException(e2);
            }
        }
    }

    public <T> T fromJson(JsonReader jsonReader, Type type) throws JsonIOException, JsonSyntaxException {
        boolean isLenient = jsonReader.isLenient();
        boolean z = DEFAULT_ESCAPE_HTML;
        jsonReader.setLenient(DEFAULT_ESCAPE_HTML);
        try {
            jsonReader.peek();
            z = false;
            type = getAdapter(TypeToken.get(type)).read(jsonReader);
            jsonReader.setLenient(isLenient);
            return type;
        } catch (Throwable e) {
            if (z) {
                jsonReader.setLenient(isLenient);
                return null;
            }
            throw new JsonSyntaxException(e);
        } catch (Throwable e2) {
            throw new JsonSyntaxException(e2);
        } catch (Throwable e22) {
            throw new JsonSyntaxException(e22);
        } catch (Throwable th) {
            jsonReader.setLenient(isLenient);
        }
    }

    public <T> T fromJson(JsonElement jsonElement, Class<T> cls) throws JsonSyntaxException {
        return Primitives.wrap(cls).cast(fromJson(jsonElement, (Type) cls));
    }

    public <T> T fromJson(JsonElement jsonElement, Type type) throws JsonSyntaxException {
        return jsonElement == null ? null : fromJson(new JsonTreeReader(jsonElement), type);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{serializeNulls:");
        stringBuilder.append(this.serializeNulls);
        stringBuilder.append(",factories:");
        stringBuilder.append(this.factories);
        stringBuilder.append(",instanceCreators:");
        stringBuilder.append(this.constructorConstructor);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
