package org.halley.md.hallscrum.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.halley.md.hallscrum.Activity.Listed.ListFasesActivity;
import org.halley.md.hallscrum.R;

/**
 * Created by LUIS MENDEZ on 22/07/2015.
 */
public class Fragment_Dialog_Project {
    private Context contex;
    String[] OPTIONS = {"Eliminar", "Actualizar", "Ver Fases"};


    public void createDialog(final Context context,final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);


        // Setting Dialog Title
        builder.setTitle("Selecciona una opcion:");
        builder.setItems(OPTIONS, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i ==2){
                    Intent intent = new Intent(context, ListFasesActivity.class);
                    intent.putExtra("idproyecto", id);
                    context.startActivity(intent);
                }

            }
        });

        AlertDialog alertDialog = builder.create();
        // Showing Alert Message
        alertDialog.show();
    }

}
