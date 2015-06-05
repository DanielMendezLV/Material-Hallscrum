package org.halley.md.hallscrum.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.halley.md.hallscrum.Model.TarjetRecycler;
import org.halley.md.hallscrum.R;
import org.w3c.dom.Text;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mendez Diaz on 03/06/2015.
 */
public class TarjetAdapter extends RecyclerView.Adapter<TarjetAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    List<TarjetRecycler> data = Collections.emptyList();

    public TarjetAdapter(Context context, List<TarjetRecycler> data){
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    //Un view holder describe un elemento que se encuentra en un recycler view
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Este view represenntna mi vistga del custom row
        View view =inflater.inflate(R.layout.custom_row, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TarjetRecycler current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.idIcon);

    }

    @Override
    public int getItemCount() {
       return data.size();
    }

    //Para crear un nuevo viewholder necesito que se cree una vista.
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.list_icon);
        }
    }
}
