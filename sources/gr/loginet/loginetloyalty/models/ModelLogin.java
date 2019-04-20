package gr.loginet.loginetloyalty.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ModelLogin implements Serializable {
    @SerializedName("password")
    public String password;
    @SerializedName("username")
    public String username;
}
