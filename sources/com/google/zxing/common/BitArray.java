package com.google.zxing.common;

import java.util.Arrays;

public final class BitArray implements Cloneable {
    private int[] bits;
    private int size;

    public void appendBits(int r3, int r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:13:0x0027 in {7, 8, 9, 10, 12} preds:[]
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
        r2 = this;
        if (r4 < 0) goto L_0x001f;
    L_0x0002:
        r0 = 32;
        if (r4 > r0) goto L_0x001f;
    L_0x0006:
        r0 = r2.size;
        r0 = r0 + r4;
        r2.ensureCapacity(r0);
    L_0x000c:
        if (r4 <= 0) goto L_0x001e;
    L_0x000e:
        r0 = r4 + -1;
        r0 = r3 >> r0;
        r1 = 1;
        r0 = r0 & r1;
        if (r0 != r1) goto L_0x0017;
    L_0x0016:
        goto L_0x0018;
    L_0x0017:
        r1 = 0;
    L_0x0018:
        r2.appendBit(r1);
        r4 = r4 + -1;
        goto L_0x000c;
    L_0x001e:
        return;
    L_0x001f:
        r3 = new java.lang.IllegalArgumentException;
        r4 = "Num bits must be between 0 and 32";
        r3.<init>(r4);
        throw r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.BitArray.appendBits(int, int):void");
    }

    public boolean isRange(int r11, int r12, boolean r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:29:0x0044 in {3, 8, 9, 11, 12, 15, 18, 21, 22, 24, 25, 26, 28} preds:[]
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
        if (r12 < r11) goto L_0x003e;
    L_0x0002:
        r0 = 1;
        if (r12 != r11) goto L_0x0006;
    L_0x0005:
        return r0;
    L_0x0006:
        r1 = -1;
        r12 = r12 + r1;
        r2 = r11 / 32;
        r3 = r12 / 32;
        r4 = r2;
    L_0x000d:
        if (r4 > r3) goto L_0x003d;
    L_0x000f:
        r5 = 0;
        r6 = 31;
        if (r4 <= r2) goto L_0x0016;
    L_0x0014:
        r7 = 0;
        goto L_0x0018;
    L_0x0016:
        r7 = r11 & 31;
    L_0x0018:
        if (r4 >= r3) goto L_0x001d;
    L_0x001a:
        r8 = 31;
        goto L_0x001f;
    L_0x001d:
        r8 = r12 & 31;
    L_0x001f:
        if (r7 != 0) goto L_0x0025;
    L_0x0021:
        if (r8 != r6) goto L_0x0025;
    L_0x0023:
        r6 = -1;
        goto L_0x002e;
    L_0x0025:
        r6 = 0;
    L_0x0026:
        if (r7 > r8) goto L_0x002e;
    L_0x0028:
        r9 = r0 << r7;
        r6 = r6 | r9;
        r7 = r7 + 1;
        goto L_0x0026;
    L_0x002e:
        r7 = r10.bits;
        r7 = r7[r4];
        r7 = r7 & r6;
        if (r13 == 0) goto L_0x0036;
    L_0x0035:
        goto L_0x0037;
    L_0x0036:
        r6 = 0;
    L_0x0037:
        if (r7 == r6) goto L_0x003a;
    L_0x0039:
        return r5;
    L_0x003a:
        r4 = r4 + 1;
        goto L_0x000d;
    L_0x003d:
        return r0;
    L_0x003e:
        r11 = new java.lang.IllegalArgumentException;
        r11.<init>();
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.BitArray.isRange(int, int, boolean):boolean");
    }

    public void setRange(int r9, int r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:21:0x003d in {2, 7, 8, 10, 11, 14, 16, 17, 18, 20} preds:[]
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
        r8 = this;
        if (r10 < r9) goto L_0x0037;
    L_0x0002:
        if (r10 != r9) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = -1;
        r10 = r10 + r0;
        r1 = r9 / 32;
        r2 = r10 / 32;
        r3 = r1;
    L_0x000c:
        if (r3 > r2) goto L_0x0036;
    L_0x000e:
        r4 = 0;
        r5 = 31;
        if (r3 <= r1) goto L_0x0015;
    L_0x0013:
        r6 = 0;
        goto L_0x0017;
    L_0x0015:
        r6 = r9 & 31;
    L_0x0017:
        if (r3 >= r2) goto L_0x001c;
    L_0x0019:
        r7 = 31;
        goto L_0x001e;
    L_0x001c:
        r7 = r10 & 31;
    L_0x001e:
        if (r6 != 0) goto L_0x0024;
    L_0x0020:
        if (r7 != r5) goto L_0x0024;
    L_0x0022:
        r4 = -1;
        goto L_0x002c;
    L_0x0024:
        if (r6 > r7) goto L_0x002c;
    L_0x0026:
        r5 = 1;
        r5 = r5 << r6;
        r4 = r4 | r5;
        r6 = r6 + 1;
        goto L_0x0024;
    L_0x002c:
        r5 = r8.bits;
        r6 = r5[r3];
        r4 = r4 | r6;
        r5[r3] = r4;
        r3 = r3 + 1;
        goto L_0x000c;
    L_0x0036:
        return;
    L_0x0037:
        r9 = new java.lang.IllegalArgumentException;
        r9.<init>();
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.BitArray.setRange(int, int):void");
    }

