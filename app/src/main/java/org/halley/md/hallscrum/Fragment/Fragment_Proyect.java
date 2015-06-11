package org.halley.md.hallscrum.Fragment;


import android.app.DownloadManager;
import android.os.Bundle;
import android.app.Fragment;
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

import org.halley.md.hallscrum.Network.VolleySingleton;
import org.halley.md.hallscrum.R;
import org.json.JSONObject;

import java.io.StringReader;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Proyect extends android.support.v4.app.Fragment {
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;



    public Fragment_Proyect() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        volleySingleton = volleySingleton.getsInstance();
        requestQueue = volleySingleton.getmRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://192.168.1.7:3000/api/proyect",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT);
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
        });
        requestQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment__proyect, container, false);


    }

    public static void getInstance(int position){

    }

}
