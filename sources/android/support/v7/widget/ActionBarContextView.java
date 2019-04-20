package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.appcompat.C0291R;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.MenuBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ActionBarContextView extends AbsActionBarView {
    private static final String TAG = "ActionBarContextView";
    private View mClose;
    private int mCloseItemLayout;
    private View mCustomView;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    protected void onMeasure(int r11, int r12) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:66:0x0139 in {6, 7, 10, 15, 24, 25, 27, 30, 31, 32, 33, 38, 39, 42, 45, 46, 49, 50, 57, 58, 59, 60, 61, 63, 65} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r10 = this;
        r0 = android.view.View.MeasureSpec.getMode(r11);
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r0 != r1) goto L_0x0115;
    L_0x0008:
        r0 = android.view.View.MeasureSpec.getMode(r12);
        if (r0 == 0) goto L_0x00f1;
    L_0x000e:
        r11 = android.view.View.MeasureSpec.getSize(r11);
        r0 = r10.mContentHeight;
        if (r0 <= 0) goto L_0x0019;
    L_0x0016:
        r12 = r10.mContentHeight;
        goto L_0x001d;
    L_0x0019:
        r12 = android.view.View.MeasureSpec.getSize(r12);
    L_0x001d:
        r0 = r10.getPaddingTop();
        r2 = r10.getPaddingBottom();
        r0 = r0 + r2;
        r2 = r10.getPaddingLeft();
        r2 = r11 - r2;
        r3 = r10.getPaddingRight();
        r2 = r2 - r3;
        r3 = r12 - r0;
        r4 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r5 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r4);
        r6 = r10.mClose;
        r7 = 0;
        if (r6 == 0) goto L_0x0050;
    L_0x003e:
        r2 = r10.measureChildView(r6, r2, r5, r7);
        r6 = r10.mClose;
        r6 = r6.getLayoutParams();
        r6 = (android.view.ViewGroup.MarginLayoutParams) r6;
        r8 = r6.leftMargin;
        r6 = r6.rightMargin;
        r8 = r8 + r6;
        r2 = r2 - r8;
    L_0x0050:
        r6 = r10.mMenuView;
        if (r6 == 0) goto L_0x0062;
    L_0x0054:
        r6 = r10.mMenuView;
        r6 = r6.getParent();
        if (r6 != r10) goto L_0x0062;
    L_0x005c:
        r6 = r10.mMenuView;
        r2 = r10.measureChildView(r6, r2, r5, r7);
    L_0x0062:
        r6 = r10.mTitleLayout;
        if (r6 == 0) goto L_0x0095;
    L_0x0066:
        r8 = r10.mCustomView;
        if (r8 != 0) goto L_0x0095;
    L_0x006a:
        r8 = r10.mTitleOptional;
        if (r8 == 0) goto L_0x0091;
    L_0x006e:
        r6 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r7);
        r8 = r10.mTitleLayout;
        r8.measure(r6, r5);
        r5 = r10.mTitleLayout;
        r5 = r5.getMeasuredWidth();
        if (r5 > r2) goto L_0x0081;
    L_0x007f:
        r6 = 1;
        goto L_0x0082;
    L_0x0081:
        r6 = 0;
    L_0x0082:
        if (r6 == 0) goto L_0x0085;
    L_0x0084:
        r2 = r2 - r5;
    L_0x0085:
        r5 = r10.mTitleLayout;
        if (r6 == 0) goto L_0x008b;
    L_0x0089:
        r6 = 0;
        goto L_0x008d;
    L_0x008b:
        r6 = 8;
    L_0x008d:
        r5.setVisibility(r6);
        goto L_0x0095;
    L_0x0091:
        r2 = r10.measureChildView(r6, r2, r5, r7);
    L_0x0095:
        r5 = r10.mCustomView;
        if (r5 == 0) goto L_0x00cf;
    L_0x0099:
        r5 = r5.getLayoutParams();
        r6 = r5.width;
        r8 = -2;
        if (r6 == r8) goto L_0x00a5;
    L_0x00a2:
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        goto L_0x00a7;
    L_0x00a5:
        r6 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
    L_0x00a7:
        r9 = r5.width;
        if (r9 < 0) goto L_0x00b1;
    L_0x00ab:
        r9 = r5.width;
        r2 = java.lang.Math.min(r9, r2);
    L_0x00b1:
        r9 = r5.height;
        if (r9 == r8) goto L_0x00b6;
    L_0x00b5:
        goto L_0x00b8;
    L_0x00b6:
        r1 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
    L_0x00b8:
        r4 = r5.height;
        if (r4 < 0) goto L_0x00c2;
    L_0x00bc:
        r4 = r5.height;
        r3 = java.lang.Math.min(r4, r3);
    L_0x00c2:
        r4 = r10.mCustomView;
        r2 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r6);
        r1 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r1);
        r4.measure(r2, r1);
    L_0x00cf:
        r1 = r10.mContentHeight;
        if (r1 > 0) goto L_0x00ed;
    L_0x00d3:
        r12 = r10.getChildCount();
        r1 = 0;
    L_0x00d8:
        if (r7 >= r12) goto L_0x00e9;
    L_0x00da:
        r2 = r10.getChildAt(r7);
        r2 = r2.getMeasuredHeight();
        r2 = r2 + r0;
        if (r2 <= r1) goto L_0x00e6;
    L_0x00e5:
        r1 = r2;
    L_0x00e6:
        r7 = r7 + 1;
        goto L_0x00d8;
    L_0x00e9:
        r10.setMeasuredDimension(r11, r1);
        goto L_0x00f0;
    L_0x00ed:
        r10.setMeasuredDimension(r11, r12);
    L_0x00f0:
        return;
    L_0x00f1:
        r11 = new java.lang.IllegalStateException;
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r0 = r10.getClass();
        r0 = r0.getSimpleName();
        r12.append(r0);
        r0 = " can only be used ";
        r12.append(r0);
        r0 = "with android:layout_height=\"wrap_content\"";
        r12.append(r0);
        r12 = r12.toString();
        r11.<init>(r12);
        throw r11;
    L_0x0115:
        r11 = new java.lang.IllegalStateException;
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r0 = r10.getClass();
        r0 = r0.getSimpleName();
        r12.append(r0);
        r0 = " can only be used ";
        r12.append(r0);
        r0 = "with android:layout_width=\"match_parent\" (or fill_parent)";
        r12.append(r0);
        r12 = r12.toString();
        r11.<init>(r12);
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarContextView.onMeasure(int, int):void");
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public /* bridge */ /* synthetic */ void animateToVisibility(int i) {
        super.animateToVisibility(i);
    }

    public /* bridge */ /* synthetic */ boolean canShowOverflowMenu() {
        return super.canShowOverflowMenu();
    }

    public /* bridge */ /* synthetic */ void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowMenuShowPending() {
        return super.isOverflowMenuShowPending();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    public /* bridge */ /* synthetic */ ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long j) {
        return super.setupAnimatorToVisibility(i, j);
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0291R.attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        context = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0291R.styleable.ActionMode, i, 0);
        ViewCompat.setBackground(this, context.getDrawable(C0291R.styleable.ActionMode_background));
        this.mTitleStyleRes = context.getResourceId(C0291R.styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = context.getResourceId(C0291R.styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = context.getLayoutDimension(C0291R.styleable.ActionMode_height, 0);
        this.mCloseItemLayout = context.getResourceId(C0291R.styleable.ActionMode_closeItemLayout, C0291R.layout.abc_action_mode_close_item_material);
        context.recycle();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    public void setContentHeight(int i) {
        this.mContentHeight = i;
    }

    public void setCustomView(View view) {
        View view2 = this.mCustomView;
        if (view2 != null) {
            removeView(view2);
        }
        this.mCustomView = view;
        if (view != null) {
            view2 = this.mTitleLayout;
            if (view2 != null) {
                removeView(view2);
                this.mTitleLayout = null;
            }
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        initTitle();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        initTitle();
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    private void initTitle() {
        if (this.mTitleLayout == null) {
            LayoutInflater.from(getContext()).inflate(C0291R.layout.abc_action_bar_title_item, this);
            this.mTitleLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.mTitleView = (TextView) this.mTitleLayout.findViewById(C0291R.id.action_bar_title);
            this.mSubtitleView = (TextView) this.mTitleLayout.findViewById(C0291R.id.action_bar_subtitle);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(getContext(), this.mTitleStyleRes);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        int isEmpty = TextUtils.isEmpty(this.mTitle) ^ 1;
        int isEmpty2 = TextUtils.isEmpty(this.mSubtitle) ^ 1;
        int i = 0;
        this.mSubtitleView.setVisibility(isEmpty2 != 0 ? 0 : 8);
        LinearLayout linearLayout = this.mTitleLayout;
        if (isEmpty == 0) {
            if (isEmpty2 == 0) {
                i = 8;
            }
        }
        linearLayout.setVisibility(i);
        if (this.mTitleLayout.getParent() == null) {
            addView(this.mTitleLayout);
        }
    }

    public void initForMode(final ActionMode actionMode) {
        View view = this.mClose;
        if (view == null) {
            this.mClose = LayoutInflater.from(getContext()).inflate(this.mCloseItemLayout, this, false);
            addView(this.mClose);
        } else if (view.getParent() == null) {
            addView(this.mClose);
        }
        this.mClose.findViewById(C0291R.id.action_mode_close_button).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                actionMode.finish();
            }
        });
        MenuBuilder menuBuilder = (MenuBuilder) actionMode.getMenu();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
        this.mActionMenuPresenter = new ActionMenuPresenter(getContext());
        this.mActionMenuPresenter.setReserveOverflow(true);
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        menuBuilder.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
        this.mMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
        ViewCompat.setBackground(this.mMenuView, null);
        addView(this.mMenuView, layoutParams);
    }

    public void closeMode() {
        if (this.mClose == null) {
            killMode();
        }
    }

    public void killMode() {
        removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
    }

    public boolean showOverflowMenu() {
        return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.showOverflowMenu() : false;
    }

    public boolean hideOverflowMenu() {
        return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.hideOverflowMenu() : false;
    }

    public boolean isOverflowMenuShowing() {
        return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.isOverflowMenuShowing() : false;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-1, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        z = ViewUtils.isLayoutRtl(this);
        int paddingRight = z ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        i2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        i4 = this.mClose;
        if (i4 == 0 || i4.getVisibility() == 8) {
            i4 = paddingRight;
        } else {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mClose.getLayoutParams();
            int i5 = z ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            i4 = z ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int next = AbsActionBarView.next(paddingRight, i5, z);
            i4 = AbsActionBarView.next(next + positionChild(this.mClose, next, paddingTop, i2, z), i4, z);
        }
        LinearLayout linearLayout = this.mTitleLayout;
        int positionChild = (linearLayout == null || this.mCustomView != null || linearLayout.getVisibility() == 8) ? i4 : i4 + positionChild(this.mTitleLayout, i4, paddingTop, i2, z);
        View view = this.mCustomView;
        if (view != null) {
            positionChild(view, positionChild, paddingTop, i2, z);
        }
        int paddingLeft = z ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        if (this.mMenuView != 0) {
            positionChild(this.mMenuView, paddingLeft, paddingTop, i2, z ^ 1);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.mTitle);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.mTitleOptional) {
            requestLayout();
        }
        this.mTitleOptional = z;
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }
}
