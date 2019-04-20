package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StyleRes;
import android.support.design.C0026R;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.internal.ThemeEnforcement;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.appcompat.C0291R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuItemImpl;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;

public class NavigationView extends ScrimInsetsFrameLayout {
    private static final int[] CHECKED_STATE_SET = new int[]{16842912};
    private static final int[] DISABLED_STATE_SET = new int[]{-16842910};
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    OnNavigationItemSelectedListener listener;
    private final int maxWidth;
    private final NavigationMenu menu;
    private MenuInflater menuInflater;
    private final NavigationMenuPresenter presenter;

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(@NonNull MenuItem menuItem);
    }

    /* renamed from: android.support.design.widget.NavigationView$1 */
    class C04741 implements Callback {
        public void onMenuModeChange(MenuBuilder menuBuilder) {
        }

        C04741() {
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            return (NavigationView.this.listener == null || NavigationView.this.listener.onNavigationItemSelected(menuItem) == null) ? null : true;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new C00721();
        public Bundle menuState;

        /* renamed from: android.support.design.widget.NavigationView$SavedState$1 */
        static class C00721 implements ClassLoaderCreator<SavedState> {
            C00721() {
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.menuState = parcel.readBundle(classLoader);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.menuState);
        }
    }

    public NavigationView(Context context) {
        this(context, null);
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0026R.attr.navigationViewStyle);
    }

    public NavigationView(Context context, AttributeSet attributeSet, int i) {
        int resourceId;
        Object obj;
        super(context, attributeSet, i);
        this.presenter = new NavigationMenuPresenter();
        this.menu = new NavigationMenu(context);
        attributeSet = ThemeEnforcement.obtainTintedStyledAttributes(context, attributeSet, C0026R.styleable.NavigationView, i, C0026R.style.Widget_Design_NavigationView, new int[0]);
        ViewCompat.setBackground(this, attributeSet.getDrawable(C0026R.styleable.NavigationView_android_background));
        if (attributeSet.hasValue(C0026R.styleable.NavigationView_elevation) != 0) {
            ViewCompat.setElevation(this, (float) attributeSet.getDimensionPixelSize(C0026R.styleable.NavigationView_elevation, 0));
        }
        ViewCompat.setFitsSystemWindows(this, attributeSet.getBoolean(C0026R.styleable.NavigationView_android_fitsSystemWindows, false));
        this.maxWidth = attributeSet.getDimensionPixelSize(C0026R.styleable.NavigationView_android_maxWidth, 0);
        if (attributeSet.hasValue(C0026R.styleable.NavigationView_itemIconTint) != 0) {
            i = attributeSet.getColorStateList(C0026R.styleable.NavigationView_itemIconTint);
        } else {
            i = createDefaultColorStateList(16842808);
        }
        if (attributeSet.hasValue(C0026R.styleable.NavigationView_itemTextAppearance)) {
            resourceId = attributeSet.getResourceId(C0026R.styleable.NavigationView_itemTextAppearance, 0);
            obj = 1;
        } else {
            obj = null;
            resourceId = 0;
        }
        ColorStateList colorStateList = null;
        if (attributeSet.hasValue(C0026R.styleable.NavigationView_itemTextColor)) {
            colorStateList = attributeSet.getColorStateList(C0026R.styleable.NavigationView_itemTextColor);
        }
        if (obj == null && r4 == null) {
            colorStateList = createDefaultColorStateList(16842806);
        }
        Drawable drawable = attributeSet.getDrawable(C0026R.styleable.NavigationView_itemBackground);
        if (attributeSet.hasValue(C0026R.styleable.NavigationView_itemHorizontalPadding)) {
            this.presenter.setItemHorizontalPadding(attributeSet.getDimensionPixelSize(C0026R.styleable.NavigationView_itemHorizontalPadding, 0));
        }
        int dimensionPixelSize = attributeSet.getDimensionPixelSize(C0026R.styleable.NavigationView_itemIconPadding, 0);
        this.menu.setCallback(new C04741());
        this.presenter.setId(1);
        this.presenter.initForMenu(context, this.menu);
        this.presenter.setItemIconTintList(i);
        if (obj != null) {
            this.presenter.setItemTextAppearance(resourceId);
        }
        this.presenter.setItemTextColor(colorStateList);
        this.presenter.setItemBackground(drawable);
        this.presenter.setItemIconPadding(dimensionPixelSize);
        this.menu.addMenuPresenter(this.presenter);
        addView((View) this.presenter.getMenuView(this));
        if (attributeSet.hasValue(C0026R.styleable.NavigationView_menu) != null) {
            inflateMenu(attributeSet.getResourceId(C0026R.styleable.NavigationView_menu, 0));
        }
        if (attributeSet.hasValue(C0026R.styleable.NavigationView_headerLayout) != null) {
            inflateHeaderView(attributeSet.getResourceId(C0026R.styleable.NavigationView_headerLayout, 0));
        }
        attributeSet.recycle();
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.menuState = new Bundle();
        this.menu.savePresenterStates(savedState.menuState);
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.menu.restorePresenterStates(savedState.menuState);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void setNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.listener = onNavigationItemSelectedListener;
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE) {
            i = MeasureSpec.makeMeasureSpec(Math.min(MeasureSpec.getSize(i), this.maxWidth), 1073741824);
        } else if (mode == 0) {
            i = MeasureSpec.makeMeasureSpec(this.maxWidth, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected void onInsetsChanged(WindowInsetsCompat windowInsetsCompat) {
        this.presenter.dispatchApplyWindowInsets(windowInsetsCompat);
    }

    public void inflateMenu(int i) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(false);
    }

    public Menu getMenu() {
        return this.menu;
    }

    public View inflateHeaderView(@LayoutRes int i) {
        return this.presenter.inflateHeaderView(i);
    }

    public void addHeaderView(@NonNull View view) {
        this.presenter.addHeaderView(view);
    }

    public void removeHeaderView(@NonNull View view) {
        this.presenter.removeHeaderView(view);
    }

    public int getHeaderCount() {
        return this.presenter.getHeaderCount();
    }

    public View getHeaderView(int i) {
        return this.presenter.getHeaderView(i);
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.presenter.getItemTintList();
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.presenter.setItemIconTintList(colorStateList);
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.presenter.getItemTextColor();
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.presenter.setItemTextColor(colorStateList);
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.presenter.getItemBackground();
    }

    public void setItemBackgroundResource(@DrawableRes int i) {
        setItemBackground(ContextCompat.getDrawable(getContext(), i));
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.presenter.setItemBackground(drawable);
    }

    @Dimension
    public int getItemHorizontalPadding() {
        return this.presenter.getItemHorizontalPadding();
    }

    public void setItemHorizontalPadding(@Dimension int i) {
        this.presenter.setItemHorizontalPadding(i);
    }

    public void setItemHorizontalPaddingResource(@DimenRes int i) {
        this.presenter.setItemHorizontalPadding(getResources().getDimensionPixelSize(i));
    }

    @Dimension
    public int getItemIconPadding() {
        return this.presenter.getItemIconPadding();
    }

    public void setItemIconPadding(@Dimension int i) {
        this.presenter.setItemIconPadding(i);
    }

    public void setItemIconPaddingResource(int i) {
        this.presenter.setItemIconPadding(getResources().getDimensionPixelSize(i));
    }

    public void setCheckedItem(@IdRes int i) {
        i = this.menu.findItem(i);
        if (i != 0) {
            this.presenter.setCheckedItem((MenuItemImpl) i);
        }
    }

    public void setCheckedItem(@NonNull MenuItem menuItem) {
        menuItem = this.menu.findItem(menuItem.getItemId());
        if (menuItem != null) {
            this.presenter.setCheckedItem((MenuItemImpl) menuItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }

    @Nullable
    public MenuItem getCheckedItem() {
        return this.presenter.getCheckedItem();
    }

    public void setItemTextAppearance(@StyleRes int i) {
        this.presenter.setItemTextAppearance(i);
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(getContext());
        }
        return this.menuInflater;
    }

    private ColorStateList createDefaultColorStateList(int i) {
        TypedValue typedValue = new TypedValue();
        if (getContext().getTheme().resolveAttribute(i, typedValue, true) == 0) {
            return null;
        }
        i = AppCompatResources.getColorStateList(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(C0291R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = i.getDefaultColor();
        return new ColorStateList(new int[][]{DISABLED_STATE_SET, CHECKED_STATE_SET, EMPTY_STATE_SET}, new int[]{i.getColorForState(DISABLED_STATE_SET, defaultColor), i2, defaultColor});
    }
}
