package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    //Método siguiente pantalla Descuentos
    public void siguiente1(View view){
        //Intent para ir a la siguiente pantalla
        Intent i = new Intent(this, CalculadoraDeDescuentos.class);
        //Inicia la actividad
        startActivity(i);
    }

    //Método de cambio de pantalla wikipedia
    public void siguiente2(View view){
        //Intent para ir a la siguiente pantalla
        Intent i = new Intent(this,ConsultaWeb.class);
        //Inicia la actividad
        startActivity(i);
    }

    //Método siguiente pantalla Registro de gastos
    public void siguiente3(View view){
        //Intent para ir a la siguiente pantalla
        Intent i = new Intent(this,RegistrosGastosPersonales.class);
        //Inicia la actividad
        startActivity(i);
    }
}