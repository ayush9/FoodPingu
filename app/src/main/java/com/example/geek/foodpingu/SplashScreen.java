package com.example.geek.foodpingu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
Intent i = new Intent(SplashScreen.this,Fbb.class);
                startActivity(i);
//FacebookLogIn frag = new FacebookLogIn();
            //    FragmentManager manager = getFragmentManager();
              //  FragmentTransaction transaction = manager.beginTransaction();
             //   transaction.add(R.id.container,frag,"abc");
               // transaction.commit();





                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}