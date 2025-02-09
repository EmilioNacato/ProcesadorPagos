package com.banquito.paymentprocessor.transaccion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "HISTORIAL_ESTADO_TRANSACCION")
public class HistorialEstadoTransaccion {
    
    @Id
    @Column(name = "COD_HISTORIAL_ESTADO", length = 10)
    private String codHistorialEstado;

    @NotBlank(message = "El código de transacción no puede estar vacío")
    @Size(max = 10, message = "El código de transacción no puede tener más de 10 caracteres")
    @Column(name = "COD_TRANSACCION", length = 10, nullable = false)
    private String codTransaccion;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 3, message = "El estado no puede tener más de 3 caracteres")
    @Column(name = "ESTADO", length = 3, nullable = false)
    private String estado;

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