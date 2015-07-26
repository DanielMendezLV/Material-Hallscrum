package org.halley.md.hallscrum.Adapter;

/**
 * Created by LUIS MENDEZ on 19/07/2015.
 */

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
import org.halley.md.hallscrum.Activity.Listed.ListMetaActivity;
import org.halley.md.hallscrum.Fragment.Fragment_Dialog_Fase;
import org.halley.md.hallscrum.Fragment.Fragment_Dialog_Project;
import org.halley.md.hallscrum.Model.Fase;
import org.halley.md.hallscrum.Model.Meta;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.http.HallscrumRequests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FaseAdapter extends RecyclerView.Adapter<FaseAdapter.ViewHolderFaseAdapter> {

    private LayoutInflater inflater;
    private ArrayList<Fase> fases = new ArrayList<>();
    private Context context;

    public void delete(int position){
        fases.remove(position);
        notifyItemRemoved(position);
    }

    public FaseAdapter(Context context){
        this.context=context;
        inflater= LayoutInflater.from(context);

    }

    public void setFases(ArrayList<Fase> listFases){
        this.fases=listFases;
        notifyItemRangeChanged(0,listFases.size());
    }

    @Override
    public ViewHolderFaseAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.custom_fase, parent,false);
        ViewHolderFaseAdapter holder = new ViewHolderFaseAdapter(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderFaseAdapter holder, int position) {
        Fase currentFase = fases.get(position);
        holder.faseTitle.setText(currentFase.getNombre());
        holder.faseThumbnail.setImageResource(currentFase.getFoto());
        holder.id=currentFase.getIdFase();
    }

    @Override
    public int getItemCount() {
        return fases.size();
    }

    class ViewHolderFaseAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView faseThumbnail;
        private TextView faseTitle;
        private int id;

        public ViewHolderFaseAdapter(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            faseThumbnail = (ImageView) itemView.findViewById(R.id.faseThumbnail);
            faseTitle = (TextView) itemView.findViewById(R.id.faseTitle);
        }

        @Override
        public void onClick(View view) {

            String[] OPTIONS = {"Eliminar", "Actualizar", "Ver Metas"};

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
                        dell.put("idfase", Integer.toString(id));
                        hallscrumRequests.addHallScrum(AddressAPI.URL_FASES_DEL,dell);
                    }

                    if(i ==2){
                        Intent intent = new Intent(context, ListMetaActivity.class);
                        intent.putExtra("idfase", id);
                        //esto me tiene preocupado, pero por ahora lo dejare asi.
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

}
