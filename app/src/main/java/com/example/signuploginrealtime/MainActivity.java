package com.example.signuploginrealtime;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HikeAdapter hikeAdapter;
    FloatingActionButton fabAddHike;
    Button btnViewMountains;
    HikeDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        fabAddHike = findViewById(R.id.fab_add_hike);
        btnViewMountains = findViewById(R.id.btn_view_mountains);

        // Initialize Room database
        database = HikeDatabase.getInstance(this);

        // Setup RecyclerView
        hikeAdapter = new HikeAdapter(this, new ArrayList<>()); // Initialize with empty list
        recyclerView.setAdapter(hikeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load hikes from database
        loadHikesFromDatabase();

        // Floating Action Button click listener
        fabAddHike.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddHikeActivity.class);
            startActivity(intent);
        });

        btnViewMountains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MountainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadHikesFromDatabase() {
        // Load hikes from the database in the background
        new AsyncTask<Void, Void, List<Hike>>() {
            @Override
            protected List<Hike> doInBackground(Void... voids) {
                return database.hikeDao().getAllHikes();
            }

            @Override
            protected void onPostExecute(List<Hike> hikes) {
                super.onPostExecute(hikes);
                hikeAdapter.setData(hikes); // Update adapter data
            }
        }.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHikesFromDatabase();
    }
}