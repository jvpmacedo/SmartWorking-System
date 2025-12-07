package com.example.smartworkingsystem.model;

public class Usuario {
    protected int id;
    protected String nome;
    protected String email;
    protected String senha;
    protected String telefone; // Novo campo

    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String senha, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getNome() { return nome; }
    public int getId() { return id; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    // Metodo para facilitar a exibição
    @Override
    public String toString() {
        return id + " - " + nome + " (" + email + ")";
    }
}
