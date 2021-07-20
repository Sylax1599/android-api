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

public class ApiInfoAdapter extends ArrayAdapter<ApiInfo>{

    public ApiInfoAdapter(@NonNull Context context, @NonNull ArrayList<ApiInfo> competitions) {
        super(context, 0, competitions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       ApiInfo apiInfo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_api, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.api_id);
        TextView name = (TextView) convertView.findViewById(R.id.api_name);
        TextView url = (TextView) convertView.findViewById(R.id.api_url);
        // Populate the data into the template view using the data object
        id.setText(apiInfo.getId());
        name.setText(apiInfo.getName());
        url.setText(apiInfo.getUrl());
        // Return the completed view to render on screen
        return convertView;
    }
}
