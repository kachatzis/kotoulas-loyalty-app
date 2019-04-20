package android.support.v7.widget;

import android.support.annotation.Nullable;
import android.support.v4.os.TraceCompat;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

final class GapWorker implements Runnable {
    static final ThreadLocal<GapWorker> sGapWorker = new ThreadLocal();
    static Comparator<Task> sTaskComparator = new C03401();
    long mFrameIntervalNs;
    long mPostTimeNs;
    ArrayList<RecyclerView> mRecyclerViews = new ArrayList();
    private ArrayList<Task> mTasks = new ArrayList();

    /* renamed from: android.support.v7.widget.GapWorker$1 */
    static class C03401 implements Comparator<Task> {
        C03401() {
        }

        public int compare(Task task, Task task2) {
            int i = 1;
            if ((task.view == null ? 1 : null) != (task2.view == null ? 1 : null)) {
                if (task.view != null) {
                    i = -1;
                }
                return i;
            } else if (task.immediate != task2.immediate) {
                if (task.immediate != null) {
                    i = -1;
                }
                return i;
            } else {
                int i2 = task2.viewVelocity - task.viewVelocity;
                if (i2 != 0) {
                    return i2;
                }
                task = task.distanceToItem - task2.distanceToItem;
                if (task != null) {
                    return task;
                }
                return 0;
            }
        }
    }

    static class Task {
        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        Task() {
        }

        public void clear() {
            this.immediate = false;
            this.viewVelocity = 0;
            this.distanceToItem = 0;
            this.view = null;
            this.position = 0;
        }
    }

    static class LayoutPrefetchRegistryImpl implements LayoutPrefetchRegistry {
        int mCount;
        int[] mPrefetchArray;
        int mPrefetchDx;
        int mPrefetchDy;

        LayoutPrefetchRegistryImpl() {
        }

        void setPrefetchVector(int i, int i2) {
            this.mPrefetchDx = i;
            this.mPrefetchDy = i2;
        }

        void collectPrefetchPositionsFromView(RecyclerView recyclerView, boolean z) {
            this.mCount = 0;
            int[] iArr = this.mPrefetchArray;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            LayoutManager layoutManager = recyclerView.mLayout;
            if (recyclerView.mAdapter != null && layoutManager != null && layoutManager.isItemPrefetchEnabled()) {
                if (z) {
                    if (!recyclerView.mAdapterHelper.hasPendingUpdates()) {
                        layoutManager.collectInitialPrefetchPositions(recyclerView.mAdapter.getItemCount(), this);
                    }
                } else if (!recyclerView.hasPendingAdapterUpdates()) {
                    layoutManager.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, recyclerView.mState, this);
                }
                if (this.mCount > layoutManager.mPrefetchMaxCountObserved) {
                    layoutManager.mPrefetchMaxCountObserved = this.mCount;
                    layoutManager.mPrefetchMaxObservedInInitialPrefetch = z;
                    recyclerView.mRecycler.updateViewCacheSize();
                }
            }
        }

        public void addPosition(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            } else if (i2 >= 0) {
                int i3 = this.mCount * 2;
                Object obj = this.mPrefetchArray;
                if (obj == null) {
                    this.mPrefetchArray = new int[4];
                    Arrays.fill(this.mPrefetchArray, -1);
                } else if (i3 >= obj.length) {
                    this.mPrefetchArray = new int[(i3 * 2)];
                    System.arraycopy(obj, 0, this.mPrefetchArray, 0, obj.length);
                }
                int[] iArr = this.mPrefetchArray;
                iArr[i3] = i;
                iArr[i3 + 1] = i2;
                this.mCount++;
            } else {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
        }

