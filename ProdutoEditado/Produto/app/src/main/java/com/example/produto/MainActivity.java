package com.example.produto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtTitulo;
    ImageView imgCadastro;
    Button btIniciar, btItens; //Variável btIniciar e btItens do tipo Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        imgCadastro = (ImageView) findViewById(R.id.imgCadastro);

        btIniciar = (Button) findViewById(R.id.btcadastro); //Atribuir na variável o id do botão do main activity
        btItens = (Button) findViewById(R.id.btitens);

        btIniciar.setOnClickListener(new View.OnClickListener() { //Método da classe Button responsável por saber se houver um clique no botão

            public void onClick(View v) { //Em caso de haver clique
                chamaSegunda(); //Irá executar este método
            }

        });

        btItens.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
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