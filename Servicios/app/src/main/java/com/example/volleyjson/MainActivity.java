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
    Boolean apiuser=false, apirepo=false;



    ArrayList<ApiInfo> ListUsers = new ArrayList<ApiInfo>();

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
                 apiuser=true;
             }
         });

        APIRepositoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url="https://api.github.com/repositories";
                requestDatos();
                apirepo=true;
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListadoInfo.class);
                i.putParcelableArrayListExtra("apiInfo", ListUsers);
                startActivity(i);
                ListUsers.clear();
            }
        });
    }

    public void requestDatos(){
        RequestQueue cola = Volley.newRequestQueue(this);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String mensaje="";
                        if(apiuser){
                            mensaje="Se han leido los datos de la API Users";
                        }
                        if(apirepo){
                            mensaje="Se han leido los datos de la API Repositories";
                        }
                        dato.setText(mensaje);
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

            if(apiuser){
                JSONArray users = response;

                for (int i = 0 ; i<users.length(); i++) {
                    JSONObject user = users.getJSONObject(i);
                    String id = user.getString("id");
                    String login = user.getString("login");
                    String url = user.getString("html_url");
                    ApiInfo co = new ApiInfo(id,login,url);
                    ListUsers.add(co);
                }

            }




            // ASÍ SE LEEN LOS DATOS DEL Repositorio, esta parte es tuya

            //API Repos
            if(apirepo){
                JSONArray repositories=response;

                for (int i = 0 ; i<repositories.length(); i++) {
                    JSONObject rep = repositories.getJSONObject(i);
                    String id = rep.getString("id");
                    String html_url = rep.getString("name");
                    String description = rep.getString("html_url");
                    ApiInfo co = new ApiInfo(id,html_url,description);
                    ListUsers.add(co);
                }
            }

            apiuser=false;
            apirepo=false;




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
