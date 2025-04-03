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
        setContentView(R.layout.simple_list);

        // Change status bar color (yall keep these lines)
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));


        ArrayList<String> list = new ArrayList<String>();
        list.add("TakeOff Lounge");
        list.add("Restaurant les palmiers");
        list.add("Snack YouYOu");
        list.add("Magic House");
        list.add("Igherssan Restaurant");
        list.add("Le KS");

        int [] images={
                R.drawable.takeoff,
                R.drawable.palmiers,
                R.drawable.snackyouyou,
                R.drawable.mgc_hse,
                R.drawable.igherssan,
                R.drawable.ks
        };

        int[] names={
                R.string.takeOff_Lounge,
                R.string.restaurant_les_palmiers,
                R.string.snack_YouYou,
                R.string.magic_house,
                R.string.igherssan_Restaurant,
                R.string.ks
        };
        int[]desciptions={
                R.string.takeoff_description,
                R.string.palmiers_description,
                R.string.youyou_description,
                R.string.magic_house_description,
                R.string.igherssan_description,
                R.string.ks_description
        };
        int []locations={
                R.string.takeoff_location,
                R.string.palmiers_location,
                R.string.youyou_location,
                R.string.magic_house_location,
                R.string.igherssan_location,
                R.string.ks_location
        };

        int []phone_numbers={
                R.string.takeoff_number,
                R.string.palmiers_number,
                R.string.yoyou_number,
                R.string.magic_number,
                R.string.igherssan_number,
                R.string.ks_number

        };
        int[] adresses={
                R.string.takeoff_addr,
                R.string.palmiers_addr,
                R.string.youyouc_addr,
                R.string.magic_addr,
                R.string.igherssan_addr,
                R.string.ks_addr
        };


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.item_list, R.id.name, getStringArray(names));


        ListView lv = (ListView) this.findViewById(R.id.listView);
        lv.setAdapter(adapter);

        TextView default_name=this.findViewById(R.id.titleTextView);
        default_name.setText(R.string.Restaurants);

        //set an item click listener to open detailactivity

        lv.setOnItemClickListener((parent, view, position, id) ->{
            Intent intent =new Intent(restaurants.this,restautnat_details.class);

            //now we pass the data needed
            intent.putExtra("name",names[position]);
            intent.putExtra("location",locations[position]);
            intent.putExtra("image", images[position]);
            intent.putExtra("phone",phone_numbers[position]);
            intent.putExtra("description", desciptions[position]);
            intent.putExtra("adress",adresses[position]);

            //start the activity
            startActivity(intent);
        });

    }

    //this function coverts the ID int the "names " array into strings
    //to use it in the array addapter
    private String[] getStringArray(int[] resIds) {
        String[] strings = new String[resIds.length];
        for (int i = 0; i < resIds.length; i++) {
            strings[i] = getString(resIds[i]);
        }
        return strings;
    }
}









