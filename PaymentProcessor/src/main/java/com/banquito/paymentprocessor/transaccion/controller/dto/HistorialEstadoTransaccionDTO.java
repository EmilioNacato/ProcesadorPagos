package com.banquito.paymentprocessor.transaccion.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HistorialEstadoTransaccionDTO {
    private String codHistorialEstado;

    @NotBlank(message = "El código de transacción no puede estar vacío")
    @Size(max = 10, message = "El código de transacción no puede tener más de 10 caracteres")
    private String codTransaccion;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 3, message = "El estado no puede tener más de 3 caracteres")
    private String estado;

    private LocalDateTime fechaEstadoCambio;
} 