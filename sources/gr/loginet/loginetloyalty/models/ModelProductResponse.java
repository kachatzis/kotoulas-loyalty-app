package gr.loginet.loginetloyalty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class ModelProductResponse implements Serializable {
    @SerializedName("resource")
    @Expose
    private List<ModelProduct> results = null;

    public List<ModelProduct> getResults() {
        return this.results;
    }

    public void setResults(List<ModelProduct> list) {
        this.results = list;
    }
}
