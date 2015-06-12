package org.halley.md.hallscrum.Fragment;


import android.app.DownloadManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.halley.md.hallscrum.Adapter.ProyectAdapter;
import org.halley.md.hallscrum.Model.Proyect;
import org.halley.md.hallscrum.Network.VolleySingleton;
import org.halley.md.hallscrum.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Proyect extends android.support.v4.app.Fragment {
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ArrayList<Proyect> proyects = new ArrayList<Proyect>();
    private RecyclerView listProyects;
    private ProyectAdapter adapter;

    public void setProyects(ArrayList<Proyect> proyects){
        this.proyects=proyects;
    }

    public ArrayList<Proyect> getProyects(){
        return this.proyects;
    }

    public Fragment_Proyect() {
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


        JsonArrayRequest request = new JsonArrayRequest("http://192.168.1.7:3000/api/proyect", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    proyects = parseJSONResponse(response);
                    adapter.setProyects(proyects);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), ""+error, Toast.LENGTH_LONG).show();
                Log.d("VolleyError", "Error");
            }


        });
        requestQueue.add(request);
    };

    public ArrayList<Proyect> parseJSONResponse(JSONArray response){
        ArrayList<Proyect> listaProyectos = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject proyectoDownload = response.getJSONObject(i);
                Proyect pry = new Proyect();
                pry.setNombre(proyectoDownload.getString("nombre"));
                pry.setFechaCreacion(proyectoDownload.getString("fechacreacion"));
                System.out.println(pry.getNombre());
                System.out.println(pry.getFechaCreacion());
                pry.setFoto(R.drawable.ic_action_person);

                listaProyectos.add(pry);
            } catch (JSONException e) {
                Log.d("JSONException", "Error");
            }
        }
        return listaProyectos;
        //Toast.makeText(getActivity(),proyects.toString(),Toast.LENGTH_LONG).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__proyect, container, false);
        listProyects = (RecyclerView) view.findViewById(R.id.listProyects);
        listProyects.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProyectAdapter(getActivity());
        listProyects.setAdapter(adapter);
        sendJSONRequest();
        return view;

    }

    public static void getInstance(int position){

    }

}
