package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http2Stream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    long bytesLeftInWriteWindow;
    final Http2Connection connection;
    ErrorCode errorCode = null;
    private boolean hasResponseHeaders;
    final int id;
    final StreamTimeout readTimeout = new StreamTimeout();
    private final List<Header> requestHeaders;
    private List<Header> responseHeaders;
    final FramingSink sink;
    private final FramingSource source;
    long unacknowledgedBytesRead = 0;
    final StreamTimeout writeTimeout = new StreamTimeout();

    final class FramingSink implements Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long EMIT_BUFFER_SIZE = 16384;
        boolean closed;
        boolean finished;
        private final Buffer sendBuffer = new Buffer();

        private void emitFrame(boolean r12) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:39:0x008c in {14, 24, 25, 28, 31, 35, 38} preds:[]
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
            r11 = this;
            r0 = okhttp3.internal.http2.Http2Stream.this;
            monitor-enter(r0);
            r1 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0089 }
            r1 = r1.writeTimeout;	 Catch:{ all -> 0x0089 }
            r1.enter();	 Catch:{ all -> 0x0089 }
        L_0x000a:
            r1 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0080 }
            r1 = r1.bytesLeftInWriteWindow;	 Catch:{ all -> 0x0080 }
            r3 = 0;	 Catch:{ all -> 0x0080 }
            r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));	 Catch:{ all -> 0x0080 }
            if (r5 > 0) goto L_0x0028;	 Catch:{ all -> 0x0080 }
        L_0x0014:
            r1 = r11.finished;	 Catch:{ all -> 0x0080 }
            if (r1 != 0) goto L_0x0028;	 Catch:{ all -> 0x0080 }
        L_0x0018:
            r1 = r11.closed;	 Catch:{ all -> 0x0080 }
            if (r1 != 0) goto L_0x0028;	 Catch:{ all -> 0x0080 }
        L_0x001c:
            r1 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0080 }
            r1 = r1.errorCode;	 Catch:{ all -> 0x0080 }
            if (r1 != 0) goto L_0x0028;	 Catch:{ all -> 0x0080 }
        L_0x0022:
            r1 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0080 }
            r1.waitForIo();	 Catch:{ all -> 0x0080 }
            goto L_0x000a;
        L_0x0028:
            r1 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0089 }
            r1 = r1.writeTimeout;	 Catch:{ all -> 0x0089 }
            r1.exitAndThrowIfTimedOut();	 Catch:{ all -> 0x0089 }
            r1 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0089 }
            r1.checkOutNotClosed();	 Catch:{ all -> 0x0089 }
            r1 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0089 }
            r1 = r1.bytesLeftInWriteWindow;	 Catch:{ all -> 0x0089 }
            r3 = r11.sendBuffer;	 Catch:{ all -> 0x0089 }
            r3 = r3.size();	 Catch:{ all -> 0x0089 }
            r9 = java.lang.Math.min(r1, r3);	 Catch:{ all -> 0x0089 }
            r1 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0089 }
            r2 = r1.bytesLeftInWriteWindow;	 Catch:{ all -> 0x0089 }
            r2 = r2 - r9;	 Catch:{ all -> 0x0089 }
            r1.bytesLeftInWriteWindow = r2;	 Catch:{ all -> 0x0089 }
            monitor-exit(r0);	 Catch:{ all -> 0x0089 }
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r0 = r0.writeTimeout;
            r0.enter();
            r0 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0077 }
            r5 = r0.connection;	 Catch:{ all -> 0x0077 }
            r0 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0077 }
            r6 = r0.id;	 Catch:{ all -> 0x0077 }
            if (r12 == 0) goto L_0x0068;	 Catch:{ all -> 0x0077 }
        L_0x005b:
            r12 = r11.sendBuffer;	 Catch:{ all -> 0x0077 }
            r0 = r12.size();	 Catch:{ all -> 0x0077 }
            r12 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));	 Catch:{ all -> 0x0077 }
            if (r12 != 0) goto L_0x0068;	 Catch:{ all -> 0x0077 }
        L_0x0065:
            r12 = 1;	 Catch:{ all -> 0x0077 }
            r7 = 1;	 Catch:{ all -> 0x0077 }
            goto L_0x006a;	 Catch:{ all -> 0x0077 }
        L_0x0068:
            r12 = 0;	 Catch:{ all -> 0x0077 }
            r7 = 0;	 Catch:{ all -> 0x0077 }
        L_0x006a:
            r8 = r11.sendBuffer;	 Catch:{ all -> 0x0077 }
            r5.writeData(r6, r7, r8, r9);	 Catch:{ all -> 0x0077 }
            r12 = okhttp3.internal.http2.Http2Stream.this;
            r12 = r12.writeTimeout;
            r12.exitAndThrowIfTimedOut();
            return;
        L_0x0077:
            r12 = move-exception;
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r0 = r0.writeTimeout;
            r0.exitAndThrowIfTimedOut();
            throw r12;
        L_0x0080:
            r12 = move-exception;
            r1 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0089 }
            r1 = r1.writeTimeout;	 Catch:{ all -> 0x0089 }
            r1.exitAndThrowIfTimedOut();	 Catch:{ all -> 0x0089 }
            throw r12;	 Catch:{ all -> 0x0089 }
        L_0x0089:
            r12 = move-exception;	 Catch:{ all -> 0x0089 }
            monitor-exit(r0);	 Catch:{ all -> 0x0089 }
            throw r12;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSink.emitFrame(boolean):void");
        }

        public void close() throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:31:0x0055 in {6, 14, 15, 22, 26, 30} preds:[]
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
            r8 = this;
            r0 = okhttp3.internal.http2.Http2Stream.this;
            monitor-enter(r0);
            r1 = r8.closed;	 Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0009;	 Catch:{ all -> 0x0052 }
        L_0x0007:
            monitor-exit(r0);	 Catch:{ all -> 0x0052 }
            return;	 Catch:{ all -> 0x0052 }
        L_0x0009:
            monitor-exit(r0);	 Catch:{ all -> 0x0052 }
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r0 = r0.sink;
            r0 = r0.finished;
            r1 = 1;
            if (r0 != 0) goto L_0x003c;
        L_0x0013:
            r0 = r8.sendBuffer;
            r2 = r0.size();
            r4 = 0;
            r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x002d;
        L_0x001f:
            r0 = r8.sendBuffer;
            r2 = r0.size();
            r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x003c;
        L_0x0029:
            r8.emitFrame(r1);
            goto L_0x001f;
        L_0x002d:
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r2 = r0.connection;
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r3 = r0.id;
            r4 = 1;
            r5 = 0;
            r6 = 0;
            r2.writeData(r3, r4, r5, r6);
        L_0x003c:
            r2 = okhttp3.internal.http2.Http2Stream.this;
            monitor-enter(r2);
            r8.closed = r1;	 Catch:{ all -> 0x004f }
            monitor-exit(r2);	 Catch:{ all -> 0x004f }
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r0 = r0.connection;
            r0.flush();
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r0.cancelStreamIfNecessary();
            return;
        L_0x004f:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x004f }
            throw r0;
        L_0x0052:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0052 }
            throw r1;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSink.close():void");
        }

        public void flush() throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:13:0x0025 in {7, 8, 12} preds:[]
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
            r5 = this;
            r0 = okhttp3.internal.http2.Http2Stream.this;
            monitor-enter(r0);
            r1 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0022 }
            r1.checkOutNotClosed();	 Catch:{ all -> 0x0022 }
            monitor-exit(r0);	 Catch:{ all -> 0x0022 }
        L_0x0009:
            r0 = r5.sendBuffer;
            r0 = r0.size();
            r2 = 0;
            r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r4 <= 0) goto L_0x0021;
        L_0x0015:
            r0 = 0;
            r5.emitFrame(r0);
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r0 = r0.connection;
            r0.flush();
            goto L_0x0009;
        L_0x0021:
            return;
        L_0x0022:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0022 }
            throw r1;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSink.flush():void");
        }

        static {
            Class cls = Http2Stream.class;
        }

        FramingSink() {
        }

        public void write(Buffer buffer, long j) throws IOException {
            this.sendBuffer.write(buffer, j);
            while (this.sendBuffer.size() >= 16384) {
                emitFrame(null);
            }
        }

        public Timeout timeout() {
            return Http2Stream.this.writeTimeout;
        }
    }

    private final class FramingSource implements Source {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        boolean closed;
        boolean finished;
        private final long maxByteCount;
        private final Buffer readBuffer = new Buffer();
        private final Buffer receiveBuffer = new Buffer();

        private void waitUntilReadable() throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:17:0x0038 in {11, 13, 16} preds:[]
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
            r5 = this;
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r0 = r0.readTimeout;
            r0.enter();
        L_0x0007:
            r0 = r5.readBuffer;	 Catch:{ all -> 0x002f }
            r0 = r0.size();	 Catch:{ all -> 0x002f }
            r2 = 0;	 Catch:{ all -> 0x002f }
            r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));	 Catch:{ all -> 0x002f }
            if (r4 != 0) goto L_0x0027;	 Catch:{ all -> 0x002f }
        L_0x0013:
            r0 = r5.finished;	 Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x0027;	 Catch:{ all -> 0x002f }
        L_0x0017:
            r0 = r5.closed;	 Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x0027;	 Catch:{ all -> 0x002f }
        L_0x001b:
            r0 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x002f }
            r0 = r0.errorCode;	 Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x0027;	 Catch:{ all -> 0x002f }
        L_0x0021:
            r0 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x002f }
            r0.waitForIo();	 Catch:{ all -> 0x002f }
            goto L_0x0007;
        L_0x0027:
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r0 = r0.readTimeout;
            r0.exitAndThrowIfTimedOut();
            return;
        L_0x002f:
            r0 = move-exception;
            r1 = okhttp3.internal.http2.Http2Stream.this;
            r1 = r1.readTimeout;
            r1.exitAndThrowIfTimedOut();
            throw r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSource.waitUntilReadable():void");
        }

        static {
            Class cls = Http2Stream.class;
        }

        FramingSource(long j) {
            this.maxByteCount = j;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(okio.Buffer r8, long r9) throws java.io.IOException {
            /*
            r7 = this;
            r0 = 0;
            r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));
            if (r2 < 0) goto L_0x009e;
        L_0x0006:
            r2 = okhttp3.internal.http2.Http2Stream.this;
            monitor-enter(r2);
            r7.waitUntilReadable();	 Catch:{ all -> 0x009b }
            r7.checkNotClosed();	 Catch:{ all -> 0x009b }
            r3 = r7.readBuffer;	 Catch:{ all -> 0x009b }
            r3 = r3.size();	 Catch:{ all -> 0x009b }
            r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1));
            if (r5 != 0) goto L_0x001d;
        L_0x0019:
            r8 = -1;
            monitor-exit(r2);	 Catch:{ all -> 0x009b }
            return r8;
        L_0x001d:
            r3 = r7.readBuffer;	 Catch:{ all -> 0x009b }
            r4 = r7.readBuffer;	 Catch:{ all -> 0x009b }
            r4 = r4.size();	 Catch:{ all -> 0x009b }
            r9 = java.lang.Math.min(r9, r4);	 Catch:{ all -> 0x009b }
            r8 = r3.read(r8, r9);	 Catch:{ all -> 0x009b }
            r10 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x009b }
            r3 = r10.unacknowledgedBytesRead;	 Catch:{ all -> 0x009b }
            r3 = r3 + r8;
            r10.unacknowledgedBytesRead = r3;	 Catch:{ all -> 0x009b }
            r10 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x009b }
            r3 = r10.unacknowledgedBytesRead;	 Catch:{ all -> 0x009b }
            r10 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x009b }
            r10 = r10.connection;	 Catch:{ all -> 0x009b }
            r10 = r10.okHttpSettings;	 Catch:{ all -> 0x009b }
            r10 = r10.getInitialWindowSize();	 Catch:{ all -> 0x009b }
            r10 = r10 / 2;
            r5 = (long) r10;	 Catch:{ all -> 0x009b }
            r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
            if (r10 < 0) goto L_0x005c;
        L_0x0049:
            r10 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x009b }
            r10 = r10.connection;	 Catch:{ all -> 0x009b }
            r3 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x009b }
            r3 = r3.id;	 Catch:{ all -> 0x009b }
            r4 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x009b }
            r4 = r4.unacknowledgedBytesRead;	 Catch:{ all -> 0x009b }
            r10.writeWindowUpdateLater(r3, r4);	 Catch:{ all -> 0x009b }
            r10 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x009b }
            r10.unacknowledgedBytesRead = r0;	 Catch:{ all -> 0x009b }
        L_0x005c:
            monitor-exit(r2);	 Catch:{ all -> 0x009b }
            r10 = okhttp3.internal.http2.Http2Stream.this;
            r10 = r10.connection;
            monitor-enter(r10);
            r2 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0098 }
            r2 = r2.connection;	 Catch:{ all -> 0x0098 }
            r3 = r2.unacknowledgedBytesRead;	 Catch:{ all -> 0x0098 }
            r3 = r3 + r8;
            r2.unacknowledgedBytesRead = r3;	 Catch:{ all -> 0x0098 }
            r2 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0098 }
            r2 = r2.connection;	 Catch:{ all -> 0x0098 }
            r2 = r2.unacknowledgedBytesRead;	 Catch:{ all -> 0x0098 }
            r4 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0098 }
            r4 = r4.connection;	 Catch:{ all -> 0x0098 }
            r4 = r4.okHttpSettings;	 Catch:{ all -> 0x0098 }
            r4 = r4.getInitialWindowSize();	 Catch:{ all -> 0x0098 }
            r4 = r4 / 2;
            r4 = (long) r4;	 Catch:{ all -> 0x0098 }
            r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r6 < 0) goto L_0x0096;
        L_0x0082:
            r2 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0098 }
            r2 = r2.connection;	 Catch:{ all -> 0x0098 }
            r3 = 0;
            r4 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0098 }
            r4 = r4.connection;	 Catch:{ all -> 0x0098 }
            r4 = r4.unacknowledgedBytesRead;	 Catch:{ all -> 0x0098 }
            r2.writeWindowUpdateLater(r3, r4);	 Catch:{ all -> 0x0098 }
            r2 = okhttp3.internal.http2.Http2Stream.this;	 Catch:{ all -> 0x0098 }
            r2 = r2.connection;	 Catch:{ all -> 0x0098 }
            r2.unacknowledgedBytesRead = r0;	 Catch:{ all -> 0x0098 }
        L_0x0096:
            monitor-exit(r10);	 Catch:{ all -> 0x0098 }
            return r8;
        L_0x0098:
            r8 = move-exception;
            monitor-exit(r10);	 Catch:{ all -> 0x0098 }
            throw r8;
        L_0x009b:
            r8 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x009b }
            throw r8;
        L_0x009e:
            r8 = new java.lang.IllegalArgumentException;
            r0 = new java.lang.StringBuilder;
            r0.<init>();
            r1 = "byteCount < 0: ";
            r0.append(r1);
            r0.append(r9);
            r9 = r0.toString();
            r8.<init>(r9);
            throw r8;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSource.read(okio.Buffer, long):long");
        }

        void receive(BufferedSource bufferedSource, long j) throws IOException {
            while (j > 0) {
                synchronized (Http2Stream.this) {
                    boolean z = this.finished;
                    Object obj = 1;
                    Object obj2 = this.readBuffer.size() + j > this.maxByteCount ? 1 : null;
                }
                if (obj2 != null) {
                    bufferedSource.skip(j);
                    Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                } else if (z) {
                    bufferedSource.skip(j);
                    return;
                } else {
                    long read = bufferedSource.read(this.receiveBuffer, j);
                    if (read != -1) {
                        j -= read;
                        synchronized (Http2Stream.this) {
                            if (this.readBuffer.size() != 0) {
                                obj = null;
                            }
                            this.readBuffer.writeAll(this.receiveBuffer);
                            if (obj != null) {
                                Http2Stream.this.notifyAll();
                            }
                        }
                    } else {
                        throw new EOFException();
                    }
                }
            }
        }

        public Timeout timeout() {
            return Http2Stream.this.readTimeout;
        }

        public void close() throws IOException {
            synchronized (Http2Stream.this) {
                this.closed = true;
                this.readBuffer.clear();
                Http2Stream.this.notifyAll();
            }
            Http2Stream.this.cancelStreamIfNecessary();
        }

        private void checkNotClosed() throws IOException {
            if (this.closed) {
                throw new IOException("stream closed");
            } else if (Http2Stream.this.errorCode != null) {
                throw new StreamResetException(Http2Stream.this.errorCode);
            }
        }
    }

    class StreamTimeout extends AsyncTimeout {
        StreamTimeout() {
        }

        protected void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
        }

        protected IOException newTimeoutException(IOException iOException) {
            IOException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }
    }

    public synchronized java.util.List<okhttp3.internal.http2.Header> takeResponseHeaders() throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:29:0x0040 in {11, 17, 20, 23, 25, 28} preds:[]
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
        r2 = this;
        monitor-enter(r2);
        r0 = r2.isLocallyInitiated();	 Catch:{ all -> 0x003d }
        if (r0 == 0) goto L_0x0035;	 Catch:{ all -> 0x003d }
    L_0x0007:
        r0 = r2.readTimeout;	 Catch:{ all -> 0x003d }
        r0.enter();	 Catch:{ all -> 0x003d }
    L_0x000c:
        r0 = r2.responseHeaders;	 Catch:{ all -> 0x002e }
        if (r0 != 0) goto L_0x0018;	 Catch:{ all -> 0x002e }
    L_0x0010:
        r0 = r2.errorCode;	 Catch:{ all -> 0x002e }
        if (r0 != 0) goto L_0x0018;	 Catch:{ all -> 0x002e }
    L_0x0014:
        r2.waitForIo();	 Catch:{ all -> 0x002e }
        goto L_0x000c;
    L_0x0018:
        r0 = r2.readTimeout;	 Catch:{ all -> 0x003d }
        r0.exitAndThrowIfTimedOut();	 Catch:{ all -> 0x003d }
        r0 = r2.responseHeaders;	 Catch:{ all -> 0x003d }
        if (r0 == 0) goto L_0x0026;	 Catch:{ all -> 0x003d }
    L_0x0021:
        r1 = 0;	 Catch:{ all -> 0x003d }
        r2.responseHeaders = r1;	 Catch:{ all -> 0x003d }
        monitor-exit(r2);
        return r0;
    L_0x0026:
        r0 = new okhttp3.internal.http2.StreamResetException;	 Catch:{ all -> 0x003d }
        r1 = r2.errorCode;	 Catch:{ all -> 0x003d }
        r0.<init>(r1);	 Catch:{ all -> 0x003d }
        throw r0;	 Catch:{ all -> 0x003d }
    L_0x002e:
        r0 = move-exception;	 Catch:{ all -> 0x003d }
        r1 = r2.readTimeout;	 Catch:{ all -> 0x003d }
        r1.exitAndThrowIfTimedOut();	 Catch:{ all -> 0x003d }
        throw r0;	 Catch:{ all -> 0x003d }
    L_0x0035:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x003d }
        r1 = "servers cannot read response headers";	 Catch:{ all -> 0x003d }
        r0.<init>(r1);	 Catch:{ all -> 0x003d }
        throw r0;	 Catch:{ all -> 0x003d }
    L_0x003d:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.takeResponseHeaders():java.util.List<okhttp3.internal.http2.Header>");
    }

    Http2Stream(int i, Http2Connection http2Connection, boolean z, boolean z2, List<Header> list) {
        if (http2Connection == null) {
            throw new NullPointerException("connection == null");
        } else if (list != null) {
            this.id = i;
            this.connection = http2Connection;
            this.bytesLeftInWriteWindow = (long) http2Connection.peerSettings.getInitialWindowSize();
            this.source = new FramingSource((long) http2Connection.okHttpSettings.getInitialWindowSize());
            this.sink = new FramingSink();
            this.source.finished = z2;
            this.sink.finished = z;
            this.requestHeaders = list;
        } else {
            throw new NullPointerException("requestHeaders == null");
        }
    }

    public int getId() {
        return this.id;
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        if ((this.source.finished || this.source.closed) && ((this.sink.finished || this.sink.closed) && this.hasResponseHeaders)) {
            return false;
        }
        return true;
    }

    public boolean isLocallyInitiated() {
        if (this.connection.client == ((this.id & 1) == 1)) {
            return true;
        }
        return false;
    }

    public Http2Connection getConnection() {
        return this.connection;
    }

    public List<Header> getRequestHeaders() {
        return this.requestHeaders;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public void sendResponseHeaders(List<Header> list, boolean z) throws IOException {
        if (list != null) {
            boolean z2 = false;
            synchronized (this) {
                this.hasResponseHeaders = true;
                if (!z) {
                    this.sink.finished = true;
                    z2 = true;
                }
            }
            this.connection.writeSynReply(this.id, z2, list);
            if (z2) {
                this.connection.flush();
                return;
            }
            return;
        }
        throw new NullPointerException("responseHeaders == null");
    }

    public Timeout readTimeout() {
        return this.readTimeout;
    }

    public Timeout writeTimeout() {
        return this.writeTimeout;
    }

    public Source getSource() {
        return this.source;
    }

    public Sink getSink() {
        synchronized (this) {
            if (!this.hasResponseHeaders) {
                if (!isLocallyInitiated()) {
                    throw new IllegalStateException("reply before requesting the sink");
                }
            }
        }
        return this.sink;
    }

    public void close(ErrorCode errorCode) throws IOException {
        if (closeInternal(errorCode)) {
            this.connection.writeSynReset(this.id, errorCode);
        }
    }

    public void closeLater(ErrorCode errorCode) {
        if (closeInternal(errorCode)) {
            this.connection.writeSynResetLater(this.id, errorCode);
        }
    }

    private boolean closeInternal(ErrorCode errorCode) {
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            } else if (this.source.finished && this.sink.finished) {
                return false;
            } else {
                this.errorCode = errorCode;
                notifyAll();
                this.connection.removeStream(this.id);
                return true;
            }
        }
    }

    void receiveHeaders(List<Header> list) {
        synchronized (this) {
            boolean z = true;
            this.hasResponseHeaders = true;
            if (this.responseHeaders == null) {
                this.responseHeaders = list;
                z = isOpen();
                notifyAll();
            } else {
                List arrayList = new ArrayList();
                arrayList.addAll(this.responseHeaders);
                arrayList.add(null);
                arrayList.addAll(list);
                this.responseHeaders = arrayList;
            }
        }
        if (!z) {
            this.connection.removeStream(this.id);
        }
    }

    void receiveData(BufferedSource bufferedSource, int i) throws IOException {
        this.source.receive(bufferedSource, (long) i);
    }

    void receiveFin() {
        synchronized (this) {
            this.source.finished = true;
            boolean isOpen = isOpen();
            notifyAll();
        }
        if (!isOpen) {
            this.connection.removeStream(this.id);
        }
    }

    synchronized void receiveRstStream(ErrorCode errorCode) {
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            notifyAll();
        }
    }

    void cancelStreamIfNecessary() throws IOException {
        synchronized (this) {
            Object obj = (!this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed)) ? 1 : null;
            boolean isOpen = isOpen();
        }
        if (obj != null) {
            close(ErrorCode.CANCEL);
        } else if (!isOpen) {
            this.connection.removeStream(this.id);
        }
    }

    void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    void checkOutNotClosed() throws IOException {
        if (this.sink.closed) {
            throw new IOException("stream closed");
        } else if (this.sink.finished) {
            throw new IOException("stream finished");
        } else {
            ErrorCode errorCode = this.errorCode;
            if (errorCode != null) {
                throw new StreamResetException(errorCode);
            }
        }
    }

    void waitForIo() throws java.io.InterruptedIOException {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r1 = this;
        r1.wait();	 Catch:{ InterruptedException -> 0x0004 }
        return;
    L_0x0004:
        r0 = new java.io.InterruptedIOException;
        r0.<init>();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.waitForIo():void");
    }
}
