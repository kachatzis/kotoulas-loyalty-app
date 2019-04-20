package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.List;

final class AlignmentPatternFinder {
    private final int[] crossCheckStateCount;
    private final int height;
    private final BitMatrix image;
    private final float moduleSize;
    private final List<AlignmentPattern> possibleCenters = new ArrayList(5);
    private final ResultPointCallback resultPointCallback;
    private final int startX;
    private final int startY;
    private final int width;

    com.google.zxing.qrcode.detector.AlignmentPattern find() throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:41:0x009c in {4, 5, 10, 16, 22, 23, 24, 26, 27, 28, 33, 34, 38, 40} preds:[]
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
        r0 = r13.startX;
        r1 = r13.height;
        r2 = r13.width;
        r2 = r2 + r0;
        r3 = r13.startY;
        r4 = r1 / 2;
        r3 = r3 + r4;
        r4 = 3;
        r4 = new int[r4];
        r5 = 0;
        r6 = 0;
    L_0x0011:
        if (r6 >= r1) goto L_0x0086;
    L_0x0013:
        r7 = r6 & 1;
        r8 = 2;
        if (r7 != 0) goto L_0x001c;
    L_0x0018:
        r7 = r6 + 1;
        r7 = r7 / r8;
        goto L_0x0020;
    L_0x001c:
        r7 = r6 + 1;
        r7 = r7 / r8;
        r7 = -r7;
    L_0x0020:
        r7 = r7 + r3;
        r4[r5] = r5;
        r9 = 1;
        r4[r9] = r5;
        r4[r8] = r5;
        r10 = r0;
    L_0x0029:
        if (r10 >= r2) goto L_0x0036;
    L_0x002b:
        r11 = r13.image;
        r11 = r11.get(r10, r7);
        if (r11 != 0) goto L_0x0036;
    L_0x0033:
        r10 = r10 + 1;
        goto L_0x0029;
    L_0x0036:
        r11 = 0;
    L_0x0037:
        if (r10 >= r2) goto L_0x0076;
    L_0x0039:
        r12 = r13.image;
        r12 = r12.get(r10, r7);
        if (r12 == 0) goto L_0x006a;
    L_0x0041:
        if (r11 != r9) goto L_0x0049;
    L_0x0043:
        r12 = r4[r11];
        r12 = r12 + r9;
        r4[r11] = r12;
        goto L_0x0073;
    L_0x0049:
        if (r11 != r8) goto L_0x0062;
    L_0x004b:
        r11 = r13.foundPatternCross(r4);
        if (r11 == 0) goto L_0x0058;
    L_0x0051:
        r11 = r13.handlePossibleCenter(r4, r7, r10);
        if (r11 == 0) goto L_0x0058;
    L_0x0057:
        return r11;
    L_0x0058:
        r11 = r4[r8];
        r4[r5] = r11;
        r4[r9] = r9;
        r4[r8] = r5;
        r11 = 1;
        goto L_0x0073;
    L_0x0062:
        r11 = r11 + 1;
        r12 = r4[r11];
        r12 = r12 + r9;
        r4[r11] = r12;
        goto L_0x0073;
    L_0x006a:
        if (r11 != r9) goto L_0x006e;
    L_0x006c:
        r11 = r11 + 1;
    L_0x006e:
        r12 = r4[r11];
        r12 = r12 + r9;
        r4[r11] = r12;
    L_0x0073:
        r10 = r10 + 1;
        goto L_0x0037;
    L_0x0076:
        r8 = r13.foundPatternCross(r4);
        if (r8 == 0) goto L_0x0083;
    L_0x007c:
        r7 = r13.handlePossibleCenter(r4, r7, r2);
        if (r7 == 0) goto L_0x0083;
    L_0x0082:
        return r7;
    L_0x0083:
        r6 = r6 + 1;
        goto L_0x0011;
    L_0x0086:
        r0 = r13.possibleCenters;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0097;
    L_0x008e:
        r0 = r13.possibleCenters;
        r0 = r0.get(r5);
        r0 = (com.google.zxing.qrcode.detector.AlignmentPattern) r0;
        return r0;
    L_0x0097:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.AlignmentPatternFinder.find():com.google.zxing.qrcode.detector.AlignmentPattern");
    }

    AlignmentPatternFinder(BitMatrix bitMatrix, int i, int i2, int i3, int i4, float f, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.startX = i;
        this.startY = i2;
        this.width = i3;
        this.height = i4;
        this.moduleSize = f;
        this.crossCheckStateCount = new int[3];
        this.resultPointCallback = resultPointCallback;
    }

    private static float centerFromEnd(int[] iArr, int i) {
        return ((float) (i - iArr[2])) - (((float) iArr[1]) / 2.0f);
    }

    private boolean foundPatternCross(int[] iArr) {
        float f = this.moduleSize;
        float f2 = f / 2.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(f - ((float) iArr[i])) >= f2) {
                return false;
            }
        }
        return 1;
    }

    private float crossCheckVertical(int i, int i2, int i3, int i4) {
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] iArr = this.crossCheckStateCount;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        int i5 = i;
        while (i5 >= 0 && bitMatrix.get(i2, i5) && iArr[1] <= i3) {
            iArr[1] = iArr[1] + 1;
            i5--;
        }
        float f = Float.NaN;
        if (i5 >= 0) {
            if (iArr[1] <= i3) {
                while (i5 >= 0 && !bitMatrix.get(i2, i5) && iArr[0] <= i3) {
                    iArr[0] = iArr[0] + 1;
                    i5--;
                }
                if (iArr[0] > i3) {
                    return Float.NaN;
                }
                i++;
                while (i < height && bitMatrix.get(i2, i) && iArr[1] <= i3) {
                    iArr[1] = iArr[1] + 1;
                    i++;
                }
                if (i != height) {
                    if (iArr[1] <= i3) {
                        while (i < height && !bitMatrix.get(i2, i) && iArr[2] <= i3) {
                            iArr[2] = iArr[2] + 1;
                            i++;
                        }
                        if (iArr[2] > i3 || Math.abs(((iArr[0] + iArr[1]) + iArr[2]) - i4) * 5 >= i4 * 2) {
                            return Float.NaN;
                        }
                        if (foundPatternCross(iArr) != 0) {
                            f = centerFromEnd(iArr, i);
                        }
                        return f;
                    }
                }
                return Float.NaN;
            }
        }
        return Float.NaN;
    }

    private AlignmentPattern handlePossibleCenter(int[] iArr, int i, int i2) {
        int i3 = (iArr[0] + iArr[1]) + iArr[2];
        i2 = centerFromEnd(iArr, i2);
        i = crossCheckVertical(i, (int) i2, iArr[1] * 2, i3);
        if (!Float.isNaN(i)) {
            iArr = ((float) ((iArr[0] + iArr[1]) + iArr[2])) / 3.0f;
            for (AlignmentPattern alignmentPattern : this.possibleCenters) {
                if (alignmentPattern.aboutEquals(iArr, i, i2)) {
                    return alignmentPattern.combineEstimate(i, i2, iArr);
                }
            }
            ResultPoint alignmentPattern2 = new AlignmentPattern(i2, i, iArr);
            this.possibleCenters.add(alignmentPattern2);
            iArr = this.resultPointCallback;
            if (iArr != null) {
                iArr.foundPossibleResultPoint(alignmentPattern2);
            }
        }
        return null;
    }
}
