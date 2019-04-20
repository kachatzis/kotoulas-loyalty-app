package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public final class Buffer implements BufferedSource, BufferedSink, Cloneable {
    private static final byte[] DIGITS = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
    static final int REPLACEMENT_CHARACTER = 65533;
    @Nullable
    Segment head;
    long size;

    /* renamed from: okio.Buffer$1 */
    class C04421 extends OutputStream {
        public void close() {
        }

        public void flush() {
        }

        C04421() {
        }

        public void write(int i) {
            Buffer.this.writeByte((byte) i);
        }

        public void write(byte[] bArr, int i, int i2) {
            Buffer.this.write(bArr, i, i2);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Buffer.this);
            stringBuilder.append(".outputStream()");
            return stringBuilder.toString();
        }
    }

    /* renamed from: okio.Buffer$2 */
    class C04432 extends InputStream {
        public void close() {
        }

        C04432() {
        }

        public int read() {
            return Buffer.this.size > 0 ? Buffer.this.readByte() & 255 : -1;
        }

        public int read(byte[] bArr, int i, int i2) {
            return Buffer.this.read(bArr, i, i2);
        }

        public int available() {
            return (int) Math.min(Buffer.this.size, 2147483647L);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Buffer.this);
            stringBuilder.append(".inputStream()");
            return stringBuilder.toString();
        }
    }

    private okio.ByteString digest(java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:11:0x0040 in {6, 8, 10} preds:[]
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
        r5 = this;
        r6 = java.security.MessageDigest.getInstance(r6);	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r0 = r5.head;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        if (r0 == 0) goto L_0x0031;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
    L_0x0008:
        r0 = r5.head;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r0 = r0.data;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r1 = r5.head;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r1 = r1.pos;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r2 = r5.head;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r2 = r2.limit;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r3 = r5.head;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r3 = r3.pos;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r2 = r2 - r3;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r6.update(r0, r1, r2);	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r0 = r5.head;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
    L_0x001e:
        r0 = r0.next;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r1 = r5.head;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        if (r0 == r1) goto L_0x0031;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
    L_0x0024:
        r1 = r0.data;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r2 = r0.pos;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r3 = r0.limit;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r4 = r0.pos;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r3 = r3 - r4;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r6.update(r1, r2, r3);	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        goto L_0x001e;	 Catch:{ NoSuchAlgorithmException -> 0x003a }
    L_0x0031:
        r6 = r6.digest();	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        r6 = okio.ByteString.of(r6);	 Catch:{ NoSuchAlgorithmException -> 0x003a }
        return r6;
    L_0x003a:
        r6 = new java.lang.AssertionError;
        r6.<init>();
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.digest(java.lang.String):okio.ByteString");
    }

    private okio.ByteString hmac(java.lang.String r5, okio.ByteString r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:14:0x0053 in {6, 8, 11, 13} preds:[]
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
        r0 = javax.crypto.Mac.getInstance(r5);	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r1 = new javax.crypto.spec.SecretKeySpec;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r6 = r6.toByteArray();	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r1.<init>(r6, r5);	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r0.init(r1);	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r5 = r4.head;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        if (r5 == 0) goto L_0x003d;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
    L_0x0014:
        r5 = r4.head;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r5 = r5.data;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r6 = r4.head;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r6 = r6.pos;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r1 = r4.head;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r1 = r1.limit;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r2 = r4.head;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r2 = r2.pos;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r1 = r1 - r2;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r0.update(r5, r6, r1);	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r5 = r4.head;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
    L_0x002a:
        r5 = r5.next;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r6 = r4.head;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        if (r5 == r6) goto L_0x003d;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
    L_0x0030:
        r6 = r5.data;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r1 = r5.pos;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r2 = r5.limit;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r3 = r5.pos;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r2 = r2 - r3;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r0.update(r6, r1, r2);	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        goto L_0x002a;	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
    L_0x003d:
        r5 = r0.doFinal();	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        r5 = okio.ByteString.of(r5);	 Catch:{ NoSuchAlgorithmException -> 0x004d, InvalidKeyException -> 0x0046 }
        return r5;
    L_0x0046:
        r5 = move-exception;
        r6 = new java.lang.IllegalArgumentException;
        r6.<init>(r5);
        throw r6;
    L_0x004d:
        r5 = new java.lang.AssertionError;
        r5.<init>();
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.hmac(java.lang.String, okio.ByteString):okio.ByteString");
    }

    private void readFrom(java.io.InputStream r5, long r6, boolean r8) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:15:0x0044 in {4, 5, 9, 11, 12, 14} preds:[]
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
        if (r5 == 0) goto L_0x003c;
    L_0x0002:
        r0 = 0;
        r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
        if (r2 > 0) goto L_0x000c;
    L_0x0008:
        if (r8 == 0) goto L_0x000b;
    L_0x000a:
        goto L_0x000c;
    L_0x000b:
        return;
    L_0x000c:
        r0 = 1;
        r0 = r4.writableSegment(r0);
        r1 = r0.limit;
        r1 = 8192 - r1;
        r1 = (long) r1;
        r1 = java.lang.Math.min(r6, r1);
        r2 = (int) r1;
        r1 = r0.data;
        r3 = r0.limit;
        r1 = r5.read(r1, r3, r2);
        r2 = -1;
        if (r1 != r2) goto L_0x002f;
    L_0x0026:
        if (r8 == 0) goto L_0x0029;
    L_0x0028:
        return;
    L_0x0029:
        r5 = new java.io.EOFException;
        r5.<init>();
        throw r5;
    L_0x002f:
        r2 = r0.limit;
        r2 = r2 + r1;
        r0.limit = r2;
        r2 = r4.size;
        r0 = (long) r1;
        r2 = r2 + r0;
        r4.size = r2;
        r6 = r6 - r0;
        goto L_0x0002;
    L_0x003c:
        r5 = new java.lang.IllegalArgumentException;
        r6 = "in == null";
        r5.<init>(r6);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readFrom(java.io.InputStream, long, boolean):void");
    }

    public Buffer buffer() {
        return this;
    }

    public void close() {
    }

    public okio.Buffer copyTo(java.io.OutputStream r7, long r8, long r10) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:14:0x004c in {3, 7, 10, 11, 13} preds:[]
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
        r6 = this;
        if (r7 == 0) goto L_0x0044;
    L_0x0002:
        r0 = r6.size;
        r2 = r8;
        r4 = r10;
        okio.Util.checkOffsetAndCount(r0, r2, r4);
        r0 = 0;
        r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1));
        if (r2 != 0) goto L_0x0010;
    L_0x000f:
        return r6;
    L_0x0010:
        r2 = r6.head;
    L_0x0012:
        r3 = r2.limit;
        r4 = r2.pos;
        r3 = r3 - r4;
        r3 = (long) r3;
        r5 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1));
        if (r5 < 0) goto L_0x0026;
    L_0x001c:
        r3 = r2.limit;
        r4 = r2.pos;
        r3 = r3 - r4;
        r3 = (long) r3;
        r8 = r8 - r3;
        r2 = r2.next;
        goto L_0x0012;
    L_0x0026:
        r3 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1));
        if (r3 <= 0) goto L_0x0043;
    L_0x002a:
        r3 = r2.pos;
        r3 = (long) r3;
        r3 = r3 + r8;
        r8 = (int) r3;
        r9 = r2.limit;
        r9 = r9 - r8;
        r3 = (long) r9;
        r3 = java.lang.Math.min(r3, r10);
        r9 = (int) r3;
        r3 = r2.data;
        r7.write(r3, r8, r9);
        r8 = (long) r9;
        r10 = r10 - r8;
        r2 = r2.next;
        r8 = r0;
        goto L_0x0026;
    L_0x0043:
        return r6;
    L_0x0044:
        r7 = new java.lang.IllegalArgumentException;
        r8 = "out == null";
        r7.<init>(r8);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.copyTo(java.io.OutputStream, long, long):okio.Buffer");
    }

    public okio.Buffer copyTo(okio.Buffer r7, long r8, long r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:18:0x006b in {3, 7, 12, 13, 14, 15, 17} preds:[]
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
        r6 = this;
        if (r7 == 0) goto L_0x0063;
    L_0x0002:
        r0 = r6.size;
        r2 = r8;
        r4 = r10;
        okio.Util.checkOffsetAndCount(r0, r2, r4);
        r0 = 0;
        r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1));
        if (r2 != 0) goto L_0x0010;
    L_0x000f:
        return r6;
    L_0x0010:
        r2 = r7.size;
        r2 = r2 + r10;
        r7.size = r2;
        r2 = r6.head;
    L_0x0017:
        r3 = r2.limit;
        r4 = r2.pos;
        r3 = r3 - r4;
        r3 = (long) r3;
        r5 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1));
        if (r5 < 0) goto L_0x002b;
    L_0x0021:
        r3 = r2.limit;
        r4 = r2.pos;
        r3 = r3 - r4;
        r3 = (long) r3;
        r8 = r8 - r3;
        r2 = r2.next;
        goto L_0x0017;
    L_0x002b:
        r3 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1));
        if (r3 <= 0) goto L_0x0062;
    L_0x002f:
        r3 = new okio.Segment;
        r3.<init>(r2);
        r4 = r3.pos;
        r4 = (long) r4;
        r4 = r4 + r8;
        r8 = (int) r4;
        r3.pos = r8;
        r8 = r3.pos;
        r9 = (int) r10;
        r8 = r8 + r9;
        r9 = r3.limit;
        r8 = java.lang.Math.min(r8, r9);
        r3.limit = r8;
        r8 = r7.head;
        if (r8 != 0) goto L_0x0052;
    L_0x004b:
        r3.prev = r3;
        r3.next = r3;
        r7.head = r3;
        goto L_0x0057;
    L_0x0052:
        r8 = r8.prev;
        r8.push(r3);
    L_0x0057:
        r8 = r3.limit;
        r9 = r3.pos;
        r8 = r8 - r9;
        r8 = (long) r8;
        r10 = r10 - r8;
        r2 = r2.next;
        r8 = r0;
        goto L_0x002b;
    L_0x0062:
        return r6;
    L_0x0063:
        r7 = new java.lang.IllegalArgumentException;
        r8 = "out == null";
        r7.<init>(r8);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.copyTo(okio.Buffer, long, long):okio.Buffer");
    }

    public BufferedSink emit() {
        return this;
    }

    public Buffer emitCompleteSegments() {
        return this;
    }

    public void flush() {
    }

    public long indexOf(byte r16, long r17, long r19) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:38:0x00a9 in {6, 7, 10, 13, 18, 19, 23, 24, 32, 33, 34, 35, 37} preds:[]
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
        r15 = this;
        r0 = r15;
        r1 = 0;
        r3 = (r17 > r1 ? 1 : (r17 == r1 ? 0 : -1));
        if (r3 < 0) goto L_0x0083;
    L_0x0007:
        r3 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1));
        if (r3 < 0) goto L_0x0083;
    L_0x000b:
        r3 = r0.size;
        r5 = (r19 > r3 ? 1 : (r19 == r3 ? 0 : -1));
        if (r5 <= 0) goto L_0x0012;
    L_0x0011:
        goto L_0x0014;
    L_0x0012:
        r3 = r19;
    L_0x0014:
        r5 = -1;
        r7 = (r17 > r3 ? 1 : (r17 == r3 ? 0 : -1));
        if (r7 != 0) goto L_0x001b;
    L_0x001a:
        return r5;
    L_0x001b:
        r7 = r0.head;
        if (r7 != 0) goto L_0x0020;
    L_0x001f:
        return r5;
    L_0x0020:
        r8 = r0.size;
        r10 = r8 - r17;
        r12 = (r10 > r17 ? 1 : (r10 == r17 ? 0 : -1));
        if (r12 >= 0) goto L_0x0039;
    L_0x0028:
        r1 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1));
        if (r1 <= 0) goto L_0x0036;
    L_0x002c:
        r7 = r7.prev;
        r1 = r7.limit;
        r2 = r7.pos;
        r1 = r1 - r2;
        r1 = (long) r1;
        r8 = r8 - r1;
        goto L_0x0028;
    L_0x0036:
        r1 = r17;
        goto L_0x004b;
    L_0x0039:
        r8 = r1;
    L_0x003a:
        r1 = r7.limit;
        r2 = r7.pos;
        r1 = r1 - r2;
        r1 = (long) r1;
        r1 = r1 + r8;
        r10 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1));
        if (r10 >= 0) goto L_0x0049;
    L_0x0045:
        r7 = r7.next;
        r8 = r1;
        goto L_0x003a;
    L_0x0049:
        r1 = r17;
    L_0x004b:
        r10 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1));
        if (r10 >= 0) goto L_0x0082;
    L_0x004f:
        r10 = r7.data;
        r11 = r7.limit;
        r11 = (long) r11;
        r13 = r7.pos;
        r13 = (long) r13;
        r13 = r13 + r3;
        r13 = r13 - r8;
        r11 = java.lang.Math.min(r11, r13);
        r12 = (int) r11;
        r11 = r7.pos;
        r13 = (long) r11;
        r13 = r13 + r1;
        r13 = r13 - r8;
        r1 = (int) r13;
    L_0x0064:
        if (r1 >= r12) goto L_0x0075;
    L_0x0066:
        r2 = r10[r1];
        r11 = r16;
        if (r2 != r11) goto L_0x0072;
    L_0x006c:
        r2 = r7.pos;
        r1 = r1 - r2;
        r1 = (long) r1;
        r1 = r1 + r8;
        return r1;
    L_0x0072:
        r1 = r1 + 1;
        goto L_0x0064;
    L_0x0075:
        r11 = r16;
        r1 = r7.limit;
        r2 = r7.pos;
        r1 = r1 - r2;
        r1 = (long) r1;
        r1 = r1 + r8;
        r7 = r7.next;
        r8 = r1;
        goto L_0x004b;
    L_0x0082:
        return r5;
    L_0x0083:
        r1 = new java.lang.IllegalArgumentException;
        r2 = 3;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = r0.size;
        r4 = java.lang.Long.valueOf(r4);
        r2[r3] = r4;
        r3 = java.lang.Long.valueOf(r17);
        r4 = 1;
        r2[r4] = r3;
        r3 = 2;
        r4 = java.lang.Long.valueOf(r19);
        r2[r3] = r4;
        r3 = "size=%s fromIndex=%s toIndex=%s";
        r2 = java.lang.String.format(r3, r2);
        r1.<init>(r2);
        throw r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.indexOf(byte, long, long):long");
    }

    public long indexOf(okio.ByteString r19, long r20) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:36:0x00b9 in {6, 11, 15, 26, 27, 28, 29, 31, 33, 35} preds:[]
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
        r18 = this;
        r6 = r18;
        r0 = r19.size();
        if (r0 == 0) goto L_0x00b1;
    L_0x0008:
        r0 = 0;
        r2 = (r20 > r0 ? 1 : (r20 == r0 ? 0 : -1));
        if (r2 < 0) goto L_0x00a9;
    L_0x000e:
        r2 = r6.head;
        r7 = -1;
        if (r2 != 0) goto L_0x0015;
    L_0x0014:
        return r7;
    L_0x0015:
        r3 = r6.size;
        r9 = r3 - r20;
        r5 = (r9 > r20 ? 1 : (r9 == r20 ? 0 : -1));
        if (r5 >= 0) goto L_0x002b;
    L_0x001d:
        r0 = (r3 > r20 ? 1 : (r3 == r20 ? 0 : -1));
        if (r0 <= 0) goto L_0x003b;
    L_0x0021:
        r2 = r2.prev;
        r0 = r2.limit;
        r1 = r2.pos;
        r0 = r0 - r1;
        r0 = (long) r0;
        r3 = r3 - r0;
        goto L_0x001d;
    L_0x002b:
        r3 = r0;
    L_0x002c:
        r0 = r2.limit;
        r1 = r2.pos;
        r0 = r0 - r1;
        r0 = (long) r0;
        r0 = r0 + r3;
        r5 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1));
        if (r5 >= 0) goto L_0x003b;
    L_0x0037:
        r2 = r2.next;
        r3 = r0;
        goto L_0x002c;
    L_0x003b:
        r0 = 0;
        r9 = r19;
        r10 = r9.getByte(r0);
        r11 = r19.size();
        r0 = r6.size;
        r12 = (long) r11;
        r0 = r0 - r12;
        r12 = 1;
        r12 = r12 + r0;
        r0 = r20;
        r5 = r2;
        r14 = r3;
    L_0x0051:
        r2 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1));
        if (r2 >= 0) goto L_0x00a7;
    L_0x0055:
        r4 = r5.data;
        r2 = r5.limit;
        r2 = (long) r2;
        r7 = r5.pos;
        r7 = (long) r7;
        r7 = r7 + r12;
        r7 = r7 - r14;
        r2 = java.lang.Math.min(r2, r7);
        r7 = (int) r2;
        r2 = r5.pos;
        r2 = (long) r2;
        r2 = r2 + r0;
        r2 = r2 - r14;
        r0 = (int) r2;
        r8 = r0;
    L_0x006b:
        if (r8 >= r7) goto L_0x0097;
    L_0x006d:
        r0 = r4[r8];
        if (r0 != r10) goto L_0x008c;
    L_0x0071:
        r2 = r8 + 1;
        r16 = 1;
        r0 = r18;
        r1 = r5;
        r3 = r19;
        r17 = r4;
        r4 = r16;
        r6 = r5;
        r5 = r11;
        r0 = r0.rangeEquals(r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x008f;
    L_0x0086:
        r0 = r6.pos;
        r8 = r8 - r0;
        r0 = (long) r8;
        r0 = r0 + r14;
        return r0;
    L_0x008c:
        r17 = r4;
        r6 = r5;
    L_0x008f:
        r8 = r8 + 1;
        r5 = r6;
        r4 = r17;
        r6 = r18;
        goto L_0x006b;
    L_0x0097:
        r6 = r5;
        r0 = r6.limit;
        r1 = r6.pos;
        r0 = r0 - r1;
        r0 = (long) r0;
        r0 = r0 + r14;
        r5 = r6.next;
        r14 = r0;
        r6 = r18;
        r7 = -1;
        goto L_0x0051;
    L_0x00a7:
        r0 = r7;
        return r0;
    L_0x00a9:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "fromIndex < 0";
        r0.<init>(r1);
        throw r0;
    L_0x00b1:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "bytes is empty";
        r0.<init>(r1);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.indexOf(okio.ByteString, long):long");
    }

    public long indexOfElement(okio.ByteString r12, long r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:46:0x00b1 in {4, 9, 13, 24, 25, 27, 28, 39, 40, 41, 42, 43, 45} preds:[]
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
        r11 = this;
        r0 = 0;
        r2 = (r13 > r0 ? 1 : (r13 == r0 ? 0 : -1));
        if (r2 < 0) goto L_0x00a9;
    L_0x0006:
        r2 = r11.head;
        r3 = -1;
        if (r2 != 0) goto L_0x000d;
    L_0x000c:
        return r3;
    L_0x000d:
        r5 = r11.size;
        r7 = r5 - r13;
        r9 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1));
        if (r9 >= 0) goto L_0x0023;
    L_0x0015:
        r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1));
        if (r0 <= 0) goto L_0x0033;
    L_0x0019:
        r2 = r2.prev;
        r0 = r2.limit;
        r1 = r2.pos;
        r0 = r0 - r1;
        r0 = (long) r0;
        r5 = r5 - r0;
        goto L_0x0015;
    L_0x0023:
        r5 = r0;
    L_0x0024:
        r0 = r2.limit;
        r1 = r2.pos;
        r0 = r0 - r1;
        r0 = (long) r0;
        r0 = r0 + r5;
        r7 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1));
        if (r7 >= 0) goto L_0x0033;
    L_0x002f:
        r2 = r2.next;
        r5 = r0;
        goto L_0x0024;
    L_0x0033:
        r0 = r12.size();
        r1 = 2;
        r7 = 0;
        if (r0 != r1) goto L_0x0071;
    L_0x003b:
        r0 = r12.getByte(r7);
        r1 = 1;
        r12 = r12.getByte(r1);
    L_0x0044:
        r7 = r11.size;
        r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r1 >= 0) goto L_0x00a8;
    L_0x004a:
        r1 = r2.data;
        r7 = r2.pos;
        r7 = (long) r7;
        r7 = r7 + r13;
        r7 = r7 - r5;
        r13 = (int) r7;
        r14 = r2.limit;
    L_0x0054:
        if (r13 >= r14) goto L_0x0066;
    L_0x0056:
        r7 = r1[r13];
        if (r7 == r0) goto L_0x0060;
    L_0x005a:
        if (r7 != r12) goto L_0x005d;
    L_0x005c:
        goto L_0x0060;
    L_0x005d:
        r13 = r13 + 1;
        goto L_0x0054;
    L_0x0060:
        r12 = r2.pos;
        r13 = r13 - r12;
        r12 = (long) r13;
        r12 = r12 + r5;
        return r12;
    L_0x0066:
        r13 = r2.limit;
        r14 = r2.pos;
        r13 = r13 - r14;
        r13 = (long) r13;
        r13 = r13 + r5;
        r2 = r2.next;
        r5 = r13;
        goto L_0x0044;
    L_0x0071:
        r12 = r12.internalArray();
    L_0x0075:
        r0 = r11.size;
        r8 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1));
        if (r8 >= 0) goto L_0x00a8;
    L_0x007b:
        r0 = r2.data;
        r1 = r2.pos;
        r8 = (long) r1;
        r8 = r8 + r13;
        r8 = r8 - r5;
        r13 = (int) r8;
        r14 = r2.limit;
    L_0x0085:
        if (r13 >= r14) goto L_0x009d;
    L_0x0087:
        r1 = r0[r13];
        r8 = r12.length;
        r9 = 0;
    L_0x008b:
        if (r9 >= r8) goto L_0x009a;
    L_0x008d:
        r10 = r12[r9];
        if (r1 != r10) goto L_0x0097;
    L_0x0091:
        r12 = r2.pos;
        r13 = r13 - r12;
        r12 = (long) r13;
        r12 = r12 + r5;
        return r12;
    L_0x0097:
        r9 = r9 + 1;
        goto L_0x008b;
    L_0x009a:
        r13 = r13 + 1;
        goto L_0x0085;
    L_0x009d:
        r13 = r2.limit;
        r14 = r2.pos;
        r13 = r13 - r14;
        r13 = (long) r13;
        r13 = r13 + r5;
        r2 = r2.next;
        r5 = r13;
        goto L_0x0075;
    L_0x00a8:
        return r3;
    L_0x00a9:
        r12 = new java.lang.IllegalArgumentException;
        r13 = "fromIndex < 0";
        r12.<init>(r13);
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.indexOfElement(okio.ByteString, long):long");
    }

    public long readDecimalLong() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:44:0x00c1 in {15, 16, 19, 21, 25, 26, 28, 30, 32, 33, 36, 39, 40, 41, 43} preds:[]
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
        r17 = this;
        r0 = r17;
        r1 = r0.size;
        r3 = 0;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 == 0) goto L_0x00b9;
    L_0x000a:
        r5 = -7;
        r7 = 0;
        r8 = r5;
        r5 = 0;
        r6 = 0;
    L_0x0010:
        r10 = r0.head;
        r11 = r10.data;
        r12 = r10.pos;
        r13 = r10.limit;
    L_0x0018:
        if (r12 >= r13) goto L_0x009a;
    L_0x001a:
        r15 = r11[r12];
        r14 = 48;
        if (r15 < r14) goto L_0x006c;
    L_0x0020:
        r1 = 57;
        if (r15 > r1) goto L_0x006c;
    L_0x0024:
        r14 = r14 - r15;
        r1 = -922337203685477580; // 0xf333333333333334 float:4.1723254E-8 double:-8.390303882365713E246;
        r16 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1));
        if (r16 < 0) goto L_0x003f;
    L_0x002e:
        r16 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1));
        if (r16 != 0) goto L_0x0038;
    L_0x0032:
        r1 = (long) r14;
        r16 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1));
        if (r16 >= 0) goto L_0x0038;
    L_0x0037:
        goto L_0x003f;
    L_0x0038:
        r1 = 10;
        r3 = r3 * r1;
        r1 = (long) r14;
        r3 = r3 + r1;
        goto L_0x0076;
    L_0x003f:
        r1 = new okio.Buffer;
        r1.<init>();
        r1 = r1.writeDecimalLong(r3);
        r1 = r1.writeByte(r15);
        if (r5 != 0) goto L_0x0051;
    L_0x004e:
        r1.readByte();
    L_0x0051:
        r2 = new java.lang.NumberFormatException;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Number too large: ";
        r3.append(r4);
        r1 = r1.readUtf8();
        r3.append(r1);
        r1 = r3.toString();
        r2.<init>(r1);
        throw r2;
    L_0x006c:
        r1 = 45;
        if (r15 != r1) goto L_0x007b;
    L_0x0070:
        if (r7 != 0) goto L_0x007b;
    L_0x0072:
        r1 = 1;
        r8 = r8 - r1;
        r5 = 1;
    L_0x0076:
        r12 = r12 + 1;
        r7 = r7 + 1;
        goto L_0x0018;
    L_0x007b:
        if (r7 == 0) goto L_0x007f;
    L_0x007d:
        r6 = 1;
        goto L_0x009a;
    L_0x007f:
        r1 = new java.lang.NumberFormatException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Expected leading [0-9] or '-' character but was 0x";
        r2.append(r3);
        r3 = java.lang.Integer.toHexString(r15);
        r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2);
        throw r1;
    L_0x009a:
        if (r12 != r13) goto L_0x00a6;
    L_0x009c:
        r1 = r10.pop();
        r0.head = r1;
        okio.SegmentPool.recycle(r10);
        goto L_0x00a8;
    L_0x00a6:
        r10.pos = r12;
    L_0x00a8:
        if (r6 != 0) goto L_0x00ae;
    L_0x00aa:
        r1 = r0.head;
        if (r1 != 0) goto L_0x0010;
    L_0x00ae:
        r1 = r0.size;
        r6 = (long) r7;
        r1 = r1 - r6;
        r0.size = r1;
        if (r5 == 0) goto L_0x00b7;
    L_0x00b6:
        goto L_0x00b8;
    L_0x00b7:
        r3 = -r3;
    L_0x00b8:
        return r3;
    L_0x00b9:
        r1 = new java.lang.IllegalStateException;
        r2 = "size == 0";
        r1.<init>(r2);
        throw r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    public long readHexadecimalUnsignedLong() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:39:0x00b5 in {9, 14, 19, 22, 24, 26, 28, 30, 31, 34, 36, 38} preds:[]
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
        r15 = this;
        r0 = r15.size;
        r2 = 0;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 == 0) goto L_0x00ad;
    L_0x0008:
        r0 = 0;
        r4 = r2;
        r1 = 0;
    L_0x000b:
        r6 = r15.head;
        r7 = r6.data;
        r8 = r6.pos;
        r9 = r6.limit;
    L_0x0013:
        if (r8 >= r9) goto L_0x0092;
    L_0x0015:
        r10 = r7[r8];
        r11 = 48;
        if (r10 < r11) goto L_0x0022;
    L_0x001b:
        r11 = 57;
        if (r10 > r11) goto L_0x0022;
    L_0x001f:
        r11 = r10 + -48;
        goto L_0x003b;
    L_0x0022:
        r11 = 97;
        if (r10 < r11) goto L_0x002f;
    L_0x0026:
        r11 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r10 > r11) goto L_0x002f;
    L_0x002a:
        r11 = r10 + -97;
        r11 = r11 + 10;
        goto L_0x003b;
    L_0x002f:
        r11 = 65;
        if (r10 < r11) goto L_0x0073;
    L_0x0033:
        r11 = 70;
        if (r10 > r11) goto L_0x0073;
    L_0x0037:
        r11 = r10 + -65;
        r11 = r11 + 10;
    L_0x003b:
        r12 = -1152921504606846976; // 0xf000000000000000 float:0.0 double:-3.105036184601418E231;
        r12 = r12 & r4;
        r14 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1));
        if (r14 != 0) goto L_0x004b;
    L_0x0042:
        r10 = 4;
        r4 = r4 << r10;
        r10 = (long) r11;
        r4 = r4 | r10;
        r8 = r8 + 1;
        r1 = r1 + 1;
        goto L_0x0013;
    L_0x004b:
        r0 = new okio.Buffer;
        r0.<init>();
        r0 = r0.writeHexadecimalUnsignedLong(r4);
        r0 = r0.writeByte(r10);
        r1 = new java.lang.NumberFormatException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Number too large: ";
        r2.append(r3);
        r0 = r0.readUtf8();
        r2.append(r0);
        r0 = r2.toString();
        r1.<init>(r0);
        throw r1;
    L_0x0073:
        if (r1 == 0) goto L_0x0077;
    L_0x0075:
        r0 = 1;
        goto L_0x0092;
    L_0x0077:
        r0 = new java.lang.NumberFormatException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Expected leading [0-9a-fA-F] character but was 0x";
        r1.append(r2);
        r2 = java.lang.Integer.toHexString(r10);
        r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0092:
        if (r8 != r9) goto L_0x009e;
    L_0x0094:
        r7 = r6.pop();
        r15.head = r7;
        okio.SegmentPool.recycle(r6);
        goto L_0x00a0;
    L_0x009e:
        r6.pos = r8;
    L_0x00a0:
        if (r0 != 0) goto L_0x00a6;
    L_0x00a2:
        r6 = r15.head;
        if (r6 != 0) goto L_0x000b;
    L_0x00a6:
        r2 = r15.size;
        r0 = (long) r1;
        r2 = r2 - r0;
        r15.size = r2;
        return r4;
    L_0x00ad:
        r0 = new java.lang.IllegalStateException;
        r1 = "size == 0";
        r0.<init>(r1);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    public int readUtf8CodePoint() throws java.io.EOFException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:39:0x00b3 in {4, 7, 10, 13, 19, 21, 24, 29, 31, 32, 34, 36, 38} preds:[]
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
        r12 = this;
        r0 = r12.size;
        r2 = 0;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 == 0) goto L_0x00ad;
    L_0x0008:
        r0 = r12.getByte(r2);
        r1 = r0 & 128;
        r2 = 1;
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r4 = 65533; // 0xfffd float:9.1831E-41 double:3.23776E-319;
        if (r1 != 0) goto L_0x001c;
    L_0x0016:
        r1 = r0 & 127;
        r5 = 0;
        r5 = 1;
        r6 = 0;
        goto L_0x003f;
    L_0x001c:
        r1 = r0 & 224;
        r5 = 192; // 0xc0 float:2.69E-43 double:9.5E-322;
        if (r1 != r5) goto L_0x0028;
    L_0x0022:
        r1 = r0 & 31;
        r5 = 2;
        r6 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        goto L_0x003f;
    L_0x0028:
        r1 = r0 & 240;
        r5 = 224; // 0xe0 float:3.14E-43 double:1.107E-321;
        if (r1 != r5) goto L_0x0034;
    L_0x002e:
        r1 = r0 & 15;
        r5 = 3;
        r6 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        goto L_0x003f;
    L_0x0034:
        r1 = r0 & 248;
        r5 = 240; // 0xf0 float:3.36E-43 double:1.186E-321;
        if (r1 != r5) goto L_0x00a7;
    L_0x003a:
        r1 = r0 & 7;
        r5 = 4;
        r6 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
    L_0x003f:
        r7 = r12.size;
        r9 = (long) r5;
        r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1));
        if (r11 < 0) goto L_0x0075;
    L_0x0046:
        if (r2 >= r5) goto L_0x005d;
    L_0x0048:
        r7 = (long) r2;
        r0 = r12.getByte(r7);
        r11 = r0 & 192;
        if (r11 != r3) goto L_0x0059;
    L_0x0051:
        r1 = r1 << 6;
        r0 = r0 & 63;
        r1 = r1 | r0;
        r2 = r2 + 1;
        goto L_0x0046;
    L_0x0059:
        r12.skip(r7);
        return r4;
    L_0x005d:
        r12.skip(r9);
        r0 = 1114111; // 0x10ffff float:1.561202E-39 double:5.50444E-318;
        if (r1 <= r0) goto L_0x0066;
    L_0x0065:
        return r4;
    L_0x0066:
        r0 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r1 < r0) goto L_0x0071;
    L_0x006b:
        r0 = 57343; // 0xdfff float:8.0355E-41 double:2.8331E-319;
        if (r1 > r0) goto L_0x0071;
    L_0x0070:
        return r4;
    L_0x0071:
        if (r1 >= r6) goto L_0x0074;
    L_0x0073:
        return r4;
    L_0x0074:
        return r1;
    L_0x0075:
        r1 = new java.io.EOFException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "size < ";
        r2.append(r3);
        r2.append(r5);
        r3 = ": ";
        r2.append(r3);
        r3 = r12.size;
        r2.append(r3);
        r3 = " (to read code point prefixed 0x";
        r2.append(r3);
        r0 = java.lang.Integer.toHexString(r0);
        r2.append(r0);
        r0 = ")";
        r2.append(r0);
        r0 = r2.toString();
        r1.<init>(r0);
        throw r1;
    L_0x00a7:
        r0 = 1;
        r12.skip(r0);
        return r4;
    L_0x00ad:
        r0 = new java.io.EOFException;
        r0.<init>();
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readUtf8CodePoint():int");
    }

    public okio.Buffer write(byte[] r10, int r11, int r12) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:8:0x0038 in {3, 5, 7} preds:[]
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
        r9 = this;
        if (r10 == 0) goto L_0x0030;
    L_0x0002:
        r0 = r10.length;
        r1 = (long) r0;
        r3 = (long) r11;
        r7 = (long) r12;
        r5 = r7;
        okio.Util.checkOffsetAndCount(r1, r3, r5);
        r12 = r12 + r11;
    L_0x000b:
        if (r11 >= r12) goto L_0x002a;
    L_0x000d:
        r0 = 1;
        r0 = r9.writableSegment(r0);
        r1 = r12 - r11;
        r2 = r0.limit;
        r2 = 8192 - r2;
        r1 = java.lang.Math.min(r1, r2);
        r2 = r0.data;
        r3 = r0.limit;
        java.lang.System.arraycopy(r10, r11, r2, r3, r1);
        r11 = r11 + r1;
        r2 = r0.limit;
        r2 = r2 + r1;
        r0.limit = r2;
        goto L_0x000b;
    L_0x002a:
        r10 = r9.size;
        r10 = r10 + r7;
        r9.size = r10;
        return r9;
    L_0x0030:
        r10 = new java.lang.IllegalArgumentException;
        r11 = "source == null";
        r10.<init>(r11);
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.write(byte[], int, int):okio.Buffer");
    }

    public void write(okio.Buffer r7, long r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:33:0x009d in {9, 10, 16, 17, 21, 22, 25, 26, 27, 28, 30, 32} preds:[]
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
        r6 = this;
        if (r7 == 0) goto L_0x0095;
    L_0x0002:
        if (r7 == r6) goto L_0x008d;
    L_0x0004:
        r0 = r7.size;
        r2 = 0;
        r4 = r8;
        okio.Util.checkOffsetAndCount(r0, r2, r4);
    L_0x000c:
        r0 = 0;
        r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x008c;
    L_0x0012:
        r0 = r7.head;
        r0 = r0.limit;
        r1 = r7.head;
        r1 = r1.pos;
        r0 = r0 - r1;
        r0 = (long) r0;
        r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1));
        if (r2 >= 0) goto L_0x005c;
    L_0x0020:
        r0 = r6.head;
        if (r0 == 0) goto L_0x0027;
    L_0x0024:
        r0 = r0.prev;
        goto L_0x0028;
    L_0x0027:
        r0 = 0;
    L_0x0028:
        if (r0 == 0) goto L_0x0053;
    L_0x002a:
        r1 = r0.owner;
        if (r1 == 0) goto L_0x0053;
    L_0x002e:
        r1 = r0.limit;
        r1 = (long) r1;
        r1 = r1 + r8;
        r3 = r0.shared;
        if (r3 == 0) goto L_0x0038;
    L_0x0036:
        r3 = 0;
        goto L_0x003a;
    L_0x0038:
        r3 = r0.pos;
    L_0x003a:
        r3 = (long) r3;
        r1 = r1 - r3;
        r3 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 > 0) goto L_0x0053;
    L_0x0042:
        r1 = r7.head;
        r2 = (int) r8;
        r1.writeTo(r0, r2);
        r0 = r7.size;
        r0 = r0 - r8;
        r7.size = r0;
        r0 = r6.size;
        r0 = r0 + r8;
        r6.size = r0;
        return;
    L_0x0053:
        r0 = r7.head;
        r1 = (int) r8;
        r0 = r0.split(r1);
        r7.head = r0;
    L_0x005c:
        r0 = r7.head;
        r1 = r0.limit;
        r2 = r0.pos;
        r1 = r1 - r2;
        r1 = (long) r1;
        r3 = r0.pop();
        r7.head = r3;
        r3 = r6.head;
        if (r3 != 0) goto L_0x0077;
    L_0x006e:
        r6.head = r0;
        r0 = r6.head;
        r0.prev = r0;
        r0.next = r0;
        goto L_0x0080;
    L_0x0077:
        r3 = r3.prev;
        r0 = r3.push(r0);
        r0.compact();
    L_0x0080:
        r3 = r7.size;
        r3 = r3 - r1;
        r7.size = r3;
        r3 = r6.size;
        r3 = r3 + r1;
        r6.size = r3;
        r8 = r8 - r1;
        goto L_0x000c;
    L_0x008c:
        return;
    L_0x008d:
        r7 = new java.lang.IllegalArgumentException;
        r8 = "source == this";
        r7.<init>(r8);
        throw r7;
    L_0x0095:
        r7 = new java.lang.IllegalArgumentException;
        r8 = "source == null";
        r7.<init>(r8);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.write(okio.Buffer, long):void");
    }

    public long writeAll(okio.Source r8) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:8:0x001b in {4, 5, 7} preds:[]
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
        if (r8 == 0) goto L_0x0013;
    L_0x0002:
        r0 = 0;
    L_0x0004:
        r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r2 = r8.read(r7, r2);
        r4 = -1;
        r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r6 == 0) goto L_0x0012;
    L_0x0010:
        r0 = r0 + r2;
        goto L_0x0004;
    L_0x0012:
        return r0;
    L_0x0013:
        r8 = new java.lang.IllegalArgumentException;
        r0 = "source == null";
        r8.<init>(r0);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.writeAll(okio.Source):long");
    }

    public okio.Buffer writeTo(java.io.OutputStream r7, long r8) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:10:0x004a in {6, 7, 9} preds:[]
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
        r6 = this;
        if (r7 == 0) goto L_0x0042;
    L_0x0002:
        r0 = r6.size;
        r2 = 0;
        r4 = r8;
        okio.Util.checkOffsetAndCount(r0, r2, r4);
        r0 = r6.head;
    L_0x000c:
        r1 = 0;
        r3 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1));
        if (r3 <= 0) goto L_0x0041;
    L_0x0012:
        r1 = r0.limit;
        r2 = r0.pos;
        r1 = r1 - r2;
        r1 = (long) r1;
        r1 = java.lang.Math.min(r8, r1);
        r2 = (int) r1;
        r1 = r0.data;
        r3 = r0.pos;
        r7.write(r1, r3, r2);
        r1 = r0.pos;
        r1 = r1 + r2;
        r0.pos = r1;
        r3 = r6.size;
        r1 = (long) r2;
        r3 = r3 - r1;
        r6.size = r3;
        r8 = r8 - r1;
        r1 = r0.pos;
        r2 = r0.limit;
        if (r1 != r2) goto L_0x000c;
    L_0x0036:
        r1 = r0.pop();
        r6.head = r1;
        okio.SegmentPool.recycle(r0);
        r0 = r1;
        goto L_0x000c;
    L_0x0041:
        return r6;
    L_0x0042:
        r7 = new java.lang.IllegalArgumentException;
        r8 = "out == null";
        r7.<init>(r8);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.writeTo(java.io.OutputStream, long):okio.Buffer");
    }

    public okio.Buffer writeUtf8(java.lang.String r8, int r9, int r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:45:0x0132 in {12, 13, 14, 17, 22, 25, 26, 32, 33, 34, 35, 36, 38, 40, 42, 44} preds:[]
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
        if (r8 == 0) goto L_0x012a;
    L_0x0002:
        if (r9 < 0) goto L_0x0113;
    L_0x0004:
        if (r10 < r9) goto L_0x00f4;
    L_0x0006:
        r0 = r8.length();
        if (r10 > r0) goto L_0x00d1;
    L_0x000c:
        if (r9 >= r10) goto L_0x00d0;
    L_0x000e:
        r0 = r8.charAt(r9);
        r1 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r0 >= r1) goto L_0x004e;
    L_0x0016:
        r2 = 1;
        r2 = r7.writableSegment(r2);
        r3 = r2.data;
        r4 = r2.limit;
        r4 = r4 - r9;
        r5 = 8192 - r4;
        r5 = java.lang.Math.min(r10, r5);
        r6 = r9 + 1;
        r9 = r9 + r4;
        r0 = (byte) r0;
        r3[r9] = r0;
    L_0x002c:
        if (r6 >= r5) goto L_0x003d;
    L_0x002e:
        r9 = r8.charAt(r6);
        if (r9 < r1) goto L_0x0035;
    L_0x0034:
        goto L_0x003d;
    L_0x0035:
        r0 = r6 + 1;
        r6 = r6 + r4;
        r9 = (byte) r9;
        r3[r6] = r9;
        r6 = r0;
        goto L_0x002c;
    L_0x003d:
        r4 = r4 + r6;
        r9 = r2.limit;
        r4 = r4 - r9;
        r9 = r2.limit;
        r9 = r9 + r4;
        r2.limit = r9;
        r0 = r7.size;
        r2 = (long) r4;
        r0 = r0 + r2;
        r7.size = r0;
        r9 = r6;
        goto L_0x000c;
    L_0x004e:
        r2 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        if (r0 >= r2) goto L_0x0062;
    L_0x0052:
        r2 = r0 >> 6;
        r2 = r2 | 192;
        r7.writeByte(r2);
        r0 = r0 & 63;
        r0 = r0 | r1;
        r7.writeByte(r0);
        r9 = r9 + 1;
        goto L_0x000c;
    L_0x0062:
        r2 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        r3 = 63;
        if (r0 < r2) goto L_0x00b8;
    L_0x0069:
        r2 = 57343; // 0xdfff float:8.0355E-41 double:2.8331E-319;
        if (r0 <= r2) goto L_0x006f;
    L_0x006e:
        goto L_0x00b8;
    L_0x006f:
        r4 = r9 + 1;
        if (r4 >= r10) goto L_0x0078;
    L_0x0073:
        r5 = r8.charAt(r4);
        goto L_0x0079;
    L_0x0078:
        r5 = 0;
    L_0x0079:
        r6 = 56319; // 0xdbff float:7.892E-41 double:2.78253E-319;
        if (r0 > r6) goto L_0x00b2;
    L_0x007e:
        r6 = 56320; // 0xdc00 float:7.8921E-41 double:2.7826E-319;
        if (r5 < r6) goto L_0x00b2;
    L_0x0083:
        if (r5 <= r2) goto L_0x0086;
    L_0x0085:
        goto L_0x00b2;
    L_0x0086:
        r2 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r4 = -55297; // 0xffffffffffff27ff float:NaN double:NaN;
        r0 = r0 & r4;
        r0 = r0 << 10;
        r4 = -56321; // 0xffffffffffff23ff float:NaN double:NaN;
        r4 = r4 & r5;
        r0 = r0 | r4;
        r0 = r0 + r2;
        r2 = r0 >> 18;
        r2 = r2 | 240;
        r7.writeByte(r2);
        r2 = r0 >> 12;
        r2 = r2 & r3;
        r2 = r2 | r1;
        r7.writeByte(r2);
        r2 = r0 >> 6;
        r2 = r2 & r3;
        r2 = r2 | r1;
        r7.writeByte(r2);
        r0 = r0 & r3;
        r0 = r0 | r1;
        r7.writeByte(r0);
        r9 = r9 + 2;
        goto L_0x000c;
    L_0x00b2:
        r7.writeByte(r3);
        r9 = r4;
        goto L_0x000c;
    L_0x00b8:
        r2 = r0 >> 12;
        r2 = r2 | 224;
        r7.writeByte(r2);
        r2 = r0 >> 6;
        r2 = r2 & r3;
        r2 = r2 | r1;
        r7.writeByte(r2);
        r0 = r0 & 63;
        r0 = r0 | r1;
        r7.writeByte(r0);
        r9 = r9 + 1;
        goto L_0x000c;
    L_0x00d0:
        return r7;
    L_0x00d1:
        r9 = new java.lang.IllegalArgumentException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "endIndex > string.length: ";
        r0.append(r1);
        r0.append(r10);
        r10 = " > ";
        r0.append(r10);
        r8 = r8.length();
        r0.append(r8);
        r8 = r0.toString();
        r9.<init>(r8);
        throw r9;
    L_0x00f4:
        r8 = new java.lang.IllegalArgumentException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "endIndex < beginIndex: ";
        r0.append(r1);
        r0.append(r10);
        r10 = " < ";
        r0.append(r10);
        r0.append(r9);
        r9 = r0.toString();
        r8.<init>(r9);
        throw r8;
    L_0x0113:
        r8 = new java.lang.IllegalArgumentException;
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r0 = "beginIndex < 0: ";
        r10.append(r0);
        r10.append(r9);
        r9 = r10.toString();
        r8.<init>(r9);
        throw r8;
    L_0x012a:
        r8 = new java.lang.IllegalArgumentException;
        r9 = "string == null";
        r8.<init>(r9);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.writeUtf8(java.lang.String, int, int):okio.Buffer");
    }

    public long size() {
        return this.size;
    }

    public OutputStream outputStream() {
        return new C04421();
    }

    public boolean exhausted() {
        return this.size == 0;
    }

    public void require(long j) throws EOFException {
        if (this.size < j) {
            throw new EOFException();
        }
    }

    public boolean request(long j) {
        return this.size >= j ? 1 : 0;
    }

    public InputStream inputStream() {
        return new C04432();
    }

    public Buffer copyTo(OutputStream outputStream) throws IOException {
        return copyTo(outputStream, 0, this.size);
    }

    public Buffer writeTo(OutputStream outputStream) throws IOException {
        return writeTo(outputStream, this.size);
    }

    public Buffer readFrom(InputStream inputStream) throws IOException {
        readFrom(inputStream, Long.MAX_VALUE, true);
        return this;
    }

    public Buffer readFrom(InputStream inputStream, long j) throws IOException {
        if (j >= 0) {
            readFrom(inputStream, j, false);
            return this;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("byteCount < 0: ");
        stringBuilder.append(j);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public long completeSegmentByteCount() {
        long j = this.size;
        if (j == 0) {
            return 0;
        }
        Segment segment = this.head.prev;
        if (segment.limit < 8192 && segment.owner) {
            j -= (long) (segment.limit - segment.pos);
        }
        return j;
    }

    public byte readByte() {
        if (this.size != 0) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            int i3 = i + 1;
            byte b = segment.data[i];
            this.size--;
            if (i3 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i3;
            }
            return b;
        }
        throw new IllegalStateException("size == 0");
    }

    public byte getByte(long j) {
        Util.checkOffsetAndCount(this.size, j, 1);
        Segment segment = this.head;
        while (true) {
            long j2 = (long) (segment.limit - segment.pos);
            if (j < j2) {
                return segment.data[segment.pos + ((int) j)];
            }
            j -= j2;
            segment = segment.next;
        }
    }

    public short readShort() {
        if (this.size >= 2) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 2) {
                return (short) (((readByte() & 255) << 8) | (readByte() & 255));
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            i = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
            this.size -= 2;
            if (i4 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return (short) i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size < 2: ");
        stringBuilder.append(this.size);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public int readInt() {
        if (this.size >= 4) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 4) {
                return ((((readByte() & 255) << 24) | ((readByte() & 255) << 16)) | ((readByte() & 255) << 8)) | (readByte() & 255);
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            i = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
            i3 = i4 + 1;
            i |= (bArr[i4] & 255) << 8;
            i4 = i3 + 1;
            i |= bArr[i3] & 255;
            this.size -= 4;
            if (i4 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size < 4: ");
        stringBuilder.append(this.size);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public long readLong() {
        if (this.size >= 8) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 8) {
                return ((((long) readInt()) & 4294967295L) << 32) | (4294967295L & ((long) readInt()));
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            i = i3 + 1;
            i3 = i + 1;
            i = i3 + 1;
            int i4 = i + 1;
            i = i4 + 1;
            long j = ((((((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i3]) & 255) << 48)) | ((((long) bArr[i]) & 255) << 40)) | ((((long) bArr[i3]) & 255) << 32)) | ((((long) bArr[i]) & 255) << 24)) | ((((long) bArr[i4]) & 255) << 16);
            i4 = i + 1;
            long j2 = ((((long) bArr[i]) & 255) << 8) | j;
            i = i4 + 1;
            long j3 = (((long) bArr[i4]) & 255) | j2;
            this.size -= 8;
            if (i == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i;
            }
            return j3;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size < 8: ");
        stringBuilder.append(this.size);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public short readShortLe() {
        return Util.reverseBytesShort(readShort());
    }

    public int readIntLe() {
        return Util.reverseBytesInt(readInt());
    }

    public long readLongLe() {
        return Util.reverseBytesLong(readLong());
    }

    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    public ByteString readByteString(long j) throws EOFException {
        return new ByteString(readByteArray(j));
    }

    public int select(Options options) {
        Segment segment = this.head;
        if (segment == null) {
            return options.indexOf(ByteString.EMPTY);
        }
        options = options.byteStrings;
        int length = options.length;
        for (int i = 0; i < length; i++) {
            ByteString byteString = options[i];
            if (this.size >= ((long) byteString.size())) {
                if (rangeEquals(segment, segment.pos, byteString, 0, byteString.size())) {
                    try {
                        skip((long) byteString.size());
                        return i;
                    } catch (Options options2) {
                        throw new AssertionError(options2);
                    }
                }
            }
        }
        return -1;
    }

    int selectPrefix(Options options) {
        Segment segment = this.head;
        options = options.byteStrings;
        int length = options.length;
        int i = 0;
        while (i < length) {
            ByteString byteString = options[i];
            int min = (int) Math.min(this.size, (long) byteString.size());
            if (min != 0) {
                if (!rangeEquals(segment, segment.pos, byteString, 0, min)) {
                    i++;
                }
            }
            return i;
        }
        return -1;
    }

    public void readFully(Buffer buffer, long j) throws EOFException {
        long j2 = this.size;
        if (j2 >= j) {
            buffer.write(this, j);
        } else {
            buffer.write(this, j2);
            throw new EOFException();
        }
    }

    public long readAll(Sink sink) throws IOException {
        long j = this.size;
        if (j > 0) {
            sink.write(this, j);
        }
        return j;
    }

    public String readUtf8() {
        try {
            return readString(this.size, Util.UTF_8);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String readUtf8(long j) throws EOFException {
        return readString(j, Util.UTF_8);
    }

    public String readString(Charset charset) {
        try {
            return readString(this.size, charset);
        } catch (Charset charset2) {
            throw new AssertionError(charset2);
        }
    }

    public String readString(long j, Charset charset) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("byteCount > Integer.MAX_VALUE: ");
            stringBuilder.append(j);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (j == 0) {
            return "";
        } else {
            Segment segment = this.head;
            if (((long) segment.pos) + j > ((long) segment.limit)) {
                return new String(readByteArray(j), charset);
            }
            String str = new String(segment.data, segment.pos, (int) j, charset);
            segment.pos = (int) (((long) segment.pos) + j);
            this.size -= j;
            if (segment.pos == segment.limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            return str;
        }
    }

    @Nullable
    public String readUtf8Line() throws EOFException {
        long indexOf = indexOf((byte) 10);
        if (indexOf != -1) {
            return readUtf8Line(indexOf);
        }
        indexOf = this.size;
        return indexOf != 0 ? readUtf8(indexOf) : null;
    }

    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    public String readUtf8LineStrict(long j) throws EOFException {
        if (j >= 0) {
            long j2 = Long.MAX_VALUE;
            if (j != Long.MAX_VALUE) {
                j2 = j + 1;
            }
            long indexOf = indexOf((byte) 10, 0, j2);
            if (indexOf != -1) {
                return readUtf8Line(indexOf);
            }
            if (j2 < size() && getByte(j2 - 1) == (byte) 13 && getByte(j2) == (byte) 10) {
                return readUtf8Line(j2);
            }
            Buffer buffer = new Buffer();
            copyTo(buffer, 0, Math.min(32, size()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\\n not found: limit=");
            stringBuilder.append(Math.min(size(), j));
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

    String readUtf8Line(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (getByte(j2) == (byte) 13) {
                j = readUtf8(j2);
                skip(2);
                return j;
            }
        }
        j = readUtf8(j);
        skip(1);
        return j;
    }

    public byte[] readByteArray() {
        try {
            return readByteArray(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public byte[] readByteArray(long j) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0, j);
        if (j <= 2147483647L) {
            j = new byte[((int) j)];
            readFully(j);
            return j;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("byteCount > Integer.MAX_VALUE: ");
        stringBuilder.append(j);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public void readFully(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int read = read(bArr, i, bArr.length - i);
            if (read != -1) {
                i += read;
            } else {
                throw new EOFException();
            }
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        i2 = Math.min(i2, segment.limit - segment.pos);
        System.arraycopy(segment.data, segment.pos, bArr, i, i2);
        segment.pos += i2;
        this.size -= (long) i2;
        if (segment.pos == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return i2;
    }

    public void clear() {
        try {
            skip(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public void skip(long j) throws EOFException {
        while (j > 0) {
            Segment segment = this.head;
            if (segment != null) {
                int min = (int) Math.min(j, (long) (segment.limit - this.head.pos));
                long j2 = (long) min;
                this.size -= j2;
                j -= j2;
                segment = this.head;
                segment.pos += min;
                if (this.head.pos == this.head.limit) {
                    segment = this.head;
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    public Buffer write(ByteString byteString) {
        if (byteString != null) {
            byteString.write(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    public Buffer writeUtf8(String str) {
        return writeUtf8(str, 0, str.length());
    }

    public Buffer writeUtf8CodePoint(int i) {
        if (i < 128) {
            writeByte(i);
        } else if (i < 2048) {
            writeByte((i >> 6) | 192);
            writeByte((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                writeByte((i >> 12) | 224);
                writeByte(((i >> 6) & 63) | 128);
                writeByte((i & 63) | 128);
            } else {
                writeByte(63);
            }
        } else if (i <= 1114111) {
            writeByte((i >> 18) | 240);
            writeByte(((i >> 12) & 63) | 128);
            writeByte(((i >> 6) & 63) | 128);
            writeByte((i & 63) | 128);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected code point: ");
            stringBuilder.append(Integer.toHexString(i));
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        return this;
    }

    public Buffer writeString(String str, Charset charset) {
        return writeString(str, 0, str.length(), charset);
    }

    public Buffer writeString(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            i2 = new StringBuilder();
            i2.append("beginIndex < 0: ");
            i2.append(i);
            throw new IllegalAccessError(i2.toString());
        } else if (i2 < i) {
            charset = new StringBuilder();
            charset.append("endIndex < beginIndex: ");
            charset.append(i2);
            charset.append(" < ");
            charset.append(i);
            throw new IllegalArgumentException(charset.toString());
        } else if (i2 > str.length()) {
            charset = new StringBuilder();
            charset.append("endIndex > string.length: ");
            charset.append(i2);
            charset.append(" > ");
            charset.append(str.length());
            throw new IllegalArgumentException(charset.toString());
        } else if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (charset.equals(Util.UTF_8)) {
            return writeUtf8(str, i, i2);
        } else {
            byte[] bytes = str.substring(i, i2).getBytes(charset);
            return write(bytes, 0, bytes.length);
        }
    }

    public Buffer write(byte[] bArr) {
        if (bArr != null) {
            return write(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    public BufferedSink write(Source source, long j) throws IOException {
        while (j > 0) {
            long read = source.read(this, j);
            if (read != -1) {
                j -= read;
            } else {
                throw new EOFException();
            }
        }
        return this;
    }

    public Buffer writeByte(int i) {
        Segment writableSegment = writableSegment(1);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        writableSegment.limit = i2 + 1;
        bArr[i2] = (byte) i;
        this.size++;
        return this;
    }

    public Buffer writeShort(int i) {
        Segment writableSegment = writableSegment(2);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        writableSegment.limit = i2;
        this.size += 2;
        return this;
    }

    public Buffer writeShortLe(int i) {
        return writeShort(Util.reverseBytesShort((short) i));
    }

    public Buffer writeInt(int i) {
        Segment writableSegment = writableSegment(4);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        writableSegment.limit = i2;
        this.size += 4;
        return this;
    }

    public Buffer writeIntLe(int i) {
        return writeInt(Util.reverseBytesInt(i));
    }

    public Buffer writeLong(long j) {
        Segment writableSegment = writableSegment(8);
        byte[] bArr = writableSegment.data;
        int i = writableSegment.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 56) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 48) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 40) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 32) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 24) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 16) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 8) & 255));
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((int) (j & 255));
        writableSegment.limit = i3;
        this.size += 8;
        return this;
    }

    public Buffer writeLongLe(long j) {
        return writeLong(Util.reverseBytesLong(j));
    }

    public Buffer writeDecimalLong(long j) {
        if (j == 0) {
            return writeByte((int) 48);
        }
        Object obj = null;
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                return writeUtf8("-9223372036854775808");
            }
            obj = 1;
        }
        if (j >= 100000000) {
            i = j < 1000000000000L ? j < 10000000000L ? j < 1000000000 ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j >= 10000) {
            i = j < 1000000 ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8;
        } else if (j >= 100) {
            i = j < 1000 ? 3 : 4;
        } else if (j >= 10) {
            i = 2;
        }
        if (obj != null) {
            i++;
        }
        Segment writableSegment = writableSegment(i);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit + i;
        while (j != 0) {
            i2--;
            bArr[i2] = DIGITS[(int) (j % 10)];
            j /= 10;
        }
        if (obj != null) {
            bArr[i2 - 1] = 45;
        }
        writableSegment.limit += i;
        this.size += (long) i;
        return this;
    }

    public Buffer writeHexadecimalUnsignedLong(long j) {
        if (j == 0) {
            return writeByte((int) 48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        Segment writableSegment = writableSegment(numberOfTrailingZeros);
        byte[] bArr = writableSegment.data;
        int i = writableSegment.limit;
        for (int i2 = (writableSegment.limit + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = DIGITS[(int) (15 & j)];
            j >>>= 4;
        }
        writableSegment.limit += numberOfTrailingZeros;
        this.size += (long) numberOfTrailingZeros;
        return this;
    }

    Segment writableSegment(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        Segment segment = this.head;
        if (segment == null) {
            this.head = SegmentPool.take();
            i = this.head;
            i.prev = i;
            i.next = i;
            return i;
        }
        segment = segment.prev;
        if (segment.limit + i > 8192 || segment.owner == 0) {
            segment = segment.push(SegmentPool.take());
        }
        return segment;
    }

    public long read(Buffer buffer, long j) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j >= 0) {
            long j2 = this.size;
            if (j2 == 0) {
                return -1;
            }
            if (j > j2) {
                j = j2;
            }
            buffer.write(this, j);
            return j;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("byteCount < 0: ");
            stringBuilder.append(j);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public long indexOf(byte b) {
        return indexOf(b, 0, Long.MAX_VALUE);
    }

    public long indexOf(byte b, long j) {
        return indexOf(b, j, Long.MAX_VALUE);
    }

    public long indexOf(ByteString byteString) throws IOException {
        return indexOf(byteString, 0);
    }

    public long indexOfElement(ByteString byteString) {
        return indexOfElement(byteString, 0);
    }

    public boolean rangeEquals(long j, ByteString byteString) {
        return rangeEquals(j, byteString, 0, byteString.size());
    }

    public boolean rangeEquals(long j, ByteString byteString, int i, int i2) {
        if (j >= 0 && i >= 0 && i2 >= 0 && this.size - j >= ((long) i2)) {
            if (byteString.size() - i >= i2) {
                for (int i3 = 0; i3 < i2; i3++) {
                    if (getByte(((long) i3) + j) != byteString.getByte(i + i3)) {
                        return false;
                    }
                }
                return 1;
            }
        }
        return false;
    }

    private boolean rangeEquals(Segment segment, int i, ByteString byteString, int i2, int i3) {
        int i4 = segment.limit;
        byte[] bArr = segment.data;
        while (i2 < i3) {
            if (i == i4) {
                segment = segment.next;
                i = segment.data;
                i4 = segment.pos;
                bArr = i;
                i = i4;
                i4 = segment.limit;
            }
            if (bArr[i] != byteString.getByte(i2)) {
                return null;
            }
            i++;
            i2++;
        }
        return true;
    }

    public Timeout timeout() {
        return Timeout.NONE;
    }

    List<Integer> segmentSizes() {
        if (this.head == null) {
            return Collections.emptyList();
        }
        List<Integer> arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(this.head.limit - this.head.pos));
        Segment segment = this.head;
        while (true) {
            segment = segment.next;
            if (segment == this.head) {
                return arrayList;
            }
            arrayList.add(Integer.valueOf(segment.limit - segment.pos));
        }
    }

    public ByteString md5() {
        return digest("MD5");
    }

    public ByteString sha1() {
        return digest("SHA-1");
    }

    public ByteString sha256() {
        return digest("SHA-256");
    }

    public ByteString sha512() {
        return digest("SHA-512");
    }

    public ByteString hmacSha1(ByteString byteString) {
        return hmac("HmacSHA1", byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        return hmac("HmacSHA256", byteString);
    }

    public ByteString hmacSha512(ByteString byteString) {
        return hmac("HmacSHA512", byteString);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        Buffer buffer = (Buffer) obj;
        long j = this.size;
        if (j != buffer.size) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        Segment segment = this.head;
        obj = buffer.head;
        int i = segment.pos;
        int i2 = obj.pos;
        while (j2 < this.size) {
            long min = (long) Math.min(segment.limit - i, obj.limit - i2);
            int i3 = i2;
            i2 = i;
            i = 0;
            while (((long) i) < min) {
                int i4 = i2 + 1;
                int i5 = i3 + 1;
                if (segment.data[i2] != obj.data[i3]) {
                    return false;
                }
                i++;
                i2 = i4;
                i3 = i5;
            }
            if (i2 == segment.limit) {
                segment = segment.next;
                i = segment.pos;
            } else {
                i = i2;
            }
            if (i3 == obj.limit) {
                obj = obj.next;
                i2 = obj.pos;
            } else {
                i2 = i3;
            }
            j2 += min;
        }
        return true;
    }

    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i = 1;
        do {
            for (int i2 = segment.pos; i2 < segment.limit; i2++) {
                i = (i * 31) + segment.data[i2];
            }
            segment = segment.next;
        } while (segment != this.head);
        return i;
    }

    public String toString() {
        return snapshot().toString();
    }

    public Buffer clone() {
        Buffer buffer = new Buffer();
        if (this.size == 0) {
            return buffer;
        }
        buffer.head = new Segment(this.head);
        Segment segment = buffer.head;
        segment.prev = segment;
        segment.next = segment;
        segment = this.head;
        while (true) {
            segment = segment.next;
            if (segment != this.head) {
                buffer.head.prev.push(new Segment(segment));
            } else {
                buffer.size = this.size;
                return buffer;
            }
        }
    }

    public ByteString snapshot() {
        long j = this.size;
        if (j <= 2147483647L) {
            return snapshot((int) j);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size > Integer.MAX_VALUE: ");
        stringBuilder.append(this.size);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public ByteString snapshot(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, i);
    }
}
