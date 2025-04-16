package com.traamici.reservas_backend.service.dto;

import com.traamici.reservas_backend.model.Mesa;
import jakarta.persistence.*;

public class MesaDTO {

    private Long id;

    private int numeroMesa;

    private int capacidade;

    private Mesa.StatusMesa status;

    public enum StatusMesa {
        DISPONIVEL,
        RESERVADA,
        INDISPONIVEL
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Mesa.StatusMesa getStatus() {
        return status;
    }

    public void setStatus(Mesa.StatusMesa status) {
        this.status = status;
    }
}
