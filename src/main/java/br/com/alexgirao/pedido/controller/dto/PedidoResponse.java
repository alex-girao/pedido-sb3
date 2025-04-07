package br.com.alexgirao.pedido.controller.dto;

import br.com.alexgirao.pedido.entity.PedidoEntity;

import java.math.BigDecimal;

public record PedidoResponse(Long pedidoId,
                             Long clienteId,
                             BigDecimal total) {

    public static PedidoResponse fromEntity(PedidoEntity entity) {
        return new PedidoResponse(entity.getPedidoId(), entity.getClienteId(), entity.getTotal());
    }

}
