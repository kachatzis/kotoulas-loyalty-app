package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.transition.Transition.TransitionListener;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Visibility extends Transition {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;
    private static final String PROPNAME_PARENT = "android:visibility:parent";
    private static final String PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation";
    static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
    private static final String[] sTransitionProperties = new String[]{PROPNAME_VISIBILITY, PROPNAME_PARENT};
    private int mMode = 3;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    private static class VisibilityInfo {
        ViewGroup mEndParent;
        int mEndVisibility;
        boolean mFadeIn;
        ViewGroup mStartParent;
        int mStartVisibility;
        boolean mVisibilityChange;

        VisibilityInfo() {
        }
    }

    private static class DisappearListener extends AnimatorListenerAdapter implements TransitionListener, AnimatorPauseListenerCompat {
        boolean mCanceled = false;
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final boolean mSuppressLayout;
        private final View mView;

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onTransitionCancel(@NonNull Transition transition) {
        }

        public void onTransitionStart(@NonNull Transition transition) {
        }

        DisappearListener(View view, int i, boolean z) {
            this.mView = view;
            this.mFinalVisibility = i;
            this.mParent = (ViewGroup) view.getParent();
            this.mSuppressLayout = z;
            suppressLayout(true);
        }

        public void onAnimationPause(Animator animator) {
            if (this.mCanceled == null) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
            }
        }

        public void onAnimationResume(Animator animator) {
            if (this.mCanceled == null) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.mCanceled = true;
        }

        public void onAnimationEnd(Animator animator) {
            hideViewWhenNotCanceled();
        }

        public void onTransitionEnd(@NonNull Transition transition) {
            hideViewWhenNotCanceled();
            transition.removeListener(this);
        }

        public void onTransitionPause(@NonNull Transition transition) {
            suppressLayout(null);
        }

        public void onTransitionResume(@NonNull Transition transition) {
            suppressLayout(true);
        }

        private void hideViewWhenNotCanceled() {
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
                ViewGroup viewGroup = this.mParent;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            suppressLayout(false);
        }

        private void suppressLayout(boolean z) {
            if (this.mSuppressLayout && this.mLayoutSuppressed != z) {
                ViewGroup viewGroup = this.mParent;
                if (viewGroup != null) {
                    this.mLayoutSuppressed = z;
                    ViewGroupUtils.suppressLayout(viewGroup, z);
                }
            }
        }
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public Visibility(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        context = context.obtainStyledAttributes(attributeSet, Styleable.VISIBILITY_TRANSITION);
        attributeSet = TypedArrayUtils.getNamedInt(context, (XmlResourceParser) attributeSet, "transitionVisibilityMode", 0, 0);
        context.recycle();
        if (attributeSet != null) {
            setMode(attributeSet);
        }
    }

    public void setMode(int i) {
        if ((i & -4) == 0) {
            this.mMode = i;
            return;
        }
        throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
    }

    public int getMode() {
        return this.mMode;
    }

    @Nullable
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    private void captureValues(TransitionValues transitionValues) {
        transitionValues.values.put(PROPNAME_VISIBILITY, Integer.valueOf(transitionValues.view.getVisibility()));
        transitionValues.values.put(PROPNAME_PARENT, transitionValues.view.getParent());
        Object obj = new int[2];
        transitionValues.view.getLocationOnScreen(obj);
        transitionValues.values.put(PROPNAME_SCREEN_LOCATION, obj);
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public boolean isVisible(TransitionValues transitionValues) {
        boolean z = false;
        if (transitionValues == null) {
            return false;
        }
        int intValue = ((Integer) transitionValues.values.get(PROPNAME_VISIBILITY)).intValue();
        View view = (View) transitionValues.values.get(PROPNAME_PARENT);
        if (intValue == 0 && view != null) {
            z = true;
        }
        return z;
    }

    private VisibilityInfo getVisibilityChangeInfo(TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo visibilityInfo = new VisibilityInfo();
        visibilityInfo.mVisibilityChange = false;
        visibilityInfo.mFadeIn = false;
        if (transitionValues == null || !transitionValues.values.containsKey(PROPNAME_VISIBILITY)) {
            visibilityInfo.mStartVisibility = -1;
            visibilityInfo.mStartParent = null;
        } else {
            visibilityInfo.mStartVisibility = ((Integer) transitionValues.values.get(PROPNAME_VISIBILITY)).intValue();
            visibilityInfo.mStartParent = (ViewGroup) transitionValues.values.get(PROPNAME_PARENT);
        }
        if (transitionValues2 == null || !transitionValues2.values.containsKey(PROPNAME_VISIBILITY)) {
            visibilityInfo.mEndVisibility = -1;
            visibilityInfo.mEndParent = null;
        } else {
            visibilityInfo.mEndVisibility = ((Integer) transitionValues2.values.get(PROPNAME_VISIBILITY)).intValue();
            visibilityInfo.mEndParent = (ViewGroup) transitionValues2.values.get(PROPNAME_PARENT);
        }
        if (transitionValues == null || transitionValues2 == null) {
            if (transitionValues == null && visibilityInfo.mEndVisibility == null) {
                visibilityInfo.mFadeIn = true;
                visibilityInfo.mVisibilityChange = true;
            } else if (transitionValues2 == null && visibilityInfo.mStartVisibility == null) {
                visibilityInfo.mFadeIn = false;
                visibilityInfo.mVisibilityChange = true;
            }
        } else if (visibilityInfo.mStartVisibility == visibilityInfo.mEndVisibility && visibilityInfo.mStartParent == visibilityInfo.mEndParent) {
            return visibilityInfo;
        } else {
            if (visibilityInfo.mStartVisibility != visibilityInfo.mEndVisibility) {
                if (visibilityInfo.mStartVisibility == null) {
                    visibilityInfo.mFadeIn = false;
                    visibilityInfo.mVisibilityChange = true;
                } else if (visibilityInfo.mEndVisibility == null) {
                    visibilityInfo.mFadeIn = true;
                    visibilityInfo.mVisibilityChange = true;
                }
            } else if (visibilityInfo.mEndParent == null) {
                visibilityInfo.mFadeIn = false;
                visibilityInfo.mVisibilityChange = true;
            } else if (visibilityInfo.mStartParent == null) {
                visibilityInfo.mFadeIn = true;
                visibilityInfo.mVisibilityChange = true;
            }
        }
        return visibilityInfo;
    }

    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        VisibilityInfo visibilityChangeInfo = getVisibilityChangeInfo(transitionValues, transitionValues2);
        if (!visibilityChangeInfo.mVisibilityChange || (visibilityChangeInfo.mStartParent == null && visibilityChangeInfo.mEndParent == null)) {
            return null;
        }
        if (visibilityChangeInfo.mFadeIn) {
            return onAppear(viewGroup, transitionValues, visibilityChangeInfo.mStartVisibility, transitionValues2, visibilityChangeInfo.mEndVisibility);
        }
        return onDisappear(viewGroup, transitionValues, visibilityChangeInfo.mStartVisibility, transitionValues2, visibilityChangeInfo.mEndVisibility);
    }

    public Animator onAppear(ViewGroup viewGroup, TransitionValues transitionValues, int i, TransitionValues transitionValues2, int i2) {
        if ((this.mMode & 1) == 1) {
            if (transitionValues2 != null) {
                if (transitionValues == null) {
                    View view = (View) transitionValues2.view.getParent();
                    if (getVisibilityChangeInfo(getMatchedTransitionValues(view, false), getTransitionValues(view, false)).mVisibilityChange != 0) {
                        return null;
                    }
                }
                return onAppear(viewGroup, transitionValues2.view, transitionValues, transitionValues2);
            }
        }
        return null;
    }

    public Animator onDisappear(ViewGroup viewGroup, TransitionValues transitionValues, int i, TransitionValues transitionValues2, int i2) {
        if ((this.mMode & 2) != 2) {
            return null;
        }
        i = transitionValues != null ? transitionValues.view : 0;
        View view = transitionValues2 != null ? transitionValues2.view : null;
        if (view != null) {
            if (view.getParent() != null) {
                if (i2 != 4) {
                    if (i != view) {
                        if (this.mCanRemoveViews) {
                            view = null;
                            if (i == 0 && transitionValues != null) {
                                int[] iArr = (int[]) transitionValues.values.get(PROPNAME_SCREEN_LOCATION);
                                int i3 = iArr[0];
                                i2 = iArr[1];
                                int[] iArr2 = new int[2];
                                viewGroup.getLocationOnScreen(iArr2);
                                i.offsetLeftAndRight((i3 - iArr2[0]) - i.getLeft());
                                i.offsetTopAndBottom((i2 - iArr2[1]) - i.getTop());
                                i2 = ViewGroupUtils.getOverlay(viewGroup);
                                i2.add(i);
                                viewGroup = onDisappear(viewGroup, i, transitionValues, transitionValues2);
                                if (viewGroup == null) {
                                    i2.remove(i);
                                } else {
                                    viewGroup.addListener(new AnimatorListenerAdapter() {
                                        public void onAnimationEnd(Animator animator) {
                                            i2.remove(i);
                                        }
                                    });
                                }
                                return viewGroup;
                            } else if (view == null) {
                                return null;
                            } else {
                                i = view.getVisibility();
                                ViewUtils.setTransitionVisibility(view, 0);
                                viewGroup = onDisappear(viewGroup, view, transitionValues, transitionValues2);
                                if (viewGroup == null) {
                                    transitionValues = new DisappearListener(view, i2, true);
                                    viewGroup.addListener(transitionValues);
                                    AnimatorUtils.addPauseListener(viewGroup, transitionValues);
                                    addListener(transitionValues);
                                } else {
                                    ViewUtils.setTransitionVisibility(view, i);
                                }
                                return viewGroup;
                            }
                        }
                        i = TransitionUtils.copyViewImage(viewGroup, i, (View) i.getParent());
                        view = null;
                        if (i == 0) {
                        }
                        if (view == null) {
                            return null;
                        }
                        i = view.getVisibility();
                        ViewUtils.setTransitionVisibility(view, 0);
                        viewGroup = onDisappear(viewGroup, view, transitionValues, transitionValues2);
                        if (viewGroup == null) {
                            ViewUtils.setTransitionVisibility(view, i);
                        } else {
                            transitionValues = new DisappearListener(view, i2, true);
                            viewGroup.addListener(transitionValues);
                            AnimatorUtils.addPauseListener(viewGroup, transitionValues);
                            addListener(transitionValues);
                        }
                        return viewGroup;
                    }
                }
                i = 0;
                if (i == 0) {
                }
                if (view == null) {
                    return null;
                }
                i = view.getVisibility();
                ViewUtils.setTransitionVisibility(view, 0);
                viewGroup = onDisappear(viewGroup, view, transitionValues, transitionValues2);
                if (viewGroup == null) {
                    transitionValues = new DisappearListener(view, i2, true);
                    viewGroup.addListener(transitionValues);
                    AnimatorUtils.addPauseListener(viewGroup, transitionValues);
                    addListener(transitionValues);
                } else {
                    ViewUtils.setTransitionVisibility(view, i);
                }
                return viewGroup;
            }
        }
        if (view != null) {
            i = view;
            view = null;
        } else {
            if (i != 0) {
                if (i.getParent() != null) {
                    if (i.getParent() instanceof View) {
                        view = (View) i.getParent();
                        if (getVisibilityChangeInfo(getTransitionValues(view, true), getMatchedTransitionValues(view, true)).mVisibilityChange) {
                            if (view.getParent() == null) {
                                int id = view.getId();
                                if (!(id == -1 || viewGroup.findViewById(id) == null || !this.mCanRemoveViews)) {
                                }
                            }
                            i = 0;
                        } else {
                            i = TransitionUtils.copyViewImage(viewGroup, i, view);
                        }
                        view = null;
                    }
                }
                view = null;
            }
            i = 0;
            view = i;
        }
        if (i == 0) {
        }
        if (view == null) {
            return null;
        }
        i = view.getVisibility();
        ViewUtils.setTransitionVisibility(view, 0);
        viewGroup = onDisappear(viewGroup, view, transitionValues, transitionValues2);
        if (viewGroup == null) {
            ViewUtils.setTransitionVisibility(view, i);
        } else {
            transitionValues = new DisappearListener(view, i2, true);
            viewGroup.addListener(transitionValues);
            AnimatorUtils.addPauseListener(viewGroup, transitionValues);
            addListener(transitionValues);
        }
        return viewGroup;
    }

    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        boolean z = false;
        if (transitionValues == null && transitionValues2 == null) {
            return false;
        }
        if (transitionValues != null && transitionValues2 != null && transitionValues2.values.containsKey(PROPNAME_VISIBILITY) != transitionValues.values.containsKey(PROPNAME_VISIBILITY)) {
            return false;
        }
        transitionValues = getVisibilityChangeInfo(transitionValues, transitionValues2);
        if (transitionValues.mVisibilityChange != null && (transitionValues.mStartVisibility == null || transitionValues.mEndVisibility == null)) {
            z = true;
        }
        return z;
    }
}
