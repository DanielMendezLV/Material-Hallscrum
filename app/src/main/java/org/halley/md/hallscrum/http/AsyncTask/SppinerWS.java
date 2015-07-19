package org.halley.md.hallscrum.http.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.halley.md.hallscrum.API.AddressAPI;
import org.halley.md.hallscrum.Model.Team;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mendez Diaz on 19/07/2015.
 */
public class SppinerWS extends AsyncTask<String,Integer,ArrayList<Team>> {
    private static String URL_TEAMS_API= AddressAPI.URL_TEAMS_ALT;
    private static String TOKEN_KEY="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyb2lkIiwiaWF0IjoxNDM2MjAyMDAxLCJleHAiOjE0Mzc5MzAwMDF9.vUyVP-NcPz0ZaAuENvQXmwOKxwqXVZEL2jg47JKiYwc";
    private ArrayList<Team> array_team = new ArrayList<Team>();


    @Override
    protected ArrayList<Team> doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL_TEAMS_API);
        post.setHeader("Content-type","application/json");
        post.setHeader("charset","utf-8");
        post.setHeader("Authorization",TOKEN_KEY);
        JSONObject data = new JSONObject();

        try{
            array_team.clear();
            HttpResponse respuesta = httpClient.execute(post);
            JSONArray listaDeDatos = new JSONArray(EntityUtils.toString(respuesta.getEntity()));
            data = null;
            for(int i=0;i<listaDeDatos.length();i++){
                Team tmDownload = new Team();
                data = listaDeDatos.getJSONObject(i);
                tmDownload.setIdEquipo(data.getInt("idequipo"));
                tmDownload.setKey(data.getString("mykey"));
                tmDownload.setNombre(data.getString("nombre"));
                array_team.add(tmDownload);
            }
            return array_team;

        } catch(JSONException e){
            Log.e("doInBa-JSONEXCEPTION", "" + e);
        } catch (UnsupportedEncodingException e) {
            Log.e("doInBa-ENCODING",""+e);
        } catch (ClientProtocolException e) {
            Log.e("doInBa-CLIENT",""+e);
        } catch(IOException e){
            Log.e("doInBa-IO", "" + e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return null;
    }




}
