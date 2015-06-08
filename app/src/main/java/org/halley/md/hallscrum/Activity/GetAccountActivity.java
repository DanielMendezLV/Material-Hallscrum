package org.halley.md.hallscrum.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.halley.md.hallscrum.MainActivity;
import org.halley.md.hallscrum.Model.Usuario;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.http.UsuarioWS;

import java.util.concurrent.ExecutionException;


/**
 * Created by Mendez Diaz on 29/05/2015.
 */
public class GetAccountActivity extends Activity{
    private Button btnRegistrar;
    private TextView txtNickname;
    private TextView txtNombre;
    private TextView txtApellido;
    private TextView txtContrasena;
    //private Usuario userIO;


    public void getContextObjetcs(){
        txtContrasena=(TextView)(findViewById(R.id.txtContrasena));
        txtNombre=(TextView)(findViewById(R.id.txtNombre));
        txtNickname=(TextView)(findViewById(R.id.txtNickname));
        txtApellido=(TextView)(findViewById(R.id.txtApellido));
        txtContrasena=(TextView)(findViewById(R.id.txtContrasena));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account);
        btnRegistrar = (Button)(findViewById(R.id.btnRegistrar));
        getContextObjetcs();


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsuarioWS insertar = new UsuarioWS();
                Usuario userIO = new Usuario();
                try {
                    userIO=insertar.execute(txtNombre.getText().toString(),txtApellido.getText().toString(), txtNickname.getText().toString(), txtContrasena.getText().toString()).get();

                    if(userIO!=null){
                        Toast.makeText(getApplicationContext(), "Bienvenido: " + userIO.getNombre(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(GetAccountActivity.this,MainActivity.class));
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
