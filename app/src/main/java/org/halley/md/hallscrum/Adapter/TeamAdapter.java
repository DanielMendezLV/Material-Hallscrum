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
import org.halley.md.hallscrum.Activity.Update.EditTeamActivity;
import org.halley.md.hallscrum.Model.Proyect;
import org.halley.md.hallscrum.Model.Team;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.http.HallscrumRequests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mendez Diaz on 16/07/2015.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolderTeamAdapter> {
    private LayoutInflater inflater;
    private ArrayList<Team> teams = new ArrayList<>();
    private Context context;


    public void delete(int position){
        teams.remove(position);
        notifyItemRemoved(position);
    }


    public TeamAdapter(Context context){
        this.context=context;
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
        holder.id = currentTeam.getIdEquipo();
    }


    @Override
    public int getItemCount() {
        return teams.size();
    }


    class ViewHolderTeamAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView teamTitle;
        private TextView teamKey;
        private ImageView teamThumbnail;
        private int id;

        public ViewHolderTeamAdapter(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            teamTitle = (TextView) itemView.findViewById(R.id.teamTitle);
            teamKey = (TextView) itemView.findViewById(R.id.teamCode);
            teamThumbnail = (ImageView) itemView.findViewById(R.id.teamThumbnail);
        }

        @Override
        public void onClick(View view) {
            String[] OPTIONS = {"Eliminar", "Actualizar"};
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
                        dell.put("idequipo", Integer.toString(id));
                        hallscrumRequests.addHallScrum(AddressAPI.URL_TEAM_DEL, dell);
                    }

                    if(i==1){
                        Intent intent = new Intent(context, EditTeamActivity.class);
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











