package com.banquito.paymentprocessor.transaccion.controller;

import com.banquito.paymentprocessor.transaccion.controller.dto.TransaccionDTO;
import com.banquito.paymentprocessor.transaccion.controller.mapper.TransaccionMapper;
import com.banquito.paymentprocessor.transaccion.model.Transaccion;
import com.banquito.paymentprocessor.transaccion.service.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/transacciones")
@Tag(name = "Transacciones", description = "API para gestionar las transacciones de pago")
public class TransaccionController {

    private final TransaccionService transaccionService;
    private final TransaccionMapper transaccionMapper;

    public TransaccionController(TransaccionService transaccionService, TransaccionMapper transaccionMapper) {
        this.transaccionService = transaccionService;
        this.transaccionMapper = transaccionMapper;
    }

    @Operation(summary = "Obtener transacciones con filtros y paginación")
    @ApiResponse(responseCode = "200", description = "Lista de transacciones obtenida exitosamente")
    @GetMapping
    public ResponseEntity<Page<TransaccionDTO>> obtenerTransacciones(
            @Parameter(description = "Estado de la transacción") 
            @RequestParam(required = false) String estado,
            @Parameter(description = "Fecha desde") 
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaDesde,
            @Parameter(description = "Fecha hasta") 
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHasta,
            @Parameter(description = "Número de tarjeta") 
            @RequestParam(required = false) String numeroTarjeta,
            Pageable pageable) {
        log.info("Obteniendo transacciones con filtros - estado: {}, fechaDesde: {}, fechaHasta: {}", 
                estado, fechaDesde, fechaHasta);
        Page<Transaccion> transacciones = transaccionService.buscarTransacciones(
                estado, fechaDesde, fechaHasta, numeroTarjeta, pageable);
        return ResponseEntity.ok(transacciones.map(transaccionMapper::toDTO));
    }

    @Operation(summary = "Obtener transacción por ID")
    @ApiResponse(responseCode = "200", description = "Transacción encontrada")
    @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<TransaccionDTO> obtenerTransaccionPorId(
            @Parameter(description = "ID de la transacción") 
            @PathVariable String id) {
        log.info("Obteniendo transacción con ID: {}", id);
        Transaccion transaccion = transaccionService.obtenerTransaccionPorId(id);
        return ResponseEntity.ok(transaccionMapper.toDTO(transaccion));
    }

    @Operation(summary = "Procesar nueva transacción")
    @ApiResponse(responseCode = "200", description = "Transacción procesada exitosamente")
    @PostMapping
    public ResponseEntity<TransaccionDTO> procesarTransaccion(
            @Parameter(description = "Datos de la transacción") 
            @Valid @RequestBody TransaccionDTO transaccionDTO) {
        log.info("Procesando nueva transacción: {}", transaccionDTO);
        Transaccion transaccion = transaccionMapper.toModel(transaccionDTO);
        Transaccion transaccionProcesada = transaccionService.procesarTransaccion(transaccion);
        return ResponseEntity.ok(transaccionMapper.toDTO(transaccionProcesada));
    }

    @Operation(summary = "Reversar transacción")
    @ApiResponse(responseCode = "200", description = "Transacción reversada exitosamente")
    @PostMapping("/{id}/reverso")
    public ResponseEntity<TransaccionDTO> reversarTransaccion(
            @Parameter(description = "ID de la transacción") 
            @PathVariable String id) {
        log.info("Reversando transacción con ID: {}", id);
        Transaccion transaccionReversada = transaccionService.reversarTransaccion(id);
        return ResponseEntity.ok(transaccionMapper.toDTO(transaccionReversada));
    }

    @Operation(summary = "Actualizar estado de transacción")
    @ApiResponse(responseCode = "200", description = "Estado de transacción actualizado exitosamente")
    @PatchMapping("/{id}/estado")
    public ResponseEntity<TransaccionDTO> actualizarEstado(
            @Parameter(description = "ID de la transacción") 
            @PathVariable String id,
            @Parameter(description = "Nuevo estado") 
            @RequestParam String estado) {
        log.info("Actualizando estado de transacción {} a {}", id, estado);
        Transaccion transaccionActualizada = transaccionService.actualizarEstado(id, estado);
        return ResponseEntity.ok(transaccionMapper.toDTO(transaccionActualizada));
    }

    @Operation(summary = "Obtener historial de estados de una transacción")
    @ApiResponse(responseCode = "200", description = "Historial obtenido exitosamente")
    @GetMapping("/{id}/historial")
    public ResponseEntity<List<TransaccionDTO>> obtenerHistorial(
            @Parameter(description = "ID de la transacción") 
            @PathVariable String id) {
        log.info("Obteniendo historial de transacción con ID: {}", id);
        List<Transaccion> historial = transaccionService.obtenerHistorialTransaccion(id);
        return ResponseEntity.ok(transaccionMapper.toDto(historial));
    }
} 