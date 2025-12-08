package com.example.smartworkingsystem.controller;

import com.example.smartworkingsystem.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    public static List<Reserva> reservas = new ArrayList<>();
    private static int nextId = 1; // Start IDs from 1 for new reservations

    @PostMapping
    public ResponseEntity<String> fazerReserva(@RequestBody Reserva reserva) {
        // Find the complete Espaco object based on the ID provided in the request body
        Espaco espacoCompleto = EspacoController.espacos.stream()
                .filter(e -> e.getId() == reserva.getEspaco().getId())
                .findFirst().orElse(null);

        if (espacoCompleto == null) {
            return new ResponseEntity<>("Espaço não encontrado", HttpStatus.NOT_FOUND);
        }

        // Create a new Reserva instance with the complete Espaco object
        Reserva novaReserva = new Reserva(
                reserva.getUsuario(),
                espacoCompleto, // Use the complete Espaco object here
                reserva.getInicio(), // Corrected: use getInicio() from incoming reserva
                reserva.getDuracao(), // Use getDuracao() from incoming reserva
                reserva.getTipoReserva()
        );

        boolean conflito = reservas.stream().anyMatch(r -> r.getEspaco().getId() == novaReserva.getEspaco().getId() &&
            r.getInicio().isBefore(novaReserva.getFim()) && 
            r.getFim().isAfter(novaReserva.getInicio()));

        if (conflito) {
            return new ResponseEntity<>("Data Indisponível para o período selecionado", HttpStatus.CONFLICT);
        } else {
            novaReserva.setId(nextId++);
            reservas.add(novaReserva);
            return new ResponseEntity<>("Reserva efetuada com sucesso!", HttpStatus.CREATED);
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Reserva>> getReservasPorUsuario(@PathVariable int id) {
        List<Reserva> reservasDoUsuario = reservas.stream()
                .filter(r -> r.getUsuario().getId() == id)
                .map(reserva -> {
                    Espaco espacoCompleto = EspacoController.espacos.stream()
                            .filter(e -> e.getId() == reserva.getEspaco().getId())
                            .findFirst().orElse(null);
                    reserva.setEspaco(espacoCompleto);
                    return reserva;
                }).collect(Collectors.toList());
        return new ResponseEntity<>(reservasDoUsuario, HttpStatus.OK);
    }

    @GetMapping("/espaco/{idEspaco}")
    public ResponseEntity<List<Reserva>> getReservasPorEspaco(@PathVariable int idEspaco) {
        List<Reserva> reservasDoEspaco = reservas.stream()
                .filter(r -> r.getEspaco().getId() == idEspaco)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reservasDoEspaco, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable int id) {
        boolean removed = reservas.removeIf(r -> r.getId() == id);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
