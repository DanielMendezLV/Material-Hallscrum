package org.halley.md.hallscrum.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.halley.md.hallscrum.API.AddressAPI;
import org.halley.md.hallscrum.Activity.Listed.ListFasesActivity;
import org.halley.md.hallscrum.Activity.Update.EditProyectActivity;
import org.halley.md.hallscrum.Fragment.Fragment_Dialog_Project;
import org.halley.md.hallscrum.Model.Proyect;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.http.HallscrumRequests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mendez Diaz on 11/06/2015.
 */
public class ProyectAdapter extends RecyclerView.Adapter<ProyectAdapter.ViewHolderProyectAdapter> {
    private LayoutInflater inflater;
    private ArrayList<Proyect> proyects = new ArrayList<>();
    private Context context;


    public void delete(int position){
         proyects.remove(position);
         notifyItemRemoved(position);
    }

    public ProyectAdapter(Context context){
        this.context=context;
        inflater= LayoutInflater.from(context);

    }

    public void setProyects(ArrayList<Proyect> listProyects){
        this.proyects=listProyects;
        notifyItemRangeChanged(0,listProyects.size());
    }

    @Override
    public ViewHolderProyectAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.custom_proyect, parent,false);
        ViewHolderProyectAdapter holder = new ViewHolderProyectAdapter(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderProyectAdapter holder, int position) {
        Proyect currentProyect = proyects.get(position);
        holder.proyectTitle.setText(currentProyect.getNombre());
        holder.proyectDate.setText(currentProyect.getFechaCreacion());
        holder.proyectThumbnail.setImageResource(currentProyect.getFoto());
        holder.id = currentProyect.getIdProyecto();
    }

    @Override
    public int getItemCount() {
       return proyects.size();
    }

    class ViewHolderProyectAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        //implements View.OnClickListener
        private ImageView proyectThumbnail;
        private TextView proyectTitle;
        private TextView proyectDate;
        private int id;

        public ViewHolderProyectAdapter(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            //itemView.setOnClickListener(proyectThumbnail);
            proyectThumbnail = (ImageView) itemView.findViewById(R.id.proyectThumbnail);
            proyectTitle = (TextView) itemView.findViewById(R.id.proyectTitle);
            proyectDate = (TextView) itemView.findViewById(R.id.proyectDate);
        }



        @Override
        public void onClick(View view) {

            String[] OPTIONS = {"Eliminar", "Actualizar", "Ver Fases"};
            AlertDialog.Builder builder = new AlertDialog.Builder(context);


            // Setting Dialog Title
            builder.setTitle("Selecciona una opcion:");
            builder.setItems(OPTIONS, new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(i==0){
                        delete(getPosition());
                        HallscrumRequests hallscrumRequests = new HallscrumRequests();
                        Map<String, String> dell= new HashMap<String, String>();
                        dell.put("idproyecto", Integer.toString(id));
                        hallscrumRequests.addHallScrum(AddressAPI.URL_PROJECTS_DEL, dell);
                    }

                    if(i==1){
                        Intent intent = new Intent(context, EditProyectActivity.class);
                        context.startActivity(intent);
                    }

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



}
