package com.girao.async_order.application.mappers;

import com.girao.async_order.domain.entities.Item;
import com.girao.async_order.domain.entities.Pedido;
import com.girao.async_order.application.dto.*;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public Item toItem(ItemRequest itemRequest) {
        return new Item(
                itemRequest.getId(),
                itemRequest.getNome(),
                itemRequest.getQuantidade(),
                itemRequest.getPreco());
    }

    public List<Item> toItens(List<ItemRequest> itemRequests) {
        return itemRequests.stream()
                .map(this::toItem)
                .collect(Collectors.toList());
    }

    public PedidoResponse toPedidoResponse(Pedido pedido) {
        List<ItemResponse> itensResponse = pedido.getItens().stream()
                .map(this::toItemResponse)
                .collect(Collectors.toList());

        return new PedidoResponse(
                pedido.getId(),
                pedido.getClienteId(),
                itensResponse,
                pedido.getStatus().getDescricao(),
                pedido.calcularTotal(),
                pedido.getDataCriacaoPedido(),
                pedido.getDataProcessamentoPedido());
    }

    private ItemResponse toItemResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getNome(),
                item.getQuantidade(),
                item.getPreco(),
                item.calcularSubTotal());
    }
}
