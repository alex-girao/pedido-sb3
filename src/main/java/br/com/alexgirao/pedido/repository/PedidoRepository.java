package br.com.alexgirao.pedido.repository;

import br.com.alexgirao.pedido.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<PedidoEntity, Long> {

    Page<PedidoEntity> findAllByClienteId(Long clienteId, PageRequest pageRequest);

}
