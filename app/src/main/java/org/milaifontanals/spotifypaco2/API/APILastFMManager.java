package org.milaifontanals.spotifypaco2.API;


import org.milaifontanals.spotifypaco2.models.AlbumJson;
import org.milaifontanals.spotifypaco2.models.SearchAlbum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APILastFMManager {
    // Constant de tipus String amb la part estàtica o bàsica de la URL:
    private final String BASE_URL = "https://ws.audioscrobbler.com";

    private static APILastFMManager mInstance;
    private APILastFM mApiService;


    private APILastFMManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = retrofit.create(APILastFM.class);
    }

    public static synchronized APILastFMManager getInstance(){
        if(mInstance == null){
            mInstance = new APILastFMManager();
        }

        return mInstance;
    }

    /*
        Funció que cridem per a fer la cerca de albums.
        Param: titol del album que volem trobar
        'Retorna' una llista de albums.
     */
    public void getAlbums(String titol, Callback<SearchAlbum> cb) {
        Call<SearchAlbum> call = mApiService.searchAlbums(titol);
        call.enqueue(cb);
    }

}
