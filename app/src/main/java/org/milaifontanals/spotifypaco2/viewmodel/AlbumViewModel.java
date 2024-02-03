package org.milaifontanals.spotifypaco2.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.milaifontanals.spotifypaco2.dao.AlbumDao;
import org.milaifontanals.spotifypaco2.db.AppDatabase;
import org.milaifontanals.spotifypaco2.db.AppDatabaseSingleton;
import org.milaifontanals.spotifypaco2.models.Album;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AlbumViewModel extends AndroidViewModel {
    private AppDatabase db;
    public MutableLiveData<Boolean> insertFet = new MutableLiveData<>();
    public MutableLiveData<Boolean> deleteFet = new MutableLiveData<>();
    public AlbumViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabaseSingleton.getInstance(application);
    }

    public LiveData<List<Album>> getAlbums() {
        AlbumDao albumDao = db.albumDao();
        return albumDao.getAll();
    }

    public LiveData<Album> getAlbumById(int idAlbum) {
        AlbumDao albumDao = db.albumDao();
        return albumDao.loadAllById(idAlbum);
    }

    public void insertAlbum(String name, String author, int any, String drawable) {

        Observable.fromCallable(() -> {
            Log.d("XXX", "Inserim ????");
            AlbumDao albumDao = db.albumDao();
            Album a = new Album(author, name, any, drawable);
            albumDao.insertAll(a);
            insertFet.postValue(true);
            return true;
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    public void deleteAlbum(Album a) {
        Observable.fromCallable(() -> {
            AlbumDao albumDao = db.albumDao();
            albumDao.delete(a);
            deleteFet.postValue(true);
            return true;
        }).subscribeOn(Schedulers.io()).subscribe();

    }
}
