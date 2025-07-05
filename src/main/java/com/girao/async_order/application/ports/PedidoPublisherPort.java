package com.girao.async_order.application.ports;

public interface PedidoPublisherPort {
    void publishPedidoParaProcessamento(String pedidoId);
}
