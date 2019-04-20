package mehdi.sakout.fancybuttons;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.support.annotation.FontRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

public class FancyButton extends LinearLayout {
    public static final int POSITION_BOTTOM = 4;
    public static final int POSITION_LEFT = 1;
    public static final int POSITION_RIGHT = 2;
    public static final int POSITION_TOP = 3;
    public static final String TAG = "FancyButton";
    private int mBorderColor = 0;
    private int mBorderWidth = 0;
    private Context mContext;
    private int mDefaultBackgroundColor = ViewCompat.MEASURED_STATE_MASK;
    private int mDefaultIconColor = -1;
    private String mDefaultIconFont = "fontawesome.ttf";
    private int mDefaultTextColor = -1;
    private String mDefaultTextFont = "robotoregular.ttf";
    private int mDefaultTextGravity = 17;
    private int mDefaultTextSize = Utils.spToPx(getContext(), 15.0f);
    private int mDisabledBackgroundColor = Color.parseColor("#f6f7f9");
    private int mDisabledBorderColor = Color.parseColor("#dddfe2");
    private int mDisabledTextColor = Color.parseColor("#bec2c9");
    private boolean mEnabled = true;
    private int mFocusBackgroundColor = 0;
    private String mFontIcon = null;
    private int mFontIconSize = Utils.spToPx(getContext(), 15.0f);
    private TextView mFontIconView;
    private boolean mGhost = false;
    private int mIconPaddingBottom = 0;
    private int mIconPaddingLeft = 10;
    private int mIconPaddingRight = 10;
    private int mIconPaddingTop = 0;
    private int mIconPosition = 1;
    private Drawable mIconResource = null;
    private Typeface mIconTypeFace = null;
    private ImageView mIconView;
    private int mRadius = 0;
    private int mRadiusBottomLeft = 0;
    private int mRadiusBottomRight = 0;
    private int mRadiusTopLeft = 0;
    private int mRadiusTopRight = 0;
    private String mText = null;
    private boolean mTextAllCaps = false;
    private int mTextPosition = 1;
    private Typeface mTextTypeFace = null;
    private TextView mTextView;
    private boolean mUseRippleEffect = true;
    private boolean mUseSystemFont = false;
    private int textStyle;

    @TargetApi(21)
    private class CustomOutline extends ViewOutlineProvider {
        int height;
        int width;

        CustomOutline(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        public void getOutline(View view, Outline outline) {
            if (FancyButton.this.mRadius == null) {
                outline.setRect(null, 10, this.width, this.height);
                return;
            }
            outline.setRoundRect(0, 10, this.width, this.height, (float) FancyButton.this.mRadius);
        }
    }

    public FancyButton(Context context) {
        super(context);
        this.mContext = context;
        this.mTextTypeFace = Utils.findFont(this.mContext, this.mDefaultTextFont, null);
        this.mIconTypeFace = Utils.findFont(this.mContext, this.mDefaultIconFont, null);
        initializeFancyButton();
    }

    public FancyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        context = context.obtainStyledAttributes(attributeSet, C0430R.styleable.FancyButtonsAttrs, 0, 0);
        initAttributesArray(context);
        context.recycle();
        initializeFancyButton();
    }

    private void initializeFancyButton() {
        TextView textView;
        ImageView imageView;
        Iterator it;
        initializeButtonContainer();
        this.mTextView = setupTextView();
        this.mIconView = setupIconView();
        this.mFontIconView = setupFontIconView();
        removeAllViews();
        setupBackground();
        ArrayList arrayList = new ArrayList();
        int i = this.mIconPosition;
        if (i != 1) {
            if (i != 3) {
                textView = this.mTextView;
                if (textView != null) {
                    arrayList.add(textView);
                }
                imageView = this.mIconView;
                if (imageView != null) {
                    arrayList.add(imageView);
                }
                textView = this.mFontIconView;
                if (textView != null) {
                    arrayList.add(textView);
                }
                it = arrayList.iterator();
                while (it.hasNext()) {
                    addView((View) it.next());
                }
            }
        }
        imageView = this.mIconView;
        if (imageView != null) {
            arrayList.add(imageView);
        }
        textView = this.mFontIconView;
        if (textView != null) {
            arrayList.add(textView);
        }
        textView = this.mTextView;
        if (textView != null) {
            arrayList.add(textView);
        }
        it = arrayList.iterator();
        while (it.hasNext()) {
            addView((View) it.next());
        }
    }

