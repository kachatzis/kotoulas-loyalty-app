package android.support.v7.graphics.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.appcompat.C0291R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DrawerArrowDrawable extends Drawable {
    public static final int ARROW_DIRECTION_END = 3;
    public static final int ARROW_DIRECTION_LEFT = 0;
    public static final int ARROW_DIRECTION_RIGHT = 1;
    public static final int ARROW_DIRECTION_START = 2;
    private static final float ARROW_HEAD_ANGLE = ((float) Math.toRadians(45.0d));
    private float mArrowHeadLength;
    private float mArrowShaftLength;
    private float mBarGap;
    private float mBarLength;
    private int mDirection = 2;
    private float mMaxCutForBarSize;
    private final Paint mPaint = new Paint();
    private final Path mPath = new Path();
    private float mProgress;
    private final int mSize;
    private boolean mSpin;
    private boolean mVerticalMirror = false;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ArrowDirection {
    }

    private static float lerp(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    public int getOpacity() {
        return -3;
    }

    public DrawerArrowDrawable(Context context) {
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeJoin(Join.MITER);
        this.mPaint.setStrokeCap(Cap.BUTT);
        this.mPaint.setAntiAlias(true);
        context = context.getTheme().obtainStyledAttributes(null, C0291R.styleable.DrawerArrowToggle, C0291R.attr.drawerArrowStyle, C0291R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        setColor(context.getColor(C0291R.styleable.DrawerArrowToggle_color, 0));
        setBarThickness(context.getDimension(C0291R.styleable.DrawerArrowToggle_thickness, 0.0f));
        setSpinEnabled(context.getBoolean(C0291R.styleable.DrawerArrowToggle_spinBars, true));
        setGapSize((float) Math.round(context.getDimension(C0291R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f)));
        this.mSize = context.getDimensionPixelSize(C0291R.styleable.DrawerArrowToggle_drawableSize, 0);
        this.mBarLength = (float) Math.round(context.getDimension(C0291R.styleable.DrawerArrowToggle_barLength, 0.0f));
        this.mArrowHeadLength = (float) Math.round(context.getDimension(C0291R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.mArrowShaftLength = context.getDimension(C0291R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        context.recycle();
    }

    public void setArrowHeadLength(float f) {
        if (this.mArrowHeadLength != f) {
            this.mArrowHeadLength = f;
            invalidateSelf();
        }
    }

    public float getArrowHeadLength() {
        return this.mArrowHeadLength;
    }

    public void setArrowShaftLength(float f) {
        if (this.mArrowShaftLength != f) {
            this.mArrowShaftLength = f;
            invalidateSelf();
        }
    }

    public float getArrowShaftLength() {
        return this.mArrowShaftLength;
    }

    public float getBarLength() {
        return this.mBarLength;
    }

    public void setBarLength(float f) {
        if (this.mBarLength != f) {
            this.mBarLength = f;
            invalidateSelf();
        }
    }

    public void setColor(@ColorInt int i) {
        if (i != this.mPaint.getColor()) {
            this.mPaint.setColor(i);
            invalidateSelf();
        }
    }

    @ColorInt
    public int getColor() {
        return this.mPaint.getColor();
    }

    public void setBarThickness(float f) {
        if (this.mPaint.getStrokeWidth() != f) {
            this.mPaint.setStrokeWidth(f);
            double d = (double) (f / 2.0f);
            double cos = Math.cos((double) ARROW_HEAD_ANGLE);
            Double.isNaN(d);
            this.mMaxCutForBarSize = (float) (d * cos);
            invalidateSelf();
        }
    }

    public float getBarThickness() {
        return this.mPaint.getStrokeWidth();
    }

    public float getGapSize() {
        return this.mBarGap;
    }

    public void setGapSize(float f) {
        if (f != this.mBarGap) {
            this.mBarGap = f;
            invalidateSelf();
        }
    }

    public void setDirection(int i) {
        if (i != this.mDirection) {
            this.mDirection = i;
            invalidateSelf();
        }
    }

    public boolean isSpinEnabled() {
        return this.mSpin;
    }

    public void setSpinEnabled(boolean z) {
        if (this.mSpin != z) {
            this.mSpin = z;
            invalidateSelf();
        }
    }

    public int getDirection() {
        return this.mDirection;
    }

    public void setVerticalMirror(boolean z) {
        if (this.mVerticalMirror != z) {
            this.mVerticalMirror = z;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        Rect bounds = getBounds();
        int i = this.mDirection;
        int i2 = 0;
        if (i != 3) {
            switch (i) {
                case 0:
                    break;
                case 1:
                    i2 = 1;
                    break;
                default:
                    if (DrawableCompat.getLayoutDirection(this) == 1) {
                        i2 = 1;
                        break;
                    }
                    break;
            }
        } else if (DrawableCompat.getLayoutDirection(this) == 0) {
            i2 = 1;
        }
        float f = r0.mArrowHeadLength;
        f = lerp(r0.mBarLength, (float) Math.sqrt((double) ((f * f) * 2.0f)), r0.mProgress);
        float lerp = lerp(r0.mBarLength, r0.mArrowShaftLength, r0.mProgress);
        float round = (float) Math.round(lerp(0.0f, r0.mMaxCutForBarSize, r0.mProgress));
        float lerp2 = lerp(0.0f, ARROW_HEAD_ANGLE, r0.mProgress);
        double d = (double) f;
        float lerp3 = lerp(i2 != 0 ? 0.0f : -180.0f, i2 != 0 ? 180.0f : 0.0f, r0.mProgress);
        double d2 = (double) lerp2;
        double cos = Math.cos(d2);
        Double.isNaN(d);
        f = (float) Math.round(cos * d);
        d2 = Math.sin(d2);
        Double.isNaN(d);
        float round2 = (float) Math.round(d * d2);
        r0.mPath.rewind();
        float lerp4 = lerp(r0.mBarGap + r0.mPaint.getStrokeWidth(), -r0.mMaxCutForBarSize, r0.mProgress);
        float f2 = (-lerp) / 2.0f;
        r0.mPath.moveTo(f2 + round, 0.0f);
        r0.mPath.rLineTo(lerp - (round * 2.0f), 0.0f);
        r0.mPath.moveTo(f2, lerp4);
        r0.mPath.rLineTo(f, round2);
        r0.mPath.moveTo(f2, -lerp4);
        r0.mPath.rLineTo(f, -round2);
        r0.mPath.close();
        canvas.save();
        f = r0.mPaint.getStrokeWidth();
        round2 = ((float) bounds.height()) - (3.0f * f);
        float f3 = r0.mBarGap;
        canvas2.translate((float) bounds.centerX(), ((float) ((((int) (round2 - (2.0f * f3))) / 4) * 2)) + ((f * 1.5f) + f3));
        if (r0.mSpin) {
            canvas2.rotate(lerp3 * ((float) ((r0.mVerticalMirror ^ i2) != 0 ? -1 : 1)));
        } else if (i2 != 0) {
            canvas2.rotate(180.0f);
        }
        canvas2.drawPath(r0.mPath, r0.mPaint);
        canvas.restore();
    }

    public void setAlpha(int i) {
        if (i != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getIntrinsicHeight() {
        return this.mSize;
    }

    public int getIntrinsicWidth() {
        return this.mSize;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.mProgress;
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.mProgress != f) {
            this.mProgress = f;
            invalidateSelf();
        }
    }

    public final Paint getPaint() {
        return this.mPaint;
    }
}
