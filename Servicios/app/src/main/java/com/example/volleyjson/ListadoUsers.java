package com.example.volleyjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class ListadoUsers extends AppCompatActivity {

    ArrayList<Users> users = new ArrayList<Users>();
    ListView listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_equipos);
        Intent i = getIntent();
        users = i.getParcelableArrayListExtra("apiInfo");
        listado = findViewById(R.id.lstcompetitions);
        if (users!=null && users.size()>0){
           UsersAdapter adapter = new UsersAdapter(this, users);
           listado.setAdapter(adapter);
           adapter.notifyDataSetChanged();
           }
           else{
            Toast.makeText(this,"No hay datos" , Toast.LENGTH_LONG).show();
        }
    }
}
