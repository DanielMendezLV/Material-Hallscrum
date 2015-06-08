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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);

        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent=new Intent().setClass(Splash.this,LoginActivity.class);
                startActivity(mainIntent);

            }
        };
        Timer timer=new Timer();
        timer.schedule(task,SPLASH_SCREEN_DELAY);
    }
}
