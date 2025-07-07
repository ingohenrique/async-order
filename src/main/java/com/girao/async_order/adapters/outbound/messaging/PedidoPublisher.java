package com.girao.async_order.adapters.outbound.messaging;

import com.girao.async_order.application.ports.PedidoPublisherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PedidoPublisher implements PedidoPublisherPort {
    private static final Logger logger = LoggerFactory.getLogger(PedidoPublisher.class);
    private final RabbitTemplate rabbitTemplate;

    public PedidoPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishPedidoParaProcessamento(String pedidoId) {
        logger.info("Enviando pedido {} para processamento", pedidoId);

        PedidoMessage message = new PedidoMessage(pedidoId, LocalDateTime.now());

        rabbitTemplate.convertAndSend(
                "pedido.exchange",
                "pedido.processar",
                message
        );

        logger.info("Pedido {} enviado para processamento", pedidoId);

    }
}
