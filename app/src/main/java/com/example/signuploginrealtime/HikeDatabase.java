package com.example.signuploginrealtime;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Hike.class}, version = 1)
public abstract class HikeDatabase extends RoomDatabase {
    private static HikeDatabase instance;

    public abstract HikeDao hikeDao();

    public static synchronized HikeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            HikeDatabase.class, "hike_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}