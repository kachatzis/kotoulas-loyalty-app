package okhttp3.internal.ws;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

final class WebSocketWriter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    boolean activeWriter;
    final Buffer buffer = new Buffer();
    final FrameSink frameSink = new FrameSink();
    final boolean isClient;
    final byte[] maskBuffer;
    final byte[] maskKey;
    final Random random;
    final BufferedSink sink;
    boolean writerClosed;

    final class FrameSink implements Sink {
        boolean closed;
        long contentLength;
        int formatOpcode;
        boolean isFirstFrame;

        FrameSink() {
        }

        public void write(Buffer buffer, long j) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.buffer.write(buffer, j);
            buffer = (this.isFirstFrame == null || this.contentLength == -1 || WebSocketWriter.this.buffer.size() <= this.contentLength - PlaybackStateCompat.ACTION_PLAY_FROM_URI) ? null : true;
            long completeSegmentByteCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
            if (completeSegmentByteCount > 0 && buffer == null) {
                synchronized (WebSocketWriter.this) {
                    WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, completeSegmentByteCount, this.isFirstFrame, false);
                }
                this.isFirstFrame = 0;
            }
        }

        public void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, false);
            }
            this.isFirstFrame = false;
        }

        public Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        public void close() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, true);
            }
            this.closed = true;
            WebSocketWriter.this.activeWriter = false;
        }
    }

    void writeMessageFrameSynchronized(int r10, long r11, boolean r13, boolean r14) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:34:0x00a3 in {4, 5, 7, 10, 11, 14, 17, 18, 26, 28, 29, 31, 33} preds:[]
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
        r0 = r9.writerClosed;
        if (r0 != 0) goto L_0x009b;
    L_0x0004:
        r0 = 0;
        if (r13 == 0) goto L_0x0008;
    L_0x0007:
        goto L_0x0009;
    L_0x0008:
        r10 = 0;
    L_0x0009:
        if (r14 == 0) goto L_0x000d;
    L_0x000b:
        r10 = r10 | 128;
    L_0x000d:
        r13 = r9.sink;
        r13.writeByte(r10);
        r10 = r9.isClient;
        if (r10 == 0) goto L_0x0019;
    L_0x0016:
        r10 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        goto L_0x001a;
    L_0x0019:
        r10 = 0;
    L_0x001a:
        r13 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r1 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1));
        if (r1 > 0) goto L_0x0028;
    L_0x0020:
        r13 = (int) r11;
        r10 = r10 | r13;
        r13 = r9.sink;
        r13.writeByte(r10);
        goto L_0x0049;
    L_0x0028:
        r13 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r1 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1));
        if (r1 > 0) goto L_0x003d;
    L_0x002f:
        r10 = r10 | 126;
        r13 = r9.sink;
        r13.writeByte(r10);
        r10 = r9.sink;
        r13 = (int) r11;
        r10.writeShort(r13);
        goto L_0x0049;
    L_0x003d:
        r10 = r10 | 127;
        r13 = r9.sink;
        r13.writeByte(r10);
        r10 = r9.sink;
        r10.writeLong(r11);
    L_0x0049:
        r10 = r9.isClient;
        if (r10 == 0) goto L_0x008e;
    L_0x004d:
        r10 = r9.random;
        r13 = r9.maskKey;
        r10.nextBytes(r13);
        r10 = r9.sink;
        r13 = r9.maskKey;
        r10.write(r13);
        r13 = 0;
    L_0x005d:
        r10 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1));
        if (r10 >= 0) goto L_0x0095;
    L_0x0061:
        r10 = r9.maskBuffer;
        r10 = r10.length;
        r1 = (long) r10;
        r1 = java.lang.Math.min(r11, r1);
        r10 = (int) r1;
        r1 = r9.buffer;
        r2 = r9.maskBuffer;
        r10 = r1.read(r2, r0, r10);
        r1 = -1;
        if (r10 == r1) goto L_0x0088;
    L_0x0075:
        r1 = r9.maskBuffer;
        r7 = (long) r10;
        r4 = r9.maskKey;
        r2 = r7;
        r5 = r13;
        okhttp3.internal.ws.WebSocketProtocol.toggleMask(r1, r2, r4, r5);
        r1 = r9.sink;
        r2 = r9.maskBuffer;
        r1.write(r2, r0, r10);
        r13 = r13 + r7;
        goto L_0x005d;
    L_0x0088:
        r10 = new java.lang.AssertionError;
        r10.<init>();
        throw r10;
    L_0x008e:
        r10 = r9.sink;
        r13 = r9.buffer;
        r10.write(r13, r11);
    L_0x0095:
        r10 = r9.sink;
        r10.emit();
        return;
    L_0x009b:
        r10 = new java.io.IOException;
        r11 = "closed";
        r10.<init>(r11);
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.WebSocketWriter.writeMessageFrameSynchronized(int, long, boolean, boolean):void");
    }

    WebSocketWriter(boolean z, BufferedSink bufferedSink, Random random) {
        if (bufferedSink == null) {
            throw new NullPointerException("sink == null");
        } else if (random != null) {
            this.isClient = z;
            this.sink = bufferedSink;
            this.random = random;
            bufferedSink = null;
            this.maskKey = z ? new byte[4] : null;
            if (z) {
                bufferedSink = new byte[true];
            }
            this.maskBuffer = bufferedSink;
        } else {
            throw new NullPointerException("random == null");
        }
    }

    void writePing(ByteString byteString) throws IOException {
        synchronized (this) {
            writeControlFrameSynchronized(9, byteString);
        }
    }

    void writePong(ByteString byteString) throws IOException {
        synchronized (this) {
            writeControlFrameSynchronized(10, byteString);
        }
    }

    void writeClose(int i, ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.EMPTY;
        if (!(i == 0 && byteString == null)) {
            if (i != 0) {
                WebSocketProtocol.validateCloseCode(i);
            }
            Buffer buffer = new Buffer();
            buffer.writeShort(i);
            if (byteString != null) {
                buffer.write(byteString);
            }
            byteString2 = buffer.readByteString();
        }
        synchronized (this) {
            try {
                writeControlFrameSynchronized(8, byteString2);
                this.writerClosed = true;
            } catch (Throwable th) {
                this.writerClosed = true;
            }
        }
    }

    private void writeControlFrameSynchronized(int i, ByteString byteString) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int size = byteString.size();
        if (((long) size) <= 125) {
            this.sink.writeByte(i | 128);
            if (this.isClient != 0) {
                this.sink.writeByte(size | 128);
                this.random.nextBytes(this.maskKey);
                this.sink.write(this.maskKey);
                byte[] toByteArray = byteString.toByteArray();
                WebSocketProtocol.toggleMask(toByteArray, (long) toByteArray.length, this.maskKey, 0);
                this.sink.write(toByteArray);
            } else {
                this.sink.writeByte(size);
                this.sink.write(byteString);
            }
            this.sink.flush();
            return;
        }
        throw new IllegalArgumentException("Payload size must be less than or equal to 125");
    }

    Sink newMessageSink(int i, long j) {
        if (this.activeWriter) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.activeWriter = true;
        Sink sink = this.frameSink;
        sink.formatOpcode = i;
        sink.contentLength = j;
        sink.isFirstFrame = true;
        sink.closed = false;
        return sink;
    }
}
