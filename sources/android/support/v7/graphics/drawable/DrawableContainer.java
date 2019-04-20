package android.support.v7.graphics.drawable;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.SparseArray;

@RestrictTo({Scope.LIBRARY_GROUP})
class DrawableContainer extends Drawable implements Callback {
    private static final boolean DEBUG = false;
    private static final boolean DEFAULT_DITHER = true;
    private static final String TAG = "DrawableContainer";
    private int mAlpha = 255;
    private Runnable mAnimationRunnable;
    private BlockInvalidateCallback mBlockInvalidateCallback;
    private int mCurIndex = -1;
    private Drawable mCurrDrawable;
    private DrawableContainerState mDrawableContainerState;
    private long mEnterAnimationEnd;
    private long mExitAnimationEnd;
    private boolean mHasAlpha;
    private Rect mHotspotBounds;
    private Drawable mLastDrawable;
    private int mLastIndex = -1;
    private boolean mMutated;

    /* renamed from: android.support.v7.graphics.drawable.DrawableContainer$1 */
    class C02961 implements Runnable {
        C02961() {
        }

        public void run() {
            DrawableContainer.this.animate(DrawableContainer.DEFAULT_DITHER);
            DrawableContainer.this.invalidateSelf();
        }
    }

    static class BlockInvalidateCallback implements Callback {
        private Callback mCallback;

        public void invalidateDrawable(@NonNull Drawable drawable) {
        }

        BlockInvalidateCallback() {
        }

        public BlockInvalidateCallback wrap(Callback callback) {
            this.mCallback = callback;
            return this;
        }

        public Callback unwrap() {
            Callback callback = this.mCallback;
            this.mCallback = null;
            return callback;
        }

        public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }

