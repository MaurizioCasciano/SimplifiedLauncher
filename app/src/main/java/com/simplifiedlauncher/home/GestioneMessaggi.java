package com.simplifiedlauncher.home;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maurizio.simplifiedlauncher.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Carmine on 19/01/2017.
 */

public class GestioneMessaggi extends AppCompatActivity {
    Intent padre;
    ArrayList<Contatto> array_contatti;
    ArrayList<Messaggio> array_messaggi;
    CustomAdapterMessaggio adapter;
    ListView listaMessaggi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messaggi);

        listaMessaggi = (ListView) findViewById(R.id.elencoMessaggi);

        padre = getIntent();
        array_contatti = (ArrayList<Contatto>) padre.getSerializableExtra("Rubrica");

        array_messaggi = new ArrayList<Messaggio>();
        caricaMessaggi();

        listaMessaggi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Messaggio m = (Messaggio) listaMessaggi.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(), VisualizzaMessaggio.class);
                i.putExtra("Messaggio", m);
                array_messaggi.get(position).setLetto(true);
                ContentValues values = new ContentValues();
                values.put("read", "1");
                int risultato = getContentResolver().update(Uri.parse("content://sms"), values, "date" + m.getDataInMillis().toString(), null);
                startActivityForResult(i, 1);
            }
        });

    }

    public void caricaMessaggi() {
        long data = System.currentTimeMillis();
        String[] args = {Long.toString(data - TimeUnit.DAYS.toMillis(20)), Long.toString(data)};
        String[] reqCols = new String[]{"_id", "body", "address", "read", "date", "type"};
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), reqCols, "date between ? and ?", args, "date DESC");
        String body = "";
        String address = "";
        long date;
        String type = "";
        String read;
        Contatto contatto;
        Messaggio messaggio;
        Boolean letto;

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                body = cursor.getString(1);
                address = cursor.getString(2);
                date = Long.parseLong(cursor.getString(4));
                type = cursor.getString(5);
                contatto = cercaContatto(address);
                read = cursor.getString(3);
                System.out.println(cursor.getString(0));
                if (read.equals("0")) {
                    letto = false;
                } else {
                    letto = true;
                }
                if (contatto != null) {
                    if (type.equalsIgnoreCase("1")) {
                        messaggio = new Messaggio(contatto.getNome(), body, date, false, letto);
                    } else {
                        messaggio = new Messaggio(contatto.getNome(), body, date, true, letto);
                    }
                    array_messaggi.add(messaggio);
                }
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
        mostraElencoMessaggi();
    }

    public Contatto cercaContatto(String numero) {
        int i = 0;
        if (numero.substring(0, 1).equalsIgnoreCase("+"))
            numero = numero.substring(3, numero.length());
        while (i < array_contatti.size()) {
            if (array_contatti.get(i).getNumero().equalsIgnoreCase(numero))
                return array_contatti.get(i);
            i++;
        }
        if (i < array_contatti.size()) {
            return array_contatti.get(i);
        } else {
            return null;
        }
    }

    public void mostraElencoMessaggi() {
        /*ArrayList<Contatto> elencoContatti = new ArrayList<Contatto>();
        for (Contatto c:array_contatti){
            if (c.getElencoMessaggi().size()!=0){
                elencoContatti.add(c);
            }
        }*/
        adapter = new CustomAdapterMessaggio(this, R.layout.messaggio_singolo, array_messaggi);
        listaMessaggi.setAdapter(adapter);
    }

    public void avviaFormInserisciMessaggio(View v) {
        Intent i = new Intent(getApplicationContext(), FormInserisciMessaggio.class);
        i.putExtra("Rubrica", array_contatti);
        startActivityForResult(i, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Messaggio messaggio = (Messaggio) data.getSerializableExtra("MessaggioInviato");
                System.out.println(messaggio.getTesto());
                array_messaggi.add(0, messaggio);
                mostraElencoMessaggi();
            }
        } else {
            if (requestCode == 1) {
                adapter.notifyDataSetChanged();
            }
        }
    }

}
