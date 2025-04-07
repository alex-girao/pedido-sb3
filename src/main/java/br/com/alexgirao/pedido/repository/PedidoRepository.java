package br.com.alexgirao.pedido.repository;

import br.com.alexgirao.pedido.entity.PedidoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<PedidoEntity, Long> {
}
