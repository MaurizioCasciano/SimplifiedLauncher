package com.simplifiedlauncher.home;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;


import com.example.maurizio.simplifiedlauncher.R;

import java.util.ArrayList;
import java.util.Arrays;

public class RapidCall extends AppCompatActivity {
    private Button btnFirstContact;
    private Button btnSecondContact;
    private Button btnThirdContact;
    private Button btnFourthContact;
    private Button btnFifthContact;
    private Button btnSixthContact;
    private static final int FIRST_PICK_CONTACT_REQUEST = 0;
    private static final int SECOND_PICK_CONTACT_REQUEST = 1;
    private static final int THIRD_PICK_CONTACT_REQUEST = 2;
    private static final int FOURTH_PICK_CONTACT_REQUEST = 3;
    private static final int FIFTH_PICK_CONTACT_REQUEST = 4;
    private static final int SIXTH_PICK_CONTACT_REQUEST = 5;
    private static final String CHECK1 = "firstContact";
    private static final String CHECK2 = "secondContact";
    private static final String CHECK3 = "thirdContact";
    private static final String CHECK4 = "fourthContact";
    private static final String CHECK5 = "fifthContact";
    private static final String CHECK6 = "sixthContact";
    private static final Boolean INSERT = false;
    private static final Boolean CLICK = true;
    private static final String[] arrayOfCheck = {CHECK1, CHECK2, CHECK3, CHECK4, CHECK5, CHECK6};
    private static final int[] arrayRequestCode = {FIRST_PICK_CONTACT_REQUEST, SECOND_PICK_CONTACT_REQUEST, THIRD_PICK_CONTACT_REQUEST,
            FOURTH_PICK_CONTACT_REQUEST, FIFTH_PICK_CONTACT_REQUEST, SIXTH_PICK_CONTACT_REQUEST};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapid_call);
        btnFirstContact = (Button) findViewById(R.id.btnFirstContact);
        btnSecondContact = (Button) findViewById(R.id.btnSecondContact);
        btnThirdContact = (Button) findViewById(R.id.btnThirdContact);
        btnFourthContact = (Button) findViewById(R.id.btnFourthContact);
        btnFifthContact = (Button) findViewById(R.id.btnFifthContact);
        btnSixthContact = (Button) findViewById(R.id.btnSixthContact);
        checkExisting(FIRST_PICK_CONTACT_REQUEST, INSERT);
        checkExisting(SECOND_PICK_CONTACT_REQUEST, INSERT);
        checkExisting(THIRD_PICK_CONTACT_REQUEST, INSERT);
        checkExisting(FOURTH_PICK_CONTACT_REQUEST, INSERT);
        checkExisting(FIFTH_PICK_CONTACT_REQUEST, INSERT);
        checkExisting(SIXTH_PICK_CONTACT_REQUEST, INSERT);


        btnFirstContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExisting(FIRST_PICK_CONTACT_REQUEST, CLICK);
            }
        });
        btnSecondContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExisting(SECOND_PICK_CONTACT_REQUEST, CLICK);
            }
        });
        btnThirdContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExisting(THIRD_PICK_CONTACT_REQUEST, CLICK);
            }
        });
        btnFourthContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExisting(FOURTH_PICK_CONTACT_REQUEST, CLICK);
            }
        });
        btnFifthContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExisting(FIFTH_PICK_CONTACT_REQUEST, CLICK);
            }
        });
        btnSixthContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExisting(SIXTH_PICK_CONTACT_REQUEST, CLICK);
            }
        });
    }

    private void checkExisting(int requestCode, Boolean mode) {
        if (mode) {
            Log.d("SL", "checkExisting: ci sono dentro");
            SharedPreferences prefs = getSharedPreferences(arrayOfCheck[requestCode], MODE_PRIVATE);
            String restoredText = prefs.getString("name", null);
            if (restoredText != null) {
                String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
                int idName = prefs.getInt("idName", 0); //0 is the default value.
                //avvia chiamata
            } else {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, arrayRequestCode[requestCode]);

            }
        } else {
            SharedPreferences prefs = getSharedPreferences(arrayOfCheck[requestCode], MODE_PRIVATE);
            String restoredText = prefs.getString("name", null);
            if (restoredText != null) {
                Log.d("SL", "checkExisting: ci sono veramente dentro");
                String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
                switch (arrayOfCheck[requestCode]) {
                    case CHECK1:
                        btnFirstContact.setText(name);
                    case CHECK2:
                        btnSecondContact.setText(name);
                    case CHECK3:
                        btnThirdContact.setText(name);
                    case CHECK4:
                        btnFourthContact.setText(name);
                    case CHECK5:
                        btnFifthContact.setText(name);
                    case CHECK6:
                        btnSixthContact.setText(name);
                }
            } else {

            }
        }
    }

    private void saveData(String name, String phoneN, int requestCode) {
        SharedPreferences prefs = getSharedPreferences(arrayOfCheck[requestCode], MODE_PRIVATE);
        prefs.edit().putString("name", name).apply();
        prefs.edit().putInt("idName", Integer.parseInt(phoneN));
        checkExisting(requestCode, INSERT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (resultCode == Activity.RESULT_OK) {
            Cursor cursor = null;
            try {
                String phoneNo = null;
                String name = null;
                // getData() method will have the Content Uri of the selected contact
                Uri uri = data.getData();
                //Query the content uri
                cursor = getContentResolver().query(uri, null, null, null, null);
                cursor.moveToFirst();
                // column index of the phone number
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                // column index of the contact name
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                phoneNo = cursor.getString(phoneIndex);
                name = cursor.getString(nameIndex);
                // Set the value to the textviews
                saveData(name, phoneNo, requestCode);
                Log.d("SL", "onActivityResult: " + phoneNo + name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        checkExisting(requestCode, INSERT);
    }
}
