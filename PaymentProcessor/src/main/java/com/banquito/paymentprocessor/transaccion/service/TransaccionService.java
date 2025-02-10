package com.banquito.paymentprocessor.transaccion.service;

import com.banquito.paymentprocessor.exception.BadRequestException;
import com.banquito.paymentprocessor.exception.NotFoundException;
import com.banquito.paymentprocessor.transaccion.model.Transaccion;
import com.banquito.paymentprocessor.transaccion.model.HistorialEstadoTransaccion;
import com.banquito.paymentprocessor.transaccion.repository.TransaccionRepository;
import com.banquito.paymentprocessor.transaccion.repository.HistorialEstadoTransaccionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final HistorialEstadoTransaccionRepository historialRepository;

    public TransaccionService(TransaccionRepository transaccionRepository, 
                            HistorialEstadoTransaccionRepository historialRepository) {
        this.transaccionRepository = transaccionRepository;
        this.historialRepository = historialRepository;
    }

    public Page<Transaccion> buscarTransacciones(String estado, LocalDateTime fechaDesde, 
            LocalDateTime fechaHasta, String numeroTarjeta, Pageable pageable) {
        return transaccionRepository.findAll(pageable);
    }

    public Transaccion obtenerTransaccionPorId(String id) {
        return transaccionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transacción no encontrada con ID: " + id));
    }

    @Transactional
    public Transaccion procesarTransaccion(Transaccion transaccion) {
        validarTransaccion(transaccion);
        transaccion.setCodTransaccion(generarCodigo());
        transaccion.setEstado("PEN"); 
        transaccion.setFechaCreacion(LocalDateTime.now());
        
        Transaccion transaccionGuardada = transaccionRepository.save(transaccion);
        registrarHistorialEstado(transaccionGuardada, "PEN");
        
        return transaccionGuardada;
    }

    @Transactional
    public Transaccion reversarTransaccion(String id) {
        Transaccion transaccion = obtenerTransaccionPorId(id);
        if (!"APR".equals(transaccion.getEstado())) {
            throw new BadRequestException("Solo se pueden reversar transacciones aprobadas");
        }
        
        transaccion.setEstado("REV"); 
        Transaccion transaccionReversada = transaccionRepository.save(transaccion);
        registrarHistorialEstado(transaccionReversada, "REV");
        
        return transaccionReversada;
    }

    @Transactional
    public Transaccion actualizarEstado(String id, String estado) {
        Transaccion transaccion = obtenerTransaccionPorId(id);
        validarEstado(estado);
        
        transaccion.setEstado(estado);
        Transaccion transaccionActualizada = transaccionRepository.save(transaccion);
        registrarHistorialEstado(transaccionActualizada, estado);
        
        return transaccionActualizada;
    }

    public List<Transaccion> obtenerHistorialTransaccion(String id) {
        Transaccion transaccion = obtenerTransaccionPorId(id);
        return transaccionRepository.findByCodigoUnico(transaccion.getCodigoUnico());
    }

    private void registrarHistorialEstado(Transaccion transaccion, String estado) {
        HistorialEstadoTransaccion historial = new HistorialEstadoTransaccion();
        historial.setCodHistorialEstado(generarCodigo());
        historial.setCodTransaccion(transaccion.getCodTransaccion());
        historial.setEstado(estado);
        historial.setFechaEstadoCambio(LocalDateTime.now());
        historialRepository.save(historial);
    }

    private void validarTransaccion(Transaccion transaccion) {
        if (transaccion.getNumeroTarjeta() == null || transaccion.getNumeroTarjeta().trim().isEmpty()) {
            throw new BadRequestException("El número de tarjeta es requerido");
        }
        if (transaccion.getMonto() == null || transaccion.getMonto().signum() <= 0) {
            throw new BadRequestException("El monto debe ser mayor a cero");
        }
    }

    private void validarEstado(String estado) {
        List<String> estadosValidos = List.of("PEN", "APR", "REV", "REC");
        if (!estadosValidos.contains(estado)) {
            throw new BadRequestException("Estado inválido: " + estado);
        }
    }

    private String generarCodigo() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
} 