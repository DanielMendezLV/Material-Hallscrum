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
import android.widget.TextView;
import android.widget.Toast;

import org.halley.md.hallscrum.API.AddressAPI;
import org.halley.md.hallscrum.MainActivity;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.Session.UserSession;
import org.halley.md.hallscrum.db.DbManager;
import org.halley.md.hallscrum.http.HallscrumRequests;

import java.util.HashMap;
import java.util.Map;

public class AddTeamActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private Button btnAgregarTeam;
    private TextView txtNombreTeam;
    private String idUsuario;
    private UserSession session;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindDrawables(findViewById(R.id.add_team_act));
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__team);
        toolbar = (Toolbar)findViewById(R.id.toolbar_team);
        btnAgregarTeam = (Button)(findViewById(R.id.btn_add_team));
        txtNombreTeam=(TextView)findViewById(R.id.txt_add_team);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new UserSession(getApplicationContext());
        // Check user login (this is the important point)
        // If User is not logged in , This will redirect user to LoginActivity
        // and finish current activity from activity stack.
        if(session.checkLogin())
            finish();

        btnAgregarTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("llega al click?");
                ProgressDialog progress = ProgressDialog.show(AddTeamActivity.this, "Agregando", "Espere un momento", true);
                HashMap<String, String> user = session.getUserDetails();
                String id = user.get(UserSession.KEY_ID);
                HallscrumRequests hallscrumRequests = new HallscrumRequests();
                hallscrumRequests.addHallScrum(AddressAPI.URL_TEAMS, getMapAgregar(txtNombreTeam.getText().toString(), id));
                progress.dismiss();
                startActivity(new Intent(AddTeamActivity.this, MainActivity.class));
            }
        });

    }

    public Map<String, String> getMapAgregar(String nombre, String id){
        Map<String, String> add= new HashMap<String, String>();
        add.put("nombre", nombre);
        add.put("id", id);
        return add;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_team, menu);
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
            startActivity(new Intent(AddTeamActivity.this,MainActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}
