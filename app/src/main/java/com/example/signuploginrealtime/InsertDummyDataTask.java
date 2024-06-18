package com.example.signuploginrealtime;

import android.os.AsyncTask;

public class InsertDummyDataTask extends AsyncTask<Void, Void, Void> {

    private final HikeDao hikeDao;

    public InsertDummyDataTask(HikeDao hikeDao) {
        this.hikeDao = hikeDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // Insert dummy data
        hikeDao.insert(new Hike(1,"Mountain A", "3000 meters", "10 km", "Hard", "Beautiful mountain with stunning views", ""));
        hikeDao.insert(new Hike(2,"Mountain B", "2500 meters", "8 km", "Moderate", "Scenic trail through forests", ""));
        hikeDao.insert(new Hike(3,"Mountain C", "4000 meters", "15 km", "Difficult", "Challenging trek with rocky terrain", ""));
        return null;
    }
}
