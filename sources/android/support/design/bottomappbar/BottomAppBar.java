package android.support.design.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.Dimension;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.design.C0026R;
import android.support.design.animation.AnimationUtils;
import android.support.design.behavior.HideBottomViewOnScrollBehavior;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.resources.MaterialResources;
import android.support.design.shape.MaterialShapeDrawable;
import android.support.design.shape.ShapePathModel;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.AttachedBehavior;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BottomAppBar extends Toolbar implements AttachedBehavior {
    private static final long ANIMATION_DURATION = 300;
    public static final int FAB_ALIGNMENT_MODE_CENTER = 0;
    public static final int FAB_ALIGNMENT_MODE_END = 1;
    @Nullable
    private Animator attachAnimator;
    private int fabAlignmentMode;
    AnimatorListenerAdapter fabAnimationListener;
    private boolean fabAttached;
    private final int fabOffsetEndMode;
    private boolean hideOnScroll;
    private final MaterialShapeDrawable materialShapeDrawable;
    @Nullable
    private Animator menuAnimator;
    @Nullable
    private Animator modeAnimator;
    private final BottomAppBarTopEdgeTreatment topEdgeTreatment;

    /* renamed from: android.support.design.bottomappbar.BottomAppBar$1 */
    class C00281 extends AnimatorListenerAdapter {
        C00281() {
        }

        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.modeAnimator = null;
        }
    }

    /* renamed from: android.support.design.bottomappbar.BottomAppBar$2 */
    class C00292 implements AnimatorUpdateListener {
        C00292() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BottomAppBar.this.topEdgeTreatment.setHorizontalOffset(((Float) valueAnimator.getAnimatedValue()).floatValue());
            BottomAppBar.this.materialShapeDrawable.invalidateSelf();
        }
    }

    /* renamed from: android.support.design.bottomappbar.BottomAppBar$3 */
    class C00303 extends AnimatorListenerAdapter {
        C00303() {
        }

        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.menuAnimator = null;
        }
    }

    /* renamed from: android.support.design.bottomappbar.BottomAppBar$5 */
    class C00325 extends AnimatorListenerAdapter {
        C00325() {
        }

        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.attachAnimator = null;
        }
    }

    /* renamed from: android.support.design.bottomappbar.BottomAppBar$6 */
    class C00336 implements AnimatorUpdateListener {
        C00336() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BottomAppBar.this.materialShapeDrawable.setInterpolation(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* renamed from: android.support.design.bottomappbar.BottomAppBar$7 */
    class C00347 extends AnimatorListenerAdapter {
        C00347() {
        }

        public void onAnimationStart(Animator animator) {
            animator = BottomAppBar.this;
            animator.maybeAnimateAttachChange(animator.fabAttached);
            animator = BottomAppBar.this;
            animator.maybeAnimateMenuView(animator.fabAlignmentMode, BottomAppBar.this.fabAttached);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAlignmentMode {
    }

    static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new C00351();
        int fabAlignmentMode;
        boolean fabAttached;

        /* renamed from: android.support.design.bottomappbar.BottomAppBar$SavedState$1 */
        static class C00351 implements ClassLoaderCreator<SavedState> {
            C00351() {
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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.fabAlignmentMode = parcel.readInt();
            this.fabAttached = parcel.readInt() != null ? true : null;
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.fabAlignmentMode);
            parcel.writeInt(this.fabAttached);
        }
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        private final Rect fabContentRect = new Rect();

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        private boolean updateFabPositionAndVisibility(FloatingActionButton floatingActionButton, BottomAppBar bottomAppBar) {
            ((LayoutParams) floatingActionButton.getLayoutParams()).anchorGravity = 17;
            bottomAppBar.addFabAnimationListeners(floatingActionButton);
            return true;
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i) {
            FloatingActionButton access$1100 = bottomAppBar.findDependentFab();
            if (access$1100 != null) {
                updateFabPositionAndVisibility(access$1100, bottomAppBar);
                access$1100.getMeasuredContentRect(this.fabContentRect);
                bottomAppBar.setFabDiameter(this.fabContentRect.height());
            }
            if (!bottomAppBar.isAnimationRunning()) {
                bottomAppBar.setCutoutState();
            }
            coordinatorLayout.onLayoutChild(bottomAppBar, i);
            return super.onLayoutChild(coordinatorLayout, bottomAppBar, i);
        }

        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomAppBar bottomAppBar, @NonNull View view, @NonNull View view2, int i, int i2) {
            return (!bottomAppBar.getHideOnScroll() || super.onStartNestedScroll(coordinatorLayout, bottomAppBar, view, view2, i, i2) == null) ? null : true;
        }

        protected void slideUp(BottomAppBar bottomAppBar) {
            super.slideUp(bottomAppBar);
            FloatingActionButton access$1100 = bottomAppBar.findDependentFab();
            if (access$1100 != null) {
                access$1100.clearAnimation();
                access$1100.animate().translationY(bottomAppBar.getFabTranslationY()).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setDuration(225);
            }
        }

        protected void slideDown(BottomAppBar bottomAppBar) {
            super.slideDown(bottomAppBar);
            bottomAppBar = bottomAppBar.findDependentFab();
            if (bottomAppBar != null) {
                bottomAppBar.getContentRect(this.fabContentRect);
                float measuredHeight = (float) (bottomAppBar.getMeasuredHeight() - this.fabContentRect.height());
                bottomAppBar.clearAnimation();
                bottomAppBar.animate().translationY(((float) (-bottomAppBar.getPaddingBottom())) + measuredHeight).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setDuration(175);
            }
        }
    }

    public void setSubtitle(CharSequence charSequence) {
    }

    public void setTitle(CharSequence charSequence) {
    }

    public BottomAppBar(Context context) {
        this(context, null, 0);
    }

    public BottomAppBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, C0026R.attr.bottomAppBarStyle);
    }

    public BottomAppBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.fabAttached = true;
        this.fabAnimationListener = new C00347();
        attributeSet = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C0026R.styleable.BottomAppBar, i, C0026R.style.Widget_MaterialComponents_BottomAppBar, new int[0]);
        context = MaterialResources.getColorStateList(context, attributeSet, C0026R.styleable.BottomAppBar_backgroundTint);
        i = (float) attributeSet.getDimensionPixelOffset(C0026R.styleable.BottomAppBar_fabCradleMargin, 0);
        float dimensionPixelOffset = (float) attributeSet.getDimensionPixelOffset(C0026R.styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0);
        float dimensionPixelOffset2 = (float) attributeSet.getDimensionPixelOffset(C0026R.styleable.BottomAppBar_fabCradleVerticalOffset, 0);
        this.fabAlignmentMode = attributeSet.getInt(C0026R.styleable.BottomAppBar_fabAlignmentMode, 0);
        this.hideOnScroll = attributeSet.getBoolean(C0026R.styleable.BottomAppBar_hideOnScroll, false);
        attributeSet.recycle();
        this.fabOffsetEndMode = getResources().getDimensionPixelOffset(C0026R.dimen.mtrl_bottomappbar_fabOffsetEndMode);
        this.topEdgeTreatment = new BottomAppBarTopEdgeTreatment(i, dimensionPixelOffset, dimensionPixelOffset2);
        attributeSet = new ShapePathModel();
        attributeSet.setTopEdge(this.topEdgeTreatment);
        this.materialShapeDrawable = new MaterialShapeDrawable(attributeSet);
        this.materialShapeDrawable.setShadowEnabled(true);
        this.materialShapeDrawable.setPaintStyle(Style.FILL);
        DrawableCompat.setTintList(this.materialShapeDrawable, context);
        ViewCompat.setBackground(this, this.materialShapeDrawable);
    }

    public int getFabAlignmentMode() {
        return this.fabAlignmentMode;
    }

    public void setFabAlignmentMode(int i) {
        maybeAnimateModeChange(i);
        maybeAnimateMenuView(i, this.fabAttached);
        this.fabAlignmentMode = i;
    }

    public void setBackgroundTint(@Nullable ColorStateList colorStateList) {
        DrawableCompat.setTintList(this.materialShapeDrawable, colorStateList);
    }

    @Nullable
    public ColorStateList getBackgroundTint() {
        return this.materialShapeDrawable.getTintList();
    }

    public float getFabCradleMargin() {
        return this.topEdgeTreatment.getFabCradleMargin();
    }

    public void setFabCradleMargin(@Dimension float f) {
        if (f != getFabCradleMargin()) {
            this.topEdgeTreatment.setFabCradleMargin(f);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    @Dimension
    public float getFabCradleRoundedCornerRadius() {
        return this.topEdgeTreatment.getFabCradleRoundedCornerRadius();
    }

    public void setFabCradleRoundedCornerRadius(@Dimension float f) {
        if (f != getFabCradleRoundedCornerRadius()) {
            this.topEdgeTreatment.setFabCradleRoundedCornerRadius(f);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    @Dimension
    public float getCradleVerticalOffset() {
        return this.topEdgeTreatment.getCradleVerticalOffset();
    }

    public void setCradleVerticalOffset(@Dimension float f) {
        if (f != getCradleVerticalOffset()) {
            this.topEdgeTreatment.setCradleVerticalOffset(f);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public boolean getHideOnScroll() {
        return this.hideOnScroll;
    }

    public void setHideOnScroll(boolean z) {
        this.hideOnScroll = z;
    }

    public void replaceMenu(@MenuRes int i) {
        getMenu().clear();
        inflateMenu(i);
    }

    void setFabDiameter(@Px int i) {
        i = (float) i;
        if (i != this.topEdgeTreatment.getFabDiameter()) {
            this.topEdgeTreatment.setFabDiameter(i);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    private void maybeAnimateModeChange(int i) {
        if (this.fabAlignmentMode != i) {
            if (ViewCompat.isLaidOut(this)) {
                Animator animator = this.modeAnimator;
                if (animator != null) {
                    animator.cancel();
                }
                Collection arrayList = new ArrayList();
                createCradleTranslationAnimation(i, arrayList);
                createFabTranslationXAnimation(i, arrayList);
                i = new AnimatorSet();
                i.playTogether(arrayList);
                this.modeAnimator = i;
                this.modeAnimator.addListener(new C00281());
                this.modeAnimator.start();
            }
        }
    }

    private void createCradleTranslationAnimation(int i, List<Animator> list) {
        if (this.fabAttached) {
            i = ValueAnimator.ofFloat(new float[]{this.topEdgeTreatment.getHorizontalOffset(), (float) getFabTranslationX(i)});
            i.addUpdateListener(new C00292());
            i.setDuration(ANIMATION_DURATION);
            list.add(i);
        }
    }

    @Nullable
    private FloatingActionButton findDependentFab() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View view : ((CoordinatorLayout) getParent()).getDependents(this)) {
            if (view instanceof FloatingActionButton) {
                return (FloatingActionButton) view;
            }
        }
        return null;
    }

    private boolean isVisibleFab() {
        FloatingActionButton findDependentFab = findDependentFab();
        return findDependentFab != null && findDependentFab.isOrWillBeShown();
    }

    private void createFabTranslationXAnimation(int i, List<Animator> list) {
        i = ObjectAnimator.ofFloat(findDependentFab(), "translationX", new float[]{(float) getFabTranslationX(i)});
        i.setDuration(ANIMATION_DURATION);
        list.add(i);
    }

    private void maybeAnimateMenuView(int i, boolean z) {
        if (ViewCompat.isLaidOut(this)) {
            Animator animator = this.menuAnimator;
            if (animator != null) {
                animator.cancel();
            }
            Collection arrayList = new ArrayList();
            if (!isVisibleFab()) {
                i = 0;
                z = false;
            }
            createMenuViewTranslationAnimation(i, z, arrayList);
            i = new AnimatorSet();
            i.playTogether(arrayList);
            this.menuAnimator = i;
            this.menuAnimator.addListener(new C00303());
            this.menuAnimator.start();
        }
    }

    private void createMenuViewTranslationAnimation(final int i, final boolean z, List<Animator> list) {
        final ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", new float[]{1.0f});
            if (this.fabAttached || (z && isVisibleFab())) {
                if (this.fabAlignmentMode != 1) {
                    if (i == 1) {
                    }
                }
                ObjectAnimator.ofFloat(actionMenuView, "alpha", new float[]{0.0f}).addListener(new AnimatorListenerAdapter() {
                    public boolean cancelled;

                    public void onAnimationCancel(Animator animator) {
                        this.cancelled = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        if (this.cancelled == null) {
                            BottomAppBar.this.translateActionMenuView(actionMenuView, i, z);
                        }
                    }
                });
                i = new AnimatorSet();
                i.setDuration(150);
                i.playSequentially(new Animator[]{r3, ofFloat});
                list.add(i);
            }
            if (actionMenuView.getAlpha() < 1065353216) {
                list.add(ofFloat);
            }
        }
    }

    private void maybeAnimateAttachChange(boolean z) {
        if (ViewCompat.isLaidOut(this)) {
            Animator animator = this.attachAnimator;
            if (animator != null) {
                animator.cancel();
            }
            Collection arrayList = new ArrayList();
            boolean z2 = z && isVisibleFab();
            createCradleShapeAnimation(z2, arrayList);
            createFabTranslationYAnimation(z, arrayList);
            z = new AnimatorSet();
            z.playTogether(arrayList);
            this.attachAnimator = z;
            this.attachAnimator.addListener(new C00325());
            this.attachAnimator.start();
        }
    }

    private void createCradleShapeAnimation(boolean z, List<Animator> list) {
        if (z) {
            this.topEdgeTreatment.setHorizontalOffset(getFabTranslationX());
        }
        float[] fArr = new float[2];
        fArr[0] = this.materialShapeDrawable.getInterpolation();
        fArr[1] = z ? true : false;
        z = ValueAnimator.ofFloat(fArr);
        z.addUpdateListener(new C00336());
        z.setDuration(ANIMATION_DURATION);
        list.add(z);
    }

    private void createFabTranslationYAnimation(boolean z, List<Animator> list) {
        FloatingActionButton findDependentFab = findDependentFab();
        if (findDependentFab != null) {
            z = ObjectAnimator.ofFloat(findDependentFab, "translationY", new float[]{getFabTranslationY(z)});
            z.setDuration(ANIMATION_DURATION);
            list.add(z);
        }
    }

    private float getFabTranslationY(boolean z) {
        FloatingActionButton findDependentFab = findDependentFab();
        if (findDependentFab == null) {
            return 0.0f;
        }
        Rect rect = new Rect();
        findDependentFab.getContentRect(rect);
        float height = (float) rect.height();
        if (height == 0.0f) {
            height = (float) findDependentFab.getMeasuredHeight();
        }
        float height2 = (float) (findDependentFab.getHeight() - rect.bottom);
        float f = ((-getCradleVerticalOffset()) + (height / 2.0f)) + height2;
        float height3 = ((float) (findDependentFab.getHeight() - rect.height())) - ((float) findDependentFab.getPaddingBottom());
        height2 = (float) (-getMeasuredHeight());
        if (z) {
            height3 = f;
        }
        return height2 + height3;
    }

    private float getFabTranslationY() {
        return getFabTranslationY(this.fabAttached);
    }

    private int getFabTranslationX(int i) {
        int i2 = 1;
        Object obj = ViewCompat.getLayoutDirection(this) == 1 ? 1 : null;
        if (i != 1) {
            return 0;
        }
        i = (getMeasuredWidth() / 2) - this.fabOffsetEndMode;
        if (obj != null) {
            i2 = -1;
        }
        return i * i2;
    }

    private float getFabTranslationX() {
        return (float) getFabTranslationX(this.fabAlignmentMode);
    }

    @Nullable
    private ActionMenuView getActionMenuView() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    private void translateActionMenuView(ActionMenuView actionMenuView, int i, boolean z) {
        Object obj = ViewCompat.getLayoutDirection(this) == 1 ? 1 : null;
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            Object obj2 = ((childAt.getLayoutParams() instanceof Toolbar.LayoutParams) && (((Toolbar.LayoutParams) childAt.getLayoutParams()).gravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 8388611) ? 1 : null;
            if (obj2 != null) {
                i2 = Math.max(i2, obj != null ? childAt.getLeft() : childAt.getRight());
            }
        }
        i = (i == 1 && z) ? (float) (i2 - (obj != null ? actionMenuView.getRight() : actionMenuView.getLeft())) : 0;
        actionMenuView.setTranslationX(i);
    }

    private void cancelAnimations() {
        Animator animator = this.attachAnimator;
        if (animator != null) {
            animator.cancel();
        }
        animator = this.menuAnimator;
        if (animator != null) {
            animator.cancel();
        }
        animator = this.modeAnimator;
        if (animator != null) {
            animator.cancel();
        }
    }

    private boolean isAnimationRunning() {
        Animator animator = this.attachAnimator;
        if (animator == null || !animator.isRunning()) {
            animator = this.menuAnimator;
            if (animator == null || !animator.isRunning()) {
                animator = this.modeAnimator;
                if (animator == null || !animator.isRunning()) {
                    return false;
                }
            }
        }
        return true;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        cancelAnimations();
        setCutoutState();
    }

    private void setCutoutState() {
        this.topEdgeTreatment.setHorizontalOffset(getFabTranslationX());
        FloatingActionButton findDependentFab = findDependentFab();
        MaterialShapeDrawable materialShapeDrawable = this.materialShapeDrawable;
        float f = (this.fabAttached && isVisibleFab()) ? 1.0f : 0.0f;
        materialShapeDrawable.setInterpolation(f);
        if (findDependentFab != null) {
            findDependentFab.setTranslationY(getFabTranslationY());
            findDependentFab.setTranslationX(getFabTranslationX());
        }
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            actionMenuView.setAlpha(1.0f);
            if (isVisibleFab()) {
                translateActionMenuView(actionMenuView, this.fabAlignmentMode, this.fabAttached);
            } else {
                translateActionMenuView(actionMenuView, 0, false);
            }
        }
    }

    private void addFabAnimationListeners(@NonNull FloatingActionButton floatingActionButton) {
        removeFabAnimationListeners(floatingActionButton);
        floatingActionButton.addOnHideAnimationListener(this.fabAnimationListener);
        floatingActionButton.addOnShowAnimationListener(this.fabAnimationListener);
    }

    private void removeFabAnimationListeners(@NonNull FloatingActionButton floatingActionButton) {
        floatingActionButton.removeOnHideAnimationListener(this.fabAnimationListener);
        floatingActionButton.removeOnShowAnimationListener(this.fabAnimationListener);
    }

    @NonNull
    public android.support.design.widget.CoordinatorLayout.Behavior<BottomAppBar> getBehavior() {
        return new Behavior();
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.fabAlignmentMode = this.fabAlignmentMode;
        savedState.fabAttached = this.fabAttached;
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.fabAlignmentMode = savedState.fabAlignmentMode;
            this.fabAttached = savedState.fabAttached;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
