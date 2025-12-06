package com.example.smartworkingsystem.controller;

import com.example.smartworkingsystem.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    public static List<Usuario> usuarios = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        usuarios.add(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarios.stream()
                .filter(u -> u.getEmail().equals(usuario.getEmail()) && u.getSenha().equals(usuario.getSenha()))
                .findFirst();

        if (usuarioOptional.isPresent()) {
            return new ResponseEntity<>("Login bem-sucedido!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email ou senha inválidos.", HttpStatus.UNAUTHORIZED);
        }
    }
}
