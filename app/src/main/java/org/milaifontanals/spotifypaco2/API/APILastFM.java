package org.milaifontanals.spotifypaco2.API;

import org.milaifontanals.spotifypaco2.models.AlbumJson;
import org.milaifontanals.spotifypaco2.models.SearchAlbum;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APILastFM {
    // Quan es fagi una consulta es rebrà un objecte de classe 'SearchAlbum'

    @GET("/2.0/?method=album.search&album={titol}&api_key=c926b2d702cb69c703fe7b15983846f2&format=json")
    Call<SearchAlbum> searchAlbums(
            @Path("titol") String titol
    );
}
