package com.banquito.paymentprocessor.fraude.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "REGLA_FRAUDE")
public class ReglaFraude {
    
    @Id
    @Column(name = "COD_REGLA_FRAUDE", length = 10)
    private String codReglaFraude;

    @NotBlank(message = "El nombre de la regla no puede estar vacío")
    @Size(max = 50, message = "El nombre de la regla no puede tener más de 50 caracteres")
    @Column(name = "NOMBRE_REGLA", length = 50, nullable = false)
    private String nombreRegla;

    @Size(max = 100, message = "La descripción no puede tener más de 100 caracteres")
    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;

    @NotBlank(message = "El tipo de regla no puede estar vacío")
    @Size(max = 3, message = "El tipo de regla no puede tener más de 3 caracteres")
    @Column(name = "TIPO_REGLA", length = 3, nullable = false)
    private String tipoRegla;

    @DecimalMin(value = "0.0", message = "El monto límite debe ser mayor o igual a 0")
    @Column(name = "MONTO_LIMITE", precision = 20, scale = 2)
    private BigDecimal montoLimite;

    @Min(value = 0, message = "El máximo de transacciones debe ser mayor o igual a 0")
    @Column(name = "MAX_TRANSACCIONES")
    private Integer maxTransacciones;

    @Min(value = 0, message = "El período en minutos debe ser mayor o igual a 0")
    @Column(name = "PERIODO_MINUTOS")
    private Integer periodoMinutos;

    @Column(name = "ESTADO", nullable = false)
    private Boolean estado;
} 