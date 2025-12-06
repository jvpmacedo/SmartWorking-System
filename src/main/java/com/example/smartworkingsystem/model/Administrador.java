package com.example.smartworkingsystem.model;
// Arquivo: Administrador.java
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {
    private String razaoSocial;
    private String cnpj;
    private String emailEmpresarial; // Pode ser diferente do email de login
    private String enderecoEspaco;
    private String descricaoEspaco;
    private List<String> fotosEspaco; // Lista de nomes de arquivos ou URLs das fotos

    public Administrador(int id, String nome, String email, String senha, String telefone,
                         String razaoSocial, String cnpj, String emailEmpresarial,
                         String enderecoEspaco, String descricaoEspaco) {
        super(id, nome, email, senha, telefone); // Passa dados básicos para a classe Pai
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.emailEmpresarial = emailEmpresarial;
        this.enderecoEspaco = enderecoEspaco;
        this.descricaoEspaco = descricaoEspaco;
        this.fotosEspaco = new ArrayList<>();
    }

    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
        this.fotosEspaco = new ArrayList<>();
    }

    public void adicionarFoto(String nomeArquivo) {
        this.fotosEspaco.add(nomeArquivo);
    }

    public String getNomeEmpresa() {
        return razaoSocial;
    }

    public void exibirDetalhesEmpresa() {
        System.out.println("--- EMPRESA/ESPAÇO ---");
        System.out.println("Empresa: " + razaoSocial + " (CNPJ: " + cnpj + ")");
        System.out.println("Endereço: " + enderecoEspaco);
        System.out.println("Sobre: " + descricaoEspaco);
        System.out.println("Fotos: " + fotosEspaco.toString());
    }
}
