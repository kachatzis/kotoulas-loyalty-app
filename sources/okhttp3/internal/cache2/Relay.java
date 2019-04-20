package okhttp3.internal.cache2;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

final class Relay {
    private static final long FILE_HEADER_SIZE = 32;
    static final ByteString PREFIX_CLEAN = ByteString.encodeUtf8("OkHttp cache v1\n");
    static final ByteString PREFIX_DIRTY = ByteString.encodeUtf8("OkHttp DIRTY :(\n");
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    final Buffer buffer = new Buffer();
    final long bufferMaxSize;
    boolean complete;
    RandomAccessFile file;
    private final ByteString metadata;
    int sourceCount;
    Source upstream;
    final Buffer upstreamBuffer = new Buffer();
    long upstreamPos;
    Thread upstreamReader;

    class RelaySource implements Source {
        private FileOperator fileOperator = new FileOperator(Relay.this.file.getChannel());
        private long sourcePos;
        private final Timeout timeout = new Timeout();

        public long read(okio.Buffer r22, long r23) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:82:0x0142 in {10, 13, 16, 20, 24, 35, 38, 45, 53, 56, 61, 68, 72, 76, 79, 81} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r21 = this;
            r1 = r21;
            r2 = r23;
            r0 = r1.fileOperator;
            if (r0 == 0) goto L_0x013a;
        L_0x0008:
            r4 = okhttp3.internal.cache2.Relay.this;
            monitor-enter(r4);
        L_0x000b:
            r5 = r1.sourcePos;	 Catch:{ all -> 0x0137 }
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0137 }
            r7 = r0.upstreamPos;	 Catch:{ all -> 0x0137 }
            r0 = 2;	 Catch:{ all -> 0x0137 }
            r9 = -1;	 Catch:{ all -> 0x0137 }
            r11 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));	 Catch:{ all -> 0x0137 }
            if (r11 != 0) goto L_0x0039;	 Catch:{ all -> 0x0137 }
        L_0x0018:
            r5 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0137 }
            r5 = r5.complete;	 Catch:{ all -> 0x0137 }
            if (r5 == 0) goto L_0x0020;	 Catch:{ all -> 0x0137 }
        L_0x001e:
            monitor-exit(r4);	 Catch:{ all -> 0x0137 }
            return r9;	 Catch:{ all -> 0x0137 }
        L_0x0020:
            r5 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0137 }
            r5 = r5.upstreamReader;	 Catch:{ all -> 0x0137 }
            if (r5 == 0) goto L_0x002e;	 Catch:{ all -> 0x0137 }
        L_0x0026:
            r0 = r1.timeout;	 Catch:{ all -> 0x0137 }
            r5 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0137 }
            r0.waitUntilNotified(r5);	 Catch:{ all -> 0x0137 }
            goto L_0x000b;	 Catch:{ all -> 0x0137 }
        L_0x002e:
            r5 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0137 }
            r6 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0137 }
            r5.upstreamReader = r6;	 Catch:{ all -> 0x0137 }
            r5 = 1;	 Catch:{ all -> 0x0137 }
            monitor-exit(r4);	 Catch:{ all -> 0x0137 }
            goto L_0x004b;	 Catch:{ all -> 0x0137 }
        L_0x0039:
            r5 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0137 }
            r5 = r5.buffer;	 Catch:{ all -> 0x0137 }
            r5 = r5.size();	 Catch:{ all -> 0x0137 }
            r5 = r7 - r5;	 Catch:{ all -> 0x0137 }
            r11 = r1.sourcePos;	 Catch:{ all -> 0x0137 }
            r13 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1));	 Catch:{ all -> 0x0137 }
            if (r13 >= 0) goto L_0x011b;	 Catch:{ all -> 0x0137 }
        L_0x0049:
            monitor-exit(r4);	 Catch:{ all -> 0x0137 }
            r5 = 2;
        L_0x004b:
            r11 = 32;
            if (r5 != r0) goto L_0x0069;
        L_0x004f:
            r4 = r1.sourcePos;
            r7 = r7 - r4;
            r2 = java.lang.Math.min(r2, r7);
            r13 = r1.fileOperator;
            r4 = r1.sourcePos;
            r14 = r4 + r11;
            r16 = r22;
            r17 = r2;
            r13.read(r14, r16, r17);
            r4 = r1.sourcePos;
            r4 = r4 + r2;
            r1.sourcePos = r4;
            return r2;
        L_0x0069:
            r4 = 0;
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0109 }
            r0 = r0.upstream;	 Catch:{ all -> 0x0109 }
            r5 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0109 }
            r5 = r5.upstreamBuffer;	 Catch:{ all -> 0x0109 }
            r6 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0109 }
            r13 = r6.bufferMaxSize;	 Catch:{ all -> 0x0109 }
            r5 = r0.read(r5, r13);	 Catch:{ all -> 0x0109 }
            r0 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1));	 Catch:{ all -> 0x0109 }
            if (r0 != 0) goto L_0x0094;	 Catch:{ all -> 0x0109 }
        L_0x007e:
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0109 }
            r0.commit(r7);	 Catch:{ all -> 0x0109 }
            r2 = okhttp3.internal.cache2.Relay.this;
            monitor-enter(r2);
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0091 }
            r0.upstreamReader = r4;	 Catch:{ all -> 0x0091 }
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0091 }
            r0.notifyAll();	 Catch:{ all -> 0x0091 }
            monitor-exit(r2);	 Catch:{ all -> 0x0091 }
            return r9;	 Catch:{ all -> 0x0091 }
        L_0x0091:
            r0 = move-exception;	 Catch:{ all -> 0x0091 }
            monitor-exit(r2);	 Catch:{ all -> 0x0091 }
            throw r0;
        L_0x0094:
            r2 = java.lang.Math.min(r5, r2);	 Catch:{ all -> 0x0109 }
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0109 }
            r13 = r0.upstreamBuffer;	 Catch:{ all -> 0x0109 }
            r15 = 0;	 Catch:{ all -> 0x0109 }
            r14 = r22;	 Catch:{ all -> 0x0109 }
            r17 = r2;	 Catch:{ all -> 0x0109 }
            r13.copyTo(r14, r15, r17);	 Catch:{ all -> 0x0109 }
            r9 = r1.sourcePos;	 Catch:{ all -> 0x0109 }
            r9 = r9 + r2;	 Catch:{ all -> 0x0109 }
            r1.sourcePos = r9;	 Catch:{ all -> 0x0109 }
            r15 = r1.fileOperator;	 Catch:{ all -> 0x0109 }
            r16 = r7 + r11;	 Catch:{ all -> 0x0109 }
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0109 }
            r0 = r0.upstreamBuffer;	 Catch:{ all -> 0x0109 }
            r18 = r0.clone();	 Catch:{ all -> 0x0109 }
            r19 = r5;	 Catch:{ all -> 0x0109 }
            r15.write(r16, r18, r19);	 Catch:{ all -> 0x0109 }
            r7 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0109 }
            monitor-enter(r7);	 Catch:{ all -> 0x0109 }
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0106 }
            r0 = r0.buffer;	 Catch:{ all -> 0x0106 }
            r8 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0106 }
            r8 = r8.upstreamBuffer;	 Catch:{ all -> 0x0106 }
            r0.write(r8, r5);	 Catch:{ all -> 0x0106 }
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0106 }
            r0 = r0.buffer;	 Catch:{ all -> 0x0106 }
            r8 = r0.size();	 Catch:{ all -> 0x0106 }
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0106 }
            r10 = r0.bufferMaxSize;	 Catch:{ all -> 0x0106 }
            r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));	 Catch:{ all -> 0x0106 }
            if (r0 <= 0) goto L_0x00ed;	 Catch:{ all -> 0x0106 }
        L_0x00d9:
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0106 }
            r0 = r0.buffer;	 Catch:{ all -> 0x0106 }
            r8 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0106 }
            r8 = r8.buffer;	 Catch:{ all -> 0x0106 }
            r8 = r8.size();	 Catch:{ all -> 0x0106 }
            r10 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0106 }
            r10 = r10.bufferMaxSize;	 Catch:{ all -> 0x0106 }
            r8 = r8 - r10;	 Catch:{ all -> 0x0106 }
            r0.skip(r8);	 Catch:{ all -> 0x0106 }
        L_0x00ed:
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0106 }
            r8 = r0.upstreamPos;	 Catch:{ all -> 0x0106 }
            r8 = r8 + r5;	 Catch:{ all -> 0x0106 }
            r0.upstreamPos = r8;	 Catch:{ all -> 0x0106 }
            monitor-exit(r7);	 Catch:{ all -> 0x0106 }
            r5 = okhttp3.internal.cache2.Relay.this;
            monitor-enter(r5);
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0103 }
            r0.upstreamReader = r4;	 Catch:{ all -> 0x0103 }
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0103 }
            r0.notifyAll();	 Catch:{ all -> 0x0103 }
            monitor-exit(r5);	 Catch:{ all -> 0x0103 }
            return r2;	 Catch:{ all -> 0x0103 }
        L_0x0103:
            r0 = move-exception;	 Catch:{ all -> 0x0103 }
            monitor-exit(r5);	 Catch:{ all -> 0x0103 }
            throw r0;
        L_0x0106:
            r0 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x0106 }
            throw r0;	 Catch:{ all -> 0x0109 }
        L_0x0109:
            r0 = move-exception;
            r2 = okhttp3.internal.cache2.Relay.this;
            monitor-enter(r2);
            r3 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0118 }
            r3.upstreamReader = r4;	 Catch:{ all -> 0x0118 }
            r3 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0118 }
            r3.notifyAll();	 Catch:{ all -> 0x0118 }
            monitor-exit(r2);	 Catch:{ all -> 0x0118 }
            throw r0;
        L_0x0118:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0118 }
            throw r0;
        L_0x011b:
            r9 = r1.sourcePos;	 Catch:{ all -> 0x0137 }
            r7 = r7 - r9;	 Catch:{ all -> 0x0137 }
            r2 = java.lang.Math.min(r2, r7);	 Catch:{ all -> 0x0137 }
            r0 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0137 }
            r9 = r0.buffer;	 Catch:{ all -> 0x0137 }
            r7 = r1.sourcePos;	 Catch:{ all -> 0x0137 }
            r11 = r7 - r5;	 Catch:{ all -> 0x0137 }
            r10 = r22;	 Catch:{ all -> 0x0137 }
            r13 = r2;	 Catch:{ all -> 0x0137 }
            r9.copyTo(r10, r11, r13);	 Catch:{ all -> 0x0137 }
            r5 = r1.sourcePos;	 Catch:{ all -> 0x0137 }
            r5 = r5 + r2;	 Catch:{ all -> 0x0137 }
            r1.sourcePos = r5;	 Catch:{ all -> 0x0137 }
            monitor-exit(r4);	 Catch:{ all -> 0x0137 }
            return r2;	 Catch:{ all -> 0x0137 }
        L_0x0137:
            r0 = move-exception;	 Catch:{ all -> 0x0137 }
            monitor-exit(r4);	 Catch:{ all -> 0x0137 }
            throw r0;
        L_0x013a:
            r0 = new java.lang.IllegalStateException;
            r2 = "closed";
            r0.<init>(r2);
            throw r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.Relay.RelaySource.read(okio.Buffer, long):long");
        }

        RelaySource() {
        }

        public Timeout timeout() {
            return this.timeout;
        }

        public void close() throws IOException {
            if (this.fileOperator != null) {
                Closeable closeable = null;
                this.fileOperator = null;
                synchronized (Relay.this) {
                    Relay relay = Relay.this;
                    relay.sourceCount--;
                    if (Relay.this.sourceCount == 0) {
                        RandomAccessFile randomAccessFile = Relay.this.file;
                        Relay.this.file = null;
                        closeable = randomAccessFile;
                    }
                }
                if (closeable != null) {
                    Util.closeQuietly(closeable);
                }
            }
        }
    }

    private Relay(RandomAccessFile randomAccessFile, Source source, long j, ByteString byteString, long j2) {
        this.file = randomAccessFile;
        this.upstream = source;
        this.complete = source == null ? true : null;
        this.upstreamPos = j;
        this.metadata = byteString;
        this.bufferMaxSize = j2;
    }

    public static Relay edit(File file, Source source, ByteString byteString, long j) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        File relay = new Relay(randomAccessFile, source, 0, byteString, j);
        randomAccessFile.setLength(null);
        relay.writeHeader(PREFIX_DIRTY, -1, -1);
        return relay;
    }

    public static Relay read(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        file = new FileOperator(randomAccessFile.getChannel());
        Buffer buffer = new Buffer();
        file.read(0, buffer, 32);
        if (buffer.readByteString((long) PREFIX_CLEAN.size()).equals(PREFIX_CLEAN)) {
            long readLong = buffer.readLong();
            long readLong2 = buffer.readLong();
            buffer = new Buffer();
            file.read(readLong + 32, buffer, readLong2);
            return new Relay(randomAccessFile, null, readLong, buffer.readByteString(), 0);
        }
        throw new IOException("unreadable cache file");
    }

    private void writeHeader(ByteString byteString, long j, long j2) throws IOException {
        Buffer buffer = new Buffer();
        buffer.write(byteString);
        buffer.writeLong(j);
        buffer.writeLong(j2);
        if (buffer.size() == 32) {
            new FileOperator(this.file.getChannel()).write(0, buffer, 32);
            return;
        }
        throw new IllegalArgumentException();
    }

    private void writeMetadata(long j) throws IOException {
        Buffer buffer = new Buffer();
        buffer.write(this.metadata);
        new FileOperator(this.file.getChannel()).write(32 + j, buffer, (long) this.metadata.size());
    }

    void commit(long j) throws IOException {
        writeMetadata(j);
        this.file.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, j, (long) this.metadata.size());
        this.file.getChannel().force(false);
        synchronized (this) {
            this.complete = 1;
        }
        Util.closeQuietly(this.upstream);
        this.upstream = 0;
    }

    boolean isClosed() {
        return this.file == null;
    }

    public ByteString metadata() {
        return this.metadata;
    }

    public Source newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }
}
