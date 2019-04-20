package android.support.design.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.C0026R;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;

public class TabItem extends View {
    public final int customLayout;
    public final Drawable icon;
    public final CharSequence text;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        context = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0026R.styleable.TabItem);
        this.text = context.getText(C0026R.styleable.TabItem_android_text);
        this.icon = context.getDrawable(C0026R.styleable.TabItem_android_icon);
        this.customLayout = context.getResourceId(C0026R.styleable.TabItem_android_layout, 0);
        context.recycle();
    }
}
