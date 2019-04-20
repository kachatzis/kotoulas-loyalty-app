package com.google.zxing.common;

import java.util.Arrays;

public final class BitMatrix implements Cloneable {
    private final int[] bits;
    private final int height;
    private final int rowSize;
    private final int width;

    public static com.google.zxing.common.BitMatrix parse(java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:43:0x00c1 in {8, 11, 14, 16, 19, 22, 24, 25, 28, 31, 33, 38, 39, 40, 42} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        if (r10 == 0) goto L_0x00bb;
    L_0x0002:
        r0 = r10.length();
        r0 = new boolean[r0];
        r1 = -1;
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = -1;
        r7 = 0;
    L_0x000f:
        r8 = r10.length();
        if (r3 >= r8) goto L_0x008e;
    L_0x0015:
        r8 = r10.charAt(r3);
        r9 = 10;
        if (r8 == r9) goto L_0x0074;
    L_0x001d:
        r8 = r10.charAt(r3);
        r9 = 13;
        if (r8 != r9) goto L_0x0026;
    L_0x0025:
        goto L_0x0074;
    L_0x0026:
        r8 = r11.length();
        r8 = r8 + r3;
        r8 = r10.substring(r3, r8);
        r8 = r8.equals(r11);
        if (r8 == 0) goto L_0x0040;
    L_0x0035:
        r8 = r11.length();
        r3 = r3 + r8;
        r8 = 1;
        r0[r4] = r8;
        r4 = r4 + 1;
        goto L_0x000f;
    L_0x0040:
        r8 = r12.length();
        r8 = r8 + r3;
        r8 = r10.substring(r3, r8);
        r8 = r8.equals(r12);
        if (r8 == 0) goto L_0x0059;
    L_0x004f:
        r8 = r12.length();
        r3 = r3 + r8;
        r0[r4] = r2;
        r4 = r4 + 1;
        goto L_0x000f;
    L_0x0059:
        r11 = new java.lang.IllegalArgumentException;
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r0 = "illegal character encountered: ";
        r12.append(r0);
        r10 = r10.substring(r3);
        r12.append(r10);
        r10 = r12.toString();
        r11.<init>(r10);
        throw r11;
    L_0x0074:
        if (r4 <= r5) goto L_0x008b;
    L_0x0076:
        if (r6 != r1) goto L_0x007b;
    L_0x0078:
        r6 = r4 - r5;
        goto L_0x007f;
    L_0x007b:
        r5 = r4 - r5;
        if (r5 != r6) goto L_0x0083;
    L_0x007f:
        r7 = r7 + 1;
        r5 = r4;
        goto L_0x008b;
    L_0x0083:
        r10 = new java.lang.IllegalArgumentException;
        r11 = "row lengths do not match";
        r10.<init>(r11);
        throw r10;
    L_0x008b:
        r3 = r3 + 1;
        goto L_0x000f;
    L_0x008e:
        if (r4 <= r5) goto L_0x00a5;
    L_0x0090:
        if (r6 != r1) goto L_0x0096;
    L_0x0092:
        r10 = r4 - r5;
        r6 = r10;
        goto L_0x009a;
    L_0x0096:
        r10 = r4 - r5;
        if (r10 != r6) goto L_0x009d;
    L_0x009a:
        r7 = r7 + 1;
        goto L_0x00a5;
    L_0x009d:
        r10 = new java.lang.IllegalArgumentException;
        r11 = "row lengths do not match";
        r10.<init>(r11);
        throw r10;
    L_0x00a5:
        r10 = new com.google.zxing.common.BitMatrix;
        r10.<init>(r6, r7);
    L_0x00aa:
        if (r2 >= r4) goto L_0x00ba;
    L_0x00ac:
        r11 = r0[r2];
        if (r11 == 0) goto L_0x00b7;
    L_0x00b0:
        r11 = r2 % r6;
        r12 = r2 / r6;
        r10.set(r11, r12);
    L_0x00b7:
        r2 = r2 + 1;
        goto L_0x00aa;
    L_0x00ba:
        return r10;
    L_0x00bb:
        r10 = new java.lang.IllegalArgumentException;
        r10.<init>();
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.BitMatrix.parse(java.lang.String, java.lang.String, java.lang.String):com.google.zxing.common.BitMatrix");
    }

