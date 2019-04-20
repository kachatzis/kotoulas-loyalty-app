package android.support.design.transformation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.C0026R;
import android.support.design.animation.AnimationUtils;
import android.support.design.animation.AnimatorSetCompat;
import android.support.design.animation.ArgbEvaluatorCompat;
import android.support.design.animation.ChildrenAlphaProperty;
import android.support.design.animation.DrawableAlphaProperty;
import android.support.design.animation.MotionSpec;
import android.support.design.animation.MotionTiming;
import android.support.design.animation.Positioning;
import android.support.design.circularreveal.CircularRevealCompat;
import android.support.design.circularreveal.CircularRevealHelper;
import android.support.design.circularreveal.CircularRevealWidget;
import android.support.design.circularreveal.CircularRevealWidget.CircularRevealScrimColorProperty;
import android.support.design.circularreveal.CircularRevealWidget.RevealInfo;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.MathUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    private final int[] tmpArray;
    private final Rect tmpRect;
    private final RectF tmpRectF1;
    private final RectF tmpRectF2;

    protected static class FabTransformationSpec {
        public Positioning positioning;
        public MotionSpec timings;

        protected FabTransformationSpec() {
        }
    }

    protected abstract FabTransformationSpec onCreateMotionSpec(Context context, boolean z);

    public FabTransformationBehavior() {
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }

    @CallSuper
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() != 8) {
            boolean z = false;
            if ((view2 instanceof FloatingActionButton) == null) {
                return false;
            }
            View expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
            if (expandedComponentIdHint == null || expandedComponentIdHint == view.getId()) {
                z = true;
            }
            return z;
        }
        throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
    }

    @CallSuper
    public void onAttachedToLayoutParams(@NonNull LayoutParams layoutParams) {
        if (layoutParams.dodgeInsetEdges == 0) {
            layoutParams.dodgeInsetEdges = 80;
        }
    }

    @NonNull
    protected AnimatorSet onCreateExpandedStateChangeAnimation(View view, View view2, boolean z, boolean z2) {
        FabTransformationBehavior fabTransformationBehavior = this;
        final boolean z3 = z;
        FabTransformationSpec onCreateMotionSpec = onCreateMotionSpec(view2.getContext(), z3);
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        if (VERSION.SDK_INT >= 21) {
            createElevationAnimation(view, view2, z, z2, onCreateMotionSpec, arrayList, arrayList2);
        }
        RectF rectF = fabTransformationBehavior.tmpRectF1;
        View view3 = view;
        View view4 = view2;
        boolean z4 = z;
        boolean z5 = z2;
        FabTransformationSpec fabTransformationSpec = onCreateMotionSpec;
        List list = arrayList;
        List list2 = arrayList2;
        createTranslationAnimation(view3, view4, z4, z5, fabTransformationSpec, list, list2, rectF);
        float width = rectF.width();
        float height = rectF.height();
        createIconFadeAnimation(view3, view4, z4, z5, fabTransformationSpec, list, list2);
        createExpansionAnimation(view3, view4, z4, z5, fabTransformationSpec, width, height, arrayList, arrayList2);
        list = arrayList;
        list2 = arrayList2;
        createColorAnimation(view3, view4, z4, z5, fabTransformationSpec, list, list2);
        createChildrenFadeAnimation(view3, view4, z4, z5, fabTransformationSpec, list, list2);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        view4 = view;
        final View view5 = view2;
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                if (z3 != null) {
                    view5.setVisibility(0);
                    view4.setAlpha(0.0f);
                    view4.setVisibility(4);
                }
            }

            public void onAnimationEnd(Animator animator) {
                if (z3 == null) {
                    view5.setVisibility(4);
                    view4.setAlpha(1.0f);
                    view4.setVisibility(0);
                }
            }
        });
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            animatorSet.addListener((AnimatorListener) arrayList2.get(i));
        }
        return animatorSet;
    }

    @TargetApi(21)
    private void createElevationAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<AnimatorListener> list2) {
        list2 = ViewCompat.getElevation(view2) - ViewCompat.getElevation(view);
        if (z) {
            if (!z2) {
                view2.setTranslationZ(-list2);
            }
            view = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Z, new float[]{null});
        } else {
            view = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Z, new float[]{-list2});
        }
        fabTransformationSpec.timings.getTiming("elevation").apply(view);
        list.add(view);
    }

    private void createTranslationAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<AnimatorListener> list2, RectF rectF) {
        MotionTiming timing;
        MotionTiming timing2;
        Animator ofFloat;
        Animator ofFloat2;
        View view3 = view;
        View view4 = view2;
        FabTransformationSpec fabTransformationSpec2 = fabTransformationSpec;
        List<Animator> list3 = list;
        float calculateTranslationX = calculateTranslationX(view3, view4, fabTransformationSpec2.positioning);
        float calculateTranslationY = calculateTranslationY(view3, view4, fabTransformationSpec2.positioning);
        if (calculateTranslationX != 0.0f) {
            if (calculateTranslationY != 0.0f) {
                if ((!z || calculateTranslationY >= 0.0f) && (z || calculateTranslationY <= 0.0f)) {
                    timing = fabTransformationSpec2.timings.getTiming("translationXCurveDownwards");
                    timing2 = fabTransformationSpec2.timings.getTiming("translationYCurveDownwards");
                } else {
                    timing = fabTransformationSpec2.timings.getTiming("translationXCurveUpwards");
                    timing2 = fabTransformationSpec2.timings.getTiming("translationYCurveUpwards");
                }
                if (z) {
                    ofFloat = ObjectAnimator.ofFloat(view4, View.TRANSLATION_X, new float[]{-calculateTranslationX});
                    ofFloat2 = ObjectAnimator.ofFloat(view4, View.TRANSLATION_Y, new float[]{-calculateTranslationY});
                } else {
                    if (!z2) {
                        view4.setTranslationX(-calculateTranslationX);
                        view4.setTranslationY(-calculateTranslationY);
                    }
                    ofFloat = ObjectAnimator.ofFloat(view4, View.TRANSLATION_X, new float[]{0.0f});
                    ofFloat2 = ObjectAnimator.ofFloat(view4, View.TRANSLATION_Y, new float[]{0.0f});
                    calculateChildVisibleBoundsAtEndOfExpansion(view2, fabTransformationSpec, timing, timing2, -calculateTranslationX, -calculateTranslationY, 0.0f, 0.0f, rectF);
                }
                timing.apply(ofFloat);
                timing2.apply(ofFloat2);
                list3.add(ofFloat);
                list3.add(ofFloat2);
            }
        }
        timing = fabTransformationSpec2.timings.getTiming("translationXLinear");
        timing2 = fabTransformationSpec2.timings.getTiming("translationYLinear");
        if (z) {
            ofFloat = ObjectAnimator.ofFloat(view4, View.TRANSLATION_X, new float[]{-calculateTranslationX});
            ofFloat2 = ObjectAnimator.ofFloat(view4, View.TRANSLATION_Y, new float[]{-calculateTranslationY});
        } else {
            if (z2) {
                view4.setTranslationX(-calculateTranslationX);
                view4.setTranslationY(-calculateTranslationY);
            }
            ofFloat = ObjectAnimator.ofFloat(view4, View.TRANSLATION_X, new float[]{0.0f});
            ofFloat2 = ObjectAnimator.ofFloat(view4, View.TRANSLATION_Y, new float[]{0.0f});
            calculateChildVisibleBoundsAtEndOfExpansion(view2, fabTransformationSpec, timing, timing2, -calculateTranslationX, -calculateTranslationY, 0.0f, 0.0f, rectF);
        }
        timing.apply(ofFloat);
        timing2.apply(ofFloat2);
        list3.add(ofFloat);
        list3.add(ofFloat2);
    }

    private void createIconFadeAnimation(View view, final View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<AnimatorListener> list2) {
        if (view2 instanceof CircularRevealWidget) {
            if (view instanceof ImageView) {
                final CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
                view = ((ImageView) view).getDrawable();
                if (view != null) {
                    view.mutate();
                    if (z) {
                        if (!z2) {
                            view.setAlpha(255);
                        }
                        z = ObjectAnimator.ofInt(view, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[]{null});
                    } else {
                        z = ObjectAnimator.ofInt(view, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[]{255});
                    }
                    z.addUpdateListener(new AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            view2.invalidate();
                        }
                    });
                    fabTransformationSpec.timings.getTiming("iconFade").apply(z);
                    list.add(z);
                    list2.add(new AnimatorListenerAdapter() {
                        public void onAnimationStart(Animator animator) {
                            circularRevealWidget.setCircularRevealOverlayDrawable(view);
                        }

                        public void onAnimationEnd(Animator animator) {
                            circularRevealWidget.setCircularRevealOverlayDrawable(null);
                        }
                    });
                }
            }
        }
    }

    private void createExpansionAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, float f, float f2, List<Animator> list, List<AnimatorListener> list2) {
        FabTransformationBehavior fabTransformationBehavior = this;
        View view3 = view;
        View view4 = view2;
        FabTransformationSpec fabTransformationSpec2 = fabTransformationSpec;
        if (view4 instanceof CircularRevealWidget) {
            Animator createCircularReveal;
            final CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view4;
            float calculateRevealCenterX = calculateRevealCenterX(view3, view4, fabTransformationSpec2.positioning);
            float calculateRevealCenterY = calculateRevealCenterY(view3, view4, fabTransformationSpec2.positioning);
            ((FloatingActionButton) view3).getContentRect(fabTransformationBehavior.tmpRect);
            float width = ((float) fabTransformationBehavior.tmpRect.width()) / 2.0f;
            MotionTiming timing = fabTransformationSpec2.timings.getTiming("expansion");
            if (z) {
                if (!z2) {
                    circularRevealWidget.setRevealInfo(new RevealInfo(calculateRevealCenterX, calculateRevealCenterY, width));
                }
                if (z2) {
                    width = circularRevealWidget.getRevealInfo().radius;
                }
                createCircularReveal = CircularRevealCompat.createCircularReveal(circularRevealWidget, calculateRevealCenterX, calculateRevealCenterY, MathUtils.distanceToFurthestCorner(calculateRevealCenterX, calculateRevealCenterY, 0.0f, 0.0f, f, f2));
                createCircularReveal.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        animator = circularRevealWidget.getRevealInfo();
                        animator.radius = Float.MAX_VALUE;
                        circularRevealWidget.setRevealInfo(animator);
                    }
                });
                createPreFillRadialExpansion(view2, timing.getDelay(), (int) calculateRevealCenterX, (int) calculateRevealCenterY, width, list);
            } else {
                float f3 = circularRevealWidget.getRevealInfo().radius;
                Animator createCircularReveal2 = CircularRevealCompat.createCircularReveal(circularRevealWidget, calculateRevealCenterX, calculateRevealCenterY, width);
                int i = (int) calculateRevealCenterX;
                int i2 = (int) calculateRevealCenterY;
                View view5 = view2;
                int i3 = i;
                createPreFillRadialExpansion(view5, timing.getDelay(), i, i2, f3, list);
                createPostFillRadialExpansion(view5, timing.getDelay(), timing.getDuration(), fabTransformationSpec2.timings.getTotalDuration(), i3, i2, width, list);
                createCircularReveal = createCircularReveal2;
            }
            timing.apply(createCircularReveal);
            list.add(createCircularReveal);
            list2.add(CircularRevealCompat.createCircularRevealListener(circularRevealWidget));
        }
    }

    private void createColorAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<AnimatorListener> list2) {
        if ((view2 instanceof CircularRevealWidget) != null) {
            CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            view = getBackgroundTint(view);
            list2 = 16777215 & view;
            if (z) {
                if (!z2) {
                    circularRevealWidget.setCircularRevealScrimColor(view);
                }
                view = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[]{list2});
            } else {
                view = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[]{view});
            }
            view.setEvaluator(ArgbEvaluatorCompat.getInstance());
            fabTransformationSpec.timings.getTiming("color").apply(view);
            list.add(view);
        }
    }

    private void createChildrenFadeAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<AnimatorListener> list2) {
        if ((view2 instanceof ViewGroup) != null) {
            if ((view2 instanceof CircularRevealWidget) == null || CircularRevealHelper.STRATEGY != null) {
                view = calculateChildContentContainer(view2);
                if (view != null) {
                    if (z) {
                        if (!z2) {
                            ChildrenAlphaProperty.CHILDREN_ALPHA.set(view, Float.valueOf(0.0f));
                        }
                        view = ObjectAnimator.ofFloat(view, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[]{true});
                    } else {
                        view = ObjectAnimator.ofFloat(view, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[]{null});
                    }
                    fabTransformationSpec.timings.getTiming("contentFade").apply(view);
                    list.add(view);
                }
            }
        }
    }

    private float calculateTranslationX(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        view = positioning.gravity & 7;
        view = view != 1 ? view != 3 ? view != 5 ? null : rectF2.right - rectF.right : rectF2.left - rectF.left : rectF2.centerX() - rectF.centerX();
        return view + positioning.xAdjustment;
    }

    private float calculateTranslationY(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        view = positioning.gravity & 112;
        view = view != 16 ? view != 48 ? view != 80 ? null : rectF2.bottom - rectF.bottom : rectF2.top - rectF.top : rectF2.centerY() - rectF.centerY();
        return view + positioning.yAdjustment;
    }

    private void calculateWindowBounds(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        int[] iArr = this.tmpArray;
        view.getLocationInWindow(iArr);
        rectF.offsetTo((float) iArr[0], (float) iArr[1]);
        rectF.offset((float) ((int) (-view.getTranslationX())), (float) ((int) (-view.getTranslationY())));
    }

    private float calculateRevealCenterX(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        rectF2.offset(-calculateTranslationX(view, view2, positioning), null);
        return rectF.centerX() - rectF2.left;
    }

    private float calculateRevealCenterY(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        rectF2.offset(null, -calculateTranslationY(view, view2, positioning));
        return rectF.centerY() - rectF2.top;
    }

    private void calculateChildVisibleBoundsAtEndOfExpansion(View view, FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, MotionTiming motionTiming2, float f, float f2, float f3, float f4, RectF rectF) {
        motionTiming = calculateValueOfAnimationAtEndOfExpansion(fabTransformationSpec, motionTiming, f, f3);
        fabTransformationSpec = calculateValueOfAnimationAtEndOfExpansion(fabTransformationSpec, motionTiming2, f2, f4);
        motionTiming2 = this.tmpRect;
        view.getWindowVisibleDisplayFrame(motionTiming2);
        f = this.tmpRectF1;
        f.set(motionTiming2);
        motionTiming2 = this.tmpRectF2;
        calculateWindowBounds(view, motionTiming2);
        motionTiming2.offset(motionTiming, fabTransformationSpec);
        motionTiming2.intersect(f);
        rectF.set(motionTiming2);
    }

    private float calculateValueOfAnimationAtEndOfExpansion(FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, float f, float f2) {
        long delay = motionTiming.getDelay();
        long duration = motionTiming.getDuration();
        fabTransformationSpec = fabTransformationSpec.timings.getTiming("expansion");
        return AnimationUtils.lerp(f, f2, motionTiming.getInterpolator().getInterpolation(((float) (((fabTransformationSpec.getDelay() + fabTransformationSpec.getDuration()) + 17) - delay)) / ((float) duration)));
    }

    @Nullable
    private ViewGroup calculateChildContentContainer(View view) {
        View findViewById = view.findViewById(C0026R.id.mtrl_child_content_container);
        if (findViewById != null) {
            return toViewGroupOrNull(findViewById);
        }
        if (!(view instanceof TransformationChildLayout)) {
            if (!(view instanceof TransformationChildCard)) {
                return toViewGroupOrNull(view);
            }
        }
        return toViewGroupOrNull(((ViewGroup) view).getChildAt(0));
    }

    @Nullable
    private ViewGroup toViewGroupOrNull(View view) {
        return view instanceof ViewGroup ? (ViewGroup) view : null;
    }

    private int getBackgroundTint(View view) {
        ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(view);
        return backgroundTintList != null ? backgroundTintList.getColorForState(view.getDrawableState(), backgroundTintList.getDefaultColor()) : null;
    }

    private void createPreFillRadialExpansion(View view, long j, int i, int i2, float f, List<Animator> list) {
        if (VERSION.SDK_INT >= 21 && j > 0) {
            view = ViewAnimationUtils.createCircularReveal(view, i, i2, f, f);
            view.setStartDelay(0);
            view.setDuration(j);
            list.add(view);
        }
    }

    private void createPostFillRadialExpansion(View view, long j, long j2, long j3, int i, int i2, float f, List<Animator> list) {
        if (VERSION.SDK_INT >= 21) {
            j += j2;
            if (j < j3) {
                view = ViewAnimationUtils.createCircularReveal(view, i, i2, f, f);
                view.setStartDelay(j);
                view.setDuration(j3 - j);
                list.add(view);
            }
        }
    }
}
