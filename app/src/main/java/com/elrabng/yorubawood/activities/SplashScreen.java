package com.elrabng.yorubawood.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.elrabng.yorubawood.R;

public class SplashScreen extends AppCompatActivity{
    private static int SPLASH_TIME_OUT = 2000;
    boolean running = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                while(!running) {
                    if (!NetworkUtils.isNetworkAvailable(getBaseContext())) {
                        Toast.makeText(SplashScreen.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        running = false;
                    } else {
                        running = true;
                    }

                }
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
            }
        },SPLASH_TIME_OUT);
    }
    }
