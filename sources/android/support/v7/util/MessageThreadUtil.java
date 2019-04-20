package android.support.v7.util;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.util.ThreadUtil.BackgroundCallback;
import android.support.v7.util.ThreadUtil.MainThreadCallback;
import android.support.v7.util.TileList.Tile;
import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

class MessageThreadUtil<T> implements ThreadUtil<T> {

    static class MessageQueue {
        private SyncQueueItem mRoot;

        synchronized void removeMessages(int r5) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:22:0x0035 in {6, 14, 15, 16, 18, 21} preds:[]
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
            r4 = this;
            monitor-enter(r4);
        L_0x0001:
            r0 = r4.mRoot;	 Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0017;	 Catch:{ all -> 0x0032 }
        L_0x0005:
            r0 = r4.mRoot;	 Catch:{ all -> 0x0032 }
            r0 = r0.what;	 Catch:{ all -> 0x0032 }
            if (r0 != r5) goto L_0x0017;	 Catch:{ all -> 0x0032 }
        L_0x000b:
            r0 = r4.mRoot;	 Catch:{ all -> 0x0032 }
            r1 = r4.mRoot;	 Catch:{ all -> 0x0032 }
            r1 = r1.next;	 Catch:{ all -> 0x0032 }
            r4.mRoot = r1;	 Catch:{ all -> 0x0032 }
            r0.recycle();	 Catch:{ all -> 0x0032 }
            goto L_0x0001;	 Catch:{ all -> 0x0032 }
        L_0x0017:
            r0 = r4.mRoot;	 Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0030;	 Catch:{ all -> 0x0032 }
        L_0x001b:
            r0 = r4.mRoot;	 Catch:{ all -> 0x0032 }
            r1 = r0.next;	 Catch:{ all -> 0x0032 }
        L_0x001f:
            if (r1 == 0) goto L_0x0030;	 Catch:{ all -> 0x0032 }
        L_0x0021:
            r2 = r1.next;	 Catch:{ all -> 0x0032 }
            r3 = r1.what;	 Catch:{ all -> 0x0032 }
            if (r3 != r5) goto L_0x002d;	 Catch:{ all -> 0x0032 }
        L_0x0027:
            r0.next = r2;	 Catch:{ all -> 0x0032 }
            r1.recycle();	 Catch:{ all -> 0x0032 }
            goto L_0x002e;
        L_0x002d:
            r0 = r1;
        L_0x002e:
            r1 = r2;
            goto L_0x001f;
        L_0x0030:
            monitor-exit(r4);
            return;
        L_0x0032:
            r5 = move-exception;
            monitor-exit(r4);
            throw r5;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.util.MessageThreadUtil.MessageQueue.removeMessages(int):void");
        }

