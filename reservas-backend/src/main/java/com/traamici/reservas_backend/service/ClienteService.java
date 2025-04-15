package com.traamici.reservas_backend.service;

import com.traamici.reservas_backend.service.dto.ClienteDTO;
import com.traamici.reservas_backend.model.Cliente;
import com.traamici.reservas_backend.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository repository;

    public ClienteDTO registrarCliente(String nome, String email,  String telefone) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setDataCadastro(new Date());

        Cliente salvo = repository.save(cliente);
        return toDTO(salvo);
    }

    public ClienteDTO editarPerfil(Long clienteId, String novoNome, String novoEmail, String novoTelefone) {
        Optional<Cliente> opt = repository.findById(clienteId);
        if (opt.isEmpty()) return null;

        Cliente cliente = opt.get();
        cliente.setNome(novoNome);
        cliente.setEmail(novoEmail);
        cliente.setTelefone(novoTelefone);

        Cliente atualizado = repository.save(cliente);
        return toDTO(atualizado);
    }

    public boolean deletarPerfil(Long clienteId) {
        Optional<Cliente> opt = repository.findById(clienteId);
        if (opt.isEmpty()) return false;

        repository.deleteById(clienteId);
        return true;
    }

    public List<ClienteDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

//    public String visualizarReservas(Long clienteId) {
//        return "Reservas do cliente ID: " + clienteId + " (ainda não implementado)";
//    }


    private ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setTelefone(cliente.getTelefone());
        dto.setDataCadastro(cliente.getDataCadastro());
        return dto;
    }
}
