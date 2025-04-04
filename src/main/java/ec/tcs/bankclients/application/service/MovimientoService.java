package ec.tcs.bankclients.application.service;

import ec.tcs.bankclients.application.dto.MovimientoDTO;
import ec.tcs.bankclients.application.exception.DataNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    /// baseUrl in application.properties
    private final WebClient webClient;

    /**
     * Método síncrono que valida la transaccion de una cuenta en especifico
     *
     * @param movimientoDTO
     * @return
     */
    public Optional<MovimientoDTO> validateMovimiento(MovimientoDTO movimientoDTO) {
        return Optional.ofNullable(webClient.post()
                .uri("/movimientos/validar")
                .body(Mono.just(movimientoDTO), MovimientoDTO.class)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        res -> Mono.error(new DataNotFound("Cuenta no encontrada o saldo insuficiente"))
                )
                .onStatus(HttpStatusCode::is5xxServerError,
                        res -> Mono.error(new RuntimeException("Error en servidor"))
                )
                .bodyToMono(MovimientoDTO.class)
                .block());
    }

}
