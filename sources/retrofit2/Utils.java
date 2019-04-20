package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import javax.annotation.Nullable;
import okhttp3.ResponseBody;
import okio.Buffer;

final class Utils {
    static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    private static final class GenericArrayTypeImpl implements GenericArrayType {
        private final Type componentType;

        GenericArrayTypeImpl(Type type) {
            this.componentType = type;
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        public boolean equals(Object obj) {
            return (!(obj instanceof GenericArrayType) || Utils.equals(this, (GenericArrayType) obj) == null) ? null : true;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Utils.typeToString(this.componentType));
            stringBuilder.append("[]");
            return stringBuilder.toString();
        }
    }

    private static final class ParameterizedTypeImpl implements ParameterizedType {
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        ParameterizedTypeImpl(Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                Object obj = 1;
                Object obj2 = type == null ? 1 : null;
                if (((Class) type2).getEnclosingClass() != null) {
                    obj = null;
                }
                if (obj2 != obj) {
                    throw new IllegalArgumentException();
                }
            }
            for (Type type3 : typeArr) {
                Utils.checkNotNull(type3, "typeArgument == null");
                Utils.checkNotPrimitive(type3);
            }
            this.ownerType = type;
            this.rawType = type2;
            this.typeArguments = (Type[]) typeArr.clone();
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        public Type getRawType() {
            return this.rawType;
        }

        public Type getOwnerType() {
            return this.ownerType;
        }

        public boolean equals(Object obj) {
            return (!(obj instanceof ParameterizedType) || Utils.equals(this, (ParameterizedType) obj) == null) ? null : true;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ Utils.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            Type[] typeArr = this.typeArguments;
            if (typeArr.length == 0) {
                return Utils.typeToString(this.rawType);
            }
            int i = 1;
            StringBuilder stringBuilder = new StringBuilder((typeArr.length + 1) * 30);
            stringBuilder.append(Utils.typeToString(this.rawType));
            stringBuilder.append("<");
            stringBuilder.append(Utils.typeToString(this.typeArguments[0]));
            while (i < this.typeArguments.length) {
                stringBuilder.append(", ");
                stringBuilder.append(Utils.typeToString(this.typeArguments[i]));
                i++;
            }
            stringBuilder.append(">");
            return stringBuilder.toString();
        }
    }

    private static final class WildcardTypeImpl implements WildcardType {
        private final Type lowerBound;
        private final Type upperBound;

        WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            if (typeArr2.length > 1) {
                throw new IllegalArgumentException();
            } else if (typeArr.length != 1) {
                throw new IllegalArgumentException();
            } else if (typeArr2.length == 1) {
                if (typeArr2[0] != null) {
                    Utils.checkNotPrimitive(typeArr2[0]);
                    if (typeArr[0] == Object.class) {
                        this.lowerBound = typeArr2[0];
                        this.upperBound = Object.class;
                        return;
                    }
                    throw new IllegalArgumentException();
                }
                throw new NullPointerException();
            } else if (typeArr[0] != null) {
                Utils.checkNotPrimitive(typeArr[0]);
                this.lowerBound = null;
                this.upperBound = typeArr[0];
            } else {
                throw new NullPointerException();
            }
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public Type[] getLowerBounds() {
            if (this.lowerBound == null) {
                return Utils.EMPTY_TYPE_ARRAY;
            }
            return new Type[]{this.lowerBound};
        }

        public boolean equals(Object obj) {
            return (!(obj instanceof WildcardType) || Utils.equals(this, (WildcardType) obj) == null) ? null : true;
        }

        public int hashCode() {
            Type type = this.lowerBound;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public String toString() {
            StringBuilder stringBuilder;
            if (this.lowerBound != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("? super ");
                stringBuilder.append(Utils.typeToString(this.lowerBound));
                return stringBuilder.toString();
            } else if (this.upperBound == Object.class) {
                return "?";
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("? extends ");
                stringBuilder.append(Utils.typeToString(this.upperBound));
                return stringBuilder.toString();
            }
        }
    }

