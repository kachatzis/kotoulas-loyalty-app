package gr.loginet.loginetloyalty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ModelGift implements Serializable {
    @SerializedName("credits_cost")
    @Expose
    private int credits_cost;
    @SerializedName("gift_id")
    @Expose
    private int gift_id;
    @SerializedName("portal_big_description")
    @Expose
    private String portal_big_description;
    @SerializedName("portal_photo")
    @Expose
    private String portal_photo;
    @SerializedName("portal_small_description")
    @Expose
    private String portal_small_description;
    @SerializedName("portal_title")
    @Expose
    private String portal_title;

    public int getGift_id() {
        return this.gift_id;
    }

    public void setGift_id(int i) {
        this.gift_id = i;
    }

    public String getPortal_title() {
        return this.portal_title;
    }

    public void setPortal_title(String str) {
        this.portal_title = str;
    }

    public int getCredits_cost() {
        return this.credits_cost;
    }

    public void setCredits_cost(int i) {
        this.credits_cost = i;
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
