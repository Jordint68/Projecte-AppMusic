package org.milaifontanals.spotifypaco2.db;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseSingleton {
    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context c)  {
        if(instance == null) {
            synchronized (AppDatabaseSingleton.class) {
                if(instance == null) {
                    instance = Room.databaseBuilder(
                            c.getApplicationContext(),
                            AppDatabase.class,
                            "dbAppMusic.db"
                    ).build();
                }
            }
        }
        return instance;
    }
}
