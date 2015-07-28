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
import org.halley.md.hallscrum.MainActivity;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.http.HallscrumRequests;

import java.util.HashMap;
import java.util.Map;

public class EditFaseActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private TextView txtEditName;
    private TextView txtDateFase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fase);
        toolbar = (Toolbar)findViewById(R.id.toolbar);//
        txtEditName = (TextView) findViewById(R.id.txtNewName);
        txtDateFase = (TextView) findViewById(R.id.txtdate_fase);
        Bundle extra = getIntent().getExtras();
        txtEditName.setText(extra.getString("titleFase"));
        txtDateFase.setText(extra.getString("titleDate"));

        //Yo ya no quiero usar tu toolbar, por esto yo te envio el mio para que le des soporte
        setSupportActionBar(toolbar);
        // getSupportActionBar()
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_okedition);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_fase, menu);
        return true;
    }


    public boolean VerificarEditar(String nombre){
        String nuevoNombre = txtEditName.getText().toString();
        if(!nombre.equals(nuevoNombre)){

            return true;
        }
        return false;
    }


    public Map<String, String> getMapEdit(String nombre, String id){
        Map<String, String> edit= new HashMap<String, String>();
        edit.put("nombre", nombre);
        edit.put("idfase", id);
        return edit;
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
            String nombre = extra.getString("titleFase");
            String idFase = Integer.toString(extra.getInt("idFase"));

            if(VerificarEditar(nombre)){
                //This works!
                ProgressDialog progress = ProgressDialog.show(EditFaseActivity.this, "Actualizando", "Espere un momento", true);
                String nuevoNombre = txtEditName.getText().toString();
                HallscrumRequests hallscrumRequests = new HallscrumRequests();
                hallscrumRequests.editHallScrum(AddressAPI.URL_FASES,getMapEdit(nuevoNombre,idFase));
                progress.dismiss();
                startActivity(new Intent(EditFaseActivity.this,MainActivity.class));
            }else{
                startActivity(new Intent(EditFaseActivity.this,MainActivity.class));
            }
        }


        return super.onOptionsItemSelected(item);
    }
}
