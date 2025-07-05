package com.girao.async_order.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarPedidoRequest {
    @JsonProperty("clienteId")
    @NotBlank(message = "Cliente ID não pode ser vazio")
    private String clienteId;

    @JsonProperty("itens")
    @NotEmpty(message = "Lista de itens não pode ser vazia")
    @Valid
    private List<ItemRequest> itens;

    public boolean isValid() {
        return clienteId != null && !clienteId.trim().isEmpty()
                && itens != null && !itens.isEmpty();
    }
}
