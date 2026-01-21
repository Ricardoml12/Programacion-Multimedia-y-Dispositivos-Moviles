package com.example.practicatema6;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.metrics.Event;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Evento> events = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventAdapter adapter;

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
        requestNotificationPermission();

        recyclerView = findViewById(R.id.recyclerEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EventAdapter();
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAddEvent).setOnClickListener(v -> showAddEventDialog());
    }

    /* ------------------ DIÁLOGOS ------------------ */

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void showAddEventDialog() {
        EditText input = new EditText(this);
        input.setHint("Nombre del evento");

        new AlertDialog.Builder(this)
                .setTitle("Nuevo evento")
                .setView(input)
                .setPositiveButton("Siguiente",
                        (d, w) -> pickDate(input.getText().toString()))
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void pickDate(String name) {
        Calendar cal = Calendar.getInstance();

        new DatePickerDialog(this,
                (v, y, m, d) -> pickTime(name,
                        d + "/" + (m + 1) + "/" + y),
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void pickTime(String name, String date) {
        Calendar cal = Calendar.getInstance();

        new TimePickerDialog(this, (v, h, m) -> {
            String time = String.format("%02d:%02d", h, m);
            Evento event = new Evento(name, date, time, cal.getTimeInMillis());

            events.add(event);
            adapter.notifyItemInserted(events.size() - 1);
            showNotification(event);

        }, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true).show();
    }

    /* ------------------ NOTIFICACIÓN ------------------ */

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void showNotification(Evento event) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, "EVENTS")
                .setSmallIcon(R.drawable.ic_event)
                .setContentTitle(event.name)
                .setContentText(event.date + " " + event.time)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat.from(this).notify((int) System.currentTimeMillis(), notification);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "EVENTS", "Eventos",
                    NotificationManager.IMPORTANCE_DEFAULT);

            getSystemService(NotificationManager.class)
                    .createNotificationChannel(channel);
        }
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= 33 &&
                checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
        }
    }

    /* ------------------ TOAST PERSONALIZADO ------------------ */

    private void showCustomToast(Evento event) {
        View view = getLayoutInflater().inflate(R.layout.toast_event, null);
        TextView txt = view.findViewById(R.id.txtToast);

        txt.setText(event.name + "\n" + event.date + " " + event.time);

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    /* ------------------ ADAPTER INTERNO ------------------ */

    class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, date;

            ViewHolder(View v) {
                super(v);
                name = v.findViewById(R.id.txtName);
                date = v.findViewById(R.id.txtDate);

                v.setOnClickListener(e ->
                        showCustomToast(events.get(getAdapterPosition())));
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_event, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder h, int p) {
            Evento e = events.get(p);
            h.name.setText(e.name);
            h.date.setText(e.date + " - " + e.time);
        }

        @Override
        public int getItemCount() {
            return events.size();
        }
    }
}
