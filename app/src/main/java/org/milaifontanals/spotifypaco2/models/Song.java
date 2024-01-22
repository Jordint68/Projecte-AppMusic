package org.milaifontanals.spotifypaco2.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Song implements Parcelable {
    /*
    *   Constructor buit:
     */

    public Song() {}
    @PrimaryKey
    int id;
    @ColumnInfo(name = "idAlbum")
    int idAlbum;

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "mg")
    Boolean mg;
    @ColumnInfo(name = "time")
    String time;

    public Song(int id, int idAlbum, String name, Boolean mg, String time) {
        this.id = id;
        this.idAlbum = idAlbum;
        this.name = name;
        this.mg = mg;
        this.time = time;
    }

    protected Song(Parcel in) {
        id = in.readInt();
        name = in.readString();
        byte tmpMg = in.readByte();
        mg = tmpMg == 0 ? null : tmpMg == 1;
        time = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMg() {
        return mg;
    }

    public void setMg(Boolean mg) {
        this.mg = mg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Song(int id, String name, String time) {
        this.id = id;
        this.name = name;
        this.mg = false;
        this.time = time;

        // Per defecte, una canço no estará seleccionada dins de les que t'agraden.
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeByte((byte) (mg == null ? 0 : mg ? 1 : 2));
        dest.writeString(time);
    }
}
