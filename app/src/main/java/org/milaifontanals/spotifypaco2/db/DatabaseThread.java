package org.milaifontanals.spotifypaco2.db;

import org.milaifontanals.spotifypaco2.dao.AlbumDao;
import org.milaifontanals.spotifypaco2.models.Album;

import java.util.List;

public class DatabaseThread  extends Thread {
    private final AlbumDao albumDao;
    private final OnQueryCompleteListener  listener;

    public DatabaseThread(AlbumDao albumDao, OnQueryCompleteListener listener) {
        this.albumDao = albumDao;
        this.listener = listener;
    }

    @Override
    public void run() {
        // Realitzar consultes a la base de dades:
        List<Album> albums = albumDao.getAll();



        // Notificar al listener amb els resultats:
        listener.onQueryComplete(albums);



    }


    public interface OnQueryCompleteListener {
        void onQueryComplete(List<Album> albums);
    }
}
