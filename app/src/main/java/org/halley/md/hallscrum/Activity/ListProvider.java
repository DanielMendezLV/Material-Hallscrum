package org.halley.md.hallscrum.Activity;

/**
 * Created by U on 23/07/2015.
 */
import java.util.ArrayList;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.halley.md.hallscrum.API.AddressAPI;
import org.halley.md.hallscrum.Adapter.ProyectAdapter;
import org.halley.md.hallscrum.Model.Proyect;
import org.halley.md.hallscrum.Network.VolleySingleton;
import org.halley.md.hallscrum.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * If you are familiar with Adapter of ListView,this is the same as adapter
 * with few changes
 *
 */
public class ListProvider implements RemoteViewsFactory {
    private ArrayList<Proyect> listItemList = new ArrayList<Proyect>();
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ProyectAdapter adapter;

    private Context context = null;
    private int appWidgetId;

    public void setProyects(ArrayList<Proyect> proyects){
        this.listItemList=proyects;
    }

    public ArrayList<Proyect> getProyects(){
        return this.listItemList;
    }

    public ListProvider(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        populateListItem();

    }

    public void populateListItem() {



        for (int i = 0; i < 10; i++) {

            Proyect listItemProyect = new Proyect();
            listItemProyect.getIdProyecto();
            listItemProyect.getNombre();
            listItemProyect.getFechaCreacion();

            listItemList.add(listItemProyect);

        }

    }
    public void sendJSONRequest(){
        volleySingleton = volleySingleton.getsInstance();
        requestQueue = volleySingleton.getmRequestQueue();


        JsonArrayRequest request = new JsonArrayRequest(AddressAPI.URL_PROJECTS, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    listItemList = parseJSONResponse(response);
                    adapter.setProyects(listItemList);
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

    public ArrayList<Proyect> parseJSONResponse(JSONArray response) {
        ArrayList<Proyect> listaProyectos = new ArrayList<>();

            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject proyectoDownload = response.getJSONObject(i);
                    Proyect pry = new Proyect();
                    pry.setNombre(proyectoDownload.getString("nombre"));
                    pry.setFechaCreacion(proyectoDownload.getString("fechacreacion"));
                    pry.setIdProyecto(proyectoDownload.getInt("idproyecto"));
                    if (i % 2 == 0) {
                        pry.setFoto(R.drawable.houston);
                    } else {
                        pry.setFoto(R.drawable.golden);
                    }


                    listaProyectos.add(pry);
                } catch (JSONException e) {
                    Log.d("JSONException", "Error");
                }
            }

            return listaProyectos;
            //Toast.makeText(getActivity(),proyects.toString(),Toast.LENGTH_LONG).show();
}


    @Override
    public int getCount() {
        return listItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     *Similar to getView of Adapter where instead of View
     *we return RemoteViews
     *
     */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.list_row);

        Proyect listItemProyect = listItemList.get(position);

        if (listItemProyect != null) {
            remoteView.setTextViewText(R.id.proyectTitle, listItemProyect.getNombre());
            remoteView.setTextViewText(R.id.proyectDate, listItemProyect.getFechaCreacion());
            adapter = new ProyectAdapter(context);
            adapter.setProyects(listItemList);
            listItemProyect.setIdProyecto(listItemProyect.getIdProyecto());
            listItemProyect.setNombre(listItemProyect.getNombre());
            listItemProyect.setFechaCreacion(listItemProyect.getFechaCreacion());
            listItemProyect.setFoto(listItemProyect.getFoto());
            sendJSONRequest();
        }




        return remoteView;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {
    }

}