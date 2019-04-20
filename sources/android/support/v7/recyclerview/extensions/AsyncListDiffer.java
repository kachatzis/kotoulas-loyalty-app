package android.support.v7.recyclerview.extensions;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig.Builder;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.Callback;
import android.support.v7.util.DiffUtil.DiffResult;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView.Adapter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public class AsyncListDiffer<T> {
    private static final Executor sMainThreadExecutor = new MainThreadExecutor();
    final AsyncDifferConfig<T> mConfig;
    @Nullable
    private List<T> mList;
    final Executor mMainThreadExecutor;
    int mMaxScheduledGeneration;
    @NonNull
    private List<T> mReadOnlyList;
    private final ListUpdateCallback mUpdateCallback;

    private static class MainThreadExecutor implements Executor {
        final Handler mHandler = new Handler(Looper.getMainLooper());

        MainThreadExecutor() {
        }

        public void execute(@NonNull Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    public AsyncListDiffer(@NonNull Adapter adapter, @NonNull ItemCallback<T> itemCallback) {
        this(new AdapterListUpdateCallback(adapter), new Builder(itemCallback).build());
    }

    public AsyncListDiffer(@NonNull ListUpdateCallback listUpdateCallback, @NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mReadOnlyList = Collections.emptyList();
        this.mUpdateCallback = listUpdateCallback;
        this.mConfig = asyncDifferConfig;
        if (asyncDifferConfig.getMainThreadExecutor() != null) {
            this.mMainThreadExecutor = asyncDifferConfig.getMainThreadExecutor();
        } else {
            this.mMainThreadExecutor = sMainThreadExecutor;
        }
    }

    @NonNull
    public List<T> getCurrentList() {
        return this.mReadOnlyList;
    }

    public void submitList(@Nullable final List<T> list) {
        final int i = this.mMaxScheduledGeneration + 1;
        this.mMaxScheduledGeneration = i;
        final List<T> list2 = this.mList;
        if (list != list2) {
            if (list == null) {
                list = list2.size();
                this.mList = null;
                this.mReadOnlyList = Collections.emptyList();
                this.mUpdateCallback.onRemoved(0, list);
            } else if (list2 == null) {
                this.mList = list;
                this.mReadOnlyList = Collections.unmodifiableList(list);
                this.mUpdateCallback.onInserted(0, list.size());
            } else {
                this.mConfig.getBackgroundThreadExecutor().execute(new Runnable() {

                    /* renamed from: android.support.v7.recyclerview.extensions.AsyncListDiffer$1$1 */
                    class C05231 extends Callback {
                        C05231() {
                        }

                        public int getOldListSize() {
                            return list2.size();
                        }

                        public int getNewListSize() {
                            return list.size();
                        }

                        public boolean areItemsTheSame(int i, int i2) {
                            i = list2.get(i);
                            i2 = list.get(i2);
                            if (i != 0 && i2 != 0) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().areItemsTheSame(i, i2);
                            }
                            i = (i == 0 && i2 == 0) ? 1 : 0;
                            return i;
                        }

                        public boolean areContentsTheSame(int i, int i2) {
                            i = list2.get(i);
                            i2 = list.get(i2);
                            if (i != 0 && i2 != 0) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().areContentsTheSame(i, i2);
                            }
                            if (i == 0 && i2 == 0) {
                                return true;
                            }
                            throw new AssertionError();
                        }

                        @Nullable
                        public Object getChangePayload(int i, int i2) {
                            i = list2.get(i);
                            i2 = list.get(i2);
                            if (i != 0 && i2 != 0) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().getChangePayload(i, i2);
                            }
                            throw new AssertionError();
                        }
                    }

                    public void run() {
                        final DiffResult calculateDiff = DiffUtil.calculateDiff(new C05231());
                        AsyncListDiffer.this.mMainThreadExecutor.execute(new Runnable() {
                            public void run() {
                                if (AsyncListDiffer.this.mMaxScheduledGeneration == i) {
                                    AsyncListDiffer.this.latchList(list, calculateDiff);
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    void latchList(@NonNull List<T> list, @NonNull DiffResult diffResult) {
        this.mList = list;
        this.mReadOnlyList = Collections.unmodifiableList(list);
        diffResult.dispatchUpdatesTo(this.mUpdateCallback);
    }
}
