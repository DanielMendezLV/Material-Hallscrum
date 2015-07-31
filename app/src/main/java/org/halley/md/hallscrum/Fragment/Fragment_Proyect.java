package org.halley.md.hallscrum.Fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.halley.md.hallscrum.API.AddressAPI;
import org.halley.md.hallscrum.Activity.Adds.AddProyectActivity;
import org.halley.md.hallscrum.Activity.Adds.AddTeamActivity;
import org.halley.md.hallscrum.Adapter.ProyectAdapter;
import org.halley.md.hallscrum.Model.Proyect;
import org.halley.md.hallscrum.Network.VolleySingleton;
import org.halley.md.hallscrum.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


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
    private FloatingActionButton ftlAgregar;
    private FloatingActionButton ftlRefresh;





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
        //System.out.println("Si hace la solicitud");
        volleySingleton = volleySingleton.getsInstance();
        requestQueue = volleySingleton.getmRequestQueue();


        JsonArrayRequest request = new JsonArrayRequest(AddressAPI.URL_PROJECTS, new Response.Listener<JSONArray>() {
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
                sendJSONRequest();
                Log.d("VolleyError", "Error Intentando de nuevo");
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
                String fecha[] = proyectoDownload.getString("fechacreacion").split("T");
                pry.setFechaCreacion(fecha[0]);

                pry.setIdProyecto(proyectoDownload.getInt("idproyecto"));
                /*
                if(i%2==0){
                    pry.setFoto(R.drawable.houston);
                }else{
                    pry.setFoto(R.drawable.golden);
                }*/


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

        FloatingActionMenu menu1 = (FloatingActionMenu) view.findViewById(R.id.menu);
        ftlAgregar = (FloatingActionButton) view.findViewById(R.id.ftlAgregar);
        ftlRefresh = (FloatingActionButton) view.findViewById(R.id.ftlRefresh);

        ftlAgregar.setOnClickListener(clickListener);
        ftlRefresh.setOnClickListener(clickListener);


        listProyects.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProyectAdapter(getActivity());
        listProyects.setAdapter(adapter);
        sendJSONRequest();
        return view;

    }


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = "";

            switch (v.getId()) {
                case R.id.ftlAgregar:
                    startActivity(new Intent(getActivity(), AddProyectActivity.class));
                    break;
                case R.id.ftlRefresh:
                    //System.out.println("Si lo presione?");
                    sendJSONRequest();
                    break;
            }
        }
    };


    public static void getInstance(int position){

    }

}
