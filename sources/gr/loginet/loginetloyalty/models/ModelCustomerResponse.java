package gr.loginet.loginetloyalty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class ModelCustomerResponse implements Serializable {
    @SerializedName("resource")
    @Expose
    private List<ModelCustomer> results = null;

    public List<ModelCustomer> getResults() {
        return this.results;
    }

    public void setResults(List<ModelCustomer> list) {
        this.results = list;
    }
}
