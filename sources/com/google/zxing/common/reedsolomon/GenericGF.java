package com.google.zxing.common.reedsolomon;

public final class GenericGF {
    public static final GenericGF AZTEC_DATA_10 = new GenericGF(1033, 1024, 1);
    public static final GenericGF AZTEC_DATA_12 = new GenericGF(4201, 4096, 1);
    public static final GenericGF AZTEC_DATA_6 = new GenericGF(67, 64, 1);
    public static final GenericGF AZTEC_DATA_8 = DATA_MATRIX_FIELD_256;
    public static final GenericGF AZTEC_PARAM = new GenericGF(19, 16, 1);
    public static final GenericGF DATA_MATRIX_FIELD_256 = new GenericGF(301, 256, 1);
    public static final GenericGF MAXICODE_FIELD_64 = AZTEC_DATA_6;
    public static final GenericGF QR_CODE_FIELD_256 = new GenericGF(285, 256, 0);
    private final int[] expTable;
    private final int generatorBase;
    private final int[] logTable;
    private final GenericGFPoly one;
    private final int primitive;
    private final int size;
    private final GenericGFPoly zero;

    static int addOrSubtract(int i, int i2) {
        return i ^ i2;
    }

    public GenericGF(int i, int i2, int i3) {
        this.primitive = i;
        this.size = i2;
        this.generatorBase = i3;
        this.expTable = new int[i2];
        this.logTable = new int[i2];
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            this.expTable[i5] = i4;
            i4 *= 2;
            if (i4 >= i2) {
                i4 = (i4 ^ i) & (i2 - 1);
            }
        }
        for (i = 0; i < i2 - 1; i++) {
            this.logTable[this.expTable[i]] = i;
        }
        this.zero = new GenericGFPoly(this, new int[]{null});
        this.one = new GenericGFPoly(this, new int[]{1});
    }

    GenericGFPoly getZero() {
        return this.zero;
    }

    GenericGFPoly getOne() {
        return this.one;
    }

    GenericGFPoly buildMonomial(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.zero;
        } else {
            i = new int[(i + 1)];
            i[0] = i2;
            return new GenericGFPoly(this, i);
        }
    }

    int exp(int i) {
        return this.expTable[i];
    }

    int log(int i) {
        if (i != 0) {
            return this.logTable[i];
        }
        throw new IllegalArgumentException();
    }

    int inverse(int i) {
        if (i != 0) {
            return this.expTable[(this.size - this.logTable[i]) - 1];
        }
        throw new ArithmeticException();
    }

    int multiply(int i, int i2) {
        if (i != 0) {
            if (i2 != 0) {
                int[] iArr = this.expTable;
                int[] iArr2 = this.logTable;
                return iArr[(iArr2[i] + iArr2[i2]) % (this.size - 1)];
            }
        }
        return 0;
    }

    public int getSize() {
        return this.size;
    }

    public int getGeneratorBase() {
        return this.generatorBase;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GF(0x");
        stringBuilder.append(Integer.toHexString(this.primitive));
        stringBuilder.append(',');
        stringBuilder.append(this.size);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }
}
