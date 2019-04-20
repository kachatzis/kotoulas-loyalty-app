package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({Scope.LIBRARY_GROUP})
public class PathInterpolatorCompat implements Interpolator {
    public static final double EPSILON = 1.0E-5d;
    public static final int MAX_NUM_POINTS = 3000;
    private static final float PRECISION = 0.002f;
    private float[] mX;
    private float[] mY;

    private void initPath(android.graphics.Path r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:29:0x0114 in {4, 17, 19, 22, 24, 26, 28} preds:[]
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
        r10 = this;
        r0 = new android.graphics.PathMeasure;
        r1 = 0;
        r0.<init>(r11, r1);
        r11 = r0.getLength();
        r2 = 990057071; // 0x3b03126f float:0.002 double:4.89153186E-315;
        r2 = r11 / r2;
        r2 = (int) r2;
        r3 = 1;
        r2 = r2 + r3;
        r4 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r2 = java.lang.Math.min(r4, r2);
        if (r2 <= 0) goto L_0x00fd;
    L_0x001a:
        r4 = new float[r2];
        r10.mX = r4;
        r4 = new float[r2];
        r10.mY = r4;
        r4 = 2;
        r4 = new float[r4];
        r5 = 0;
    L_0x0026:
        if (r5 >= r2) goto L_0x0042;
    L_0x0028:
        r6 = (float) r5;
        r6 = r6 * r11;
        r7 = r2 + -1;
        r7 = (float) r7;
        r6 = r6 / r7;
        r7 = 0;
        r0.getPosTan(r6, r4, r7);
        r6 = r10.mX;
        r7 = r4[r1];
        r6[r5] = r7;
        r6 = r10.mY;
        r7 = r4[r3];
        r6[r5] = r7;
        r5 = r5 + 1;
        goto L_0x0026;
    L_0x0042:
        r11 = r10.mX;
        r11 = r11[r1];
        r11 = java.lang.Math.abs(r11);
        r4 = (double) r11;
        r6 = 4532020583610935537; // 0x3ee4f8b588e368f1 float:-1.3686737E-33 double:1.0E-5;
        r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r11 > 0) goto L_0x00bd;
    L_0x0054:
        r11 = r10.mY;
        r11 = r11[r1];
        r11 = java.lang.Math.abs(r11);
        r4 = (double) r11;
        r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r11 > 0) goto L_0x00bd;
    L_0x0061:
        r11 = r10.mX;
        r4 = r2 + -1;
        r11 = r11[r4];
        r5 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r11 = r11 - r5;
        r11 = java.lang.Math.abs(r11);
        r8 = (double) r11;
        r11 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r11 > 0) goto L_0x00bd;
    L_0x0073:
        r11 = r10.mY;
        r11 = r11[r4];
        r11 = r11 - r5;
        r11 = java.lang.Math.abs(r11);
        r4 = (double) r11;
        r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r11 > 0) goto L_0x00bd;
    L_0x0081:
        r11 = 0;
        r11 = 0;
        r3 = 0;
    L_0x0084:
        if (r1 >= r2) goto L_0x00ae;
    L_0x0086:
        r4 = r10.mX;
        r5 = r11 + 1;
        r11 = r4[r11];
        r3 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1));
        if (r3 < 0) goto L_0x0097;
    L_0x0090:
        r4[r1] = r11;
        r1 = r1 + 1;
        r3 = r11;
        r11 = r5;
        goto L_0x0084;
    L_0x0097:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "The Path cannot loop back on itself, x :";
        r1.append(r2);
        r1.append(r11);
        r11 = r1.toString();
        r0.<init>(r11);
        throw r0;
    L_0x00ae:
        r11 = r0.nextContour();
        if (r11 != 0) goto L_0x00b5;
    L_0x00b4:
        return;
    L_0x00b5:
        r11 = new java.lang.IllegalArgumentException;
        r0 = "The Path should be continuous, can't have 2+ contours";
        r11.<init>(r0);
        throw r11;
    L_0x00bd:
        r11 = new java.lang.IllegalArgumentException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r4 = "The Path must start at (0,0) and end at (1,1) start: ";
        r0.append(r4);
        r4 = r10.mX;
        r4 = r4[r1];
        r0.append(r4);
        r4 = ",";
        r0.append(r4);
        r4 = r10.mY;
        r1 = r4[r1];
        r0.append(r1);
        r1 = " end:";
        r0.append(r1);
        r1 = r10.mX;
        r2 = r2 - r3;
        r1 = r1[r2];
        r0.append(r1);
        r1 = ",";
        r0.append(r1);
        r1 = r10.mY;
        r1 = r1[r2];
        r0.append(r1);
        r0 = r0.toString();
        r11.<init>(r0);
        throw r11;
    L_0x00fd:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "The Path has a invalid length ";
        r1.append(r2);
        r1.append(r11);
        r11 = r1.toString();
        r0.<init>(r11);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.PathInterpolatorCompat.initPath(android.graphics.Path):void");
    }

    public PathInterpolatorCompat(Context context, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        this(context.getResources(), context.getTheme(), attributeSet, xmlPullParser);
    }

    public PathInterpolatorCompat(Resources resources, Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        resources = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_PATH_INTERPOLATOR);
        parseInterpolatorFromTypeArray(resources, xmlPullParser);
        resources.recycle();
    }

    private void parseInterpolatorFromTypeArray(TypedArray typedArray, XmlPullParser xmlPullParser) {
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
            typedArray = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "pathData", 4);
            xmlPullParser = PathParser.createPathFromPathData(typedArray);
            if (xmlPullParser != null) {
                initPath(xmlPullParser);
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("The path is null, which is created from ");
            stringBuilder.append(typedArray);
            throw new InflateException(stringBuilder.toString());
        } else if (!TypedArrayUtils.hasAttribute(xmlPullParser, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        } else if (TypedArrayUtils.hasAttribute(xmlPullParser, "controlY1")) {
            float namedFloat = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlX1", 0, 0.0f);
            float namedFloat2 = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlY1", 1, 0.0f);
            boolean hasAttribute = TypedArrayUtils.hasAttribute(xmlPullParser, "controlX2");
            if (hasAttribute != TypedArrayUtils.hasAttribute(xmlPullParser, "controlY2")) {
                throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
            } else if (hasAttribute) {
                initCubic(namedFloat, namedFloat2, TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlX2", 2, 0.0f), TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlY2", 3, 0.0f));
            } else {
                initQuad(namedFloat, namedFloat2);
            }
        } else {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        }
    }

    private void initQuad(float f, float f2) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f, f2, 1.0f, 1.0f);
        initPath(path);
    }

    private void initCubic(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f, f2, f3, f4, 1.0f, 1.0f);
        initPath(path);
    }

    public float getInterpolation(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int i = 0;
        int length = this.mX.length - 1;
        while (length - i > 1) {
            int i2 = (i + length) / 2;
            if (f < this.mX[i2]) {
                length = i2;
            } else {
                i = i2;
            }
        }
        float[] fArr = this.mX;
        float f2 = fArr[length] - fArr[i];
        if (f2 == 0.0f) {
            return this.mY[i];
        }
        f = (f - fArr[i]) / f2;
        float[] fArr2 = this.mY;
        float f3 = fArr2[i];
        return f3 + (f * (fArr2[length] - f3));
    }
}
