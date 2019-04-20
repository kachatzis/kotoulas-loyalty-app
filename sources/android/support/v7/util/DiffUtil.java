package android.support.v7.util;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView.Adapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DiffUtil {
    private static final Comparator<Snake> SNAKE_COMPARATOR = new C03081();

    /* renamed from: android.support.v7.util.DiffUtil$1 */
    static class C03081 implements Comparator<Snake> {
        C03081() {
        }

        public int compare(Snake snake, Snake snake2) {
            int i = snake.f11x - snake2.f11x;
            return i == 0 ? snake.f12y - snake2.f12y : i;
        }
    }

    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i, int i2);

        public abstract boolean areItemsTheSame(int i, int i2);

        @Nullable
        public Object getChangePayload(int i, int i2) {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        public static final int NO_POSITION = -1;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List<Snake> mSnakes;

        DiffResult(Callback callback, List<Snake> list, int[] iArr, int[] iArr2, boolean z) {
            this.mSnakes = list;
            this.mOldItemStatuses = iArr;
            this.mNewItemStatuses = iArr2;
            Arrays.fill(this.mOldItemStatuses, 0);
            Arrays.fill(this.mNewItemStatuses, 0);
            this.mCallback = callback;
            this.mOldListSize = callback.getOldListSize();
            this.mNewListSize = callback.getNewListSize();
            this.mDetectMoves = z;
            addRootSnake();
            findMatchingItems();
        }

        private void addRootSnake() {
            Snake snake = this.mSnakes.isEmpty() ? null : (Snake) this.mSnakes.get(0);
            if (snake == null || snake.f11x != 0 || snake.f12y != 0) {
                snake = new Snake();
                snake.f11x = 0;
                snake.f12y = 0;
                snake.removal = false;
                snake.size = 0;
                snake.reverse = false;
                this.mSnakes.add(0, snake);
            }
        }

        private void findMatchingItems() {
            int i = this.mOldListSize;
            int i2 = this.mNewListSize;
            for (int size = this.mSnakes.size() - 1; size >= 0; size--) {
                Snake snake = (Snake) this.mSnakes.get(size);
                int i3 = snake.f11x + snake.size;
                int i4 = snake.f12y + snake.size;
                if (this.mDetectMoves) {
                    while (i > i3) {
                        findAddition(i, i2, size);
                        i--;
                    }
                    while (i2 > i4) {
                        findRemoval(i, i2, size);
                        i2--;
                    }
                }
                for (i = 0; i < snake.size; i++) {
                    i2 = snake.f11x + i;
                    i3 = snake.f12y + i;
                    i4 = this.mCallback.areContentsTheSame(i2, i3) ? 1 : 2;
                    this.mOldItemStatuses[i2] = (i3 << 5) | i4;
                    this.mNewItemStatuses[i3] = (i2 << 5) | i4;
                }
                i = snake.f11x;
                i2 = snake.f12y;
            }
        }

        private void findAddition(int i, int i2, int i3) {
            if (this.mOldItemStatuses[i - 1] == 0) {
                findMatchingItem(i, i2, i3, false);
            }
        }

        private void findRemoval(int i, int i2, int i3) {
            if (this.mNewItemStatuses[i2 - 1] == 0) {
                findMatchingItem(i, i2, i3, true);
            }
        }

        public int convertOldPositionToNew(@IntRange(from = 0) int i) {
            if (i >= 0) {
                int[] iArr = this.mOldItemStatuses;
                if (i < iArr.length) {
                    i = iArr[i];
                    return (i & 31) == 0 ? -1 : i >> 5;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Index out of bounds - passed position = ");
            stringBuilder.append(i);
            stringBuilder.append(", old list size = ");
            stringBuilder.append(this.mOldItemStatuses.length);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        }

        public int convertNewPositionToOld(@IntRange(from = 0) int i) {
            if (i >= 0) {
                int[] iArr = this.mNewItemStatuses;
                if (i < iArr.length) {
                    i = iArr[i];
                    return (i & 31) == 0 ? -1 : i >> 5;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Index out of bounds - passed position = ");
            stringBuilder.append(i);
            stringBuilder.append(", new list size = ");
            stringBuilder.append(this.mNewItemStatuses.length);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        }

        private boolean findMatchingItem(int i, int i2, int i3, boolean z) {
            int i4;
            int i5;
            if (z) {
                i2--;
                i4 = i;
                i5 = i2;
            } else {
                i4 = i - 1;
                i5 = i4;
            }
            while (i3 >= 0) {
                Snake snake = (Snake) this.mSnakes.get(i3);
                int i6 = snake.f11x + snake.size;
                int i7 = snake.f12y + snake.size;
                int i8 = 8;
                if (z) {
                    for (i4--; i4 >= i6; i4--) {
                        if (this.mCallback.areItemsTheSame(i4, i5) != 0) {
                            if (this.mCallback.areContentsTheSame(i4, i5) == 0) {
                                i8 = 4;
                            }
                            this.mNewItemStatuses[i5] = (i4 << 5) | 16;
                            this.mOldItemStatuses[i4] = (i5 << 5) | i8;
                            return true;
                        }
                    }
                    continue;
                } else {
                    for (i2--; i2 >= i7; i2--) {
                        if (this.mCallback.areItemsTheSame(i5, i2)) {
                            if (this.mCallback.areContentsTheSame(i5, i2) == 0) {
                                i8 = 4;
                            }
                            i--;
                            this.mOldItemStatuses[i] = (i2 << 5) | 16;
                            this.mNewItemStatuses[i2] = (i << 5) | i8;
                            return true;
                        }
                    }
                    continue;
                }
                i4 = snake.f11x;
                i2 = snake.f12y;
                i3--;
            }
            return false;
        }

        public void dispatchUpdatesTo(@NonNull Adapter adapter) {
            dispatchUpdatesTo(new AdapterListUpdateCallback(adapter));
        }

        public void dispatchUpdatesTo(@NonNull ListUpdateCallback listUpdateCallback) {
            if (listUpdateCallback instanceof BatchingListUpdateCallback) {
                listUpdateCallback = (BatchingListUpdateCallback) listUpdateCallback;
            } else {
                listUpdateCallback = new BatchingListUpdateCallback(listUpdateCallback);
            }
            ArrayList arrayList = new ArrayList();
            int i = this.mOldListSize;
            int i2 = this.mNewListSize;
            for (int size = this.mSnakes.size() - 1; size >= 0; size--) {
                Snake snake = (Snake) this.mSnakes.get(size);
                int i3 = snake.size;
                int i4 = snake.f11x + i3;
                int i5 = snake.f12y + i3;
                if (i4 < i) {
                    dispatchRemovals(arrayList, listUpdateCallback, i4, i - i4, i4);
                }
                if (i5 < i2) {
                    dispatchAdditions(arrayList, listUpdateCallback, i4, i2 - i5, i5);
                }
                for (i3--; i3 >= 0; i3--) {
                    if ((this.mOldItemStatuses[snake.f11x + i3] & 31) == 2) {
                        listUpdateCallback.onChanged(snake.f11x + i3, 1, this.mCallback.getChangePayload(snake.f11x + i3, snake.f12y + i3));
                    }
                }
                i = snake.f11x;
                i2 = snake.f12y;
            }
            listUpdateCallback.dispatchLastEvent();
        }

        private static PostponedUpdate removePostponedUpdate(List<PostponedUpdate> list, int i, boolean z) {
            int size = list.size() - 1;
            while (size >= 0) {
                PostponedUpdate postponedUpdate = (PostponedUpdate) list.get(size);
                if (postponedUpdate.posInOwnerList == i && postponedUpdate.removal == z) {
                    list.remove(size);
                    while (size < list.size()) {
                        PostponedUpdate postponedUpdate2 = (PostponedUpdate) list.get(size);
                        postponedUpdate2.currentPos += z ? 1 : -1;
                        size++;
                    }
                    return postponedUpdate;
                }
                size--;
            }
            return null;
        }

        private void dispatchAdditions(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i, int i2, int i3) {
            if (this.mDetectMoves) {
                for (i2--; i2 >= 0; i2--) {
                    int i4 = i3 + i2;
                    int i5 = this.mNewItemStatuses[i4] & 31;
                    if (i5 == 0) {
                        listUpdateCallback.onInserted(i, 1);
                        for (PostponedUpdate postponedUpdate : list) {
                            postponedUpdate.currentPos++;
                        }
                    } else if (i5 == 4 || i5 == 8) {
                        int i6 = this.mNewItemStatuses[i4] >> 5;
                        listUpdateCallback.onMoved(removePostponedUpdate(list, i6, true).currentPos, i);
                        if (i5 == 4) {
                            listUpdateCallback.onChanged(i, 1, this.mCallback.getChangePayload(i6, i4));
                        }
                    } else if (i5 == 16) {
                        list.add(new PostponedUpdate(i4, i, false));
                    } else {
                        listUpdateCallback = new StringBuilder();
                        listUpdateCallback.append("unknown flag for pos ");
                        listUpdateCallback.append(i4);
                        listUpdateCallback.append(" ");
                        listUpdateCallback.append(Long.toBinaryString((long) i5));
                        throw new IllegalStateException(listUpdateCallback.toString());
                    }
                }
                return;
            }
            listUpdateCallback.onInserted(i, i2);
        }

        private void dispatchRemovals(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i, int i2, int i3) {
            if (this.mDetectMoves) {
                for (i2--; i2 >= 0; i2--) {
                    int i4 = i3 + i2;
                    int i5 = this.mOldItemStatuses[i4] & 31;
                    if (i5 == 0) {
                        listUpdateCallback.onRemoved(i + i2, 1);
                        for (PostponedUpdate postponedUpdate : list) {
                            postponedUpdate.currentPos--;
                        }
                    } else if (i5 == 4 || i5 == 8) {
                        int i6 = this.mOldItemStatuses[i4] >> 5;
                        PostponedUpdate removePostponedUpdate = removePostponedUpdate(list, i6, false);
                        listUpdateCallback.onMoved(i + i2, removePostponedUpdate.currentPos - 1);
                        if (i5 == 4) {
                            listUpdateCallback.onChanged(removePostponedUpdate.currentPos - 1, 1, this.mCallback.getChangePayload(i4, i6));
                        }
                    } else if (i5 == 16) {
                        list.add(new PostponedUpdate(i4, i + i2, true));
                    } else {
                        listUpdateCallback = new StringBuilder();
                        listUpdateCallback.append("unknown flag for pos ");
                        listUpdateCallback.append(i4);
                        listUpdateCallback.append(" ");
                        listUpdateCallback.append(Long.toBinaryString((long) i5));
                        throw new IllegalStateException(listUpdateCallback.toString());
                    }
                }
                return;
            }
            listUpdateCallback.onRemoved(i, i2);
        }

        @VisibleForTesting
        List<Snake> getSnakes() {
            return this.mSnakes;
        }
    }

    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(@NonNull T t, @NonNull T t2);

        public abstract boolean areItemsTheSame(@NonNull T t, @NonNull T t2);

        @Nullable
        public Object getChangePayload(@NonNull T t, @NonNull T t2) {
            return null;
        }
    }

    private static class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        public PostponedUpdate(int i, int i2, boolean z) {
            this.posInOwnerList = i;
            this.currentPos = i2;
            this.removal = z;
        }
    }

    static class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range(int i, int i2, int i3, int i4) {
            this.oldListStart = i;
            this.oldListEnd = i2;
            this.newListStart = i3;
            this.newListEnd = i4;
        }
    }

    static class Snake {
        boolean removal;
        boolean reverse;
        int size;
        /* renamed from: x */
        int f11x;
        /* renamed from: y */
        int f12y;

        Snake() {
        }
    }

    private DiffUtil() {
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback callback) {
        return calculateDiff(callback, true);
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback callback, boolean z) {
        int oldListSize = callback.getOldListSize();
        int newListSize = callback.getNewListSize();
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        arrayList2.add(new Range(0, oldListSize, 0, newListSize));
        oldListSize = Math.abs(oldListSize - newListSize) + (oldListSize + newListSize);
        newListSize = oldListSize * 2;
        int[] iArr = new int[newListSize];
        int[] iArr2 = new int[newListSize];
        List arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            Range range = (Range) arrayList2.remove(arrayList2.size() - 1);
            Snake diffPartial = diffPartial(callback, range.oldListStart, range.oldListEnd, range.newListStart, range.newListEnd, iArr, iArr2, oldListSize);
            if (diffPartial != null) {
                if (diffPartial.size > 0) {
                    arrayList.add(diffPartial);
                }
                diffPartial.f11x += range.oldListStart;
                diffPartial.f12y += range.newListStart;
                Range range2 = arrayList3.isEmpty() ? new Range() : (Range) arrayList3.remove(arrayList3.size() - 1);
                range2.oldListStart = range.oldListStart;
                range2.newListStart = range.newListStart;
                if (diffPartial.reverse) {
                    range2.oldListEnd = diffPartial.f11x;
                    range2.newListEnd = diffPartial.f12y;
                } else if (diffPartial.removal) {
                    range2.oldListEnd = diffPartial.f11x - 1;
                    range2.newListEnd = diffPartial.f12y;
                } else {
                    range2.oldListEnd = diffPartial.f11x;
                    range2.newListEnd = diffPartial.f12y - 1;
                }
                arrayList2.add(range2);
                if (!diffPartial.reverse) {
                    range.oldListStart = diffPartial.f11x + diffPartial.size;
                    range.newListStart = diffPartial.f12y + diffPartial.size;
                } else if (diffPartial.removal) {
                    range.oldListStart = (diffPartial.f11x + diffPartial.size) + 1;
                    range.newListStart = diffPartial.f12y + diffPartial.size;
                } else {
                    range.oldListStart = diffPartial.f11x + diffPartial.size;
                    range.newListStart = (diffPartial.f12y + diffPartial.size) + 1;
                }
                arrayList2.add(range);
            } else {
                arrayList3.add(range);
            }
        }
        Collections.sort(arrayList, SNAKE_COMPARATOR);
        return new DiffResult(callback, arrayList, iArr, iArr2, z);
    }

    private static Snake diffPartial(Callback callback, int i, int i2, int i3, int i4, int[] iArr, int[] iArr2, int i5) {
        Callback callback2 = callback;
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        int i6 = i2 - i;
        int i7 = i4 - i3;
        int i8 = 1;
        if (i6 >= 1) {
            if (i7 >= 1) {
                int i9 = i6 - i7;
                int i10 = ((i6 + i7) + 1) / 2;
                int i11 = (i5 - i10) - 1;
                int i12 = (i5 + i10) + 1;
                Arrays.fill(iArr3, i11, i12, 0);
                Arrays.fill(iArr4, i11 + i9, i12 + i9, i6);
                Object obj = i9 % 2 != 0 ? 1 : null;
                i12 = 0;
                while (i12 <= i10) {
                    int i13;
                    boolean z;
                    int i14;
                    Snake snake;
                    int i15 = -i12;
                    int i16 = i15;
                    while (i16 <= i12) {
                        if (i16 != i15) {
                            if (i16 != i12) {
                                i13 = i5 + i16;
                                if (iArr3[i13 - 1] < iArr3[i13 + i8]) {
                                }
                            }
                            i13 = iArr3[(i5 + i16) - i8] + i8;
                            z = true;
                            i14 = i13 - i16;
                            while (i13 < i6 && i14 < i7 && callback2.areItemsTheSame(i + i13, i3 + i14)) {
                                i13++;
                                i14++;
                            }
                            i8 = i5 + i16;
                            iArr3[i8] = i13;
                            if (obj == null && i16 >= (i9 - i12) + 1 && i16 <= (i9 + i12) - 1) {
                                if (iArr3[i8] >= iArr4[i8]) {
                                    snake = new Snake();
                                    snake.f11x = iArr4[i8];
                                    snake.f12y = snake.f11x - i16;
                                    snake.size = iArr3[i8] - iArr4[i8];
                                    snake.removal = z;
                                    snake.reverse = false;
                                    return snake;
                                }
                            }
                            i16 += 2;
                            i8 = 1;
                        }
                        i13 = iArr3[(i5 + i16) + i8];
                        z = false;
                        i14 = i13 - i16;
                        while (i13 < i6) {
                            i13++;
                            i14++;
                        }
                        i8 = i5 + i16;
                        iArr3[i8] = i13;
                        if (obj == null) {
                        }
                        i16 += 2;
                        i8 = 1;
                    }
                    int i17 = i15;
                    while (i17 <= i12) {
                        int i18;
                        int i19;
                        i16 = i17 + i9;
                        if (i16 != i12 + i9) {
                            if (i16 != i15 + i9) {
                                i13 = i5 + i16;
                                i14 = 1;
                                if (iArr4[i13 - 1] < iArr4[i13 + 1]) {
                                }
                            } else {
                                i14 = 1;
                            }
                            i13 = iArr4[(i5 + i16) + i14] - i14;
                            z = true;
                            i18 = i13 - i16;
                            while (i13 > 0 && i18 > 0) {
                                i19 = i6;
                                if (callback2.areItemsTheSame((i + i13) - 1, (i3 + i18) - 1)) {
                                    break;
                                }
                                i13--;
                                i18--;
                                i6 = i19;
                            }
                            i19 = i6;
                            i6 = i5 + i16;
                            iArr4[i6] = i13;
                            if (obj != null && i16 >= i15 && i16 <= i12) {
                                if (iArr3[i6] >= iArr4[i6]) {
                                    snake = new Snake();
                                    snake.f11x = iArr4[i6];
                                    snake.f12y = snake.f11x - i16;
                                    snake.size = iArr3[i6] - iArr4[i6];
                                    snake.removal = z;
                                    snake.reverse = true;
                                    return snake;
                                }
                            }
                            i17 += 2;
                            i6 = i19;
                        } else {
                            i14 = 1;
                        }
                        i13 = iArr4[(i5 + i16) - i14];
                        z = false;
                        i18 = i13 - i16;
                        while (i13 > 0) {
                            i19 = i6;
                            if (callback2.areItemsTheSame((i + i13) - 1, (i3 + i18) - 1)) {
                                break;
                            }
                            i13--;
                            i18--;
                            i6 = i19;
                        }
                        i19 = i6;
                        i6 = i5 + i16;
                        iArr4[i6] = i13;
                        if (obj != null) {
                        }
                        i17 += 2;
                        i6 = i19;
                    }
                    i12++;
                    i6 = i6;
                    i8 = 1;
                }
                throw new IllegalStateException("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
            }
        }
        return null;
    }
}
