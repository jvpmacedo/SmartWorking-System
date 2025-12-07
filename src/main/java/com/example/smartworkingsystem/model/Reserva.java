package com.example.smartworkingsystem.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Reserva {
    private Usuario usuario;
    private Espaco espaco;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public Reserva(Usuario usuario, Espaco espaco, LocalDateTime dataInicio, int horasDuracao) {
        this.usuario = usuario;
        this.espaco = espaco;
        this.dataInicio = dataInicio;
        this.dataFim = dataInicio.plusHours(horasDuracao);
    }

    public Espaco getEspaco() { return espaco; }
    public Usuario getUsuario() { return usuario; }
    public LocalDateTime getInicio() { return dataInicio; }
    public LocalDateTime getFim() { return dataFim; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Reserva: " + espaco.getNome() + " | Início: " + dataInicio.format(fmt) + " | Fim: " + dataFim.format(fmt);
    }
}
