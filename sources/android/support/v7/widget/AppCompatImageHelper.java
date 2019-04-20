package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.appcompat.C0291R;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ImageView;

@RestrictTo({Scope.LIBRARY_GROUP})
public class AppCompatImageHelper {
    private TintInfo mImageTint;
    private TintInfo mInternalImageTint;
    private TintInfo mTmpInfo;
    private final ImageView mView;

    public AppCompatImageHelper(ImageView imageView) {
        this.mView = imageView;
    }

    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        attributeSet = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet, C0291R.styleable.AppCompatImageView, i, 0);
        try {
            i = this.mView.getDrawable();
            if (i == 0) {
                int resourceId = attributeSet.getResourceId(C0291R.styleable.AppCompatImageView_srcCompat, -1);
                if (resourceId != -1) {
                    i = AppCompatResources.getDrawable(this.mView.getContext(), resourceId);
                    if (i != 0) {
                        this.mView.setImageDrawable(i);
                    }
                }
            }
            if (i != 0) {
                DrawableUtils.fixDrawable(i);
            }
            if (attributeSet.hasValue(C0291R.styleable.AppCompatImageView_tint) != 0) {
                ImageViewCompat.setImageTintList(this.mView, attributeSet.getColorStateList(C0291R.styleable.AppCompatImageView_tint));
            }
            if (attributeSet.hasValue(C0291R.styleable.AppCompatImageView_tintMode) != 0) {
                ImageViewCompat.setImageTintMode(this.mView, DrawableUtils.parseTintMode(attributeSet.getInt(C0291R.styleable.AppCompatImageView_tintMode, -1), null));
            }
            attributeSet.recycle();
        } catch (Throwable th) {
            attributeSet.recycle();
        }
    }

    public void setImageResource(int i) {
        if (i != 0) {
            i = AppCompatResources.getDrawable(this.mView.getContext(), i);
            if (i != 0) {
                DrawableUtils.fixDrawable(i);
            }
            this.mView.setImageDrawable(i);
        } else {
            this.mView.setImageDrawable(null);
        }
        applySupportImageTint();
    }

    boolean hasOverlappingRendering() {
        return VERSION.SDK_INT < 21 || !(this.mView.getBackground() instanceof RippleDrawable);
    }

    void setSupportImageTintList(ColorStateList colorStateList) {
        if (this.mImageTint == null) {
            this.mImageTint = new TintInfo();
        }
        TintInfo tintInfo = this.mImageTint;
        tintInfo.mTintList = colorStateList;
        tintInfo.mHasTintList = true;
        applySupportImageTint();
    }

    ColorStateList getSupportImageTintList() {
        TintInfo tintInfo = this.mImageTint;
        return tintInfo != null ? tintInfo.mTintList : null;
    }

    void setSupportImageTintMode(Mode mode) {
        if (this.mImageTint == null) {
            this.mImageTint = new TintInfo();
        }
        TintInfo tintInfo = this.mImageTint;
        tintInfo.mTintMode = mode;
        tintInfo.mHasTintMode = true;
        applySupportImageTint();
    }

    Mode getSupportImageTintMode() {
        TintInfo tintInfo = this.mImageTint;
        return tintInfo != null ? tintInfo.mTintMode : null;
    }

    void applySupportImageTint() {
        Drawable drawable = this.mView.getDrawable();
        if (drawable != null) {
            DrawableUtils.fixDrawable(drawable);
        }
        if (drawable != null && (!shouldApplyFrameworkTintUsingColorFilter() || !applyFrameworkTintUsingColorFilter(drawable))) {
            TintInfo tintInfo = this.mImageTint;
            if (tintInfo != null) {
                AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
            } else {
                tintInfo = this.mInternalImageTint;
                if (tintInfo != null) {
                    AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
                }
            }
        }
    }

    void setInternalImageTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.mInternalImageTint == null) {
                this.mInternalImageTint = new TintInfo();
            }
            TintInfo tintInfo = this.mInternalImageTint;
            tintInfo.mTintList = colorStateList;
            tintInfo.mHasTintList = true;
        } else {
            this.mInternalImageTint = null;
        }
        applySupportImageTint();
    }

    private boolean shouldApplyFrameworkTintUsingColorFilter() {
        int i = VERSION.SDK_INT;
        boolean z = true;
        if (i <= 21) {
            return i == 21;
        } else {
            if (this.mInternalImageTint == null) {
                z = false;
            }
            return z;
        }
    }

    private boolean applyFrameworkTintUsingColorFilter(@NonNull Drawable drawable) {
        if (this.mTmpInfo == null) {
            this.mTmpInfo = new TintInfo();
        }
        TintInfo tintInfo = this.mTmpInfo;
        tintInfo.clear();
        ColorStateList imageTintList = ImageViewCompat.getImageTintList(this.mView);
        if (imageTintList != null) {
            tintInfo.mHasTintList = true;
            tintInfo.mTintList = imageTintList;
        }
        Mode imageTintMode = ImageViewCompat.getImageTintMode(this.mView);
        if (imageTintMode != null) {
            tintInfo.mHasTintMode = true;
            tintInfo.mTintMode = imageTintMode;
        }
        if (!tintInfo.mHasTintList) {
            if (!tintInfo.mHasTintMode) {
                return null;
            }
        }
        AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
        return true;
    }
}
