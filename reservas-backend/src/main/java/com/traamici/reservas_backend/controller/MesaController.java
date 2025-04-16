package com.traamici.reservas_backend.controller;

import com.traamici.reservas_backend.service.MesaService;
import com.traamici.reservas_backend.service.dto.MesaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mesa", produces = MediaType.APPLICATION_JSON_VALUE)
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @PostMapping
    public ResponseEntity<MesaDTO> registrarMesa(@RequestBody MesaDTO dto) {
        return ResponseEntity.ok(mesaService.registrarMesa(dto));
    }

    @PutMapping
    public ResponseEntity<MesaDTO> editarMesa(@RequestBody MesaDTO dto) {
        MesaDTO atualizada = mesaService.editarMesa(dto);
        if (atualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMesa(@PathVariable Long id) {
        boolean deletada = mesaService.deletarMesa(id);
        if (!deletada) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MesaDTO>> listarTodas() {
        return ResponseEntity.ok(mesaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaDTO> buscarPorId(@PathVariable Long id) {
        MesaDTO mesa = mesaService.buscarPorId(id);
        if (mesa == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mesa);
    }
}
