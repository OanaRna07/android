package com.example.signuploginrealtime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MountainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MountainAdapter mountainAdapter;
    private TextView tv_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain);

        recyclerView = findViewById(R.id.recycler_view);
        tv_preferences = findViewById(R.id.tv_preferences);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mountainAdapter = new MountainAdapter(this);
        recyclerView.setAdapter(mountainAdapter);

        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPref.getString("key_mountain", "Default_mountain");

        tv_preferences.setText("Your favourite mountain is:" + username);


        List<Mountain> mountains = loadMountainsFromJson();
        if (mountains != null) {
            mountainAdapter.setMountains(mountains);
        }
    }

    private List<Mountain> loadMountainsFromJson() {
        List<Mountain> mountainList = new ArrayList<>();
        try {
            // Read the JSON file from assets
            InputStream inputStream = getAssets().open("mountains.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            // Deserialize JSON to List<Mountain>
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Mountain>>(){}.getType();
            mountainList = gson.fromJson(json, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mountainList;
    }
}
