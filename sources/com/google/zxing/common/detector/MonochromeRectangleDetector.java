package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    private com.google.zxing.ResultPoint findCornerFromCenter(int r16, int r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:48:0x00a8 in {9, 10, 21, 22, 24, 26, 28, 35, 36, 38, 40, 42, 44, 45, 47} preds:[]
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
        r15 = this;
        r0 = r16;
        r1 = r20;
        r2 = 0;
        r10 = r0;
        r9 = r1;
        r11 = r2;
        r2 = r23;
    L_0x000a:
        if (r9 >= r2) goto L_0x00a3;
    L_0x000c:
        r12 = r22;
        if (r9 < r12) goto L_0x00a3;
    L_0x0010:
        r13 = r19;
        if (r10 >= r13) goto L_0x00a3;
    L_0x0014:
        r14 = r18;
        if (r10 < r14) goto L_0x00a3;
    L_0x0018:
        if (r17 != 0) goto L_0x0028;
    L_0x001a:
        r8 = 1;
        r3 = r15;
        r4 = r9;
        r5 = r24;
        r6 = r18;
        r7 = r19;
        r3 = r3.blackWhiteRange(r4, r5, r6, r7, r8);
        goto L_0x0035;
    L_0x0028:
        r8 = 0;
        r3 = r15;
        r4 = r10;
        r5 = r24;
        r6 = r22;
        r7 = r23;
        r3 = r3.blackWhiteRange(r4, r5, r6, r7, r8);
    L_0x0035:
        if (r3 != 0) goto L_0x009c;
    L_0x0037:
        if (r11 == 0) goto L_0x0097;
    L_0x0039:
        r2 = 1;
        r3 = 0;
        if (r17 != 0) goto L_0x006a;
    L_0x003d:
        r9 = r9 - r21;
        r1 = r11[r3];
        if (r1 >= r0) goto L_0x0060;
    L_0x0043:
        r1 = r11[r2];
        if (r1 <= r0) goto L_0x0056;
    L_0x0047:
        r0 = new com.google.zxing.ResultPoint;
        if (r21 <= 0) goto L_0x004e;
    L_0x004b:
        r1 = r11[r3];
        goto L_0x0050;
    L_0x004e:
        r1 = r11[r2];
    L_0x0050:
        r1 = (float) r1;
        r2 = (float) r9;
        r0.<init>(r1, r2);
        return r0;
    L_0x0056:
        r0 = new com.google.zxing.ResultPoint;
        r1 = r11[r3];
        r1 = (float) r1;
        r2 = (float) r9;
        r0.<init>(r1, r2);
        return r0;
    L_0x0060:
        r0 = new com.google.zxing.ResultPoint;
        r1 = r11[r2];
        r1 = (float) r1;
        r2 = (float) r9;
        r0.<init>(r1, r2);
        return r0;
    L_0x006a:
        r10 = r10 - r17;
        r0 = r11[r3];
        if (r0 >= r1) goto L_0x008d;
    L_0x0070:
        r0 = r11[r2];
        if (r0 <= r1) goto L_0x0083;
    L_0x0074:
        r0 = new com.google.zxing.ResultPoint;
        r1 = (float) r10;
        if (r17 >= 0) goto L_0x007c;
    L_0x0079:
        r2 = r11[r3];
        goto L_0x007e;
    L_0x007c:
        r2 = r11[r2];
    L_0x007e:
        r2 = (float) r2;
        r0.<init>(r1, r2);
        return r0;
    L_0x0083:
        r0 = new com.google.zxing.ResultPoint;
        r1 = (float) r10;
        r2 = r11[r3];
        r2 = (float) r2;
        r0.<init>(r1, r2);
        return r0;
    L_0x008d:
        r0 = new com.google.zxing.ResultPoint;
        r1 = (float) r10;
        r2 = r11[r2];
        r2 = (float) r2;
        r0.<init>(r1, r2);
        return r0;
    L_0x0097:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x009c:
        r9 = r9 + r21;
        r10 = r10 + r17;
        r11 = r3;
        goto L_0x000a;
    L_0x00a3:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.detector.MonochromeRectangleDetector.findCornerFromCenter(int, int, int, int, int, int, int, int, int):com.google.zxing.ResultPoint");
    }

    public MonochromeRectangleDetector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i = height / 2;
        int i2 = width / 2;
        int max = Math.max(1, height / 256);
        int i3 = -max;
        int i4 = i2 / 2;
        int i5 = i2;
        int i6 = width;
        int i7 = i;
        int i8 = i3;
        int max2 = Math.max(1, width / 256);
        int i9 = height;
        int i10 = max;
        max = max2;
        int i11 = -max;
        i3 = ((int) findCornerFromCenter(i5, 0, 0, i6, i7, i3, 0, i9, i4).getY()) - 1;
        int i12 = max;
        max = i / 2;
        int x = ((int) findCornerFromCenter(i5, i11, 0, i6, i7, 0, i3, i9, max).getX()) - 1;
        i6 = ((int) findCornerFromCenter(i5, i12, x, i6, i7, 0, i3, i9, max).getX()) + 1;
        int i13 = i8;
        ResultPoint findCornerFromCenter = findCornerFromCenter(i5, 0, x, i6, i7, i13, i3, ((int) findCornerFromCenter(i5, 0, x, i6, i7, i10, i3, i9, i4).getY()) + 1, i2 / 4);
        return new ResultPoint[]{findCornerFromCenter, r22, r12, r11};
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int[] blackWhiteRange(int r6, int r7, int r8, int r9, boolean r10) {
        /*
        r5 = this;
        r0 = r8 + r9;
        r1 = 2;
        r0 = r0 / r1;
        r2 = r0;
    L_0x0005:
        if (r2 < r8) goto L_0x003e;
    L_0x0007:
        if (r10 == 0) goto L_0x0012;
    L_0x0009:
        r3 = r5.image;
        r3 = r3.get(r2, r6);
        if (r3 == 0) goto L_0x001d;
    L_0x0011:
        goto L_0x001a;
    L_0x0012:
        r3 = r5.image;
        r3 = r3.get(r6, r2);
        if (r3 == 0) goto L_0x001d;
    L_0x001a:
        r2 = r2 + -1;
        goto L_0x0005;
    L_0x001d:
        r3 = r2;
    L_0x001e:
        r3 = r3 + -1;
        if (r3 < r8) goto L_0x0035;
    L_0x0022:
        if (r10 == 0) goto L_0x002d;
    L_0x0024:
        r4 = r5.image;
        r4 = r4.get(r3, r6);
        if (r4 == 0) goto L_0x001e;
    L_0x002c:
        goto L_0x0035;
    L_0x002d:
        r4 = r5.image;
        r4 = r4.get(r6, r3);
        if (r4 == 0) goto L_0x001e;
    L_0x0035:
        r4 = r2 - r3;
        if (r3 < r8) goto L_0x003e;
    L_0x0039:
        if (r4 <= r7) goto L_0x003c;
    L_0x003b:
        goto L_0x003e;
    L_0x003c:
        r2 = r3;
        goto L_0x0005;
    L_0x003e:
        r8 = 1;
        r2 = r2 + r8;
    L_0x0040:
        if (r0 >= r9) goto L_0x0078;
    L_0x0042:
        if (r10 == 0) goto L_0x004d;
    L_0x0044:
        r3 = r5.image;
        r3 = r3.get(r0, r6);
        if (r3 == 0) goto L_0x0058;
    L_0x004c:
        goto L_0x0055;
    L_0x004d:
        r3 = r5.image;
        r3 = r3.get(r6, r0);
        if (r3 == 0) goto L_0x0058;
    L_0x0055:
        r0 = r0 + 1;
        goto L_0x0040;
    L_0x0058:
        r3 = r0;
    L_0x0059:
        r3 = r3 + r8;
        if (r3 >= r9) goto L_0x006f;
    L_0x005c:
        if (r10 == 0) goto L_0x0067;
    L_0x005e:
        r4 = r5.image;
        r4 = r4.get(r3, r6);
        if (r4 == 0) goto L_0x0059;
    L_0x0066:
        goto L_0x006f;
    L_0x0067:
        r4 = r5.image;
        r4 = r4.get(r6, r3);
        if (r4 == 0) goto L_0x0059;
    L_0x006f:
        r4 = r3 - r0;
        if (r3 >= r9) goto L_0x0078;
    L_0x0073:
        if (r4 <= r7) goto L_0x0076;
    L_0x0075:
        goto L_0x0078;
    L_0x0076:
        r0 = r3;
        goto L_0x0040;
    L_0x0078:
        r0 = r0 + -1;
        if (r0 <= r2) goto L_0x0084;
    L_0x007c:
        r6 = new int[r1];
        r7 = 0;
        r6[r7] = r2;
        r6[r8] = r0;
        goto L_0x0085;
    L_0x0084:
        r6 = 0;
    L_0x0085:
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.detector.MonochromeRectangleDetector.blackWhiteRange(int, int, int, int, boolean):int[]");
    }
}
