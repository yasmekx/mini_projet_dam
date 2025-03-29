package com.example.mini_projet_dam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;

public class Disc extends BaseActivity {

    MaterialButton mapButton;
    int categoryIndex; // New variable to track category
    int itemIndex;
    TextView itemNameTextView;
    ImageView itemImageView;
    TextView itemDisc;
    TextView itemLocation;
    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get the category index and item index sent from previous activity
        categoryIndex = getIntent().getIntExtra("CATEGORY_INDEX", 0); // Default to 0 if not found
        itemIndex = getIntent().getIntExtra("ITEM_INDEX", -1);

        // Get location data
        latitude = getIntent().getDoubleExtra("LATITUDE", 0.0);
        longitude = getIntent().getDoubleExtra("LONGITUDE", 0.0);

        // Initialize UI elements
        itemNameTextView = findViewById(R.id.itemNameTextView);
        itemImageView = findViewById(R.id.itemImageView);
        itemDisc = findViewById(R.id.disc);
        itemLocation = findViewById(R.id.loc);
        mapButton = findViewById(R.id.mapButton);
        Toolbar toolbar = findViewById(R.id.topAppBar);

        // Set the Toolbar as the ActionBar
        setSupportActionBar(toolbar);

        // Arrays for item details
        int[][] imageResIds = {
                // Bridges
                {
                        R.drawable.sidi_mcid,
                        R.drawable.sidi_rached,
                        R.drawable.el_kantara,
                        R.drawable.pont_des_chutes,
                        R.drawable.bab_el_kantra
                },
                // Historical Landmarks
                {
                        R.drawable.emir_abdelkader_mosque,
                        R.drawable.palace_ahmed_bey
                },
                // Museums
                {
                        R.drawable.cirta_museum,
                        R.drawable.cirta_museum,
                        R.drawable.cirta_museum,
                        R.drawable.cirta_museum,
                        R.drawable.cirta_museum
                },
                // Natural Sites (New Category)
                {
                        R.drawable.ghabat_el_wahch,
                        R.drawable.tiddis,
                        R.drawable.oued_rhumel
                }
        };

        int[][] nameResIds = {
                // Bridges
                {
                        R.string.sidi_mcid,
                        R.string.sidi_rached,
                        R.string.el_kantara,
                        R.string.pont_des_chutes,
                        R.string.bab_el_kantra
                },
                // Historical Landmarks
                {
                        R.string.emir_abdelkader_mosque,
                        R.string.palace_ahmed_bey
                },
                // Museums
                {
                        R.string.cirta_museum
                },
                // Natural Sites (New Category)
                {
                        R.string.ghabat_el_wahch,
                        R.string.tiddis,
                        R.string.oued_rhumel
                }
        };

        int[][] discResIds = {
                // Bridges
                {
                         R.string.sidi_mcid_desc,
                        R.string.sidi_rached_desc,
                        R.string.el_kantara_desc,
                        R.string.pont_des_chutes_desc,
                        R.string.bab_el_kantra_desc
                },
                // Historical Landmarks
                {
                        R.string.emir_abdelkader_mosque_desc,
                        R.string.palace_ahmed_bey_desc
                },
                // Museums
                {
                        R.string.cirta_museum_desc
                },
                // Natural Sites (New Category)
                {
                        R.string.ghabat_el_wahch_desc,
                        R.string.tiddis_desc,
                        R.string.oued_rhumel_desc
                }
        };

        int[][] locationResIds = {
                // Bridges
                {
                        R.string.sidi_mcid_location,
                        R.string.sidi_rached_location,
                        R.string.el_kantara_location,
                        R.string.pont_des_chutes_location,
                        R.string.bab_el_kantra_location
                },
                // Historical Landmarks
                {
                        R.string.emir_abdelkader_mosque_location,
                        R.string.palace_ahmed_bey_location
                },
                // Museums
                {
                        R.string.cirta_museum_location
                },
                // Natural Sites (New Category)
                {
                        R.string.ghabat_el_wahch_location,
                        R.string.tiddis_location,
                        R.string.oued_rhumel_location
                }
        };

        // Check if indices are valid
        if (categoryIndex >= 0 && categoryIndex < imageResIds.length &&
                itemIndex >= 0 && itemIndex < imageResIds[categoryIndex].length) {

            getSupportActionBar().setTitle(nameResIds[categoryIndex][itemIndex]);

            // Set image
            itemImageView.setImageResource(imageResIds[categoryIndex][itemIndex]);

            // Set name
            itemNameTextView.setText(getString(nameResIds[categoryIndex][itemIndex]));

            // Set description
            itemDisc.setText(getString(discResIds[categoryIndex][itemIndex]));

            // Set location
            itemLocation.setText(getString(locationResIds[categoryIndex][itemIndex]));
        } else {
            itemImageView.setImageResource(R.drawable.default_image); // Default image
        }

        // Set click listener for map
        mapButton.setOnClickListener(v -> openGoogleMaps());
    }

    private void openGoogleMaps() {
        if (latitude != 0.0 && longitude != 0.0) {
            Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");

            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);  // Open in Google Maps app
            } else {
                // Open in a web browser if Google Maps app is not installed
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude));
                startActivity(browserIntent);
            }
        }
    }
}