package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.ResultPoint;

final class DetectionResultRowIndicatorColumn extends DetectionResultColumn {
    private final boolean isLeft;

    DetectionResultRowIndicatorColumn(BoundingBox boundingBox, boolean z) {
        super(boundingBox);
        this.isLeft = z;
    }

    void setRowNumbers() {
        for (Codeword codeword : getCodewords()) {
            if (codeword != null) {
                codeword.setRowNumberAsRowIndicatorColumn();
            }
        }
    }

    int adjustCompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodeMetadata) {
        Codeword[] codewords = getCodewords();
        setRowNumbers();
        removeIncorrectCodewords(codewords, barcodeMetadata);
        BoundingBox boundingBox = getBoundingBox();
        ResultPoint topLeft = this.isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        ResultPoint bottomLeft = this.isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        int imageRowToCodewordIndex = imageRowToCodewordIndex((int) topLeft.getY());
        int imageRowToCodewordIndex2 = imageRowToCodewordIndex((int) bottomLeft.getY());
        float rowCount = ((float) (imageRowToCodewordIndex2 - imageRowToCodewordIndex)) / ((float) barcodeMetadata.getRowCount());
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        while (imageRowToCodewordIndex < imageRowToCodewordIndex2) {
            if (codewords[imageRowToCodewordIndex] != null) {
                Codeword codeword = codewords[imageRowToCodewordIndex];
                int rowNumber = codeword.getRowNumber() - i;
                if (rowNumber == 0) {
                    i2++;
                } else if (rowNumber == 1) {
                    i3 = Math.max(i3, i2);
                    i = codeword.getRowNumber();
                    i2 = 1;
                } else {
                    if (rowNumber >= 0 && codeword.getRowNumber() < barcodeMetadata.getRowCount()) {
                        if (rowNumber <= imageRowToCodewordIndex) {
                            if (i3 > 2) {
                                rowNumber *= i3 - 2;
                            }
                            Object obj = rowNumber >= imageRowToCodewordIndex ? 1 : null;
                            for (int i4 = 1; i4 <= rowNumber && obj == null; i4++) {
                                obj = codewords[imageRowToCodewordIndex - i4] != null ? 1 : null;
                            }
                            if (obj != null) {
                                codewords[imageRowToCodewordIndex] = null;
                            } else {
                                i = codeword.getRowNumber();
                                i2 = 1;
                            }
                        }
                    }
                    codewords[imageRowToCodewordIndex] = null;
                }
            }
            imageRowToCodewordIndex++;
        }
        double d = (double) rowCount;
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    int[] getRowHeights() throws FormatException {
        BarcodeMetadata barcodeMetadata = getBarcodeMetadata();
        if (barcodeMetadata == null) {
            return null;
        }
        adjustIncompleteIndicatorColumnRowNumbers(barcodeMetadata);
        int[] iArr = new int[barcodeMetadata.getRowCount()];
        for (Codeword codeword : getCodewords()) {
            if (codeword != null) {
                int rowNumber = codeword.getRowNumber();
                if (rowNumber < iArr.length) {
                    iArr[rowNumber] = iArr[rowNumber] + 1;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
        }
        return iArr;
    }

    int adjustIncompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodeMetadata) {
        BoundingBox boundingBox = getBoundingBox();
        ResultPoint topLeft = this.isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        ResultPoint bottomLeft = this.isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        int imageRowToCodewordIndex = imageRowToCodewordIndex((int) topLeft.getY());
        int imageRowToCodewordIndex2 = imageRowToCodewordIndex((int) bottomLeft.getY());
        float rowCount = ((float) (imageRowToCodewordIndex2 - imageRowToCodewordIndex)) / ((float) barcodeMetadata.getRowCount());
        Codeword[] codewords = getCodewords();
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        while (imageRowToCodewordIndex < imageRowToCodewordIndex2) {
            if (codewords[imageRowToCodewordIndex] != null) {
                Codeword codeword = codewords[imageRowToCodewordIndex];
                codeword.setRowNumberAsRowIndicatorColumn();
                int rowNumber = codeword.getRowNumber() - i;
                if (rowNumber == 0) {
                    i2++;
                } else if (rowNumber == 1) {
                    i3 = Math.max(i3, i2);
                    i = codeword.getRowNumber();
                    i2 = 1;
                } else if (codeword.getRowNumber() >= barcodeMetadata.getRowCount()) {
                    codewords[imageRowToCodewordIndex] = null;
                } else {
                    i = codeword.getRowNumber();
                    i2 = 1;
                }
            }
            imageRowToCodewordIndex++;
        }
        double d = (double) rowCount;
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    BarcodeMetadata getBarcodeMetadata() {
        Codeword[] codewords = getCodewords();
        BarcodeValue barcodeValue = new BarcodeValue();
        BarcodeValue barcodeValue2 = new BarcodeValue();
        BarcodeValue barcodeValue3 = new BarcodeValue();
        BarcodeValue barcodeValue4 = new BarcodeValue();
        for (Codeword codeword : codewords) {
            if (codeword != null) {
                codeword.setRowNumberAsRowIndicatorColumn();
                int value = codeword.getValue() % 30;
                int rowNumber = codeword.getRowNumber();
                if (!this.isLeft) {
                    rowNumber += 2;
                }
                switch (rowNumber % 3) {
                    case 0:
                        barcodeValue2.setValue((value * 3) + 1);
                        break;
                    case 1:
                        barcodeValue4.setValue(value / 3);
                        barcodeValue3.setValue(value % 3);
                        break;
                    case 2:
                        barcodeValue.setValue(value + 1);
                        break;
                    default:
                        break;
                }
            }
        }
        if (!(barcodeValue.getValue().length == 0 || barcodeValue2.getValue().length == 0 || barcodeValue3.getValue().length == 0 || barcodeValue4.getValue().length == 0 || barcodeValue.getValue()[0] < 1 || barcodeValue2.getValue()[0] + barcodeValue3.getValue()[0] < 3)) {
            if (barcodeValue2.getValue()[0] + barcodeValue3.getValue()[0] <= 90) {
                BarcodeMetadata barcodeMetadata = new BarcodeMetadata(barcodeValue.getValue()[0], barcodeValue2.getValue()[0], barcodeValue3.getValue()[0], barcodeValue4.getValue()[0]);
                removeIncorrectCodewords(codewords, barcodeMetadata);
                return barcodeMetadata;
            }
        }
        return null;
    }

    private void removeIncorrectCodewords(Codeword[] codewordArr, BarcodeMetadata barcodeMetadata) {
        for (int i = 0; i < codewordArr.length; i++) {
            Codeword codeword = codewordArr[i];
            if (codewordArr[i] != null) {
                int value = codeword.getValue() % 30;
                int rowNumber = codeword.getRowNumber();
                if (rowNumber <= barcodeMetadata.getRowCount()) {
                    if (!this.isLeft) {
                        rowNumber += 2;
                    }
                    switch (rowNumber % 3) {
                        case 0:
                            if ((value * 3) + 1 == barcodeMetadata.getRowCountUpperPart()) {
                                break;
                            }
                            codewordArr[i] = null;
                            break;
                        case 1:
                            if (value / 3 != barcodeMetadata.getErrorCorrectionLevel() || value % 3 != barcodeMetadata.getRowCountLowerPart()) {
                                codewordArr[i] = null;
                                break;
                            }
                            break;
                        case 2:
                            if (value + 1 == barcodeMetadata.getColumnCount()) {
                                break;
                            }
                            codewordArr[i] = null;
                            break;
                        default:
                            break;
                    }
                }
                codewordArr[i] = null;
            }
        }
    }

    boolean isLeft() {
        return this.isLeft;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IsLeft: ");
        stringBuilder.append(this.isLeft);
        stringBuilder.append('\n');
        stringBuilder.append(super.toString());
        return stringBuilder.toString();
    }
}
