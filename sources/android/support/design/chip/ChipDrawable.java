package android.support.design.chip;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.AnimatorRes;
import android.support.annotation.AttrRes;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.C0026R;
import android.support.design.animation.MotionSpec;
import android.support.design.canvas.CanvasCompat;
import android.support.design.drawable.DrawableUtils;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.resources.MaterialResources;
import android.support.design.resources.TextAppearance;
import android.support.design.ripple.RippleUtils;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.text.BidiFormatter;
import android.support.v4.view.ViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class ChipDrawable extends Drawable implements TintAwareDrawable, Callback {
    private static final boolean DEBUG = false;
    private static final int[] DEFAULT_STATE = new int[]{16842910};
    private static final String NAMESPACE_APP = "http://schemas.android.com/apk/res-auto";
    private int alpha = 255;
    private boolean checkable;
    @Nullable
    private Drawable checkedIcon;
    private boolean checkedIconVisible;
    @Nullable
    private ColorStateList chipBackgroundColor;
    private float chipCornerRadius;
    private float chipEndPadding;
    @Nullable
    private Drawable chipIcon;
    private float chipIconSize;
    @Nullable
    private ColorStateList chipIconTint;
    private boolean chipIconVisible;
    private float chipMinHeight;
    private final Paint chipPaint = new Paint(1);
    private float chipStartPadding;
    @Nullable
    private ColorStateList chipStrokeColor;
    private float chipStrokeWidth;
    @Nullable
    private Drawable closeIcon;
    @Nullable
    private CharSequence closeIconContentDescription;
    private float closeIconEndPadding;
    private float closeIconSize;
    private float closeIconStartPadding;
    private int[] closeIconStateSet;
    @Nullable
    private ColorStateList closeIconTint;
    private boolean closeIconVisible;
    @Nullable
    private ColorFilter colorFilter;
    @Nullable
    private ColorStateList compatRippleColor;
    private final Context context;
    private boolean currentChecked;
    @ColorInt
    private int currentChipBackgroundColor;
    @ColorInt
    private int currentChipStrokeColor;
    @ColorInt
    private int currentCompatRippleColor;
    @ColorInt
    private int currentTextColor;
    @ColorInt
    private int currentTint;
    @Nullable
    private final Paint debugPaint;
    private WeakReference<Delegate> delegate = new WeakReference(null);
    private final FontCallback fontCallback = new C04541();
    private final FontMetrics fontMetrics = new FontMetrics();
    @Nullable
    private MotionSpec hideMotionSpec;
    private float iconEndPadding;
    private float iconStartPadding;
    private int maxWidth;
    private final PointF pointF = new PointF();
    @Nullable
    private CharSequence rawText;
    private final RectF rectF = new RectF();
    @Nullable
    private ColorStateList rippleColor;
    private boolean shouldDrawText;
    @Nullable
    private MotionSpec showMotionSpec;
    @Nullable
    private TextAppearance textAppearance;
    private float textEndPadding;
    private final TextPaint textPaint = new TextPaint(1);
    private float textStartPadding;
    private float textWidth;
    private boolean textWidthDirty = true;
    @Nullable
    private ColorStateList tint;
    @Nullable
    private PorterDuffColorFilter tintFilter;
    @Nullable
    private Mode tintMode = Mode.SRC_IN;
    private TruncateAt truncateAt;
    @Nullable
    private CharSequence unicodeWrappedText;
    private boolean useCompatRipple;

    public interface Delegate {
        void onChipDrawableSizeChange();
    }

    /* renamed from: android.support.design.chip.ChipDrawable$1 */
    class C04541 extends FontCallback {
        public void onFontRetrievalFailed(int i) {
        }

        C04541() {
        }

        public void onFontRetrieved(@NonNull Typeface typeface) {
            ChipDrawable.this.textWidthDirty = true;
            ChipDrawable.this.onSizeChange();
            ChipDrawable.this.invalidateSelf();
        }
    }

    public static android.support.design.chip.ChipDrawable createFromResource(android.content.Context r4, @android.support.annotation.XmlRes int r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x0064 in {5, 11, 13, 15, 17, 19, 20, 22} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r0 = r4.getResources();	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        r0 = r0.getXml(r5);	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
    L_0x0008:
        r1 = r0.next();	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        r2 = 2;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        if (r1 == r2) goto L_0x0012;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
    L_0x000f:
        r3 = 1;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        if (r1 != r3) goto L_0x0008;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
    L_0x0012:
        if (r1 != r2) goto L_0x003b;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
    L_0x0014:
        r1 = r0.getName();	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        r2 = "chip";	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        r1 = android.text.TextUtils.equals(r1, r2);	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        if (r1 == 0) goto L_0x0033;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
    L_0x0020:
        r0 = android.util.Xml.asAttributeSet(r0);	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        r1 = r0.getStyleAttribute();	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        if (r1 != 0) goto L_0x002c;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
    L_0x002a:
        r1 = android.support.design.C0026R.style.Widget_MaterialComponents_Chip_Entry;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
    L_0x002c:
        r2 = android.support.design.C0026R.attr.chipStandaloneStyle;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        r4 = createFromAttributes(r4, r0, r2, r1);	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        return r4;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
    L_0x0033:
        r4 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        r0 = "Must have a <chip> start tag";	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        r4.<init>(r0);	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        throw r4;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
    L_0x003b:
        r4 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        r0 = "No start tag found";	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        r4.<init>(r0);	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
        throw r4;	 Catch:{ XmlPullParserException -> 0x0045, IOException -> 0x0043 }
    L_0x0043:
        r4 = move-exception;
        goto L_0x0046;
    L_0x0045:
        r4 = move-exception;
    L_0x0046:
        r0 = new android.content.res.Resources$NotFoundException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Can't load chip resource ID #0x";
        r1.append(r2);
        r5 = java.lang.Integer.toHexString(r5);
        r1.append(r5);
        r5 = r1.toString();
        r0.<init>(r5);
        r0.initCause(r4);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.chip.ChipDrawable.createFromResource(android.content.Context, int):android.support.design.chip.ChipDrawable");
    }

    public int getOpacity() {
        return -3;
    }

    public static ChipDrawable createFromAttributes(Context context, AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        ChipDrawable chipDrawable = new ChipDrawable(context);
        chipDrawable.loadFromAttributes(attributeSet, i, i2);
        return chipDrawable;
    }

    private ChipDrawable(Context context) {
        this.context = context;
        this.rawText = "";
        this.textPaint.density = context.getResources().getDisplayMetrics().density;
        this.debugPaint = null;
        context = this.debugPaint;
        if (context != null) {
            context.setStyle(Style.STROKE);
        }
        setState(DEFAULT_STATE);
        setCloseIconState(DEFAULT_STATE);
        this.shouldDrawText = true;
    }

    private void loadFromAttributes(AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        i = ThemeEnforcement.obtainStyledAttributes(this.context, attributeSet, C0026R.styleable.Chip, i, i2, new int[0]);
        setChipBackgroundColor(MaterialResources.getColorStateList(this.context, i, C0026R.styleable.Chip_chipBackgroundColor));
        setChipMinHeight(i.getDimension(C0026R.styleable.Chip_chipMinHeight, 0.0f));
        setChipCornerRadius(i.getDimension(C0026R.styleable.Chip_chipCornerRadius, 0.0f));
        setChipStrokeColor(MaterialResources.getColorStateList(this.context, i, C0026R.styleable.Chip_chipStrokeColor));
        setChipStrokeWidth(i.getDimension(C0026R.styleable.Chip_chipStrokeWidth, 0.0f));
        setRippleColor(MaterialResources.getColorStateList(this.context, i, C0026R.styleable.Chip_rippleColor));
        setText(i.getText(C0026R.styleable.Chip_android_text));
        setTextAppearance(MaterialResources.getTextAppearance(this.context, i, C0026R.styleable.Chip_android_textAppearance));
        switch (i.getInt(C0026R.styleable.Chip_android_ellipsize, 0)) {
            case 1:
                setEllipsize(TruncateAt.START);
                break;
            case 2:
                setEllipsize(TruncateAt.MIDDLE);
                break;
            case 3:
                setEllipsize(TruncateAt.END);
                break;
            default:
                break;
        }
        setChipIconVisible(i.getBoolean(C0026R.styleable.Chip_chipIconVisible, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue(NAMESPACE_APP, "chipIconEnabled") == 0 || attributeSet.getAttributeValue(NAMESPACE_APP, "chipIconVisible") != 0)) {
            setChipIconVisible(i.getBoolean(C0026R.styleable.Chip_chipIconEnabled, false));
        }
        setChipIcon(MaterialResources.getDrawable(this.context, i, C0026R.styleable.Chip_chipIcon));
        setChipIconTint(MaterialResources.getColorStateList(this.context, i, C0026R.styleable.Chip_chipIconTint));
        setChipIconSize(i.getDimension(C0026R.styleable.Chip_chipIconSize, 0.0f));
        setCloseIconVisible(i.getBoolean(C0026R.styleable.Chip_closeIconVisible, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue(NAMESPACE_APP, "closeIconEnabled") == 0 || attributeSet.getAttributeValue(NAMESPACE_APP, "closeIconVisible") != 0)) {
            setCloseIconVisible(i.getBoolean(C0026R.styleable.Chip_closeIconEnabled, false));
        }
        setCloseIcon(MaterialResources.getDrawable(this.context, i, C0026R.styleable.Chip_closeIcon));
        setCloseIconTint(MaterialResources.getColorStateList(this.context, i, C0026R.styleable.Chip_closeIconTint));
        setCloseIconSize(i.getDimension(C0026R.styleable.Chip_closeIconSize, 0.0f));
        setCheckable(i.getBoolean(C0026R.styleable.Chip_android_checkable, false));
        setCheckedIconVisible(i.getBoolean(C0026R.styleable.Chip_checkedIconVisible, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue(NAMESPACE_APP, "checkedIconEnabled") == 0 || attributeSet.getAttributeValue(NAMESPACE_APP, "checkedIconVisible") != null)) {
            setCheckedIconVisible(i.getBoolean(C0026R.styleable.Chip_checkedIconEnabled, false));
        }
        setCheckedIcon(MaterialResources.getDrawable(this.context, i, C0026R.styleable.Chip_checkedIcon));
        setShowMotionSpec(MotionSpec.createFromAttribute(this.context, i, C0026R.styleable.Chip_showMotionSpec));
        setHideMotionSpec(MotionSpec.createFromAttribute(this.context, i, C0026R.styleable.Chip_hideMotionSpec));
        setChipStartPadding(i.getDimension(C0026R.styleable.Chip_chipStartPadding, 0.0f));
        setIconStartPadding(i.getDimension(C0026R.styleable.Chip_iconStartPadding, 0.0f));
        setIconEndPadding(i.getDimension(C0026R.styleable.Chip_iconEndPadding, 0.0f));
        setTextStartPadding(i.getDimension(C0026R.styleable.Chip_textStartPadding, 0.0f));
        setTextEndPadding(i.getDimension(C0026R.styleable.Chip_textEndPadding, 0.0f));
        setCloseIconStartPadding(i.getDimension(C0026R.styleable.Chip_closeIconStartPadding, 0.0f));
        setCloseIconEndPadding(i.getDimension(C0026R.styleable.Chip_closeIconEndPadding, 0.0f));
        setChipEndPadding(i.getDimension(C0026R.styleable.Chip_chipEndPadding, 0.0f));
        setMaxWidth(i.getDimensionPixelSize(C0026R.styleable.Chip_android_maxWidth, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED));
        i.recycle();
    }

    public void setUseCompatRipple(boolean z) {
        if (this.useCompatRipple != z) {
            this.useCompatRipple = z;
            updateCompatRippleColor();
            onStateChange(getState());
        }
    }

    public boolean getUseCompatRipple() {
        return this.useCompatRipple;
    }

    public void setDelegate(@Nullable Delegate delegate) {
        this.delegate = new WeakReference(delegate);
    }

    protected void onSizeChange() {
        Delegate delegate = (Delegate) this.delegate.get();
        if (delegate != null) {
            delegate.onChipDrawableSizeChange();
        }
    }

    public void getChipTouchBounds(RectF rectF) {
        calculateChipTouchBounds(getBounds(), rectF);
    }

    public void getCloseIconTouchBounds(RectF rectF) {
        calculateCloseIconTouchBounds(getBounds(), rectF);
    }

    public int getIntrinsicWidth() {
        return Math.min(Math.round((((((this.chipStartPadding + calculateChipIconWidth()) + this.textStartPadding) + getTextWidth()) + this.textEndPadding) + calculateCloseIconWidth()) + this.chipEndPadding), this.maxWidth);
    }

    public int getIntrinsicHeight() {
        return (int) this.chipMinHeight;
    }

    private boolean showsChipIcon() {
        return this.chipIconVisible && this.chipIcon != null;
    }

    private boolean showsCheckedIcon() {
        return this.checkedIconVisible && this.checkedIcon != null && this.currentChecked;
    }

    private boolean showsCloseIcon() {
        return this.closeIconVisible && this.closeIcon != null;
    }

    private boolean canShowCheckedIcon() {
        return this.checkedIconVisible && this.checkedIcon != null && this.checkable;
    }

    float calculateChipIconWidth() {
        if (!showsChipIcon()) {
            if (!showsCheckedIcon()) {
                return 0.0f;
            }
        }
        return (this.iconStartPadding + this.chipIconSize) + this.iconEndPadding;
    }

    private float getTextWidth() {
        if (!this.textWidthDirty) {
            return this.textWidth;
        }
        this.textWidth = calculateTextWidth(this.unicodeWrappedText);
        this.textWidthDirty = false;
        return this.textWidth;
    }

    private float calculateTextWidth(@Nullable CharSequence charSequence) {
        return charSequence == null ? null : this.textPaint.measureText(charSequence, 0, charSequence.length());
    }

    private float calculateCloseIconWidth() {
        return showsCloseIcon() ? (this.closeIconStartPadding + this.closeIconSize) + this.closeIconEndPadding : 0.0f;
    }

    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            if (getAlpha() != 0) {
                int i = 0;
                if (this.alpha < 255) {
                    i = CanvasCompat.saveLayerAlpha(canvas, (float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.alpha);
                }
                drawChipBackground(canvas, bounds);
                drawChipStroke(canvas, bounds);
                drawCompatRipple(canvas, bounds);
                drawChipIcon(canvas, bounds);
                drawCheckedIcon(canvas, bounds);
                if (this.shouldDrawText) {
                    drawText(canvas, bounds);
                }
                drawCloseIcon(canvas, bounds);
                drawDebug(canvas, bounds);
                if (this.alpha < 255) {
                    canvas.restoreToCount(i);
                }
            }
        }
    }

    private void drawChipBackground(@NonNull Canvas canvas, Rect rect) {
        this.chipPaint.setColor(this.currentChipBackgroundColor);
        this.chipPaint.setStyle(Style.FILL);
        this.chipPaint.setColorFilter(getTintColorFilter());
        this.rectF.set(rect);
        rect = this.rectF;
        float f = this.chipCornerRadius;
        canvas.drawRoundRect(rect, f, f, this.chipPaint);
    }

    private void drawChipStroke(@NonNull Canvas canvas, Rect rect) {
        if (this.chipStrokeWidth > 0.0f) {
            this.chipPaint.setColor(this.currentChipStrokeColor);
            this.chipPaint.setStyle(Style.STROKE);
            this.chipPaint.setColorFilter(getTintColorFilter());
            this.rectF.set(((float) rect.left) + (this.chipStrokeWidth / 2.0f), ((float) rect.top) + (this.chipStrokeWidth / 2.0f), ((float) rect.right) - (this.chipStrokeWidth / 2.0f), ((float) rect.bottom) - (this.chipStrokeWidth / 2.0f));
            rect = this.chipCornerRadius - (this.chipStrokeWidth / 2.0f);
            canvas.drawRoundRect(this.rectF, rect, rect, this.chipPaint);
        }
    }

    private void drawCompatRipple(@NonNull Canvas canvas, Rect rect) {
        this.chipPaint.setColor(this.currentCompatRippleColor);
        this.chipPaint.setStyle(Style.FILL);
        this.rectF.set(rect);
        rect = this.rectF;
        float f = this.chipCornerRadius;
        canvas.drawRoundRect(rect, f, f, this.chipPaint);
    }

    private void drawChipIcon(@NonNull Canvas canvas, Rect rect) {
        if (showsChipIcon()) {
            calculateChipIconBounds(rect, this.rectF);
            rect = this.rectF.left;
            float f = this.rectF.top;
            canvas.translate(rect, f);
            this.chipIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.chipIcon.draw(canvas);
            canvas.translate(-rect, -f);
        }
    }

    private void drawCheckedIcon(@NonNull Canvas canvas, Rect rect) {
        if (showsCheckedIcon()) {
            calculateChipIconBounds(rect, this.rectF);
            rect = this.rectF.left;
            float f = this.rectF.top;
            canvas.translate(rect, f);
            this.checkedIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.checkedIcon.draw(canvas);
            canvas.translate(-rect, -f);
        }
    }

    private void drawText(@NonNull Canvas canvas, Rect rect) {
        if (this.unicodeWrappedText != null) {
            Align calculateTextOriginAndAlignment = calculateTextOriginAndAlignment(rect, this.pointF);
            calculateTextBounds(rect, this.rectF);
            if (this.textAppearance != null) {
                this.textPaint.drawableState = getState();
                this.textAppearance.updateDrawState(this.context, this.textPaint, this.fontCallback);
            }
            this.textPaint.setTextAlign(calculateTextOriginAndAlignment);
            int i = 0;
            rect = Math.round(getTextWidth()) > Math.round(this.rectF.width()) ? true : null;
            if (rect != null) {
                i = canvas.save();
                canvas.clipRect(this.rectF);
            }
            CharSequence charSequence = this.unicodeWrappedText;
            CharSequence ellipsize = (rect == null || this.truncateAt == null) ? charSequence : TextUtils.ellipsize(charSequence, this.textPaint, this.rectF.width(), this.truncateAt);
            canvas.drawText(ellipsize, 0, ellipsize.length(), this.pointF.x, this.pointF.y, this.textPaint);
            if (rect != null) {
                canvas.restoreToCount(i);
            }
        }
    }

    private void drawCloseIcon(@NonNull Canvas canvas, Rect rect) {
        if (showsCloseIcon()) {
            calculateCloseIconBounds(rect, this.rectF);
            rect = this.rectF.left;
            float f = this.rectF.top;
            canvas.translate(rect, f);
            this.closeIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.closeIcon.draw(canvas);
            canvas.translate(-rect, -f);
        }
    }

    private void drawDebug(@NonNull Canvas canvas, Rect rect) {
        Paint paint = this.debugPaint;
        if (paint != null) {
            paint.setColor(ColorUtils.setAlphaComponent(ViewCompat.MEASURED_STATE_MASK, 127));
            canvas.drawRect(rect, this.debugPaint);
            if (showsChipIcon() || showsCheckedIcon()) {
                calculateChipIconBounds(rect, this.rectF);
                canvas.drawRect(this.rectF, this.debugPaint);
            }
            if (this.unicodeWrappedText != null) {
                canvas.drawLine((float) rect.left, rect.exactCenterY(), (float) rect.right, rect.exactCenterY(), this.debugPaint);
            }
            if (showsCloseIcon()) {
                calculateCloseIconBounds(rect, this.rectF);
                canvas.drawRect(this.rectF, this.debugPaint);
            }
            this.debugPaint.setColor(ColorUtils.setAlphaComponent(SupportMenu.CATEGORY_MASK, 127));
            calculateChipTouchBounds(rect, this.rectF);
            canvas.drawRect(this.rectF, this.debugPaint);
            this.debugPaint.setColor(ColorUtils.setAlphaComponent(-16711936, 127));
            calculateCloseIconTouchBounds(rect, this.rectF);
            canvas.drawRect(this.rectF, this.debugPaint);
        }
    }

    private void calculateChipIconBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsChipIcon() || showsCheckedIcon()) {
            float f = this.chipStartPadding + this.iconStartPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.left = ((float) rect.left) + f;
                rectF.right = rectF.left + this.chipIconSize;
            } else {
                rectF.right = ((float) rect.right) - f;
                rectF.left = rectF.right - this.chipIconSize;
            }
            rectF.top = rect.exactCenterY() - (this.chipIconSize / 2.0f);
            rectF.bottom = rectF.top + this.chipIconSize;
        }
    }

    Align calculateTextOriginAndAlignment(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Align align = Align.LEFT;
        if (this.unicodeWrappedText != null) {
            float calculateChipIconWidth = (this.chipStartPadding + calculateChipIconWidth()) + this.textStartPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                pointF.x = ((float) rect.left) + calculateChipIconWidth;
                align = Align.LEFT;
            } else {
                pointF.x = ((float) rect.right) - calculateChipIconWidth;
                align = Align.RIGHT;
            }
            pointF.y = ((float) rect.centerY()) - calculateTextCenterFromBaseline();
        }
        return align;
    }

    private float calculateTextCenterFromBaseline() {
        this.textPaint.getFontMetrics(this.fontMetrics);
        return (this.fontMetrics.descent + this.fontMetrics.ascent) / 2.0f;
    }

    private void calculateTextBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.unicodeWrappedText != null) {
            float calculateChipIconWidth = (this.chipStartPadding + calculateChipIconWidth()) + this.textStartPadding;
            float calculateCloseIconWidth = (this.chipEndPadding + calculateCloseIconWidth()) + this.textEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.left = ((float) rect.left) + calculateChipIconWidth;
                rectF.right = ((float) rect.right) - calculateCloseIconWidth;
            } else {
                rectF.left = ((float) rect.left) + calculateCloseIconWidth;
                rectF.right = ((float) rect.right) - calculateChipIconWidth;
            }
            rectF.top = (float) rect.top;
            rectF.bottom = (float) rect.bottom;
        }
    }

    private void calculateCloseIconBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsCloseIcon()) {
            float f = this.chipEndPadding + this.closeIconEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = ((float) rect.right) - f;
                rectF.left = rectF.right - this.closeIconSize;
            } else {
                rectF.left = ((float) rect.left) + f;
                rectF.right = rectF.left + this.closeIconSize;
            }
            rectF.top = rect.exactCenterY() - (this.closeIconSize / 2.0f);
            rectF.bottom = rectF.top + this.closeIconSize;
        }
    }

    private void calculateChipTouchBounds(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (showsCloseIcon()) {
            float f = (((this.chipEndPadding + this.closeIconEndPadding) + this.closeIconSize) + this.closeIconStartPadding) + this.textEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = ((float) rect.right) - f;
            } else {
                rectF.left = ((float) rect.left) + f;
            }
        }
    }

    private void calculateCloseIconTouchBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsCloseIcon()) {
            float f = (((this.chipEndPadding + this.closeIconEndPadding) + this.closeIconSize) + this.closeIconStartPadding) + this.textEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = (float) rect.right;
                rectF.left = rectF.right - f;
            } else {
                rectF.left = (float) rect.left;
                rectF.right = ((float) rect.left) + f;
            }
            rectF.top = (float) rect.top;
            rectF.bottom = (float) rect.bottom;
        }
    }

    public boolean isStateful() {
        if (!(isStateful(this.chipBackgroundColor) || isStateful(this.chipStrokeColor) || ((this.useCompatRipple && isStateful(this.compatRippleColor)) || isStateful(this.textAppearance) || canShowCheckedIcon() || isStateful(this.chipIcon) || isStateful(this.checkedIcon)))) {
            if (!isStateful(this.tint)) {
                return false;
            }
        }
        return true;
    }

    public boolean isCloseIconStateful() {
        return isStateful(this.closeIcon);
    }

    public boolean setCloseIconState(@NonNull int[] iArr) {
        if (!Arrays.equals(this.closeIconStateSet, iArr)) {
            this.closeIconStateSet = iArr;
            if (showsCloseIcon()) {
                return onStateChange(getState(), iArr);
            }
        }
        return null;
    }

    @NonNull
    public int[] getCloseIconState() {
        return this.closeIconStateSet;
    }

    protected boolean onStateChange(int[] iArr) {
        return onStateChange(iArr, getCloseIconState());
    }

    private boolean onStateChange(int[] iArr, int[] iArr2) {
        Object obj;
        boolean onStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList = this.chipBackgroundColor;
        int i = 0;
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, this.currentChipBackgroundColor) : 0;
        if (this.currentChipBackgroundColor != colorForState) {
            this.currentChipBackgroundColor = colorForState;
            onStateChange = true;
        }
        colorStateList = this.chipStrokeColor;
        colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, this.currentChipStrokeColor) : 0;
        if (this.currentChipStrokeColor != colorForState) {
            this.currentChipStrokeColor = colorForState;
            onStateChange = true;
        }
        colorStateList = this.compatRippleColor;
        colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, this.currentCompatRippleColor) : 0;
        if (this.currentCompatRippleColor != colorForState) {
            this.currentCompatRippleColor = colorForState;
            if (this.useCompatRipple) {
                onStateChange = true;
            }
        }
        TextAppearance textAppearance = this.textAppearance;
        colorForState = (textAppearance == null || textAppearance.textColor == null) ? 0 : this.textAppearance.textColor.getColorForState(iArr, this.currentTextColor);
        if (this.currentTextColor != colorForState) {
            this.currentTextColor = colorForState;
            onStateChange = true;
        }
        boolean z = hasState(getState(), 16842912) && this.checkable;
        if (this.currentChecked == z || this.checkedIcon == null) {
            obj = null;
        } else {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.currentChecked = z;
            if (calculateChipIconWidth != calculateChipIconWidth()) {
                onStateChange = true;
                obj = 1;
            } else {
                onStateChange = true;
                obj = null;
            }
        }
        ColorStateList colorStateList2 = this.tint;
        if (colorStateList2 != null) {
            i = colorStateList2.getColorForState(iArr, this.currentTint);
        }
        if (this.currentTint != i) {
            this.currentTint = i;
            this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, this.tintMode);
            onStateChange = true;
        }
        if (isStateful(this.chipIcon)) {
            onStateChange |= this.chipIcon.setState(iArr);
        }
        if (isStateful(this.checkedIcon)) {
            onStateChange |= this.checkedIcon.setState(iArr);
        }
        if (isStateful(this.closeIcon) != null) {
            onStateChange |= this.closeIcon.setState(iArr2);
        }
        if (onStateChange) {
            invalidateSelf();
        }
        if (obj != null) {
            onSizeChange();
        }
        return onStateChange;
    }

    private static boolean isStateful(@Nullable ColorStateList colorStateList) {
        return (colorStateList == null || colorStateList.isStateful() == null) ? null : true;
    }

    private static boolean isStateful(@Nullable Drawable drawable) {
        return (drawable == null || drawable.isStateful() == null) ? null : true;
    }

    private static boolean isStateful(@Nullable TextAppearance textAppearance) {
        return (textAppearance == null || textAppearance.textColor == null || textAppearance.textColor.isStateful() == null) ? null : true;
    }

    @TargetApi(23)
    public boolean onLayoutDirectionChanged(int i) {
        int onLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (showsChipIcon()) {
            onLayoutDirectionChanged |= this.chipIcon.setLayoutDirection(i);
        }
        if (showsCheckedIcon()) {
            onLayoutDirectionChanged |= this.checkedIcon.setLayoutDirection(i);
        }
        if (showsCloseIcon()) {
            onLayoutDirectionChanged |= this.closeIcon.setLayoutDirection(i);
        }
        if (onLayoutDirectionChanged != 0) {
            invalidateSelf();
        }
        return true;
    }

    protected boolean onLevelChange(int i) {
        boolean onLevelChange = super.onLevelChange(i);
        if (showsChipIcon()) {
            onLevelChange |= this.chipIcon.setLevel(i);
        }
        if (showsCheckedIcon()) {
            onLevelChange |= this.checkedIcon.setLevel(i);
        }
        if (showsCloseIcon()) {
            onLevelChange |= this.closeIcon.setLevel(i);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (showsChipIcon()) {
            visible |= this.chipIcon.setVisible(z, z2);
        }
        if (showsCheckedIcon()) {
            visible |= this.checkedIcon.setVisible(z, z2);
        }
        if (showsCloseIcon()) {
            visible |= this.closeIcon.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public void setAlpha(int i) {
        if (this.alpha != i) {
            this.alpha = i;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        if (this.colorFilter != colorFilter) {
            this.colorFilter = colorFilter;
            invalidateSelf();
        }
    }

    @Nullable
    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public void setTintList(@Nullable ColorStateList colorStateList) {
        if (this.tint != colorStateList) {
            this.tint = colorStateList;
            onStateChange(getState());
        }
    }

    public void setTintMode(@NonNull Mode mode) {
        if (this.tintMode != mode) {
            this.tintMode = mode;
            this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, mode);
            invalidateSelf();
        }
    }

    @TargetApi(21)
    public void getOutline(@NonNull Outline outline) {
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.chipCornerRadius);
        } else {
            outline.setRoundRect(bounds, this.chipCornerRadius);
        }
        outline.setAlpha(((float) getAlpha()) / 255.0f);
    }

    public void invalidateDrawable(@NonNull Drawable drawable) {
        drawable = getCallback();
        if (drawable != null) {
            drawable.invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
        drawable = getCallback();
        if (drawable != null) {
            drawable.scheduleDrawable(this, runnable, j);
        }
    }

    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        drawable = getCallback();
        if (drawable != null) {
            drawable.unscheduleDrawable(this, runnable);
        }
    }

    private void unapplyChildDrawable(@Nullable Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    private void applyChildDrawable(@Nullable Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
            DrawableCompat.setLayoutDirection(drawable, DrawableCompat.getLayoutDirection(this));
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == this.closeIcon) {
                if (drawable.isStateful()) {
                    drawable.setState(getCloseIconState());
                }
                DrawableCompat.setTintList(drawable, this.closeIconTint);
            } else if (drawable.isStateful()) {
                drawable.setState(getState());
            }
        }
    }

    @Nullable
    private ColorFilter getTintColorFilter() {
        ColorFilter colorFilter = this.colorFilter;
        return colorFilter != null ? colorFilter : this.tintFilter;
    }

    private void updateCompatRippleColor() {
        this.compatRippleColor = this.useCompatRipple ? RippleUtils.convertToRippleDrawableColor(this.rippleColor) : null;
    }

    private static boolean hasState(@Nullable int[] iArr, @AttrRes int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return 1;
            }
        }
        return false;
    }

    @Nullable
    public ColorStateList getChipBackgroundColor() {
        return this.chipBackgroundColor;
    }

    public void setChipBackgroundColorResource(@ColorRes int i) {
        setChipBackgroundColor(AppCompatResources.getColorStateList(this.context, i));
    }

    public void setChipBackgroundColor(@Nullable ColorStateList colorStateList) {
        if (this.chipBackgroundColor != colorStateList) {
            this.chipBackgroundColor = colorStateList;
            onStateChange(getState());
        }
    }

    public float getChipMinHeight() {
        return this.chipMinHeight;
    }

    public void setChipMinHeightResource(@DimenRes int i) {
        setChipMinHeight(this.context.getResources().getDimension(i));
    }

    public void setChipMinHeight(float f) {
        if (this.chipMinHeight != f) {
            this.chipMinHeight = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getChipCornerRadius() {
        return this.chipCornerRadius;
    }

    public void setChipCornerRadiusResource(@DimenRes int i) {
        setChipCornerRadius(this.context.getResources().getDimension(i));
    }

    public void setChipCornerRadius(float f) {
        if (this.chipCornerRadius != f) {
            this.chipCornerRadius = f;
            invalidateSelf();
        }
    }

    @Nullable
    public ColorStateList getChipStrokeColor() {
        return this.chipStrokeColor;
    }

    public void setChipStrokeColorResource(@ColorRes int i) {
        setChipStrokeColor(AppCompatResources.getColorStateList(this.context, i));
    }

    public void setChipStrokeColor(@Nullable ColorStateList colorStateList) {
        if (this.chipStrokeColor != colorStateList) {
            this.chipStrokeColor = colorStateList;
            onStateChange(getState());
        }
    }

    public float getChipStrokeWidth() {
        return this.chipStrokeWidth;
    }

    public void setChipStrokeWidthResource(@DimenRes int i) {
        setChipStrokeWidth(this.context.getResources().getDimension(i));
    }

    public void setChipStrokeWidth(float f) {
        if (this.chipStrokeWidth != f) {
            this.chipStrokeWidth = f;
            this.chipPaint.setStrokeWidth(f);
            invalidateSelf();
        }
    }

    @Nullable
    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    public void setRippleColorResource(@ColorRes int i) {
        setRippleColor(AppCompatResources.getColorStateList(this.context, i));
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        if (this.rippleColor != colorStateList) {
            this.rippleColor = colorStateList;
            updateCompatRippleColor();
            onStateChange(getState());
        }
    }

    @NonNull
    public CharSequence getText() {
        return this.rawText;
    }

    public void setTextResource(@StringRes int i) {
        setText(this.context.getResources().getString(i));
    }

    public void setText(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (this.rawText != charSequence) {
            this.rawText = charSequence;
            this.unicodeWrappedText = BidiFormatter.getInstance().unicodeWrap(charSequence);
            this.textWidthDirty = true;
            invalidateSelf();
            onSizeChange();
        }
    }

    @Nullable
    public TextAppearance getTextAppearance() {
        return this.textAppearance;
    }

    public void setTextAppearanceResource(@StyleRes int i) {
        setTextAppearance(new TextAppearance(this.context, i));
    }

    public void setTextAppearance(@Nullable TextAppearance textAppearance) {
        if (this.textAppearance != textAppearance) {
            this.textAppearance = textAppearance;
            if (textAppearance != null) {
                textAppearance.updateMeasureState(this.context, this.textPaint, this.fontCallback);
                this.textWidthDirty = true;
            }
            onStateChange(getState());
            onSizeChange();
        }
    }

    public TruncateAt getEllipsize() {
        return this.truncateAt;
    }

    public void setEllipsize(@Nullable TruncateAt truncateAt) {
        this.truncateAt = truncateAt;
    }

    public boolean isChipIconVisible() {
        return this.chipIconVisible;
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return isChipIconVisible();
    }

    public void setChipIconVisible(@BoolRes int i) {
        setChipIconVisible(this.context.getResources().getBoolean(i));
    }

    public void setChipIconVisible(boolean z) {
        if (this.chipIconVisible != z) {
            boolean showsChipIcon = showsChipIcon();
            this.chipIconVisible = z;
            z = showsChipIcon();
            if ((showsChipIcon != z ? 1 : null) != null) {
                if (z) {
                    applyChildDrawable(this.chipIcon);
                } else {
                    unapplyChildDrawable(this.chipIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setChipIconEnabledResource(@BoolRes int i) {
        setChipIconVisible(i);
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Nullable
    public Drawable getChipIcon() {
        Drawable drawable = this.chipIcon;
        return drawable != null ? DrawableCompat.unwrap(drawable) : null;
    }

    public void setChipIconResource(@DrawableRes int i) {
        setChipIcon(AppCompatResources.getDrawable(this.context, i));
    }

    public void setChipIcon(@Nullable Drawable drawable) {
        Drawable chipIcon = getChipIcon();
        if (chipIcon != drawable) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.chipIcon = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            drawable = calculateChipIconWidth();
            unapplyChildDrawable(chipIcon);
            if (showsChipIcon()) {
                applyChildDrawable(this.chipIcon);
            }
            invalidateSelf();
            if (calculateChipIconWidth != drawable) {
                onSizeChange();
            }
        }
    }

    @Nullable
    public ColorStateList getChipIconTint() {
        return this.chipIconTint;
    }

    public void setChipIconTintResource(@ColorRes int i) {
        setChipIconTint(AppCompatResources.getColorStateList(this.context, i));
    }

    public void setChipIconTint(@Nullable ColorStateList colorStateList) {
        if (this.chipIconTint != colorStateList) {
            this.chipIconTint = colorStateList;
            if (showsChipIcon()) {
                DrawableCompat.setTintList(this.chipIcon, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public float getChipIconSize() {
        return this.chipIconSize;
    }

    public void setChipIconSizeResource(@DimenRes int i) {
        setChipIconSize(this.context.getResources().getDimension(i));
    }

    public void setChipIconSize(float f) {
        if (this.chipIconSize != f) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.chipIconSize = f;
            f = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != f) {
                onSizeChange();
            }
        }
    }

    public boolean isCloseIconVisible() {
        return this.closeIconVisible;
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return isCloseIconVisible();
    }

    public void setCloseIconVisible(@BoolRes int i) {
        setCloseIconVisible(this.context.getResources().getBoolean(i));
    }

    public void setCloseIconVisible(boolean z) {
        if (this.closeIconVisible != z) {
            boolean showsCloseIcon = showsCloseIcon();
            this.closeIconVisible = z;
            z = showsCloseIcon();
            if ((showsCloseIcon != z ? 1 : null) != null) {
                if (z) {
                    applyChildDrawable(this.closeIcon);
                } else {
                    unapplyChildDrawable(this.closeIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setCloseIconEnabledResource(@BoolRes int i) {
        setCloseIconVisible(i);
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Nullable
    public Drawable getCloseIcon() {
        Drawable drawable = this.closeIcon;
        return drawable != null ? DrawableCompat.unwrap(drawable) : null;
    }

    public void setCloseIconResource(@DrawableRes int i) {
        setCloseIcon(AppCompatResources.getDrawable(this.context, i));
    }

    public void setCloseIcon(@Nullable Drawable drawable) {
        Drawable closeIcon = getCloseIcon();
        if (closeIcon != drawable) {
            float calculateCloseIconWidth = calculateCloseIconWidth();
            this.closeIcon = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            drawable = calculateCloseIconWidth();
            unapplyChildDrawable(closeIcon);
            if (showsCloseIcon()) {
                applyChildDrawable(this.closeIcon);
            }
            invalidateSelf();
            if (calculateCloseIconWidth != drawable) {
                onSizeChange();
            }
        }
    }

    @Nullable
    public ColorStateList getCloseIconTint() {
        return this.closeIconTint;
    }

    public void setCloseIconTintResource(@ColorRes int i) {
        setCloseIconTint(AppCompatResources.getColorStateList(this.context, i));
    }

    public void setCloseIconTint(@Nullable ColorStateList colorStateList) {
        if (this.closeIconTint != colorStateList) {
            this.closeIconTint = colorStateList;
            if (showsCloseIcon()) {
                DrawableCompat.setTintList(this.closeIcon, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public float getCloseIconSize() {
        return this.closeIconSize;
    }

    public void setCloseIconSizeResource(@DimenRes int i) {
        setCloseIconSize(this.context.getResources().getDimension(i));
    }

    public void setCloseIconSize(float f) {
        if (this.closeIconSize != f) {
            this.closeIconSize = f;
            invalidateSelf();
            if (showsCloseIcon() != null) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconContentDescription(@Nullable CharSequence charSequence) {
        if (this.closeIconContentDescription != charSequence) {
            this.closeIconContentDescription = BidiFormatter.getInstance().unicodeWrap(charSequence);
            invalidateSelf();
        }
    }

    @Nullable
    public CharSequence getCloseIconContentDescription() {
        return this.closeIconContentDescription;
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    public void setCheckableResource(@BoolRes int i) {
        setCheckable(this.context.getResources().getBoolean(i));
    }

    public void setCheckable(boolean z) {
        if (this.checkable != z) {
            this.checkable = z;
            float calculateChipIconWidth = calculateChipIconWidth();
            if (!z && this.currentChecked) {
                this.currentChecked = false;
            }
            z = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != z) {
                onSizeChange();
            }
        }
    }

    public boolean isCheckedIconVisible() {
        return this.checkedIconVisible;
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return isCheckedIconVisible();
    }

    public void setCheckedIconVisible(@BoolRes int i) {
        setCheckedIconVisible(this.context.getResources().getBoolean(i));
    }

    public void setCheckedIconVisible(boolean z) {
        if (this.checkedIconVisible != z) {
            boolean showsCheckedIcon = showsCheckedIcon();
            this.checkedIconVisible = z;
            z = showsCheckedIcon();
            if ((showsCheckedIcon != z ? 1 : null) != null) {
                if (z) {
                    applyChildDrawable(this.checkedIcon);
                } else {
                    unapplyChildDrawable(this.checkedIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setCheckedIconEnabledResource(@BoolRes int i) {
        setCheckedIconVisible(this.context.getResources().getBoolean(i));
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Nullable
    public Drawable getCheckedIcon() {
        return this.checkedIcon;
    }

    public void setCheckedIconResource(@DrawableRes int i) {
        setCheckedIcon(AppCompatResources.getDrawable(this.context, i));
    }

    public void setCheckedIcon(@Nullable Drawable drawable) {
        if (this.checkedIcon != drawable) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.checkedIcon = drawable;
            drawable = calculateChipIconWidth();
            unapplyChildDrawable(this.checkedIcon);
            applyChildDrawable(this.checkedIcon);
            invalidateSelf();
            if (calculateChipIconWidth != drawable) {
                onSizeChange();
            }
        }
    }

    @Nullable
    public MotionSpec getShowMotionSpec() {
        return this.showMotionSpec;
    }

    public void setShowMotionSpecResource(@AnimatorRes int i) {
        setShowMotionSpec(MotionSpec.createFromResource(this.context, i));
    }

    public void setShowMotionSpec(@Nullable MotionSpec motionSpec) {
        this.showMotionSpec = motionSpec;
    }

    @Nullable
    public MotionSpec getHideMotionSpec() {
        return this.hideMotionSpec;
    }

    public void setHideMotionSpecResource(@AnimatorRes int i) {
        setHideMotionSpec(MotionSpec.createFromResource(this.context, i));
    }

    public void setHideMotionSpec(@Nullable MotionSpec motionSpec) {
        this.hideMotionSpec = motionSpec;
    }

    public float getChipStartPadding() {
        return this.chipStartPadding;
    }

    public void setChipStartPaddingResource(@DimenRes int i) {
        setChipStartPadding(this.context.getResources().getDimension(i));
    }

    public void setChipStartPadding(float f) {
        if (this.chipStartPadding != f) {
            this.chipStartPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getIconStartPadding() {
        return this.iconStartPadding;
    }

    public void setIconStartPaddingResource(@DimenRes int i) {
        setIconStartPadding(this.context.getResources().getDimension(i));
    }

    public void setIconStartPadding(float f) {
        if (this.iconStartPadding != f) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.iconStartPadding = f;
            f = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != f) {
                onSizeChange();
            }
        }
    }

    public float getIconEndPadding() {
        return this.iconEndPadding;
    }

    public void setIconEndPaddingResource(@DimenRes int i) {
        setIconEndPadding(this.context.getResources().getDimension(i));
    }

    public void setIconEndPadding(float f) {
        if (this.iconEndPadding != f) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.iconEndPadding = f;
            f = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != f) {
                onSizeChange();
            }
        }
    }

    public float getTextStartPadding() {
        return this.textStartPadding;
    }

    public void setTextStartPaddingResource(@DimenRes int i) {
        setTextStartPadding(this.context.getResources().getDimension(i));
    }

    public void setTextStartPadding(float f) {
        if (this.textStartPadding != f) {
            this.textStartPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getTextEndPadding() {
        return this.textEndPadding;
    }

    public void setTextEndPaddingResource(@DimenRes int i) {
        setTextEndPadding(this.context.getResources().getDimension(i));
    }

    public void setTextEndPadding(float f) {
        if (this.textEndPadding != f) {
            this.textEndPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getCloseIconStartPadding() {
        return this.closeIconStartPadding;
    }

    public void setCloseIconStartPaddingResource(@DimenRes int i) {
        setCloseIconStartPadding(this.context.getResources().getDimension(i));
    }

    public void setCloseIconStartPadding(float f) {
        if (this.closeIconStartPadding != f) {
            this.closeIconStartPadding = f;
            invalidateSelf();
            if (showsCloseIcon() != null) {
                onSizeChange();
            }
        }
    }

    public float getCloseIconEndPadding() {
        return this.closeIconEndPadding;
    }

    public void setCloseIconEndPaddingResource(@DimenRes int i) {
        setCloseIconEndPadding(this.context.getResources().getDimension(i));
    }

    public void setCloseIconEndPadding(float f) {
        if (this.closeIconEndPadding != f) {
            this.closeIconEndPadding = f;
            invalidateSelf();
            if (showsCloseIcon() != null) {
                onSizeChange();
            }
        }
    }

    public float getChipEndPadding() {
        return this.chipEndPadding;
    }

    public void setChipEndPaddingResource(@DimenRes int i) {
        setChipEndPadding(this.context.getResources().getDimension(i));
    }

    public void setChipEndPadding(float f) {
        if (this.chipEndPadding != f) {
            this.chipEndPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    @Px
    public int getMaxWidth() {
        return this.maxWidth;
    }

    public void setMaxWidth(@Px int i) {
        this.maxWidth = i;
    }

    boolean shouldDrawText() {
        return this.shouldDrawText;
    }

    void setShouldDrawText(boolean z) {
        this.shouldDrawText = z;
    }
}
