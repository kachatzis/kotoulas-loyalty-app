package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.KeyEventDispatcher;
import android.support.v4.view.KeyEventDispatcher.Component;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.app.ActionBarDrawerToggle.Delegate;
import android.support.v7.appcompat.C0291R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.SupportActionModeWrapper.CallbackWrapper;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ContentFrameLayout.OnAttachListener;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.FitWindowsViewGroup.OnFitSystemWindowsListener;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

class AppCompatDelegateImpl extends AppCompatDelegate implements Callback, Factory2 {
    private static final boolean DEBUG = false;
    static final String EXCEPTION_HANDLER_MESSAGE_SUFFIX = ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.";
    private static final boolean IS_PRE_LOLLIPOP = (VERSION.SDK_INT < 21);
    private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
    private static boolean sInstalledExceptionHandler = true;
    private static final int[] sWindowBackgroundStyleable = new int[]{16842836};
    ActionBar mActionBar;
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    final AppCompatCallback mAppCompatCallback;
    private AppCompatViewInflater mAppCompatViewInflater;
    final Window.Callback mAppCompatWindowCallback;
    private boolean mApplyDayNightCalled;
    private AutoNightModeManager mAutoNightModeManager;
    private boolean mClosingActionMenu;
    final Context mContext;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    ViewPropertyAnimatorCompat mFadeAnim = null;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private boolean mHandleNativeActionModes = true;
    boolean mHasActionBar;
    int mInvalidatePanelMenuFeatures;
    boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable = new C02682();
    boolean mIsDestroyed;
    boolean mIsFloating;
    private int mLocalNightMode = -100;
    private boolean mLongPressBackDown;
    MenuInflater mMenuInflater;
    final Window.Callback mOriginalWindowCallback;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private CharSequence mTitle;
    private TextView mTitleView;
    final Window mWindow;
    boolean mWindowNoTitle;

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$2 */
    class C02682 implements Runnable {
        C02682() {
        }

        public void run() {
            if ((AppCompatDelegateImpl.this.mInvalidatePanelMenuFeatures & 1) != 0) {
                AppCompatDelegateImpl.this.doInvalidatePanelMenu(0);
            }
            if ((AppCompatDelegateImpl.this.mInvalidatePanelMenuFeatures & 4096) != 0) {
                AppCompatDelegateImpl.this.doInvalidatePanelMenu(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.mInvalidatePanelMenuPosted = false;
            appCompatDelegateImpl.mInvalidatePanelMenuFeatures = 0;
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$6 */
    class C02696 implements Runnable {

        /* renamed from: android.support.v7.app.AppCompatDelegateImpl$6$1 */
        class C06631 extends ViewPropertyAnimatorListenerAdapter {
            C06631() {
            }

            public void onAnimationStart(View view) {
                AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
            }

            public void onAnimationEnd(View view) {
                AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0f);
                AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
                AppCompatDelegateImpl.this.mFadeAnim = null;
            }
        }

        C02696() {
        }

        public void run() {
            AppCompatDelegateImpl.this.mActionModePopup.showAtLocation(AppCompatDelegateImpl.this.mActionModeView, 55, 0, 0);
            AppCompatDelegateImpl.this.endOnGoingFadeAnimation();
            if (AppCompatDelegateImpl.this.shouldAnimateActionModeView()) {
                AppCompatDelegateImpl.this.mActionModeView.setAlpha(0.0f);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                appCompatDelegateImpl.mFadeAnim = ViewCompat.animate(appCompatDelegateImpl.mActionModeView).alpha(1.0f);
                AppCompatDelegateImpl.this.mFadeAnim.setListener(new C06631());
                return;
            }
            AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0f);
            AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
        }
    }

    @VisibleForTesting
    final class AutoNightModeManager {
        private BroadcastReceiver mAutoTimeChangeReceiver;
        private IntentFilter mAutoTimeChangeReceiverFilter;
        private boolean mIsNight;
        private TwilightManager mTwilightManager;

        /* renamed from: android.support.v7.app.AppCompatDelegateImpl$AutoNightModeManager$1 */
        class C02701 extends BroadcastReceiver {
            C02701() {
            }

            public void onReceive(Context context, Intent intent) {
                AutoNightModeManager.this.dispatchTimeChanged();
            }
        }

        AutoNightModeManager(@NonNull TwilightManager twilightManager) {
            this.mTwilightManager = twilightManager;
            this.mIsNight = twilightManager.isNight();
        }

        int getApplyableNightMode() {
            this.mIsNight = this.mTwilightManager.isNight();
            return this.mIsNight ? 2 : 1;
        }

        void dispatchTimeChanged() {
            boolean isNight = this.mTwilightManager.isNight();
            if (isNight != this.mIsNight) {
                this.mIsNight = isNight;
                AppCompatDelegateImpl.this.applyDayNight();
            }
        }

        void setup() {
            cleanup();
            if (this.mAutoTimeChangeReceiver == null) {
                this.mAutoTimeChangeReceiver = new C02701();
            }
            if (this.mAutoTimeChangeReceiverFilter == null) {
                this.mAutoTimeChangeReceiverFilter = new IntentFilter();
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
            }
            AppCompatDelegateImpl.this.mContext.registerReceiver(this.mAutoTimeChangeReceiver, this.mAutoTimeChangeReceiverFilter);
        }

        void cleanup() {
            if (this.mAutoTimeChangeReceiver != null) {
                AppCompatDelegateImpl.this.mContext.unregisterReceiver(this.mAutoTimeChangeReceiver);
                this.mAutoTimeChangeReceiver = null;
            }
        }
    }

    protected static final class PanelFeatureState {
        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        Bundle frozenMenuState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView = false;
        boolean refreshMenuContent;
        View shownPanelView;
        boolean wasLastOpen;
        int windowAnimations;
        /* renamed from: x */
        int f9x;
        /* renamed from: y */
        int f10y;

        private static class SavedState implements Parcelable {
            public static final Creator<SavedState> CREATOR = new C02711();
            int featureId;
            boolean isOpen;
            Bundle menuState;

            /* renamed from: android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState$SavedState$1 */
            static class C02711 implements ClassLoaderCreator<SavedState> {
                C02711() {
                }

                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.readFromParcel(parcel, classLoader);
                }

                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.readFromParcel(parcel, null);
                }

                public SavedState[] newArray(int i) {
                    return new SavedState[i];
                }
            }

            public int describeContents() {
                return 0;
            }

            SavedState() {
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.featureId);
                parcel.writeInt(this.isOpen);
                if (this.isOpen != 0) {
                    parcel.writeBundle(this.menuState);
                }
            }

            static SavedState readFromParcel(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.featureId = parcel.readInt();
                boolean z = true;
                if (parcel.readInt() != 1) {
                    z = false;
                }
                savedState.isOpen = z;
                if (savedState.isOpen) {
                    savedState.menuState = parcel.readBundle(classLoader);
                }
                return savedState;
            }
        }

        PanelFeatureState(int i) {
            this.featureId = i;
        }

        public boolean hasPanelItems() {
            boolean z = false;
            if (this.shownPanelView == null) {
                return false;
            }
            if (this.createdPanelView != null) {
                return true;
            }
            if (this.listMenuPresenter.getAdapter().getCount() > 0) {
                z = true;
            }
            return z;
        }

        public void clearMenuPresenters() {
            MenuBuilder menuBuilder = this.menu;
            if (menuBuilder != null) {
                menuBuilder.removeMenuPresenter(this.listMenuPresenter);
            }
            this.listMenuPresenter = null;
        }

        void setStyle(Context context) {
            TypedValue typedValue = new TypedValue();
            Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(C0291R.attr.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            }
            newTheme.resolveAttribute(C0291R.attr.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            } else {
                newTheme.applyStyle(C0291R.style.Theme_AppCompat_CompactMenu, true);
            }
            Context contextThemeWrapper = new ContextThemeWrapper(context, 0);
            contextThemeWrapper.getTheme().setTo(newTheme);
            this.listPresenterContext = contextThemeWrapper;
            context = contextThemeWrapper.obtainStyledAttributes(C0291R.styleable.AppCompatTheme);
            this.background = context.getResourceId(C0291R.styleable.AppCompatTheme_panelBackground, 0);
            this.windowAnimations = context.getResourceId(C0291R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            context.recycle();
        }

        void setMenu(MenuBuilder menuBuilder) {
            MenuBuilder menuBuilder2 = this.menu;
            if (menuBuilder != menuBuilder2) {
                if (menuBuilder2 != null) {
                    menuBuilder2.removeMenuPresenter(this.listMenuPresenter);
                }
                this.menu = menuBuilder;
                if (menuBuilder != null) {
                    MenuPresenter menuPresenter = this.listMenuPresenter;
                    if (menuPresenter != null) {
                        menuBuilder.addMenuPresenter(menuPresenter);
                    }
                }
            }
        }

        MenuView getListMenuView(MenuPresenter.Callback callback) {
            if (this.menu == null) {
                return null;
            }
            if (this.listMenuPresenter == null) {
                this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, C0291R.layout.abc_list_menu_item_layout);
                this.listMenuPresenter.setCallback(callback);
                this.menu.addMenuPresenter(this.listMenuPresenter);
            }
            return this.listMenuPresenter.getMenuView(this.decorView);
        }

        Parcelable onSaveInstanceState() {
            Parcelable savedState = new SavedState();
            savedState.featureId = this.featureId;
            savedState.isOpen = this.isOpen;
            if (this.menu != null) {
                savedState.menuState = new Bundle();
                this.menu.savePresenterStates(savedState.menuState);
            }
            return savedState;
        }

