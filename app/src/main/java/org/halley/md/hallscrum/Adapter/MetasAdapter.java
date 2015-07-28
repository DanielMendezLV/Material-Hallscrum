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
import org.halley.md.hallscrum.Activity.Update.EditFaseActivity;
import org.halley.md.hallscrum.Activity.Update.EditMetaActivity;
import org.halley.md.hallscrum.Fragment.Fragment_Dialog_Meta;
import org.halley.md.hallscrum.Fragment.Fragment_Dialog_Project;
import org.halley.md.hallscrum.Model.Meta;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.http.HallscrumRequests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by U on 14/07/2015.
 */
public class MetasAdapter extends RecyclerView.Adapter<MetasAdapter.ViewHolderMetasAdapter> {
    private LayoutInflater inflater;
    private ArrayList<Meta> metas = new ArrayList<>();
    private Context context;

    public MetasAdapter(Context context){
        this.context=context;
        inflater= LayoutInflater.from(context);

    }

    public void delete(int position){
        metas.remove(position);
        notifyItemRemoved(position);
    }


    public void setMetas(ArrayList<Meta> listMetas){
        this.metas=listMetas;
        notifyItemRangeChanged(0,listMetas.size());
    }

    @Override
    public ViewHolderMetasAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.custom_meta, parent,false);
        ViewHolderMetasAdapter holder = new ViewHolderMetasAdapter(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMetasAdapter holder, int position) {
        Meta currentMeta = metas.get(position);
        holder.metaTitle.setText(currentMeta.getDescripcion());
        holder.metaThumbnail.setImageResource(currentMeta.getFoto());
        holder.id = currentMeta.getIdMeta();
        if(currentMeta.getEstado()){
            holder.estado="Finalizado";
        }else{
            holder.estado="Pendiente";
        }

    }

    @Override
    public int getItemCount() {
        return metas.size();
    }

    class ViewHolderMetasAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView metaThumbnail;
        private TextView metaTitle;
        private String estado;
        private int id;

        public ViewHolderMetasAdapter(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            metaThumbnail = (ImageView) itemView.findViewById(R.id.metaThumbnail);
            metaTitle = (TextView) itemView.findViewById(R.id.metaTitle);
        }

        @Override
        public void onClick(View view) {

            String[] OPTIONS = {"Eliminar", "Actualizar"};

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            // Setting Dialog Title
            builder.setTitle("Selecciona una opcion:");
            builder.setItems(OPTIONS, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 0) {
                        delete(getPosition());
                        HallscrumRequests hallscrumRequests = new HallscrumRequests();
                        Map<String, String> dell = new HashMap<String, String>();
                        dell.put("idmeta", Integer.toString(id));
                        hallscrumRequests.addHallScrum(AddressAPI.URL_META_DEL, dell);
                    }
                    if (i == 1) {
                        Intent intent = new Intent(context, EditMetaActivity.class);
                        String titleMeta = ((TextView) metaTitle).getText().toString();
                        intent.putExtra("titleMeta", titleMeta);
                        intent.putExtra("idMeta", id);
                        intent.putExtra("estadoMeta", estado);
                        context.startActivity(intent);
                    }
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }

}
