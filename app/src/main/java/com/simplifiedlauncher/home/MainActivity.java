package com.simplifiedlauncher.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.maurizio.simplifiedlauncher.R;

public class MainActivity extends AppCompatActivity {
    Button telefono;
    Button rubrica;
    Button sms;
    Button chiamate_rapide;
    Button fotocamera;
    Button galleria;
    Intent i;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container=(LinearLayout) findViewById(R.id.container);

        telefono= (Button) findViewById(R.id.telefono);
        rubrica = (Button) findViewById(R.id.rubrica);
        sms = (Button) findViewById(R.id.sms);
        chiamate_rapide = (Button) findViewById(R.id.chiamate_rapide);
        fotocamera = (Button) findViewById(R.id.camera);
        galleria = (Button) findViewById(R.id.galleria);

        telefono.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        telefono.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        telefono.setBackgroundColor(Color.WHITE);
                        i= new Intent(getApplicationContext(),Telefono.class);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

        sms.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        sms.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        sms.setBackgroundColor(Color.WHITE);
                        i= new Intent(getApplicationContext(),GestioneMessaggi.class);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

        rubrica.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        rubrica.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        rubrica.setBackgroundColor(Color.WHITE);
                        i= new Intent(getApplicationContext(),Rubrica.class);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

        chiamate_rapide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        chiamate_rapide.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        chiamate_rapide.setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });

        fotocamera.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        fotocamera.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        fotocamera.setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
        galleria.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        galleria.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        galleria.setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }


}
