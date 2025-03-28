package com.example.mini_projet_dam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.GridView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends BaseActivity{

    ArrayList<Category> categories;
    GridView lv;
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Change status bar color (yall keep these lines)
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        // Initialize ListView and categories
        lv = findViewById(R.id.gridView);
        categories = new ArrayList<>();

        // Add categories
        categories.add(new Category(getString(R.string.Restaurants), ContextCompat.getDrawable(this, R.drawable.restruant)));
        categories.add(new Category(getString(R.string.Hotels), ContextCompat.getDrawable(this, R.drawable.hotel)));
        categories.add(new Category(getString(R.string.Tourist), ContextCompat.getDrawable(this, R.drawable.tourist)));
        categories.add(new Category(getString(R.string.Transportation), ContextCompat.getDrawable(this, R.drawable.trasportation)));

        // Set up adapter
        adapter = new CategoryAdapter(this, R.layout.main_list, categories);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = null;

            if (position == 0) { // Restaurants
                intent = new Intent(MainActivity.this, restaurants.class);
            } else if (position == 1) { // Hotels
                intent = new Intent(MainActivity.this, HotelsActivity.class);
            } else if (position == 2) { // Tourist Attractions
                intent = new Intent(MainActivity.this, TouristSitesActivity.class);
            } else if (position == 3) { // Transportation
                intent = new Intent(MainActivity.this, TransportationActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.about) {
            startActivity(new Intent(this, About.class));
            return true;
        }
        if (id == R.id.students) {
            startActivity(new Intent(this, Students.class));
            return true;
        }
        if (id == R.id.language) {
            showEditLang();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showEditLang() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Language");

        String[] languages = {"English", "Arabic"};
        final int[] selectedOption = {0};

        builder.setSingleChoiceItems(languages, selectedOption[0], (dialog, which) -> {
            selectedOption[0] = which;
        });

        builder.setPositiveButton("OK", (dialog, which) -> {
            if (selectedOption[0] == 0) {
                setLocale("en");
            } else {
                setLocale("ar");
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    private void setLocale(String lang) {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("my_lang", lang);
        editor.apply();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
