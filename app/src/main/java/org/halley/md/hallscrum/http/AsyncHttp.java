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
import org.halley.md.hallscrum.Model.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

// Esta onda ya no sirve , ahora usa volley xD :v :'v


public class AsyncHttp extends AsyncTask<String,Integer,Usuario>{
    //TODO: DEFINICIoN DE RUTAS DEL API
    private static String URL_API="http://192.168.1.12:3000/";
    private static String LOGIN_API=URL_API+"auth/login";
    private static String USUARIO_API1=URL_API+"auth/register";
    private static String USUARIO_API=URL_API+"api/v1/usuario";
    //TODO: Llave para acceder al API
    private static String TOKEN_KEY="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyb2lkIiwiaWF0IjoxNDMyNzIyNTUwLCJleHAiOjE0MzQ0NTA1NTB9.FdURsguvFO8xKTXvJAS4i02KmsqC7dvVQiDr_fDJJC0";
    private Usuario usuario=null;
    @Override
    protected Usuario doInBackground(String ... params) {
        //Preparamos la consulta
        HttpClient httpClient=new DefaultHttpClient();
        HttpPost postLogin=new HttpPost(LOGIN_API);
        postLogin.setHeader("content-type","application/json");
        postLogin.setHeader("charset","utf-8");
        postLogin.setHeader("Authorization",TOKEN_KEY);
        //Construimos el objeto Usuario con los datos de nick y contrasena a validar en el webservice
        JSONObject data=new JSONObject();
        try {
            //INSERTO LOS DATOS A ENVIAR TODO: Puede generar el error de JSONException
            data.put("contrasena",params[1]);
            data.put("nick",params[0]);
            StringEntity entity=new StringEntity(data.toString());//Todo: Puede generar la excepcion UnsupportedEncodingException
            //TODO: se cargan los datos a enviar en la consulta
            postLogin.setEntity(entity);
            //TODO: Se ejecuta la consulta y se guardan los resultados obtenidos a cambio de ejecutar lo que queriamos
            HttpResponse resp=httpClient.execute(postLogin);//PUEDE GENERAR 2 EXCEPTCIONES TODO: ClientProtocolException Y IOException
            //Obtengo los datos y los guardo en un array del tipo JSON Todo: Ejemplo de formato: [{objeto:definicionArray}]
            JSONArray arrayDeDatos=new JSONArray(EntityUtils.toString(resp.getEntity()));
            data=null;
            for (int i=0;i<arrayDeDatos.length();i++){
                data=arrayDeDatos.getJSONObject(i);
                // String nombre,String apellido, String nickname, String contrasena
                usuario=new Usuario(data.getString("nombre"),data.getString("apellido")
                        ,data.getString("nickname"),data.getString("contrasena"));
                return usuario;
            }
        }catch (JSONException e){
            Log.e("JSONEXCEPTION-onLogin",""+e);
        }catch (UnsupportedEncodingException e){
            Log.e("ErrorAlEncodiarDatos",""+e);
        }catch (ClientProtocolException e){
            Log.e("ErrorAlEjecutarConsulta",""+e);
        }catch (IOException e){
            Log.e("ErrorAlTransportarDatos",""+e);
        }
        return usuario;
    }

    @Override
    protected void onPostExecute(Usuario usr) {
        super.onPostExecute(usr);
        if(usr!=null){
            this.usuario=usr;
        }
    }
    public Usuario getUsuario() {
        return usuario;
    }
}
