package com.simplifiedlauncher.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.maurizio.simplifiedlauncher.R;
import com.simplifiedlauncher.permissions.RuntimePermissionsActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Carmine on 18/01/2017.
 */

public class Rubrica extends RuntimePermissionsActivity {
    ListView elencoContatti;
    Button aggiungiContatto;
    EditText editCerca;
    ArrayList<Contatto> array_contatti;
    CustomAdapterContatto adapter;
    Intent i;
    final int REQUEST_CODE_FORM_INSERISCI_CONTATTO=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rubrica);

        elencoContatti = (ListView) findViewById(R.id.elencoContatti);
        aggiungiContatto = (Button) findViewById(R.id.aggiungiContatto);
        editCerca = (EditText) findViewById(R.id.cerca);

        elencoContatti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contatto contatto = (Contatto) elencoContatti.getItemAtPosition(position);
                Intent avviaTelefono = new Intent(getApplicationContext(),Telefono.class);
                avviaTelefono.putExtra("NumeroContattoSelezionato",contatto.getNumero());
                startActivity(avviaTelefono);
            }
        });

        editCerca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filtraElenco(editCerca.getText().toString());

            }
        });

        array_contatti = new ArrayList<Contatto>();

        creaListaContatti();
    }

    /* Ottiene la lista dei contatti della rubrica*/
    /* Chiama il metodo getPhoneNumber*/
    public void creaListaContatti(){
        String[] columns = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, columns, null,null, ContactsContract.Contacts.DISPLAY_NAME);

        int ColumeIndex_ID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int ColumeIndex_DISPLAY_NAME = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        int ColumeIndex_HAS_PHONE_NUMBER = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
        int i=0;

        while (cursor.moveToNext()&&i<50) {
            String id = cursor.getString(ColumeIndex_ID);
            String nome = cursor.getString(ColumeIndex_DISPLAY_NAME);
            String has_phone = cursor.getString(ColumeIndex_HAS_PHONE_NUMBER);
            String numero="";

            if (!has_phone.endsWith("0")) {
                numero=getPhoneNumber(id);
                Contatto contatto = new Contatto(nome,numero);
                array_contatti.add(contatto);
                i++;
            }
        }
        cursor.close();
        aggiornaLista();
    }

    //Viene chiamato da creaListaContatti
    //Restituisce il numero (i numeri se più di uno) del contatto con l'id passato come parametro
    public String getPhoneNumber(String id) {
        String number = "";
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null,  ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        if(phones.getCount() > 0)
        {
            while(phones.moveToNext())
            {
                number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }

        }

        phones.close();

        return number;
    }

    //Viene chiamato dopo l'aggiunta di un nuovo contatto
    //Ordina i contatti in ordine alfabetico e li mostra
    public void aggiornaLista(){
        Collections.sort(array_contatti, new Comparator<Contatto>() {
            @Override
            public int compare(Contatto o1, Contatto o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        });
        adapter= new CustomAdapterContatto(this, R.layout.contatto_rubrica, array_contatti);
        elencoContatti.setAdapter(adapter);
    }

    //Viene chiamato appena l'utente digita un carattere nell' edit text per cercare un contatto nella rubrica
    //Come parametro viene passato il carattere o i caratteri inseriti dall'utente
    public void filtraElenco(String filtro){
        int lunghezzaFiltro = filtro.length();
        ArrayList<Contatto> elencoFiltrato = new ArrayList<Contatto>();
        for (Contatto c: array_contatti){
            if (c.getNome().substring(0,lunghezzaFiltro).equalsIgnoreCase(filtro))
                elencoFiltrato.add(c);
        }
        aggiornaListaFiltrata(elencoFiltrato);
    }

    //Viene chiamato dopo che è stato filtrato l'elenco dei contatti
    //Mostra l'elenco dei contatti filtrato
    public void aggiornaListaFiltrata(ArrayList<Contatto> elencoFiltrato){
        adapter= new CustomAdapterContatto(this, R.layout.contatto_rubrica, elencoFiltrato);
        elencoContatti.setAdapter(adapter);
    }

    //Tramite un intent avvia l'activity FormInserisciContatto
    public void aggiungiContatto(View v) throws InterruptedException {
        Intent formAggiungiContatto = new Intent(getApplicationContext(),FormInserisciContatto.class);
        startActivityForResult(formAggiungiContatto,REQUEST_CODE_FORM_INSERISCI_CONTATTO);
    }

    //In risposta all activity FormInserisciContenuto
    /*Se l'utente ha aggiunto il contatto (quindi resultCode==RESULT_OK) viene creato il contatto aggiunto e viene aggiunto
      all ArrayList di contatti per poterlo visualizzare*/
    //Infine viene chiamato il metodo aggiornaLista() per aggiornare la ListView contenente l'elenco dei contatti
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE_FORM_INSERISCI_CONTATTO){
            if(resultCode==RESULT_OK) {
                String nomeContatto = data.getStringExtra("NomeContatto");
                String numeroContatto = data.getStringExtra("NumeroContatto");
                Contatto contatto = new Contatto(nomeContatto, numeroContatto);
                array_contatti.add(contatto);
                aggiornaLista();
            }
        }
    }

    /*======================================PERMISSIONS==============================================*/

    @Override
    public void onPermissionsGranted(int requestCode) {

    }
}
