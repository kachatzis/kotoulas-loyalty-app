package android.support.v7.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.util.ThreadUtil.BackgroundCallback;
import android.support.v7.util.ThreadUtil.MainThreadCallback;
import android.support.v7.util.TileList.Tile;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

public class AsyncListUtil<T> {
    static final boolean DEBUG = false;
    static final String TAG = "AsyncListUtil";
    boolean mAllowScrollHints;
    private final BackgroundCallback<T> mBackgroundCallback = new C05252();
    final BackgroundCallback<T> mBackgroundProxy;
    final DataCallback<T> mDataCallback;
    int mDisplayedGeneration = 0;
    int mItemCount = 0;
    private final MainThreadCallback<T> mMainThreadCallback = new C05241();
    final MainThreadCallback<T> mMainThreadProxy;
    final SparseIntArray mMissingPositions = new SparseIntArray();
    final int[] mPrevRange = new int[2];
    int mRequestedGeneration = this.mDisplayedGeneration;
    private int mScrollHint = 0;
    final Class<T> mTClass;
    final TileList<T> mTileList;
    final int mTileSize;
    final int[] mTmpRange = new int[2];
    final int[] mTmpRangeExtended = new int[2];
    final ViewCallback mViewCallback;

    public static abstract class DataCallback<T> {
        @WorkerThread
        public abstract void fillData(@NonNull T[] tArr, int i, int i2);

        @WorkerThread
        public int getMaxCachedTiles() {
            return 10;
        }

        @WorkerThread
        public void recycleData(@NonNull T[] tArr, int i) {
        }

        @WorkerThread
        public abstract int refreshData();
    }

    public static abstract class ViewCallback {
        public static final int HINT_SCROLL_ASC = 2;
        public static final int HINT_SCROLL_DESC = 1;
        public static final int HINT_SCROLL_NONE = 0;

        @UiThread
        public abstract void getItemRangeInto(@NonNull int[] iArr);

        @UiThread
        public abstract void onDataRefresh();

        @UiThread
        public abstract void onItemLoaded(int i);

        @UiThread
        public void extendRangeInto(@NonNull int[] iArr, @NonNull int[] iArr2, int i) {
            int i2 = (iArr[1] - iArr[0]) + 1;
            int i3 = i2 / 2;
            iArr2[0] = iArr[0] - (i == 1 ? i2 : i3);
            iArr = iArr[1];
            if (i != 2) {
                i2 = i3;
            }
            iArr2[1] = iArr + i2;
        }
    }

    /* renamed from: android.support.v7.util.AsyncListUtil$1 */
    class C05241 implements MainThreadCallback<T> {
        C05241() {
        }

        public void updateItemCount(int i, int i2) {
            if (isRequestedGeneration(i) != 0) {
                i = AsyncListUtil.this;
                i.mItemCount = i2;
                i.mViewCallback.onDataRefresh();
                i = AsyncListUtil.this;
                i.mDisplayedGeneration = i.mRequestedGeneration;
                recycleAllTiles();
                i = AsyncListUtil.this;
                i.mAllowScrollHints = false;
                i.updateRange();
            }
        }

        public void addTile(int i, Tile<T> tile) {
            if (isRequestedGeneration(i) == 0) {
                AsyncListUtil.this.mBackgroundProxy.recycleTile(tile);
                return;
            }
            i = AsyncListUtil.this.mTileList.addOrReplace(tile);
            if (i != 0) {
                String str = AsyncListUtil.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("duplicate tile @");
                stringBuilder.append(i.mStartPosition);
                Log.e(str, stringBuilder.toString());
                AsyncListUtil.this.mBackgroundProxy.recycleTile(i);
            }
            i = tile.mStartPosition + tile.mItemCount;
            int i2 = 0;
            while (i2 < AsyncListUtil.this.mMissingPositions.size()) {
                int keyAt = AsyncListUtil.this.mMissingPositions.keyAt(i2);
                if (tile.mStartPosition > keyAt || keyAt >= i) {
                    i2++;
                } else {
                    AsyncListUtil.this.mMissingPositions.removeAt(i2);
                    AsyncListUtil.this.mViewCallback.onItemLoaded(keyAt);
                }
            }
        }

