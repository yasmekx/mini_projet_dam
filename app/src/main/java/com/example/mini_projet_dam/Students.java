package com.example.mini_projet_dam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class Students extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //to change the color of the icon bar
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.about) {
            startActivity(new Intent(this, About.class));
            finish();
            return true;
        }
        if (id == R.id.language) {
            showEditLang();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showEditLang(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Language");

        // Language options
        String[] languages = {"English", "Arabic"};
        final int[] selectedOption = {0}; // Default selection (0 = English)

        builder.setSingleChoiceItems(languages,selectedOption[0],(dialog, which) -> {
            selectedOption[0] = which;
        });

        //"ok" button to confirm selection
        builder.setPositiveButton("OK", (dialog, which) -> {
            if (selectedOption[0] == 0){
                setLocale("en");
            }
            else {
                setLocale("ar");
            }
        });
        // "Cancel" button to dismiss dialog
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        // Show the dialog (this was missing)
        builder.show();
    }
    private void setLocale(String lang) {
        // Save selected language
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("my_lang", lang);
        editor.apply();

        // Restart the app to apply changes globally
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("my_lang", "en"); // Default is English

        Locale myLocale = new Locale(language);
        Locale.setDefault(myLocale);
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        config.setLocale(myLocale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }


}
