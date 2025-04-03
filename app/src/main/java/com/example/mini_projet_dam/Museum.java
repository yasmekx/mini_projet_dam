package com.example.mini_projet_dam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Museum extends BaseActivity{
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
        categories.add(new Category(getString(R.string.cirta_museum), 36.3655, 6.6147));

        // Set up adapter
        adapter = new CategoryAdapter(this, R.layout.item_list, categories);
        lv.setAdapter(adapter);

        // Handle item click: Pass index and location data
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category selectedCategory = categories.get(position);

                Intent intent = new Intent(Museum.this, Disc.class);
                intent.putExtra("CATEGORY_INDEX", 2); // This is the Bridges category
                intent.putExtra("ITEM_INDEX", position); // Send the index
                intent.putExtra("LATITUDE", selectedCategory.latitude);
                intent.putExtra("LONGITUDE", selectedCategory.longitude);
                startActivity(intent);
            }
        });
//
    }
}
