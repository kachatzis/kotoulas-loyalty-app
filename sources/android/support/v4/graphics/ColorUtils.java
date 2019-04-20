package android.support.v4.graphics;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.ViewCompat;

public final class ColorUtils {
    private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
    private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
    private static final ThreadLocal<double[]> TEMP_ARRAY = new ThreadLocal();
    private static final double XYZ_EPSILON = 0.008856d;
    private static final double XYZ_KAPPA = 903.3d;
    private static final double XYZ_WHITE_REFERENCE_X = 95.047d;
    private static final double XYZ_WHITE_REFERENCE_Y = 100.0d;
    private static final double XYZ_WHITE_REFERENCE_Z = 108.883d;

    public static int calculateMinimumAlpha(@android.support.annotation.ColorInt int r8, @android.support.annotation.ColorInt int r9, float r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:19:0x0054 in {5, 13, 14, 15, 16, 18} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
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
        r0 = android.graphics.Color.alpha(r9);
        r1 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r0 != r1) goto L_0x0039;
    L_0x0008:
        r0 = setAlphaComponent(r8, r1);
        r2 = calculateContrast(r0, r9);
        r4 = (double) r10;
        r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r10 >= 0) goto L_0x0017;
    L_0x0015:
        r8 = -1;
        return r8;
    L_0x0017:
        r10 = 0;
        r0 = 0;
    L_0x0019:
        r2 = 10;
        if (r10 > r2) goto L_0x0038;
    L_0x001d:
        r2 = r1 - r0;
        r3 = 1;
        if (r2 <= r3) goto L_0x0038;
    L_0x0022:
        r2 = r0 + r1;
        r2 = r2 / 2;
        r3 = setAlphaComponent(r8, r2);
        r6 = calculateContrast(r3, r9);
        r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r3 >= 0) goto L_0x0034;
    L_0x0032:
        r0 = r2;
        goto L_0x0035;
    L_0x0034:
        r1 = r2;
    L_0x0035:
        r10 = r10 + 1;
        goto L_0x0019;
    L_0x0038:
        return r1;
    L_0x0039:
        r8 = new java.lang.IllegalArgumentException;
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r0 = "background can not be translucent: #";
        r10.append(r0);
        r9 = java.lang.Integer.toHexString(r9);
        r10.append(r9);
        r9 = r10.toString();
        r8.<init>(r9);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.ColorUtils.calculateMinimumAlpha(int, int, float):int");
    }

    @android.support.annotation.RequiresApi(26)
    @android.support.annotation.NonNull
    public static android.graphics.Color compositeColors(@android.support.annotation.NonNull android.graphics.Color r7, @android.support.annotation.NonNull android.graphics.Color r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x0097 in {4, 5, 8, 11, 13, 15} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
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
        r0 = r7.getModel();
        r1 = r8.getModel();
        r0 = java.util.Objects.equals(r0, r1);
        if (r0 == 0) goto L_0x006b;
    L_0x000e:
        r0 = r8.getColorSpace();
        r1 = r7.getColorSpace();
        r0 = java.util.Objects.equals(r0, r1);
        if (r0 == 0) goto L_0x001d;
    L_0x001c:
        goto L_0x0025;
    L_0x001d:
        r0 = r8.getColorSpace();
        r7 = r7.convert(r0);
    L_0x0025:
        r0 = r7.getComponents();
        r1 = r8.getComponents();
        r7 = r7.alpha();
        r2 = r8.alpha();
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = r3 - r7;
        r2 = r2 * r3;
        r3 = r8.getComponentCount();
        r3 = r3 + -1;
        r4 = r7 + r2;
        r1[r3] = r4;
        r4 = r1[r3];
        r5 = 0;
        r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r4 <= 0) goto L_0x0051;
    L_0x004b:
        r4 = r1[r3];
        r7 = r7 / r4;
        r4 = r1[r3];
        r2 = r2 / r4;
    L_0x0051:
        r4 = 0;
    L_0x0052:
        if (r4 >= r3) goto L_0x0062;
    L_0x0054:
        r5 = r0[r4];
        r5 = r5 * r7;
        r6 = r1[r4];
        r6 = r6 * r2;
        r5 = r5 + r6;
        r1[r4] = r5;
        r4 = r4 + 1;
        goto L_0x0052;
    L_0x0062:
        r7 = r8.getColorSpace();
        r7 = android.graphics.Color.valueOf(r1, r7);
        return r7;
    L_0x006b:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Color models must match (";
        r1.append(r2);
        r7 = r7.getModel();
        r1.append(r7);
        r7 = " vs. ";
        r1.append(r7);
        r7 = r8.getModel();
        r1.append(r7);
        r7 = ")";
        r1.append(r7);
        r7 = r1.toString();
        r0.<init>(r7);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.ColorUtils.compositeColors(android.graphics.Color, android.graphics.Color):android.graphics.Color");
    }

    private static float constrain(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    private static int constrain(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    private ColorUtils() {
    }

    public static int compositeColors(@ColorInt int i, @ColorInt int i2) {
        int alpha = Color.alpha(i2);
        int alpha2 = Color.alpha(i);
        int compositeAlpha = compositeAlpha(alpha2, alpha);
        return Color.argb(compositeAlpha, compositeComponent(Color.red(i), alpha2, Color.red(i2), alpha, compositeAlpha), compositeComponent(Color.green(i), alpha2, Color.green(i2), alpha, compositeAlpha), compositeComponent(Color.blue(i), alpha2, Color.blue(i2), alpha, compositeAlpha));
    }

    private static int compositeAlpha(int i, int i2) {
        return 255 - (((255 - i2) * (255 - i)) / 255);
    }

    private static int compositeComponent(int i, int i2, int i3, int i4, int i5) {
        return i5 == 0 ? 0 : (((i * 255) * i2) + ((i3 * i4) * (255 - i2))) / (i5 * 255);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public static double calculateLuminance(@ColorInt int i) {
        double[] tempDouble3Array = getTempDouble3Array();
        colorToXYZ(i, tempDouble3Array);
        return tempDouble3Array[1] / XYZ_WHITE_REFERENCE_Y;
    }

    public static double calculateContrast(@ColorInt int i, @ColorInt int i2) {
        if (Color.alpha(i2) == 255) {
            if (Color.alpha(i) < 255) {
                i = compositeColors(i, i2);
            }
            double calculateLuminance = calculateLuminance(i) + 0.05d;
            i = calculateLuminance(i2) + -1717986918;
            return Math.max(calculateLuminance, i) / Math.min(calculateLuminance, i);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("background can not be translucent: #");
        stringBuilder.append(Integer.toHexString(i2));
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public static void RGBToHSL(@IntRange(from = 0, to = 255) int i, @IntRange(from = 0, to = 255) int i2, @IntRange(from = 0, to = 255) int i3, @NonNull float[] fArr) {
        float f;
        i = ((float) i) / 1132396544;
        i2 = ((float) i2) / 1132396544;
        i3 = ((float) i3) / 1132396544;
        float max = Math.max(i, Math.max(i2, i3));
        float min = Math.min(i, Math.min(i2, i3));
        float f2 = max - min;
        float f3 = (max + min) / 2.0f;
        if (max == min) {
            i = 0;
            f = 0;
        } else {
            i = max == i ? ((i2 - i3) / f2) % 1086324736 : max == i2 ? ((i3 - i) / f2) + 1073741824 : ((i - i2) / f2) + 1082130432;
            f = f2 / (1065353216 - Math.abs((2.0f * f3) - 1.0f));
        }
        float f4 = (i * 1114636288) % 1135869952;
        if (f4 < 0) {
            f4 += 1135869952;
        }
        fArr[0] = constrain(f4, 0.0f, 360.0f);
        fArr[1] = constrain(f, 0.0f, 1.0f);
        fArr[2] = constrain(f3, 0.0f, 1.0f);
    }

    public static void colorToHSL(@ColorInt int i, @NonNull float[] fArr) {
        RGBToHSL(Color.red(i), Color.green(i), Color.blue(i), fArr);
    }

    @ColorInt
    public static int HSLToColor(@NonNull float[] fArr) {
        int round;
        int round2;
        int round3;
        float f = fArr[0];
        float f2 = fArr[1];
        fArr = fArr[2];
        float abs = (1.0f - Math.abs((fArr * 2.0f) - 1.0f)) * f2;
        fArr -= 0.5f * abs;
        float abs2 = (1.0f - Math.abs(((f / 60.0f) % 2.0f) - 1.0f)) * abs;
        switch (((int) f) / 60) {
            case 0:
                round = Math.round((abs + fArr) * 255.0f);
                round2 = Math.round((abs2 + fArr) * 255.0f);
                round3 = Math.round(fArr * 1132396544);
                break;
            case 1:
                round = Math.round((abs2 + fArr) * 255.0f);
                round2 = Math.round((abs + fArr) * 255.0f);
                round3 = Math.round(fArr * 1132396544);
                break;
            case 2:
                round = Math.round(fArr * 255.0f);
                round2 = Math.round((abs + fArr) * 255.0f);
                round3 = Math.round((abs2 + fArr) * 255.0f);
                break;
            case 3:
                round = Math.round(fArr * 255.0f);
                round2 = Math.round((abs2 + fArr) * 255.0f);
                round3 = Math.round((abs + fArr) * 255.0f);
                break;
            case 4:
                round = Math.round((abs2 + fArr) * 255.0f);
                round2 = Math.round(fArr * 255.0f);
                round3 = Math.round((abs + fArr) * 255.0f);
                break;
            case 5:
            case 6:
                round = Math.round((abs + fArr) * 255.0f);
                round2 = Math.round(fArr * 255.0f);
                round3 = Math.round((abs2 + fArr) * 255.0f);
                break;
            default:
                round3 = null;
                round = 0;
                round2 = 0;
                break;
        }
        return Color.rgb(constrain(round, 0, 255), constrain(round2, 0, 255), constrain(round3, 0, 255));
    }

    @ColorInt
    public static int setAlphaComponent(@ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        if (i2 >= 0 && i2 <= 255) {
            return (i & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
        }
        throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }

    public static void colorToLAB(@ColorInt int i, @NonNull double[] dArr) {
        RGBToLAB(Color.red(i), Color.green(i), Color.blue(i), dArr);
    }

    public static void RGBToLAB(@IntRange(from = 0, to = 255) int i, @IntRange(from = 0, to = 255) int i2, @IntRange(from = 0, to = 255) int i3, @NonNull double[] dArr) {
        RGBToXYZ(i, i2, i3, dArr);
        XYZToLAB(dArr[0], dArr[1], dArr[2], dArr);
    }

    public static void colorToXYZ(@ColorInt int i, @NonNull double[] dArr) {
        RGBToXYZ(Color.red(i), Color.green(i), Color.blue(i), dArr);
    }

    public static void RGBToXYZ(@IntRange(from = 0, to = 255) int i, @IntRange(from = 0, to = 255) int i2, @IntRange(from = 0, to = 255) int i3, @NonNull double[] dArr) {
        double[] dArr2 = dArr;
        if (dArr2.length == 3) {
            double d = (double) i;
            Double.isNaN(d);
            d /= 255.0d;
            if (d < 0.04045d) {
                d /= 12.92d;
            } else {
                d = Math.pow((d + 0.055d) / 1.055d, 2.4d);
            }
            double d2 = d;
            d = (double) i2;
            Double.isNaN(d);
            d /= 255.0d;
            if (d < 0.04045d) {
                d /= 12.92d;
            } else {
                d = Math.pow((d + 0.055d) / 1.055d, 2.4d);
            }
            double d3 = d;
            d = (double) i3;
            Double.isNaN(d);
            d /= 255.0d;
            if (d < 0.04045d) {
                d /= 12.92d;
            } else {
                d = Math.pow((d + 0.055d) / 1.055d, 2.4d);
            }
            dArr2[0] = (((0.4124d * d2) + (0.3576d * d3)) + (0.1805d * d)) * XYZ_WHITE_REFERENCE_Y;
            dArr2[1] = (((0.2126d * d2) + (0.7152d * d3)) + (0.0722d * d)) * XYZ_WHITE_REFERENCE_Y;
            dArr2[2] = (((d2 * 0.0193d) + (d3 * 0.1192d)) + (d * 0.9505d)) * XYZ_WHITE_REFERENCE_Y;
            return;
        }
        throw new IllegalArgumentException("outXyz must have a length of 3.");
    }

    public static void XYZToLAB(@FloatRange(from = 0.0d, to = 95.047d) double d, @FloatRange(from = 0.0d, to = 100.0d) double d2, @FloatRange(from = 0.0d, to = 108.883d) double d3, @NonNull double[] dArr) {
        if (dArr.length == 3) {
            d = pivotXyzComponent(d / XYZ_WHITE_REFERENCE_X);
            d2 = pivotXyzComponent(d2 / XYZ_WHITE_REFERENCE_Y);
            d3 = pivotXyzComponent(d3 / XYZ_WHITE_REFERENCE_Z);
            dArr[0] = Math.max(0.0d, (116.0d * d2) - 16.0d);
            dArr[1] = (d - d2) * 500.0d;
            dArr[1.0E-323d] = (d2 - d3) * 200.0d;
            return;
        }
        throw new IllegalArgumentException("outLab must have a length of 3.");
    }

    public static void LABToXYZ(@FloatRange(from = 0.0d, to = 100.0d) double d, @FloatRange(from = -128.0d, to = 127.0d) double d2, @FloatRange(from = -128.0d, to = 127.0d) double d3, @NonNull double[] dArr) {
        double d4 = (d + 16.0d) / 116.0d;
        double d5 = (d2 / 500.0d) + d4;
        double d6 = d4 - (d3 / 200.0d);
        double pow = Math.pow(d5, 3.0d);
        if (pow <= XYZ_EPSILON) {
            pow = ((d5 * 116.0d) - 16.0d) / XYZ_KAPPA;
        }
        d4 = d > 7.9996247999999985d ? Math.pow(d4, 3.0d) : d / XYZ_KAPPA;
        d5 = Math.pow(d6, 3.0d);
        if (d5 <= XYZ_EPSILON) {
            d5 = ((d6 * 116.0d) - 16.0d) / XYZ_KAPPA;
        }
        dArr[0] = pow * XYZ_WHITE_REFERENCE_X;
        dArr[1] = d4 * XYZ_WHITE_REFERENCE_Y;
        dArr[2] = d5 * XYZ_WHITE_REFERENCE_Z;
    }

    @ColorInt
    public static int XYZToColor(@FloatRange(from = 0.0d, to = 95.047d) double d, @FloatRange(from = 0.0d, to = 100.0d) double d2, @FloatRange(from = 0.0d, to = 108.883d) double d3) {
        double d4 = (((3.2406d * d) + (-1.5372d * d2)) + (-0.4986d * d3)) / XYZ_WHITE_REFERENCE_Y;
        double d5 = (((-0.9689d * d) + (1.8758d * d2)) + (0.0415d * d3)) / XYZ_WHITE_REFERENCE_Y;
        double d6 = (((0.0557d * d) + (-0.204d * d2)) + (1.057d * d3)) / XYZ_WHITE_REFERENCE_Y;
        return Color.rgb(constrain((int) Math.round((d4 > 0.0031308d ? (Math.pow(d4, 0.4166666666666667d) * 1.055d) - 0.055d : d4 * 12.92d) * 255.0d), 0, 255), constrain((int) Math.round((d5 > 0.0031308d ? (Math.pow(d5, 0.4166666666666667d) * 1.055d) - 0.055d : d5 * 12.92d) * 255.0d), 0, 255), constrain((int) Math.round((d6 > 0.0031308d ? (Math.pow(d6, 0.4166666666666667d) * 1.055d) - 0.055d : d6 * 12.92d) * 255.0d), 0, 255));
    }

    @ColorInt
    public static int LABToColor(@FloatRange(from = 0.0d, to = 100.0d) double d, @FloatRange(from = -128.0d, to = 127.0d) double d2, @FloatRange(from = -128.0d, to = 127.0d) double d3) {
        double[] tempDouble3Array = getTempDouble3Array();
        LABToXYZ(d, d2, d3, tempDouble3Array);
        return XYZToColor(tempDouble3Array[0.0d], tempDouble3Array[Double.MIN_VALUE], tempDouble3Array[1.0E-323d]);
    }

    public static double distanceEuclidean(@NonNull double[] dArr, @NonNull double[] dArr2) {
        return Math.sqrt((Math.pow(dArr[0] - dArr2[0], 2.0d) + Math.pow(dArr[1] - dArr2[1], 2.0d)) + Math.pow(dArr[2] - dArr2[2], 2.0d));
    }

    private static double pivotXyzComponent(double d) {
        return d > XYZ_EPSILON ? Math.pow(d, 0.3333333333333333d) : ((d * XYZ_KAPPA) + 16.0d) / 116.0d;
    }

    @ColorInt
    public static int blendARGB(@ColorInt int i, @ColorInt int i2, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((((float) Color.alpha(i)) * f2) + (((float) Color.alpha(i2)) * f)), (int) ((((float) Color.red(i)) * f2) + (((float) Color.red(i2)) * f)), (int) ((((float) Color.green(i)) * f2) + (((float) Color.green(i2)) * f)), (int) ((((float) Color.blue(i)) * f2) + (((float) Color.blue(i2)) * f)));
    }

    public static void blendHSL(@NonNull float[] fArr, @NonNull float[] fArr2, @FloatRange(from = 0.0d, to = 1.0d) float f, @NonNull float[] fArr3) {
        if (fArr3.length == 3) {
            float f2 = 1.0f - f;
            fArr3[0] = circularInterpolate(fArr[0], fArr2[0], f);
            fArr3[1] = (fArr[1] * f2) + (fArr2[1] * f);
            fArr3[2] = (fArr[2] * f2) + (fArr2[2] * f);
            return;
        }
        throw new IllegalArgumentException("result must have a length of 3.");
    }

    public static void blendLAB(@NonNull double[] dArr, @NonNull double[] dArr2, @FloatRange(from = 0.0d, to = 1.0d) double d, @NonNull double[] dArr3) {
        if (dArr3.length == 3) {
            double d2 = 1.0d - d;
            dArr3[0] = (dArr[0] * d2) + (dArr2[0] * d);
            dArr3[1] = (dArr[1] * d2) + (dArr2[1] * d);
            dArr3[2] = (dArr[2] * d2) + (dArr2[2] * d);
            return;
        }
        throw new IllegalArgumentException("outResult must have a length of 3.");
    }

    @VisibleForTesting
    static float circularInterpolate(float f, float f2, float f3) {
        if (Math.abs(f2 - f) > 180.0f) {
            if (f2 > f) {
                f += 360.0f;
            } else {
                f2 += 360.0f;
            }
        }
        return (f + ((f2 - f) * f3)) % 360.0f;
    }

    private static double[] getTempDouble3Array() {
        double[] dArr = (double[]) TEMP_ARRAY.get();
        if (dArr != null) {
            return dArr;
        }
        Object obj = new double[3];
        TEMP_ARRAY.set(obj);
        return obj;
    }
}
