package com.girao.async_order.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("clienteId")
    private String clienteId;

    @JsonProperty("itens")
    private List<ItemResponse> itens;

    @JsonProperty("status")
    private String status;

    @JsonProperty("total")
    private BigDecimal total;

    @JsonProperty("dataCriacaoPedido")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacaoPedido;

    @JsonProperty("dataProcessamentoPedido")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataProcessamentoPedido;
}
