package ec.tcs.bankclients.web.controller;

import ec.tcs.bankclients.application.dto.ClienteCuentaDTO;
import ec.tcs.bankclients.application.dto.ClienteDTO;
import ec.tcs.bankclients.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.save(clienteDTO));
    }

    @PostMapping("/cuenta")
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteCuentaDTO clienteCuentaDTO) {
        return ResponseEntity.ok(clienteService.saveClienteWithCuenta(clienteCuentaDTO));
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> getClienteByClienteId(@PathVariable String clienteId) {
        return ResponseEntity.ok(clienteService.readByClienteId(clienteId));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.ok(clienteService.readAll());
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable String clienteId, @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.updateByClienteId(clienteId, clienteDTO));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String clienteId) {
        clienteService.deleteByClienteId(clienteId);
        return ResponseEntity.noContent().build();
    }


}
