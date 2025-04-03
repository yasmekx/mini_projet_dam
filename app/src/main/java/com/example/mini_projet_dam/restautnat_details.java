package com.example.mini_projet_dam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mini_projet_dam.BaseActivity;
import com.example.mini_projet_dam.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class restautnat_details extends BaseActivity {

    ImageView rest_image;
    TextView rest_name,rest_disc,rest_location,phonetxt;
    MaterialButton mapButton;
    MaterialButton callButton;
    MaterialCardView contactCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //get all the views into variables
        rest_image = this.findViewById(R.id.itemImageView);
        rest_name = this.findViewById(R.id.itemNameTextView);
        rest_disc = this.findViewById(R.id.disc);
        rest_location = this.findViewById(R.id.loc);
        mapButton = this.findViewById(R.id.mapButton);
        phonetxt=this.findViewById(R.id.phone);
        callButton= this.findViewById(R.id.callButton);
        contactCardView=findViewById(R.id.contactCardView);


        // Toolbar toolbar = findViewById(R.id.topAppBar);

        //getintent() allows the receiving activity to access the
        //data that was sent using intent from the other activity
        //getstringextra allows extracting the sent data from the other activity


        Intent intent=getIntent();
        if(intent !=null){
            String name = getString(intent.getIntExtra("name", R.string.ks));
            String discription = getString(intent.getIntExtra("description", R.string.ks));
            String location=getString(intent.getIntExtra("location",R.string.ks)) ;
            int image=intent.getIntExtra("image",R.drawable.bab_el_kantra);
            String phone = getString(intent.getIntExtra("phone",R.string.ks));
            String adresses=getString(intent.getIntExtra("adress",R.string.ks)) ;
            //update the ui
            rest_name.setText(name);
            rest_disc.setText(discription);
            rest_location.setText(location);
            rest_image.setImageResource(image);

            if(phone !=null && !phone.isEmpty()){
                phonetxt.setText(phone);
                contactCardView.setVisibility(View.VISIBLE);
            }
            else { contactCardView.setVisibility(View.GONE);}


            // Open location in Google Maps
            mapButton.setOnClickListener(v -> {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(adresses));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            });


            // Dial phone number
            callButton.setOnClickListener(v -> {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);
            });


        }

    }

}
