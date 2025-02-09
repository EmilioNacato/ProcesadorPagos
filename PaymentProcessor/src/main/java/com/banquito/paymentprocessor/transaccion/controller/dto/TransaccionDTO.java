package com.banquito.paymentprocessor.transaccion.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TransaccionDTO {
    private String codTransaccion;

    @NotBlank(message = "El código único no puede estar vacío")
    @Size(max = 32, message = "El código único no puede tener más de 32 caracteres")
    private String codigoUnico;

    @NotBlank(message = "El número de tarjeta no puede estar vacío")
    @Size(max = 20, message = "El número de tarjeta no puede tener más de 20 caracteres")
    private String numeroTarjeta;

    @NotNull(message = "La fecha de caducidad de la tarjeta no puede estar vacía")
    private LocalDate fechaCaducidadTarjeta;

    @NotNull(message = "El monto no puede estar vacío")
    @DecimalMin(value = "0.0", message = "El monto debe ser mayor o igual a 0")
    private BigDecimal monto;

    @NotBlank(message = "El código de moneda no puede estar vacío")
    @Size(max = 3, message = "El código de moneda no puede tener más de 3 caracteres")
    private String codigoMoneda;

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(max = 4, message = "La marca no puede tener más de 4 caracteres")
    private String marca;

    private String estado;
    private LocalDateTime fechaCreacion;

    @Size(max = 30, message = "La referencia no puede tener más de 30 caracteres")
    private String referencia;

    @NotBlank(message = "El país no puede estar vacío")
    @Size(max = 2, message = "El país no puede tener más de 2 caracteres")
    private String pais;

    @NotBlank(message = "El código del banco emisor no puede estar vacío")
    @Size(max = 20, message = "El código del banco emisor no puede tener más de 20 caracteres")
    private String codigoBancoEmisor;

    @NotBlank(message = "La cuenta del emisor no puede estar vacía")
    @Size(max = 16, message = "La cuenta del emisor no puede tener más de 16 caracteres")
    private String cuentaEmisor;

    @NotBlank(message = "El código del banco adquiriente no puede estar vacío")
    @Size(max = 20, message = "El código del banco adquiriente no puede tener más de 20 caracteres")
    private String codigoBancoAdquiriente;

    @NotBlank(message = "La cuenta del adquiriente no puede estar vacía")
    @Size(max = 16, message = "La cuenta del adquiriente no puede tener más de 16 caracteres")
    private String cuentaAdquiriente;
} 