package br.com.alexgirao.pedido.dto;

import java.math.BigDecimal;

public record PedidoItemEvent(String produto,
                              Integer quantidade,
                              BigDecimal preco) {
}
