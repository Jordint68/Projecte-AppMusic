package org.milaifontanals.spotifypaco2.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.milaifontanals.spotifypaco2.R;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Album implements Parcelable {

    /*
     *      Constructor buit:
     */
    public Album() {}

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "author")
    String author;

    @ColumnInfo(name = "year")
    int any;

    @ColumnInfo(name = "drawable")
    String drawable;

    protected Album(Parcel in) {
        id = in.readInt();
        name = in.readString();
        author = in.readString();
        any = in.readInt();
        drawable = in.readString();
    }

    public Album(int id, String name, String author, int any, String drawable) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.any = any;
        this.drawable = drawable;
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<Song> getSongs() {
        //return songs;
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Album(String name, String autor, int any, String drawable) {
        this.name = name;
        this.author = autor;
        this.any = any;
        this.drawable = drawable;
        //songs = new ArrayList<>();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(author);
        dest.writeInt(any);
        dest.writeString(drawable);
    }
}
