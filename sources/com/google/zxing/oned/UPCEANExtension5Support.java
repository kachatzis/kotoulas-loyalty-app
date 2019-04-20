package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension5Support {
    private static final int[] CHECK_DIGIT_ENCODINGS = new int[]{24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder();

    private static int determineCheckDigit(int r2) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:9:0x0014 in {5, 6, 8} preds:[]
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
        r1 = 10;
        if (r0 >= r1) goto L_0x000f;
    L_0x0005:
        r1 = CHECK_DIGIT_ENCODINGS;
        r1 = r1[r0];
        if (r2 != r1) goto L_0x000c;
    L_0x000b:
        return r0;
    L_0x000c:
        r0 = r0 + 1;
        goto L_0x0001;
    L_0x000f:
        r2 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCEANExtension5Support.determineCheckDigit(int):int");
    }

    int decodeMiddle(com.google.zxing.common.BitArray r11, int[] r12, java.lang.StringBuilder r13) throws com.google.zxing.NotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x006d in {6, 9, 11, 12, 13, 18, 20, 22} preds:[]
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
        r10 = this;
        r0 = r10.decodeMiddleCounters;
        r1 = 0;
        r0[r1] = r1;
        r2 = 1;
        r0[r2] = r1;
        r3 = 2;
        r0[r3] = r1;
        r3 = 3;
        r0[r3] = r1;
        r3 = r11.getSize();
        r12 = r12[r2];
        r4 = r12;
        r12 = 0;
        r5 = 0;
    L_0x0017:
        r6 = 5;
        if (r12 >= r6) goto L_0x004e;
    L_0x001a:
        if (r4 >= r3) goto L_0x004e;
    L_0x001c:
        r6 = com.google.zxing.oned.UPCEANReader.L_AND_G_PATTERNS;
        r6 = com.google.zxing.oned.UPCEANReader.decodeDigit(r11, r0, r4, r6);
        r7 = r6 % 10;
        r7 = r7 + 48;
        r7 = (char) r7;
        r13.append(r7);
        r7 = r0.length;
        r8 = r4;
        r4 = 0;
    L_0x002d:
        if (r4 >= r7) goto L_0x0035;
    L_0x002f:
        r9 = r0[r4];
        r8 = r8 + r9;
        r4 = r4 + 1;
        goto L_0x002d;
    L_0x0035:
        r4 = 4;
        r7 = 10;
        if (r6 < r7) goto L_0x003f;
    L_0x003a:
        r6 = 4 - r12;
        r6 = r2 << r6;
        r5 = r5 | r6;
    L_0x003f:
        if (r12 == r4) goto L_0x004a;
    L_0x0041:
        r4 = r11.getNextSet(r8);
        r4 = r11.getNextUnset(r4);
        goto L_0x004b;
    L_0x004a:
        r4 = r8;
    L_0x004b:
        r12 = r12 + 1;
        goto L_0x0017;
    L_0x004e:
        r11 = r13.length();
        if (r11 != r6) goto L_0x0068;
    L_0x0054:
        r11 = determineCheckDigit(r5);
        r12 = r13.toString();
        r12 = extensionChecksum(r12);
        if (r12 != r11) goto L_0x0063;
    L_0x0062:
        return r4;
    L_0x0063:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
    L_0x0068:
        r11 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCEANExtension5Support.decodeMiddle(com.google.zxing.common.BitArray, int[], java.lang.StringBuilder):int");
    }

    UPCEANExtension5Support() {
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

    private static int extensionChecksum(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        for (int i2 = length - 2; i2 >= 0; i2 -= 2) {
            i += charSequence.charAt(i2) - 48;
        }
        i *= 3;
        for (length--; length >= 0; length -= 2) {
            i += charSequence.charAt(length) - 48;
        }
        return (i * 3) % 10;
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(String str) {
        if (str.length() != 5) {
            return null;
        }
        str = parseExtension5String(str);
        if (str == null) {
            return null;
        }
        Map<ResultMetadataType, Object> enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.SUGGESTED_PRICE, str);
        return enumMap;
    }

    private static String parseExtension5String(String str) {
        String str2;
        StringBuilder stringBuilder;
        char charAt = str.charAt(0);
        if (charAt == '0') {
            str2 = "£";
        } else if (charAt == '5') {
            str2 = "$";
        } else if (charAt != '9') {
            str2 = "";
        } else if ("90000".equals(str)) {
            return null;
        } else {
            if ("99991".equals(str)) {
                return "0.00";
            }
            if ("99990".equals(str)) {
                return "Used";
            }
            str2 = "";
        }
        str = Integer.parseInt(str.substring(1));
        String valueOf = String.valueOf(str / 100);
        str %= 100;
        if (str < 10) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("0");
            stringBuilder.append(str);
            str = stringBuilder.toString();
        } else {
            str = String.valueOf(str);
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append(valueOf);
        stringBuilder.append('.');
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}
