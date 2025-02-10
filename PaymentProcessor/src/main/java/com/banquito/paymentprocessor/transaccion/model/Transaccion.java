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
    
    public static final String MARCA_VISA = "VISA";
    public static final String MARCA_AMEX = "AMEX";
    public static final String MARCA_MASTERCARD = "MSCD";
    public static final String MARCA_DINERS = "DINE";
    
    public static final String ESTADO_PENDIENTE = "PEN";
    public static final String ESTADO_APROBADA = "APR";
    public static final String ESTADO_RECHAZADA = "REC";

    @Id
    @Column(name = "COD_TRANSACCION", length = 10, nullable = false)
    private String codTransaccion;

    @NotNull
    @Size(max = 64)
    @Column(name = "CODIGO_UNICO", length = 64, nullable = false)
    private String codigoUnico;

    @NotNull
    @Size(max = 20)
    @Column(name = "NUMERO_TARJETA", length = 20, nullable = false)
    private String numeroTarjeta;

    @NotNull
    @Column(name = "FECHA_CADUCIDAD_TARJETA", nullable = false)
    private LocalDate fechaCaducidadTarjeta;

    @NotNull
    @Column(name = "MONTO", precision = 20, scale = 2, nullable = false)
    private BigDecimal monto;

    @NotNull
    @Size(max = 3)
    @Column(name = "CODIGO_MONEDA", length = 3, nullable = false)
    private String codigoMoneda;

    @NotNull
    @Pattern(regexp = "VISA|AMEX|MSCD|DINE")
    @Column(name = "MARCA", length = 4, nullable = false)
    private String marca;

    @NotNull
    @Pattern(regexp = "PEN|APR|REC")
    @Column(name = "ESTADO", length = 3, nullable = false)
    private String estado;

    @NotNull
    @Column(name = "FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCreacion;

    @NotNull
    @Size(max = 30)
    @Column(name = "REFERENCIA", length = 30, nullable = false)
    private String referencia;

    @NotNull
    @Size(max = 2)
    @Column(name = "PAIS", length = 2, nullable = false)
    private String pais;

    @NotNull
    @Size(max = 20)
    @Column(name = "CODIGO_BANCO_EMISOR", length = 20, nullable = false)
    private String codigoBancoEmisor;

    @NotNull
    @Size(max = 16)
    @Column(name = "CUENTA_EMISOR", length = 16, nullable = false)
    private String cuentaEmisor;

    @NotNull
    @Size(max = 20)
    @Column(name = "CODIGO_BANCO_ADQUIRIENTE", length = 20, nullable = false)
    private String codigoBancoAdquiriente;

    @NotNull
    @Size(max = 16)
    @Column(name = "CUENTA_ADQUIRIENTE", length = 16, nullable = false)
    private String cuentaAdquiriente;

    @NotNull
    @Size(max = 1000)
    @Column(name = "TRANSACCION_ENCRIPTADA", length = 1000, nullable = false)
    private String transaccionEncriptada;

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
    }
} 