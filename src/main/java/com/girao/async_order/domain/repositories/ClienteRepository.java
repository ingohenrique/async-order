package com.girao.async_order.domain.repositories;

import com.girao.async_order.domain.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    Cliente save(Cliente cliente);
    Optional<Cliente> findById(String id);
    List<Cliente> findAll();
    void deleteById(String id);
    boolean existsById(String id);

}
