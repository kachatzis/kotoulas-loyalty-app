package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Map;

public abstract class OneDReader implements Reader {
    private com.google.zxing.Result doDecode(com.google.zxing.BinaryBitmap r20, java.util.Map<com.google.zxing.DecodeHintType, ?> r21) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:60:0x00f4 in {4, 5, 7, 8, 11, 12, 17, 18, 20, 21, 36, 37, 49, 50, 51, 52, 53, 54, 55, 56, 57, 59} preds:[]
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
        r0 = r21;
        r1 = r20.getWidth();
        r2 = r20.getHeight();
        r3 = new com.google.zxing.common.BitArray;
        r3.<init>(r1);
        r4 = r2 >> 1;
        r5 = 0;
        r6 = 1;
        if (r0 == 0) goto L_0x001f;
    L_0x0015:
        r7 = com.google.zxing.DecodeHintType.TRY_HARDER;
        r7 = r0.containsKey(r7);
        if (r7 == 0) goto L_0x001f;
    L_0x001d:
        r7 = 1;
        goto L_0x0020;
    L_0x001f:
        r7 = 0;
    L_0x0020:
        if (r7 == 0) goto L_0x0025;
    L_0x0022:
        r8 = 8;
        goto L_0x0026;
    L_0x0025:
        r8 = 5;
    L_0x0026:
        r8 = r2 >> r8;
        r8 = java.lang.Math.max(r6, r8);
        if (r7 == 0) goto L_0x0030;
    L_0x002e:
        r7 = r2;
        goto L_0x0032;
    L_0x0030:
        r7 = 15;
    L_0x0032:
        r9 = r0;
        r0 = 0;
    L_0x0034:
        if (r0 >= r7) goto L_0x00ed;
    L_0x0036:
        r10 = r0 + 1;
        r11 = r10 / 2;
        r0 = r0 & 1;
        if (r0 != 0) goto L_0x0040;
    L_0x003e:
        r0 = 1;
        goto L_0x0041;
    L_0x0040:
        r0 = 0;
    L_0x0041:
        if (r0 == 0) goto L_0x0044;
    L_0x0043:
        goto L_0x0045;
    L_0x0044:
        r11 = -r11;
    L_0x0045:
        r11 = r11 * r8;
        r11 = r11 + r4;
        if (r11 < 0) goto L_0x00ea;
    L_0x004a:
        if (r11 >= r2) goto L_0x00e7;
    L_0x004c:
        r0 = r20;
        r3 = r0.getBlackRow(r11, r3);	 Catch:{ NotFoundException -> 0x00db }
        r12 = r9;
        r9 = 0;
    L_0x0054:
        r13 = 2;
        if (r9 >= r13) goto L_0x00d4;
    L_0x0057:
        if (r9 != r6) goto L_0x0079;
    L_0x0059:
        r3.reverse();
        if (r12 == 0) goto L_0x0079;
    L_0x005e:
        r13 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        r13 = r12.containsKey(r13);
        if (r13 == 0) goto L_0x0079;
    L_0x0066:
        r13 = new java.util.EnumMap;
        r14 = com.google.zxing.DecodeHintType.class;
        r13.<init>(r14);
        r13.putAll(r12);
        r12 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        r13.remove(r12);
        r12 = r13;
        r13 = r19;
        goto L_0x007b;
    L_0x0079:
        r13 = r19;
    L_0x007b:
        r14 = r13.decodeRow(r11, r3, r12);	 Catch:{ ReaderException -> 0x00c8 }
        if (r9 != r6) goto L_0x00c7;	 Catch:{ ReaderException -> 0x00c8 }
    L_0x0081:
        r15 = com.google.zxing.ResultMetadataType.ORIENTATION;	 Catch:{ ReaderException -> 0x00c8 }
        r16 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;	 Catch:{ ReaderException -> 0x00c8 }
        r6 = java.lang.Integer.valueOf(r16);	 Catch:{ ReaderException -> 0x00c8 }
        r14.putMetadata(r15, r6);	 Catch:{ ReaderException -> 0x00c8 }
        r6 = r14.getResultPoints();	 Catch:{ ReaderException -> 0x00c8 }
        if (r6 == 0) goto L_0x00c7;	 Catch:{ ReaderException -> 0x00c8 }
    L_0x0092:
        r15 = new com.google.zxing.ResultPoint;	 Catch:{ ReaderException -> 0x00c8 }
        r0 = (float) r1;	 Catch:{ ReaderException -> 0x00c8 }
        r16 = r6[r5];	 Catch:{ ReaderException -> 0x00c8 }
        r16 = r16.getX();	 Catch:{ ReaderException -> 0x00c8 }
        r16 = r0 - r16;
        r17 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r18 = r1;
        r1 = r16 - r17;
        r16 = r6[r5];	 Catch:{ ReaderException -> 0x00ca }
        r5 = r16.getY();	 Catch:{ ReaderException -> 0x00ca }
        r15.<init>(r1, r5);	 Catch:{ ReaderException -> 0x00ca }
        r1 = 0;	 Catch:{ ReaderException -> 0x00ca }
        r6[r1] = r15;	 Catch:{ ReaderException -> 0x00ca }
        r5 = new com.google.zxing.ResultPoint;	 Catch:{ ReaderException -> 0x00ca }
        r15 = 1;
        r16 = r6[r15];	 Catch:{ ReaderException -> 0x00cb }
        r16 = r16.getX();	 Catch:{ ReaderException -> 0x00cb }
        r0 = r0 - r16;	 Catch:{ ReaderException -> 0x00cb }
        r0 = r0 - r17;	 Catch:{ ReaderException -> 0x00cb }
        r16 = r6[r15];	 Catch:{ ReaderException -> 0x00cb }
        r1 = r16.getY();	 Catch:{ ReaderException -> 0x00cb }
        r5.<init>(r0, r1);	 Catch:{ ReaderException -> 0x00cb }
        r6[r15] = r5;	 Catch:{ ReaderException -> 0x00cb }
    L_0x00c7:
        return r14;
    L_0x00c8:
        r18 = r1;
    L_0x00ca:
        r15 = 1;
    L_0x00cb:
        r9 = r9 + 1;
        r1 = r18;
        r0 = r20;
        r5 = 0;
        r6 = 1;
        goto L_0x0054;
    L_0x00d4:
        r15 = 1;
        r13 = r19;
        r18 = r1;
        r9 = r12;
        goto L_0x00e0;
    L_0x00db:
        r15 = 1;
        r13 = r19;
        r18 = r1;
    L_0x00e0:
        r0 = r10;
        r1 = r18;
        r5 = 0;
        r6 = 1;
        goto L_0x0034;
    L_0x00e7:
        r13 = r19;
        goto L_0x00ef;
    L_0x00ea:
        r13 = r19;
        goto L_0x00ef;
    L_0x00ed:
        r13 = r19;
    L_0x00ef:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.doDecode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    protected static void recordPattern(com.google.zxing.common.BitArray r7, int r8, int[] r9) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:25:0x0043 in {6, 9, 12, 13, 14, 19, 21, 22, 24} preds:[]
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
        java.util.Arrays.fill(r9, r1, r0, r1);
        r2 = r7.getSize();
        if (r8 >= r2) goto L_0x003e;
    L_0x000b:
        r3 = r7.get(r8);
        r4 = 1;
        r3 = r3 ^ r4;
        r5 = 0;
    L_0x0012:
        if (r8 >= r2) goto L_0x0030;
    L_0x0014:
        r6 = r7.get(r8);
        r6 = r6 ^ r3;
        if (r6 == 0) goto L_0x0021;
    L_0x001b:
        r6 = r9[r5];
        r6 = r6 + r4;
        r9[r5] = r6;
        goto L_0x002d;
    L_0x0021:
        r5 = r5 + 1;
        if (r5 != r0) goto L_0x0026;
    L_0x0025:
        goto L_0x0030;
    L_0x0026:
        r9[r5] = r4;
        if (r3 != 0) goto L_0x002c;
    L_0x002a:
        r3 = 1;
        goto L_0x002d;
    L_0x002c:
        r3 = 0;
    L_0x002d:
        r8 = r8 + 1;
        goto L_0x0012;
    L_0x0030:
        if (r5 == r0) goto L_0x003d;
    L_0x0032:
        r0 = r0 - r4;
        if (r5 != r0) goto L_0x0038;
    L_0x0035:
        if (r8 != r2) goto L_0x0038;
    L_0x0037:
        goto L_0x003d;
    L_0x0038:
        r7 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r7;
    L_0x003d:
        return;
    L_0x003e:
        r7 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.recordPattern(com.google.zxing.common.BitArray, int, int[]):void");
    }

    protected static void recordPatternInReverse(com.google.zxing.common.BitArray r4, int r5, int[] r6) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:15:0x0026 in {8, 9, 12, 14} preds:[]
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
        r1 = r4.get(r5);
    L_0x0005:
        r2 = 1;
        if (r5 <= 0) goto L_0x001a;
    L_0x0008:
        if (r0 < 0) goto L_0x001a;
    L_0x000a:
        r5 = r5 + -1;
        r3 = r4.get(r5);
        if (r3 == r1) goto L_0x0005;
    L_0x0012:
        r0 = r0 + -1;
        if (r1 != 0) goto L_0x0018;
    L_0x0016:
        r1 = 1;
        goto L_0x0005;
    L_0x0018:
        r1 = 0;
        goto L_0x0005;
    L_0x001a:
        if (r0 >= 0) goto L_0x0021;
    L_0x001c:
        r5 = r5 + r2;
        recordPattern(r4, r5, r6);
        return;
    L_0x0021:
        r4 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.recordPatternInReverse(com.google.zxing.common.BitArray, int, int[]):void");
    }

    public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap r6, java.util.Map<com.google.zxing.DecodeHintType, ?> r7) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:26:0x0076 in {2, 8, 9, 17, 23, 24, 25} preds:[]
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
        r5 = this;
        r6 = r5.doDecode(r6, r7);	 Catch:{ NotFoundException -> 0x0005 }
        return r6;
    L_0x0005:
        r0 = move-exception;
        r1 = 0;
        if (r7 == 0) goto L_0x0013;
    L_0x0009:
        r2 = com.google.zxing.DecodeHintType.TRY_HARDER;
        r2 = r7.containsKey(r2);
        if (r2 == 0) goto L_0x0013;
    L_0x0011:
        r2 = 1;
        goto L_0x0014;
    L_0x0013:
        r2 = 0;
    L_0x0014:
        if (r2 == 0) goto L_0x0075;
    L_0x0016:
        r2 = r6.isRotateSupported();
        if (r2 == 0) goto L_0x0075;
    L_0x001c:
        r6 = r6.rotateCounterClockwise();
        r7 = r5.doDecode(r6, r7);
        r0 = r7.getResultMetadata();
        r2 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        if (r0 == 0) goto L_0x0043;
    L_0x002c:
        r3 = com.google.zxing.ResultMetadataType.ORIENTATION;
        r3 = r0.containsKey(r3);
        if (r3 == 0) goto L_0x0043;
    L_0x0034:
        r3 = com.google.zxing.ResultMetadataType.ORIENTATION;
        r0 = r0.get(r3);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r0 = r0 + r2;
        r2 = r0 % 360;
    L_0x0043:
        r0 = com.google.zxing.ResultMetadataType.ORIENTATION;
        r2 = java.lang.Integer.valueOf(r2);
        r7.putMetadata(r0, r2);
        r0 = r7.getResultPoints();
        if (r0 == 0) goto L_0x0074;
    L_0x0052:
        r6 = r6.getHeight();
    L_0x0056:
        r2 = r0.length;
        if (r1 >= r2) goto L_0x0074;
    L_0x0059:
        r2 = new com.google.zxing.ResultPoint;
        r3 = (float) r6;
        r4 = r0[r1];
        r4 = r4.getY();
        r3 = r3 - r4;
        r4 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = r3 - r4;
        r4 = r0[r1];
        r4 = r4.getX();
        r2.<init>(r3, r4);
        r0[r1] = r2;
        r1 = r1 + 1;
        goto L_0x0056;
    L_0x0074:
        return r7;
    L_0x0075:
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.decode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    public abstract Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    public void reset() {
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    protected static float patternMatchVariance(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = (float) i;
        float f3 = f2 / ((float) i2);
        f *= f3;
        float f4 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            float f5 = ((float) iArr2[i4]) * f3;
            float f6 = (float) iArr[i4];
            f6 = f6 > f5 ? f6 - f5 : f5 - f6;
            if (f6 > f) {
                return Float.POSITIVE_INFINITY;
            }
            f4 += f6;
        }
        return f4 / f2;
    }
}
