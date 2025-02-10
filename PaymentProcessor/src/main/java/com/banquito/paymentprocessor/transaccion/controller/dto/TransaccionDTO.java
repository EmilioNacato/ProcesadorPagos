package com.banquito.paymentprocessor.transaccion.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Datos de una transacción")
public class TransaccionDTO {
    @Schema(description = "Código único de la transacción", example = "TRX123456")
    private String codTransaccion;

    @NotBlank(message = "El código único no puede estar vacío")
    @Size(max = 32, message = "El código único no puede tener más de 32 caracteres")
    @Schema(description = "Código único de referencia", example = "REF123456789")
    private String codigoUnico;

    @NotBlank(message = "El número de tarjeta no puede estar vacío")
    @Size(max = 20, message = "El número de tarjeta no puede tener más de 20 caracteres")
    @Pattern(regexp = "^[0-9]{16}$", message = "El número de tarjeta debe tener 16 dígitos")
    @Schema(description = "Número de tarjeta enmascarado", example = "************1234")
    private String numeroTarjeta;

    @NotNull(message = "La fecha de caducidad de la tarjeta no puede estar vacía")
    @Future(message = "La fecha de caducidad debe ser futura")
    @Schema(description = "Fecha de caducidad de la tarjeta", example = "2025-12-31")
    private LocalDate fechaCaducidadTarjeta;

    @NotNull(message = "El monto no puede estar vacío")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    @DecimalMax(value = "999999.99", message = "El monto no puede exceder 999999.99")
    @Schema(description = "Monto de la transacción", example = "100.50")
    private BigDecimal monto;

    @NotBlank(message = "La marca no puede estar vacía")
    @Pattern(regexp = "^(VISA|MAST|AMEX|DISC)$", message = "Marca inválida")
    @Schema(description = "Marca de la tarjeta", example = "VISA")
    private String marca;

    @Schema(description = "Estado de la transacción", example = "APR")
    private String estado;

    @Schema(description = "Fecha de creación de la transacción")
    private LocalDateTime fechaCreacion;

    @Size(max = 30, message = "La referencia no puede tener más de 30 caracteres")
    @Schema(description = "Referencia de la transacción", example = "COMPRA-123")
    private String referencia;

    @NotBlank(message = "El país no puede estar vacío")
    @Size(min = 2, max = 2, message = "El código de país debe tener 2 caracteres")
    @Schema(description = "Código ISO del país", example = "EC")
    private String pais;

    @NotBlank(message = "El código del banco emisor no puede estar vacío")
    @Size(max = 20, message = "El código del banco emisor no puede tener más de 20 caracteres")
    @Schema(description = "Código del banco emisor", example = "BANCO001")
    private String codigoBancoEmisor;

    @NotBlank(message = "La cuenta del emisor no puede estar vacía")
    @Size(max = 16, message = "La cuenta del emisor no puede tener más de 16 caracteres")
    @Schema(description = "Número de cuenta del emisor", example = "1234567890")
    private String cuentaEmisor;

    @NotBlank(message = "El código del banco adquiriente no puede estar vacío")
    @Size(max = 20, message = "El código del banco adquiriente no puede tener más de 20 caracteres")
    @Schema(description = "Código del banco adquiriente", example = "BANCO002")
    private String codigoBancoAdquiriente;

    @NotBlank(message = "La cuenta del adquiriente no puede estar vacía")
    @Size(max = 16, message = "La cuenta del adquiriente no puede tener más de 16 caracteres")
    @Schema(description = "Número de cuenta del adquiriente", example = "0987654321")
    private String cuentaAdquiriente;
}