package com.example.tema5;

import static com.example.tema5.R.*;
import static com.example.tema5.R.id.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        List<String> data = Arrays.asList("Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4");

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        TextView textView = findViewById(R.id.textView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String elemento = (String) parent.getItemAtPosition(position);
                textView.setText(elemento);
                Toast.makeText(MainActivity.this, "A", Toast.LENGTH_SHORT).show();
            }
        });
        GridView listado = findViewById(id.gridView);
        listado.setAdapter(adapter);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String elemento = (String) parent.getItemAtPosition(position);
                textView.setText(elemento);
                Toast.makeText(MainActivity.this, "A", Toast.LENGTH_SHORT).show();
            }
        });
    }
}