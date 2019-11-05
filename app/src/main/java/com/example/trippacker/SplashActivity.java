package com.example.trippacker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Timer for splash screen
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //close screen after fire
                finish();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        };

        // instantiating timer
        Timer opening = new Timer();

        opening.schedule(task, 5000);
    }
}
