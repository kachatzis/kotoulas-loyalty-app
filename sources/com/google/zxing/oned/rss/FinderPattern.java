package com.google.zxing.oned.rss;

import com.google.zxing.ResultPoint;

public final class FinderPattern {
    private final ResultPoint[] resultPoints;
    private final int[] startEnd;
    private final int value;

    public FinderPattern(int i, int[] iArr, int i2, int i3, int i4) {
        this.value = i;
        this.startEnd = iArr;
        i = new ResultPoint[2];
        i4 = (float) i4;
        i[0] = new ResultPoint((float) i2, i4);
        i[1] = new ResultPoint((float) i3, i4);
        this.resultPoints = i;
    }

    public int getValue() {
        return this.value;
    }

    public int[] getStartEnd() {
        return this.startEnd;
    }

    public ResultPoint[] getResultPoints() {
        return this.resultPoints;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof FinderPattern)) {
            return false;
        }
        if (this.value == ((FinderPattern) obj).value) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return this.value;
    }
}
