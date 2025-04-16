package com.traamici.reservas_backend.repository;

import com.traamici.reservas_backend.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMesaRepository extends JpaRepository<Mesa, Long> {
    Optional<Mesa> findByNumeroMesa(int numeroMesa);

    List<Mesa> findByCapacidade(int capacidade);
}
