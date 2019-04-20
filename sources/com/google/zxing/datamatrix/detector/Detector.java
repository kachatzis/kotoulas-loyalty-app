package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

public final class Detector {
    private final BitMatrix image;
    private final WhiteRectangleDetector rectangleDetector;

    private static final class ResultPointsAndTransitions {
        private final ResultPoint from;
        private final ResultPoint to;
        private final int transitions;

        private ResultPointsAndTransitions(ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
            this.from = resultPoint;
            this.to = resultPoint2;
            this.transitions = i;
        }

        ResultPoint getFrom() {
            return this.from;
        }

        ResultPoint getTo() {
            return this.to;
        }

        public int getTransitions() {
            return this.transitions;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.from);
            stringBuilder.append("/");
            stringBuilder.append(this.to);
            stringBuilder.append('/');
            stringBuilder.append(this.transitions);
            return stringBuilder.toString();
        }
    }

    private static final class ResultPointsAndTransitionsComparator implements Comparator<ResultPointsAndTransitions>, Serializable {
        private ResultPointsAndTransitionsComparator() {
        }

        public int compare(ResultPointsAndTransitions resultPointsAndTransitions, ResultPointsAndTransitions resultPointsAndTransitions2) {
            return resultPointsAndTransitions.getTransitions() - resultPointsAndTransitions2.getTransitions();
        }
    }

    public com.google.zxing.common.DetectorResult detect() throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:57:0x019a in {5, 7, 8, 14, 17, 20, 21, 24, 27, 32, 35, 38, 39, 40, 43, 46, 47, 50, 51, 52, 54, 56} preds:[]
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
        r23 = this;
        r7 = r23;
        r0 = r7.rectangleDetector;
        r0 = r0.detect();
        r8 = 0;
        r1 = r0[r8];
        r9 = 1;
        r2 = r0[r9];
        r10 = 2;
        r3 = r0[r10];
        r11 = 3;
        r0 = r0[r11];
        r4 = new java.util.ArrayList;
        r12 = 4;
        r4.<init>(r12);
        r5 = r7.transitionsBetween(r1, r2);
        r4.add(r5);
        r5 = r7.transitionsBetween(r1, r3);
        r4.add(r5);
        r5 = r7.transitionsBetween(r2, r0);
        r4.add(r5);
        r5 = r7.transitionsBetween(r3, r0);
        r4.add(r5);
        r5 = new com.google.zxing.datamatrix.detector.Detector$ResultPointsAndTransitionsComparator;
        r6 = 0;
        r5.<init>();
        java.util.Collections.sort(r4, r5);
        r5 = r4.get(r8);
        r5 = (com.google.zxing.datamatrix.detector.Detector.ResultPointsAndTransitions) r5;
        r4 = r4.get(r9);
        r4 = (com.google.zxing.datamatrix.detector.Detector.ResultPointsAndTransitions) r4;
        r13 = new java.util.HashMap;
        r13.<init>();
        r14 = r5.getFrom();
        increment(r13, r14);
        r5 = r5.getTo();
        increment(r13, r5);
        r5 = r4.getFrom();
        increment(r13, r5);
        r4 = r4.getTo();
        increment(r13, r4);
        r4 = r13.entrySet();
        r4 = r4.iterator();
        r5 = r6;
        r14 = r5;
    L_0x0076:
        r15 = r4.hasNext();
        if (r15 == 0) goto L_0x009f;
    L_0x007c:
        r15 = r4.next();
        r15 = (java.util.Map.Entry) r15;
        r16 = r15.getKey();
        r16 = (com.google.zxing.ResultPoint) r16;
        r15 = r15.getValue();
        r15 = (java.lang.Integer) r15;
        r15 = r15.intValue();
        if (r15 != r10) goto L_0x0097;
    L_0x0094:
        r5 = r16;
        goto L_0x0076;
    L_0x0097:
        if (r6 != 0) goto L_0x009c;
    L_0x0099:
        r6 = r16;
        goto L_0x0076;
    L_0x009c:
        r14 = r16;
        goto L_0x0076;
    L_0x009f:
        if (r6 == 0) goto L_0x0195;
    L_0x00a1:
        if (r5 == 0) goto L_0x0195;
    L_0x00a3:
        if (r14 == 0) goto L_0x0195;
    L_0x00a5:
        r4 = new com.google.zxing.ResultPoint[r11];
        r4[r8] = r6;
        r4[r9] = r5;
        r4[r10] = r14;
        com.google.zxing.ResultPoint.orderBestPatterns(r4);
        r14 = r4[r8];
        r22 = r4[r9];
        r6 = r4[r10];
        r4 = r13.containsKey(r1);
        if (r4 != 0) goto L_0x00be;
    L_0x00bc:
        r13 = r1;
        goto L_0x00cf;
    L_0x00be:
        r1 = r13.containsKey(r2);
        if (r1 != 0) goto L_0x00c6;
    L_0x00c4:
        r13 = r2;
        goto L_0x00cf;
    L_0x00c6:
        r1 = r13.containsKey(r3);
        if (r1 != 0) goto L_0x00ce;
    L_0x00cc:
        r13 = r3;
        goto L_0x00cf;
    L_0x00ce:
        r13 = r0;
    L_0x00cf:
        r0 = r7.transitionsBetween(r6, r13);
        r0 = r0.getTransitions();
        r1 = r7.transitionsBetween(r14, r13);
        r1 = r1.getTransitions();
        r2 = r0 & 1;
        if (r2 != r9) goto L_0x00e5;
    L_0x00e3:
        r0 = r0 + 1;
    L_0x00e5:
        r5 = r0 + 2;
        r0 = r1 & 1;
        if (r0 != r9) goto L_0x00ed;
    L_0x00eb:
        r1 = r1 + 1;
    L_0x00ed:
        r15 = r1 + 2;
        r0 = r5 * 4;
        r1 = r15 * 7;
        if (r0 >= r1) goto L_0x0140;
    L_0x00f5:
        r0 = r15 * 4;
        r1 = r5 * 7;
        if (r0 < r1) goto L_0x00fc;
    L_0x00fb:
        goto L_0x0140;
    L_0x00fc:
        r5 = java.lang.Math.min(r15, r5);
        r0 = r23;
        r1 = r22;
        r2 = r14;
        r3 = r6;
        r4 = r13;
        r0 = r0.correctTopRight(r1, r2, r3, r4, r5);
        if (r0 != 0) goto L_0x010e;
    L_0x010d:
        r0 = r13;
    L_0x010e:
        r1 = r7.transitionsBetween(r6, r0);
        r1 = r1.getTransitions();
        r2 = r7.transitionsBetween(r14, r0);
        r2 = r2.getTransitions();
        r1 = java.lang.Math.max(r1, r2);
        r1 = r1 + r9;
        r2 = r1 & 1;
        if (r2 != r9) goto L_0x012c;
    L_0x0127:
        r1 = r1 + 1;
        r21 = r1;
        goto L_0x012e;
    L_0x012c:
        r21 = r1;
    L_0x012e:
        r15 = r7.image;
        r16 = r6;
        r17 = r22;
        r18 = r14;
        r19 = r0;
        r20 = r21;
        r1 = sampleGrid(r15, r16, r17, r18, r19, r20, r21);
        r11 = r6;
        goto L_0x0184;
    L_0x0140:
        r0 = r23;
        r1 = r22;
        r2 = r14;
        r3 = r6;
        r4 = r13;
        r11 = r6;
        r6 = r15;
        r0 = r0.correctTopRightRectangular(r1, r2, r3, r4, r5, r6);
        if (r0 != 0) goto L_0x0150;
    L_0x014f:
        r0 = r13;
    L_0x0150:
        r1 = r7.transitionsBetween(r11, r0);
        r1 = r1.getTransitions();
        r2 = r7.transitionsBetween(r14, r0);
        r2 = r2.getTransitions();
        r3 = r1 & 1;
        if (r3 != r9) goto L_0x0169;
    L_0x0164:
        r1 = r1 + 1;
        r20 = r1;
        goto L_0x016b;
    L_0x0169:
        r20 = r1;
    L_0x016b:
        r1 = r2 & 1;
        if (r1 != r9) goto L_0x0174;
    L_0x016f:
        r2 = r2 + 1;
        r21 = r2;
        goto L_0x0176;
    L_0x0174:
        r21 = r2;
    L_0x0176:
        r15 = r7.image;
        r16 = r11;
        r17 = r22;
        r18 = r14;
        r19 = r0;
        r1 = sampleGrid(r15, r16, r17, r18, r19, r20, r21);
    L_0x0184:
        r2 = new com.google.zxing.common.DetectorResult;
        r3 = new com.google.zxing.ResultPoint[r12];
        r3[r8] = r11;
        r3[r9] = r22;
        r3[r10] = r14;
        r4 = 3;
        r3[r4] = r0;
        r2.<init>(r1, r3);
        return r2;
    L_0x0195:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.detector.Detector.detect():com.google.zxing.common.DetectorResult");
    }

    public Detector(BitMatrix bitMatrix) throws NotFoundException {
        this.image = bitMatrix;
        this.rectangleDetector = new WhiteRectangleDetector(bitMatrix);
    }

    private ResultPoint correctTopRightRectangular(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) {
        float distance = ((float) distance(resultPoint, resultPoint2)) / ((float) i);
        float distance2 = (float) distance(resultPoint3, resultPoint4);
        ResultPoint resultPoint5 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint3.getX()) / distance2) * distance), resultPoint4.getY() + (distance * ((resultPoint4.getY() - resultPoint3.getY()) / distance2)));
        resultPoint = ((float) distance(resultPoint, resultPoint3)) / ((float) i2);
        distance = (float) distance(resultPoint2, resultPoint4);
        ResultPoint resultPoint6 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint2.getX()) / distance) * resultPoint), resultPoint4.getY() + (resultPoint * ((resultPoint4.getY() - resultPoint2.getY()) / distance)));
        return isValid(resultPoint5) == null ? isValid(resultPoint6) != null ? resultPoint6 : null : (isValid(resultPoint6) != null && Math.abs(i - transitionsBetween(resultPoint3, resultPoint5).getTransitions()) + Math.abs(i2 - transitionsBetween(resultPoint2, resultPoint5).getTransitions()) > Math.abs(i - transitionsBetween(resultPoint3, resultPoint6).getTransitions()) + Math.abs(i2 - transitionsBetween(resultPoint2, resultPoint6).getTransitions())) ? resultPoint6 : resultPoint5;
    }

    private ResultPoint correctTopRight(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) {
        i = (float) i;
        float distance = ((float) distance(resultPoint, resultPoint2)) / i;
        float distance2 = (float) distance(resultPoint3, resultPoint4);
        ResultPoint resultPoint5 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint3.getX()) / distance2) * distance), resultPoint4.getY() + (distance * ((resultPoint4.getY() - resultPoint3.getY()) / distance2)));
        resultPoint = ((float) distance(resultPoint, resultPoint3)) / i;
        i = (float) distance(resultPoint2, resultPoint4);
        i = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint2.getX()) / i) * resultPoint), resultPoint4.getY() + (resultPoint * ((resultPoint4.getY() - resultPoint2.getY()) / i)));
        if (isValid(resultPoint5) == null) {
            return isValid(i) != null ? i : null;
        } else {
            if (isValid(i) == null) {
                return resultPoint5;
            }
            if (Math.abs(transitionsBetween(resultPoint3, resultPoint5).getTransitions() - transitionsBetween(resultPoint2, resultPoint5).getTransitions()) <= Math.abs(transitionsBetween(resultPoint3, i).getTransitions() - transitionsBetween(resultPoint2, i).getTransitions())) {
                i = resultPoint5;
            }
            return i;
        }
    }

    private boolean isValid(ResultPoint resultPoint) {
        return (resultPoint.getX() < 0.0f || resultPoint.getX() >= ((float) this.image.getWidth()) || resultPoint.getY() <= 0.0f || resultPoint.getY() >= ((float) this.image.getHeight())) ? null : true;
    }

    private static int distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.round(ResultPoint.distance(resultPoint, resultPoint2));
    }

    private static void increment(Map<ResultPoint, Integer> map, ResultPoint resultPoint) {
        Integer num = (Integer) map.get(resultPoint);
        int i = 1;
        if (num != null) {
            i = 1 + num.intValue();
        }
        map.put(resultPoint, Integer.valueOf(i));
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) throws NotFoundException {
        float f = ((float) i) - 0.5f;
        float f2 = ((float) i2) - 0.5f;
        return GridSampler.getInstance().sampleGrid(bitMatrix, i, i2, 0.5f, 0.5f, f, 0.5f, f, f2, 0.5f, f2, resultPoint.getX(), resultPoint.getY(), resultPoint4.getX(), resultPoint4.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private ResultPointsAndTransitions transitionsBetween(ResultPoint resultPoint, ResultPoint resultPoint2) {
        Detector detector = this;
        int x = (int) resultPoint.getX();
        int y = (int) resultPoint.getY();
        int x2 = (int) resultPoint2.getX();
        int y2 = (int) resultPoint2.getY();
        int i = 0;
        int i2 = 1;
        Object obj = Math.abs(y2 - y) > Math.abs(x2 - x) ? 1 : null;
        if (obj != null) {
            int i3 = y;
            y = x;
            x = i3;
            int i4 = y2;
            y2 = x2;
            x2 = i4;
        }
        int abs = Math.abs(x2 - x);
        int abs2 = Math.abs(y2 - y);
        int i5 = (-abs) / 2;
        int i6 = y < y2 ? 1 : -1;
        if (x >= x2) {
            i2 = -1;
        }
        boolean z = detector.image.get(obj != null ? y : x, obj != null ? x : y);
        while (x != x2) {
            boolean z2 = detector.image.get(obj != null ? y : x, obj != null ? x : y);
            if (z2 != z) {
                i++;
                z = z2;
            }
            i5 += abs2;
            if (i5 > 0) {
                if (y == y2) {
                    break;
                }
                y += i6;
                i5 -= abs;
            }
            x += i2;
        }
        return new ResultPointsAndTransitions(resultPoint, resultPoint2, i);
    }
}