    static boolean hasUnresolvableType(java.lang.reflect.Type r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:27:0x0067 in {2, 9, 10, 11, 15, 18, 21, 23, 24, 26} preds:[]
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
        r0 = r5 instanceof java.lang.Class;
        r1 = 0;
        if (r0 == 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = r5 instanceof java.lang.reflect.ParameterizedType;
        r2 = 1;
        if (r0 == 0) goto L_0x0022;
    L_0x000b:
        r5 = (java.lang.reflect.ParameterizedType) r5;
        r5 = r5.getActualTypeArguments();
        r0 = r5.length;
        r3 = 0;
    L_0x0013:
        if (r3 >= r0) goto L_0x0021;
    L_0x0015:
        r4 = r5[r3];
        r4 = hasUnresolvableType(r4);
        if (r4 == 0) goto L_0x001e;
    L_0x001d:
        return r2;
    L_0x001e:
        r3 = r3 + 1;
        goto L_0x0013;
    L_0x0021:
        return r1;
    L_0x0022:
        r0 = r5 instanceof java.lang.reflect.GenericArrayType;
        if (r0 == 0) goto L_0x0031;
    L_0x0026:
        r5 = (java.lang.reflect.GenericArrayType) r5;
        r5 = r5.getGenericComponentType();
        r5 = hasUnresolvableType(r5);
        return r5;
    L_0x0031:
        r0 = r5 instanceof java.lang.reflect.TypeVariable;
        if (r0 == 0) goto L_0x0036;
    L_0x0035:
        return r2;
    L_0x0036:
        r0 = r5 instanceof java.lang.reflect.WildcardType;
        if (r0 == 0) goto L_0x003b;
    L_0x003a:
        return r2;
    L_0x003b:
        if (r5 != 0) goto L_0x0040;
    L_0x003d:
        r0 = "null";
        goto L_0x0048;
    L_0x0040:
        r0 = r5.getClass();
        r0 = r0.getName();
    L_0x0048:
        r1 = new java.lang.IllegalArgumentException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Expected a Class, ParameterizedType, or GenericArrayType, but <";
        r2.append(r3);
        r2.append(r5);
        r5 = "> is of type ";
        r2.append(r5);
        r2.append(r0);
        r5 = r2.toString();
        r1.<init>(r5);
        throw r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.Utils.hasUnresolvableType(java.lang.reflect.Type):boolean");
    }

    private static int indexOf(java.lang.Object[] r2, java.lang.Object r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:9:0x0016 in {5, 6, 8} preds:[]
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
        r0 = 0;
    L_0x0001:
        r1 = r2.length;
        if (r0 >= r1) goto L_0x0010;
    L_0x0004:
        r1 = r2[r0];
        r1 = r3.equals(r1);
        if (r1 == 0) goto L_0x000d;
    L_0x000c:
        return r0;
    L_0x000d:
        r0 = r0 + 1;
        goto L_0x0001;
    L_0x0010:
        r2 = new java.util.NoSuchElementException;
        r2.<init>();
        throw r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.Utils.indexOf(java.lang.Object[], java.lang.Object):int");
    }

    static java.lang.reflect.Type resolve(java.lang.reflect.Type r8, java.lang.Class<?> r9, java.lang.reflect.Type r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:61:0x00cf in {4, 5, 12, 13, 14, 19, 20, 21, 26, 27, 33, 34, 35, 37, 38, 39, 47, 56, 57, 58, 60} preds:[]
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
    L_0x0000:
        r0 = r10 instanceof java.lang.reflect.TypeVariable;
        if (r0 == 0) goto L_0x000f;
    L_0x0004:
        r10 = (java.lang.reflect.TypeVariable) r10;
        r0 = resolveTypeVariable(r8, r9, r10);
        if (r0 != r10) goto L_0x000d;
    L_0x000c:
        return r0;
    L_0x000d:
        r10 = r0;
        goto L_0x0000;
    L_0x000f:
        r0 = r10 instanceof java.lang.Class;
        if (r0 == 0) goto L_0x002d;
    L_0x0013:
        r0 = r10;
        r0 = (java.lang.Class) r0;
        r1 = r0.isArray();
        if (r1 == 0) goto L_0x002d;
    L_0x001c:
        r10 = r0.getComponentType();
        r8 = resolve(r8, r9, r10);
        if (r10 != r8) goto L_0x0027;
    L_0x0026:
        goto L_0x002c;
    L_0x0027:
        r0 = new retrofit2.Utils$GenericArrayTypeImpl;
        r0.<init>(r8);
    L_0x002c:
        return r0;
    L_0x002d:
        r0 = r10 instanceof java.lang.reflect.GenericArrayType;
        if (r0 == 0) goto L_0x0044;
    L_0x0031:
        r10 = (java.lang.reflect.GenericArrayType) r10;
        r0 = r10.getGenericComponentType();
        r8 = resolve(r8, r9, r0);
        if (r0 != r8) goto L_0x003e;
    L_0x003d:
        goto L_0x0043;
    L_0x003e:
        r10 = new retrofit2.Utils$GenericArrayTypeImpl;
        r10.<init>(r8);
    L_0x0043:
        return r10;
    L_0x0044:
        r0 = r10 instanceof java.lang.reflect.ParameterizedType;
        r1 = 1;
        r2 = 0;
        if (r0 == 0) goto L_0x0087;
    L_0x004a:
        r10 = (java.lang.reflect.ParameterizedType) r10;
        r0 = r10.getOwnerType();
        r3 = resolve(r8, r9, r0);
        if (r3 == r0) goto L_0x0058;
    L_0x0056:
        r0 = 1;
        goto L_0x0059;
    L_0x0058:
        r0 = 0;
    L_0x0059:
        r4 = r10.getActualTypeArguments();
        r5 = r4.length;
    L_0x005e:
        if (r2 >= r5) goto L_0x0079;
    L_0x0060:
        r6 = r4[r2];
        r6 = resolve(r8, r9, r6);
        r7 = r4[r2];
        if (r6 == r7) goto L_0x0076;
    L_0x006a:
        if (r0 != 0) goto L_0x0074;
    L_0x006c:
        r0 = r4.clone();
        r4 = r0;
        r4 = (java.lang.reflect.Type[]) r4;
        r0 = 1;
    L_0x0074:
        r4[r2] = r6;
    L_0x0076:
        r2 = r2 + 1;
        goto L_0x005e;
    L_0x0079:
        if (r0 == 0) goto L_0x0085;
    L_0x007b:
        r8 = new retrofit2.Utils$ParameterizedTypeImpl;
        r9 = r10.getRawType();
        r8.<init>(r3, r9, r4);
        goto L_0x0086;
    L_0x0085:
        r8 = r10;
    L_0x0086:
        return r8;
    L_0x0087:
        r0 = r10 instanceof java.lang.reflect.WildcardType;
        if (r0 == 0) goto L_0x00cc;
    L_0x008b:
        r10 = (java.lang.reflect.WildcardType) r10;
        r0 = r10.getLowerBounds();
        r3 = r10.getUpperBounds();
        r4 = r0.length;
        if (r4 != r1) goto L_0x00b2;
    L_0x0098:
        r3 = r0[r2];
        r8 = resolve(r8, r9, r3);
        r9 = r0[r2];
        if (r8 == r9) goto L_0x00cb;
    L_0x00a2:
        r9 = new retrofit2.Utils$WildcardTypeImpl;
        r10 = new java.lang.reflect.Type[r1];
        r0 = java.lang.Object.class;
        r10[r2] = r0;
        r0 = new java.lang.reflect.Type[r1];
        r0[r2] = r8;
        r9.<init>(r10, r0);
        return r9;
    L_0x00b2:
        r0 = r3.length;
        if (r0 != r1) goto L_0x00cb;
    L_0x00b5:
        r0 = r3[r2];
        r8 = resolve(r8, r9, r0);	 Catch:{ Throwable -> 0x00cd }
        r9 = r3[r2];
        if (r8 == r9) goto L_0x00cb;
    L_0x00bf:
        r9 = new retrofit2.Utils$WildcardTypeImpl;
        r10 = new java.lang.reflect.Type[r1];
        r10[r2] = r8;
        r8 = EMPTY_TYPE_ARRAY;
        r9.<init>(r10, r8);
        return r9;
    L_0x00cb:
        return r10;
    L_0x00cc:
        return r10;
    L_0x00cd:
        r8 = move-exception;
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.Utils.resolve(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type):java.lang.reflect.Type");
    }

    private Utils() {
    }

    static Class<?> getRawType(Type type) {
        checkNotNull(type, "type == null");
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
            if (type instanceof Class) {
                return (Class) type;
            }
            throw new IllegalArgumentException();
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return getRawType(((WildcardType) type).getUpperBounds()[0]);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a Class, ParameterizedType, or GenericArrayType, but <");
            stringBuilder.append(type);
            stringBuilder.append("> is of type ");
            stringBuilder.append(type.getClass().getName());
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    static boolean equals(Type type, Type type2) {
        boolean z = true;
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            if (!equal(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) || !parameterizedType.getRawType().equals(parameterizedType2.getRawType()) || Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments()) == null) {
                z = false;
            }
            return z;
        } else if (type instanceof GenericArrayType) {
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return equals(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
        } else if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            if (!Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) || Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds()) == null) {
                z = false;
            }
            return z;
        } else if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (typeVariable.getGenericDeclaration() != typeVariable2.getGenericDeclaration() || typeVariable.getName().equals(typeVariable2.getName()) == null) {
                z = false;
            }
            return z;
        }
    }

    static Type getGenericSupertype(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface() != null) {
            type = cls.getInterfaces();
            int length = type.length;
            for (int i = 0; i < length; i++) {
                if (type[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(type[i])) {
                    return getGenericSupertype(cls.getGenericInterfaces()[i], type[i], cls2);
                }
            }
        }
        if (cls.isInterface() == null) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return getGenericSupertype(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    private static boolean equal(Object obj, Object obj2) {
        if (obj != obj2) {
            if (obj == null || obj.equals(obj2) == null) {
                return null;
            }
        }
        return true;
    }

    static int hashCodeOrZero(Object obj) {
        return obj != null ? obj.hashCode() : null;
    }

    static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    static Type getSupertype(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2.isAssignableFrom(cls)) {
            return resolve(type, cls, getGenericSupertype(type, cls, cls2));
        }
        throw new IllegalArgumentException();
    }

    private static Type resolveTypeVariable(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class declaringClassOf = declaringClassOf(typeVariable);
        if (declaringClassOf == null) {
            return typeVariable;
        }
        type = getGenericSupertype(type, cls, declaringClassOf);
        if ((type instanceof ParameterizedType) == null) {
            return typeVariable;
        }
        return ((ParameterizedType) type).getActualTypeArguments()[indexOf(declaringClassOf.getTypeParameters(), typeVariable)];
    }

    private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        typeVariable = typeVariable.getGenericDeclaration();
        return typeVariable instanceof Class ? (Class) typeVariable : null;
    }

    static void checkNotPrimitive(Type type) {
        if (!(type instanceof Class)) {
            return;
        }
        if (((Class) type).isPrimitive() != null) {
            throw new IllegalArgumentException();
        }
    }

    static <T> T checkNotNull(@Nullable T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    static boolean isAnnotationPresent(Annotation[] annotationArr, Class<? extends Annotation> cls) {
        for (Object isInstance : annotationArr) {
            if (cls.isInstance(isInstance)) {
                return 1;
            }
        }
        return false;
    }

    static ResponseBody buffer(ResponseBody responseBody) throws IOException {
        Object buffer = new Buffer();
        responseBody.source().readAll(buffer);
        return ResponseBody.create(responseBody.contentType(), responseBody.contentLength(), buffer);
    }

    static <T> void validateServiceInterface(Class<T> cls) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        } else if (cls.getInterfaces().length > null) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }

    static Type getParameterUpperBound(int i, ParameterizedType parameterizedType) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (i < 0 || i >= actualTypeArguments.length) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Index ");
            stringBuilder.append(i);
            stringBuilder.append(" not in range [0,");
            stringBuilder.append(actualTypeArguments.length);
            stringBuilder.append(") for ");
            stringBuilder.append(parameterizedType);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        i = actualTypeArguments[i];
        return (i instanceof WildcardType) != null ? ((WildcardType) i).getUpperBounds()[null] : i;
    }

    static Type getCallResponseType(Type type) {
        if (type instanceof ParameterizedType) {
            return getParameterUpperBound(0, (ParameterizedType) type);
        }
        throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
    }
}
