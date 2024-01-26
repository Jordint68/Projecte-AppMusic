package org.milaifontanals.spotifypaco2.API;

import org.milaifontanals.spotifypaco2.models.Resultats;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("/2.0/")
    Call<Resultats> searchAlbum(
            @Query("method") String method,
            @Query("album") String album,
            @Query("api_key") String apiKey,
            @Query("format") String format
    );


}

