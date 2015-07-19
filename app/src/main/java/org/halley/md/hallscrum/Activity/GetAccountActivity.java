package org.halley.md.hallscrum.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.halley.md.hallscrum.Model.Usuario;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.http.RegistrarWS;

import java.util.concurrent.ExecutionException;


/**
 * Created by Mendez Diaz on 29/05/2015.
 */
public class GetAccountActivity extends Activity{
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtNickname;
    private EditText txtContrasena;
    private Button btnRegistrar;
    //private Usuario userIO;


    public void getContextObjetcs(){
        txtNombre=(EditText)(findViewById(R.id.txtNombre));
        txtApellido=(EditText)(findViewById(R.id.txtApellido));
        txtNickname=(EditText)(findViewById(R.id.txtNickname));
        txtContrasena=(EditText)(findViewById(R.id.txtContrasena));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account);
        getContextObjetcs();
        btnRegistrar = (Button)(findViewById(R.id.btnRegistrar));
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegistrarWS insertar = new RegistrarWS();
               Usuario userIO = new Usuario();
                try {
                    userIO=insertar.execute(txtNombre.getText().toString(),txtApellido.getText().toString(), txtNickname.getText().toString(), txtContrasena.getText().toString()).get();

                    if(userIO!=null){

                        Toast.makeText(getApplicationContext(), "Ahora Puedes inicar: " + userIO.getNombre(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(GetAccountActivity.this,LoginActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Verifique sus Credenciales ",Toast.LENGTH_LONG).show();
                    }
                }catch (InterruptedException | ExecutionException e){
                    Log.e("ERROR-INSERT", "" + e);
                }

            }
        });


    }



}
