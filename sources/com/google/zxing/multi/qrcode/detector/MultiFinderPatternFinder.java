package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final float DIFF_MODSIZE_CUTOFF = 0.5f;
    private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05f;
    private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];
    private static final float MAX_MODULE_COUNT_PER_EDGE = 180.0f;
    private static final float MIN_MODULE_COUNT_PER_EDGE = 9.0f;

    private static final class ModuleSizeComparator implements Comparator<FinderPattern>, Serializable {
        private ModuleSizeComparator() {
        }

        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            finderPattern = (double) (finderPattern2.getEstimatedModuleSize() - finderPattern.getEstimatedModuleSize());
            if (finderPattern < null) {
                return -1;
            }
            return finderPattern > null ? 1 : null;
        }
    }

    private com.google.zxing.qrcode.detector.FinderPattern[][] selectMutipleBestPatterns() throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:58:0x018c in {5, 11, 17, 22, 27, 32, 37, 40, 43, 44, 45, 46, 47, 48, 49, 53, 55, 57} preds:[]
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
        r19 = this;
        r0 = r19.getPossibleCenters();
        r1 = r0.size();
        r2 = 3;
        if (r1 < r2) goto L_0x0187;
    L_0x000b:
        r3 = 2;
        r4 = 0;
        r5 = 1;
        if (r1 != r2) goto L_0x002f;
    L_0x0010:
        r1 = new com.google.zxing.qrcode.detector.FinderPattern[r5][];
        r2 = new com.google.zxing.qrcode.detector.FinderPattern[r2];
        r6 = r0.get(r4);
        r6 = (com.google.zxing.qrcode.detector.FinderPattern) r6;
        r2[r4] = r6;
        r6 = r0.get(r5);
        r6 = (com.google.zxing.qrcode.detector.FinderPattern) r6;
        r2[r5] = r6;
        r0 = r0.get(r3);
        r0 = (com.google.zxing.qrcode.detector.FinderPattern) r0;
        r2[r3] = r0;
        r1[r4] = r2;
        return r1;
    L_0x002f:
        r6 = new com.google.zxing.multi.qrcode.detector.MultiFinderPatternFinder$ModuleSizeComparator;
        r7 = 0;
        r6.<init>();
        java.util.Collections.sort(r0, r6);
        r6 = new java.util.ArrayList;
        r6.<init>();
        r7 = 0;
    L_0x003e:
        r8 = r1 + -2;
        if (r7 >= r8) goto L_0x016f;
    L_0x0042:
        r8 = r0.get(r7);
        r8 = (com.google.zxing.qrcode.detector.FinderPattern) r8;
        if (r8 != 0) goto L_0x004d;
    L_0x004a:
        r15 = 2;
        goto L_0x0167;
    L_0x004d:
        r9 = r7 + 1;
    L_0x004f:
        r10 = r1 + -1;
        if (r9 >= r10) goto L_0x0166;
    L_0x0053:
        r10 = r0.get(r9);
        r10 = (com.google.zxing.qrcode.detector.FinderPattern) r10;
        if (r10 != 0) goto L_0x005e;
    L_0x005b:
        r15 = 2;
        goto L_0x015e;
    L_0x005e:
        r11 = r8.getEstimatedModuleSize();
        r12 = r10.getEstimatedModuleSize();
        r11 = r11 - r12;
        r12 = r8.getEstimatedModuleSize();
        r13 = r10.getEstimatedModuleSize();
        r12 = java.lang.Math.min(r12, r13);
        r11 = r11 / r12;
        r12 = r8.getEstimatedModuleSize();
        r13 = r10.getEstimatedModuleSize();
        r12 = r12 - r13;
        r12 = java.lang.Math.abs(r12);
        r13 = 1028443341; // 0x3d4ccccd float:0.05 double:5.081185235E-315;
        r14 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
        if (r12 <= 0) goto L_0x0091;
    L_0x008a:
        r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1));
        if (r11 < 0) goto L_0x0091;
    L_0x008e:
        r15 = 2;
        goto L_0x0167;
    L_0x0091:
        r11 = r9 + 1;
    L_0x0093:
        if (r11 >= r1) goto L_0x015d;
    L_0x0095:
        r12 = r0.get(r11);
        r12 = (com.google.zxing.qrcode.detector.FinderPattern) r12;
        if (r12 != 0) goto L_0x00a0;
    L_0x009d:
        r15 = 2;
        goto L_0x0150;
    L_0x00a0:
        r15 = r10.getEstimatedModuleSize();
        r16 = r12.getEstimatedModuleSize();
        r15 = r15 - r16;
        r3 = r10.getEstimatedModuleSize();
        r5 = r12.getEstimatedModuleSize();
        r3 = java.lang.Math.min(r3, r5);
        r15 = r15 / r3;
        r3 = r10.getEstimatedModuleSize();
        r5 = r12.getEstimatedModuleSize();
        r3 = r3 - r5;
        r3 = java.lang.Math.abs(r3);
        r3 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1));
        if (r3 <= 0) goto L_0x00cf;
    L_0x00c8:
        r3 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1));
        if (r3 < 0) goto L_0x00cf;
    L_0x00cc:
        r15 = 2;
        goto L_0x015e;
    L_0x00cf:
        r3 = new com.google.zxing.qrcode.detector.FinderPattern[r2];
        r3[r4] = r8;
        r5 = 1;
        r3[r5] = r10;
        r15 = 2;
        r3[r15] = r12;
        com.google.zxing.ResultPoint.orderBestPatterns(r3);
        r12 = new com.google.zxing.qrcode.detector.FinderPatternInfo;
        r12.<init>(r3);
        r2 = r12.getTopLeft();
        r4 = r12.getBottomLeft();
        r2 = com.google.zxing.ResultPoint.distance(r2, r4);
        r4 = r12.getTopRight();
        r5 = r12.getBottomLeft();
        r4 = com.google.zxing.ResultPoint.distance(r4, r5);
        r5 = r12.getTopLeft();
        r12 = r12.getTopRight();
        r5 = com.google.zxing.ResultPoint.distance(r5, r12);
        r12 = r2 + r5;
        r17 = r8.getEstimatedModuleSize();
        r18 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r17 = r17 * r18;
        r12 = r12 / r17;
        r17 = 1127481344; // 0x43340000 float:180.0 double:5.570497984E-315;
        r17 = (r12 > r17 ? 1 : (r12 == r17 ? 0 : -1));
        if (r17 > 0) goto L_0x0150;
    L_0x0117:
        r17 = 1091567616; // 0x41100000 float:9.0 double:5.39306059E-315;
        r12 = (r12 > r17 ? 1 : (r12 == r17 ? 0 : -1));
        if (r12 >= 0) goto L_0x011e;
    L_0x011d:
        goto L_0x0150;
    L_0x011e:
        r12 = r2 - r5;
        r17 = java.lang.Math.min(r2, r5);
        r12 = r12 / r17;
        r12 = java.lang.Math.abs(r12);
        r17 = 1036831949; // 0x3dcccccd float:0.1 double:5.122630465E-315;
        r12 = (r12 > r17 ? 1 : (r12 == r17 ? 0 : -1));
        if (r12 < 0) goto L_0x0132;
    L_0x0131:
        goto L_0x0150;
    L_0x0132:
        r2 = r2 * r2;
        r5 = r5 * r5;
        r2 = r2 + r5;
        r13 = (double) r2;
        r13 = java.lang.Math.sqrt(r13);
        r2 = (float) r13;
        r13 = r4 - r2;
        r2 = java.lang.Math.min(r4, r2);
        r13 = r13 / r2;
        r2 = java.lang.Math.abs(r13);
        r2 = (r2 > r17 ? 1 : (r2 == r17 ? 0 : -1));
        if (r2 < 0) goto L_0x014d;
    L_0x014c:
        goto L_0x0150;
    L_0x014d:
        r6.add(r3);
    L_0x0150:
        r11 = r11 + 1;
        r2 = 3;
        r3 = 2;
        r4 = 0;
        r5 = 1;
        r13 = 1028443341; // 0x3d4ccccd float:0.05 double:5.081185235E-315;
        r14 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        goto L_0x0093;
    L_0x015d:
        r15 = 2;
    L_0x015e:
        r9 = r9 + 1;
        r2 = 3;
        r3 = 2;
        r4 = 0;
        r5 = 1;
        goto L_0x004f;
    L_0x0166:
        r15 = 2;
    L_0x0167:
        r7 = r7 + 1;
        r2 = 3;
        r3 = 2;
        r4 = 0;
        r5 = 1;
        goto L_0x003e;
    L_0x016f:
        r0 = r6.isEmpty();
        if (r0 != 0) goto L_0x0182;
    L_0x0175:
        r0 = r6.size();
        r0 = new com.google.zxing.qrcode.detector.FinderPattern[r0][];
        r0 = r6.toArray(r0);
        r0 = (com.google.zxing.qrcode.detector.FinderPattern[][]) r0;
        return r0;
    L_0x0182:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0187:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.multi.qrcode.detector.MultiFinderPatternFinder.selectMutipleBestPatterns():com.google.zxing.qrcode.detector.FinderPattern[][]");
    }

    MultiFinderPatternFinder(BitMatrix bitMatrix) {
        super(bitMatrix);
    }

    MultiFinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        super(bitMatrix, resultPointCallback);
    }

    public FinderPatternInfo[] findMulti(Map<DecodeHintType, ?> map) throws NotFoundException {
        int i = 0;
        Object obj = (map == null || !map.containsKey(DecodeHintType.TRY_HARDER)) ? null : 1;
        map = (map == null || map.containsKey(DecodeHintType.PURE_BARCODE) == null) ? null : true;
        BitMatrix image = getImage();
        int height = image.getHeight();
        int width = image.getWidth();
        int i2 = (int) ((((float) height) / 228.0f) * 3.0f);
        if (i2 < 3 || obj != null) {
            i2 = 3;
        }
        int[] iArr = new int[5];
        int i3 = i2 - 1;
        while (i3 < height) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i4 = 0;
            int i5 = 0;
            while (i4 < width) {
                if (image.get(i4, i3)) {
                    if ((i5 & 1) == 1) {
                        i5++;
                    }
                    iArr[i5] = iArr[i5] + 1;
                } else if ((i5 & 1) != 0) {
                    iArr[i5] = iArr[i5] + 1;
                } else if (i5 != 4) {
                    i5++;
                    iArr[i5] = iArr[i5] + 1;
                } else if (FinderPatternFinder.foundPatternCross(iArr) && handlePossibleCenter(iArr, i3, i4, map)) {
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    i5 = 0;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i5 = 3;
                }
                i4++;
            }
            if (FinderPatternFinder.foundPatternCross(iArr)) {
                handlePossibleCenter(iArr, i3, width, map);
            }
            i3 += i2;
        }
        map = selectMutipleBestPatterns();
        List arrayList = new ArrayList();
        int length = map.length;
        while (i < length) {
            ResultPoint[] resultPointArr = map[i];
            ResultPoint.orderBestPatterns(resultPointArr);
            arrayList.add(new FinderPatternInfo(resultPointArr));
            i++;
        }
        if (arrayList.isEmpty() != null) {
            return EMPTY_RESULT_ARRAY;
        }
        return (FinderPatternInfo[]) arrayList.toArray(new FinderPatternInfo[arrayList.size()]);
    }
}
