package com.example.tema5adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Adaptador extends ArrayAdapter<Datos> {
    private Datos[] datos;

    public Adaptador(Context context, Datos[] datos){
        super(context,R.layout.elemento , datos);
        this.datos=datos;
    }

    public View getView(int positon, View convertView, ViewGroup parent){
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.elemento, parent, false);
        TextView text1 = elemento.findViewById(R.id.text1);
        TextView text2 = elemento.findViewById(R.id.text2);

        text1.setText(datos[positon].getTexto1());
        text2.setText(datos[positon].getTexto2());
        return elemento;
    }
}