        synchronized void sendMessage(android.support.v7.util.MessageThreadUtil.SyncQueueItem r3) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:18:0x0019 in {6, 11, 14, 17} preds:[]
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
            r2 = this;
            monitor-enter(r2);
            r0 = r2.mRoot;	 Catch:{ all -> 0x0016 }
            if (r0 != 0) goto L_0x0009;	 Catch:{ all -> 0x0016 }
        L_0x0005:
            r2.mRoot = r3;	 Catch:{ all -> 0x0016 }
            monitor-exit(r2);
            return;
        L_0x0009:
            r0 = r2.mRoot;	 Catch:{ all -> 0x0016 }
        L_0x000b:
            r1 = r0.next;	 Catch:{ all -> 0x0016 }
            if (r1 == 0) goto L_0x0012;	 Catch:{ all -> 0x0016 }
        L_0x000f:
            r0 = r0.next;	 Catch:{ all -> 0x0016 }
            goto L_0x000b;	 Catch:{ all -> 0x0016 }
        L_0x0012:
            r0.next = r3;	 Catch:{ all -> 0x0016 }
            monitor-exit(r2);
            return;
        L_0x0016:
            r3 = move-exception;
            monitor-exit(r2);
            throw r3;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.util.MessageThreadUtil.MessageQueue.sendMessage(android.support.v7.util.MessageThreadUtil$SyncQueueItem):void");
        }

        MessageQueue() {
        }

        synchronized SyncQueueItem next() {
            if (this.mRoot == null) {
                return null;
            }
            SyncQueueItem syncQueueItem = this.mRoot;
            this.mRoot = this.mRoot.next;
            return syncQueueItem;
        }

        synchronized void sendMessageAtFrontOfQueue(SyncQueueItem syncQueueItem) {
            syncQueueItem.next = this.mRoot;
            this.mRoot = syncQueueItem;
        }
    }

    static class SyncQueueItem {
        private static SyncQueueItem sPool;
        private static final Object sPoolLock = new Object();
        public int arg1;
        public int arg2;
        public int arg3;
        public int arg4;
        public int arg5;
        public Object data;
        SyncQueueItem next;
        public int what;

        SyncQueueItem() {
        }

        void recycle() {
            this.next = null;
            this.arg5 = 0;
            this.arg4 = 0;
            this.arg3 = 0;
            this.arg2 = 0;
            this.arg1 = 0;
            this.what = 0;
            this.data = null;
            synchronized (sPoolLock) {
                if (sPool != null) {
                    this.next = sPool;
                }
                sPool = this;
            }
        }

        static SyncQueueItem obtainMessage(int i, int i2, int i3, int i4, int i5, int i6, Object obj) {
            SyncQueueItem syncQueueItem;
            synchronized (sPoolLock) {
                if (sPool == null) {
                    syncQueueItem = new SyncQueueItem();
                } else {
                    syncQueueItem = sPool;
                    sPool = sPool.next;
                    syncQueueItem.next = null;
                }
                syncQueueItem.what = i;
                syncQueueItem.arg1 = i2;
                syncQueueItem.arg2 = i3;
                syncQueueItem.arg3 = i4;
                syncQueueItem.arg4 = i5;
                syncQueueItem.arg5 = i6;
                syncQueueItem.data = obj;
            }
            return syncQueueItem;
        }

        static SyncQueueItem obtainMessage(int i, int i2, int i3) {
            return obtainMessage(i, i2, i3, 0, 0, 0, null);
        }

        static SyncQueueItem obtainMessage(int i, int i2, Object obj) {
            return obtainMessage(i, i2, 0, 0, 0, 0, obj);
        }
    }

    MessageThreadUtil() {
    }

    public MainThreadCallback<T> getMainThreadProxy(final MainThreadCallback<T> mainThreadCallback) {
        return new MainThreadCallback<T>() {
            static final int ADD_TILE = 2;
            static final int REMOVE_TILE = 3;
            static final int UPDATE_ITEM_COUNT = 1;
            private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
            private Runnable mMainThreadRunnable = new C03091();
            final MessageQueue mQueue = new MessageQueue();

            /* renamed from: android.support.v7.util.MessageThreadUtil$1$1 */
            class C03091 implements Runnable {
                C03091() {
                }

                public void run() {
                    SyncQueueItem next = C05261.this.mQueue.next();
                    while (next != null) {
                        switch (next.what) {
                            case 1:
                                mainThreadCallback.updateItemCount(next.arg1, next.arg2);
                                break;
                            case 2:
                                mainThreadCallback.addTile(next.arg1, (Tile) next.data);
                                break;
                            case 3:
                                mainThreadCallback.removeTile(next.arg1, next.arg2);
                                break;
                            default:
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("Unsupported message, what=");
                                stringBuilder.append(next.what);
                                Log.e("ThreadUtil", stringBuilder.toString());
                                break;
                        }
                        next = C05261.this.mQueue.next();
                    }
                }
            }

            public void updateItemCount(int i, int i2) {
                sendMessage(SyncQueueItem.obtainMessage(1, i, i2));
            }

            public void addTile(int i, Tile<T> tile) {
                sendMessage(SyncQueueItem.obtainMessage(2, i, (Object) tile));
            }

            public void removeTile(int i, int i2) {
                sendMessage(SyncQueueItem.obtainMessage(3, i, i2));
            }

            private void sendMessage(SyncQueueItem syncQueueItem) {
                this.mQueue.sendMessage(syncQueueItem);
                this.mMainThreadHandler.post(this.mMainThreadRunnable);
            }
        };
    }

    public BackgroundCallback<T> getBackgroundProxy(final BackgroundCallback<T> backgroundCallback) {
        return new BackgroundCallback<T>() {
            static final int LOAD_TILE = 3;
            static final int RECYCLE_TILE = 4;
            static final int REFRESH = 1;
            static final int UPDATE_RANGE = 2;
            private Runnable mBackgroundRunnable = new C03101();
            AtomicBoolean mBackgroundRunning = new AtomicBoolean(null);
            private final Executor mExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
            final MessageQueue mQueue = new MessageQueue();

            /* renamed from: android.support.v7.util.MessageThreadUtil$2$1 */
            class C03101 implements Runnable {
                C03101() {
                }

                public void run() {
                    while (true) {
                        SyncQueueItem next = C05272.this.mQueue.next();
                        if (next != null) {
                            switch (next.what) {
                                case 1:
                                    C05272.this.mQueue.removeMessages(1);
                                    backgroundCallback.refresh(next.arg1);
                                    break;
                                case 2:
                                    C05272.this.mQueue.removeMessages(2);
                                    C05272.this.mQueue.removeMessages(3);
                                    backgroundCallback.updateRange(next.arg1, next.arg2, next.arg3, next.arg4, next.arg5);
                                    break;
                                case 3:
                                    backgroundCallback.loadTile(next.arg1, next.arg2);
                                    break;
                                case 4:
                                    backgroundCallback.recycleTile((Tile) next.data);
                                    break;
                                default:
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("Unsupported message, what=");
                                    stringBuilder.append(next.what);
                                    Log.e("ThreadUtil", stringBuilder.toString());
                                    break;
                            }
                        }
                        C05272.this.mBackgroundRunning.set(false);
                        return;
                    }
                }
            }

            public void refresh(int i) {
                sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(1, i, null));
            }

            public void updateRange(int i, int i2, int i3, int i4, int i5) {
                sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(2, i, i2, i3, i4, i5, null));
            }

            public void loadTile(int i, int i2) {
                sendMessage(SyncQueueItem.obtainMessage(3, i, i2));
            }

            public void recycleTile(Tile<T> tile) {
                sendMessage(SyncQueueItem.obtainMessage(4, 0, (Object) tile));
            }

            private void sendMessage(SyncQueueItem syncQueueItem) {
                this.mQueue.sendMessage(syncQueueItem);
                maybeExecuteBackgroundRunnable();
            }

            private void sendMessageAtFrontOfQueue(SyncQueueItem syncQueueItem) {
                this.mQueue.sendMessageAtFrontOfQueue(syncQueueItem);
                maybeExecuteBackgroundRunnable();
            }

            private void maybeExecuteBackgroundRunnable() {
                if (this.mBackgroundRunning.compareAndSet(false, true)) {
                    this.mExecutor.execute(this.mBackgroundRunnable);
                }
            }
        };
    }
}
