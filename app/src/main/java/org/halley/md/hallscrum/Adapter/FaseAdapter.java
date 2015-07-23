package org.halley.md.hallscrum.Adapter;

/**
 * Created by LUIS MENDEZ on 19/07/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.halley.md.hallscrum.Activity.Listed.ListFasesActivity;
import org.halley.md.hallscrum.Activity.Listed.ListMetaActivity;
import org.halley.md.hallscrum.Fragment.Fragment_Dialog_Fase;
import org.halley.md.hallscrum.Fragment.Fragment_Dialog_Project;
import org.halley.md.hallscrum.Model.Fase;
import org.halley.md.hallscrum.Model.Meta;
import org.halley.md.hallscrum.R;

import java.util.ArrayList;


public class FaseAdapter extends RecyclerView.Adapter<FaseAdapter.ViewHolderFaseAdapter> {

    private LayoutInflater inflater;
    private ArrayList<Fase> fases = new ArrayList<>();
    private Context context;

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
            Fragment_Dialog_Fase fragment_dialog_fase = new Fragment_Dialog_Fase();
            fragment_dialog_fase.createDialog(context, id);
        }
    }

}
