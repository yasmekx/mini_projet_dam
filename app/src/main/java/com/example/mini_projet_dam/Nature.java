package com.example.mini_projet_dam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Nature extends BaseActivity {
    ArrayList<Category> categories;
    ListView lv;
    CategoryAdapter adapter;
    TextView titleTextView; // Reference to the TextView tit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_list); // Ensure you have a `bridge.xml` layout

        // Retrieve the title passed from the intent
        String title = getIntent().getStringExtra("TITLE");

        // Set the title to a TextView (Make sure you have a TextView with id titleTextView in bridge.xml)
        TextView titleTextView = findViewById(R.id.titleTextView);
        if (titleTextView != null) {
            titleTextView.setText(title);
        }

        // Initialize ListView and categories
        lv = findViewById(R.id.listView);
        categories = new ArrayList<>();

        // Natural Sites Category
        categories.add(new Category(getString(R.string.ghabat_el_wahch), 36.3800, 6.6300));
        categories.add(new Category(getString(R.string.tiddis), 36.4500, 6.7000));
        categories.add(new Category(getString(R.string.oued_rhumel), 36.3671, 6.6149));

        // Set up adapter
        adapter = new CategoryAdapter(this, R.layout.item_list, categories);
        lv.setAdapter(adapter);

        // Handle item click: Pass index and location data
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category selectedCategory = categories.get(position);

                Intent intent = new Intent(Nature.this, Disc.class);
                intent.putExtra("CATEGORY_INDEX", 3); // This is the Bridges category
                intent.putExtra("ITEM_INDEX", position); // Send the index
                intent.putExtra("LATITUDE", selectedCategory.latitude);
                intent.putExtra("LONGITUDE", selectedCategory.longitude);
                startActivity(intent);
            }
        });

    }
}
