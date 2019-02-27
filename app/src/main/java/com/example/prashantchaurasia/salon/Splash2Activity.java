package com.example.prashantchaurasia.salon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        Thread background = new Thread() {
            public void run() {

                try {
                    sleep(3 * 1000);
                    Intent i = new Intent(Splash2Activity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                } catch (Exception e) {


                }
            }
        };
        background.start();

    }
}
