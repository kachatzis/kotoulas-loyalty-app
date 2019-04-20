package android.support.design.widget;

import android.content.Context;
import android.support.v4.math.MathUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    private static final int INVALID_POINTER = -1;
    private int activePointerId = -1;
    private Runnable flingRunnable;
    private boolean isBeingDragged;
    private int lastMotionY;
    OverScroller scroller;
    private int touchSlop = -1;
    private VelocityTracker velocityTracker;

    private class FlingRunnable implements Runnable {
        private final V layout;
        private final CoordinatorLayout parent;

        FlingRunnable(CoordinatorLayout coordinatorLayout, V v) {
            this.parent = coordinatorLayout;
            this.layout = v;
        }

        public void run() {
            if (this.layout != null && HeaderBehavior.this.scroller != null) {
                if (HeaderBehavior.this.scroller.computeScrollOffset()) {
                    HeaderBehavior headerBehavior = HeaderBehavior.this;
                    headerBehavior.setHeaderTopBottomOffset(this.parent, this.layout, headerBehavior.scroller.getCurrY());
                    ViewCompat.postOnAnimation(this.layout, this);
                    return;
                }
                HeaderBehavior.this.onFlingFinished(this.parent, this.layout);
            }
        }
    }

    boolean canDragView(V v) {
        return false;
    }

    void onFlingFinished(CoordinatorLayout coordinatorLayout, V v) {
    }

    public HeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.touchSlop < 0) {
            this.touchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getAction() == 2 && this.isBeingDragged) {
            return true;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.isBeingDragged = false;
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (canDragView(v) && coordinatorLayout.isPointInChildBounds(v, x, y) != null) {
                    this.lastMotionY = y;
                    this.activePointerId = motionEvent.getPointerId(0);
                    ensureVelocityTracker();
                    break;
                }
            case 1:
            case 3:
                this.isBeingDragged = false;
                this.activePointerId = -1;
                coordinatorLayout = this.velocityTracker;
                if (coordinatorLayout != null) {
                    coordinatorLayout.recycle();
                    this.velocityTracker = null;
                    break;
                }
                break;
            case 2:
                coordinatorLayout = this.activePointerId;
                if (coordinatorLayout != -1) {
                    coordinatorLayout = motionEvent.findPointerIndex(coordinatorLayout);
                    if (coordinatorLayout != -1) {
                        coordinatorLayout = (int) motionEvent.getY(coordinatorLayout);
                        if (Math.abs(coordinatorLayout - this.lastMotionY) > this.touchSlop) {
                            this.isBeingDragged = true;
                            this.lastMotionY = coordinatorLayout;
                            break;
                        }
                    }
                    break;
                }
                break;
                break;
            default:
                break;
        }
        coordinatorLayout = this.velocityTracker;
        if (coordinatorLayout != null) {
            coordinatorLayout.addMovement(motionEvent);
        }
        return this.isBeingDragged;
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.touchSlop < 0) {
            this.touchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        int y;
        switch (motionEvent.getActionMasked()) {
            case 0:
                y = (int) motionEvent.getY();
                if (coordinatorLayout.isPointInChildBounds(v, (int) motionEvent.getX(), y) != null && canDragView(v) != null) {
                    this.lastMotionY = y;
                    this.activePointerId = motionEvent.getPointerId(0);
                    ensureVelocityTracker();
                    break;
                }
                return false;
                break;
            case 1:
                VelocityTracker velocityTracker = this.velocityTracker;
                if (velocityTracker != null) {
                    velocityTracker.addMovement(motionEvent);
                    this.velocityTracker.computeCurrentVelocity(1000);
                    fling(coordinatorLayout, v, -getScrollRangeForDragFling(v), 0, this.velocityTracker.getYVelocity(this.activePointerId));
                    break;
                }
                break;
            case 2:
                int findPointerIndex = motionEvent.findPointerIndex(this.activePointerId);
                if (findPointerIndex == -1) {
                    return false;
                }
                int i;
                findPointerIndex = (int) motionEvent.getY(findPointerIndex);
                y = this.lastMotionY - findPointerIndex;
                if (!this.isBeingDragged) {
                    int abs = Math.abs(y);
                    int i2 = this.touchSlop;
                    if (abs > i2) {
                        this.isBeingDragged = true;
                        i = y > 0 ? y - i2 : y + i2;
                        if (this.isBeingDragged) {
                            this.lastMotionY = findPointerIndex;
                            scroll(coordinatorLayout, v, i, getMaxDragOffset(v), 0);
                            break;
                        }
                    }
                }
                i = y;
                if (this.isBeingDragged) {
                    this.lastMotionY = findPointerIndex;
                    scroll(coordinatorLayout, v, i, getMaxDragOffset(v), 0);
                }
                break;
            case 3:
                break;
            default:
                break;
        }
        this.isBeingDragged = false;
        this.activePointerId = -1;
        coordinatorLayout = this.velocityTracker;
        if (coordinatorLayout != null) {
            coordinatorLayout.recycle();
            this.velocityTracker = null;
        }
        coordinatorLayout = this.velocityTracker;
        if (coordinatorLayout != null) {
            coordinatorLayout.addMovement(motionEvent);
        }
        return true;
    }

    int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, V v, int i) {
        return setHeaderTopBottomOffset(coordinatorLayout, v, i, Integer.MIN_VALUE, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        V topAndBottomOffset = getTopAndBottomOffset();
        if (i2 != 0 && topAndBottomOffset >= i2 && topAndBottomOffset <= i3) {
            v = MathUtils.clamp(i, i2, i3);
            if (topAndBottomOffset != v) {
                setTopAndBottomOffset(v);
                return topAndBottomOffset - v;
            }
        }
        return null;
    }

    int getTopBottomOffsetForScrollingSibling() {
        return getTopAndBottomOffset();
    }

    final int scroll(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        return setHeaderTopBottomOffset(coordinatorLayout, v, getTopBottomOffsetForScrollingSibling() - i, i2, i3);
    }

    final boolean fling(CoordinatorLayout coordinatorLayout, V v, int i, int i2, float f) {
        V v2 = v;
        Runnable runnable = this.flingRunnable;
        if (runnable != null) {
            v.removeCallbacks(runnable);
            r0.flingRunnable = null;
        }
        if (r0.scroller == null) {
            r0.scroller = new OverScroller(v.getContext());
        }
        r0.scroller.fling(0, getTopAndBottomOffset(), 0, Math.round(f), 0, 0, i, i2);
        if (r0.scroller.computeScrollOffset()) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            r0.flingRunnable = new FlingRunnable(coordinatorLayout, v);
            ViewCompat.postOnAnimation(v, r0.flingRunnable);
            return true;
        }
        coordinatorLayout2 = coordinatorLayout;
        onFlingFinished(coordinatorLayout, v);
        return false;
    }

    int getMaxDragOffset(V v) {
        return -v.getHeight();
    }

    int getScrollRangeForDragFling(V v) {
        return v.getHeight();
    }

    private void ensureVelocityTracker() {
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
    }
}
