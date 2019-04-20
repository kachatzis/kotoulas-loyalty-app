package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.List;

public final class PDF417ScanningDecoder {
    private static final int CODEWORD_SKEW_SIZE = 2;
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
    private static final ErrorCorrection errorCorrection = new ErrorCorrection();

    private static boolean checkCodewordSkew(int i, int i2, int i3) {
        return i2 + -2 <= i && i <= i3 + 2;
    }

    private static com.google.zxing.common.DecoderResult createDecoderResultFromAmbiguousValues(int r7, int[] r8, int[] r9, int[] r10, int[][] r11) throws com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:28:0x0052 in {6, 9, 17, 20, 22, 23, 25, 27} preds:[]
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
        r0 = r10.length;
        r0 = new int[r0];
        r1 = 100;
    L_0x0005:
        r2 = r1 + -1;
        if (r1 <= 0) goto L_0x004d;
    L_0x0009:
        r1 = 0;
        r3 = 0;
    L_0x000b:
        r4 = r0.length;
        if (r3 >= r4) goto L_0x001b;
    L_0x000e:
        r4 = r10[r3];
        r5 = r11[r3];
        r6 = r0[r3];
        r5 = r5[r6];
        r8[r4] = r5;
        r3 = r3 + 1;
        goto L_0x000b;
    L_0x001b:
        r7 = decodeCodewords(r8, r7, r9);	 Catch:{ ChecksumException -> 0x0020 }
        return r7;
    L_0x0020:
        r3 = r0.length;
        if (r3 == 0) goto L_0x0048;
    L_0x0023:
        r3 = 0;
    L_0x0024:
        r4 = r0.length;
        if (r3 >= r4) goto L_0x0046;
    L_0x0027:
        r4 = r0[r3];
        r5 = r11[r3];
        r5 = r5.length;
        r5 = r5 + -1;
        if (r4 >= r5) goto L_0x0037;
    L_0x0030:
        r1 = r0[r3];
        r1 = r1 + 1;
        r0[r3] = r1;
        goto L_0x0046;
    L_0x0037:
        r0[r3] = r1;
        r4 = r0.length;
        r4 = r4 + -1;
        if (r3 == r4) goto L_0x0041;
    L_0x003e:
        r3 = r3 + 1;
        goto L_0x0024;
    L_0x0041:
        r7 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r7;
    L_0x0046:
        r1 = r2;
        goto L_0x0005;
    L_0x0048:
        r7 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r7;
    L_0x004d:
        r7 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.createDecoderResultFromAmbiguousValues(int, int[], int[], int[], int[][]):com.google.zxing.common.DecoderResult");
    }

    private static int getNumberOfECCodeWords(int i) {
        return 2 << i;
    }

    private PDF417ScanningDecoder() {
    }

    public static DecoderResult decode(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) throws NotFoundException, FormatException, ChecksumException {
        DetectionResultColumn rowIndicatorColumn;
        DetectionResult detectionResult = null;
        DetectionResult detectionResult2 = detectionResult;
        DetectionResultColumn detectionResultColumn = detectionResult2;
        BoundingBox boundingBox = new BoundingBox(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
        int i3 = 0;
        while (i3 < 2) {
            rowIndicatorColumn = resultPoint != null ? getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint, true, i, i2) : detectionResult;
            if (resultPoint3 != null) {
                detectionResultColumn = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint3, false, i, i2);
            }
            detectionResult2 = merge(rowIndicatorColumn, detectionResultColumn);
            if (detectionResult2 == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (i3 != 0 || detectionResult2.getBoundingBox() == null || (detectionResult2.getBoundingBox().getMinY() >= boundingBox.getMinY() && detectionResult2.getBoundingBox().getMaxY() <= boundingBox.getMaxY())) {
                detectionResult2.setBoundingBox(boundingBox);
                break;
            } else {
                boundingBox = detectionResult2.getBoundingBox();
                i3++;
                Object obj = rowIndicatorColumn;
            }
        }
        rowIndicatorColumn = detectionResult;
        i3 = detectionResult2.getBarcodeColumnCount() + 1;
        detectionResult2.setDetectionResultColumn(0, rowIndicatorColumn);
        detectionResult2.setDetectionResultColumn(i3, detectionResultColumn);
        boolean z = rowIndicatorColumn != null;
        int i4 = i;
        int i5 = i2;
        int i6 = 1;
        while (i6 <= i3) {
            int i7 = z ? i6 : i3 - i6;
            if (detectionResult2.getDetectionResultColumn(i7) == null) {
                int i8;
                int i9;
                int i10;
                int startColumn;
                int i11;
                int i12;
                Codeword detectCodeword;
                int i13;
                if (i7 != 0) {
                    if (i7 != i3) {
                        rowIndicatorColumn = new DetectionResultColumn(boundingBox);
                        detectionResult2.setDetectionResultColumn(i7, rowIndicatorColumn);
                        i8 = -1;
                        i9 = i5;
                        i10 = -1;
                        i5 = i4;
                        i4 = boundingBox.getMinY();
                        while (i4 <= boundingBox.getMaxY()) {
                            startColumn = getStartColumn(detectionResult2, i7, i4, z);
                            if (startColumn >= 0) {
                                if (startColumn > boundingBox.getMaxX()) {
                                    i11 = startColumn;
                                    i12 = i10;
                                    resultPoint = i9;
                                    detectCodeword = detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z, i11, i4, i5, resultPoint);
                                    if (detectCodeword == null) {
                                        rowIndicatorColumn.setCodeword(i4, detectCodeword);
                                        i5 = Math.min(i5, detectCodeword.getWidth());
                                        i9 = Math.max(resultPoint, detectCodeword.getWidth());
                                        i10 = i11;
                                        i4++;
                                        i8 = -1;
                                    } else {
                                        i13 = resultPoint;
                                        i9 = i13;
                                        i10 = i12;
                                        i4++;
                                        i8 = -1;
                                    }
                                }
                            }
                            if (i10 != i8) {
                                i12 = i10;
                                i13 = i9;
                                i9 = i13;
                                i10 = i12;
                                i4++;
                                i8 = -1;
                            } else {
                                i11 = i10;
                                i12 = i10;
                                resultPoint = i9;
                                detectCodeword = detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z, i11, i4, i5, resultPoint);
                                if (detectCodeword == null) {
                                    i13 = resultPoint;
                                    i9 = i13;
                                    i10 = i12;
                                    i4++;
                                    i8 = -1;
                                } else {
                                    rowIndicatorColumn.setCodeword(i4, detectCodeword);
                                    i5 = Math.min(i5, detectCodeword.getWidth());
                                    i9 = Math.max(resultPoint, detectCodeword.getWidth());
                                    i10 = i11;
                                    i4++;
                                    i8 = -1;
                                }
                            }
                        }
                        i4 = i5;
                        i5 = i9;
                    }
                }
                rowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, i7 == 0);
                detectionResult2.setDetectionResultColumn(i7, rowIndicatorColumn);
                i8 = -1;
                i9 = i5;
                i10 = -1;
                i5 = i4;
                i4 = boundingBox.getMinY();
                while (i4 <= boundingBox.getMaxY()) {
                    startColumn = getStartColumn(detectionResult2, i7, i4, z);
                    if (startColumn >= 0) {
                        if (startColumn > boundingBox.getMaxX()) {
                            i11 = startColumn;
                            i12 = i10;
                            resultPoint = i9;
                            detectCodeword = detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z, i11, i4, i5, resultPoint);
                            if (detectCodeword == null) {
                                rowIndicatorColumn.setCodeword(i4, detectCodeword);
                                i5 = Math.min(i5, detectCodeword.getWidth());
                                i9 = Math.max(resultPoint, detectCodeword.getWidth());
                                i10 = i11;
                                i4++;
                                i8 = -1;
                            } else {
                                i13 = resultPoint;
                                i9 = i13;
                                i10 = i12;
                                i4++;
                                i8 = -1;
                            }
                        }
                    }
                    if (i10 != i8) {
                        i11 = i10;
                        i12 = i10;
                        resultPoint = i9;
                        detectCodeword = detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z, i11, i4, i5, resultPoint);
                        if (detectCodeword == null) {
                            i13 = resultPoint;
                            i9 = i13;
                            i10 = i12;
                            i4++;
                            i8 = -1;
                        } else {
                            rowIndicatorColumn.setCodeword(i4, detectCodeword);
                            i5 = Math.min(i5, detectCodeword.getWidth());
                            i9 = Math.max(resultPoint, detectCodeword.getWidth());
                            i10 = i11;
                            i4++;
                            i8 = -1;
                        }
                    } else {
                        i12 = i10;
                        i13 = i9;
                        i9 = i13;
                        i10 = i12;
                        i4++;
                        i8 = -1;
                    }
                }
                i4 = i5;
                i5 = i9;
            }
            i6++;
        }
        return createDecoderResult(detectionResult2);
    }

    private static DetectionResult merge(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) throws NotFoundException, FormatException {
        if (detectionResultRowIndicatorColumn == null && detectionResultRowIndicatorColumn2 == null) {
            return null;
        }
        BarcodeMetadata barcodeMetadata = getBarcodeMetadata(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2);
        if (barcodeMetadata == null) {
            return null;
        }
        return new DetectionResult(barcodeMetadata, BoundingBox.merge(adjustBoundingBox(detectionResultRowIndicatorColumn), adjustBoundingBox(detectionResultRowIndicatorColumn2)));
    }

    private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn) throws NotFoundException, FormatException {
        if (detectionResultRowIndicatorColumn == null) {
            return null;
        }
        int[] rowHeights = detectionResultRowIndicatorColumn.getRowHeights();
        if (rowHeights == null) {
            return null;
        }
        int max = getMax(rowHeights);
        int i = 0;
        int i2 = 0;
        for (int i3 : rowHeights) {
            i2 += max - i3;
            if (i3 > 0) {
                break;
            }
        }
        Codeword[] codewords = detectionResultRowIndicatorColumn.getCodewords();
        int i4 = 0;
        while (i2 > 0 && codewords[i4] == null) {
            i2--;
            i4++;
        }
        for (i4 = rowHeights.length - 1; i4 >= 0; i4--) {
            i += max - rowHeights[i4];
            if (rowHeights[i4] > 0) {
                break;
            }
        }
        max = codewords.length - 1;
        while (i > 0 && codewords[max] == null) {
            i--;
            max--;
        }
        return detectionResultRowIndicatorColumn.getBoundingBox().addMissingRows(i2, i, detectionResultRowIndicatorColumn.isLeft());
    }

    private static int getMax(int[] iArr) {
        int i = -1;
        for (int max : iArr) {
            i = Math.max(i, max);
        }
        return i;
    }

    private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) {
        BarcodeMetadata barcodeMetadata = null;
        if (detectionResultRowIndicatorColumn != null) {
            detectionResultRowIndicatorColumn = detectionResultRowIndicatorColumn.getBarcodeMetadata();
            if (detectionResultRowIndicatorColumn != null) {
                if (detectionResultRowIndicatorColumn2 != null) {
                    detectionResultRowIndicatorColumn2 = detectionResultRowIndicatorColumn2.getBarcodeMetadata();
                    if (detectionResultRowIndicatorColumn2 != null) {
                        if (detectionResultRowIndicatorColumn.getColumnCount() == detectionResultRowIndicatorColumn2.getColumnCount() || detectionResultRowIndicatorColumn.getErrorCorrectionLevel() == detectionResultRowIndicatorColumn2.getErrorCorrectionLevel() || detectionResultRowIndicatorColumn.getRowCount() == detectionResultRowIndicatorColumn2.getRowCount()) {
                            return detectionResultRowIndicatorColumn;
                        }
                        return null;
                    }
                }
                return detectionResultRowIndicatorColumn;
            }
        }
        if (detectionResultRowIndicatorColumn2 != null) {
            barcodeMetadata = detectionResultRowIndicatorColumn2.getBarcodeMetadata();
        }
        return barcodeMetadata;
    }

    private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix bitMatrix, BoundingBox boundingBox, ResultPoint resultPoint, boolean z, int i, int i2) {
        boolean z2 = z;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z2);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int x = (int) resultPoint.getX();
            int y = (int) resultPoint.getY();
            while (y <= boundingBox.getMaxY() && y >= boundingBox.getMinY()) {
                Codeword detectCodeword = detectCodeword(bitMatrix, 0, bitMatrix.getWidth(), z, x, y, i, i2);
                if (detectCodeword != null) {
                    detectionResultRowIndicatorColumn.setCodeword(y, detectCodeword);
                    if (z2) {
                        x = detectCodeword.getStartX();
                    } else {
                        x = detectCodeword.getEndX();
                    }
                }
                y += i4;
            }
            i3++;
        }
        return detectionResultRowIndicatorColumn;
    }

    private static void adjustCodewordCount(DetectionResult detectionResult, BarcodeValue[][] barcodeValueArr) throws NotFoundException {
        int[] value = barcodeValueArr[0][1].getValue();
        int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * detectionResult.getBarcodeRowCount()) - getNumberOfECCodeWords(detectionResult.getBarcodeECLevel());
        if (value.length == null) {
            if (barcodeColumnCount < 1 || barcodeColumnCount > PDF417Common.MAX_CODEWORDS_IN_BARCODE) {
                throw NotFoundException.getNotFoundInstance();
            }
            barcodeValueArr[0][1].setValue(barcodeColumnCount);
        } else if (value[0] != barcodeColumnCount) {
            barcodeValueArr[0][1].setValue(barcodeColumnCount);
        }
    }

    private static DecoderResult createDecoderResult(DetectionResult detectionResult) throws FormatException, ChecksumException, NotFoundException {
        BarcodeValue[][] createBarcodeMatrix = createBarcodeMatrix(detectionResult);
        adjustCodewordCount(detectionResult, createBarcodeMatrix);
        Collection arrayList = new ArrayList();
        int[] iArr = new int[(detectionResult.getBarcodeRowCount() * detectionResult.getBarcodeColumnCount())];
        List arrayList2 = new ArrayList();
        Collection arrayList3 = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < detectionResult.getBarcodeRowCount(); i2++) {
            int i3 = 0;
            while (i3 < detectionResult.getBarcodeColumnCount()) {
                int i4 = i3 + 1;
                Object value = createBarcodeMatrix[i2][i4].getValue();
                int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * i2) + i3;
                if (value.length == 0) {
                    arrayList.add(Integer.valueOf(barcodeColumnCount));
                } else if (value.length == 1) {
                    iArr[barcodeColumnCount] = value[0];
                } else {
                    arrayList3.add(Integer.valueOf(barcodeColumnCount));
                    arrayList2.add(value);
                }
                i3 = i4;
            }
        }
        int[][] iArr2 = new int[arrayList2.size()][];
        while (i < iArr2.length) {
            iArr2[i] = (int[]) arrayList2.get(i);
            i++;
        }
        return createDecoderResultFromAmbiguousValues(detectionResult.getBarcodeECLevel(), iArr, PDF417Common.toIntArray(arrayList), PDF417Common.toIntArray(arrayList3), iArr2);
    }

    private static BarcodeValue[][] createBarcodeMatrix(DetectionResult detectionResult) throws FormatException {
        BarcodeValue[][] barcodeValueArr = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, new int[]{detectionResult.getBarcodeRowCount(), detectionResult.getBarcodeColumnCount() + 2});
        for (int i = 0; i < barcodeValueArr.length; i++) {
            for (int i2 = 0; i2 < barcodeValueArr[i].length; i2++) {
                barcodeValueArr[i][i2] = new BarcodeValue();
            }
        }
        int i3 = 0;
        for (DetectionResultColumn detectionResultColumn : detectionResult.getDetectionResultColumns()) {
            if (detectionResultColumn != null) {
                for (Codeword codeword : detectionResultColumn.getCodewords()) {
                    if (codeword != null) {
                        int rowNumber = codeword.getRowNumber();
                        if (rowNumber < 0) {
                            continue;
                        } else if (rowNumber < barcodeValueArr.length) {
                            barcodeValueArr[rowNumber][i3].setValue(codeword.getValue());
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    }
                }
                continue;
            }
            i3++;
        }
        return barcodeValueArr;
    }

    private static boolean isValidBarcodeColumn(DetectionResult detectionResult, int i) {
        return i >= 0 && i <= detectionResult.getBarcodeColumnCount() + 1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int getStartColumn(com.google.zxing.pdf417.decoder.DetectionResult r6, int r7, int r8, boolean r9) {
        /*
        if (r9 == 0) goto L_0x0004;
    L_0x0002:
        r0 = 1;
        goto L_0x0005;
    L_0x0004:
        r0 = -1;
    L_0x0005:
        r1 = 0;
        r2 = r7 - r0;
        r3 = isValidBarcodeColumn(r6, r2);
        if (r3 == 0) goto L_0x0016;
    L_0x000e:
        r1 = r6.getDetectionResultColumn(r2);
        r1 = r1.getCodeword(r8);
    L_0x0016:
        if (r1 == 0) goto L_0x0024;
    L_0x0018:
        if (r9 == 0) goto L_0x001f;
    L_0x001a:
        r6 = r1.getEndX();
        goto L_0x0023;
    L_0x001f:
        r6 = r1.getStartX();
    L_0x0023:
        return r6;
    L_0x0024:
        r1 = r6.getDetectionResultColumn(r7);
        r1 = r1.getCodewordNearby(r8);
        if (r1 == 0) goto L_0x003a;
    L_0x002e:
        if (r9 == 0) goto L_0x0035;
    L_0x0030:
        r6 = r1.getStartX();
        goto L_0x0039;
    L_0x0035:
        r6 = r1.getEndX();
    L_0x0039:
        return r6;
    L_0x003a:
        r3 = isValidBarcodeColumn(r6, r2);
        if (r3 == 0) goto L_0x0048;
    L_0x0040:
        r1 = r6.getDetectionResultColumn(r2);
        r1 = r1.getCodewordNearby(r8);
    L_0x0048:
        if (r1 == 0) goto L_0x0056;
    L_0x004a:
        if (r9 == 0) goto L_0x0051;
    L_0x004c:
        r6 = r1.getEndX();
        goto L_0x0055;
    L_0x0051:
        r6 = r1.getStartX();
    L_0x0055:
        return r6;
    L_0x0056:
        r8 = 0;
        r1 = 0;
    L_0x0058:
        r7 = r7 - r0;
        r2 = isValidBarcodeColumn(r6, r7);
        if (r2 == 0) goto L_0x008f;
    L_0x005f:
        r2 = r6.getDetectionResultColumn(r7);
        r2 = r2.getCodewords();
        r3 = r2.length;
        r4 = 0;
    L_0x0069:
        if (r4 >= r3) goto L_0x008c;
    L_0x006b:
        r5 = r2[r4];
        if (r5 == 0) goto L_0x0089;
    L_0x006f:
        if (r9 == 0) goto L_0x0076;
    L_0x0071:
        r6 = r5.getEndX();
        goto L_0x007a;
    L_0x0076:
        r6 = r5.getStartX();
    L_0x007a:
        r0 = r0 * r1;
        r7 = r5.getEndX();
        r8 = r5.getStartX();
        r7 = r7 - r8;
        r0 = r0 * r7;
        r6 = r6 + r0;
        return r6;
    L_0x0089:
        r4 = r4 + 1;
        goto L_0x0069;
    L_0x008c:
        r1 = r1 + 1;
        goto L_0x0058;
    L_0x008f:
        if (r9 == 0) goto L_0x009a;
    L_0x0091:
        r6 = r6.getBoundingBox();
        r6 = r6.getMinX();
        goto L_0x00a2;
    L_0x009a:
        r6 = r6.getBoundingBox();
        r6 = r6.getMaxX();
    L_0x00a2:
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.getStartColumn(com.google.zxing.pdf417.decoder.DetectionResult, int, int, boolean):int");
    }

    private static Codeword detectCodeword(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        i3 = adjustCodewordStartColumn(bitMatrix, i, i2, z, i3, i4);
        bitMatrix = getModuleBitCount(bitMatrix, i, i2, z, i3, i4);
        if (bitMatrix == null) {
            return null;
        }
        i2 = PDF417Common.getBitCountSum(bitMatrix);
        if (z) {
            boolean z2 = i3;
            i3 += i2;
            z = z2;
        } else {
            for (z = false; z < bitMatrix.length / 2; z++) {
                i4 = bitMatrix[z];
                bitMatrix[z] = bitMatrix[(bitMatrix.length - 1) - z];
                bitMatrix[(bitMatrix.length - 1) - z] = i4;
            }
            z = i3 - i2;
        }
        if (checkCodewordSkew(i2, i5, i6) == 0) {
            return null;
        }
        int decodedValue = PDF417CodewordDecoder.getDecodedValue(bitMatrix);
        i2 = PDF417Common.getCodeword(decodedValue);
        if (i2 == -1) {
            return null;
        }
        return new Codeword(z, i3, getCodewordBucketNumber(decodedValue), i2);
    }

    private static int[] getModuleBitCount(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4) {
        int[] iArr = new int[8];
        int i5 = z ? 1 : -1;
        boolean z2 = z;
        int i6 = 0;
        while (true) {
            if (((z && i3 < i2) || (!z && i3 >= i)) && i6 < iArr.length) {
                if (bitMatrix.get(i3, i4) == z2) {
                    iArr[i6] = iArr[i6] + 1;
                    i3 += i5;
                } else {
                    i6++;
                    z2 = !z2;
                }
            }
        }
        if (i6 != iArr.length) {
            if ((!(z && i3 == i2) && (z || i3 != i)) || i6 != iArr.length - 1) {
                return null;
            }
        }
        return iArr;
    }

    private static int adjustCodewordStartColumn(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4) {
        boolean z2 = z;
        int i5 = z ? -1 : 1;
        int i6 = i3;
        for (z = false; z < true; z++) {
            while (true) {
                if (((!z2 || i6 < i) && (z2 || i6 >= i2)) || z2 != bitMatrix.get(i6, i4)) {
                    i5 = -i5;
                } else if (Math.abs(i3 - i6) > 2) {
                    return i3;
                } else {
                    i6 += i5;
                }
            }
            i5 = -i5;
            z2 = !z2;
        }
        return i6;
    }

    private static DecoderResult decodeCodewords(int[] iArr, int i, int[] iArr2) throws FormatException, ChecksumException {
        if (iArr.length != 0) {
            int i2 = 1 << (i + 1);
            int correctErrors = correctErrors(iArr, iArr2, i2);
            verifyCodewordCount(iArr, i2);
            iArr = DecodedBitStreamParser.decode(iArr, String.valueOf(i));
            iArr.setErrorsCorrected(Integer.valueOf(correctErrors));
            iArr.setErasures(Integer.valueOf(iArr2.length));
            return iArr;
        }
        throw FormatException.getFormatInstance();
    }

    private static int correctErrors(int[] iArr, int[] iArr2, int i) throws ChecksumException {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return errorCorrection.decode(iArr, i, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static void verifyCodewordCount(int[] iArr, int i) throws FormatException {
        if (iArr.length >= 4) {
            int i2 = iArr[0];
            if (i2 > iArr.length) {
                throw FormatException.getFormatInstance();
            } else if (i2 != 0) {
                return;
            } else {
                if (i < iArr.length) {
                    iArr[0] = iArr.length - i;
                    return;
                }
                throw FormatException.getFormatInstance();
            }
        }
        throw FormatException.getFormatInstance();
    }

    private static int[] getBitCountForCodeword(int i) {
        int[] iArr = new int[8];
        int length = iArr.length - 1;
        int i2 = 0;
        while (true) {
            int i3 = i & 1;
            if (i3 != i2) {
                length--;
                if (length < 0) {
                    return iArr;
                }
                i2 = i3;
            }
            iArr[length] = iArr[length] + 1;
            i >>= 1;
        }
    }

    private static int getCodewordBucketNumber(int i) {
        return getCodewordBucketNumber(getBitCountForCodeword(i));
    }

    private static int getCodewordBucketNumber(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    public static String toString(BarcodeValue[][] barcodeValueArr) {
        Formatter formatter = new Formatter();
        for (int i = 0; i < barcodeValueArr.length; i++) {
            formatter.format("Row %2d: ", new Object[]{Integer.valueOf(i)});
            for (BarcodeValue value : barcodeValueArr[i]) {
                if (value.getValue().length == 0) {
                    formatter.format("        ", (Object[]) null);
                } else {
                    formatter.format("%4d(%2d)", new Object[]{Integer.valueOf(barcodeValueArr[i][r3].getValue()[0]), barcodeValueArr[i][r3].getConfidence(barcodeValueArr[i][r3].getValue()[0])});
                }
            }
            formatter.format("%n", new Object[0]);
        }
        barcodeValueArr = formatter.toString();
        formatter.close();
        return barcodeValueArr;
    }
}
