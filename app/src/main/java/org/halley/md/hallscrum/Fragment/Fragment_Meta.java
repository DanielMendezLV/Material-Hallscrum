package org.halley.md.hallscrum.Fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.melnykov.fab.FloatingActionButton;

import org.halley.md.hallscrum.API.AddressAPI;
import org.halley.md.hallscrum.Activity.Adds.AddMetaActivity;
import org.halley.md.hallscrum.Adapter.MetasAdapter;
import org.halley.md.hallscrum.Model.Meta;
import org.halley.md.hallscrum.Network.VolleySingleton;
import org.halley.md.hallscrum.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Meta extends android.support.v4.app.Fragment {

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ArrayList<Meta> metas = new ArrayList<Meta>();
    private RecyclerView listMetas;
    private MetasAdapter adapter;

    public void setMetas(ArrayList<Meta> metas){
        this.metas=metas;
    }

    public ArrayList<Meta> getMetas(){
        return this.metas;
    }

    public Fragment_Meta() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Toast.makeText(getActivity(),proyects.size()+"",Toast.LENGTH_LONG).show();

    }


    public void sendJSONRequest(){
        volleySingleton = volleySingleton.getsInstance();
        requestQueue = volleySingleton.getmRequestQueue();


        JsonArrayRequest request = new JsonArrayRequest(AddressAPI.URL_META, new Response.Listener<JSONArray>() {
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
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_LONG).show();
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
                Meta me = new Meta();
                me.setDescripcion(metaDownload.getString("descripcion"));
                me.setEstado(metaDownload.getBoolean("estado"));
                //me.setFoto(R.drawable.ic_action_person);
                listaMetas.add(me);
            } catch (JSONException e) {
                Log.d("JSONException", "Error");
            }
        }
        return listaMetas;
        //Toast.makeText(getActivity(),proyects.toString(),Toast.LENGTH_LONG).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__meta, container, false);
        listMetas = (RecyclerView) view.findViewById(R.id.listMetas);
        listMetas.setLayoutManager(new LinearLayoutManager(getActivity()));
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_meta);
        fab.attachToRecyclerView(listMetas);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddMetaActivity.class));
            }
        });


        adapter = new MetasAdapter(getActivity());
        listMetas.setAdapter(adapter);
        sendJSONRequest();
        return view;

    }

    public static void getInstance(int position){

    }
}
