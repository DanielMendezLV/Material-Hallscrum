package org.halley.md.hallscrum.Splash;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import org.halley.md.hallscrum.R;
import org.halley.md.hallscrum.Activity.LoginActivity;
import java.util.Timer;
import java.util.TimerTask;



public class Splash extends Activity {
    private static final long SPLASH_SCREEN_DELAY = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread logoTimer = new Thread() {
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer < 5000){
                        sleep(100);
                        logoTimer = logoTimer +100;
                    };
                    Intent mainIntent=new Intent().setClass(Splash.this,LoginActivity.class);
                    startActivity(mainIntent);
                }

                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                finally{
                    finish();
                }
            }
        };

        logoTimer.start();

    }
}
