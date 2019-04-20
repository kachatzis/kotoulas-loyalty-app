package android.arch.lifecycle;

import android.arch.core.util.Function;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Transformations {
    private Transformations() {
    }

    @MainThread
    public static <X, Y> LiveData<Y> map(@NonNull LiveData<X> liveData, @NonNull final Function<X, Y> function) {
        final LiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<X>() {
            public void onChanged(@Nullable X x) {
                mediatorLiveData.setValue(function.apply(x));
            }
        });
        return mediatorLiveData;
    }

    @MainThread
    public static <X, Y> LiveData<Y> switchMap(@NonNull LiveData<X> liveData, @NonNull final Function<X, LiveData<Y>> function) {
        final LiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<X>() {
            LiveData<Y> mSource;

            /* renamed from: android.arch.lifecycle.Transformations$2$1 */
            class C04511 implements Observer<Y> {
                C04511() {
                }

                public void onChanged(@Nullable Y y) {
                    mediatorLiveData.setValue(y);
                }
            }

            public void onChanged(@Nullable X x) {
                LiveData liveData = (LiveData) function.apply(x);
                LiveData liveData2 = this.mSource;
                if (liveData2 != liveData) {
                    if (liveData2 != null) {
                        mediatorLiveData.removeSource(liveData2);
                    }
                    this.mSource = liveData;
                    x = this.mSource;
                    if (x != null) {
                        mediatorLiveData.addSource(x, new C04511());
                    }
                }
            }
        });
        return mediatorLiveData;
    }
}
