package android.support.v4.util;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY_GROUP})
public class Preconditions {
    public static <T> T[] checkArrayElementsNotNull(T[] r4, java.lang.String r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:12:0x0040 in {6, 8, 9, 11} preds:[]
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
        if (r4 == 0) goto L_0x0029;
    L_0x0002:
        r0 = 0;
        r1 = 0;
    L_0x0004:
        r2 = r4.length;
        if (r1 >= r2) goto L_0x0028;
    L_0x0007:
        r2 = r4[r1];
        if (r2 == 0) goto L_0x000e;
    L_0x000b:
        r1 = r1 + 1;
        goto L_0x0004;
    L_0x000e:
        r4 = new java.lang.NullPointerException;
        r2 = java.util.Locale.US;
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r3[r0] = r5;
        r5 = java.lang.Integer.valueOf(r1);
        r0 = 1;
        r3[r0] = r5;
        r5 = "%s[%d] must not be null";
        r5 = java.lang.String.format(r2, r5, r3);
        r4.<init>(r5);
        throw r4;
    L_0x0028:
        return r4;
    L_0x0029:
        r4 = new java.lang.NullPointerException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r5);
        r5 = " must not be null";
        r0.append(r5);
        r5 = r0.toString();
        r4.<init>(r5);
        throw r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.Preconditions.checkArrayElementsNotNull(java.lang.Object[], java.lang.String):T[]");
    }

    @android.support.annotation.NonNull
    public static <C extends java.util.Collection<T>, T> C checkCollectionElementsNotNull(C r5, java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:12:0x004b in {6, 8, 9, 11} preds:[]
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
        if (r5 == 0) goto L_0x0034;
    L_0x0002:
        r0 = 0;
        r2 = r5.iterator();
    L_0x0008:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x0033;
    L_0x000e:
        r3 = r2.next();
        if (r3 == 0) goto L_0x0018;
    L_0x0014:
        r3 = 1;
        r0 = r0 + r3;
        goto L_0x0008;
    L_0x0018:
        r5 = new java.lang.NullPointerException;
        r2 = java.util.Locale.US;
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r3[r4] = r6;
        r6 = 1;
        r0 = java.lang.Long.valueOf(r0);
        r3[r6] = r0;
        r6 = "%s[%d] must not be null";
        r6 = java.lang.String.format(r2, r6, r3);
        r5.<init>(r6);
        throw r5;
    L_0x0033:
        return r5;
    L_0x0034:
        r5 = new java.lang.NullPointerException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r6);
        r6 = " must not be null";
        r0.append(r6);
        r6 = r0.toString();
        r5.<init>(r6);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.Preconditions.checkCollectionElementsNotNull(java.util.Collection, java.lang.String):C");
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    @NonNull
    public static <T extends CharSequence> T checkStringNotEmpty(T t) {
        if (!TextUtils.isEmpty(t)) {
            return t;
        }
        throw new IllegalArgumentException();
    }

    @NonNull
    public static <T extends CharSequence> T checkStringNotEmpty(T t, Object obj) {
        if (!TextUtils.isEmpty(t)) {
            return t;
        }
        throw new IllegalArgumentException(String.valueOf(obj));
    }

    @NonNull
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    @NonNull
    public static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static void checkState(boolean z) {
        checkState(z, null);
    }

    public static int checkFlagsArgument(int i, int i2) {
        if ((i & i2) == i) {
            return i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Requested flags 0x");
        stringBuilder.append(Integer.toHexString(i));
        stringBuilder.append(", but only 0x");
        stringBuilder.append(Integer.toHexString(i2));
        stringBuilder.append(" are allowed");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    @IntRange(from = 0)
    public static int checkArgumentNonnegative(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str);
    }

    @IntRange(from = 0)
    public static int checkArgumentNonnegative(int i) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException();
    }

    public static long checkArgumentNonnegative(long j) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException();
    }

    public static long checkArgumentNonnegative(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str);
    }

    public static int checkArgumentPositive(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str);
    }

    public static float checkArgumentFinite(float f, String str) {
        StringBuilder stringBuilder;
        if (Float.isNaN(f)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" must not be NaN");
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (!Float.isInfinite(f)) {
            return f;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" must not be infinite");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public static float checkArgumentInRange(float f, float f2, float f3, String str) {
        if (Float.isNaN(f)) {
            f2 = new StringBuilder();
            f2.append(str);
            f2.append(" must not be NaN");
            throw new IllegalArgumentException(f2.toString());
        } else if (f < f2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", new Object[]{str, Float.valueOf(f2), Float.valueOf(f3)}));
        } else if (f <= f3) {
            return f;
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", new Object[]{str, Float.valueOf(f2), Float.valueOf(f3)}));
        }
    }

    public static int checkArgumentInRange(int i, int i2, int i3, String str) {
        if (i < i2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i3)}));
        } else if (i <= i3) {
            return i;
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i3)}));
        }
    }

    public static long checkArgumentInRange(long j, long j2, long j3, String str) {
        if (j < j2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", new Object[]{str, Long.valueOf(j2), Long.valueOf(j3)}));
        } else if (j <= j3) {
            return j;
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", new Object[]{str, Long.valueOf(j2), Long.valueOf(j3)}));
        }
    }

    public static <T> Collection<T> checkCollectionNotEmpty(Collection<T> collection, String str) {
        StringBuilder stringBuilder;
        if (collection == null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" must not be null");
            throw new NullPointerException(stringBuilder.toString());
        } else if (!collection.isEmpty()) {
            return collection;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" is empty");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public static float[] checkArrayElementsInRange(float[] fArr, float f, float f2, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" must not be null");
        checkNotNull(fArr, stringBuilder.toString());
        int i = 0;
        while (i < fArr.length) {
            float f3 = fArr[i];
            if (Float.isNaN(f3)) {
                f = new StringBuilder();
                f.append(str);
                f.append("[");
                f.append(i);
                f.append("] must not be NaN");
                throw new IllegalArgumentException(f.toString());
            } else if (f3 < f) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too low)", new Object[]{str, Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f2)}));
            } else if (f3 <= f2) {
                i++;
            } else {
                throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too high)", new Object[]{str, Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f2)}));
            }
        }
        return fArr;
    }

    private Preconditions() {
    }
}
