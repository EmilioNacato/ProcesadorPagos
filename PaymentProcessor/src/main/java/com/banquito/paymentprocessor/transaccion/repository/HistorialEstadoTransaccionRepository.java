package com.banquito.paymentprocessor.transaccion.repository;

import com.banquito.paymentprocessor.transaccion.model.HistorialEstadoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialEstadoTransaccionRepository extends JpaRepository<HistorialEstadoTransaccion, String> {
    List<HistorialEstadoTransaccion> findByCodTransaccion(String codTransaccion);
    List<HistorialEstadoTransaccion> findByEstado(String estado);
} 