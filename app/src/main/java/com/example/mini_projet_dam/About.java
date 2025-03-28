package com.example.mini_projet_dam;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

public class About extends BaseActivity {

    String email = "Kenza.mennas@gmail.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        //to change the color of the icon bar
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        MaterialButton btn = findViewById(R.id.feedback);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+ email));
                intent.putExtra(Intent.EXTRA_SUBJECT,"Feedbaack for cirta App");
                intent.putExtra(Intent.EXTRA_TEXT,"Dear Cirta Support Team,\n\n");

                try {
                    startActivity(Intent.createChooser(intent,"send feedback via"));
                }catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(About.this, "No email app installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
