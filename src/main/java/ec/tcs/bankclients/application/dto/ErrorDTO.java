package ec.tcs.bankclients.application.dto;

import java.time.LocalDateTime;

public record ErrorDTO(LocalDateTime timestamp, String message, String details) {}