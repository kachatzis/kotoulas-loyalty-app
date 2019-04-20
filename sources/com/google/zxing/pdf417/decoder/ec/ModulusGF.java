package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.pdf417.PDF417Common;

public final class ModulusGF {
    public static final ModulusGF PDF417_GF = new ModulusGF(PDF417Common.NUMBER_OF_CODEWORDS, 3);
    private final int[] expTable;
    private final int[] logTable;
    private final int modulus;
    private final ModulusPoly one;
    private final ModulusPoly zero;

    private ModulusGF(int i, int i2) {
        this.modulus = i;
        this.expTable = new int[i];
        this.logTable = new int[i];
        int i3 = 1;
        for (int i4 = 0; i4 < i; i4++) {
            this.expTable[i4] = i3;
            i3 = (i3 * i2) % i;
        }
        for (i2 = 0; i2 < i - 1; i2++) {
            this.logTable[this.expTable[i2]] = i2;
        }
        this.zero = new ModulusPoly(this, new int[]{null});
        this.one = new ModulusPoly(this, new int[]{1});
    }

    ModulusPoly getZero() {
        return this.zero;
    }

    ModulusPoly getOne() {
        return this.one;
    }

    ModulusPoly buildMonomial(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.zero;
        } else {
            i = new int[(i + 1)];
            i[0] = i2;
            return new ModulusPoly(this, i);
        }
    }

    int add(int i, int i2) {
        return (i + i2) % this.modulus;
    }

    int subtract(int i, int i2) {
        int i3 = this.modulus;
        return ((i + i3) - i2) % i3;
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
            return this.expTable[(this.modulus - this.logTable[i]) - 1];
        }
        throw new ArithmeticException();
    }

    int multiply(int i, int i2) {
        if (i != 0) {
            if (i2 != 0) {
                int[] iArr = this.expTable;
                int[] iArr2 = this.logTable;
                return iArr[(iArr2[i] + iArr2[i2]) % (this.modulus - 1)];
            }
        }
        return 0;
    }

    int getSize() {
        return this.modulus;
    }
}
