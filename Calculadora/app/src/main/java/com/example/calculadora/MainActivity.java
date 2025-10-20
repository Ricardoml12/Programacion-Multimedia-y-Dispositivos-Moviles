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

    /**
     * Texto de Operación
     */
    TextView miTexto;
    /**
     * Boton número 1
     */
    Button boton1;
    /**
     * Boton número 2
     */
    Button boton2;
    /**
     * Boton número 3
     */
    Button boton3;
    /**
     * Boton operador dividir
     */
    Button botonDividir;
    /**
     * Boton número 4
     */
    Button boton4;
    /**
     * Boton número 5
     */
    Button boton5;
    /**
     * Boton número 6
     */
    Button boton6;
    /**
     * Boton operador multiplicar
     */
    Button botonMultiplicar;
    /**
     * Boton número 7
     */
    Button boton7;
    /**
     * Boton número 8
     */
    Button boton8;
    /**
     * Boton número 9
     */
    Button boton9;
    /**
     * Boton operador resta
     */
    Button botonMenos;
    /**
     * Boton borrar 1 número
     */
    Button botonBorrar;
    /**
     * Boton numero 0
     */
    Button boton0;
    /**
     * Boton operador igual
     */
    Button botonIgual;
    /**
     * Boton operador sumar
     */
    Button botonSumar;
    /**
     * Boton borrar todo el numero
     */
    Button botonC;
    /**
     * Boton borrar todos los numeros
     */
    Button botonAC;
    /**
     * Boton coma
     */
    Button botonComa;

    /**
     * Primero numero
     */
    float num1;
    /**
     * Segundo numero
     */
    float num2;
    /**
     * Operacion
     */
    String operador = "";

    /**
     * Crea la aplicación
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    /**
     * Ejecuta la aplicación
     */
    protected void onStart() {
        super.onStart();
        /**
         * Inicializo todas las variables de los botones
         */
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

        /**
         * Escribe "1" al clickar en el boton
         */
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "1");
            }
        });

        /**
         * Escribe "2" al clickar en el boton
         */
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "2");
            }
        });

        /**
         * Escribe "3" al clickar en el boton
         */
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "3");
            }
        });

        /**
         * Escribe "4" al clickar en el boton
         */
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "4");
            }
        });

        /**
         * Escribe "5" al clickar en el boton
         */
        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "5");
            }
        });

        /**
         * Escribe "6" al clickar en el boton
         */
        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "6");
            }
        });

        /**
         * Escribe "7" al clickar en el boton
         */
        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "7");
            }
        });

        /**
         * Escribe "8" al clickar en el boton
         */
        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "8");
            }
        });

        /**
         * Escribe "9" al clickar en el boton
         */
        boton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                miTexto.setText(texto + "9");
            }
        });

        /**
         * Escribe "0" al clickar en el boton comprobando que a la izquierda
         * del todo del numero no se pueda escribir 0
         */
        boton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = (String) miTexto.getText();
                if (!texto.equals("")) {
                    miTexto.setText(texto + "0");
                }
            }
        });

        /**
         * Guarda en las variables num1 y operador el primer numero y el operador "+"
         */
        botonSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Float.parseFloat((String) miTexto.getText());
                miTexto.setText("");
                operador = "+";
            }
        });

        /**
         * Guarda en las variables num1 y operador el primer numero y el operador "-"
         */
        botonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Float.parseFloat((String) miTexto.getText());
                miTexto.setText("");
                operador = "-";
            }
        });

        /**
         * Guarda en las variables num1 y operador el primer numero y el operador "*"
         */
        botonMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Float.parseFloat((String) miTexto.getText());
                miTexto.setText("");
                operador = "*";
            }
        });

        /**
         * Guarda en las variables num1 y operador el primer numero y el operador "/"
         */
        botonDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Float.parseFloat((String) miTexto.getText());
                miTexto.setText("");
                operador = "/";
            }
        });

        /**
         * Obtiene el segundo numero y dependiendo del operador seleccionado hace la
         * operacion correspondiente poniendo en el texto el resultado
         */
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
                    case "":
                        break;
                }
                String i = String.valueOf(resultado);
                miTexto.setText(i);
            }
        });

        /**
         * Borra el carácter más a la derecha del número
         */
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

        /**
         * Elimina solo el número visto en pantalla
         */
        botonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miTexto.setText("");
            }
        });

        /**
         * Elimina tanto el operador como ambos números
         */
        botonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miTexto.setText("");
                num1 = 0;
                num2 = 0;
                operador = null;
            }
        });

        /**
         * Pone un "." en el número comprobando si hay números a la izquierda y en caso que
         * no los haya escribiendo "0."
         */
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