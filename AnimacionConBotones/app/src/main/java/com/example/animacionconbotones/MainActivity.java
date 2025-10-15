package com.example.animacionconbotones;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button boton1;
    Button boton2;
    Button boton3;
    TextView miTexto;
    Animation miAnimacion;

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
        miTexto = (TextView) findViewById(R.id.texto);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View traslate) {
                detener();
                miAnimacion = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
                miAnimacion.setRepeatMode(Animation.RESTART);
                miAnimacion.setRepeatCount(20);
                miTexto.startAnimation(miAnimacion);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View rotar) {
                detener();
                miAnimacion = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotar);
                miAnimacion.setRepeatMode(Animation.RESTART);
                miAnimacion.setRepeatCount(20);
                miTexto.startAnimation(miAnimacion);
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View detener) {
                detener();
            }
        });

    }
    private void detener(){
        if(miAnimacion != null) {
            miTexto.clearAnimation();
            miAnimacion.cancel();
            miAnimacion = null;
        }
    }
}