    private TextView setupTextView() {
        if (this.mText == null) {
            this.mText = "Fancy Button";
        }
        TextView textView = new TextView(this.mContext);
        textView.setText(this.mText);
        textView.setGravity(this.mDefaultTextGravity);
        textView.setTextColor(this.mEnabled ? this.mDefaultTextColor : this.mDisabledTextColor);
        textView.setTextSize((float) Utils.pxToSp(getContext(), (float) this.mDefaultTextSize));
        textView.setLayoutParams(new LayoutParams(-2, -2));
        if (!(isInEditMode() || this.mUseSystemFont)) {
            textView.setTypeface(this.mTextTypeFace, this.textStyle);
        }
        return textView;
    }

    private TextView setupFontIconView() {
        if (this.mFontIcon == null) {
            return null;
        }
        TextView textView = new TextView(this.mContext);
        textView.setTextColor(this.mEnabled ? this.mDefaultIconColor : this.mDisabledTextColor);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.rightMargin = this.mIconPaddingRight;
        layoutParams.leftMargin = this.mIconPaddingLeft;
        layoutParams.topMargin = this.mIconPaddingTop;
        layoutParams.bottomMargin = this.mIconPaddingBottom;
        if (this.mTextView != null) {
            int i = this.mIconPosition;
            if (i != 3) {
                if (i != 4) {
                    textView.setGravity(16);
                    layoutParams.gravity = 16;
                }
            }
            layoutParams.gravity = 17;
            textView.setGravity(17);
        } else {
            layoutParams.gravity = 17;
            textView.setGravity(16);
        }
        textView.setLayoutParams(layoutParams);
        if (isInEditMode()) {
            textView.setTextSize((float) Utils.pxToSp(getContext(), (float) this.mFontIconSize));
            textView.setText("O");
        } else {
            textView.setTextSize((float) Utils.pxToSp(getContext(), (float) this.mFontIconSize));
            textView.setText(this.mFontIcon);
            textView.setTypeface(this.mIconTypeFace);
        }
        return textView;
    }

