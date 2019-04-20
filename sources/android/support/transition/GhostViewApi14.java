package android.support.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;

@SuppressLint({"ViewConstructor"})
class GhostViewApi14 extends View implements GhostViewImpl {
    Matrix mCurrentMatrix;
    private int mDeltaX;
    private int mDeltaY;
    private final Matrix mMatrix = new Matrix();
    private final OnPreDrawListener mOnPreDrawListener = new C01131();
    int mReferences;
    ViewGroup mStartParent;
    View mStartView;
    final View mView;

    /* renamed from: android.support.transition.GhostViewApi14$1 */
    class C01131 implements OnPreDrawListener {
        C01131() {
        }

        public boolean onPreDraw() {
            GhostViewApi14 ghostViewApi14 = GhostViewApi14.this;
            ghostViewApi14.mCurrentMatrix = ghostViewApi14.mView.getMatrix();
            ViewCompat.postInvalidateOnAnimation(GhostViewApi14.this);
            if (!(GhostViewApi14.this.mStartParent == null || GhostViewApi14.this.mStartView == null)) {
                GhostViewApi14.this.mStartParent.endViewTransition(GhostViewApi14.this.mStartView);
                ViewCompat.postInvalidateOnAnimation(GhostViewApi14.this.mStartParent);
                ghostViewApi14 = GhostViewApi14.this;
                ghostViewApi14.mStartParent = null;
                ghostViewApi14.mStartView = null;
            }
            return true;
        }
    }

    static GhostViewImpl addGhost(View view, ViewGroup viewGroup) {
        GhostViewImpl ghostView = getGhostView(view);
        if (ghostView == null) {
            viewGroup = findFrameLayout(viewGroup);
            if (viewGroup == null) {
                return null;
            }
            ghostView = new GhostViewApi14(view);
            viewGroup.addView(ghostView);
        }
        ghostView.mReferences++;
        return ghostView;
    }

    static void removeGhost(View view) {
        view = getGhostView(view);
        if (view != null) {
            view.mReferences--;
            if (view.mReferences <= 0) {
                ViewParent parent = view.getParent();
                if (parent instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) parent;
                    viewGroup.endViewTransition(view);
                    viewGroup.removeView(view);
                }
            }
        }
    }

    private static FrameLayout findFrameLayout(ViewGroup viewGroup) {
        while (!(viewGroup instanceof FrameLayout)) {
            viewGroup = viewGroup.getParent();
            if (!(viewGroup instanceof ViewGroup)) {
                return null;
            }
            viewGroup = viewGroup;
        }
        return (FrameLayout) viewGroup;
    }

    GhostViewApi14(View view) {
        super(view.getContext());
        this.mView = view;
        setLayerType(2, null);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setGhostView(this.mView, this);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        getLocationOnScreen(iArr);
        this.mView.getLocationOnScreen(iArr2);
        iArr2[0] = (int) (((float) iArr2[0]) - this.mView.getTranslationX());
        iArr2[1] = (int) (((float) iArr2[1]) - this.mView.getTranslationY());
        this.mDeltaX = iArr2[0] - iArr[0];
        this.mDeltaY = iArr2[1] - iArr[1];
        this.mView.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        this.mView.setVisibility(4);
    }

    protected void onDetachedFromWindow() {
        this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        this.mView.setVisibility(0);
        setGhostView(this.mView, null);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas) {
        this.mMatrix.set(this.mCurrentMatrix);
        this.mMatrix.postTranslate((float) this.mDeltaX, (float) this.mDeltaY);
        canvas.setMatrix(this.mMatrix);
        this.mView.draw(canvas);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        this.mView.setVisibility(i == 0 ? 4 : 0);
    }

    public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
        this.mStartParent = viewGroup;
        this.mStartView = view;
    }

    private static void setGhostView(@NonNull View view, GhostViewApi14 ghostViewApi14) {
        view.setTag(C0116R.id.ghost_view, ghostViewApi14);
    }

    static GhostViewApi14 getGhostView(@NonNull View view) {
        return (GhostViewApi14) view.getTag(C0116R.id.ghost_view);
    }
}
