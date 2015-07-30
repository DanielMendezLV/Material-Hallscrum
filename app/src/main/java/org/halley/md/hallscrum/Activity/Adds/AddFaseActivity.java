package org.halley.md.hallscrum.Activity.Adds;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.halley.md.hallscrum.API.AddressAPI;
import org.halley.md.hallscrum.Activity.Listed.ListFasesActivity;
import org.halley.md.hallscrum.Activity.Listed.ListMetaActivity;
import org.halley.md.hallscrum.MainActivity;
import org.halley.md.hallscrum.Model.Team;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.http.HallscrumRequests;

import java.util.HashMap;
import java.util.Map;

public class AddFaseActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private Button btnAgregarFase;
    private TextView txtNombreFase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fase);
        toolbar = (Toolbar)findViewById(R.id.toolbar_addfase);//
        setSupportActionBar(toolbar);

        btnAgregarFase = (Button)(findViewById(R.id.btn_add_fase));
        txtNombreFase=(TextView)findViewById(R.id.txt_add_fase);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAgregarFase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("llega al click?");
                //nombre equipo fecha
                ProgressDialog progress = ProgressDialog.show(AddFaseActivity.this, "Agregando", "Espere un momento", true);

                Bundle extras = getIntent().getExtras();
                String idProyecto = Integer.toString(extras.getInt("idproyecto"));
                HallscrumRequests hallscrumRequests = new HallscrumRequests();
                hallscrumRequests.addHallScrum(AddressAPI.URL_FASES_INSERT, getMapAgregar(txtNombreFase.getText().toString(), idProyecto));
                progress.dismiss();
                Intent intent = new Intent(AddFaseActivity.this, ListFasesActivity.class);
                intent.putExtra("idproyecto",extras.getInt("idproyecto"));
                startActivity(intent);

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindDrawables(findViewById(R.id.add_fase_act));
        System.gc();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }


    public Map<String, String> getMapAgregar(String nombre, String id){
        Map<String, String> add= new HashMap<String, String>();
        add.put("nombre", nombre);
        add.put("idproyecto", id);
        return add;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_fase, menu);
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
            Bundle extras = getIntent().getExtras();
            int idProyecto = extras.getInt("idproyecto");
            Intent intent = new Intent(AddFaseActivity.this,ListFasesActivity.class);
            intent.putExtra("idproyecto", idProyecto);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
