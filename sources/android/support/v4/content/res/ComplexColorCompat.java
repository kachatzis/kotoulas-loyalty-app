package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class ComplexColorCompat {
    private static final String LOG_TAG = "ComplexColorCompat";
    private int mColor;
    private final ColorStateList mColorStateList;
    private final Shader mShader;

    @android.support.annotation.NonNull
    private static android.support.v4.content.res.ComplexColorCompat createFromXml(@android.support.annotation.NonNull android.content.res.Resources r6, @android.support.annotation.ColorRes int r7, @android.support.annotation.Nullable android.content.res.Resources.Theme r8) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:27:0x0078 in {4, 10, 13, 16, 17, 20, 22, 24, 26} preds:[]
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
        r7 = r6.getXml(r7);
        r0 = android.util.Xml.asAttributeSet(r7);
    L_0x0008:
        r1 = r7.next();
        r2 = 1;
        r3 = 2;
        if (r1 == r3) goto L_0x0013;
    L_0x0010:
        if (r1 == r2) goto L_0x0013;
    L_0x0012:
        goto L_0x0008;
    L_0x0013:
        if (r1 != r3) goto L_0x0070;
    L_0x0015:
        r1 = r7.getName();
        r3 = -1;
        r4 = r1.hashCode();
        r5 = 89650992; // 0x557f730 float:1.01546526E-35 double:4.42934753E-316;
        if (r4 == r5) goto L_0x0033;
    L_0x0023:
        r2 = 1191572447; // 0x4705f3df float:34291.87 double:5.887150106E-315;
        if (r4 == r2) goto L_0x0029;
    L_0x0028:
        goto L_0x003c;
    L_0x0029:
        r2 = "selector";
        r2 = r1.equals(r2);
        if (r2 == 0) goto L_0x003c;
    L_0x0031:
        r2 = 0;
        goto L_0x003d;
    L_0x0033:
        r4 = "gradient";
        r4 = r1.equals(r4);
        if (r4 == 0) goto L_0x003c;
    L_0x003b:
        goto L_0x003d;
    L_0x003c:
        r2 = -1;
    L_0x003d:
        switch(r2) {
            case 0: goto L_0x0067;
            case 1: goto L_0x005e;
            default: goto L_0x0040;
        };
    L_0x0040:
        r6 = new org.xmlpull.v1.XmlPullParserException;
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r7 = r7.getPositionDescription();
        r8.append(r7);
        r7 = ": unsupported complex color tag ";
        r8.append(r7);
        r8.append(r1);
        r7 = r8.toString();
        r6.<init>(r7);
        throw r6;
    L_0x005e:
        r6 = android.support.v4.content.res.GradientColorInflaterCompat.createFromXmlInner(r6, r7, r0, r8);
        r6 = from(r6);
        return r6;
    L_0x0067:
        r6 = android.support.v4.content.res.ColorStateListInflaterCompat.createFromXmlInner(r6, r7, r0, r8);
        r6 = from(r6);
        return r6;
    L_0x0070:
        r6 = new org.xmlpull.v1.XmlPullParserException;
        r7 = "No start tag found";
        r6.<init>(r7);
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.ComplexColorCompat.createFromXml(android.content.res.Resources, int, android.content.res.Resources$Theme):android.support.v4.content.res.ComplexColorCompat");
    }

    private ComplexColorCompat(Shader shader, ColorStateList colorStateList, @ColorInt int i) {
        this.mShader = shader;
        this.mColorStateList = colorStateList;
        this.mColor = i;
    }

    static ComplexColorCompat from(@NonNull Shader shader) {
        return new ComplexColorCompat(shader, null, 0);
    }

    static ComplexColorCompat from(@NonNull ColorStateList colorStateList) {
        return new ComplexColorCompat(null, colorStateList, colorStateList.getDefaultColor());
    }

    static ComplexColorCompat from(@ColorInt int i) {
        return new ComplexColorCompat(null, null, i);
    }

    @Nullable
    public Shader getShader() {
        return this.mShader;
    }

    @ColorInt
    public int getColor() {
        return this.mColor;
    }

    public void setColor(@ColorInt int i) {
        this.mColor = i;
    }

    public boolean isGradient() {
        return this.mShader != null;
    }

    public boolean isStateful() {
        if (this.mShader == null) {
            ColorStateList colorStateList = this.mColorStateList;
            if (colorStateList != null && colorStateList.isStateful()) {
                return true;
            }
        }
        return false;
    }

    public boolean onStateChanged(int[] iArr) {
        if (isStateful()) {
            ColorStateList colorStateList = this.mColorStateList;
            iArr = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (iArr != this.mColor) {
                this.mColor = iArr;
                return true;
            }
        }
        return false;
    }

    public boolean willDraw() {
        if (!isGradient()) {
            if (this.mColor == 0) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    public static ComplexColorCompat inflate(@NonNull Resources resources, @ColorRes int i, @Nullable Theme theme) {
        try {
            return createFromXml(resources, i, theme);
        } catch (Resources resources2) {
            Log.e(LOG_TAG, "Failed to inflate ComplexColor.", resources2);
            return null;
        }
    }
}
