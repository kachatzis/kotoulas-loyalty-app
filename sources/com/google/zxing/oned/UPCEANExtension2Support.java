package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension2Support {
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder();

    int decodeMiddle(com.google.zxing.common.BitArray r12, int[] r13, java.lang.StringBuilder r14) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:22:0x006a in {5, 8, 10, 11, 12, 17, 19, 21} preds:[]
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
        r11 = this;
        r0 = r11.decodeMiddleCounters;
        r1 = 0;
        r0[r1] = r1;
        r2 = 1;
        r0[r2] = r1;
        r3 = 2;
        r0[r3] = r1;
        r4 = 3;
        r0[r4] = r1;
        r4 = r12.getSize();
        r13 = r13[r2];
        r5 = r13;
        r13 = 0;
        r6 = 0;
    L_0x0017:
        if (r13 >= r3) goto L_0x004d;
    L_0x0019:
        if (r5 >= r4) goto L_0x004d;
    L_0x001b:
        r7 = com.google.zxing.oned.UPCEANReader.L_AND_G_PATTERNS;
        r7 = com.google.zxing.oned.UPCEANReader.decodeDigit(r12, r0, r5, r7);
        r8 = r7 % 10;
        r8 = r8 + 48;
        r8 = (char) r8;
        r14.append(r8);
        r8 = r0.length;
        r9 = r5;
        r5 = 0;
    L_0x002c:
        if (r5 >= r8) goto L_0x0034;
    L_0x002e:
        r10 = r0[r5];
        r9 = r9 + r10;
        r5 = r5 + 1;
        goto L_0x002c;
    L_0x0034:
        r5 = 10;
        if (r7 < r5) goto L_0x003e;
    L_0x0038:
        r5 = 1 - r13;
        r5 = r2 << r5;
        r5 = r5 | r6;
        r6 = r5;
    L_0x003e:
        if (r13 == r2) goto L_0x0049;
    L_0x0040:
        r5 = r12.getNextSet(r9);
        r5 = r12.getNextUnset(r5);
        goto L_0x004a;
    L_0x0049:
        r5 = r9;
    L_0x004a:
        r13 = r13 + 1;
        goto L_0x0017;
    L_0x004d:
        r12 = r14.length();
        if (r12 != r3) goto L_0x0065;
    L_0x0053:
        r12 = r14.toString();
        r12 = java.lang.Integer.parseInt(r12);
        r12 = r12 % 4;
        if (r12 != r6) goto L_0x0060;
    L_0x005f:
        return r5;
    L_0x0060:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
    L_0x0065:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCEANExtension2Support.decodeMiddle(com.google.zxing.common.BitArray, int[], java.lang.StringBuilder):int");
    }

    UPCEANExtension2Support() {
    }

    Result decodeRow(int i, BitArray bitArray, int[] iArr) throws NotFoundException {
        StringBuilder stringBuilder = this.decodeRowStringBuffer;
        stringBuilder.setLength(0);
        bitArray = decodeMiddle(bitArray, iArr, stringBuilder);
        String stringBuilder2 = stringBuilder.toString();
        Map parseExtensionString = parseExtensionString(stringBuilder2);
        r4 = new ResultPoint[2];
        i = (float) i;
        r4[0] = new ResultPoint(((float) (iArr[0] + iArr[1])) / 2.0f, i);
        r4[1] = new ResultPoint((float) bitArray, i);
        Result result = new Result(stringBuilder2, null, r4, BarcodeFormat.UPC_EAN_EXTENSION);
        if (parseExtensionString != null) {
            result.putAllMetadata(parseExtensionString);
        }
        return result;
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(String str) {
        if (str.length() != 2) {
            return null;
        }
        Map<ResultMetadataType, Object> enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(str));
        return enumMap;
    }
}