        public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }
    }

    static abstract class DrawableContainerState extends ConstantState {
        boolean mAutoMirrored;
        boolean mCanConstantState;
        int mChangingConfigurations;
        boolean mCheckedConstantSize;
        boolean mCheckedConstantState;
        boolean mCheckedOpacity;
        boolean mCheckedPadding;
        boolean mCheckedStateful;
        int mChildrenChangingConfigurations;
        ColorFilter mColorFilter;
        int mConstantHeight;
        int mConstantMinimumHeight;
        int mConstantMinimumWidth;
        Rect mConstantPadding;
        boolean mConstantSize;
        int mConstantWidth;
        int mDensity = 160;
        boolean mDither;
        SparseArray<ConstantState> mDrawableFutures;
        Drawable[] mDrawables;
        int mEnterFadeDuration;
        int mExitFadeDuration;
        boolean mHasColorFilter;
        boolean mHasTintList;
        boolean mHasTintMode;
        int mLayoutDirection;
        boolean mMutated;
        int mNumChildren;
        int mOpacity;
        final DrawableContainer mOwner;
        Resources mSourceRes;
        boolean mStateful;
        ColorStateList mTintList;
        Mode mTintMode;
        boolean mVariablePadding;

        public synchronized boolean canConstantState() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x002d in {6, 14, 15, 19, 22} preds:[]
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
            monitor-enter(r6);
            r0 = r6.mCheckedConstantState;	 Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0009;	 Catch:{ all -> 0x002a }
        L_0x0005:
            r0 = r6.mCanConstantState;	 Catch:{ all -> 0x002a }
            monitor-exit(r6);
            return r0;
        L_0x0009:
            r6.createAllFutures();	 Catch:{ all -> 0x002a }
            r0 = 1;	 Catch:{ all -> 0x002a }
            r6.mCheckedConstantState = r0;	 Catch:{ all -> 0x002a }
            r1 = r6.mNumChildren;	 Catch:{ all -> 0x002a }
            r2 = r6.mDrawables;	 Catch:{ all -> 0x002a }
            r3 = 0;	 Catch:{ all -> 0x002a }
            r4 = 0;	 Catch:{ all -> 0x002a }
        L_0x0015:
            if (r4 >= r1) goto L_0x0026;	 Catch:{ all -> 0x002a }
        L_0x0017:
            r5 = r2[r4];	 Catch:{ all -> 0x002a }
            r5 = r5.getConstantState();	 Catch:{ all -> 0x002a }
            if (r5 != 0) goto L_0x0023;	 Catch:{ all -> 0x002a }
        L_0x001f:
            r6.mCanConstantState = r3;	 Catch:{ all -> 0x002a }
            monitor-exit(r6);
            return r3;
        L_0x0023:
            r4 = r4 + 1;
            goto L_0x0015;
        L_0x0026:
            r6.mCanConstantState = r0;	 Catch:{ all -> 0x002a }
            monitor-exit(r6);
            return r0;
        L_0x002a:
            r0 = move-exception;
            monitor-exit(r6);
            throw r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.graphics.drawable.DrawableContainer.DrawableContainerState.canConstantState():boolean");
        }

        DrawableContainerState(DrawableContainerState drawableContainerState, DrawableContainer drawableContainer, Resources resources) {
            int i = 0;
            this.mVariablePadding = false;
            this.mConstantSize = false;
            this.mDither = DrawableContainer.DEFAULT_DITHER;
            this.mEnterFadeDuration = 0;
            this.mExitFadeDuration = 0;
            this.mOwner = drawableContainer;
            drawableContainer = resources != null ? resources : drawableContainerState != null ? drawableContainerState.mSourceRes : null;
            this.mSourceRes = drawableContainer;
            this.mDensity = DrawableContainer.resolveDensity(resources, drawableContainerState != null ? drawableContainerState.mDensity : null);
            if (drawableContainerState != null) {
                this.mChangingConfigurations = drawableContainerState.mChangingConfigurations;
                this.mChildrenChangingConfigurations = drawableContainerState.mChildrenChangingConfigurations;
                this.mCheckedConstantState = DrawableContainer.DEFAULT_DITHER;
                this.mCanConstantState = DrawableContainer.DEFAULT_DITHER;
                this.mVariablePadding = drawableContainerState.mVariablePadding;
                this.mConstantSize = drawableContainerState.mConstantSize;
                this.mDither = drawableContainerState.mDither;
                this.mMutated = drawableContainerState.mMutated;
                this.mLayoutDirection = drawableContainerState.mLayoutDirection;
                this.mEnterFadeDuration = drawableContainerState.mEnterFadeDuration;
                this.mExitFadeDuration = drawableContainerState.mExitFadeDuration;
                this.mAutoMirrored = drawableContainerState.mAutoMirrored;
                this.mColorFilter = drawableContainerState.mColorFilter;
                this.mHasColorFilter = drawableContainerState.mHasColorFilter;
                this.mTintList = drawableContainerState.mTintList;
                this.mTintMode = drawableContainerState.mTintMode;
                this.mHasTintList = drawableContainerState.mHasTintList;
                this.mHasTintMode = drawableContainerState.mHasTintMode;
                if (drawableContainerState.mDensity == this.mDensity) {
                    if (drawableContainerState.mCheckedPadding != null) {
                        this.mConstantPadding = new Rect(drawableContainerState.mConstantPadding);
                        this.mCheckedPadding = DrawableContainer.DEFAULT_DITHER;
                    }
                    if (drawableContainerState.mCheckedConstantSize != null) {
                        this.mConstantWidth = drawableContainerState.mConstantWidth;
                        this.mConstantHeight = drawableContainerState.mConstantHeight;
                        this.mConstantMinimumWidth = drawableContainerState.mConstantMinimumWidth;
                        this.mConstantMinimumHeight = drawableContainerState.mConstantMinimumHeight;
                        this.mCheckedConstantSize = DrawableContainer.DEFAULT_DITHER;
                    }
                }
                if (drawableContainerState.mCheckedOpacity != null) {
                    this.mOpacity = drawableContainerState.mOpacity;
                    this.mCheckedOpacity = DrawableContainer.DEFAULT_DITHER;
                }
                if (drawableContainerState.mCheckedStateful != null) {
                    this.mStateful = drawableContainerState.mStateful;
                    this.mCheckedStateful = DrawableContainer.DEFAULT_DITHER;
                }
                drawableContainer = drawableContainerState.mDrawables;
                this.mDrawables = new Drawable[drawableContainer.length];
                this.mNumChildren = drawableContainerState.mNumChildren;
                drawableContainerState = drawableContainerState.mDrawableFutures;
                if (drawableContainerState != null) {
                    this.mDrawableFutures = drawableContainerState.clone();
                } else {
                    this.mDrawableFutures = new SparseArray(this.mNumChildren);
                }
                drawableContainerState = this.mNumChildren;
                while (i < drawableContainerState) {
                    if (drawableContainer[i] != null) {
                        resources = drawableContainer[i].getConstantState();
                        if (resources != null) {
                            this.mDrawableFutures.put(i, resources);
                        } else {
                            this.mDrawables[i] = drawableContainer[i];
                        }
                    }
                    i++;
                }
                return;
            }
            this.mDrawables = new Drawable[10];
            this.mNumChildren = 0;
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations | this.mChildrenChangingConfigurations;
        }

        public final int addChild(Drawable drawable) {
            int i = this.mNumChildren;
            if (i >= this.mDrawables.length) {
                growArray(i, i + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, DrawableContainer.DEFAULT_DITHER);
            drawable.setCallback(this.mOwner);
            this.mDrawables[i] = drawable;
            this.mNumChildren++;
            this.mChildrenChangingConfigurations = drawable.getChangingConfigurations() | this.mChildrenChangingConfigurations;
            invalidateCache();
            this.mConstantPadding = null;
            this.mCheckedPadding = false;
            this.mCheckedConstantSize = false;
            this.mCheckedConstantState = false;
            return i;
        }

        void invalidateCache() {
            this.mCheckedOpacity = false;
            this.mCheckedStateful = false;
        }

        final int getCapacity() {
            return this.mDrawables.length;
        }

        private void createAllFutures() {
            SparseArray sparseArray = this.mDrawableFutures;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    this.mDrawables[this.mDrawableFutures.keyAt(i)] = prepareDrawable(((ConstantState) this.mDrawableFutures.valueAt(i)).newDrawable(this.mSourceRes));
                }
                this.mDrawableFutures = null;
            }
        }

        private Drawable prepareDrawable(Drawable drawable) {
            if (VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(this.mLayoutDirection);
            }
            drawable = drawable.mutate();
            drawable.setCallback(this.mOwner);
            return drawable;
        }

        public final int getChildCount() {
            return this.mNumChildren;
        }

        public final Drawable getChild(int i) {
            Drawable drawable = this.mDrawables[i];
            if (drawable != null) {
                return drawable;
            }
            SparseArray sparseArray = this.mDrawableFutures;
            if (sparseArray != null) {
                int indexOfKey = sparseArray.indexOfKey(i);
                if (indexOfKey >= 0) {
                    Drawable prepareDrawable = prepareDrawable(((ConstantState) this.mDrawableFutures.valueAt(indexOfKey)).newDrawable(this.mSourceRes));
                    this.mDrawables[i] = prepareDrawable;
                    this.mDrawableFutures.removeAt(indexOfKey);
                    if (this.mDrawableFutures.size() == 0) {
                        this.mDrawableFutures = null;
                    }
                    return prepareDrawable;
                }
            }
            return null;
        }

        final boolean setLayoutDirection(int i, int i2) {
            int i3 = this.mNumChildren;
            Drawable[] drawableArr = this.mDrawables;
            boolean z = false;
            for (int i4 = 0; i4 < i3; i4++) {
                if (drawableArr[i4] != null) {
                    boolean layoutDirection = VERSION.SDK_INT >= 23 ? drawableArr[i4].setLayoutDirection(i) : false;
                    if (i4 == i2) {
                        z = layoutDirection;
                    }
                }
            }
            this.mLayoutDirection = i;
            return z;
        }

        final void updateDensity(Resources resources) {
            if (resources != null) {
                this.mSourceRes = resources;
                resources = DrawableContainer.resolveDensity(resources, this.mDensity);
                int i = this.mDensity;
                this.mDensity = resources;
                if (i != resources) {
                    this.mCheckedConstantSize = false;
                    this.mCheckedPadding = false;
                }
            }
        }

        @RequiresApi(21)
        final void applyTheme(Theme theme) {
            if (theme != null) {
                createAllFutures();
                int i = this.mNumChildren;
                Drawable[] drawableArr = this.mDrawables;
                int i2 = 0;
                while (i2 < i) {
                    if (drawableArr[i2] != null && drawableArr[i2].canApplyTheme()) {
                        drawableArr[i2].applyTheme(theme);
                        this.mChildrenChangingConfigurations |= drawableArr[i2].getChangingConfigurations();
                    }
                    i2++;
                }
                updateDensity(theme.getResources());
            }
        }

        @RequiresApi(21)
        public boolean canApplyTheme() {
            int i = this.mNumChildren;
            Drawable[] drawableArr = this.mDrawables;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                if (drawable == null) {
                    ConstantState constantState = (ConstantState) this.mDrawableFutures.get(i2);
                    if (constantState != null && constantState.canApplyTheme()) {
                        return DrawableContainer.DEFAULT_DITHER;
                    }
                } else if (drawable.canApplyTheme()) {
                    return DrawableContainer.DEFAULT_DITHER;
                }
            }
            return false;
        }

        void mutate() {
            int i = this.mNumChildren;
            Drawable[] drawableArr = this.mDrawables;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2] != null) {
                    drawableArr[i2].mutate();
                }
            }
            this.mMutated = DrawableContainer.DEFAULT_DITHER;
        }

        final void clearMutated() {
            this.mMutated = false;
        }

        public final void setVariablePadding(boolean z) {
            this.mVariablePadding = z;
        }

        public final Rect getConstantPadding() {
            if (this.mVariablePadding) {
                return null;
            }
            if (this.mConstantPadding == null) {
                if (!this.mCheckedPadding) {
                    createAllFutures();
                    Rect rect = new Rect();
                    int i = this.mNumChildren;
                    Drawable[] drawableArr = this.mDrawables;
                    Rect rect2 = null;
                    for (int i2 = 0; i2 < i; i2++) {
                        if (drawableArr[i2].getPadding(rect)) {
                            if (rect2 == null) {
                                rect2 = new Rect(0, 0, 0, 0);
                            }
                            if (rect.left > rect2.left) {
                                rect2.left = rect.left;
                            }
                            if (rect.top > rect2.top) {
                                rect2.top = rect.top;
                            }
                            if (rect.right > rect2.right) {
                                rect2.right = rect.right;
                            }
                            if (rect.bottom > rect2.bottom) {
                                rect2.bottom = rect.bottom;
                            }
                        }
                    }
                    this.mCheckedPadding = DrawableContainer.DEFAULT_DITHER;
                    this.mConstantPadding = rect2;
                    return rect2;
                }
            }
            return this.mConstantPadding;
        }

        public final void setConstantSize(boolean z) {
            this.mConstantSize = z;
        }

        public final boolean isConstantSize() {
            return this.mConstantSize;
        }

        public final int getConstantWidth() {
            if (!this.mCheckedConstantSize) {
                computeConstantSize();
            }
            return this.mConstantWidth;
        }

        public final int getConstantHeight() {
            if (!this.mCheckedConstantSize) {
                computeConstantSize();
            }
            return this.mConstantHeight;
        }

        public final int getConstantMinimumWidth() {
            if (!this.mCheckedConstantSize) {
                computeConstantSize();
            }
            return this.mConstantMinimumWidth;
        }

        public final int getConstantMinimumHeight() {
            if (!this.mCheckedConstantSize) {
                computeConstantSize();
            }
            return this.mConstantMinimumHeight;
        }

        protected void computeConstantSize() {
            this.mCheckedConstantSize = DrawableContainer.DEFAULT_DITHER;
            createAllFutures();
            int i = this.mNumChildren;
            Drawable[] drawableArr = this.mDrawables;
            this.mConstantHeight = -1;
            this.mConstantWidth = -1;
            int i2 = 0;
            this.mConstantMinimumHeight = 0;
            this.mConstantMinimumWidth = 0;
            while (i2 < i) {
                Drawable drawable = drawableArr[i2];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.mConstantWidth) {
                    this.mConstantWidth = intrinsicWidth;
                }
                intrinsicWidth = drawable.getIntrinsicHeight();
                if (intrinsicWidth > this.mConstantHeight) {
                    this.mConstantHeight = intrinsicWidth;
                }
                intrinsicWidth = drawable.getMinimumWidth();
                if (intrinsicWidth > this.mConstantMinimumWidth) {
                    this.mConstantMinimumWidth = intrinsicWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.mConstantMinimumHeight) {
                    this.mConstantMinimumHeight = minimumHeight;
                }
                i2++;
            }
        }

        public final void setEnterFadeDuration(int i) {
            this.mEnterFadeDuration = i;
        }

        public final int getEnterFadeDuration() {
            return this.mEnterFadeDuration;
        }

        public final void setExitFadeDuration(int i) {
            this.mExitFadeDuration = i;
        }

        public final int getExitFadeDuration() {
            return this.mExitFadeDuration;
        }

        public final int getOpacity() {
            if (this.mCheckedOpacity) {
                return this.mOpacity;
            }
            createAllFutures();
            int i = this.mNumChildren;
            Drawable[] drawableArr = this.mDrawables;
            int opacity = i > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i2 = 1; i2 < i; i2++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i2].getOpacity());
            }
            this.mOpacity = opacity;
            this.mCheckedOpacity = DrawableContainer.DEFAULT_DITHER;
            return opacity;
        }

        public final boolean isStateful() {
            if (this.mCheckedStateful) {
                return this.mStateful;
            }
            createAllFutures();
            int i = this.mNumChildren;
            Drawable[] drawableArr = this.mDrawables;
            boolean z = false;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2].isStateful()) {
                    z = DrawableContainer.DEFAULT_DITHER;
                    break;
                }
            }
            this.mStateful = z;
            this.mCheckedStateful = DrawableContainer.DEFAULT_DITHER;
            return z;
        }

        public void growArray(int i, int i2) {
            i2 = new Drawable[i2];
            System.arraycopy(this.mDrawables, 0, i2, 0, i);
            this.mDrawables = i2;
        }
    }

    DrawableContainer() {
    }

    public void draw(@NonNull Canvas canvas) {
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        drawable = this.mLastDrawable;
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.mDrawableContainerState.getChangingConfigurations();
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(23)
    private boolean needsMirroring() {
        return (isAutoMirrored() && getLayoutDirection() == 1) ? DEFAULT_DITHER : false;
    }

    public boolean getPadding(@NonNull Rect rect) {
        boolean z;
        Rect constantPadding = this.mDrawableContainerState.getConstantPadding();
        if (constantPadding != null) {
            rect.set(constantPadding);
            z = (constantPadding.right | ((constantPadding.left | constantPadding.top) | constantPadding.bottom)) != 0 ? DEFAULT_DITHER : false;
        } else {
            Drawable drawable = this.mCurrDrawable;
            z = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (needsMirroring()) {
            int i = rect.left;
            rect.left = rect.right;
            rect.right = i;
        }
        return z;
    }

    @RequiresApi(21)
    public void getOutline(@NonNull Outline outline) {
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    public void setAlpha(int i) {
        if (!this.mHasAlpha || this.mAlpha != i) {
            this.mHasAlpha = DEFAULT_DITHER;
            this.mAlpha = i;
            Drawable drawable = this.mCurrDrawable;
            if (drawable == null) {
                return;
            }
            if (this.mEnterAnimationEnd == 0) {
                drawable.setAlpha(i);
            } else {
                animate(0);
            }
        }
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void setDither(boolean z) {
        if (this.mDrawableContainerState.mDither != z) {
            DrawableContainerState drawableContainerState = this.mDrawableContainerState;
            drawableContainerState.mDither = z;
            z = this.mCurrDrawable;
            if (z) {
                z.setDither(drawableContainerState.mDither);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        drawableContainerState.mHasColorFilter = DEFAULT_DITHER;
        if (drawableContainerState.mColorFilter != colorFilter) {
            this.mDrawableContainerState.mColorFilter = colorFilter;
            Drawable drawable = this.mCurrDrawable;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        drawableContainerState.mHasTintList = DEFAULT_DITHER;
        if (drawableContainerState.mTintList != colorStateList) {
            this.mDrawableContainerState.mTintList = colorStateList;
            DrawableCompat.setTintList(this.mCurrDrawable, colorStateList);
        }
    }

    public void setTintMode(@NonNull Mode mode) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        drawableContainerState.mHasTintMode = DEFAULT_DITHER;
        if (drawableContainerState.mTintMode != mode) {
            this.mDrawableContainerState.mTintMode = mode;
            DrawableCompat.setTintMode(this.mCurrDrawable, mode);
        }
    }

    public void setEnterFadeDuration(int i) {
        this.mDrawableContainerState.mEnterFadeDuration = i;
    }

    public void setExitFadeDuration(int i) {
        this.mDrawableContainerState.mExitFadeDuration = i;
    }

    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        drawable = this.mCurrDrawable;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    public boolean isStateful() {
        return this.mDrawableContainerState.isStateful();
    }

    public void setAutoMirrored(boolean z) {
        if (this.mDrawableContainerState.mAutoMirrored != z) {
            DrawableContainerState drawableContainerState = this.mDrawableContainerState;
            drawableContainerState.mAutoMirrored = z;
            z = this.mCurrDrawable;
            if (z) {
                DrawableCompat.setAutoMirrored(z, drawableContainerState.mAutoMirrored);
            }
        }
    }

    public boolean isAutoMirrored() {
        return this.mDrawableContainerState.mAutoMirrored;
    }

    public void jumpToCurrentState() {
        Object obj;
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.mLastDrawable = null;
            this.mLastIndex = -1;
            obj = 1;
        } else {
            obj = null;
        }
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.mHasAlpha) {
                this.mCurrDrawable.setAlpha(this.mAlpha);
            }
        }
        if (this.mExitAnimationEnd != 0) {
            this.mExitAnimationEnd = 0;
            obj = 1;
        }
        if (this.mEnterAnimationEnd != 0) {
            this.mEnterAnimationEnd = 0;
            obj = 1;
        }
        if (obj != null) {
            invalidateSelf();
        }
    }

    public void setHotspot(float f, float f2) {
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            DrawableCompat.setHotspot(drawable, f, f2);
        }
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect rect = this.mHotspotBounds;
        if (rect == null) {
            this.mHotspotBounds = new Rect(i, i2, i3, i4);
        } else {
            rect.set(i, i2, i3, i4);
        }
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            DrawableCompat.setHotspotBounds(drawable, i, i2, i3, i4);
        }
    }

    public void getHotspotBounds(@NonNull Rect rect) {
        Rect rect2 = this.mHotspotBounds;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    protected boolean onStateChange(int[] iArr) {
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        drawable = this.mCurrDrawable;
        return drawable != null ? drawable.setState(iArr) : null;
    }

    protected boolean onLevelChange(int i) {
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        drawable = this.mCurrDrawable;
        return drawable != null ? drawable.setLevel(i) : false;
    }

    public boolean onLayoutDirectionChanged(int i) {
        return this.mDrawableContainerState.setLayoutDirection(i, getCurrentIndex());
    }

    public int getIntrinsicWidth() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantWidth();
        }
        Drawable drawable = this.mCurrDrawable;
        return drawable != null ? drawable.getIntrinsicWidth() : -1;
    }

    public int getIntrinsicHeight() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantHeight();
        }
        Drawable drawable = this.mCurrDrawable;
        return drawable != null ? drawable.getIntrinsicHeight() : -1;
    }

    public int getMinimumWidth() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantMinimumWidth();
        }
        Drawable drawable = this.mCurrDrawable;
        return drawable != null ? drawable.getMinimumWidth() : 0;
    }

    public int getMinimumHeight() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantMinimumHeight();
        }
        Drawable drawable = this.mCurrDrawable;
        return drawable != null ? drawable.getMinimumHeight() : 0;
    }

    public void invalidateDrawable(@NonNull Drawable drawable) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState != null) {
            drawableContainerState.invalidateCache();
        }
        if (drawable == this.mCurrDrawable && getCallback() != null) {
            getCallback().invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
        if (drawable == this.mCurrDrawable && getCallback() != null) {
            getCallback().scheduleDrawable(this, runnable, j);
        }
    }

    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        if (drawable == this.mCurrDrawable && getCallback() != null) {
            getCallback().unscheduleDrawable(this, runnable);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        drawable = this.mCurrDrawable;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        return visible;
    }

    public int getOpacity() {
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            if (drawable.isVisible()) {
                return this.mDrawableContainerState.getOpacity();
            }
        }
        return -2;
    }

    void setCurrentIndex(int i) {
        selectDrawable(i);
    }

    int getCurrentIndex() {
        return this.mCurIndex;
    }

    boolean selectDrawable(int i) {
        if (i == this.mCurIndex) {
            return false;
        }
        Drawable drawable;
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.mDrawableContainerState.mExitFadeDuration > 0) {
            drawable = this.mLastDrawable;
            if (drawable != null) {
                drawable.setVisible(false, false);
            }
            drawable = this.mCurrDrawable;
            if (drawable != null) {
                this.mLastDrawable = drawable;
                this.mLastIndex = this.mCurIndex;
                this.mExitAnimationEnd = ((long) this.mDrawableContainerState.mExitFadeDuration) + uptimeMillis;
            } else {
                this.mLastDrawable = null;
                this.mLastIndex = -1;
                this.mExitAnimationEnd = 0;
            }
        } else {
            drawable = this.mCurrDrawable;
            if (drawable != null) {
                drawable.setVisible(false, false);
            }
        }
        if (i < 0 || i >= this.mDrawableContainerState.mNumChildren) {
            this.mCurrDrawable = null;
            this.mCurIndex = -1;
        } else {
            drawable = this.mDrawableContainerState.getChild(i);
            this.mCurrDrawable = drawable;
            this.mCurIndex = i;
            if (drawable != null) {
                if (this.mDrawableContainerState.mEnterFadeDuration > 0) {
                    this.mEnterAnimationEnd = uptimeMillis + ((long) this.mDrawableContainerState.mEnterFadeDuration);
                }
                initializeDrawableForDisplay(drawable);
            }
        }
        if (!(this.mEnterAnimationEnd == 0 && this.mExitAnimationEnd == 0)) {
            Runnable runnable = this.mAnimationRunnable;
            if (runnable == null) {
                this.mAnimationRunnable = new C02961();
            } else {
                unscheduleSelf(runnable);
            }
            animate(DEFAULT_DITHER);
        }
        invalidateSelf();
        return DEFAULT_DITHER;
    }

    private void initializeDrawableForDisplay(Drawable drawable) {
        if (this.mBlockInvalidateCallback == null) {
            this.mBlockInvalidateCallback = new BlockInvalidateCallback();
        }
        drawable.setCallback(this.mBlockInvalidateCallback.wrap(drawable.getCallback()));
        try {
            if (this.mDrawableContainerState.mEnterFadeDuration <= 0 && this.mHasAlpha) {
                drawable.setAlpha(this.mAlpha);
            }
            if (this.mDrawableContainerState.mHasColorFilter) {
                drawable.setColorFilter(this.mDrawableContainerState.mColorFilter);
            } else {
                if (this.mDrawableContainerState.mHasTintList) {
                    DrawableCompat.setTintList(drawable, this.mDrawableContainerState.mTintList);
                }
                if (this.mDrawableContainerState.mHasTintMode) {
                    DrawableCompat.setTintMode(drawable, this.mDrawableContainerState.mTintMode);
                }
            }
            drawable.setVisible(isVisible(), DEFAULT_DITHER);
            drawable.setDither(this.mDrawableContainerState.mDither);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            if (VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            if (VERSION.SDK_INT >= 19) {
                drawable.setAutoMirrored(this.mDrawableContainerState.mAutoMirrored);
            }
            Rect rect = this.mHotspotBounds;
            if (VERSION.SDK_INT >= 21 && rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
            drawable.setCallback(this.mBlockInvalidateCallback.unwrap());
        } catch (Throwable th) {
            drawable.setCallback(this.mBlockInvalidateCallback.unwrap());
        }
    }

    void animate(boolean z) {
        boolean z2;
        Drawable drawable;
        boolean z3 = DEFAULT_DITHER;
        this.mHasAlpha = DEFAULT_DITHER;
        long uptimeMillis = SystemClock.uptimeMillis();
        Drawable drawable2 = this.mCurrDrawable;
        long j;
        if (drawable2 != null) {
            long j2 = this.mEnterAnimationEnd;
            if (j2 != 0) {
                if (j2 <= uptimeMillis) {
                    drawable2.setAlpha(this.mAlpha);
                    this.mEnterAnimationEnd = 0;
                } else {
                    this.mCurrDrawable.setAlpha(((255 - (((int) ((j2 - uptimeMillis) * 255)) / this.mDrawableContainerState.mEnterFadeDuration)) * this.mAlpha) / 255);
                    z2 = DEFAULT_DITHER;
                    drawable = this.mLastDrawable;
                    if (drawable == null) {
                        j = this.mExitAnimationEnd;
                        if (j != 0) {
                            if (j > uptimeMillis) {
                                drawable.setVisible(false, false);
                                this.mLastDrawable = null;
                                this.mLastIndex = -1;
                                this.mExitAnimationEnd = 0;
                            } else {
                                this.mLastDrawable.setAlpha(((((int) ((j - uptimeMillis) * 255)) / this.mDrawableContainerState.mExitFadeDuration) * this.mAlpha) / 255);
                                if (z && r0) {
                                    scheduleSelf(this.mAnimationRunnable, uptimeMillis + 16);
                                    return;
                                }
                            }
                        }
                    }
                    this.mExitAnimationEnd = 0;
                    z3 = z2;
                    if (z) {
                    }
                }
            }
        }
        this.mEnterAnimationEnd = 0;
        z2 = false;
        drawable = this.mLastDrawable;
        if (drawable == null) {
            this.mExitAnimationEnd = 0;
        } else {
            j = this.mExitAnimationEnd;
            if (j != 0) {
                if (j > uptimeMillis) {
                    this.mLastDrawable.setAlpha(((((int) ((j - uptimeMillis) * 255)) / this.mDrawableContainerState.mExitFadeDuration) * this.mAlpha) / 255);
                    if (z) {
                    }
                }
                drawable.setVisible(false, false);
                this.mLastDrawable = null;
                this.mLastIndex = -1;
                this.mExitAnimationEnd = 0;
            }
        }
        z3 = z2;
        if (z) {
        }
    }

    @NonNull
    public Drawable getCurrent() {
        return this.mCurrDrawable;
    }

    final void updateDensity(Resources resources) {
        this.mDrawableContainerState.updateDensity(resources);
    }

    @RequiresApi(21)
    public void applyTheme(@NonNull Theme theme) {
        this.mDrawableContainerState.applyTheme(theme);
    }

    @RequiresApi(21)
    public boolean canApplyTheme() {
        return this.mDrawableContainerState.canApplyTheme();
    }

    public final ConstantState getConstantState() {
        if (!this.mDrawableContainerState.canConstantState()) {
            return null;
        }
        this.mDrawableContainerState.mChangingConfigurations = getChangingConfigurations();
        return this.mDrawableContainerState;
    }

    @NonNull
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            DrawableContainerState cloneConstantState = cloneConstantState();
            cloneConstantState.mutate();
            setConstantState(cloneConstantState);
            this.mMutated = DEFAULT_DITHER;
        }
        return this;
    }

    DrawableContainerState cloneConstantState() {
        return this.mDrawableContainerState;
    }

    void clearMutated() {
        this.mDrawableContainerState.clearMutated();
        this.mMutated = false;
    }

    protected void setConstantState(DrawableContainerState drawableContainerState) {
        this.mDrawableContainerState = drawableContainerState;
        int i = this.mCurIndex;
        if (i >= 0) {
            this.mCurrDrawable = drawableContainerState.getChild(i);
            drawableContainerState = this.mCurrDrawable;
            if (drawableContainerState != null) {
                initializeDrawableForDisplay(drawableContainerState);
            }
        }
        this.mLastIndex = -1;
        this.mLastDrawable = null;
    }

    static int resolveDensity(@Nullable Resources resources, int i) {
        if (resources != null) {
            i = resources.getDisplayMetrics().densityDpi;
        }
        return i == 0 ? 160 : i;
    }
}
