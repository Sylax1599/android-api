package com.example.volleyjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class ListadoInfo extends AppCompatActivity {

    ArrayList<ApiInfo> api_info = new ArrayList<ApiInfo>();
    ListView listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_api);
        Intent i = getIntent();
        api_info = i.getParcelableArrayListExtra("apiInfo");
        listado = findViewById(R.id.lstcompetitions);
        if (api_info!=null && api_info.size()>0){
           ApiInfoAdapter adapter = new ApiInfoAdapter(this, api_info);
           listado.setAdapter(adapter);
           adapter.notifyDataSetChanged();
           }
           else{
            Toast.makeText(this,"No hay datos" , Toast.LENGTH_LONG).show();
        }
    }
}
