package com.google.zxing.oned.rss;

import com.google.zxing.oned.OneDReader;

public abstract class AbstractRSSReader extends OneDReader {
    private static final float MAX_AVG_VARIANCE = 0.2f;
    private static final float MAX_FINDER_PATTERN_RATIO = 0.89285713f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.45f;
    private static final float MIN_FINDER_PATTERN_RATIO = 0.7916667f;
    private final int[] dataCharacterCounters = new int[8];
    private final int[] decodeFinderCounters = new int[4];
    private final int[] evenCounts;
    private final float[] evenRoundingErrors = new float[4];
    private final int[] oddCounts;
    private final float[] oddRoundingErrors = new float[4];

    protected static int parseFinderValue(int[] r3, int[][] r4) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:9:0x001d in {5, 6, 8} preds:[]
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
        r0 = 0;
    L_0x0001:
        r1 = r4.length;
        if (r0 >= r1) goto L_0x0018;
    L_0x0004:
        r1 = r4[r0];
        r2 = 1055286886; // 0x3ee66666 float:0.45 double:5.21380997E-315;
        r1 = com.google.zxing.oned.OneDReader.patternMatchVariance(r3, r1, r2);
        r2 = 1045220557; // 0x3e4ccccd float:0.2 double:5.164075695E-315;
        r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1));
        if (r1 >= 0) goto L_0x0015;
    L_0x0014:
        return r0;
    L_0x0015:
        r0 = r0 + 1;
        goto L_0x0001;
    L_0x0018:
        r3 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.AbstractRSSReader.parseFinderValue(int[], int[][]):int");
    }

    protected AbstractRSSReader() {
        int[] iArr = this.dataCharacterCounters;
        this.oddCounts = new int[(iArr.length / 2)];
        this.evenCounts = new int[(iArr.length / 2)];
    }

    protected final int[] getDecodeFinderCounters() {
        return this.decodeFinderCounters;
    }

    protected final int[] getDataCharacterCounters() {
        return this.dataCharacterCounters;
    }

    protected final float[] getOddRoundingErrors() {
        return this.oddRoundingErrors;
    }

    protected final float[] getEvenRoundingErrors() {
        return this.evenRoundingErrors;
    }

    protected final int[] getOddCounts() {
        return this.oddCounts;
    }

    protected final int[] getEvenCounts() {
        return this.evenCounts;
    }

    protected static int count(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        return i;
    }

    protected static void increment(int[] iArr, float[] fArr) {
        float f = fArr[0];
        int i = 0;
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] > f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] + 1;
    }

    protected static void decrement(int[] iArr, float[] fArr) {
        float f = fArr[0];
        int i = 0;
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] < f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] - 1;
    }

    protected static boolean isFinderPattern(int[] iArr) {
        boolean z = false;
        int i = iArr[0] + iArr[1];
        float f = ((float) i) / ((float) ((iArr[2] + i) + iArr[3]));
        if (f < MIN_FINDER_PATTERN_RATIO || f > MAX_FINDER_PATTERN_RATIO) {
            return false;
        }
        int i2 = Integer.MIN_VALUE;
        int i3 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (int i4 : iArr) {
            if (i4 > i2) {
                i2 = i4;
            }
            if (i4 < i3) {
                i3 = i4;
            }
        }
        if (i2 < i3 * 10) {
            z = true;
        }
        return z;
    }
}
