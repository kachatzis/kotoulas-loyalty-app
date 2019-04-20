package gr.loginet.loginetloyalty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ModelProduct implements Serializable {
    @SerializedName("good_id")
    @Expose
    private int good_id;
    @SerializedName("portal_big_description")
    @Expose
    private String portal_big_description;
    @SerializedName("portal_photo")
    @Expose
    private String portal_photo;
    @SerializedName("portal_price")
    @Expose
    private String portal_price;
    @SerializedName("portal_small_description")
    @Expose
    private String portal_small_description;
    @SerializedName("portal_title")
    @Expose
    private String portal_title;

    public int getGood_id() {
        return this.good_id;
    }

    public void setGood_id(int i) {
        this.good_id = i;
    }

    public String getPortal_title() {
        return this.portal_title;
    }

    public void setPortal_title(String str) {
        this.portal_title = str;
    }

    public String getPortal_price() {
        return this.portal_price;
    }

    public void setPortal_price(String str) {
        this.portal_price = str;
    }

    public String getPortal_small_description() {
        return this.portal_small_description;
    }

    public void setPortal_small_description(String str) {
        this.portal_small_description = str;
    }

    public String getPortal_big_description() {
        return this.portal_big_description;
    }

    public void setPortal_big_description(String str) {
        this.portal_big_description = str;
    }

    public String getPortal_photo() {
        return this.portal_photo;
    }

    public void setPortal_photo(String str) {
        this.portal_photo = str;
    }
}
