package com.example.smartworkingsystem.controller;

import com.example.smartworkingsystem.model.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/espacos")
public class EspacoController {

    public static List<Espaco> espacos = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Espaco>> listarEspacos() {
        return new ResponseEntity<>(espacos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Espaco> cadastrarEspaco(@RequestBody Espaco espaco) {
        // Simple authorization check (in a real app, use Spring Security)
        // For now, let's assume any logged in user can add spaces.
        // A better check would be to verify the user is an admin.
        espacos.add(espaco);
        return new ResponseEntity<>(espaco, HttpStatus.CREATED);
    }
}
