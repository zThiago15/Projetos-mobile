package com.example.produto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Exibir extends AppCompatActivity {

    BancoDados bd = new BancoDados(this);

    TextView txtCodigobar;
    TextView txtNome;
    TextView txtPreco;
    TextView txtCategoria;
    Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir);


        txtCodigobar = findViewById(R.id.vCodigobar);
        txtNome = findViewById(R.id.vNome);
        txtPreco = findViewById(R.id.vPreco);
        txtCategoria = findViewById(R.id.vCategoria);
        btSalvar = findViewById(R.id.btSalvar);

        Intent intentLocal = getIntent();

        String recebeCodigobar = intentLocal.getStringExtra("valor1");
        String recebeNome = intentLocal.getStringExtra("valor2");
        String recebePreco = intentLocal.getStringExtra("valor3");
        String recebeCategoria = intentLocal.getStringExtra("valor4");

        txtCodigobar.setText(recebeCodigobar);
        txtNome.setText(recebeNome);
        txtPreco.setText(recebePreco);
        txtCategoria.setText(recebeCategoria);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String codigobar = txtCodigobar.getText().toString();
                String nome = txtNome.getText().toString();
                String preco = txtPreco.getText().toString();
                String categoria = txtCategoria.getText().toString();


                bd.addProduto(new Produto(codigobar, nome, preco, categoria));
                Toast.makeText(Exibir.this,"Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                telaInicio();


            }
        });
    }

    private void telaInicio() {
        Intent it = new Intent(this, MainActivity.class); //Instanciando a variável it do tipo Intent
        //Atribuindo para essa variável a Intent Cadastro.class
        startActivityForResult(it,1); //Executando o método para abrir a Intent inicial de cadastro

        Toast.makeText(Exibir.this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
    }

}