package com.vadinei.dm1.modulo3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        final Button btBuscarCepMain = findViewById(R.id.btBuscarCepMain);
        final Button btBuscarCnpjMain = findViewById(R.id.btBuscarCnpjMain);

        btBuscarCepMain.setOnClickListener(this);
        btBuscarCnpjMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btBuscarCepMain) {
            // Abrir a tela de Busca de CEPs
            final Intent telaBuscarCep = new Intent(MainActivity.this, CepActivity.class);
            startActivity(telaBuscarCep);
        } else if (view.getId() == R.id.btBuscarCnpjMain) {
            // Abrir a tela de Busca de CNPJs
            final Intent telaBuscarCnpj = new Intent(MainActivity.this, CnpjActivity.class);
            startActivity(telaBuscarCnpj);
        }
    }
}