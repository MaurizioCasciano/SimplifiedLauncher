package com.simplifiedlauncher.home;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.maurizio.simplifiedlauncher.R;

import java.util.ArrayList;

/**
 * Created by Carmine on 19/01/2017.
 */

public class FormInserisciContatto extends AppCompatActivity {

    final int REQUEST_CODE_AGGIUNGI_CONTATTO=0;
    EditText edit_nome;
    EditText edit_numero;
    Intent activity_padre,aggiungiContatto;
    String nome;
    String numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_inserisci_contatto);

        edit_nome = (EditText) findViewById(R.id.nome);
        edit_numero = (EditText) findViewById(R.id.numero);

        nome="";
        numero="";

        activity_padre=getIntent();

    }

    public void aggiungi(View v){
        if (!edit_nome.getText().toString().matches("") && !edit_numero.getText().toString().matches("")) {
            nome = edit_nome.getText().toString();
            numero = edit_numero.getText().toString();

            ArrayList<ContentProviderOperation> ops;
            ops = new ArrayList<ContentProviderOperation>();
            int rawContactInsertIndex = ops.size();

            ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());

            //Phone Number
            ops.add(ContentProviderOperation
                    .newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                            rawContactInsertIndex)
                    .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, numero)
                    .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, "1").build());

            //Display name/Contact name
            ops.add(ContentProviderOperation
                    .newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID,
                            rawContactInsertIndex)
                    .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, nome)
                    .build());

            try {
                ContentProviderResult[] res = getContentResolver().applyBatch(
                        ContactsContract.AUTHORITY, ops);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (OperationApplicationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            activity_padre.putExtra("NomeContatto", nome);
            activity_padre.putExtra("NumeroContatto", numero);
            setResult(RESULT_OK, activity_padre);
            finish();
        }else{
            setResult(RESULT_CANCELED,activity_padre);
        }
    }
}
