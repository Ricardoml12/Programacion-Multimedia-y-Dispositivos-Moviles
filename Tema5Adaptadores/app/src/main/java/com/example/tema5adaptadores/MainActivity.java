package com.example.tema5adaptadores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    Datos[] datos = new Datos[]{
            new Datos("Linea principal 1", "Linea inferion 1"),
            new Datos("Linea principal 2", "Linea inferion 2"),
            new Datos("Linea principal 3", "Linea inferion 3"),
            new Datos("Linea principal 4", "Linea inferion 4")
    };
        ListView listado = findViewById(R.id.listView);
        Adaptador miAdaptador = new Adaptador(this,datos);
        listado.setAdapter(miAdaptador);
    }
}