    private ImageView setupIconView() {
        if (this.mIconResource == null) {
            return null;
        }
        ImageView imageView = new ImageView(this.mContext);
        imageView.setImageDrawable(this.mIconResource);
        imageView.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight, this.mIconPaddingBottom);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        if (this.mTextView != null) {
            int i = this.mIconPosition;
            if (i != 3) {
                if (i != 4) {
                    layoutParams.gravity = 8388611;
                    layoutParams.rightMargin = 10;
                    layoutParams.leftMargin = 10;
                }
            }
            layoutParams.gravity = 17;
            layoutParams.rightMargin = 10;
            layoutParams.leftMargin = 10;
        } else {
            layoutParams.gravity = 16;
        }
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    private void initAttributesArray(android.content.res.TypedArray r7) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r6 = this;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_defaultColor;
        r1 = r6.mDefaultBackgroundColor;
        r0 = r7.getColor(r0, r1);
        r6.mDefaultBackgroundColor = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_focusColor;
        r1 = r6.mFocusBackgroundColor;
        r0 = r7.getColor(r0, r1);
        r6.mFocusBackgroundColor = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_disabledColor;
        r1 = r6.mDisabledBackgroundColor;
        r0 = r7.getColor(r0, r1);
        r6.mDisabledBackgroundColor = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_android_enabled;
        r1 = 1;
        r0 = r7.getBoolean(r0, r1);
        r6.mEnabled = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_disabledTextColor;
        r1 = r6.mDisabledTextColor;
        r0 = r7.getColor(r0, r1);
        r6.mDisabledTextColor = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_disabledBorderColor;
        r1 = r6.mDisabledBorderColor;
        r0 = r7.getColor(r0, r1);
        r6.mDisabledBorderColor = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_textColor;
        r1 = r6.mDefaultTextColor;
        r0 = r7.getColor(r0, r1);
        r6.mDefaultTextColor = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_iconColor;
        r1 = r6.mDefaultTextColor;
        r0 = r7.getColor(r0, r1);
        r6.mDefaultIconColor = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_textSize;
        r1 = r6.mDefaultTextSize;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mDefaultTextSize = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_android_textSize;
        r1 = r6.mDefaultTextSize;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mDefaultTextSize = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_textGravity;
        r1 = r6.mDefaultTextGravity;
        r0 = r7.getInt(r0, r1);
        r6.mDefaultTextGravity = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_borderColor;
        r1 = r6.mBorderColor;
        r0 = r7.getColor(r0, r1);
        r6.mBorderColor = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_borderWidth;
        r1 = r6.mBorderWidth;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mBorderWidth = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_radius;
        r1 = r6.mRadius;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mRadius = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_radiusTopLeft;
        r1 = r6.mRadius;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mRadiusTopLeft = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_radiusTopRight;
        r1 = r6.mRadius;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mRadiusTopRight = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_radiusBottomLeft;
        r1 = r6.mRadius;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mRadiusBottomLeft = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_radiusBottomRight;
        r1 = r6.mRadius;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mRadiusBottomRight = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_fontIconSize;
        r1 = r6.mFontIconSize;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mFontIconSize = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_iconPaddingLeft;
        r1 = r6.mIconPaddingLeft;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mIconPaddingLeft = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_iconPaddingRight;
        r1 = r6.mIconPaddingRight;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mIconPaddingRight = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_iconPaddingTop;
        r1 = r6.mIconPaddingTop;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mIconPaddingTop = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_iconPaddingBottom;
        r1 = r6.mIconPaddingBottom;
        r1 = (float) r1;
        r0 = r7.getDimension(r0, r1);
        r0 = (int) r0;
        r6.mIconPaddingBottom = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_textAllCaps;
        r1 = 0;
        r0 = r7.getBoolean(r0, r1);
        r6.mTextAllCaps = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_android_textAllCaps;
        r0 = r7.getBoolean(r0, r1);
        r6.mTextAllCaps = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_ghost;
        r2 = r6.mGhost;
        r0 = r7.getBoolean(r0, r2);
        r6.mGhost = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_useSystemFont;
        r2 = r6.mUseSystemFont;
        r0 = r7.getBoolean(r0, r2);
        r6.mUseSystemFont = r0;
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_text;
        r0 = r7.getString(r0);
        if (r0 != 0) goto L_0x0132;
    L_0x012c:
        r0 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_android_text;
        r0 = r7.getString(r0);
    L_0x0132:
        r2 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_iconPosition;
        r3 = r6.mIconPosition;
        r2 = r7.getInt(r2, r3);
        r6.mIconPosition = r2;
        r2 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_android_textStyle;
        r1 = r7.getInt(r2, r1);
        r6.textStyle = r1;
        r1 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_fontIconResource;
        r1 = r7.getString(r1);
        r2 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_iconFont;
        r2 = r7.getString(r2);
        r3 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_textFont;
        r3 = r7.getString(r3);
        r4 = 0;
        r5 = mehdi.sakout.fancybuttons.C0430R.styleable.FancyButtonsAttrs_fb_iconResource;	 Catch:{ Exception -> 0x0160 }
        r5 = r7.getDrawable(r5);	 Catch:{ Exception -> 0x0160 }
        r6.mIconResource = r5;	 Catch:{ Exception -> 0x0160 }
        goto L_0x0162;
    L_0x0160:
        r6.mIconResource = r4;
    L_0x0162:
        if (r1 == 0) goto L_0x0166;
    L_0x0164:
        r6.mFontIcon = r1;
    L_0x0166:
        if (r0 == 0) goto L_0x0172;
    L_0x0168:
        r1 = r6.mTextAllCaps;
        if (r1 == 0) goto L_0x0170;
    L_0x016c:
        r0 = r0.toUpperCase();
    L_0x0170:
        r6.mText = r0;
    L_0x0172:
        r0 = r6.isInEditMode();
        if (r0 != 0) goto L_0x01ab;
    L_0x0178:
        if (r2 == 0) goto L_0x0183;
    L_0x017a:
        r0 = r6.mContext;
        r1 = r6.mDefaultIconFont;
        r0 = mehdi.sakout.fancybuttons.Utils.findFont(r0, r2, r1);
        goto L_0x018b;
    L_0x0183:
        r0 = r6.mContext;
        r1 = r6.mDefaultIconFont;
        r0 = mehdi.sakout.fancybuttons.Utils.findFont(r0, r1, r4);
    L_0x018b:
        r6.mIconTypeFace = r0;
        r7 = r6.getTypeface(r7);
        if (r7 == 0) goto L_0x0196;
    L_0x0193:
        r6.mTextTypeFace = r7;
        goto L_0x01ab;
    L_0x0196:
        if (r3 == 0) goto L_0x01a1;
    L_0x0198:
        r7 = r6.mContext;
        r0 = r6.mDefaultTextFont;
        r7 = mehdi.sakout.fancybuttons.Utils.findFont(r7, r3, r0);
        goto L_0x01a9;
    L_0x01a1:
        r7 = r6.mContext;
        r0 = r6.mDefaultTextFont;
        r7 = mehdi.sakout.fancybuttons.Utils.findFont(r7, r0, r4);
    L_0x01a9:
        r6.mTextTypeFace = r7;
    L_0x01ab:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: mehdi.sakout.fancybuttons.FancyButton.initAttributesArray(android.content.res.TypedArray):void");
    }

    private Typeface getTypeface(TypedArray typedArray) {
        if (typedArray.hasValue(C0430R.styleable.FancyButtonsAttrs_android_fontFamily)) {
            int resourceId = typedArray.getResourceId(C0430R.styleable.FancyButtonsAttrs_android_fontFamily, 0);
            if (resourceId != 0) {
                return ResourcesCompat.getFont(getContext(), resourceId);
            }
        }
        if (typedArray.hasValue(C0430R.styleable.FancyButtonsAttrs_fb_textFontRes)) {
            typedArray = typedArray.getResourceId(C0430R.styleable.FancyButtonsAttrs_fb_textFontRes, 0);
            if (typedArray != null) {
                return ResourcesCompat.getFont(getContext(), typedArray);
            }
        }
        return null;
    }

    @TargetApi(21)
    private Drawable getRippleDrawable(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        if (this.mEnabled) {
            return new RippleDrawable(ColorStateList.valueOf(this.mFocusBackgroundColor), drawable, drawable2);
        }
        return drawable3;
    }

    private void applyRadius(GradientDrawable gradientDrawable) {
        int i = this.mRadius;
        if (i > 0) {
            gradientDrawable.setCornerRadius((float) i);
            return;
        }
        r0 = new float[8];
        int i2 = this.mRadiusTopLeft;
        r0[0] = (float) i2;
        r0[1] = (float) i2;
        i2 = this.mRadiusTopRight;
        r0[2] = (float) i2;
        r0[3] = (float) i2;
        i2 = this.mRadiusBottomRight;
        r0[4] = (float) i2;
        r0[5] = (float) i2;
        i2 = this.mRadiusBottomLeft;
        r0[6] = (float) i2;
        r0[7] = (float) i2;
        gradientDrawable.setCornerRadii(r0);
    }

    @SuppressLint({"NewApi"})
    private void setupBackground() {
        Drawable gradientDrawable = new GradientDrawable();
        applyRadius(gradientDrawable);
        if (this.mGhost) {
            gradientDrawable.setColor(getResources().getColor(17170445));
        } else {
            gradientDrawable.setColor(this.mDefaultBackgroundColor);
        }
        Drawable gradientDrawable2 = new GradientDrawable();
        applyRadius(gradientDrawable2);
        gradientDrawable2.setColor(this.mFocusBackgroundColor);
        Drawable gradientDrawable3 = new GradientDrawable();
        applyRadius(gradientDrawable3);
        gradientDrawable3.setColor(this.mDisabledBackgroundColor);
        gradientDrawable3.setStroke(this.mBorderWidth, this.mDisabledBorderColor);
        int i = this.mBorderColor;
        if (i != 0) {
            gradientDrawable.setStroke(this.mBorderWidth, i);
        }
        if (!this.mEnabled) {
            gradientDrawable.setStroke(this.mBorderWidth, this.mDisabledBorderColor);
            if (this.mGhost) {
                gradientDrawable3.setColor(getResources().getColor(17170445));
            }
        }
        if (!this.mUseRippleEffect || VERSION.SDK_INT < 21) {
            gradientDrawable2 = new StateListDrawable();
            Drawable gradientDrawable4 = new GradientDrawable();
            applyRadius(gradientDrawable4);
            if (this.mGhost) {
                gradientDrawable4.setColor(getResources().getColor(17170445));
            } else {
                gradientDrawable4.setColor(this.mFocusBackgroundColor);
            }
            int i2 = this.mBorderColor;
            if (i2 != 0) {
                if (this.mGhost) {
                    gradientDrawable4.setStroke(this.mBorderWidth, this.mFocusBackgroundColor);
                } else {
                    gradientDrawable4.setStroke(this.mBorderWidth, i2);
                }
            }
            if (!this.mEnabled) {
                if (this.mGhost) {
                    gradientDrawable4.setStroke(this.mBorderWidth, this.mDisabledBorderColor);
                } else {
                    gradientDrawable4.setStroke(this.mBorderWidth, this.mDisabledBorderColor);
                }
            }
            if (this.mFocusBackgroundColor != 0) {
                gradientDrawable2.addState(new int[]{16842919}, gradientDrawable4);
                gradientDrawable2.addState(new int[]{16842908}, gradientDrawable4);
                gradientDrawable2.addState(new int[]{-16842910}, gradientDrawable3);
            }
            gradientDrawable2.addState(new int[0], gradientDrawable);
            if (VERSION.SDK_INT < 16) {
                setBackgroundDrawable(gradientDrawable2);
                return;
            } else {
                setBackground(gradientDrawable2);
                return;
            }
        }
        setBackground(getRippleDrawable(gradientDrawable, gradientDrawable2, gradientDrawable3));
    }

    private void initializeButtonContainer() {
        int i = this.mIconPosition;
        if (i != 3) {
            if (i != 4) {
                setOrientation(0);
                if (getLayoutParams() == null) {
                    setLayoutParams(new LayoutParams(-2, -2));
                }
                setGravity(17);
                if (this.mIconResource == null && this.mFontIcon == null && getPaddingLeft() == 0 && getPaddingRight() == 0 && getPaddingTop() == 0 && getPaddingBottom() == 0) {
                    setPadding(20, 0, 20, 0);
                    return;
                }
                return;
            }
        }
        setOrientation(1);
        if (getLayoutParams() == null) {
            setLayoutParams(new LayoutParams(-2, -2));
        }
        setGravity(17);
        if (this.mIconResource == null) {
        }
    }

    public void setText(String str) {
        if (this.mTextAllCaps) {
            str = str.toUpperCase();
        }
        this.mText = str;
        TextView textView = this.mTextView;
        if (textView == null) {
            initializeFancyButton();
        } else {
            textView.setText(str);
        }
    }

    public void setTextAllCaps(boolean z) {
        this.mTextAllCaps = z;
        setText(this.mText);
    }

    public void setTextColor(int i) {
        this.mDefaultTextColor = i;
        TextView textView = this.mTextView;
        if (textView == null) {
            initializeFancyButton();
        } else {
            textView.setTextColor(i);
        }
    }

    public void setIconColor(int i) {
        TextView textView = this.mFontIconView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public void setBackgroundColor(int i) {
        this.mDefaultBackgroundColor = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setFocusBackgroundColor(int i) {
        this.mFocusBackgroundColor = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setDisableBackgroundColor(int i) {
        this.mDisabledBackgroundColor = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setDisableTextColor(int i) {
        this.mDisabledTextColor = i;
        TextView textView = this.mTextView;
        if (textView == null) {
            initializeFancyButton();
        } else if (!this.mEnabled) {
            textView.setTextColor(i);
        }
    }

    public void setDisableBorderColor(int i) {
        this.mDisabledBorderColor = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setTextSize(int i) {
        i = (float) i;
        this.mDefaultTextSize = Utils.spToPx(getContext(), i);
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setTextSize(i);
        }
    }

    public void setTextGravity(int i) {
        this.mDefaultTextGravity = i;
        if (this.mTextView != null) {
            setGravity(i);
        }
    }

    public void setIconPadding(int i, int i2, int i3, int i4) {
        this.mIconPaddingLeft = i;
        this.mIconPaddingTop = i2;
        this.mIconPaddingRight = i3;
        this.mIconPaddingBottom = i4;
        i = this.mIconView;
        if (i != 0) {
            i.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight, this.mIconPaddingBottom);
        }
        i = this.mFontIconView;
        if (i != 0) {
            i.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight, this.mIconPaddingBottom);
        }
    }

    public void setIconResource(int i) {
        this.mIconResource = this.mContext.getResources().getDrawable(i);
        i = this.mIconView;
        if (i != 0) {
            if (this.mFontIconView == null) {
                i.setImageDrawable(this.mIconResource);
                return;
            }
        }
        this.mFontIconView = 0;
        initializeFancyButton();
    }

    public void setIconResource(Drawable drawable) {
        this.mIconResource = drawable;
        drawable = this.mIconView;
        if (drawable != null) {
            if (this.mFontIconView == null) {
                drawable.setImageDrawable(this.mIconResource);
                return;
            }
        }
        this.mFontIconView = null;
        initializeFancyButton();
    }

    public void setIconResource(String str) {
        this.mFontIcon = str;
        TextView textView = this.mFontIconView;
        if (textView == null) {
            this.mIconView = null;
            initializeFancyButton();
            return;
        }
        textView.setText(str);
    }

    public void setFontIconSize(int i) {
        i = (float) i;
        this.mFontIconSize = Utils.spToPx(getContext(), i);
        TextView textView = this.mFontIconView;
        if (textView != null) {
            textView.setTextSize(i);
        }
    }

    public void setIconPosition(int i) {
        if (i <= 0 || i >= 5) {
            this.mIconPosition = 1;
        } else {
            this.mIconPosition = i;
        }
        initializeFancyButton();
    }

    public void setBorderColor(int i) {
        this.mBorderColor = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setBorderWidth(int i) {
        this.mBorderWidth = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setRadius(int i) {
        this.mRadius = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setRadius(int[] iArr) {
        this.mRadiusTopLeft = iArr[0];
        this.mRadiusTopRight = iArr[1];
        this.mRadiusBottomLeft = iArr[2];
        this.mRadiusBottomRight = iArr[3];
        if (this.mIconView != null || this.mFontIconView != null || this.mTextView != null) {
            setupBackground();
        }
    }

    public void setCustomTextFont(String str) {
        this.mTextTypeFace = Utils.findFont(this.mContext, str, this.mDefaultTextFont);
        str = this.mTextView;
        if (str == null) {
            initializeFancyButton();
        } else {
            str.setTypeface(this.mTextTypeFace, this.textStyle);
        }
    }

    public void setCustomTextFont(@FontRes int i) {
        this.mTextTypeFace = ResourcesCompat.getFont(getContext(), i);
        i = this.mTextView;
        if (i == 0) {
            initializeFancyButton();
        } else {
            i.setTypeface(this.mTextTypeFace, this.textStyle);
        }
    }

    public void setCustomIconFont(String str) {
        this.mIconTypeFace = Utils.findFont(this.mContext, str, this.mDefaultIconFont);
        str = this.mFontIconView;
        if (str == null) {
            initializeFancyButton();
        } else {
            str.setTypeface(this.mIconTypeFace);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mEnabled = z;
        initializeFancyButton();
    }

    public void setGhost(boolean z) {
        this.mGhost = z;
        if (this.mIconView || this.mFontIconView || this.mTextView) {
            setupBackground();
        }
    }

    public void setUsingSystemFont(boolean z) {
        this.mUseSystemFont = z;
    }

    public CharSequence getText() {
        TextView textView = this.mTextView;
        return textView != null ? textView.getText() : "";
    }

    public TextView getTextViewObject() {
        return this.mTextView;
    }

    public TextView getIconFontObject() {
        return this.mFontIconView;
    }

    public ImageView getIconImageObject() {
        return this.mIconView;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (VERSION.SDK_INT >= 21) {
            setOutlineProvider(new CustomOutline(i, i2));
        }
    }
}
