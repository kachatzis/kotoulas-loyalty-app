package okhttp3;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.connection.StreamAllocation.StreamAllocationReference;
import okhttp3.internal.platform.Platform;

public final class ConnectionPool {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Executor executor = new ThreadPoolExecutor(0, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
    private final Runnable cleanupRunnable;
    boolean cleanupRunning;
    private final Deque<RealConnection> connections;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;
    final RouteDatabase routeDatabase;

    /* renamed from: okhttp3.ConnectionPool$1 */
    class C04331 implements Runnable {
        public void run() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:17:0x002f in {2, 9, 14, 16} preds:[]
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
            r6 = this;
        L_0x0000:
            r0 = okhttp3.ConnectionPool.this;
            r1 = java.lang.System.nanoTime();
            r0 = r0.cleanup(r1);
            r2 = -1;
            r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r4 != 0) goto L_0x0011;
        L_0x0010:
            return;
        L_0x0011:
            r2 = 0;
            r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r4 <= 0) goto L_0x0000;
        L_0x0017:
            r2 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
            r4 = r0 / r2;
            r2 = r2 * r4;
            r0 = r0 - r2;
            r2 = okhttp3.ConnectionPool.this;
            monitor-enter(r2);
            r3 = okhttp3.ConnectionPool.this;	 Catch:{ InterruptedException -> 0x002b }
            r1 = (int) r0;	 Catch:{ InterruptedException -> 0x002b }
            r3.wait(r4, r1);	 Catch:{ InterruptedException -> 0x002b }
            goto L_0x002b;
        L_0x0029:
            r0 = move-exception;
            goto L_0x002d;
        L_0x002b:
            monitor-exit(r2);	 Catch:{ all -> 0x0029 }
            goto L_0x0000;	 Catch:{ all -> 0x0029 }
        L_0x002d:
            monitor-exit(r2);	 Catch:{ all -> 0x0029 }
            throw r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.ConnectionPool.1.run():void");
        }

        C04331() {
        }
    }

    long cleanup(long r12) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:35:0x0061 in {7, 10, 15, 19, 23, 26, 30, 34} preds:[]
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
        r11 = this;
        monitor-enter(r11);
        r0 = r11.connections;	 Catch:{ all -> 0x005e }
        r0 = r0.iterator();	 Catch:{ all -> 0x005e }
        r1 = 0;	 Catch:{ all -> 0x005e }
        r2 = 0;	 Catch:{ all -> 0x005e }
        r3 = -9223372036854775808;	 Catch:{ all -> 0x005e }
        r5 = r2;	 Catch:{ all -> 0x005e }
        r2 = 0;	 Catch:{ all -> 0x005e }
        r6 = 0;	 Catch:{ all -> 0x005e }
    L_0x000e:
        r7 = r0.hasNext();	 Catch:{ all -> 0x005e }
        if (r7 == 0) goto L_0x0030;	 Catch:{ all -> 0x005e }
    L_0x0014:
        r7 = r0.next();	 Catch:{ all -> 0x005e }
        r7 = (okhttp3.internal.connection.RealConnection) r7;	 Catch:{ all -> 0x005e }
        r8 = r11.pruneAndGetAllocationCount(r7, r12);	 Catch:{ all -> 0x005e }
        if (r8 <= 0) goto L_0x0023;	 Catch:{ all -> 0x005e }
    L_0x0020:
        r6 = r6 + 1;	 Catch:{ all -> 0x005e }
        goto L_0x000e;	 Catch:{ all -> 0x005e }
    L_0x0023:
        r2 = r2 + 1;	 Catch:{ all -> 0x005e }
        r8 = r7.idleAtNanos;	 Catch:{ all -> 0x005e }
        r8 = r12 - r8;	 Catch:{ all -> 0x005e }
        r10 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1));	 Catch:{ all -> 0x005e }
        if (r10 <= 0) goto L_0x000e;	 Catch:{ all -> 0x005e }
    L_0x002d:
        r5 = r7;	 Catch:{ all -> 0x005e }
        r3 = r8;	 Catch:{ all -> 0x005e }
        goto L_0x000e;	 Catch:{ all -> 0x005e }
    L_0x0030:
        r12 = r11.keepAliveDurationNs;	 Catch:{ all -> 0x005e }
        r0 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1));	 Catch:{ all -> 0x005e }
        if (r0 >= 0) goto L_0x004e;	 Catch:{ all -> 0x005e }
    L_0x0036:
        r12 = r11.maxIdleConnections;	 Catch:{ all -> 0x005e }
        if (r2 <= r12) goto L_0x003b;	 Catch:{ all -> 0x005e }
    L_0x003a:
        goto L_0x004e;	 Catch:{ all -> 0x005e }
    L_0x003b:
        if (r2 <= 0) goto L_0x0042;	 Catch:{ all -> 0x005e }
    L_0x003d:
        r12 = r11.keepAliveDurationNs;	 Catch:{ all -> 0x005e }
        r12 = r12 - r3;	 Catch:{ all -> 0x005e }
        monitor-exit(r11);	 Catch:{ all -> 0x005e }
        return r12;	 Catch:{ all -> 0x005e }
    L_0x0042:
        if (r6 <= 0) goto L_0x0048;	 Catch:{ all -> 0x005e }
    L_0x0044:
        r12 = r11.keepAliveDurationNs;	 Catch:{ all -> 0x005e }
        monitor-exit(r11);	 Catch:{ all -> 0x005e }
        return r12;	 Catch:{ all -> 0x005e }
    L_0x0048:
        r11.cleanupRunning = r1;	 Catch:{ all -> 0x005e }
        r12 = -1;	 Catch:{ all -> 0x005e }
        monitor-exit(r11);	 Catch:{ all -> 0x005e }
        return r12;	 Catch:{ all -> 0x005e }
    L_0x004e:
        r12 = r11.connections;	 Catch:{ all -> 0x005e }
        r12.remove(r5);	 Catch:{ all -> 0x005e }
        monitor-exit(r11);	 Catch:{ all -> 0x005e }
        r12 = r5.socket();
        okhttp3.internal.Util.closeQuietly(r12);
        r12 = 0;
        return r12;
    L_0x005e:
        r12 = move-exception;
        monitor-exit(r11);	 Catch:{ all -> 0x005e }
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.ConnectionPool.cleanup(long):long");
    }

    public void evictAll() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:19:0x0047 in {8, 13, 14, 18} preds:[]
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
        r0 = new java.util.ArrayList;
        r0.<init>();
        monitor-enter(r4);
        r1 = r4.connections;	 Catch:{ all -> 0x0044 }
        r1 = r1.iterator();	 Catch:{ all -> 0x0044 }
    L_0x000c:
        r2 = r1.hasNext();	 Catch:{ all -> 0x0044 }
        if (r2 == 0) goto L_0x002a;	 Catch:{ all -> 0x0044 }
    L_0x0012:
        r2 = r1.next();	 Catch:{ all -> 0x0044 }
        r2 = (okhttp3.internal.connection.RealConnection) r2;	 Catch:{ all -> 0x0044 }
        r3 = r2.allocations;	 Catch:{ all -> 0x0044 }
        r3 = r3.isEmpty();	 Catch:{ all -> 0x0044 }
        if (r3 == 0) goto L_0x000c;	 Catch:{ all -> 0x0044 }
    L_0x0020:
        r3 = 1;	 Catch:{ all -> 0x0044 }
        r2.noNewStreams = r3;	 Catch:{ all -> 0x0044 }
        r0.add(r2);	 Catch:{ all -> 0x0044 }
        r1.remove();	 Catch:{ all -> 0x0044 }
        goto L_0x000c;	 Catch:{ all -> 0x0044 }
    L_0x002a:
        monitor-exit(r4);	 Catch:{ all -> 0x0044 }
        r0 = r0.iterator();
    L_0x002f:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x0043;
    L_0x0035:
        r1 = r0.next();
        r1 = (okhttp3.internal.connection.RealConnection) r1;
        r1 = r1.socket();
        okhttp3.internal.Util.closeQuietly(r1);
        goto L_0x002f;
    L_0x0043:
        return;
    L_0x0044:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0044 }
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.ConnectionPool.evictAll():void");
    }

    public synchronized int idleConnectionCount() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:14:0x0024 in {8, 10, 13} preds:[]
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
        r3 = this;
        monitor-enter(r3);
        r0 = 0;
        r1 = r3.connections;	 Catch:{ all -> 0x0021 }
        r1 = r1.iterator();	 Catch:{ all -> 0x0021 }
    L_0x0008:
        r2 = r1.hasNext();	 Catch:{ all -> 0x0021 }
        if (r2 == 0) goto L_0x001f;	 Catch:{ all -> 0x0021 }
    L_0x000e:
        r2 = r1.next();	 Catch:{ all -> 0x0021 }
        r2 = (okhttp3.internal.connection.RealConnection) r2;	 Catch:{ all -> 0x0021 }
        r2 = r2.allocations;	 Catch:{ all -> 0x0021 }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x0021 }
        if (r2 == 0) goto L_0x0008;
    L_0x001c:
        r0 = r0 + 1;
        goto L_0x0008;
    L_0x001f:
        monitor-exit(r3);
        return r0;
    L_0x0021:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.ConnectionPool.idleConnectionCount():int");
    }

    public ConnectionPool() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public ConnectionPool(int i, long j, TimeUnit timeUnit) {
        this.cleanupRunnable = new C04331();
        this.connections = new ArrayDeque();
        this.routeDatabase = new RouteDatabase();
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = timeUnit.toNanos(j);
        if (j <= 0) {
            timeUnit = new StringBuilder();
            timeUnit.append("keepAliveDuration <= 0: ");
            timeUnit.append(j);
            throw new IllegalArgumentException(timeUnit.toString());
        }
    }

    public synchronized int connectionCount() {
        return this.connections.size();
    }

    @Nullable
    RealConnection get(Address address, StreamAllocation streamAllocation, Route route) {
        for (RealConnection realConnection : this.connections) {
            if (realConnection.isEligible(address, route)) {
                streamAllocation.acquire(realConnection);
                return realConnection;
            }
        }
        return null;
    }

    @Nullable
    Socket deduplicate(Address address, StreamAllocation streamAllocation) {
        for (RealConnection realConnection : this.connections) {
            if (realConnection.isEligible(address, null) && realConnection.isMultiplexed() && realConnection != streamAllocation.connection()) {
                return streamAllocation.releaseAndAcquire(realConnection);
            }
        }
        return null;
    }

    void put(RealConnection realConnection) {
        if (!this.cleanupRunning) {
            this.cleanupRunning = true;
            executor.execute(this.cleanupRunnable);
        }
        this.connections.add(realConnection);
    }

    boolean connectionBecameIdle(RealConnection realConnection) {
        if (!realConnection.noNewStreams) {
            if (this.maxIdleConnections != 0) {
                notifyAll();
                return null;
            }
        }
        this.connections.remove(realConnection);
        return true;
    }

    private int pruneAndGetAllocationCount(RealConnection realConnection, long j) {
        List list = realConnection.allocations;
        int i = 0;
        while (i < list.size()) {
            Reference reference = (Reference) list.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                StreamAllocationReference streamAllocationReference = (StreamAllocationReference) reference;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("A connection to ");
                stringBuilder.append(realConnection.route().address().url());
                stringBuilder.append(" was leaked. Did you forget to close a response body?");
                Platform.get().logCloseableLeak(stringBuilder.toString(), streamAllocationReference.callStackTrace);
                list.remove(i);
                realConnection.noNewStreams = true;
                if (list.isEmpty()) {
                    realConnection.idleAtNanos = j - this.keepAliveDurationNs;
                    return 0;
                }
            }
        }
        return list.size();
    }
}
