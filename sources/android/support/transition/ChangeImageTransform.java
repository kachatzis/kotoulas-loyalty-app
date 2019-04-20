package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ChangeImageTransform extends Transition {
    private static final Property<ImageView, Matrix> ANIMATED_TRANSFORM_PROPERTY = new Property<ImageView, Matrix>(Matrix.class, "animatedTransform") {
        public Matrix get(ImageView imageView) {
            return null;
        }

        public void set(ImageView imageView, Matrix matrix) {
            ImageViewUtils.animateTransform(imageView, matrix);
        }
    };
    private static final TypeEvaluator<Matrix> NULL_MATRIX_EVALUATOR = new C01071();
    private static final String PROPNAME_BOUNDS = "android:changeImageTransform:bounds";
    private static final String PROPNAME_MATRIX = "android:changeImageTransform:matrix";
    private static final String[] sTransitionProperties = new String[]{PROPNAME_MATRIX, PROPNAME_BOUNDS};

    /* renamed from: android.support.transition.ChangeImageTransform$1 */
    static class C01071 implements TypeEvaluator<Matrix> {
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            return null;
        }

        C01071() {
        }
    }

    /* renamed from: android.support.transition.ChangeImageTransform$3 */
    static /* synthetic */ class C01093 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ScaleType.values().length];

        static {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
            r0 = android.widget.ImageView.ScaleType.values();
            r0 = r0.length;
            r0 = new int[r0];
            $SwitchMap$android$widget$ImageView$ScaleType = r0;
            r0 = $SwitchMap$android$widget$ImageView$ScaleType;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = android.widget.ImageView.ScaleType.FIT_XY;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = $SwitchMap$android$widget$ImageView$ScaleType;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = android.widget.ImageView.ScaleType.CENTER_CROP;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;	 Catch:{ NoSuchFieldError -> 0x001f }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ChangeImageTransform.3.<clinit>():void");
        }
    }

    public ChangeImageTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view instanceof ImageView) {
            if (view.getVisibility() == 0) {
                ImageView imageView = (ImageView) view;
                if (imageView.getDrawable() != null) {
                    transitionValues = transitionValues.values;
                    transitionValues.put(PROPNAME_BOUNDS, new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
                    transitionValues.put(PROPNAME_MATRIX, copyImageMatrix(imageView));
                }
            }
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public Animator createAnimator(@NonNull ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues != null) {
            if (transitionValues2 != null) {
                Rect rect = (Rect) transitionValues.values.get(PROPNAME_BOUNDS);
                Rect rect2 = (Rect) transitionValues2.values.get(PROPNAME_BOUNDS);
                if (rect != null) {
                    if (rect2 != null) {
                        transitionValues = (Matrix) transitionValues.values.get(PROPNAME_MATRIX);
                        Matrix matrix = (Matrix) transitionValues2.values.get(PROPNAME_MATRIX);
                        Object obj = (!(transitionValues == null && matrix == null) && (transitionValues == null || !transitionValues.equals(matrix))) ? null : 1;
                        if (rect.equals(rect2) && obj != null) {
                            return null;
                        }
                        ImageView imageView = (ImageView) transitionValues2.view;
                        transitionValues2 = imageView.getDrawable();
                        int intrinsicWidth = transitionValues2.getIntrinsicWidth();
                        transitionValues2 = transitionValues2.getIntrinsicHeight();
                        ImageViewUtils.startAnimateTransform(imageView);
                        if (intrinsicWidth != 0) {
                            if (transitionValues2 != null) {
                                if (transitionValues == null) {
                                    transitionValues = MatrixUtils.IDENTITY_MATRIX;
                                }
                                if (matrix == null) {
                                    matrix = MatrixUtils.IDENTITY_MATRIX;
                                }
                                ANIMATED_TRANSFORM_PROPERTY.set(imageView, transitionValues);
                                transitionValues = createMatrixAnimator(imageView, transitionValues, matrix);
                                ImageViewUtils.reserveEndAnimateTransform(imageView, transitionValues);
                                return transitionValues;
                            }
                        }
                        transitionValues = createNullAnimator(imageView);
                        ImageViewUtils.reserveEndAnimateTransform(imageView, transitionValues);
                        return transitionValues;
                    }
                }
                return null;
            }
        }
        return null;
    }

    private ObjectAnimator createNullAnimator(ImageView imageView) {
        return ObjectAnimator.ofObject(imageView, ANIMATED_TRANSFORM_PROPERTY, NULL_MATRIX_EVALUATOR, new Matrix[]{null, null});
    }

    private ObjectAnimator createMatrixAnimator(ImageView imageView, Matrix matrix, Matrix matrix2) {
        return ObjectAnimator.ofObject(imageView, ANIMATED_TRANSFORM_PROPERTY, new MatrixEvaluator(), new Matrix[]{matrix, matrix2});
    }

    private static Matrix copyImageMatrix(ImageView imageView) {
        switch (C01093.$SwitchMap$android$widget$ImageView$ScaleType[imageView.getScaleType().ordinal()]) {
            case 1:
                return fitXYMatrix(imageView);
            case 2:
                return centerCropMatrix(imageView);
            default:
                return new Matrix(imageView.getImageMatrix());
        }
    }

    private static Matrix fitXYMatrix(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) imageView.getWidth()) / ((float) drawable.getIntrinsicWidth()), ((float) imageView.getHeight()) / ((float) drawable.getIntrinsicHeight()));
        return matrix;
    }

    private static Matrix centerCropMatrix(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        float width = (float) imageView.getWidth();
        float intrinsicWidth = (float) drawable.getIntrinsicWidth();
        float f = width / intrinsicWidth;
        imageView = (float) imageView.getHeight();
        float intrinsicHeight = (float) drawable.getIntrinsicHeight();
        f = Math.max(f, imageView / intrinsicHeight);
        intrinsicHeight *= f;
        int round = Math.round((width - (intrinsicWidth * f)) / 2.0f);
        imageView = Math.round((imageView - intrinsicHeight) / 1073741824);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        matrix.postTranslate((float) round, (float) imageView);
        return matrix;
    }
}
