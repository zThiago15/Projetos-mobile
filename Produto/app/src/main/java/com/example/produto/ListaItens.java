package com.example.produto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaItens extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    BancoDados bd= new BancoDados(this);

    EditText txtcodigo;
    EditText txtcodigobar;
    EditText txtnome;
    EditText txtpreco;
    EditText txtcategoria;
    ListView listViewProdutos;
    Button btnVoltar, btnEditar, btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);

        txtcodigo = (EditText)findViewById(R.id.txtcodigo);
        txtcodigobar= (EditText)findViewById(R.id.txtcodigobar);
        txtnome = (EditText)findViewById(R.id.txtnome);
        txtpreco = (EditText)findViewById(R.id.txtpreco);
        txtcategoria = (EditText)findViewById(R.id.txtcategoria);

        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        btnEditar = (Button)findViewById(R.id.btnEditar);
        btnExcluir = (Button)findViewById(R.id.btnExcluir);

        listViewProdutos = (ListView)findViewById(R.id.listViewProdutos);
        listarProdutos();

        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String conteudo = (String) listViewProdutos.getItemAtPosition(position);
                Toast.makeText(ListaItens.this, "Produto selecionado: " + conteudo, Toast.LENGTH_SHORT).show();

                String codigo = conteudo.substring(0, conteudo.indexOf(" - "));
                Produto produto = bd.selecionaProduto(Integer.parseInt(codigo));

                txtcodigo.setText(String.valueOf( produto.getCodigo()));
                txtcodigobar.setText( produto.getCodigobar());
                txtnome.setText( produto.getNome());
                txtpreco.setText( produto.getPreco());
                txtcategoria.setText( produto.getCategoria());

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                primeiraTela();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = txtcodigo.getText().toString();
                String codigobar = txtcodigobar.getText().toString();
                String nome = txtnome.getText().toString();
                String preco = txtpreco.getText().toString();
                String categoria = txtcategoria.getText().toString();

                if (codigo.isEmpty()){
                    txtcodigobar.setError("Nenhum produto selecionado para editar");
                } else if(codigobar.isEmpty()){
                    txtcodigobar.setError("Este campo é obrigatório");
                }else if(txtcodigobar.length() < 13){
                    txtcodigobar.setError("Precisa ter 13 números no mínimo");
                }
                else if(nome.isEmpty()) {
                    txtnome.setError("Este campo é obrigatório");
                }
                else {
                    bd.atualizarProduto(new Produto(Integer.parseInt(codigo),codigobar,nome,preco,categoria));
                    Toast.makeText(ListaItens.this, "Produto editado com sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampos();
                    listarProdutos();
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = txtcodigo.getText().toString();
                String codigobar = txtcodigobar.getText().toString();
                String nome = txtnome.getText().toString();

                if (codigo.isEmpty()){
                    txtcodigobar.setError("Nenhum produto selecionado para excluir");
                } else if(codigobar.isEmpty()){
                    txtcodigobar.setError("Este campo é obrigatório");
                } else if(nome.isEmpty()) {
                    txtnome.setError("Este campo é obrigatório");
                } else {
                    Produto produto = new Produto( );
                    produto.setCodigo(Integer.parseInt(codigo));
                    bd.apagarProduto(produto);
                    Toast.makeText(ListaItens.this, "Produto excluído com sucesso!", Toast.LENGTH_SHORT).show();

                    limparCampos();
                    listarProdutos();
                }

            }
        });

    }

    private void listarProdutos(){

        List<Produto> produtos = bd.listaProduto();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(ListaItens.this, android.R.layout.simple_list_item_1, arrayList); //contexto, listview e colocar produtos cadastrados

        listViewProdutos.setAdapter(adapter);

        for (Produto c : produtos){

            arrayList.add(c.getCodigo() + " - " + c.getNome());
            adapter.notifyDataSetChanged();
        }
    }

    private void primeiraTela(){

        Intent it = new Intent(this, Inicio.class);
        startActivityForResult(it, 1);
    }

    private void limparCampos(){
        txtcodigo.setText("");
        txtcodigobar.setText("");
        txtnome.setText("");
        txtpreco.setText("");
        txtcategoria.setText("");
    }

}