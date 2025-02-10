package com.banquito.paymentprocessor.transaccion.repository;

import com.banquito.paymentprocessor.transaccion.model.HistorialEstadoTransaccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialEstadoTransaccionRepository extends JpaRepository<HistorialEstadoTransaccion, String> {
    Page<HistorialEstadoTransaccion> findByCodTransaccion(String codTransaccion, Pageable pageable);
    Page<HistorialEstadoTransaccion> findByEstado(String estado, Pageable pageable);
    List<HistorialEstadoTransaccion> findByCodTransaccionOrderByFechaEstadoCambioDesc(String codTransaccion);
} 