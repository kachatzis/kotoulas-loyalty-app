package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.annotation.VisibleForTesting;
import android.support.design.C0026R;
import android.support.design.animation.AnimationUtils;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.internal.ViewUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DrawableUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TextInputLayout extends LinearLayout {
    public static final int BOX_BACKGROUND_FILLED = 1;
    public static final int BOX_BACKGROUND_NONE = 0;
    public static final int BOX_BACKGROUND_OUTLINE = 2;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final int LABEL_SCALE_ANIMATION_DURATION = 167;
    private static final String LOG_TAG = "TextInputLayout";
    private ValueAnimator animator;
    private GradientDrawable boxBackground;
    @ColorInt
    private int boxBackgroundColor;
    private int boxBackgroundMode;
    private final int boxBottomOffsetPx;
    private final int boxCollapsedPaddingTopPx;
    private float boxCornerRadiusBottomEnd;
    private float boxCornerRadiusBottomStart;
    private float boxCornerRadiusTopEnd;
    private float boxCornerRadiusTopStart;
    private final int boxLabelCutoutPaddingPx;
    @ColorInt
    private int boxStrokeColor;
    private final int boxStrokeWidthDefaultPx;
    private final int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;
    final CollapsingTextHelper collapsingTextHelper;
    boolean counterEnabled;
    private int counterMaxLength;
    private final int counterOverflowTextAppearance;
    private boolean counterOverflowed;
    private final int counterTextAppearance;
    private TextView counterView;
    private ColorStateList defaultHintTextColor;
    @ColorInt
    private final int defaultStrokeColor;
    @ColorInt
    private final int disabledColor;
    EditText editText;
    private Drawable editTextOriginalDrawable;
    @ColorInt
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean hasPasswordToggleTintList;
    private boolean hasPasswordToggleTintMode;
    private boolean hasReconstructedEditTextBackground;
    private CharSequence hint;
    private boolean hintAnimationEnabled;
    private boolean hintEnabled;
    private boolean hintExpanded;
    @ColorInt
    private final int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    private final IndicatorViewController indicatorViewController;
    private final FrameLayout inputFrame;
    private boolean isProvidingHint;
    private Drawable originalEditTextEndDrawable;
    private CharSequence originalHint;
    private CharSequence passwordToggleContentDesc;
    private Drawable passwordToggleDrawable;
    private Drawable passwordToggleDummyDrawable;
    private boolean passwordToggleEnabled;
    private ColorStateList passwordToggleTintList;
    private Mode passwordToggleTintMode;
    private CheckableImageButton passwordToggleView;
    private boolean passwordToggledVisible;
    private boolean restoringSavedState;
    private final Rect tmpRect;
    private final RectF tmpRectF;
    private Typeface typeface;

    /* renamed from: android.support.design.widget.TextInputLayout$1 */
    class C00791 implements TextWatcher {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C00791() {
        }

        public void afterTextChanged(Editable editable) {
            TextInputLayout textInputLayout = TextInputLayout.this;
            textInputLayout.updateLabelState(textInputLayout.restoringSavedState ^ 1);
            if (TextInputLayout.this.counterEnabled) {
                TextInputLayout.this.updateCounter(editable.length());
            }
        }
    }

    /* renamed from: android.support.design.widget.TextInputLayout$2 */
    class C00802 implements OnClickListener {
        C00802() {
        }

        public void onClick(View view) {
            TextInputLayout.this.passwordVisibilityToggleRequested(false);
        }
    }

    /* renamed from: android.support.design.widget.TextInputLayout$3 */
    class C00813 implements AnimatorUpdateListener {
        C00813() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TextInputLayout.this.collapsingTextHelper.setExpansionFraction(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoxBackgroundMode {
    }

    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout textInputLayout) {
            this.layout = textInputLayout;
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            Object obj;
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            view = this.layout.getEditText();
            view = view != null ? view.getText() : null;
            CharSequence hint = this.layout.getHint();
            CharSequence error = this.layout.getError();
            CharSequence counterOverflowDescription = this.layout.getCounterOverflowDescription();
            int isEmpty = TextUtils.isEmpty(view) ^ 1;
            int isEmpty2 = TextUtils.isEmpty(hint) ^ 1;
            int isEmpty3 = TextUtils.isEmpty(error) ^ 1;
            boolean z = false;
            if (isEmpty3 == 0) {
                if (TextUtils.isEmpty(counterOverflowDescription)) {
                    obj = null;
                    if (isEmpty != 0) {
                        accessibilityNodeInfoCompat.setText(view);
                    } else if (isEmpty2 != 0) {
                        accessibilityNodeInfoCompat.setText(hint);
                    }
                    if (isEmpty2 != 0) {
                        accessibilityNodeInfoCompat.setHintText(hint);
                        if (isEmpty == 0 && isEmpty2 != 0) {
                            z = true;
                        }
                        accessibilityNodeInfoCompat.setShowingHintText(z);
                    }
                    if (obj != null) {
                        if (isEmpty3 != 0) {
                            error = counterOverflowDescription;
                        }
                        accessibilityNodeInfoCompat.setError(error);
                        accessibilityNodeInfoCompat.setContentInvalid(true);
                    }
                }
            }
            obj = 1;
            if (isEmpty != 0) {
                accessibilityNodeInfoCompat.setText(view);
            } else if (isEmpty2 != 0) {
                accessibilityNodeInfoCompat.setText(hint);
            }
            if (isEmpty2 != 0) {
                accessibilityNodeInfoCompat.setHintText(hint);
                z = true;
                accessibilityNodeInfoCompat.setShowingHintText(z);
            }
            if (obj != null) {
                if (isEmpty3 != 0) {
                    error = counterOverflowDescription;
                }
                accessibilityNodeInfoCompat.setError(error);
                accessibilityNodeInfoCompat.setContentInvalid(true);
            }
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            view = this.layout.getEditText();
            view = view != null ? view.getText() : null;
            if (TextUtils.isEmpty(view)) {
                view = this.layout.getHint();
            }
            if (!TextUtils.isEmpty(view)) {
                accessibilityEvent.getText().add(view);
            }
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new C00821();
        CharSequence error;
        boolean isPasswordToggledVisible;

        /* renamed from: android.support.design.widget.TextInputLayout$SavedState$1 */
        static class C00821 implements ClassLoaderCreator<SavedState> {
            C00821() {
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

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.error = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            classLoader = true;
            if (parcel.readInt() != 1) {
                classLoader = null;
            }
            this.isPasswordToggledVisible = classLoader;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.error, parcel, i);
            parcel.writeInt(this.isPasswordToggledVisible);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("TextInputLayout.SavedState{");
            stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringBuilder.append(" error=");
            stringBuilder.append(this.error);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0026R.attr.textInputStyle);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.indicatorViewController = new IndicatorViewController(this);
        this.tmpRect = new Rect();
        this.tmpRectF = new RectF();
        this.collapsingTextHelper = new CollapsingTextHelper(this);
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        this.inputFrame = new FrameLayout(context);
        this.inputFrame.setAddStatesFromChildren(true);
        addView(this.inputFrame);
        this.collapsingTextHelper.setTextSizeInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        this.collapsingTextHelper.setPositionInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        this.collapsingTextHelper.setCollapsedTextGravity(8388659);
        attributeSet = ThemeEnforcement.obtainTintedStyledAttributes(context, attributeSet, C0026R.styleable.TextInputLayout, i, C0026R.style.Widget_Design_TextInputLayout, new int[0]);
        this.hintEnabled = attributeSet.getBoolean(C0026R.styleable.TextInputLayout_hintEnabled, true);
        setHint(attributeSet.getText(C0026R.styleable.TextInputLayout_android_hint));
        this.hintAnimationEnabled = attributeSet.getBoolean(C0026R.styleable.TextInputLayout_hintAnimationEnabled, true);
        this.boxBottomOffsetPx = context.getResources().getDimensionPixelOffset(C0026R.dimen.mtrl_textinput_box_bottom_offset);
        this.boxLabelCutoutPaddingPx = context.getResources().getDimensionPixelOffset(C0026R.dimen.mtrl_textinput_box_label_cutout_padding);
        this.boxCollapsedPaddingTopPx = attributeSet.getDimensionPixelOffset(C0026R.styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.boxCornerRadiusTopStart = attributeSet.getDimension(C0026R.styleable.TextInputLayout_boxCornerRadiusTopStart, 0.0f);
        this.boxCornerRadiusTopEnd = attributeSet.getDimension(C0026R.styleable.TextInputLayout_boxCornerRadiusTopEnd, 0.0f);
        this.boxCornerRadiusBottomEnd = attributeSet.getDimension(C0026R.styleable.TextInputLayout_boxCornerRadiusBottomEnd, 0.0f);
        this.boxCornerRadiusBottomStart = attributeSet.getDimension(C0026R.styleable.TextInputLayout_boxCornerRadiusBottomStart, 0.0f);
        this.boxBackgroundColor = attributeSet.getColor(C0026R.styleable.TextInputLayout_boxBackgroundColor, 0);
        this.focusedStrokeColor = attributeSet.getColor(C0026R.styleable.TextInputLayout_boxStrokeColor, 0);
        this.boxStrokeWidthDefaultPx = context.getResources().getDimensionPixelSize(C0026R.dimen.mtrl_textinput_box_stroke_width_default);
        this.boxStrokeWidthFocusedPx = context.getResources().getDimensionPixelSize(C0026R.dimen.mtrl_textinput_box_stroke_width_focused);
        this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
        setBoxBackgroundMode(attributeSet.getInt(C0026R.styleable.TextInputLayout_boxBackgroundMode, 0));
        if (attributeSet.hasValue(C0026R.styleable.TextInputLayout_android_textColorHint) != 0) {
            i = attributeSet.getColorStateList(C0026R.styleable.TextInputLayout_android_textColorHint);
            this.focusedTextColor = i;
            this.defaultHintTextColor = i;
        }
        this.defaultStrokeColor = ContextCompat.getColor(context, C0026R.color.mtrl_textinput_default_box_stroke_color);
        this.disabledColor = ContextCompat.getColor(context, C0026R.color.mtrl_textinput_disabled_color);
        this.hoveredStrokeColor = ContextCompat.getColor(context, C0026R.color.mtrl_textinput_hovered_box_stroke_color);
        if (attributeSet.getResourceId(C0026R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            setHintTextAppearance(attributeSet.getResourceId(C0026R.styleable.TextInputLayout_hintTextAppearance, 0));
        }
        context = attributeSet.getResourceId(C0026R.styleable.TextInputLayout_errorTextAppearance, 0);
        boolean z = attributeSet.getBoolean(C0026R.styleable.TextInputLayout_errorEnabled, false);
        int resourceId = attributeSet.getResourceId(C0026R.styleable.TextInputLayout_helperTextTextAppearance, 0);
        boolean z2 = attributeSet.getBoolean(C0026R.styleable.TextInputLayout_helperTextEnabled, false);
        CharSequence text = attributeSet.getText(C0026R.styleable.TextInputLayout_helperText);
        boolean z3 = attributeSet.getBoolean(C0026R.styleable.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(attributeSet.getInt(C0026R.styleable.TextInputLayout_counterMaxLength, -1));
        this.counterTextAppearance = attributeSet.getResourceId(C0026R.styleable.TextInputLayout_counterTextAppearance, 0);
        this.counterOverflowTextAppearance = attributeSet.getResourceId(C0026R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        this.passwordToggleEnabled = attributeSet.getBoolean(C0026R.styleable.TextInputLayout_passwordToggleEnabled, false);
        this.passwordToggleDrawable = attributeSet.getDrawable(C0026R.styleable.TextInputLayout_passwordToggleDrawable);
        this.passwordToggleContentDesc = attributeSet.getText(C0026R.styleable.TextInputLayout_passwordToggleContentDescription);
        if (attributeSet.hasValue(C0026R.styleable.TextInputLayout_passwordToggleTint)) {
            this.hasPasswordToggleTintList = true;
            this.passwordToggleTintList = attributeSet.getColorStateList(C0026R.styleable.TextInputLayout_passwordToggleTint);
        }
        if (attributeSet.hasValue(C0026R.styleable.TextInputLayout_passwordToggleTintMode)) {
            this.hasPasswordToggleTintMode = true;
            this.passwordToggleTintMode = ViewUtils.parseTintMode(attributeSet.getInt(C0026R.styleable.TextInputLayout_passwordToggleTintMode, -1), null);
        }
        attributeSet.recycle();
        setHelperTextEnabled(z2);
        setHelperText(text);
        setHelperTextTextAppearance(resourceId);
        setErrorEnabled(z);
        setErrorTextAppearance(context);
        setCounterEnabled(z3);
        applyPasswordToggleTint();
        ViewCompat.setImportantForAccessibility(this, 2);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (view instanceof EditText) {
            i = new FrameLayout.LayoutParams(layoutParams);
            i.gravity = (i.gravity & -113) | 16;
            this.inputFrame.addView(view, i);
            this.inputFrame.setLayoutParams(layoutParams);
            updateInputLayoutMargins();
            setEditText((EditText) view);
            return;
        }
        super.addView(view, i, layoutParams);
    }

    @NonNull
    private Drawable getBoxBackground() {
        int i = this.boxBackgroundMode;
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException();
            }
        }
        return this.boxBackground;
    }

    public void setBoxBackgroundMode(int i) {
        if (i != this.boxBackgroundMode) {
            this.boxBackgroundMode = i;
            onApplyBoxBackgroundMode();
        }
    }

    private void onApplyBoxBackgroundMode() {
        assignBoxBackgroundByMode();
        if (this.boxBackgroundMode != 0) {
            updateInputLayoutMargins();
        }
        updateTextInputBoxBounds();
    }

    private void assignBoxBackgroundByMode() {
        int i = this.boxBackgroundMode;
        if (i == 0) {
            this.boxBackground = null;
        } else if (i == 2 && this.hintEnabled && !(this.boxBackground instanceof CutoutDrawable)) {
            this.boxBackground = new CutoutDrawable();
        } else if (!(this.boxBackground instanceof GradientDrawable)) {
            this.boxBackground = new GradientDrawable();
        }
    }

    public void setBoxStrokeColor(@ColorInt int i) {
        if (this.focusedStrokeColor != i) {
            this.focusedStrokeColor = i;
            updateTextInputBoxState();
        }
    }

    public int getBoxStrokeColor() {
        return this.focusedStrokeColor;
    }

    public void setBoxBackgroundColorResource(@ColorRes int i) {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), i));
    }

    public void setBoxBackgroundColor(@ColorInt int i) {
        if (this.boxBackgroundColor != i) {
            this.boxBackgroundColor = i;
            applyBoxAttributes();
        }
    }

    public int getBoxBackgroundColor() {
        return this.boxBackgroundColor;
    }

    public void setBoxCornerRadiiResources(@DimenRes int i, @DimenRes int i2, @DimenRes int i3, @DimenRes int i4) {
        setBoxCornerRadii(getContext().getResources().getDimension(i), getContext().getResources().getDimension(i2), getContext().getResources().getDimension(i3), getContext().getResources().getDimension(i4));
    }

    public void setBoxCornerRadii(float f, float f2, float f3, float f4) {
        if (this.boxCornerRadiusTopStart != f || this.boxCornerRadiusTopEnd != f2 || this.boxCornerRadiusBottomEnd != f4 || this.boxCornerRadiusBottomStart != f3) {
            this.boxCornerRadiusTopStart = f;
            this.boxCornerRadiusTopEnd = f2;
            this.boxCornerRadiusBottomEnd = f4;
            this.boxCornerRadiusBottomStart = f3;
            applyBoxAttributes();
        }
    }

    public float getBoxCornerRadiusTopStart() {
        return this.boxCornerRadiusTopStart;
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.boxCornerRadiusTopEnd;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.boxCornerRadiusBottomEnd;
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.boxCornerRadiusBottomStart;
    }

    private float[] getCornerRadiiAsArray() {
        if (ViewUtils.isLayoutRtl(this)) {
            r0 = new float[8];
            float f = this.boxCornerRadiusTopEnd;
            r0[0] = f;
            r0[1] = f;
            float f2 = this.boxCornerRadiusTopStart;
            r0[2] = f2;
            r0[3] = f2;
            float f3 = this.boxCornerRadiusBottomStart;
            r0[4] = f3;
            r0[5] = f3;
            float f4 = this.boxCornerRadiusBottomEnd;
            r0[6] = f4;
            r0[7] = f4;
            return r0;
        }
        r0 = new float[8];
        f = this.boxCornerRadiusTopStart;
        r0[0] = f;
        r0[1] = f;
        f2 = this.boxCornerRadiusTopEnd;
        r0[2] = f2;
        r0[3] = f2;
        f3 = this.boxCornerRadiusBottomEnd;
        r0[4] = f3;
        r0[5] = f3;
        f4 = this.boxCornerRadiusBottomStart;
        r0[6] = f4;
        r0[7] = f4;
        return r0;
    }

    public void setTypeface(@Nullable Typeface typeface) {
        if (typeface != this.typeface) {
            this.typeface = typeface;
            this.collapsingTextHelper.setTypefaces(typeface);
            this.indicatorViewController.setTypefaces(typeface);
            TextView textView = this.counterView;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    @Nullable
    public Typeface getTypeface() {
        return this.typeface;
    }

    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        if (this.originalHint != null) {
            EditText editText = this.editText;
            if (editText != null) {
                boolean z = this.isProvidingHint;
                this.isProvidingHint = false;
                CharSequence hint = editText.getHint();
                this.editText.setHint(this.originalHint);
                try {
                    super.dispatchProvideAutofillStructure(viewStructure, i);
                    return;
                } finally {
                    this.editText.setHint(hint);
                    this.isProvidingHint = z;
                }
            }
        }
        super.dispatchProvideAutofillStructure(viewStructure, i);
    }

    private void setEditText(EditText editText) {
        if (this.editText == null) {
            if (!(editText instanceof TextInputEditText)) {
                Log.i(LOG_TAG, "EditText added is not a TextInputEditText. Please switch to using that class instead.");
            }
            this.editText = editText;
            onApplyBoxBackgroundMode();
            setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
            if (hasPasswordTransformation() == null) {
                this.collapsingTextHelper.setTypefaces(this.editText.getTypeface());
            }
            this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
            editText = this.editText.getGravity();
            this.collapsingTextHelper.setCollapsedTextGravity((editText & -113) | 48);
            this.collapsingTextHelper.setExpandedTextGravity(editText);
            this.editText.addTextChangedListener(new C00791());
            if (this.defaultHintTextColor == null) {
                this.defaultHintTextColor = this.editText.getHintTextColors();
            }
            if (this.hintEnabled != null) {
                if (TextUtils.isEmpty(this.hint) != null) {
                    this.originalHint = this.editText.getHint();
                    setHint(this.originalHint);
                    this.editText.setHint(null);
                }
                this.isProvidingHint = true;
            }
            if (this.counterView != null) {
                updateCounter(this.editText.getText().length());
            }
            this.indicatorViewController.adjustIndicatorPadding();
            updatePasswordToggleView();
            updateLabelState(null, true);
            return;
        }
        throw new IllegalArgumentException("We already have an EditText, can only have one");
    }

    private void updateInputLayoutMargins() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
        int calculateLabelMarginTop = calculateLabelMarginTop();
        if (calculateLabelMarginTop != layoutParams.topMargin) {
            layoutParams.topMargin = calculateLabelMarginTop;
            this.inputFrame.requestLayout();
        }
    }

    void updateLabelState(boolean z) {
        updateLabelState(z, false);
    }

    private void updateLabelState(boolean z, boolean z2) {
        boolean isEnabled = isEnabled();
        EditText editText = this.editText;
        Object obj = 1;
        Object obj2 = (editText == null || TextUtils.isEmpty(editText.getText())) ? null : 1;
        EditText editText2 = this.editText;
        if (editText2 == null || !editText2.hasFocus()) {
            obj = null;
        }
        boolean errorShouldBeShown = this.indicatorViewController.errorShouldBeShown();
        ColorStateList colorStateList = this.defaultHintTextColor;
        if (colorStateList != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
            this.collapsingTextHelper.setExpandedTextColor(this.defaultHintTextColor);
        }
        if (!isEnabled) {
            this.collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(this.disabledColor));
            this.collapsingTextHelper.setExpandedTextColor(ColorStateList.valueOf(this.disabledColor));
        } else if (errorShouldBeShown) {
            this.collapsingTextHelper.setCollapsedTextColor(this.indicatorViewController.getErrorViewTextColors());
        } else {
            if (this.counterOverflowed) {
                TextView textView = this.counterView;
                if (textView != null) {
                    this.collapsingTextHelper.setCollapsedTextColor(textView.getTextColors());
                }
            }
            if (obj != null) {
                ColorStateList colorStateList2 = this.focusedTextColor;
                if (colorStateList2 != null) {
                    this.collapsingTextHelper.setCollapsedTextColor(colorStateList2);
                }
            }
        }
        if (obj2 == null) {
            if (isEnabled()) {
                if (obj == null) {
                    if (errorShouldBeShown) {
                    }
                }
            }
            if (z2 || !this.hintExpanded) {
                expandHint(z);
                return;
            }
            return;
        }
        if (z2 || this.hintExpanded) {
            collapseHint(z);
        }
    }

    @Nullable
    public EditText getEditText() {
        return this.editText;
    }

    public void setHint(@Nullable CharSequence charSequence) {
        if (this.hintEnabled) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    private void setHintInternal(CharSequence charSequence) {
        if (!TextUtils.equals(charSequence, this.hint)) {
            this.hint = charSequence;
            this.collapsingTextHelper.setText(charSequence);
            if (this.hintExpanded == null) {
                openCutout();
            }
        }
    }

    @Nullable
    public CharSequence getHint() {
        return this.hintEnabled ? this.hint : null;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.hintEnabled) {
            this.hintEnabled = z;
            if (this.hintEnabled) {
                z = this.editText.getHint();
                if (!TextUtils.isEmpty(z)) {
                    if (TextUtils.isEmpty(this.hint)) {
                        setHint(z);
                    }
                    this.editText.setHint(null);
                }
                this.isProvidingHint = true;
            } else {
                this.isProvidingHint = false;
                if (!TextUtils.isEmpty(this.hint) && TextUtils.isEmpty(this.editText.getHint())) {
                    this.editText.setHint(this.hint);
                }
                setHintInternal(null);
            }
            if (this.editText) {
                updateInputLayoutMargins();
            }
        }
    }

    public boolean isHintEnabled() {
        return this.hintEnabled;
    }

    boolean isProvidingHint() {
        return this.isProvidingHint;
    }

    public void setHintTextAppearance(@StyleRes int i) {
        this.collapsingTextHelper.setCollapsedTextAppearance(i);
        this.focusedTextColor = this.collapsingTextHelper.getCollapsedTextColor();
        if (this.editText != 0) {
            updateLabelState(0);
            updateInputLayoutMargins();
        }
    }

    public void setDefaultHintTextColor(@Nullable ColorStateList colorStateList) {
        this.defaultHintTextColor = colorStateList;
        this.focusedTextColor = colorStateList;
        if (this.editText != null) {
            updateLabelState(null);
        }
    }

    @Nullable
    public ColorStateList getDefaultHintTextColor() {
        return this.defaultHintTextColor;
    }

    public void setErrorEnabled(boolean z) {
        this.indicatorViewController.setErrorEnabled(z);
    }

    public void setErrorTextAppearance(@StyleRes int i) {
        this.indicatorViewController.setErrorTextAppearance(i);
    }

    public void setErrorTextColor(@Nullable ColorStateList colorStateList) {
        this.indicatorViewController.setErrorViewTextColor(colorStateList);
    }

    @ColorInt
    public int getErrorCurrentTextColors() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public void setHelperTextTextAppearance(@StyleRes int i) {
        this.indicatorViewController.setHelperTextAppearance(i);
    }

    public boolean isErrorEnabled() {
        return this.indicatorViewController.isErrorEnabled();
    }

    public void setHelperTextEnabled(boolean z) {
        this.indicatorViewController.setHelperTextEnabled(z);
    }

    public void setHelperText(@Nullable CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (!isHelperTextEnabled()) {
                setHelperTextEnabled(true);
            }
            this.indicatorViewController.showHelper(charSequence);
        } else if (isHelperTextEnabled() != null) {
            setHelperTextEnabled(null);
        }
    }

    public boolean isHelperTextEnabled() {
        return this.indicatorViewController.isHelperTextEnabled();
    }

    public void setHelperTextColor(@Nullable ColorStateList colorStateList) {
        this.indicatorViewController.setHelperTextViewTextColor(colorStateList);
    }

    @ColorInt
    public int getHelperTextCurrentTextColor() {
        return this.indicatorViewController.getHelperTextViewCurrentTextColor();
    }

    public void setError(@Nullable CharSequence charSequence) {
        if (!this.indicatorViewController.isErrorEnabled()) {
            if (!TextUtils.isEmpty(charSequence)) {
                setErrorEnabled(true);
            } else {
                return;
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.indicatorViewController.hideError();
        } else {
            this.indicatorViewController.showError(charSequence);
        }
    }

    public void setCounterEnabled(boolean z) {
        if (this.counterEnabled != z) {
            if (z) {
                this.counterView = new AppCompatTextView(getContext());
                this.counterView.setId(C0026R.id.textinput_counter);
                Typeface typeface = this.typeface;
                if (typeface != null) {
                    this.counterView.setTypeface(typeface);
                }
                this.counterView.setMaxLines(1);
                setTextAppearanceCompatWithErrorFallback(this.counterView, this.counterTextAppearance);
                this.indicatorViewController.addIndicator(this.counterView, 2);
                EditText editText = this.editText;
                if (editText == null) {
                    updateCounter(0);
                } else {
                    updateCounter(editText.getText().length());
                }
            } else {
                this.indicatorViewController.removeIndicator(this.counterView, 2);
                this.counterView = null;
            }
            this.counterEnabled = z;
        }
    }

    public boolean isCounterEnabled() {
        return this.counterEnabled;
    }

    public void setCounterMaxLength(int i) {
        if (this.counterMaxLength != i) {
            if (i > 0) {
                this.counterMaxLength = i;
            } else {
                this.counterMaxLength = -1;
            }
            if (this.counterEnabled != 0) {
                i = this.editText;
                updateCounter(i == 0 ? 0 : i.getText().length());
            }
        }
    }

    public void setEnabled(boolean z) {
        recursiveSetEnabled(this, z);
        super.setEnabled(z);
    }

    private static void recursiveSetEnabled(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                recursiveSetEnabled((ViewGroup) childAt, z);
            }
        }
    }

    public int getCounterMaxLength() {
        return this.counterMaxLength;
    }

    @Nullable
    CharSequence getCounterOverflowDescription() {
        if (this.counterEnabled && this.counterOverflowed) {
            TextView textView = this.counterView;
            if (textView != null) {
                return textView.getContentDescription();
            }
        }
        return null;
    }

    void updateCounter(int i) {
        boolean z = this.counterOverflowed;
        if (this.counterMaxLength == -1) {
            this.counterView.setText(String.valueOf(i));
            this.counterView.setContentDescription(null);
            this.counterOverflowed = false;
        } else {
            if (ViewCompat.getAccessibilityLiveRegion(this.counterView) == 1) {
                ViewCompat.setAccessibilityLiveRegion(this.counterView, 0);
            }
            this.counterOverflowed = i > this.counterMaxLength;
            boolean z2 = this.counterOverflowed;
            if (z != z2) {
                setTextAppearanceCompatWithErrorFallback(this.counterView, z2 ? this.counterOverflowTextAppearance : this.counterTextAppearance);
                if (this.counterOverflowed) {
                    ViewCompat.setAccessibilityLiveRegion(this.counterView, 1);
                }
            }
            this.counterView.setText(getContext().getString(C0026R.string.character_counter_pattern, new Object[]{Integer.valueOf(i), Integer.valueOf(this.counterMaxLength)}));
            this.counterView.setContentDescription(getContext().getString(C0026R.string.character_counter_content_description, new Object[]{Integer.valueOf(i), Integer.valueOf(this.counterMaxLength)}));
        }
        if (this.editText != 0 && z != this.counterOverflowed) {
            updateLabelState(false);
            updateTextInputBoxState();
            updateEditTextBackground();
        }
    }

    void setTextAppearanceCompatWithErrorFallback(android.widget.TextView r3, @android.support.annotation.StyleRes int r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        r2 = this;
        r0 = 1;
        android.support.v4.widget.TextViewCompat.setTextAppearance(r3, r4);	 Catch:{ Exception -> 0x001a }
        r4 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x001a }
        r1 = 23;	 Catch:{ Exception -> 0x001a }
        if (r4 < r1) goto L_0x0018;	 Catch:{ Exception -> 0x001a }
    L_0x000a:
        r4 = r3.getTextColors();	 Catch:{ Exception -> 0x001a }
        r4 = r4.getDefaultColor();	 Catch:{ Exception -> 0x001a }
        r1 = -65281; // 0xffffffffffff00ff float:NaN double:NaN;
        if (r4 != r1) goto L_0x0018;
    L_0x0017:
        goto L_0x001a;
    L_0x0018:
        r4 = 0;
        r0 = 0;
    L_0x001a:
        if (r0 == 0) goto L_0x002e;
    L_0x001c:
        r4 = android.support.design.C0026R.style.TextAppearance_AppCompat_Caption;
        android.support.v4.widget.TextViewCompat.setTextAppearance(r3, r4);
        r4 = r2.getContext();
        r0 = android.support.design.C0026R.color.design_error;
        r4 = android.support.v4.content.ContextCompat.getColor(r4, r0);
        r3.setTextColor(r4);
    L_0x002e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.TextInputLayout.setTextAppearanceCompatWithErrorFallback(android.widget.TextView, int):void");
    }

    private void updateTextInputBoxBounds() {
        if (!(this.boxBackgroundMode == 0 || this.boxBackground == null || this.editText == null)) {
            if (getRight() != 0) {
                int left = this.editText.getLeft();
                int calculateBoxBackgroundTop = calculateBoxBackgroundTop();
                int right = this.editText.getRight();
                int bottom = this.editText.getBottom() + this.boxBottomOffsetPx;
                if (this.boxBackgroundMode == 2) {
                    int i = this.boxStrokeWidthFocusedPx;
                    left += i / 2;
                    calculateBoxBackgroundTop -= i / 2;
                    right -= i / 2;
                    bottom += i / 2;
                }
                this.boxBackground.setBounds(left, calculateBoxBackgroundTop, right, bottom);
                applyBoxAttributes();
                updateEditTextBackgroundBounds();
            }
        }
    }

    private int calculateBoxBackgroundTop() {
        EditText editText = this.editText;
        if (editText == null) {
            return 0;
        }
        switch (this.boxBackgroundMode) {
            case 1:
                return editText.getTop();
            case 2:
                return editText.getTop() + calculateLabelMarginTop();
            default:
                return 0;
        }
    }

    private int calculateLabelMarginTop() {
        if (!this.hintEnabled) {
            return 0;
        }
        switch (this.boxBackgroundMode) {
            case 0:
            case 1:
                return (int) this.collapsingTextHelper.getCollapsedTextHeight();
            case 2:
                return (int) (this.collapsingTextHelper.getCollapsedTextHeight() / 2.0f);
            default:
                return 0;
        }
    }

    private int calculateCollapsedTextTopBounds() {
        switch (this.boxBackgroundMode) {
            case 1:
                return getBoxBackground().getBounds().top + this.boxCollapsedPaddingTopPx;
            case 2:
                return getBoxBackground().getBounds().top - calculateLabelMarginTop();
            default:
                return getPaddingTop();
        }
    }

    private void updateEditTextBackgroundBounds() {
        EditText editText = this.editText;
        if (editText != null) {
            Drawable background = editText.getBackground();
            if (background != null) {
                if (DrawableUtils.canSafelyMutateDrawable(background)) {
                    background = background.mutate();
                }
                DescendantOffsetUtils.getDescendantRect(this, this.editText, new Rect());
                Rect bounds = background.getBounds();
                if (bounds.left != bounds.right) {
                    Rect rect = new Rect();
                    background.getPadding(rect);
                    background.setBounds(bounds.left - rect.left, bounds.top, bounds.right + (rect.right * 2), this.editText.getBottom());
                }
            }
        }
    }

    private void setBoxAttributes() {
        switch (this.boxBackgroundMode) {
            case 1:
                this.boxStrokeWidthPx = 0;
                return;
            case 2:
                if (this.focusedStrokeColor == 0) {
                    this.focusedStrokeColor = this.focusedTextColor.getColorForState(getDrawableState(), this.focusedTextColor.getDefaultColor());
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void applyBoxAttributes() {
        if (this.boxBackground != null) {
            setBoxAttributes();
            EditText editText = this.editText;
            if (editText != null && this.boxBackgroundMode == 2) {
                if (editText.getBackground() != null) {
                    this.editTextOriginalDrawable = this.editText.getBackground();
                }
                ViewCompat.setBackground(this.editText, null);
            }
            View view = this.editText;
            if (view != null && this.boxBackgroundMode == 1) {
                Drawable drawable = this.editTextOriginalDrawable;
                if (drawable != null) {
                    ViewCompat.setBackground(view, drawable);
                }
            }
            int i = this.boxStrokeWidthPx;
            if (i > -1) {
                int i2 = this.boxStrokeColor;
                if (i2 != 0) {
                    this.boxBackground.setStroke(i, i2);
                }
            }
            this.boxBackground.setCornerRadii(getCornerRadiiAsArray());
            this.boxBackground.setColor(this.boxBackgroundColor);
            invalidate();
        }
    }

    void updateEditTextBackground() {
        EditText editText = this.editText;
        if (editText != null) {
            Drawable background = editText.getBackground();
            if (background != null) {
                ensureBackgroundDrawableStateWorkaround();
                if (DrawableUtils.canSafelyMutateDrawable(background)) {
                    background = background.mutate();
                }
                if (this.indicatorViewController.errorShouldBeShown()) {
                    background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.indicatorViewController.getErrorViewCurrentTextColor(), Mode.SRC_IN));
                } else {
                    if (this.counterOverflowed) {
                        TextView textView = this.counterView;
                        if (textView != null) {
                            background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(textView.getCurrentTextColor(), Mode.SRC_IN));
                        }
                    }
                    DrawableCompat.clearColorFilter(background);
                    this.editText.refreshDrawableState();
                }
            }
        }
    }

    private void ensureBackgroundDrawableStateWorkaround() {
        int i = VERSION.SDK_INT;
        if (i == 21 || i == 22) {
            Drawable background = this.editText.getBackground();
            if (!(background == null || this.hasReconstructedEditTextBackground)) {
                Drawable newDrawable = background.getConstantState().newDrawable();
                if (background instanceof DrawableContainer) {
                    this.hasReconstructedEditTextBackground = DrawableUtils.setContainerConstantState((DrawableContainer) background, newDrawable.getConstantState());
                }
                if (!this.hasReconstructedEditTextBackground) {
                    ViewCompat.setBackground(this.editText, newDrawable);
                    this.hasReconstructedEditTextBackground = true;
                    onApplyBoxBackgroundMode();
                }
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        if (this.indicatorViewController.errorShouldBeShown()) {
            savedState.error = getError();
        }
        savedState.isPasswordToggledVisible = this.passwordToggledVisible;
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            setError(savedState.error);
            if (savedState.isPasswordToggledVisible != null) {
                passwordVisibilityToggleRequested(true);
            }
            requestLayout();
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.restoringSavedState = null;
    }

    @Nullable
    public CharSequence getError() {
        return this.indicatorViewController.isErrorEnabled() ? this.indicatorViewController.getErrorText() : null;
    }

    @Nullable
    public CharSequence getHelperText() {
        return this.indicatorViewController.isHelperTextEnabled() ? this.indicatorViewController.getHelperText() : null;
    }

    public boolean isHintAnimationEnabled() {
        return this.hintAnimationEnabled;
    }

    public void setHintAnimationEnabled(boolean z) {
        this.hintAnimationEnabled = z;
    }

    public void draw(Canvas canvas) {
        GradientDrawable gradientDrawable = this.boxBackground;
        if (gradientDrawable != null) {
            gradientDrawable.draw(canvas);
        }
        super.draw(canvas);
        if (this.hintEnabled) {
            this.collapsingTextHelper.draw(canvas);
        }
    }

    protected void onMeasure(int i, int i2) {
        updatePasswordToggleView();
        super.onMeasure(i, i2);
    }

    private void updatePasswordToggleView() {
        if (this.editText != null) {
            Drawable[] compoundDrawablesRelative;
            if (shouldShowPasswordIcon()) {
                if (this.passwordToggleView == null) {
                    this.passwordToggleView = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(C0026R.layout.design_text_input_password_icon, this.inputFrame, false);
                    this.passwordToggleView.setImageDrawable(this.passwordToggleDrawable);
                    this.passwordToggleView.setContentDescription(this.passwordToggleContentDesc);
                    this.inputFrame.addView(this.passwordToggleView);
                    this.passwordToggleView.setOnClickListener(new C00802());
                }
                View view = this.editText;
                if (view != null && ViewCompat.getMinimumHeight(view) <= 0) {
                    this.editText.setMinimumHeight(ViewCompat.getMinimumHeight(this.passwordToggleView));
                }
                this.passwordToggleView.setVisibility(0);
                this.passwordToggleView.setChecked(this.passwordToggledVisible);
                if (this.passwordToggleDummyDrawable == null) {
                    this.passwordToggleDummyDrawable = new ColorDrawable();
                }
                this.passwordToggleDummyDrawable.setBounds(0, 0, this.passwordToggleView.getMeasuredWidth(), 1);
                compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                if (compoundDrawablesRelative[2] != this.passwordToggleDummyDrawable) {
                    this.originalEditTextEndDrawable = compoundDrawablesRelative[2];
                }
                TextViewCompat.setCompoundDrawablesRelative(this.editText, compoundDrawablesRelative[0], compoundDrawablesRelative[1], this.passwordToggleDummyDrawable, compoundDrawablesRelative[3]);
                this.passwordToggleView.setPadding(this.editText.getPaddingLeft(), this.editText.getPaddingTop(), this.editText.getPaddingRight(), this.editText.getPaddingBottom());
            } else {
                CheckableImageButton checkableImageButton = this.passwordToggleView;
                if (checkableImageButton != null && checkableImageButton.getVisibility() == 0) {
                    this.passwordToggleView.setVisibility(8);
                }
                if (this.passwordToggleDummyDrawable != null) {
                    compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                    if (compoundDrawablesRelative[2] == this.passwordToggleDummyDrawable) {
                        TextViewCompat.setCompoundDrawablesRelative(this.editText, compoundDrawablesRelative[0], compoundDrawablesRelative[1], this.originalEditTextEndDrawable, compoundDrawablesRelative[3]);
                        this.passwordToggleDummyDrawable = null;
                    }
                }
            }
        }
    }

    public void setPasswordVisibilityToggleDrawable(@DrawableRes int i) {
        setPasswordVisibilityToggleDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : 0);
    }

    public void setPasswordVisibilityToggleDrawable(@Nullable Drawable drawable) {
        this.passwordToggleDrawable = drawable;
        CheckableImageButton checkableImageButton = this.passwordToggleView;
        if (checkableImageButton != null) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public void setPasswordVisibilityToggleContentDescription(@StringRes int i) {
        setPasswordVisibilityToggleContentDescription(i != 0 ? getResources().getText(i) : 0);
    }

    public void setPasswordVisibilityToggleContentDescription(@Nullable CharSequence charSequence) {
        this.passwordToggleContentDesc = charSequence;
        CheckableImageButton checkableImageButton = this.passwordToggleView;
        if (checkableImageButton != null) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    @Nullable
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.passwordToggleDrawable;
    }

    @Nullable
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.passwordToggleContentDesc;
    }

    public boolean isPasswordVisibilityToggleEnabled() {
        return this.passwordToggleEnabled;
    }

    public void setPasswordVisibilityToggleEnabled(boolean z) {
        if (this.passwordToggleEnabled != z) {
            this.passwordToggleEnabled = z;
            if (!z && this.passwordToggledVisible) {
                z = this.editText;
                if (z) {
                    z.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
            this.passwordToggledVisible = false;
            updatePasswordToggleView();
        }
    }

    public void setPasswordVisibilityToggleTintList(@Nullable ColorStateList colorStateList) {
        this.passwordToggleTintList = colorStateList;
        this.hasPasswordToggleTintList = true;
        applyPasswordToggleTint();
    }

    public void setPasswordVisibilityToggleTintMode(@Nullable Mode mode) {
        this.passwordToggleTintMode = mode;
        this.hasPasswordToggleTintMode = true;
        applyPasswordToggleTint();
    }

    public void passwordVisibilityToggleRequested(boolean z) {
        if (this.passwordToggleEnabled) {
            int selectionEnd = this.editText.getSelectionEnd();
            if (hasPasswordTransformation()) {
                this.editText.setTransformationMethod(null);
                this.passwordToggledVisible = true;
            } else {
                this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                this.passwordToggledVisible = false;
            }
            this.passwordToggleView.setChecked(this.passwordToggledVisible);
            if (z) {
                this.passwordToggleView.jumpDrawablesToCurrentState();
            }
            this.editText.setSelection(selectionEnd);
        }
    }

    public void setTextInputAccessibilityDelegate(AccessibilityDelegate accessibilityDelegate) {
        View view = this.editText;
        if (view != null) {
            ViewCompat.setAccessibilityDelegate(view, accessibilityDelegate);
        }
    }

    private boolean hasPasswordTransformation() {
        EditText editText = this.editText;
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    private boolean shouldShowPasswordIcon() {
        return this.passwordToggleEnabled && (hasPasswordTransformation() || this.passwordToggledVisible);
    }

    private void applyPasswordToggleTint() {
        if (this.passwordToggleDrawable == null) {
            return;
        }
        if (this.hasPasswordToggleTintList || this.hasPasswordToggleTintMode) {
            this.passwordToggleDrawable = DrawableCompat.wrap(this.passwordToggleDrawable).mutate();
            if (this.hasPasswordToggleTintList) {
                DrawableCompat.setTintList(this.passwordToggleDrawable, this.passwordToggleTintList);
            }
            if (this.hasPasswordToggleTintMode) {
                DrawableCompat.setTintMode(this.passwordToggleDrawable, this.passwordToggleTintMode);
            }
            CheckableImageButton checkableImageButton = this.passwordToggleView;
            if (checkableImageButton != null) {
                Drawable drawable = checkableImageButton.getDrawable();
                Drawable drawable2 = this.passwordToggleDrawable;
                if (drawable != drawable2) {
                    this.passwordToggleView.setImageDrawable(drawable2);
                }
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.boxBackground) {
            updateTextInputBoxBounds();
        }
        if (this.hintEnabled) {
            z = this.editText;
            if (z) {
                i = this.tmpRect;
                DescendantOffsetUtils.getDescendantRect(this, z, i);
                z = i.left + this.editText.getCompoundPaddingLeft();
                i3 = i.right - this.editText.getCompoundPaddingRight();
                int calculateCollapsedTextTopBounds = calculateCollapsedTextTopBounds();
                this.collapsingTextHelper.setExpandedBounds(z, i.top + this.editText.getCompoundPaddingTop(), i3, i.bottom - this.editText.getCompoundPaddingBottom());
                this.collapsingTextHelper.setCollapsedBounds(z, calculateCollapsedTextTopBounds, i3, (i4 - i2) - getPaddingBottom());
                this.collapsingTextHelper.recalculate();
                if (cutoutEnabled() && !this.hintExpanded) {
                    openCutout();
                }
            }
        }
    }

    private void collapseHint(boolean z) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (z && this.hintAnimationEnabled) {
            animateToExpansionFraction(1.0f);
        } else {
            this.collapsingTextHelper.setExpansionFraction(1.0f);
        }
        this.hintExpanded = false;
        if (cutoutEnabled()) {
            openCutout();
        }
    }

    private boolean cutoutEnabled() {
        return this.hintEnabled && !TextUtils.isEmpty(this.hint) && (this.boxBackground instanceof CutoutDrawable);
    }

    private void openCutout() {
        if (cutoutEnabled()) {
            RectF rectF = this.tmpRectF;
            this.collapsingTextHelper.getCollapsedTextActualBounds(rectF);
            applyCutoutPadding(rectF);
            ((CutoutDrawable) this.boxBackground).setCutout(rectF);
        }
    }

    private void closeCutout() {
        if (cutoutEnabled()) {
            ((CutoutDrawable) this.boxBackground).removeCutout();
        }
    }

    private void applyCutoutPadding(RectF rectF) {
        rectF.left -= (float) this.boxLabelCutoutPaddingPx;
        rectF.top -= (float) this.boxLabelCutoutPaddingPx;
        rectF.right += (float) this.boxLabelCutoutPaddingPx;
        rectF.bottom += (float) this.boxLabelCutoutPaddingPx;
    }

    @VisibleForTesting
    boolean cutoutIsOpen() {
        return cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout();
    }

    protected void drawableStateChanged() {
        if (!this.inDrawableStateChanged) {
            boolean z = true;
            this.inDrawableStateChanged = true;
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            if (!ViewCompat.isLaidOut(this) || !isEnabled()) {
                z = false;
            }
            updateLabelState(z);
            updateEditTextBackground();
            updateTextInputBoxBounds();
            updateTextInputBoxState();
            CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
            if ((collapsingTextHelper != null ? collapsingTextHelper.setState(drawableState) | 0 : 0) != 0) {
                invalidate();
            }
            this.inDrawableStateChanged = false;
        }
    }

    void updateTextInputBoxState() {
        if (this.boxBackground != null) {
            if (this.boxBackgroundMode != 0) {
                EditText editText = this.editText;
                Object obj = 1;
                Object obj2 = (editText == null || !editText.hasFocus()) ? null : 1;
                EditText editText2 = this.editText;
                if (editText2 == null || !editText2.isHovered()) {
                    obj = null;
                }
                if (this.boxBackgroundMode == 2) {
                    if (!isEnabled()) {
                        this.boxStrokeColor = this.disabledColor;
                    } else if (this.indicatorViewController.errorShouldBeShown()) {
                        this.boxStrokeColor = this.indicatorViewController.getErrorViewCurrentTextColor();
                    } else {
                        if (this.counterOverflowed) {
                            TextView textView = this.counterView;
                            if (textView != null) {
                                this.boxStrokeColor = textView.getCurrentTextColor();
                            }
                        }
                        if (obj2 != null) {
                            this.boxStrokeColor = this.focusedStrokeColor;
                        } else if (obj != null) {
                            this.boxStrokeColor = this.hoveredStrokeColor;
                        } else {
                            this.boxStrokeColor = this.defaultStrokeColor;
                        }
                    }
                    if (!(obj == null && obj2 == null) && isEnabled()) {
                        this.boxStrokeWidthPx = this.boxStrokeWidthFocusedPx;
                    } else {
                        this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
                    }
                    applyBoxAttributes();
                }
            }
        }
    }

    private void expandHint(boolean z) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (z && this.hintAnimationEnabled) {
            animateToExpansionFraction(0.0f);
        } else {
            this.collapsingTextHelper.setExpansionFraction(0.0f);
        }
        if (cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout()) {
            closeCutout();
        }
        this.hintExpanded = true;
    }

    @VisibleForTesting
    void animateToExpansionFraction(float f) {
        if (this.collapsingTextHelper.getExpansionFraction() != f) {
            if (this.animator == null) {
                this.animator = new ValueAnimator();
                this.animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                this.animator.setDuration(167);
                this.animator.addUpdateListener(new C00813());
            }
            this.animator.setFloatValues(new float[]{this.collapsingTextHelper.getExpansionFraction(), f});
            this.animator.start();
        }
    }

    @VisibleForTesting
    final boolean isHintExpanded() {
        return this.hintExpanded;
    }

    @VisibleForTesting
    final boolean isHelperTextDisplayed() {
        return this.indicatorViewController.helperTextIsDisplayed();
    }

    @VisibleForTesting
    final int getHintCurrentCollapsedTextColor() {
        return this.collapsingTextHelper.getCurrentCollapsedTextColor();
    }

    @VisibleForTesting
    final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.getCollapsedTextHeight();
    }

    @VisibleForTesting
    final int getErrorTextCurrentColor() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }
}
