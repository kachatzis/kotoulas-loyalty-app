package com.google.zxing.maxicode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import java.util.Map;

public final class Decoder {
    private static final int ALL = 0;
    private static final int EVEN = 1;
    private static final int ODD = 2;
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.MAXICODE_FIELD_64);

    private void correctErrors(byte[] r8, int r9, int r10, int r11, int r12) throws com.google.zxing.ChecksumException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:22:0x0046 in {2, 3, 8, 9, 10, 16, 17, 18, 19, 21} preds:[]
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
        r7 = this;
        r0 = r10 + r11;
        if (r12 != 0) goto L_0x0006;
    L_0x0004:
        r1 = 1;
        goto L_0x0007;
    L_0x0006:
        r1 = 2;
    L_0x0007:
        r2 = r0 / r1;
        r2 = new int[r2];
        r3 = 0;
        r4 = 0;
    L_0x000d:
        if (r4 >= r0) goto L_0x0024;
    L_0x000f:
        if (r12 == 0) goto L_0x0017;
    L_0x0011:
        r5 = r4 % 2;
        r6 = r12 + -1;
        if (r5 != r6) goto L_0x0021;
    L_0x0017:
        r5 = r4 / r1;
        r6 = r4 + r9;
        r6 = r8[r6];
        r6 = r6 & 255;
        r2[r5] = r6;
    L_0x0021:
        r4 = r4 + 1;
        goto L_0x000d;
    L_0x0024:
        r0 = r7.rsDecoder;	 Catch:{ ReedSolomonException -> 0x0041 }
        r11 = r11 / r1;	 Catch:{ ReedSolomonException -> 0x0041 }
        r0.decode(r2, r11);	 Catch:{ ReedSolomonException -> 0x0041 }
    L_0x002a:
        if (r3 >= r10) goto L_0x0040;
    L_0x002c:
        if (r12 == 0) goto L_0x0034;
    L_0x002e:
        r11 = r3 % 2;
        r0 = r12 + -1;
        if (r11 != r0) goto L_0x003d;
    L_0x0034:
        r11 = r3 + r9;
        r0 = r3 / r1;
        r0 = r2[r0];
        r0 = (byte) r0;
        r8[r11] = r0;
    L_0x003d:
        r3 = r3 + 1;
        goto L_0x002a;
    L_0x0040:
        return;
    L_0x0041:
        r8 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.maxicode.decoder.Decoder.correctErrors(byte[], int, int, int, int):void");
    }

    public DecoderResult decode(BitMatrix bitMatrix) throws ChecksumException, FormatException {
        return decode(bitMatrix, null);
    }

    public DecoderResult decode(BitMatrix bitMatrix, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        Object obj;
        bitMatrix = new BitMatrixParser(bitMatrix).readCodewords();
        correctErrors(bitMatrix, 0, 10, 10, 0);
        int i = bitMatrix[0] & 15;
        byte[] bArr;
        switch (i) {
            case 2:
            case 3:
            case 4:
                bArr = bitMatrix;
                correctErrors(bArr, 20, 84, 40, 1);
                correctErrors(bArr, 20, 84, 40, 2);
                obj = new byte[94];
                break;
            case 5:
                bArr = bitMatrix;
                correctErrors(bArr, 20, 68, 56, 1);
                correctErrors(bArr, 20, 68, 56, 2);
                obj = new byte[78];
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        System.arraycopy(bitMatrix, 0, obj, 0, 10);
        System.arraycopy(bitMatrix, 20, obj, 10, obj.length - 10);
        return DecodedBitStreamParser.decode(obj, i);
    }
}
