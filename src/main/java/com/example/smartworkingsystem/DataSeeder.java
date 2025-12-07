package com.example.smartworkingsystem;

import com.example.smartworkingsystem.controller.*;
import com.example.smartworkingsystem.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class DataSeeder {

    @PostConstruct
    public void seedData() {
        // Seed Users
        Usuario admin = new Administrador("João Landlord", "admin@email.com", "123456");
        Usuario comum = new Membro("João Worker", "cliente@email.com", "123456");
        UsuarioController.usuarios.add(admin);
        UsuarioController.usuarios.add(comum);

        // Seed Spaces
        Espaco espaco1 = new Espaco(0, "Sala de Reunião A", "Sala", 50.0, 350.0, 5000.0, "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==", "Av. Paulista, 123");
        Espaco espaco2 = new Espaco(1, "Mesa Compartilhada B", "Mesa", 25.0, 150.0, 2000.0, "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==", "Rua Augusta, 456");
        EspacoController.espacos.add(espaco1);
        EspacoController.espacos.add(espaco2);

        // Seed Reservations
        Reserva reserva1 = new Reserva(comum, espaco1, LocalDateTime.now().plusDays(1), 2, TipoReserva.HORA);
        Reserva reserva2 = new Reserva(comum, espaco2, LocalDateTime.now().plusDays(3), 1, TipoReserva.DIARIA);
        ReservaController.reservas.add(reserva1);
        ReservaController.reservas.add(reserva2);
    }
}
