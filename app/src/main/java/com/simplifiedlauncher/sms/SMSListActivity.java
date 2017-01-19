package com.simplifiedlauncher.sms;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.maurizio.simplifiedlauncher.R;

import java.util.Date;

public class SMSListActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ASK_PERMISSIONS = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smslist);

        if (ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
            this.getAllSms(this.getApplicationContext());
        } else {
            ActivityCompat.requestPermissions(SMSListActivity.this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
            this.getAllSms(this.getApplicationContext());
        }
    }

    public void getAllSms(Context context) {

        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
        int totalSMS = 0;

        if (cursor != null) {
            totalSMS = cursor.getCount();
            Log.i("SMS", totalSMS + "");

            while (cursor.moveToNext()) {
                String smsDate = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                String body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));
                Date dateFormat = new Date(Long.valueOf(smsDate));
                int type = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.TYPE)));

                SMS sms = new SMS(dateFormat, number, body, type);
                Log.i("SMS", sms.toString());
            }
        } else {
            Toast.makeText(this, "No message to show!", Toast.LENGTH_SHORT).show();
        }
    }
}
