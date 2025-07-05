package com.girao.async_order.application.usecases;

import com.girao.async_order.domain.entities.Item;
import com.girao.async_order.domain.entities.Pedido;
import com.girao.async_order.domain.repositories.IdGenerator;
import com.girao.async_order.domain.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriarPedidoUseCase {

    private final PedidoRepository pedidoRepository;
    private final IdGenerator idGenerator;

    public CriarPedidoUseCase(PedidoRepository pedidoRepository, IdGenerator idGenerator) {
        this.pedidoRepository = pedidoRepository;
        this.idGenerator = idGenerator;
    }

    public Pedido execute(String clienteId, List<Item> itens) {
        validateInput(clienteId, itens);

        String pedidoId = idGenerator.generateId();
        Pedido pedido = new Pedido(pedidoId, clienteId, itens);

        return pedidoRepository.save(pedido);
    }

    private void validateInput(String clienteId, List<Item> itens) {
        if (clienteId == null || clienteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Cliente ID não pode ser nulo ou vazio");
        }

        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula ou vazia");
        }

        for (Item item : itens) {
            if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade do item deve ser maior que zero");
            }
            if (item.getPreco() == null || item.getPreco().signum() <= 0) {
                throw new IllegalArgumentException("Preço do item deve ser maior que zero");
            }
        }
    }
}