        public void removeTile(int i, int i2) {
            if (isRequestedGeneration(i) != 0) {
                i = AsyncListUtil.this.mTileList.removeAtPos(i2);
                if (i == 0) {
                    i = AsyncListUtil.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("tile not found @");
                    stringBuilder.append(i2);
                    Log.e(i, stringBuilder.toString());
                    return;
                }
                AsyncListUtil.this.mBackgroundProxy.recycleTile(i);
            }
        }

        private void recycleAllTiles() {
            for (int i = 0; i < AsyncListUtil.this.mTileList.size(); i++) {
                AsyncListUtil.this.mBackgroundProxy.recycleTile(AsyncListUtil.this.mTileList.getAtIndex(i));
            }
            AsyncListUtil.this.mTileList.clear();
        }

        private boolean isRequestedGeneration(int i) {
            return i == AsyncListUtil.this.mRequestedGeneration;
        }
    }

    /* renamed from: android.support.v7.util.AsyncListUtil$2 */
    class C05252 implements BackgroundCallback<T> {
        private int mFirstRequiredTileStart;
        private int mGeneration;
        private int mItemCount;
        private int mLastRequiredTileStart;
        final SparseBooleanArray mLoadedTiles = new SparseBooleanArray();
        private Tile<T> mRecycledRoot;

        C05252() {
        }

        public void refresh(int i) {
            this.mGeneration = i;
            this.mLoadedTiles.clear();
            this.mItemCount = AsyncListUtil.this.mDataCallback.refreshData();
            AsyncListUtil.this.mMainThreadProxy.updateItemCount(this.mGeneration, this.mItemCount);
        }

        public void updateRange(int i, int i2, int i3, int i4, int i5) {
            if (i <= i2) {
                i = getTileStart(i);
                i2 = getTileStart(i2);
                this.mFirstRequiredTileStart = getTileStart(i3);
                this.mLastRequiredTileStart = getTileStart(i4);
                if (i5 == 1) {
                    requestTiles(this.mFirstRequiredTileStart, i2, i5, true);
                    requestTiles(i2 + AsyncListUtil.this.mTileSize, this.mLastRequiredTileStart, i5, false);
                } else {
                    requestTiles(i, this.mLastRequiredTileStart, i5, false);
                    requestTiles(this.mFirstRequiredTileStart, i - AsyncListUtil.this.mTileSize, i5, true);
                }
            }
        }

        private int getTileStart(int i) {
            return i - (i % AsyncListUtil.this.mTileSize);
        }

        private void requestTiles(int i, int i2, int i3, boolean z) {
            int i4 = i;
            while (i4 <= i2) {
                AsyncListUtil.this.mBackgroundProxy.loadTile(z ? (i2 + i) - i4 : i4, i3);
                i4 += AsyncListUtil.this.mTileSize;
            }
        }

        public void loadTile(int i, int i2) {
            if (!isTileLoaded(i)) {
                Tile acquireTile = acquireTile();
                acquireTile.mStartPosition = i;
                acquireTile.mItemCount = Math.min(AsyncListUtil.this.mTileSize, this.mItemCount - acquireTile.mStartPosition);
                AsyncListUtil.this.mDataCallback.fillData(acquireTile.mItems, acquireTile.mStartPosition, acquireTile.mItemCount);
                flushTileCache(i2);
                addTile(acquireTile);
            }
        }

        public void recycleTile(Tile<T> tile) {
            AsyncListUtil.this.mDataCallback.recycleData(tile.mItems, tile.mItemCount);
            tile.mNext = this.mRecycledRoot;
            this.mRecycledRoot = tile;
        }

        private Tile<T> acquireTile() {
            Tile<T> tile = this.mRecycledRoot;
            if (tile == null) {
                return new Tile(AsyncListUtil.this.mTClass, AsyncListUtil.this.mTileSize);
            }
            this.mRecycledRoot = tile.mNext;
            return tile;
        }

        private boolean isTileLoaded(int i) {
            return this.mLoadedTiles.get(i);
        }

        private void addTile(Tile<T> tile) {
            this.mLoadedTiles.put(tile.mStartPosition, true);
            AsyncListUtil.this.mMainThreadProxy.addTile(this.mGeneration, tile);
        }

        private void removeTile(int i) {
            this.mLoadedTiles.delete(i);
            AsyncListUtil.this.mMainThreadProxy.removeTile(this.mGeneration, i);
        }

