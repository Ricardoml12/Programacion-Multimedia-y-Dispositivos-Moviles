package com.example.practica5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    TextView miTexto;

    Button boton1, boton2, boton3, boton4, boton5, boton6,
            boton7, boton8, boton9, boton0;
    Button botonSumar, botonMenos, botonMultiplicar, botonDividir;
    Button botonIgual, botonBorrar, botonC, botonAC, botonComa;

    float num1, num2;
    String operador = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);

        // TextView
        miTexto = view.findViewById(R.id.texto);

        // Números
        boton1 = view.findViewById(R.id.button1);
        boton2 = view.findViewById(R.id.button2);
        boton3 = view.findViewById(R.id.button3);
        boton4 = view.findViewById(R.id.button4);
        boton5 = view.findViewById(R.id.button5);
        boton6 = view.findViewById(R.id.button6);
        boton7 = view.findViewById(R.id.button7);
        boton8 = view.findViewById(R.id.button8);
        boton9 = view.findViewById(R.id.button9);
        boton0 = view.findViewById(R.id.button0);

        // Operadores
        botonSumar = view.findViewById(R.id.buttonSumar);
        botonMenos = view.findViewById(R.id.buttonMenos);
        botonMultiplicar = view.findViewById(R.id.buttonMultiplicar);
        botonDividir = view.findViewById(R.id.buttonDividir);

        // Otros
        botonIgual = view.findViewById(R.id.buttonIgual);
        botonBorrar = view.findViewById(R.id.buttonBorrar);
        botonC = view.findViewById(R.id.buttonC);
        botonAC = view.findViewById(R.id.buttonAC);
        botonComa = view.findViewById(R.id.buttonComa);

        configurarBotones();

        return view;
    }

    private void configurarBotones() {

        View.OnClickListener numeroListener = v -> {
            Button b = (Button) v;
            miTexto.setText(miTexto.getText().toString() + b.getText());
        };

        boton1.setOnClickListener(numeroListener);
        boton2.setOnClickListener(numeroListener);
        boton3.setOnClickListener(numeroListener);
        boton4.setOnClickListener(numeroListener);
        boton5.setOnClickListener(numeroListener);
        boton6.setOnClickListener(numeroListener);
        boton7.setOnClickListener(numeroListener);
        boton8.setOnClickListener(numeroListener);
        boton9.setOnClickListener(numeroListener);

        boton0.setOnClickListener(v -> {
            if (!miTexto.getText().toString().equals("")) {
                miTexto.setText(miTexto.getText().toString() + "0");
            }
        });

        botonSumar.setOnClickListener(v -> operar("+"));
        botonMenos.setOnClickListener(v -> operar("-"));
        botonMultiplicar.setOnClickListener(v -> operar("*"));
        botonDividir.setOnClickListener(v -> operar("/"));

        botonIgual.setOnClickListener(v -> {
            num2 = Float.parseFloat(miTexto.getText().toString());
            float resultado = 0;

            switch (operador) {
                case "+": resultado = num1 + num2; break;
                case "-": resultado = num1 - num2; break;
                case "*": resultado = num1 * num2; break;
                case "/": resultado = num1 / num2; break;
            }

            miTexto.setText(String.valueOf(resultado));
        });

        botonBorrar.setOnClickListener(v -> {
            String texto = miTexto.getText().toString();
            if (!texto.isEmpty()) {
                miTexto.setText(texto.substring(0, texto.length() - 1));
            }
        });

        botonC.setOnClickListener(v -> miTexto.setText(""));

        botonAC.setOnClickListener(v -> {
            miTexto.setText("");
            num1 = 0;
            num2 = 0;
            operador = "";
        });

        botonComa.setOnClickListener(v -> {
            String texto = miTexto.getText().toString();
            if (!texto.contains(".")) {
                miTexto.setText(texto.isEmpty() ? "0." : texto + ".");
            }
        });
    }

    private void operar(String op) {
        if (!miTexto.getText().toString().isEmpty()) {
            num1 = Float.parseFloat(miTexto.getText().toString());
            operador = op;
            miTexto.setText("");
        }
    }
}
