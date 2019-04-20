package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SwipeDismissBehavior<V extends View> extends Behavior<V> {
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    float alphaEndSwipeDistance = 0.5f;
    float alphaStartSwipeDistance = 0.0f;
    private final Callback dragCallback = new C04751();
    float dragDismissThreshold = 0.5f;
    private boolean interceptingEvents;
    OnDismissListener listener;
    private float sensitivity = 0.0f;
    private boolean sensitivitySet;
    int swipeDirection = 2;
    ViewDragHelper viewDragHelper;

    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i);
    }

    private class SettleRunnable implements Runnable {
        private final boolean dismiss;
        private final View view;

        SettleRunnable(View view, boolean z) {
            this.view = view;
            this.dismiss = z;
        }

        public void run() {
            if (SwipeDismissBehavior.this.viewDragHelper != null && SwipeDismissBehavior.this.viewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.view, this);
            } else if (this.dismiss && SwipeDismissBehavior.this.listener != null) {
                SwipeDismissBehavior.this.listener.onDismiss(this.view);
            }
        }
    }

    /* renamed from: android.support.design.widget.SwipeDismissBehavior$1 */
    class C04751 extends Callback {
        private static final int INVALID_POINTER_ID = -1;
        private int activePointerId = -1;
        private int originalCapturedViewLeft;

        C04751() {
        }

        public boolean tryCaptureView(View view, int i) {
            return (this.activePointerId != -1 || SwipeDismissBehavior.this.canSwipeDismissView(view) == null) ? null : true;
        }

        public void onViewCaptured(View view, int i) {
            this.activePointerId = i;
            this.originalCapturedViewLeft = view.getLeft();
            view = view.getParent();
            if (view != null) {
                view.requestDisallowInterceptTouchEvent(1);
            }
        }

        public void onViewDragStateChanged(int i) {
            if (SwipeDismissBehavior.this.listener != null) {
                SwipeDismissBehavior.this.listener.onDragStateChanged(i);
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            int i;
            this.activePointerId = -1;
            f2 = view.getWidth();
            if (shouldDismiss(view, f) != null) {
                int left = view.getLeft();
                i = this.originalCapturedViewLeft;
                i = left < i ? i - f2 : i + f2;
                f = Float.MIN_VALUE;
            } else {
                i = this.originalCapturedViewLeft;
                f = 0.0f;
            }
            if (SwipeDismissBehavior.this.viewDragHelper.settleCapturedViewAt(i, view.getTop()) != null) {
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, f));
            } else if (f != null && SwipeDismissBehavior.this.listener != null) {
                SwipeDismissBehavior.this.listener.onDismiss(view);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean shouldDismiss(android.view.View r6, float r7) {
            /*
            r5 = this;
            r0 = 0;
            r1 = 0;
            r2 = 1;
            r3 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
            if (r3 == 0) goto L_0x003f;
        L_0x0007:
            r6 = android.support.v4.view.ViewCompat.getLayoutDirection(r6);
            if (r6 != r2) goto L_0x000f;
        L_0x000d:
            r6 = 1;
            goto L_0x0010;
        L_0x000f:
            r6 = 0;
        L_0x0010:
            r3 = android.support.design.widget.SwipeDismissBehavior.this;
            r3 = r3.swipeDirection;
            r4 = 2;
            if (r3 != r4) goto L_0x0018;
        L_0x0017:
            return r2;
        L_0x0018:
            r3 = android.support.design.widget.SwipeDismissBehavior.this;
            r3 = r3.swipeDirection;
            if (r3 != 0) goto L_0x002b;
        L_0x001e:
            if (r6 == 0) goto L_0x0025;
        L_0x0020:
            r6 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
            if (r6 >= 0) goto L_0x002a;
        L_0x0024:
            goto L_0x0029;
        L_0x0025:
            r6 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
            if (r6 <= 0) goto L_0x002a;
        L_0x0029:
            r1 = 1;
        L_0x002a:
            return r1;
        L_0x002b:
            r3 = android.support.design.widget.SwipeDismissBehavior.this;
            r3 = r3.swipeDirection;
            if (r3 != r2) goto L_0x003e;
        L_0x0031:
            if (r6 == 0) goto L_0x0038;
        L_0x0033:
            r6 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
            if (r6 <= 0) goto L_0x003d;
        L_0x0037:
            goto L_0x003c;
        L_0x0038:
            r6 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
            if (r6 >= 0) goto L_0x003d;
        L_0x003c:
            r1 = 1;
        L_0x003d:
            return r1;
        L_0x003e:
            return r1;
        L_0x003f:
            r7 = r6.getLeft();
            r0 = r5.originalCapturedViewLeft;
            r7 = r7 - r0;
            r6 = r6.getWidth();
            r6 = (float) r6;
            r0 = android.support.design.widget.SwipeDismissBehavior.this;
            r0 = r0.dragDismissThreshold;
            r6 = r6 * r0;
            r6 = java.lang.Math.round(r6);
            r7 = java.lang.Math.abs(r7);
            if (r7 < r6) goto L_0x005c;
        L_0x005b:
            r1 = 1;
        L_0x005c:
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.SwipeDismissBehavior.1.shouldDismiss(android.view.View, float):boolean");
        }

        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            int i3;
            i2 = ViewCompat.getLayoutDirection(view) == 1 ? 1 : 0;
            if (SwipeDismissBehavior.this.swipeDirection == 0) {
                if (i2 != 0) {
                    i2 = this.originalCapturedViewLeft - view.getWidth();
                    i3 = this.originalCapturedViewLeft;
                } else {
                    i2 = this.originalCapturedViewLeft;
                    i3 = view.getWidth() + i2;
                }
            } else if (SwipeDismissBehavior.this.swipeDirection != 1) {
                i2 = this.originalCapturedViewLeft - view.getWidth();
                i3 = view.getWidth() + this.originalCapturedViewLeft;
            } else if (i2 != 0) {
                i2 = this.originalCapturedViewLeft;
                i3 = view.getWidth() + i2;
            } else {
                i2 = this.originalCapturedViewLeft - view.getWidth();
                i3 = this.originalCapturedViewLeft;
            }
            return SwipeDismissBehavior.clamp(i2, i, i3);
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            i2 = ((float) this.originalCapturedViewLeft) + (((float) view.getWidth()) * SwipeDismissBehavior.this.alphaStartSwipeDistance);
            i3 = ((float) this.originalCapturedViewLeft) + (((float) view.getWidth()) * SwipeDismissBehavior.this.alphaEndSwipeDistance);
            i = (float) i;
            if (i <= i2) {
                view.setAlpha(1.0f);
            } else if (i >= i3) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.clamp(0.0f, 1065353216 - SwipeDismissBehavior.fraction(i2, i3, i), 1.0f));
            }
        }
    }

    static float fraction(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    public boolean canSwipeDismissView(@NonNull View view) {
        return true;
    }

    public void setListener(OnDismissListener onDismissListener) {
        this.listener = onDismissListener;
    }

    public void setSwipeDirection(int i) {
        this.swipeDirection = i;
    }

    public void setDragDismissDistance(float f) {
        this.dragDismissThreshold = clamp(0.0f, f, 1.0f);
    }

    public void setStartAlphaSwipeDistance(float f) {
        this.alphaStartSwipeDistance = clamp(0.0f, f, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float f) {
        this.alphaEndSwipeDistance = clamp(0.0f, f, 1.0f);
    }

    public void setSensitivity(float f) {
        this.sensitivity = f;
        this.sensitivitySet = true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z = this.interceptingEvents;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 3) {
            switch (actionMasked) {
                case 0:
                    this.interceptingEvents = coordinatorLayout.isPointInChildBounds(v, (int) motionEvent.getX(), (int) motionEvent.getY());
                    z = this.interceptingEvents;
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }
        this.interceptingEvents = false;
        if (!z) {
            return false;
        }
        ensureViewDragHelper(coordinatorLayout);
        return this.viewDragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        coordinatorLayout = this.viewDragHelper;
        if (coordinatorLayout == null) {
            return null;
        }
        coordinatorLayout.processTouchEvent(motionEvent);
        return true;
    }

    private void ensureViewDragHelper(ViewGroup viewGroup) {
        if (this.viewDragHelper == null) {
            if (this.sensitivitySet) {
                viewGroup = ViewDragHelper.create(viewGroup, this.sensitivity, this.dragCallback);
            } else {
                viewGroup = ViewDragHelper.create(viewGroup, this.dragCallback);
            }
            this.viewDragHelper = viewGroup;
        }
    }

    static float clamp(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    static int clamp(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    public int getDragState() {
        ViewDragHelper viewDragHelper = this.viewDragHelper;
        return viewDragHelper != null ? viewDragHelper.getViewDragState() : 0;
    }
}
