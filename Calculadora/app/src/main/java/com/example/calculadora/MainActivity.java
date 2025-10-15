package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView miTexto;
    Button boton1;
    Button boton2;
    Button boton3;
    Button botonDividir;
    Button boton4;
    Button boton5;
    Button boton6;
    Button botonMultiplicar;
    Button boton7;
    Button boton8;
    Button boton9;
    Button botonMenos;
    Button botonBorrar;
    Button boton0;
    Button botonIgual;
    Button botonSumar;
    Button botonC;
    Button botonAC;
    Button botonComa;

    float num1;
    float num2;
    String operador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    protected void onStart() {
        super.onStart();
        boton1 = findViewById(R.id.button1);
        boton2 = findViewById(R.id.button2);
        boton3 = findViewById(R.id.button3);
        botonDividir = findViewById(R.id.buttonDividir);
        boton4 = findViewById(R.id.button4);
        boton5 = findViewById(R.id.button5);
        boton6 = findViewById(R.id.button6);
        botonMultiplicar = findViewById(R.id.buttonMultiplicar);
        boton7 = findViewById(R.id.button7);
        boton8 = findViewById(R.id.button8);
        boton9 = findViewById(R.id.button9);
        botonMenos = findViewById(R.id.buttonMenos);
        botonBorrar = findViewById(R.id.buttonBorrar);
        boton0 = findViewById(R.id.button0);
        botonIgual = findViewById(R.id.buttonIgual);
        botonSumar = findViewById(R.id.buttonSumar);
        botonC = findViewById(R.id.buttonC);
        botonAC = findViewById(R.id.buttonAC);
        botonComa = findViewById(R.id.buttonComa);

        miTexto = (TextView) findViewById(R.id.texto);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "1");
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "2");
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "3");
            }
        });

        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "4");
            }
        });

        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "5");
            }
        });

        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "6");
            }
        });

        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "7");
            }
        });

        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "8");
            }
        });

        boton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "9");
            }
        });

        boton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                if (!texto.equals("")) {
                    miTexto.setText(texto + "0");
                }
            }
        });

        botonSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Float.parseFloat((String) miTexto.getText());
                miTexto.setText("");
                operador = "+";
            }
        });

        botonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Float.parseFloat((String) miTexto.getText());
                miTexto.setText("");
                operador = "-";
            }
        });

        botonMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Float.parseFloat((String) miTexto.getText());
                miTexto.setText("");
                operador = "*";
            }
        });

        botonDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Float.parseFloat((String) miTexto.getText());
                miTexto.setText("");
                operador = "/";
            }
        });

        botonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num2 = Float.parseFloat((String) miTexto.getText());
                float resultado = 0;
                switch (operador) {
                    case "+":
                        resultado = num1 + num2;
                        break;
                    case "-":
                        resultado = num1 - num2;
                        break;
                    case "*":
                        resultado = num1 * num2;
                        break;
                    case "/":
                        resultado = num1 / num2;
                        break;
                }
                String i = String.valueOf(resultado);
                miTexto.setText(i);
            }
        });

        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = miTexto.getText().toString();
                if (!texto.isEmpty()) {
                    texto = texto.substring(0, texto.length() - 1);
                    miTexto.setText(texto);
                }
            }
        });

        botonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miTexto.setText("");
            }
        });

        botonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miTexto.setText("");
                num1 = 0;
                num2 = 0;
                operador = null;
            }
        });

        botonComa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = miTexto.getText().toString();
                if (!texto.contains(".")) {
                    if (texto.equals("")) {
                        miTexto.setText("0.");
                    } else {
                        miTexto.setText(texto + ".");
                    }
                }
            }
        });
    }
}