package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class WhiteRectangleDetector {
    private static final int CORR = 1;
    private static final int INIT_SIZE = 10;
    private final int downInit;
    private final int height;
    private final BitMatrix image;
    private final int leftInit;
    private final int rightInit;
    private final int upInit;
    private final int width;

    public com.google.zxing.ResultPoint[] detect() throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:94:0x0111 in {4, 9, 11, 14, 17, 22, 24, 27, 30, 34, 36, 38, 41, 45, 47, 49, 51, 52, 59, 60, 66, 67, 73, 74, 79, 80, 83, 85, 87, 89, 91, 93} preds:[]
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
        r13 = this;
        r0 = r13.leftInit;
        r1 = r13.rightInit;
        r2 = r13.upInit;
        r3 = r13.downInit;
        r4 = 0;
        r5 = 1;
        r7 = r0;
        r0 = 1;
        r6 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r11 = 0;
    L_0x0011:
        if (r0 == 0) goto L_0x008e;
    L_0x0013:
        r0 = 1;
        r12 = 0;
    L_0x0015:
        if (r0 != 0) goto L_0x0019;
    L_0x0017:
        if (r6 != 0) goto L_0x002d;
    L_0x0019:
        r0 = r13.width;
        if (r1 >= r0) goto L_0x002d;
    L_0x001d:
        r0 = r13.containsBlackPoint(r2, r3, r1, r4);
        if (r0 == 0) goto L_0x0028;
    L_0x0023:
        r1 = r1 + 1;
        r6 = 1;
        r12 = 1;
        goto L_0x0015;
    L_0x0028:
        if (r6 != 0) goto L_0x0015;
    L_0x002a:
        r1 = r1 + 1;
        goto L_0x0015;
    L_0x002d:
        r0 = r13.width;
        if (r1 < r0) goto L_0x0034;
    L_0x0031:
        r4 = 1;
        goto L_0x008e;
    L_0x0034:
        r0 = 1;
    L_0x0035:
        if (r0 != 0) goto L_0x0039;
    L_0x0037:
        if (r8 != 0) goto L_0x004d;
    L_0x0039:
        r0 = r13.height;
        if (r3 >= r0) goto L_0x004d;
    L_0x003d:
        r0 = r13.containsBlackPoint(r7, r1, r3, r5);
        if (r0 == 0) goto L_0x0048;
    L_0x0043:
        r3 = r3 + 1;
        r8 = 1;
        r12 = 1;
        goto L_0x0035;
    L_0x0048:
        if (r8 != 0) goto L_0x0035;
    L_0x004a:
        r3 = r3 + 1;
        goto L_0x0035;
    L_0x004d:
        r0 = r13.height;
        if (r3 < r0) goto L_0x0053;
    L_0x0051:
        r4 = 1;
        goto L_0x008e;
    L_0x0053:
        r0 = 1;
    L_0x0054:
        if (r0 != 0) goto L_0x0058;
    L_0x0056:
        if (r9 != 0) goto L_0x006a;
    L_0x0058:
        if (r7 < 0) goto L_0x006a;
    L_0x005a:
        r0 = r13.containsBlackPoint(r2, r3, r7, r4);
        if (r0 == 0) goto L_0x0065;
    L_0x0060:
        r7 = r7 + -1;
        r9 = 1;
        r12 = 1;
        goto L_0x0054;
    L_0x0065:
        if (r9 != 0) goto L_0x0054;
    L_0x0067:
        r7 = r7 + -1;
        goto L_0x0054;
    L_0x006a:
        if (r7 >= 0) goto L_0x006e;
    L_0x006c:
        r4 = 1;
        goto L_0x008e;
    L_0x006e:
        r0 = 1;
    L_0x006f:
        if (r0 != 0) goto L_0x0073;
    L_0x0071:
        if (r11 != 0) goto L_0x0085;
    L_0x0073:
        if (r2 < 0) goto L_0x0085;
    L_0x0075:
        r0 = r13.containsBlackPoint(r7, r1, r2, r5);
        if (r0 == 0) goto L_0x0080;
    L_0x007b:
        r2 = r2 + -1;
        r11 = 1;
        r12 = 1;
        goto L_0x006f;
    L_0x0080:
        if (r11 != 0) goto L_0x006f;
    L_0x0082:
        r2 = r2 + -1;
        goto L_0x006f;
    L_0x0085:
        if (r2 >= 0) goto L_0x0089;
    L_0x0087:
        r4 = 1;
        goto L_0x008e;
    L_0x0089:
        if (r12 == 0) goto L_0x008c;
    L_0x008b:
        r10 = 1;
    L_0x008c:
        r0 = r12;
        goto L_0x0011;
    L_0x008e:
        if (r4 != 0) goto L_0x010c;
    L_0x0090:
        if (r10 == 0) goto L_0x010c;
    L_0x0092:
        r0 = r1 - r7;
        r4 = 0;
        r8 = r4;
        r6 = 1;
    L_0x0097:
        if (r6 >= r0) goto L_0x00ab;
    L_0x0099:
        r8 = (float) r7;
        r9 = r3 - r6;
        r9 = (float) r9;
        r10 = r7 + r6;
        r10 = (float) r10;
        r11 = (float) r3;
        r8 = r13.getBlackPointOnSegment(r8, r9, r10, r11);
        if (r8 == 0) goto L_0x00a8;
    L_0x00a7:
        goto L_0x00ab;
    L_0x00a8:
        r6 = r6 + 1;
        goto L_0x0097;
    L_0x00ab:
        if (r8 == 0) goto L_0x0107;
    L_0x00ad:
        r9 = r4;
        r6 = 1;
    L_0x00af:
        if (r6 >= r0) goto L_0x00c3;
    L_0x00b1:
        r9 = (float) r7;
        r10 = r2 + r6;
        r10 = (float) r10;
        r11 = r7 + r6;
        r11 = (float) r11;
        r12 = (float) r2;
        r9 = r13.getBlackPointOnSegment(r9, r10, r11, r12);
        if (r9 == 0) goto L_0x00c0;
    L_0x00bf:
        goto L_0x00c3;
    L_0x00c0:
        r6 = r6 + 1;
        goto L_0x00af;
    L_0x00c3:
        if (r9 == 0) goto L_0x0102;
    L_0x00c5:
        r7 = r4;
        r6 = 1;
    L_0x00c7:
        if (r6 >= r0) goto L_0x00db;
    L_0x00c9:
        r7 = (float) r1;
        r10 = r2 + r6;
        r10 = (float) r10;
        r11 = r1 - r6;
        r11 = (float) r11;
        r12 = (float) r2;
        r7 = r13.getBlackPointOnSegment(r7, r10, r11, r12);
        if (r7 == 0) goto L_0x00d8;
    L_0x00d7:
        goto L_0x00db;
    L_0x00d8:
        r6 = r6 + 1;
        goto L_0x00c7;
    L_0x00db:
        if (r7 == 0) goto L_0x00fd;
    L_0x00dd:
        if (r5 >= r0) goto L_0x00f1;
    L_0x00df:
        r2 = (float) r1;
        r4 = r3 - r5;
        r4 = (float) r4;
        r6 = r1 - r5;
        r6 = (float) r6;
        r10 = (float) r3;
        r4 = r13.getBlackPointOnSegment(r2, r4, r6, r10);
        if (r4 == 0) goto L_0x00ee;
    L_0x00ed:
        goto L_0x00f1;
    L_0x00ee:
        r5 = r5 + 1;
        goto L_0x00dd;
    L_0x00f1:
        if (r4 == 0) goto L_0x00f8;
    L_0x00f3:
        r0 = r13.centerEdges(r4, r8, r7, r9);
        return r0;
    L_0x00f8:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x00fd:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0102:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0107:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x010c:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.detector.WhiteRectangleDetector.detect():com.google.zxing.ResultPoint[]");
    }

    public WhiteRectangleDetector(BitMatrix bitMatrix) throws NotFoundException {
        this(bitMatrix, 10, bitMatrix.getWidth() / 2, bitMatrix.getHeight() / 2);
    }

    public WhiteRectangleDetector(BitMatrix bitMatrix, int i, int i2, int i3) throws NotFoundException {
        this.image = bitMatrix;
        this.height = bitMatrix.getHeight();
        this.width = bitMatrix.getWidth();
        i /= 2;
        this.leftInit = i2 - i;
        this.rightInit = i2 + i;
        this.upInit = i3 - i;
        this.downInit = i3 + i;
        if (this.upInit < null || this.leftInit < null || this.downInit >= this.height || this.rightInit >= this.width) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private ResultPoint getBlackPointOnSegment(float f, float f2, float f3, float f4) {
        int round = MathUtils.round(MathUtils.distance(f, f2, f3, f4));
        float f5 = (float) round;
        f3 = (f3 - f) / f5;
        f4 = (f4 - f2) / f5;
        for (int i = 0; i < round; i++) {
            float f6 = (float) i;
            int round2 = MathUtils.round((f6 * f3) + f);
            int round3 = MathUtils.round((f6 * f4) + f2);
            if (this.image.get(round2, round3)) {
                return new ResultPoint((float) round2, (float) round3);
            }
        }
        return 0.0f;
    }

    private ResultPoint[] centerEdges(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) {
        float x = resultPoint.getX();
        resultPoint = resultPoint.getY();
        float x2 = resultPoint2.getX();
        resultPoint2 = resultPoint2.getY();
        float x3 = resultPoint3.getX();
        resultPoint3 = resultPoint3.getY();
        float x4 = resultPoint4.getX();
        resultPoint4 = resultPoint4.getY();
        if (x < ((float) this.width) / 2.0f) {
            return new ResultPoint[]{new ResultPoint(x4 - 1.0f, resultPoint4 + 1065353216), new ResultPoint(x2 + 1.0f, resultPoint2 + 1065353216), new ResultPoint(x3 - 1.0f, resultPoint3 - 1065353216), new ResultPoint(x + 1.0f, resultPoint - 1065353216)};
        }
        return new ResultPoint[]{new ResultPoint(x4 + 1.0f, resultPoint4 + 1065353216), new ResultPoint(x2 + 1.0f, resultPoint2 - 1065353216), new ResultPoint(x3 - 1.0f, resultPoint3 + 1065353216), new ResultPoint(x - 1.0f, resultPoint - 1065353216)};
    }

    private boolean containsBlackPoint(int i, int i2, int i3, boolean z) {
        if (z) {
            while (i <= i2) {
                if (this.image.get(i, i3)) {
                    return true;
                }
                i++;
            }
        } else {
            while (i <= i2) {
                if (this.image.get(i3, i)) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }
}
