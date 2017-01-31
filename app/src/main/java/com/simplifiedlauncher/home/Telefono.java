package com.simplifiedlauncher.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
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
        display.setSelected(true);
        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        button1.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        button1.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("1");
                        break;
                }
                return true;            }
        });
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        button2.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        button2.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("2");
                        break;
                }
                return true;            }
        });
        button3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        button3.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        button3.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("3");
                        break;
                }
                return true;            }
        });
        button4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        button4.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        button4.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("4");
                        break;
                }
                return true;            }
        });
        button5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        button5.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        button5.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("5");
                        break;
                }
                return true;            }
        });
        button6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        button6.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        button6.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("6");
                        break;
                }
                return true;            }
        });
        button7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        button7.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        button7.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("7");
                        break;
                }
                return true;            }
        });
        button8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        button8.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        button8.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("8");
                        break;
                }
                return true;            }
        });
        button9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        button9.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        button9.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("9");
                        break;
                }
                return true;            }
        });
        buttonAsterisco.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        buttonAsterisco.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        buttonAsterisco.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("*");
                        break;
                }
                return true;            }
        });
        button0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        button0.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        button0.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("0");
                        break;
                }
                return true;            }
        });
        buttonCancelletto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        buttonCancelletto.setBackgroundColor(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_UP:
                        buttonCancelletto.setBackgroundColor(getResources().getColor(R.color.awesomeBlue));
                        updateDisplay("#");
                        break;
                }
                return true;            }
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
