package org.halley.md.hallscrum.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import org.halley.md.hallscrum.Activity.Listed.ListMetaActivity;

/**
 * Created by LUIS MENDEZ on 23/07/2015.
 */
public class Fragment_Dialog_Meta {

    private Context contex;
    String[] OPTIONS = {"Eliminar", "Actualizar"};

    public void createDialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);


        // Setting Dialog Title
        builder.setTitle("Selecciona una opcion:");
        builder.setItems(OPTIONS, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        // Showing Alert Message
        alertDialog.show();
    }

}
