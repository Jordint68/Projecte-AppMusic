package org.milaifontanals.spotifypaco2.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.milaifontanals.spotifypaco2.dao.AlbumDao;
import org.milaifontanals.spotifypaco2.dao.SongDao;
import org.milaifontanals.spotifypaco2.models.Album;
import org.milaifontanals.spotifypaco2.models.Song;


// Configuració de la base de dades:

@Database(entities = {Album.class, Song.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlbumDao albumDao();
    public abstract SongDao songDao();

}

