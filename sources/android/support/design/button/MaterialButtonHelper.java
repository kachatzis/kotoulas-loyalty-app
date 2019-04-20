package android.support.design.button;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.C0026R;
import android.support.design.internal.ViewUtils;
import android.support.design.resources.MaterialResources;
import android.support.design.ripple.RippleUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;

@RestrictTo({Scope.LIBRARY_GROUP})
class MaterialButtonHelper {
    private static final float CORNER_RADIUS_ADJUSTMENT = 1.0E-5f;
    private static final int DEFAULT_BACKGROUND_COLOR = -1;
    private static final boolean IS_LOLLIPOP = (VERSION.SDK_INT >= 21);
    @Nullable
    private GradientDrawable backgroundDrawableLollipop;
    private boolean backgroundOverwritten = false;
    @Nullable
    private ColorStateList backgroundTint;
    @Nullable
    private Mode backgroundTintMode;
    private final Rect bounds = new Rect();
    private final Paint buttonStrokePaint = new Paint(1);
    @Nullable
    private GradientDrawable colorableBackgroundDrawableCompat;
    private int cornerRadius;
    private int insetBottom;
    private int insetLeft;
    private int insetRight;
    private int insetTop;
    @Nullable
    private GradientDrawable maskDrawableLollipop;
    private final MaterialButton materialButton;
    private final RectF rectF = new RectF();
    @Nullable
    private ColorStateList rippleColor;
    @Nullable
    private GradientDrawable rippleDrawableCompat;
    @Nullable
    private ColorStateList strokeColor;
    @Nullable
    private GradientDrawable strokeDrawableLollipop;
    private int strokeWidth;
    @Nullable
    private Drawable tintableBackgroundDrawableCompat;
    @Nullable
    private Drawable tintableRippleDrawableCompat;

    public MaterialButtonHelper(MaterialButton materialButton) {
        this.materialButton = materialButton;
    }

