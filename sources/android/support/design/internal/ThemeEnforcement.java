package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StyleRes;
import android.support.annotation.StyleableRes;
import android.support.design.C0026R;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class ThemeEnforcement {
    private static final int[] APPCOMPAT_CHECK_ATTRS = new int[]{C0026R.attr.colorPrimary};
    private static final String APPCOMPAT_THEME_NAME = "Theme.AppCompat";
    private static final int[] MATERIAL_CHECK_ATTRS = new int[]{C0026R.attr.colorSecondary};
    private static final String MATERIAL_THEME_NAME = "Theme.MaterialComponents";

    private ThemeEnforcement() {
    }

    public static TypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, @StyleableRes int[] iArr, @AttrRes int i, @StyleRes int i2, @StyleableRes int... iArr2) {
        checkCompatibleTheme(context, attributeSet, i, i2);
        checkTextAppearance(context, attributeSet, iArr, i, i2, iArr2);
        return context.obtainStyledAttributes(attributeSet, iArr, i, i2);
    }

    public static TintTypedArray obtainTintedStyledAttributes(Context context, AttributeSet attributeSet, @StyleableRes int[] iArr, @AttrRes int i, @StyleRes int i2, @StyleableRes int... iArr2) {
        checkCompatibleTheme(context, attributeSet, i, i2);
        checkTextAppearance(context, attributeSet, iArr, i, i2, iArr2);
        return TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i, i2);
    }

    private static void checkCompatibleTheme(Context context, AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        attributeSet = context.obtainStyledAttributes(attributeSet, C0026R.styleable.ThemeEnforcement, i, i2);
        i = attributeSet.getBoolean(C0026R.styleable.ThemeEnforcement_enforceMaterialTheme, 0);
        attributeSet.recycle();
        if (i != 0) {
            checkMaterialTheme(context);
        }
        checkAppCompatTheme(context);
    }

    private static void checkTextAppearance(Context context, AttributeSet attributeSet, @StyleableRes int[] iArr, @AttrRes int i, @StyleRes int i2, @StyleableRes int... iArr2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0026R.styleable.ThemeEnforcement, i, i2);
        if (obtainStyledAttributes.getBoolean(C0026R.styleable.ThemeEnforcement_enforceTextAppearance, false)) {
            if (iArr2 != null) {
                if (iArr2.length != 0) {
                    context = isCustomTextAppearanceValid(context, attributeSet, iArr, i, i2, iArr2);
                    obtainStyledAttributes.recycle();
                    if (context != null) {
                        throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
                    }
                    return;
                }
            }
            context = obtainStyledAttributes.getResourceId(C0026R.styleable.ThemeEnforcement_android_textAppearance, -1) != -1 ? true : null;
            obtainStyledAttributes.recycle();
            if (context != null) {
                throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
            }
            return;
        }
        obtainStyledAttributes.recycle();
    }

    private static boolean isCustomTextAppearanceValid(Context context, AttributeSet attributeSet, @StyleableRes int[] iArr, @AttrRes int i, @StyleRes int i2, @StyleableRes int... iArr2) {
        context = context.obtainStyledAttributes(attributeSet, iArr, i, i2);
        for (int i22 : iArr2) {
            if (context.getResourceId(i22, -1) == -1) {
                context.recycle();
                return null;
            }
        }
        context.recycle();
        return true;
    }

    public static void checkAppCompatTheme(Context context) {
        checkTheme(context, APPCOMPAT_CHECK_ATTRS, APPCOMPAT_THEME_NAME);
    }

    public static void checkMaterialTheme(Context context) {
        checkTheme(context, MATERIAL_CHECK_ATTRS, MATERIAL_THEME_NAME);
    }

    public static boolean isAppCompatTheme(Context context) {
        return isTheme(context, APPCOMPAT_CHECK_ATTRS);
    }

    public static boolean isMaterialTheme(Context context) {
        return isTheme(context, MATERIAL_CHECK_ATTRS);
    }

    private static boolean isTheme(Context context, int[] iArr) {
        context = context.obtainStyledAttributes(iArr);
        iArr = context.hasValue(null);
        context.recycle();
        return iArr;
    }

    private static void checkTheme(Context context, int[] iArr, String str) {
        if (isTheme(context, iArr) == null) {
            iArr = new StringBuilder();
            iArr.append("The style on this component requires your app theme to be ");
            iArr.append(str);
            iArr.append(" (or a descendant).");
            throw new IllegalArgumentException(iArr.toString());
        }
    }
}
