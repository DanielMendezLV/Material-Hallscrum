package org.halley.md.hallscrum.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.halley.md.hallscrum.Fragment.Fragment_Dialog_Meta;
import org.halley.md.hallscrum.Fragment.Fragment_Dialog_Project;
import org.halley.md.hallscrum.Model.Meta;
import org.halley.md.hallscrum.R;

import java.util.ArrayList;

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


    }

    @Override
    public int getItemCount() {
        return metas.size();
    }

    class ViewHolderMetasAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView metaThumbnail;
        private TextView metaTitle;


        public ViewHolderMetasAdapter(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            metaThumbnail = (ImageView) itemView.findViewById(R.id.metaThumbnail);
            metaTitle = (TextView) itemView.findViewById(R.id.metaTitle);
        }

        @Override
        public void onClick(View view) {
            Fragment_Dialog_Meta fdmeta = new Fragment_Dialog_Meta();
            fdmeta.createDialog(context);
        }
    }

}
