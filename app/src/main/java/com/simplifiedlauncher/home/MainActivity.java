package com.simplifiedlauncher.home;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maurizio.simplifiedlauncher.R;
import com.simplifiedlauncher.gallery.PhotoGalleryActivity;
import com.simplifiedlauncher.permissions.RuntimePermissionsActivity;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends RuntimePermissionsActivity {
    final int RUBRICA = 0;
    public static final String TAG = MainActivity.class.getName();
    Button telefono;
    Button rubrica;
    Button sms;
    Button chiamate_rapide;
    Button fotocamera;
    Button galleria;
    TextView orario;
    Intent i;
    LinearLayout container;
    ArrayList<Contatto> array_contatti;

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
        final Fotocamera f = new Fotocamera(this);
        galleria = (Button) findViewById(R.id.galleria);
        orario = (TextView) findViewById(R.id.orario);

        Thread myThread = null;

        Runnable myRunnableThread = new CountDownRunner();
        myThread = new Thread(myRunnableThread);
        myThread.start();

        array_contatti = new ArrayList<>();

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
                        i.putExtra("Rubrica", array_contatti);
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
                        i = new Intent(getApplicationContext(), RapidCall.class);
                        startActivity(i);
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
                        Intent i = new Intent(getApplicationContext(), PhotoGalleryActivity.class);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

        new CaricaRubrica().execute();
    }

    class CaricaRubrica extends AsyncTask<Integer, Integer, ArrayList<Contatto>> {
        String[] columns = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, columns, null, null, ContactsContract.Contacts.DISPLAY_NAME);

        int ColumeIndex_ID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int ColumeIndex_DISPLAY_NAME = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        int ColumeIndex_HAS_PHONE_NUMBER = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);


        @Override
        protected void onPreExecute() {
        }

        @Override
        protected ArrayList<Contatto> doInBackground(Integer... img_ids) {
            int i = 0;
            while (cursor.moveToNext()) {
                String id = cursor.getString(ColumeIndex_ID);
                String nome = cursor.getString(ColumeIndex_DISPLAY_NAME);
                String has_phone = cursor.getString(ColumeIndex_HAS_PHONE_NUMBER);
                String numero = "";

                if (!has_phone.endsWith("0")) {
                    numero = getPhoneNumber(id);
                    if (numero.substring(0, 1).equalsIgnoreCase("+"))
                        numero = numero.substring(3, numero.length());
                    Contatto contatto = new Contatto(nome, numero);
                    array_contatti.add(contatto);
                    i++;
                }
            }
            cursor.close();
            return array_contatti;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(ArrayList<Contatto> array_contatti) {
        }
    }

    public String getPhoneNumber(String id) {
        String number = "";
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        if (phones.getCount() > 0) {
            while (phones.moveToNext()) {
                number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }

        }

        phones.close();

        return number;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RUBRICA) {
            if (resultCode == RESULT_OK) {
                array_contatti = (ArrayList<Contatto>) data.getSerializableExtra("RubricaAggiornata");
            }
        }
    }

    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    orario = (TextView) findViewById(R.id.orario);
                    Date dt = new Date();
                    int hours = dt.getHours();
                    int minutes = dt.getMinutes();
                    String curTime = hours + ":" + minutes;
                    orario.setText(curTime);
                } catch (Exception e) {
                }
            }
        });
    }


    class CountDownRunner implements Runnable {
        // @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork();
                    Thread.sleep(1000); // Pause of 1 Second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }


}
