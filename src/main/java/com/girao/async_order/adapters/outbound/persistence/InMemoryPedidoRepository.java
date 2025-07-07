package com.girao.async_order.adapters.outbound.persistence;

import com.girao.async_order.domain.entities.Pedido;
import com.girao.async_order.domain.repositories.PedidoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryPedidoRepository implements PedidoRepository {
    private final ConcurrentHashMap<String, Pedido> pedidos = new ConcurrentHashMap<>();

    @Override
    public Pedido save(Pedido pedido) {
        pedidos.put(pedido.getId(), pedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> findById(String id) {
        return Optional.ofNullable(pedidos.get(id));
    }

    @Override
    public List<Pedido> findAll() {
        return new ArrayList<>(pedidos.values());
    }

    @Override
    public void deleteById(String id) {
        pedidos.remove(id);
    }

    @Override
    public boolean existsById(String id) {
        return pedidos.containsKey(id);
    }
}