        void onRestoreInstanceState(Parcelable parcelable) {
            SavedState savedState = (SavedState) parcelable;
            this.featureId = savedState.featureId;
            this.wasLastOpen = savedState.isOpen;
            this.frozenMenuState = savedState.menuState;
            this.shownPanelView = null;
            this.decorView = null;
        }

        void applyFrozenState() {
            MenuBuilder menuBuilder = this.menu;
            if (menuBuilder != null) {
                Bundle bundle = this.frozenMenuState;
                if (bundle != null) {
                    menuBuilder.restorePresenterStates(bundle);
                    this.frozenMenuState = null;
                }
            }
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$3 */
    class C05103 implements OnApplyWindowInsetsListener {
        C05103() {
        }

        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
            int updateStatusGuard = AppCompatDelegateImpl.this.updateStatusGuard(systemWindowInsetTop);
            if (systemWindowInsetTop != updateStatusGuard) {
                windowInsetsCompat = windowInsetsCompat.replaceSystemWindowInsets(windowInsetsCompat.getSystemWindowInsetLeft(), updateStatusGuard, windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
            }
            return ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$4 */
    class C05114 implements OnFitSystemWindowsListener {
        C05114() {
        }

        public void onFitSystemWindows(Rect rect) {
            rect.top = AppCompatDelegateImpl.this.updateStatusGuard(rect.top);
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$5 */
    class C05125 implements OnAttachListener {
        public void onAttachedFromWindow() {
        }

        C05125() {
        }

        public void onDetachedFromWindow() {
            AppCompatDelegateImpl.this.dismissPopups();
        }
    }

    private class ActionBarDrawableToggleImpl implements Delegate {
        ActionBarDrawableToggleImpl() {
        }

        public Drawable getThemeUpIndicator() {
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), null, new int[]{C0291R.attr.homeAsUpIndicator});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }

        public Context getActionBarThemedContext() {
            return AppCompatDelegateImpl.this.getActionBarThemedContext();
        }

        public boolean isNavigationVisible() {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            return (supportActionBar == null || (supportActionBar.getDisplayOptions() & 4) == 0) ? false : true;
        }

        public void setActionBarUpIndicator(Drawable drawable, int i) {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeAsUpIndicator(drawable);
                supportActionBar.setHomeActionContentDescription(i);
            }
        }

        public void setActionBarDescription(int i) {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeActionContentDescription(i);
            }
        }
    }

    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        ActionMenuPresenterCallback() {
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback windowCallback = AppCompatDelegateImpl.this.getWindowCallback();
            if (windowCallback != null) {
                windowCallback.onMenuOpened(108, menuBuilder);
            }
            return true;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            AppCompatDelegateImpl.this.checkCloseActionMenu(menuBuilder);
        }
    }

    class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;

        /* renamed from: android.support.v7.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9$1 */
        class C06651 extends ViewPropertyAnimatorListenerAdapter {
            C06651() {
            }

            public void onAnimationEnd(View view) {
                AppCompatDelegateImpl.this.mActionModeView.setVisibility(8);
                if (AppCompatDelegateImpl.this.mActionModePopup != null) {
                    AppCompatDelegateImpl.this.mActionModePopup.dismiss();
                } else if ((AppCompatDelegateImpl.this.mActionModeView.getParent() instanceof View) != null) {
                    ViewCompat.requestApplyInsets((View) AppCompatDelegateImpl.this.mActionModeView.getParent());
                }
                AppCompatDelegateImpl.this.mActionModeView.removeAllViews();
                AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
                AppCompatDelegateImpl.this.mFadeAnim = null;
            }
        }