    public void setRegion(int r8, int r9, int r10, int r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:21:0x0049 in {12, 13, 14, 16, 18, 20} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r7 = this;
        if (r9 < 0) goto L_0x0041;
    L_0x0002:
        if (r8 < 0) goto L_0x0041;
    L_0x0004:
        r0 = 1;
        if (r11 < r0) goto L_0x0039;
    L_0x0007:
        if (r10 < r0) goto L_0x0039;
    L_0x0009:
        r10 = r10 + r8;
        r11 = r11 + r9;
        r1 = r7.height;
        if (r11 > r1) goto L_0x0031;
    L_0x000f:
        r1 = r7.width;
        if (r10 > r1) goto L_0x0031;
    L_0x0013:
        if (r9 >= r11) goto L_0x0030;
    L_0x0015:
        r1 = r7.rowSize;
        r1 = r1 * r9;
        r2 = r8;
    L_0x001a:
        if (r2 >= r10) goto L_0x002d;
    L_0x001c:
        r3 = r7.bits;
        r4 = r2 / 32;
        r4 = r4 + r1;
        r5 = r3[r4];
        r6 = r2 & 31;
        r6 = r0 << r6;
        r5 = r5 | r6;
        r3[r4] = r5;
        r2 = r2 + 1;
        goto L_0x001a;
    L_0x002d:
        r9 = r9 + 1;
        goto L_0x0013;
    L_0x0030:
        return;
    L_0x0031:
        r8 = new java.lang.IllegalArgumentException;
        r9 = "The region must fit inside the matrix";
        r8.<init>(r9);
        throw r8;
    L_0x0039:
        r8 = new java.lang.IllegalArgumentException;
        r9 = "Height and width must be at least 1";
        r8.<init>(r9);
        throw r8;
    L_0x0041:
        r8 = new java.lang.IllegalArgumentException;
        r9 = "Left and top must be nonnegative";
        r8.<init>(r9);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.BitMatrix.setRegion(int, int, int, int):void");
    }

    public void xor(com.google.zxing.common.BitMatrix r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:17:0x0054 in {12, 13, 14, 16} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r10 = this;
        r0 = r10.width;
        r1 = r11.getWidth();
        if (r0 != r1) goto L_0x004c;
    L_0x0008:
        r0 = r10.height;
        r1 = r11.getHeight();
        if (r0 != r1) goto L_0x004c;
    L_0x0010:
        r0 = r10.rowSize;
        r1 = r11.getRowSize();
        if (r0 != r1) goto L_0x004c;
    L_0x0018:
        r0 = new com.google.zxing.common.BitArray;
        r1 = r10.width;
        r1 = r1 / 32;
        r1 = r1 + 1;
        r0.<init>(r1);
        r1 = 0;
        r2 = 0;
    L_0x0025:
        r3 = r10.height;
        if (r2 >= r3) goto L_0x004b;
    L_0x0029:
        r3 = r10.rowSize;
        r3 = r3 * r2;
        r4 = r11.getRow(r2, r0);
        r4 = r4.getBitArray();
        r5 = 0;
    L_0x0036:
        r6 = r10.rowSize;
        if (r5 >= r6) goto L_0x0048;
    L_0x003a:
        r6 = r10.bits;
        r7 = r3 + r5;
        r8 = r6[r7];
        r9 = r4[r5];
        r8 = r8 ^ r9;
        r6[r7] = r8;
        r5 = r5 + 1;
        goto L_0x0036;
    L_0x0048:
        r2 = r2 + 1;
        goto L_0x0025;
    L_0x004b:
        return;
    L_0x004c:
        r11 = new java.lang.IllegalArgumentException;
        r0 = "input matrix dimensions do not match";
        r11.<init>(r0);
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.BitMatrix.xor(com.google.zxing.common.BitMatrix):void");
    }

    public BitMatrix(int i) {
        this(i, i);
    }

