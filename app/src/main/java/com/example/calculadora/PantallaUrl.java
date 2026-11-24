package com.example.calculadora;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaUrl extends AppCompatActivity {

    public static final String EXTRA_URL = "direccion";
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_url);

        web = findViewById(R.id.web);
        web.setWebViewClient(new WebViewClient());

        String direccion = getIntent().getStringExtra(EXTRA_URL);
        if (direccion == null || direccion.trim().isEmpty()) {
            Toast.makeText(this, "No se ha recibido ninguna dirección web", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Uri uri = Uri.parse(direccion);
        String scheme = uri.getScheme();
        if (uri.getHost() == null ||
                (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme))) {
            Toast.makeText(this, "La dirección web no es válida", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        web.loadUrl(uri.toString());
    }

    public void volver2(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (web != null && web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
