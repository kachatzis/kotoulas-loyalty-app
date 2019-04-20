package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.appcompat.C0291R;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

@RestrictTo({Scope.LIBRARY_GROUP})
class TooltipPopup {
    private static final String TAG = "TooltipPopup";
    private final View mContentView;
    private final Context mContext;
    private final LayoutParams mLayoutParams = new LayoutParams();
    private final TextView mMessageView;
    private final int[] mTmpAnchorPos = new int[2];
    private final int[] mTmpAppPos = new int[2];
    private final Rect mTmpDisplayFrame = new Rect();

    TooltipPopup(Context context) {
        this.mContext = context;
        this.mContentView = LayoutInflater.from(this.mContext).inflate(C0291R.layout.abc_tooltip, null);
        this.mMessageView = (TextView) this.mContentView.findViewById(C0291R.id.message);
        this.mLayoutParams.setTitle(getClass().getSimpleName());
        this.mLayoutParams.packageName = this.mContext.getPackageName();
        context = this.mLayoutParams;
        context.type = PointerIconCompat.TYPE_HAND;
        context.width = -2;
        context.height = -2;
        context.format = -3;
        context.windowAnimations = C0291R.style.Animation_AppCompat_Tooltip;
        this.mLayoutParams.flags = 24;
    }

    void show(View view, int i, int i2, boolean z, CharSequence charSequence) {
        if (isShowing()) {
            hide();
        }
        this.mMessageView.setText(charSequence);
        computePosition(view, i, i2, z, this.mLayoutParams);
        ((WindowManager) this.mContext.getSystemService("window")).addView(this.mContentView, this.mLayoutParams);
    }

    void hide() {
        if (isShowing()) {
            ((WindowManager) this.mContext.getSystemService("window")).removeView(this.mContentView);
        }
    }

    boolean isShowing() {
        return this.mContentView.getParent() != null;
    }

    private void computePosition(View view, int i, int i2, boolean z, LayoutParams layoutParams) {
        int i3;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(C0291R.dimen.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(C0291R.dimen.tooltip_precise_anchor_extra_offset);
            i3 = i2 + dimensionPixelOffset;
            i2 -= dimensionPixelOffset;
        } else {
            i3 = view.getHeight();
            i2 = 0;
        }
        layoutParams.gravity = 49;
        dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(z ? C0291R.dimen.tooltip_y_offset_touch : C0291R.dimen.tooltip_y_offset_non_touch);
        View appRootView = getAppRootView(view);
        if (appRootView == null) {
            Log.e(TAG, "Cannot find app view");
            return;
        }
        appRootView.getWindowVisibleDisplayFrame(this.mTmpDisplayFrame);
        if (this.mTmpDisplayFrame.left < 0 && this.mTmpDisplayFrame.top < 0) {
            Resources resources = this.mContext.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", SystemMediaRouteProvider.PACKAGE_NAME);
            identifier = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.mTmpDisplayFrame.set(0, identifier, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        appRootView.getLocationOnScreen(this.mTmpAppPos);
        view.getLocationOnScreen(this.mTmpAnchorPos);
        view = this.mTmpAnchorPos;
        int i4 = view[0];
        int[] iArr = this.mTmpAppPos;
        view[0] = i4 - iArr[0];
        view[1] = view[1] - iArr[1];
        layoutParams.x = (view[0] + i) - (appRootView.getWidth() / 2);
        view = MeasureSpec.makeMeasureSpec(0, 0);
        this.mContentView.measure(view, view);
        view = this.mContentView.getMeasuredHeight();
        i = this.mTmpAnchorPos;
        int i5 = ((i[1] + i2) - dimensionPixelOffset) - view;
        i = (i[1] + i3) + dimensionPixelOffset;
        if (z) {
            if (i5 >= 0) {
                layoutParams.y = i5;
            } else {
                layoutParams.y = i;
            }
        } else if (view + i <= this.mTmpDisplayFrame.height()) {
            layoutParams.y = i;
        } else {
            layoutParams.y = i5;
        }
    }

    private static View getAppRootView(View view) {
        View rootView = view.getRootView();
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        if ((layoutParams instanceof LayoutParams) && ((LayoutParams) layoutParams).type == 2) {
            return rootView;
        }
        for (view = view.getContext(); view instanceof ContextWrapper; view = ((ContextWrapper) view).getBaseContext()) {
            if (view instanceof Activity) {
                return ((Activity) view).getWindow().getDecorView();
            }
        }
        return rootView;
    }
}
