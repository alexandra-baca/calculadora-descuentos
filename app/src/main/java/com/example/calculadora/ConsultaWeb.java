package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConsultaWeb extends AppCompatActivity {

    private EditText et11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_web);
        et11 = findViewById(R.id.et11);
    }

    public void ver(View view) {
        String direccion = et11.getText().toString().trim();

        if (direccion.isEmpty()) {
            et11.setError("Introduce una dirección web");
            et11.requestFocus();
            return;
        }

        if (!direccion.startsWith("http://") && !direccion.startsWith("https://")) {
            direccion = "https://" + direccion;
        }

        Intent intent = new Intent(this, PantallaUrl.class);
        intent.putExtra(PantallaUrl.EXTRA_URL, direccion);
        startActivity(intent);
    }

    public void volver1(View view) {
        finish();
    }
}
