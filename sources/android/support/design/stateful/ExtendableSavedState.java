package android.support.design.stateful;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.AbsSavedState;

public class ExtendableSavedState extends AbsSavedState {
    public static final Creator<ExtendableSavedState> CREATOR = new C00461();
    public final SimpleArrayMap<String, Bundle> extendableStates;

    /* renamed from: android.support.design.stateful.ExtendableSavedState$1 */
    static class C00461 implements ClassLoaderCreator<ExtendableSavedState> {
        C00461() {
        }

        public ExtendableSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new ExtendableSavedState(parcel, classLoader);
        }

        public ExtendableSavedState createFromParcel(Parcel parcel) {
            return new ExtendableSavedState(parcel, null);
        }

        public ExtendableSavedState[] newArray(int i) {
            return new ExtendableSavedState[i];
        }
    }

    public ExtendableSavedState(Parcelable parcelable) {
        super(parcelable);
        this.extendableStates = new SimpleArrayMap();
    }

    private ExtendableSavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int readInt = parcel.readInt();
        String[] strArr = new String[readInt];
        parcel.readStringArray(strArr);
        Bundle[] bundleArr = new Bundle[readInt];
        parcel.readTypedArray(bundleArr, Bundle.CREATOR);
        this.extendableStates = new SimpleArrayMap(readInt);
        for (parcel = null; parcel < readInt; parcel++) {
            this.extendableStates.put(strArr[parcel], bundleArr[parcel]);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        i = this.extendableStates.size();
        parcel.writeInt(i);
        String[] strArr = new String[i];
        Parcelable[] parcelableArr = new Bundle[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = (String) this.extendableStates.keyAt(i2);
            parcelableArr[i2] = (Bundle) this.extendableStates.valueAt(i2);
        }
        parcel.writeStringArray(strArr);
        parcel.writeTypedArray(parcelableArr, 0);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ExtendableSavedState{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" states=");
        stringBuilder.append(this.extendableStates);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
