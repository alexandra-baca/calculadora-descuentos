package com.example.calculadora;

import static android.content.Context.MODE_APPEND;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class RegistrosGastosPersonales extends AppCompatActivity {

    private static final String ARCHIVO_GASTOS = "gastos.txt";

    private EditText etConcepto;
    private EditText etImporte;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_gastos_personales);

        etConcepto = findViewById(R.id.et111);
        etImporte = findViewById(R.id.et222);
        spinner = findViewById(R.id.spinner);

        String[] categorias = {"Alimentación", "Transporte", "Ocio"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        cargarGastos();
    }

    private void cargarGastos() {
        StringBuilder contenido = new StringBuilder();
        try (FileInputStream fis = openFileInput(ARCHIVO_GASTOS);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\\n");
            }

            if (contenido.length() > 0) {
                new AlertDialog.Builder(this)
                        .setTitle("Gastos guardados")
                        .setMessage(contenido.toString())
                        .setPositiveButton("Cerrar", null)
                        .show();
            }
        } catch (IOException e) {
            // El archivo todavía no existe: es normal en la primera ejecución.
        }
    }

    public void guardar(View view) {
        String concepto = etConcepto.getText().toString().trim();
        String importeTexto = etImporte.getText().toString().trim().replace(',', '.');
        String categoria = spinner.getSelectedItem().toString();

        if (concepto.isEmpty()) {
            etConcepto.setError("Introduce el concepto del gasto");
            etConcepto.requestFocus();
            return;
        }
        if (importeTexto.isEmpty()) {
            etImporte.setError("Introduce el importe");
            etImporte.requestFocus();
            return;
        }

        double importe;
        try {
            importe = Double.parseDouble(importeTexto);
        } catch (NumberFormatException e) {
            etImporte.setError("Introduce un importe válido");
            etImporte.requestFocus();
            return;
        }

        if (importe <= 0) {
            etImporte.setError("El importe debe ser mayor que 0");
            etImporte.requestFocus();
            return;
        }

        String registro = String.format(Locale.getDefault(),
                "%s | %s | %.2f €%n", concepto, categoria, importe);

        try (FileOutputStream fos = openFileOutput(ARCHIVO_GASTOS, MODE_APPEND);
             OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
            writer.write(registro);
            writer.flush();

            Toast.makeText(this, "Gasto guardado correctamente", Toast.LENGTH_SHORT).show();
            etConcepto.setText("");
            etImporte.setText("");
            etConcepto.requestFocus();
        } catch (IOException e) {
            Toast.makeText(this, "No se ha podido guardar el gasto", Toast.LENGTH_LONG).show();
        }
    }

    public void volver3(View view) {
        finish();
    }
}
