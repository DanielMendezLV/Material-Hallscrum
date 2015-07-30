package org.halley.md.hallscrum.Activity.Update;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.halley.md.hallscrum.API.AddressAPI;
import org.halley.md.hallscrum.Activity.SearchActivity;
import org.halley.md.hallscrum.MainActivity;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.http.HallscrumRequests;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class EditTeamActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private TextView txtEditName;
    private TextView txtCodeTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        toolbar = (Toolbar)findViewById(R.id.toolbar);//}
        txtEditName = (TextView) findViewById(R.id.txtNewName);
        txtCodeTeam = (TextView) findViewById(R.id.txtcode_team);
        Bundle extra = getIntent().getExtras();
        txtEditName.setText(extra.getString("nameTeam"));
        txtCodeTeam.setText(extra.getString("codeTeam"));
        //Yo ya no quiero usar tu toolbar, por esto yo te envio el mio para que le des soporte
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_okedition);
        // if(id==android.R.id.home){
        // getSupportActionBar()
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_team, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id==android.R.id.home){
            Bundle extra = getIntent().getExtras();
            String nombre = extra.getString("nameTeam");
            String idTeam = Integer.toString(extra.getInt("idTeam"));

            if(VerificarEditar(nombre)){
                //This works!
                ProgressDialog progress = ProgressDialog.show(EditTeamActivity.this, "Actualizando", "Espere un momento", true);
                String nuevoNombre = txtEditName.getText().toString();
                HallscrumRequests hallscrumRequests = new HallscrumRequests();
                hallscrumRequests.editHallScrum(AddressAPI.URL_TEAMS,getMapEdit(nuevoNombre,idTeam));
                progress.dismiss();
                startActivity(new Intent(EditTeamActivity.this,MainActivity.class));
            }else{
                startActivity(new Intent(EditTeamActivity.this,MainActivity.class));
            }
        }


        return super.onOptionsItemSelected(item);
    }

    public Map<String, String> getMapEdit(String nombre, String id){
        Map<String, String> edit= new HashMap<String, String>();
        edit.put("nombre", nombre);
        edit.put("idequipo", id);
        return edit;
    }

    public boolean VerificarEditar(String nombre){
        String nuevoNombre = txtEditName.getText().toString();
        if(!nombre.equals(nuevoNombre)){

            return true;
        }
        return false;
    }

}
