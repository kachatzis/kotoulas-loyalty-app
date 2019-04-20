package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Method;

@RequiresApi(21)
class ViewUtilsApi21 extends ViewUtilsApi19 {
    private static final String TAG = "ViewUtilsApi21";
    private static Method sSetAnimationMatrixMethod;
    private static boolean sSetAnimationMatrixMethodFetched;
    private static Method sTransformMatrixToGlobalMethod;
    private static boolean sTransformMatrixToGlobalMethodFetched;
    private static Method sTransformMatrixToLocalMethod;
    private static boolean sTransformMatrixToLocalMethodFetched;

    ViewUtilsApi21() {
    }

    public void transformMatrixToGlobal(@android.support.annotation.NonNull android.view.View r4, @android.support.annotation.NonNull android.graphics.Matrix r5) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r3 = this;
        r3.fetchTransformMatrixToGlobalMethod();
        r0 = sTransformMatrixToGlobalMethod;
        if (r0 == 0) goto L_0x001c;
    L_0x0007:
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ IllegalAccessException -> 0x001c, InvocationTargetException -> 0x0011 }
        r2 = 0;	 Catch:{ IllegalAccessException -> 0x001c, InvocationTargetException -> 0x0011 }
        r1[r2] = r5;	 Catch:{ IllegalAccessException -> 0x001c, InvocationTargetException -> 0x0011 }
        r0.invoke(r4, r1);	 Catch:{ IllegalAccessException -> 0x001c, InvocationTargetException -> 0x0011 }
        goto L_0x001c;
    L_0x0011:
        r4 = move-exception;
        r5 = new java.lang.RuntimeException;
        r4 = r4.getCause();
        r5.<init>(r4);
        throw r5;
    L_0x001c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ViewUtilsApi21.transformMatrixToGlobal(android.view.View, android.graphics.Matrix):void");
    }

    public void transformMatrixToLocal(@android.support.annotation.NonNull android.view.View r4, @android.support.annotation.NonNull android.graphics.Matrix r5) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r3 = this;
        r3.fetchTransformMatrixToLocalMethod();
        r0 = sTransformMatrixToLocalMethod;
        if (r0 == 0) goto L_0x001c;
    L_0x0007:
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ IllegalAccessException -> 0x001c, InvocationTargetException -> 0x0011 }
        r2 = 0;	 Catch:{ IllegalAccessException -> 0x001c, InvocationTargetException -> 0x0011 }
        r1[r2] = r5;	 Catch:{ IllegalAccessException -> 0x001c, InvocationTargetException -> 0x0011 }
        r0.invoke(r4, r1);	 Catch:{ IllegalAccessException -> 0x001c, InvocationTargetException -> 0x0011 }
        goto L_0x001c;
    L_0x0011:
        r4 = move-exception;
        r5 = new java.lang.RuntimeException;
        r4 = r4.getCause();
        r5.<init>(r4);
        throw r5;
    L_0x001c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ViewUtilsApi21.transformMatrixToLocal(android.view.View, android.graphics.Matrix):void");
    }

    public void setAnimationMatrix(@android.support.annotation.NonNull android.view.View r4, android.graphics.Matrix r5) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r3 = this;
        r3.fetchSetAnimationMatrix();
        r0 = sSetAnimationMatrixMethod;
        if (r0 == 0) goto L_0x001c;
    L_0x0007:
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ InvocationTargetException -> 0x001c, IllegalAccessException -> 0x0011 }
        r2 = 0;	 Catch:{ InvocationTargetException -> 0x001c, IllegalAccessException -> 0x0011 }
        r1[r2] = r5;	 Catch:{ InvocationTargetException -> 0x001c, IllegalAccessException -> 0x0011 }
        r0.invoke(r4, r1);	 Catch:{ InvocationTargetException -> 0x001c, IllegalAccessException -> 0x0011 }
        goto L_0x001c;
    L_0x0011:
        r4 = move-exception;
        r5 = new java.lang.RuntimeException;
        r4 = r4.getCause();
        r5.<init>(r4);
        throw r5;
    L_0x001c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ViewUtilsApi21.setAnimationMatrix(android.view.View, android.graphics.Matrix):void");
    }

    private void fetchTransformMatrixToGlobalMethod() {
        if (!sTransformMatrixToGlobalMethodFetched) {
            try {
                sTransformMatrixToGlobalMethod = View.class.getDeclaredMethod("transformMatrixToGlobal", new Class[]{Matrix.class});
                sTransformMatrixToGlobalMethod.setAccessible(true);
            } catch (Throwable e) {
                Log.i(TAG, "Failed to retrieve transformMatrixToGlobal method", e);
            }
            sTransformMatrixToGlobalMethodFetched = true;
        }
    }

    private void fetchTransformMatrixToLocalMethod() {
        if (!sTransformMatrixToLocalMethodFetched) {
            try {
                sTransformMatrixToLocalMethod = View.class.getDeclaredMethod("transformMatrixToLocal", new Class[]{Matrix.class});
                sTransformMatrixToLocalMethod.setAccessible(true);
            } catch (Throwable e) {
                Log.i(TAG, "Failed to retrieve transformMatrixToLocal method", e);
            }
            sTransformMatrixToLocalMethodFetched = true;
        }
    }

    private void fetchSetAnimationMatrix() {
        if (!sSetAnimationMatrixMethodFetched) {
            try {
                sSetAnimationMatrixMethod = View.class.getDeclaredMethod("setAnimationMatrix", new Class[]{Matrix.class});
                sSetAnimationMatrixMethod.setAccessible(true);
            } catch (Throwable e) {
                Log.i(TAG, "Failed to retrieve setAnimationMatrix method", e);
            }
            sSetAnimationMatrixMethodFetched = true;
        }
    }
}
