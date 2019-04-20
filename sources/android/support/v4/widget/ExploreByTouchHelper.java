package android.support.v4.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.FocusStrategy.BoundsAdapter;
import android.support.v4.widget.FocusStrategy.CollectionAdapter;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.List;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private static final Rect INVALID_PARENT_BOUNDS = new Rect(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final BoundsAdapter<AccessibilityNodeInfoCompat> NODE_ADAPTER = new C05071();
    private static final CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> SPARSE_VALUES_ADAPTER = new C05082();
    int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
    private final View mHost;
    private int mHoveredVirtualViewId = Integer.MIN_VALUE;
    int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    private final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final int[] mTempGlobalRect = new int[2];
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();

    /* renamed from: android.support.v4.widget.ExploreByTouchHelper$1 */
    static class C05071 implements BoundsAdapter<AccessibilityNodeInfoCompat> {
        C05071() {
        }

        public void obtainBounds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Rect rect) {
            accessibilityNodeInfoCompat.getBoundsInParent(rect);
        }
    }

    /* renamed from: android.support.v4.widget.ExploreByTouchHelper$2 */
    static class C05082 implements CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> {
        C05082() {
        }

        public AccessibilityNodeInfoCompat get(SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat, int i) {
            return (AccessibilityNodeInfoCompat) sparseArrayCompat.valueAt(i);
        }

        public int size(SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat) {
            return sparseArrayCompat.size();
        }
    }

    private class MyNodeProvider extends AccessibilityNodeProviderCompat {
        MyNodeProvider() {
        }

        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i) {
            return AccessibilityNodeInfoCompat.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(i));
        }

        public boolean performAction(int i, int i2, Bundle bundle) {
            return ExploreByTouchHelper.this.performAction(i, i2, bundle);
        }

        public AccessibilityNodeInfoCompat findFocus(int i) {
            i = i == 2 ? ExploreByTouchHelper.this.mAccessibilityFocusedVirtualViewId : ExploreByTouchHelper.this.mKeyboardFocusedVirtualViewId;
            if (i == Integer.MIN_VALUE) {
                return 0;
            }
            return createAccessibilityNodeInfo(i);
        }
    }

    @android.support.annotation.NonNull
    private android.support.v4.view.accessibility.AccessibilityNodeInfoCompat createNodeForChild(int r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:49:0x0153 in {4, 6, 15, 16, 19, 20, 22, 25, 32, 33, 34, 41, 42, 44, 46, 48} preds:[]
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
        r0 = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.obtain();
        r1 = 1;
        r0.setEnabled(r1);
        r0.setFocusable(r1);
        r2 = "android.view.View";
        r0.setClassName(r2);
        r2 = INVALID_PARENT_BOUNDS;
        r0.setBoundsInParent(r2);
        r2 = INVALID_PARENT_BOUNDS;
        r0.setBoundsInScreen(r2);
        r2 = r7.mHost;
        r0.setParent(r2);
        r7.onPopulateNodeForVirtualView(r8, r0);
        r2 = r0.getText();
        if (r2 != 0) goto L_0x0037;
    L_0x0028:
        r2 = r0.getContentDescription();
        if (r2 == 0) goto L_0x002f;
    L_0x002e:
        goto L_0x0037;
    L_0x002f:
        r8 = new java.lang.RuntimeException;
        r0 = "Callbacks must add text or a content description in populateNodeForVirtualViewId()";
        r8.<init>(r0);
        throw r8;
    L_0x0037:
        r2 = r7.mTempParentRect;
        r0.getBoundsInParent(r2);
        r2 = r7.mTempParentRect;
        r3 = INVALID_PARENT_BOUNDS;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x014b;
    L_0x0046:
        r2 = r0.getActions();
        r3 = r2 & 64;
        if (r3 != 0) goto L_0x0143;
    L_0x004e:
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r2 = r2 & r3;
        if (r2 != 0) goto L_0x013b;
    L_0x0053:
        r2 = r7.mHost;
        r2 = r2.getContext();
        r2 = r2.getPackageName();
        r0.setPackageName(r2);
        r2 = r7.mHost;
        r0.setSource(r2, r8);
        r2 = r7.mAccessibilityFocusedVirtualViewId;
        r4 = 0;
        if (r2 != r8) goto L_0x0071;
    L_0x006a:
        r0.setAccessibilityFocused(r1);
        r0.addAction(r3);
        goto L_0x0079;
    L_0x0071:
        r0.setAccessibilityFocused(r4);
        r2 = 64;
        r0.addAction(r2);
    L_0x0079:
        r2 = r7.mKeyboardFocusedVirtualViewId;
        if (r2 != r8) goto L_0x007f;
    L_0x007d:
        r8 = 1;
        goto L_0x0080;
    L_0x007f:
        r8 = 0;
    L_0x0080:
        if (r8 == 0) goto L_0x0087;
    L_0x0082:
        r2 = 2;
        r0.addAction(r2);
        goto L_0x0090;
    L_0x0087:
        r2 = r0.isFocusable();
        if (r2 == 0) goto L_0x0090;
    L_0x008d:
        r0.addAction(r1);
    L_0x0090:
        r0.setFocused(r8);
        r8 = r7.mHost;
        r2 = r7.mTempGlobalRect;
        r8.getLocationOnScreen(r2);
        r8 = r7.mTempScreenRect;
        r0.getBoundsInScreen(r8);
        r8 = r7.mTempScreenRect;
        r2 = INVALID_PARENT_BOUNDS;
        r8 = r8.equals(r2);
        if (r8 == 0) goto L_0x00fb;
    L_0x00a9:
        r8 = r7.mTempScreenRect;
        r0.getBoundsInParent(r8);
        r8 = r0.mParentVirtualDescendantId;
        r2 = -1;
        if (r8 == r2) goto L_0x00e0;
    L_0x00b3:
        r8 = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.obtain();
        r3 = r0.mParentVirtualDescendantId;
    L_0x00b9:
        if (r3 == r2) goto L_0x00dd;
    L_0x00bb:
        r5 = r7.mHost;
        r8.setParent(r5, r2);
        r5 = INVALID_PARENT_BOUNDS;
        r8.setBoundsInParent(r5);
        r7.onPopulateNodeForVirtualView(r3, r8);
        r3 = r7.mTempParentRect;
        r8.getBoundsInParent(r3);
        r3 = r7.mTempScreenRect;
        r5 = r7.mTempParentRect;
        r5 = r5.left;
        r6 = r7.mTempParentRect;
        r6 = r6.top;
        r3.offset(r5, r6);
        r3 = r8.mParentVirtualDescendantId;
        goto L_0x00b9;
    L_0x00dd:
        r8.recycle();
    L_0x00e0:
        r8 = r7.mTempScreenRect;
        r2 = r7.mTempGlobalRect;
        r2 = r2[r4];
        r3 = r7.mHost;
        r3 = r3.getScrollX();
        r2 = r2 - r3;
        r3 = r7.mTempGlobalRect;
        r3 = r3[r1];
        r5 = r7.mHost;
        r5 = r5.getScrollY();
        r3 = r3 - r5;
        r8.offset(r2, r3);
    L_0x00fb:
        r8 = r7.mHost;
        r2 = r7.mTempVisibleRect;
        r8 = r8.getLocalVisibleRect(r2);
        if (r8 == 0) goto L_0x013a;
    L_0x0105:
        r8 = r7.mTempVisibleRect;
        r2 = r7.mTempGlobalRect;
        r2 = r2[r4];
        r3 = r7.mHost;
        r3 = r3.getScrollX();
        r2 = r2 - r3;
        r3 = r7.mTempGlobalRect;
        r3 = r3[r1];
        r4 = r7.mHost;
        r4 = r4.getScrollY();
        r3 = r3 - r4;
        r8.offset(r2, r3);
        r8 = r7.mTempScreenRect;
        r2 = r7.mTempVisibleRect;
        r8 = r8.intersect(r2);
        if (r8 == 0) goto L_0x013a;
    L_0x012a:
        r8 = r7.mTempScreenRect;
        r0.setBoundsInScreen(r8);
        r8 = r7.mTempScreenRect;
        r8 = r7.isVisibleToUser(r8);
        if (r8 == 0) goto L_0x013a;
    L_0x0137:
        r0.setVisibleToUser(r1);
    L_0x013a:
        return r0;
    L_0x013b:
        r8 = new java.lang.RuntimeException;
        r0 = "Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()";
        r8.<init>(r0);
        throw r8;
    L_0x0143:
        r8 = new java.lang.RuntimeException;
        r0 = "Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()";
        r8.<init>(r0);
        throw r8;
    L_0x014b:
        r8 = new java.lang.RuntimeException;
        r0 = "Callbacks must set parent bounds in populateNodeForVirtualViewId()";
        r8.<init>(r0);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.ExploreByTouchHelper.createNodeForChild(int):android.support.v4.view.accessibility.AccessibilityNodeInfoCompat");
    }

    private static int keyToDirection(int i) {
        switch (i) {
            case 19:
                return 33;
            case 21:
                return 17;
            case 22:
                return 66;
            default:
                return 130;
        }
    }

    protected abstract int getVirtualViewAt(float f, float f2);

    protected abstract void getVisibleVirtualViews(List<Integer> list);

    protected abstract boolean onPerformActionForVirtualView(int i, int i2, @Nullable Bundle bundle);

    protected void onPopulateEventForHost(@NonNull AccessibilityEvent accessibilityEvent) {
    }

    protected void onPopulateEventForVirtualView(int i, @NonNull AccessibilityEvent accessibilityEvent) {
    }

    protected void onPopulateNodeForHost(@NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    protected abstract void onPopulateNodeForVirtualView(int i, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    protected void onVirtualViewKeyboardFocusChanged(int i, boolean z) {
    }

    public ExploreByTouchHelper(@NonNull View view) {
        if (view != null) {
            this.mHost = view;
            this.mManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
            view.setFocusable(true);
            if (ViewCompat.getImportantForAccessibility(view) == 0) {
                ViewCompat.setImportantForAccessibility(view, 1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("View may not be null");
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new MyNodeProvider();
        }
        return this.mNodeProvider;
    }

    public final boolean dispatchHoverEvent(@NonNull MotionEvent motionEvent) {
        boolean z = false;
        if (this.mManager.isEnabled()) {
            if (this.mManager.isTouchExplorationEnabled()) {
                int action = motionEvent.getAction();
                if (action != 7) {
                    switch (action) {
                        case 9:
                            break;
                        case 10:
                            if (this.mHoveredVirtualViewId == -2147483648) {
                                return false;
                            }
                            updateHoveredVirtualView(Integer.MIN_VALUE);
                            return true;
                        default:
                            return false;
                    }
                }
                motionEvent = getVirtualViewAt(motionEvent.getX(), motionEvent.getY());
                updateHoveredVirtualView(motionEvent);
                if (motionEvent != -2147483648) {
                    z = true;
                }
                return z;
            }
        }
        return false;
    }

    public final boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
        int i = 0;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 61) {
            if (keyCode != 66) {
                switch (keyCode) {
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                        if (!keyEvent.hasNoModifiers()) {
                            return false;
                        }
                        keyCode = keyToDirection(keyCode);
                        keyEvent = keyEvent.getRepeatCount() + 1;
                        boolean z = false;
                        while (i < keyEvent && moveFocus(keyCode, null)) {
                            i++;
                            z = true;
                        }
                        return z;
                    case 23:
                        break;
                    default:
                        return false;
                }
            }
            if (!keyEvent.hasNoModifiers() || keyEvent.getRepeatCount() != null) {
                return false;
            }
            clickKeyboardFocusedVirtualView();
            return true;
        } else if (keyEvent.hasNoModifiers()) {
            return moveFocus(2, null);
        } else {
            if (keyEvent.hasModifiers(1) != null) {
                return moveFocus(1, null);
            }
            return false;
        }
    }

    public final void onFocusChanged(boolean z, int i, @Nullable Rect rect) {
        int i2 = this.mKeyboardFocusedVirtualViewId;
        if (i2 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i2);
        }
        if (z) {
            moveFocus(i, rect);
        }
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }

    private void getBoundsInParent(int i, Rect rect) {
        obtainAccessibilityNodeInfo(i).getBoundsInParent(rect);
    }

    private boolean moveFocus(int i, @Nullable Rect rect) {
        Object obj;
        SparseArrayCompat allNodes = getAllNodes();
        int i2 = this.mKeyboardFocusedVirtualViewId;
        int i3 = Integer.MIN_VALUE;
        if (i2 == Integer.MIN_VALUE) {
            obj = null;
        } else {
            obj = (AccessibilityNodeInfoCompat) allNodes.get(i2);
        }
        if (i == 17 || i == 33 || i == 66 || i == 130) {
            Rect rect2 = new Rect();
            i2 = this.mKeyboardFocusedVirtualViewId;
            if (i2 != Integer.MIN_VALUE) {
                getBoundsInParent(i2, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                guessPreviouslyFocusedRect(this.mHost, i, rect2);
            }
            i = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInAbsoluteDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, obj, rect2, i);
        } else {
            switch (i) {
                case 1:
                case 2:
                    i = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInRelativeDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, obj, i, ViewCompat.getLayoutDirection(this.mHost) == 1, false);
                    break;
                default:
                    throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
        }
        if (i != 0) {
            i3 = allNodes.keyAt(allNodes.indexOfValue(i));
        }
        return requestKeyboardFocusForVirtualView(i3);
    }

    private SparseArrayCompat<AccessibilityNodeInfoCompat> getAllNodes() {
        List arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat = new SparseArrayCompat();
        for (int i = 0; i < arrayList.size(); i++) {
            sparseArrayCompat.put(i, createNodeForChild(i));
        }
        return sparseArrayCompat;
    }

    private static Rect guessPreviouslyFocusedRect(@NonNull View view, int i, @NonNull Rect rect) {
        int width = view.getWidth();
        view = view.getHeight();
        if (i == 17) {
            rect.set(width, 0, width, view);
        } else if (i == 33) {
            rect.set(0, view, width, view);
        } else if (i == 66) {
            rect.set(-1, 0, -1, view);
        } else if (i == 130) {
            rect.set(0, -1, width, -1);
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return rect;
    }

    private boolean clickKeyboardFocusedVirtualView() {
        int i = this.mKeyboardFocusedVirtualViewId;
        return i != Integer.MIN_VALUE && onPerformActionForVirtualView(i, 16, null);
    }

    public final boolean sendEventForVirtualView(int i, int i2) {
        if (i != Integer.MIN_VALUE) {
            if (this.mManager.isEnabled()) {
                ViewParent parent = this.mHost.getParent();
                if (parent == null) {
                    return false;
                }
                return ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, createEvent(i, i2));
            }
        }
        return false;
    }

    public final void invalidateRoot() {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int i) {
        invalidateVirtualView(i, 0);
    }

    public final void invalidateVirtualView(int i, int i2) {
        if (i != Integer.MIN_VALUE && this.mManager.isEnabled()) {
            ViewParent parent = this.mHost.getParent();
            if (parent != null) {
                i = createEvent(i, 2048);
                AccessibilityEventCompat.setContentChangeTypes(i, i2);
                ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, i);
            }
        }
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return getAccessibilityFocusedVirtualViewId();
    }

    private void updateHoveredVirtualView(int i) {
        int i2 = this.mHoveredVirtualViewId;
        if (i2 != i) {
            this.mHoveredVirtualViewId = i;
            sendEventForVirtualView(i, 128);
            sendEventForVirtualView(i2, 256);
        }
    }

    private AccessibilityEvent createEvent(int i, int i2) {
        if (i != -1) {
            return createEventForChild(i, i2);
        }
        return createEventForHost(i2);
    }

    private AccessibilityEvent createEventForHost(int i) {
        i = AccessibilityEvent.obtain(i);
        this.mHost.onInitializeAccessibilityEvent(i);
        return i;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        onPopulateEventForHost(accessibilityEvent);
    }

    private AccessibilityEvent createEventForChild(int i, int i2) {
        i2 = AccessibilityEvent.obtain(i2);
        AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo = obtainAccessibilityNodeInfo(i);
        i2.getText().add(obtainAccessibilityNodeInfo.getText());
        i2.setContentDescription(obtainAccessibilityNodeInfo.getContentDescription());
        i2.setScrollable(obtainAccessibilityNodeInfo.isScrollable());
        i2.setPassword(obtainAccessibilityNodeInfo.isPassword());
        i2.setEnabled(obtainAccessibilityNodeInfo.isEnabled());
        i2.setChecked(obtainAccessibilityNodeInfo.isChecked());
        onPopulateEventForVirtualView(i, i2);
        if (i2.getText().isEmpty()) {
            if (i2.getContentDescription() == null) {
                throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
            }
        }
        i2.setClassName(obtainAccessibilityNodeInfo.getClassName());
        AccessibilityRecordCompat.setSource(i2, this.mHost, i);
        i2.setPackageName(this.mHost.getContext().getPackageName());
        return i2;
    }

    @NonNull
    AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int i) {
        if (i == -1) {
            return createNodeForHost();
        }
        return createNodeForChild(i);
    }

    @NonNull
    private AccessibilityNodeInfoCompat createNodeForHost() {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(this.mHost);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, obtain);
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        if (obtain.getChildCount() > 0) {
            if (arrayList.size() > 0) {
                throw new RuntimeException("Views cannot have both real and virtual children");
            }
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            obtain.addChild(this.mHost, ((Integer) arrayList.get(i)).intValue());
        }
        return obtain;
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        onPopulateNodeForHost(accessibilityNodeInfoCompat);
    }

    boolean performAction(int i, int i2, Bundle bundle) {
        if (i != -1) {
            return performActionForChild(i, i2, bundle);
        }
        return performActionForHost(i2, bundle);
    }

    private boolean performActionForHost(int i, Bundle bundle) {
        return ViewCompat.performAccessibilityAction(this.mHost, i, bundle);
    }

    private boolean performActionForChild(int i, int i2, Bundle bundle) {
        if (i2 == 64) {
            return requestAccessibilityFocus(i);
        }
        if (i2 == 128) {
            return clearAccessibilityFocus(i);
        }
        switch (i2) {
            case 1:
                return requestKeyboardFocusForVirtualView(i);
            case 2:
                return clearKeyboardFocusForVirtualView(i);
            default:
                return onPerformActionForVirtualView(i, i2, bundle);
        }
    }

    private boolean isVisibleToUser(Rect rect) {
        boolean z = false;
        if (rect != null) {
            if (rect.isEmpty() == null) {
                if (this.mHost.getWindowVisibility() != null) {
                    return false;
                }
                rect = this.mHost.getParent();
                while (rect instanceof View) {
                    View view = (View) rect;
                    if (view.getAlpha() > 0.0f) {
                        if (view.getVisibility() == 0) {
                            rect = view.getParent();
                        }
                    }
                    return false;
                }
                if (rect != null) {
                    z = true;
                }
                return z;
            }
        }
        return false;
    }

    private boolean requestAccessibilityFocus(int i) {
        if (this.mManager.isEnabled()) {
            if (this.mManager.isTouchExplorationEnabled()) {
                int i2 = this.mAccessibilityFocusedVirtualViewId;
                if (i2 == i) {
                    return false;
                }
                if (i2 != Integer.MIN_VALUE) {
                    clearAccessibilityFocus(i2);
                }
                this.mAccessibilityFocusedVirtualViewId = i;
                this.mHost.invalidate();
                sendEventForVirtualView(i, 32768);
                return true;
            }
        }
        return false;
    }

    private boolean clearAccessibilityFocus(int i) {
        if (this.mAccessibilityFocusedVirtualViewId != i) {
            return false;
        }
        this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mHost.invalidate();
        sendEventForVirtualView(i, 65536);
        return true;
    }

    public final boolean requestKeyboardFocusForVirtualView(int i) {
        if (!this.mHost.isFocused() && !this.mHost.requestFocus()) {
            return false;
        }
        int i2 = this.mKeyboardFocusedVirtualViewId;
        if (i2 == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i2);
        }
        this.mKeyboardFocusedVirtualViewId = i;
        onVirtualViewKeyboardFocusChanged(i, true);
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final boolean clearKeyboardFocusForVirtualView(int i) {
        if (this.mKeyboardFocusedVirtualViewId != i) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        onVirtualViewKeyboardFocusChanged(i, false);
        sendEventForVirtualView(i, 8);
        return true;
    }
}
