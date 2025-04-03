package com.example.mini_projet_dam;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

//1st the class is extending the AppCompactActivity so the sub classes are doing the same thing
//the problem was that whenever i change the theme the sub activities go back to the main language
//instead of the one that i set! the issue was that the new setting was uploading in the main class
//only so to fix that we put the method of loading the language here and make the whole classes extend it
//befor setting the layout so the app knows which strings to use(ar or en)!
//if y'all are going to create new classes make sure to extend this;)

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
//
    }

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("my_lang", "en");

        Locale myLocale = new Locale(language);
        Locale.setDefault(myLocale);
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        config.setLocale(myLocale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
