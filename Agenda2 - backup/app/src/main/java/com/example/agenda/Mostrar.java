package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Mostrar extends AppCompatActivity {

    TextView txtNome;
    TextView txtEmail;
    TextView txtCelular;
    TextView txtTelefone;
    TextView txtEndereco;
    TextView txtBairro;
    TextView txtCidade;
    TextView txtEstado;
    Button btConfirma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        txtNome = findViewById(R.id.vNome);
        txtEmail = findViewById(R.id.vEmail);
        txtCelular = findViewById(R.id.vCelular);
        txtTelefone = findViewById(R.id.vTelefone);
        txtEndereco = findViewById(R.id.vEndereco);
        txtBairro = findViewById(R.id.vBairro);
        txtCidade = findViewById(R.id.vCidade);
        txtEstado = findViewById(R.id.vEstado);
        btConfirma = findViewById(R.id.btconfirma);

        Intent intentLocal = getIntent();

        String recebeNome = intentLocal.getStringExtra("valor1");
        String recebeEmail = intentLocal.getStringExtra("valor2");
        String recebeCelular = intentLocal.getStringExtra("valor3");
        String recebeTelefone = intentLocal.getStringExtra("valor4");
        String recebeEndereco = intentLocal.getStringExtra("valor5");
        String recebeBairro = intentLocal.getStringExtra("valor6");
        String recebeCidade = intentLocal.getStringExtra("valor7");
        String recebeEstado = intentLocal.getStringExtra("valor8");

        txtNome.setText(recebeNome);
        txtEmail.setText(recebeEmail);
        txtCelular.setText(recebeCelular);
        txtTelefone.setText(recebeTelefone);
        txtEndereco.setText(recebeEndereco);
        txtBairro.setText(recebeBairro);
        txtCidade.setText(recebeCidade);
        txtEstado.setText(recebeEstado);

        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltaInicio();
            }
        });
    }

            private void voltaInicio(){

                Intent it = new Intent(this, MainActivity.class); //instanciando a varivel it do tipo Intent
                //Atribuindo para essa variável a Intent Cadastro.class
                startActivityForResult(it, 1); //executando o método para abrir a Intent Incial de Cadastro

                Toast.makeText(Mostrar.this, "Dados gravados com sucesso!!!", Toast.LENGTH_SHORT).show();
                //https://developer.android.com/guide/topics/ui/notifiers/toasts?hl=pt-br
            }
}