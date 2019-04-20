package gr.loginet.loginetloyalty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ModelLoginResponse implements Serializable {
    @SerializedName("session_token")
    @Expose
    private String session_token;

    public String getToken() {
        return this.session_token;
    }

    public void setToken(String str) {
        this.session_token = str;
    }
}
