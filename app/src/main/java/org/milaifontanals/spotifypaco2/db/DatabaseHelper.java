package org.milaifontanals.spotifypaco2.db;

import android.content.Context;

public class DatabaseHelper {
    private final AppDatabase db;

    public DatabaseHelper(Context context) {
        db = AppDatabase.getInstance(context);
    }

    public void getAlbumsAsync(DatabaseThread.OnQueryCompleteListener listener) {
        DatabaseThread databaseThread = new DatabaseThread(db.albumDao(), listener);
        databaseThread.start();
    }

    // S'introdueixen més mètodes a mesura que els vagi necessitan...
}
