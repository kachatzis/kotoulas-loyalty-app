package com.google.zxing.oned.rss;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class RSS14Reader extends AbstractRSSReader {
    private static final int[][] FINDER_PATTERNS = new int[][]{new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    private static final int[] INSIDE_GSUM = new int[]{0, 336, 1036, 1516};
    private static final int[] INSIDE_ODD_TOTAL_SUBSET = new int[]{4, 20, 48, 81};
    private static final int[] INSIDE_ODD_WIDEST = new int[]{2, 4, 6, 8};
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = new int[]{1, 10, 34, 70, 126};
    private static final int[] OUTSIDE_GSUM = new int[]{0, 161, 961, 2015, 2715};
    private static final int[] OUTSIDE_ODD_WIDEST = new int[]{8, 6, 4, 3, 1};
    private final List<Pair> possibleLeftPairs = new ArrayList();
    private final List<Pair> possibleRightPairs = new ArrayList();

    private com.google.zxing.oned.rss.DataCharacter decodeDataCharacter(com.google.zxing.common.BitArray r18, com.google.zxing.oned.rss.FinderPattern r19, boolean r20) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:48:0x0124 in {2, 5, 7, 8, 14, 16, 19, 20, 21, 24, 27, 36, 38, 45, 47} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r17 = this;
        r0 = r18;
        r1 = r20;
        r2 = r17.getDataCharacterCounters();
        r3 = 0;
        r2[r3] = r3;
        r4 = 1;
        r2[r4] = r3;
        r5 = 2;
        r2[r5] = r3;
        r6 = 3;
        r2[r6] = r3;
        r7 = 4;
        r2[r7] = r3;
        r8 = 5;
        r2[r8] = r3;
        r8 = 6;
        r2[r8] = r3;
        r8 = 7;
        r2[r8] = r3;
        if (r1 == 0) goto L_0x002c;
    L_0x0022:
        r8 = r19.getStartEnd();
        r8 = r8[r3];
        com.google.zxing.oned.OneDReader.recordPatternInReverse(r0, r8, r2);
        goto L_0x0049;
    L_0x002c:
        r8 = r19.getStartEnd();
        r8 = r8[r4];
        r8 = r8 + r4;
        com.google.zxing.oned.OneDReader.recordPattern(r0, r8, r2);
        r0 = r2.length;
        r0 = r0 - r4;
        r8 = r0;
        r0 = 0;
    L_0x003a:
        if (r0 >= r8) goto L_0x0049;
    L_0x003c:
        r9 = r2[r0];
        r10 = r2[r8];
        r2[r0] = r10;
        r2[r8] = r9;
        r0 = r0 + 1;
        r8 = r8 + -1;
        goto L_0x003a;
    L_0x0049:
        if (r1 == 0) goto L_0x004e;
    L_0x004b:
        r0 = 16;
        goto L_0x0050;
    L_0x004e:
        r0 = 15;
    L_0x0050:
        r8 = com.google.zxing.oned.rss.AbstractRSSReader.count(r2);
        r8 = (float) r8;
        r9 = (float) r0;
        r8 = r8 / r9;
        r9 = r17.getOddCounts();
        r10 = r17.getEvenCounts();
        r11 = r17.getOddRoundingErrors();
        r12 = r17.getEvenRoundingErrors();
        r13 = 0;
    L_0x0068:
        r14 = r2.length;
        if (r13 >= r14) goto L_0x0094;
    L_0x006b:
        r14 = r2[r13];
        r14 = (float) r14;
        r14 = r14 / r8;
        r15 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r15 = r15 + r14;
        r15 = (int) r15;
        r3 = 8;
        if (r15 >= r4) goto L_0x0079;
    L_0x0077:
        r15 = 1;
        goto L_0x007d;
    L_0x0079:
        if (r15 <= r3) goto L_0x007d;
    L_0x007b:
        r15 = 8;
    L_0x007d:
        r3 = r13 / 2;
        r16 = r13 & 1;
        if (r16 != 0) goto L_0x008a;
    L_0x0083:
        r9[r3] = r15;
        r15 = (float) r15;
        r14 = r14 - r15;
        r11[r3] = r14;
        goto L_0x0090;
    L_0x008a:
        r10[r3] = r15;
        r15 = (float) r15;
        r14 = r14 - r15;
        r12[r3] = r14;
    L_0x0090:
        r13 = r13 + 1;
        r3 = 0;
        goto L_0x0068;
    L_0x0094:
        r3 = r17;
        r3.adjustOddEvenCounts(r1, r0);
        r0 = r9.length;
        r0 = r0 - r4;
        r2 = 0;
        r8 = 0;
    L_0x009d:
        if (r0 < 0) goto L_0x00aa;
    L_0x009f:
        r2 = r2 * 9;
        r11 = r9[r0];
        r2 = r2 + r11;
        r11 = r9[r0];
        r8 = r8 + r11;
        r0 = r0 + -1;
        goto L_0x009d;
    L_0x00aa:
        r0 = r10.length;
        r0 = r0 - r4;
        r11 = 0;
        r12 = 0;
    L_0x00ae:
        if (r0 < 0) goto L_0x00bb;
    L_0x00b0:
        r11 = r11 * 9;
        r13 = r10[r0];
        r11 = r11 + r13;
        r13 = r10[r0];
        r12 = r12 + r13;
        r0 = r0 + -1;
        goto L_0x00ae;
    L_0x00bb:
        r11 = r11 * 3;
        r2 = r2 + r11;
        if (r1 == 0) goto L_0x00f2;
    L_0x00c0:
        r0 = r8 & 1;
        if (r0 != 0) goto L_0x00ed;
    L_0x00c4:
        r0 = 12;
        if (r8 > r0) goto L_0x00ed;
    L_0x00c8:
        if (r8 < r7) goto L_0x00ed;
    L_0x00ca:
        r0 = r0 - r8;
        r0 = r0 / r5;
        r1 = OUTSIDE_ODD_WIDEST;
        r1 = r1[r0];
        r5 = 9 - r1;
        r6 = 0;
        r1 = com.google.zxing.oned.rss.RSSUtils.getRSSvalue(r9, r1, r6);
        r4 = com.google.zxing.oned.rss.RSSUtils.getRSSvalue(r10, r5, r4);
        r5 = OUTSIDE_EVEN_TOTAL_SUBSET;
        r5 = r5[r0];
        r6 = OUTSIDE_GSUM;
        r0 = r6[r0];
        r6 = new com.google.zxing.oned.rss.DataCharacter;
        r1 = r1 * r5;
        r1 = r1 + r4;
        r1 = r1 + r0;
        r6.<init>(r1, r2);
        return r6;
    L_0x00ed:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x00f2:
        r0 = r12 & 1;
        if (r0 != 0) goto L_0x011f;
    L_0x00f6:
        r0 = 10;
        if (r12 > r0) goto L_0x011f;
    L_0x00fa:
        if (r12 < r7) goto L_0x011f;
    L_0x00fc:
        r0 = r0 - r12;
        r0 = r0 / r5;
        r1 = INSIDE_ODD_WIDEST;
        r1 = r1[r0];
        r5 = 9 - r1;
        r1 = com.google.zxing.oned.rss.RSSUtils.getRSSvalue(r9, r1, r4);
        r4 = 0;
        r4 = com.google.zxing.oned.rss.RSSUtils.getRSSvalue(r10, r5, r4);
        r5 = INSIDE_ODD_TOTAL_SUBSET;
        r5 = r5[r0];
        r6 = INSIDE_GSUM;
        r0 = r6[r0];
        r6 = new com.google.zxing.oned.rss.DataCharacter;
        r4 = r4 * r5;
        r4 = r4 + r1;
        r4 = r4 + r0;
        r6.<init>(r4, r2);
        return r6;
    L_0x011f:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.RSS14Reader.decodeDataCharacter(com.google.zxing.common.BitArray, com.google.zxing.oned.rss.FinderPattern, boolean):com.google.zxing.oned.rss.DataCharacter");
    }

    private int[] findFinderPattern(com.google.zxing.common.BitArray r11, int r12, boolean r13) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:25:0x0068 in {4, 5, 10, 15, 16, 17, 20, 21, 22, 24} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r10 = this;
        r0 = r10.getDecodeFinderCounters();
        r1 = 0;
        r0[r1] = r1;
        r2 = 1;
        r0[r2] = r1;
        r3 = 2;
        r0[r3] = r1;
        r4 = 3;
        r0[r4] = r1;
        r5 = r11.getSize();
        r6 = 0;
    L_0x0015:
        if (r12 >= r5) goto L_0x0022;
    L_0x0017:
        r6 = r11.get(r12);
        r6 = r6 ^ r2;
        if (r13 != r6) goto L_0x001f;
    L_0x001e:
        goto L_0x0022;
    L_0x001f:
        r12 = r12 + 1;
        goto L_0x0015;
    L_0x0022:
        r7 = r12;
        r13 = 0;
    L_0x0024:
        if (r12 >= r5) goto L_0x0063;
    L_0x0026:
        r8 = r11.get(r12);
        r8 = r8 ^ r6;
        if (r8 == 0) goto L_0x0033;
    L_0x002d:
        r8 = r0[r13];
        r8 = r8 + r2;
        r0[r13] = r8;
        goto L_0x0060;
    L_0x0033:
        if (r13 != r4) goto L_0x0057;
    L_0x0035:
        r8 = com.google.zxing.oned.rss.AbstractRSSReader.isFinderPattern(r0);
        if (r8 == 0) goto L_0x0042;
    L_0x003b:
        r11 = new int[r3];
        r11[r1] = r7;
        r11[r2] = r12;
        return r11;
    L_0x0042:
        r8 = r0[r1];
        r9 = r0[r2];
        r8 = r8 + r9;
        r7 = r7 + r8;
        r8 = r0[r3];
        r0[r1] = r8;
        r8 = r0[r4];
        r0[r2] = r8;
        r0[r3] = r1;
        r0[r4] = r1;
        r13 = r13 + -1;
        goto L_0x0059;
    L_0x0057:
        r13 = r13 + 1;
    L_0x0059:
        r0[r13] = r2;
        if (r6 != 0) goto L_0x005f;
    L_0x005d:
        r6 = 1;
        goto L_0x0060;
    L_0x005f:
        r6 = 0;
    L_0x0060:
        r12 = r12 + 1;
        goto L_0x0024;
    L_0x0063:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.RSS14Reader.findFinderPattern(com.google.zxing.common.BitArray, int, boolean):int[]");
    }

    public com.google.zxing.Result decodeRow(int r7, com.google.zxing.common.BitArray r8, java.util.Map<com.google.zxing.DecodeHintType, ?> r9) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x005e in {11, 12, 13, 15} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r6 = this;
        r0 = 0;
        r1 = r6.decodePair(r8, r0, r7, r9);
        r2 = r6.possibleLeftPairs;
        addOrTally(r2, r1);
        r8.reverse();
        r1 = 1;
        r7 = r6.decodePair(r8, r1, r7, r9);
        r9 = r6.possibleRightPairs;
        addOrTally(r9, r7);
        r8.reverse();
        r7 = r6.possibleLeftPairs;
        r7 = r7.size();
        r8 = 0;
    L_0x0021:
        if (r8 >= r7) goto L_0x0059;
    L_0x0023:
        r9 = r6.possibleLeftPairs;
        r9 = r9.get(r8);
        r9 = (com.google.zxing.oned.rss.Pair) r9;
        r2 = r9.getCount();
        if (r2 <= r1) goto L_0x0056;
    L_0x0031:
        r2 = r6.possibleRightPairs;
        r2 = r2.size();
        r3 = 0;
    L_0x0038:
        if (r3 >= r2) goto L_0x0056;
    L_0x003a:
        r4 = r6.possibleRightPairs;
        r4 = r4.get(r3);
        r4 = (com.google.zxing.oned.rss.Pair) r4;
        r5 = r4.getCount();
        if (r5 <= r1) goto L_0x0053;
    L_0x0048:
        r5 = checkChecksum(r9, r4);
        if (r5 == 0) goto L_0x0053;
    L_0x004e:
        r7 = constructResult(r9, r4);
        return r7;
    L_0x0053:
        r3 = r3 + 1;
        goto L_0x0038;
    L_0x0056:
        r8 = r8 + 1;
        goto L_0x0021;
    L_0x0059:
        r7 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.RSS14Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }

    private static void addOrTally(Collection<Pair> collection, Pair pair) {
        if (pair != null) {
            Object obj = null;
            for (Pair pair2 : collection) {
                if (pair2.getValue() == pair.getValue()) {
                    pair2.incrementCount();
                    obj = 1;
                    break;
                }
            }
            if (obj == null) {
                collection.add(pair);
            }
        }
    }

    public void reset() {
        this.possibleLeftPairs.clear();
        this.possibleRightPairs.clear();
    }

    private static Result constructResult(Pair pair, Pair pair2) {
        int length;
        String valueOf = String.valueOf((((long) pair.getValue()) * 4537077) + ((long) pair2.getValue()));
        StringBuilder stringBuilder = new StringBuilder(14);
        for (length = 13 - valueOf.length(); length > 0; length--) {
            stringBuilder.append('0');
        }
        stringBuilder.append(valueOf);
        int i = 0;
        for (length = 0; length < 13; length++) {
            int charAt = stringBuilder.charAt(length) - 48;
            if ((length & 1) == 0) {
                charAt *= 3;
            }
            i += charAt;
        }
        int i2 = 10 - (i % 10);
        if (i2 == 10) {
            i2 = 0;
        }
        stringBuilder.append(i2);
        pair = pair.getFinderPattern().getResultPoints();
        pair2 = pair2.getFinderPattern().getResultPoints();
        return new Result(String.valueOf(stringBuilder.toString()), null, new ResultPoint[]{pair[0], pair[1], pair2[0], pair2[1]}, BarcodeFormat.RSS_14);
    }

    private static boolean checkChecksum(Pair pair, Pair pair2) {
        int checksumPortion = (pair.getChecksumPortion() + (pair2.getChecksumPortion() * 16)) % 79;
        pair = (pair.getFinderPattern().getValue() * 9) + pair2.getFinderPattern().getValue();
        if (pair > 72) {
            pair--;
        }
        if (pair > 8) {
            pair--;
        }
        return checksumPortion == pair ? true : null;
    }

    private com.google.zxing.oned.rss.Pair decodePair(com.google.zxing.common.BitArray r7, boolean r8, int r9, java.util.Map<com.google.zxing.DecodeHintType, ?> r10) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r6 = this;
        r0 = 0;
        r1 = 0;
        r2 = r6.findFinderPattern(r7, r1, r8);	 Catch:{ NotFoundException -> 0x0059 }
        r3 = r6.parseFoundFinderPattern(r7, r9, r8, r2);	 Catch:{ NotFoundException -> 0x0059 }
        if (r10 != 0) goto L_0x000e;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x000c:
        r10 = r0;	 Catch:{ NotFoundException -> 0x0059 }
        goto L_0x0016;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x000e:
        r4 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;	 Catch:{ NotFoundException -> 0x0059 }
        r10 = r10.get(r4);	 Catch:{ NotFoundException -> 0x0059 }
        r10 = (com.google.zxing.ResultPointCallback) r10;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x0016:
        r4 = 1;	 Catch:{ NotFoundException -> 0x0059 }
        if (r10 == 0) goto L_0x0035;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x0019:
        r5 = r2[r1];	 Catch:{ NotFoundException -> 0x0059 }
        r2 = r2[r4];	 Catch:{ NotFoundException -> 0x0059 }
        r5 = r5 + r2;	 Catch:{ NotFoundException -> 0x0059 }
        r2 = (float) r5;	 Catch:{ NotFoundException -> 0x0059 }
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;	 Catch:{ NotFoundException -> 0x0059 }
        r2 = r2 / r5;	 Catch:{ NotFoundException -> 0x0059 }
        if (r8 == 0) goto L_0x002c;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x0024:
        r8 = r7.getSize();	 Catch:{ NotFoundException -> 0x0059 }
        r8 = r8 - r4;	 Catch:{ NotFoundException -> 0x0059 }
        r8 = (float) r8;	 Catch:{ NotFoundException -> 0x0059 }
        r2 = r8 - r2;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x002c:
        r8 = new com.google.zxing.ResultPoint;	 Catch:{ NotFoundException -> 0x0059 }
        r9 = (float) r9;	 Catch:{ NotFoundException -> 0x0059 }
        r8.<init>(r2, r9);	 Catch:{ NotFoundException -> 0x0059 }
        r10.foundPossibleResultPoint(r8);	 Catch:{ NotFoundException -> 0x0059 }
    L_0x0035:
        r8 = r6.decodeDataCharacter(r7, r3, r4);	 Catch:{ NotFoundException -> 0x0059 }
        r7 = r6.decodeDataCharacter(r7, r3, r1);	 Catch:{ NotFoundException -> 0x0059 }
        r9 = new com.google.zxing.oned.rss.Pair;	 Catch:{ NotFoundException -> 0x0059 }
        r10 = r8.getValue();	 Catch:{ NotFoundException -> 0x0059 }
        r10 = r10 * 1597;	 Catch:{ NotFoundException -> 0x0059 }
        r1 = r7.getValue();	 Catch:{ NotFoundException -> 0x0059 }
        r10 = r10 + r1;	 Catch:{ NotFoundException -> 0x0059 }
        r8 = r8.getChecksumPortion();	 Catch:{ NotFoundException -> 0x0059 }
        r7 = r7.getChecksumPortion();	 Catch:{ NotFoundException -> 0x0059 }
        r7 = r7 * 4;	 Catch:{ NotFoundException -> 0x0059 }
        r8 = r8 + r7;	 Catch:{ NotFoundException -> 0x0059 }
        r9.<init>(r10, r8, r3);	 Catch:{ NotFoundException -> 0x0059 }
        return r9;
    L_0x0059:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.RSS14Reader.decodePair(com.google.zxing.common.BitArray, boolean, int, java.util.Map):com.google.zxing.oned.rss.Pair");
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i, boolean z, int[] iArr) throws NotFoundException {
        int size;
        int size2;
        boolean z2 = bitArray.get(iArr[0]);
        int i2 = iArr[0] - 1;
        while (i2 >= 0 && (bitArray.get(i2) ^ z2) != 0) {
            i2--;
        }
        i2++;
        int i3 = iArr[0] - i2;
        Object decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = i3;
        int parseFinderValue = AbstractRSSReader.parseFinderValue(decodeFinderCounters, FINDER_PATTERNS);
        i3 = iArr[1];
        if (z) {
            size = (bitArray.getSize() - 1) - i3;
            size2 = (bitArray.getSize() - true) - i2;
        } else {
            size = i3;
            size2 = i2;
        }
        return new FinderPattern(parseFinderValue, new int[]{i2, iArr[1]}, size2, size, i);
    }

    private void adjustOddEvenCounts(boolean z, int i) throws NotFoundException {
        Object obj;
        Object obj2;
        boolean count = AbstractRSSReader.count(getOddCounts());
        boolean count2 = AbstractRSSReader.count(getEvenCounts());
        int i2 = (count + count2) - i;
        Object obj3 = null;
        i = (count & 1) == z ? 1 : 0;
        Object obj4 = (count2 & 1) == 1 ? 1 : null;
        if (z) {
            boolean z2;
            Object obj5;
            if (count > true) {
                z2 = false;
                obj5 = 1;
            } else if (count < true) {
                z2 = true;
                obj5 = null;
            } else {
                z2 = false;
                obj5 = null;
            }
            if (count2 > true) {
                z = z2;
                obj = obj5;
                obj2 = 1;
            } else if (count2 < true) {
                z = z2;
                obj = obj5;
                obj3 = 1;
                obj2 = null;
            } else {
                z = z2;
                obj = obj5;
                obj2 = null;
            }
        } else {
            if (count > true) {
                z = false;
                obj = 1;
            } else if (count < true) {
                z = true;
                obj = null;
            } else {
                z = false;
                obj = null;
            }
            if (count2 > true) {
                obj2 = 1;
            } else if (count2 < true) {
                obj3 = 1;
                obj2 = null;
            } else {
                obj2 = null;
            }
        }
        if (i2 == 1) {
            if (i != 0) {
                if (obj4 == null) {
                    obj = 1;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (obj4 != null) {
                obj2 = 1;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else if (i2 == -1) {
            if (i != 0) {
                if (obj4 == null) {
                    z = true;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (obj4 != null) {
                obj3 = 1;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else if (i2 != 0) {
            throw NotFoundException.getNotFoundInstance();
        } else if (i != 0) {
            if (obj4 == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (count < count2) {
                z = true;
                obj2 = 1;
            } else {
                obj3 = 1;
                obj = 1;
            }
        } else if (obj4 != null) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (z) {
            if (obj == null) {
                AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (obj != null) {
            AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (obj3 != null) {
            if (obj2 == null) {
                AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (obj2 != null) {
            AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
        }
    }
}
