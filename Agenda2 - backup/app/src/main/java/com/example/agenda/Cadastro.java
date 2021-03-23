package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Cadastro extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    BancoDados bd = new BancoDados(this);

    TextView txtítulo;
    EditText txcodigo;
    EditText txnome;
    EditText txemail;
    EditText txcelular;
    EditText txtelefone;
    EditText txendereco;
    EditText txbairro;
    EditText txcidade;
    EditText txestado;
    Button  btnLimpar, btnSalvar, btnExcluir, btCadastrar;
    ListView listViewPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtítulo = findViewById(R.id.titulo);
        txcodigo = (EditText)findViewById(R.id.txcodigo );
        txnome = (EditText)findViewById(R.id.txnome);
        txemail = (EditText)findViewById(R.id.txemail);
        txcelular =  (EditText)findViewById(R.id.txcelular);
        txtelefone =  (EditText)findViewById(R.id.txtelefone);
        txendereco = (EditText)findViewById(R.id.txendereco);
        txbairro = (EditText)findViewById(R.id.txbairro);
        txcidade = (EditText)findViewById(R.id.txcidade);
        txestado = (EditText)findViewById(R.id.txestado);

       btnLimpar = (Button)findViewById(R.id.btnLimpar);
       btnSalvar = (Button)findViewById(R.id.btnSalvar);
       btnExcluir = (Button)findViewById(R.id.btnExcluir);

       txnome.requestFocus();

       listViewPessoas = (ListView)findViewById( R.id.listViewPessoas );

       listarPessoas();
       listViewPessoas.setOnItemClickListener( new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String conteudo = (String) listViewPessoas.getItemAtPosition( position );
               Toast.makeText(Cadastro.this, "Select: " + conteudo, Toast.LENGTH_SHORT).show();

                String codigo = conteudo.substring( 0, conteudo.indexOf( "-" ) );
                Pessoa pessoa = bd.selecionarPessoa( Integer.parseInt( codigo ) );

                txcodigo.setText( String.valueOf(  pessoa.getCodigo()));
                txnome.setText( pessoa.getNome() );
                txemail.setText( pessoa.getEmail() );
                txcelular.setText( pessoa.getCelular() );
                txtelefone.setText( pessoa.getTelefone() );
                txendereco.setText( pessoa.getEndereco() );
                txbairro.setText( pessoa.getBairro() );
                txcidade.setText( pessoa.getCidade() );
                txestado.setText( pessoa.getEstado() );

           }
       } );

       btnLimpar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               limpaCampos();

            }
        } );

       btnSalvar.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String codigo = txcodigo.getText().toString();
               String nome = txnome.getText().toString();
               String email = txemail.getText().toString();
               String celular = txcelular.getText().toString();
               String telefone = txtelefone.getText().toString();
               String endereco = txendereco.getText().toString();
               String bairro = txbairro.getText().toString();
               String cidade = txcidade.getText().toString();
               String estado = txestado.getText().toString();



               if(nome.isEmpty()){
                   txnome.setError( "Este campo é obrigatório" );
               }else if(codigo.isEmpty()){
                   bd.addPessoa(new Pessoa(nome,email,celular,telefone,endereco,bairro,cidade,estado));
                   Toast.makeText(Cadastro.this, "Dados gravados com sucesso!!!", Toast.LENGTH_SHORT).show();
                   limpaCampos();
                   listarPessoas();

               } else
                    bd.atualizarPessoa(new Pessoa(Integer.parseInt( codigo ),nome,email,celular,telefone,endereco,bairro,cidade,estado));
                    Toast.makeText(Cadastro.this, "Dados atualizado com sucesso!!!", Toast.LENGTH_SHORT).show();
                    limpaCampos();
                    listarPessoas();
               {


               }
           }
       } );

        btnExcluir.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = txcodigo.getText().toString();

                if(codigo.isEmpty()){

                    Toast.makeText(Cadastro.this, "Nenhum contato está selecionado", Toast.LENGTH_SHORT).show();

                }else {

                    Pessoa pessoa = new Pessoa(  );
                    pessoa.setCodigo( Integer.parseInt( codigo ) );
                    bd.apagarPessoa( pessoa );
                    Toast.makeText(Cadastro.this, "Contato excluído com sucesso", Toast.LENGTH_SHORT).show();

                    limpaCampos();
                    listarPessoas();

                }
            }
        } );


    }

      /** private void telaMostrar(){

           Intent it = new Intent(this, Mostrar.class);

           it.putExtra("valor1", txtNome.getText().toString());
           it.putExtra("valor2", txtEmail.getText().toString());
           it.putExtra("valor3", txtCelular.getText().toString());
           it.putExtra("valor4", txtTelefone.getText().toString());
           it.putExtra("valor5", txtEndereco.getText().toString());
           it.putExtra("valor6", txtBairro.getText().toString());
           it.putExtra("valor7", txtCidade.getText().toString());
           it.putExtra("valor8", txtEstado.getText().toString());

           startActivityForResult(it, 1);
       }**/

    void limpaCampos(){
        txcodigo.setText( "" );
        txnome.setText("");
        txemail.setText("");
        txcelular.setText("");
        txtelefone.setText("");
        txendereco.setText("");
        txbairro.setText("");
        txcidade.setText("");
        txestado.setText("");

        txnome.requestFocus();
    }

    public void listarPessoas(){

        List<Pessoa> pessoas = bd.listaPessoas();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Cadastro.this, android.R.layout.simple_list_item_1, arrayList);

        listViewPessoas.setAdapter(adapter);

        for(Pessoa c : pessoas){
        //    Log.d( "Lista", "\nID: " + c.getCodigo() + "Nome: " + c.getNome(  ));            //arrayList. add( c.getCodigo() + "-" + c.getCodigo());
         arrayList.add(c.getCodigo() + "-" + c.getNome());
         adapter.notifyDataSetChanged();
        }

    }
}