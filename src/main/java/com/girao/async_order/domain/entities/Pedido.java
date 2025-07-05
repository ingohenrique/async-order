package com.girao.async_order.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
    @EqualsAndHashCode.Include
    private String id;
    private String clienteId;
    private List<Item> itens;
    private StatusPedido status;
    private LocalDateTime dataCriacaoPedido;
    private LocalDateTime dataProcessamentoPedido;

    public Pedido(String id, String clienteId, List<Item> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.itens = itens;
        this.status = StatusPedido.PENDENTE;
        this.dataCriacaoPedido = LocalDateTime.now();
    }

    public void processar() {
        if (this.status == StatusPedido.PENDENTE) {
            this.status = StatusPedido.PROCESSADO;
            this.dataProcessamentoPedido = LocalDateTime.now();
        }
    }

    public BigDecimal calcularTotal() {
        return itens.stream()
                .map(Item::calcularSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
