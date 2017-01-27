package com.simplifiedlauncher.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maurizio.simplifiedlauncher.R;

import java.util.ArrayList;

/**
 * Created by Carmine on 21/01/2017.
 */

public class FormInserisciMessaggio extends AppCompatActivity {
    EditText editDestinatario;
    EditText editTestoMessaggio;
    String destinatario;
    String testoMessaggio;
    Messaggio messaggio;
    Contatto contatto;
    ArrayList<Contatto> array_contatti;
    CustomAdapterContatto adapter;
    ListView rubrica;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_inserisci_messaggio);

        i=getIntent();
        array_contatti= (ArrayList<Contatto>) i.getSerializableExtra("Rubrica");
        rubrica = (ListView) findViewById(R.id.elencoContatti);
        editDestinatario = (EditText) findViewById(R.id.destinatario);
        editTestoMessaggio = (EditText) findViewById(R.id.testoMessaggio);

        adapter= new CustomAdapterContatto(this, R.layout.contatto_rubrica, array_contatti);
        rubrica.setAdapter(adapter);

        editDestinatario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean digitsOnly = TextUtils.isDigitsOnly(editDestinatario.getText());
                if (digitsOnly){
                    mostraTestoMessaggio();
                }else{
                    mostraRubrica();
                }
                filtraElenco(editDestinatario.getText().toString());
            }
        });

        rubrica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contatto = (Contatto) rubrica.getItemAtPosition(position);
                editDestinatario.setText(contatto.getNumero().toString());
            }
        });
    }

    public void invia(View v){
        destinatario= editDestinatario.getText().toString();
        testoMessaggio = editTestoMessaggio.getText().toString();
        if (destinatario.matches("")|| testoMessaggio.matches("")){
            Toast.makeText(getApplicationContext(),"Riempire i campi",Toast.LENGTH_SHORT).show();
        }else{
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(destinatario,null,testoMessaggio,null,null);
            long data = System.currentTimeMillis();
            messaggio= new Messaggio(contatto.getNome(),testoMessaggio,data,true,true);
            Toast.makeText(getApplicationContext(),"Messaggio inviato",Toast.LENGTH_SHORT).show();
            i.putExtra("MessaggioInviato",messaggio);
            setResult(RESULT_OK,i);
            finish();
        }
    }

    public void filtraElenco(String filtro){
        int lunghezzaFiltro = filtro.length();
        ArrayList<Contatto> elencoFiltrato = new ArrayList<Contatto>();
        for (Contatto c: array_contatti){
            if (lunghezzaFiltro<=c.getNome().length() && c.getNome().substring(0,lunghezzaFiltro).equalsIgnoreCase(filtro))
                elencoFiltrato.add(c);
        }
        aggiornaListaFiltrata(elencoFiltrato);
    }

    public void aggiornaListaFiltrata(ArrayList<Contatto> elencoFiltrato){
        adapter= new CustomAdapterContatto(this, R.layout.contatto_rubrica, elencoFiltrato);
        rubrica.setAdapter(adapter);
    }

    public void mostraTestoMessaggio(){
        rubrica.setVisibility(View.INVISIBLE);
        editTestoMessaggio.setVisibility(View.VISIBLE);
    }

    public void mostraRubrica(){
        rubrica.setVisibility(View.VISIBLE);
        editTestoMessaggio.setVisibility(View.INVISIBLE);
    }

    public void indietro(View v){
        finish();
    }
}
