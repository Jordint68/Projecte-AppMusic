
package org.milaifontanals.spotifypaco2.models.searchSongs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Example {

    @SerializedName("album")
    @Expose
    private AlbumJSONSongs albumJSONSongs;

    public AlbumJSONSongs getAlbum() {
        return albumJSONSongs;
    }

    public void setAlbum(AlbumJSONSongs albumJSONSongs) {
        this.albumJSONSongs = albumJSONSongs;
    }

}
