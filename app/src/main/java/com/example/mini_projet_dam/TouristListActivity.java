package com.example.mini_projet_dam;

import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class TouristListActivity extends BaseActivity {

    ArrayList<Category> categories;
    ListView lv;
    CategoryAdapter adapter;
    TextView titleTextView; // Reference to the TextView title

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_list); // Use a generic layout

        // Change status bar color
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        // Get reference to title TextView
        titleTextView = findViewById(R.id.titleTextView);

        // Get the title from the intent and set it
        String title = getIntent().getStringExtra("TITLE");
        if (title != null) {
            titleTextView.setText(title);
        }

        // Initialize ListView and categories
        lv = findViewById(R.id.listView);
        categories = new ArrayList<>();

        categories.add(new Category(getString(R.string.bridges)));
        categories.add(new Category(getString(R.string.bridges)));
        categories.add(new Category(getString(R.string.bridges)));

        // Set up adapter
        adapter = new CategoryAdapter(this, R.layout.item_list, categories);
        lv.setAdapter(adapter);
    }
}
