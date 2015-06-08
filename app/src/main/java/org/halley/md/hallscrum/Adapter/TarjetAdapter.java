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
    private Context context;
    private ClickListener clickListener;

    public TarjetAdapter(Context context, List<TarjetRecycler> data){
        inflater= LayoutInflater.from(context);
        this.data=data;
        this.context=context;
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
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
           // itemView.setOnClickListener(this);
            itemView.setOnClickListener(this);
            title= (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.list_icon);
        }


        @Override
        public void onClick(View v) {
            if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
            }

        }


    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener= clickListener;
    }

    public interface ClickListener{
        public void itemClicked(View view,int position);
    }


}
