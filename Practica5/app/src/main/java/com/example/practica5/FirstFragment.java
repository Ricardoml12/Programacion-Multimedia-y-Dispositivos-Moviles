package com.example.practica5;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FirstFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        TextInputLayout layoutNombre = view.findViewById(R.id.layoutNombre);
        TextInputLayout layoutEmail = view.findViewById(R.id.layoutEmail);
        TextInputLayout layoutTelefono = view.findViewById(R.id.layoutTelefono);
        TextInputLayout layoutPassword = view.findViewById(R.id.layoutPassword);

        TextInputEditText editNombre = view.findViewById(R.id.editNombre);
        TextInputEditText editEmail = view.findViewById(R.id.editEmail);
        TextInputEditText editTelefono = view.findViewById(R.id.editTelefono);
        TextInputEditText editPassword = view.findViewById(R.id.editPassword);

        Button btnValidar = view.findViewById(R.id.btnValidar);

        btnValidar.setOnClickListener(v -> {

            layoutNombre.setError(null);
            layoutEmail.setError(null);
            layoutTelefono.setError(null);
            layoutPassword.setError(null);

            boolean valido = true;

            if (editNombre.getText().toString().trim().isEmpty()) {
                layoutNombre.setError("Campo obligatorio");
                valido = false;
            }

            String email = editEmail.getText().toString().trim();
            if (email.isEmpty()) {
                layoutEmail.setError("Campo obligatorio");
                valido = false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                layoutEmail.setError("Email no válido");
                valido = false;
            }

            if (editTelefono.getText().toString().trim().isEmpty()) {
                layoutTelefono.setError("Campo obligatorio");
                valido = false;
            }else if (editTelefono.getText().length() != 9) {
                layoutTelefono.setError("El teléfono debe tener 9 dígitos");
                valido = false;
            }

            if (editPassword.getText().toString().trim().isEmpty()) {
                layoutPassword.setError("Campo obligatorio");
                valido = false;
            }

            if (valido) {
                Snackbar.make(view, "Formulario válido", Snackbar.LENGTH_LONG).setAction("OK", b -> {}).show();
                editNombre.setText("");
                editEmail.setText("");
                editTelefono.setText("");
                editPassword.setText("");
            }
        });

        return view;
    }
}
