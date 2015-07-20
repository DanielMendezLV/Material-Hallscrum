package org.halley.md.hallscrum.Activity.Adds;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.halley.md.hallscrum.API.AddressAPI;
import org.halley.md.hallscrum.Activity.Listed.ListMetaActivity;
import org.halley.md.hallscrum.MainActivity;
import org.halley.md.hallscrum.Model.Team;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.db.DbManager;
import org.halley.md.hallscrum.http.AsyncTask.SppinerWS;
import org.halley.md.hallscrum.http.HallscrumRequests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AddProyectActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener{
    private Toolbar toolbar;
    private Button btnAgregarProyecto;
    private TextView txtNombreProyecto;
    private String idUsuario;
    private String idEquipo;
    private Spinner espEquipos;
    private SpinnerAdapter spn_teamAdap;
    private ArrayList<Team> listaSpinner = new ArrayList<Team>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //System.out.println("... entro??");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proyect);
        toolbar = (Toolbar)findViewById(R.id.toolbar_proyect);//
        btnAgregarProyecto = (Button)(findViewById(R.id.btn_add_proyect));
        txtNombreProyecto=(TextView)findViewById(R.id.txtNombreProyecto);
        //Spiners
        espEquipos = (Spinner)(findViewById(R.id.spinner_teams));
        espEquipos.setOnItemSelectedListener(this);

        SppinerWS llenarSpn = new SppinerWS();

        try{
            listaSpinner = llenarSpn.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(this, R.layout.support_simple_spinner_dropdown_item,listaSpinner);
        espEquipos.setAdapter(adapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //DbManager db = new DbManager(getApplicationContext());



        //System.out.println("... holo holo llego ");

        btnAgregarProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("llega al click?");
                //nombre equipo fecha
                int tmId = espEquipos.getSelectedItemPosition();
                spn_teamAdap = espEquipos.getAdapter();
                Team tms = (Team)spn_teamAdap.getItem(tmId);
                idEquipo = Integer.toString(tms.getIdEquipo());
                System.out.println(idEquipo);
                Team tm = (Team) ((Spinner) findViewById(R.id.spinner_teams)).getSelectedItem();
                System.out.println("el id equipo es " + idEquipo);
                HallscrumRequests hallscrumRequests = new HallscrumRequests();
                hallscrumRequests.addHallScrum(AddressAPI.URL_PROJECTS,getMapAgregar(txtNombreProyecto.getText().toString(),idEquipo));
            }
        });

    }

    public Map<String, String> getMapAgregar(String nombre, String id){
        Map<String, String> add= new HashMap<String, String>();
        add.put("nombre", nombre);
        add.put("idequipo", id);
        return add;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_proyect, menu);
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
            startActivity(new Intent(AddProyectActivity.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
