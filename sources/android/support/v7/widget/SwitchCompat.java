package android.support.v7.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.appcompat.C0291R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.text.AllCapsTransformationMethod;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode.Callback;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;

public class SwitchCompat extends CompoundButton {
    private static final String ACCESSIBILITY_EVENT_CLASS_NAME = "android.widget.Switch";
    private static final int[] CHECKED_STATE_SET = new int[]{16842912};
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int THUMB_ANIMATION_DURATION = 250;
    private static final Property<SwitchCompat, Float> THUMB_POS = new Property<SwitchCompat, Float>(Float.class, "thumbPos") {
        public Float get(SwitchCompat switchCompat) {
            return Float.valueOf(switchCompat.mThumbPosition);
        }

        public void set(SwitchCompat switchCompat, Float f) {
            switchCompat.setThumbPosition(f.floatValue());
        }
    };
    private static final int TOUCH_MODE_DOWN = 1;
    private static final int TOUCH_MODE_DRAGGING = 2;
    private static final int TOUCH_MODE_IDLE = 0;
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mHasTrackTint;
    private boolean mHasTrackTintMode;
    private int mMinFlingVelocity;
    private Layout mOffLayout;
    private Layout mOnLayout;
    ObjectAnimator mPositionAnimator;
    private boolean mShowText;
    private boolean mSplitTrack;
    private int mSwitchBottom;
    private int mSwitchHeight;
    private int mSwitchLeft;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private int mSwitchRight;
    private int mSwitchTop;
    private TransformationMethod mSwitchTransformationMethod;
    private int mSwitchWidth;
    private final Rect mTempRect;
    private ColorStateList mTextColors;
    private CharSequence mTextOff;
    private CharSequence mTextOn;
    private final TextPaint mTextPaint;
    private Drawable mThumbDrawable;
    float mThumbPosition;
    private int mThumbTextPadding;
    private ColorStateList mThumbTintList;
    private Mode mThumbTintMode;
    private int mThumbWidth;
    private int mTouchMode;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private Drawable mTrackDrawable;
    private ColorStateList mTrackTintList;
    private Mode mTrackTintMode;
    private VelocityTracker mVelocityTracker;

