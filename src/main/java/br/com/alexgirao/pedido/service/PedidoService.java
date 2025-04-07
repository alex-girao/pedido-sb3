package br.com.alexgirao.pedido.service;

import br.com.alexgirao.pedido.dto.PedidoCriadoEvent;
import br.com.alexgirao.pedido.entity.PedidoEntity;
import br.com.alexgirao.pedido.entity.PedidoItem;
import br.com.alexgirao.pedido.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public void save(PedidoCriadoEvent event) {
        List<PedidoItem> itens = event.itens().stream()
                .map(item -> PedidoItem.builder()
                        .produto(item.produto())
                        .quantidade(item.quantidade())
                        .preco(item.preco())
                        .build())
                .toList();
        BigDecimal total = itens.stream()
                .map(item -> item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        PedidoEntity entity = PedidoEntity.builder()
                .pedidoId(event.codigoPedido())
                .clienteId(event.codigoCliente())
                .itens(itens)
                .total(total)
                .build();
        pedidoRepository.save(entity);
    }

}
