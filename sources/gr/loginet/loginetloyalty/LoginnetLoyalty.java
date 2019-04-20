package gr.loginet.loginetloyalty;

import android.app.Application;

public class LoginnetLoyalty extends Application {
    private String apikey;
    private String base_url;
    private String token;

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getApiKey() {
        return this.apikey;
    }

    public void setApiKey(String str) {
        this.apikey = str;
    }

    public String getBaseUrl() {
        return this.base_url;
    }

    public void setBaseUrl(String str) {
        this.base_url = str;
    }
}
