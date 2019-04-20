package android.support.design.card;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.design.C0026R;
import android.support.design.internal.ThemeEnforcement;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

public class MaterialCardView extends CardView {
    private final MaterialCardViewHelper cardViewHelper;

    public MaterialCardView(Context context) {
        this(context, null);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0026R.attr.materialCardViewStyle);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        context = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C0026R.styleable.MaterialCardView, i, C0026R.style.Widget_MaterialComponents_CardView, new int[0]);
        this.cardViewHelper = new MaterialCardViewHelper(this);
        this.cardViewHelper.loadFromAttributes(context);
        context.recycle();
    }

    public void setStrokeColor(@ColorInt int i) {
        this.cardViewHelper.setStrokeColor(i);
    }

    @ColorInt
    public int getStrokeColor() {
        return this.cardViewHelper.getStrokeColor();
    }

    public void setStrokeWidth(@Dimension int i) {
        this.cardViewHelper.setStrokeWidth(i);
    }

    @Dimension
    public int getStrokeWidth() {
        return this.cardViewHelper.getStrokeWidth();
    }

    public void setRadius(float f) {
        super.setRadius(f);
        this.cardViewHelper.updateForeground();
    }
}