        private void flushTileCache(int i) {
            int maxCachedTiles = AsyncListUtil.this.mDataCallback.getMaxCachedTiles();
            while (this.mLoadedTiles.size() >= maxCachedTiles) {
                int keyAt = this.mLoadedTiles.keyAt(0);
                SparseBooleanArray sparseBooleanArray = this.mLoadedTiles;
                int keyAt2 = sparseBooleanArray.keyAt(sparseBooleanArray.size() - 1);
                int i2 = this.mFirstRequiredTileStart - keyAt;
                int i3 = keyAt2 - this.mLastRequiredTileStart;
                if (i2 > 0 && (i2 >= i3 || i == 2)) {
                    removeTile(keyAt);
                } else if (i3 > 0 && (i2 < i3 || i == 1)) {
                    removeTile(keyAt2);
                } else {
                    return;
                }
            }
        }

        private void log(String str, Object... objArr) {
            String str2 = AsyncListUtil.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[BKGR] ");
            stringBuilder.append(String.format(str, objArr));
            Log.d(str2, stringBuilder.toString());
        }
    }

    void log(String str, Object... objArr) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[MAIN] ");
        stringBuilder.append(String.format(str, objArr));
        Log.d(str2, stringBuilder.toString());
    }

    public AsyncListUtil(@NonNull Class<T> cls, int i, @NonNull DataCallback<T> dataCallback, @NonNull ViewCallback viewCallback) {
        this.mTClass = cls;
        this.mTileSize = i;
        this.mDataCallback = dataCallback;
        this.mViewCallback = viewCallback;
        this.mTileList = new TileList(this.mTileSize);
        cls = new MessageThreadUtil();
        this.mMainThreadProxy = cls.getMainThreadProxy(this.mMainThreadCallback);
        this.mBackgroundProxy = cls.getBackgroundProxy(this.mBackgroundCallback);
        refresh();
    }

    private boolean isRefreshPending() {
        return this.mRequestedGeneration != this.mDisplayedGeneration;
    }

    public void onRangeChanged() {
        if (!isRefreshPending()) {
            updateRange();
            this.mAllowScrollHints = true;
        }
    }

    public void refresh() {
        this.mMissingPositions.clear();
        BackgroundCallback backgroundCallback = this.mBackgroundProxy;
        int i = this.mRequestedGeneration + 1;
        this.mRequestedGeneration = i;
        backgroundCallback.refresh(i);
    }

    @Nullable
    public T getItem(int i) {
        if (i < 0 || i >= this.mItemCount) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            stringBuilder.append(" is not within 0 and ");
            stringBuilder.append(this.mItemCount);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        }
        T itemAt = this.mTileList.getItemAt(i);
        if (itemAt == null && !isRefreshPending()) {
            this.mMissingPositions.put(i, 0);
        }
        return itemAt;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    void updateRange() {
        this.mViewCallback.getItemRangeInto(this.mTmpRange);
        int[] iArr = this.mTmpRange;
        if (iArr[0] <= iArr[1]) {
            if (iArr[0] >= 0) {
                if (iArr[1] < this.mItemCount) {
                    if (this.mAllowScrollHints) {
                        int i = iArr[0];
                        int[] iArr2 = this.mPrevRange;
                        if (i <= iArr2[1]) {
                            if (iArr2[0] <= iArr[1]) {
                                if (iArr[0] < iArr2[0]) {
                                    this.mScrollHint = 1;
                                } else if (iArr[0] > iArr2[0]) {
                                    this.mScrollHint = 2;
                                }
                            }
                        }
                        this.mScrollHint = 0;
                    } else {
                        this.mScrollHint = 0;
                    }
                    iArr = this.mPrevRange;
                    int[] iArr3 = this.mTmpRange;
                    iArr[0] = iArr3[0];
                    iArr[1] = iArr3[1];
                    this.mViewCallback.extendRangeInto(iArr3, this.mTmpRangeExtended, this.mScrollHint);
                    iArr = this.mTmpRangeExtended;
                    iArr[0] = Math.min(this.mTmpRange[0], Math.max(iArr[0], 0));
                    iArr = this.mTmpRangeExtended;
                    iArr[1] = Math.max(this.mTmpRange[1], Math.min(iArr[1], this.mItemCount - 1));
                    BackgroundCallback backgroundCallback = this.mBackgroundProxy;
                    iArr = this.mTmpRange;
                    int i2 = iArr[0];
                    int i3 = iArr[1];
                    iArr = this.mTmpRangeExtended;
                    backgroundCallback.updateRange(i2, i3, iArr[0], iArr[1], this.mScrollHint);
                }
            }
        }
    }
}
