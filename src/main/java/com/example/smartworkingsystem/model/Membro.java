package com.example.smartworkingsystem.model;

public class Membro extends Usuario {

    public Membro(int id, String nome, String email, String senha, String telefone) {
        super(id, nome, email, senha, telefone);
    }

    public Membro(String nome, String email, String senha) {
        super(nome, email, senha);
    }
}
