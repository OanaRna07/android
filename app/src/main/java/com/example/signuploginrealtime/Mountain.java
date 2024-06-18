package com.example.signuploginrealtime;

import com.google.gson.annotations.SerializedName;

public class Mountain {

    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private String height;

    @SerializedName("location")
    private String location;

    public Mountain(String name, String height, String location) {
        this.name = name;
        this.height = height;
        this.location = location;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
