package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Cadastro extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    BancoDados bd = new BancoDados(this);

    TextView txTítulo;
    EditText txNome;
    EditText txEmail;
    EditText txCelular;
    EditText txTelefone;
    EditText txEndereco;
    EditText txBairro;
    EditText txCidade;
    EditText txEstado;
    Button btnLimpar, btnSalvar, btnExcluir, btCadastrar;
    ListView listViewPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txTítulo = findViewById(R.id.titulo);
        txNome = findViewById(R.id.txnome);
        txEmail = findViewById(R.id.txemail);
        txCelular = findViewById(R.id.txcelular);
        txTelefone = findViewById(R.id.txtelefone);
        txEndereco = findViewById(R.id.txendereco);
        txBairro = findViewById(R.id.txbairro);
        txCidade = findViewById(R.id.txcidade);
        txEstado = findViewById(R.id.txestado);

        btnLimpar = (Button)findViewById(R.id.btnLimpar);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnExcluir = (Button)findViewById(R.id.btnExcluir);

        listViewPessoas = (ListView)findViewById(R.id.listViewPessoas); //Declarei a listview que vem do xml

        listarPessoas();

    }

    private void telaMostrar(){

        Intent it = new Intent(this, Mostrar.class);
        it.putExtra("valor1", txtNome.getText().toString());
        it.putExtra("valor2", txtEmail.getText().toString());
        it.putExtra("valor3", txtCelular.getText().toString());
        it.putExtra("valor4", txtTelefone.getText().toString());
        it.putExtra("valor5", txtEndereco.getText().toString());
        it.putExtra("valor6", txtBairro.getText().toString());
        it.putExtra("valor7", txtCidade.getText().toString());
        it.putExtra("valor8", txtEstado.getText().toString());
        startActivityForResult(it,1);
    }

}