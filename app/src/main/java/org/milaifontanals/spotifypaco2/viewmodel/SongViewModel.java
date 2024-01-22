package org.milaifontanals.spotifypaco2.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import org.milaifontanals.spotifypaco2.dao.AlbumDao;
import org.milaifontanals.spotifypaco2.dao.SongDao;
import org.milaifontanals.spotifypaco2.db.AppDatabase;
import org.milaifontanals.spotifypaco2.db.AppDatabaseSingleton;
import org.milaifontanals.spotifypaco2.models.Album;
import org.milaifontanals.spotifypaco2.models.Song;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SongViewModel extends AndroidViewModel {
    private AppDatabase db;
    public MutableLiveData<Boolean> insertFet = new MutableLiveData<>();
    public SongViewModel(@NonNull Application application) {
        super(application);


        db = AppDatabaseSingleton.getInstance(application);
        insertFet.setValue(false);
    }

    public LiveData<List<Song>> getSongsById(int id) {
        SongDao songDao = db.songDao();
        return songDao.getAllByAlbum(id);
    }

    public LiveData<List<Song>> getAllSongs() {
        SongDao songDao = db.songDao();
        return songDao.getAll();
    }

    public void insertAlbum(int id, int idAlbum, String name, boolean mg, String time) {

        Observable.fromCallable(() -> {
            Log.d("XXX", "Inserim ????");
            SongDao songDao = db.songDao();
            Song s = new Song(id, idAlbum, name, mg, time);
            songDao.insertAll(s);
            insertFet.postValue(true);
            return true;
        }).subscribeOn(Schedulers.io()).subscribe();
    }
}
