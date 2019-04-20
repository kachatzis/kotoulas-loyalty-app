package okhttp3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.Util;

public final class Dispatcher {
    @Nullable
    private ExecutorService executorService;
    @Nullable
    private Runnable idleCallback;
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    private final Deque<AsyncCall> readyAsyncCalls = new ArrayDeque();
    private final Deque<AsyncCall> runningAsyncCalls = new ArrayDeque();
    private final Deque<RealCall> runningSyncCalls = new ArrayDeque();

    public synchronized void cancelAll() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x0050 in {5, 9, 14, 16, 19} preds:[]
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
        r0 = r2.readyAsyncCalls;	 Catch:{ all -> 0x004d }
        r0 = r0.iterator();	 Catch:{ all -> 0x004d }
    L_0x0007:
        r1 = r0.hasNext();	 Catch:{ all -> 0x004d }
        if (r1 == 0) goto L_0x001b;	 Catch:{ all -> 0x004d }
    L_0x000d:
        r1 = r0.next();	 Catch:{ all -> 0x004d }
        r1 = (okhttp3.RealCall.AsyncCall) r1;	 Catch:{ all -> 0x004d }
        r1 = r1.get();	 Catch:{ all -> 0x004d }
        r1.cancel();	 Catch:{ all -> 0x004d }
        goto L_0x0007;	 Catch:{ all -> 0x004d }
    L_0x001b:
        r0 = r2.runningAsyncCalls;	 Catch:{ all -> 0x004d }
        r0 = r0.iterator();	 Catch:{ all -> 0x004d }
    L_0x0021:
        r1 = r0.hasNext();	 Catch:{ all -> 0x004d }
        if (r1 == 0) goto L_0x0035;	 Catch:{ all -> 0x004d }
    L_0x0027:
        r1 = r0.next();	 Catch:{ all -> 0x004d }
        r1 = (okhttp3.RealCall.AsyncCall) r1;	 Catch:{ all -> 0x004d }
        r1 = r1.get();	 Catch:{ all -> 0x004d }
        r1.cancel();	 Catch:{ all -> 0x004d }
        goto L_0x0021;	 Catch:{ all -> 0x004d }
    L_0x0035:
        r0 = r2.runningSyncCalls;	 Catch:{ all -> 0x004d }
        r0 = r0.iterator();	 Catch:{ all -> 0x004d }
    L_0x003b:
        r1 = r0.hasNext();	 Catch:{ all -> 0x004d }
        if (r1 == 0) goto L_0x004b;	 Catch:{ all -> 0x004d }
    L_0x0041:
        r1 = r0.next();	 Catch:{ all -> 0x004d }
        r1 = (okhttp3.RealCall) r1;	 Catch:{ all -> 0x004d }
        r1.cancel();	 Catch:{ all -> 0x004d }
        goto L_0x003b;
    L_0x004b:
        monitor-exit(r2);
        return;
    L_0x004d:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Dispatcher.cancelAll():void");
    }

    public synchronized java.util.List<okhttp3.Call> queuedCalls() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:12:0x0029 in {5, 8, 11} preds:[]
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
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0026 }
        r0.<init>();	 Catch:{ all -> 0x0026 }
        r1 = r3.readyAsyncCalls;	 Catch:{ all -> 0x0026 }
        r1 = r1.iterator();	 Catch:{ all -> 0x0026 }
    L_0x000c:
        r2 = r1.hasNext();	 Catch:{ all -> 0x0026 }
        if (r2 == 0) goto L_0x0020;	 Catch:{ all -> 0x0026 }
    L_0x0012:
        r2 = r1.next();	 Catch:{ all -> 0x0026 }
        r2 = (okhttp3.RealCall.AsyncCall) r2;	 Catch:{ all -> 0x0026 }
        r2 = r2.get();	 Catch:{ all -> 0x0026 }
        r0.add(r2);	 Catch:{ all -> 0x0026 }
        goto L_0x000c;	 Catch:{ all -> 0x0026 }
    L_0x0020:
        r0 = java.util.Collections.unmodifiableList(r0);	 Catch:{ all -> 0x0026 }
        monitor-exit(r3);
        return r0;
    L_0x0026:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Dispatcher.queuedCalls():java.util.List<okhttp3.Call>");
    }

    public synchronized java.util.List<okhttp3.Call> runningCalls() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:12:0x002e in {5, 8, 11} preds:[]
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
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x002b }
        r0.<init>();	 Catch:{ all -> 0x002b }
        r1 = r3.runningSyncCalls;	 Catch:{ all -> 0x002b }
        r0.addAll(r1);	 Catch:{ all -> 0x002b }
        r1 = r3.runningAsyncCalls;	 Catch:{ all -> 0x002b }
        r1 = r1.iterator();	 Catch:{ all -> 0x002b }
    L_0x0011:
        r2 = r1.hasNext();	 Catch:{ all -> 0x002b }
        if (r2 == 0) goto L_0x0025;	 Catch:{ all -> 0x002b }
    L_0x0017:
        r2 = r1.next();	 Catch:{ all -> 0x002b }
        r2 = (okhttp3.RealCall.AsyncCall) r2;	 Catch:{ all -> 0x002b }
        r2 = r2.get();	 Catch:{ all -> 0x002b }
        r0.add(r2);	 Catch:{ all -> 0x002b }
        goto L_0x0011;	 Catch:{ all -> 0x002b }
    L_0x0025:
        r0 = java.util.Collections.unmodifiableList(r0);	 Catch:{ all -> 0x002b }
        monitor-exit(r3);
        return r0;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Dispatcher.runningCalls():java.util.List<okhttp3.Call>");
    }

    public Dispatcher(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public synchronized ExecutorService executorService() {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(0, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return this.executorService;
    }

    public synchronized void setMaxRequests(int i) {
        if (i >= 1) {
            this.maxRequests = i;
            promoteCalls();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("max < 1: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public synchronized int getMaxRequests() {
        return this.maxRequests;
    }

    public synchronized void setMaxRequestsPerHost(int i) {
        if (i >= 1) {
            this.maxRequestsPerHost = i;
            promoteCalls();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("max < 1: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public synchronized int getMaxRequestsPerHost() {
        return this.maxRequestsPerHost;
    }

    public synchronized void setIdleCallback(@Nullable Runnable runnable) {
        this.idleCallback = runnable;
    }

    synchronized void enqueue(AsyncCall asyncCall) {
        if (this.runningAsyncCalls.size() >= this.maxRequests || runningCallsForHost(asyncCall) >= this.maxRequestsPerHost) {
            this.readyAsyncCalls.add(asyncCall);
        } else {
            this.runningAsyncCalls.add(asyncCall);
            executorService().execute(asyncCall);
        }
    }

    private void promoteCalls() {
        if (this.runningAsyncCalls.size() < this.maxRequests && !this.readyAsyncCalls.isEmpty()) {
            Iterator it = this.readyAsyncCalls.iterator();
            while (it.hasNext()) {
                AsyncCall asyncCall = (AsyncCall) it.next();
                if (runningCallsForHost(asyncCall) < this.maxRequestsPerHost) {
                    it.remove();
                    this.runningAsyncCalls.add(asyncCall);
                    executorService().execute(asyncCall);
                }
                if (this.runningAsyncCalls.size() >= this.maxRequests) {
                    return;
                }
            }
        }
    }

    private int runningCallsForHost(AsyncCall asyncCall) {
        int i = 0;
        for (AsyncCall host : this.runningAsyncCalls) {
            if (host.host().equals(asyncCall.host())) {
                i++;
            }
        }
        return i;
    }

    synchronized void executed(RealCall realCall) {
        this.runningSyncCalls.add(realCall);
    }

    void finished(AsyncCall asyncCall) {
        finished(this.runningAsyncCalls, asyncCall, true);
    }

    void finished(RealCall realCall) {
        finished(this.runningSyncCalls, realCall, false);
    }

    private <T> void finished(Deque<T> deque, T t, boolean z) {
        synchronized (this) {
            if (deque.remove(t) != null) {
                if (z) {
                    promoteCalls();
                }
                deque = runningCallsCount();
                t = this.idleCallback;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (deque == null && t != null) {
            t.run();
        }
    }

    public synchronized int queuedCallsCount() {
        return this.readyAsyncCalls.size();
    }

    public synchronized int runningCallsCount() {
        return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
    }
}
