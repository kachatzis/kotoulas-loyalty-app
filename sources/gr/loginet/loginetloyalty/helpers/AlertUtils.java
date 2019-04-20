package gr.loginet.loginetloyalty.helpers;

import android.app.AlertDialog.Builder;
import android.content.Context;

public class AlertUtils {
    public static void showOKDialog(Context context, String str, String str2) {
        Builder builder = new Builder(context);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.setPositiveButton(17039370, null);
        builder.show();
    }
}
