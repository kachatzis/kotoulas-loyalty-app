package gr.loginet.loginetloyalty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ModelCustomer implements Serializable {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("card")
    @Expose
    private int card;
    @SerializedName("card_level_id")
    @Expose
    private int card_level_id;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("credit")
    @Expose
    private int credit;
    @SerializedName("customer_id")
    @Expose
    private int customer_id;
    @SerializedName("email1")
    @Expose
    private String email1;
    @SerializedName("email2")
    @Expose
    private String email2;
    @SerializedName("first_name")
    @Expose
    private String first_name;
    @SerializedName("is_individual")
    @Expose
    private boolean is_individual;
    @SerializedName("is_professional")
    @Expose
    private boolean is_professional;
    @SerializedName("last_name")
    @Expose
    private String last_name;
    @SerializedName("phone_home")
    @Expose
    private String phone_home;
    @SerializedName("phone_mobile")
    @Expose
    private String phone_mobile;
    @SerializedName("postal_code")
    @Expose
    private int postal_code;
    @SerializedName("previous_credit")
    @Expose
    private int previous_credit;
    @SerializedName("registration_date")
    @Expose
    private String registration_date;
    @SerializedName("registration_store_id")
    @Expose
    private int registration_store_id;
    @SerializedName("send_email")
    @Expose
    private boolean send_email;
    @SerializedName("send_sms")
    @Expose
    private boolean send_sms;

    public int getCustomer_id() {
        return this.customer_id;
    }

    public void setCustomer_id(int i) {
        this.customer_id = i;
    }

    public int getRegistration_store_id() {
        return this.registration_store_id;
    }

    public void setRegistration_store_id(int i) {
        this.registration_store_id = i;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String str) {
        this.first_name = str;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String str) {
        this.last_name = str;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public String getEmail1() {
        return this.email1;
    }

    public void setEmail1(String str) {
        this.email1 = str;
    }

    public String getEmail2() {
        return this.email2;
    }

    public void setEmail2(String str) {
        this.email2 = str;
    }

    public String getPhone_home() {
        return this.phone_home;
    }

    public void setPhone_home(String str) {
        this.phone_home = str;
    }

    public String getPhone_mobile() {
        return this.phone_mobile;
    }

    public void setPhone_mobile(String str) {
        this.phone_mobile = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public int getCard() {
        return this.card;
    }

    public void setCard(int i) {
        this.card = i;
    }

    public String getRegistration_date() {
        return this.registration_date;
    }

    public void setRegistration_date(String str) {
        this.registration_date = str;
    }

    public boolean isIs_individual() {
        return this.is_individual;
    }

    public void setIs_individual(boolean z) {
        this.is_individual = z;
    }

    public boolean isSend_sms() {
        return this.send_sms;
    }

    public void setSend_sms(boolean z) {
        this.send_sms = z;
    }

    public boolean isSend_email() {
        return this.send_email;
    }

    public void setSend_email(boolean z) {
        this.send_email = z;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public int getPostal_code() {
        return this.postal_code;
    }

    public void setPostal_code(int i) {
        this.postal_code = i;
    }

    public int getCredit() {
        return this.credit;
    }

    public void setCredit(int i) {
        this.credit = i;
    }

    public int getCard_level_id() {
        return this.card_level_id;
    }

    public void setCard_level_id(int i) {
        this.card_level_id = i;
    }

    public int getPrevious_credit() {
        return this.previous_credit;
    }

    public void setPrevious_credit(int i) {
        this.previous_credit = i;
    }

    public boolean isIs_professional() {
        return this.is_professional;
    }

    public void setIs_professional(boolean z) {
        this.is_professional = z;
    }
}
