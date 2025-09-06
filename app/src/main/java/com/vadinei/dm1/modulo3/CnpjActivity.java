package com.vadinei.dm1.modulo3;

import static com.vadinei.dm1.modulo3.R.id.btVoltarCnpj;

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
import com.vadinei.dm1.modulo3.model.EmpresaModel;
import com.vadinei.dm1.modulo3.util.ConstantUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CnpjActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cnpj);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Cria as variáveis e faz o mapamento com a View
        final Button btBuscarCnpj = findViewById(R.id.btBuscarCnpj);
        final Button btVoltarCnpj = findViewById(R.id.btVoltarCnpj);

        btBuscarCnpj.setOnClickListener(this);
        btVoltarCnpj.setOnClickListener(this);

        final EditText edCnpj = findViewById(R.id.etCnpj);
        edCnpj.requestFocus();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btVoltarCnpj) {
            finish();
        } else if (view.getId() == R.id.btBuscarCnpj) {
            final EditText edCnpj = findViewById(R.id.etCnpj);
            final String numeroCnpj = edCnpj.getText().toString();
            if (numeroCnpj.isEmpty()) {
                Toast.makeText(CnpjActivity.this, "Informe o CNPJ", Toast.LENGTH_LONG).show();
                edCnpj.requestFocus();
            } else if (numeroCnpj.length() < 14) {
                Toast.makeText(CnpjActivity.this, "Um CNPJ possui 14 caracteres", Toast.LENGTH_LONG).show();
                edCnpj.requestFocus();
            } else {
                buscarCnpj(numeroCnpj);
            }
        }
    }

    private void buscarCnpj(String numeroCnpj) {
        final ProgressBar pbLoadingCnpj = findViewById(R.id.pbLoadingCnpj);
        pbLoadingCnpj.setVisibility(View.VISIBLE);
        final TextView tvInfo = findViewById(R.id.tvInfo);
        tvInfo.setText("");
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantUtil.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final InverTextoApi inverTextoApi = retrofit.create(InverTextoApi.class);
        final Call<EmpresaModel> call = inverTextoApi.getEmpresa(
                numeroCnpj, BuildConfig.INVERTEXTO_TOKEN
        );
        call.enqueue(new Callback<EmpresaModel>() {
            @Override
            public void onResponse(Call<EmpresaModel> call, Response<EmpresaModel> response) {
                pbLoadingCnpj.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    final EmpresaModel empresaModel = response.body();
                    if (empresaModel != null) {
                        tvInfo.setText(empresaModel.format());
                    }
                } else {
                    Toast.makeText(CnpjActivity.this, "Erro ao buscar informações do CNPJ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EmpresaModel> call, Throwable throwable) {
                pbLoadingCnpj.setVisibility(View.INVISIBLE);
                Toast.makeText(CnpjActivity.this, "Verifique as conexões com a Internet", Toast.LENGTH_LONG).show();
            }
        });
    }
}