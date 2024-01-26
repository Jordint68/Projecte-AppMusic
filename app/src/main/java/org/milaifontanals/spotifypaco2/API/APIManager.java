package org.milaifontanals.spotifypaco2.API;

import org.milaifontanals.spotifypaco2.models.Resultats;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    private final String BASE_URL = "http://ws.audioscrobbler.com/";

    private static final String TOKEN = "c926b2d702cb69c703fe7b15983846f2";
    private static final String FORMAT = "json";

    private static APIManager mInstance;
    private API mApiService;


    private APIManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService=retrofit.create(API.class);
    }

    public static synchronized APIManager getInstance(){
        if(mInstance == null){
            mInstance = new APIManager();
        }

        return mInstance;
    }

    public void getAlbums(String titol, Callback<Resultats> cb) {
        Call<Resultats> call = mApiService.searchAlbum("album.search", titol, TOKEN, FORMAT);
        call.enqueue(cb);
    }

}
