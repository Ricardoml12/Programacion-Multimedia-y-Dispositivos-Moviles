package com.example.edittext;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        String[] opciones = {"Cara", "Hombro", "Patata", "Pera", "Cleopatra"};
        @SuppressLint("WrongViewCast") AutoCompleteTextView texto = findViewById(R.id.texto);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        texto.setAdapter(adapter);


        Spinner miSpinner = (Spinner) findViewById(R.id.miSpinner);
        String[] valores = {"Star Wars", "Naufrago", "El Club de la Lucha", "Mamma mia", "Jurassic Park", "Tron"};
        miSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Has seleccionado: " + adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "No has seleccionado nada", Toast.LENGTH_SHORT).show();
            }
        });

        CheckBox checkbox1 = findViewById(R.id.checkbox1);
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Si checkeado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No checkeado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        class Radio extends AppCompatActivity {

            RadioGroup radioGroup;
            Button buttonCheck;
            TextView textViewResult;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                radioGroup = findViewById(R.id.radioGroup);
                buttonCheck = findViewById(R.id.button_check);
                textViewResult = findViewById(R.id.textViewResult);

                buttonCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedId = radioGroup.getCheckedRadioButtonId();

                        if (selectedId != -1) {
                            RadioButton selectedRadioButton = findViewById(selectedId);
                            String selectedOption = selectedRadioButton.getText().toString();
                            textViewResult.setText("Seleccionado: " + selectedOption);
                        } else {
                            textViewResult.setText("No se ha seleccionado ninguna opción");
                        }
                    }
                });
            }
        }

        Switch switch1;
        TextView textViewStatus;
        setContentView(R.layout.activity_main);
        switch1 = findViewById(R.id.switch1);
        textViewStatus = findViewById(R.id.textViewStatus);
        // Listener para el cambio de estado del Switch
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textViewStatus.setText("Estado: Encendido");
                } else {
                    textViewStatus.setText("Estado: Apagado");
                }
            }
        });
        SeekBar seekBar;
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this, "Azul", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Me gusta el queso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Guapo", Toast.LENGTH_SHORT).show();
            }
        });
        RatingBar ratingBar;
        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this, "SENSUALIZADOR3K00", Toast.LENGTH_SHORT).show();
            }
        });
    }
}