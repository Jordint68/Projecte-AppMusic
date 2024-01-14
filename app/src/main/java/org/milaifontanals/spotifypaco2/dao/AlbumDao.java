package org.milaifontanals.spotifypaco2.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import org.milaifontanals.spotifypaco2.models.Album;

import java.util.List;

@Dao
public interface AlbumDao {
    @Query("SELECT * FROM album")
    LiveData<List<Album>> getAll();

    @Query("SELECT * FROM album WHERE id IN (:albumIds)")
    List<Album> loadAllByIds(int[] albumIds);

    @Insert
    void insertAll(Album... albums);

    @Delete
    void delete(Album user);
}
