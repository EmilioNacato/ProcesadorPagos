package com.banquito.paymentprocessor.transaccion.repository;

import com.banquito.paymentprocessor.transaccion.model.HistorialEstadoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface HistorialEstadoTransaccionRepository extends JpaRepository<HistorialEstadoTransaccion, String> {
    List<HistorialEstadoTransaccion> findByTransaccionCodigoOrderByFechaEstadoCambioDesc(Integer codigoTransaccion);
    
    List<HistorialEstadoTransaccion> findByEstadoAndFechaEstadoCambioBetweenOrderByFechaEstadoCambioDesc(
            String estado, LocalDateTime fechaInicio, LocalDateTime fechaFin);
            
    List<HistorialEstadoTransaccion> findByFechaEstadoCambioBetweenOrderByFechaEstadoCambioDesc(
            LocalDateTime fechaInicio, LocalDateTime fechaFin);
} 