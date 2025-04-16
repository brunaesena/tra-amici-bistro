package com.traamici.reservas_backend.controller;

import com.traamici.reservas_backend.service.ClienteService;
import com.traamici.reservas_backend.service.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @PostMapping("/registrar")
    public ResponseEntity<ClienteDTO> registrar(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO dto = clienteService.registrarCliente(clienteDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<ClienteDTO> editar(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO dto = clienteService.editarPerfil(clienteDTO);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPerfil(@PathVariable Long id) {
        boolean sucesso = clienteService.deletarPerfil(id);
        if (!sucesso) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Perfil deletado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

}
