package com.girao.async_order.adapters.outbound.messaging;

import com.girao.async_order.application.usecases.ProcessarPedidoUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {
    private static final Logger logger = LoggerFactory.getLogger(PedidoConsumer.class);
    private final ProcessarPedidoUseCase processarPedidoUseCase;

    public PedidoConsumer(ProcessarPedidoUseCase processarPedidoUseCase) {
        this.processarPedidoUseCase = processarPedidoUseCase;
    }

    @RabbitListener(queues = "pedido.queue")
    public void processar(PedidoMessage message) {
        logger.info("Processando pedido: {}", message.getPedidoId());

        try {
            Thread.sleep(2000);

            processarPedidoUseCase.execute(message.getPedidoId());

            logger.info("Pedido {} processado com sucesso", message.getPedidoId());

        } catch (InterruptedException e) {
            logger.warn("Processamento do pedido {} foi interrompido", message.getPedidoId());
            Thread.currentThread().interrupt();
        }
    }

}
