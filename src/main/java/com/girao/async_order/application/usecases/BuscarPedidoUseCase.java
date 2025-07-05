package com.girao.async_order.application.usecases;

import com.girao.async_order.domain.entities.Pedido;
import com.girao.async_order.domain.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public BuscarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Optional<Pedido> execute(String pedidoId) {
        if (pedidoId == null || pedidoId.trim().isEmpty()) {
            throw new IllegalArgumentException("ID de Pedido não pode ser nulo ou vazio");
        }

        return pedidoRepository.findById(pedidoId);
    }
}
