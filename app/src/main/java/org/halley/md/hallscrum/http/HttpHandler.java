package org.halley.md.hallscrum.http;

import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import halley.mendez.org.hallscrum.model.Usuario;

/**
 * Created by retana on 22/05/2015.
 */
public class HttpHandler {
    //http://192.168.1.12:3000
    private final String API_URL = "http://192.168.1.12:3000/";
    private final String USUARIOS_URL = "http://192.168.1.12:3000/api/v1/usuario/";

    private InputStream getInputStream(String uri) {
        try {
            URL jsonUrl = new URL(uri);
            return jsonUrl.openConnection().getInputStream();
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getData(String uri) {
        BufferedReader reader = null;
        try {
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(this.getInputStream(uri)));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<Usuario> onAutenticar(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JSONArray array=null;
        List<Usuario> listaUsuario;
        try{
            array=new JSONArray(this.getData(USUARIOS_URL));
            listaUsuario=new ArrayList<>();
        }catch (Exception e){
           Log.e("OnAutenticar1_:",""+e);
            return null;
        }
        for (int i=0;i<array.length();i++){
            JSONObject obj=null;
            try{
                Log.e("RESULTADO: ",obj.getString("nombre"));
                obj=array.getJSONObject(i);
                Usuario usr=new Usuario(obj.getInt("idUsuario"),obj.getString("nombre"),obj.getString("apellido")
                ,obj.getString("nickname"),obj.getString("contrasena"));
                listaUsuario.add(usr);
            }catch(Exception e){
                Log.e("OnAutenticar: ",""+e);
                return null;
            }
        }
        return listaUsuario;
    }
    public Usuario onPostAutenticar(String... parametros) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(parametros[0]);//URL A ENVIAR

            //FORMATEO PARAMETROS
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("nickname", parametros[1]));
            params.add(new BasicNameValuePair("contrasena", parametros[2]));

            //ENVIO PARAMETROS
            httppost.setEntity(new UrlEncodedFormEntity(params));

            //EJECUTO LA PETICION
            HttpResponse resp = httpclient.execute(httppost);
            //OBTENGO LOS RESULTADOS
            HttpEntity ent = resp.getEntity();
            //LOS OBTENGO EN FORMATO ARRAY DE JSON
            JSONArray array=new JSONArray(EntityUtils.toString(ent));
            Log.e("PRUEBA: ",""+array.length());
            for (int i=0;i<array.length();i++){
                JSONObject obj=null;
                try{
                    obj=array.getJSONObject(i);
                    Log.e("RESULTADO: ",obj.getString("nombre"));
                    Usuario usr=new Usuario(obj.getInt("idusuario"),obj.getString("nombre"),obj.getString("apellido")
                            ,obj.getString("nickname"),obj.getString("contrasena"));
                    return usr;
                }catch(Exception e){
                    Log.e("OnAutenticar: ",""+e);
                    return null;
                }
            }
            //String text = EntityUtils.toString(ent);

            return null;
        } catch (Exception e) {
            Log.e("OnPost", "" + e);
            Log.e("OnPost", "##################");

            return null;
        }
    }

    public String pruebaConexion(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            URL url = new URL(USUARIOS_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = null;
            try {
                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                return sb.toString();

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        }catch(MalformedURLException e){
            Log.e("PruebaURL: ",""+e);
        }catch(IOException e){
            Log.e("PruebaIO: ",""+e);
        }
        return "";
    }
}
