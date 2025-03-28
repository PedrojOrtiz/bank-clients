package ec.tcs.bankclients.application.dto;

public record CuentaDTO(
        String clienteId,
        String numeroCuenta,
        String tipoCuenta,
        Double saldoInicial,
        Boolean estado
) {}
