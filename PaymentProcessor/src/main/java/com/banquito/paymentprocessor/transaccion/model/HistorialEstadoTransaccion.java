package com.banquito.paymentprocessor.transaccion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "HISTORIAL_ESTADO_TRANSACCION")
public class HistorialEstadoTransaccion implements Serializable {

    public static final String ESTADO_PENDIENTE = "PEN";
    public static final String ESTADO_APROBADA = "APR";
    public static final String ESTADO_RECHAZADO_MONTO = "RECM";
    public static final String ESTADO_RECHAZADO_BANCO = "RECB";
    public static final String ESTADO_TRANSFERENCIA_PENDIENTE = "TRRFP";

    @Id
    @Column(name = "COD_HISTORIAL_ESTADO", length = 10, nullable = false)
    private String codHistorialEstado;

    @Column(name = "COD_TRANSACCION", length = 10)
    private String codTransaccion;

    @NotNull
    @Pattern(regexp = "PEN|APR|RECM|RECB|TRRFP")
    @Column(name = "ESTADO", length = 5, nullable = false)
    private String estado;

    @NotNull
    @Column(name = "FECHA_ESTADO_CAMBIO", nullable = false)
    private LocalDateTime fechaEstadoCambio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_TRANSACCION", insertable = false, updatable = false)
    private Transaccion transaccion;

    @PrePersist
    public void prePersist() {
        fechaEstadoCambio = LocalDateTime.now();
    }
} 