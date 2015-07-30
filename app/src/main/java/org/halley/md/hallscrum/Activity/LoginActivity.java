package org.halley.md.hallscrum.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.halley.md.hallscrum.MainActivity;
import org.halley.md.hallscrum.Model.Usuario;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.Session.UserSession;
import org.halley.md.hallscrum.db.DbManager;
import org.halley.md.hallscrum.http.LoginWS;

import java.util.concurrent.ExecutionException;


public class LoginActivity extends Activity {
    private Button btnLogin;
    private Button btnUnete;
    private String datos="";
    private TextView txtUsuario;
    private TextView txtContrasena;
    private TextView txtDoYouNeedAccount;
    private Usuario logged;
    private UserSession session;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindDrawables(findViewById(R.id.drawer_layout));
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
        session = new UserSession(getApplicationContext());
        if(!session.onlyCheck()){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            startActivity(i);
        }

        setContentView(R.layout.activity_login);
        btnLogin=(Button)(findViewById(R.id.btnLogin));
        txtDoYouNeedAccount=(TextView)findViewById(R.id.txtWithoutAccount);
        txtContrasena=(TextView)findViewById(R.id.txtContrasena);
        txtUsuario=(TextView)findViewById(R.id.txtUsuario);
        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isUserLoggedIn(), Toast.LENGTH_LONG).show();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginWS autenticar=new LoginWS();
                try {
                   // final ProgressDialog progressDialog = ProgressDialog.show(getA(), "Espere por favor", "Estamos verificando sus datos");
                    logged=autenticar.execute(txtUsuario.getText().toString(),txtContrasena.getText().toString()).get();
                   // progressDialog.cancel();
                }catch (InterruptedException | ExecutionException e){
                    Log.e("ERROR-LOGINEXEC",""+e);
                }

                if(logged!=null){
                    Toast.makeText(getApplicationContext(),"Bienvenido: "+logged.getNombre(),Toast.LENGTH_LONG).show();
                    session.createUserLoginSession(logged.getNombre(), logged.getNickname(), Integer.toString(logged.getIdUsuario()));
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                    //DbManager dbManager = new DbManager(getApplicationContext());
                    //dbManager.insertarUsuario(Integer.toString(logged.getIdUsuario()), logged.getNombre(),logged.getApellido(), logged.getNickname(), logged.getContrasena());

                }else{
                    Toast.makeText(getApplicationContext(),"Verifique sus Credenciales ",Toast.LENGTH_LONG).show();
                }

            }
        });

        txtDoYouNeedAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, GetAccountActivity.class));
            }
        });


    }


}
