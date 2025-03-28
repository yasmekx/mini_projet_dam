package com.example.mini_projet_dam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class TouristSitesActivity extends BaseActivity {

    ArrayList<Category> categories;
    GridView lv;
    CategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tourist_sites);
        // Change status bar color (y'all keep these lines)
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        // Initialize ListView and categories
        lv = findViewById(R.id.sites);
        categories = new ArrayList<>();

        // Add categories
        categories.add(new Category(getString(R.string.bridges), ContextCompat.getDrawable(this, R.drawable.brg)));
        categories.add(new Category(getString(R.string.historical_landmarks), ContextCompat.getDrawable(this, R.drawable.historical)));
        categories.add(new Category(getString(R.string.natural_sites), ContextCompat.getDrawable(this, R.drawable.nature)));
        categories.add(new Category(getString(R.string.museums), ContextCompat.getDrawable(this, R.drawable.museum)));


        // Set up adapter
        adapter = new CategoryAdapter(this, R.layout.main_list, categories);
        lv.setAdapter(adapter);

        /// Add the OnItemClickListener HERE
        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = null;

            if (position == 0) {
                intent = new Intent(TouristSitesActivity.this, Bridge.class);
                intent.putExtra("TITLE", getString(R.string.bridges));
            } else if (position == 1) {
                intent = new Intent(TouristSitesActivity.this, Historical.class);
                intent.putExtra("TITLE", getString(R.string.historical_landmarks));
            } else if (position == 2) {
                intent = new Intent(TouristSitesActivity.this, Nature.class);
                intent.putExtra("TITLE", getString(R.string.natural_sites));
            } else if (position == 3) {
                intent = new Intent(TouristSitesActivity.this, Museum.class);
                intent.putExtra("TITLE", getString(R.string.museums));
            }
            if (intent != null) {
                startActivity(intent);
            }
        });


    }
}
