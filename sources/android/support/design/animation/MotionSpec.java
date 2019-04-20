package android.support.design.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AnimatorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleableRes;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MotionSpec {
    private static final String TAG = "MotionSpec";
    private final SimpleArrayMap<String, MotionTiming> timings = new SimpleArrayMap();

    public boolean hasTiming(String str) {
        return this.timings.get(str) != null ? true : null;
    }

    public MotionTiming getTiming(String str) {
        if (hasTiming(str)) {
            return (MotionTiming) this.timings.get(str);
        }
        throw new IllegalArgumentException();
    }

    public void setTiming(String str, @Nullable MotionTiming motionTiming) {
        this.timings.put(str, motionTiming);
    }

    public long getTotalDuration() {
        int size = this.timings.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            MotionTiming motionTiming = (MotionTiming) this.timings.valueAt(i);
            j = Math.max(j, motionTiming.getDelay() + motionTiming.getDuration());
        }
        return j;
    }

    @Nullable
    public static MotionSpec createFromAttribute(Context context, TypedArray typedArray, @StyleableRes int i) {
        if (typedArray.hasValue(i)) {
            typedArray = typedArray.getResourceId(i, 0);
            if (typedArray != null) {
                return createFromResource(context, typedArray);
            }
        }
        return null;
    }

    @Nullable
    public static MotionSpec createFromResource(Context context, @AnimatorRes int i) {
        try {
            context = AnimatorInflater.loadAnimator(context, i);
            if (context instanceof AnimatorSet) {
                return createSpecFromAnimators(((AnimatorSet) context).getChildAnimations());
            }
            if (context == null) {
                return null;
            }
            List arrayList = new ArrayList();
            arrayList.add(context);
            return createSpecFromAnimators(arrayList);
        } catch (Context context2) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Can't load animation resource ID #0x");
            stringBuilder.append(Integer.toHexString(i));
            Log.w(str, stringBuilder.toString(), context2);
            return null;
        }
    }

    private static MotionSpec createSpecFromAnimators(List<Animator> list) {
        MotionSpec motionSpec = new MotionSpec();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            addTimingFromAnimator(motionSpec, (Animator) list.get(i));
        }
        return motionSpec;
    }

    private static void addTimingFromAnimator(MotionSpec motionSpec, Animator animator) {
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            motionSpec.setTiming(objectAnimator.getPropertyName(), MotionTiming.createFromAnimator(objectAnimator));
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Animator must be an ObjectAnimator: ");
        stringBuilder.append(animator);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (getClass() == obj.getClass()) {
                return this.timings.equals(((MotionSpec) obj).timings);
            }
        }
        return null;
    }

    public int hashCode() {
        return this.timings.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('\n');
        stringBuilder.append(getClass().getName());
        stringBuilder.append('{');
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" timings: ");
        stringBuilder.append(this.timings);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }
}
