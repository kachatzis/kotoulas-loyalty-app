package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;

public class GlobalHistogramBinarizer extends Binarizer {
    private static final byte[] EMPTY = new byte[0];
    private static final int LUMINANCE_BITS = 5;
    private static final int LUMINANCE_BUCKETS = 32;
    private static final int LUMINANCE_SHIFT = 3;
    private final int[] buckets = new int[32];
    private byte[] luminances = EMPTY;

    private static int estimateBlackPoint(int[] r9) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:29:0x005a in {4, 7, 8, 13, 14, 16, 23, 24, 26, 28} preds:[]
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
        r0 = r9.length;
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
    L_0x0006:
        if (r2 >= r0) goto L_0x0018;
    L_0x0008:
        r6 = r9[r2];
        if (r6 <= r3) goto L_0x000f;
    L_0x000c:
        r3 = r9[r2];
        r5 = r2;
    L_0x000f:
        r6 = r9[r2];
        if (r6 <= r4) goto L_0x0015;
    L_0x0013:
        r4 = r9[r2];
    L_0x0015:
        r2 = r2 + 1;
        goto L_0x0006;
    L_0x0018:
        r2 = 0;
        r3 = 0;
    L_0x001a:
        if (r1 >= r0) goto L_0x002b;
    L_0x001c:
        r6 = r1 - r5;
        r7 = r9[r1];
        r7 = r7 * r6;
        r7 = r7 * r6;
        if (r7 <= r3) goto L_0x0028;
    L_0x0026:
        r2 = r1;
        r3 = r7;
    L_0x0028:
        r1 = r1 + 1;
        goto L_0x001a;
    L_0x002b:
        if (r5 <= r2) goto L_0x0030;
    L_0x002d:
        r8 = r5;
        r5 = r2;
        r2 = r8;
    L_0x0030:
        r1 = r2 - r5;
        r0 = r0 / 16;
        if (r1 <= r0) goto L_0x0055;
    L_0x0036:
        r0 = r2 + -1;
        r1 = -1;
        r1 = r0;
        r3 = -1;
    L_0x003b:
        if (r0 <= r5) goto L_0x0052;
    L_0x003d:
        r6 = r0 - r5;
        r6 = r6 * r6;
        r7 = r2 - r0;
        r6 = r6 * r7;
        r7 = r9[r0];
        r7 = r4 - r7;
        r6 = r6 * r7;
        if (r6 <= r3) goto L_0x004f;
    L_0x004d:
        r1 = r0;
        r3 = r6;
    L_0x004f:
        r0 = r0 + -1;
        goto L_0x003b;
    L_0x0052:
        r9 = r1 << 3;
        return r9;
    L_0x0055:
        r9 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.GlobalHistogramBinarizer.estimateBlackPoint(int[]):int");
    }

    public GlobalHistogramBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    public BitArray getBlackRow(int i, BitArray bitArray) throws NotFoundException {
        int[] iArr;
        int i2;
        int i3;
        int estimateBlackPoint;
        int i4;
        int i5;
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        if (bitArray != null) {
            if (bitArray.getSize() >= width) {
                bitArray.clear();
                initArrays(width);
                i = luminanceSource.getRow(i, this.luminances);
                iArr = this.buckets;
                for (i2 = 0; i2 < width; i2++) {
                    i3 = (i[i2] & 255) >> 3;
                    iArr[i3] = iArr[i3] + 1;
                }
                estimateBlackPoint = estimateBlackPoint(iArr);
                i2 = i[1] & 255;
                i3 = i[0] & 255;
                i4 = 1;
                while (i4 < width - 1) {
                    int i6 = i4 + 1;
                    i5 = i[i6] & 255;
                    if ((((i2 * 4) - i3) - i5) / 2 < estimateBlackPoint) {
                        bitArray.set(i4);
                    }
                    i3 = i2;
                    i4 = i6;
                    i2 = i5;
                }
                return bitArray;
            }
        }
        bitArray = new BitArray(width);
        initArrays(width);
        i = luminanceSource.getRow(i, this.luminances);
        iArr = this.buckets;
        for (i2 = 0; i2 < width; i2++) {
            i3 = (i[i2] & 255) >> 3;
            iArr[i3] = iArr[i3] + 1;
        }
        estimateBlackPoint = estimateBlackPoint(iArr);
        i2 = i[1] & 255;
        i3 = i[0] & 255;
        i4 = 1;
        while (i4 < width - 1) {
            int i62 = i4 + 1;
            i5 = i[i62] & 255;
            if ((((i2 * 4) - i3) - i5) / 2 < estimateBlackPoint) {
                bitArray.set(i4);
            }
            i3 = i2;
            i4 = i62;
            i2 = i5;
        }
        return bitArray;
    }

    public BitMatrix getBlackMatrix() throws NotFoundException {
        int i;
        int i2;
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        BitMatrix bitMatrix = new BitMatrix(width, height);
        initArrays(width);
        int[] iArr = this.buckets;
        for (i = 1; i < 5; i++) {
            byte[] row = luminanceSource.getRow((height * i) / 5, this.luminances);
            int i3 = (width * 4) / 5;
            for (i2 = width / 5; i2 < i3; i2++) {
                int i4 = (row[i2] & 255) >> 3;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int estimateBlackPoint = estimateBlackPoint(iArr);
        byte[] matrix = luminanceSource.getMatrix();
        for (i = 0; i < height; i++) {
            i2 = i * width;
            for (int i5 = 0; i5 < width; i5++) {
                if ((matrix[i2 + i5] & 255) < estimateBlackPoint) {
                    bitMatrix.set(i5, i);
                }
            }
        }
        return bitMatrix;
    }

    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new GlobalHistogramBinarizer(luminanceSource);
    }

    private void initArrays(int i) {
        if (this.luminances.length < i) {
            this.luminances = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.buckets[i2] = 0;
        }
    }
}
