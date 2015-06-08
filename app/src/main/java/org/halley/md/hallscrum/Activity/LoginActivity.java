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
import org.halley.md.hallscrum.http.HttpHandler;
import org.halley.md.hallscrum.http.LoginWS;

import java.util.concurrent.ExecutionException;


public class LoginActivity extends Activity {
    private Button btnLogin;
    private Button btnUnete;
    private HttpHandler handler;
    private String datos="";
    private TextView txtUsuario;
    private TextView txtContrasena;
    private Usuario logged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=(Button)(findViewById(R.id.btnLogin));
        btnUnete=(Button)(findViewById(R.id.btnUnete));

        handler=new HttpHandler();
        txtContrasena=(TextView)findViewById(R.id.txtContrasena);
        txtUsuario=(TextView)findViewById(R.id.txtUsuario);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginWS autenticar=new LoginWS();
                try {
                    logged=autenticar.execute(txtUsuario.getText().toString(),txtContrasena.getText().toString()).get();
                }catch (InterruptedException | ExecutionException e){
                    Log.e("ERROR-LOGINEXEC",""+e);
                }

                if(logged!=null){
                    Toast.makeText(getApplicationContext(),"Bienvenido: "+logged.getNombre(),Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(),"Verifique sus Credenciales ",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnUnete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,GetAccountActivity.class));
            }
        });


    }


}
