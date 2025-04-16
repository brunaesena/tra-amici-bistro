package com.traamici.reservas_backend.controller;

import com.traamici.reservas_backend.service.ReservaService;
import com.traamici.reservas_backend.service.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reserva", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDTO> criarReserva(@RequestBody ReservaDTO dto) {
        try{
            ReservaDTO novaReserva = reservaService.criarReserva(dto);
            return ResponseEntity.ok(novaReserva);
        }catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @PutMapping
//    public ResponseEntity<ReservaDTO> editarReserva(@RequestBody ReservaDTO dto) {
//        ReservaDTO atualizada = reservaService.editarReserva(dto);
//        if (atualizada == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(atualizada);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        boolean cancelada = reservaService.cancelarReserva(id);
        if (!cancelada) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarTodasReservas() {
        return ResponseEntity.ok(reservaService.listarReservas());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ReservaDTO>> visualizarReservasPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(reservaService.visualizarReservasPorCliente(clienteId));
    }
}
