package ec.tcs.bankclients.application.service;

import ec.tcs.bankclients.application.dto.ClienteCuentaDTO;
import ec.tcs.bankclients.application.dto.ClienteDTO;
import ec.tcs.bankclients.application.dto.CuentaDTO;
import ec.tcs.bankclients.application.mapper.ClienteMapper;
import ec.tcs.bankclients.domain.model.Cliente;
import ec.tcs.bankclients.domain.repository.ClienteRepository;
import ec.tcs.bankclients.infrastructure.event.ClienteProducer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ClienteServiceTest {

    private AutoCloseable mocks;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteProducer clienteProducer;

    @InjectMocks
    private ClienteService clienteService;

    private ClienteCuentaDTO clienteCuentaDTO;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        clienteCuentaDTO = ClienteCuentaDTO.builder()
                .cliente(
                        ClienteDTO.builder()
                                .nombre("Mock Client")
                                .genero("Masculino")
                                .edad(30)
                                .identificacion("123456789")
                                .direccion("Calle 123")
                                .telefono("0999999999")
                                .clienteId("CLI123")
                                .contrasena("pass123")
                                .estado(true)
                                .build()
                )
                .saldoInicial(0.0)
                .numeroCuenta("test")
                .tipoCuenta("test")
                .estado(true)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void testCreateClienteWithAccount() {

        when(
                clienteRepository.save(
                        any(Cliente.class)
                )
        ).thenReturn(
                ClienteMapper.INSTANCE.clientDTOToClient(
                        clienteCuentaDTO.getCliente()
                )
        );

        doNothing()
                .when(clienteProducer)
                .enviarEventoClienteCreado(
                        new CuentaDTO(
                                clienteCuentaDTO.getCliente().getClienteId(),
                                clienteCuentaDTO.getNumeroCuenta(),
                                clienteCuentaDTO.getTipoCuenta(),
                                clienteCuentaDTO.getSaldoInicial(),
                                clienteCuentaDTO.getEstado()
                        )
                );

        ClienteDTO clienteCreado = clienteService.save(clienteCuentaDTO.getCliente());

        assertNotNull(clienteCreado);

        assertEquals(
                clienteCuentaDTO.getCliente().getNombre(),
                clienteCreado.getNombre()
        );

        verify(
                clienteRepository,
                times(1)).save(any(Cliente.class)
        );

    }

}