package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    private static final class CenterComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private CenterComparator(float f) {
            this.average = f;
        }

        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            if (finderPattern2.getCount() != finderPattern.getCount()) {
                return finderPattern2.getCount() - finderPattern.getCount();
            }
            finderPattern2 = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            finderPattern = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            finderPattern = finderPattern2 < finderPattern ? true : finderPattern2 == finderPattern ? null : -1;
            return finderPattern;
        }
    }

    private static final class FurthestFromAverageComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private FurthestFromAverageComparator(float f) {
            this.average = f;
        }

        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            finderPattern2 = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            finderPattern = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            if (finderPattern2 < finderPattern) {
                return -1;
            }
            return finderPattern2 == finderPattern ? null : 1;
        }
    }

    private com.google.zxing.qrcode.detector.FinderPattern[] selectBestPatterns() throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:28:0x00e0 in {7, 15, 16, 22, 23, 25, 27} preds:[]
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
        r0 = r10.possibleCenters;
        r0 = r0.size();
        r1 = 3;
        if (r0 < r1) goto L_0x00db;
    L_0x0009:
        r2 = 0;
        r3 = 1;
        r4 = 0;
        r5 = 0;
        if (r0 <= r1) goto L_0x007a;
    L_0x000f:
        r6 = r10.possibleCenters;
        r6 = r6.iterator();
        r7 = 0;
        r8 = 0;
    L_0x0017:
        r9 = r6.hasNext();
        if (r9 == 0) goto L_0x002c;
    L_0x001d:
        r9 = r6.next();
        r9 = (com.google.zxing.qrcode.detector.FinderPattern) r9;
        r9 = r9.getEstimatedModuleSize();
        r7 = r7 + r9;
        r9 = r9 * r9;
        r8 = r8 + r9;
        goto L_0x0017;
    L_0x002c:
        r0 = (float) r0;
        r7 = r7 / r0;
        r8 = r8 / r0;
        r0 = r7 * r7;
        r8 = r8 - r0;
        r8 = (double) r8;
        r8 = java.lang.Math.sqrt(r8);
        r0 = (float) r8;
        r6 = r10.possibleCenters;
        r8 = new com.google.zxing.qrcode.detector.FinderPatternFinder$FurthestFromAverageComparator;
        r8.<init>(r7);
        java.util.Collections.sort(r6, r8);
        r6 = 1045220557; // 0x3e4ccccd float:0.2 double:5.164075695E-315;
        r6 = r6 * r7;
        r0 = java.lang.Math.max(r6, r0);
        r6 = 0;
    L_0x004c:
        r8 = r10.possibleCenters;
        r8 = r8.size();
        if (r6 >= r8) goto L_0x007a;
    L_0x0054:
        r8 = r10.possibleCenters;
        r8 = r8.size();
        if (r8 <= r1) goto L_0x007a;
    L_0x005c:
        r8 = r10.possibleCenters;
        r8 = r8.get(r6);
        r8 = (com.google.zxing.qrcode.detector.FinderPattern) r8;
        r8 = r8.getEstimatedModuleSize();
        r8 = r8 - r7;
        r8 = java.lang.Math.abs(r8);
        r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1));
        if (r8 <= 0) goto L_0x0078;
    L_0x0071:
        r8 = r10.possibleCenters;
        r8.remove(r6);
        r6 = r6 + -1;
    L_0x0078:
        r6 = r6 + r3;
        goto L_0x004c;
    L_0x007a:
        r0 = r10.possibleCenters;
        r0 = r0.size();
        if (r0 <= r1) goto L_0x00b9;
    L_0x0082:
        r0 = r10.possibleCenters;
        r0 = r0.iterator();
    L_0x0088:
        r6 = r0.hasNext();
        if (r6 == 0) goto L_0x009a;
    L_0x008e:
        r6 = r0.next();
        r6 = (com.google.zxing.qrcode.detector.FinderPattern) r6;
        r6 = r6.getEstimatedModuleSize();
        r5 = r5 + r6;
        goto L_0x0088;
    L_0x009a:
        r0 = r10.possibleCenters;
        r0 = r0.size();
        r0 = (float) r0;
        r5 = r5 / r0;
        r0 = r10.possibleCenters;
        r6 = new com.google.zxing.qrcode.detector.FinderPatternFinder$CenterComparator;
        r6.<init>(r5);
        java.util.Collections.sort(r0, r6);
        r0 = r10.possibleCenters;
        r2 = r0.size();
        r0 = r0.subList(r1, r2);
        r0.clear();
    L_0x00b9:
        r0 = new com.google.zxing.qrcode.detector.FinderPattern[r1];
        r1 = r10.possibleCenters;
        r1 = r1.get(r4);
        r1 = (com.google.zxing.qrcode.detector.FinderPattern) r1;
        r0[r4] = r1;
        r1 = r10.possibleCenters;
        r1 = r1.get(r3);
        r1 = (com.google.zxing.qrcode.detector.FinderPattern) r1;
        r0[r3] = r1;
        r1 = r10.possibleCenters;
        r2 = 2;
        r1 = r1.get(r2);
        r1 = (com.google.zxing.qrcode.detector.FinderPattern) r1;
        r0[r2] = r1;
        return r0;
    L_0x00db:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.selectBestPatterns():com.google.zxing.qrcode.detector.FinderPattern[]");
    }

    public FinderPatternFinder(BitMatrix bitMatrix) {
        this(bitMatrix, null);
    }

    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback;
    }

    protected final BitMatrix getImage() {
        return this.image;
    }

    protected final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    final FinderPatternInfo find(Map<DecodeHintType, ?> map) throws NotFoundException {
        FinderPatternFinder finderPatternFinder = this;
        Map<DecodeHintType, ?> map2 = map;
        Object obj = (map2 == null || !map2.containsKey(DecodeHintType.TRY_HARDER)) ? null : 1;
        boolean z = map2 != null && map2.containsKey(DecodeHintType.PURE_BARCODE);
        int height = finderPatternFinder.image.getHeight();
        int width = finderPatternFinder.image.getWidth();
        int i = (height * 3) / 228;
        if (i < 3 || obj != null) {
            i = 3;
        }
        int[] iArr = new int[5];
        int i2 = i - 1;
        int i3 = i;
        boolean z2 = false;
        while (i2 < height && !z2) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            boolean z3 = z2;
            int i4 = i3;
            i = 0;
            i3 = 0;
            while (i < width) {
                if (finderPatternFinder.image.get(i, i2)) {
                    if ((i3 & 1) == 1) {
                        i3++;
                    }
                    iArr[i3] = iArr[i3] + 1;
                } else if ((i3 & 1) != 0) {
                    iArr[i3] = iArr[i3] + 1;
                } else if (i3 != 4) {
                    i3++;
                    iArr[i3] = iArr[i3] + 1;
                } else if (!foundPatternCross(iArr)) {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i3 = 3;
                } else if (handlePossibleCenter(iArr, i2, i, z)) {
                    if (finderPatternFinder.hasSkipped) {
                        z3 = haveMultiplyConfirmedCenters();
                    } else {
                        i3 = findRowSkip();
                        if (i3 > iArr[2]) {
                            i2 += (i3 - iArr[2]) - 2;
                            i = width - 1;
                        }
                    }
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    i3 = 0;
                    i4 = 2;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i3 = 3;
                }
                i++;
            }
            if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i2, width, z)) {
                i = iArr[0];
                if (finderPatternFinder.hasSkipped) {
                    i3 = i;
                    z2 = haveMultiplyConfirmedCenters();
                } else {
                    i3 = i;
                    z2 = z3;
                }
            } else {
                i3 = i4;
                z2 = z3;
            }
            i2 += i3;
        }
        ResultPoint[] selectBestPatterns = selectBestPatterns();
        ResultPoint.orderBestPatterns(selectBestPatterns);
        return new FinderPatternInfo(selectBestPatterns);
    }

    private static float centerFromEnd(int[] iArr, int i) {
        return ((float) ((i - iArr[4]) - iArr[3])) - (((float) iArr[2]) / 2.0f);
    }

    protected static boolean foundPatternCross(int[] iArr) {
        boolean z = false;
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = ((float) i) / 7.0f;
        float f2 = f / 2.0f;
        if (Math.abs(f - ((float) iArr[0])) < f2 && Math.abs(f - ((float) iArr[1])) < f2 && Math.abs((f * 3.0f) - ((float) iArr[2])) < 3.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2) {
            z = true;
        }
        return z;
    }

    private int[] getCrossCheckStateCount() {
        int[] iArr = this.crossCheckStateCount;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        return iArr;
    }

    private boolean crossCheckDiagonal(int i, int i2, int i3, int i4) {
        FinderPatternFinder finderPatternFinder = this;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int[] crossCheckStateCount = getCrossCheckStateCount();
        boolean z = false;
        int i8 = 0;
        while (i5 >= i8 && i6 >= i8 && finderPatternFinder.image.get(i6 - i8, i5 - i8)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i8++;
        }
        if (i5 >= i8) {
            if (i6 >= i8) {
                while (i5 >= i8 && i6 >= i8 && !finderPatternFinder.image.get(i6 - i8, i5 - i8) && crossCheckStateCount[1] <= i7) {
                    crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
                    i8++;
                }
                if (i5 >= i8 && i6 >= i8) {
                    if (crossCheckStateCount[1] <= i7) {
                        while (i5 >= i8 && i6 >= i8 && finderPatternFinder.image.get(i6 - i8, i5 - i8) && crossCheckStateCount[0] <= i7) {
                            crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
                            i8++;
                        }
                        if (crossCheckStateCount[0] > i7) {
                            return false;
                        }
                        int i9;
                        i8 = finderPatternFinder.image.getHeight();
                        int width = finderPatternFinder.image.getWidth();
                        int i10 = 1;
                        while (true) {
                            i9 = i5 + i10;
                            if (i9 >= i8) {
                                break;
                            }
                            int i11 = i6 + i10;
                            if (i11 >= width || !finderPatternFinder.image.get(i11, i9)) {
                                break;
                            }
                            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
                            i10++;
                        }
                        if (i9 < i8) {
                            if (i6 + i10 < width) {
                                while (true) {
                                    i9 = i5 + i10;
                                    if (i9 >= i8) {
                                        break;
                                    }
                                    int i12 = i6 + i10;
                                    if (i12 >= width || finderPatternFinder.image.get(i12, i9) || crossCheckStateCount[3] >= i7) {
                                        break;
                                    }
                                    crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
                                    i10++;
                                }
                                if (i9 < i8 && i6 + i10 < width) {
                                    if (crossCheckStateCount[3] < i7) {
                                        while (true) {
                                            i9 = i5 + i10;
                                            if (i9 >= i8) {
                                                break;
                                            }
                                            int i13 = i6 + i10;
                                            if (i13 >= width || !finderPatternFinder.image.get(i13, i9) || crossCheckStateCount[4] >= i7) {
                                                break;
                                            }
                                            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
                                            i10++;
                                        }
                                        if (crossCheckStateCount[4] >= i7) {
                                            return false;
                                        }
                                        if (Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + crossCheckStateCount[4]) - i4) < i4 * 2 && foundPatternCross(crossCheckStateCount)) {
                                            z = true;
                                        }
                                        return z;
                                    }
                                }
                                return false;
                            }
                        }
                        return false;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private float crossCheckVertical(int i, int i2, int i3, int i4) {
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i5 = i;
        while (i5 >= 0 && bitMatrix.get(i2, i5)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i5--;
        }
        float f = Float.NaN;
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bitMatrix.get(i2, i5) && crossCheckStateCount[1] <= i3) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            i5--;
        }
        if (i5 >= 0) {
            if (crossCheckStateCount[1] <= i3) {
                while (i5 >= 0 && bitMatrix.get(i2, i5) && crossCheckStateCount[0] <= i3) {
                    crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
                    i5--;
                }
                if (crossCheckStateCount[0] > i3) {
                    return Float.NaN;
                }
                i++;
                while (i < height && bitMatrix.get(i2, i)) {
                    crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
                    i++;
                }
                if (i == height) {
                    return Float.NaN;
                }
                while (i < height && !bitMatrix.get(i2, i) && crossCheckStateCount[3] < i3) {
                    crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
                    i++;
                }
                if (i != height) {
                    if (crossCheckStateCount[3] < i3) {
                        while (i < height && bitMatrix.get(i2, i) && crossCheckStateCount[4] < i3) {
                            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
                            i++;
                        }
                        if (crossCheckStateCount[4] >= i3 || Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + crossCheckStateCount[4]) - i4) * 5 >= i4 * 2) {
                            return Float.NaN;
                        }
                        if (foundPatternCross(crossCheckStateCount) != 0) {
                            f = centerFromEnd(crossCheckStateCount, i);
                        }
                        return f;
                    }
                }
                return Float.NaN;
            }
        }
        return Float.NaN;
    }

    private float crossCheckHorizontal(int i, int i2, int i3, int i4) {
        BitMatrix bitMatrix = this.image;
        int width = bitMatrix.getWidth();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i5 = i;
        while (i5 >= 0 && bitMatrix.get(i5, i2)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i5--;
        }
        float f = Float.NaN;
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bitMatrix.get(i5, i2) && crossCheckStateCount[1] <= i3) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            i5--;
        }
        if (i5 >= 0) {
            if (crossCheckStateCount[1] <= i3) {
                while (i5 >= 0 && bitMatrix.get(i5, i2) && crossCheckStateCount[0] <= i3) {
                    crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
                    i5--;
                }
                if (crossCheckStateCount[0] > i3) {
                    return Float.NaN;
                }
                i++;
                while (i < width && bitMatrix.get(i, i2)) {
                    crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
                    i++;
                }
                if (i == width) {
                    return Float.NaN;
                }
                while (i < width && !bitMatrix.get(i, i2) && crossCheckStateCount[3] < i3) {
                    crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
                    i++;
                }
                if (i != width) {
                    if (crossCheckStateCount[3] < i3) {
                        while (i < width && bitMatrix.get(i, i2) && crossCheckStateCount[4] < i3) {
                            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
                            i++;
                        }
                        if (crossCheckStateCount[4] >= i3 || Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + crossCheckStateCount[4]) - i4) * 5 >= i4) {
                            return Float.NaN;
                        }
                        if (foundPatternCross(crossCheckStateCount) != 0) {
                            f = centerFromEnd(crossCheckStateCount, i);
                        }
                        return f;
                    }
                }
                return Float.NaN;
            }
        }
        return Float.NaN;
    }

    protected final boolean handlePossibleCenter(int[] iArr, int i, int i2, boolean z) {
        boolean z2 = false;
        int i3 = (((iArr[0] + iArr[1]) + iArr[2]) + iArr[3]) + iArr[4];
        i2 = (int) centerFromEnd(iArr, i2);
        i = crossCheckVertical(i, i2, iArr[2], i3);
        if (!Float.isNaN(i)) {
            int i4 = (int) i;
            i2 = crossCheckHorizontal(i2, i4, iArr[2], i3);
            if (!(Float.isNaN(i2) || (z && crossCheckDiagonal(i4, (int) i2, iArr[2], i3) == null))) {
                iArr = ((float) i3) / true;
                for (z = false; z < this.possibleCenters.size(); z++) {
                    FinderPattern finderPattern = (FinderPattern) this.possibleCenters.get(z);
                    if (finderPattern.aboutEquals(iArr, i, i2)) {
                        this.possibleCenters.set(z, finderPattern.combineEstimate(i, i2, iArr));
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    z = new FinderPattern(i2, i, iArr);
                    this.possibleCenters.add(z);
                    iArr = this.resultPointCallback;
                    if (iArr != null) {
                        iArr.foundPossibleResultPoint(z);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        ResultPoint resultPoint = null;
        for (ResultPoint resultPoint2 : this.possibleCenters) {
            if (resultPoint2.getCount() >= 2) {
                if (resultPoint == null) {
                    resultPoint = resultPoint2;
                } else {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(resultPoint.getX() - resultPoint2.getX()) - Math.abs(resultPoint.getY() - resultPoint2.getY()))) / 2;
                }
            }
        }
        return 0;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int size = this.possibleCenters.size();
        float f = 0.0f;
        boolean z = false;
        int i = 0;
        float f2 = 0.0f;
        for (FinderPattern finderPattern : this.possibleCenters) {
            if (finderPattern.getCount() >= 2) {
                i++;
                f2 += finderPattern.getEstimatedModuleSize();
            }
        }
        if (i < 3) {
            return false;
        }
        float f3 = f2 / ((float) size);
        for (FinderPattern estimatedModuleSize : this.possibleCenters) {
            f += Math.abs(estimatedModuleSize.getEstimatedModuleSize() - f3);
        }
        if (f <= f2 * 0.05f) {
            z = true;
        }
        return z;
    }
}
