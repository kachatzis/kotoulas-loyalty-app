package android.support.transition;

import android.animation.LayoutTransition;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class ViewGroupUtilsApi14 {
    private static final int LAYOUT_TRANSITION_CHANGING = 4;
    private static final String TAG = "ViewGroupUtilsApi14";
    private static Method sCancelMethod;
    private static boolean sCancelMethodFetched;
    private static LayoutTransition sEmptyLayoutTransition;
    private static Field sLayoutSuppressedField;
    private static boolean sLayoutSuppressedFieldFetched;

    /* renamed from: android.support.transition.ViewGroupUtilsApi14$1 */
    static class C01191 extends LayoutTransition {
        public boolean isChangingLayout() {
            return true;
        }

        C01191() {
        }
    }

    static void suppressLayout(@android.support.annotation.NonNull android.view.ViewGroup r5, boolean r6) {
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
        r0 = sEmptyLayoutTransition;
        r1 = 1;
        r2 = 0;
        r3 = 0;
        if (r0 != 0) goto L_0x002a;
    L_0x0007:
        r0 = new android.support.transition.ViewGroupUtilsApi14$1;
        r0.<init>();
        sEmptyLayoutTransition = r0;
        r0 = sEmptyLayoutTransition;
        r4 = 2;
        r0.setAnimator(r4, r3);
        r0 = sEmptyLayoutTransition;
        r0.setAnimator(r2, r3);
        r0 = sEmptyLayoutTransition;
        r0.setAnimator(r1, r3);
        r0 = sEmptyLayoutTransition;
        r4 = 3;
        r0.setAnimator(r4, r3);
        r0 = sEmptyLayoutTransition;
        r4 = 4;
        r0.setAnimator(r4, r3);
    L_0x002a:
        if (r6 == 0) goto L_0x004a;
    L_0x002c:
        r6 = r5.getLayoutTransition();
        if (r6 == 0) goto L_0x0044;
    L_0x0032:
        r0 = r6.isRunning();
        if (r0 == 0) goto L_0x003b;
    L_0x0038:
        cancelLayoutTransition(r6);
    L_0x003b:
        r0 = sEmptyLayoutTransition;
        if (r6 == r0) goto L_0x0044;
    L_0x003f:
        r0 = android.support.transition.C0116R.id.transition_layout_save;
        r5.setTag(r0, r6);
    L_0x0044:
        r6 = sEmptyLayoutTransition;
        r5.setLayoutTransition(r6);
        goto L_0x009c;
    L_0x004a:
        r5.setLayoutTransition(r3);
        r6 = sLayoutSuppressedFieldFetched;
        if (r6 != 0) goto L_0x006a;
    L_0x0051:
        r6 = android.view.ViewGroup.class;	 Catch:{ NoSuchFieldException -> 0x0061 }
        r0 = "mLayoutSuppressed";	 Catch:{ NoSuchFieldException -> 0x0061 }
        r6 = r6.getDeclaredField(r0);	 Catch:{ NoSuchFieldException -> 0x0061 }
        sLayoutSuppressedField = r6;	 Catch:{ NoSuchFieldException -> 0x0061 }
        r6 = sLayoutSuppressedField;	 Catch:{ NoSuchFieldException -> 0x0061 }
        r6.setAccessible(r1);	 Catch:{ NoSuchFieldException -> 0x0061 }
        goto L_0x0068;
    L_0x0061:
        r6 = "ViewGroupUtilsApi14";
        r0 = "Failed to access mLayoutSuppressed field by reflection";
        android.util.Log.i(r6, r0);
    L_0x0068:
        sLayoutSuppressedFieldFetched = r1;
    L_0x006a:
        r6 = sLayoutSuppressedField;
        if (r6 == 0) goto L_0x0085;
    L_0x006e:
        r6 = r6.getBoolean(r5);	 Catch:{ IllegalAccessException -> 0x007e }
        if (r6 == 0) goto L_0x007c;
    L_0x0074:
        r0 = sLayoutSuppressedField;	 Catch:{ IllegalAccessException -> 0x007a }
        r0.setBoolean(r5, r2);	 Catch:{ IllegalAccessException -> 0x007a }
        goto L_0x007c;
    L_0x007a:
        r2 = r6;
        goto L_0x007e;
    L_0x007c:
        r2 = r6;
        goto L_0x0085;
    L_0x007e:
        r6 = "ViewGroupUtilsApi14";
        r0 = "Failed to get mLayoutSuppressed field by reflection";
        android.util.Log.i(r6, r0);
    L_0x0085:
        if (r2 == 0) goto L_0x008a;
    L_0x0087:
        r5.requestLayout();
    L_0x008a:
        r6 = android.support.transition.C0116R.id.transition_layout_save;
        r6 = r5.getTag(r6);
        r6 = (android.animation.LayoutTransition) r6;
        if (r6 == 0) goto L_0x009c;
    L_0x0094:
        r0 = android.support.transition.C0116R.id.transition_layout_save;
        r5.setTag(r0, r3);
        r5.setLayoutTransition(r6);
    L_0x009c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ViewGroupUtilsApi14.suppressLayout(android.view.ViewGroup, boolean):void");
    }

    private static void cancelLayoutTransition(android.animation.LayoutTransition r5) {
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
        r0 = sCancelMethodFetched;
        r1 = 0;
        if (r0 != 0) goto L_0x0021;
    L_0x0005:
        r0 = 1;
        r2 = android.animation.LayoutTransition.class;	 Catch:{ NoSuchMethodException -> 0x0018 }
        r3 = "cancel";	 Catch:{ NoSuchMethodException -> 0x0018 }
        r4 = new java.lang.Class[r1];	 Catch:{ NoSuchMethodException -> 0x0018 }
        r2 = r2.getDeclaredMethod(r3, r4);	 Catch:{ NoSuchMethodException -> 0x0018 }
        sCancelMethod = r2;	 Catch:{ NoSuchMethodException -> 0x0018 }
        r2 = sCancelMethod;	 Catch:{ NoSuchMethodException -> 0x0018 }
        r2.setAccessible(r0);	 Catch:{ NoSuchMethodException -> 0x0018 }
        goto L_0x001f;
    L_0x0018:
        r2 = "ViewGroupUtilsApi14";
        r3 = "Failed to access cancel method by reflection";
        android.util.Log.i(r2, r3);
    L_0x001f:
        sCancelMethodFetched = r0;
    L_0x0021:
        r0 = sCancelMethod;
        if (r0 == 0) goto L_0x003a;
    L_0x0025:
        r1 = new java.lang.Object[r1];	 Catch:{ IllegalAccessException -> 0x0033, InvocationTargetException -> 0x002b }
        r0.invoke(r5, r1);	 Catch:{ IllegalAccessException -> 0x0033, InvocationTargetException -> 0x002b }
        goto L_0x003a;
    L_0x002b:
        r5 = "ViewGroupUtilsApi14";
        r0 = "Failed to invoke cancel method by reflection";
        android.util.Log.i(r5, r0);
        goto L_0x003a;
    L_0x0033:
        r5 = "ViewGroupUtilsApi14";
        r0 = "Failed to access cancel method by reflection";
        android.util.Log.i(r5, r0);
    L_0x003a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ViewGroupUtilsApi14.cancelLayoutTransition(android.animation.LayoutTransition):void");
    }

    private ViewGroupUtilsApi14() {
    }
}
