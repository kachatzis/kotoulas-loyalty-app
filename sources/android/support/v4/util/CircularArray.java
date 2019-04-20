package android.support.v4.util;

public final class CircularArray<E> {
    private int mCapacityBitmask;
    private E[] mElements;
    private int mHead;
    private int mTail;

    public void removeFromEnd(int r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:21:0x0042 in {1, 6, 10, 16, 17, 18, 20} preds:[]
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
        r4 = this;
        if (r5 > 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = r4.size();
        if (r5 > r0) goto L_0x003c;
    L_0x0009:
        r0 = 0;
        r1 = r4.mTail;
        if (r5 >= r1) goto L_0x0010;
    L_0x000e:
        r0 = r1 - r5;
    L_0x0010:
        r1 = r0;
    L_0x0011:
        r2 = r4.mTail;
        r3 = 0;
        if (r1 >= r2) goto L_0x001d;
    L_0x0016:
        r2 = r4.mElements;
        r2[r1] = r3;
        r1 = r1 + 1;
        goto L_0x0011;
    L_0x001d:
        r0 = r2 - r0;
        r5 = r5 - r0;
        r2 = r2 - r0;
        r4.mTail = r2;
        if (r5 <= 0) goto L_0x003b;
    L_0x0025:
        r0 = r4.mElements;
        r0 = r0.length;
        r4.mTail = r0;
        r0 = r4.mTail;
        r0 = r0 - r5;
        r5 = r0;
    L_0x002e:
        r1 = r4.mTail;
        if (r5 >= r1) goto L_0x0039;
    L_0x0032:
        r1 = r4.mElements;
        r1[r5] = r3;
        r5 = r5 + 1;
        goto L_0x002e;
    L_0x0039:
        r4.mTail = r0;
    L_0x003b:
        return;
    L_0x003c:
        r5 = new java.lang.ArrayIndexOutOfBoundsException;
        r5.<init>();
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.CircularArray.removeFromEnd(int):void");
    }

    public void removeFromStart(int r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x003f in {1, 6, 10, 15, 16, 17, 19} preds:[]
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
        r4 = this;
        if (r5 > 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = r4.size();
        if (r5 > r0) goto L_0x0039;
    L_0x0009:
        r0 = r4.mElements;
        r0 = r0.length;
        r1 = r4.mHead;
        r2 = r0 - r1;
        if (r5 >= r2) goto L_0x0014;
    L_0x0012:
        r0 = r1 + r5;
    L_0x0014:
        r1 = r4.mHead;
    L_0x0016:
        r2 = 0;
        if (r1 >= r0) goto L_0x0020;
    L_0x0019:
        r3 = r4.mElements;
        r3[r1] = r2;
        r1 = r1 + 1;
        goto L_0x0016;
    L_0x0020:
        r1 = r4.mHead;
        r0 = r0 - r1;
        r5 = r5 - r0;
        r1 = r1 + r0;
        r0 = r4.mCapacityBitmask;
        r0 = r0 & r1;
        r4.mHead = r0;
        if (r5 <= 0) goto L_0x0038;
    L_0x002c:
        r0 = 0;
    L_0x002d:
        if (r0 >= r5) goto L_0x0036;
    L_0x002f:
        r1 = r4.mElements;
        r1[r0] = r2;
        r0 = r0 + 1;
        goto L_0x002d;
    L_0x0036:
        r4.mHead = r5;
    L_0x0038:
        return;
    L_0x0039:
        r5 = new java.lang.ArrayIndexOutOfBoundsException;
        r5.<init>();
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.CircularArray.removeFromStart(int):void");
    }

    private void doubleCapacity() {
        Object obj = this.mElements;
        int length = obj.length;
        int i = this.mHead;
        int i2 = length - i;
        int i3 = length << 1;
        if (i3 >= 0) {
            Object obj2 = new Object[i3];
            System.arraycopy(obj, i, obj2, 0, i2);
            System.arraycopy(this.mElements, 0, obj2, i2, this.mHead);
            this.mElements = (Object[]) obj2;
            this.mHead = 0;
            this.mTail = length;
            this.mCapacityBitmask = i3 - 1;
            return;
        }
        throw new RuntimeException("Max array capacity exceeded");
    }

    public CircularArray() {
        this(8);
    }

    public CircularArray(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        } else if (i <= 1073741824) {
            if (Integer.bitCount(i) != 1) {
                i = Integer.highestOneBit(i - 1) << 1;
            }
            this.mCapacityBitmask = i - 1;
            this.mElements = (Object[]) new Object[i];
        } else {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
    }

    public void addFirst(E e) {
        this.mHead = (this.mHead - 1) & this.mCapacityBitmask;
        Object[] objArr = this.mElements;
        E e2 = this.mHead;
        objArr[e2] = e;
        if (e2 == this.mTail) {
            doubleCapacity();
        }
    }

    public void addLast(E e) {
        Object[] objArr = this.mElements;
        int i = this.mTail;
        objArr[i] = e;
        this.mTail = this.mCapacityBitmask & (i + 1);
        if (this.mTail == this.mHead) {
            doubleCapacity();
        }
    }

    public E popFirst() {
        int i = this.mHead;
        if (i != this.mTail) {
            Object[] objArr = this.mElements;
            E e = objArr[i];
            objArr[i] = null;
            this.mHead = (i + 1) & this.mCapacityBitmask;
            return e;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E popLast() {
        int i = this.mHead;
        int i2 = this.mTail;
        if (i != i2) {
            i = this.mCapacityBitmask & (i2 - 1);
            Object[] objArr = this.mElements;
            E e = objArr[i];
            objArr[i] = null;
            this.mTail = i;
            return e;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void clear() {
        removeFromStart(size());
    }

    public E getFirst() {
        int i = this.mHead;
        if (i != this.mTail) {
            return this.mElements[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E getLast() {
        int i = this.mHead;
        int i2 = this.mTail;
        if (i != i2) {
            return this.mElements[(i2 - 1) & this.mCapacityBitmask];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E get(int i) {
        if (i < 0 || i >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mCapacityBitmask & (this.mHead + i)];
    }

    public int size() {
        return (this.mTail - this.mHead) & this.mCapacityBitmask;
    }

    public boolean isEmpty() {
        return this.mHead == this.mTail;
    }
}
