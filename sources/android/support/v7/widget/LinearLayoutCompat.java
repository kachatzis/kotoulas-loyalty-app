package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0291R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public static class LayoutParams extends MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            context = context.obtainStyledAttributes(attributeSet, C0291R.styleable.LinearLayoutCompat_Layout);
            this.weight = context.getFloat(C0291R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = context.getInt(C0291R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            context.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = 0;
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2);
            this.gravity = -1;
            this.weight = f;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
            this.weight = layoutParams.weight;
            this.gravity = layoutParams.gravity;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    int measureNullChild(int i) {
        return 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        context = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0291R.styleable.LinearLayoutCompat, i, 0);
        attributeSet = context.getInt(C0291R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (attributeSet >= null) {
            setOrientation(attributeSet);
        }
        attributeSet = context.getInt(C0291R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (attributeSet >= null) {
            setGravity(attributeSet);
        }
        attributeSet = context.getBoolean(C0291R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (attributeSet == null) {
            setBaselineAligned(attributeSet);
        }
        this.mWeightSum = context.getFloat(C0291R.styleable.LinearLayoutCompat_android_weightSum, -1082130432);
        this.mBaselineAlignedChildIndex = context.getInt(C0291R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = context.getBoolean(C0291R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(context.getDrawable(C0291R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = context.getInt(C0291R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = context.getDimensionPixelSize(C0291R.styleable.LinearLayoutCompat_dividerPadding, 0);
        context.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean z = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    protected void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        int i = 0;
        while (i < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
            i++;
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                virtualChildCount = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                virtualChildCount = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, virtualChildCount);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int i = 0;
        while (i < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                int right;
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (isLayoutRtl) {
                    right = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    right = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, right);
            }
            i++;
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    virtualChildCount = (virtualChildAt2.getLeft() - layoutParams2.leftMargin) - this.mDividerWidth;
                } else {
                    virtualChildCount = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            } else if (isLayoutRtl) {
                virtualChildCount = getPaddingLeft();
            } else {
                virtualChildCount = (getWidth() - getPaddingRight()) - this.mDividerWidth;
            }
            drawVerticalDivider(canvas, virtualChildCount);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public int getBaseline() {
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i = this.mBaselineAlignedChildIndex;
        if (childCount > i) {
            View childAt = getChildAt(i);
            i = childAt.getBaseline();
            if (i != -1) {
                int i2 = this.mBaselineChildTop;
                if (this.mOrientation == 1) {
                    int i3 = this.mGravity & 112;
                    if (i3 != 48) {
                        if (i3 == 16) {
                            i2 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                        } else if (i3 == 80) {
                            i2 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                        }
                    }
                }
                return (i2 + ((LayoutParams) childAt.getLayoutParams()).topMargin) + i;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("base aligned child index out of range (0, ");
            stringBuilder.append(getChildCount());
            stringBuilder.append(")");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.mBaselineAlignedChildIndex = i;
    }

    View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    protected void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    @RestrictTo({Scope.LIBRARY})
    protected boolean hasDividerBeforeChildAt(int i) {
        boolean z = false;
        if (i == 0) {
            if ((this.mShowDividers & 1) != 0) {
                z = true;
            }
            return z;
        } else if (i == getChildCount()) {
            if ((this.mShowDividers & 4) != 0) {
                z = true;
            }
            return z;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            for (i--; i >= 0; i--) {
                if (getChildAt(i).getVisibility() != 8) {
                    z = true;
                    break;
                }
            }
            return z;
        }
    }

    void measureVertical(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = i;
        int i9 = i2;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int i10 = this.mBaselineAlignedChildIndex;
        boolean z = this.mUseLargestChild;
        float f = 0.0f;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        Object obj = null;
        Object obj2 = 1;
        Object obj3 = null;
        while (true) {
            i3 = 8;
            i4 = i14;
            if (i16 >= virtualChildCount) {
                break;
            }
            View view;
            View virtualChildAt = getVirtualChildAt(i16);
            if (virtualChildAt == null) {
                r7.mTotalLength += measureNullChild(i16);
                i5 = virtualChildCount;
                i14 = i4;
                i4 = mode2;
            } else {
                int i17 = i11;
                if (virtualChildAt.getVisibility() == 8) {
                    i16 += getChildrenSkipCount(virtualChildAt, i16);
                    i5 = virtualChildCount;
                    i14 = i4;
                    i11 = i17;
                    i4 = mode2;
                } else {
                    int i18;
                    int i19;
                    Object obj4;
                    if (hasDividerBeforeChildAt(i16)) {
                        r7.mTotalLength += r7.mDividerHeight;
                    }
                    LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                    float f2 = f + layoutParams.weight;
                    int i20;
                    if (mode2 == 1073741824 && layoutParams.height == 0 && layoutParams.weight > 0.0f) {
                        i6 = r7.mTotalLength;
                        i20 = i12;
                        r7.mTotalLength = Math.max(i6, (layoutParams.topMargin + i6) + layoutParams.bottomMargin);
                        i6 = i13;
                        view = virtualChildAt;
                        i18 = i15;
                        i5 = virtualChildCount;
                        i8 = i17;
                        i19 = i20;
                        obj = 1;
                        virtualChildCount = i16;
                        i7 = i4;
                        i4 = mode2;
                        mode2 = i7;
                    } else {
                        i20 = i12;
                        if (layoutParams.height != 0 || layoutParams.weight <= 0.0f) {
                            i12 = Integer.MIN_VALUE;
                        } else {
                            layoutParams.height = -2;
                            i12 = 0;
                        }
                        i8 = i17;
                        int i21 = i12;
                        i19 = i20;
                        i9 = i13;
                        View view2 = virtualChildAt;
                        i5 = virtualChildCount;
                        i7 = i4;
                        i4 = mode2;
                        mode2 = i7;
                        i18 = i15;
                        virtualChildCount = i16;
                        measureChildBeforeLayout(virtualChildAt, i16, i, 0, i2, f2 == 0.0f ? r7.mTotalLength : 0);
                        i6 = i21;
                        if (i6 != Integer.MIN_VALUE) {
                            layoutParams.height = i6;
                        }
                        i6 = view2.getMeasuredHeight();
                        i11 = r7.mTotalLength;
                        view = view2;
                        r7.mTotalLength = Math.max(i11, (((i11 + i6) + layoutParams.topMargin) + layoutParams.bottomMargin) + getNextLocationOffset(view));
                        i6 = z ? Math.max(i6, i9) : i9;
                    }
                    if (i10 >= 0 && i10 == virtualChildCount + 1) {
                        r7.mBaselineChildTop = r7.mTotalLength;
                    }
                    if (virtualChildCount < i10) {
                        if (layoutParams.weight > 0.0f) {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                    }
                    if (mode == 1073741824 || layoutParams.width != -1) {
                        obj4 = null;
                    } else {
                        obj4 = 1;
                        obj3 = 1;
                    }
                    i12 = layoutParams.leftMargin + layoutParams.rightMargin;
                    i14 = view.getMeasuredWidth() + i12;
                    i15 = Math.max(i19, i14);
                    i16 = View.combineMeasuredStates(i8, view.getMeasuredState());
                    Object obj5 = (obj2 == null || layoutParams.width != -1) ? null : 1;
                    if (layoutParams.weight > 0.0f) {
                        if (obj4 == null) {
                            i12 = i14;
                        }
                        mode2 = Math.max(mode2, i12);
                        i11 = i18;
                    } else {
                        if (obj4 == null) {
                            i12 = i14;
                        }
                        i11 = Math.max(i18, i12);
                    }
                    i13 = i6;
                    obj2 = obj5;
                    i14 = mode2;
                    f = f2;
                    i7 = i15;
                    i15 = i11;
                    i11 = i16;
                    i16 = getChildrenSkipCount(view, virtualChildCount) + virtualChildCount;
                    i12 = i7;
                }
            }
            i16++;
            mode2 = i4;
            virtualChildCount = i5;
            i8 = i;
            i9 = i2;
        }
        i8 = i11;
        i9 = i13;
        i11 = i15;
        i5 = virtualChildCount;
        i15 = i12;
        i7 = i4;
        i4 = mode2;
        mode2 = i7;
        if (r7.mTotalLength > 0) {
            i12 = i5;
            if (hasDividerBeforeChildAt(i12)) {
                r7.mTotalLength += r7.mDividerHeight;
            }
        } else {
            i12 = i5;
        }
        if (z) {
            i13 = i4;
            if (i13 == Integer.MIN_VALUE || i13 == 0) {
                r7.mTotalLength = 0;
                i14 = 0;
                while (i14 < i12) {
                    View virtualChildAt2 = getVirtualChildAt(i14);
                    if (virtualChildAt2 == null) {
                        r7.mTotalLength += measureNullChild(i14);
                    } else if (virtualChildAt2.getVisibility() == i3) {
                        i14 += getChildrenSkipCount(virtualChildAt2, i14);
                    } else {
                        LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                        i10 = r7.mTotalLength;
                        r7.mTotalLength = Math.max(i10, (((i10 + i9) + layoutParams2.topMargin) + layoutParams2.bottomMargin) + getNextLocationOffset(virtualChildAt2));
                    }
                    i14++;
                    i3 = 8;
                }
            }
        } else {
            i13 = i4;
        }
        r7.mTotalLength += getPaddingTop() + getPaddingBottom();
        i3 = i9;
        i16 = i2;
        i14 = View.resolveSizeAndState(Math.max(r7.mTotalLength, getSuggestedMinimumHeight()), i16, 0);
        i9 = (ViewCompat.MEASURED_SIZE_MASK & i14) - r7.mTotalLength;
        if (obj == null) {
            if (i9 == 0 || f <= 0.0f) {
                i6 = Math.max(i11, mode2);
                if (z && i13 != 1073741824) {
                    for (i11 = 0; i11 < i12; i11++) {
                        view = getVirtualChildAt(i11);
                        if (view != null) {
                            if (view.getVisibility() != 8) {
                                if (((LayoutParams) view.getLayoutParams()).weight > 0.0f) {
                                    view.measure(MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(i3, 1073741824));
                                }
                            }
                        }
                    }
                }
                i11 = i8;
                virtualChildCount = i;
                if (obj2 == null || mode == 1073741824) {
                    i6 = i15;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(i6 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), virtualChildCount, i11), i14);
                if (obj3 != null) {
                    forceUniformWidth(i12, i16);
                }
            }
        }
        float f3 = r7.mWeightSum;
        if (f3 > 0.0f) {
            f = f3;
        }
        r7.mTotalLength = 0;
        float f4 = f;
        i6 = 0;
        i7 = i8;
        i8 = i11;
        i11 = i7;
        while (i6 < i12) {
            float f5;
            View virtualChildAt3 = getVirtualChildAt(i6);
            if (virtualChildAt3.getVisibility() == 8) {
                f5 = f4;
                virtualChildCount = i;
            } else {
                int i22;
                Object obj6;
                Object obj7;
                LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                f3 = layoutParams3.weight;
                if (f3 > 0.0f) {
                    int i23 = (int) ((((float) i9) * f3) / f4);
                    i22 = i9 - i23;
                    f5 = f4 - f3;
                    i9 = getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + layoutParams3.leftMargin) + layoutParams3.rightMargin, layoutParams3.width);
                    if (layoutParams3.height == 0) {
                        i3 = 1073741824;
                        if (i13 == 1073741824) {
                            if (i23 <= 0) {
                                i23 = 0;
                            }
                            virtualChildAt3.measure(i9, MeasureSpec.makeMeasureSpec(i23, 1073741824));
                            i11 = View.combineMeasuredStates(i11, virtualChildAt3.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                        }
                    } else {
                        i3 = 1073741824;
                    }
                    i23 = virtualChildAt3.getMeasuredHeight() + i23;
                    if (i23 < 0) {
                        i23 = 0;
                    }
                    virtualChildAt3.measure(i9, MeasureSpec.makeMeasureSpec(i23, i3));
                    i11 = View.combineMeasuredStates(i11, virtualChildAt3.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                } else {
                    f3 = f4;
                    virtualChildCount = i;
                    i22 = i9;
                    f5 = f3;
                }
                i9 = layoutParams3.leftMargin + layoutParams3.rightMargin;
                i3 = virtualChildAt3.getMeasuredWidth() + i9;
                i15 = Math.max(i15, i3);
                if (mode != 1073741824) {
                    i5 = i11;
                    i11 = -1;
                    if (layoutParams3.width == -1) {
                        obj6 = 1;
                        if (obj6 != null) {
                            i9 = i3;
                        }
                        i8 = Math.max(i8, i9);
                        obj7 = (obj2 == null && layoutParams3.width == i11) ? 1 : null;
                        i3 = r7.mTotalLength;
                        r7.mTotalLength = Math.max(i3, (((virtualChildAt3.getMeasuredHeight() + i3) + layoutParams3.topMargin) + layoutParams3.bottomMargin) + getNextLocationOffset(virtualChildAt3));
                        obj2 = obj7;
                        i9 = i22;
                        i11 = i5;
                    }
                } else {
                    i5 = i11;
                    i11 = -1;
                }
                obj6 = null;
                if (obj6 != null) {
                    i9 = i3;
                }
                i8 = Math.max(i8, i9);
                if (obj2 == null) {
                }
                i3 = r7.mTotalLength;
                r7.mTotalLength = Math.max(i3, (((virtualChildAt3.getMeasuredHeight() + i3) + layoutParams3.topMargin) + layoutParams3.bottomMargin) + getNextLocationOffset(virtualChildAt3));
                obj2 = obj7;
                i9 = i22;
                i11 = i5;
            }
            i6++;
            f4 = f5;
        }
        virtualChildCount = i;
        r7.mTotalLength += getPaddingTop() + getPaddingBottom();
        i6 = i8;
        if (obj2 == null) {
        }
        i6 = i15;
        setMeasuredDimension(View.resolveSizeAndState(Math.max(i6 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), virtualChildCount, i11), i14);
        if (obj3 != null) {
            forceUniformWidth(i12, i16);
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    void measureHorizontal(int i, int i2) {
        int[] iArr;
        boolean z;
        boolean z2;
        int i3;
        View view;
        int baseline;
        int i4;
        int i5;
        LayoutParams layoutParams;
        float f;
        float f2;
        View virtualChildAt;
        float f3;
        float f4;
        int i6;
        Object obj;
        Object obj2;
        int i7 = i;
        int i8 = i2;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        if (this.mMaxAscent == null || r7.mMaxDescent == null) {
            r7.mMaxAscent = new int[4];
            r7.mMaxDescent = new int[4];
        }
        int[] iArr2 = r7.mMaxAscent;
        int[] iArr3 = r7.mMaxDescent;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z3 = r7.mBaselineAligned;
        boolean z4 = r7.mUseLargestChild;
        int i9 = 1073741824;
        Object obj3 = mode == 1073741824 ? 1 : null;
        float f5 = 0.0f;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        Object obj4 = null;
        int i15 = 0;
        Object obj5 = 1;
        Object obj6 = null;
        while (true) {
            iArr = iArr3;
            if (i10 >= virtualChildCount) {
                break;
            }
            int measuredHeight;
            int combineMeasuredStates;
            View virtualChildAt2 = getVirtualChildAt(i10);
            if (virtualChildAt2 == null) {
                r7.mTotalLength += measureNullChild(i10);
                z = z4;
                z2 = z3;
            } else if (virtualChildAt2.getVisibility() == 8) {
                i10 += getChildrenSkipCount(virtualChildAt2, i10);
                z = z4;
                z2 = z3;
            } else {
                int i16;
                Object obj7;
                Object obj8;
                if (hasDividerBeforeChildAt(i10)) {
                    r7.mTotalLength += r7.mDividerWidth;
                }
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                float f6 = f5 + layoutParams2.weight;
                if (mode == i9 && layoutParams2.width == 0 && layoutParams2.weight > 0.0f) {
                    if (obj3 != null) {
                        r7.mTotalLength += layoutParams2.leftMargin + layoutParams2.rightMargin;
                    } else {
                        i3 = r7.mTotalLength;
                        r7.mTotalLength = Math.max(i3, (layoutParams2.leftMargin + i3) + layoutParams2.rightMargin);
                    }
                    if (z3) {
                        i9 = MeasureSpec.makeMeasureSpec(0, 0);
                        virtualChildAt2.measure(i9, i9);
                        i16 = i10;
                        z = z4;
                        z2 = z3;
                        view = virtualChildAt2;
                    } else {
                        i16 = i10;
                        z = z4;
                        z2 = z3;
                        view = virtualChildAt2;
                        i10 = 1073741824;
                        obj4 = 1;
                        if (mode2 == i10) {
                        }
                        obj7 = null;
                        i9 = layoutParams2.topMargin + layoutParams2.bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i9;
                        combineMeasuredStates = View.combineMeasuredStates(i15, view.getMeasuredState());
                        if (z2) {
                            i10 = i12;
                        } else {
                            baseline = view.getBaseline();
                            if (baseline != -1) {
                                i10 = i12;
                            } else {
                                if (layoutParams2.gravity < 0) {
                                }
                                i8 = ((((layoutParams2.gravity < 0 ? r7.mGravity : layoutParams2.gravity) & 112) >> 4) & -2) >> 1;
                                iArr2[i8] = Math.max(iArr2[i8], baseline);
                                iArr[i8] = Math.max(iArr[i8], measuredHeight - baseline);
                                i10 = i12;
                            }
                        }
                        i10 = Math.max(i10, measuredHeight);
                        if (obj5 == null) {
                        }
                        if (layoutParams2.weight > 0.0f) {
                            i4 = i14;
                            if (obj7 != null) {
                                measuredHeight = i9;
                            }
                            i13 = Math.max(i13, measuredHeight);
                            i14 = i4;
                            i4 = i16;
                        } else {
                            if (obj7 != null) {
                                i9 = measuredHeight;
                            }
                            i14 = Math.max(i14, i9);
                            i4 = i16;
                        }
                        i12 = i10;
                        i15 = combineMeasuredStates;
                        obj5 = obj8;
                        i10 = getChildrenSkipCount(view, i4) + i4;
                        f5 = f6;
                    }
                } else {
                    if (layoutParams2.width != 0 || layoutParams2.weight <= 0.0f) {
                        i9 = Integer.MIN_VALUE;
                    } else {
                        layoutParams2.width = -2;
                        i9 = 0;
                    }
                    i16 = i10;
                    int i17 = i9;
                    z = z4;
                    z2 = z3;
                    View view2 = virtualChildAt2;
                    measureChildBeforeLayout(virtualChildAt2, i16, i, f6 == 0.0f ? r7.mTotalLength : 0, i2, 0);
                    i3 = i17;
                    if (i3 != Integer.MIN_VALUE) {
                        layoutParams2.width = i3;
                    }
                    i3 = view2.getMeasuredWidth();
                    if (obj3 != null) {
                        view = view2;
                        r7.mTotalLength += ((layoutParams2.leftMargin + i3) + layoutParams2.rightMargin) + getNextLocationOffset(view);
                    } else {
                        view = view2;
                        i10 = r7.mTotalLength;
                        r7.mTotalLength = Math.max(i10, (((i10 + i3) + layoutParams2.leftMargin) + layoutParams2.rightMargin) + getNextLocationOffset(view));
                    }
                    if (z) {
                        i11 = Math.max(i3, i11);
                        i10 = 1073741824;
                        if (mode2 == i10 && layoutParams2.height == -1) {
                            obj7 = 1;
                            obj6 = 1;
                        } else {
                            obj7 = null;
                        }
                        i9 = layoutParams2.topMargin + layoutParams2.bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i9;
                        combineMeasuredStates = View.combineMeasuredStates(i15, view.getMeasuredState());
                        if (z2) {
                            baseline = view.getBaseline();
                            if (baseline != -1) {
                                i8 = ((((layoutParams2.gravity < 0 ? r7.mGravity : layoutParams2.gravity) & 112) >> 4) & -2) >> 1;
                                iArr2[i8] = Math.max(iArr2[i8], baseline);
                                iArr[i8] = Math.max(iArr[i8], measuredHeight - baseline);
                                i10 = i12;
                            } else {
                                i10 = i12;
                            }
                        } else {
                            i10 = i12;
                        }
                        i10 = Math.max(i10, measuredHeight);
                        obj8 = (obj5 == null && layoutParams2.height == -1) ? 1 : null;
                        if (layoutParams2.weight > 0.0f) {
                            if (obj7 != null) {
                                i9 = measuredHeight;
                            }
                            i14 = Math.max(i14, i9);
                            i4 = i16;
                        } else {
                            i4 = i14;
                            if (obj7 != null) {
                                measuredHeight = i9;
                            }
                            i13 = Math.max(i13, measuredHeight);
                            i14 = i4;
                            i4 = i16;
                        }
                        i12 = i10;
                        i15 = combineMeasuredStates;
                        obj5 = obj8;
                        i10 = getChildrenSkipCount(view, i4) + i4;
                        f5 = f6;
                    }
                }
                i10 = 1073741824;
                if (mode2 == i10) {
                }
                obj7 = null;
                i9 = layoutParams2.topMargin + layoutParams2.bottomMargin;
                measuredHeight = view.getMeasuredHeight() + i9;
                combineMeasuredStates = View.combineMeasuredStates(i15, view.getMeasuredState());
                if (z2) {
                    baseline = view.getBaseline();
                    if (baseline != -1) {
                        if (layoutParams2.gravity < 0) {
                        }
                        i8 = ((((layoutParams2.gravity < 0 ? r7.mGravity : layoutParams2.gravity) & 112) >> 4) & -2) >> 1;
                        iArr2[i8] = Math.max(iArr2[i8], baseline);
                        iArr[i8] = Math.max(iArr[i8], measuredHeight - baseline);
                        i10 = i12;
                    } else {
                        i10 = i12;
                    }
                } else {
                    i10 = i12;
                }
                i10 = Math.max(i10, measuredHeight);
                if (obj5 == null) {
                }
                if (layoutParams2.weight > 0.0f) {
                    if (obj7 != null) {
                        i9 = measuredHeight;
                    }
                    i14 = Math.max(i14, i9);
                    i4 = i16;
                } else {
                    i4 = i14;
                    if (obj7 != null) {
                        measuredHeight = i9;
                    }
                    i13 = Math.max(i13, measuredHeight);
                    i14 = i4;
                    i4 = i16;
                }
                i12 = i10;
                i15 = combineMeasuredStates;
                obj5 = obj8;
                i10 = getChildrenSkipCount(view, i4) + i4;
                f5 = f6;
            }
            i10++;
            iArr3 = iArr;
            z4 = z;
            z3 = z2;
            i9 = 1073741824;
            i8 = i2;
        }
        z = z4;
        z2 = z3;
        i10 = i12;
        i9 = i13;
        i4 = i14;
        baseline = i15;
        if (r7.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            r7.mTotalLength += r7.mDividerWidth;
        }
        if (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1) {
            if (iArr2[3] == -1) {
                i14 = baseline;
                if (z || !(mode == Integer.MIN_VALUE || mode == 0)) {
                    i13 = i10;
                } else {
                    r7.mTotalLength = 0;
                    i5 = 0;
                    while (i5 < virtualChildCount) {
                        View virtualChildAt3 = getVirtualChildAt(i5);
                        if (virtualChildAt3 == null) {
                            r7.mTotalLength += measureNullChild(i5);
                            i13 = i10;
                        } else if (virtualChildAt3.getVisibility() == 8) {
                            i5 += getChildrenSkipCount(virtualChildAt3, i5);
                            i13 = i10;
                        } else {
                            layoutParams = (LayoutParams) virtualChildAt3.getLayoutParams();
                            if (obj3 != null) {
                                r7.mTotalLength += ((layoutParams.leftMargin + i11) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt3);
                                i13 = i10;
                            } else {
                                baseline = r7.mTotalLength;
                                i13 = i10;
                                r7.mTotalLength = Math.max(baseline, (((baseline + i11) + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt3));
                            }
                        }
                        i5++;
                        i10 = i13;
                    }
                    i13 = i10;
                }
                r7.mTotalLength += getPaddingLeft() + getPaddingRight();
                i10 = View.resolveSizeAndState(Math.max(r7.mTotalLength, getSuggestedMinimumWidth()), i7, 0);
                i5 = (ViewCompat.MEASURED_SIZE_MASK & i10) - r7.mTotalLength;
                if (obj4 == null) {
                    if (i5 != 0 || f5 <= 0.0f) {
                        i3 = Math.max(i9, i4);
                        if (z && mode != 1073741824) {
                            for (i9 = 0; i9 < virtualChildCount; i9++) {
                                view = getVirtualChildAt(i9);
                                if (view == null) {
                                    if (view.getVisibility() == 8) {
                                        if (((LayoutParams) view.getLayoutParams()).weight > 0.0f) {
                                            view.measure(MeasureSpec.makeMeasureSpec(i11, 1073741824), MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
                                        }
                                    }
                                }
                            }
                        }
                        i15 = virtualChildCount;
                        i9 = i13;
                        i5 = i2;
                        if (obj5 == null || mode2 == 1073741824) {
                            i3 = i9;
                        }
                        setMeasuredDimension(i10 | (i14 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i3 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i5, i14 << 16));
                        if (obj6 != null) {
                            forceUniformHeight(i15, i);
                        }
                    }
                }
                f = r7.mWeightSum;
                if (f > 0.0f) {
                    f5 = f;
                }
                iArr2[3] = -1;
                iArr2[2] = -1;
                iArr2[1] = -1;
                iArr2[0] = -1;
                iArr[3] = -1;
                iArr[2] = -1;
                iArr[1] = -1;
                iArr[0] = -1;
                r7.mTotalLength = 0;
                i4 = i9;
                i8 = i14;
                baseline = -1;
                f2 = f5;
                i3 = 0;
                while (i3 < virtualChildCount) {
                    virtualChildAt = getVirtualChildAt(i3);
                    if (virtualChildAt != null) {
                        measuredHeight = i5;
                        i15 = virtualChildCount;
                        i5 = i2;
                    } else if (virtualChildAt.getVisibility() != 8) {
                        measuredHeight = i5;
                        i15 = virtualChildCount;
                        i5 = i2;
                    } else {
                        layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                        f3 = layoutParams.weight;
                        if (f3 <= 0.0f) {
                            i7 = (int) ((((float) i5) * f3) / f2);
                            f4 = f2 - f3;
                            i6 = i5 - i7;
                            i15 = virtualChildCount;
                            i9 = getChildMeasureSpec(i2, ((getPaddingTop() + getPaddingBottom()) + layoutParams.topMargin) + layoutParams.bottomMargin, layoutParams.height);
                            if (layoutParams.width != 0) {
                                measuredHeight = 1073741824;
                                if (mode != 1073741824) {
                                    if (i7 > 0) {
                                        i7 = 0;
                                    }
                                    virtualChildAt.measure(MeasureSpec.makeMeasureSpec(i7, 1073741824), i9);
                                    i8 = View.combineMeasuredStates(i8, virtualChildAt.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                                    f2 = f4;
                                    measuredHeight = i6;
                                }
                            } else {
                                measuredHeight = 1073741824;
                            }
                            i7 = virtualChildAt.getMeasuredWidth() + i7;
                            if (i7 < 0) {
                                i7 = 0;
                            }
                            virtualChildAt.measure(MeasureSpec.makeMeasureSpec(i7, measuredHeight), i9);
                            i8 = View.combineMeasuredStates(i8, virtualChildAt.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                            f2 = f4;
                            measuredHeight = i6;
                        } else {
                            measuredHeight = i5;
                            i15 = virtualChildCount;
                            i5 = i2;
                        }
                        if (obj3 == null) {
                            r7.mTotalLength += ((virtualChildAt.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt);
                            f4 = f2;
                            i9 = 1073741824;
                        } else {
                            i7 = r7.mTotalLength;
                            f4 = f2;
                            r7.mTotalLength = Math.max(i7, (((virtualChildAt.getMeasuredWidth() + i7) + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt));
                            i9 = 1073741824;
                        }
                        obj = (mode2 == i9 && layoutParams.height == -1) ? 1 : null;
                        i7 = layoutParams.topMargin + layoutParams.bottomMargin;
                        virtualChildCount = virtualChildAt.getMeasuredHeight() + i7;
                        baseline = Math.max(baseline, virtualChildCount);
                        if (obj != null) {
                            i7 = virtualChildCount;
                        }
                        i9 = Math.max(i4, i7);
                        if (obj5 == null) {
                            i4 = -1;
                            if (layoutParams.height == -1) {
                                obj2 = 1;
                                if (!z2) {
                                    i11 = virtualChildAt.getBaseline();
                                    if (i11 == i4) {
                                        combineMeasuredStates = ((((layoutParams.gravity >= 0 ? r7.mGravity : layoutParams.gravity) & 112) >> 4) & -2) >> 1;
                                        iArr2[combineMeasuredStates] = Math.max(iArr2[combineMeasuredStates], i11);
                                        iArr[combineMeasuredStates] = Math.max(iArr[combineMeasuredStates], virtualChildCount - i11);
                                    }
                                }
                                i4 = i9;
                                obj5 = obj2;
                                f2 = f4;
                            }
                        } else {
                            i4 = -1;
                        }
                        obj2 = null;
                        if (!z2) {
                            i11 = virtualChildAt.getBaseline();
                            if (i11 == i4) {
                                if (layoutParams.gravity >= 0) {
                                }
                                combineMeasuredStates = ((((layoutParams.gravity >= 0 ? r7.mGravity : layoutParams.gravity) & 112) >> 4) & -2) >> 1;
                                iArr2[combineMeasuredStates] = Math.max(iArr2[combineMeasuredStates], i11);
                                iArr[combineMeasuredStates] = Math.max(iArr[combineMeasuredStates], virtualChildCount - i11);
                            }
                        }
                        i4 = i9;
                        obj5 = obj2;
                        f2 = f4;
                    }
                    i3++;
                    i5 = measuredHeight;
                    virtualChildCount = i15;
                    i7 = i;
                }
                i15 = virtualChildCount;
                i5 = i2;
                r7.mTotalLength += getPaddingLeft() + getPaddingRight();
                if (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1) {
                    if (iArr2[3] != -1) {
                        i3 = baseline;
                        i9 = i3;
                        i14 = i8;
                        i3 = i4;
                        if (obj5 == null) {
                        }
                        i3 = i9;
                        setMeasuredDimension(i10 | (i14 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i3 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i5, i14 << 16));
                        if (obj6 != null) {
                            forceUniformHeight(i15, i);
                        }
                    }
                }
                i3 = Math.max(baseline, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
                i9 = i3;
                i14 = i8;
                i3 = i4;
                if (obj5 == null) {
                }
                i3 = i9;
                setMeasuredDimension(i10 | (i14 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i3 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i5, i14 << 16));
                if (obj6 != null) {
                    forceUniformHeight(i15, i);
                }
            }
        }
        i14 = baseline;
        i10 = Math.max(i10, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        if (z) {
        }
        i13 = i10;
        r7.mTotalLength += getPaddingLeft() + getPaddingRight();
        i10 = View.resolveSizeAndState(Math.max(r7.mTotalLength, getSuggestedMinimumWidth()), i7, 0);
        i5 = (ViewCompat.MEASURED_SIZE_MASK & i10) - r7.mTotalLength;
        if (obj4 == null) {
            if (i5 != 0) {
            }
            i3 = Math.max(i9, i4);
            for (i9 = 0; i9 < virtualChildCount; i9++) {
                view = getVirtualChildAt(i9);
                if (view == null) {
                    if (view.getVisibility() == 8) {
                        if (((LayoutParams) view.getLayoutParams()).weight > 0.0f) {
                            view.measure(MeasureSpec.makeMeasureSpec(i11, 1073741824), MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
                        }
                    }
                }
            }
            i15 = virtualChildCount;
            i9 = i13;
            i5 = i2;
            if (obj5 == null) {
            }
            i3 = i9;
            setMeasuredDimension(i10 | (i14 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i3 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i5, i14 << 16));
            if (obj6 != null) {
                forceUniformHeight(i15, i);
            }
        }
        f = r7.mWeightSum;
        if (f > 0.0f) {
            f5 = f;
        }
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr[3] = -1;
        iArr[2] = -1;
        iArr[1] = -1;
        iArr[0] = -1;
        r7.mTotalLength = 0;
        i4 = i9;
        i8 = i14;
        baseline = -1;
        f2 = f5;
        i3 = 0;
        while (i3 < virtualChildCount) {
            virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt != null) {
                measuredHeight = i5;
                i15 = virtualChildCount;
                i5 = i2;
            } else if (virtualChildAt.getVisibility() != 8) {
                layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                f3 = layoutParams.weight;
                if (f3 <= 0.0f) {
                    measuredHeight = i5;
                    i15 = virtualChildCount;
                    i5 = i2;
                } else {
                    i7 = (int) ((((float) i5) * f3) / f2);
                    f4 = f2 - f3;
                    i6 = i5 - i7;
                    i15 = virtualChildCount;
                    i9 = getChildMeasureSpec(i2, ((getPaddingTop() + getPaddingBottom()) + layoutParams.topMargin) + layoutParams.bottomMargin, layoutParams.height);
                    if (layoutParams.width != 0) {
                        measuredHeight = 1073741824;
                    } else {
                        measuredHeight = 1073741824;
                        if (mode != 1073741824) {
                            if (i7 > 0) {
                                i7 = 0;
                            }
                            virtualChildAt.measure(MeasureSpec.makeMeasureSpec(i7, 1073741824), i9);
                            i8 = View.combineMeasuredStates(i8, virtualChildAt.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                            f2 = f4;
                            measuredHeight = i6;
                        }
                    }
                    i7 = virtualChildAt.getMeasuredWidth() + i7;
                    if (i7 < 0) {
                        i7 = 0;
                    }
                    virtualChildAt.measure(MeasureSpec.makeMeasureSpec(i7, measuredHeight), i9);
                    i8 = View.combineMeasuredStates(i8, virtualChildAt.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                    f2 = f4;
                    measuredHeight = i6;
                }
                if (obj3 == null) {
                    i7 = r7.mTotalLength;
                    f4 = f2;
                    r7.mTotalLength = Math.max(i7, (((virtualChildAt.getMeasuredWidth() + i7) + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt));
                    i9 = 1073741824;
                } else {
                    r7.mTotalLength += ((virtualChildAt.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt);
                    f4 = f2;
                    i9 = 1073741824;
                }
                if (mode2 == i9) {
                }
                i7 = layoutParams.topMargin + layoutParams.bottomMargin;
                virtualChildCount = virtualChildAt.getMeasuredHeight() + i7;
                baseline = Math.max(baseline, virtualChildCount);
                if (obj != null) {
                    i7 = virtualChildCount;
                }
                i9 = Math.max(i4, i7);
                if (obj5 == null) {
                    i4 = -1;
                } else {
                    i4 = -1;
                    if (layoutParams.height == -1) {
                        obj2 = 1;
                        if (!z2) {
                            i11 = virtualChildAt.getBaseline();
                            if (i11 == i4) {
                                if (layoutParams.gravity >= 0) {
                                }
                                combineMeasuredStates = ((((layoutParams.gravity >= 0 ? r7.mGravity : layoutParams.gravity) & 112) >> 4) & -2) >> 1;
                                iArr2[combineMeasuredStates] = Math.max(iArr2[combineMeasuredStates], i11);
                                iArr[combineMeasuredStates] = Math.max(iArr[combineMeasuredStates], virtualChildCount - i11);
                            }
                        }
                        i4 = i9;
                        obj5 = obj2;
                        f2 = f4;
                    }
                }
                obj2 = null;
                if (!z2) {
                    i11 = virtualChildAt.getBaseline();
                    if (i11 == i4) {
                        if (layoutParams.gravity >= 0) {
                        }
                        combineMeasuredStates = ((((layoutParams.gravity >= 0 ? r7.mGravity : layoutParams.gravity) & 112) >> 4) & -2) >> 1;
                        iArr2[combineMeasuredStates] = Math.max(iArr2[combineMeasuredStates], i11);
                        iArr[combineMeasuredStates] = Math.max(iArr[combineMeasuredStates], virtualChildCount - i11);
                    }
                }
                i4 = i9;
                obj5 = obj2;
                f2 = f4;
            } else {
                measuredHeight = i5;
                i15 = virtualChildCount;
                i5 = i2;
            }
            i3++;
            i5 = measuredHeight;
            virtualChildCount = i15;
            i7 = i;
        }
        i15 = virtualChildCount;
        i5 = i2;
        r7.mTotalLength += getPaddingLeft() + getPaddingRight();
        if (iArr2[3] != -1) {
            i3 = baseline;
            i9 = i3;
            i14 = i8;
            i3 = i4;
            if (obj5 == null) {
            }
            i3 = i9;
            setMeasuredDimension(i10 | (i14 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i3 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i5, i14 << 16));
            if (obj6 != null) {
                forceUniformHeight(i15, i);
            }
        }
        i3 = Math.max(baseline, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        i9 = i3;
        i14 = i8;
        i3 = i4;
        if (obj5 == null) {
        }
        i3 = i9;
        setMeasuredDimension(i10 | (i14 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i3 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i5, i14 << 16));
        if (obj6 != null) {
            forceUniformHeight(i15, i);
        }
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    void layoutVertical(int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int i5 = i3 - i;
        int paddingRight = i5 - getPaddingRight();
        int paddingRight2 = (i5 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        i5 = this.mGravity;
        int i6 = i5 & 112;
        int i7 = i5 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i6 == 16) {
            i5 = getPaddingTop() + (((i4 - i2) - r6.mTotalLength) / 2);
        } else if (i6 != 80) {
            i5 = getPaddingTop();
        } else {
            i5 = ((getPaddingTop() + i4) - i2) - r6.mTotalLength;
        }
        int i8 = 0;
        while (i8 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i8);
            if (virtualChildAt == null) {
                i5 += measureNullChild(i8);
                i6 = 1;
            } else if (virtualChildAt.getVisibility() != 8) {
                int i9;
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                i6 = layoutParams.gravity;
                if (i6 < 0) {
                    i6 = i7;
                }
                i6 = GravityCompat.getAbsoluteGravity(i6, ViewCompat.getLayoutDirection(this)) & 7;
                if (i6 == 1) {
                    i9 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                } else if (i6 != 5) {
                    i9 = layoutParams.leftMargin + paddingLeft;
                } else {
                    i9 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                }
                if (hasDividerBeforeChildAt(i8)) {
                    i5 += r6.mDividerHeight;
                }
                int i10 = i5 + layoutParams.topMargin;
                LayoutParams layoutParams2 = layoutParams;
                setChildFrame(virtualChildAt, i9, i10 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                i8 += getChildrenSkipCount(virtualChildAt, i8);
                i5 = i10 + ((measuredHeight + layoutParams2.bottomMargin) + getNextLocationOffset(virtualChildAt));
                i6 = 1;
            } else {
                i6 = 1;
            }
            i8 += i6;
        }
    }

    void layoutHorizontal(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int i7 = i4 - i2;
        int paddingBottom = i7 - getPaddingBottom();
        int paddingBottom2 = (i7 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        i7 = this.mGravity;
        int i8 = GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK & i7;
        int i9 = i7 & 112;
        boolean z = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        i7 = GravityCompat.getAbsoluteGravity(i8, ViewCompat.getLayoutDirection(this));
        if (i7 == 1) {
            i7 = getPaddingLeft() + (((i3 - i) - r6.mTotalLength) / 2);
        } else if (i7 != 5) {
            i7 = getPaddingLeft();
        } else {
            i7 = ((getPaddingLeft() + i3) - i) - r6.mTotalLength;
        }
        if (isLayoutRtl) {
            i5 = virtualChildCount - 1;
            i6 = -1;
        } else {
            i5 = 0;
            i6 = 1;
        }
        int i10 = 0;
        while (i10 < virtualChildCount) {
            int i11;
            int i12;
            int i13;
            i8 = i5 + (i6 * i10);
            View virtualChildAt = getVirtualChildAt(i8);
            if (virtualChildAt == null) {
                i7 += measureNullChild(i8);
                i11 = paddingTop;
                i12 = virtualChildCount;
                i13 = i9;
            } else if (virtualChildAt.getVisibility() != 8) {
                View view;
                LayoutParams layoutParams;
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt.getLayoutParams();
                if (z) {
                    r18 = i10;
                    i12 = virtualChildCount;
                    if (layoutParams2.height != -1) {
                        i10 = virtualChildAt.getBaseline();
                        virtualChildCount = layoutParams2.gravity;
                        if (virtualChildCount < 0) {
                            virtualChildCount = i9;
                        }
                        virtualChildCount &= 112;
                        i13 = i9;
                        if (virtualChildCount != 16) {
                            i10 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + layoutParams2.topMargin) - layoutParams2.bottomMargin;
                        } else if (virtualChildCount != 48) {
                            virtualChildCount = layoutParams2.topMargin + paddingTop;
                            i10 = i10 == -1 ? virtualChildCount + (iArr[1] - i10) : virtualChildCount;
                        } else if (virtualChildCount == 80) {
                            i10 = paddingTop;
                        } else {
                            virtualChildCount = (paddingBottom - measuredHeight) - layoutParams2.bottomMargin;
                            i10 = i10 == -1 ? virtualChildCount - (iArr2[2] - (virtualChildAt.getMeasuredHeight() - i10)) : virtualChildCount;
                        }
                        if (hasDividerBeforeChildAt(i8)) {
                            i7 += r6.mDividerWidth;
                        }
                        virtualChildCount = layoutParams2.leftMargin + i7;
                        view = virtualChildAt;
                        i = view;
                        i9 = i8;
                        i11 = paddingTop;
                        layoutParams = layoutParams2;
                        setChildFrame(view, virtualChildCount + getLocationOffset(virtualChildAt), i10, measuredWidth, measuredHeight);
                        virtualChildAt = i;
                        i10 = r18 + getChildrenSkipCount(virtualChildAt, i9);
                        i7 = virtualChildCount + ((measuredWidth + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt));
                    }
                } else {
                    r18 = i10;
                    i12 = virtualChildCount;
                }
                i10 = -1;
                virtualChildCount = layoutParams2.gravity;
                if (virtualChildCount < 0) {
                    virtualChildCount = i9;
                }
                virtualChildCount &= 112;
                i13 = i9;
                if (virtualChildCount != 16) {
                    i10 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + layoutParams2.topMargin) - layoutParams2.bottomMargin;
                } else if (virtualChildCount != 48) {
                    virtualChildCount = layoutParams2.topMargin + paddingTop;
                    if (i10 == -1) {
                    }
                } else if (virtualChildCount == 80) {
                    virtualChildCount = (paddingBottom - measuredHeight) - layoutParams2.bottomMargin;
                    if (i10 == -1) {
                    }
                } else {
                    i10 = paddingTop;
                }
                if (hasDividerBeforeChildAt(i8)) {
                    i7 += r6.mDividerWidth;
                }
                virtualChildCount = layoutParams2.leftMargin + i7;
                view = virtualChildAt;
                i = view;
                i9 = i8;
                i11 = paddingTop;
                layoutParams = layoutParams2;
                setChildFrame(view, virtualChildCount + getLocationOffset(virtualChildAt), i10, measuredWidth, measuredHeight);
                virtualChildAt = i;
                i10 = r18 + getChildrenSkipCount(virtualChildAt, i9);
                i7 = virtualChildCount + ((measuredWidth + layoutParams.rightMargin) + getNextLocationOffset(virtualChildAt));
            } else {
                r18 = i10;
                i11 = paddingTop;
                i12 = virtualChildCount;
                i13 = i9;
            }
            i10++;
            virtualChildCount = i12;
            i9 = i13;
            paddingTop = i11;
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        i &= GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i2 = this.mGravity;
        if ((GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK & i2) != i) {
            this.mGravity = i | (-8388616 & i2);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        i &= 112;
        int i2 = this.mGravity;
        if ((i2 & 112) != i) {
            this.mGravity = i | (i2 & -113);
            requestLayout();
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        return i == 1 ? new LayoutParams(-1, -2) : null;
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }
}
