package org.halley.md.hallscrum.http;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.halley.md.hallscrum.Network.VolleySingleton;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Mendez Diaz on 18/07/2015.
 */
public class HallscrumRequests {
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public HallscrumRequests(){

    }

    public void addHallScrum(String url, Map<String, String> mapDatos){
        sendJSONRequest(url, Request.Method.POST ,mapDatos);
    }

    public void editHallScrum(String url, Map<String, String> mapDatos){
        sendJSONRequest(url, Request.Method.PUT ,mapDatos);
    }

    public void deleteHallScrum(String url, Map<String, String> mapDatos){
        sendJSONRequest(url, Request.Method.DELETE ,mapDatos);
    }

    public void sendJSONRequest(String url,int method,Map<String, String> mapDatos){
        volleySingleton = volleySingleton.getsInstance();
        requestQueue = volleySingleton.getmRequestQueue();
        System.out.println("... llego a Hallscrum Request");
        JsonArrayRequest request = new JsonArrayRequest(method,url,new JSONObject(mapDatos), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyError", "Error");
                System.out.println(error.toString());
            }
        });
        requestQueue.add(request);
    };




}
