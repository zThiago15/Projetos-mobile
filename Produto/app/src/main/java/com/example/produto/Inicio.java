package com.example.produto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.produto.Cadastro;
import com.example.produto.ListaItens;
import com.example.produto.R;

public class Inicio extends AppCompatActivity {

    TextView txtTitulo;
    ImageView imgCadastro;
    Button btIniciar, btItens; //Variável btIniciar e btItens do tipo Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        imgCadastro = (ImageView) findViewById(R.id.imgCadastro);

        btIniciar = (Button) findViewById(R.id.btcadastro); //Atribuir na variável o id do botão do main activity
        btItens = (Button) findViewById(R.id.btitens);

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaSegunda();
            }
        });

        btItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaItens();
            }
        });
    }

    private void chamaSegunda() { //Método privado chamaSegunda

        Intent it = new Intent(this, Cadastro.class); //Instanciando a variável it do tipo Intent
        //Atribuindo para essa variável a Intent Cadastro.class
        startActivityForResult(it, 1);
    }

    //Ir para a tela de itens cadastrados
    private void chamaItens() {

        Intent it = new Intent(this, ListaItens.class);
        startActivityForResult(it, 1);

    }

}