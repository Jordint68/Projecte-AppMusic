package org.milaifontanals.spotifypaco2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchAlbum {
    @SerializedName("Data")
    @Expose
    private List<AlbumJson> data;

    public List<AlbumJson> getData() {
        return data;
    }

    public void setData(List<AlbumJson> data) {
        this.data = data;
    }
}
