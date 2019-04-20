package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Method;

@RequiresApi(21)
class GhostViewApi21 implements GhostViewImpl {
    private static final String TAG = "GhostViewApi21";
    private static Method sAddGhostMethod;
    private static boolean sAddGhostMethodFetched;
    private static Class<?> sGhostViewClass;
    private static boolean sGhostViewClassFetched;
    private static Method sRemoveGhostMethod;
    private static boolean sRemoveGhostMethodFetched;
    private final View mGhostView;

    public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
    }

    static android.support.transition.GhostViewImpl addGhost(android.view.View r5, android.view.ViewGroup r6, android.graphics.Matrix r7) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        fetchAddGhostMethod();
        r0 = sAddGhostMethod;
        r1 = 0;
        if (r0 == 0) goto L_0x002b;
    L_0x0008:
        r2 = new android.support.transition.GhostViewApi21;	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        r3 = 3;	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        r4 = 0;	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        r3[r4] = r5;	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        r5 = 1;	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        r3[r5] = r6;	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        r5 = 2;	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        r3[r5] = r7;	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        r5 = r0.invoke(r1, r3);	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        r5 = (android.view.View) r5;	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        r2.<init>(r5);	 Catch:{ IllegalAccessException -> 0x002b, InvocationTargetException -> 0x0020 }
        return r2;
    L_0x0020:
        r5 = move-exception;
        r6 = new java.lang.RuntimeException;
        r5 = r5.getCause();
        r6.<init>(r5);
        throw r6;
    L_0x002b:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.GhostViewApi21.addGhost(android.view.View, android.view.ViewGroup, android.graphics.Matrix):android.support.transition.GhostViewImpl");
    }

    static void removeGhost(android.view.View r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        fetchRemoveGhostMethod();
        r0 = sRemoveGhostMethod;
        if (r0 == 0) goto L_0x001d;
    L_0x0007:
        r1 = 0;
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ IllegalAccessException -> 0x001d, InvocationTargetException -> 0x0012 }
        r3 = 0;	 Catch:{ IllegalAccessException -> 0x001d, InvocationTargetException -> 0x0012 }
        r2[r3] = r4;	 Catch:{ IllegalAccessException -> 0x001d, InvocationTargetException -> 0x0012 }
        r0.invoke(r1, r2);	 Catch:{ IllegalAccessException -> 0x001d, InvocationTargetException -> 0x0012 }
        goto L_0x001d;
    L_0x0012:
        r4 = move-exception;
        r0 = new java.lang.RuntimeException;
        r4 = r4.getCause();
        r0.<init>(r4);
        throw r0;
    L_0x001d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.GhostViewApi21.removeGhost(android.view.View):void");
    }

    private GhostViewApi21(@NonNull View view) {
        this.mGhostView = view;
    }

    public void setVisibility(int i) {
        this.mGhostView.setVisibility(i);
    }

    private static void fetchGhostViewClass() {
        if (!sGhostViewClassFetched) {
            try {
                sGhostViewClass = Class.forName("android.view.GhostView");
            } catch (Throwable e) {
                Log.i(TAG, "Failed to retrieve GhostView class", e);
            }
            sGhostViewClassFetched = true;
        }
    }

    private static void fetchAddGhostMethod() {
        if (!sAddGhostMethodFetched) {
            try {
                fetchGhostViewClass();
                sAddGhostMethod = sGhostViewClass.getDeclaredMethod("addGhost", new Class[]{View.class, ViewGroup.class, Matrix.class});
                sAddGhostMethod.setAccessible(true);
            } catch (Throwable e) {
                Log.i(TAG, "Failed to retrieve addGhost method", e);
            }
            sAddGhostMethodFetched = true;
        }
    }

    private static void fetchRemoveGhostMethod() {
        if (!sRemoveGhostMethodFetched) {
            try {
                fetchGhostViewClass();
                sRemoveGhostMethod = sGhostViewClass.getDeclaredMethod("removeGhost", new Class[]{View.class});
                sRemoveGhostMethod.setAccessible(true);
            } catch (Throwable e) {
                Log.i(TAG, "Failed to retrieve removeGhost method", e);
            }
            sRemoveGhostMethodFetched = true;
        }
    }
}
