package org.milaifontanals.spotifypaco2.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import org.milaifontanals.spotifypaco2.dao.AlbumDao;
import org.milaifontanals.spotifypaco2.db.AppDatabase;
import org.milaifontanals.spotifypaco2.models.Album;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AlbumViewModel extends AndroidViewModel {
    private AppDatabase db;
    public MutableLiveData<Boolean> insertFet = new MutableLiveData<>();
    public AlbumViewModel(@NonNull Application application) {
        super(application);
        db = Room.databaseBuilder(application,
                AppDatabase.class, "dbAppMusic.db").build();
        insertFet.setValue(false);
    }

    public LiveData<List<Album>> getAlbums() {
        AlbumDao albumDao = db.albumDao();
        return albumDao.getAll();
    }

    public void insertAlbum(int id, String name, String author, int any, int drawable) {

        Observable.fromCallable(() -> {
            Log.d("XXX", "Inserim ????");
            AlbumDao albumDao = db.albumDao();
            Album a = new Album(id, name, author, any, drawable);
            albumDao.insertAll(a);
            insertFet.postValue(true);
            return true;
        }).subscribeOn(Schedulers.io()).subscribe();
    }
}
