package br.com.alexgirao.pedido.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pedidos")
public class PedidoEntity {

    @MongoId
    private Long pedidoId;

    @Indexed(name = "cliente_id_index")
    private Long clienteId;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal total;

    private List<PedidoItem> itens;

}
