package com.google.zxing.common;

import com.google.zxing.NotFoundException;

public abstract class GridSampler {
    private static GridSampler gridSampler = new DefaultGridSampler();

    public abstract BitMatrix sampleGrid(BitMatrix bitMatrix, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws NotFoundException;

    public abstract BitMatrix sampleGrid(BitMatrix bitMatrix, int i, int i2, PerspectiveTransform perspectiveTransform) throws NotFoundException;

    public static void setGridSampler(GridSampler gridSampler) {
        gridSampler = gridSampler;
    }

    public static GridSampler getInstance() {
        return gridSampler;
    }

    protected static void checkAndNudgePoints(BitMatrix bitMatrix, float[] fArr) throws NotFoundException {
        int i;
        int width = bitMatrix.getWidth();
        bitMatrix = bitMatrix.getHeight();
        Object obj = 1;
        for (i = 0; i < fArr.length && r4 != null; i += 2) {
            int i2 = (int) fArr[i];
            int i3 = i + 1;
            int i4 = (int) fArr[i3];
            if (i2 < -1 || i2 > width || i4 < -1 || i4 > bitMatrix) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (i2 == -1) {
                fArr[i] = 0.0f;
                obj = 1;
            } else if (i2 == width) {
                fArr[i] = (float) (width - 1);
                obj = 1;
            } else {
                obj = null;
            }
            if (i4 == -1) {
                fArr[i3] = 0.0f;
                obj = 1;
            } else if (i4 == bitMatrix) {
                fArr[i3] = (float) (bitMatrix - 1);
                obj = 1;
            }
        }
        obj = 1;
        for (i = fArr.length - 2; i >= 0 && r4 != null; i -= 2) {
            i2 = (int) fArr[i];
            i3 = i + 1;
            i4 = (int) fArr[i3];
            if (i2 < -1 || i2 > width || i4 < -1 || i4 > bitMatrix) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (i2 == -1) {
                fArr[i] = 0.0f;
                obj = 1;
            } else if (i2 == width) {
                fArr[i] = (float) (width - 1);
                obj = 1;
            } else {
                obj = null;
            }
            if (i4 == -1) {
                fArr[i3] = 0.0f;
                obj = 1;
            } else if (i4 == bitMatrix) {
                fArr[i3] = (float) (bitMatrix - 1);
                obj = 1;
            }
        }
    }
}
