package com.example.mini_projet_dam;

import android.os.Bundle;
import android.view.Window;
import androidx.core.content.ContextCompat;

//check Base activity to understand why we're extending this class instead of the usual one:)
public class TransportationActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Change status bar color (yall keep these lines)
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));
    }
}
