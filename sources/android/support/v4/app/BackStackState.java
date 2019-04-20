package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

/* compiled from: BackStackRecord */
final class BackStackState implements Parcelable {
    public static final Creator<BackStackState> CREATOR = new C01281();
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int mIndex;
    final String mName;
    final int[] mOps;
    final boolean mReorderingAllowed;
    final ArrayList<String> mSharedElementSourceNames;
    final ArrayList<String> mSharedElementTargetNames;
    final int mTransition;
    final int mTransitionStyle;

    /* compiled from: BackStackRecord */
    /* renamed from: android.support.v4.app.BackStackState$1 */
    static class C01281 implements Creator<BackStackState> {
        C01281() {
        }

        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        public BackStackState[] newArray(int i) {
            return new BackStackState[i];
        }
    }

    public BackStackState(android.support.v4.app.BackStackRecord r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:13:0x0090 in {6, 7, 8, 10, 12} preds:[]
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
        r7.<init>();
        r0 = r8.mOps;
        r0 = r0.size();
        r1 = r0 * 6;
        r1 = new int[r1];
        r7.mOps = r1;
        r1 = r8.mAddToBackStack;
        if (r1 == 0) goto L_0x0088;
    L_0x0013:
        r1 = 0;
        r2 = 0;
    L_0x0015:
        if (r1 >= r0) goto L_0x005b;
    L_0x0017:
        r3 = r8.mOps;
        r3 = r3.get(r1);
        r3 = (android.support.v4.app.BackStackRecord.Op) r3;
        r4 = r7.mOps;
        r5 = r2 + 1;
        r6 = r3.cmd;
        r4[r2] = r6;
        r2 = r7.mOps;
        r4 = r5 + 1;
        r6 = r3.fragment;
        if (r6 == 0) goto L_0x0034;
    L_0x002f:
        r6 = r3.fragment;
        r6 = r6.mIndex;
        goto L_0x0035;
    L_0x0034:
        r6 = -1;
    L_0x0035:
        r2[r5] = r6;
        r2 = r7.mOps;
        r5 = r4 + 1;
        r6 = r3.enterAnim;
        r2[r4] = r6;
        r2 = r7.mOps;
        r4 = r5 + 1;
        r6 = r3.exitAnim;
        r2[r5] = r6;
        r2 = r7.mOps;
        r5 = r4 + 1;
        r6 = r3.popEnterAnim;
        r2[r4] = r6;
        r2 = r7.mOps;
        r4 = r5 + 1;
        r3 = r3.popExitAnim;
        r2[r5] = r3;
        r1 = r1 + 1;
        r2 = r4;
        goto L_0x0015;
    L_0x005b:
        r0 = r8.mTransition;
        r7.mTransition = r0;
        r0 = r8.mTransitionStyle;
        r7.mTransitionStyle = r0;
        r0 = r8.mName;
        r7.mName = r0;
        r0 = r8.mIndex;
        r7.mIndex = r0;
        r0 = r8.mBreadCrumbTitleRes;
        r7.mBreadCrumbTitleRes = r0;
        r0 = r8.mBreadCrumbTitleText;
        r7.mBreadCrumbTitleText = r0;
        r0 = r8.mBreadCrumbShortTitleRes;
        r7.mBreadCrumbShortTitleRes = r0;
        r0 = r8.mBreadCrumbShortTitleText;
        r7.mBreadCrumbShortTitleText = r0;
        r0 = r8.mSharedElementSourceNames;
        r7.mSharedElementSourceNames = r0;
        r0 = r8.mSharedElementTargetNames;
        r7.mSharedElementTargetNames = r0;
        r8 = r8.mReorderingAllowed;
        r7.mReorderingAllowed = r8;
        return;
    L_0x0088:
        r8 = new java.lang.IllegalStateException;
        r0 = "Not on back stack";
        r8.<init>(r0);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.BackStackState.<init>(android.support.v4.app.BackStackRecord):void");
    }

    public int describeContents() {
        return 0;
    }

    public BackStackState(Parcel parcel) {
        this.mOps = parcel.createIntArray();
        this.mTransition = parcel.readInt();
        this.mTransitionStyle = parcel.readInt();
        this.mName = parcel.readString();
        this.mIndex = parcel.readInt();
        this.mBreadCrumbTitleRes = parcel.readInt();
        this.mBreadCrumbTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mBreadCrumbShortTitleRes = parcel.readInt();
        this.mBreadCrumbShortTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mSharedElementSourceNames = parcel.createStringArrayList();
        this.mSharedElementTargetNames = parcel.createStringArrayList();
        this.mReorderingAllowed = parcel.readInt() != null ? true : null;
    }

    public BackStackRecord instantiate(FragmentManagerImpl fragmentManagerImpl) {
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManagerImpl);
        int i = 0;
        int i2 = 0;
        while (i < this.mOps.length) {
            Op op = new Op();
            int i3 = i + 1;
            op.cmd = this.mOps[i];
            if (FragmentManagerImpl.DEBUG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Instantiate ");
                stringBuilder.append(backStackRecord);
                stringBuilder.append(" op #");
                stringBuilder.append(i2);
                stringBuilder.append(" base fragment #");
                stringBuilder.append(this.mOps[i3]);
                Log.v("FragmentManager", stringBuilder.toString());
            }
            int i4 = i3 + 1;
            i = this.mOps[i3];
            if (i >= 0) {
                op.fragment = (Fragment) fragmentManagerImpl.mActive.get(i);
            } else {
                op.fragment = null;
            }
            int[] iArr = this.mOps;
            i3 = i4 + 1;
            op.enterAnim = iArr[i4];
            i4 = i3 + 1;
            op.exitAnim = iArr[i3];
            i3 = i4 + 1;
            op.popEnterAnim = iArr[i4];
            i4 = i3 + 1;
            op.popExitAnim = iArr[i3];
            backStackRecord.mEnterAnim = op.enterAnim;
            backStackRecord.mExitAnim = op.exitAnim;
            backStackRecord.mPopEnterAnim = op.popEnterAnim;
            backStackRecord.mPopExitAnim = op.popExitAnim;
            backStackRecord.addOp(op);
            i2++;
            i = i4;
        }
        backStackRecord.mTransition = this.mTransition;
        backStackRecord.mTransitionStyle = this.mTransitionStyle;
        backStackRecord.mName = this.mName;
        backStackRecord.mIndex = this.mIndex;
        backStackRecord.mAddToBackStack = true;
        backStackRecord.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
        backStackRecord.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
        backStackRecord.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
        backStackRecord.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
        backStackRecord.mSharedElementSourceNames = this.mSharedElementSourceNames;
        backStackRecord.mSharedElementTargetNames = this.mSharedElementTargetNames;
        backStackRecord.mReorderingAllowed = this.mReorderingAllowed;
        backStackRecord.bumpBackStackNesting(1);
        return backStackRecord;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.mOps);
        parcel.writeInt(this.mTransition);
        parcel.writeInt(this.mTransitionStyle);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, parcel, 0);
        parcel.writeStringList(this.mSharedElementSourceNames);
        parcel.writeStringList(this.mSharedElementTargetNames);
        parcel.writeInt(this.mReorderingAllowed);
    }
}