        boolean lastPrefetchIncludedPosition(int i) {
            if (this.mPrefetchArray != null) {
                int i2 = this.mCount * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.mPrefetchArray[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }

        void clearPrefetchPositions() {
            int[] iArr = this.mPrefetchArray;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.mCount = 0;
        }
    }

    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:24:0x005b in {5, 11, 12, 16, 20, 23} preds:[]
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
        r0 = 0;
        r2 = "RV Prefetch";	 Catch:{ all -> 0x0054 }
        android.support.v4.os.TraceCompat.beginSection(r2);	 Catch:{ all -> 0x0054 }
        r2 = r8.mRecyclerViews;	 Catch:{ all -> 0x0054 }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x0054 }
        if (r2 == 0) goto L_0x0015;
    L_0x000f:
        r8.mPostTimeNs = r0;
        android.support.v4.os.TraceCompat.endSection();
        return;
    L_0x0015:
        r2 = r8.mRecyclerViews;	 Catch:{ all -> 0x0054 }
        r2 = r2.size();	 Catch:{ all -> 0x0054 }
        r3 = 0;	 Catch:{ all -> 0x0054 }
        r4 = r0;	 Catch:{ all -> 0x0054 }
    L_0x001d:
        if (r3 >= r2) goto L_0x0038;	 Catch:{ all -> 0x0054 }
    L_0x001f:
        r6 = r8.mRecyclerViews;	 Catch:{ all -> 0x0054 }
        r6 = r6.get(r3);	 Catch:{ all -> 0x0054 }
        r6 = (android.support.v7.widget.RecyclerView) r6;	 Catch:{ all -> 0x0054 }
        r7 = r6.getWindowVisibility();	 Catch:{ all -> 0x0054 }
        if (r7 != 0) goto L_0x0035;	 Catch:{ all -> 0x0054 }
    L_0x002d:
        r6 = r6.getDrawingTime();	 Catch:{ all -> 0x0054 }
        r4 = java.lang.Math.max(r6, r4);	 Catch:{ all -> 0x0054 }
    L_0x0035:
        r3 = r3 + 1;
        goto L_0x001d;
    L_0x0038:
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r2 != 0) goto L_0x0042;
    L_0x003c:
        r8.mPostTimeNs = r0;
        android.support.v4.os.TraceCompat.endSection();
        return;
    L_0x0042:
        r2 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ all -> 0x0054 }
        r2 = r2.toNanos(r4);	 Catch:{ all -> 0x0054 }
        r4 = r8.mFrameIntervalNs;	 Catch:{ all -> 0x0054 }
        r2 = r2 + r4;	 Catch:{ all -> 0x0054 }
        r8.prefetch(r2);	 Catch:{ all -> 0x0054 }
        r8.mPostTimeNs = r0;
        android.support.v4.os.TraceCompat.endSection();
        return;
    L_0x0054:
        r2 = move-exception;
        r8.mPostTimeNs = r0;
        android.support.v4.os.TraceCompat.endSection();
        throw r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GapWorker.run():void");
    }

    GapWorker() {
    }

    public void add(RecyclerView recyclerView) {
        this.mRecyclerViews.add(recyclerView);
    }

    public void remove(RecyclerView recyclerView) {
        this.mRecyclerViews.remove(recyclerView);
    }

    void postFromTraversal(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.mPostTimeNs == 0) {
            this.mPostTimeNs = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.mPrefetchRegistry.setPrefetchVector(i, i2);
    }

    private void buildTaskList() {
        int i;
        int size = this.mRecyclerViews.size();
        int i2 = 0;
        for (i = 0; i < size; i++) {
            RecyclerView recyclerView = (RecyclerView) this.mRecyclerViews.get(i);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.mPrefetchRegistry.collectPrefetchPositionsFromView(recyclerView, false);
                i2 += recyclerView.mPrefetchRegistry.mCount;
            }
        }
        this.mTasks.ensureCapacity(i2);
        i2 = 0;
        for (i = 0; i < size; i++) {
            recyclerView = (RecyclerView) this.mRecyclerViews.get(i);
            if (recyclerView.getWindowVisibility() == 0) {
                LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView.mPrefetchRegistry;
                int abs = Math.abs(layoutPrefetchRegistryImpl.mPrefetchDx) + Math.abs(layoutPrefetchRegistryImpl.mPrefetchDy);
                int i3 = i2;
                for (i2 = 0; i2 < layoutPrefetchRegistryImpl.mCount * 2; i2 += 2) {
                    Task task;
                    if (i3 >= this.mTasks.size()) {
                        task = new Task();
                        this.mTasks.add(task);
                    } else {
                        task = (Task) this.mTasks.get(i3);
                    }
                    int i4 = layoutPrefetchRegistryImpl.mPrefetchArray[i2 + 1];
                    task.immediate = i4 <= abs;
                    task.viewVelocity = abs;
                    task.distanceToItem = i4;
                    task.view = recyclerView;
                    task.position = layoutPrefetchRegistryImpl.mPrefetchArray[i2];
                    i3++;
                }
                i2 = i3;
            }
        }
        Collections.sort(this.mTasks, sTaskComparator);
    }

    static boolean isPrefetchPositionAttached(RecyclerView recyclerView, int i) {
        int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i2));
            if (childViewHolderInt.mPosition == i && !childViewHolderInt.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    private ViewHolder prefetchPositionWithDeadline(RecyclerView recyclerView, int i, long j) {
        if (isPrefetchPositionAttached(recyclerView, i)) {
            return null;
        }
        Recycler recycler = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            i = recycler.tryGetViewHolderForPositionByDeadline(i, false, j);
            if (i != 0) {
                if (i.isBound() == null || i.isInvalid() != null) {
                    recycler.addViewHolderToRecycledViewPool(i, false);
                } else {
                    recycler.recycleView(i.itemView);
                }
            }
            recyclerView.onExitLayoutOrScroll(false);
            return i;
        } catch (Throwable th) {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    private void prefetchInnerRecyclerViewWithDeadline(@Nullable RecyclerView recyclerView, long j) {
        if (recyclerView != null) {
            if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.getUnfilteredChildCount() != 0) {
                recyclerView.removeAndRecycleViews();
            }
            LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView.mPrefetchRegistry;
            layoutPrefetchRegistryImpl.collectPrefetchPositionsFromView(recyclerView, true);
            if (layoutPrefetchRegistryImpl.mCount != 0) {
                try {
                    TraceCompat.beginSection("RV Nested Prefetch");
                    recyclerView.mState.prepareForNestedPrefetch(recyclerView.mAdapter);
                    for (int i = 0; i < layoutPrefetchRegistryImpl.mCount * 2; i += 2) {
                        prefetchPositionWithDeadline(recyclerView, layoutPrefetchRegistryImpl.mPrefetchArray[i], j);
                    }
                } finally {
                    TraceCompat.endSection();
                }
            }
        }
    }

    private void flushTaskWithDeadline(Task task, long j) {
        task = prefetchPositionWithDeadline(task.view, task.position, task.immediate ? Long.MAX_VALUE : j);
        if (task != null && task.mNestedRecyclerView != null && task.isBound() && !task.isInvalid()) {
            prefetchInnerRecyclerViewWithDeadline((RecyclerView) task.mNestedRecyclerView.get(), j);
        }
    }

    private void flushTasksWithDeadline(long j) {
        int i = 0;
        while (i < this.mTasks.size()) {
            Task task = (Task) this.mTasks.get(i);
            if (task.view != null) {
                flushTaskWithDeadline(task, j);
                task.clear();
                i++;
            } else {
                return;
            }
        }
    }

    void prefetch(long j) {
        buildTaskList();
        flushTasksWithDeadline(j);
    }
}
