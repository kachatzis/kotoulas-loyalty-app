package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.design.C0026R;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;

public class ShadowDrawableWrapper extends DrawableWrapper {
    static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    static final float SHADOW_BOTTOM_SCALE = 1.0f;
    static final float SHADOW_HORIZ_SCALE = 0.5f;
    static final float SHADOW_MULTIPLIER = 1.5f;
    static final float SHADOW_TOP_SCALE = 0.25f;
    private boolean addPaddingForCorners = true;
    final RectF contentBounds;
    float cornerRadius;
    final Paint cornerShadowPaint;
    Path cornerShadowPath;
    private boolean dirty = true;
    final Paint edgeShadowPaint;
    float maxShadowSize;
    private boolean printedShadowClipWarning = false;
    float rawMaxShadowSize;
    float rawShadowSize;
    private float rotation;
    private final int shadowEndColor;
    private final int shadowMiddleColor;
    float shadowSize;
    private final int shadowStartColor;

    public int getOpacity() {
        return -3;
    }

    public ShadowDrawableWrapper(Context context, Drawable drawable, float f, float f2, float f3) {
        super(drawable);
        this.shadowStartColor = ContextCompat.getColor(context, C0026R.color.design_fab_shadow_start_color);
        this.shadowMiddleColor = ContextCompat.getColor(context, C0026R.color.design_fab_shadow_mid_color);
        this.shadowEndColor = ContextCompat.getColor(context, C0026R.color.design_fab_shadow_end_color);
        this.cornerShadowPaint = new Paint(5);
        this.cornerShadowPaint.setStyle(Style.FILL);
        this.cornerRadius = (float) Math.round(f);
        this.contentBounds = new RectF();
        this.edgeShadowPaint = new Paint(this.cornerShadowPaint);
        this.edgeShadowPaint.setAntiAlias(false);
        setShadowSize(f2, f3);
    }

    private static int toEven(float f) {
        f = Math.round(f);
        return f % 2 == 1 ? f - 1 : f;
    }

    public void setAddPaddingForCorners(boolean z) {
        this.addPaddingForCorners = z;
        invalidateSelf();
    }

    public void setAlpha(int i) {
        super.setAlpha(i);
        this.cornerShadowPaint.setAlpha(i);
        this.edgeShadowPaint.setAlpha(i);
    }

    protected void onBoundsChange(Rect rect) {
        this.dirty = true;
    }

