package com.example.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {

    public static final int VERSAO_BANCO = 1;
    public static final String BANCO_AGENDA = "bd_agenda";

    public static final String TABELA_PESSOA = "tb_pessoa";

    public static final String COLUNA_codigo = "codigo";
    public static final String COLUNA_nome = "nome";
    public static final String COLUNA_email = "email";
    public static final String COLUNA_celular = "celular";
    public static final String COLUNA_telefone = "telefone";
    public static final String COLUNA_endereco = "endereço";
    public static final String COLUNA_bairro = "bairro";
    public static final String COLUNA_cidade = "cidade";
    public static final String COLUNA_estado = "estado";

    public BancoDados(@Nullable Context context) {
        super(context, BANCO_AGENDA, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CRIAR_TABELA = "CREATE TABLE " + TABELA_PESSOA +  "(" + COLUNA_codigo
                + "INTEGER PRIMARY KEY, " + COLUNA_nome + " TEXT," + COLUNA_email + " TEXT,"
                + COLUNA_telefone + " TEXT," + COLUNA_celular + " TEXT," + COLUNA_endereco + " TEXT,"
                + COLUNA_bairro + " TEXT," + COLUNA_cidade + " TEXT," + COLUNA_estado + " TEXT)";

        db.execSQL(CRIAR_TABELA); //Executa a String CRIAR_TABELA

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void addPessoa(Pessoa pessoa){ //responsável por adicionar as pessoas que cadastramos na base de dados

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(COLUNA_nome, pessoa.getNome());
        valor.put(COLUNA_email, pessoa.getEmail());
        valor.put(COLUNA_celular, pessoa.getCelular());
        valor.put(COLUNA_telefone, pessoa.getTelefone());
        valor.put(COLUNA_endereco, pessoa.getEndereco());
        valor.put(COLUNA_bairro, pessoa.getBairro());
        valor.put(COLUNA_cidade, pessoa.getCidade());
        valor.put(COLUNA_estado, pessoa.getEstado());

        db.insert(TABELA_PESSOA, null, valor);
        db.close();

    }

    void apagarPessoa(Pessoa pessoa){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_PESSOA, COLUNA_codigo + " = ?", new String[]{ //A '?' é o parâmetro que vai ser passado
                String.valueOf(pessoa.getCodigo())});

        db.close();
    }


    Pessoa selecionarPessoa(int codigo) { //Vou chamar uma pessoa na minha agenda pelo código 5, por exemplo. Uma consulta

        SQLiteDatabase db = this.getReadableDatabase(); //Consulta na tabela pessoa
        Cursor cursor = db.query(TABELA_PESSOA, new String[]{COLUNA_codigo, COLUNA_nome, COLUNA_email,
        COLUNA_celular, COLUNA_telefone, COLUNA_endereco, COLUNA_bairro, COLUNA_cidade, COLUNA_estado},
        COLUNA_codigo + " = ?", new String[]{String.valueOf(codigo)}, null, null, null,
         null);
        //1 parametro - tabela_pessoa, 2 - A string com todos os campos, 3 - o código que vou receber

        if(cursor != null) {
            cursor.moveToFirst(); //Se o cursor for diferente de nulo, vai se mover pro primeiro
        }

        Pessoa pessoa = new Pessoa(Integer.parseInt(cursor.getString(0)), cursor.getString(1),         //Estou indexando a pessoa na minha tabela, convertendo de string pra inteiro
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8));

        return pessoa;
    }

    void atualizarPessoa(Pessoa pessoa) {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues valor = new ContentValues();

                valor.put(COLUNA_nome, pessoa.getNome());
                valor.put(COLUNA_email, pessoa.getEmail());
                valor.put(COLUNA_celular, pessoa.getCelular());
                valor.put(COLUNA_telefone, pessoa.getTelefone());
                valor.put(COLUNA_endereco, pessoa.getEndereco());
                valor.put(COLUNA_bairro, pessoa.getBairro());
                valor.put(COLUNA_cidade, pessoa.getCidade());
                valor.put(COLUNA_estado, pessoa.getEstado());

            db.update(TABELA_PESSOA, valor, COLUNA_codigo + " = ?", new String[]{
                    String.valueOf(pessoa.getCodigo())});
            db.close();
    }

    public List<Pessoa> listaPessoa(){

        List<Pessoa> pessoaList = new ArrayList<Pessoa>();

        String query = "SELECT * FROM " + TABELA_PESSOA;

        SQLiteDatabase db = this.getWritableDatabase(); //Pega todos os registro do bd
        Cursor c = db.rawQuery(query, null); //2 parametros

        if(c.moveToFirst()) {  //Irá verificar se há registros, se sim, irá pro 1

            do {
                Pessoa pessoa = new Pessoa();
                pessoa.setCodigo(Integer.parseInt(c.getString(0))); //Vou converter para String
                pessoa.setNome(c.getString(1));
                pessoa.setEmail(c.getString(2));
                pessoa.setCelular(c.getString(3));
                pessoa.setTelefone(c.getString(4));
                pessoa.setEndereco(c.getString(5));
                pessoa.setBairro(c.getString(6));
                pessoa.setCidade(c.getString(7));
                pessoa.setEstado(c.getString(8));

                pessoaList.add(pessoa);

            } while (c.moveToNext()); //Enquanto achar registros, irá mover
        }
            return pessoaList;
        }
    }