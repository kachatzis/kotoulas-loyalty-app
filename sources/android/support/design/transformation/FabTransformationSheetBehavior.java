package android.support.design.transformation;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.CallSuper;
import android.support.design.C0026R;
import android.support.design.animation.MotionSpec;
import android.support.design.animation.Positioning;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import java.util.HashMap;
import java.util.Map;

public class FabTransformationSheetBehavior extends FabTransformationBehavior {
    private Map<View, Integer> importantForAccessibilityMap;

    public FabTransformationSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected FabTransformationSpec onCreateMotionSpec(Context context, boolean z) {
        if (z) {
            z = C0026R.animator.mtrl_fab_transformation_sheet_expand_spec;
        } else {
            z = C0026R.animator.mtrl_fab_transformation_sheet_collapse_spec;
        }
        FabTransformationSpec fabTransformationSpec = new FabTransformationSpec();
        fabTransformationSpec.timings = MotionSpec.createFromResource(context, z);
        fabTransformationSpec.positioning = new Positioning(true, 0.0f, 0.0f);
        return fabTransformationSpec;
    }

    @CallSuper
    protected boolean onExpandedStateChange(View view, View view2, boolean z, boolean z2) {
        updateImportantForAccessibility(view2, z);
        return super.onExpandedStateChange(view, view2, z, z2);
    }

    private void updateImportantForAccessibility(View view, boolean z) {
        ViewParent parent = view.getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (VERSION.SDK_INT >= 16 && z) {
                this.importantForAccessibilityMap = new HashMap(childCount);
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                Object obj = ((childAt.getLayoutParams() instanceof LayoutParams) && (((LayoutParams) childAt.getLayoutParams()).getBehavior() instanceof FabTransformationScrimBehavior)) ? 1 : null;
                if (childAt != view) {
                    if (obj == null) {
                        if (z) {
                            if (VERSION.SDK_INT >= 16) {
                                this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                            }
                            ViewCompat.setImportantForAccessibility(childAt, 4);
                        } else {
                            Map map = this.importantForAccessibilityMap;
                            if (map != null && map.containsKey(childAt)) {
                                ViewCompat.setImportantForAccessibility(childAt, ((Integer) this.importantForAccessibilityMap.get(childAt)).intValue());
                            }
                        }
                    }
                }
            }
            if (!z) {
                this.importantForAccessibilityMap = null;
            }
        }
    }
}
