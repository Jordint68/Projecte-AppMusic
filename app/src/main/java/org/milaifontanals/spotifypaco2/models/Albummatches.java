
package org.milaifontanals.spotifypaco2.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Albummatches {

    @SerializedName("album")
    @Expose
    private List<AlbumJSON> album;

    public List<AlbumJSON> getAlbum() {
        return album;
    }

    public void setAlbum(List<AlbumJSON> album) {
        this.album = album;
    }

}
