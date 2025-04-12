package com.example.mini_projet_dam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class restaurant_adapter extends ArrayAdapter<restaurant_class> {
    private Context context;
    private List<restaurant_class> restaurantList;

    public restaurant_adapter(@NonNull Context context, List<restaurant_class>list) {
        super(context,0,list);
        this.context=context;
        this.restaurantList=list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        restaurant_class restaurant=restaurantList.get(position);

        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.restaurant_list,parent,false);
        }
        ImageView imageview=convertView.findViewById(R.id.image);
        TextView name=convertView.findViewById(R.id.Name);
        TextView desc=convertView.findViewById(R.id.Description);
        RatingBar ratings=convertView.findViewById(R.id.ratingBar);

        imageview.setImageResource(restaurant.image);
        name.setText(restaurant.name);
        desc.setText(restaurant.description);
        ratings.setRating(restaurant.rating);

        return convertView;

    }
}
