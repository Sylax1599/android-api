package com.example.volleyjson;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button APIUsersButton, APIRepositoriesButton, listar;
    TextView dato;
    String url= "";


    ArrayList<Users> ListUsers = new ArrayList<Users>();
    ArrayList<Repositories> ListRepos = new ArrayList<Repositories>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIUsersButton = findViewById(R.id.APIUsersButton);
        APIRepositoriesButton = findViewById(R.id.APIRepositoriesButton);
         dato = findViewById(R.id.txtdatos);
         listar = findViewById(R.id.btnlistado);

        APIUsersButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 url="https://api.github.com/users";
                 requestDatos();
             }
         });

        APIRepositoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url="https://api.github.com/repositories";
                requestDatos();
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListadoUsers.class);
                i.putParcelableArrayListExtra("apiInfo", ListUsers);
                startActivity(i);
            }
        });
    }

    public void requestDatos(){
        RequestQueue cola = Volley.newRequestQueue(this);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        dato.setText("Se han leido los datos de la API");
                        parserJson(response);
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "onErrorResponse: "+error);
                Toast.makeText(getApplicationContext(),"Error en la conexion"+ error, Toast.LENGTH_LONG).show();
            }
            })


        ;

        cola.add(jsonObjectRequest);
    }

        public void parserJson(JSONArray response){
        try {

            // SI USAR LA URL DE REPOSITORIOS, PUES DARÁ ERROR PORQUE EL CAMPO LOGIN NO EXISTE EN EL ARRAY
            //API Users
            JSONArray users = response;

            for (int i = 0 ; i<users.length(); i++) {
                JSONObject com = users.getJSONObject(i);
                String id = com.getString("id");
                String login = com.getString("login");
                String url = com.getString("html_url");
                Users co = new Users(id,login,url);
                ListUsers.add(co);
            }


            // ASÍ SE LEEN LOS DATOS DEL Repositorio, esta parte es tuya
            /*
            //API Repos
            JSONArray repositories=response;

            for (int i = 0 ; i<repositories.length(); i++) {
                JSONObject com = repositories.getJSONObject(i);
                String id = com.getString("id");
                String html_url = com.getString("html_url");
                String description = com.getString("description");
                Repositories co = new Repositories(id,html_url,description);
                ListRepos.add(co);
            }
             */


        }
            catch (JSONException e) {
               Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
