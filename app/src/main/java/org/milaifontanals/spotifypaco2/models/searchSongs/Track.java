
package org.milaifontanals.spotifypaco2.models.searchSongs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Track {

    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
