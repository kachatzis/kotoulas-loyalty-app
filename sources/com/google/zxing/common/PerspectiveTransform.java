package com.google.zxing.common;

public final class PerspectiveTransform {
    private final float a11;
    private final float a12;
    private final float a13;
    private final float a21;
    private final float a22;
    private final float a23;
    private final float a31;
    private final float a32;
    private final float a33;

    private PerspectiveTransform(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.a11 = f;
        this.a12 = f4;
        this.a13 = f7;
        this.a21 = f2;
        this.a22 = f5;
        this.a23 = f8;
        this.a31 = f3;
        this.a32 = f6;
        this.a33 = f9;
    }

    public static PerspectiveTransform quadrilateralToQuadrilateral(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        return squareToQuadrilateral(f9, f10, f11, f12, f13, f14, f15, f16).times(quadrilateralToSquare(f, f2, f3, f4, f5, f6, f7, f8));
    }

    public void transformPoints(float[] fArr) {
        float[] fArr2 = fArr;
        int length = fArr2.length;
        float f = this.a11;
        float f2 = this.a12;
        float f3 = this.a13;
        float f4 = this.a21;
        float f5 = this.a22;
        float f6 = this.a23;
        float f7 = this.a31;
        float f8 = this.a32;
        float f9 = this.a33;
        for (int i = 0; i < length; i += 2) {
            float f10 = fArr2[i];
            int i2 = i + 1;
            float f11 = fArr2[i2];
            float f12 = ((f3 * f10) + (f6 * f11)) + f9;
            fArr2[i] = (((f * f10) + (f4 * f11)) + f7) / f12;
            fArr2[i2] = (((f10 * f2) + (f11 * f5)) + f8) / f12;
        }
    }

    public void transformPoints(float[] fArr, float[] fArr2) {
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            float f = fArr[i];
            float f2 = fArr2[i];
            float f3 = ((this.a13 * f) + (this.a23 * f2)) + this.a33;
            fArr[i] = (((this.a11 * f) + (this.a21 * f2)) + this.a31) / f3;
            fArr2[i] = (((this.a12 * f) + (this.a22 * f2)) + this.a32) / f3;
        }
    }

    public static PerspectiveTransform squareToQuadrilateral(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = ((f - f3) + f5) - f7;
        float f10 = ((f2 - f4) + f6) - f8;
        if (f9 == 0.0f && f10 == 0.0f) {
            return new PerspectiveTransform(f3 - f, f5 - f3, f, f4 - f2, f6 - f4, f2, 0.0f, 0.0f, 1.0f);
        }
        float f11 = f3 - f5;
        float f12 = f7 - f5;
        float f13 = f4 - f6;
        float f14 = f8 - f6;
        float f15 = (f11 * f14) - (f12 * f13);
        float f16 = ((f14 * f9) - (f12 * f10)) / f15;
        float f17 = ((f11 * f10) - (f9 * f13)) / f15;
        return new PerspectiveTransform((f16 * f3) + (f3 - f), (f17 * f7) + (f7 - f), f, (f4 - f2) + (f16 * f4), (f8 - f2) + (f17 * f8), f2, f16, f17, 1.0f);
    }

    public static PerspectiveTransform quadrilateralToSquare(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        return squareToQuadrilateral(f, f2, f3, f4, f5, f6, f7, f8).buildAdjoint();
    }

    PerspectiveTransform buildAdjoint() {
        float f = this.a22;
        float f2 = this.a33;
        float f3 = f * f2;
        float f4 = this.a23;
        float f5 = this.a32;
        f3 -= f4 * f5;
        float f6 = this.a31;
        float f7 = f4 * f6;
        float f8 = this.a21;
        f7 -= f8 * f2;
        float f9 = (f8 * f5) - (f * f6);
        float f10 = this.a13;
        float f11 = f10 * f5;
        float f12 = this.a12;
        f11 -= f12 * f2;
        float f13 = this.a11;
        return new PerspectiveTransform(f3, f7, f9, f11, (f2 * f13) - (f10 * f6), (f6 * f12) - (f5 * f13), (f12 * f4) - (f10 * f), (f10 * f8) - (f4 * f13), (f13 * f) - (f12 * f8));
    }

    PerspectiveTransform times(PerspectiveTransform perspectiveTransform) {
        PerspectiveTransform perspectiveTransform2 = perspectiveTransform;
        float f = this.a11;
        float f2 = perspectiveTransform2.a11;
        float f3 = f * f2;
        float f4 = this.a21;
        float f5 = perspectiveTransform2.a12;
        f3 += f4 * f5;
        float f6 = this.a31;
        float f7 = perspectiveTransform2.a13;
        f3 += f6 * f7;
        float f8 = perspectiveTransform2.a21;
        float f9 = f * f8;
        float f10 = perspectiveTransform2.a22;
        f9 += f4 * f10;
        float f11 = perspectiveTransform2.a23;
        f9 += f6 * f11;
        float f12 = perspectiveTransform2.a31;
        f *= f12;
        float f13 = perspectiveTransform2.a32;
        f += f4 * f13;
        float f14 = perspectiveTransform2.a33;
        f4 = f + (f6 * f14);
        f = this.a12;
        f6 = f * f2;
        perspectiveTransform = f4;
        f4 = this.a22;
        f6 += f4 * f5;
        float f15 = f9;
        f9 = this.a32;
        f6 += f9 * f7;
        float f16 = ((f * f8) + (f4 * f10)) + (f9 * f11);
        f9 = (f9 * f14) + ((f * f12) + (f4 * f13));
        f = this.a13;
        f2 *= f;
        f4 = this.a23;
        f2 += f5 * f4;
        f5 = this.a33;
        f10 = ((f * f12) + (f4 * f13)) + (f5 * f14);
        return new PerspectiveTransform(f3, f15, perspectiveTransform, f6, f16, f9, (f7 * f5) + f2, ((f8 * f) + (f10 * f4)) + (f11 * f5), f10);
    }
}
