package android.support.v7.app;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.mediarouter.C0303R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

class MediaRouteExpandCollapseButton extends ImageButton {
    final AnimationDrawable mCollapseAnimationDrawable;
    final String mCollapseGroupDescription;
    final AnimationDrawable mExpandAnimationDrawable;
    final String mExpandGroupDescription;
    boolean mIsGroupExpanded;
    OnClickListener mListener;

    /* renamed from: android.support.v7.app.MediaRouteExpandCollapseButton$1 */
    class C02891 implements OnClickListener {
        C02891() {
        }

        public void onClick(View view) {
            MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
            mediaRouteExpandCollapseButton.mIsGroupExpanded ^= 1;
            if (MediaRouteExpandCollapseButton.this.mIsGroupExpanded) {
                mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
                mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.mExpandAnimationDrawable);
                MediaRouteExpandCollapseButton.this.mExpandAnimationDrawable.start();
                mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
                mediaRouteExpandCollapseButton.setContentDescription(mediaRouteExpandCollapseButton.mCollapseGroupDescription);
            } else {
                mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
                mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.mCollapseAnimationDrawable);
                MediaRouteExpandCollapseButton.this.mCollapseAnimationDrawable.start();
                mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
                mediaRouteExpandCollapseButton.setContentDescription(mediaRouteExpandCollapseButton.mExpandGroupDescription);
            }
            if (MediaRouteExpandCollapseButton.this.mListener != null) {
                MediaRouteExpandCollapseButton.this.mListener.onClick(view);
            }
        }
    }

    public MediaRouteExpandCollapseButton(Context context) {
        this(context, null);
    }

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mExpandAnimationDrawable = (AnimationDrawable) ContextCompat.getDrawable(context, C0303R.drawable.mr_group_expand);
        this.mCollapseAnimationDrawable = (AnimationDrawable) ContextCompat.getDrawable(context, C0303R.drawable.mr_group_collapse);
        attributeSet = new PorterDuffColorFilter(MediaRouterThemeHelper.getControllerColor(context, i), Mode.SRC_IN);
        this.mExpandAnimationDrawable.setColorFilter(attributeSet);
        this.mCollapseAnimationDrawable.setColorFilter(attributeSet);
        this.mExpandGroupDescription = context.getString(C0303R.string.mr_controller_expand_group);
        this.mCollapseGroupDescription = context.getString(C0303R.string.mr_controller_collapse_group);
        setImageDrawable(this.mExpandAnimationDrawable.getFrame(null));
        setContentDescription(this.mExpandGroupDescription);
        super.setOnClickListener(new C02891());
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }
}