    public void loadFromAttributes(TypedArray typedArray) {
        int i = 0;
        this.insetLeft = typedArray.getDimensionPixelOffset(C0026R.styleable.MaterialButton_android_insetLeft, 0);
        this.insetRight = typedArray.getDimensionPixelOffset(C0026R.styleable.MaterialButton_android_insetRight, 0);
        this.insetTop = typedArray.getDimensionPixelOffset(C0026R.styleable.MaterialButton_android_insetTop, 0);
        this.insetBottom = typedArray.getDimensionPixelOffset(C0026R.styleable.MaterialButton_android_insetBottom, 0);
        this.cornerRadius = typedArray.getDimensionPixelSize(C0026R.styleable.MaterialButton_cornerRadius, 0);
        this.strokeWidth = typedArray.getDimensionPixelSize(C0026R.styleable.MaterialButton_strokeWidth, 0);
        this.backgroundTintMode = ViewUtils.parseTintMode(typedArray.getInt(C0026R.styleable.MaterialButton_backgroundTintMode, -1), Mode.SRC_IN);
        this.backgroundTint = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, C0026R.styleable.MaterialButton_backgroundTint);
        this.strokeColor = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, C0026R.styleable.MaterialButton_strokeColor);
        this.rippleColor = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, C0026R.styleable.MaterialButton_rippleColor);
        this.buttonStrokePaint.setStyle(Style.STROKE);
        this.buttonStrokePaint.setStrokeWidth((float) this.strokeWidth);
        typedArray = this.buttonStrokePaint;
        ColorStateList colorStateList = this.strokeColor;
        if (colorStateList != null) {
            i = colorStateList.getColorForState(this.materialButton.getDrawableState(), 0);
        }
        typedArray.setColor(i);
        typedArray = ViewCompat.getPaddingStart(this.materialButton);
        int paddingTop = this.materialButton.getPaddingTop();
        i = ViewCompat.getPaddingEnd(this.materialButton);
        int paddingBottom = this.materialButton.getPaddingBottom();
        this.materialButton.setInternalBackground(IS_LOLLIPOP ? createBackgroundLollipop() : createBackgroundCompat());
        ViewCompat.setPaddingRelative(this.materialButton, typedArray + this.insetLeft, paddingTop + this.insetTop, i + this.insetRight, paddingBottom + this.insetBottom);
    }

    void setBackgroundOverwritten() {
        this.backgroundOverwritten = true;
        this.materialButton.setSupportBackgroundTintList(this.backgroundTint);
        this.materialButton.setSupportBackgroundTintMode(this.backgroundTintMode);
    }

    boolean isBackgroundOverwritten() {
        return this.backgroundOverwritten;
    }

    void drawStroke(@Nullable Canvas canvas) {
        if (canvas != null && this.strokeColor != null && this.strokeWidth > 0) {
            this.bounds.set(this.materialButton.getBackground().getBounds());
            this.rectF.set((((float) this.bounds.left) + (((float) this.strokeWidth) / 2.0f)) + ((float) this.insetLeft), (((float) this.bounds.top) + (((float) this.strokeWidth) / 2.0f)) + ((float) this.insetTop), (((float) this.bounds.right) - (((float) this.strokeWidth) / 2.0f)) - ((float) this.insetRight), (((float) this.bounds.bottom) - (((float) this.strokeWidth) / 2.0f)) - ((float) this.insetBottom));
            float f = ((float) this.cornerRadius) - (((float) this.strokeWidth) / 2.0f);
            canvas.drawRoundRect(this.rectF, f, f, this.buttonStrokePaint);
        }
    }

    private Drawable createBackgroundCompat() {
        this.colorableBackgroundDrawableCompat = new GradientDrawable();
        this.colorableBackgroundDrawableCompat.setCornerRadius(((float) this.cornerRadius) + CORNER_RADIUS_ADJUSTMENT);
        this.colorableBackgroundDrawableCompat.setColor(-1);
        this.tintableBackgroundDrawableCompat = DrawableCompat.wrap(this.colorableBackgroundDrawableCompat);
        DrawableCompat.setTintList(this.tintableBackgroundDrawableCompat, this.backgroundTint);
        Mode mode = this.backgroundTintMode;
        if (mode != null) {
            DrawableCompat.setTintMode(this.tintableBackgroundDrawableCompat, mode);
        }
        this.rippleDrawableCompat = new GradientDrawable();
        this.rippleDrawableCompat.setCornerRadius(((float) this.cornerRadius) + CORNER_RADIUS_ADJUSTMENT);
        this.rippleDrawableCompat.setColor(-1);
        this.tintableRippleDrawableCompat = DrawableCompat.wrap(this.rippleDrawableCompat);
        DrawableCompat.setTintList(this.tintableRippleDrawableCompat, this.rippleColor);
        return wrapDrawableWithInset(new LayerDrawable(new Drawable[]{this.tintableBackgroundDrawableCompat, this.tintableRippleDrawableCompat}));
    }

    private InsetDrawable wrapDrawableWithInset(Drawable drawable) {
        return new InsetDrawable(drawable, this.insetLeft, this.insetTop, this.insetRight, this.insetBottom);
    }

    void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        if (this.backgroundTint != colorStateList) {
            this.backgroundTint = colorStateList;
            if (IS_LOLLIPOP != null) {
                updateTintAndTintModeLollipop();
                return;
            }
            colorStateList = this.tintableBackgroundDrawableCompat;
            if (colorStateList != null) {
                DrawableCompat.setTintList(colorStateList, this.backgroundTint);
            }
        }
    }

    ColorStateList getSupportBackgroundTintList() {
        return this.backgroundTint;
    }

    void setSupportBackgroundTintMode(@Nullable Mode mode) {
        if (this.backgroundTintMode != mode) {
            this.backgroundTintMode = mode;
            if (IS_LOLLIPOP != null) {
                updateTintAndTintModeLollipop();
                return;
            }
            mode = this.tintableBackgroundDrawableCompat;
            if (mode != null) {
                Mode mode2 = this.backgroundTintMode;
                if (mode2 != null) {
                    DrawableCompat.setTintMode(mode, mode2);
                }
            }
        }
    }

    Mode getSupportBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    private void updateTintAndTintModeLollipop() {
        Drawable drawable = this.backgroundDrawableLollipop;
        if (drawable != null) {
            DrawableCompat.setTintList(drawable, this.backgroundTint);
            Mode mode = this.backgroundTintMode;
            if (mode != null) {
                DrawableCompat.setTintMode(this.backgroundDrawableLollipop, mode);
            }
        }
    }

    @TargetApi(21)
    private Drawable createBackgroundLollipop() {
        this.backgroundDrawableLollipop = new GradientDrawable();
        this.backgroundDrawableLollipop.setCornerRadius(((float) this.cornerRadius) + CORNER_RADIUS_ADJUSTMENT);
        this.backgroundDrawableLollipop.setColor(-1);
        updateTintAndTintModeLollipop();
        this.strokeDrawableLollipop = new GradientDrawable();
        this.strokeDrawableLollipop.setCornerRadius(((float) this.cornerRadius) + CORNER_RADIUS_ADJUSTMENT);
        this.strokeDrawableLollipop.setColor(0);
        this.strokeDrawableLollipop.setStroke(this.strokeWidth, this.strokeColor);
        InsetDrawable wrapDrawableWithInset = wrapDrawableWithInset(new LayerDrawable(new Drawable[]{this.backgroundDrawableLollipop, this.strokeDrawableLollipop}));
        this.maskDrawableLollipop = new GradientDrawable();
        this.maskDrawableLollipop.setCornerRadius(((float) this.cornerRadius) + CORNER_RADIUS_ADJUSTMENT);
        this.maskDrawableLollipop.setColor(-1);
        return new MaterialButtonBackgroundDrawable(RippleUtils.convertToRippleDrawableColor(this.rippleColor), wrapDrawableWithInset, this.maskDrawableLollipop);
    }

    void updateMaskBounds(int i, int i2) {
        GradientDrawable gradientDrawable = this.maskDrawableLollipop;
        if (gradientDrawable != null) {
            gradientDrawable.setBounds(this.insetLeft, this.insetTop, i2 - this.insetRight, i - this.insetBottom);
        }
    }

    void setBackgroundColor(int i) {
        GradientDrawable gradientDrawable;
        if (IS_LOLLIPOP) {
            gradientDrawable = this.backgroundDrawableLollipop;
            if (gradientDrawable != null) {
                gradientDrawable.setColor(i);
                return;
            }
        }
        if (!IS_LOLLIPOP) {
            gradientDrawable = this.colorableBackgroundDrawableCompat;
            if (gradientDrawable != null) {
                gradientDrawable.setColor(i);
            }
        }
    }

    void setRippleColor(@Nullable ColorStateList colorStateList) {
        if (this.rippleColor != colorStateList) {
            this.rippleColor = colorStateList;
            if (IS_LOLLIPOP && (this.materialButton.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.materialButton.getBackground()).setColor(colorStateList);
            } else if (!IS_LOLLIPOP) {
                Drawable drawable = this.tintableRippleDrawableCompat;
                if (drawable != null) {
                    DrawableCompat.setTintList(drawable, colorStateList);
                }
            }
        }
    }

    @Nullable
    ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    void setStrokeColor(@Nullable ColorStateList colorStateList) {
        if (this.strokeColor != colorStateList) {
            this.strokeColor = colorStateList;
            Paint paint = this.buttonStrokePaint;
            int i = 0;
            if (colorStateList != null) {
                i = colorStateList.getColorForState(this.materialButton.getDrawableState(), 0);
            }
            paint.setColor(i);
            updateStroke();
        }
    }

    @Nullable
    ColorStateList getStrokeColor() {
        return this.strokeColor;
    }

    void setStrokeWidth(int i) {
        if (this.strokeWidth != i) {
            this.strokeWidth = i;
            this.buttonStrokePaint.setStrokeWidth((float) i);
            updateStroke();
        }
    }

    int getStrokeWidth() {
        return this.strokeWidth;
    }

    private void updateStroke() {
        if (IS_LOLLIPOP && this.strokeDrawableLollipop != null) {
            this.materialButton.setInternalBackground(createBackgroundLollipop());
        } else if (!IS_LOLLIPOP) {
            this.materialButton.invalidate();
        }
    }

    void setCornerRadius(int i) {
        if (this.cornerRadius != i) {
            this.cornerRadius = i;
            if (IS_LOLLIPOP && this.backgroundDrawableLollipop != null && this.strokeDrawableLollipop != null && this.maskDrawableLollipop != null) {
                if (VERSION.SDK_INT == 21) {
                    float f = ((float) i) + CORNER_RADIUS_ADJUSTMENT;
                    unwrapBackgroundDrawable().setCornerRadius(f);
                    unwrapStrokeDrawable().setCornerRadius(f);
                }
                i = ((float) i) + 925353388;
                this.backgroundDrawableLollipop.setCornerRadius(i);
                this.strokeDrawableLollipop.setCornerRadius(i);
                this.maskDrawableLollipop.setCornerRadius(i);
            } else if (!IS_LOLLIPOP) {
                GradientDrawable gradientDrawable = this.colorableBackgroundDrawableCompat;
                if (gradientDrawable != null && this.rippleDrawableCompat != null) {
                    i = ((float) i) + 925353388;
                    gradientDrawable.setCornerRadius(i);
                    this.rippleDrawableCompat.setCornerRadius(i);
                    this.materialButton.invalidate();
                }
            }
        }
    }

    int getCornerRadius() {
        return this.cornerRadius;
    }

    @Nullable
    private GradientDrawable unwrapStrokeDrawable() {
        return (!IS_LOLLIPOP || this.materialButton.getBackground() == null) ? null : (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(1);
    }

    @Nullable
    private GradientDrawable unwrapBackgroundDrawable() {
        return (!IS_LOLLIPOP || this.materialButton.getBackground() == null) ? null : (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(0);
    }
}
