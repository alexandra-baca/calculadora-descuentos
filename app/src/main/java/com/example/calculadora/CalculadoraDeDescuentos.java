package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class CalculadoraDeDescuentos extends AppCompatActivity {

    private EditText et1, et2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_de_descuentos);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        tv1 = findViewById(R.id.tv1);
    }

    public void calcular(View view) {
        String precioTexto = et1.getText().toString().trim().replace(',', '.');
        String porcentajeTexto = et2.getText().toString().trim().replace(',', '.');

        if (precioTexto.isEmpty()) {
            et1.setError("Introduce el precio");
            et1.requestFocus();
            return;
        }
        if (porcentajeTexto.isEmpty()) {
            et2.setError("Introduce el porcentaje");
            et2.requestFocus();
            return;
        }

        try {
            double precio = Double.parseDouble(precioTexto);
            double porcentaje = Double.parseDouble(porcentajeTexto);

            if (precio < 0) {
                et1.setError("El precio no puede ser negativo");
                et1.requestFocus();
                return;
            }
            if (porcentaje < 0 || porcentaje > 100) {
                et2.setError("Introduce un porcentaje entre 0 y 100");
                et2.requestFocus();
                return;
            }

            double importeDescuento = precio * porcentaje / 100.0;
            double precioFinal = precio - importeDescuento;

            tv1.setText(String.format(Locale.getDefault(),
                    "Precio con descuento: %.2f €\nDescuento: %.2f €",
                    precioFinal, importeDescuento));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Introduce valores numéricos válidos", Toast.LENGTH_SHORT).show();
        }
    }

    public void volver(View view) {
        finish();
    }
}
