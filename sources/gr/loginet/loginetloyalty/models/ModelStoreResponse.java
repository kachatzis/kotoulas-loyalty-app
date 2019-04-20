package gr.loginet.loginetloyalty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class ModelStoreResponse implements Serializable {
    @SerializedName("resource")
    @Expose
    private List<ModelStore> results = null;

    public List<ModelStore> getResults() {
        return this.results;
    }

    public void setResults(List<ModelStore> list) {
        this.results = list;
    }
}
