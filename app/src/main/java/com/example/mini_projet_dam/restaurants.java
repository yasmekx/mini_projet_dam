
package com.example.mini_projet_dam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class restaurants extends BaseActivity {

    ImageView rest_image;
    TextView rest_name, rest_disc, rest_location;
    MaterialButton mapButton;
    double latitude;
    double longitude;
    int categoryIndex;
    int itemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_rest);

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        int[] images = {
                R.drawable.takeoff,
                R.drawable.palmiers,
                R.drawable.snackyouyou,
                R.drawable.mgc_hse,
                R.drawable.igherssan,
                R.drawable.ks
        };

        int[] names = {
                R.string.takeOff_Lounge,
                R.string.restaurant_les_palmiers,
                R.string.snack_YouYou,
                R.string.magic_house,
                R.string.igherssan_Restaurant,
                R.string.ks
        };

        int[] desciptions = {
                R.string.takeoff_description,
                R.string.palmiers_description,
                R.string.youyou_description,
                R.string.magic_house_description,
                R.string.igherssan_description,
                R.string.ks_description
        };

        int[] locations = {
                R.string.takeoff_location,
                R.string.palmiers_location,
                R.string.youyou_location,
                R.string.magic_house_location,
                R.string.igherssan_location,
                R.string.ks_location
        };

        int[] phone_numbers = {
                R.string.takeoff_number,
                R.string.palmiers_number,
                R.string.yoyou_number,
                R.string.magic_number,
                R.string.igherssan_number,
                R.string.ks_number
        };

        int[] adresses = {
                R.string.takeoff_addr,
                R.string.palmiers_addr,
                R.string.youyouc_addr,
                R.string.magic_addr,
                R.string.igherssan_addr,
                R.string.ks_addr
        };

        String[][] dishes = {
                {
                        getString(R.string.lounge_bbq),
                        getString(R.string.lounge_margerita),
                        getString(R.string.lounge_chkicken_pizza),
                        getString(R.string.lounge_pasta_salade),
                        getString(R.string.lounge_brownies),
                        getString(R.string.lounge_creme_brule)


                },
                {
                        getString(R.string.palmier_cocktail),
                        getString(R.string.palmier_merlon_grille),
                        getString(R.string.palmier_crevette),
                        getString(R.string.palmier_dorade),
                        getString(R.string.palmier_couscous),
                        getString(R.string.palmier_salade)


                },
                {
                        getString(R.string.snack_tacos_poulet),
                        getString(R.string.snack_tacos_viande),
                        getString(R.string.snack_chapati_poulet),
                        getString(R.string.snack_chapati_viande),
                        getString(R.string.snack_brownies),
                        getString(R.string.snack_crepe)
                },
                {
                        getString(R.string.mgc_eascalope_wsauce),
                        getString(R.string.mgc_escalope),
                        getString(R.string.mgc_kebab),
                        getString(R.string.mgc_poulet),
                        getString(R.string.mgc_mousse),
                        getString(R.string.mgc_tiramissu)
                },
                {
                        getString(R.string.ighr_espadon_grille),
                        getString(R.string.ighr_merlon_grille),
                        getString(R.string.ighr_rouget_grille),
                        getString(R.string.ighr_spagheti),
                        getString(R.string.ighr_caffe),
                        getString(R.string.ighr_the)
                },
                {
                        getString(R.string.ks_beef_burger),
                        getString(R.string.ks_cheese_burger),
                        getString(R.string.ks_panini_viande),
                        getString(R.string.ks_pizza_margerita),
                        getString(R.string.tiramissu),
                        getString(R.string.cheese_cake)
                }
        };


        String prices[][]={
                { "1200 DA",
                "1500 DA",
                "800 DA",
                "400 DA",
                "150 DA",
                "100 DA"},

                { "800 DA",
                    "1400 DA",
                    "1000 DA",
                    "600 DA",
                    "250 DA",
                    "100 DA"},
                { "2200 DA",
                    "1500 DA",
                    "400 DA",
                    "600 DA",
                    "300 DA",
                    "140 DA"},
                { "2000 DA",
                    "1500 DA",
                    "800 DA",
                    "400 DA",
                    "150 DA",
                    "100 DA"},
                { "1100 DA",
                    "1500 DA",
                    "800 DA",
                    "400 DA",
                    "150 DA",
                    "100 DA"},
                { "1200 DA",
                    "1500 DA",
                    "800 DA",
                    "400 DA",
                    "150 DA",
                    "100 DA"}
        };

        //the list view
        ListView listView=this.findViewById(R.id.listView);
        ArrayList<restaurant_class>restaurants=new ArrayList<>();
        //filling the array
        restaurants.add(new restaurant_class(R.string.takeOff_Lounge,R.string.fast_food,R.drawable.takeoff,3.5f));
        restaurants.add(new restaurant_class(R.string.restaurant_les_palmiers,R.string.seafood,R.drawable.palmiers,4.5f));
        restaurants.add(new restaurant_class(R.string.snack_YouYou,R.string.fast_food,R.drawable.snackyouyou,3.2f));
        restaurants.add(new restaurant_class(R.string.magic_house,R.string.rest_cafe,R.drawable.mgc_hse,4.0f));
        restaurants.add(new restaurant_class(R.string.igherssan_Restaurant,R.string.traditional,R.drawable.igherssan,5.0f));
        restaurants.add(new restaurant_class(R.string.ks,R.string.fast_cafe,R.drawable.ks,4.0f));

        //setting the adapter
        restaurant_adapter adapter=new restaurant_adapter(this,restaurants);
        listView.setAdapter(adapter);

        //control click events of the list
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(restaurants.this, restautnat_details.class);

            //we send the data to the other activity
            intent.putExtra("name", names[position]);
            intent.putExtra("location", locations[position]);
            intent.putExtra("image", images[position]);
            intent.putExtra("phone", phone_numbers[position]);
            intent.putExtra("description", desciptions[position]);
            intent.putExtra("adress", adresses[position]);
            intent.putExtra("dishes", dishes[position]);
            intent.putExtra("prices", prices[position]);

            //start the activity
            startActivity(intent);
        });
    }

}
