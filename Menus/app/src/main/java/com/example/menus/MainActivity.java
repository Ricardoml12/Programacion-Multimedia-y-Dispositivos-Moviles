package com.example.menus;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView etiqueta =findViewById(R.id.listView);
        registerForContextMenu(etiqueta);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu,menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_acercade){
            Toast.makeText(this, "Acerca de...", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.action_settings){
            Toast.makeText(this, "Altos ajustes...", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.action_help){
            Toast.makeText(this, "AYUDAAAAAAAAA", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_acercade){
            Toast.makeText(this, "Acerca de...", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.action_settings){
            Toast.makeText(this, "Altos ajustes...", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.action_help){
            Toast.makeText(this, "AYUDAAAAAAAAA", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}