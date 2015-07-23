package org.halley.md.hallscrum.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import org.halley.md.hallscrum.Activity.Listed.ListFasesActivity;
import org.halley.md.hallscrum.Activity.Listed.ListMetaActivity;

/**
 * Created by LUIS MENDEZ on 23/07/2015.
 */
public class Fragment_Dialog_Fase {

    private Context contex;
    String[] OPTIONS = {"Eliminar", "Actualizar", "Ver Metas"};


    public void createDialog(final Context context,final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);


        // Setting Dialog Title
        builder.setTitle("Selecciona una opcion:");
        builder.setItems(OPTIONS, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i ==2){
                    Intent intent = new Intent(context, ListMetaActivity.class);
                    intent.putExtra("idfase", id);
                    //esto me tiene preocupado, pero por ahora lo dejare así.
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//not recommend
                    // intent.putExtra("nombreproyecto",tv.getText().toString());
                    context.startActivity(intent);
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        // Showing Alert Message
        alertDialog.show();
    }

}
