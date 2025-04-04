package ec.tcs.bankclients.web.controller;

import ec.tcs.bankclients.application.dto.MovimientoDTO;
import ec.tcs.bankclients.application.service.MovimientoService;
import ec.tcs.bankclients.infrastructure.event.MovimientoProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoProducer movimientoProducer;
    private final MovimientoService movimientoService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        movimientoService.validateMovimiento(movimientoDTO);
        movimientoProducer.enviarEventoMovimiento(movimientoDTO);
        return ResponseEntity.ok("Movimiento enviado para procesamiento");
    }

}
