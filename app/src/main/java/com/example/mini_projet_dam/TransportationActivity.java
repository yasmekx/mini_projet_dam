package com.example.mini_projet_dam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

//check Base activity to understand why we're extending this class instead of the usual one:)
public class TransportationActivity extends BaseActivity {
    ArrayList<Category> categories;
    GridView lv;
    CategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity_list);
        // Change status bar color (y'all keep these lines)
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        // Initialize ListView and categories
        lv = findViewById(R.id.sites);
        categories = new ArrayList<>();

        // Add categories
        categories.add(new Category(getString(R.string.bus), ContextCompat.getDrawable(this, R.drawable.bus)));
        categories.add(new Category(getString(R.string.tramway), ContextCompat.getDrawable(this, R.drawable.tramway)));
        categories.add(new Category(getString(R.string.cablecar), ContextCompat.getDrawable(this, R.drawable.cablecar)));



        // Set up adapter
        adapter = new CategoryAdapter(this, R.layout.main_list, categories);
        lv.setAdapter(adapter);

        //set title
        TextView t = findViewById(R.id.title);
        t.setText(R.string.Transportation);


        /// Add the OnItemClickListener HERE
        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = null;

            if (position == 0) {
                intent = new Intent(TransportationActivity.this, Bus.class);
                intent.putExtra("TITLE", getString(R.string.bus));
            } else if (position == 1) {
                intent = new Intent(TransportationActivity.this, Tramway.class);
                intent.putExtra("TITLE", getString(R.string.tramway));
            } else if (position == 2) {
                intent = new Intent(TransportationActivity.this, Cablecar.class);
                intent.putExtra("TITLE", getString(R.string.cablecar));
            }

            if (intent != null) {
                startActivity(intent);
            }
        });



    }
}
