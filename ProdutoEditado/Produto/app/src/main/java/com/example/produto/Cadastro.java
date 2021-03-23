package com.example.produto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {


    TextView txTitulo;
    EditText txCodigo;
    EditText txCodigobar;
    EditText txNome;
    EditText txPreco;
    EditText txCategoria;
    Button btnLimpar, btnSalvar;

    BancoDados bd;

    private AlertDialog dialog;
    private  AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        bd = new BancoDados(this);

        txTitulo = (TextView) findViewById(R.id.titulo);
        txCodigo = (EditText) findViewById(R.id.txcodigo);
        txCodigobar = (EditText) findViewById(R.id.txcodigobar);
        txNome= (EditText) findViewById(R.id.txnome);
        txPreco = (EditText) findViewById(R.id.txpreco);
        txCategoria = (EditText) findViewById(R.id.txcategoria);

        txCodigobar.requestFocus();

        btnLimpar = (Button)findViewById(R.id.btnLimpar);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = txCodigo.getText().toString();
                String codigobar = txCodigobar.getText().toString();


                if(codigobar.isEmpty()){
                    txCodigobar.setError( "Este campo é obrigatório" );
                } else if(codigo.isEmpty()){
                    criarPopupConfirmarCadastro();
                }
            }
        });
    }

    private void limpaCampos(){

        txCodigobar.setText("");
        txNome.setText("");
        txPreco.setText("");
        txCategoria.setText("");

    }

    private void telaMostrar(){

        Intent it = new Intent(this, ListaItens.class);
        it.putExtra("valor1", txCodigobar.getText().toString());
        it.putExtra("valor2", txNome.getText().toString());
        it.putExtra("valor3", txPreco.getText().toString());
        it.putExtra("valor4", txCategoria.getText().toString());
        startActivityForResult(it, 1);
        finish();
    }

    private void criarPopupConfirmarCadastro() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View confirmarCadastro = getLayoutInflater().inflate(R.layout.popup_confirmar, null);

        TextView txCodigoBarras = confirmarCadastro.findViewById(R.id.txCodigoBarras);
        TextView txNomeProduto = confirmarCadastro.findViewById(R.id.txNomeProduto);
        TextView txPrecoProduto = confirmarCadastro.findViewById(R.id.txPrecoProduto);
        TextView txCategoriaProduto = confirmarCadastro.findViewById(R.id.txCategoriaProduto);
        Button btnConfirmarCadastro = confirmarCadastro.findViewById(R.id.btnConfirmarCadastro);
        Button btnCancelarCadastro = confirmarCadastro.findViewById(R.id.btnCancelarCadastro);


        txCodigoBarras.setText(txCodigobar.getText().toString());
        txNomeProduto.setText(txNome.getText().toString());
        txPrecoProduto.setText(txPreco.getText().toString());
        txCategoriaProduto.setText(txCategoria.getText().toString());

        btnConfirmarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigobar = txCodigobar.getText().toString();
                String nome = txNome.getText().toString();
                String preco = txPreco.getText().toString();
                String categoria = txCategoria.getText().toString();

                bd.addProduto(new Produto(codigobar, nome, preco, categoria));
                telaMostrar();
            }
        });

        btnCancelarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialogBuilder.setView(confirmarCadastro);
        dialog = dialogBuilder.create();
        dialog.show();
    }

}