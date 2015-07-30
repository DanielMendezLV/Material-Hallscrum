package org.halley.md.hallscrum.Activity.Listed;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.melnykov.fab.FloatingActionButton;

import org.halley.md.hallscrum.API.AddressAPI;
import org.halley.md.hallscrum.Activity.Adds.AddFaseActivity;
import org.halley.md.hallscrum.Activity.Adds.AddProyectActivity;
import org.halley.md.hallscrum.Adapter.FaseAdapter;
import org.halley.md.hallscrum.Adapter.MetasAdapter;
import org.halley.md.hallscrum.Adapter.ProyectAdapter;
import org.halley.md.hallscrum.MainActivity;
import org.halley.md.hallscrum.Model.Fase;
import org.halley.md.hallscrum.Model.Meta;
import org.halley.md.hallscrum.Model.Proyect;
import org.halley.md.hallscrum.Network.VolleySingleton;
import org.halley.md.hallscrum.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListFasesActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ArrayList<Fase> fases = new ArrayList<Fase>();
    private RecyclerView listFases;
    private FaseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fases);
        toolbar = (Toolbar)findViewById(R.id.toolbar);//
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        int idProyecto = extras.getInt("idproyecto");
        System.out.println("Id proyecto " + idProyecto);
        listFases = (RecyclerView) findViewById(R.id.listFases);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.attachToRecyclerView(listFases);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                int idproy = extras.getInt("idproyecto");
                //System.out.println("... lllll ego oooo a  fragment y paso al otro");
                Intent intent = new Intent(getApplicationContext(), AddFaseActivity.class);
                intent.putExtra("idproyecto", idproy);
                startActivity(intent);
            }
        });

        listFases.setLayoutManager(new LinearLayoutManager(this));
        Context contx = getApplicationContext();
        adapter = new FaseAdapter(ListFasesActivity.this);
        listFases.setAdapter(adapter);
        //System.out.println("Este es el id proyecto: "+idProyecto);
        sendJSONRequest(Integer.toString(idProyecto));
    }



    public Map<String, String> getMapAgregar(String id){
        Map<String, String> add= new HashMap<String, String>();
        add.put("idproyecto", id);
       // System.out.println("MAP" + id);
        return add;
    }


    public void sendJSONRequest(String idProyecto){
        System.out.println("...llego de nuevo");
        volleySingleton = volleySingleton.getsInstance();
        requestQueue = volleySingleton.getmRequestQueue();
        Map<String, String> map = getMapAgregar(idProyecto);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,AddressAPI.URL_FASES,new JSONObject(map), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    fases = parseJSONResponse(response);
                    adapter.setFases(fases);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyError", "Error");
            }


        });
        requestQueue.add(request);
    };

    public ArrayList<Fase> parseJSONResponse(JSONArray response){
        ArrayList<Fase> listaFases = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject faseDownload = response.getJSONObject(i);
                Fase fase = new Fase();
                fase.setNombre(faseDownload.getString("nombre"));
                fase.setFechaInicio(getFecha(faseDownload.getString("fechainicio")));
                fase.setFechaFinalizacion(getFecha(faseDownload.getString("fechafinalizacion")));
                fase.setIdFase(faseDownload.getInt("idfase"));

                /*if(i%2==0){
                    fase.setFoto(R.drawable.positivism);
                }else{
                    fase.setFoto(R.drawable.nihilism);
                }*/


                fase.setIdProyecto(faseDownload.getInt("idproyecto"));
                listaFases.add(fase);
            } catch (JSONException e) {
                Log.d("JSONException", "Error");
            }
        }
        return listaFases;
        //Toast.makeText(getActivity(),proyects.toString(),Toast.LENGTH_LONG).show();
    }

    public String getFecha(String fechaToFormat){
        String fecha[] = fechaToFormat.split("T");
        return fecha[0];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_fases, menu);
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

        if(id==android.R.id.home){
            startActivity(new Intent(ListFasesActivity.this,MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
