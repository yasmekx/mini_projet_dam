package com.example.mini_projet_dam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.GridView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ArrayList<Category> categories;
    GridView lv;
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
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
        adapter = new CategoryAdapter(this, categories);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCategory = categories.get(position).name; // Assuming getName() returns category name

            if (selectedCategory.equals("Restaurants")){
                startActivity(new Intent(MainActivity.this, RestaurantActivity.class));
            }
            else if (selectedCategory.equals("Tourist Attractions")){
                startActivity(new Intent(MainActivity.this, TouristSitesActivity.class));
            }
            else if (selectedCategory.equals("Transportation")){
                startActivity(new Intent(MainActivity.this, TransportationActivity.class));
            }
            else if (selectedCategory.equals("Hotels")){
                startActivity(new Intent(MainActivity.this, HotelsActivity.class));
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

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("my_lang", "en");

        Locale myLocale = new Locale(language);
        Locale.setDefault(myLocale);
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        config.setLocale(myLocale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
