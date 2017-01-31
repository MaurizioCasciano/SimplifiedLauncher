package com.simplifiedlauncher.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.maurizio.simplifiedlauncher.R;

/**
 * Created by Carmine on 21/01/2017.
 */

public class VisualizzaMessaggio extends AppCompatActivity {
    TextView viewCorpoMessaggio;
    TextView viewContattoMessaggio;
    TextView viewDataMessaggio;
    TextView viewContattoMessaggioLabel;
    TextView viewDataMessaggioLabel;
    TextView viewOraMessaggioLabel;
    TextView viewOraMessaggio;
    Intent padre;
    Messaggio messaggio;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizzazione_messaggio);

        viewCorpoMessaggio = (TextView) findViewById(R.id.corpoMessaggio);
        viewContattoMessaggio = (TextView) findViewById(R.id.contattoMessaggio);
        viewDataMessaggio = (TextView) findViewById(R.id.dataMessaggio);
        viewContattoMessaggioLabel = (TextView) findViewById(R.id.contattoMessaggioLabel);
        viewDataMessaggioLabel = (TextView) findViewById(R.id.dataMessaggioLabel);
        viewOraMessaggioLabel = (TextView) findViewById(R.id.oraMessaggioLabel);
        viewOraMessaggio = (TextView) findViewById(R.id.oraMessaggio);

        padre= getIntent();
        messaggio = (Messaggio) padre.getSerializableExtra("Messaggio");
        viewCorpoMessaggio.setMovementMethod(new ScrollingMovementMethod());
        if(messaggio.getInviato()) {
            viewCorpoMessaggio.setBackground(getResources().getDrawable(R.drawable.bordo_messaggio_inviato));
            viewContattoMessaggioLabel.setText("Inviato a: ");
            viewContattoMessaggioLabel.setTextColor(Color.GREEN);
            viewDataMessaggioLabel.setTextColor(Color.GREEN);
            viewOraMessaggioLabel.setTextColor(Color.GREEN);

        }else{
            viewCorpoMessaggio.setBackground(getResources().getDrawable(R.drawable.bordo_messaggio_ricevuto));
            viewContattoMessaggioLabel.setText("Ricevuto da: ");
            viewContattoMessaggioLabel.setTextColor(Color.rgb(255,211,27));
            viewDataMessaggioLabel.setTextColor(Color.rgb(255,211,27));
            viewOraMessaggioLabel.setTextColor(Color.rgb(255,211,27));

        }
        viewCorpoMessaggio.setText(messaggio.getTesto().toString());
        viewContattoMessaggio.setText(messaggio.getNomeContatto().toString());
        viewDataMessaggio.setText(messaggio.getData());
        viewOraMessaggio.setText(messaggio.getOra());

    }

    public void indietro(View v){
        finish();
    }
}
