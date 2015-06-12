package org.halley.md.hallscrum.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Mendez Diaz on 10/06/2015.
 */
public class DbManager {

    //Tablas
    public static final String TABLE_USUARIO = "usuario";

    //Atributos
    public static final String CN_ID = "_id";
    public static final String CN_NAME="nombre";
    public static final String CN_LASTNAME="apellido";
    public static final String CN_NICKNAME="nickname";
    public static final String CN_PASSWORD="contrasena";


    public static final String TEXT_NOT_NULL = " text not null,";

    //Secuencias
    public static final String CREATE = "CREATE TABLE ";
    public static final String CREATE_TABLE_USUARIO =   CREATE+TABLE_USUARIO+"("+
            CN_ID+" integer primary key autoincrement,"+
            CN_NAME + TEXT_NOT_NULL +
            CN_LASTNAME+ TEXT_NOT_NULL+
            CN_NICKNAME+TEXT_NOT_NULL+
            CN_PASSWORD+TEXT_NOT_NULL+");";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DbManager(Context context){
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }


    public ContentValues generarContentUsuario(String nombre, String apellido, String nickname, String password){
        ContentValues values = new ContentValues();
        values.put(CN_NAME,nombre);
        values.put(CN_LASTNAME,apellido);
        values.put(CN_NICKNAME,nickname);
        values.put(CN_PASSWORD,password);
        return values;
    }

    public void insertarUsuario(String nombre, String apellido, String nickname, String password){
        db.insert(TABLE_USUARIO, null, generarContentUsuario(nombre, apellido, nickname, password));
    }

    public void eliminar(String id){

        db.delete(TABLE_USUARIO, CN_ID + "=?", new String[]{id});
    }

    public void updateUsuario(String id,String nombre, String apellido, String nickname, String password ){
        db.update(TABLE_USUARIO, generarContentUsuario(nombre,apellido,nickname,password), CN_ID+"=?", new String[]{id});
    }
}
