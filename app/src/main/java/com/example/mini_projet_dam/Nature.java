package com.example.mini_projet_dam;

import android.os.Bundle;
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

        // Set up adapter
        adapter = new CategoryAdapter(this, R.layout.item_list, categories);
        lv.setAdapter(adapter);
    }
}
