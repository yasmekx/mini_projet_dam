package com.example.mini_projet_dam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

//check Base activity to understand why we're extending this class instead of the usual one:)
public class restaurants extends BaseActivity {

    ImageView rest_image;
    TextView rest_name,rest_disc,rest_location;
    MaterialButton mapButton;
    double latitude;//will be used later for maps
    double longitude;//will be used later for maps
    int categoryIndex; // New variable to track category
    int itemIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Change status bar color (yall keep these lines)
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        //get all the views into the variables
        rest_image=this.findViewById(R.id.itemImageView);
        rest_name=this.findViewById(R.id.itemNameTextView);
        rest_disc=this.findViewById(R.id.disc);
        rest_location=this.findViewById(R.id.loc);
        mapButton=this.findViewById(R.id.mapButton);
        Toolbar toolbar = findViewById(R.id.topAppBar);

        // Set the Toolbar as the ActionBar
        setSupportActionBar(toolbar);



        // Get the category index and item index sent from previous activity
        categoryIndex = getIntent().getIntExtra("CATEGORY_INDEX", 0); // Default to 0 if not found
        itemIndex = getIntent().getIntExtra("ITEM_INDEX", -1);

        // Get location data
        latitude = getIntent().getDoubleExtra("LATITUDE", 0.0);
        longitude = getIntent().getDoubleExtra("LONGITUDE", 0.0);


        ArrayList<String> list=new ArrayList<String>();
        list.add("rsto1");
        list.add("resto2");
        list.add("resto3");

        ArrayAdapter<String> adapter=new ArrayAdapter<String> (
                this, R.layout.item_list, R.id.name, list);

        ListView lv=(ListView)this.findViewById(R.id.listView);
        lv.setAdapter(adapter);

        //now that the list is ready move on to activities for restaurants


        int[][] imageResIds = {
                // pictures of restaurants
                {
                        R.drawable.igherssan,
                        R.drawable.mgc_hse,
                        R.drawable.palmiers,
                        R.drawable.snackyouyou,
                        R.drawable.takeoff
                },

        };

        int[][] nameResIds = {
                //names of restaurants
                {
                        R.string.igherssan_Restaurant,
                        R.string.magic_house,
                        R.string.restaurant_les_palmiers,
                        R.string.snack_YouYou,
                        R.string.takeOff_Lounge

                },

        };

        int[][] discResIds = {
                //discription of restaurants
                {
                        R.string.igherssan_description,
                        R.string.magic_house_description,
                        R.string.palmiers_description,
                        R.string.youyou_description,
                        R.string.takoff_description
                },

        };

        int[][] locationResIds = {
                //location of restaurants
                {
                        R.string.igherssan_location,
                        R.string.magic_house_location,
                        R.string.palmiers_location,
                        R.string.youyou_location,
                        R.string.takeoff_location
                },

        };

        // Check if indices are valid
        if (categoryIndex >= 0 && categoryIndex < imageResIds.length &&
                itemIndex >= 0 && itemIndex < imageResIds[categoryIndex].length) {

            getSupportActionBar().setTitle(nameResIds[categoryIndex][itemIndex]);

            // Set image
            rest_image.setImageResource(imageResIds[categoryIndex][itemIndex]);

            // Set name
            rest_name.setText(getString(nameResIds[categoryIndex][itemIndex]));

            // Set description
            rest_disc.setText(getString(discResIds[categoryIndex][itemIndex]));

            // Set location
            rest_location.setText(getString(locationResIds[categoryIndex][itemIndex]));
        } else {
            rest_image.setImageResource(R.drawable.default_image); // Default image
        }

        // Set click listener for map
//        mapButton.setOnClickListener(v -> openGoogleMaps());
    }


//    private void openGoogleMaps() {
//        if (latitude != 0.0 && longitude != 0.0) {
//            Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude);
//            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//            mapIntent.setPackage("com.google.android.apps.maps");
//
//            if (mapIntent.resolveActivity(getPackageManager()) != null) {
//                startActivity(mapIntent);  // Open in Google Maps app
//            } else {
//                // Open in a web browser if Google Maps app is not installed
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude));
//                startActivity(browserIntent);
//            }
//        }
//    }
}









