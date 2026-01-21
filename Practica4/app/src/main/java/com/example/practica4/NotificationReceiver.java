package com.example.practica4;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String nombre = intent.getStringExtra("nombre");
        String fecha = intent.getStringExtra("fecha");
        String hora = intent.getStringExtra("hora");

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, "evento_channel")
                        .setSmallIcon(R.drawable.ic_event)
                        .setContentTitle(nombre)
                        .setContentText("Fecha: " + fecha + " Hora: " + hora)
                        .setAutoCancel(true);

        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify((int) System.currentTimeMillis(), builder.build());
    }
}

