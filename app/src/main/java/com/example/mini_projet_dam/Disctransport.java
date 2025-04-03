package com.example.mini_projet_dam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;

public class Disctransport extends BaseActivity {

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
                // Bus station
                {
                        R.drawable.bus1,
                        R.drawable.bus2,

                },
                // Tramway
                {
                        R.drawable.tram1,
                        R.drawable.tram2,
                        R.drawable.tram3,
                },
                // Cable car
                {
                        R.drawable.cablecar1,
                        R.drawable.cablecar2,
                        R.drawable.cablecar3,

                }

        };

        int[][] nameResIds = {
                // Bus station
                {
                        R.string.sahraoui_taher,
                        R.string.ali_mendjeli,

                },
                // Tramway
                {
                        R.string.ben_abdelmalek_ramdane,
                        R.string.zouaghi_slimane,
                        R.string.chahid_kadri_brahim,
                },
                // cable car
                {
                        R.string.tanouji,
                        R.string.ibn_badis_university_hospital,
                        R.string.tatache_square,
                }

        };

        int[][] discResIds = {
                // Bus station
                {
                        R.string.sahraoui_taher_descreption,
                        R.string.ali_mendjeli_descreption,

                },
                // Tramway
                {
                        R.string.ben_abdelmalek_ramdane_desc,
                        R.string.zouaghi_slimane_desc,
                        R.string.chahid_kadri_brahim_desc,
                },
                // cable car
                {
                        R.string.tanouji_desc,
                        R.string.ibn_badis_university_hospital_desc,
                        R.string.tatache_square_desc,
                }

        };

        int[][] locationResIds = {
                // Bus
                {
                        R.string.sahraoui_taher_loc,
                        R.string.ali_mendjeli_loc,
                },
                // Tramway
                {
                        R.string.ben_abdelmalek_ramdane_loc,
                        R.string.zouaghi_slimane_loc,
                        R.string.chahid_kadri_brahim_loc,
                },
                // Cable car
                {
                        R.string.tanouji_loc,
                        R.string.ibn_badis_university_hospital_loc,
                        R.string.tatache_square_loc,
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
//
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