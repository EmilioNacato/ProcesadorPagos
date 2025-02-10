package com.banquito.paymentprocessor.transaccion.service;

import com.banquito.paymentprocessor.exception.NotFoundException;
import com.banquito.paymentprocessor.transaccion.model.HistorialEstadoTransaccion;
import com.banquito.paymentprocessor.transaccion.repository.HistorialEstadoTransaccionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class HistorialEstadoTransaccionService {

    private final HistorialEstadoTransaccionRepository historialRepository;

    public HistorialEstadoTransaccionService(HistorialEstadoTransaccionRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public Page<HistorialEstadoTransaccion> buscarHistorial(String codTransaccion, String estado,
            LocalDateTime fechaDesde, LocalDateTime fechaHasta, Pageable pageable) {
        if (codTransaccion != null) {
            return historialRepository.findByCodTransaccion(codTransaccion, pageable);
        } else if (estado != null) {
            return historialRepository.findByEstado(estado, pageable);
        }
        // Implementar más filtros según sea necesario
        return historialRepository.findAll(pageable);
    }

    public HistorialEstadoTransaccion obtenerHistorialPorId(String id) {
        return historialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Registro de historial no encontrado con ID: " + id));
    }

    public List<HistorialEstadoTransaccion> obtenerHistorialPorTransaccion(String codTransaccion) {
        return historialRepository.findByCodTransaccionOrderByFechaEstadoCambioDesc(codTransaccion);
    }
} 