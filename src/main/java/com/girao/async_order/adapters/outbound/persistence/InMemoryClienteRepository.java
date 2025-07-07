package com.girao.async_order.adapters.outbound.persistence;

import com.girao.async_order.domain.entities.Cliente;
import com.girao.async_order.domain.repositories.ClienteRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryClienteRepository implements ClienteRepository {
    private final ConcurrentHashMap<String, Cliente> clientes = new ConcurrentHashMap<>();

    @Override
    public Optional<Cliente> findById(String id) {
        return Optional.ofNullable(clientes.get(id));
    }

    @Override
    public Cliente save(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(clientes.values());
    }

    @Override
    public void deleteById(String id) {
        clientes.remove(id);
    }

    @Override
    public boolean existsById(String id) {
        return clientes.containsKey(id);
    }


}
