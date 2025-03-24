package com.example.mini_projet_dam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private final int resourceLayout; // Store the passed layout

    public CategoryAdapter(Context context, int resource, List<Category> categories) {
        super(context, resource, categories);
        this.resourceLayout = resource; // Assign layout to the variable
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceLayout, parent, false);
        }

        Category category = getItem(position);
        if (category != null) {
            TextView nameText = convertView.findViewById(R.id.name);
            nameText.setText(category.name);

            // Check if layout has an ImageView before using it
            ImageView categoryImage = convertView.findViewById(R.id.img);
            if (categoryImage != null && category.img != null) {
                categoryImage.setImageDrawable(category.img);
            }
        }
        return convertView;
    }
}
