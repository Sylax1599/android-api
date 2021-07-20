package com.example.volleyjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RepositoriesAdapter extends ArrayAdapter<Repositories>{

    public RepositoriesAdapter(@NonNull Context context, @NonNull ArrayList<Repositories> competitions) {
        super(context, 0, competitions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Repositories comp = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.txtid);
        TextView compet = (TextView) convertView.findViewById(R.id.txtcomp);
        TextView area = (TextView) convertView.findViewById(R.id.txtarea);
        // Populate the data into the template view using the data object
        id.setText(comp.getId());
        compet.setText(comp.getHtml_url());
        area.setText(comp.getDescription());
        // Return the completed view to render on screen
        return convertView;
    }
}

