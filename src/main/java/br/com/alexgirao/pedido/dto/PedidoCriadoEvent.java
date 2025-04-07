package br.com.alexgirao.pedido.dto;

import java.util.List;

public record PedidoCriadoEvent(Long codigoPedido,
                                Long codigoCliente,
                                List<PedidoItemEvent> itens) {

}
