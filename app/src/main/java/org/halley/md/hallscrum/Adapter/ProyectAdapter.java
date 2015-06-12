package org.halley.md.hallscrum.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.halley.md.hallscrum.Model.Proyect;
import org.halley.md.hallscrum.Model.TarjetRecycler;
import org.halley.md.hallscrum.R;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mendez Diaz on 11/06/2015.
 */
public class ProyectAdapter extends RecyclerView.Adapter<ProyectAdapter.ViewHolderProyectAdapter> {
    private LayoutInflater inflater;
    private ArrayList<Proyect> proyects = new ArrayList<>();


    public ProyectAdapter(Context context){
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


    }

    @Override
    public int getItemCount() {
       return proyects.size();
    }

    static class ViewHolderProyectAdapter extends RecyclerView.ViewHolder{
        private ImageView proyectThumbnail;
        private TextView proyectTitle;
        private TextView proyectDate;


        public ViewHolderProyectAdapter(View itemView){
            super(itemView);
            proyectThumbnail = (ImageView) itemView.findViewById(R.id.proyectThumbnail);
            proyectTitle = (TextView) itemView.findViewById(R.id.proyectTitle);
            proyectDate = (TextView) itemView.findViewById(R.id.proyectDate);
        }
    }


}
