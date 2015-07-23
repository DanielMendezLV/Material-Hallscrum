package org.halley.md.hallscrum.Activity.Adds;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.halley.md.hallscrum.API.AddressAPI;
import org.halley.md.hallscrum.Activity.Listed.ListFasesActivity;
import org.halley.md.hallscrum.Activity.Listed.ListMetaActivity;
import org.halley.md.hallscrum.MainActivity;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.http.HallscrumRequests;

import java.util.HashMap;
import java.util.Map;

public class AddMetaActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private Button btnAgregarMeta;
    private TextView txtNombreMeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meta);
        toolbar = (Toolbar)findViewById(R.id.toolbar_meta);//
        setSupportActionBar(toolbar);
        btnAgregarMeta = (Button)(findViewById(R.id.btn_add_meta));
        txtNombreMeta=(TextView)findViewById(R.id.txt_add_meta);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAgregarMeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("llega al click?");
                //nombre equipo fecha
                Bundle extras = getIntent().getExtras();
                String idFase = Integer.toString(extras.getInt("idfase"));
                HallscrumRequests hallscrumRequests = new HallscrumRequests();
                hallscrumRequests.addHallScrum(AddressAPI.URL_META_INSERT, getMapAgregar(txtNombreMeta.getText().toString(), idFase));
            }
        });

    }


    public Map<String, String> getMapAgregar(String nombre, String id){
        Map<String, String> add= new HashMap<String, String>();
        add.put("nombre", nombre);
        add.put("idfase", id);
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
            int idfase = extras.getInt("idfase");
            Intent intent = new Intent(AddMetaActivity.this, ListMetaActivity.class);
            intent.putExtra("idfase", idfase);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
