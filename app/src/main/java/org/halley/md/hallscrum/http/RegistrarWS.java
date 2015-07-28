package org.halley.md.hallscrum.http;

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
import org.halley.md.hallscrum.Model.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RegistrarWS extends AsyncTask<String,Integer,Usuario> {
    private static String URL_LOGIN_API= AddressAPI.URL_MAKE_AND_ACCOUNT;
    private static String TOKEN_KEY=AddressAPI.TOKEN;
    @Override
    protected Usuario doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL_LOGIN_API);
        post.setHeader("Content-type","application/json");
        post.setHeader("charset","utf-8");
        post.setHeader("Authorization",TOKEN_KEY);
        JSONObject data=new JSONObject();
        try {

            data.put("nombre", params[0]);
            data.put("apellido", params[1]);
            data.put("nickname", params[2]);
            data.put("contrasena", params[3]);


            //Aca si llegan los datos.
            StringEntity stringEntity=new StringEntity(data.toString());
            post.setEntity(stringEntity);
            //here this works
            HttpResponse respuesta=httpClient.execute(post);
            //System.out.println("Here doesn't work ");
            JSONArray listaDeDatos=new JSONArray(EntityUtils.toString(respuesta.getEntity()));
            data=null;
            for (int i=0;i<listaDeDatos.length();i++){
                data=listaDeDatos.getJSONObject(i);

                return new Usuario(
                        data.getString("nombre"),
                        data.getString("apellido"),
                        data.getString("nickname"),
                        data.getString("contrasena")
                );
            }


        }catch (JSONException  e){
            Log.e("doInBa-JSONEXCEPTION : ",""+e);
        }catch (UnsupportedEncodingException e){
            Log.e("doInBa-ENCODING: ",""+e);
        }catch(ClientProtocolException e){
            Log.e("doInBa-CLIENT: ",""+e);
        }catch (IOException e){
            Log.e("doInBa-IO: ",""+e);
        }
        return null;
    }
}
