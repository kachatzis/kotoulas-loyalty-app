package gr.loginet.loginetloyalty.helpers;

import android.content.Context;
import retrofit2.Response;

public class ErrorUtils {
    public void error_statuscode(Context context, Response<?> response) {
        try {
            response.errorBody().string();
        } catch (Context context2) {
            context2.printStackTrace();
        }
        context2 = response.code();
        if (context2 != 400 && context2 != 404) {
        }
    }
}
