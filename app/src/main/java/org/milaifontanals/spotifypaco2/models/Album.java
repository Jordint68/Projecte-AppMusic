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

    @PrimaryKey
    int id;
    //List<Song> songs;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "author")
    String author;

    @ColumnInfo(name = "year")
    int any;

    @ColumnInfo(name = "drawable")
    int drawable;

    protected Album(Parcel in) {
        id = in.readInt();
        name = in.readString();
        author = in.readString();
        any = in.readInt();
        drawable = in.readInt();
    }

    public Album(int id, String name, String author, int any, int drawable) {
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



    /*
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    */

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

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Album(String name, String autor, int any, int drawable) {
        this.name = name;
        this.author = autor;
        this.any = any;
        this.drawable = drawable;
        //songs = new ArrayList<>();
    }


    private static List<Album> lAlbums;

    public static List<Album> createListAlbums() {
        if(lAlbums == null) {
            lAlbums = new ArrayList<>();
            Album a1 = new Album("A Night At The Opera","Queen", 1999, R.drawable.queen_logo);
            Album a2 = new Album("Californication","Red Hot Chili Peppers", 1999, R.drawable.rhcp_californication_logo);
            Album a3 = new Album("The Resistance", "Muse", 2009, R.drawable.muse_resistance_logo);
            Album a4 = new Album("Nevermind", "Nirvana", 2009, R.drawable.nevermind_nirvana_logo);

            /*
            List<Song> ls = new ArrayList<>();
            a1.songs.add(new Song(1, "Death on Two Legs", "3:43"));
            a1.songs.add(new Song(2, "I'm In Love With My Car", "3:05"));

            ls.clear();
            a2.songs.add(new Song(1, "Californication", "5:22"));
            a2.songs.add(new Song(2, "Otherside", "4:16"));

            ls.clear();
            a3.songs.add(new Song(1, "Uprising", "5:05"));
            a3.songs.add(new Song(2, "Resistance", "5:47"));

            ls.clear();
            a4.songs.add(new Song(1,"Smells Like Teen Spirit", "5:01"));
            a4.songs.add(new Song(2, "Come as You Are", "3:39"));

            lAlbums.add(a1);
            lAlbums.add(a2);
            lAlbums.add(a3);
            lAlbums.add(a4);
             */
        }
        return lAlbums;
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
        dest.writeInt(drawable);
    }
}
