package com.girao.async_order.domain.repositories;

import com.girao.async_order.domain.entities.Pedido;

import java.util.Optional;
import java.util.List;

public interface PedidoRepository {

    Pedido save(Pedido pedido);
    Optional<Pedido> findById(String id);
    List<Pedido> findAll();
    void deleteById(String id);
    boolean existsById(String id);

}
