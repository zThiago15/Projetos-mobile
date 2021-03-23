package com.example.agenda;

// Por quê eu criei a classe modelo? Serve para integrar a classe com a base de dados e com o front-end do projeto

public class Pessoa {

        int codigo; //Controle de registros
        String nome;
        String email;
        String celular;
        String telefone;
        String endereco;
        String bairro;
        String cidade;
        String estado;

        //Criar 3 métodos para utilizarmos no crudge

    //Botão direito: Generate - método construtor

    public Pessoa() {
    }

    // Selecionei todos os atributos que quero no meu método construtor
    public Pessoa(int codigo, String nome, String email, String celular, String telefone, String endereco, String bairro, String cidade, String estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.telefone = telefone;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    //Criei outro mas sem o código int
    public Pessoa(String nome, String email, String celular, String telefone, String endereco, String bairro, String cidade, String estado) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.telefone = telefone;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    // Criei um GET e um SET para todos. Para receber e enviar informação. Botão direito -> generate ->


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}