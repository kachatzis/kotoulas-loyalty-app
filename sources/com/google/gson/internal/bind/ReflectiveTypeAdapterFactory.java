package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;

    static abstract class BoundField {
        final boolean deserialized;
        final String name;
        final boolean serialized;

        abstract void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        abstract void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

        abstract boolean writeField(Object obj) throws IOException, IllegalAccessException;

        protected BoundField(String str, boolean z, boolean z2) {
            this.name = str;
            this.serialized = z;
            this.deserialized = z2;
        }
    }

    public static final class Adapter<T> extends TypeAdapter<T> {
        private final Map<String, BoundField> boundFields;
        private final ObjectConstructor<T> constructor;

        public T read(com.google.gson.stream.JsonReader r4) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:25:0x0049 in {3, 13, 14, 16, 18, 21, 24} preds:[]
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
            r0 = r4.peek();
            r1 = com.google.gson.stream.JsonToken.NULL;
            if (r0 != r1) goto L_0x000d;
        L_0x0008:
            r4.nextNull();
            r4 = 0;
            return r4;
        L_0x000d:
            r0 = r3.constructor;
            r0 = r0.construct();
            r4.beginObject();	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
        L_0x0016:
            r1 = r4.hasNext();	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
            if (r1 == 0) goto L_0x0037;	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
        L_0x001c:
            r1 = r4.nextName();	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
            r2 = r3.boundFields;	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
            r1 = r2.get(r1);	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
            r1 = (com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField) r1;	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
            if (r1 == 0) goto L_0x0033;	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
        L_0x002a:
            r2 = r1.deserialized;	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
            if (r2 != 0) goto L_0x002f;	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
        L_0x002e:
            goto L_0x0033;	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
        L_0x002f:
            r1.read(r4, r0);	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
            goto L_0x0016;	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
        L_0x0033:
            r4.skipValue();	 Catch:{ IllegalStateException -> 0x0042, IllegalAccessException -> 0x003b }
            goto L_0x0016;
        L_0x0037:
            r4.endObject();
            return r0;
        L_0x003b:
            r4 = move-exception;
            r0 = new java.lang.AssertionError;
            r0.<init>(r4);
            throw r0;
        L_0x0042:
            r4 = move-exception;
            r0 = new com.google.gson.JsonSyntaxException;
            r0.<init>(r4);
            throw r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter.read(com.google.gson.stream.JsonReader):T");
        }

        public void write(com.google.gson.stream.JsonWriter r4, T r5) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:17:0x0039 in {2, 11, 13, 16} preds:[]
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
            if (r5 != 0) goto L_0x0006;
        L_0x0002:
            r4.nullValue();
            return;
        L_0x0006:
            r4.beginObject();
            r0 = r3.boundFields;	 Catch:{ IllegalAccessException -> 0x0032 }
            r0 = r0.values();	 Catch:{ IllegalAccessException -> 0x0032 }
            r0 = r0.iterator();	 Catch:{ IllegalAccessException -> 0x0032 }
        L_0x0013:
            r1 = r0.hasNext();	 Catch:{ IllegalAccessException -> 0x0032 }
            if (r1 == 0) goto L_0x002e;	 Catch:{ IllegalAccessException -> 0x0032 }
        L_0x0019:
            r1 = r0.next();	 Catch:{ IllegalAccessException -> 0x0032 }
            r1 = (com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField) r1;	 Catch:{ IllegalAccessException -> 0x0032 }
            r2 = r1.writeField(r5);	 Catch:{ IllegalAccessException -> 0x0032 }
            if (r2 == 0) goto L_0x0013;	 Catch:{ IllegalAccessException -> 0x0032 }
        L_0x0025:
            r2 = r1.name;	 Catch:{ IllegalAccessException -> 0x0032 }
            r4.name(r2);	 Catch:{ IllegalAccessException -> 0x0032 }
            r1.write(r4, r5);	 Catch:{ IllegalAccessException -> 0x0032 }
            goto L_0x0013;
        L_0x002e:
            r4.endObject();
            return;
        L_0x0032:
            r4 = move-exception;
            r5 = new java.lang.AssertionError;
            r5.<init>(r4);
            throw r5;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter.write(com.google.gson.stream.JsonWriter, java.lang.Object):void");
        }

        Adapter(ObjectConstructor<T> objectConstructor, Map<String, BoundField> map) {
            this.constructor = objectConstructor;
            this.boundFields = map;
        }
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingStrategy, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingStrategy;
        this.excluder = excluder;
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory;
    }

    public boolean excludeField(Field field, boolean z) {
        return excludeField(field, z, this.excluder);
    }

    static boolean excludeField(Field field, boolean z, Excluder excluder) {
        return (excluder.excludeClass(field.getType(), z) || excluder.excludeField(field, z) != null) ? null : true;
    }

    private List<String> getFieldNames(Field field) {
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        if (serializedName == null) {
            return Collections.singletonList(this.fieldNamingPolicy.translateName(field));
        }
        field = serializedName.value();
        String[] alternate = serializedName.alternate();
        if (alternate.length == 0) {
            return Collections.singletonList(field);
        }
        List<String> arrayList = new ArrayList(alternate.length + 1);
        arrayList.add(field);
        for (Object add : alternate) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class rawType = typeToken.getRawType();
        if (Object.class.isAssignableFrom(rawType)) {
            return new Adapter(this.constructorConstructor.get(typeToken), getBoundFields(gson, typeToken, rawType));
        }
        return null;
    }

    private BoundField createBoundField(Gson gson, Field field, String str, TypeToken<?> typeToken, boolean z, boolean z2) {
        ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory = this;
        Gson gson2 = gson;
        TypeToken typeToken2 = typeToken;
        final boolean isPrimitive = Primitives.isPrimitive(typeToken.getRawType());
        Field field2 = field;
        JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        TypeAdapter typeAdapter = jsonAdapter != null ? reflectiveTypeAdapterFactory.jsonAdapterFactory.getTypeAdapter(reflectiveTypeAdapterFactory.constructorConstructor, gson, typeToken2, jsonAdapter) : null;
        final boolean z3 = typeAdapter != null;
        if (typeAdapter == null) {
            typeAdapter = gson.getAdapter(typeToken2);
        }
        final TypeAdapter typeAdapter2 = typeAdapter;
        field2 = field;
        gson2 = gson;
        final TypeToken<?> typeToken3 = typeToken;
        return new BoundField(str, z, z2) {
            void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException {
                TypeAdapter typeAdapter;
                obj = field2.get(obj);
                if (z3) {
                    typeAdapter = typeAdapter2;
                } else {
                    typeAdapter = new TypeAdapterRuntimeTypeWrapper(gson2, typeAdapter2, typeToken3.getType());
                }
                typeAdapter.write(jsonWriter, obj);
            }

            void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException {
                jsonReader = typeAdapter2.read(jsonReader);
                if (jsonReader != null || !isPrimitive) {
                    field2.set(obj, jsonReader);
                }
            }

            public boolean writeField(Object obj) throws IOException, IllegalAccessException {
                boolean z = false;
                if (!this.serialized) {
                    return false;
                }
                if (field2.get(obj) != obj) {
                    z = true;
                }
                return z;
            }
        };
    }

    private Map<String, BoundField> getBoundFields(Gson gson, TypeToken<?> typeToken, Class<?> cls) {
        ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory = this;
        Map<String, BoundField> linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type type = typeToken.getType();
        TypeToken typeToken2 = typeToken;
        Class cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean excludeField = excludeField(field, true);
                boolean excludeField2 = excludeField(field, z);
                if (excludeField || excludeField2) {
                    BoundField boundField;
                    field.setAccessible(true);
                    Type resolve = C$Gson$Types.resolve(typeToken2.getType(), cls2, field.getGenericType());
                    List fieldNames = getFieldNames(field);
                    int size = fieldNames.size();
                    BoundField boundField2 = null;
                    int i2 = 0;
                    while (i2 < size) {
                        String str = (String) fieldNames.get(i2);
                        boolean z2 = i2 != 0 ? false : excludeField;
                        String str2 = str;
                        boundField = boundField2;
                        int i3 = i2;
                        int i4 = size;
                        List list = fieldNames;
                        Field field2 = field;
                        boundField2 = boundField == null ? (BoundField) linkedHashMap.put(str2, createBoundField(gson, field, str2, TypeToken.get(resolve), z2, excludeField2)) : boundField;
                        i2 = i3 + 1;
                        excludeField = z2;
                        fieldNames = list;
                        size = i4;
                        field = field2;
                    }
                    boundField = boundField2;
                    if (boundField != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(type);
                        stringBuilder.append(" declares multiple JSON fields named ");
                        stringBuilder.append(boundField.name);
                        throw new IllegalArgumentException(stringBuilder.toString());
                    }
                }
                i++;
                z = false;
            }
            typeToken2 = TypeToken.get(C$Gson$Types.resolve(typeToken2.getType(), cls2, cls2.getGenericSuperclass()));
            cls2 = typeToken2.getRawType();
        }
        return linkedHashMap;
    }
}