    private static float constrain(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    public SwitchCompat(Context context) {
        this(context, null);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0291R.attr.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mThumbTintList = null;
        this.mThumbTintMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbTintMode = false;
        this.mTrackTintList = null;
        this.mTrackTintMode = null;
        this.mHasTrackTint = false;
        this.mHasTrackTintMode = false;
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mTempRect = new Rect();
        this.mTextPaint = new TextPaint(1);
        Resources resources = getResources();
        this.mTextPaint.density = resources.getDisplayMetrics().density;
        attributeSet = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0291R.styleable.SwitchCompat, i, 0);
        this.mThumbDrawable = attributeSet.getDrawable(C0291R.styleable.SwitchCompat_android_thumb);
        i = this.mThumbDrawable;
        if (i != 0) {
            i.setCallback(this);
        }
        this.mTrackDrawable = attributeSet.getDrawable(C0291R.styleable.SwitchCompat_track);
        i = this.mTrackDrawable;
        if (i != 0) {
            i.setCallback(this);
        }
        this.mTextOn = attributeSet.getText(C0291R.styleable.SwitchCompat_android_textOn);
        this.mTextOff = attributeSet.getText(C0291R.styleable.SwitchCompat_android_textOff);
        this.mShowText = attributeSet.getBoolean(C0291R.styleable.SwitchCompat_showText, true);
        this.mThumbTextPadding = attributeSet.getDimensionPixelSize(C0291R.styleable.SwitchCompat_thumbTextPadding, 0);
        this.mSwitchMinWidth = attributeSet.getDimensionPixelSize(C0291R.styleable.SwitchCompat_switchMinWidth, 0);
        this.mSwitchPadding = attributeSet.getDimensionPixelSize(C0291R.styleable.SwitchCompat_switchPadding, 0);
        this.mSplitTrack = attributeSet.getBoolean(C0291R.styleable.SwitchCompat_splitTrack, false);
        i = attributeSet.getColorStateList(C0291R.styleable.SwitchCompat_thumbTint);
        if (i != 0) {
            this.mThumbTintList = i;
            this.mHasThumbTint = true;
        }
        i = DrawableUtils.parseTintMode(attributeSet.getInt(C0291R.styleable.SwitchCompat_thumbTintMode, -1), null);
        if (this.mThumbTintMode != i) {
            this.mThumbTintMode = i;
            this.mHasThumbTintMode = true;
        }
        if (!(this.mHasThumbTint == 0 && this.mHasThumbTintMode == 0)) {
            applyThumbTint();
        }
        i = attributeSet.getColorStateList(C0291R.styleable.SwitchCompat_trackTint);
        if (i != 0) {
            this.mTrackTintList = i;
            this.mHasTrackTint = true;
        }
        i = DrawableUtils.parseTintMode(attributeSet.getInt(C0291R.styleable.SwitchCompat_trackTintMode, -1), null);
        if (this.mTrackTintMode != i) {
            this.mTrackTintMode = i;
            this.mHasTrackTintMode = true;
        }
        if (!(this.mHasTrackTint == 0 && this.mHasTrackTintMode == 0)) {
            applyTrackTint();
        }
        i = attributeSet.getResourceId(C0291R.styleable.SwitchCompat_switchTextAppearance, 0);
        if (i != 0) {
            setSwitchTextAppearance(context, i);
        }
        attributeSet.recycle();
        context = ViewConfiguration.get(context);
        this.mTouchSlop = context.getScaledTouchSlop();
        this.mMinFlingVelocity = context.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    public void setSwitchTextAppearance(Context context, int i) {
        context = TintTypedArray.obtainStyledAttributes(context, i, C0291R.styleable.TextAppearance);
        i = context.getColorStateList(C0291R.styleable.TextAppearance_android_textColor);
        if (i != 0) {
            this.mTextColors = i;
        } else {
            this.mTextColors = getTextColors();
        }
        i = context.getDimensionPixelSize(C0291R.styleable.TextAppearance_android_textSize, 0);
        if (i != 0) {
            i = (float) i;
            if (i != this.mTextPaint.getTextSize()) {
                this.mTextPaint.setTextSize(i);
                requestLayout();
            }
        }
        setSwitchTypefaceByIndex(context.getInt(C0291R.styleable.TextAppearance_android_typeface, -1), context.getInt(C0291R.styleable.TextAppearance_android_textStyle, -1));
        if (context.getBoolean(C0291R.styleable.TextAppearance_textAllCaps, false) != 0) {
            this.mSwitchTransformationMethod = new AllCapsTransformationMethod(getContext());
        } else {
            this.mSwitchTransformationMethod = 0;
        }
        context.recycle();
    }

    private void setSwitchTypefaceByIndex(int i, int i2) {
        switch (i) {
            case 1:
                i = Typeface.SANS_SERIF;
                break;
            case 2:
                i = Typeface.SERIF;
                break;
            case 3:
                i = Typeface.MONOSPACE;
                break;
            default:
                i = 0;
                break;
        }
        setSwitchTypeface(i, i2);
    }

    public void setSwitchTypeface(Typeface typeface, int i) {
        float f = 0.0f;
        boolean z = false;
        if (i > 0) {
            if (typeface == null) {
                typeface = Typeface.defaultFromStyle(i);
            } else {
                typeface = Typeface.create(typeface, i);
            }
            setSwitchTypeface(typeface);
            typeface = ((typeface != null ? typeface.getStyle() : null) ^ -1) & i;
            i = this.mTextPaint;
            if ((typeface & 1) != 0) {
                z = true;
            }
            i.setFakeBoldText(z);
            i = this.mTextPaint;
            if ((typeface & 2) != null) {
                f = -0.25f;
            }
            i.setTextSkewX(f);
            return;
        }
        this.mTextPaint.setFakeBoldText(false);
        this.mTextPaint.setTextSkewX(0.0f);
        setSwitchTypeface(typeface);
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.mTextPaint.getTypeface() != null && !this.mTextPaint.getTypeface().equals(typeface)) || (this.mTextPaint.getTypeface() == null && typeface != null)) {
            this.mTextPaint.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void setSwitchPadding(int i) {
        this.mSwitchPadding = i;
        requestLayout();
    }

    public int getSwitchPadding() {
        return this.mSwitchPadding;
    }

    public void setSwitchMinWidth(int i) {
        this.mSwitchMinWidth = i;
        requestLayout();
    }

    public int getSwitchMinWidth() {
        return this.mSwitchMinWidth;
    }

    public void setThumbTextPadding(int i) {
        this.mThumbTextPadding = i;
        requestLayout();
    }

    public int getThumbTextPadding() {
        return this.mThumbTextPadding;
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.mTrackDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i) {
        setTrackDrawable(AppCompatResources.getDrawable(getContext(), i));
    }

    public Drawable getTrackDrawable() {
        return this.mTrackDrawable;
    }

    public void setTrackTintList(@Nullable ColorStateList colorStateList) {
        this.mTrackTintList = colorStateList;
        this.mHasTrackTint = true;
        applyTrackTint();
    }

    @Nullable
    public ColorStateList getTrackTintList() {
        return this.mTrackTintList;
    }

    public void setTrackTintMode(@Nullable Mode mode) {
        this.mTrackTintMode = mode;
        this.mHasTrackTintMode = true;
        applyTrackTint();
    }

    @Nullable
    public Mode getTrackTintMode() {
        return this.mTrackTintMode;
    }

    private void applyTrackTint() {
        if (this.mTrackDrawable == null) {
            return;
        }
        if (this.mHasTrackTint || this.mHasTrackTintMode) {
            this.mTrackDrawable = this.mTrackDrawable.mutate();
            if (this.mHasTrackTint) {
                DrawableCompat.setTintList(this.mTrackDrawable, this.mTrackTintList);
            }
            if (this.mHasTrackTintMode) {
                DrawableCompat.setTintMode(this.mTrackDrawable, this.mTrackTintMode);
            }
            if (this.mTrackDrawable.isStateful()) {
                this.mTrackDrawable.setState(getDrawableState());
            }
        }
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.mThumbDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.mThumbDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbResource(int i) {
        setThumbDrawable(AppCompatResources.getDrawable(getContext(), i));
    }

    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    public void setThumbTintList(@Nullable ColorStateList colorStateList) {
        this.mThumbTintList = colorStateList;
        this.mHasThumbTint = true;
        applyThumbTint();
    }

    @Nullable
    public ColorStateList getThumbTintList() {
        return this.mThumbTintList;
    }

    public void setThumbTintMode(@Nullable Mode mode) {
        this.mThumbTintMode = mode;
        this.mHasThumbTintMode = true;
        applyThumbTint();
    }

    @Nullable
    public Mode getThumbTintMode() {
        return this.mThumbTintMode;
    }

    private void applyThumbTint() {
        if (this.mThumbDrawable == null) {
            return;
        }
        if (this.mHasThumbTint || this.mHasThumbTintMode) {
            this.mThumbDrawable = this.mThumbDrawable.mutate();
            if (this.mHasThumbTint) {
                DrawableCompat.setTintList(this.mThumbDrawable, this.mThumbTintList);
            }
            if (this.mHasThumbTintMode) {
                DrawableCompat.setTintMode(this.mThumbDrawable, this.mThumbTintMode);
            }
            if (this.mThumbDrawable.isStateful()) {
                this.mThumbDrawable.setState(getDrawableState());
            }
        }
    }

    public void setSplitTrack(boolean z) {
        this.mSplitTrack = z;
        invalidate();
    }

    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }

    public CharSequence getTextOn() {
        return this.mTextOn;
    }

    public void setTextOn(CharSequence charSequence) {
        this.mTextOn = charSequence;
        requestLayout();
    }

    public CharSequence getTextOff() {
        return this.mTextOff;
    }

    public void setTextOff(CharSequence charSequence) {
        this.mTextOff = charSequence;
        requestLayout();
    }

    public void setShowText(boolean z) {
        if (this.mShowText != z) {
            this.mShowText = z;
            requestLayout();
        }
    }

    public boolean getShowText() {
        return this.mShowText;
    }

    public void onMeasure(int i, int i2) {
        int intrinsicWidth;
        int intrinsicHeight;
        if (this.mShowText) {
            if (this.mOnLayout == null) {
                this.mOnLayout = makeLayout(this.mTextOn);
            }
            if (this.mOffLayout == null) {
                this.mOffLayout = makeLayout(this.mTextOff);
            }
        }
        Rect rect = this.mTempRect;
        Drawable drawable = this.mThumbDrawable;
        int i3 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            intrinsicWidth = (this.mThumbDrawable.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.mThumbDrawable.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
            intrinsicHeight = 0;
        }
        this.mThumbWidth = Math.max(this.mShowText ? Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth()) + (this.mThumbTextPadding * 2) : 0, intrinsicWidth);
        drawable = this.mTrackDrawable;
        if (drawable != null) {
            drawable.getPadding(rect);
            i3 = this.mTrackDrawable.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        intrinsicWidth = rect.left;
        int i4 = rect.right;
        Drawable drawable2 = this.mThumbDrawable;
        if (drawable2 != null) {
            Rect opticalBounds = DrawableUtils.getOpticalBounds(drawable2);
            intrinsicWidth = Math.max(intrinsicWidth, opticalBounds.left);
            i4 = Math.max(i4, opticalBounds.right);
        }
        i4 = Math.max(this.mSwitchMinWidth, ((this.mThumbWidth * 2) + intrinsicWidth) + i4);
        intrinsicWidth = Math.max(i3, intrinsicHeight);
        this.mSwitchWidth = i4;
        this.mSwitchHeight = intrinsicWidth;
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < intrinsicWidth) {
            setMeasuredDimension(getMeasuredWidthAndState(), intrinsicWidth);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        Object obj = isChecked() ? this.mTextOn : this.mTextOff;
        if (obj != null) {
            accessibilityEvent.getText().add(obj);
        }
    }

    private Layout makeLayout(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.mSwitchTransformationMethod;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        CharSequence charSequence2 = charSequence;
        TextPaint textPaint = this.mTextPaint;
        return new StaticLayout(charSequence2, textPaint, charSequence2 != null ? (int) Math.ceil((double) Layout.getDesiredWidth(charSequence2, textPaint)) : 0, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    private boolean hitThumb(float f, float f2) {
        boolean z = false;
        if (this.mThumbDrawable == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.mThumbDrawable.getPadding(this.mTempRect);
        int i = this.mSwitchTop;
        int i2 = this.mTouchSlop;
        i -= i2;
        int i3 = (this.mSwitchLeft + thumbOffset) - i2;
        thumbOffset = ((this.mThumbWidth + i3) + this.mTempRect.left) + this.mTempRect.right;
        i2 = this.mTouchSlop;
        thumbOffset += i2;
        int i4 = this.mSwitchBottom + i2;
        if (f > ((float) i3) && f < ((float) thumbOffset) && f2 > ((float) i) && f2 < ((float) i4)) {
            z = true;
        }
        return z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mVelocityTracker.addMovement(motionEvent);
        float x;
        float y;
        switch (motionEvent.getActionMasked()) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                if (isEnabled() && hitThumb(x, y)) {
                    this.mTouchMode = 1;
                    this.mTouchX = x;
                    this.mTouchY = y;
                    break;
                }
            case 1:
            case 3:
                if (this.mTouchMode != 2) {
                    this.mTouchMode = 0;
                    this.mVelocityTracker.clear();
                    break;
                }
                stopDrag(motionEvent);
                super.onTouchEvent(motionEvent);
                return true;
            case 2:
                switch (this.mTouchMode) {
                    case 0:
                        break;
                    case 1:
                        x = motionEvent.getX();
                        float y2 = motionEvent.getY();
                        if (Math.abs(x - this.mTouchX) > ((float) this.mTouchSlop) || Math.abs(y2 - this.mTouchY) > ((float) this.mTouchSlop)) {
                            this.mTouchMode = 2;
                            getParent().requestDisallowInterceptTouchEvent(true);
                            this.mTouchX = x;
                            this.mTouchY = y2;
                            return true;
                        }
                    case 2:
                        motionEvent = motionEvent.getX();
                        int thumbScrollRange = getThumbScrollRange();
                        y = motionEvent - this.mTouchX;
                        y = thumbScrollRange != 0 ? y / ((float) thumbScrollRange) : y > 0.0f ? 1.0f : -1.0f;
                        if (ViewUtils.isLayoutRtl(this)) {
                            y = -y;
                        }
                        x = constrain(this.mThumbPosition + y, 0.0f, 1.0f);
                        if (x != this.mThumbPosition) {
                            this.mTouchX = motionEvent;
                            setThumbPosition(x);
                        }
                        return true;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void cancelSuperTouch(MotionEvent motionEvent) {
        motionEvent = MotionEvent.obtain(motionEvent);
        motionEvent.setAction(3);
        super.onTouchEvent(motionEvent);
        motionEvent.recycle();
    }

    private void stopDrag(MotionEvent motionEvent) {
        boolean z;
        this.mTouchMode = 0;
        boolean z2 = true;
        Object obj = (motionEvent.getAction() == 1 && isEnabled()) ? 1 : null;
        boolean isChecked = isChecked();
        if (obj != null) {
            this.mVelocityTracker.computeCurrentVelocity(1000);
            float xVelocity = this.mVelocityTracker.getXVelocity();
            if (Math.abs(xVelocity) > ((float) this.mMinFlingVelocity)) {
                if (ViewUtils.isLayoutRtl(this)) {
                    if (xVelocity < 0.0f) {
                        z = z2;
                    }
                } else if (xVelocity > 0.0f) {
                    z = z2;
                }
                z2 = false;
                z = z2;
            } else {
                z = getTargetCheckedState();
            }
        } else {
            z = isChecked;
        }
        if (z != isChecked) {
            playSoundEffect(0);
        }
        setChecked(z);
        cancelSuperTouch(motionEvent);
    }

    private void animateThumbToCheckedState(boolean z) {
        z = z ? true : false;
        this.mPositionAnimator = ObjectAnimator.ofFloat(this, THUMB_POS, new float[]{z});
        this.mPositionAnimator.setDuration(250);
        if (VERSION.SDK_INT >= true) {
            this.mPositionAnimator.setAutoCancel(true);
        }
        this.mPositionAnimator.start();
    }

    private void cancelPositionAnimator() {
        ObjectAnimator objectAnimator = this.mPositionAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    private boolean getTargetCheckedState() {
        return this.mThumbPosition > 0.5f;
    }

    void setThumbPosition(float f) {
        this.mThumbPosition = f;
        invalidate();
    }

    public void toggle() {
        setChecked(isChecked() ^ 1);
    }

    public void setChecked(boolean z) {
        super.setChecked(z);
        z = isChecked();
        if (getWindowToken() == null || !ViewCompat.isLaidOut(this)) {
            cancelPositionAnimator();
            setThumbPosition(z ? true : false);
            return;
        }
        animateThumbToCheckedState(z);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        i = 0;
        if (this.mThumbDrawable) {
            z = this.mTempRect;
            i2 = this.mTrackDrawable;
            if (i2 != 0) {
                i2.getPadding(z);
            } else {
                z.setEmpty();
            }
            i2 = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
            i3 = Math.max(0, i2.left - z.left);
            i = Math.max(0, i2.right - z.right);
        } else {
            i3 = 0;
        }
        if (ViewUtils.isLayoutRtl(this)) {
            z = getPaddingLeft() + i3;
            i2 = ((this.mSwitchWidth + z) - i3) - i;
        } else {
            i2 = (getWidth() - getPaddingRight()) - i;
            z = ((i2 - this.mSwitchWidth) + i3) + i;
        }
        i = getGravity() & 112;
        if (i == 16) {
            i = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2;
            i3 = this.mSwitchHeight;
            i -= i3 / 2;
            i3 += i;
        } else if (i != 80) {
            i = getPaddingTop();
            i3 = this.mSwitchHeight + i;
        } else {
            i3 = getHeight() - getPaddingBottom();
            i = i3 - this.mSwitchHeight;
        }
        this.mSwitchLeft = z;
        this.mSwitchTop = i;
        this.mSwitchBottom = i3;
        this.mSwitchRight = i2;
    }

    public void draw(Canvas canvas) {
        Rect opticalBounds;
        Rect rect = this.mTempRect;
        int i = this.mSwitchLeft;
        int i2 = this.mSwitchTop;
        int i3 = this.mSwitchRight;
        int i4 = this.mSwitchBottom;
        int thumbOffset = getThumbOffset() + i;
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            opticalBounds = DrawableUtils.getOpticalBounds(drawable);
        } else {
            opticalBounds = DrawableUtils.INSETS_NONE;
        }
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            int i5;
            int i6;
            drawable2.getPadding(rect);
            thumbOffset += rect.left;
            if (opticalBounds != null) {
                if (opticalBounds.left > rect.left) {
                    i += opticalBounds.left - rect.left;
                }
                i5 = opticalBounds.top > rect.top ? (opticalBounds.top - rect.top) + i2 : i2;
                if (opticalBounds.right > rect.right) {
                    i3 -= opticalBounds.right - rect.right;
                }
                i6 = opticalBounds.bottom > rect.bottom ? i4 - (opticalBounds.bottom - rect.bottom) : i4;
            } else {
                i5 = i2;
                i6 = i4;
            }
            this.mTrackDrawable.setBounds(i, i5, i3, i6);
        }
        Drawable drawable3 = this.mThumbDrawable;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            i = thumbOffset - rect.left;
            thumbOffset = (thumbOffset + this.mThumbWidth) + rect.right;
            this.mThumbDrawable.setBounds(i, i2, thumbOffset, i4);
            Drawable background = getBackground();
            if (background != null) {
                DrawableCompat.setHotspotBounds(background, i, i2, thumbOffset, i4);
            }
        }
        super.draw(canvas);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = this.mTempRect;
        Drawable drawable = this.mTrackDrawable;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i = this.mSwitchTop + rect.top;
        int i2 = this.mSwitchBottom - rect.bottom;
        Drawable drawable2 = this.mThumbDrawable;
        if (drawable != null) {
            if (!this.mSplitTrack || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect opticalBounds = DrawableUtils.getOpticalBounds(drawable2);
                drawable2.copyBounds(rect);
                rect.left += opticalBounds.left;
                rect.right -= opticalBounds.right;
                int save = canvas.save();
                canvas.clipRect(rect, Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        int save2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.mOnLayout : this.mOffLayout;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.mTextColors;
            if (colorStateList != null) {
                this.mTextPaint.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.mTextPaint.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                save = bounds.left + bounds.right;
            } else {
                save = getWidth();
            }
            canvas.translate((float) ((save / 2) - (layout.getWidth() / 2)), (float) (((i + i2) / 2) - (layout.getHeight() / 2)));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }

    public int getCompoundPaddingLeft() {
        if (!ViewUtils.isLayoutRtl(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.mSwitchWidth;
        if (!TextUtils.isEmpty(getText())) {
            compoundPaddingLeft += this.mSwitchPadding;
        }
        return compoundPaddingLeft;
    }

    public int getCompoundPaddingRight() {
        if (ViewUtils.isLayoutRtl(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.mSwitchWidth;
        if (!TextUtils.isEmpty(getText())) {
            compoundPaddingRight += this.mSwitchPadding;
        }
        return compoundPaddingRight;
    }

    private int getThumbOffset() {
        float f;
        if (ViewUtils.isLayoutRtl(this)) {
            f = 1.0f - this.mThumbPosition;
        } else {
            f = this.mThumbPosition;
        }
        return (int) ((f * ((float) getThumbScrollRange())) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.mTrackDrawable;
        if (drawable == null) {
            return 0;
        }
        Rect opticalBounds;
        Rect rect = this.mTempRect;
        drawable.getPadding(rect);
        drawable = this.mThumbDrawable;
        if (drawable != null) {
            opticalBounds = DrawableUtils.getOpticalBounds(drawable);
        } else {
            opticalBounds = DrawableUtils.INSETS_NONE;
        }
        return ((((this.mSwitchWidth - this.mThumbWidth) - rect.left) - rect.right) - opticalBounds.left) - opticalBounds.right;
    }

    protected int[] onCreateDrawableState(int i) {
        i = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(i, CHECKED_STATE_SET);
        }
        return i;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mThumbDrawable;
        int i = 0;
        if (drawable != null && drawable.isStateful()) {
            i = 0 | drawable.setState(drawableState);
        }
        drawable = this.mTrackDrawable;
        if (drawable != null && drawable.isStateful()) {
            i |= drawable.setState(drawableState);
        }
        if (i != 0) {
            invalidate();
        }
    }

    public void drawableHotspotChanged(float f, float f2) {
        if (VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f, f2);
        }
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            DrawableCompat.setHotspot(drawable, f, f2);
        }
        drawable = this.mTrackDrawable;
        if (drawable != null) {
            DrawableCompat.setHotspot(drawable, f, f2);
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        if (!(super.verifyDrawable(drawable) || drawable == this.mThumbDrawable)) {
            if (drawable != this.mTrackDrawable) {
                return null;
            }
        }
        return true;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        drawable = this.mTrackDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.mPositionAnimator;
        if (objectAnimator != null && objectAnimator.isStarted()) {
            this.mPositionAnimator.end();
            this.mPositionAnimator = null;
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ACCESSIBILITY_EVENT_CLASS_NAME);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_EVENT_CLASS_NAME);
        CharSequence charSequence = isChecked() ? this.mTextOn : this.mTextOff;
        if (!TextUtils.isEmpty(charSequence)) {
            CharSequence text = accessibilityNodeInfo.getText();
            if (TextUtils.isEmpty(text)) {
                accessibilityNodeInfo.setText(charSequence);
                return;
            }
            CharSequence stringBuilder = new StringBuilder();
            stringBuilder.append(text);
            stringBuilder.append(' ');
            stringBuilder.append(charSequence);
            accessibilityNodeInfo.setText(stringBuilder);
        }
    }

    public void setCustomSelectionActionModeCallback(Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }
}
