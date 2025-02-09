package com.banquito.paymentprocessor.transaccion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TRANSACCION")
public class Transaccion {
    
    @Id
    @Column(name = "COD_TRANSACCION", length = 10)
    private String codTransaccion;

    @NotBlank(message = "El código único no puede estar vacío")
    @Size(max = 32, message = "El código único no puede tener más de 32 caracteres")
    @Column(name = "CODIGO_UNICO", length = 32, nullable = false)
    private String codigoUnico;

    @NotBlank(message = "El número de tarjeta no puede estar vacío")
    @Size(max = 20, message = "El número de tarjeta no puede tener más de 20 caracteres")
    @Column(name = "NUMERO_TARJETA", length = 20, nullable = false)
    private String numeroTarjeta;

    @NotNull(message = "La fecha de caducidad de la tarjeta no puede estar vacía")
    @Column(name = "FECHA_CADUCIDAD_TARJETA", nullable = false)
    private LocalDate fechaCaducidadTarjeta;

    @NotNull(message = "El monto no puede estar vacío")
    @DecimalMin(value = "0.0", message = "El monto debe ser mayor o igual a 0")
    @Column(name = "MONTO", precision = 20, scale = 2, nullable = false)
    private BigDecimal monto;

    @NotBlank(message = "El código de moneda no puede estar vacío")
    @Size(max = 3, message = "El código de moneda no puede tener más de 3 caracteres")
    @Column(name = "CODIGO_MONEDA", length = 3, nullable = false)
    private String codigoMoneda;

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(max = 4, message = "La marca no puede tener más de 4 caracteres")
    @Column(name = "MARCA", length = 4, nullable = false)
    private String marca;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 3, message = "El estado no puede tener más de 3 caracteres")
    @Column(name = "ESTADO", length = 3, nullable = false)
    private String estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCreacion;

    @Size(max = 30, message = "La referencia no puede tener más de 30 caracteres")
    @Column(name = "REFERENCIA", length = 30)
    private String referencia;

    @NotBlank(message = "El país no puede estar vacío")
    @Size(max = 2, message = "El país no puede tener más de 2 caracteres")
    @Column(name = "PAIS", length = 2, nullable = false)
    private String pais;

    @NotBlank(message = "El código del banco emisor no puede estar vacío")
    @Size(max = 20, message = "El código del banco emisor no puede tener más de 20 caracteres")
    @Column(name = "CODIGO_BANCO_EMISOR", length = 20, nullable = false)
    private String codigoBancoEmisor;

    @NotBlank(message = "La cuenta del emisor no puede estar vacía")
    @Size(max = 16, message = "La cuenta del emisor no puede tener más de 16 caracteres")
    @Column(name = "CUENTA_EMISOR", length = 16, nullable = false)
    private String cuentaEmisor;

    @NotBlank(message = "El código del banco adquiriente no puede estar vacío")
    @Size(max = 20, message = "El código del banco adquiriente no puede tener más de 20 caracteres")
    @Column(name = "CODIGO_BANCO_ADQUIRIENTE", length = 20, nullable = false)
    private String codigoBancoAdquiriente;

    @NotBlank(message = "La cuenta del adquiriente no puede estar vacía")
    @Size(max = 16, message = "La cuenta del adquiriente no puede tener más de 16 caracteres")
    @Column(name = "CUENTA_ADQUIRIENTE", length = 16, nullable = false)
    private String cuentaAdquiriente;

    @Size(max = 1000, message = "La transacción encriptada no puede tener más de 1000 caracteres")
    @Column(name = "TRANSACCION_ENCRIPTADA", length = 1000)
    private String transaccionEncriptada;

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
    }
} 