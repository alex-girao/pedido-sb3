package br.com.alexgirao.pedido.listener;

import br.com.alexgirao.pedido.dto.OrderCreatedEvent;
import br.com.alexgirao.pedido.dto.PedidoCriadoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static br.com.alexgirao.pedido.config.RabbitMqConfig.PEDIDO_CRIADO_QUEUE;

@Slf4j
@Component
public class PedidoCriadoListener {

    @RabbitListener(queues = PEDIDO_CRIADO_QUEUE)
    public void listen(Message<PedidoCriadoEvent> message) {
        log.info("Pedido criado: {}", message.getPayload());
    }

}
