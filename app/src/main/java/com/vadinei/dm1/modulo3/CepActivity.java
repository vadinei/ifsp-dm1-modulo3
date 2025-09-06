package com.vadinei.dm1.modulo3;

import static com.vadinei.dm1.modulo3.R.id.btVoltarCep;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vadinei.dm1.modulo3.api.InverTextoApi;
import com.vadinei.dm1.modulo3.model.LogradouroModel;
import com.vadinei.dm1.modulo3.util.ConstantUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CepActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cep);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Cria as variáveis e faz o mapamento com a View
        final Button btBuscarCep = findViewById(R.id.btBuscarCep);
        final Button btVoltarCep = findViewById(R.id.btVoltarCep);

        btBuscarCep.setOnClickListener(this);
        btVoltarCep.setOnClickListener(this);

        final EditText edCep = findViewById(R.id.etCep);
        edCep.requestFocus();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btVoltarCep) {
            finish();
        } else if (view.getId() == R.id.btBuscarCep) {
            final EditText edCep = findViewById(R.id.etCep);
            final String numeroCep = edCep.getText().toString();
            if (numeroCep.isEmpty()) {
                Toast.makeText(CepActivity.this, "Informe o CEP", Toast.LENGTH_LONG).show();
                edCep.requestFocus();
            } else if (numeroCep.length() < 8) {
                Toast.makeText(CepActivity.this, "Um CEP possui 8 caracteres", Toast.LENGTH_LONG).show();
                edCep.requestFocus();
            } else {
                buscarCep(numeroCep);
            }
        }
    }

    private void buscarCep(String numeroCep) {
        final ProgressBar pbLoadingCep = findViewById(R.id.pbLoadingCep);
        pbLoadingCep.setVisibility(View.VISIBLE);
        final TextView tvInfo = findViewById(R.id.tvInfo);
        tvInfo.setText("");
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantUtil.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final InverTextoApi inverTextoApi = retrofit.create(InverTextoApi.class);
        final Call<LogradouroModel> call = inverTextoApi.getLogradouro(
            numeroCep, BuildConfig.INVERTEXTO_TOKEN
        );
        call.enqueue(new Callback<LogradouroModel>() {
            @Override
            public void onResponse(Call<LogradouroModel> call, Response<LogradouroModel> response) {
                pbLoadingCep.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    final LogradouroModel logradouroModel = response.body();
                    if (logradouroModel != null) {
                        tvInfo.setText(logradouroModel.format());
                    }
                } else {
                    Toast.makeText(CepActivity.this, "Erro ao buscar informações do CEP", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LogradouroModel> call, Throwable throwable) {
                pbLoadingCep.setVisibility(View.INVISIBLE);
                Toast.makeText(CepActivity.this, "Verifique as conexões com a Internet", Toast.LENGTH_LONG).show();
            }
        });
    }
}