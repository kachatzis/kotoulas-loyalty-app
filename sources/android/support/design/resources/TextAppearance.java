package android.support.design.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StyleRes;
import android.support.design.C0026R;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TextAppearance {
    private static final String TAG = "TextAppearance";
    private static final int TYPEFACE_MONOSPACE = 3;
    private static final int TYPEFACE_SANS = 1;
    private static final int TYPEFACE_SERIF = 2;
    @Nullable
    private Typeface font;
    @Nullable
    public final String fontFamily;
    @FontRes
    private final int fontFamilyResourceId;
    private boolean fontResolved = false;
    @Nullable
    public final ColorStateList shadowColor;
    public final float shadowDx;
    public final float shadowDy;
    public final float shadowRadius;
    public final boolean textAllCaps;
    @Nullable
    public final ColorStateList textColor;
    @Nullable
    public final ColorStateList textColorHint;
    @Nullable
    public final ColorStateList textColorLink;
    public final float textSize;
    public final int textStyle;
    public final int typeface;

    public TextAppearance(Context context, @StyleRes int i) {
        i = context.obtainStyledAttributes(i, C0026R.styleable.TextAppearance);
        this.textSize = i.getDimension(C0026R.styleable.TextAppearance_android_textSize, 0.0f);
        this.textColor = MaterialResources.getColorStateList(context, i, C0026R.styleable.TextAppearance_android_textColor);
        this.textColorHint = MaterialResources.getColorStateList(context, i, C0026R.styleable.TextAppearance_android_textColorHint);
        this.textColorLink = MaterialResources.getColorStateList(context, i, C0026R.styleable.TextAppearance_android_textColorLink);
        this.textStyle = i.getInt(C0026R.styleable.TextAppearance_android_textStyle, 0);
        this.typeface = i.getInt(C0026R.styleable.TextAppearance_android_typeface, 1);
        int indexWithValue = MaterialResources.getIndexWithValue(i, C0026R.styleable.TextAppearance_fontFamily, C0026R.styleable.TextAppearance_android_fontFamily);
        this.fontFamilyResourceId = i.getResourceId(indexWithValue, 0);
        this.fontFamily = i.getString(indexWithValue);
        this.textAllCaps = i.getBoolean(C0026R.styleable.TextAppearance_textAllCaps, false);
        this.shadowColor = MaterialResources.getColorStateList(context, i, C0026R.styleable.TextAppearance_android_shadowColor);
        this.shadowDx = i.getFloat(C0026R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.shadowDy = i.getFloat(C0026R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.shadowRadius = i.getFloat(C0026R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        i.recycle();
    }

    @android.support.annotation.VisibleForTesting
    @android.support.annotation.NonNull
    public android.graphics.Typeface getFont(android.content.Context r4) {
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
        r3 = this;
        r0 = r3.fontResolved;
        if (r0 == 0) goto L_0x0007;
    L_0x0004:
        r4 = r3.font;
        return r4;
    L_0x0007:
        r0 = r4.isRestricted();
        if (r0 != 0) goto L_0x003d;
    L_0x000d:
        r0 = r3.fontFamilyResourceId;	 Catch:{ UnsupportedOperationException -> 0x003d, UnsupportedOperationException -> 0x003d, Exception -> 0x0024 }
        r4 = android.support.v4.content.res.ResourcesCompat.getFont(r4, r0);	 Catch:{ UnsupportedOperationException -> 0x003d, UnsupportedOperationException -> 0x003d, Exception -> 0x0024 }
        r3.font = r4;	 Catch:{ UnsupportedOperationException -> 0x003d, UnsupportedOperationException -> 0x003d, Exception -> 0x0024 }
        r4 = r3.font;	 Catch:{ UnsupportedOperationException -> 0x003d, UnsupportedOperationException -> 0x003d, Exception -> 0x0024 }
        if (r4 == 0) goto L_0x003d;	 Catch:{ UnsupportedOperationException -> 0x003d, UnsupportedOperationException -> 0x003d, Exception -> 0x0024 }
    L_0x0019:
        r4 = r3.font;	 Catch:{ UnsupportedOperationException -> 0x003d, UnsupportedOperationException -> 0x003d, Exception -> 0x0024 }
        r0 = r3.textStyle;	 Catch:{ UnsupportedOperationException -> 0x003d, UnsupportedOperationException -> 0x003d, Exception -> 0x0024 }
        r4 = android.graphics.Typeface.create(r4, r0);	 Catch:{ UnsupportedOperationException -> 0x003d, UnsupportedOperationException -> 0x003d, Exception -> 0x0024 }
        r3.font = r4;	 Catch:{ UnsupportedOperationException -> 0x003d, UnsupportedOperationException -> 0x003d, Exception -> 0x0024 }
        goto L_0x003d;
    L_0x0024:
        r4 = move-exception;
        r0 = "TextAppearance";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Error loading font ";
        r1.append(r2);
        r2 = r3.fontFamily;
        r1.append(r2);
        r1 = r1.toString();
        android.util.Log.d(r0, r1, r4);
    L_0x003d:
        r3.createFallbackTypeface();
        r4 = 1;
        r3.fontResolved = r4;
        r4 = r3.font;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.resources.TextAppearance.getFont(android.content.Context):android.graphics.Typeface");
    }

    public void getFontAsync(android.content.Context r3, final android.text.TextPaint r4, @android.support.annotation.NonNull final android.support.v4.content.res.ResourcesCompat.FontCallback r5) {
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
        r0 = r2.fontResolved;
        if (r0 == 0) goto L_0x000a;
    L_0x0004:
        r3 = r2.font;
        r2.updateTextPaintMeasureState(r4, r3);
        return;
    L_0x000a:
        r2.createFallbackTypeface();
        r0 = r3.isRestricted();
        if (r0 == 0) goto L_0x001c;
    L_0x0013:
        r3 = 1;
        r2.fontResolved = r3;
        r3 = r2.font;
        r2.updateTextPaintMeasureState(r4, r3);
        return;
    L_0x001c:
        r0 = r2.fontFamilyResourceId;	 Catch:{ UnsupportedOperationException -> 0x0041, UnsupportedOperationException -> 0x0041, Exception -> 0x0028 }
        r1 = new android.support.design.resources.TextAppearance$1;	 Catch:{ UnsupportedOperationException -> 0x0041, UnsupportedOperationException -> 0x0041, Exception -> 0x0028 }
        r1.<init>(r4, r5);	 Catch:{ UnsupportedOperationException -> 0x0041, UnsupportedOperationException -> 0x0041, Exception -> 0x0028 }
        r4 = 0;	 Catch:{ UnsupportedOperationException -> 0x0041, UnsupportedOperationException -> 0x0041, Exception -> 0x0028 }
        android.support.v4.content.res.ResourcesCompat.getFont(r3, r0, r1, r4);	 Catch:{ UnsupportedOperationException -> 0x0041, UnsupportedOperationException -> 0x0041, Exception -> 0x0028 }
        goto L_0x0041;
    L_0x0028:
        r3 = move-exception;
        r4 = "TextAppearance";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r0 = "Error loading font ";
        r5.append(r0);
        r0 = r2.fontFamily;
        r5.append(r0);
        r5 = r5.toString();
        android.util.Log.d(r4, r5, r3);
    L_0x0041:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.resources.TextAppearance.getFontAsync(android.content.Context, android.text.TextPaint, android.support.v4.content.res.ResourcesCompat$FontCallback):void");
    }

    private void createFallbackTypeface() {
        if (this.font == null) {
            this.font = Typeface.create(this.fontFamily, this.textStyle);
        }
        if (this.font == null) {
            switch (this.typeface) {
                case 1:
                    this.font = Typeface.SANS_SERIF;
                    break;
                case 2:
                    this.font = Typeface.SERIF;
                    break;
                case 3:
                    this.font = Typeface.MONOSPACE;
                    break;
                default:
                    this.font = Typeface.DEFAULT;
                    break;
            }
            Typeface typeface = this.font;
            if (typeface != null) {
                this.font = Typeface.create(typeface, this.textStyle);
            }
        }
    }

    public void updateDrawState(Context context, TextPaint textPaint, FontCallback fontCallback) {
        updateMeasureState(context, textPaint, fontCallback);
        context = this.textColor;
        textPaint.setColor(context != null ? context.getColorForState(textPaint.drawableState, this.textColor.getDefaultColor()) : ViewCompat.MEASURED_STATE_MASK);
        context = this.shadowRadius;
        fontCallback = this.shadowDx;
        float f = this.shadowDy;
        ColorStateList colorStateList = this.shadowColor;
        textPaint.setShadowLayer(context, fontCallback, f, colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, this.shadowColor.getDefaultColor()) : 0);
    }

    public void updateMeasureState(Context context, TextPaint textPaint, @Nullable FontCallback fontCallback) {
        if (TextAppearanceConfig.shouldLoadFontSynchronously()) {
            updateTextPaintMeasureState(textPaint, getFont(context));
            return;
        }
        getFontAsync(context, textPaint, fontCallback);
        if (this.fontResolved == null) {
            updateTextPaintMeasureState(textPaint, this.font);
        }
    }

    public void updateTextPaintMeasureState(@NonNull TextPaint textPaint, @NonNull Typeface typeface) {
        textPaint.setTypeface(typeface);
        typeface = (typeface.getStyle() ^ -1) & this.textStyle;
        textPaint.setFakeBoldText((typeface & 1) != 0);
        textPaint.setTextSkewX((typeface & 2) != null ? -1098907648 : null);
        textPaint.setTextSize(this.textSize);
    }
}
