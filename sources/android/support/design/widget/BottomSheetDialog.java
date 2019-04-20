package android.support.design.widget;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.C0026R;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class BottomSheetDialog extends AppCompatDialog {
    private BottomSheetBehavior<FrameLayout> behavior;
    private BottomSheetCallback bottomSheetCallback;
    boolean cancelable;
    private boolean canceledOnTouchOutside;
    private boolean canceledOnTouchOutsideSet;

    /* renamed from: android.support.design.widget.BottomSheetDialog$1 */
    class C00631 implements OnClickListener {
        C00631() {
        }

        public void onClick(View view) {
            if (BottomSheetDialog.this.cancelable != null && BottomSheetDialog.this.isShowing() != null && BottomSheetDialog.this.shouldWindowCloseOnTouchOutside() != null) {
                BottomSheetDialog.this.cancel();
            }
        }
    }

    /* renamed from: android.support.design.widget.BottomSheetDialog$3 */
    class C00643 implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }

        C00643() {
        }
    }

    /* renamed from: android.support.design.widget.BottomSheetDialog$2 */
    class C04682 extends AccessibilityDelegateCompat {
        C04682() {
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (BottomSheetDialog.this.cancelable != null) {
                accessibilityNodeInfoCompat.addAction(1048576);
                accessibilityNodeInfoCompat.setDismissable(true);
                return;
            }
            accessibilityNodeInfoCompat.setDismissable(null);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (i != 1048576 || !BottomSheetDialog.this.cancelable) {
                return super.performAccessibilityAction(view, i, bundle);
            }
            BottomSheetDialog.this.cancel();
            return true;
        }
    }

    /* renamed from: android.support.design.widget.BottomSheetDialog$4 */
    class C04694 extends BottomSheetCallback {
        public void onSlide(@NonNull View view, float f) {
        }

        C04694() {
        }

        public void onStateChanged(@NonNull View view, int i) {
            if (i == 5) {
                BottomSheetDialog.this.cancel();
            }
        }
    }

    public BottomSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public BottomSheetDialog(@NonNull Context context, @StyleRes int i) {
        super(context, getThemeResId(context, i));
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.bottomSheetCallback = new C04694();
        supportRequestWindowFeature(1);
    }

    protected BottomSheetDialog(@NonNull Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.bottomSheetCallback = new C04694();
        supportRequestWindowFeature(1);
        this.cancelable = z;
    }

    public void setContentView(@LayoutRes int i) {
        super.setContentView(wrapInBottomSheet(i, null, null));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getWindow();
        if (bundle != null) {
            if (VERSION.SDK_INT >= 21) {
                bundle.clearFlags(67108864);
                bundle.addFlags(Integer.MIN_VALUE);
            }
            bundle.setLayout(-1, -1);
        }
    }

    public void setContentView(View view) {
        super.setContentView(wrapInBottomSheet(0, view, null));
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(wrapInBottomSheet(0, view, layoutParams));
    }

    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.cancelable != z) {
            this.cancelable = z;
            BottomSheetBehavior bottomSheetBehavior = this.behavior;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.setHideable(z);
            }
        }
    }

    protected void onStart() {
        super.onStart();
        BottomSheetBehavior bottomSheetBehavior = this.behavior;
        if (bottomSheetBehavior != null && bottomSheetBehavior.getState() == 5) {
            this.behavior.setState(4);
        }
    }

    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.cancelable) {
            this.cancelable = true;
        }
        this.canceledOnTouchOutside = z;
        this.canceledOnTouchOutsideSet = true;
    }

    private View wrapInBottomSheet(int i, View view, LayoutParams layoutParams) {
        FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), C0026R.layout.design_bottom_sheet_dialog, null);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) frameLayout.findViewById(C0026R.id.coordinator);
        if (i != 0 && view == null) {
            view = getLayoutInflater().inflate(i, coordinatorLayout, false);
        }
        FrameLayout frameLayout2 = (FrameLayout) coordinatorLayout.findViewById(C0026R.id.design_bottom_sheet);
        this.behavior = BottomSheetBehavior.from(frameLayout2);
        this.behavior.setBottomSheetCallback(this.bottomSheetCallback);
        this.behavior.setHideable(this.cancelable);
        if (layoutParams == null) {
            frameLayout2.addView(view);
        } else {
            frameLayout2.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(C0026R.id.touch_outside).setOnClickListener(new C00631());
        ViewCompat.setAccessibilityDelegate(frameLayout2, new C04682());
        frameLayout2.setOnTouchListener(new C00643());
        return frameLayout;
    }

    boolean shouldWindowCloseOnTouchOutside() {
        if (!this.canceledOnTouchOutsideSet) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{16843611});
            this.canceledOnTouchOutside = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
            this.canceledOnTouchOutsideSet = true;
        }
        return this.canceledOnTouchOutside;
    }

    private static int getThemeResId(Context context, int i) {
        if (i != 0) {
            return i;
        }
        i = new TypedValue();
        if (context.getTheme().resolveAttribute(C0026R.attr.bottomSheetDialogTheme, i, true) != null) {
            return i.resourceId;
        }
        return C0026R.style.Theme_Design_Light_BottomSheetDialog;
    }
}
