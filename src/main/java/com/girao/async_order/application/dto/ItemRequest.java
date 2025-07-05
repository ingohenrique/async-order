package com.girao.async_order.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    @JsonProperty("id")
    @NotBlank(message = "ID do item não pode ser vazio")
    private String id;

    @JsonProperty("nome")
    @NotBlank(message = "Nome do item não pode ser vazio")
    private String nome;

    @JsonProperty("quantidade")
    @NotNull(message = "Quantidade não pode ser nula")
    @Positive(message = "Quantidade deve ser maior que zero")
    private Integer quantidade;

    @JsonProperty("preco")
    @NotNull(message = "Preço não pode ser nulo")
    @Positive(message = "Preço deve ser maior que zero")
    private BigDecimal preco;
}
