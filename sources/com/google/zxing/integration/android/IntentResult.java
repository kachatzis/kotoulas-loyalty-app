package com.google.zxing.integration.android;

public final class IntentResult {
    private final String barcodeImagePath;
    private final String contents;
    private final String errorCorrectionLevel;
    private final String formatName;
    private final Integer orientation;
    private final byte[] rawBytes;

    IntentResult() {
        this(null, null, null, null, null, null);
    }

    IntentResult(String str, String str2, byte[] bArr, Integer num, String str3, String str4) {
        this.contents = str;
        this.formatName = str2;
        this.rawBytes = bArr;
        this.orientation = num;
        this.errorCorrectionLevel = str3;
        this.barcodeImagePath = str4;
    }

    public String getContents() {
        return this.contents;
    }

    public String getFormatName() {
        return this.formatName;
    }

    public byte[] getRawBytes() {
        return this.rawBytes;
    }

    public Integer getOrientation() {
        return this.orientation;
    }

    public String getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }

    public String getBarcodeImagePath() {
        return this.barcodeImagePath;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(120);
        stringBuilder.append("Format: ");
        stringBuilder.append(this.formatName);
        stringBuilder.append('\n');
        stringBuilder.append("Contents: ");
        stringBuilder.append(this.contents);
        stringBuilder.append('\n');
        byte[] bArr = this.rawBytes;
        int length = bArr == null ? 0 : bArr.length;
        stringBuilder.append("Raw bytes: (");
        stringBuilder.append(length);
        stringBuilder.append(" bytes)\n");
        stringBuilder.append("Orientation: ");
        stringBuilder.append(this.orientation);
        stringBuilder.append('\n');
        stringBuilder.append("EC level: ");
        stringBuilder.append(this.errorCorrectionLevel);
        stringBuilder.append('\n');
        stringBuilder.append("Barcode image: ");
        stringBuilder.append(this.barcodeImagePath);
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
