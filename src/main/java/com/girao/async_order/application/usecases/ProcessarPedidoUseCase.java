package com.girao.async_order.application.usecases;

import com.girao.async_order.domain.entities.Pedido;
import com.girao.async_order.domain.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcessarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public ProcessarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Optional<Pedido> execute(String pedidoId) {
        if (pedidoId == null || pedidoId.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do pedido não pode ser nulo ou vazio");
        }

        Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoId);

        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.processar();
            return Optional.of(pedidoRepository.save(pedido));
        }

        return Optional.empty();
    }
}
