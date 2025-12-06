package com.example.smartworkingsystem.model;

public class Espaco {
    private int id;
    private String nome;
    private String tipo; // Mesa ou Sala
    private double precoHora;

    public Espaco(int id, String nome, String tipo, double precoHora) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.precoHora = precoHora;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nome + " (" + tipo + ") - R$ " + precoHora + "/hora";
    }
}
