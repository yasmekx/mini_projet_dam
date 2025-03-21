package com.example.mini_projet_dam;

import android.graphics.drawable.Drawable;

public class Category {
    String name;
    String description;
    String location;
    String phone;
    Drawable img;

    public Category(String name, String description, String location, String phone, Drawable img){
        this.name = name;
        this.description = description;
        this.location = location;
        this.phone = phone;
        this.img = img;
    }
    public Category(String name, Drawable img){
        this.name = name;
        this.img = img;
    }
}
