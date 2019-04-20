package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

final class RealBufferedSource implements BufferedSource {
    public final Buffer buffer = new Buffer();
    boolean closed;
    public final Source source;

    /* renamed from: okio.RealBufferedSource$1 */
    class C04451 extends InputStream {
        C04451() {
        }

        public int read() throws IOException {
            if (RealBufferedSource.this.closed) {
                throw new IOException("closed");
            } else if (RealBufferedSource.this.buffer.size == 0 && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1;
            } else {
                return RealBufferedSource.this.buffer.readByte() & 255;
            }
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (RealBufferedSource.this.closed) {
                throw new IOException("closed");
            }
            Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
            if (RealBufferedSource.this.buffer.size == 0 && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1;
            }
            return RealBufferedSource.this.buffer.read(bArr, i, i2);
        }

        public int available() throws IOException {
            if (!RealBufferedSource.this.closed) {
                return (int) Math.min(RealBufferedSource.this.buffer.size, 2147483647L);
            }
            throw new IOException("closed");
        }

        public void close() throws IOException {
            RealBufferedSource.this.close();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(RealBufferedSource.this);
            stringBuilder.append(".inputStream()");
            return stringBuilder.toString();
        }
    }

    public long indexOf(byte r10, long r11, long r13) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x0065 in {10, 15, 16, 17, 18, 20, 22} preds:[]
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
        r9 = this;
        r0 = r9.closed;
        if (r0 != 0) goto L_0x005d;
    L_0x0004:
        r0 = 0;
        r2 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1));
        if (r2 < 0) goto L_0x0040;
    L_0x000a:
        r0 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1));
        if (r0 < 0) goto L_0x0040;
    L_0x000e:
        r7 = -1;
        r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1));
        if (r0 >= 0) goto L_0x003f;
    L_0x0014:
        r1 = r9.buffer;
        r2 = r10;
        r3 = r11;
        r5 = r13;
        r0 = r1.indexOf(r2, r3, r5);
        r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1));
        if (r2 == 0) goto L_0x0022;
    L_0x0021:
        return r0;
    L_0x0022:
        r0 = r9.buffer;
        r0 = r0.size;
        r2 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1));
        if (r2 >= 0) goto L_0x003e;
    L_0x002a:
        r2 = r9.source;
        r3 = r9.buffer;
        r4 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r2 = r2.read(r3, r4);
        r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1));
        if (r4 != 0) goto L_0x0039;
    L_0x0038:
        goto L_0x003e;
    L_0x0039:
        r11 = java.lang.Math.max(r11, r0);
        goto L_0x000e;
    L_0x003e:
        return r7;
    L_0x003f:
        return r7;
    L_0x0040:
        r10 = new java.lang.IllegalArgumentException;
        r0 = 2;
        r0 = new java.lang.Object[r0];
        r1 = 0;
        r11 = java.lang.Long.valueOf(r11);
        r0[r1] = r11;
        r11 = 1;
        r12 = java.lang.Long.valueOf(r13);
        r0[r11] = r12;
        r11 = "fromIndex=%s toIndex=%s";
        r11 = java.lang.String.format(r11, r0);
        r10.<init>(r11);
        throw r10;
    L_0x005d:
        r10 = new java.lang.IllegalStateException;
        r11 = "closed";
        r10.<init>(r11);
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.indexOf(byte, long, long):long");
    }

    public long indexOf(okio.ByteString r9, long r10) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:11:0x003a in {4, 7, 8, 10} preds:[]
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
        r8 = this;
        r0 = r8.closed;
        if (r0 != 0) goto L_0x0032;
    L_0x0004:
        r0 = r8.buffer;
        r0 = r0.indexOf(r9, r10);
        r2 = -1;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 == 0) goto L_0x0011;
    L_0x0010:
        return r0;
    L_0x0011:
        r0 = r8.buffer;
        r0 = r0.size;
        r4 = r8.source;
        r5 = r8.buffer;
        r6 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r4 = r4.read(r5, r6);
        r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r6 != 0) goto L_0x0024;
    L_0x0023:
        return r2;
    L_0x0024:
        r2 = r9.size();
        r2 = (long) r2;
        r0 = r0 - r2;
        r2 = 1;
        r0 = r0 + r2;
        r10 = java.lang.Math.max(r10, r0);
        goto L_0x0004;
    L_0x0032:
        r9 = new java.lang.IllegalStateException;
        r10 = "closed";
        r9.<init>(r10);
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.indexOf(okio.ByteString, long):long");
    }

    public long indexOfElement(okio.ByteString r9, long r10) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:11:0x0031 in {4, 7, 8, 10} preds:[]
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
        r8 = this;
        r0 = r8.closed;
        if (r0 != 0) goto L_0x0029;
    L_0x0004:
        r0 = r8.buffer;
        r0 = r0.indexOfElement(r9, r10);
        r2 = -1;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 == 0) goto L_0x0011;
    L_0x0010:
        return r0;
    L_0x0011:
        r0 = r8.buffer;
        r0 = r0.size;
        r4 = r8.source;
        r5 = r8.buffer;
        r6 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r4 = r4.read(r5, r6);
        r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r6 != 0) goto L_0x0024;
    L_0x0023:
        return r2;
    L_0x0024:
        r10 = java.lang.Math.max(r10, r0);
        goto L_0x0004;
    L_0x0029:
        r9 = new java.lang.IllegalStateException;
        r10 = "closed";
        r9.<init>(r10);
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.indexOfElement(okio.ByteString, long):long");
    }

    public boolean rangeEquals(long r8, okio.ByteString r10, int r11, int r12) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x0043 in {8, 13, 16, 17, 19, 20, 22} preds:[]
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
        r7 = this;
        r0 = r7.closed;
        if (r0 != 0) goto L_0x003b;
    L_0x0004:
        r0 = 0;
        r2 = 0;
        r3 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1));
        if (r3 < 0) goto L_0x003a;
    L_0x000b:
        if (r11 < 0) goto L_0x003a;
    L_0x000d:
        if (r12 < 0) goto L_0x003a;
    L_0x000f:
        r0 = r10.size();
        r0 = r0 - r11;
        if (r0 >= r12) goto L_0x0017;
    L_0x0016:
        goto L_0x003a;
    L_0x0017:
        r0 = 0;
    L_0x0018:
        if (r0 >= r12) goto L_0x0038;
    L_0x001a:
        r3 = (long) r0;
        r3 = r3 + r8;
        r5 = 1;
        r5 = r5 + r3;
        r1 = r7.request(r5);
        if (r1 != 0) goto L_0x0026;
    L_0x0025:
        return r2;
    L_0x0026:
        r1 = r7.buffer;
        r1 = r1.getByte(r3);
        r3 = r11 + r0;
        r3 = r10.getByte(r3);
        if (r1 == r3) goto L_0x0035;
    L_0x0034:
        return r2;
    L_0x0035:
        r0 = r0 + 1;
        goto L_0x0018;
    L_0x0038:
        r8 = 1;
        return r8;
    L_0x003a:
        return r2;
    L_0x003b:
        r8 = new java.lang.IllegalStateException;
        r9 = "closed";
        r8.<init>(r9);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.rangeEquals(long, okio.ByteString, int, int):boolean");
    }

    public long readAll(okio.Sink r10) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:13:0x0049 in {6, 9, 10, 12} preds:[]
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
        r9 = this;
        if (r10 == 0) goto L_0x0041;
    L_0x0002:
        r0 = 0;
        r2 = r0;
    L_0x0005:
        r4 = r9.source;
        r5 = r9.buffer;
        r6 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r4 = r4.read(r5, r6);
        r6 = -1;
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x0026;
    L_0x0015:
        r4 = r9.buffer;
        r4 = r4.completeSegmentByteCount();
        r6 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r6 <= 0) goto L_0x0005;
    L_0x001f:
        r2 = r2 + r4;
        r6 = r9.buffer;
        r10.write(r6, r4);
        goto L_0x0005;
    L_0x0026:
        r4 = r9.buffer;
        r4 = r4.size();
        r6 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r6 <= 0) goto L_0x0040;
    L_0x0030:
        r0 = r9.buffer;
        r0 = r0.size();
        r2 = r2 + r0;
        r0 = r9.buffer;
        r4 = r0.size();
        r10.write(r0, r4);
    L_0x0040:
        return r2;
    L_0x0041:
        r10 = new java.lang.IllegalArgumentException;
        r0 = "sink == null";
        r10.<init>(r0);
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.readAll(okio.Sink):long");
    }

    public void readFully(byte[] r8) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:14:0x002c in {3, 10, 12, 13} preds:[]
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
        r7 = this;
        r0 = r8.length;	 Catch:{ EOFException -> 0x000b }
        r0 = (long) r0;	 Catch:{ EOFException -> 0x000b }
        r7.require(r0);	 Catch:{ EOFException -> 0x000b }
        r0 = r7.buffer;
        r0.readFully(r8);
        return;
    L_0x000b:
        r0 = move-exception;
        r1 = 0;
    L_0x000d:
        r2 = r7.buffer;
        r2 = r2.size;
        r4 = 0;
        r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r6 <= 0) goto L_0x002b;
    L_0x0017:
        r2 = r7.buffer;
        r3 = r2.size;
        r4 = (int) r3;
        r2 = r2.read(r8, r1, r4);
        r3 = -1;
        if (r2 == r3) goto L_0x0025;
    L_0x0023:
        r1 = r1 + r2;
        goto L_0x000d;
    L_0x0025:
        r8 = new java.lang.AssertionError;
        r8.<init>();
        throw r8;
    L_0x002b:
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.readFully(byte[]):void");
    }

    public boolean request(long r6) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:0x0045 in {9, 11, 13, 15} preds:[]
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
        r5 = this;
        r0 = 0;
        r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
        if (r2 < 0) goto L_0x002e;
    L_0x0006:
        r0 = r5.closed;
        if (r0 != 0) goto L_0x0026;
    L_0x000a:
        r0 = r5.buffer;
        r0 = r0.size;
        r2 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r2 >= 0) goto L_0x0024;
    L_0x0012:
        r0 = r5.source;
        r1 = r5.buffer;
        r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r0 = r0.read(r1, r2);
        r2 = -1;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 != 0) goto L_0x000a;
    L_0x0022:
        r6 = 0;
        return r6;
    L_0x0024:
        r6 = 1;
        return r6;
    L_0x0026:
        r6 = new java.lang.IllegalStateException;
        r7 = "closed";
        r6.<init>(r7);
        throw r6;
    L_0x002e:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "byteCount < 0: ";
        r1.append(r2);
        r1.append(r6);
        r6 = r1.toString();
        r0.<init>(r6);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.request(long):boolean");
    }

    public int select(okio.Options r8) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:14:0x003e in {4, 8, 11, 13} preds:[]
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
        r7 = this;
        r0 = r7.closed;
        if (r0 != 0) goto L_0x0036;
    L_0x0004:
        r0 = r7.buffer;
        r0 = r0.selectPrefix(r8);
        r1 = -1;
        if (r0 != r1) goto L_0x000e;
    L_0x000d:
        return r1;
    L_0x000e:
        r2 = r8.byteStrings;
        r2 = r2[r0];
        r2 = r2.size();
        r2 = (long) r2;
        r4 = r7.buffer;
        r4 = r4.size;
        r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r6 > 0) goto L_0x0025;
    L_0x001f:
        r8 = r7.buffer;
        r8.skip(r2);
        return r0;
    L_0x0025:
        r0 = r7.source;
        r2 = r7.buffer;
        r3 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r2 = r0.read(r2, r3);
        r4 = -1;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x0004;
    L_0x0035:
        return r1;
    L_0x0036:
        r8 = new java.lang.IllegalStateException;
        r0 = "closed";
        r8.<init>(r0);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.select(okio.Options):int");
    }

    public void skip(long r6) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:15:0x0043 in {8, 10, 11, 12, 14} preds:[]
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
        r5 = this;
        r0 = r5.closed;
        if (r0 != 0) goto L_0x003b;
    L_0x0004:
        r0 = 0;
        r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x003a;
    L_0x000a:
        r2 = r5.buffer;
        r2 = r2.size;
        r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r4 != 0) goto L_0x0029;
    L_0x0012:
        r0 = r5.source;
        r1 = r5.buffer;
        r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r0 = r0.read(r1, r2);
        r2 = -1;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 == 0) goto L_0x0023;
    L_0x0022:
        goto L_0x0029;
    L_0x0023:
        r6 = new java.io.EOFException;
        r6.<init>();
        throw r6;
    L_0x0029:
        r0 = r5.buffer;
        r0 = r0.size();
        r0 = java.lang.Math.min(r6, r0);
        r2 = r5.buffer;
        r2.skip(r0);
        r6 = r6 - r0;
        goto L_0x0004;
    L_0x003a:
        return;
    L_0x003b:
        r6 = new java.lang.IllegalStateException;
        r7 = "closed";
        r6.<init>(r7);
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.skip(long):void");
    }

    RealBufferedSource(Source source) {
        if (source != null) {
            this.source = source;
            return;
        }
        throw new NullPointerException("source == null");
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public long read(Buffer buffer, long j) throws IOException {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("byteCount < 0: ");
            stringBuilder.append(j);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (this.buffer.size == 0 && this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        } else {
            return this.buffer.read(buffer, Math.min(j, this.buffer.size));
        }
    }

    public boolean exhausted() throws IOException {
        if (!this.closed) {
            return this.buffer.exhausted() && this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    public void require(long j) throws IOException {
        if (request(j) == null) {
            throw new EOFException();
        }
    }

    public byte readByte() throws IOException {
        require(1);
        return this.buffer.readByte();
    }

    public ByteString readByteString() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteString();
    }

    public ByteString readByteString(long j) throws IOException {
        require(j);
        return this.buffer.readByteString(j);
    }

    public byte[] readByteArray() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteArray();
    }

    public byte[] readByteArray(long j) throws IOException {
        require(j);
        return this.buffer.readByteArray(j);
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = (long) i2;
        Util.checkOffsetAndCount((long) bArr.length, (long) i, j);
        if (this.buffer.size == 0 && this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.buffer.read(bArr, i, (int) Math.min(j, this.buffer.size));
    }

    public void readFully(Buffer buffer, long j) throws IOException {
        try {
            require(j);
            this.buffer.readFully(buffer, j);
        } catch (long j2) {
            buffer.writeAll(this.buffer);
            throw j2;
        }
    }

    public String readUtf8() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readUtf8();
    }

    public String readUtf8(long j) throws IOException {
        require(j);
        return this.buffer.readUtf8(j);
    }

    public String readString(Charset charset) throws IOException {
        if (charset != null) {
            this.buffer.writeAll(this.source);
            return this.buffer.readString(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    public String readString(long j, Charset charset) throws IOException {
        require(j);
        if (charset != null) {
            return this.buffer.readString(j, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Nullable
    public String readUtf8Line() throws IOException {
        long indexOf = indexOf((byte) 10);
        if (indexOf != -1) {
            return this.buffer.readUtf8Line(indexOf);
        }
        return this.buffer.size != 0 ? readUtf8(this.buffer.size) : null;
    }

    public String readUtf8LineStrict() throws IOException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    public String readUtf8LineStrict(long j) throws IOException {
        if (j >= 0) {
            long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
            long indexOf = indexOf((byte) 10, 0, j2);
            if (indexOf != -1) {
                return this.buffer.readUtf8Line(indexOf);
            }
            if (j2 < Long.MAX_VALUE && request(j2) && this.buffer.getByte(j2 - 1) == (byte) 13 && request(1 + j2) && this.buffer.getByte(j2) == (byte) 10) {
                return this.buffer.readUtf8Line(j2);
            }
            Buffer buffer = new Buffer();
            Buffer buffer2 = this.buffer;
            buffer2.copyTo(buffer, 0, Math.min(32, buffer2.size()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\\n not found: limit=");
            stringBuilder.append(Math.min(this.buffer.size(), j));
            stringBuilder.append(" content=");
            stringBuilder.append(buffer.readByteString().hex());
            stringBuilder.append(8230);
            throw new EOFException(stringBuilder.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("limit < 0: ");
        stringBuilder.append(j);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public int readUtf8CodePoint() throws IOException {
        require(1);
        byte b = this.buffer.getByte(0);
        if ((b & 224) == 192) {
            require(2);
        } else if ((b & 240) == 224) {
            require(3);
        } else if ((b & 248) == 240) {
            require(4);
        }
        return this.buffer.readUtf8CodePoint();
    }

    public short readShort() throws IOException {
        require(2);
        return this.buffer.readShort();
    }

    public short readShortLe() throws IOException {
        require(2);
        return this.buffer.readShortLe();
    }

    public int readInt() throws IOException {
        require(4);
        return this.buffer.readInt();
    }

    public int readIntLe() throws IOException {
        require(4);
        return this.buffer.readIntLe();
    }

    public long readLong() throws IOException {
        require(8);
        return this.buffer.readLong();
    }

    public long readLongLe() throws IOException {
        require(8);
        return this.buffer.readLongLe();
    }

    public long readDecimalLong() throws IOException {
        require(1);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!request((long) i2)) {
                break;
            }
            byte b = this.buffer.getByte((long) i);
            if (b < (byte) 48 || b > (byte) 57) {
                if (i != 0) {
                    break;
                } else if (b != (byte) 45) {
                    break;
                }
            }
            i = i2;
        }
        if (i != 0) {
            return this.buffer.readDecimalLong();
        }
        throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", new Object[]{Byte.valueOf(b)}));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readHexadecimalUnsignedLong() throws java.io.IOException {
        /*
        r6 = this;
        r0 = 1;
        r6.require(r0);
        r0 = 0;
        r1 = 0;
    L_0x0007:
        r2 = r1 + 1;
        r3 = (long) r2;
        r3 = r6.request(r3);
        if (r3 == 0) goto L_0x004a;
    L_0x0010:
        r3 = r6.buffer;
        r4 = (long) r1;
        r3 = r3.getByte(r4);
        r4 = 48;
        if (r3 < r4) goto L_0x001f;
    L_0x001b:
        r4 = 57;
        if (r3 <= r4) goto L_0x0030;
    L_0x001f:
        r4 = 97;
        if (r3 < r4) goto L_0x0027;
    L_0x0023:
        r4 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r3 <= r4) goto L_0x0030;
    L_0x0027:
        r4 = 65;
        if (r3 < r4) goto L_0x0032;
    L_0x002b:
        r4 = 70;
        if (r3 <= r4) goto L_0x0030;
    L_0x002f:
        goto L_0x0032;
    L_0x0030:
        r1 = r2;
        goto L_0x0007;
    L_0x0032:
        if (r1 == 0) goto L_0x0035;
    L_0x0034:
        goto L_0x004a;
    L_0x0035:
        r1 = new java.lang.NumberFormatException;
        r2 = 1;
        r2 = new java.lang.Object[r2];
        r3 = java.lang.Byte.valueOf(r3);
        r2[r0] = r3;
        r0 = "Expected leading [0-9a-fA-F] character but was %#x";
        r0 = java.lang.String.format(r0, r2);
        r1.<init>(r0);
        throw r1;
    L_0x004a:
        r0 = r6.buffer;
        r0 = r0.readHexadecimalUnsignedLong();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.readHexadecimalUnsignedLong():long");
    }

    public long indexOf(byte b) throws IOException {
        return indexOf(b, 0, Long.MAX_VALUE);
    }

    public long indexOf(byte b, long j) throws IOException {
        return indexOf(b, j, Long.MAX_VALUE);
    }

    public long indexOf(ByteString byteString) throws IOException {
        return indexOf(byteString, 0);
    }

    public long indexOfElement(ByteString byteString) throws IOException {
        return indexOfElement(byteString, 0);
    }

    public boolean rangeEquals(long j, ByteString byteString) throws IOException {
        return rangeEquals(j, byteString, 0, byteString.size());
    }

    public InputStream inputStream() {
        return new C04451();
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.source.close();
            this.buffer.clear();
        }
    }

    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("buffer(");
        stringBuilder.append(this.source);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
