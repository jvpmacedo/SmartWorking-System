package com.example.smartworkingsystem.model;

public class Espaco {
    private int id;
    private String nome;
    private String tipo; // Mesa ou Sala
    private double precoHora;
    private double precoDiaria;
    private double precoMensal;
    private String fotoBase64;
    private String endereco;
    private String politicaCancelamento;

    public Espaco(int id, String nome, String tipo, double precoHora, double precoDiaria, double precoMensal, String fotoBase64, String endereco, String politicaCancelamento) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.precoHora = precoHora;
        this.precoDiaria = precoDiaria;
        this.precoMensal = precoMensal;
        this.fotoBase64 = fotoBase64;
        this.endereco = endereco;
        this.politicaCancelamento = politicaCancelamento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; } // Added setId method
    public String getNome() { return nome; }
    public double getPrecoHora() { return precoHora; }
    public double getPrecoDiaria() { return precoDiaria; }
    public double getPrecoMensal() { return precoMensal; }
    public String getFotoBase64() { return fotoBase64; }
    public String getEndereco() { return endereco; }
    public String getPoliticaCancelamento() { return politicaCancelamento; }

    public void setNome(String nome) { this.nome = nome; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setPrecoHora(double precoHora) { this.precoHora = precoHora; }
    public void setPrecoDiaria(double precoDiaria) { this.precoDiaria = precoDiaria; }
    public void setPrecoMensal(double precoMensal) { this.precoMensal = precoMensal; }
    public void setFotoBase64(String fotoBase64) { this.fotoBase64 = fotoBase64; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setPoliticaCancelamento(String politicaCancelamento) { this.politicaCancelamento = politicaCancelamento; }


    @Override
    public String toString() {
        return "ID: " + id + " | " + nome + " (" + tipo + ") - R$ " + precoHora + "/hora";
    }
}
