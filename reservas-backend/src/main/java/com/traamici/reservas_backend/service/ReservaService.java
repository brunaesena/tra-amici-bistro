package com.traamici.reservas_backend.service;

import com.traamici.reservas_backend.model.Cliente;
import com.traamici.reservas_backend.model.Mesa;
import com.traamici.reservas_backend.model.Reserva;
import com.traamici.reservas_backend.repository.IClienteRepository;
import com.traamici.reservas_backend.repository.IMesaRepository;
import com.traamici.reservas_backend.repository.IReservaRepository;
import com.traamici.reservas_backend.service.dto.ClienteDTO;
import com.traamici.reservas_backend.service.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    @Autowired
    private IReservaRepository reservaRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IMesaRepository mesaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MesaService mesaService;

    public ReservaDTO criarReserva(ReservaDTO dto) {
        try {
            clienteService.buildCliente(dto.getClienteEmail(), dto.getClienteNome(), dto.getClienteTelefone());
            Optional<Cliente> optCliente = clienteRepository.findByEmail(dto.getClienteEmail());

            List<Mesa> mesas = mesaRepository.findByCapacidade(dto.getQuantidadePessoas());

            Mesa mesaDisponivel = mesas.stream()
                    .filter(mesa -> mesa.getStatus().toString().equals("DISPONIVEL"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Nenhuma mesa disponível no momento"));

            Cliente cliente = optCliente.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            Reserva reserva = new Reserva();
            reserva.setCliente(cliente);
            reserva.setMesa(mesaDisponivel);
            reserva.setDataHoraReserva(dto.getDataHoraReserva());
            reserva.setQuantidadePessoas(dto.getQuantidadePessoas());
            reserva.setStatusReserva(Reserva.StatusReserva.CONFIRMADA);
            Reserva salva = reservaRepository.save(reserva);
            mesaService.editarStatusMesa(mesaDisponivel);
            return toDTO(salva);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//    public ReservaDTO editarReserva(ReservaDTO dto) {
//        Optional<Reserva> opt = reservaRepository.findById(dto.getId());
//        if (opt.isEmpty()) return null;
//
//        Reserva reserva = opt.get();
//        reserva.setDataReserva(dto.getDataReserva());
//        reserva.setHorarioReserva(dto.getHorarioReserva());
//        reserva.setStatusReserva(dto.getStatusReserva());
//        reserva.setStatusPagamento(dto.getStatusPagamento());
//
//        Optional<Mesa> mesaOpt = mesaRepository.findById(dto.getMesaId());
//        mesaOpt.ifPresent(reserva::setMesa);
//
//        Reserva atualizada = reservaRepository.save(reserva);
//        return toDTO(atualizada);
//    }

    public boolean cancelarReserva(Long id) {
        Optional<Reserva> opt = reservaRepository.findById(id);
        if (opt.isEmpty()) return false;

        reservaRepository.deleteById(id);
        return true;
    }

    public List<ReservaDTO> listarReservas() {
        return reservaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ReservaDTO> visualizarReservasPorCliente(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ReservaDTO toDTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setClienteNome(reserva.getCliente().getNome());
        dto.setClienteEmail(reserva.getCliente().getEmail());
        dto.setClienteTelefone(reserva.getCliente().getTelefone());
        dto.setNumeroMesa(reserva.getMesa().getNumeroMesa());
        dto.setDataHoraReserva(reserva.getDataHoraReserva());
        dto.setQuantidadePessoas(reserva.getQuantidadePessoas());
        dto.setStatusReserva(reserva.getStatusReserva());
        return dto;
    }
}