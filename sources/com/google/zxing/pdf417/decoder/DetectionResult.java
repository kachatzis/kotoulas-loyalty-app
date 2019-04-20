package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.Formatter;

final class DetectionResult {
    private static final int ADJUST_ROW_NUMBER_SKIP = 2;
    private final int barcodeColumnCount;
    private final BarcodeMetadata barcodeMetadata;
    private BoundingBox boundingBox;
    private final DetectionResultColumn[] detectionResultColumns = new DetectionResultColumn[(this.barcodeColumnCount + 2)];

    DetectionResult(BarcodeMetadata barcodeMetadata, BoundingBox boundingBox) {
        this.barcodeMetadata = barcodeMetadata;
        this.barcodeColumnCount = barcodeMetadata.getColumnCount();
        this.boundingBox = boundingBox;
    }

    DetectionResultColumn[] getDetectionResultColumns() {
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[0]);
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[this.barcodeColumnCount + 1]);
        int i = PDF417Common.MAX_CODEWORDS_IN_BARCODE;
        while (true) {
            int adjustRowNumbers = adjustRowNumbers();
            if (adjustRowNumbers <= 0) {
                break;
            } else if (adjustRowNumbers >= i) {
                break;
            } else {
                i = adjustRowNumbers;
            }
        }
        return this.detectionResultColumns;
    }

    private void adjustIndicatorColumnRowNumbers(DetectionResultColumn detectionResultColumn) {
        if (detectionResultColumn != null) {
            ((DetectionResultRowIndicatorColumn) detectionResultColumn).adjustCompleteIndicatorColumnRowNumbers(this.barcodeMetadata);
        }
    }

    private int adjustRowNumbers() {
        int adjustRowNumbersByRow = adjustRowNumbersByRow();
        if (adjustRowNumbersByRow == 0) {
            return 0;
        }
        for (int i = 1; i < this.barcodeColumnCount + 1; i++) {
            Codeword[] codewords = this.detectionResultColumns[i].getCodewords();
            for (int i2 = 0; i2 < codewords.length; i2++) {
                if (codewords[i2] != null) {
                    if (!codewords[i2].hasValidRowNumber()) {
                        adjustRowNumbers(i, i2, codewords);
                    }
                }
            }
        }
        return adjustRowNumbersByRow;
    }

    private int adjustRowNumbersByRow() {
        adjustRowNumbersFromBothRI();
        return adjustRowNumbersFromLRI() + adjustRowNumbersFromRRI();
    }

    private void adjustRowNumbersFromBothRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        int i = 0;
        if (detectionResultColumnArr[0] != null) {
            if (detectionResultColumnArr[this.barcodeColumnCount + 1] != null) {
                Codeword[] codewords = detectionResultColumnArr[0].getCodewords();
                Codeword[] codewords2 = this.detectionResultColumns[this.barcodeColumnCount + 1].getCodewords();
                while (i < codewords.length) {
                    if (!(codewords[i] == null || codewords2[i] == null || codewords[i].getRowNumber() != codewords2[i].getRowNumber())) {
                        for (int i2 = 1; i2 <= this.barcodeColumnCount; i2++) {
                            Codeword codeword = this.detectionResultColumns[i2].getCodewords()[i];
                            if (codeword != null) {
                                codeword.setRowNumber(codewords[i].getRowNumber());
                                if (!codeword.hasValidRowNumber()) {
                                    this.detectionResultColumns[i2].getCodewords()[i] = null;
                                }
                            }
                        }
                    }
                    i++;
                }
            }
        }
    }

    private int adjustRowNumbersFromRRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        int i = this.barcodeColumnCount;
        if (detectionResultColumnArr[i + 1] == null) {
            return 0;
        }
        Codeword[] codewords = detectionResultColumnArr[i + 1].getCodewords();
        int i2 = 0;
        for (i = 0; i < codewords.length; i++) {
            if (codewords[i] != null) {
                int rowNumber = codewords[i].getRowNumber();
                int i3 = i2;
                i2 = 0;
                for (int i4 = this.barcodeColumnCount + 1; i4 > 0 && i2 < 2; i4--) {
                    Codeword codeword = this.detectionResultColumns[i4].getCodewords()[i];
                    if (codeword != null) {
                        i2 = adjustRowNumberIfValid(rowNumber, i2, codeword);
                        if (!codeword.hasValidRowNumber()) {
                            i3++;
                        }
                    }
                }
                i2 = i3;
            }
        }
        return i2;
    }

    private int adjustRowNumbersFromLRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[0] == null) {
            return 0;
        }
        Codeword[] codewords = detectionResultColumnArr[0].getCodewords();
        int i = 0;
        for (int i2 = 0; i2 < codewords.length; i2++) {
            if (codewords[i2] != null) {
                int rowNumber = codewords[i2].getRowNumber();
                int i3 = i;
                int i4 = 0;
                for (i = 1; i < this.barcodeColumnCount + 1 && i4 < 2; i++) {
                    Codeword codeword = this.detectionResultColumns[i].getCodewords()[i2];
                    if (codeword != null) {
                        i4 = adjustRowNumberIfValid(rowNumber, i4, codeword);
                        if (!codeword.hasValidRowNumber()) {
                            i3++;
                        }
                    }
                }
                i = i3;
            }
        }
        return i;
    }

    private static int adjustRowNumberIfValid(int i, int i2, Codeword codeword) {
        if (codeword == null) {
            return i2;
        }
        if (!codeword.hasValidRowNumber()) {
            if (codeword.isValidRowNumber(i)) {
                codeword.setRowNumber(i);
                i2 = 0;
            } else {
                i2++;
            }
        }
        return i2;
    }

    private void adjustRowNumbers(int i, int i2, Codeword[] codewordArr) {
        Codeword codeword = codewordArr[i2];
        Codeword[] codewords = this.detectionResultColumns[i - 1].getCodewords();
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        i++;
        i = detectionResultColumnArr[i] != null ? detectionResultColumnArr[i].getCodewords() : codewords;
        Codeword[] codewordArr2 = new Codeword[14];
        codewordArr2[2] = codewords[i2];
        codewordArr2[3] = i[i2];
        int i3 = 0;
        if (i2 > 0) {
            int i4 = i2 - 1;
            codewordArr2[0] = codewordArr[i4];
            codewordArr2[4] = codewords[i4];
            codewordArr2[5] = i[i4];
        }
        if (i2 > 1) {
            int i5 = i2 - 2;
            codewordArr2[8] = codewordArr[i5];
            codewordArr2[10] = codewords[i5];
            codewordArr2[11] = i[i5];
        }
        if (i2 < codewordArr.length - 1) {
            i4 = i2 + 1;
            codewordArr2[1] = codewordArr[i4];
            codewordArr2[6] = codewords[i4];
            codewordArr2[7] = i[i4];
        }
        if (i2 < codewordArr.length - 2) {
            i2 += 2;
            codewordArr2[9] = codewordArr[i2];
            codewordArr2[12] = codewords[i2];
            codewordArr2[13] = i[i2];
        }
        i = codewordArr2.length;
        while (i3 < i && adjustRowNumber(codeword, codewordArr2[i3]) == 0) {
            i3++;
        }
    }

    private static boolean adjustRowNumber(Codeword codeword, Codeword codeword2) {
        if (codeword2 == null || !codeword2.hasValidRowNumber() || codeword2.getBucket() != codeword.getBucket()) {
            return false;
        }
        codeword.setRowNumber(codeword2.getRowNumber());
        return true;
    }

    int getBarcodeColumnCount() {
        return this.barcodeColumnCount;
    }

    int getBarcodeRowCount() {
        return this.barcodeMetadata.getRowCount();
    }

    int getBarcodeECLevel() {
        return this.barcodeMetadata.getErrorCorrectionLevel();
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    void setDetectionResultColumn(int i, DetectionResultColumn detectionResultColumn) {
        this.detectionResultColumns[i] = detectionResultColumn;
    }

    DetectionResultColumn getDetectionResultColumn(int i) {
        return this.detectionResultColumns[i];
    }

    public String toString() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        DetectionResultColumn detectionResultColumn = detectionResultColumnArr[0];
        if (detectionResultColumn == null) {
            detectionResultColumn = detectionResultColumnArr[this.barcodeColumnCount + 1];
        }
        Formatter formatter = new Formatter();
        for (int i = 0; i < detectionResultColumn.getCodewords().length; i++) {
            formatter.format("CW %3d:", new Object[]{Integer.valueOf(i)});
            for (int i2 = 0; i2 < this.barcodeColumnCount + 2; i2++) {
                DetectionResultColumn[] detectionResultColumnArr2 = this.detectionResultColumns;
                if (detectionResultColumnArr2[i2] == null) {
                    formatter.format("    |   ", new Object[0]);
                } else {
                    if (detectionResultColumnArr2[i2].getCodewords()[i] == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        formatter.format(" %3d|%3d", new Object[]{Integer.valueOf(detectionResultColumnArr2[i2].getCodewords()[i].getRowNumber()), Integer.valueOf(detectionResultColumnArr2[i2].getCodewords()[i].getValue())});
                    }
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
