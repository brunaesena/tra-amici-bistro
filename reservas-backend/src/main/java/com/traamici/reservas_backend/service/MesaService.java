package com.traamici.reservas_backend.service;

import com.traamici.reservas_backend.model.Cliente;
import com.traamici.reservas_backend.model.Mesa;
import com.traamici.reservas_backend.repository.IMesaRepository;
import com.traamici.reservas_backend.service.dto.MesaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MesaService {

    @Autowired
    private IMesaRepository repository;

    public MesaDTO registrarMesa(MesaDTO dto) {
        Mesa mesa = new Mesa();
        mesa.setNumeroMesa(dto.getNumeroMesa());
        mesa.setCapacidade(dto.getCapacidade());
        mesa.setStatus(dto.getStatus());

        Mesa salva = repository.save(mesa);
        return toDTO(salva);
    }

    public MesaDTO editarMesa(MesaDTO dto) {
        Optional<Mesa> opt = repository.findById(dto.getId());
        if (opt.isEmpty()) return null;

        Mesa mesa = opt.get();
        mesa.setNumeroMesa(dto.getNumeroMesa());
        mesa.setCapacidade(dto.getCapacidade());
        mesa.setStatus(dto.getStatus());

        Mesa atualizada = repository.save(mesa);
        return toDTO(atualizada);
    }

    public void editarStatusMesa(Mesa mesa) {
        Optional<Mesa> optMesa = repository.findById(mesa.getId());
        Mesa mesaEncontrada = optMesa.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        mesaEncontrada.setStatus(Mesa.StatusMesa.INDISPONIVEL);
        repository.save(mesaEncontrada);
    }

    public boolean deletarMesa(Long mesaId) {
        Optional<Mesa> opt = repository.findById(mesaId);
        if (opt.isEmpty()) return false;

        repository.deleteById(mesaId);
        return true;
    }

    public List<MesaDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MesaDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    private MesaDTO toDTO(Mesa mesa) {
        MesaDTO dto = new MesaDTO();
        dto.setId(mesa.getId());
        dto.setNumeroMesa(mesa.getNumeroMesa());
        dto.setCapacidade(mesa.getCapacidade());
        dto.setStatus(mesa.getStatus());
        return dto;
    }
}
