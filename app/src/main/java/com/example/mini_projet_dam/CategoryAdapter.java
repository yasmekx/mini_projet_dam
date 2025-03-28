package com.example.mini_projet_dam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private final int resourceLayout;

    public CategoryAdapter(Context context, int resource, List<Category> categories) {
        super(context, resource, categories);
        this.resourceLayout = resource;
        Log.d("CategoryAdapter", "Constructor called with " + categories.size() + " categories");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("CategoryAdapter", "getView called for position " + position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceLayout, parent, false);
        }

        Category category = getItem(position);
        if (category != null) {
            TextView nameText = convertView.findViewById(R.id.name);
            nameText.setText(category.name);

            ImageView categoryImage = convertView.findViewById(R.id.img);
            if (categoryImage != null && category.img != null) {
                categoryImage.setImageDrawable(category.img);
            }
        }
        return convertView;
    }
}
