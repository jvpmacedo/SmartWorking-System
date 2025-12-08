package com.example.smartworkingsystem.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private int id;
    private Usuario usuario;
    private Espaco espaco;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private TipoReserva tipoReserva;
    private int duracao; // Added duracao field
    private double valorTotal;

    public Reserva(Usuario usuario, Espaco espaco, LocalDateTime dataInicio, int duracao, TipoReserva tipoReserva) {
        this.usuario = usuario;
        this.espaco = espaco;
        this.dataInicio = dataInicio;
        this.duracao = duracao; // Set duracao
        this.tipoReserva = tipoReserva;

        switch (tipoReserva) {
            case HORA:
                this.dataFim = dataInicio.plusHours(duracao);
                this.valorTotal = duracao * espaco.getPrecoHora();
                break;
            case DIARIA:
                this.dataFim = dataInicio.plusDays(duracao);
                this.valorTotal = duracao * espaco.getPrecoDiaria();
                break;
            case MENSAL:
                this.dataFim = dataInicio.plusMonths(duracao);
                this.valorTotal = duracao * espaco.getPrecoMensal();
                break;
        }
    }

    public int getId() { return id; }
    public Espaco getEspaco() { return espaco; }
    public Usuario getUsuario() { return usuario; }
    public LocalDateTime getInicio() { return dataInicio; }
    public LocalDateTime getFim() { return dataFim; }
    public TipoReserva getTipoReserva() { return tipoReserva; }
    public int getDuracao() { return duracao; } // Added getDuracao method
    public double getValorTotal() { return valorTotal; }

    public void setId(int id) { this.id = id; }
    public void setDuracao(int duracao) { this.duracao = duracao; } // Added setDuracao method

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Reserva: " + espaco.getNome() + " | Início: " + dataInicio.format(fmt) + " | Fim: " + dataFim.format(fmt);
    }

    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }
}
