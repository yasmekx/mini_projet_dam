package com.example.mini_projet_dam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Disc extends BaseActivity {

    LinearLayout mapButton;
    int index;
    TextView itemNameTextView;
    ImageView itemImageView;
    TextView itemDisc;
    TextView itemLocation;
    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details); // Ensure this layout exists

        // Get the index sent from Bridge activity
        index = getIntent().getIntExtra("ITEM_INDEX", -1); // Default to -1 if not found

        // Get location data
        latitude = getIntent().getDoubleExtra("LATITUDE", 0.0);
        longitude = getIntent().getDoubleExtra("LONGITUDE", 0.0);

        // Initialize UI elements
        itemNameTextView = findViewById(R.id.itemNameTextView);
        itemImageView = findViewById(R.id.itemImageView);
        itemDisc = findViewById(R.id.disc);
        itemLocation = findViewById(R.id.loc);
        mapButton = findViewById(R.id.mapButton);

        // Arrays for item details
        int[][] imageResIds = {
                {
                        R.drawable.sidi_mcid,
                        R.drawable.sidi_rached,
                        R.drawable.el_kantara,
                        R.drawable.pont_des_chutes,
                        R.drawable.bab_el_kantra
                }
        };

        int[][] nameResIds = {
                {
                        R.string.sidi_mcid,
                        R.string.sidi_rached,
                        R.string.el_kantara,
                        R.string.pont_des_chutes,
                        R.string.bab_el_kantra
                }
        };

        int[][] discResIds = {
                {
                        R.string.sidi_mcid_desc,
                        R.string.sidi_rached_desc,
                        R.string.el_kantara_desc,
                        R.string.pont_des_chutes_desc,
                        R.string.bab_el_kantra_desc
                }
        };

        int[][] locationResIds = {
                {
                        R.string.sidi_mcid_location,
                        R.string.sidi_rached_location,
                        R.string.el_kantara_location,
                        R.string.pont_des_chutes_location,
                        R.string.bab_el_kantra_location
                }
        };

        // Check if index is valid
        if (index >= 0 && index < imageResIds[0].length) {
            itemImageView.setImageResource(imageResIds[0][index]); // Set correct image
            itemNameTextView.setText(getString(nameResIds[0][index])); // Set correct name
            itemDisc.setText(getString(discResIds[0][index]));
            itemLocation.setText(getString(locationResIds[0][index]));
        } else {
            itemImageView.setImageResource(R.drawable.default_image); // Default image
        }

        // Set click listener
        mapButton.setOnClickListener(v -> {
            openGoogleMaps();
        });


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
