package android.support.design.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.C0026R;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

@RestrictTo({Scope.LIBRARY_GROUP})
public class FlowLayout extends ViewGroup {
    private int itemSpacing;
    private int lineSpacing;
    private boolean singleLine;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.singleLine = false;
        loadFromAttributes(context, attributeSet);
    }

    @TargetApi(21)
    public FlowLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.singleLine = false;
        loadFromAttributes(context, attributeSet);
    }

    private void loadFromAttributes(Context context, AttributeSet attributeSet) {
        context = context.getTheme().obtainStyledAttributes(attributeSet, C0026R.styleable.FlowLayout, 0, 0);
        this.lineSpacing = context.getDimensionPixelSize(C0026R.styleable.FlowLayout_lineSpacing, 0);
        this.itemSpacing = context.getDimensionPixelSize(C0026R.styleable.FlowLayout_itemSpacing, 0);
        context.recycle();
    }

    protected int getLineSpacing() {
        return this.lineSpacing;
    }

    protected void setLineSpacing(int i) {
        this.lineSpacing = i;
    }

    protected int getItemSpacing() {
        return this.itemSpacing;
    }

    protected void setItemSpacing(int i) {
        this.itemSpacing = i;
    }

    protected boolean isSingleLine() {
        return this.singleLine;
    }

    public void setSingleLine(boolean z) {
        this.singleLine = z;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int paddingLeft;
        int i4;
        int paddingTop;
        int i5;
        int i6;
        View childAt;
        LayoutParams layoutParams;
        int i7;
        int i8;
        int i9;
        FlowLayout flowLayout = this;
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        if (mode != Integer.MIN_VALUE) {
            if (mode != 1073741824) {
                i3 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                paddingLeft = getPaddingLeft();
                i3 -= getPaddingRight();
                i4 = paddingLeft;
                paddingTop = getPaddingTop();
                i5 = paddingTop;
                i6 = 0;
                for (paddingLeft = 0; paddingLeft < getChildCount(); paddingLeft++) {
                    childAt = getChildAt(paddingLeft);
                    if (childAt.getVisibility() != 8) {
                        int i10 = i;
                        int i11 = i2;
                    } else {
                        measureChild(childAt, i, i2);
                        layoutParams = childAt.getLayoutParams();
                        if (layoutParams instanceof MarginLayoutParams) {
                            i7 = 0;
                            i8 = 0;
                        } else {
                            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                            i7 = marginLayoutParams.leftMargin + 0;
                            i8 = marginLayoutParams.rightMargin + 0;
                        }
                        i9 = i4;
                        if ((i4 + i7) + childAt.getMeasuredWidth() > i3 && !isSingleLine()) {
                            i5 = flowLayout.lineSpacing + paddingTop;
                            i9 = getPaddingLeft();
                        }
                        paddingTop = (i9 + i7) + childAt.getMeasuredWidth();
                        i4 = childAt.getMeasuredHeight() + i5;
                        if (paddingTop > i6) {
                            i6 = paddingTop;
                        }
                        paddingTop = i4;
                        i4 = i9 + (((i7 + i8) + childAt.getMeasuredWidth()) + flowLayout.itemSpacing);
                    }
                }
                setMeasuredDimension(getMeasuredDimension(size, mode, i6), getMeasuredDimension(size2, mode2, paddingTop));
            }
        }
        i3 = size;
        paddingLeft = getPaddingLeft();
        i3 -= getPaddingRight();
        i4 = paddingLeft;
        paddingTop = getPaddingTop();
        i5 = paddingTop;
        i6 = 0;
        for (paddingLeft = 0; paddingLeft < getChildCount(); paddingLeft++) {
            childAt = getChildAt(paddingLeft);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i, i2);
                layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof MarginLayoutParams) {
                    i7 = 0;
                    i8 = 0;
                } else {
                    MarginLayoutParams marginLayoutParams2 = (MarginLayoutParams) layoutParams;
                    i7 = marginLayoutParams2.leftMargin + 0;
                    i8 = marginLayoutParams2.rightMargin + 0;
                }
                i9 = i4;
                i5 = flowLayout.lineSpacing + paddingTop;
                i9 = getPaddingLeft();
                paddingTop = (i9 + i7) + childAt.getMeasuredWidth();
                i4 = childAt.getMeasuredHeight() + i5;
                if (paddingTop > i6) {
                    i6 = paddingTop;
                }
                paddingTop = i4;
                i4 = i9 + (((i7 + i8) + childAt.getMeasuredWidth()) + flowLayout.itemSpacing);
            } else {
                int i102 = i;
                int i112 = i2;
            }
        }
        setMeasuredDimension(getMeasuredDimension(size, mode, i6), getMeasuredDimension(size2, mode2, paddingTop));
    }

    private static int getMeasuredDimension(int i, int i2, int i3) {
        if (i2 != Integer.MIN_VALUE) {
            return i2 != 1073741824 ? i3 : i;
        } else {
            return Math.min(i3, i);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getChildCount()) {
            i2 = 1;
            if (!ViewCompat.getLayoutDirection(this)) {
                i2 = 0;
            }
            z = i2 != 0 ? getPaddingRight() : getPaddingLeft();
            int paddingLeft = i2 != 0 ? getPaddingLeft() : getPaddingRight();
            int paddingTop = getPaddingTop();
            i3 = (i3 - i) - paddingLeft;
            paddingLeft = z;
            int i5 = paddingTop;
            for (i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() != 8) {
                    int marginStart;
                    int marginEnd;
                    LayoutParams layoutParams = childAt.getLayoutParams();
                    if (layoutParams instanceof MarginLayoutParams) {
                        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                        marginStart = MarginLayoutParamsCompat.getMarginStart(marginLayoutParams);
                        marginEnd = MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams);
                    } else {
                        marginEnd = 0;
                        marginStart = 0;
                    }
                    int measuredWidth = (paddingLeft + marginStart) + childAt.getMeasuredWidth();
                    if (!this.singleLine && measuredWidth > i3) {
                        i5 = paddingTop + this.lineSpacing;
                        paddingLeft = z;
                    }
                    paddingTop = paddingLeft + marginStart;
                    measuredWidth = childAt.getMeasuredWidth() + paddingTop;
                    int measuredHeight = childAt.getMeasuredHeight() + i5;
                    if (i2 != 0) {
                        childAt.layout(i3 - measuredWidth, i5, (i3 - paddingLeft) - marginStart, measuredHeight);
                    } else {
                        childAt.layout(paddingTop, i5, measuredWidth, measuredHeight);
                    }
                    paddingLeft += ((marginStart + marginEnd) + childAt.getMeasuredWidth()) + this.itemSpacing;
                    paddingTop = measuredHeight;
                }
            }
        }
    }
}