    public BitMatrix(int i, int i2) {
        if (i < 1 || i2 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.width = i;
        this.height = i2;
        this.rowSize = (i + 31) / 32;
        this.bits = new int[(this.rowSize * i2)];
    }

    private BitMatrix(int i, int i2, int i3, int[] iArr) {
        this.width = i;
        this.height = i2;
        this.rowSize = i3;
        this.bits = iArr;
    }

    public boolean get(int i, int i2) {
        return ((this.bits[(i2 * this.rowSize) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    public void set(int i, int i2) {
        i2 = (i2 * this.rowSize) + (i / 32);
        int[] iArr = this.bits;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    public void unset(int i, int i2) {
        i2 = (i2 * this.rowSize) + (i / 32);
        int[] iArr = this.bits;
        iArr[i2] = ((1 << (i & 31)) ^ -1) & iArr[i2];
    }

    public void flip(int i, int i2) {
        i2 = (i2 * this.rowSize) + (i / 32);
        int[] iArr = this.bits;
        iArr[i2] = (1 << (i & 31)) ^ iArr[i2];
    }

    public void clear() {
        int length = this.bits.length;
        for (int i = 0; i < length; i++) {
            this.bits[i] = 0;
        }
    }

    public BitArray getRow(int i, BitArray bitArray) {
        int i2;
        if (bitArray != null) {
            if (bitArray.getSize() >= this.width) {
                bitArray.clear();
                i *= this.rowSize;
                for (i2 = 0; i2 < this.rowSize; i2++) {
                    bitArray.setBulk(i2 * 32, this.bits[i + i2]);
                }
                return bitArray;
            }
        }
        bitArray = new BitArray(this.width);
        i *= this.rowSize;
        for (i2 = 0; i2 < this.rowSize; i2++) {
            bitArray.setBulk(i2 * 32, this.bits[i + i2]);
        }
        return bitArray;
    }

    public void setRow(int i, BitArray bitArray) {
        bitArray = bitArray.getBitArray();
        Object obj = this.bits;
        int i2 = this.rowSize;
        System.arraycopy(bitArray, 0, obj, i * i2, i2);
    }

    public void rotate180() {
        int width = getWidth();
        int height = getHeight();
        BitArray bitArray = new BitArray(width);
        BitArray bitArray2 = new BitArray(width);
        for (width = 0; width < (height + 1) / 2; width++) {
            bitArray = getRow(width, bitArray);
            int i = (height - 1) - width;
            bitArray2 = getRow(i, bitArray2);
            bitArray.reverse();
            bitArray2.reverse();
            setRow(width, bitArray2);
            setRow(i, bitArray);
        }
    }

    public int[] getEnclosingRectangle() {
        int i = this.width;
        int i2 = -1;
        int i3 = this.height;
        int i4 = -1;
        int i5 = i;
        i = 0;
        while (i < this.height) {
            int i6 = i4;
            i4 = i2;
            i2 = i5;
            i5 = 0;
            while (true) {
                int i7 = this.rowSize;
                if (i5 >= i7) {
                    break;
                }
                i7 = this.bits[(i7 * i) + i5];
                if (i7 != 0) {
                    if (i < i3) {
                        i3 = i;
                    }
                    if (i > i6) {
                        i6 = i;
                    }
                    int i8 = i5 * 32;
                    int i9 = 31;
                    if (i8 < i2) {
                        int i10 = 0;
                        while ((i7 << (31 - i10)) == 0) {
                            i10++;
                        }
                        i10 += i8;
                        if (i10 < i2) {
                            i2 = i10;
                        }
                    }
                    if (i8 + 31 > i4) {
                        while ((i7 >>> i9) == 0) {
                            i9--;
                        }
                        i7 = i8 + i9;
                        if (i7 > i4) {
                            i4 = i7;
                        }
                    }
                }
                i5++;
            }
            i++;
            i5 = i2;
            i2 = i4;
            i4 = i6;
        }
        i4 -= i3;
        if (i2 - i5 >= 0) {
            if (i4 >= 0) {
                return new int[]{i5, i3, i2, i4};
            }
        }
        return null;
    }

    public int[] getTopLeftOnBit() {
        int[] iArr;
        int i = 0;
        while (true) {
            iArr = this.bits;
            if (i >= iArr.length || iArr[i] != 0) {
                iArr = this.bits;
            } else {
                i++;
            }
        }
        iArr = this.bits;
        if (i == iArr.length) {
            return null;
        }
        int i2;
        int i3 = this.rowSize;
        int i4 = i / i3;
        i3 = (i % i3) * 32;
        i = iArr[i];
        for (i2 = 0; (i << (31 - i2)) == 0; i2++) {
        }
        return new int[]{i3 + i2, i4};
    }

    public int[] getBottomRightOnBit() {
        int length = this.bits.length - 1;
        while (length >= 0 && this.bits[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i;
        int i2 = this.rowSize;
        int i3 = length / i2;
        i2 = (length % i2) * 32;
        for (i = 31; (this.bits[length] >>> i) == 0; i--) {
        }
        return new int[]{i2 + i, i3};
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof BitMatrix)) {
            return false;
        }
        BitMatrix bitMatrix = (BitMatrix) obj;
        if (this.width == bitMatrix.width && this.height == bitMatrix.height && this.rowSize == bitMatrix.rowSize && Arrays.equals(this.bits, bitMatrix.bits) != null) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        int i = this.width;
        return (((((((i * 31) + i) * 31) + this.height) * 31) + this.rowSize) * 31) + Arrays.hashCode(this.bits);
    }

    public String toString() {
        return toString("X ", "  ");
    }

    public String toString(String str, String str2) {
        return toString(str, str2, "\n");
    }

    @Deprecated
    public String toString(String str, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder(this.height * (this.width + 1));
        for (int i = 0; i < this.height; i++) {
            for (int i2 = 0; i2 < this.width; i2++) {
                stringBuilder.append(get(i2, i) ? str : str2);
            }
            stringBuilder.append(str3);
        }
        return stringBuilder.toString();
    }

    public BitMatrix clone() {
        return new BitMatrix(this.width, this.height, this.rowSize, (int[]) this.bits.clone());
    }
}
