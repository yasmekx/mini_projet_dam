package com.example.mini_projet_dam;

import android.graphics.drawable.Drawable;

public class Category {
    String name;
    String description;
    String location;
    String phone;
    Drawable img;
    double latitude;
    double longitude;

    public Category(String name, String description, String location, String phone, Drawable img){
        this.name = name;
        this.description = description;
        this.location = location;
        this.phone = phone;
        this.img = img;
    }
    public Category(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Category(String name, Drawable img){
        this.name = name;
        this.img = img;
    }
    public Category(String name){
        this.name = name;
    }
}
