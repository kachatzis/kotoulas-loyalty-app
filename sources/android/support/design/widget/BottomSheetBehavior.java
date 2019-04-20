package android.support.design.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.design.C0026R;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.v4.math.MathUtils;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BottomSheetBehavior<V extends View> extends Behavior<V> {
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    int activePointerId;
    private BottomSheetCallback callback;
    int collapsedOffset;
    private final Callback dragCallback = new C04672();
    private boolean fitToContents = true;
    int fitToContentsOffset;
    int halfExpandedOffset;
    boolean hideable;
    private boolean ignoreEvents;
    private Map<View, Integer> importantForAccessibilityMap;
    private int initialY;
    private int lastNestedScrollDy;
    private int lastPeekHeight;
    private float maximumVelocity;
    private boolean nestedScrolled;
    WeakReference<View> nestedScrollingChildRef;
    int parentHeight;
    private int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightMin;
    private boolean skipCollapsed;
    int state = 4;
    boolean touchingScrollingChild;
    private VelocityTracker velocityTracker;
    ViewDragHelper viewDragHelper;
    WeakReference<V> viewRef;

    public static abstract class BottomSheetCallback {
        public abstract void onSlide(@NonNull View view, float f);

        public abstract void onStateChanged(@NonNull View view, int i);
    }

    private class SettleRunnable implements Runnable {
        private final int targetState;
        private final View view;

        SettleRunnable(View view, int i) {
            this.view = view;
            this.targetState = i;
        }

        public void run() {
            if (BottomSheetBehavior.this.viewDragHelper == null || !BottomSheetBehavior.this.viewDragHelper.continueSettling(true)) {
                BottomSheetBehavior.this.setStateInternal(this.targetState);
            } else {
                ViewCompat.postOnAnimation(this.view, this);
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    /* renamed from: android.support.design.widget.BottomSheetBehavior$2 */
    class C04672 extends Callback {
        C04672() {
        }

        public boolean tryCaptureView(@NonNull View view, int i) {
            boolean z = true;
            if (BottomSheetBehavior.this.state == 1 || BottomSheetBehavior.this.touchingScrollingChild) {
                return false;
            }
            if (BottomSheetBehavior.this.state == 3 && BottomSheetBehavior.this.activePointerId == i) {
                View view2 = (View) BottomSheetBehavior.this.nestedScrollingChildRef.get();
                if (!(view2 == null || view2.canScrollVertically(-1) == 0)) {
                    return false;
                }
            }
            if (BottomSheetBehavior.this.viewRef == 0 || BottomSheetBehavior.this.viewRef.get() != view) {
                z = false;
            }
            return z;
        }

        public void onViewPositionChanged(@NonNull View view, int i, int i2, int i3, int i4) {
            BottomSheetBehavior.this.dispatchOnSlide(i2);
        }

        public void onViewDragStateChanged(int i) {
            if (i == 1) {
                BottomSheetBehavior.this.setStateInternal(1);
            }
        }

        public void onViewReleased(@NonNull View view, float f, float f2) {
            int i = 4;
            if (f2 < 0.0f) {
                if (BottomSheetBehavior.this.fitToContents != null) {
                    f = BottomSheetBehavior.this.fitToContentsOffset;
                    i = 3;
                } else if (view.getTop() > BottomSheetBehavior.this.halfExpandedOffset) {
                    f = BottomSheetBehavior.this.halfExpandedOffset;
                    i = 6;
                } else {
                    f = 0.0f;
                    i = 3;
                }
            } else if (BottomSheetBehavior.this.hideable && BottomSheetBehavior.this.shouldHide(view, f2) && (view.getTop() > BottomSheetBehavior.this.collapsedOffset || Math.abs(f) < Math.abs(f2))) {
                f = BottomSheetBehavior.this.parentHeight;
                i = 5;
            } else {
                if (f2 != 0.0f) {
                    if (Math.abs(f) <= Math.abs(f2)) {
                        f = BottomSheetBehavior.this.collapsedOffset;
                    }
                }
                f = view.getTop();
                if (BottomSheetBehavior.this.fitToContents != null) {
                    if (Math.abs(f - BottomSheetBehavior.this.fitToContentsOffset) < Math.abs(f - BottomSheetBehavior.this.collapsedOffset)) {
                        f = BottomSheetBehavior.this.fitToContentsOffset;
                        i = 3;
                    } else {
                        f = BottomSheetBehavior.this.collapsedOffset;
                    }
                } else if (f < BottomSheetBehavior.this.halfExpandedOffset) {
                    if (f < Math.abs(f - BottomSheetBehavior.this.collapsedOffset)) {
                        f = 0.0f;
                        i = 3;
                    } else {
                        f = BottomSheetBehavior.this.halfExpandedOffset;
                        i = 6;
                    }
                } else if (Math.abs(f - BottomSheetBehavior.this.halfExpandedOffset) < Math.abs(f - BottomSheetBehavior.this.collapsedOffset)) {
                    f = BottomSheetBehavior.this.halfExpandedOffset;
                    i = 6;
                } else {
                    f = BottomSheetBehavior.this.collapsedOffset;
                }
            }
            if (BottomSheetBehavior.this.viewDragHelper.settleCapturedViewAt(view.getLeft(), f) != null) {
                BottomSheetBehavior.this.setStateInternal(2.8E-45f);
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
                return;
            }
            BottomSheetBehavior.this.setStateInternal(i);
        }

        public int clampViewPositionVertical(@NonNull View view, int i, int i2) {
            return MathUtils.clamp(i, BottomSheetBehavior.this.getExpandedOffset(), BottomSheetBehavior.this.hideable != 0 ? BottomSheetBehavior.this.parentHeight : BottomSheetBehavior.this.collapsedOffset);
        }

        public int clampViewPositionHorizontal(@NonNull View view, int i, int i2) {
            return view.getLeft();
        }

        public int getViewVerticalDragRange(@NonNull View view) {
            if (BottomSheetBehavior.this.hideable != null) {
                return BottomSheetBehavior.this.parentHeight;
            }
            return BottomSheetBehavior.this.collapsedOffset;
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new C00621();
        final int state;

        /* renamed from: android.support.design.widget.BottomSheetBehavior$SavedState$1 */
        static class C00621 implements ClassLoaderCreator<SavedState> {
            C00621() {
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel) {
            this(parcel, null);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.state = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        attributeSet = context.obtainStyledAttributes(attributeSet, C0026R.styleable.BottomSheetBehavior_Layout);
        TypedValue peekValue = attributeSet.peekValue(C0026R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (peekValue == null || peekValue.data != -1) {
            setPeekHeight(attributeSet.getDimensionPixelSize(C0026R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        } else {
            setPeekHeight(peekValue.data);
        }
        setHideable(attributeSet.getBoolean(C0026R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setFitToContents(attributeSet.getBoolean(C0026R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        setSkipCollapsed(attributeSet.getBoolean(C0026R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        attributeSet.recycle();
        this.maximumVelocity = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), this.state);
    }

    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        if (savedState.state != 1) {
            if (savedState.state != 2) {
                this.state = savedState.state;
                return;
            }
        }
        this.state = 4;
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            v.setFitsSystemWindows(true);
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i);
        this.parentHeight = coordinatorLayout.getHeight();
        if (this.peekHeightAuto != 0) {
            if (this.peekHeightMin == 0) {
                this.peekHeightMin = coordinatorLayout.getResources().getDimensionPixelSize(C0026R.dimen.design_bottom_sheet_peek_height_min);
            }
            this.lastPeekHeight = Math.max(this.peekHeightMin, this.parentHeight - ((coordinatorLayout.getWidth() * 9) / 16));
        } else {
            this.lastPeekHeight = this.peekHeight;
        }
        this.fitToContentsOffset = Math.max(0, this.parentHeight - v.getHeight());
        this.halfExpandedOffset = this.parentHeight / 2;
        calculateCollapsedOffset();
        i = this.state;
        if (i == 3) {
            ViewCompat.offsetTopAndBottom(v, getExpandedOffset());
        } else if (i == 6) {
            ViewCompat.offsetTopAndBottom(v, this.halfExpandedOffset);
        } else if (this.hideable && i == 5) {
            ViewCompat.offsetTopAndBottom(v, this.parentHeight);
        } else {
            i = this.state;
            if (i == 4) {
                ViewCompat.offsetTopAndBottom(v, this.collapsedOffset);
            } else if (i == 1 || i == 2) {
                ViewCompat.offsetTopAndBottom(v, top - v.getTop());
            }
        }
        if (this.viewDragHelper == 0) {
            this.viewDragHelper = ViewDragHelper.create(coordinatorLayout, this.dragCallback);
        }
        this.viewRef = new WeakReference(v);
        this.nestedScrollingChildRef = new WeakReference(findScrollingChild(v));
        return true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z = false;
        if (v.isShown()) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                reset();
            }
            if (this.velocityTracker == null) {
                this.velocityTracker = VelocityTracker.obtain();
            }
            this.velocityTracker.addMovement(motionEvent);
            View view = null;
            if (actionMasked != 3) {
                switch (actionMasked) {
                    case 0:
                        int x = (int) motionEvent.getX();
                        this.initialY = (int) motionEvent.getY();
                        WeakReference weakReference = this.nestedScrollingChildRef;
                        View view2 = weakReference != null ? (View) weakReference.get() : null;
                        if (view2 != null && coordinatorLayout.isPointInChildBounds(view2, x, this.initialY)) {
                            this.activePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                            this.touchingScrollingChild = true;
                        }
                        v = (this.activePointerId == -1 && coordinatorLayout.isPointInChildBounds(v, x, this.initialY) == null) ? true : null;
                        this.ignoreEvents = v;
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
            this.touchingScrollingChild = false;
            this.activePointerId = -1;
            if (this.ignoreEvents != null) {
                this.ignoreEvents = false;
                return false;
            }
            if (this.ignoreEvents == null) {
                v = this.viewDragHelper;
                if (!(v == null || v.shouldInterceptTouchEvent(motionEvent) == null)) {
                    return true;
                }
            }
            v = this.nestedScrollingChildRef;
            if (v != null) {
                view = (View) v.get();
            }
            if (actionMasked == 2 && r4 != null && this.ignoreEvents == null && this.state != 1 && coordinatorLayout.isPointInChildBounds(r4, (int) motionEvent.getX(), (int) motionEvent.getY()) == null && this.viewDragHelper != null && Math.abs(((float) this.initialY) - motionEvent.getY()) > ((float) this.viewDragHelper.getTouchSlop())) {
                z = true;
            }
            return z;
        }
        this.ignoreEvents = true;
        return false;
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (v.isShown() == null) {
            return null;
        }
        coordinatorLayout = motionEvent.getActionMasked();
        if (this.state == 1 && coordinatorLayout == null) {
            return true;
        }
        ViewDragHelper viewDragHelper = this.viewDragHelper;
        if (viewDragHelper != null) {
            viewDragHelper.processTouchEvent(motionEvent);
        }
        if (coordinatorLayout == null) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (coordinatorLayout == 2 && this.ignoreEvents == null && Math.abs(((float) this.initialY) - motionEvent.getY()) > ((float) this.viewDragHelper.getTouchSlop())) {
            this.viewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return this.ignoreEvents ^ 1;
    }

    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, @NonNull View view2, int i, int i2) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        if ((i & 2) != null) {
            return true;
        }
        return false;
    }

    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i, int i2, @NonNull int[] iArr, int i3) {
        if (i3 != 1 && view == ((View) this.nestedScrollingChildRef.get())) {
            i = v.getTop();
            i3 = i - i2;
            if (i2 > 0) {
                if (i3 < getExpandedOffset()) {
                    iArr[1] = i - getExpandedOffset();
                    ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                    setStateInternal(3);
                } else {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    setStateInternal(1);
                }
            } else if (i2 < 0 && view.canScrollVertically(-1) == null) {
                view = this.collapsedOffset;
                if (i3 > view) {
                    if (this.hideable == 0) {
                        iArr[1] = i - view;
                        ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                        setStateInternal(4);
                    }
                }
                iArr[1] = i2;
                ViewCompat.offsetTopAndBottom(v, -i2);
                setStateInternal(1);
            }
            dispatchOnSlide(v.getTop());
            this.lastNestedScrollDy = i2;
            this.nestedScrolled = true;
        }
    }

    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i) {
        int i2 = 3;
        if (v.getTop() == getExpandedOffset()) {
            setStateInternal(3);
            return;
        }
        if (view == this.nestedScrollingChildRef.get()) {
            if (this.nestedScrolled != null) {
                if (this.lastNestedScrollDy > null) {
                    coordinatorLayout = getExpandedOffset();
                } else if (this.hideable != null && shouldHide(v, getYVelocity()) != null) {
                    coordinatorLayout = this.parentHeight;
                    i2 = 5;
                } else if (this.lastNestedScrollDy == null) {
                    coordinatorLayout = v.getTop();
                    if (!this.fitToContents) {
                        int i3 = this.halfExpandedOffset;
                        if (coordinatorLayout < i3) {
                            if (coordinatorLayout < Math.abs(coordinatorLayout - this.collapsedOffset)) {
                                coordinatorLayout = null;
                            } else {
                                coordinatorLayout = this.halfExpandedOffset;
                                i2 = 6;
                            }
                        } else if (Math.abs(coordinatorLayout - i3) < Math.abs(coordinatorLayout - this.collapsedOffset)) {
                            coordinatorLayout = this.halfExpandedOffset;
                            i2 = 6;
                        } else {
                            coordinatorLayout = this.collapsedOffset;
                            i2 = 4;
                        }
                    } else if (Math.abs(coordinatorLayout - this.fitToContentsOffset) < Math.abs(coordinatorLayout - this.collapsedOffset)) {
                        coordinatorLayout = this.fitToContentsOffset;
                    } else {
                        coordinatorLayout = this.collapsedOffset;
                        i2 = 4;
                    }
                } else {
                    coordinatorLayout = this.collapsedOffset;
                    i2 = 4;
                }
                if (this.viewDragHelper.smoothSlideViewTo(v, v.getLeft(), coordinatorLayout) != null) {
                    setStateInternal(2);
                    ViewCompat.postOnAnimation(v, new SettleRunnable(v, i2));
                } else {
                    setStateInternal(i2);
                }
                this.nestedScrolled = null;
            }
        }
    }

    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, float f, float f2) {
        return (view != this.nestedScrollingChildRef.get() || (this.state == 3 && super.onNestedPreFling(coordinatorLayout, v, view, f, f2) == null)) ? null : true;
    }

    public boolean isFitToContents() {
        return this.fitToContents;
    }

    public void setFitToContents(boolean z) {
        if (this.fitToContents != z) {
            this.fitToContents = z;
            if (this.viewRef) {
                calculateCollapsedOffset();
            }
            z = (this.fitToContents && this.state) ? true : this.state;
            setStateInternal(z);
        }
    }

    public final void setPeekHeight(int i) {
        boolean z = true;
        if (i != -1) {
            if (!this.peekHeightAuto) {
                if (this.peekHeight != i) {
                }
            }
            this.peekHeightAuto = false;
            this.peekHeight = Math.max(0, i);
            this.collapsedOffset = this.parentHeight - i;
            if (!z) {
            }
        } else if (this.peekHeightAuto == 0) {
            this.peekHeightAuto = true;
            if (!z && this.state == 4) {
                i = this.viewRef;
                if (i != 0) {
                    View view = (View) i.get();
                    if (view != null) {
                        view.requestLayout();
                        return;
                    }
                    return;
                }
                return;
            }
        }
        z = false;
        if (!z) {
        }
    }

    public final int getPeekHeight() {
        return this.peekHeightAuto ? -1 : this.peekHeight;
    }

    public void setHideable(boolean z) {
        this.hideable = z;
    }

    public boolean isHideable() {
        return this.hideable;
    }

    public void setSkipCollapsed(boolean z) {
        this.skipCollapsed = z;
    }

    public boolean getSkipCollapsed() {
        return this.skipCollapsed;
    }

    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.callback = bottomSheetCallback;
    }

    public final void setState(final int i) {
        if (i != this.state) {
            WeakReference weakReference = this.viewRef;
            if (weakReference == null) {
                if (i == 4 || i == 3 || i == 6 || (this.hideable && i == 5)) {
                    this.state = i;
                }
                return;
            }
            final View view = (View) weakReference.get();
            if (view != null) {
                ViewParent parent = view.getParent();
                if (parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(view)) {
                    view.post(new Runnable() {
                        public void run() {
                            BottomSheetBehavior.this.startSettlingAnimation(view, i);
                        }
                    });
                } else {
                    startSettlingAnimation(view, i);
                }
            }
        }
    }

    public final int getState() {
        return this.state;
    }

    void setStateInternal(int i) {
        if (this.state != i) {
            View view;
            BottomSheetCallback bottomSheetCallback;
            this.state = i;
            if (i != 6) {
                if (i != 3) {
                    if (i == 5 || i == 4) {
                        updateImportantForAccessibility(false);
                    }
                    view = (View) this.viewRef.get();
                    if (view != null) {
                        bottomSheetCallback = this.callback;
                        if (bottomSheetCallback != null) {
                            bottomSheetCallback.onStateChanged(view, i);
                        }
                    }
                }
            }
            updateImportantForAccessibility(true);
            view = (View) this.viewRef.get();
            if (view != null) {
                bottomSheetCallback = this.callback;
                if (bottomSheetCallback != null) {
                    bottomSheetCallback.onStateChanged(view, i);
                }
            }
        }
    }

    private void calculateCollapsedOffset() {
        if (this.fitToContents) {
            this.collapsedOffset = Math.max(this.parentHeight - this.lastPeekHeight, this.fitToContentsOffset);
        } else {
            this.collapsedOffset = this.parentHeight - this.lastPeekHeight;
        }
    }

    private void reset() {
        this.activePointerId = -1;
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
    }

    boolean shouldHide(View view, float f) {
        boolean z = true;
        if (this.skipCollapsed) {
            return true;
        }
        if (view.getTop() < this.collapsedOffset) {
            return false;
        }
        if (Math.abs((((float) view.getTop()) + (f * HIDE_FRICTION)) - ((float) this.collapsedOffset)) / ((float) this.peekHeight) <= HIDE_THRESHOLD) {
            z = false;
        }
        return z;
    }

    @VisibleForTesting
    View findScrollingChild(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i));
                if (findScrollingChild != null) {
                    return findScrollingChild;
                }
            }
        }
        return null;
    }

    private float getYVelocity() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getYVelocity(this.activePointerId);
    }

    private int getExpandedOffset() {
        return this.fitToContents ? this.fitToContentsOffset : 0;
    }

    void startSettlingAnimation(View view, int i) {
        int i2;
        if (i == 4) {
            i2 = this.collapsedOffset;
        } else if (i == 6) {
            int i3 = this.halfExpandedOffset;
            if (this.fitToContents) {
                int i4 = this.fitToContentsOffset;
                if (i3 <= i4) {
                    i2 = i4;
                    i = 3;
                }
            }
            i2 = i3;
        } else if (i == 3) {
            i2 = getExpandedOffset();
        } else if (this.hideable && i == 5) {
            i2 = this.parentHeight;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Illegal state argument: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        if (this.viewDragHelper.smoothSlideViewTo(view, view.getLeft(), i2)) {
            setStateInternal(2);
            ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
            return;
        }
        setStateInternal(i);
    }

    void dispatchOnSlide(int i) {
        View view = (View) this.viewRef.get();
        if (view != null) {
            BottomSheetCallback bottomSheetCallback = this.callback;
            if (bottomSheetCallback != null) {
                int i2 = this.collapsedOffset;
                if (i > i2) {
                    bottomSheetCallback.onSlide(view, ((float) (i2 - i)) / ((float) (this.parentHeight - i2)));
                } else {
                    bottomSheetCallback.onSlide(view, ((float) (i2 - i)) / ((float) (i2 - getExpandedOffset())));
                }
            }
        }
    }

    @VisibleForTesting
    int getPeekHeightMin() {
        return this.peekHeightMin;
    }

    public static <V extends View> BottomSheetBehavior<V> from(V v) {
        v = v.getLayoutParams();
        if (v instanceof LayoutParams) {
            v = ((LayoutParams) v).getBehavior();
            if (v instanceof BottomSheetBehavior) {
                return (BottomSheetBehavior) v;
            }
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    private void updateImportantForAccessibility(boolean z) {
        WeakReference weakReference = this.viewRef;
        if (weakReference != null) {
            ViewParent parent = ((View) weakReference.get()).getParent();
            if (parent instanceof CoordinatorLayout) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
                int childCount = coordinatorLayout.getChildCount();
                if (VERSION.SDK_INT >= 16 && z) {
                    if (this.importantForAccessibilityMap == null) {
                        this.importantForAccessibilityMap = new HashMap(childCount);
                    } else {
                        return;
                    }
                }
                for (int i = 0; i < childCount; i++) {
                    View childAt = coordinatorLayout.getChildAt(i);
                    if (childAt != this.viewRef.get()) {
                        if (z) {
                            if (VERSION.SDK_INT >= 16) {
                                this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                            }
                            ViewCompat.setImportantForAccessibility(childAt, 4);
                        } else {
                            Map map = this.importantForAccessibilityMap;
                            if (map != null && map.containsKey(childAt)) {
                                ViewCompat.setImportantForAccessibility(childAt, ((Integer) this.importantForAccessibilityMap.get(childAt)).intValue());
                            }
                        }
                    }
                }
                if (!z) {
                    this.importantForAccessibilityMap = false;
                }
            }
        }
    }
}
