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

    @PostMapping
    public ResponseEntity<String> fazerReserva(@RequestBody Reserva reserva) {
        boolean conflito = reservas.stream().anyMatch(r -> r.getEspaco().equals(reserva.getEspaco()) &&
            r.getInicio().isBefore(reserva.getFim()) && 
            r.getFim().isAfter(reserva.getInicio()));

        if (conflito) {
            return new ResponseEntity<>("Horário indisponível.", HttpStatus.CONFLICT);
        } else {
            reservas.add(reserva);
            return new ResponseEntity<>("Reserva efetuada com sucesso!", HttpStatus.CREATED);
        }
    }
}
