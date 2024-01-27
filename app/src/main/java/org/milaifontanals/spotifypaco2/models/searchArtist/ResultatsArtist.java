
package org.milaifontanals.spotifypaco2.models.searchArtist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class ResultatsArtist {

    @SerializedName("results")
    @Expose
    private ResultsArtist results;

    public ResultsArtist getResults() {
        return results;
    }

    public void setResults(ResultsArtist results) {
        this.results = results;
    }

}
