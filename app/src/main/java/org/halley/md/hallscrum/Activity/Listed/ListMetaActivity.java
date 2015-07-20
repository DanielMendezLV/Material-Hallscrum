package org.halley.md.hallscrum.Activity.Listed;

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
import org.halley.md.hallscrum.Activity.Adds.AddMetaActivity;
import org.halley.md.hallscrum.Adapter.FaseAdapter;
import org.halley.md.hallscrum.Adapter.MetasAdapter;
import org.halley.md.hallscrum.MainActivity;
import org.halley.md.hallscrum.Model.Fase;
import org.halley.md.hallscrum.Model.Meta;
import org.halley.md.hallscrum.Network.VolleySingleton;
import org.halley.md.hallscrum.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListMetaActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ArrayList<Meta> metas = new ArrayList<Meta>();
    private RecyclerView listMetas;
    private MetasAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meta);

        toolbar = (Toolbar)findViewById(R.id.toolbar);//
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listMetas = (RecyclerView) findViewById(R.id.listMetas);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.attachToRecyclerView(listMetas);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                int idfase = extras.getInt("idfase");
                Intent intent = new Intent(getApplicationContext(), AddMetaActivity.class);
                intent.putExtra("idfase", idfase);
                startActivity(intent);
            }
        });

        listMetas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MetasAdapter(getApplicationContext());
        listMetas.setAdapter(adapter);
        Bundle extras = getIntent().getExtras();
        int idfase = extras.getInt("idfase");
        //System.out.println("Este es el id proyecto: "+idProyecto);
        sendJSONRequest(Integer.toString(idfase));

    }


    public Map<String, String> getMapAgregar(String id){
        Map<String, String> add= new HashMap<String, String>();
        add.put("idfase", id);
        // System.out.println("MAP" + id);
        return add;
    }


    public void sendJSONRequest(String idfase){
        volleySingleton = volleySingleton.getsInstance();
        requestQueue = volleySingleton.getmRequestQueue();
        Map<String, String> map = getMapAgregar(idfase);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, AddressAPI.URL_META,new JSONObject(map), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    metas = parseJSONResponse(response);
                    adapter.setMetas(metas);
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

    public ArrayList<Meta> parseJSONResponse(JSONArray response){
        ArrayList<Meta> listaMetas = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject metaDownload = response.getJSONObject(i);
                Meta meta = new Meta();
                meta.setDescripcion(metaDownload.getString(("descripcion")));
                meta.setEstado(metaDownload.getBoolean(("estado")));
                meta.setDescripcion(metaDownload.getString(("descripcion")));
                meta.setIdFase(metaDownload.getInt("idfase"));
                meta.setIdMeta(metaDownload.getInt("idmeta"));
                listaMetas.add(meta);
            } catch (JSONException e) {
                Log.d("JSONException", "Error");
            }
        }
        return listaMetas;
        //Toast.makeText(getActivity(),proyects.toString(),Toast.LENGTH_LONG).show();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_meta, menu);
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
            startActivity(new Intent(ListMetaActivity.this,MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
