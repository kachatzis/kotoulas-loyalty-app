package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

class TransitionUtils {
    private static final boolean HAS_IS_ATTACHED_TO_WINDOW = (VERSION.SDK_INT >= 19);
    private static final boolean HAS_OVERLAY = (VERSION.SDK_INT >= 18);
    private static final boolean HAS_PICTURE_BITMAP;
    private static final int MAX_IMAGE_SIZE = 1048576;

    static class MatrixEvaluator implements TypeEvaluator<Matrix> {
        final float[] mTempEndValues = new float[9];
        final Matrix mTempMatrix = new Matrix();
        final float[] mTempStartValues = new float[9];

        MatrixEvaluator() {
        }

        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            matrix.getValues(this.mTempStartValues);
            matrix2.getValues(this.mTempEndValues);
            for (matrix = null; matrix < 9; matrix++) {
                matrix2 = this.mTempEndValues;
                float f2 = matrix2[matrix];
                float[] fArr = this.mTempStartValues;
                matrix2[matrix] = fArr[matrix] + ((f2 - fArr[matrix]) * f);
            }
            this.mTempMatrix.setValues(this.mTempEndValues);
            return this.mTempMatrix;
        }
    }

    static {
        boolean z = true;
        if (VERSION.SDK_INT < 28) {
            z = false;
        }
        HAS_PICTURE_BITMAP = z;
    }

    static View copyViewImage(ViewGroup viewGroup, View view, View view2) {
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) (-view2.getScrollX()), (float) (-view2.getScrollY()));
        ViewUtils.transformMatrixToGlobal(view, matrix);
        ViewUtils.transformMatrixToLocal(viewGroup, matrix);
        view2 = new RectF(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        matrix.mapRect(view2);
        int round = Math.round(view2.left);
        int round2 = Math.round(view2.top);
        int round3 = Math.round(view2.right);
        int round4 = Math.round(view2.bottom);
        View imageView = new ImageView(view.getContext());
        imageView.setScaleType(ScaleType.CENTER_CROP);
        viewGroup = createViewBitmap(view, matrix, view2, viewGroup);
        if (viewGroup != null) {
            imageView.setImageBitmap(viewGroup);
        }
        imageView.measure(MeasureSpec.makeMeasureSpec(round3 - round, 1073741824), MeasureSpec.makeMeasureSpec(round4 - round2, 1073741824));
        imageView.layout(round, round2, round3, round4);
        return imageView;
    }

    private static Bitmap createViewBitmap(View view, Matrix matrix, RectF rectF, ViewGroup viewGroup) {
        boolean isAttachedToWindow;
        ViewGroup viewGroup2;
        int i = 0;
        int isAttachedToWindow2;
        if (HAS_IS_ATTACHED_TO_WINDOW) {
            isAttachedToWindow2 = view.isAttachedToWindow() ^ 1;
            isAttachedToWindow = viewGroup == null ? false : viewGroup.isAttachedToWindow();
        } else {
            isAttachedToWindow2 = 0;
            isAttachedToWindow = false;
        }
        Bitmap bitmap = null;
        if (!HAS_OVERLAY || r0 == 0) {
            viewGroup2 = null;
        } else if (!isAttachedToWindow) {
            return null;
        } else {
            ViewGroup viewGroup3 = (ViewGroup) view.getParent();
            int indexOfChild = viewGroup3.indexOfChild(view);
            viewGroup.getOverlay().add(view);
            int i2 = indexOfChild;
            viewGroup2 = viewGroup3;
            i = i2;
        }
        int round = Math.round(rectF.width());
        int round2 = Math.round(rectF.height());
        if (round > 0 && round2 > 0) {
            float min = Math.min(1.0f, 1048576.0f / ((float) (round * round2)));
            round = Math.round(((float) round) * min);
            round2 = Math.round(((float) round2) * min);
            matrix.postTranslate(-rectF.left, -rectF.top);
            matrix.postScale(min, min);
            if (HAS_PICTURE_BITMAP != null) {
                rectF = new Picture();
                Canvas beginRecording = rectF.beginRecording(round, round2);
                beginRecording.concat(matrix);
                view.draw(beginRecording);
                rectF.endRecording();
                bitmap = Bitmap.createBitmap(rectF);
            } else {
                bitmap = Bitmap.createBitmap(round, round2, Config.ARGB_8888);
                rectF = new Canvas(bitmap);
                rectF.concat(matrix);
                view.draw(rectF);
            }
        }
        if (!(HAS_OVERLAY == null || r0 == 0)) {
            viewGroup.getOverlay().remove(view);
            viewGroup2.addView(view, i);
        }
        return bitmap;
    }

    static Animator mergeAnimators(Animator animator, Animator animator2) {
        if (animator == null) {
            return animator2;
        }
        if (animator2 == null) {
            return animator;
        }
        Animator animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{animator, animator2});
        return animatorSet;
    }

    private TransitionUtils() {
    }
}
