package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.lang.reflect.Method;

class ImageViewUtils {
    private static final String TAG = "ImageViewUtils";
    private static Method sAnimateTransformMethod;
    private static boolean sAnimateTransformMethodFetched;

    static void startAnimateTransform(ImageView imageView) {
        if (VERSION.SDK_INT < 21) {
            ScaleType scaleType = imageView.getScaleType();
            imageView.setTag(C0116R.id.save_scale_type, scaleType);
            if (scaleType == ScaleType.MATRIX) {
                imageView.setTag(C0116R.id.save_image_matrix, imageView.getImageMatrix());
            } else {
                imageView.setScaleType(ScaleType.MATRIX);
            }
            imageView.setImageMatrix(MatrixUtils.IDENTITY_MATRIX);
        }
    }

    static void animateTransform(android.widget.ImageView r3, android.graphics.Matrix r4) {
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
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 21;
        if (r0 >= r1) goto L_0x000a;
    L_0x0006:
        r3.setImageMatrix(r4);
        goto L_0x0026;
    L_0x000a:
        fetchAnimateTransformMethod();
        r0 = sAnimateTransformMethod;
        if (r0 == 0) goto L_0x0026;
    L_0x0011:
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ IllegalAccessException -> 0x0026, InvocationTargetException -> 0x001b }
        r2 = 0;	 Catch:{ IllegalAccessException -> 0x0026, InvocationTargetException -> 0x001b }
        r1[r2] = r4;	 Catch:{ IllegalAccessException -> 0x0026, InvocationTargetException -> 0x001b }
        r0.invoke(r3, r1);	 Catch:{ IllegalAccessException -> 0x0026, InvocationTargetException -> 0x001b }
        goto L_0x0026;
    L_0x001b:
        r3 = move-exception;
        r4 = new java.lang.RuntimeException;
        r3 = r3.getCause();
        r4.<init>(r3);
        throw r4;
    L_0x0026:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ImageViewUtils.animateTransform(android.widget.ImageView, android.graphics.Matrix):void");
    }

    private static void fetchAnimateTransformMethod() {
        if (!sAnimateTransformMethodFetched) {
            try {
                sAnimateTransformMethod = ImageView.class.getDeclaredMethod("animateTransform", new Class[]{Matrix.class});
                sAnimateTransformMethod.setAccessible(true);
            } catch (Throwable e) {
                Log.i(TAG, "Failed to retrieve animateTransform method", e);
            }
            sAnimateTransformMethodFetched = true;
        }
    }

    static void reserveEndAnimateTransform(final ImageView imageView, Animator animator) {
        if (VERSION.SDK_INT < 21) {
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    ScaleType scaleType = (ScaleType) imageView.getTag(C0116R.id.save_scale_type);
                    imageView.setScaleType(scaleType);
                    imageView.setTag(C0116R.id.save_scale_type, null);
                    if (scaleType == ScaleType.MATRIX) {
                        ImageView imageView = imageView;
                        imageView.setImageMatrix((Matrix) imageView.getTag(C0116R.id.save_image_matrix));
                        imageView.setTag(C0116R.id.save_image_matrix, null);
                    }
                    animator.removeListener(this);
                }
            });
        }
    }

    private ImageViewUtils() {
    }
}
