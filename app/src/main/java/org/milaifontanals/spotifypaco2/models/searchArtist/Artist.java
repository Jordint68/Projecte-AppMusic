
package org.milaifontanals.spotifypaco2.models.searchArtist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Artist {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private List<ImageArtist> image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageArtist> getImage() {
        return image;
    }

    public void setImage(List<ImageArtist> image) {
        this.image = image;
    }

}
