package br.com.alexgirao.pedido.listener;

import br.com.alexgirao.pedido.dto.PedidoCriadoEvent;
import br.com.alexgirao.pedido.service.PedidoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static br.com.alexgirao.pedido.config.RabbitMqConfig.PEDIDO_CRIADO_QUEUE;

@Slf4j
@AllArgsConstructor
@Component
public class PedidoCriadoListener {

    private final PedidoService pedidoService;

    @RabbitListener(queues = PEDIDO_CRIADO_QUEUE)
    public void listen(Message<PedidoCriadoEvent> message) {
        log.info("Pedido recebido: {}", message.getPayload());
        pedidoService.save(message.getPayload());
        log.info("Pedido {} gravado com sucesso", message.getPayload().codigoPedido());
    }

}

