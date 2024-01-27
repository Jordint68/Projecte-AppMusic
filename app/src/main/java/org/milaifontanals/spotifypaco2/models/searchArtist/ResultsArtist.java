
package org.milaifontanals.spotifypaco2.models.searchArtist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class ResultsArtist {
    @SerializedName("artistmatches")
    @Expose
    private Artistmatches artistmatches;

    public Artistmatches getArtistmatches() {
        return artistmatches;
    }

    public void setArtistmatches(Artistmatches artistmatches) {
        this.artistmatches = artistmatches;
    }

}
