package com.example.produto;

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
    public static final String BANCO_PRODUTO = "bd_produto";

    public static final String TABELA_PRODUTO = "tb_produto";

    public static final String COLUNA_codigo = "codigo";
    public static final String COLUNA_codigobar = "codigo_de_barras";
    public static final String COLUNA_nome = "nome";
    public static final String COLUNA_preco = "preco";
    public static final String COLUNA_categoria = "categoria";

    public BancoDados(@Nullable Context context) {
        super(context, BANCO_PRODUTO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CRIAR_TABELA = "CREATE TABLE " + TABELA_PRODUTO + "(" + COLUNA_codigo
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_codigobar + " TEXT," + COLUNA_nome + " TEXT,"
                + COLUNA_preco + " TEXT," + COLUNA_categoria + " TEXT)";

        db.execSQL(CRIAR_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void addProduto(Produto produto){ //Responsável por adicionar os produtos que cadastramos no banco de dados
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(COLUNA_codigobar, produto.getCodigobar());
        valor.put(COLUNA_nome, produto.getNome());
        valor.put(COLUNA_preco, produto.getPreco());
        valor.put(COLUNA_categoria, produto.getCategoria());

        db.insert(TABELA_PRODUTO, null, valor);
        db.close();
    }

    void apagarProduto(Produto produto){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_PRODUTO, COLUNA_codigo + " = ?", new String[]{
                String.valueOf(produto.getCodigo())});

        db.close();
    }

    Produto selecionaProduto(int codigo){

        SQLiteDatabase db = this.getReadableDatabase(); //Consulta na tabela produto
        Cursor cursor = db.query(TABELA_PRODUTO, new String[]{
                        COLUNA_codigo, COLUNA_codigobar, COLUNA_nome, COLUNA_preco,
                        COLUNA_categoria}, COLUNA_codigo + " = ?", new String[]{String.valueOf(codigo)},
                null, null, null, null);
        //1 parametro - tabela_pessoa, 2 - A string com todos os campos, 3 - o código que vou receber

        if(cursor != null) {
            cursor.moveToFirst();
        }

        Produto produto = new Produto(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return produto;
    }

    void atualizarProduto(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(COLUNA_codigobar, produto.getCodigobar());
        valor.put(COLUNA_nome, produto.getNome());
        valor.put(COLUNA_preco, produto.getPreco());
        valor.put(COLUNA_categoria, produto.getCategoria());

        db.update(TABELA_PRODUTO, valor, COLUNA_codigo + " = ?", new String[]{
                String.valueOf(produto.getCodigo())});
        db.close();
    }

    public List<Produto> listaProduto(){

        List<Produto> produtoList = new ArrayList<Produto>();

        String query = "SELECT * FROM " + TABELA_PRODUTO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()) {

            do {
                Produto produto = new Produto();
                produto.setCodigo(Integer.parseInt(c.getString(0)!=null?c.getString(0):"0")); //Vou converter para Integer! Código do professor: produto.setCodigo(Integer.parseInt(c.getString(0)!=null?c.getString(0):"0"));
                produto.setCodigobar(c.getString(1));
                produto.setNome(c.getString(2));
                produto.setPreco(c.getString(3));
                produto.setCategoria(c.getString(4));

                produtoList.add(produto);

            } while (c.moveToNext()); //Enquanto achar registros, irá mover
        }
        return produtoList;
    }
}
