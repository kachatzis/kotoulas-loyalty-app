package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

/* renamed from: com.google.gson.internal.$Gson$Types */
public final class C$Gson$Types {
    static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    /* compiled from: $Gson$Types */
    /* renamed from: com.google.gson.internal.$Gson$Types$GenericArrayTypeImpl */
    private static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        public GenericArrayTypeImpl(Type type) {
            this.componentType = C$Gson$Types.canonicalize(type);
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        public boolean equals(Object obj) {
            return (!(obj instanceof GenericArrayType) || C$Gson$Types.equals(this, (GenericArrayType) obj) == null) ? null : true;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(C$Gson$Types.typeToString(this.componentType));
            stringBuilder.append("[]");
            return stringBuilder.toString();
        }
    }

    /* compiled from: $Gson$Types */
    /* renamed from: com.google.gson.internal.$Gson$Types$ParameterizedTypeImpl */
    private static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                Object obj;
                Class cls = (Class) type2;
                boolean z = true;
                if (!Modifier.isStatic(cls.getModifiers())) {
                    if (cls.getEnclosingClass() != null) {
                        obj = null;
                        if (type == null) {
                            if (obj != null) {
                                z = false;
                            }
                        }
                        C$Gson$Preconditions.checkArgument(z);
                    }
                }
                obj = 1;
                if (type == null) {
                    if (obj != null) {
                        z = false;
                    }
                }
                C$Gson$Preconditions.checkArgument(z);
            }
            if (type == null) {
                type = null;
            } else {
                type = C$Gson$Types.canonicalize(type);
            }
            this.ownerType = type;
            this.rawType = C$Gson$Types.canonicalize(type2);
            this.typeArguments = (Type[]) typeArr.clone();
            type = this.typeArguments.length;
            for (int i = 0; i < type; i++) {
                C$Gson$Preconditions.checkNotNull(this.typeArguments[i]);
                C$Gson$Types.checkNotPrimitive(this.typeArguments[i]);
                type2 = this.typeArguments;
                type2[i] = C$Gson$Types.canonicalize(type2[i]);
            }
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
            return (!(obj instanceof ParameterizedType) || C$Gson$Types.equals(this, (ParameterizedType) obj) == null) ? null : true;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ C$Gson$Types.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            int length = this.typeArguments.length;
            if (length == 0) {
                return C$Gson$Types.typeToString(this.rawType);
            }
            StringBuilder stringBuilder = new StringBuilder((length + 1) * 30);
            stringBuilder.append(C$Gson$Types.typeToString(this.rawType));
            stringBuilder.append("<");
            stringBuilder.append(C$Gson$Types.typeToString(this.typeArguments[0]));
            for (int i = 1; i < length; i++) {
                stringBuilder.append(", ");
                stringBuilder.append(C$Gson$Types.typeToString(this.typeArguments[i]));
            }
            stringBuilder.append(">");
            return stringBuilder.toString();
        }
    }

    /* compiled from: $Gson$Types */
    /* renamed from: com.google.gson.internal.$Gson$Types$WildcardTypeImpl */
    private static final class WildcardTypeImpl implements WildcardType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            boolean z = true;
            C$Gson$Preconditions.checkArgument(typeArr2.length <= 1);
            C$Gson$Preconditions.checkArgument(typeArr.length == 1);
            if (typeArr2.length == 1) {
                C$Gson$Preconditions.checkNotNull(typeArr2[0]);
                C$Gson$Types.checkNotPrimitive(typeArr2[0]);
                if (typeArr[0] != Object.class) {
                    z = false;
                }
                C$Gson$Preconditions.checkArgument(z);
                this.lowerBound = C$Gson$Types.canonicalize(typeArr2[0]);
                this.upperBound = Object.class;
                return;
            }
            C$Gson$Preconditions.checkNotNull(typeArr[0]);
            C$Gson$Types.checkNotPrimitive(typeArr[0]);
            this.lowerBound = null;
            this.upperBound = C$Gson$Types.canonicalize(typeArr[0]);
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public Type[] getLowerBounds() {
            if (this.lowerBound == null) {
                return C$Gson$Types.EMPTY_TYPE_ARRAY;
            }
            return new Type[]{this.lowerBound};
        }

        public boolean equals(Object obj) {
            return (!(obj instanceof WildcardType) || C$Gson$Types.equals(this, (WildcardType) obj) == null) ? null : true;
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
                stringBuilder.append(C$Gson$Types.typeToString(this.lowerBound));
                return stringBuilder.toString();
            } else if (this.upperBound == Object.class) {
                return "?";
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("? extends ");
                stringBuilder.append(C$Gson$Types.typeToString(this.upperBound));
                return stringBuilder.toString();
            }
        }
    }

    private static int indexOf(java.lang.Object[] r3, java.lang.Object r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:8:0x0016 in {4, 5, 7} preds:[]
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
        r0 = r3.length;
        r1 = 0;
    L_0x0002:
        if (r1 >= r0) goto L_0x0010;
    L_0x0004:
        r2 = r3[r1];
        r2 = r4.equals(r2);
        if (r2 == 0) goto L_0x000d;
    L_0x000c:
        return r1;
    L_0x000d:
        r1 = r1 + 1;
        goto L_0x0002;
    L_0x0010:
        r3 = new java.util.NoSuchElementException;
        r3.<init>();
        throw r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.$Gson$Types.indexOf(java.lang.Object[], java.lang.Object):int");
    }

    private static java.lang.reflect.Type resolve(java.lang.reflect.Type r8, java.lang.Class<?> r9, java.lang.reflect.Type r10, java.util.Collection<java.lang.reflect.TypeVariable> r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:62:0x00c1 in {4, 7, 14, 15, 16, 21, 22, 23, 28, 29, 35, 36, 37, 39, 40, 48, 57, 58, 59, 61} preds:[]
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
    L_0x0000:
        r0 = r10 instanceof java.lang.reflect.TypeVariable;
        if (r0 == 0) goto L_0x0018;
    L_0x0004:
        r0 = r10;
        r0 = (java.lang.reflect.TypeVariable) r0;
        r1 = r11.contains(r0);
        if (r1 == 0) goto L_0x000e;
    L_0x000d:
        return r10;
    L_0x000e:
        r11.add(r0);
        r10 = com.google.gson.internal.C$Gson$Types.resolveTypeVariable(r8, r9, r0);
        if (r10 != r0) goto L_0x0000;
    L_0x0017:
        return r10;
    L_0x0018:
        r0 = r10 instanceof java.lang.Class;
        if (r0 == 0) goto L_0x0035;
    L_0x001c:
        r0 = r10;
        r0 = (java.lang.Class) r0;
        r1 = r0.isArray();
        if (r1 == 0) goto L_0x0035;
    L_0x0025:
        r10 = r0.getComponentType();
        r8 = com.google.gson.internal.C$Gson$Types.resolve(r8, r9, r10, r11);
        if (r10 != r8) goto L_0x0030;
    L_0x002f:
        goto L_0x0034;
    L_0x0030:
        r0 = com.google.gson.internal.C$Gson$Types.arrayOf(r8);
    L_0x0034:
        return r0;
    L_0x0035:
        r0 = r10 instanceof java.lang.reflect.GenericArrayType;
        if (r0 == 0) goto L_0x004b;
    L_0x0039:
        r10 = (java.lang.reflect.GenericArrayType) r10;
        r0 = r10.getGenericComponentType();
        r8 = com.google.gson.internal.C$Gson$Types.resolve(r8, r9, r0, r11);
        if (r0 != r8) goto L_0x0046;
    L_0x0045:
        goto L_0x004a;
    L_0x0046:
        r10 = com.google.gson.internal.C$Gson$Types.arrayOf(r8);
    L_0x004a:
        return r10;
    L_0x004b:
        r0 = r10 instanceof java.lang.reflect.ParameterizedType;
        r1 = 1;
        r2 = 0;
        if (r0 == 0) goto L_0x008b;
    L_0x0051:
        r10 = (java.lang.reflect.ParameterizedType) r10;
        r0 = r10.getOwnerType();
        r3 = com.google.gson.internal.C$Gson$Types.resolve(r8, r9, r0, r11);
        if (r3 == r0) goto L_0x005f;
    L_0x005d:
        r0 = 1;
        goto L_0x0060;
    L_0x005f:
        r0 = 0;
    L_0x0060:
        r4 = r10.getActualTypeArguments();
        r5 = r4.length;
    L_0x0065:
        if (r2 >= r5) goto L_0x0080;
    L_0x0067:
        r6 = r4[r2];
        r6 = com.google.gson.internal.C$Gson$Types.resolve(r8, r9, r6, r11);
        r7 = r4[r2];
        if (r6 == r7) goto L_0x007d;
    L_0x0071:
        if (r0 != 0) goto L_0x007b;
    L_0x0073:
        r0 = r4.clone();
        r4 = r0;
        r4 = (java.lang.reflect.Type[]) r4;
        r0 = 1;
    L_0x007b:
        r4[r2] = r6;
    L_0x007d:
        r2 = r2 + 1;
        goto L_0x0065;
    L_0x0080:
        if (r0 == 0) goto L_0x008a;
    L_0x0082:
        r8 = r10.getRawType();
        r10 = com.google.gson.internal.C$Gson$Types.newParameterizedTypeWithOwner(r3, r8, r4);
    L_0x008a:
        return r10;
    L_0x008b:
        r0 = r10 instanceof java.lang.reflect.WildcardType;
        if (r0 == 0) goto L_0x00be;
    L_0x008f:
        r10 = (java.lang.reflect.WildcardType) r10;
        r0 = r10.getLowerBounds();
        r3 = r10.getUpperBounds();
        r4 = r0.length;
        if (r4 != r1) goto L_0x00ab;
    L_0x009c:
        r1 = r0[r2];
        r8 = com.google.gson.internal.C$Gson$Types.resolve(r8, r9, r1, r11);
        r9 = r0[r2];
        if (r8 == r9) goto L_0x00bd;
    L_0x00a6:
        r8 = com.google.gson.internal.C$Gson$Types.supertypeOf(r8);
        return r8;
    L_0x00ab:
        r0 = r3.length;
        if (r0 != r1) goto L_0x00bd;
    L_0x00ae:
        r0 = r3[r2];
        r8 = com.google.gson.internal.C$Gson$Types.resolve(r8, r9, r0, r11);	 Catch:{ Throwable -> 0x00bf }
        r9 = r3[r2];
        if (r8 == r9) goto L_0x00bd;
    L_0x00b8:
        r8 = com.google.gson.internal.C$Gson$Types.subtypeOf(r8);
        return r8;
    L_0x00bd:
        return r10;
    L_0x00be:
        return r10;
    L_0x00bf:
        r8 = move-exception;
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.$Gson$Types.resolve(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type, java.util.Collection):java.lang.reflect.Type");
    }

    private C$Gson$Types() {
        throw new UnsupportedOperationException();
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type type, Type type2, Type... typeArr) {
        return new ParameterizedTypeImpl(type, type2, typeArr);
    }

    public static GenericArrayType arrayOf(Type type) {
        return new GenericArrayTypeImpl(type);
    }

    public static WildcardType subtypeOf(Type type) {
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getUpperBounds();
        } else {
            type = new Type[]{type};
        }
        return new WildcardTypeImpl(type, EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type type) {
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getLowerBounds();
        } else {
            type = new Type[]{type};
        }
        return new WildcardTypeImpl(new Type[]{Object.class}, type);
    }

    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            type = (Class) type;
            if (type.isArray()) {
                type = new GenericArrayTypeImpl(C$Gson$Types.canonicalize(type.getComponentType()));
            }
            return type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new GenericArrayTypeImpl(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new WildcardTypeImpl(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
            C$Gson$Preconditions.checkArgument(type instanceof Class);
            return (Class) type;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(C$Gson$Types.getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return C$Gson$Types.getRawType(((WildcardType) type).getUpperBounds()[0]);
            }
            String str;
            if (type == null) {
                str = "null";
            } else {
                str = type.getClass().getName();
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a Class, ParameterizedType, or GenericArrayType, but <");
            stringBuilder.append(type);
            stringBuilder.append("> is of type ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    static boolean equal(Object obj, Object obj2) {
        if (obj != obj2) {
            if (obj == null || obj.equals(obj2) == null) {
                return null;
            }
        }
        return true;
    }

    public static boolean equals(Type type, Type type2) {
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
            if (!C$Gson$Types.equal(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) || !parameterizedType.getRawType().equals(parameterizedType2.getRawType()) || Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments()) == null) {
                z = false;
            }
            return z;
        } else if (type instanceof GenericArrayType) {
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return C$Gson$Types.equals(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
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

    static int hashCodeOrZero(Object obj) {
        return obj != null ? obj.hashCode() : null;
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
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
                    return C$Gson$Types.getGenericSupertype(cls.getGenericInterfaces()[i], type[i], cls2);
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
                    return C$Gson$Types.getGenericSupertype(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    static Type getSupertype(Type type, Class<?> cls, Class<?> cls2) {
        C$Gson$Preconditions.checkArgument(cls2.isAssignableFrom(cls));
        return C$Gson$Types.resolve(type, cls, C$Gson$Types.getGenericSupertype(type, cls, cls2));
    }

    public static Type getArrayComponentType(Type type) {
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }
        return ((Class) type).getComponentType();
    }

    public static Type getCollectionElementType(Type type, Class<?> cls) {
        type = C$Gson$Types.getSupertype(type, cls, Collection.class);
        if ((type instanceof WildcardType) != null) {
            type = ((WildcardType) type).getUpperBounds()[0];
        }
        if ((type instanceof ParameterizedType) != null) {
            return ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static Type[] getMapKeyAndValueTypes(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        type = C$Gson$Types.getSupertype(type, cls, Map.class);
        if ((type instanceof ParameterizedType) != null) {
            return ((ParameterizedType) type).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    public static Type resolve(Type type, Class<?> cls, Type type2) {
        return C$Gson$Types.resolve(type, cls, type2, new HashSet());
    }

    static Type resolveTypeVariable(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class declaringClassOf = C$Gson$Types.declaringClassOf(typeVariable);
        if (declaringClassOf == null) {
            return typeVariable;
        }
        type = C$Gson$Types.getGenericSupertype(type, cls, declaringClassOf);
        if ((type instanceof ParameterizedType) == null) {
            return typeVariable;
        }
        return ((ParameterizedType) type).getActualTypeArguments()[C$Gson$Types.indexOf(declaringClassOf.getTypeParameters(), typeVariable)];
    }

    private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        typeVariable = typeVariable.getGenericDeclaration();
        return typeVariable instanceof Class ? (Class) typeVariable : null;
    }

    static void checkNotPrimitive(Type type) {
        if (type instanceof Class) {
            if (((Class) type).isPrimitive() != null) {
                type = null;
                C$Gson$Preconditions.checkArgument(type);
            }
        }
        type = true;
        C$Gson$Preconditions.checkArgument(type);
    }
}
