package com.simplifiedlauncher.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.maurizio.simplifiedlauncher.R;

import java.util.List;

/**
 * Created by Carmine on 18/01/2017.
 */

public class CustomAdapterContatto extends ArrayAdapter<Contatto> {

    private int resource;
    private LayoutInflater inflater;

    public CustomAdapterContatto(Context context, int resource, List<Contatto> objects) {
        super(context, resource, objects);
        resource = resource;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(R.layout.contatto_rubrica, null);
        }

        Contatto c = getItem(position);


        TextView nameTextView;
        TextView telTextView;

        nameTextView = (TextView) v.findViewById(R.id.elem_lista_nome);


        telTextView = (TextView) v.findViewById(R.id.elem_lista_telefono);

        nameTextView.setText(c.getNome());
        telTextView.setText(c.getNumero());

        return v;
    }
}
