package com.example.mini_projet_dam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mini_projet_dam.BaseActivity;
import com.example.mini_projet_dam.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class restautnat_details extends BaseActivity {

    ImageView rest_image;
    TextView rest_name,rest_disc,rest_location,phonetxt;
    TextView dish1,dish2,dish3,dish4,dish5,dish6;
    TextView price1,price2,price3,price4,price5,price6;
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

        TextView [][]tvs={
                {
                this.findViewById(R.id.dish1),
                this.findViewById(R.id.dish2),
                this.findViewById(R.id.dish3),
                this.findViewById(R.id.dish4),
                this.findViewById(R.id.dish5),
                this.findViewById(R.id.dish6)
                },{
                this.findViewById(R.id.price1),
                this.findViewById(R.id.price2),
                this.findViewById(R.id.price3),
                this.findViewById(R.id.price4),
                this.findViewById(R.id.price5),
                this.findViewById(R.id.price6)
        }

        };


        // Toolbar toolbar = findViewById(R.id.topAppBar);

        //getintent() allows the receiving activity to access the
        //data that was sent using intent from the other activity
        //getstringextra allows extracting the sent data from the other activity


        Intent intent=getIntent();
        if(intent !=null){
            String name = getString(intent.getIntExtra("name", 0));
            String discription = getString(intent.getIntExtra("description", 0));
            String location=getString(intent.getIntExtra("location",0)) ;
            int image=intent.getIntExtra("image",0);
            String phone = getString(intent.getIntExtra("phone",0));
            String adresses=getString(intent.getIntExtra("adress",0)) ;
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

        //filling the tablelayout

        //referencing the table layout
        TableLayout menu=findViewById(R.id.table_dishes);

        //get the dishes array from the other activity
        String[] dishes=getIntent().getStringArrayExtra("dishes");
        String[] prices_t=getIntent().getStringArrayExtra("prices");


        if(dishes!=null && dishes.length>0){
            menu.setVisibility(View.VISIBLE);
            for(int i=0;i<dishes.length;i++){
              TextView dishname=tvs[0][i];
              TextView dishprice=tvs[1][i];

             dishname.setText(dishes[i]);
             dishprice.setText(prices_t[i]);
            }
        }

    }

}
