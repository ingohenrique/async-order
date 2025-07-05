package com.girao.async_order.adapters.inbound.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.girao.async_order.application.dto.CriarPedidoRequest;
import com.girao.async_order.application.dto.PedidoResponse;
import com.girao.async_order.application.services.PedidoService;

import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(@Valid @RequestBody CriarPedidoRequest request) {
        logger.info("Criando novo pedido para cliente: {}", request.getClienteId());
        PedidoResponse response = pedidoService.criarPedido(request);
        logger.info("Pedido {} criado com sucesso", response.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPedido(@PathVariable String id) {
        logger.info("Buscando pedido: {}", id);
        Optional<PedidoResponse> pedidoOpt = pedidoService.buscarPedido(id);

        if (pedidoOpt.isPresent()) {
            logger.info("Pedido {} encontrado", id);
            return ResponseEntity.ok(pedidoOpt.get());
        } else {
            logger.warn("Pedido {} não encontrado", id);
            return ResponseEntity.notFound().build();
        }
    }

}
