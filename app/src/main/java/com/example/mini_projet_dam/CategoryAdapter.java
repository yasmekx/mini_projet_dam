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
    public CategoryAdapter(Context context, List<Category> categories) {
        super(context, 0, categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
        }

        Category category = getItem(position);
        if (category != null) {
            TextView nameText = convertView.findViewById(R.id.name);
            ImageView categoryImage = convertView.findViewById(R.id.img);

            nameText.setText(category.name);
            categoryImage.setImageDrawable(category.img);
        }
        return convertView;
    }
}
