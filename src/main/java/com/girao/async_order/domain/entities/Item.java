package com.girao.async_order.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item {
    @EqualsAndHashCode.Include
    private String id;
    private String nome;
    private Integer quantidade;
    private BigDecimal preco;

    public BigDecimal calcularSubTotal() {
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }
}
