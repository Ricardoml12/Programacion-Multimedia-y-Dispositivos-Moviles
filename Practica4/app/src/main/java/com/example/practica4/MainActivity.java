package com.example.practica4;


import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Evento> listaEventos;
    private ArrayAdapter<String> adapter;
    private static final int REQUEST_NOTIFICATION_PERMISSION = 100;


    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearCanalNotificacion();
        verificarPermisoNotificaciones();
        listaEventos = new ArrayList<>();
        ListView listView = findViewById(R.id.listViewEventos);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getNombresEventos());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Evento evento = listaEventos.get(position);
            mostrarToast(evento);
        });

        Button btnAgregar = findViewById(R.id.btnAgregarEvento);
        btnAgregar.setOnClickListener(v -> mostrarDialogoNombre());
    }
    private void crearCanalNotificacion() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel canal = new NotificationChannel("evento_channel", "Eventos", NotificationManager.IMPORTANCE_DEFAULT);
            canal.setDescription("Notificaciones de eventos");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(canal);
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void mostrarDialogoNombre() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nuevo evento");

        EditText input = new EditText(this);
        input.setHint("Nombre del evento");
        builder.setView(input);

        builder.setPositiveButton("Siguiente", (dialog, which) -> {
            String nombre = input.getText().toString();
            mostrarDatePicker(nombre);
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void mostrarDatePicker(String nombre) {
        Calendar c = Calendar.getInstance();
        int anio = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    String fecha = dayOfMonth + "/" + (month+1) + "/" + year;
                    mostrarTimePicker(nombre, fecha);
                }, anio, mes, dia);
        dpd.show();
    }
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void mostrarTimePicker(String nombre, String fecha) {
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    String horaStr = String.format("%02d:%02d", hourOfDay, minute);
                    Evento evento = new Evento(nombre, fecha, horaStr);
                    agregarEvento(evento);
                }, hora, minuto, true);
        tpd.show();
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void agregarEvento(Evento evento) {
        listaEventos.add(evento);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getNombresEventos());
        ListView listView = findViewById(R.id.listViewEventos);
        listView.setAdapter(adapter);

        programarNotificacion(evento); // 👈 AQUÍ
    }


    private ArrayList<String> getNombresEventos(){
        ArrayList<String> nombres = new ArrayList<>();
        for(Evento e : listaEventos){
            nombres.add(e.getNombre());
        }
        return nombres;
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void mostrarNotificacion(Evento evento){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "evento_channel")
                .setSmallIcon(R.drawable.ic_event) // Vector Asset que debes crear
                .setContentTitle(evento.getNombre())
                .setContentText("Fecha: " + evento.getFecha() + " Hora: " + evento.getHora())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify((int) System.currentTimeMillis(), builder.build());
    }
    private void mostrarToast(Evento evento){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_personalizado, null);

        TextView tv = layout.findViewById(R.id.tvToast);
        tv.setText(evento.getNombre() + "\n" + evento.getFecha() + " " + evento.getHora());

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    private void verificarPermisoNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        REQUEST_NOTIFICATION_PERMISSION
                );
            }
        }
    }
    private void programarNotificacion(Evento evento) {

        Calendar calendar = Calendar.getInstance();

        String[] fecha = evento.getFecha().split("/");
        String[] hora = evento.getHora().split(":");

        calendar.set(
                Integer.parseInt(fecha[2]),      // año
                Integer.parseInt(fecha[1]) - 1,  // mes
                Integer.parseInt(fecha[0]),      // día
                Integer.parseInt(hora[0]),       // hora
                Integer.parseInt(hora[1]),       // minuto
                0
        );

        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("nombre", evento.getNombre());
        intent.putExtra("fecha", evento.getFecha());
        intent.putExtra("hora", evento.getHora());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                (int) System.currentTimeMillis(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                pendingIntent
        );

    }


}