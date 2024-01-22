package org.milaifontanals.spotifypaco2.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import org.milaifontanals.spotifypaco2.models.Song;

import java.util.List;


@Dao
public interface SongDao {
    @Query("SELECT * FROM song")
    LiveData<List<Song>> getAll();

    @Query("SELECT * FROM song WHERE idAlbum IN (:albumId)")
    LiveData<List<Song>> getAllByAlbum(int albumId);

    @Insert
    void insertAll(Song... songs);

    @Delete
    void delete(Song song);
}