    public void setShadowSize(float f, float f2) {
        if (f < 0.0f || f2 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        f = (float) toEven(f);
        f2 = (float) toEven(f2);
        if (f > f2) {
            if (this.printedShadowClipWarning == null) {
                this.printedShadowClipWarning = true;
            }
            f = f2;
        }
        if (this.rawShadowSize != f || this.rawMaxShadowSize != f2) {
            this.rawShadowSize = f;
            this.rawMaxShadowSize = f2;
            this.shadowSize = (float) Math.round(f * SHADOW_MULTIPLIER);
            this.maxShadowSize = f2;
            this.dirty = true;
            invalidateSelf();
        }
    }

    public void setShadowSize(float f) {
        setShadowSize(f, this.rawMaxShadowSize);
    }

    public float getShadowSize() {
        return this.rawShadowSize;
    }

    public boolean getPadding(Rect rect) {
        int ceil = (int) Math.ceil((double) calculateVerticalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        int ceil2 = (int) Math.ceil((double) calculateHorizontalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    public static float calculateVerticalPadding(float f, float f2, boolean z) {
        if (!z) {
            return f * SHADOW_MULTIPLIER;
        }
        double d = (double) (f * SHADOW_MULTIPLIER);
        double d2 = 1.0d - COS_45;
        f = (double) f2;
        Double.isNaN(f);
        d2 *= f;
        Double.isNaN(d);
        return (float) (d + d2);
    }

    public static float calculateHorizontalPadding(float f, float f2, boolean z) {
        if (!z) {
            return f;
        }
        double d = (double) f;
        double d2 = 1.0d - COS_45;
        f = (double) f2;
        Double.isNaN(f);
        d2 *= f;
        Double.isNaN(d);
        return (float) (d + d2);
    }

    public void setCornerRadius(float f) {
        f = (float) Math.round(f);
        if (this.cornerRadius != f) {
            this.cornerRadius = f;
            this.dirty = true;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        if (this.dirty) {
            buildComponents(getBounds());
            this.dirty = false;
        }
        drawShadow(canvas);
        super.draw(canvas);
    }

    public final void setRotation(float f) {
        if (this.rotation != f) {
            this.rotation = f;
            invalidateSelf();
        }
    }

    private void drawShadow(Canvas canvas) {
        int i;
        float f;
        int i2;
        float f2;
        float f3;
        Canvas canvas2 = canvas;
        int save = canvas.save();
        canvas2.rotate(this.rotation, this.contentBounds.centerX(), this.contentBounds.centerY());
        float f4 = this.cornerRadius;
        float f5 = (-f4) - this.shadowSize;
        float f6 = f4 * 2.0f;
        Object obj = this.contentBounds.width() - f6 > 0.0f ? 1 : null;
        Object obj2 = r0.contentBounds.height() - f6 > 0.0f ? 1 : null;
        float f7 = r0.rawShadowSize;
        float f8 = f4 / ((f7 - (SHADOW_HORIZ_SCALE * f7)) + f4);
        float f9 = f4 / ((f7 - (SHADOW_TOP_SCALE * f7)) + f4);
        float f10 = f4 / ((f7 - (f7 * SHADOW_BOTTOM_SCALE)) + f4);
        int save2 = canvas.save();
        canvas2.translate(r0.contentBounds.left + f4, r0.contentBounds.top + f4);
        canvas2.scale(f8, f9);
        canvas2.drawPath(r0.cornerShadowPath, r0.cornerShadowPaint);
        if (obj != null) {
            canvas2.scale(SHADOW_BOTTOM_SCALE / f8, SHADOW_BOTTOM_SCALE);
            i = save2;
            f = f10;
            i2 = save;
            f2 = f9;
            canvas.drawRect(0.0f, f5, r0.contentBounds.width() - f6, -r0.cornerRadius, r0.edgeShadowPaint);
        } else {
            i = save2;
            f = f10;
            i2 = save;
            f2 = f9;
        }
        canvas2.restoreToCount(i);
        i = canvas.save();
        canvas2.translate(r0.contentBounds.right - f4, r0.contentBounds.bottom - f4);
        f9 = f;
        canvas2.scale(f8, f9);
        canvas2.rotate(180.0f);
        canvas2.drawPath(r0.cornerShadowPath, r0.cornerShadowPaint);
        if (obj != null) {
            canvas2.scale(SHADOW_BOTTOM_SCALE / f8, SHADOW_BOTTOM_SCALE);
            f3 = f2;
            f2 = f9;
            canvas.drawRect(0.0f, f5, r0.contentBounds.width() - f6, (-r0.cornerRadius) + r0.shadowSize, r0.edgeShadowPaint);
        } else {
            f3 = f2;
            f2 = f9;
        }
        canvas2.restoreToCount(i);
        int save3 = canvas.save();
        canvas2.translate(r0.contentBounds.left + f4, r0.contentBounds.bottom - f4);
        canvas2.scale(f8, f2);
        canvas2.rotate(270.0f);
        canvas2.drawPath(r0.cornerShadowPath, r0.cornerShadowPaint);
        if (obj2 != null) {
            canvas2.scale(SHADOW_BOTTOM_SCALE / f2, SHADOW_BOTTOM_SCALE);
            canvas.drawRect(0.0f, f5, r0.contentBounds.height() - f6, -r0.cornerRadius, r0.edgeShadowPaint);
        }
        canvas2.restoreToCount(save3);
        save = canvas.save();
        canvas2.translate(r0.contentBounds.right - f4, r0.contentBounds.top + f4);
        f4 = f3;
        canvas2.scale(f8, f4);
        canvas2.rotate(90.0f);
        canvas2.drawPath(r0.cornerShadowPath, r0.cornerShadowPaint);
        if (obj2 != null) {
            canvas2.scale(SHADOW_BOTTOM_SCALE / f4, SHADOW_BOTTOM_SCALE);
            canvas.drawRect(0.0f, f5, r0.contentBounds.height() - f6, -r0.cornerRadius, r0.edgeShadowPaint);
        }
        canvas2.restoreToCount(save);
        canvas2.restoreToCount(i2);
    }

    private void buildShadowCorners() {
        float f = this.cornerRadius;
        RectF rectF = new RectF(-f, -f, f, f);
        RectF rectF2 = new RectF(rectF);
        float f2 = this.shadowSize;
        rectF2.inset(-f2, -f2);
        Path path = this.cornerShadowPath;
        if (path == null) {
            r0.cornerShadowPath = new Path();
        } else {
            path.reset();
        }
        r0.cornerShadowPath.setFillType(FillType.EVEN_ODD);
        r0.cornerShadowPath.moveTo(-r0.cornerRadius, 0.0f);
        r0.cornerShadowPath.rLineTo(-r0.shadowSize, 0.0f);
        r0.cornerShadowPath.arcTo(rectF2, 180.0f, 90.0f, false);
        r0.cornerShadowPath.arcTo(rectF, 270.0f, -90.0f, false);
        r0.cornerShadowPath.close();
        float f3 = -rectF2.top;
        if (f3 > 0.0f) {
            float f4 = r0.cornerRadius / f3;
            float f5 = ((SHADOW_BOTTOM_SCALE - f4) / 2.0f) + f4;
            Paint paint = r0.cornerShadowPaint;
            Shader shader = r8;
            Shader radialGradient = new RadialGradient(0.0f, 0.0f, f3, new int[]{0, r0.shadowStartColor, r0.shadowMiddleColor, r0.shadowEndColor}, new float[]{0.0f, f4, f5, SHADOW_BOTTOM_SCALE}, TileMode.CLAMP);
            paint.setShader(shader);
        }
        r0.edgeShadowPaint.setShader(new LinearGradient(0.0f, rectF.top, 0.0f, rectF2.top, new int[]{r0.shadowStartColor, r0.shadowMiddleColor, r0.shadowEndColor}, new float[]{0.0f, SHADOW_HORIZ_SCALE, SHADOW_BOTTOM_SCALE}, TileMode.CLAMP));
        r0.edgeShadowPaint.setAntiAlias(false);
    }

    private void buildComponents(Rect rect) {
        float f = this.rawMaxShadowSize * SHADOW_MULTIPLIER;
        this.contentBounds.set(((float) rect.left) + this.rawMaxShadowSize, ((float) rect.top) + f, ((float) rect.right) - this.rawMaxShadowSize, ((float) rect.bottom) - f);
        getWrappedDrawable().setBounds((int) this.contentBounds.left, (int) this.contentBounds.top, (int) this.contentBounds.right, (int) this.contentBounds.bottom);
        buildShadowCorners();
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public void setMaxShadowSize(float f) {
        setShadowSize(this.rawShadowSize, f);
    }

    public float getMaxShadowSize() {
        return this.rawMaxShadowSize;
    }

    public float getMinWidth() {
        float f = this.rawMaxShadowSize;
        return (Math.max(f, this.cornerRadius + (f / 2.0f)) * 2.0f) + (this.rawMaxShadowSize * 2.0f);
    }

    public float getMinHeight() {
        float f = this.rawMaxShadowSize;
        return (Math.max(f, this.cornerRadius + ((f * SHADOW_MULTIPLIER) / 2.0f)) * 2.0f) + ((this.rawMaxShadowSize * SHADOW_MULTIPLIER) * 2.0f);
    }
}
