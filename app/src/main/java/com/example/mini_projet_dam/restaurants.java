package com.example.mini_projet_dam;

import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

//check Base activity to understand why we're extending this class instead of the usual one:)
public class restaurants extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        // Change status bar color (yall keep these lines)
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        ArrayList<String> list=new ArrayList<String>();
        list.add("rsto1");
        list.add("resto2");
        list.add("resto3");

        ArrayAdapter<String> adapter=new ArrayAdapter<String> (
                this, R.layout.simple_list, R.id.name, list);

        ListView lv=(ListView)this.findViewById(R.id.simplelist);
        lv.setAdapter(adapter);





    }
}
