package com.simplifiedlauncher.home;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maurizio.simplifiedlauncher.R;

import java.util.List;

/**
 * Created by Carmine on 21/01/2017.
 */

public class CustomAdapterMessaggio extends ArrayAdapter<Messaggio> {
    private Context context;
    private LayoutInflater inflater;

    public CustomAdapterMessaggio(Context context, int resource, List<Messaggio> objects) {
        super(context, resource, objects);
        resource = resource;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            v = inflater.inflate(R.layout.messaggio_singolo, null);
        }

        Messaggio m = getItem(position);

        TextView nome = (TextView) v.findViewById(R.id.elem_lista_nome);
        TextView telefono = (TextView) v.findViewById(R.id.elem_lista_data);
        ImageView iconaMessaggio = (ImageView) v.findViewById(R.id.iconaMessaggio);

        if (m.isLetto() == true) {
            iconaMessaggio.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.img_messaggio_letto, null));
        } else {
            iconaMessaggio.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.img_messaggio_ricevuto, null));
        }
        nome.setText(m.getNomeContatto());
        telefono.setText(m.getData() + "    " + m.getOra());
        return v;
    }
}
