package com.example.mini_projet_dam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class Tramway extends BaseActivity {
    ArrayList<Category> categories;
    ListView lv;
    CategoryAdapter adapter;
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_list);

        // Retrieve the title (e.g., "Bridges")
        String title = getIntent().getStringExtra("TITLE");

        // Set the title
        titleTextView = findViewById(R.id.titleTextView);
        if (titleTextView != null) {
            titleTextView.setText(title);
        }

        // Initialize ListView and categories
        lv = findViewById(R.id.listView);
        categories = new ArrayList<>();

        // Add items with names and locations (latitude, longitude)
        categories.add(new Category(getString(R.string.ben_abdelmalek_ramdane), 36.2057, 6.3733));
        categories.add(new Category(getString(R.string.zouaghi_slimane), 36.31043, 6.61941));
        categories.add(new Category(getString(R.string.chahid_kadri_brahim), 36.365, 6.614722));



        // Set up adapter
        adapter = new CategoryAdapter(this, R.layout.item_list, categories);
        lv.setAdapter(adapter);

        // Handle item click: Pass index and location data
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category selectedCategory = categories.get(position);

                Intent intent = new Intent(Tramway.this, Disctransport.class);
                intent.putExtra("CATEGORY_INDEX", 1); // This is the Bridges category
                intent.putExtra("ITEM_INDEX", position); // Send the index
                intent.putExtra("LATITUDE", selectedCategory.latitude);
                intent.putExtra("LONGITUDE", selectedCategory.longitude);
                startActivity(intent);
            }
        });
//
    }

}

