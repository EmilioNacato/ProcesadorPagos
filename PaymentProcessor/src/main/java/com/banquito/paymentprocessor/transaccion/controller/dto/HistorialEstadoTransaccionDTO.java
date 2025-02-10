package com.banquito.paymentprocessor.transaccion.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Historial de estados de una transacción")
public class HistorialEstadoTransaccionDTO {
    
    @Schema(description = "Código del registro de historial", example = "HIST123456")
    private String codHistorialEstado;

    @NotBlank(message = "El código de transacción no puede estar vacío")
    @Size(max = 10, message = "El código de transacción no puede tener más de 10 caracteres")
    @Schema(description = "Código de la transacción asociada", example = "TRX123456")
    private String codTransaccion;

    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(regexp = "^(PEN|APR|REV|REC)$", message = "Estado inválido")
    @Schema(description = "Estado de la transacción", example = "APR")
    private String estado;

    @Schema(description = "Fecha y hora del cambio de estado")
    private LocalDateTime fechaEstadoCambio;
} 