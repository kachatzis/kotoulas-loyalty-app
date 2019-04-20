package com.google.zxing;

import com.google.zxing.common.detector.MathUtils;

public class ResultPoint {
    /* renamed from: x */
    private final float f13x;
    /* renamed from: y */
    private final float f14y;

    public ResultPoint(float f, float f2) {
        this.f13x = f;
        this.f14y = f2;
    }

    public final float getX() {
        return this.f13x;
    }

    public final float getY() {
        return this.f14y;
    }

    public final boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof ResultPoint)) {
            return false;
        }
        ResultPoint resultPoint = (ResultPoint) obj;
        if (this.f13x == resultPoint.f13x && this.f14y == resultPoint.f14y) {
            z = true;
        }
        return z;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f13x) * 31) + Float.floatToIntBits(this.f14y);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(25);
        stringBuilder.append('(');
        stringBuilder.append(this.f13x);
        stringBuilder.append(',');
        stringBuilder.append(this.f14y);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public static void orderBestPatterns(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        float distance = distance(resultPointArr[0], resultPointArr[1]);
        float distance2 = distance(resultPointArr[1], resultPointArr[2]);
        float distance3 = distance(resultPointArr[0], resultPointArr[2]);
        if (distance2 >= distance && distance2 >= distance3) {
            resultPoint = resultPointArr[0];
            resultPoint2 = resultPointArr[1];
            resultPoint3 = resultPointArr[2];
        } else if (distance3 < distance2 || distance3 < distance) {
            resultPoint = resultPointArr[2];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[1];
        } else {
            resultPoint = resultPointArr[1];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[2];
        }
        if (crossProductZ(resultPoint2, resultPoint, resultPoint3) < 0.0f) {
            ResultPoint resultPoint4 = resultPoint3;
            resultPoint3 = resultPoint2;
            resultPoint2 = resultPoint4;
        }
        resultPointArr[0] = resultPoint2;
        resultPointArr[1] = resultPoint;
        resultPointArr[2] = resultPoint3;
    }

    public static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.f13x, resultPoint.f14y, resultPoint2.f13x, resultPoint2.f14y);
    }

    private static float crossProductZ(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        float f = resultPoint2.f13x;
        resultPoint2 = resultPoint2.f14y;
        return ((resultPoint3.f13x - f) * (resultPoint.f14y - resultPoint2)) - ((resultPoint3.f14y - resultPoint2) * (resultPoint.f13x - f));
    }
}
