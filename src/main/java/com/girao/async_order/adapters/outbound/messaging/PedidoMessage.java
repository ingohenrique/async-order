package com.girao.async_order.adapters.outbound.messaging;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoMessage {
    @JsonProperty("pedidoId")
    private String pedidoId;

    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public PedidoMessage(String pedidoId) {
        this.pedidoId = pedidoId;
        this.timestamp = LocalDateTime.now();
    }
}
