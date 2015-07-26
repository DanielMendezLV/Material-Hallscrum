package org.halley.md.hallscrum.Activity.Update;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.halley.md.hallscrum.MainActivity;
import org.halley.md.hallscrum.R;

public class EditProyectActivity extends ActionBarActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_proyect);
        toolbar = (Toolbar)findViewById(R.id.toolbar);//
        //Yo ya no quiero usar tu toolbar, por esto yo te envio el mio para que le des soporte
        setSupportActionBar(toolbar);
        // getSupportActionBar()
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_proyect, menu);
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
            startActivity(new Intent(EditProyectActivity.this,MainActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}
