package android.support.transition;

import android.graphics.Rect;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

public class SidePropagation extends VisibilityPropagation {
    private float mPropagationSpeed = 3.0f;
    private int mSide = 80;

    public void setSide(int i) {
        this.mSide = i;
    }

    public void setPropagationSpeed(float f) {
        if (f != 0.0f) {
            this.mPropagationSpeed = f;
            return;
        }
        throw new IllegalArgumentException("propagationSpeed may not be 0");
    }

    public long getStartDelay(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        SidePropagation sidePropagation = this;
        TransitionValues transitionValues3 = transitionValues;
        if (transitionValues3 == null && transitionValues2 == null) {
            return 0;
        }
        int i;
        int viewX;
        int viewY;
        int[] iArr;
        int round;
        int round2;
        int width;
        int height;
        int centerX;
        int centerY;
        float distance;
        long duration;
        Rect epicenter = transition.getEpicenter();
        if (transitionValues2 != null) {
            if (getViewVisibility(transitionValues3) != 0) {
                transitionValues3 = transitionValues2;
                i = 1;
                viewX = getViewX(transitionValues3);
                viewY = getViewY(transitionValues3);
                iArr = new int[2];
                viewGroup.getLocationOnScreen(iArr);
                round = iArr[0] + Math.round(viewGroup.getTranslationX());
                round2 = iArr[1] + Math.round(viewGroup.getTranslationY());
                width = round + viewGroup.getWidth();
                height = round2 + viewGroup.getHeight();
                if (epicenter == null) {
                    centerX = epicenter.centerX();
                    centerY = epicenter.centerY();
                } else {
                    centerX = (round + width) / 2;
                    centerY = (round2 + height) / 2;
                }
                distance = ((float) distance(viewGroup, viewX, viewY, centerX, centerY, round, round2, width, height)) / ((float) getMaxDistance(viewGroup));
                duration = transition.getDuration();
                if (duration < 0) {
                    duration = 300;
                }
                return (long) Math.round((((float) (duration * ((long) i))) / sidePropagation.mPropagationSpeed) * distance);
            }
        }
        i = -1;
        viewX = getViewX(transitionValues3);
        viewY = getViewY(transitionValues3);
        iArr = new int[2];
        viewGroup.getLocationOnScreen(iArr);
        round = iArr[0] + Math.round(viewGroup.getTranslationX());
        round2 = iArr[1] + Math.round(viewGroup.getTranslationY());
        width = round + viewGroup.getWidth();
        height = round2 + viewGroup.getHeight();
        if (epicenter == null) {
            centerX = (round + width) / 2;
            centerY = (round2 + height) / 2;
        } else {
            centerX = epicenter.centerX();
            centerY = epicenter.centerY();
        }
        distance = ((float) distance(viewGroup, viewX, viewY, centerX, centerY, round, round2, width, height)) / ((float) getMaxDistance(viewGroup));
        duration = transition.getDuration();
        if (duration < 0) {
            duration = 300;
        }
        return (long) Math.round((((float) (duration * ((long) i))) / sidePropagation.mPropagationSpeed) * distance);
    }

    private int distance(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = this.mSide;
        Object obj = 1;
        if (i9 == 8388611) {
            if (ViewCompat.getLayoutDirection(view) != 1) {
                obj = null;
            }
            i9 = obj != null ? 5 : 3;
        } else if (i9 == GravityCompat.END) {
            if (ViewCompat.getLayoutDirection(view) != 1) {
                obj = null;
            }
            i9 = obj != null ? 3 : 5;
        }
        if (i9 == 3) {
            return (i7 - i) + Math.abs(i4 - i2);
        }
        if (i9 == 5) {
            return (i - i5) + Math.abs(i4 - i2);
        }
        if (i9 == 48) {
            return (i8 - i2) + Math.abs(i3 - i);
        }
        if (i9 != 80) {
            return 0;
        }
        return (i2 - i6) + Math.abs(i3 - i);
    }

    private int getMaxDistance(ViewGroup viewGroup) {
        int i = this.mSide;
        if (i == 3 || i == 5 || i == 8388611 || i == GravityCompat.END) {
            return viewGroup.getWidth();
        }
        return viewGroup.getHeight();
    }
}
