package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import java.util.Map;

public final class Decoder {
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);

    private void correctErrors(byte[] r6, int r7) throws com.google.zxing.ChecksumException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:11:0x0027 in {2, 7, 8, 10} preds:[]
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
        r0 = r6.length;
        r1 = new int[r0];
        r2 = 0;
        r3 = 0;
    L_0x0005:
        if (r3 >= r0) goto L_0x0010;
    L_0x0007:
        r4 = r6[r3];
        r4 = r4 & 255;
        r1[r3] = r4;
        r3 = r3 + 1;
        goto L_0x0005;
    L_0x0010:
        r0 = r6.length;
        r0 = r0 - r7;
        r3 = r5.rsDecoder;	 Catch:{ ReedSolomonException -> 0x0022 }
        r3.decode(r1, r0);	 Catch:{ ReedSolomonException -> 0x0022 }
    L_0x0017:
        if (r2 >= r7) goto L_0x0021;
    L_0x0019:
        r0 = r1[r2];
        r0 = (byte) r0;
        r6[r2] = r0;
        r2 = r2 + 1;
        goto L_0x0017;
    L_0x0021:
        return;
    L_0x0022:
        r6 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.Decoder.correctErrors(byte[], int):void");
    }

    public DecoderResult decode(boolean[][] zArr) throws ChecksumException, FormatException {
        return decode(zArr, null);
    }

    public DecoderResult decode(boolean[][] zArr, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException {
        int length = zArr.length;
        BitMatrix bitMatrix = new BitMatrix(length);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i][i2]) {
                    bitMatrix.set(i2, i);
                }
            }
        }
        return decode(bitMatrix, (Map) map);
    }

    public DecoderResult decode(BitMatrix bitMatrix) throws ChecksumException, FormatException {
        return decode(bitMatrix, null);
    }

    public DecoderResult decode(BitMatrix bitMatrix, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        ChecksumException checksumException;
        BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        bitMatrix = null;
        try {
            bitMatrix = decode(bitMatrixParser, (Map) map);
            return bitMatrix;
        } catch (FormatException e) {
            FormatException formatException = e;
            checksumException = bitMatrix;
            bitMatrix = formatException;
            try {
                bitMatrixParser.remask();
                bitMatrixParser.setMirror(true);
                bitMatrixParser.readVersion();
                bitMatrixParser.readFormatInformation();
                bitMatrixParser.mirror();
                map = decode(bitMatrixParser, (Map) map);
                map.setOther(new QRCodeDecoderMetaData(true));
                return map;
            } catch (FormatException e2) {
                map = e2;
                if (bitMatrix == null) {
                    throw bitMatrix;
                } else if (checksumException == null) {
                    throw checksumException;
                } else {
                    throw map;
                }
            } catch (ChecksumException e3) {
                map = e3;
                if (bitMatrix == null) {
                    throw bitMatrix;
                } else if (checksumException == null) {
                    throw map;
                } else {
                    throw checksumException;
                }
            }
        } catch (ChecksumException e4) {
            checksumException = e4;
            bitMatrixParser.remask();
            bitMatrixParser.setMirror(true);
            bitMatrixParser.readVersion();
            bitMatrixParser.readFormatInformation();
            bitMatrixParser.mirror();
            map = decode(bitMatrixParser, (Map) map);
            map.setOther(new QRCodeDecoderMetaData(true));
            return map;
        }
    }

    private DecoderResult decode(BitMatrixParser bitMatrixParser, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        Version readVersion = bitMatrixParser.readVersion();
        ErrorCorrectionLevel errorCorrectionLevel = bitMatrixParser.readFormatInformation().getErrorCorrectionLevel();
        bitMatrixParser = DataBlock.getDataBlocks(bitMatrixParser.readCodewords(), readVersion, errorCorrectionLevel);
        int i = 0;
        for (DataBlock numDataCodewords : bitMatrixParser) {
            i += numDataCodewords.getNumDataCodewords();
        }
        byte[] bArr = new byte[i];
        int length = bitMatrixParser.length;
        i = 0;
        int i2 = 0;
        while (i < length) {
            DataBlock dataBlock = bitMatrixParser[i];
            byte[] codewords = dataBlock.getCodewords();
            int numDataCodewords2 = dataBlock.getNumDataCodewords();
            correctErrors(codewords, numDataCodewords2);
            int i3 = i2;
            i2 = 0;
            while (i2 < numDataCodewords2) {
                int i4 = i3 + 1;
                bArr[i3] = codewords[i2];
                i2++;
                i3 = i4;
            }
            i++;
            i2 = i3;
        }
        return DecodedBitStreamParser.decode(bArr, readVersion, errorCorrectionLevel, map);
    }
}
