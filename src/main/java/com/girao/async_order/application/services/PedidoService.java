package com.girao.async_order.application.services;

import com.girao.async_order.application.ports.PedidoPublisherPort;
import com.girao.async_order.application.usecases.BuscarPedidoUseCase;
import com.girao.async_order.application.usecases.CriarPedidoUseCase;
import com.girao.async_order.application.mappers.PedidoMapper;
import com.girao.async_order.application.dto.*;
import com.girao.async_order.domain.entities.Item;
import com.girao.async_order.domain.entities.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final PedidoPublisherPort pedidoPublisher;
    private final PedidoMapper pedidoMapper;

    public PedidoService(CriarPedidoUseCase criarPedidoUseCase,
                         BuscarPedidoUseCase buscarPedidoUseCase,
                         PedidoPublisherPort pedidoPublisher,
                         PedidoMapper pedidoMapper) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.buscarPedidoUseCase = buscarPedidoUseCase;
        this.pedidoPublisher = pedidoPublisher;
        this.pedidoMapper = pedidoMapper;
    }

    public PedidoResponse criarPedido(CriarPedidoRequest request) {
        List<Item> itens = pedidoMapper.toItens(request.getItens());

        Pedido pedido = criarPedidoUseCase.execute(request.getClienteId(), itens);

        pedidoPublisher.publishPedidoParaProcessamento(pedido.getId());

        return pedidoMapper.toPedidoResponse(pedido);
    }

    public Optional<PedidoResponse> buscarPedido(String pedidoId) {
        Optional<Pedido> pedidoOpt = buscarPedidoUseCase.execute(pedidoId);
        return pedidoOpt.map(pedidoMapper::toPedidoResponse);
    }

}