        public ActionModeCallbackWrapperV9(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            if (AppCompatDelegateImpl.this.mActionModePopup != null) {
                AppCompatDelegateImpl.this.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.mShowActionModePopup);
            }
            if (AppCompatDelegateImpl.this.mActionModeView != null) {
                AppCompatDelegateImpl.this.endOnGoingFadeAnimation();
                actionMode = AppCompatDelegateImpl.this;
                actionMode.mFadeAnim = ViewCompat.animate(actionMode.mActionModeView).alpha(0.0f);
                AppCompatDelegateImpl.this.mFadeAnim.setListener(new C06651());
            }
            if (AppCompatDelegateImpl.this.mAppCompatCallback != null) {
                AppCompatDelegateImpl.this.mAppCompatCallback.onSupportActionModeFinished(AppCompatDelegateImpl.this.mActionMode);
            }
            AppCompatDelegateImpl.this.mActionMode = null;
        }
    }

    class AppCompatWindowCallback extends WindowCallbackWrapper {
        public void onContentChanged() {
        }

        AppCompatWindowCallback(Window.Callback callback) {
            super(callback);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (!AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent)) {
                if (super.dispatchKeyEvent(keyEvent) == null) {
                    return null;
                }
            }
            return true;
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            if (!super.dispatchKeyShortcutEvent(keyEvent)) {
                if (AppCompatDelegateImpl.this.onKeyShortcut(keyEvent.getKeyCode(), keyEvent) == null) {
                    return null;
                }
            }
            return true;
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
            if (i == 0 && menuBuilder == null) {
                return false;
            }
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(true);
            }
            i = super.onPreparePanel(i, view, menu);
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(false);
            }
            return i;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.onMenuOpened(i);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.onPanelClosed(i);
        }

        public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback) {
            if (VERSION.SDK_INT >= 23) {
                return null;
            }
            if (AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled()) {
                return startAsSupportActionMode(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        final android.view.ActionMode startAsSupportActionMode(android.view.ActionMode.Callback callback) {
            Object callbackWrapper = new CallbackWrapper(AppCompatDelegateImpl.this.mContext, callback);
            callback = AppCompatDelegateImpl.this.startSupportActionMode(callbackWrapper);
            return callback != null ? callbackWrapper.getActionModeWrapper(callback) : null;
        }

        @RequiresApi(23)
        public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback, int i) {
            if (AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled()) {
                if (i == 0) {
                    return startAsSupportActionMode(callback);
                }
            }
            return super.onWindowStartingActionMode(callback, i);
        }

        @RequiresApi(24)
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            PanelFeatureState panelState = AppCompatDelegateImpl.this.getPanelState(0, true);
            if (panelState == null || panelState.menu == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, panelState.menu, i);
            }
        }
    }

    private class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(Context context) {
            super(context);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (!AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent)) {
                if (super.dispatchKeyEvent(keyEvent) == null) {
                    return null;
                }
            }
            return true;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !isOutOfBounds((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.closePanel(0);
            return true;
        }

        public void setBackgroundResource(int i) {
            setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), i));
        }

        private boolean isOutOfBounds(int i, int i2) {
            if (i >= -5 && i2 >= -5 && i <= getWidth() + 5) {
                if (i2 <= getHeight() + 5) {
                    return false;
                }
            }
            return true;
        }
    }

    private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        PanelMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            MenuBuilder rootMenu = menuBuilder.getRootMenu();
            Object obj = rootMenu != menuBuilder ? 1 : null;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (obj != null) {
                menuBuilder = rootMenu;
            }
            menuBuilder = appCompatDelegateImpl.findMenuPanel(menuBuilder);
            if (menuBuilder == null) {
                return;
            }
            if (obj != null) {
                AppCompatDelegateImpl.this.callOnPanelClosed(menuBuilder.featureId, menuBuilder, rootMenu);
                AppCompatDelegateImpl.this.closePanel(menuBuilder, true);
                return;
            }
            AppCompatDelegateImpl.this.closePanel(menuBuilder, z);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null && AppCompatDelegateImpl.this.mHasActionBar) {
                Window.Callback windowCallback = AppCompatDelegateImpl.this.getWindowCallback();
                if (!(windowCallback == null || AppCompatDelegateImpl.this.mIsDestroyed)) {
                    windowCallback.onMenuOpened(108, menuBuilder);
                }
            }
            return true;
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$7 */
    class C06647 extends ViewPropertyAnimatorListenerAdapter {
        C06647() {
        }

        public void onAnimationStart(View view) {
            AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
            AppCompatDelegateImpl.this.mActionModeView.sendAccessibilityEvent(32);
            if ((AppCompatDelegateImpl.this.mActionModeView.getParent() instanceof View) != null) {
                ViewCompat.requestApplyInsets((View) AppCompatDelegateImpl.this.mActionModeView.getParent());
            }
        }

        public void onAnimationEnd(View view) {
            AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0f);
            AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
            AppCompatDelegateImpl.this.mFadeAnim = null;
        }
    }

    private android.view.ViewGroup createSubDecor() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:61:0x01ac in {4, 7, 10, 13, 18, 23, 24, 27, 30, 33, 34, 37, 38, 41, 42, 46, 51, 54, 56, 58, 60} preds:[]
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
        r7 = this;
        r0 = r7.mContext;
        r1 = android.support.v7.appcompat.C0291R.styleable.AppCompatTheme;
        r0 = r0.obtainStyledAttributes(r1);
        r1 = android.support.v7.appcompat.C0291R.styleable.AppCompatTheme_windowActionBar;
        r1 = r0.hasValue(r1);
        if (r1 == 0) goto L_0x01a1;
    L_0x0010:
        r1 = android.support.v7.appcompat.C0291R.styleable.AppCompatTheme_windowNoTitle;
        r2 = 0;
        r1 = r0.getBoolean(r1, r2);
        r3 = 1;
        if (r1 == 0) goto L_0x001e;
    L_0x001a:
        r7.requestWindowFeature(r3);
        goto L_0x002b;
    L_0x001e:
        r1 = android.support.v7.appcompat.C0291R.styleable.AppCompatTheme_windowActionBar;
        r1 = r0.getBoolean(r1, r2);
        if (r1 == 0) goto L_0x002b;
    L_0x0026:
        r1 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        r7.requestWindowFeature(r1);
    L_0x002b:
        r1 = android.support.v7.appcompat.C0291R.styleable.AppCompatTheme_windowActionBarOverlay;
        r1 = r0.getBoolean(r1, r2);
        r4 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        if (r1 == 0) goto L_0x0038;
    L_0x0035:
        r7.requestWindowFeature(r4);
    L_0x0038:
        r1 = android.support.v7.appcompat.C0291R.styleable.AppCompatTheme_windowActionModeOverlay;
        r1 = r0.getBoolean(r1, r2);
        if (r1 == 0) goto L_0x0045;
    L_0x0040:
        r1 = 10;
        r7.requestWindowFeature(r1);
    L_0x0045:
        r1 = android.support.v7.appcompat.C0291R.styleable.AppCompatTheme_android_windowIsFloating;
        r1 = r0.getBoolean(r1, r2);
        r7.mIsFloating = r1;
        r0.recycle();
        r0 = r7.mWindow;
        r0.getDecorView();
        r0 = r7.mContext;
        r0 = android.view.LayoutInflater.from(r0);
        r1 = r7.mWindowNoTitle;
        r5 = 0;
        if (r1 != 0) goto L_0x00d5;
    L_0x0060:
        r1 = r7.mIsFloating;
        if (r1 == 0) goto L_0x0072;
    L_0x0064:
        r1 = android.support.v7.appcompat.C0291R.layout.abc_dialog_title_material;
        r0 = r0.inflate(r1, r5);
        r0 = (android.view.ViewGroup) r0;
        r7.mOverlayActionBar = r2;
        r7.mHasActionBar = r2;
        goto L_0x0104;
    L_0x0072:
        r0 = r7.mHasActionBar;
        if (r0 == 0) goto L_0x00d3;
    L_0x0076:
        r0 = new android.util.TypedValue;
        r0.<init>();
        r1 = r7.mContext;
        r1 = r1.getTheme();
        r6 = android.support.v7.appcompat.C0291R.attr.actionBarTheme;
        r1.resolveAttribute(r6, r0, r3);
        r1 = r0.resourceId;
        if (r1 == 0) goto L_0x0094;
    L_0x008a:
        r1 = new android.support.v7.view.ContextThemeWrapper;
        r3 = r7.mContext;
        r0 = r0.resourceId;
        r1.<init>(r3, r0);
        goto L_0x0096;
    L_0x0094:
        r1 = r7.mContext;
    L_0x0096:
        r0 = android.view.LayoutInflater.from(r1);
        r1 = android.support.v7.appcompat.C0291R.layout.abc_screen_toolbar;
        r0 = r0.inflate(r1, r5);
        r0 = (android.view.ViewGroup) r0;
        r1 = android.support.v7.appcompat.C0291R.id.decor_content_parent;
        r1 = r0.findViewById(r1);
        r1 = (android.support.v7.widget.DecorContentParent) r1;
        r7.mDecorContentParent = r1;
        r1 = r7.mDecorContentParent;
        r3 = r7.getWindowCallback();
        r1.setWindowCallback(r3);
        r1 = r7.mOverlayActionBar;
        if (r1 == 0) goto L_0x00be;
    L_0x00b9:
        r1 = r7.mDecorContentParent;
        r1.initFeature(r4);
    L_0x00be:
        r1 = r7.mFeatureProgress;
        if (r1 == 0) goto L_0x00c8;
    L_0x00c2:
        r1 = r7.mDecorContentParent;
        r3 = 2;
        r1.initFeature(r3);
    L_0x00c8:
        r1 = r7.mFeatureIndeterminateProgress;
        if (r1 == 0) goto L_0x0104;
    L_0x00cc:
        r1 = r7.mDecorContentParent;
        r3 = 5;
        r1.initFeature(r3);
        goto L_0x0104;
    L_0x00d3:
        r0 = r5;
        goto L_0x0104;
    L_0x00d5:
        r1 = r7.mOverlayActionMode;
        if (r1 == 0) goto L_0x00e2;
    L_0x00d9:
        r1 = android.support.v7.appcompat.C0291R.layout.abc_screen_simple_overlay_action_mode;
        r0 = r0.inflate(r1, r5);
        r0 = (android.view.ViewGroup) r0;
        goto L_0x00ea;
    L_0x00e2:
        r1 = android.support.v7.appcompat.C0291R.layout.abc_screen_simple;
        r0 = r0.inflate(r1, r5);
        r0 = (android.view.ViewGroup) r0;
    L_0x00ea:
        r1 = android.os.Build.VERSION.SDK_INT;
        r3 = 21;
        if (r1 < r3) goto L_0x00f9;
    L_0x00f0:
        r1 = new android.support.v7.app.AppCompatDelegateImpl$3;
        r1.<init>();
        android.support.v4.view.ViewCompat.setOnApplyWindowInsetsListener(r0, r1);
        goto L_0x0104;
    L_0x00f9:
        r1 = r0;
        r1 = (android.support.v7.widget.FitWindowsViewGroup) r1;
        r3 = new android.support.v7.app.AppCompatDelegateImpl$4;
        r3.<init>();
        r1.setOnFitSystemWindowsListener(r3);
    L_0x0104:
        if (r0 == 0) goto L_0x015b;
    L_0x0106:
        r1 = r7.mDecorContentParent;
        if (r1 != 0) goto L_0x0114;
    L_0x010a:
        r1 = android.support.v7.appcompat.C0291R.id.title;
        r1 = r0.findViewById(r1);
        r1 = (android.widget.TextView) r1;
        r7.mTitleView = r1;
    L_0x0114:
        android.support.v7.widget.ViewUtils.makeOptionalFitsSystemWindows(r0);
        r1 = android.support.v7.appcompat.C0291R.id.action_bar_activity_content;
        r1 = r0.findViewById(r1);
        r1 = (android.support.v7.widget.ContentFrameLayout) r1;
        r3 = r7.mWindow;
        r4 = 16908290; // 0x1020002 float:2.3877235E-38 double:8.353805E-317;
        r3 = r3.findViewById(r4);
        r3 = (android.view.ViewGroup) r3;
        if (r3 == 0) goto L_0x014d;
    L_0x012c:
        r6 = r3.getChildCount();
        if (r6 <= 0) goto L_0x013d;
    L_0x0132:
        r6 = r3.getChildAt(r2);
        r3.removeViewAt(r2);
        r1.addView(r6);
        goto L_0x012c;
    L_0x013d:
        r2 = -1;
        r3.setId(r2);
        r1.setId(r4);
        r2 = r3 instanceof android.widget.FrameLayout;
        if (r2 == 0) goto L_0x014d;
    L_0x0148:
        r3 = (android.widget.FrameLayout) r3;
        r3.setForeground(r5);
    L_0x014d:
        r2 = r7.mWindow;
        r2.setContentView(r0);
        r2 = new android.support.v7.app.AppCompatDelegateImpl$5;
        r2.<init>();
        r1.setAttachListener(r2);
        return r0;
    L_0x015b:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "AppCompat does not support the current theme features: { windowActionBar: ";
        r1.append(r2);
        r2 = r7.mHasActionBar;
        r1.append(r2);
        r2 = ", windowActionBarOverlay: ";
        r1.append(r2);
        r2 = r7.mOverlayActionBar;
        r1.append(r2);
        r2 = ", android:windowIsFloating: ";
        r1.append(r2);
        r2 = r7.mIsFloating;
        r1.append(r2);
        r2 = ", windowActionModeOverlay: ";
        r1.append(r2);
        r2 = r7.mOverlayActionMode;
        r1.append(r2);
        r2 = ", windowNoTitle: ";
        r1.append(r2);
        r2 = r7.mWindowNoTitle;
        r1.append(r2);
        r2 = " }";
        r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x01a1:
        r0.recycle();
        r0 = new java.lang.IllegalStateException;
        r1 = "You need to use a Theme.AppCompat theme (or descendant) with this activity.";
        r0.<init>(r1);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.createSubDecor():android.view.ViewGroup");
    }

    void onSubDecorInstalled(ViewGroup viewGroup) {
    }

    static {
        if (IS_PRE_LOLLIPOP && !sInstalledExceptionHandler) {
            final UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    if (shouldWrapException(th)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(th.getMessage());
                        stringBuilder.append(AppCompatDelegateImpl.EXCEPTION_HANDLER_MESSAGE_SUFFIX);
                        Throwable notFoundException = new NotFoundException(stringBuilder.toString());
                        notFoundException.initCause(th.getCause());
                        notFoundException.setStackTrace(th.getStackTrace());
                        defaultUncaughtExceptionHandler.uncaughtException(thread, notFoundException);
                        return;
                    }
                    defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                }

                private boolean shouldWrapException(Throwable th) {
                    boolean z = false;
                    if (!(th instanceof NotFoundException)) {
                        return false;
                    }
                    th = th.getMessage();
                    if (th != null && (th.contains("drawable") || th.contains("Drawable") != null)) {
                        z = true;
                    }
                    return z;
                }
            });
        }
    }

    AppCompatDelegateImpl(Context context, Window window, AppCompatCallback appCompatCallback) {
        this.mContext = context;
        this.mWindow = window;
        this.mAppCompatCallback = appCompatCallback;
        this.mOriginalWindowCallback = this.mWindow.getCallback();
        window = this.mOriginalWindowCallback;
        if ((window instanceof AppCompatWindowCallback) == null) {
            this.mAppCompatWindowCallback = new AppCompatWindowCallback(window);
            this.mWindow.setCallback(this.mAppCompatWindowCallback);
            context = TintTypedArray.obtainStyledAttributes(context, null, sWindowBackgroundStyleable);
            window = context.getDrawableIfKnown(null);
            if (window != null) {
                this.mWindow.setBackgroundDrawable(window);
            }
            context.recycle();
            return;
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    public void onCreate(android.os.Bundle r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        r0 = r2.mOriginalWindowCallback;
        r1 = r0 instanceof android.app.Activity;
        if (r1 == 0) goto L_0x001c;
    L_0x0006:
        r1 = 0;
        r0 = (android.app.Activity) r0;	 Catch:{ IllegalArgumentException -> 0x000d }
        r1 = android.support.v4.app.NavUtils.getParentActivityName(r0);	 Catch:{ IllegalArgumentException -> 0x000d }
    L_0x000d:
        if (r1 == 0) goto L_0x001c;
    L_0x000f:
        r0 = r2.peekSupportActionBar();
        r1 = 1;
        if (r0 != 0) goto L_0x0019;
    L_0x0016:
        r2.mEnableDefaultActionBarUp = r1;
        goto L_0x001c;
    L_0x0019:
        r0.setDefaultDisplayHomeAsUpEnabled(r1);
    L_0x001c:
        if (r3 == 0) goto L_0x002c;
    L_0x001e:
        r0 = r2.mLocalNightMode;
        r1 = -100;
        if (r0 != r1) goto L_0x002c;
    L_0x0024:
        r0 = "appcompat:local_night_mode";
        r3 = r3.getInt(r0, r1);
        r2.mLocalNightMode = r3;
    L_0x002c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.onCreate(android.os.Bundle):void");
    }

    public void onPostCreate(Bundle bundle) {
        ensureSubDecor();
    }

    public ActionBar getSupportActionBar() {
        initWindowDecorActionBar();
        return this.mActionBar;
    }

    final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    final Window.Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    private void initWindowDecorActionBar() {
        ensureSubDecor();
        if (this.mHasActionBar) {
            if (this.mActionBar == null) {
                Window.Callback callback = this.mOriginalWindowCallback;
                if (callback instanceof Activity) {
                    this.mActionBar = new WindowDecorActionBar((Activity) callback, this.mOverlayActionBar);
                } else if (callback instanceof Dialog) {
                    this.mActionBar = new WindowDecorActionBar((Dialog) callback);
                }
                ActionBar actionBar = this.mActionBar;
                if (actionBar != null) {
                    actionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
                }
            }
        }
    }

    public void setSupportActionBar(Toolbar toolbar) {
        if (this.mOriginalWindowCallback instanceof Activity) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar instanceof WindowDecorActionBar) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.mMenuInflater = null;
            if (supportActionBar != null) {
                supportActionBar.onDestroy();
            }
            if (toolbar != null) {
                supportActionBar = new ToolbarActionBar(toolbar, ((Activity) this.mOriginalWindowCallback).getTitle(), this.mAppCompatWindowCallback);
                this.mActionBar = supportActionBar;
                this.mWindow.setCallback(supportActionBar.getWrappedWindowCallback());
            } else {
                this.mActionBar = null;
                this.mWindow.setCallback(this.mAppCompatWindowCallback);
            }
            invalidateOptionsMenu();
        }
    }

    final Context getActionBarThemedContext() {
        ActionBar supportActionBar = getSupportActionBar();
        Context themedContext = supportActionBar != null ? supportActionBar.getThemedContext() : null;
        return themedContext == null ? this.mContext : themedContext;
    }

    public MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            initWindowDecorActionBar();
            ActionBar actionBar = this.mActionBar;
            this.mMenuInflater = new SupportMenuInflater(actionBar != null ? actionBar.getThemedContext() : this.mContext);
        }
        return this.mMenuInflater;
    }

    @Nullable
    public <T extends View> T findViewById(@IdRes int i) {
        ensureSubDecor();
        return this.mWindow.findViewById(i);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.mHasActionBar && this.mSubDecorInstalled) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.onConfigurationChanged(configuration);
            }
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
        applyDayNight();
    }

    public void onStart() {
        applyDayNight();
    }

    public void onStop() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
        }
        AutoNightModeManager autoNightModeManager = this.mAutoNightModeManager;
        if (autoNightModeManager != null) {
            autoNightModeManager.cleanup();
        }
    }

    public void onPostResume() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(true);
        }
    }

    public void setContentView(View view) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setContentView(int i) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.mContext).inflate(i, viewGroup);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void addContentView(View view, LayoutParams layoutParams) {
        ensureSubDecor();
        ((ViewGroup) this.mSubDecor.findViewById(16908290)).addView(view, layoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void onSaveInstanceState(Bundle bundle) {
        int i = this.mLocalNightMode;
        if (i != -100) {
            bundle.putInt(KEY_LOCAL_NIGHT_MODE, i);
        }
    }

    public void onDestroy() {
        if (this.mInvalidatePanelMenuPosted) {
            this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
        }
        this.mIsDestroyed = true;
        ActionBar actionBar = this.mActionBar;
        if (actionBar != null) {
            actionBar.onDestroy();
        }
        AutoNightModeManager autoNightModeManager = this.mAutoNightModeManager;
        if (autoNightModeManager != null) {
            autoNightModeManager.cleanup();
        }
    }

    private void ensureSubDecor() {
        if (!this.mSubDecorInstalled) {
            this.mSubDecor = createSubDecor();
            CharSequence title = getTitle();
            if (!TextUtils.isEmpty(title)) {
                DecorContentParent decorContentParent = this.mDecorContentParent;
                if (decorContentParent != null) {
                    decorContentParent.setWindowTitle(title);
                } else if (peekSupportActionBar() != null) {
                    peekSupportActionBar().setWindowTitle(title);
                } else {
                    TextView textView = this.mTitleView;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
            applyFixedSizeWindow();
            onSubDecorInstalled(this.mSubDecor);
            this.mSubDecorInstalled = true;
            PanelFeatureState panelState = getPanelState(0, false);
            if (!this.mIsDestroyed) {
                if (panelState == null || panelState.menu == null) {
                    invalidatePanelMenu(108);
                }
            }
        }
    }

    private void applyFixedSizeWindow() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.mSubDecor.findViewById(16908290);
        View decorView = this.mWindow.getDecorView();
        contentFrameLayout.setDecorPadding(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(C0291R.styleable.AppCompatTheme);
        obtainStyledAttributes.getValue(C0291R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(C0291R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(C0291R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(C0291R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(C0291R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(C0291R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(C0291R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(C0291R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(C0291R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(C0291R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public boolean requestWindowFeature(int i) {
        i = sanitizeWindowFeatureId(i);
        if (this.mWindowNoTitle && i == 108) {
            return false;
        }
        if (this.mHasActionBar && i == 1) {
            this.mHasActionBar = false;
        }
        switch (i) {
            case 1:
                throwFeatureRequestIfSubDecorInstalled();
                this.mWindowNoTitle = true;
                return true;
            case 2:
                throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureProgress = true;
                return true;
            case 5:
                throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureIndeterminateProgress = true;
                return true;
            case 10:
                throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionMode = true;
                return true;
            case 108:
                throwFeatureRequestIfSubDecorInstalled();
                this.mHasActionBar = true;
                return true;
            case 109:
                throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionBar = true;
                return true;
            default:
                return this.mWindow.requestFeature(i);
        }
    }

    public boolean hasWindowFeature(int i) {
        boolean z;
        switch (sanitizeWindowFeatureId(i)) {
            case 1:
                z = this.mWindowNoTitle;
                break;
            case 2:
                z = this.mFeatureProgress;
                break;
            case 5:
                z = this.mFeatureIndeterminateProgress;
                break;
            case 10:
                z = this.mOverlayActionMode;
                break;
            case 108:
                z = this.mHasActionBar;
                break;
            case 109:
                z = this.mOverlayActionBar;
                break;
            default:
                z = false;
                break;
        }
        if (z || this.mWindow.hasFeature(i) != 0) {
            return true;
        }
        return false;
    }

    public final void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent != null) {
            decorContentParent.setWindowTitle(charSequence);
        } else if (peekSupportActionBar() != null) {
            peekSupportActionBar().setWindowTitle(charSequence);
        } else {
            TextView textView = this.mTitleView;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    final CharSequence getTitle() {
        Window.Callback callback = this.mOriginalWindowCallback;
        if (callback instanceof Activity) {
            return ((Activity) callback).getTitle();
        }
        return this.mTitle;
    }

    void onPanelClosed(int i) {
        if (i == 108) {
            i = getSupportActionBar();
            if (i != 0) {
                i.dispatchMenuVisibilityChanged(false);
            }
        } else if (i == 0) {
            i = getPanelState(i, true);
            if (i.isOpen) {
                closePanel(i, false);
            }
        }
    }

    void onMenuOpened(int i) {
        if (i == 108) {
            i = getSupportActionBar();
            if (i != 0) {
                i.dispatchMenuVisibilityChanged(true);
            }
        }
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        Window.Callback windowCallback = getWindowCallback();
        if (!(windowCallback == null || this.mIsDestroyed)) {
            menuBuilder = findMenuPanel(menuBuilder.getRootMenu());
            if (menuBuilder != null) {
                return windowCallback.onMenuItemSelected(menuBuilder.featureId, menuItem);
            }
        }
        return null;
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        reopenMenu(menuBuilder, true);
    }

    public ActionMode startSupportActionMode(@NonNull ActionMode.Callback callback) {
        if (callback != null) {
            ActionMode actionMode = this.mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
            ActionMode.Callback actionModeCallbackWrapperV9 = new ActionModeCallbackWrapperV9(callback);
            callback = getSupportActionBar();
            if (callback != null) {
                this.mActionMode = callback.startActionMode(actionModeCallbackWrapperV9);
                callback = this.mActionMode;
                if (callback != null) {
                    AppCompatCallback appCompatCallback = this.mAppCompatCallback;
                    if (appCompatCallback != null) {
                        appCompatCallback.onSupportActionModeStarted(callback);
                    }
                }
            }
            if (this.mActionMode == null) {
                this.mActionMode = startSupportActionModeFromWindow(actionModeCallbackWrapperV9);
            }
            return this.mActionMode;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    public void invalidateOptionsMenu() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.invalidateOptionsMenu()) {
            invalidatePanelMenu(0);
        }
    }

    android.support.v7.view.ActionMode startSupportActionModeFromWindow(@android.support.annotation.NonNull android.support.v7.view.ActionMode.Callback r8) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r7 = this;
        r7.endOnGoingFadeAnimation();
        r0 = r7.mActionMode;
        if (r0 == 0) goto L_0x000a;
    L_0x0007:
        r0.finish();
    L_0x000a:
        r0 = r8 instanceof android.support.v7.app.AppCompatDelegateImpl.ActionModeCallbackWrapperV9;
        if (r0 != 0) goto L_0x0014;
    L_0x000e:
        r0 = new android.support.v7.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9;
        r0.<init>(r8);
        r8 = r0;
    L_0x0014:
        r0 = r7.mAppCompatCallback;
        r1 = 0;
        if (r0 == 0) goto L_0x0022;
    L_0x0019:
        r2 = r7.mIsDestroyed;
        if (r2 != 0) goto L_0x0022;
    L_0x001d:
        r0 = r0.onWindowStartingSupportActionMode(r8);	 Catch:{ AbstractMethodError -> 0x0022 }
        goto L_0x0023;
    L_0x0022:
        r0 = r1;
    L_0x0023:
        if (r0 == 0) goto L_0x0029;
    L_0x0025:
        r7.mActionMode = r0;
        goto L_0x0166;
    L_0x0029:
        r0 = r7.mActionModeView;
        r2 = 0;
        r3 = 1;
        if (r0 != 0) goto L_0x00d6;
    L_0x002f:
        r0 = r7.mIsFloating;
        if (r0 == 0) goto L_0x00b7;
    L_0x0033:
        r0 = new android.util.TypedValue;
        r0.<init>();
        r4 = r7.mContext;
        r4 = r4.getTheme();
        r5 = android.support.v7.appcompat.C0291R.attr.actionBarTheme;
        r4.resolveAttribute(r5, r0, r3);
        r5 = r0.resourceId;
        if (r5 == 0) goto L_0x0068;
    L_0x0047:
        r5 = r7.mContext;
        r5 = r5.getResources();
        r5 = r5.newTheme();
        r5.setTo(r4);
        r4 = r0.resourceId;
        r5.applyStyle(r4, r3);
        r4 = new android.support.v7.view.ContextThemeWrapper;
        r6 = r7.mContext;
        r4.<init>(r6, r2);
        r6 = r4.getTheme();
        r6.setTo(r5);
        goto L_0x006a;
    L_0x0068:
        r4 = r7.mContext;
    L_0x006a:
        r5 = new android.support.v7.widget.ActionBarContextView;
        r5.<init>(r4);
        r7.mActionModeView = r5;
        r5 = new android.widget.PopupWindow;
        r6 = android.support.v7.appcompat.C0291R.attr.actionModePopupWindowStyle;
        r5.<init>(r4, r1, r6);
        r7.mActionModePopup = r5;
        r5 = r7.mActionModePopup;
        r6 = 2;
        android.support.v4.widget.PopupWindowCompat.setWindowLayoutType(r5, r6);
        r5 = r7.mActionModePopup;
        r6 = r7.mActionModeView;
        r5.setContentView(r6);
        r5 = r7.mActionModePopup;
        r6 = -1;
        r5.setWidth(r6);
        r5 = r4.getTheme();
        r6 = android.support.v7.appcompat.C0291R.attr.actionBarSize;
        r5.resolveAttribute(r6, r0, r3);
        r0 = r0.data;
        r4 = r4.getResources();
        r4 = r4.getDisplayMetrics();
        r0 = android.util.TypedValue.complexToDimensionPixelSize(r0, r4);
        r4 = r7.mActionModeView;
        r4.setContentHeight(r0);
        r0 = r7.mActionModePopup;
        r4 = -2;
        r0.setHeight(r4);
        r0 = new android.support.v7.app.AppCompatDelegateImpl$6;
        r0.<init>();
        r7.mShowActionModePopup = r0;
        goto L_0x00d6;
    L_0x00b7:
        r0 = r7.mSubDecor;
        r4 = android.support.v7.appcompat.C0291R.id.action_mode_bar_stub;
        r0 = r0.findViewById(r4);
        r0 = (android.support.v7.widget.ViewStubCompat) r0;
        if (r0 == 0) goto L_0x00d6;
    L_0x00c3:
        r4 = r7.getActionBarThemedContext();
        r4 = android.view.LayoutInflater.from(r4);
        r0.setLayoutInflater(r4);
        r0 = r0.inflate();
        r0 = (android.support.v7.widget.ActionBarContextView) r0;
        r7.mActionModeView = r0;
    L_0x00d6:
        r0 = r7.mActionModeView;
        if (r0 == 0) goto L_0x0166;
    L_0x00da:
        r7.endOnGoingFadeAnimation();
        r0 = r7.mActionModeView;
        r0.killMode();
        r0 = new android.support.v7.view.StandaloneActionMode;
        r4 = r7.mActionModeView;
        r4 = r4.getContext();
        r5 = r7.mActionModeView;
        r6 = r7.mActionModePopup;
        if (r6 != 0) goto L_0x00f1;
    L_0x00f0:
        goto L_0x00f2;
    L_0x00f1:
        r3 = 0;
    L_0x00f2:
        r0.<init>(r4, r5, r8, r3);
        r3 = r0.getMenu();
        r8 = r8.onCreateActionMode(r0, r3);
        if (r8 == 0) goto L_0x0164;
    L_0x00ff:
        r0.invalidate();
        r8 = r7.mActionModeView;
        r8.initForMode(r0);
        r7.mActionMode = r0;
        r8 = r7.shouldAnimateActionModeView();
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        if (r8 == 0) goto L_0x012e;
    L_0x0111:
        r8 = r7.mActionModeView;
        r1 = 0;
        r8.setAlpha(r1);
        r8 = r7.mActionModeView;
        r8 = android.support.v4.view.ViewCompat.animate(r8);
        r8 = r8.alpha(r0);
        r7.mFadeAnim = r8;
        r8 = r7.mFadeAnim;
        r0 = new android.support.v7.app.AppCompatDelegateImpl$7;
        r0.<init>();
        r8.setListener(r0);
        goto L_0x0154;
    L_0x012e:
        r8 = r7.mActionModeView;
        r8.setAlpha(r0);
        r8 = r7.mActionModeView;
        r8.setVisibility(r2);
        r8 = r7.mActionModeView;
        r0 = 32;
        r8.sendAccessibilityEvent(r0);
        r8 = r7.mActionModeView;
        r8 = r8.getParent();
        r8 = r8 instanceof android.view.View;
        if (r8 == 0) goto L_0x0154;
    L_0x0149:
        r8 = r7.mActionModeView;
        r8 = r8.getParent();
        r8 = (android.view.View) r8;
        android.support.v4.view.ViewCompat.requestApplyInsets(r8);
    L_0x0154:
        r8 = r7.mActionModePopup;
        if (r8 == 0) goto L_0x0166;
    L_0x0158:
        r8 = r7.mWindow;
        r8 = r8.getDecorView();
        r0 = r7.mShowActionModePopup;
        r8.post(r0);
        goto L_0x0166;
    L_0x0164:
        r7.mActionMode = r1;
    L_0x0166:
        r8 = r7.mActionMode;
        if (r8 == 0) goto L_0x0171;
    L_0x016a:
        r0 = r7.mAppCompatCallback;
        if (r0 == 0) goto L_0x0171;
    L_0x016e:
        r0.onSupportActionModeStarted(r8);
    L_0x0171:
        r8 = r7.mActionMode;
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.startSupportActionModeFromWindow(android.support.v7.view.ActionMode$Callback):android.support.v7.view.ActionMode");
    }

    final boolean shouldAnimateActionModeView() {
        if (this.mSubDecorInstalled) {
            View view = this.mSubDecor;
            if (view != null && ViewCompat.isLaidOut(view)) {
                return true;
            }
        }
        return false;
    }

    public void setHandleNativeActionModesEnabled(boolean z) {
        this.mHandleNativeActionModes = z;
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    void endOnGoingFadeAnimation() {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mFadeAnim;
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.cancel();
        }
    }

    boolean onBackPressed() {
        ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            actionMode.finish();
            return true;
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.collapseActionView()) {
            return false;
        }
        return true;
    }

    boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null && supportActionBar.onKeyShortcut(i, keyEvent) != 0) {
            return true;
        }
        i = this.mPreparedPanel;
        if (i == 0 || performPanelShortcut(i, keyEvent.getKeyCode(), keyEvent, 1) == 0) {
            if (this.mPreparedPanel == 0) {
                i = getPanelState(0, true);
                preparePanel(i, keyEvent);
                keyEvent = performPanelShortcut(i, keyEvent.getKeyCode(), keyEvent, 1);
                i.isPrepared = false;
                if (keyEvent != null) {
                    return true;
                }
            }
            return false;
        }
        i = this.mPreparedPanel;
        if (i != 0) {
            i.isHandled = true;
        }
        return true;
    }

    boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Window.Callback callback = this.mOriginalWindowCallback;
        boolean z = true;
        if ((callback instanceof Component) || (callback instanceof AppCompatDialog)) {
            View decorView = this.mWindow.getDecorView();
            if (decorView != null && KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
                return true;
            }
        }
        if (keyEvent.getKeyCode() == 82 && this.mOriginalWindowCallback.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            z = false;
        }
        return z ? onKeyDown(keyCode, keyEvent) : onKeyUp(keyCode, keyEvent);
    }

    boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4) {
            i = this.mLongPressBackDown;
            this.mLongPressBackDown = false;
            keyEvent = getPanelState(0, false);
            if (keyEvent != null && keyEvent.isOpen) {
                if (i == 0) {
                    closePanel(keyEvent, true);
                }
                return true;
            } else if (onBackPressed() != 0) {
                return true;
            }
        } else if (i == 82) {
            onKeyUpPanel(0, keyEvent);
            return true;
        }
        return false;
    }

    boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = true;
        if (i == 4) {
            if ((keyEvent.getFlags() & 128) == 0) {
                z = false;
            }
            this.mLongPressBackDown = z;
        } else if (i == 82) {
            onKeyDownPanel(0, keyEvent);
            return true;
        }
        return false;
    }

    public View createView(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        boolean z;
        boolean z2 = false;
        if (this.mAppCompatViewInflater == null) {
            String string = this.mContext.obtainStyledAttributes(C0291R.styleable.AppCompatTheme).getString(C0291R.styleable.AppCompatTheme_viewInflaterClass);
            if (string != null) {
                if (!AppCompatViewInflater.class.getName().equals(string)) {
                    try {
                        this.mAppCompatViewInflater = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    } catch (Throwable th) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to instantiate custom view inflater ");
                        stringBuilder.append(string);
                        stringBuilder.append(". Falling back to default.");
                        Log.i("AppCompatDelegate", stringBuilder.toString(), th);
                        this.mAppCompatViewInflater = new AppCompatViewInflater();
                    }
                }
            }
            this.mAppCompatViewInflater = new AppCompatViewInflater();
        }
        if (IS_PRE_LOLLIPOP) {
            if (!(attributeSet instanceof XmlPullParser)) {
                z2 = shouldInheritContext((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                z2 = true;
            }
            z = z2;
        } else {
            z = false;
        }
        return this.mAppCompatViewInflater.createView(view, str, context, attributeSet, z, IS_PRE_LOLLIPOP, true, VectorEnabledTintResources.shouldBeUsed());
    }

    private boolean shouldInheritContext(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        ViewParent decorView = this.mWindow.getDecorView();
        while (viewParent != null) {
            if (viewParent != decorView && (viewParent instanceof View)) {
                if (!ViewCompat.isAttachedToWindow((View) viewParent)) {
                    viewParent = viewParent.getParent();
                }
            }
            return false;
        }
        return true;
    }

    public void installViewFactory() {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.setFactory2(from, this);
        } else if (!(from.getFactory2() instanceof AppCompatDelegateImpl)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return createView(view, str, context, attributeSet);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private void openPanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        if (!panelFeatureState.isOpen) {
            if (!this.mIsDestroyed) {
                if (panelFeatureState.featureId == 0) {
                    if (((this.mContext.getResources().getConfiguration().screenLayout & 15) == 4 ? 1 : null) != null) {
                        return;
                    }
                }
                Window.Callback windowCallback = getWindowCallback();
                if (windowCallback == null || windowCallback.onMenuOpened(panelFeatureState.featureId, panelFeatureState.menu)) {
                    WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
                    if (windowManager != null && preparePanel(panelFeatureState, keyEvent) != null) {
                        int i;
                        KeyEvent layoutParams;
                        if (panelFeatureState.decorView != null) {
                            if (panelFeatureState.refreshDecorView == null) {
                                if (panelFeatureState.createdPanelView != null) {
                                    keyEvent = panelFeatureState.createdPanelView.getLayoutParams();
                                    if (keyEvent != null && keyEvent.width == -1) {
                                        i = -1;
                                        panelFeatureState.isHandled = false;
                                        layoutParams = new WindowManager.LayoutParams(i, -2, panelFeatureState.f9x, panelFeatureState.f10y, PointerIconCompat.TYPE_HAND, 8519680, -3);
                                        layoutParams.gravity = panelFeatureState.gravity;
                                        layoutParams.windowAnimations = panelFeatureState.windowAnimations;
                                        windowManager.addView(panelFeatureState.decorView, layoutParams);
                                        panelFeatureState.isOpen = true;
                                        return;
                                    }
                                }
                                i = -2;
                                panelFeatureState.isHandled = false;
                                layoutParams = new WindowManager.LayoutParams(i, -2, panelFeatureState.f9x, panelFeatureState.f10y, PointerIconCompat.TYPE_HAND, 8519680, -3);
                                layoutParams.gravity = panelFeatureState.gravity;
                                layoutParams.windowAnimations = panelFeatureState.windowAnimations;
                                windowManager.addView(panelFeatureState.decorView, layoutParams);
                                panelFeatureState.isOpen = true;
                                return;
                            }
                        }
                        if (panelFeatureState.decorView == null) {
                            if (initializePanelDecor(panelFeatureState) == null || panelFeatureState.decorView == null) {
                                return;
                            }
                        } else if (panelFeatureState.refreshDecorView != null && panelFeatureState.decorView.getChildCount() > null) {
                            panelFeatureState.decorView.removeAllViews();
                        }
                        if (initializePanelContent(panelFeatureState) != null) {
                            if (panelFeatureState.hasPanelItems() != null) {
                                keyEvent = panelFeatureState.shownPanelView.getLayoutParams();
                                if (keyEvent == null) {
                                    keyEvent = new LayoutParams(-2, -2);
                                }
                                panelFeatureState.decorView.setBackgroundResource(panelFeatureState.background);
                                ViewParent parent = panelFeatureState.shownPanelView.getParent();
                                if (parent != null && (parent instanceof ViewGroup)) {
                                    ((ViewGroup) parent).removeView(panelFeatureState.shownPanelView);
                                }
                                panelFeatureState.decorView.addView(panelFeatureState.shownPanelView, keyEvent);
                                if (panelFeatureState.shownPanelView.hasFocus() == null) {
                                    panelFeatureState.shownPanelView.requestFocus();
                                }
                                i = -2;
                                panelFeatureState.isHandled = false;
                                layoutParams = new WindowManager.LayoutParams(i, -2, panelFeatureState.f9x, panelFeatureState.f10y, PointerIconCompat.TYPE_HAND, 8519680, -3);
                                layoutParams.gravity = panelFeatureState.gravity;
                                layoutParams.windowAnimations = panelFeatureState.windowAnimations;
                                windowManager.addView(panelFeatureState.decorView, layoutParams);
                                panelFeatureState.isOpen = true;
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
                closePanel(panelFeatureState, true);
            }
        }
    }

    private boolean initializePanelDecor(PanelFeatureState panelFeatureState) {
        panelFeatureState.setStyle(getActionBarThemedContext());
        panelFeatureState.decorView = new ListMenuDecorView(panelFeatureState.listPresenterContext);
        panelFeatureState.gravity = 81;
        return true;
    }

    private void reopenMenu(MenuBuilder menuBuilder, boolean z) {
        menuBuilder = this.mDecorContentParent;
        if (menuBuilder == null || menuBuilder.canShowOverflowMenu() == null || (ViewConfiguration.get(this.mContext).hasPermanentMenuKey() != null && this.mDecorContentParent.isOverflowMenuShowPending() == null)) {
            menuBuilder = getPanelState(0, true);
            menuBuilder.refreshDecorView = true;
            closePanel(menuBuilder, false);
            openPanel(menuBuilder, false);
            return;
        }
        menuBuilder = getWindowCallback();
        if (this.mDecorContentParent.isOverflowMenuShowing()) {
            if (z) {
                this.mDecorContentParent.hideOverflowMenu();
                if (!this.mIsDestroyed) {
                    menuBuilder.onPanelClosed(108, getPanelState(0, true).menu);
                }
            }
        }
        if (!(menuBuilder == null || this.mIsDestroyed)) {
            if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & true)) {
                this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                this.mInvalidatePanelMenuRunnable.run();
            }
            z = getPanelState(0, true);
            if (!(z.menu == null || z.refreshMenuContent || !menuBuilder.onPreparePanel(0, z.createdPanelView, z.menu))) {
                menuBuilder.onMenuOpened(108, z.menu);
                this.mDecorContentParent.showOverflowMenu();
            }
        }
    }

    private boolean initializePanelMenu(PanelFeatureState panelFeatureState) {
        Context context = this.mContext;
        if ((panelFeatureState.featureId == 0 || panelFeatureState.featureId == 108) && this.mDecorContentParent != null) {
            TypedValue typedValue = new TypedValue();
            Theme theme = context.getTheme();
            theme.resolveAttribute(C0291R.attr.actionBarTheme, typedValue, true);
            Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(C0291R.attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(C0291R.attr.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            if (theme2 != null) {
                Context contextThemeWrapper = new ContextThemeWrapper(context, 0);
                contextThemeWrapper.getTheme().setTo(theme2);
                context = contextThemeWrapper;
            }
        }
        MenuBuilder menuBuilder = new MenuBuilder(context);
        menuBuilder.setCallback(this);
        panelFeatureState.setMenu(menuBuilder);
        return true;
    }

    private boolean initializePanelContent(PanelFeatureState panelFeatureState) {
        boolean z = true;
        if (panelFeatureState.createdPanelView != null) {
            panelFeatureState.shownPanelView = panelFeatureState.createdPanelView;
            return true;
        } else if (panelFeatureState.menu == null) {
            return false;
        } else {
            if (this.mPanelMenuPresenterCallback == null) {
                this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback();
            }
            panelFeatureState.shownPanelView = (View) panelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback);
            if (panelFeatureState.shownPanelView == null) {
                z = false;
            }
            return z;
        }
    }

    private boolean preparePanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        if (this.mIsDestroyed) {
            return false;
        }
        if (panelFeatureState.isPrepared) {
            return true;
        }
        Object obj;
        DecorContentParent decorContentParent;
        PanelFeatureState panelFeatureState2 = this.mPreparedPanel;
        if (!(panelFeatureState2 == null || panelFeatureState2 == panelFeatureState)) {
            closePanel(panelFeatureState2, false);
        }
        Window.Callback windowCallback = getWindowCallback();
        if (windowCallback != null) {
            panelFeatureState.createdPanelView = windowCallback.onCreatePanelView(panelFeatureState.featureId);
        }
        if (panelFeatureState.featureId != 0) {
            if (panelFeatureState.featureId != 108) {
                obj = null;
                if (obj != null) {
                    decorContentParent = this.mDecorContentParent;
                    if (decorContentParent != null) {
                        decorContentParent.setMenuPrepared();
                    }
                }
                if (panelFeatureState.createdPanelView == null && (obj == null || !(peekSupportActionBar() instanceof ToolbarActionBar))) {
                    if (panelFeatureState.menu == null || panelFeatureState.refreshMenuContent) {
                        if (panelFeatureState.menu != null && (!initializePanelMenu(panelFeatureState) || panelFeatureState.menu == null)) {
                            return false;
                        }
                        if (!(obj == null || this.mDecorContentParent == null)) {
                            if (this.mActionMenuPresenterCallback == null) {
                                this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
                            }
                            this.mDecorContentParent.setMenu(panelFeatureState.menu, this.mActionMenuPresenterCallback);
                        }
                        panelFeatureState.menu.stopDispatchingItemsChanged();
                        if (windowCallback.onCreatePanelMenu(panelFeatureState.featureId, panelFeatureState.menu)) {
                            panelFeatureState.setMenu(null);
                            if (obj != null) {
                                panelFeatureState = this.mDecorContentParent;
                                if (panelFeatureState != null) {
                                    panelFeatureState.setMenu(null, this.mActionMenuPresenterCallback);
                                }
                            }
                            return false;
                        }
                        panelFeatureState.refreshMenuContent = false;
                    }
                    panelFeatureState.menu.stopDispatchingItemsChanged();
                    if (panelFeatureState.frozenActionViewState != null) {
                        panelFeatureState.menu.restoreActionViewStates(panelFeatureState.frozenActionViewState);
                        panelFeatureState.frozenActionViewState = null;
                    }
                    if (windowCallback.onPreparePanel(0, panelFeatureState.createdPanelView, panelFeatureState.menu)) {
                        if (obj != null) {
                            keyEvent = this.mDecorContentParent;
                            if (keyEvent != null) {
                                keyEvent.setMenu(null, this.mActionMenuPresenterCallback);
                            }
                        }
                        panelFeatureState.menu.startDispatchingItemsChanged();
                        return false;
                    }
                    panelFeatureState.qwertyMode = KeyCharacterMap.load(keyEvent == null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1 ? true : null;
                    panelFeatureState.menu.setQwertyMode(panelFeatureState.qwertyMode);
                    panelFeatureState.menu.startDispatchingItemsChanged();
                }
                panelFeatureState.isPrepared = true;
                panelFeatureState.isHandled = false;
                this.mPreparedPanel = panelFeatureState;
                return true;
            }
        }
        obj = 1;
        if (obj != null) {
            decorContentParent = this.mDecorContentParent;
            if (decorContentParent != null) {
                decorContentParent.setMenuPrepared();
            }
        }
        if (panelFeatureState.menu != null) {
        }
        if (this.mActionMenuPresenterCallback == null) {
            this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
        }
        this.mDecorContentParent.setMenu(panelFeatureState.menu, this.mActionMenuPresenterCallback);
        panelFeatureState.menu.stopDispatchingItemsChanged();
        if (windowCallback.onCreatePanelMenu(panelFeatureState.featureId, panelFeatureState.menu)) {
            panelFeatureState.refreshMenuContent = false;
            panelFeatureState.menu.stopDispatchingItemsChanged();
            if (panelFeatureState.frozenActionViewState != null) {
                panelFeatureState.menu.restoreActionViewStates(panelFeatureState.frozenActionViewState);
                panelFeatureState.frozenActionViewState = null;
            }
            if (windowCallback.onPreparePanel(0, panelFeatureState.createdPanelView, panelFeatureState.menu)) {
                if (keyEvent == null) {
                }
                if (KeyCharacterMap.load(keyEvent == null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
                }
                panelFeatureState.qwertyMode = KeyCharacterMap.load(keyEvent == null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1 ? true : null;
                panelFeatureState.menu.setQwertyMode(panelFeatureState.qwertyMode);
                panelFeatureState.menu.startDispatchingItemsChanged();
                panelFeatureState.isPrepared = true;
                panelFeatureState.isHandled = false;
                this.mPreparedPanel = panelFeatureState;
                return true;
            }
            if (obj != null) {
                keyEvent = this.mDecorContentParent;
                if (keyEvent != null) {
                    keyEvent.setMenu(null, this.mActionMenuPresenterCallback);
                }
            }
            panelFeatureState.menu.startDispatchingItemsChanged();
            return false;
        }
        panelFeatureState.setMenu(null);
        if (obj != null) {
            panelFeatureState = this.mDecorContentParent;
            if (panelFeatureState != null) {
                panelFeatureState.setMenu(null, this.mActionMenuPresenterCallback);
            }
        }
        return false;
    }

    void checkCloseActionMenu(MenuBuilder menuBuilder) {
        if (!this.mClosingActionMenu) {
            this.mClosingActionMenu = true;
            this.mDecorContentParent.dismissPopups();
            Window.Callback windowCallback = getWindowCallback();
            if (!(windowCallback == null || this.mIsDestroyed)) {
                windowCallback.onPanelClosed(108, menuBuilder);
            }
            this.mClosingActionMenu = null;
        }
    }

    void closePanel(int i) {
        closePanel(getPanelState(i, true), true);
    }

    void closePanel(PanelFeatureState panelFeatureState, boolean z) {
        if (z && panelFeatureState.featureId == 0) {
            DecorContentParent decorContentParent = this.mDecorContentParent;
            if (decorContentParent != null && decorContentParent.isOverflowMenuShowing()) {
                checkCloseActionMenu(panelFeatureState.menu);
                return;
            }
        }
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        if (!(windowManager == null || !panelFeatureState.isOpen || panelFeatureState.decorView == null)) {
            windowManager.removeView(panelFeatureState.decorView);
            if (z) {
                callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, null);
            }
        }
        panelFeatureState.isPrepared = false;
        panelFeatureState.isHandled = false;
        panelFeatureState.isOpen = false;
        panelFeatureState.shownPanelView = null;
        panelFeatureState.refreshDecorView = true;
        if (this.mPreparedPanel == panelFeatureState) {
            this.mPreparedPanel = null;
        }
    }

    private boolean onKeyDownPanel(int i, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            i = getPanelState(i, true);
            if (!i.isOpen) {
                return preparePanel(i, keyEvent);
            }
        }
        return false;
    }

    private boolean onKeyUpPanel(int i, KeyEvent keyEvent) {
        if (this.mActionMode != null) {
            return false;
        }
        AudioManager audioManager;
        PanelFeatureState panelState = getPanelState(i, true);
        if (i == 0) {
            i = this.mDecorContentParent;
            if (!(i == 0 || i.canShowOverflowMenu() == 0 || ViewConfiguration.get(this.mContext).hasPermanentMenuKey() != 0)) {
                if (this.mDecorContentParent.isOverflowMenuShowing() == 0) {
                    if (this.mIsDestroyed == 0 && preparePanel(panelState, keyEvent) != 0) {
                        i = this.mDecorContentParent.showOverflowMenu();
                    }
                    i = 0;
                } else {
                    i = this.mDecorContentParent.hideOverflowMenu();
                }
                if (i != 0) {
                    audioManager = (AudioManager) this.mContext.getSystemService("audio");
                    if (audioManager == null) {
                        audioManager.playSoundEffect(0);
                    } else {
                        Log.w("AppCompatDelegate", "Couldn't get audio manager");
                    }
                }
                return i;
            }
        }
        if (panelState.isOpen == 0) {
            if (panelState.isHandled == 0) {
                if (panelState.isPrepared != 0) {
                    if (panelState.refreshMenuContent != 0) {
                        panelState.isPrepared = false;
                        i = preparePanel(panelState, keyEvent);
                    } else {
                        i = 1;
                    }
                    if (i != 0) {
                        openPanel(panelState, keyEvent);
                        i = 1;
                        if (i != 0) {
                            audioManager = (AudioManager) this.mContext.getSystemService("audio");
                            if (audioManager == null) {
                                Log.w("AppCompatDelegate", "Couldn't get audio manager");
                            } else {
                                audioManager.playSoundEffect(0);
                            }
                        }
                        return i;
                    }
                }
                i = 0;
                if (i != 0) {
                    audioManager = (AudioManager) this.mContext.getSystemService("audio");
                    if (audioManager == null) {
                        audioManager.playSoundEffect(0);
                    } else {
                        Log.w("AppCompatDelegate", "Couldn't get audio manager");
                    }
                }
                return i;
            }
        }
        i = panelState.isOpen;
        closePanel(panelState, true);
        if (i != 0) {
            audioManager = (AudioManager) this.mContext.getSystemService("audio");
            if (audioManager == null) {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            } else {
                audioManager.playSoundEffect(0);
            }
        }
        return i;
    }

    void callOnPanelClosed(int i, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.mPanels;
                if (i < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.menu;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.isOpen != null) && this.mIsDestroyed == null) {
            this.mOriginalWindowCallback.onPanelClosed(i, menu);
        }
    }

    PanelFeatureState findMenuPanel(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        int i = 0;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        while (i < length) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
            if (panelFeatureState != null && panelFeatureState.menu == menu) {
                return panelFeatureState;
            }
            i++;
        }
        return null;
    }

    protected PanelFeatureState getPanelState(int i, boolean z) {
        z = this.mPanels;
        if (!z || z.length <= i) {
            Object obj = new PanelFeatureState[(i + 1)];
            if (z) {
                System.arraycopy(z, 0, obj, 0, z.length);
            }
            this.mPanels = obj;
            z = obj;
        }
        PanelFeatureState panelFeatureState = z[i];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        panelFeatureState = new PanelFeatureState(i);
        z[i] = panelFeatureState;
        return panelFeatureState;
    }

    private boolean performPanelShortcut(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent, int i2) {
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.isPrepared || preparePanel(panelFeatureState, keyEvent)) && panelFeatureState.menu != null) {
            z = panelFeatureState.menu.performShortcut(i, keyEvent, i2);
        }
        if (z && (i2 & 1) == null && this.mDecorContentParent == null) {
            closePanel(panelFeatureState, 1);
        }
        return z;
    }

    private void invalidatePanelMenu(int i) {
        this.mInvalidatePanelMenuFeatures = (1 << i) | this.mInvalidatePanelMenuFeatures;
        if (this.mInvalidatePanelMenuPosted == 0) {
            ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }

    void doInvalidatePanelMenu(int i) {
        PanelFeatureState panelState = getPanelState(i, true);
        if (panelState.menu != null) {
            Bundle bundle = new Bundle();
            panelState.menu.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                panelState.frozenActionViewState = bundle;
            }
            panelState.menu.stopDispatchingItemsChanged();
            panelState.menu.clear();
        }
        panelState.refreshMenuContent = true;
        panelState.refreshDecorView = true;
        if ((i == 108 || i == 0) && this.mDecorContentParent != 0) {
            PanelFeatureState panelState2 = getPanelState(0, false);
            if (panelState2 != null) {
                panelState2.isPrepared = false;
                preparePanel(panelState2, 0);
            }
        }
    }

    int updateStatusGuard(int i) {
        Object obj;
        ActionBarContextView actionBarContextView = this.mActionModeView;
        int i2 = 0;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof MarginLayoutParams)) {
            obj = null;
        } else {
            Object obj2;
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mActionModeView.getLayoutParams();
            obj = 1;
            if (this.mActionModeView.isShown()) {
                if (this.mTempRect1 == null) {
                    this.mTempRect1 = new Rect();
                    this.mTempRect2 = new Rect();
                }
                Rect rect = this.mTempRect1;
                Rect rect2 = this.mTempRect2;
                rect.set(0, i, 0, 0);
                ViewUtils.computeFitSystemWindows(this.mSubDecor, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i : 0)) {
                    marginLayoutParams.topMargin = i;
                    View view = this.mStatusGuard;
                    if (view == null) {
                        this.mStatusGuard = new View(this.mContext);
                        this.mStatusGuard.setBackgroundColor(this.mContext.getResources().getColor(C0291R.color.abc_input_method_navigation_guard));
                        this.mSubDecor.addView(this.mStatusGuard, -1, new LayoutParams(-1, i));
                    } else {
                        LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i) {
                            layoutParams.height = i;
                            this.mStatusGuard.setLayoutParams(layoutParams);
                        }
                    }
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                if (this.mStatusGuard == null) {
                    obj = null;
                }
                if (!(this.mOverlayActionMode || r3 == null)) {
                    i = 0;
                }
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                obj2 = 1;
                obj = null;
            } else {
                obj2 = null;
                obj = null;
            }
            if (obj2 != null) {
                this.mActionModeView.setLayoutParams(marginLayoutParams);
            }
        }
        View view2 = this.mStatusGuard;
        if (view2 != null) {
            if (obj == null) {
                i2 = 8;
            }
            view2.setVisibility(i2);
        }
        return i;
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private int sanitizeWindowFeatureId(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i != 9) {
            return i;
        } else {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
    }

    ViewGroup getSubDecor() {
        return this.mSubDecor;
    }

    void dismissPopups() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        r0 = r2.mDecorContentParent;
        if (r0 == 0) goto L_0x0007;
    L_0x0004:
        r0.dismissPopups();
    L_0x0007:
        r0 = r2.mActionModePopup;
        if (r0 == 0) goto L_0x0026;
    L_0x000b:
        r0 = r2.mWindow;
        r0 = r0.getDecorView();
        r1 = r2.mShowActionModePopup;
        r0.removeCallbacks(r1);
        r0 = r2.mActionModePopup;
        r0 = r0.isShowing();
        if (r0 == 0) goto L_0x0023;
    L_0x001e:
        r0 = r2.mActionModePopup;	 Catch:{ IllegalArgumentException -> 0x0023 }
        r0.dismiss();	 Catch:{ IllegalArgumentException -> 0x0023 }
    L_0x0023:
        r0 = 0;
        r2.mActionModePopup = r0;
    L_0x0026:
        r2.endOnGoingFadeAnimation();
        r0 = 0;
        r0 = r2.getPanelState(r0, r0);
        if (r0 == 0) goto L_0x0039;
    L_0x0030:
        r1 = r0.menu;
        if (r1 == 0) goto L_0x0039;
    L_0x0034:
        r0 = r0.menu;
        r0.close();
    L_0x0039:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.dismissPopups():void");
    }

    public boolean applyDayNight() {
        int nightMode = getNightMode();
        int mapNightMode = mapNightMode(nightMode);
        boolean updateForNightMode = mapNightMode != -1 ? updateForNightMode(mapNightMode) : false;
        if (nightMode == 0) {
            ensureAutoNightModeManager();
            this.mAutoNightModeManager.setup();
        }
        this.mApplyDayNightCalled = true;
        return updateForNightMode;
    }

    public void setLocalNightMode(int i) {
        switch (i) {
            case -1:
            case 0:
            case 1:
            case 2:
                if (this.mLocalNightMode != i) {
                    this.mLocalNightMode = i;
                    if (this.mApplyDayNightCalled != 0) {
                        applyDayNight();
                        return;
                    }
                    return;
                }
                return;
            default:
                Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
                return;
        }
    }

    int mapNightMode(int i) {
        if (i == -100) {
            return -1;
        }
        if (i != 0) {
            return i;
        }
        if (VERSION.SDK_INT >= 23 && ((UiModeManager) this.mContext.getSystemService(UiModeManager.class)).getNightMode() == 0) {
            return -1;
        }
        ensureAutoNightModeManager();
        return this.mAutoNightModeManager.getApplyableNightMode();
    }

    private int getNightMode() {
        int i = this.mLocalNightMode;
        return i != -100 ? i : AppCompatDelegate.getDefaultNightMode();
    }

    private boolean updateForNightMode(int i) {
        Resources resources = this.mContext.getResources();
        Configuration configuration = resources.getConfiguration();
        int i2 = configuration.uiMode & 48;
        i = i == 2 ? 32 : 16;
        if (i2 == i) {
            return false;
        }
        if (shouldRecreateOnNightModeChange()) {
            ((Activity) this.mContext).recreate();
        } else {
            Configuration configuration2 = new Configuration(configuration);
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            configuration2.uiMode = i | (configuration2.uiMode & -49);
            resources.updateConfiguration(configuration2, displayMetrics);
            if (VERSION.SDK_INT < 26) {
                ResourcesFlusher.flush(resources);
            }
        }
        return true;
    }

    private void ensureAutoNightModeManager() {
        if (this.mAutoNightModeManager == null) {
            this.mAutoNightModeManager = new AutoNightModeManager(TwilightManager.getInstance(this.mContext));
        }
    }

    @VisibleForTesting
    final AutoNightModeManager getAutoNightModeManager() {
        ensureAutoNightModeManager();
        return this.mAutoNightModeManager;
    }

    private boolean shouldRecreateOnNightModeChange() {
        boolean z = false;
        if (this.mApplyDayNightCalled) {
            Context context = this.mContext;
            if (context instanceof Activity) {
                try {
                    if ((context.getPackageManager().getActivityInfo(new ComponentName(this.mContext, this.mContext.getClass()), 0).configChanges & 512) == 0) {
                        z = true;
                    }
                    return z;
                } catch (Throwable e) {
                    Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e);
                    return true;
                }
            }
        }
        return false;
    }

    public final Delegate getDrawerToggleDelegate() {
        return new ActionBarDrawableToggleImpl();
    }
}