    public void xor(com.google.zxing.common.BitArray r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:9:0x0023 in {5, 6, 8} preds:[]
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
        r4 = this;
        r0 = r4.bits;
        r0 = r0.length;
        r1 = r5.bits;
        r1 = r1.length;
        if (r0 != r1) goto L_0x001b;
    L_0x0008:
        r0 = 0;
    L_0x0009:
        r1 = r4.bits;
        r2 = r1.length;
        if (r0 >= r2) goto L_0x001a;
    L_0x000e:
        r2 = r1[r0];
        r3 = r5.bits;
        r3 = r3[r0];
        r2 = r2 ^ r3;
        r1[r0] = r2;
        r0 = r0 + 1;
        goto L_0x0009;
    L_0x001a:
        return;
    L_0x001b:
        r5 = new java.lang.IllegalArgumentException;
        r0 = "Sizes don't match";
        r5.<init>(r0);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.BitArray.xor(com.google.zxing.common.BitArray):void");
    }

    public BitArray() {
        this.size = 0;
        this.bits = new int[1];
    }

    public BitArray(int i) {
        this.size = i;
        this.bits = makeArray(i);
    }

    BitArray(int[] iArr, int i) {
        this.bits = iArr;
        this.size = i;
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeInBytes() {
        return (this.size + 7) / 8;
    }

    private void ensureCapacity(int i) {
        if (i > this.bits.length * 32) {
            i = makeArray(i);
            Object obj = this.bits;
            System.arraycopy(obj, 0, i, 0, obj.length);
            this.bits = i;
        }
    }

    public boolean get(int i) {
        return ((1 << (i & 31)) & this.bits[i / 32]) != 0;
    }

    public void set(int i) {
        int[] iArr = this.bits;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    public void flip(int i) {
        int[] iArr = this.bits;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) ^ iArr[i2];
    }

    public int getNextSet(int i) {
        int i2 = this.size;
        if (i >= i2) {
            return i2;
        }
        i2 = i / 32;
        i = (((1 << (i & 31)) - 1) ^ -1) & this.bits[i2];
        while (i == 0) {
            i2++;
            i = this.bits;
            if (i2 == i.length) {
                return this.size;
            }
            i = i[i2];
        }
        i2 = (i2 * 32) + Integer.numberOfTrailingZeros(i);
        i = this.size;
        if (i2 <= i) {
            i = i2;
        }
        return i;
    }

    public int getNextUnset(int i) {
        int i2 = this.size;
        if (i >= i2) {
            return i2;
        }
        i2 = i / 32;
        i = (((1 << (i & 31)) - 1) ^ -1) & (this.bits[i2] ^ -1);
        while (i == 0) {
            i2++;
            i = this.bits;
            if (i2 == i.length) {
                return this.size;
            }
            i = i[i2] ^ -1;
        }
        i2 = (i2 * 32) + Integer.numberOfTrailingZeros(i);
        i = this.size;
        if (i2 <= i) {
            i = i2;
        }
        return i;
    }

    public void setBulk(int i, int i2) {
        this.bits[i / 32] = i2;
    }

    public void clear() {
        int length = this.bits.length;
        for (int i = 0; i < length; i++) {
            this.bits[i] = 0;
        }
    }

    public void appendBit(boolean z) {
        ensureCapacity(this.size + 1);
        if (z) {
            z = this.bits;
            int i = this.size;
            int i2 = i / 32;
            z[i2] = (1 << (i & 31)) | z[i2];
        }
        this.size += true;
    }

    public void appendBitArray(BitArray bitArray) {
        int i = bitArray.size;
        ensureCapacity(this.size + i);
        for (int i2 = 0; i2 < i; i2++) {
            appendBit(bitArray.get(i2));
        }
    }

    public void toBytes(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        i = 0;
        while (i < i3) {
            int i5 = i4;
            int i6 = 0;
            for (i4 = 0; i4 < 8; i4++) {
                if (get(i5)) {
                    i6 |= 1 << (7 - i4);
                }
                i5++;
            }
            bArr[i2 + i] = (byte) i6;
            i++;
            i4 = i5;
        }
    }

    public int[] getBitArray() {
        return this.bits;
    }

    public void reverse() {
        int i;
        int[] iArr = new int[this.bits.length];
        int i2 = (this.size - 1) / 32;
        int i3 = i2 + 1;
        for (i = 0; i < i3; i++) {
            long j = (long) this.bits[i];
            j = ((j & 1431655765) << 1) | ((j >> 1) & 1431655765);
            j = ((j & 858993459) << 2) | ((j >> 2) & 858993459);
            j = ((j & 252645135) << 4) | ((j >> 4) & 252645135);
            j = ((j & 16711935) << 8) | ((j >> 8) & 16711935);
            iArr[i2 - i] = (int) (((j & 65535) << 16) | ((j >> 16) & 65535));
        }
        i2 = this.size;
        i = i3 * 32;
        if (i2 != i) {
            i -= i2;
            int i4 = 1;
            for (i2 = 0; i2 < 31 - i; i2++) {
                i4 = (i4 << 1) | 1;
            }
            int i5 = (iArr[0] >> i) & i4;
            for (i2 = 1; i2 < i3; i2++) {
                int i6 = iArr[i2];
                iArr[i2 - 1] = i5 | (i6 << (32 - i));
                i5 = (i6 >> i) & i4;
            }
            iArr[i3 - 1] = i5;
        }
        this.bits = iArr;
    }

    private static int[] makeArray(int i) {
        return new int[((i + 31) / 32)];
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof BitArray)) {
            return false;
        }
        BitArray bitArray = (BitArray) obj;
        if (this.size == bitArray.size && Arrays.equals(this.bits, bitArray.bits) != null) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (this.size * 31) + Arrays.hashCode(this.bits);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.size);
        for (int i = 0; i < this.size; i++) {
            if ((i & 7) == 0) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(get(i) ? 'X' : '.');
        }
        return stringBuilder.toString();
    }

    public BitArray clone() {
        return new BitArray((int[]) this.bits.clone(), this.size);
    }
}
