
package org.milaifontanals.spotifypaco2.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class AlbumJson {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("image")
    @Expose
    private List<ImageJson> image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<ImageJson> getImage() {
        return image;
    }

    public void setImage(List<ImageJson> image) {
        this.image = image;
    }

    public AlbumJson() {
    }

    public AlbumJson(String name, String artist, List<ImageJson> image) {
        this.name = name;
        this.artist = artist;
        this.image = image;
    }
}
