package android.support.design.transformation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.animation.AnimatorSetCompat;
import android.support.design.animation.MotionTiming;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class FabTransformationScrimBehavior extends ExpandableTransformationBehavior {
    public static final long COLLAPSE_DELAY = 0;
    public static final long COLLAPSE_DURATION = 150;
    public static final long EXPAND_DELAY = 75;
    public static final long EXPAND_DURATION = 150;
    private final MotionTiming collapseTiming = new MotionTiming(0, 150);
    private final MotionTiming expandTiming = new MotionTiming(75, 150);

    public FabTransformationScrimBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return view2 instanceof FloatingActionButton;
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return super.onTouchEvent(coordinatorLayout, view, motionEvent);
    }

    @NonNull
    protected AnimatorSet onCreateExpandedStateChangeAnimation(View view, final View view2, final boolean z, boolean z2) {
        view = new ArrayList();
        createScrimAnimation(view2, z, z2, view, new ArrayList());
        z2 = new AnimatorSet();
        AnimatorSetCompat.playTogether(z2, view);
        z2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                if (z != null) {
                    view2.setVisibility(0);
                }
            }

            public void onAnimationEnd(Animator animator) {
                if (z == null) {
                    view2.setVisibility(4);
                }
            }
        });
        return z2;
    }

    private void createScrimAnimation(View view, boolean z, boolean z2, List<Animator> list, List<AnimatorListener> list2) {
        list2 = z ? this.expandTiming : this.collapseTiming;
        if (z) {
            if (!z2) {
                view.setAlpha(0.0f);
            }
            view = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1065353216});
        } else {
            view = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{null});
        }
        list2.apply(view);
        list.add(view);
    }
}
