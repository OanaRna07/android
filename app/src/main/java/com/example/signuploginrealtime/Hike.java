package com.example.signuploginrealtime;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hikes")
public class Hike {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String elevation;
    private String distance;
    private String difficulty;
    private String description;
    private String imageUri;

    public Hike(){}

    public Hike(int id, String name, String elevation, String distance, String difficulty, String description, String imageUri) {
        this.id = id;
        this.name = name;
        this.elevation = elevation;
        this.distance = distance;
        this.difficulty = difficulty;
        this.description = description;
        this.imageUri = imageUri;
    }

    // Getters and setters
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

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
