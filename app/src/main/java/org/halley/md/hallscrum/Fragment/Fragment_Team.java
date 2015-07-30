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
import org.halley.md.hallscrum.Activity.Adds.AddTeamActivity;
import org.halley.md.hallscrum.Adapter.TeamAdapter;
import org.halley.md.hallscrum.Model.Team;
import org.halley.md.hallscrum.Network.VolleySingleton;
import org.halley.md.hallscrum.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Team extends android.support.v4.app.Fragment {
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ArrayList<Team> teams = new ArrayList<Team>();
    private RecyclerView listTeams;
    private TeamAdapter adapter;



    public Fragment_Team() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_team, container, false);
        listTeams = (RecyclerView) view.findViewById(R.id.listTeams);
        listTeams.setLayoutManager(new LinearLayoutManager(getActivity()));
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_team);
        fab.attachToRecyclerView(listTeams);

        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(getActivity(), AddTeamActivity.class));
            }
        });

        adapter = new TeamAdapter(getActivity());
        listTeams.setAdapter(adapter);
        sendJSONRequest();
        return view;

    }

    public static void getInstance(int position){

    }

    public void sendJSONRequest(){
        volleySingleton = volleySingleton.getsInstance();
        requestQueue = volleySingleton.getmRequestQueue();
        JsonArrayRequest request = new JsonArrayRequest(AddressAPI.URL_TEAMS, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    teams = parseJSONResponse(response);
                    adapter.setTeams(teams);
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


    public ArrayList<Team> parseJSONResponse(JSONArray response){
        ArrayList<Team> listaTeams = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject proyectoDownload = response.getJSONObject(i);
                Team team = new Team();
                team.setNombre(proyectoDownload.getString("nombre"));
                team.setIdEquipo(proyectoDownload.getInt("idequipo"));
                team.setKey(proyectoDownload.getString("mykey"));
                /*if(i%2==0){
                    team.setFoto(R.drawable.flashflash);
                }else{
                    team.setFoto(R.drawable.spidy);
                }*/
                listaTeams.add(team);
            } catch (JSONException e) {
                Log.d("JSONException", "Error");
            }
        }
        return listaTeams;
        //Toast.makeText(getActivity(),proyects.toString(),Toast.LENGTH_LONG).show();
    }








}
