package org.milaifontanals.spotifypaco2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.milaifontanals.spotifypaco2.dao.AlbumDao;
import org.milaifontanals.spotifypaco2.models.Album;


    // Configuració de la base de dades:

@Database(entities = {Album.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlbumDao albumDao();


}
