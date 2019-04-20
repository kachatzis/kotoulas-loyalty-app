package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class AsyncTimeout extends Timeout {
    private static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60);
    private static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    @Nullable
    static AsyncTimeout head;
    private boolean inQueue;
    @Nullable
    private AsyncTimeout next;
    private long timeoutAt;

    private static final class Watchdog extends Thread {
        public void run() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:22:0x001c in {7, 12, 16, 21} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r3 = this;
        L_0x0000:
            r0 = okio.AsyncTimeout.class;	 Catch:{ InterruptedException -> 0x0000 }
            monitor-enter(r0);	 Catch:{ InterruptedException -> 0x0000 }
            r1 = okio.AsyncTimeout.awaitTimeout();	 Catch:{ all -> 0x0019 }
            if (r1 != 0) goto L_0x000b;	 Catch:{ all -> 0x0019 }
        L_0x0009:
            monitor-exit(r0);	 Catch:{ all -> 0x0019 }
            goto L_0x0000;	 Catch:{ all -> 0x0019 }
        L_0x000b:
            r2 = okio.AsyncTimeout.head;	 Catch:{ all -> 0x0019 }
            if (r1 != r2) goto L_0x0014;	 Catch:{ all -> 0x0019 }
        L_0x000f:
            r1 = 0;	 Catch:{ all -> 0x0019 }
            okio.AsyncTimeout.head = r1;	 Catch:{ all -> 0x0019 }
            monitor-exit(r0);	 Catch:{ all -> 0x0019 }
            return;	 Catch:{ all -> 0x0019 }
        L_0x0014:
            monitor-exit(r0);	 Catch:{ all -> 0x0019 }
            r1.timedOut();	 Catch:{ InterruptedException -> 0x0000 }
            goto L_0x0000;
        L_0x0019:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0019 }
            throw r1;	 Catch:{ InterruptedException -> 0x0000 }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.Watchdog.run():void");
        }

        Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }
    }

    private static synchronized boolean cancelScheduledTimeout(okio.AsyncTimeout r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x001e in {10, 13, 16, 19} preds:[]
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
        r0 = okio.AsyncTimeout.class;
        monitor-enter(r0);
        r1 = head;	 Catch:{ all -> 0x001b }
    L_0x0005:
        if (r1 == 0) goto L_0x0018;	 Catch:{ all -> 0x001b }
    L_0x0007:
        r2 = r1.next;	 Catch:{ all -> 0x001b }
        if (r2 != r3) goto L_0x0015;	 Catch:{ all -> 0x001b }
    L_0x000b:
        r2 = r3.next;	 Catch:{ all -> 0x001b }
        r1.next = r2;	 Catch:{ all -> 0x001b }
        r1 = 0;	 Catch:{ all -> 0x001b }
        r3.next = r1;	 Catch:{ all -> 0x001b }
        r3 = 0;
        monitor-exit(r0);
        return r3;
    L_0x0015:
        r1 = r1.next;	 Catch:{ all -> 0x001b }
        goto L_0x0005;
    L_0x0018:
        r3 = 1;
        monitor-exit(r0);
        return r3;
    L_0x001b:
        r3 = move-exception;
        monitor-exit(r0);
        throw r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.cancelScheduledTimeout(okio.AsyncTimeout):boolean");
    }

    private static synchronized void scheduleTimeout(okio.AsyncTimeout r6, long r7, boolean r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:33:0x0071 in {5, 9, 12, 14, 20, 21, 24, 26, 29, 32} preds:[]
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
        r0 = okio.AsyncTimeout.class;
        monitor-enter(r0);
        r1 = head;	 Catch:{ all -> 0x006e }
        if (r1 != 0) goto L_0x0016;	 Catch:{ all -> 0x006e }
    L_0x0007:
        r1 = new okio.AsyncTimeout;	 Catch:{ all -> 0x006e }
        r1.<init>();	 Catch:{ all -> 0x006e }
        head = r1;	 Catch:{ all -> 0x006e }
        r1 = new okio.AsyncTimeout$Watchdog;	 Catch:{ all -> 0x006e }
        r1.<init>();	 Catch:{ all -> 0x006e }
        r1.start();	 Catch:{ all -> 0x006e }
    L_0x0016:
        r1 = java.lang.System.nanoTime();	 Catch:{ all -> 0x006e }
        r3 = 0;	 Catch:{ all -> 0x006e }
        r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1));	 Catch:{ all -> 0x006e }
        if (r5 == 0) goto L_0x002f;	 Catch:{ all -> 0x006e }
    L_0x0020:
        if (r9 == 0) goto L_0x002f;	 Catch:{ all -> 0x006e }
    L_0x0022:
        r3 = r6.deadlineNanoTime();	 Catch:{ all -> 0x006e }
        r3 = r3 - r1;	 Catch:{ all -> 0x006e }
        r7 = java.lang.Math.min(r7, r3);	 Catch:{ all -> 0x006e }
        r7 = r7 + r1;	 Catch:{ all -> 0x006e }
        r6.timeoutAt = r7;	 Catch:{ all -> 0x006e }
        goto L_0x003f;	 Catch:{ all -> 0x006e }
    L_0x002f:
        r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1));	 Catch:{ all -> 0x006e }
        if (r5 == 0) goto L_0x0037;	 Catch:{ all -> 0x006e }
    L_0x0033:
        r7 = r7 + r1;	 Catch:{ all -> 0x006e }
        r6.timeoutAt = r7;	 Catch:{ all -> 0x006e }
        goto L_0x003f;	 Catch:{ all -> 0x006e }
    L_0x0037:
        if (r9 == 0) goto L_0x0068;	 Catch:{ all -> 0x006e }
    L_0x0039:
        r7 = r6.deadlineNanoTime();	 Catch:{ all -> 0x006e }
        r6.timeoutAt = r7;	 Catch:{ all -> 0x006e }
    L_0x003f:
        r7 = r6.remainingNanos(r1);	 Catch:{ all -> 0x006e }
        r9 = head;	 Catch:{ all -> 0x006e }
    L_0x0045:
        r3 = r9.next;	 Catch:{ all -> 0x006e }
        if (r3 == 0) goto L_0x0057;	 Catch:{ all -> 0x006e }
    L_0x0049:
        r3 = r9.next;	 Catch:{ all -> 0x006e }
        r3 = r3.remainingNanos(r1);	 Catch:{ all -> 0x006e }
        r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1));	 Catch:{ all -> 0x006e }
        if (r5 >= 0) goto L_0x0054;	 Catch:{ all -> 0x006e }
    L_0x0053:
        goto L_0x0057;	 Catch:{ all -> 0x006e }
    L_0x0054:
        r9 = r9.next;	 Catch:{ all -> 0x006e }
        goto L_0x0045;	 Catch:{ all -> 0x006e }
    L_0x0057:
        r7 = r9.next;	 Catch:{ all -> 0x006e }
        r6.next = r7;	 Catch:{ all -> 0x006e }
        r9.next = r6;	 Catch:{ all -> 0x006e }
        r6 = head;	 Catch:{ all -> 0x006e }
        if (r9 != r6) goto L_0x0066;	 Catch:{ all -> 0x006e }
    L_0x0061:
        r6 = okio.AsyncTimeout.class;	 Catch:{ all -> 0x006e }
        r6.notify();	 Catch:{ all -> 0x006e }
    L_0x0066:
        monitor-exit(r0);
        return;
    L_0x0068:
        r6 = new java.lang.AssertionError;	 Catch:{ all -> 0x006e }
        r6.<init>();	 Catch:{ all -> 0x006e }
        throw r6;	 Catch:{ all -> 0x006e }
    L_0x006e:
        r6 = move-exception;
        monitor-exit(r0);
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.scheduleTimeout(okio.AsyncTimeout, long, boolean):void");
    }

    protected void timedOut() {
    }

    public final void enter() {
        if (this.inQueue) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long timeoutNanos = timeoutNanos();
        boolean hasDeadline = hasDeadline();
        if (timeoutNanos != 0 || hasDeadline) {
            this.inQueue = true;
            scheduleTimeout(this, timeoutNanos, hasDeadline);
        }
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return cancelScheduledTimeout(this);
    }

    private long remainingNanos(long j) {
        return this.timeoutAt - j;
    }

    public final Sink sink(final Sink sink) {
        return new Sink() {
            public void write(Buffer buffer, long j) throws IOException {
                Util.checkOffsetAndCount(buffer.size, 0, j);
                while (true) {
                    long j2 = 0;
                    if (j > 0) {
                        Segment segment = buffer.head;
                        while (j2 < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                            j2 += (long) (buffer.head.limit - buffer.head.pos);
                            if (j2 >= j) {
                                j2 = j;
                                break;
                            }
                            segment = segment.next;
                        }
                        AsyncTimeout.this.enter();
                        try {
                            sink.write(buffer, j2);
                            j -= j2;
                            AsyncTimeout.this.exit(true);
                        } catch (IOException e) {
                            throw AsyncTimeout.this.exit(e);
                        } catch (Throwable th) {
                            AsyncTimeout.this.exit(false);
                        }
                    } else {
                        return;
                    }
                }
            }

            public void flush() throws IOException {
                AsyncTimeout.this.enter();
                try {
                    sink.flush();
                    AsyncTimeout.this.exit(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                }
            }

            public void close() throws IOException {
                AsyncTimeout.this.enter();
                try {
                    sink.close();
                    AsyncTimeout.this.exit(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                }
            }

            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("AsyncTimeout.sink(");
                stringBuilder.append(sink);
                stringBuilder.append(")");
                return stringBuilder.toString();
            }
        };
    }

    public final Source source(final Source source) {
        return new Source() {
            public long read(Buffer buffer, long j) throws IOException {
                AsyncTimeout.this.enter();
                try {
                    buffer = source.read(buffer, j);
                    AsyncTimeout.this.exit(true);
                    return buffer;
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                }
            }

            public void close() throws IOException {
                try {
                    source.close();
                    AsyncTimeout.this.exit(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                }
            }

            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("AsyncTimeout.source(");
                stringBuilder.append(source);
                stringBuilder.append(")");
                return stringBuilder.toString();
            }
        };
    }

    final void exit(boolean z) throws IOException {
        if (!exit()) {
            return;
        }
        if (z) {
            throw newTimeoutException(false);
        }
    }

    final IOException exit(IOException iOException) throws IOException {
        if (exit()) {
            return newTimeoutException(iOException);
        }
        return iOException;
    }

    protected IOException newTimeoutException(@Nullable IOException iOException) {
        IOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    @Nullable
    static AsyncTimeout awaitTimeout() throws InterruptedException {
        AsyncTimeout asyncTimeout = head.next;
        AsyncTimeout asyncTimeout2 = null;
        if (asyncTimeout == null) {
            long nanoTime = System.nanoTime();
            AsyncTimeout.class.wait(IDLE_TIMEOUT_MILLIS);
            if (head.next == null && System.nanoTime() - nanoTime >= IDLE_TIMEOUT_NANOS) {
                asyncTimeout2 = head;
            }
            return asyncTimeout2;
        }
        nanoTime = asyncTimeout.remainingNanos(System.nanoTime());
        if (nanoTime > 0) {
            long j = nanoTime / 1000000;
            AsyncTimeout.class.wait(j, (int) (nanoTime - (1000000 * j)));
            return null;
        }
        head.next = asyncTimeout.next;
        asyncTimeout.next = null;
        return asyncTimeout;
    }
}
