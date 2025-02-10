package com.banquito.paymentprocessor.transaccion.repository;

import com.banquito.paymentprocessor.transaccion.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, String> {
    
    List<Transaccion> findByEstadoAndFechaCreacionBetweenOrderByFechaCreacionDesc(
            String estado, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    List<Transaccion> findByCodigoBancoEmisorAndMontoBetweenOrderByMontoDesc(
            String codigoBancoEmisor, BigDecimal montoMinimo, BigDecimal montoMaximo);
    
    List<Transaccion> findByEstadoAndCodigoBancoEmisorOrderByFechaCreacionDesc(
            String estado, String codigoBancoEmisor);
    
    List<Transaccion> findByMarcaAndFechaCreacionGreaterThanEqualOrderByFechaCreacionDesc(
            String marca, LocalDateTime fecha);

    List<Transaccion> findByNumeroTarjetaAndFechaCreacionBetweenOrderByFechaCreacionDesc(
            String numeroTarjeta, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    Optional<Transaccion> findFirstByCodigoUnicoOrderByFechaCreacionDesc(
            String codigoUnico);

    boolean existsByCodigoUnico(String codigoUnico);
    
    List<Transaccion> findByCodigoBancoAdquirienteOrderByFechaCreacionDesc(
            String codigoBancoAdquiriente);
} 