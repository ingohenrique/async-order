package com.girao.async_order.domain.entities;

import lombok.Getter;

@Getter
public enum StatusPedido {
    PENDENTE("Pendente"),
    PROCESSADO("Processado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }
}
