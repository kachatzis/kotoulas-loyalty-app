package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.C0291R;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ActionBarContainer extends FrameLayout {
    private View mActionBarView;
    Drawable mBackground;
    private View mContextView;
    private int mHeight;
    boolean mIsSplit;
    boolean mIsStacked;
    private boolean mIsTransitioning;
    Drawable mSplitBackground;
    Drawable mStackedBackground;
    private View mTabContainer;

    public ActionMode startActionModeForChild(View view, Callback callback) {
        return null;
    }

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ActionBarContainer(android.content.Context r3, android.util.AttributeSet r4) {
        /*
        r2 = this;
        r2.<init>(r3, r4);
        r0 = new android.support.v7.widget.ActionBarBackgroundDrawable;
        r0.<init>(r2);
        android.support.v4.view.ViewCompat.setBackground(r2, r0);
        r0 = android.support.v7.appcompat.C0291R.styleable.ActionBar;
        r3 = r3.obtainStyledAttributes(r4, r0);
        r4 = android.support.v7.appcompat.C0291R.styleable.ActionBar_background;
        r4 = r3.getDrawable(r4);
        r2.mBackground = r4;
        r4 = android.support.v7.appcompat.C0291R.styleable.ActionBar_backgroundStacked;
        r4 = r3.getDrawable(r4);
        r2.mStackedBackground = r4;
        r4 = android.support.v7.appcompat.C0291R.styleable.ActionBar_height;
        r0 = -1;
        r4 = r3.getDimensionPixelSize(r4, r0);
        r2.mHeight = r4;
        r4 = r2.getId();
        r0 = android.support.v7.appcompat.C0291R.id.split_action_bar;
        r1 = 1;
        if (r4 != r0) goto L_0x003d;
    L_0x0033:
        r2.mIsSplit = r1;
        r4 = android.support.v7.appcompat.C0291R.styleable.ActionBar_backgroundSplit;
        r4 = r3.getDrawable(r4);
        r2.mSplitBackground = r4;
    L_0x003d:
        r3.recycle();
        r3 = r2.mIsSplit;
        r4 = 0;
        if (r3 == 0) goto L_0x004b;
    L_0x0045:
        r3 = r2.mSplitBackground;
        if (r3 != 0) goto L_0x0054;
    L_0x0049:
        r4 = 1;
        goto L_0x0054;
    L_0x004b:
        r3 = r2.mBackground;
        if (r3 != 0) goto L_0x0054;
    L_0x004f:
        r3 = r2.mStackedBackground;
        if (r3 != 0) goto L_0x0054;
    L_0x0053:
        goto L_0x0049;
    L_0x0054:
        r2.setWillNotDraw(r4);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarContainer.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mActionBarView = findViewById(C0291R.id.action_bar);
        this.mContextView = findViewById(C0291R.id.action_context_bar);
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.mBackground;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.mBackground);
        }
        this.mBackground = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            drawable = this.mActionBarView;
            if (drawable != null) {
                this.mBackground.setBounds(drawable.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
            }
        }
        boolean z = true;
        if (this.mIsSplit != null) {
            if (this.mSplitBackground == null) {
                setWillNotDraw(z);
                invalidate();
            }
        } else if (this.mBackground == null && this.mStackedBackground == null) {
            setWillNotDraw(z);
            invalidate();
        }
        z = false;
        setWillNotDraw(z);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2 = this.mStackedBackground;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.mStackedBackground);
        }
        this.mStackedBackground = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.mIsStacked != null) {
                drawable = this.mStackedBackground;
                if (drawable != null) {
                    drawable.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
                }
            }
        }
        boolean z = true;
        if (this.mIsSplit != null) {
            if (this.mSplitBackground == null) {
                setWillNotDraw(z);
                invalidate();
            }
        } else if (this.mBackground == null && this.mStackedBackground == null) {
            setWillNotDraw(z);
            invalidate();
        }
        z = false;
        setWillNotDraw(z);
        invalidate();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSplitBackground(android.graphics.drawable.Drawable r4) {
        /*
        r3 = this;
        r0 = r3.mSplitBackground;
        if (r0 == 0) goto L_0x000d;
    L_0x0004:
        r1 = 0;
        r0.setCallback(r1);
        r0 = r3.mSplitBackground;
        r3.unscheduleDrawable(r0);
    L_0x000d:
        r3.mSplitBackground = r4;
        r0 = 0;
        if (r4 == 0) goto L_0x0028;
    L_0x0012:
        r4.setCallback(r3);
        r4 = r3.mIsSplit;
        if (r4 == 0) goto L_0x0028;
    L_0x0019:
        r4 = r3.mSplitBackground;
        if (r4 == 0) goto L_0x0028;
    L_0x001d:
        r1 = r3.getMeasuredWidth();
        r2 = r3.getMeasuredHeight();
        r4.setBounds(r0, r0, r1, r2);
    L_0x0028:
        r4 = r3.mIsSplit;
        r1 = 1;
        if (r4 == 0) goto L_0x0033;
    L_0x002d:
        r4 = r3.mSplitBackground;
        if (r4 != 0) goto L_0x003c;
    L_0x0031:
        r0 = 1;
        goto L_0x003c;
    L_0x0033:
        r4 = r3.mBackground;
        if (r4 != 0) goto L_0x003c;
    L_0x0037:
        r4 = r3.mStackedBackground;
        if (r4 != 0) goto L_0x003c;
    L_0x003b:
        goto L_0x0031;
    L_0x003c:
        r3.setWillNotDraw(r0);
        r3.invalidate();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarContainer.setSplitBackground(android.graphics.drawable.Drawable):void");
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        i = i == 0 ? 1 : 0;
        Drawable drawable = this.mBackground;
        if (drawable != null) {
            drawable.setVisible(i, false);
        }
        drawable = this.mStackedBackground;
        if (drawable != null) {
            drawable.setVisible(i, false);
        }
        drawable = this.mSplitBackground;
        if (drawable != null) {
            drawable.setVisible(i, false);
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return ((drawable != this.mBackground || this.mIsSplit) && (!(drawable == this.mStackedBackground && this.mIsStacked) && (!(drawable == this.mSplitBackground && this.mIsSplit) && super.verifyDrawable(drawable) == null))) ? null : true;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mBackground;
        if (drawable != null && drawable.isStateful()) {
            this.mBackground.setState(getDrawableState());
        }
        drawable = this.mStackedBackground;
        if (drawable != null && drawable.isStateful()) {
            this.mStackedBackground.setState(getDrawableState());
        }
        drawable = this.mSplitBackground;
        if (drawable != null && drawable.isStateful()) {
            this.mSplitBackground.setState(getDrawableState());
        }
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mBackground;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        drawable = this.mStackedBackground;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        drawable = this.mSplitBackground;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public void setTransitioning(boolean z) {
        this.mIsTransitioning = z;
        setDescendantFocusability(z ? true : true);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mIsTransitioning) {
            if (super.onInterceptTouchEvent(motionEvent) == null) {
                return null;
            }
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.mTabContainer;
        if (view != null) {
            removeView(view);
        }
        this.mTabContainer = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public View getTabContainer() {
        return this.mTabContainer;
    }

    public ActionMode startActionModeForChild(View view, Callback callback, int i) {
        return i != 0 ? super.startActionModeForChild(view, callback, i) : null;
    }

    private boolean isCollapsed(View view) {
        if (!(view == null || view.getVisibility() == 8)) {
            if (view.getMeasuredHeight() != null) {
                return null;
            }
        }
        return true;
    }

    private int getMeasuredHeightWithMargins(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return (view.getMeasuredHeight() + layoutParams.topMargin) + layoutParams.bottomMargin;
    }

    public void onMeasure(int i, int i2) {
        int i3;
        if (this.mActionBarView == null && MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            i3 = this.mHeight;
            if (i3 >= 0) {
                i2 = MeasureSpec.makeMeasureSpec(Math.min(i3, MeasureSpec.getSize(i2)), Integer.MIN_VALUE);
            }
        }
        super.onMeasure(i, i2);
        if (this.mActionBarView != 0) {
            i = MeasureSpec.getMode(i2);
            View view = this.mTabContainer;
            if (!(view == null || view.getVisibility() == 8 || i == 1073741824)) {
                i3 = !isCollapsed(this.mActionBarView) ? getMeasuredHeightWithMargins(this.mActionBarView) : !isCollapsed(this.mContextView) ? getMeasuredHeightWithMargins(this.mContextView) : 0;
                setMeasuredDimension(getMeasuredWidth(), Math.min(i3 + getMeasuredHeightWithMargins(this.mTabContainer), i == Integer.MIN_VALUE ? MeasureSpec.getSize(i2) : ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED));
            }
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        z = this.mTabContainer;
        i4 = 1;
        int i5 = 0;
        boolean z2 = z && z.getVisibility() != 8;
        if (z && z.getVisibility() != 8) {
            i2 = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) z.getLayoutParams();
            z.layout(i, (i2 - z.getMeasuredHeight()) - layoutParams.bottomMargin, i3, i2 - layoutParams.bottomMargin);
        }
        if (this.mIsSplit != 0) {
            z = this.mSplitBackground;
            if (z) {
                z.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                i4 = 0;
            }
        } else {
            if (this.mBackground != 0) {
                if (this.mActionBarView.getVisibility() == 0) {
                    this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
                } else {
                    i = this.mContextView;
                    if (i == 0 || i.getVisibility() != 0) {
                        this.mBackground.setBounds(0, 0, 0, 0);
                    } else {
                        this.mBackground.setBounds(this.mContextView.getLeft(), this.mContextView.getTop(), this.mContextView.getRight(), this.mContextView.getBottom());
                    }
                }
                i5 = 1;
            }
            this.mIsStacked = z2;
            if (z2) {
                i = this.mStackedBackground;
                if (i != 0) {
                    i.setBounds(z.getLeft(), z.getTop(), z.getRight(), z.getBottom());
                }
            }
            i4 = i5;
        }
        if (i4 != 0) {
            invalidate();
        }
    }
}
