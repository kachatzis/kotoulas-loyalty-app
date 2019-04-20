package com.google.zxing.oned.rss.expanded;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class RSSExpandedReader extends AbstractRSSReader {
    private static final int[] EVEN_TOTAL_SUBSET = new int[]{4, 20, 52, 104, 204};
    private static final int[][] FINDER_PATTERNS = new int[][]{new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] FINDER_PATTERN_SEQUENCES = new int[][]{new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    private static final int FINDER_PAT_A = 0;
    private static final int FINDER_PAT_B = 1;
    private static final int FINDER_PAT_C = 2;
    private static final int FINDER_PAT_D = 3;
    private static final int FINDER_PAT_E = 4;
    private static final int FINDER_PAT_F = 5;
    private static final int[] GSUM = new int[]{0, 348, 1388, 2948, 3988};
    private static final int MAX_PAIRS = 11;
    private static final int[] SYMBOL_WIDEST = new int[]{7, 5, 4, 3, 1};
    private static final int[][] WEIGHTS = new int[][]{new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, 145, 13, 39, 117, 140, 209, 205}, new int[]{193, 157, 49, 147, 19, 57, 171, 91}, new int[]{62, 186, 136, 197, 169, 85, 44, 132}, new int[]{185, 133, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, 158, 52, 156}, new int[]{46, 138, 203, 187, 139, 206, 196, 166}, new int[]{76, 17, 51, 153, 37, 111, 122, 155}, new int[]{43, 129, 176, 106, 107, 110, 119, 146}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, Callback.DEFAULT_DRAG_ANIMATION_DURATION, 178, 112, 125, 164}, new int[]{70, 210, 208, 202, 184, 130, 179, 115}, new int[]{134, 191, 151, 31, 93, 68, 204, 190}, new int[]{148, 22, 66, 198, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, 167}, new int[]{79, 26, 78, 23, 69, 207, 199, 175}, new int[]{103, 98, 83, 38, 114, 131, 182, 124}, new int[]{161, 61, 183, 127, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, 194, 160, 58, 174, 100, 89}};
    private final List<ExpandedPair> pairs = new ArrayList(11);
    private final List<ExpandedRow> rows = new ArrayList();
    private final int[] startEnd = new int[2];
    private boolean startFromEven;

    private java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair> checkRows(java.util.List<com.google.zxing.oned.rss.expanded.ExpandedRow> r6, int r7) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:19:0x0063 in {4, 7, 11, 15, 16, 18} preds:[]
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
        r5 = this;
    L_0x0000:
        r0 = r5.rows;
        r0 = r0.size();
        if (r7 >= r0) goto L_0x005e;
    L_0x0008:
        r0 = r5.rows;
        r0 = r0.get(r7);
        r0 = (com.google.zxing.oned.rss.expanded.ExpandedRow) r0;
        r1 = r5.pairs;
        r1.clear();
        r1 = r6.size();
        r2 = 0;
    L_0x001a:
        if (r2 >= r1) goto L_0x002e;
    L_0x001c:
        r3 = r5.pairs;
        r4 = r6.get(r2);
        r4 = (com.google.zxing.oned.rss.expanded.ExpandedRow) r4;
        r4 = r4.getPairs();
        r3.addAll(r4);
        r2 = r2 + 1;
        goto L_0x001a;
    L_0x002e:
        r1 = r5.pairs;
        r2 = r0.getPairs();
        r1.addAll(r2);
        r1 = r5.pairs;
        r1 = isValidSequence(r1);
        if (r1 != 0) goto L_0x0040;
    L_0x003f:
        goto L_0x005b;
    L_0x0040:
        r1 = r5.checkChecksum();
        if (r1 == 0) goto L_0x0049;
    L_0x0046:
        r6 = r5.pairs;
        return r6;
    L_0x0049:
        r1 = new java.util.ArrayList;
        r1.<init>();
        r1.addAll(r6);
        r1.add(r0);
        r0 = r7 + 1;
        r6 = r5.checkRows(r1, r0);	 Catch:{ NotFoundException -> 0x005b }
        return r6;
    L_0x005b:
        r7 = r7 + 1;
        goto L_0x0000;
    L_0x005e:
        r6 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.checkRows(java.util.List, int):java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair>");
    }

    private void findNextPair(com.google.zxing.common.BitArray r12, java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair> r13, int r14) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:43:0x00a2 in {2, 5, 6, 9, 10, 13, 18, 19, 24, 27, 31, 33, 34, 35, 38, 39, 40, 42} preds:[]
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
        r11 = this;
        r0 = r11.getDecodeFinderCounters();
        r1 = 0;
        r0[r1] = r1;
        r2 = 1;
        r0[r2] = r1;
        r3 = 2;
        r0[r3] = r1;
        r4 = 3;
        r0[r4] = r1;
        r5 = r12.getSize();
        if (r14 < 0) goto L_0x0017;
    L_0x0016:
        goto L_0x0034;
    L_0x0017:
        r14 = r13.isEmpty();
        if (r14 == 0) goto L_0x001f;
    L_0x001d:
        r14 = 0;
        goto L_0x0034;
    L_0x001f:
        r14 = r13.size();
        r14 = r14 - r2;
        r14 = r13.get(r14);
        r14 = (com.google.zxing.oned.rss.expanded.ExpandedPair) r14;
        r14 = r14.getFinderPattern();
        r14 = r14.getStartEnd();
        r14 = r14[r2];
    L_0x0034:
        r13 = r13.size();
        r13 = r13 % r3;
        if (r13 == 0) goto L_0x003d;
    L_0x003b:
        r13 = 1;
        goto L_0x003e;
    L_0x003d:
        r13 = 0;
    L_0x003e:
        r6 = r11.startFromEven;
        if (r6 == 0) goto L_0x0044;
    L_0x0042:
        r13 = r13 ^ 1;
    L_0x0044:
        r6 = 0;
    L_0x0045:
        if (r14 >= r5) goto L_0x0052;
    L_0x0047:
        r6 = r12.get(r14);
        r6 = r6 ^ r2;
        if (r6 != 0) goto L_0x004f;
    L_0x004e:
        goto L_0x0052;
    L_0x004f:
        r14 = r14 + 1;
        goto L_0x0045;
    L_0x0052:
        r8 = r14;
        r7 = 0;
    L_0x0054:
        if (r14 >= r5) goto L_0x009d;
    L_0x0056:
        r9 = r12.get(r14);
        r9 = r9 ^ r6;
        if (r9 == 0) goto L_0x0063;
    L_0x005d:
        r9 = r0[r7];
        r9 = r9 + r2;
        r0[r7] = r9;
        goto L_0x009a;
    L_0x0063:
        if (r7 != r4) goto L_0x0091;
    L_0x0065:
        if (r13 == 0) goto L_0x006a;
    L_0x0067:
        reverseCounters(r0);
    L_0x006a:
        r9 = com.google.zxing.oned.rss.AbstractRSSReader.isFinderPattern(r0);
        if (r9 == 0) goto L_0x0077;
    L_0x0070:
        r12 = r11.startEnd;
        r12[r1] = r8;
        r12[r2] = r14;
        return;
    L_0x0077:
        if (r13 == 0) goto L_0x007c;
    L_0x0079:
        reverseCounters(r0);
    L_0x007c:
        r9 = r0[r1];
        r10 = r0[r2];
        r9 = r9 + r10;
        r8 = r8 + r9;
        r9 = r0[r3];
        r0[r1] = r9;
        r9 = r0[r4];
        r0[r2] = r9;
        r0[r3] = r1;
        r0[r4] = r1;
        r7 = r7 + -1;
        goto L_0x0093;
    L_0x0091:
        r7 = r7 + 1;
    L_0x0093:
        r0[r7] = r2;
        if (r6 != 0) goto L_0x0099;
    L_0x0097:
        r6 = 1;
        goto L_0x009a;
    L_0x0099:
        r6 = 0;
    L_0x009a:
        r14 = r14 + 1;
        goto L_0x0054;
    L_0x009d:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.findNextPair(com.google.zxing.common.BitArray, java.util.List, int):void");
    }

    com.google.zxing.oned.rss.DataCharacter decodeDataCharacter(com.google.zxing.common.BitArray r18, com.google.zxing.oned.rss.FinderPattern r19, boolean r20, boolean r21) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:56:0x014d in {2, 5, 15, 17, 21, 23, 26, 27, 28, 31, 32, 37, 38, 43, 44, 51, 53, 55} preds:[]
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
        r1 = r17.getDataCharacterCounters();
        r2 = 0;
        r1[r2] = r2;
        r3 = 1;
        r1[r3] = r2;
        r4 = 2;
        r1[r4] = r2;
        r5 = 3;
        r1[r5] = r2;
        r5 = 4;
        r1[r5] = r2;
        r6 = 5;
        r1[r6] = r2;
        r6 = 6;
        r1[r6] = r2;
        r6 = 7;
        r1[r6] = r2;
        if (r21 == 0) goto L_0x002a;
    L_0x0020:
        r6 = r19.getStartEnd();
        r6 = r6[r2];
        com.google.zxing.oned.OneDReader.recordPatternInReverse(r0, r6, r1);
        goto L_0x0046;
    L_0x002a:
        r6 = r19.getStartEnd();
        r6 = r6[r3];
        com.google.zxing.oned.OneDReader.recordPattern(r0, r6, r1);
        r0 = r1.length;
        r0 = r0 - r3;
        r6 = r0;
        r0 = 0;
    L_0x0037:
        if (r0 >= r6) goto L_0x0046;
    L_0x0039:
        r7 = r1[r0];
        r8 = r1[r6];
        r1[r0] = r8;
        r1[r6] = r7;
        r0 = r0 + 1;
        r6 = r6 + -1;
        goto L_0x0037;
    L_0x0046:
        r0 = 17;
        r6 = com.google.zxing.oned.rss.AbstractRSSReader.count(r1);
        r6 = (float) r6;
        r7 = (float) r0;
        r6 = r6 / r7;
        r7 = r19.getStartEnd();
        r7 = r7[r3];
        r8 = r19.getStartEnd();
        r8 = r8[r2];
        r7 = r7 - r8;
        r7 = (float) r7;
        r8 = 1097859072; // 0x41700000 float:15.0 double:5.424144515E-315;
        r7 = r7 / r8;
        r8 = r6 - r7;
        r8 = java.lang.Math.abs(r8);
        r8 = r8 / r7;
        r7 = 1050253722; // 0x3e99999a float:0.3 double:5.188942835E-315;
        r8 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1));
        if (r8 > 0) goto L_0x0146;
    L_0x006e:
        r8 = r17.getOddCounts();
        r9 = r17.getEvenCounts();
        r10 = r17.getOddRoundingErrors();
        r11 = r17.getEvenRoundingErrors();
        r12 = 0;
    L_0x007f:
        r13 = r1.length;
        if (r12 >= r13) goto L_0x00c4;
    L_0x0082:
        r13 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r14 = r1[r12];
        r14 = (float) r14;
        r14 = r14 * r13;
        r14 = r14 / r6;
        r13 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r13 = r13 + r14;
        r13 = (int) r13;
        r15 = 8;
        if (r13 >= r3) goto L_0x009d;
    L_0x0092:
        r13 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1));
        if (r13 < 0) goto L_0x0098;
    L_0x0096:
        r13 = 1;
        goto L_0x00ae;
    L_0x0098:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x009d:
        if (r13 <= r15) goto L_0x00ae;
    L_0x009f:
        r13 = 1091253043; // 0x410b3333 float:8.7 double:5.391506395E-315;
        r13 = (r14 > r13 ? 1 : (r14 == r13 ? 0 : -1));
        if (r13 > 0) goto L_0x00a9;
    L_0x00a6:
        r13 = 8;
        goto L_0x00ae;
    L_0x00a9:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x00ae:
        r15 = r12 / 2;
        r16 = r12 & 1;
        if (r16 != 0) goto L_0x00bb;
    L_0x00b4:
        r8[r15] = r13;
        r13 = (float) r13;
        r14 = r14 - r13;
        r10[r15] = r14;
        goto L_0x00c1;
    L_0x00bb:
        r9[r15] = r13;
        r13 = (float) r13;
        r14 = r14 - r13;
        r11[r15] = r14;
    L_0x00c1:
        r12 = r12 + 1;
        goto L_0x007f;
    L_0x00c4:
        r12 = r17;
        r12.adjustOddEvenCounts(r0);
        r0 = r19.getValue();
        r0 = r0 * 4;
        if (r20 == 0) goto L_0x00d3;
    L_0x00d1:
        r1 = 0;
        goto L_0x00d4;
    L_0x00d3:
        r1 = 2;
    L_0x00d4:
        r0 = r0 + r1;
        r1 = r21 ^ 1;
        r0 = r0 + r1;
        r0 = r0 - r3;
        r1 = r8.length;
        r1 = r1 - r3;
        r6 = 0;
        r7 = 0;
    L_0x00dd:
        if (r1 < 0) goto L_0x00f8;
    L_0x00df:
        r10 = isNotA1left(r19, r20, r21);
        if (r10 == 0) goto L_0x00f2;
    L_0x00e5:
        r10 = WEIGHTS;
        r10 = r10[r0];
        r11 = r1 * 2;
        r10 = r10[r11];
        r11 = r8[r1];
        r11 = r11 * r10;
        r6 = r6 + r11;
    L_0x00f2:
        r10 = r8[r1];
        r7 = r7 + r10;
        r1 = r1 + -1;
        goto L_0x00dd;
    L_0x00f8:
        r1 = r9.length;
        r1 = r1 - r3;
        r10 = 0;
    L_0x00fb:
        if (r1 < 0) goto L_0x0114;
    L_0x00fd:
        r11 = isNotA1left(r19, r20, r21);
        if (r11 == 0) goto L_0x0111;
    L_0x0103:
        r11 = WEIGHTS;
        r11 = r11[r0];
        r13 = r1 * 2;
        r13 = r13 + r3;
        r11 = r11[r13];
        r13 = r9[r1];
        r13 = r13 * r11;
        r10 = r10 + r13;
    L_0x0111:
        r1 = r1 + -1;
        goto L_0x00fb;
    L_0x0114:
        r6 = r6 + r10;
        r0 = r7 & 1;
        if (r0 != 0) goto L_0x0141;
    L_0x0119:
        r0 = 13;
        if (r7 > r0) goto L_0x0141;
    L_0x011d:
        if (r7 < r5) goto L_0x0141;
    L_0x011f:
        r0 = r0 - r7;
        r0 = r0 / r4;
        r1 = SYMBOL_WIDEST;
        r1 = r1[r0];
        r4 = 9 - r1;
        r1 = com.google.zxing.oned.rss.RSSUtils.getRSSvalue(r8, r1, r3);
        r2 = com.google.zxing.oned.rss.RSSUtils.getRSSvalue(r9, r4, r2);
        r3 = EVEN_TOTAL_SUBSET;
        r3 = r3[r0];
        r4 = GSUM;
        r0 = r4[r0];
        r1 = r1 * r3;
        r1 = r1 + r2;
        r1 = r1 + r0;
        r0 = new com.google.zxing.oned.rss.DataCharacter;
        r0.<init>(r1, r6);
        return r0;
    L_0x0141:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0146:
        r12 = r17;
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.decodeDataCharacter(com.google.zxing.common.BitArray, com.google.zxing.oned.rss.FinderPattern, boolean, boolean):com.google.zxing.oned.rss.DataCharacter");
    }

    java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair> decodeRow2pairs(int r3, com.google.zxing.common.BitArray r4) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:21:0x0040 in {2, 9, 14, 17, 19, 20} preds:[]
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
        r2 = this;
    L_0x0000:
        r0 = r2.pairs;	 Catch:{ NotFoundException -> 0x000c }
        r0 = r2.retrieveNextPair(r4, r0, r3);	 Catch:{ NotFoundException -> 0x000c }
        r1 = r2.pairs;	 Catch:{ NotFoundException -> 0x000c }
        r1.add(r0);	 Catch:{ NotFoundException -> 0x000c }
        goto L_0x0000;
    L_0x000c:
        r4 = move-exception;
        r0 = r2.pairs;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x003f;
    L_0x0015:
        r4 = r2.checkChecksum();
        if (r4 == 0) goto L_0x001e;
    L_0x001b:
        r3 = r2.pairs;
        return r3;
    L_0x001e:
        r4 = r2.rows;
        r4 = r4.isEmpty();
        r0 = 1;
        r4 = r4 ^ r0;
        r1 = 0;
        r2.storeRow(r3, r1);
        if (r4 == 0) goto L_0x003a;
    L_0x002c:
        r3 = r2.checkRows(r1);
        if (r3 == 0) goto L_0x0033;
    L_0x0032:
        return r3;
    L_0x0033:
        r3 = r2.checkRows(r0);
        if (r3 == 0) goto L_0x003a;
    L_0x0039:
        return r3;
    L_0x003a:
        r3 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r3;
    L_0x003f:
        throw r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.decodeRow2pairs(int, com.google.zxing.common.BitArray):java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair>");
    }

    public com.google.zxing.Result decodeRow(int r1, com.google.zxing.common.BitArray r2, java.util.Map<com.google.zxing.DecodeHintType, ?> r3) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
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
        r0 = this;
        r3 = r0.pairs;
        r3.clear();
        r3 = 0;
        r0.startFromEven = r3;
        r3 = r0.decodeRow2pairs(r1, r2);	 Catch:{ NotFoundException -> 0x0011 }
        r1 = constructResult(r3);	 Catch:{ NotFoundException -> 0x0011 }
        return r1;
    L_0x0011:
        r3 = r0.pairs;
        r3.clear();
        r3 = 1;
        r0.startFromEven = r3;
        r1 = r0.decodeRow2pairs(r1, r2);
        r1 = constructResult(r1);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }

    public void reset() {
        this.pairs.clear();
        this.rows.clear();
    }

    private java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair> checkRows(boolean r4) {
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
        r3 = this;
        r0 = r3.rows;
        r0 = r0.size();
        r1 = 0;
        r2 = 25;
        if (r0 <= r2) goto L_0x0011;
    L_0x000b:
        r4 = r3.rows;
        r4.clear();
        return r1;
    L_0x0011:
        r0 = r3.pairs;
        r0.clear();
        if (r4 == 0) goto L_0x001d;
    L_0x0018:
        r0 = r3.rows;
        java.util.Collections.reverse(r0);
    L_0x001d:
        r0 = new java.util.ArrayList;	 Catch:{ NotFoundException -> 0x0027 }
        r0.<init>();	 Catch:{ NotFoundException -> 0x0027 }
        r2 = 0;	 Catch:{ NotFoundException -> 0x0027 }
        r1 = r3.checkRows(r0, r2);	 Catch:{ NotFoundException -> 0x0027 }
    L_0x0027:
        if (r4 == 0) goto L_0x002e;
    L_0x0029:
        r4 = r3.rows;
        java.util.Collections.reverse(r4);
    L_0x002e:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.checkRows(boolean):java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair>");
    }

    private static boolean isValidSequence(List<ExpandedPair> list) {
        for (int[] iArr : FINDER_PATTERN_SEQUENCES) {
            if (list.size() <= iArr.length) {
                Object obj;
                for (int i = 0; i < list.size(); i++) {
                    if (((ExpandedPair) list.get(i)).getFinderPattern().getValue() != iArr[i]) {
                        obj = null;
                        break;
                    }
                }
                obj = 1;
                if (obj != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private void storeRow(int i, boolean z) {
        boolean z2 = false;
        int i2 = 0;
        boolean z3 = false;
        while (i2 < this.rows.size()) {
            ExpandedRow expandedRow = (ExpandedRow) this.rows.get(i2);
            if (expandedRow.getRowNumber() > i) {
                z2 = expandedRow.isEquivalent(this.pairs);
                break;
            } else {
                z3 = expandedRow.isEquivalent(this.pairs);
                i2++;
            }
        }
        if (!z2) {
            if (!z3) {
                if (!isPartialRow(this.pairs, this.rows)) {
                    this.rows.add(i2, new ExpandedRow(this.pairs, i, z));
                    removePartialRows(this.pairs, this.rows);
                }
            }
        }
    }

    private static void removePartialRows(List<ExpandedPair> list, List<ExpandedRow> list2) {
        list2 = list2.iterator();
        while (list2.hasNext()) {
            ExpandedRow expandedRow = (ExpandedRow) list2.next();
            if (expandedRow.getPairs().size() != list.size()) {
                Object obj;
                Iterator it = expandedRow.getPairs().iterator();
                Object obj2;
                do {
                    obj = null;
                    obj2 = 1;
                    if (!it.hasNext()) {
                        obj = 1;
                        break;
                    }
                    ExpandedPair expandedPair = (ExpandedPair) it.next();
                    for (ExpandedPair equals : list) {
                        if (expandedPair.equals(equals)) {
                            continue;
                            break;
                        }
                    }
                    obj2 = null;
                    continue;
                } while (obj2 != null);
                if (obj != null) {
                    list2.remove();
                }
            }
        }
    }

    private static boolean isPartialRow(Iterable<ExpandedPair> iterable, Iterable<ExpandedRow> iterable2) {
        iterable2 = iterable2.iterator();
        boolean z;
        do {
            z = false;
            if (!iterable2.hasNext()) {
                return false;
            }
            ExpandedRow expandedRow = (ExpandedRow) iterable2.next();
            for (ExpandedPair expandedPair : iterable) {
                Object obj;
                for (ExpandedPair equals : expandedRow.getPairs()) {
                    if (expandedPair.equals(equals)) {
                        obj = 1;
                        continue;
                        break;
                    }
                }
                obj = null;
                continue;
                if (obj == null) {
                    continue;
                    break;
                }
            }
            z = true;
            continue;
        } while (!z);
        return true;
    }

    List<ExpandedRow> getRows() {
        return this.rows;
    }

    static Result constructResult(List<ExpandedPair> list) throws NotFoundException, FormatException {
        String parseInformation = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(list)).parseInformation();
        ResultPoint[] resultPoints = ((ExpandedPair) list.get(0)).getFinderPattern().getResultPoints();
        list = ((ExpandedPair) list.get(list.size() - 1)).getFinderPattern().getResultPoints();
        return new Result(parseInformation, null, new ResultPoint[]{resultPoints[0], resultPoints[1], list[0], list[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private boolean checkChecksum() {
        boolean z = false;
        ExpandedPair expandedPair = (ExpandedPair) this.pairs.get(0);
        DataCharacter leftChar = expandedPair.getLeftChar();
        DataCharacter rightChar = expandedPair.getRightChar();
        if (rightChar == null) {
            return false;
        }
        int checksumPortion = rightChar.getChecksumPortion();
        int i = 2;
        for (int i2 = 1; i2 < this.pairs.size(); i2++) {
            ExpandedPair expandedPair2 = (ExpandedPair) this.pairs.get(i2);
            checksumPortion += expandedPair2.getLeftChar().getChecksumPortion();
            i++;
            DataCharacter rightChar2 = expandedPair2.getRightChar();
            if (rightChar2 != null) {
                checksumPortion += rightChar2.getChecksumPortion();
                i++;
            }
        }
        if (((i - 4) * 211) + (checksumPortion % 211) == leftChar.getValue()) {
            z = true;
        }
        return z;
    }

    private static int getNextSecondBar(BitArray bitArray, int i) {
        if (bitArray.get(i)) {
            return bitArray.getNextSet(bitArray.getNextUnset(i));
        }
        return bitArray.getNextUnset(bitArray.getNextSet(i));
    }

    com.google.zxing.oned.rss.expanded.ExpandedPair retrieveNextPair(com.google.zxing.common.BitArray r7, java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair> r8, int r9) throws com.google.zxing.NotFoundException {
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
        r0 = r8.size();
        r0 = r0 % 2;
        r1 = 0;
        r2 = 1;
        if (r0 != 0) goto L_0x000c;
    L_0x000a:
        r0 = 1;
        goto L_0x000d;
    L_0x000c:
        r0 = 0;
    L_0x000d:
        r3 = r6.startFromEven;
        if (r3 == 0) goto L_0x0013;
    L_0x0011:
        r0 = r0 ^ 1;
    L_0x0013:
        r3 = -1;
        r4 = 1;
    L_0x0015:
        r6.findNextPair(r7, r8, r3);
        r5 = r6.parseFoundFinderPattern(r7, r9, r0);
        if (r5 != 0) goto L_0x0027;
    L_0x001e:
        r3 = r6.startEnd;
        r3 = r3[r1];
        r3 = getNextSecondBar(r7, r3);
        goto L_0x0028;
    L_0x0027:
        r4 = 0;
    L_0x0028:
        if (r4 != 0) goto L_0x0015;
    L_0x002a:
        r9 = r6.decodeDataCharacter(r7, r5, r0, r2);
        r3 = r8.isEmpty();
        if (r3 != 0) goto L_0x004b;
    L_0x0034:
        r3 = r8.size();
        r3 = r3 - r2;
        r8 = r8.get(r3);
        r8 = (com.google.zxing.oned.rss.expanded.ExpandedPair) r8;
        r8 = r8.mustBeLast();
        if (r8 != 0) goto L_0x0046;
    L_0x0045:
        goto L_0x004b;
    L_0x0046:
        r7 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r7;
    L_0x004b:
        r7 = r6.decodeDataCharacter(r7, r5, r0, r1);	 Catch:{ NotFoundException -> 0x0050 }
        goto L_0x0051;
    L_0x0050:
        r7 = 0;
    L_0x0051:
        r8 = new com.google.zxing.oned.rss.expanded.ExpandedPair;
        r8.<init>(r9, r7, r5, r2);
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.retrieveNextPair(com.google.zxing.common.BitArray, java.util.List, int):com.google.zxing.oned.rss.expanded.ExpandedPair");
    }

    private static void reverseCounters(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            int i3 = (length - i) - 1;
            iArr[i] = iArr[i3];
            iArr[i3] = i2;
        }
    }

    private com.google.zxing.oned.rss.FinderPattern parseFoundFinderPattern(com.google.zxing.common.BitArray r9, int r10, boolean r11) {
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
        r8 = this;
        r0 = 0;
        r1 = 1;
        if (r11 == 0) goto L_0x0020;
    L_0x0004:
        r11 = r8.startEnd;
        r11 = r11[r0];
        r11 = r11 - r1;
    L_0x0009:
        if (r11 < 0) goto L_0x0014;
    L_0x000b:
        r2 = r9.get(r11);
        if (r2 != 0) goto L_0x0014;
    L_0x0011:
        r11 = r11 + -1;
        goto L_0x0009;
    L_0x0014:
        r11 = r11 + r1;
        r9 = r8.startEnd;
        r2 = r9[r0];
        r2 = r2 - r11;
        r9 = r9[r1];
        r6 = r9;
        r5 = r11;
        r11 = r2;
        goto L_0x0033;
    L_0x0020:
        r11 = r8.startEnd;
        r2 = r11[r0];
        r11 = r11[r1];
        r11 = r11 + r1;
        r9 = r9.getNextUnset(r11);
        r11 = r8.startEnd;
        r11 = r11[r1];
        r11 = r9 - r11;
        r6 = r9;
        r5 = r2;
    L_0x0033:
        r9 = r8.getDecodeFinderCounters();
        r2 = r9.length;
        r2 = r2 - r1;
        java.lang.System.arraycopy(r9, r0, r9, r1, r2);
        r9[r0] = r11;
        r11 = FINDER_PATTERNS;	 Catch:{ NotFoundException -> 0x0053 }
        r3 = com.google.zxing.oned.rss.AbstractRSSReader.parseFinderValue(r9, r11);	 Catch:{ NotFoundException -> 0x0053 }
        r9 = new com.google.zxing.oned.rss.FinderPattern;
        r11 = 2;
        r4 = new int[r11];
        r4[r0] = r5;
        r4[r1] = r6;
        r2 = r9;
        r7 = r10;
        r2.<init>(r3, r4, r5, r6, r7);
        return r9;
    L_0x0053:
        r9 = 0;
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.parseFoundFinderPattern(com.google.zxing.common.BitArray, int, boolean):com.google.zxing.oned.rss.FinderPattern");
    }

    private static boolean isNotA1left(FinderPattern finderPattern, boolean z, boolean z2) {
        if (finderPattern.getValue() == null && z) {
            if (z2) {
                return null;
            }
        }
        return true;
    }

    private void adjustOddEvenCounts(int i) throws NotFoundException {
        Object obj;
        Object obj2;
        Object obj3;
        int count = AbstractRSSReader.count(getOddCounts());
        int count2 = AbstractRSSReader.count(getEvenCounts());
        int i2 = (count + count2) - i;
        Object obj4 = null;
        i = (count & 1) == 1 ? 1 : 0;
        Object obj5 = (count2 & 1) == 0 ? 1 : null;
        if (count > 13) {
            obj = null;
            obj2 = 1;
        } else if (count < 4) {
            obj = 1;
            obj2 = null;
        } else {
            obj = null;
            obj2 = null;
        }
        if (count2 > 13) {
            obj3 = 1;
        } else if (count2 < 4) {
            obj4 = 1;
            obj3 = null;
        } else {
            obj3 = null;
        }
        if (i2 == 1) {
            if (i != 0) {
                if (obj5 == null) {
                    obj2 = 1;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (obj5 != null) {
                obj3 = 1;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else if (i2 == -1) {
            if (i != 0) {
                if (obj5 == null) {
                    obj = 1;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (obj5 != null) {
                obj4 = 1;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else if (i2 != 0) {
            throw NotFoundException.getNotFoundInstance();
        } else if (i != 0) {
            if (obj5 == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (count < count2) {
                obj3 = 1;
                obj = 1;
            } else {
                obj4 = 1;
                obj2 = 1;
            }
        } else if (obj5 != null) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (obj != null) {
            if (obj2 == null) {
                AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (obj2 != null) {
            AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (obj4 != null) {
            if (obj3 == null) {
                AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (obj3 != null) {
            AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
        }
    }
}
