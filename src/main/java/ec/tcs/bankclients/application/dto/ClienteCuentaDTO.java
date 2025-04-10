package ec.tcs.bankclients.application.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteCuentaDTO {

    private ClienteDTO cliente;

    private String numeroCuenta;

    private String tipoCuenta;

    private Double saldoInicial;

    private Boolean estado;

}
