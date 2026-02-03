package com.example.practica5;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {

    private EditText etTotalCuenta;
    private CheckBox cbPropina;
    private SeekBar sbPropina;
    private TextView tvPorcentaje, tvResultado;
    private RadioGroup rgPago;
    private RatingBar ratingBar;
    private Button btnCalcular;
    private AutoCompleteTextView actvCamarero;

    private int porcentajePropina = 10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_third, container, false);

        // Referencias a vistas
        etTotalCuenta = view.findViewById(R.id.etTotalCuenta);
        cbPropina = view.findViewById(R.id.cbPropina);
        sbPropina = view.findViewById(R.id.sbPropina);
        tvPorcentaje = view.findViewById(R.id.tvPorcentaje);
        rgPago = view.findViewById(R.id.rgPago);
        ratingBar = view.findViewById(R.id.ratingBar);
        btnCalcular = view.findViewById(R.id.btnCalcular);
        tvResultado = view.findViewById(R.id.tvResultado);
        actvCamarero = view.findViewById(R.id.actvCamarero);

        configurarAutoComplete();
        configurarSeekBar();
        configurarValidacion();
        configurarBoton();

        return view;
    }

    private void configurarAutoComplete() {
        String[] camareros = {"Ana", "Andrés", "Beatriz", "Carlos", "Clara", "David"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                camareros
        );
        actvCamarero.setAdapter(adapter);
        actvCamarero.setThreshold(1);
    }

    private void configurarSeekBar() {
        sbPropina.setProgress(porcentajePropina);
        tvPorcentaje.setText("Propina: " + porcentajePropina + "%");

        sbPropina.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentajePropina = progress;
                tvPorcentaje.setText("Propina: " + porcentajePropina + "%");
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void configurarValidacion() {
        etTotalCuenta.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().matches("^\\d*\\.?\\d*$")) {
                    etTotalCuenta.setError("Solo se permiten números y decimales");
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void configurarBoton() {
        btnCalcular.setOnClickListener(v -> calcularTotal());
    }

    private void calcularTotal() {

        String totalStr = etTotalCuenta.getText().toString().trim();
        tvResultado.setTextColor(Color.BLACK);

        if (totalStr.isEmpty()) {
            mostrarError("Debes introducir el total de la cuenta.");
            return;
        }

        double total;
        try {
            total = Double.parseDouble(totalStr);
        } catch (NumberFormatException e) {
            mostrarError("Formato numérico incorrecto.");
            return;
        }

        if (total <= 0) {
            mostrarError("El valor debe ser mayor que 0.");
            return;
        }

        double propina = cbPropina.isChecked()
                ? total * porcentajePropina / 100.0
                : 0;

        double totalFinal = total + propina;

        int selectedId = rgPago.getCheckedRadioButtonId();
        String metodoPago = (selectedId != -1)
                ? ((RadioButton) requireView().findViewById(selectedId)).getText().toString()
                : "No seleccionado";

        float calificacion = ratingBar.getRating();
        String camarero = actvCamarero.getText().toString();

        String resultado =
                "Total base: " + String.format("%.2f", total) + " €\n" +
                        (cbPropina.isChecked()
                                ? "Propina (" + porcentajePropina + "%): " +
                                String.format("%.2f", propina) + " €\n"
                                : "") +
                        "TOTAL A PAGAR: " + String.format("%.2f", totalFinal) + " €\n\n" +
                        "Método de pago: " + metodoPago + "\n" +
                        "Camarero: " + (camarero.isEmpty() ? "No especificado" : camarero) + "\n" +
                        "Calificación: " + calificacion + " estrellas";

        tvResultado.setText(resultado);
    }

    private void mostrarError(String mensaje) {
        tvResultado.setTextColor(Color.RED);
        tvResultado.setText(mensaje);
    }
}
