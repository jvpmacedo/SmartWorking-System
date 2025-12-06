package com.example.smartworkingsystem;

import com.example.smartworkingsystem.controller.*;
import com.example.smartworkingsystem.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataSeeder {

    @PostConstruct
    public void seedData() {
        // Seed Users
        Usuario admin = new Administrador("João Landlord", "admin@email.com", "123");
        Usuario comum = new Membro("João Worker", "cliente@email.com", "123");
        UsuarioController.usuarios.add(admin);
        UsuarioController.usuarios.add(comum);

        // Seed Spaces
        Espaco espaco1 = new Espaco(0, "Sala de Reunião A", null, 0);
        Espaco espaco2 = new Espaco(0, "Mesa Compartilhada B", null, 0);
        EspacoController.espacos.add(espaco1);
        EspacoController.espacos.add(espaco2);
    }
}
