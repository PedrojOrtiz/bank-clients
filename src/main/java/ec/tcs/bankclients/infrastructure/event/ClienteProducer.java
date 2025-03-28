package ec.tcs.bankclients.infrastructure.event;

import ec.tcs.bankclients.application.dto.CuentaDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteProducer {

    private final Logger log = LoggerFactory.getLogger(ClienteProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "clienteExchange";
    private static final String ROUTING_KEY = "cliente.created";

    public void enviarEventoClienteCreado(CuentaDTO cuentaDTO) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, cuentaDTO);
        log.info("\uD83D\uDCE4 Evento enviado: Cliente creado, proceder a crear cuenta {}", cuentaDTO);
    }

}
