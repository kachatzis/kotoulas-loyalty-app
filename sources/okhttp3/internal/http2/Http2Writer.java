package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;

final class Http2Writer implements Closeable {
    private static final Logger logger = Logger.getLogger(Http2.class.getName());
    private final boolean client;
    private boolean closed;
    private final Buffer hpackBuffer = new Buffer();
    final Writer hpackWriter = new Writer(this.hpackBuffer);
    private int maxFrameSize = 16384;
    private final BufferedSink sink;

    public synchronized void settings(okhttp3.internal.http2.Settings r5) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:27:0x0048 in {9, 11, 14, 15, 16, 17, 20, 23, 26} preds:[]
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
        monitor-enter(r4);
        r0 = r4.closed;	 Catch:{ all -> 0x0045 }
        if (r0 != 0) goto L_0x003d;	 Catch:{ all -> 0x0045 }
    L_0x0005:
        r0 = r5.size();	 Catch:{ all -> 0x0045 }
        r0 = r0 * 6;	 Catch:{ all -> 0x0045 }
        r1 = 0;	 Catch:{ all -> 0x0045 }
        r2 = 4;	 Catch:{ all -> 0x0045 }
        r4.frameHeader(r1, r0, r2, r1);	 Catch:{ all -> 0x0045 }
    L_0x0010:
        r0 = 10;	 Catch:{ all -> 0x0045 }
        if (r1 >= r0) goto L_0x0036;	 Catch:{ all -> 0x0045 }
    L_0x0014:
        r0 = r5.isSet(r1);	 Catch:{ all -> 0x0045 }
        if (r0 != 0) goto L_0x001b;	 Catch:{ all -> 0x0045 }
    L_0x001a:
        goto L_0x0033;	 Catch:{ all -> 0x0045 }
    L_0x001b:
        if (r1 != r2) goto L_0x001f;	 Catch:{ all -> 0x0045 }
    L_0x001d:
        r0 = 3;	 Catch:{ all -> 0x0045 }
        goto L_0x0025;	 Catch:{ all -> 0x0045 }
    L_0x001f:
        r0 = 7;	 Catch:{ all -> 0x0045 }
        if (r1 != r0) goto L_0x0024;	 Catch:{ all -> 0x0045 }
    L_0x0022:
        r0 = 4;	 Catch:{ all -> 0x0045 }
        goto L_0x0025;	 Catch:{ all -> 0x0045 }
    L_0x0024:
        r0 = r1;	 Catch:{ all -> 0x0045 }
    L_0x0025:
        r3 = r4.sink;	 Catch:{ all -> 0x0045 }
        r3.writeShort(r0);	 Catch:{ all -> 0x0045 }
        r0 = r4.sink;	 Catch:{ all -> 0x0045 }
        r3 = r5.get(r1);	 Catch:{ all -> 0x0045 }
        r0.writeInt(r3);	 Catch:{ all -> 0x0045 }
    L_0x0033:
        r1 = r1 + 1;	 Catch:{ all -> 0x0045 }
        goto L_0x0010;	 Catch:{ all -> 0x0045 }
    L_0x0036:
        r5 = r4.sink;	 Catch:{ all -> 0x0045 }
        r5.flush();	 Catch:{ all -> 0x0045 }
        monitor-exit(r4);
        return;
    L_0x003d:
        r5 = new java.io.IOException;	 Catch:{ all -> 0x0045 }
        r0 = "closed";	 Catch:{ all -> 0x0045 }
        r5.<init>(r0);	 Catch:{ all -> 0x0045 }
        throw r5;	 Catch:{ all -> 0x0045 }
    L_0x0045:
        r5 = move-exception;
        monitor-exit(r4);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Writer.settings(okhttp3.internal.http2.Settings):void");
    }

    Http2Writer(BufferedSink bufferedSink, boolean z) {
        this.sink = bufferedSink;
        this.client = z;
    }

    public synchronized void connectionPreface() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (this.client) {
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Util.format(">> CONNECTION %s", Http2.CONNECTION_PREFACE.hex()));
            }
            this.sink.write(Http2.CONNECTION_PREFACE.toByteArray());
            this.sink.flush();
        }
    }

    public synchronized void applyAndAckSettings(Settings settings) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.maxFrameSize = settings.getMaxFrameSize(this.maxFrameSize);
        if (settings.getHeaderTableSize() != -1) {
            this.hpackWriter.setHeaderTableSizeSetting(settings.getHeaderTableSize());
        }
        frameHeader(0, 0, (byte) 4, (byte) 1);
        this.sink.flush();
    }

    public synchronized void pushPromise(int i, int i2, List<Header> list) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.hpackWriter.writeHeaders(list);
        long size = this.hpackBuffer.size();
        list = (int) Math.min((long) (this.maxFrameSize - 4), size);
        long j = (long) list;
        frameHeader(i, list + 4, (byte) 5, size == j ? (byte) 4 : (byte) 0);
        this.sink.writeInt(i2 & ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        this.sink.write(this.hpackBuffer, j);
        if (size > j) {
            writeContinuationFrames(i, size - j);
        }
    }

    public synchronized void flush() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.sink.flush();
    }

    public synchronized void synStream(boolean z, int i, int i2, List<Header> list) throws IOException {
        if (this.closed == 0) {
            headers(z, i, list);
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void synReply(boolean z, int i, List<Header> list) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        headers(z, i, list);
    }

    public synchronized void headers(int i, List<Header> list) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        headers(false, i, list);
    }

    public synchronized void rstStream(int i, ErrorCode errorCode) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (errorCode.httpCode != -1) {
            frameHeader(i, 4, (byte) 3, (byte) 0);
            this.sink.writeInt(errorCode.httpCode);
            this.sink.flush();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int maxDataLength() {
        return this.maxFrameSize;
    }

    public synchronized void data(boolean z, int i, Buffer buffer, int i2) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        byte b = (byte) 0;
        if (z) {
            b = (byte) true;
        }
        dataFrame(i, b, buffer, i2);
    }

    void dataFrame(int i, byte b, Buffer buffer, int i2) throws IOException {
        frameHeader(i, i2, (byte) 0, b);
        if (i2 > 0) {
            this.sink.write(buffer, (long) i2);
        }
    }

    public synchronized void ping(boolean z, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        frameHeader(0, 8, (byte) 6, z);
        this.sink.writeInt(i);
        this.sink.writeInt(i2);
        this.sink.flush();
    }

    public synchronized void goAway(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (errorCode.httpCode != -1) {
            frameHeader(0, bArr.length + 8, (byte) 7, (byte) 0);
            this.sink.writeInt(i);
            this.sink.writeInt(errorCode.httpCode);
            if (bArr.length > 0) {
                this.sink.write(bArr);
            }
            this.sink.flush();
        } else {
            throw Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
        }
    }

    public synchronized void windowUpdate(int i, long j) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (j == 0 || j > 2147483647L) {
            throw Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
        } else {
            frameHeader(i, 4, (byte) 8, (byte) 0);
            this.sink.writeInt((int) j);
            this.sink.flush();
        }
    }

    public void frameHeader(int i, int i2, byte b, byte b2) throws IOException {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Http2.frameLog(false, i, i2, b, b2));
        }
        if (i2 > this.maxFrameSize) {
            throw Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", new Object[]{Integer.valueOf(this.maxFrameSize), Integer.valueOf(i2)});
        } else if ((Integer.MIN_VALUE & i) == 0) {
            writeMedium(this.sink, i2);
            this.sink.writeByte(b & 255);
            this.sink.writeByte(b2 & 255);
            this.sink.writeInt(i & (byte) -1);
        } else {
            throw Http2.illegalArgument("reserved bit set: %s", new Object[]{Integer.valueOf(i)});
        }
    }

    public synchronized void close() throws IOException {
        this.closed = true;
        this.sink.close();
    }

    private static void writeMedium(BufferedSink bufferedSink, int i) throws IOException {
        bufferedSink.writeByte((i >>> 16) & 255);
        bufferedSink.writeByte((i >>> 8) & 255);
        bufferedSink.writeByte(i & 255);
    }

    private void writeContinuationFrames(int i, long j) throws IOException {
        while (j > 0) {
            int min = (int) Math.min((long) this.maxFrameSize, j);
            long j2 = (long) min;
            j -= j2;
            frameHeader(i, min, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.sink.write(this.hpackBuffer, j2);
        }
    }

    void headers(boolean z, int i, List<Header> list) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.hpackWriter.writeHeaders(list);
        long size = this.hpackBuffer.size();
        list = (int) Math.min((long) this.maxFrameSize, size);
        long j = (long) list;
        byte b = size == j ? (byte) 4 : (byte) 0;
        if (z) {
            b = (byte) (b | 1);
        }
        frameHeader(i, list, true, b);
        this.sink.write(this.hpackBuffer, j);
        if (size > j) {
            writeContinuationFrames(i, size - j);
        }
    }
}
