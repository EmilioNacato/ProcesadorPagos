package com.banquito.paymentprocessor.transaccion.controller;

import com.banquito.paymentprocessor.transaccion.controller.dto.HistorialEstadoTransaccionDTO;
import com.banquito.paymentprocessor.transaccion.controller.mapper.HistorialEstadoTransaccionMapper;
import com.banquito.paymentprocessor.transaccion.model.HistorialEstadoTransaccion;
import com.banquito.paymentprocessor.transaccion.service.HistorialEstadoTransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v1/historial-estados")
@Tag(name = "Historial de Estados", description = "API para consultar el historial de estados de las transacciones")
public class HistorialEstadoTransaccionController {

    private final HistorialEstadoTransaccionService historialService;
    private final HistorialEstadoTransaccionMapper historialMapper;

    public HistorialEstadoTransaccionController(HistorialEstadoTransaccionService historialService,
                                              HistorialEstadoTransaccionMapper historialMapper) {
        this.historialService = historialService;
        this.historialMapper = historialMapper;
    }

    @Operation(summary = "Obtener historial de estados con filtros")
    @ApiResponse(responseCode = "200", description = "Historial obtenido exitosamente")
    @GetMapping
    public ResponseEntity<Page<HistorialEstadoTransaccionDTO>> obtenerHistorial(
            @Parameter(description = "Código de transacción") 
            @RequestParam(required = false) String codTransaccion,
            @Parameter(description = "Estado") 
            @RequestParam(required = false) String estado,
            @Parameter(description = "Fecha desde") 
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaDesde,
            @Parameter(description = "Fecha hasta") 
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHasta,
            Pageable pageable) {
        log.info("Obteniendo historial con filtros - codTransaccion: {}, estado: {}", codTransaccion, estado);
        Page<HistorialEstadoTransaccion> historial = historialService.buscarHistorial(
                codTransaccion, estado, fechaDesde, fechaHasta, pageable);
        return ResponseEntity.ok(historial.map(historialMapper::toDTO));
    }

    @Operation(summary = "Obtener historial por ID")
    @ApiResponse(responseCode = "200", description = "Registro de historial encontrado")
    @ApiResponse(responseCode = "404", description = "Registro de historial no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<HistorialEstadoTransaccionDTO> obtenerHistorialPorId(
            @Parameter(description = "ID del registro de historial") 
            @PathVariable String id) {
        log.info("Obteniendo registro de historial con ID: {}", id);
        HistorialEstadoTransaccion historial = historialService.obtenerHistorialPorId(id);
        return ResponseEntity.ok(historialMapper.toDTO(historial));
    }

    @Operation(summary = "Obtener historial por transacción")
    @ApiResponse(responseCode = "200", description = "Historial de transacción obtenido exitosamente")
    @GetMapping("/transaccion/{codTransaccion}")
    public ResponseEntity<List<HistorialEstadoTransaccionDTO>> obtenerHistorialPorTransaccion(
            @Parameter(description = "Código de la transacción") 
            @PathVariable String codTransaccion) {
        log.info("Obteniendo historial para la transacción: {}", codTransaccion);
        List<HistorialEstadoTransaccion> historial = historialService.obtenerHistorialPorTransaccion(codTransaccion);
        return ResponseEntity.ok(historialMapper.toDto(historial));
    }
} 