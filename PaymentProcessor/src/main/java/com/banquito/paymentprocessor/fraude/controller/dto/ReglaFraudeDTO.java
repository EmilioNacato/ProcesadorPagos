package com.banquito.paymentprocessor.fraude.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ReglaFraudeDTO {
    private String codReglaFraude;

    @NotBlank(message = "El nombre de la regla no puede estar vacío")
    @Size(max = 50, message = "El nombre de la regla no puede tener más de 50 caracteres")
    private String nombreRegla;

    @Size(max = 100, message = "La descripción no puede tener más de 100 caracteres")
    private String descripcion;

    @NotBlank(message = "El tipo de regla no puede estar vacío")
    @Size(max = 3, message = "El tipo de regla no puede tener más de 3 caracteres")
    private String tipoRegla;

    @DecimalMin(value = "0.0", message = "El monto límite debe ser mayor o igual a 0")
    private BigDecimal montoLimite;

    @Min(value = 0, message = "El máximo de transacciones debe ser mayor o igual a 0")
    private Integer maxTransacciones;

    @Min(value = 0, message = "El período en minutos debe ser mayor o igual a 0")
    private Integer periodoMinutos;

    private Boolean estado;
} 