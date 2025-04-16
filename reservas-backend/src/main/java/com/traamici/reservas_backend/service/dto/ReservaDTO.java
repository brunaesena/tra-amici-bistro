package com.traamici.reservas_backend.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.traamici.reservas_backend.model.Reserva;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservaDTO {

    private Long id;

    private String clienteNome;

    private String clienteEmail;

    private String clienteTelefone;

    private int quantidadePessoas;

    private int numeroMesa;

    private LocalDateTime dataHoraReserva;

    @Enumerated(EnumType.STRING)
    private Reserva.StatusReserva statusReserva;

    public enum StatusReserva {
        CONFIRMADA, CANCELADA, CONCLUIDA
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteTelefone() {
        return clienteTelefone;
    }

    public void setClienteTelefone(String clienteTelefone) {
        this.clienteTelefone = clienteTelefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public LocalDateTime getDataHoraReserva() {
        return dataHoraReserva;
    }

    public void setDataHoraReserva(LocalDateTime dataHoraReserva) {
        this.dataHoraReserva = dataHoraReserva;
    }

    public Reserva.StatusReserva getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(Reserva.StatusReserva statusReserva) {
        this.statusReserva = statusReserva;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
}
