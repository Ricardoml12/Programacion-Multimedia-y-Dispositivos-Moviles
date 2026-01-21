package com.example.gestioncuentabar;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etTotalCuenta;
    private CheckBox cbPropina;
    private SeekBar sbPropina;
    private TextView tvPorcentaje, tvResultado;
    private RadioGroup rgPago;
    private RatingBar ratingBar;
    private Button btnCalcular;
    private AutoCompleteTextView actvCamarero;

    private int porcentajePropina = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTotalCuenta = findViewById(R.id.etTotalCuenta);
        cbPropina = findViewById(R.id.cbPropina);
        sbPropina = findViewById(R.id.sbPropina);
        tvPorcentaje = findViewById(R.id.tvPorcentaje);
        rgPago = findViewById(R.id.rgPago);
        ratingBar = findViewById(R.id.ratingBar);
        btnCalcular = findViewById(R.id.btnCalcular);
        tvResultado = findViewById(R.id.tvResultado);
        actvCamarero = findViewById(R.id.actvCamarero);

        String[] camareros = {"Ana", "Andrés", "Beatriz", "Carlos", "Clara", "David"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, camareros);
        actvCamarero.setAdapter(adapter);
        actvCamarero.setThreshold(1);

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

        btnCalcular.setOnClickListener(v -> calcularTotal());

        etTotalCuenta.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().matches("^\\d*\\.?\\d*$")) {
                    etTotalCuenta.setError("Solo se permiten números y punto decimal");
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void calcularTotal() {

        String totalStr = etTotalCuenta.getText().toString().trim();
        tvResultado.setTextColor(Color.BLACK);

        if (totalStr.isEmpty()) {
            tvResultado.setTextColor(Color.RED);
            tvResultado.setText("Debes introducir el total de la cuenta.");
            return;
        }

        double total;
        try {
            total = Double.parseDouble(totalStr);
        } catch (NumberFormatException e) {
            tvResultado.setTextColor(Color.RED);
            tvResultado.setText("Formato numérico incorrecto.");
            return;
        }

        if (total <= 0) {
            tvResultado.setTextColor(Color.RED);
            tvResultado.setText("El valor de la cuenta debe ser mayor que 0.");
            return;
        }

        double propina = cbPropina.isChecked() ? total * porcentajePropina / 100.0 : 0;
        double totalFinal = total + propina;

        int selectedId = rgPago.getCheckedRadioButtonId();
        String metodoPago = (selectedId != -1) ?
                ((RadioButton) findViewById(selectedId)).getText().toString() :
                "No seleccionado";

        float calificacion = ratingBar.getRating();
        String camarero = actvCamarero.getText().toString();

        String resultado =
                "Total base: " + String.format("%.2f", total) + " €\n" +
                        (cbPropina.isChecked() ? "Propina (" + porcentajePropina + "%): " +
                                String.format("%.2f", propina) + " €\n" : "") +
                        "TOTAL A PAGAR: " + String.format("%.2f", totalFinal) + " €\n\n" +
                        "Método de pago: " + metodoPago + "\n" +
                        "Camarero: " + (camarero.isEmpty() ? "No especificado" : camarero) + "\n" +
                        "Calificación del servicio: " + calificacion + " estrellas";

        tvResultado.setText(resultado);
    }
}
