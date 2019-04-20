package okio;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

final class SegmentedByteString extends ByteString {
    final transient int[] directory;
    final transient byte[][] segments;

    public void write(java.io.OutputStream r7) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:7:0x0027 in {3, 4, 6} preds:[]
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
        r6 = this;
        if (r7 == 0) goto L_0x001f;
    L_0x0002:
        r0 = r6.segments;
        r0 = r0.length;
        r1 = 0;
        r2 = 0;
    L_0x0007:
        if (r1 >= r0) goto L_0x001e;
    L_0x0009:
        r3 = r6.directory;
        r4 = r0 + r1;
        r4 = r3[r4];
        r3 = r3[r1];
        r5 = r6.segments;
        r5 = r5[r1];
        r2 = r3 - r2;
        r7.write(r5, r4, r2);
        r1 = r1 + 1;
        r2 = r3;
        goto L_0x0007;
    L_0x001e:
        return;
    L_0x001f:
        r7 = new java.lang.IllegalArgumentException;
        r0 = "out == null";
        r7.<init>(r0);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.SegmentedByteString.write(java.io.OutputStream):void");
    }

    SegmentedByteString(Buffer buffer, int i) {
        super(null);
        Util.checkOffsetAndCount(buffer.size, 0, (long) i);
        int i2 = 0;
        Segment segment = buffer.head;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (segment.limit != segment.pos) {
                i3 += segment.limit - segment.pos;
                i4++;
                segment = segment.next;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        this.segments = new byte[i4][];
        this.directory = new int[(i4 * 2)];
        Segment segment2 = buffer.head;
        buffer = null;
        while (i2 < i) {
            this.segments[buffer] = segment2.data;
            i2 += segment2.limit - segment2.pos;
            if (i2 > i) {
                i2 = i;
            }
            int[] iArr = this.directory;
            iArr[buffer] = i2;
            iArr[this.segments.length + buffer] = segment2.pos;
            segment2.shared = true;
            buffer++;
            segment2 = segment2.next;
        }
    }

    public String utf8() {
        return toByteString().utf8();
    }

    public String string(Charset charset) {
        return toByteString().string(charset);
    }

    public String base64() {
        return toByteString().base64();
    }

    public String hex() {
        return toByteString().hex();
    }

    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    public ByteString toAsciiUppercase() {
        return toByteString().toAsciiUppercase();
    }

    public ByteString md5() {
        return toByteString().md5();
    }

    public ByteString sha1() {
        return toByteString().sha1();
    }

    public ByteString sha256() {
        return toByteString().sha256();
    }

    public ByteString hmacSha1(ByteString byteString) {
        return toByteString().hmacSha1(byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        return toByteString().hmacSha256(byteString);
    }

    public String base64Url() {
        return toByteString().base64Url();
    }

    public ByteString substring(int i) {
        return toByteString().substring(i);
    }

    public ByteString substring(int i, int i2) {
        return toByteString().substring(i, i2);
    }

    public byte getByte(int i) {
        int i2;
        Util.checkOffsetAndCount((long) this.directory[this.segments.length - 1], (long) i, 1);
        int segment = segment(i);
        if (segment == 0) {
            i2 = 0;
        } else {
            i2 = this.directory[segment - 1];
        }
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        return bArr[segment][(i - i2) + iArr[bArr.length + segment]];
    }

    private int segment(int i) {
        i = Arrays.binarySearch(this.directory, 0, this.segments.length, i + 1);
        return i >= 0 ? i : i ^ -1;
    }

    public int size() {
        return this.directory[this.segments.length - 1];
    }

    public byte[] toByteArray() {
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        Object obj = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.directory;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            System.arraycopy(this.segments[i], i3, obj, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return obj;
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    void write(Buffer buffer) {
        int length = this.segments.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.directory;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            Segment segment = new Segment(this.segments[i], i3, (i3 + i4) - i2);
            if (buffer.head == null) {
                segment.prev = segment;
                segment.next = segment;
                buffer.head = segment;
            } else {
                buffer.head.prev.push(segment);
            }
            i++;
            i2 = i4;
        }
        buffer.size += (long) i2;
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        if (i >= 0) {
            if (i <= size() - i3) {
                int segment = segment(i);
                while (i3 > 0) {
                    int i4;
                    if (segment == 0) {
                        i4 = 0;
                    } else {
                        i4 = this.directory[segment - 1];
                    }
                    int min = Math.min(i3, ((this.directory[segment] - i4) + i4) - i);
                    int[] iArr = this.directory;
                    byte[][] bArr = this.segments;
                    if (!byteString.rangeEquals(i2, bArr[segment], (i - i4) + iArr[bArr.length + segment], min)) {
                        return false;
                    }
                    i += min;
                    i2 += min;
                    i3 -= min;
                    segment++;
                }
                return true;
            }
        }
        return false;
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        if (i >= 0 && i <= size() - i3 && i2 >= 0) {
            if (i2 <= bArr.length - i3) {
                int segment = segment(i);
                while (i3 > 0) {
                    int i4;
                    if (segment == 0) {
                        i4 = 0;
                    } else {
                        i4 = this.directory[segment - 1];
                    }
                    int min = Math.min(i3, ((this.directory[segment] - i4) + i4) - i);
                    int[] iArr = this.directory;
                    byte[][] bArr2 = this.segments;
                    if (!Util.arrayRangeEquals(bArr2[segment], (i - i4) + iArr[bArr2.length + segment], bArr, i2, min)) {
                        return false;
                    }
                    i += min;
                    i2 += min;
                    i3 -= min;
                    segment++;
                }
                return true;
            }
        }
        return false;
    }

    public int indexOf(byte[] bArr, int i) {
        return toByteString().indexOf(bArr, i);
    }

    public int lastIndexOf(byte[] bArr, int i) {
        return toByteString().lastIndexOf(bArr, i);
    }

    private ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    byte[] internalArray() {
        return toByteArray();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == size() && rangeEquals(0, byteString, 0, size()) != null) {
                return z;
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        i = this.segments.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < i) {
            byte[] bArr = this.segments[i2];
            int[] iArr = this.directory;
            int i5 = iArr[i + i2];
            int i6 = iArr[i2];
            i4 = (i6 - i4) + i5;
            while (i5 < i4) {
                i3 = (i3 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i4 = i6;
        }
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        return toByteString().toString();
    }

    private Object writeReplace() {
        return toByteString();
    }
}
