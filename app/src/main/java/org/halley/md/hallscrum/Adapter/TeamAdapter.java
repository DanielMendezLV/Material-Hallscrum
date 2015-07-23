package org.halley.md.hallscrum.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.halley.md.hallscrum.Model.Proyect;
import org.halley.md.hallscrum.Model.Team;
import org.halley.md.hallscrum.R;

import java.util.ArrayList;

/**
 * Created by Mendez Diaz on 16/07/2015.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolderTeamAdapter> {
    private LayoutInflater inflater;
    private ArrayList<Team> teams = new ArrayList<>();

    public TeamAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    public void setTeams(ArrayList<Team> listTeams){
        this.teams=listTeams;
        notifyItemRangeChanged(0,listTeams.size());
    }

    @Override
    public ViewHolderTeamAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.custom_team, parent,false);
        ViewHolderTeamAdapter holder = new ViewHolderTeamAdapter(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolderTeamAdapter holder, int position) {
        Team currentTeam = teams.get(position);
        holder.teamTitle.setText(currentTeam.getNombre());
        holder.teamKey.setText(currentTeam.getKey());
        holder.teamThumbnail.setImageResource(currentTeam.getFoto());
    }


    @Override
    public int getItemCount() {
        return teams.size();
    }


    static class ViewHolderTeamAdapter extends RecyclerView.ViewHolder{
        private TextView teamTitle;
        private TextView teamKey;
        private ImageView teamThumbnail;

        public ViewHolderTeamAdapter(View itemView){
            super(itemView);
            teamTitle = (TextView) itemView.findViewById(R.id.teamTitle);
            teamKey = (TextView) itemView.findViewById(R.id.teamCode);
            teamThumbnail = (ImageView) itemView.findViewById(R.id.teamThumbnail);
        }
    }
}











