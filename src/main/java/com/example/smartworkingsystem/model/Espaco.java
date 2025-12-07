package com.example.smartworkingsystem.model;

public class Espaco {
    private int id;
    private String nome;
    private String tipo; // Mesa ou Sala
    private double precoHora;
    private String fotoBase64;
    private String endereco;

    public Espaco(int id, String nome, String tipo, double precoHora, String fotoBase64, String endereco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.precoHora = precoHora;
        this.fotoBase64 = fotoBase64;
        this.endereco = endereco;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getFotoBase64() { return fotoBase64; }
    public String getEndereco() { return endereco; }

    public void setNome(String nome) { this.nome = nome; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setPrecoHora(double precoHora) { this.precoHora = precoHora; }
    public void setFotoBase64(String fotoBase64) { this.fotoBase64 = fotoBase64; }
    public void setEndereco(String endereco) { this.endereco = endereco; }


    @Override
    public String toString() {
        return "ID: " + id + " | " + nome + " (" + tipo + ") - R$ " + precoHora + "/hora";
    }
}
