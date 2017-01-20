package com.simplifiedlauncher.home;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.maurizio.simplifiedlauncher.R;

/**
 * Created by Carmine on 19/01/2017.
 */

public class GestioneMessaggi extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rubrica);
        String[] reqCols = new String[] { "_id", "body", "address", "read", "date", "type", "thread_id" };
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/sent"), reqCols, null, null, "date ASC");

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                }
                System.out.println("messaggio"+msgData);
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }

    }


}
