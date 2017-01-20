package com.simplifiedlauncher.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maurizio.simplifiedlauncher.R;

/**
 * Created by Carmine on 18/01/2017.
 */

public class Telefono extends AppCompatActivity {
    Intent avviaChiamata,i;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonAsterisco;
    private Button button0;
    private Button buttonCancelletto;
    private Button buttonCancella;
    private Button buttonChiama;

    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telefono);

        display = (TextView) findViewById(R.id.display);

        i = getIntent();
        if (i!=null){
            if(i.getStringExtra("NumeroContattoSelezionato")!=null) {
                display.setText(i.getStringExtra("NumeroContattoSelezionato").toString());
            }
        }


        button1 = (Button) findViewById(R.id.Button1);
        button2 = (Button) findViewById(R.id.Button2);
        button3 = (Button) findViewById(R.id.Button3);
        button4 = (Button) findViewById(R.id.Button4);
        button5 = (Button) findViewById(R.id.Button5);
        button6 = (Button) findViewById(R.id.Button6);
        button7 = (Button) findViewById(R.id.Button7);
        button8 = (Button) findViewById(R.id.Button8);
        button9 = (Button) findViewById(R.id.Button9);
        buttonAsterisco = (Button) findViewById(R.id.ButtonAsterisco);
        button0 = (Button) findViewById(R.id.Button0);
        buttonCancelletto = (Button) findViewById(R.id.ButtonCancelletto);
        buttonCancella = (Button) findViewById(R.id.ButtonCancella);
        buttonChiama = (Button) findViewById(R.id.ButtonChiama);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("9");
            }
        });
        buttonAsterisco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("*");
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("0");
            }
        });
        buttonCancelletto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("#");
            }
        });

        buttonCancella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!display.getText().toString().matches("")) {
                    String numeroCompleto = display.getText().toString();
                    numeroCompleto = numeroCompleto.substring(0, numeroCompleto.length() - 1);
                    display.setText(numeroCompleto.toString());
                }
            }
        });

        buttonChiama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!display.getText().toString().matches("")) {
                    avviaChiamata = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + display.getText().toString()));
                    startActivity(avviaChiamata);
                }
            }
        });

    }

    public void updateDisplay(String numero){
        display.setText(display.getText().toString()+numero);
    }
}
