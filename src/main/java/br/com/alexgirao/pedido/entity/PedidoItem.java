package br.com.alexgirao.pedido.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItem {

    private String produto;

    private Integer quantidade;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal preco;

}
