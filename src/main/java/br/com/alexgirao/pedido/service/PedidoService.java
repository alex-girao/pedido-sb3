package br.com.alexgirao.pedido.service;

import br.com.alexgirao.pedido.controller.dto.PedidoResponse;
import br.com.alexgirao.pedido.dto.PedidoCriadoEvent;
import br.com.alexgirao.pedido.entity.PedidoEntity;
import br.com.alexgirao.pedido.entity.PedidoItem;
import br.com.alexgirao.pedido.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final MongoTemplate mongoTemplate;

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

    public Page<PedidoResponse> findAllByClienteId(Long clienteId, PageRequest pageRequest) {
        var pedidos = pedidoRepository.findAllByClienteId(clienteId, pageRequest);
        return pedidos.map(PedidoResponse::fromEntity);
    }

    public BigDecimal findTotalPedidosByClienteId(Long clienteId) {
        var aggregations = newAggregation(
                match(Criteria.where("clienteId").is(clienteId)),
                group().sum("total").as("total")
        );
        var response = mongoTemplate.aggregate(aggregations, "pedidos", Document.class);
        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }
}
