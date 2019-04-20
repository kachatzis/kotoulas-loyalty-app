package gr.loginet.loginetloyalty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class ModelGiftResponse implements Serializable {
    @SerializedName("resource")
    @Expose
    private List<ModelGift> results = null;

    public List<ModelGift> getResults() {
        return this.results;
    }

    public void setResults(List<ModelGift> list) {
        this.results = list;
    }
}
