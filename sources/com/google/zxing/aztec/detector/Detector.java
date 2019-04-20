package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;

public final class Detector {
    private static final int[] EXPECTED_CORNER_BITS = new int[]{3808, 476, 2107, 1799};
    private boolean compact;
    private final BitMatrix image;
    private int nbCenterLayers;
    private int nbDataBlocks;
    private int nbLayers;
    private int shift;

    static final class Point {
        /* renamed from: x */
        private final int f15x;
        /* renamed from: y */
        private final int f16y;

        ResultPoint toResultPoint() {
            return new ResultPoint((float) getX(), (float) getY());
        }

        Point(int i, int i2) {
            this.f15x = i;
            this.f16y = i2;
        }

        int getX() {
            return this.f15x;
        }

        int getY() {
            return this.f16y;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<");
            stringBuilder.append(this.f15x);
            stringBuilder.append(' ');
            stringBuilder.append(this.f16y);
            stringBuilder.append('>');
            return stringBuilder.toString();
        }
    }

    private void extractParameters(com.google.zxing.ResultPoint[] r10) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:22:0x00a6 in {12, 13, 14, 17, 18, 19, 21} preds:[]
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
        r9 = this;
        r0 = 0;
        r1 = r10[r0];
        r1 = r9.isValid(r1);
        if (r1 == 0) goto L_0x00a1;
    L_0x0009:
        r1 = 1;
        r2 = r10[r1];
        r2 = r9.isValid(r2);
        if (r2 == 0) goto L_0x00a1;
    L_0x0012:
        r2 = 2;
        r3 = r10[r2];
        r3 = r9.isValid(r3);
        if (r3 == 0) goto L_0x00a1;
    L_0x001b:
        r3 = 3;
        r4 = r10[r3];
        r4 = r9.isValid(r4);
        if (r4 == 0) goto L_0x00a1;
    L_0x0024:
        r4 = r9.nbCenterLayers;
        r4 = r4 * 2;
        r5 = 4;
        r6 = new int[r5];
        r7 = r10[r0];
        r8 = r10[r1];
        r7 = r9.sampleLine(r7, r8, r4);
        r6[r0] = r7;
        r7 = r10[r1];
        r8 = r10[r2];
        r7 = r9.sampleLine(r7, r8, r4);
        r6[r1] = r7;
        r7 = r10[r2];
        r8 = r10[r3];
        r7 = r9.sampleLine(r7, r8, r4);
        r6[r2] = r7;
        r2 = r10[r3];
        r10 = r10[r0];
        r10 = r9.sampleLine(r2, r10, r4);
        r6[r3] = r10;
        r10 = getRotation(r6, r4);
        r9.shift = r10;
        r2 = 0;
    L_0x005b:
        if (r0 >= r5) goto L_0x0081;
    L_0x005d:
        r10 = r9.shift;
        r10 = r10 + r0;
        r10 = r10 % r5;
        r10 = r6[r10];
        r4 = r9.compact;
        if (r4 == 0) goto L_0x0070;
    L_0x0067:
        r4 = 7;
        r2 = r2 << r4;
        r10 = r10 >> 1;
        r10 = r10 & 127;
        r7 = (long) r10;
        r2 = r2 + r7;
        goto L_0x007e;
    L_0x0070:
        r4 = 10;
        r2 = r2 << r4;
        r4 = r10 >> 2;
        r4 = r4 & 992;
        r10 = r10 >> 1;
        r10 = r10 & 31;
        r4 = r4 + r10;
        r7 = (long) r4;
        r2 = r2 + r7;
    L_0x007e:
        r0 = r0 + 1;
        goto L_0x005b;
    L_0x0081:
        r10 = r9.compact;
        r10 = getCorrectedParameterData(r2, r10);
        r0 = r9.compact;
        if (r0 == 0) goto L_0x0096;
    L_0x008b:
        r0 = r10 >> 6;
        r0 = r0 + r1;
        r9.nbLayers = r0;
        r10 = r10 & 63;
        r10 = r10 + r1;
        r9.nbDataBlocks = r10;
        goto L_0x00a0;
    L_0x0096:
        r0 = r10 >> 11;
        r0 = r0 + r1;
        r9.nbLayers = r0;
        r10 = r10 & 2047;
        r10 = r10 + r1;
        r9.nbDataBlocks = r10;
    L_0x00a0:
        return;
    L_0x00a1:
        r10 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.detector.Detector.extractParameters(com.google.zxing.ResultPoint[]):void");
    }

    private static int getCorrectedParameterData(long r5, boolean r7) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:15:0x0036 in {2, 3, 6, 11, 12, 14} preds:[]
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
        r0 = 4;
        if (r7 == 0) goto L_0x0006;
    L_0x0003:
        r7 = 7;
        r1 = 2;
        goto L_0x0009;
    L_0x0006:
        r7 = 10;
        r1 = 4;
    L_0x0009:
        r2 = r7 - r1;
        r3 = new int[r7];
        r7 = r7 + -1;
    L_0x000f:
        if (r7 < 0) goto L_0x001a;
    L_0x0011:
        r4 = (int) r5;
        r4 = r4 & 15;
        r3[r7] = r4;
        r5 = r5 >> r0;
        r7 = r7 + -1;
        goto L_0x000f;
    L_0x001a:
        r5 = new com.google.zxing.common.reedsolomon.ReedSolomonDecoder;	 Catch:{ ReedSolomonException -> 0x0031 }
        r6 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_PARAM;	 Catch:{ ReedSolomonException -> 0x0031 }
        r5.<init>(r6);	 Catch:{ ReedSolomonException -> 0x0031 }
        r5.decode(r3, r2);	 Catch:{ ReedSolomonException -> 0x0031 }
        r5 = 0;
        r6 = 0;
    L_0x0026:
        if (r5 >= r1) goto L_0x0030;
    L_0x0028:
        r6 = r6 << 4;
        r7 = r3[r5];
        r6 = r6 + r7;
        r5 = r5 + 1;
        goto L_0x0026;
    L_0x0030:
        return r6;
    L_0x0031:
        r5 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.detector.Detector.getCorrectedParameterData(long, boolean):int");
    }

    private static int getRotation(int[] r6, int r7) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:13:0x0036 in {3, 9, 10, 12} preds:[]
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
        r0 = r6.length;
        r1 = 0;
        r2 = 0;
        r3 = 0;
    L_0x0004:
        r4 = 2;
        if (r2 >= r0) goto L_0x0018;
    L_0x0007:
        r5 = r6[r2];
        r4 = r7 + -2;
        r4 = r5 >> r4;
        r4 = r4 << 1;
        r5 = r5 & 1;
        r4 = r4 + r5;
        r3 = r3 << 3;
        r3 = r3 + r4;
        r2 = r2 + 1;
        goto L_0x0004;
    L_0x0018:
        r6 = r3 & 1;
        r6 = r6 << 11;
        r7 = r3 >> 1;
        r6 = r6 + r7;
    L_0x001f:
        r7 = 4;
        if (r1 >= r7) goto L_0x0031;
    L_0x0022:
        r7 = EXPECTED_CORNER_BITS;
        r7 = r7[r1];
        r7 = r7 ^ r6;
        r7 = java.lang.Integer.bitCount(r7);
        if (r7 > r4) goto L_0x002e;
    L_0x002d:
        return r1;
    L_0x002e:
        r1 = r1 + 1;
        goto L_0x001f;
    L_0x0031:
        r6 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.detector.Detector.getRotation(int[], int):int");
    }

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    public AztecDetectorResult detect() throws NotFoundException {
        return detect(false);
    }

    public AztecDetectorResult detect(boolean z) throws NotFoundException {
        ResultPoint[] bullsEyeCorners = getBullsEyeCorners(getMatrixCenter());
        if (z) {
            ResultPoint resultPoint = bullsEyeCorners[0];
            bullsEyeCorners[0] = bullsEyeCorners[2];
            bullsEyeCorners[2] = resultPoint;
        }
        extractParameters(bullsEyeCorners);
        BitMatrix bitMatrix = this.image;
        int i = this.shift;
        return new AztecDetectorResult(sampleGrid(bitMatrix, bullsEyeCorners[i % 4], bullsEyeCorners[(i + 1) % 4], bullsEyeCorners[(i + 2) % 4], bullsEyeCorners[(i + 3) % 4]), getMatrixCornerPoints(bullsEyeCorners), this.compact, this.nbDataBlocks, this.nbLayers);
    }

    private ResultPoint[] getBullsEyeCorners(Point point) throws NotFoundException {
        this.nbCenterLayers = 1;
        Point point2 = point;
        Point point3 = point2;
        Point point4 = point3;
        Point point5 = point4;
        boolean z = true;
        while (r0.nbCenterLayers < 9) {
            Point firstDifferent = getFirstDifferent(point2, z, 1, -1);
            Point firstDifferent2 = getFirstDifferent(point3, z, 1, 1);
            Point firstDifferent3 = getFirstDifferent(point4, z, -1, 1);
            Point firstDifferent4 = getFirstDifferent(point5, z, -1, -1);
            if (r0.nbCenterLayers > 2) {
                double distance = (double) ((distance(firstDifferent4, firstDifferent) * ((float) r0.nbCenterLayers)) / (distance(point5, point2) * ((float) (r0.nbCenterLayers + 2))));
                if (distance < 0.75d || distance > 1.25d) {
                    break;
                } else if (!isWhiteOrBlackRectangle(firstDifferent, firstDifferent2, firstDifferent3, firstDifferent4)) {
                    break;
                }
            }
            z ^= 1;
            r0.nbCenterLayers++;
            point5 = firstDifferent4;
            point2 = firstDifferent;
            point3 = firstDifferent2;
            point4 = firstDifferent3;
        }
        int i = r0.nbCenterLayers;
        if (i != 5) {
            if (i != 7) {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        r0.compact = r0.nbCenterLayers == 5;
        ResultPoint resultPoint = new ResultPoint(((float) point2.getX()) + 0.5f, ((float) point2.getY()) - 0.5f);
        ResultPoint resultPoint2 = new ResultPoint(((float) point3.getX()) + 0.5f, ((float) point3.getY()) + 0.5f);
        ResultPoint resultPoint3 = new ResultPoint(((float) point4.getX()) - 0.5f, ((float) point4.getY()) + 0.5f);
        ResultPoint resultPoint4 = new ResultPoint(((float) point5.getX()) - 0.5f, ((float) point5.getY()) - 0.5f);
        ResultPoint[] resultPointArr = new ResultPoint[]{resultPoint, resultPoint2, resultPoint3, resultPoint4};
        int i2 = r0.nbCenterLayers;
        return expandSquare(resultPointArr, (float) ((i2 * 2) - 3), (float) (i2 * 2));
    }

    private com.google.zxing.aztec.detector.Detector.Point getMatrixCenter() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r12 = this;
        r0 = 3;
        r1 = 2;
        r2 = -1;
        r3 = 1;
        r4 = 0;
        r5 = new com.google.zxing.common.detector.WhiteRectangleDetector;	 Catch:{ NotFoundException -> 0x0019 }
        r6 = r12.image;	 Catch:{ NotFoundException -> 0x0019 }
        r5.<init>(r6);	 Catch:{ NotFoundException -> 0x0019 }
        r5 = r5.detect();	 Catch:{ NotFoundException -> 0x0019 }
        r6 = r5[r4];	 Catch:{ NotFoundException -> 0x0019 }
        r7 = r5[r3];	 Catch:{ NotFoundException -> 0x0019 }
        r8 = r5[r1];	 Catch:{ NotFoundException -> 0x0019 }
        r5 = r5[r0];	 Catch:{ NotFoundException -> 0x0019 }
        goto L_0x0067;
    L_0x0019:
        r5 = r12.image;
        r5 = r5.getWidth();
        r5 = r5 / r1;
        r6 = r12.image;
        r6 = r6.getHeight();
        r6 = r6 / r1;
        r7 = new com.google.zxing.aztec.detector.Detector$Point;
        r8 = r5 + 7;
        r9 = r6 + -7;
        r7.<init>(r8, r9);
        r7 = r12.getFirstDifferent(r7, r4, r3, r2);
        r7 = r7.toResultPoint();
        r10 = new com.google.zxing.aztec.detector.Detector$Point;
        r6 = r6 + 7;
        r10.<init>(r8, r6);
        r8 = r12.getFirstDifferent(r10, r4, r3, r3);
        r8 = r8.toResultPoint();
        r10 = new com.google.zxing.aztec.detector.Detector$Point;
        r5 = r5 + -7;
        r10.<init>(r5, r6);
        r6 = r12.getFirstDifferent(r10, r4, r2, r3);
        r6 = r6.toResultPoint();
        r10 = new com.google.zxing.aztec.detector.Detector$Point;
        r10.<init>(r5, r9);
        r5 = r12.getFirstDifferent(r10, r4, r2, r2);
        r5 = r5.toResultPoint();
        r11 = r8;
        r8 = r6;
        r6 = r7;
        r7 = r11;
    L_0x0067:
        r9 = r6.getX();
        r10 = r5.getX();
        r9 = r9 + r10;
        r10 = r7.getX();
        r9 = r9 + r10;
        r10 = r8.getX();
        r9 = r9 + r10;
        r10 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r9 = r9 / r10;
        r9 = com.google.zxing.common.detector.MathUtils.round(r9);
        r6 = r6.getY();
        r5 = r5.getY();
        r6 = r6 + r5;
        r5 = r7.getY();
        r6 = r6 + r5;
        r5 = r8.getY();
        r6 = r6 + r5;
        r6 = r6 / r10;
        r5 = com.google.zxing.common.detector.MathUtils.round(r6);
        r6 = new com.google.zxing.common.detector.WhiteRectangleDetector;	 Catch:{ NotFoundException -> 0x00af }
        r7 = r12.image;	 Catch:{ NotFoundException -> 0x00af }
        r8 = 15;	 Catch:{ NotFoundException -> 0x00af }
        r6.<init>(r7, r8, r9, r5);	 Catch:{ NotFoundException -> 0x00af }
        r6 = r6.detect();	 Catch:{ NotFoundException -> 0x00af }
        r7 = r6[r4];	 Catch:{ NotFoundException -> 0x00af }
        r8 = r6[r3];	 Catch:{ NotFoundException -> 0x00af }
        r1 = r6[r1];	 Catch:{ NotFoundException -> 0x00af }
        r0 = r6[r0];	 Catch:{ NotFoundException -> 0x00af }
        goto L_0x00eb;
    L_0x00af:
        r0 = new com.google.zxing.aztec.detector.Detector$Point;
        r1 = r9 + 7;
        r6 = r5 + -7;
        r0.<init>(r1, r6);
        r0 = r12.getFirstDifferent(r0, r4, r3, r2);
        r7 = r0.toResultPoint();
        r0 = new com.google.zxing.aztec.detector.Detector$Point;
        r5 = r5 + 7;
        r0.<init>(r1, r5);
        r0 = r12.getFirstDifferent(r0, r4, r3, r3);
        r8 = r0.toResultPoint();
        r0 = new com.google.zxing.aztec.detector.Detector$Point;
        r9 = r9 + -7;
        r0.<init>(r9, r5);
        r0 = r12.getFirstDifferent(r0, r4, r2, r3);
        r1 = r0.toResultPoint();
        r0 = new com.google.zxing.aztec.detector.Detector$Point;
        r0.<init>(r9, r6);
        r0 = r12.getFirstDifferent(r0, r4, r2, r2);
        r0 = r0.toResultPoint();
    L_0x00eb:
        r2 = r7.getX();
        r3 = r0.getX();
        r2 = r2 + r3;
        r3 = r8.getX();
        r2 = r2 + r3;
        r3 = r1.getX();
        r2 = r2 + r3;
        r2 = r2 / r10;
        r2 = com.google.zxing.common.detector.MathUtils.round(r2);
        r3 = r7.getY();
        r0 = r0.getY();
        r3 = r3 + r0;
        r0 = r8.getY();
        r3 = r3 + r0;
        r0 = r1.getY();
        r3 = r3 + r0;
        r3 = r3 / r10;
        r0 = com.google.zxing.common.detector.MathUtils.round(r3);
        r1 = new com.google.zxing.aztec.detector.Detector$Point;
        r1.<init>(r2, r0);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.detector.Detector.getMatrixCenter():com.google.zxing.aztec.detector.Detector$Point");
    }

    private ResultPoint[] getMatrixCornerPoints(ResultPoint[] resultPointArr) {
        return expandSquare(resultPointArr, (float) (this.nbCenterLayers * 2), (float) getDimension());
    }

    private BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        BitMatrix bitMatrix2 = bitMatrix;
        GridSampler instance = GridSampler.getInstance();
        int dimension = getDimension();
        int i = dimension;
        int i2 = dimension;
        float f = ((float) dimension) / 2.0f;
        int i3 = this.nbCenterLayers;
        float f2 = f - ((float) i3);
        f += (float) i3;
        return instance.sampleGrid(bitMatrix2, i2, i, f2, f2, f, f2, f, f, f2, f, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint4.getX(), resultPoint4.getY());
    }

    private int sampleLine(ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
        float distance = distance(resultPoint, resultPoint2);
        float f = distance / ((float) i);
        float x = resultPoint.getX();
        float y = resultPoint.getY();
        float x2 = ((resultPoint2.getX() - resultPoint.getX()) * f) / distance;
        f = (f * (resultPoint2.getY() - resultPoint.getY())) / distance;
        resultPoint2 = null;
        for (resultPoint = null; resultPoint < i; resultPoint++) {
            float f2 = (float) resultPoint;
            if (this.image.get(MathUtils.round((f2 * x2) + x), MathUtils.round((f2 * f) + y))) {
                resultPoint2 |= 1 << ((i - resultPoint) - 1);
            }
        }
        return resultPoint2;
    }

    private boolean isWhiteOrBlackRectangle(Point point, Point point2, Point point3, Point point4) {
        Point point5 = new Point(point.getX() - 3, point.getY() + 3);
        point = new Point(point2.getX() - 3, point2.getY() - 3);
        point2 = new Point(point3.getX() + 3, point3.getY() - 3);
        point3 = new Point(point4.getX() + 3, point4.getY() + 3);
        point4 = getColor(point3, point5);
        boolean z = false;
        if (point4 == null || getColor(point5, point) != point4 || getColor(point, point2) != point4) {
            return false;
        }
        if (getColor(point2, point3) == point4) {
            z = true;
        }
        return z;
    }

    private int getColor(Point point, Point point2) {
        float distance = distance(point, point2);
        float x = ((float) (point2.getX() - point.getX())) / distance;
        point2 = ((float) (point2.getY() - point.getY())) / distance;
        float x2 = (float) point.getX();
        float y = (float) point.getY();
        point = this.image.get(point.getX(), point.getY());
        int i = 0;
        float f = x2;
        float f2 = y;
        int i2 = 0;
        for (int i3 = 0; ((float) i3) < distance; i3++) {
            f += x;
            f2 += point2;
            if (this.image.get(MathUtils.round(f), MathUtils.round(f2)) != point) {
                i2++;
            }
        }
        point2 = ((float) i2) / distance;
        if (point2 > 1036831949 && point2 < 0.9f) {
            return 0;
        }
        int i4 = 1;
        if (point2 <= 1036831949) {
            i = 1;
        }
        if (i != point) {
            i4 = -1;
        }
        return i4;
    }

    private Point getFirstDifferent(Point point, boolean z, int i, int i2) {
        int x = point.getX() + i;
        point = point.getY() + i2;
        while (isValid(x, point) && this.image.get(x, point) == z) {
            x += i;
            point += i2;
        }
        x -= i;
        point -= i2;
        while (isValid(x, point) && this.image.get(x, point) == z) {
            x += i;
        }
        x -= i;
        while (isValid(x, point) != 0 && this.image.get(x, point) == z) {
            point += i2;
        }
        return new Point(x, point - i2);
    }

    private static ResultPoint[] expandSquare(ResultPoint[] resultPointArr, float f, float f2) {
        f2 /= f * 2.0f;
        float x = (resultPointArr[0].getX() + resultPointArr[2].getX()) / 2.0f;
        float y = (resultPointArr[0].getY() + resultPointArr[2].getY()) / 2.0f;
        float x2 = (resultPointArr[0].getX() - resultPointArr[2].getX()) * f2;
        float y2 = (resultPointArr[0].getY() - resultPointArr[2].getY()) * f2;
        ResultPoint resultPoint = new ResultPoint(x + x2, y + y2);
        ResultPoint resultPoint2 = new ResultPoint(x - x2, y - y2);
        float x3 = (resultPointArr[1].getX() + resultPointArr[3].getX()) / 2.0f;
        float y3 = (resultPointArr[1].getY() + resultPointArr[3].getY()) / 2.0f;
        y2 = (resultPointArr[1].getX() - resultPointArr[3].getX()) * f2;
        f2 *= resultPointArr[1].getY() - resultPointArr[3].getY();
        resultPointArr = new ResultPoint(x3 + y2, y3 + f2);
        ResultPoint resultPoint3 = new ResultPoint(x3 - y2, y3 - f2);
        return new ResultPoint[]{resultPoint, resultPointArr, resultPoint2, resultPoint3};
    }

    private boolean isValid(int i, int i2) {
        return i >= 0 && i < this.image.getWidth() && i2 > 0 && i2 < this.image.getHeight();
    }

    private boolean isValid(ResultPoint resultPoint) {
        return isValid(MathUtils.round(resultPoint.getX()), MathUtils.round(resultPoint.getY()));
    }

    private static float distance(Point point, Point point2) {
        return MathUtils.distance(point.getX(), point.getY(), point2.getX(), point2.getY());
    }

    private static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private int getDimension() {
        if (this.compact) {
            return (this.nbLayers * 4) + 11;
        }
        int i = this.nbLayers;
        if (i <= 4) {
            return (i * 4) + 15;
        }
        return ((i * 4) + ((((i - 4) / 8) + 1) * 2)) + 15;
    }
}
