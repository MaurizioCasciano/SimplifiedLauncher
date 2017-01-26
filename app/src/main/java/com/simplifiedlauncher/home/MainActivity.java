package com.simplifiedlauncher.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.maurizio.simplifiedlauncher.R;
import com.simplifiedlauncher.gallery.PhotoGalleryActivity;
import com.simplifiedlauncher.permissions.RuntimePermissionsActivity;

public class MainActivity extends RuntimePermissionsActivity {
    public static final String TAG = MainActivity.class.getName();
    Button telefono;
    Button rubrica;
    Button sms;
    Button chiamate_rapide;
    Button fotocamera;
    Button galleria;
    Intent i;
    LinearLayout container;
    public static String[] permissions = new String[]{
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (super.checkPermission(permissions)) {
            Log.d(TAG, "onCreate: permissions granted");
        } else {
            Log.d(TAG, "onCreate: permissions denied");
            super.requestAppPermissions(permissions, R.id.activity_main, 1236);
        }


        container = (LinearLayout) findViewById(R.id.container);

        telefono = (Button) findViewById(R.id.telefono);
        rubrica = (Button) findViewById(R.id.rubrica);
        sms = (Button) findViewById(R.id.sms);
        chiamate_rapide = (Button) findViewById(R.id.chiamate_rapide);
        fotocamera = (Button) findViewById(R.id.camera);
        final Fotocamera f =new Fotocamera(this);
        galleria = (Button) findViewById(R.id.galleria);

        telefono.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        telefono.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        telefono.setBackgroundColor(Color.WHITE);
                        i = new Intent(getApplicationContext(), Telefono.class);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

        sms.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        sms.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        sms.setBackgroundColor(Color.WHITE);
                        i = new Intent(getApplicationContext(), GestioneMessaggi.class);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

        rubrica.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        rubrica.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        rubrica.setBackgroundColor(Color.WHITE);
                        i = new Intent(getApplicationContext(), Rubrica.class);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

        chiamate_rapide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
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
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        fotocamera.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        fotocamera.setBackgroundColor(Color.WHITE);
                        f.dispatchTakePictureIntent();
                        break;
                }
                return true;
            }
        });
        galleria.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        galleria.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        galleria.setBackgroundColor(Color.WHITE);
                        Intent i=new Intent(getApplicationContext(), PhotoGalleryActivity.class);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }


}