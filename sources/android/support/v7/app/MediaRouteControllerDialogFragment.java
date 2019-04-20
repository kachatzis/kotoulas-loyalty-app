package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.DialogFragment;
import android.support.v7.media.MediaRouteSelector;
import android.util.Log;

public class MediaRouteControllerDialogFragment extends DialogFragment {
    private static final String ARGUMENT_SELECTOR = "selector";
    private static final boolean USE_SUPPORT_DYNAMIC_GROUP = Log.isLoggable("UseSupportDynamicGroup", 3);
    private Dialog mDialog;
    private MediaRouteSelector mSelector;

    public MediaRouteControllerDialogFragment() {
        setCancelable(true);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public MediaRouteSelector getRouteSelector() {
        ensureRouteSelector();
        return this.mSelector;
    }

    private void ensureRouteSelector() {
        if (this.mSelector == null) {
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.mSelector = MediaRouteSelector.fromBundle(arguments.getBundle(ARGUMENT_SELECTOR));
            }
            if (this.mSelector == null) {
                this.mSelector = MediaRouteSelector.EMPTY;
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setRouteSelector(MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector != null) {
            ensureRouteSelector();
            if (!this.mSelector.equals(mediaRouteSelector)) {
                this.mSelector = mediaRouteSelector;
                Bundle arguments = getArguments();
                if (arguments == null) {
                    arguments = new Bundle();
                }
                arguments.putBundle(ARGUMENT_SELECTOR, mediaRouteSelector.asBundle());
                setArguments(arguments);
                Dialog dialog = this.mDialog;
                if (dialog != null && USE_SUPPORT_DYNAMIC_GROUP) {
                    ((MediaRouteCastDialog) dialog).setRouteSelector(mediaRouteSelector);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("selector must not be null");
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public MediaRouteCastDialog onCreateCastDialog(Context context) {
        return new MediaRouteCastDialog(context);
    }

    public MediaRouteControllerDialog onCreateControllerDialog(Context context, Bundle bundle) {
        return new MediaRouteControllerDialog(context);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (USE_SUPPORT_DYNAMIC_GROUP) {
            this.mDialog = onCreateCastDialog(getContext());
            ((MediaRouteCastDialog) this.mDialog).setRouteSelector(this.mSelector);
        } else {
            this.mDialog = onCreateControllerDialog(getContext(), bundle);
        }
        return this.mDialog;
    }

    public void onStop() {
        super.onStop();
        Dialog dialog = this.mDialog;
        if (dialog != null && !USE_SUPPORT_DYNAMIC_GROUP) {
            ((MediaRouteControllerDialog) dialog).clearGroupListAnimation(false);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        configuration = this.mDialog;
        if (configuration == null) {
            return;
        }
        if (USE_SUPPORT_DYNAMIC_GROUP) {
            ((MediaRouteCastDialog) configuration).updateLayout();
        } else {
            ((MediaRouteControllerDialog) configuration).updateLayout();
        }
    }
}
