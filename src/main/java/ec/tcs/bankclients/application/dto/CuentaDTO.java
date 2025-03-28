package ec.tcs.bankclients.application.dto;

public record CuentaDTO(
        String idCliente,
        String numeroCuenta,
        String tipoCuenta,
        Double saldoInicial,
        Boolean estado
) {}
