package com.example.produto;

public class Produto {

    int codigo; //Controle de registros
    String codigobar;
    String nome;
    String preco;
    String categoria;

    //Criar 3 métodos para utilizarmos no crudge

    //1 - Criei um método só com a classe Produto
    public Produto() {
    }

    //2 - Selecionei todos os atributos que quero no meu método construtor
    public Produto(int codigo, String codigobar, String nome, String preco, String categoria) {
        this.codigo = codigo;
        this.codigobar = codigobar;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    //3 - Criei outro método construtor mas sem o código int
    public Produto(String codigobar, String nome, String preco, String categoria) {
        this.codigobar = codigobar;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto(int parseInt, String string) {
    }

    // Criei um GET e um SET para todos. Para receber(get) e enviar(set) informação. Botão direito -> generate -> getter and setter
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigobar() {
        return codigobar;
    }

    public void setCodigobar(String codigobar) {
        this.codigobar = codigobar;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
