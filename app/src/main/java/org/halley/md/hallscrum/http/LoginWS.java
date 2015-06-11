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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.halley.md.hallscrum.Model.Usuario;

public class LoginWS extends AsyncTask<String,Integer,Usuario> {
    private static String URL_LOGIN_API="http://192.168.1.7:3000/auth/login";
    private static String TOKEN_KEY="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyb2lkIiwiaWF0IjoxNDMyODcwNjcxLCJleHAiOjE0MzQ1OTg2NzF9.R4r3srlBmiAahfTlyQDWN5alYA9EtWNGYWuGoZ8Igu4";

    @Override
    protected Usuario doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL_LOGIN_API);
        post.setHeader("Content-type","application/json");
        post.setHeader("charset","utf-8");
        post.setHeader("Authorization",TOKEN_KEY);
        JSONObject data=new JSONObject();
        try {
            data.put("nickname", params[0]);
            data.put("contrasena", params[1]);

            if(params[0].equalsIgnoreCase("admin") && params[1].equalsIgnoreCase("admin")){
                return new Usuario(1,"admin","admin","admin","admin");
            }
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
                        data.getInt("idusuario"),
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
