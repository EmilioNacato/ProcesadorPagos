package com.banquito.paymentprocessor.fraude.controller;

import com.banquito.paymentprocessor.fraude.controller.dto.ReglaFraudeDTO;
import com.banquito.paymentprocessor.fraude.controller.mapper.ReglaFraudeMapper;
import com.banquito.paymentprocessor.fraude.model.ReglaFraude;
import com.banquito.paymentprocessor.fraude.service.ReglaFraudeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/reglas-fraude")
@Tag(name = "Reglas de Fraude", description = "API para gestionar las reglas de detección de fraude")
public class ReglaFraudeController {

    private final ReglaFraudeService reglaFraudeService;
    private final ReglaFraudeMapper reglaFraudeMapper;

    public ReglaFraudeController(ReglaFraudeService reglaFraudeService, ReglaFraudeMapper reglaFraudeMapper) {
        this.reglaFraudeService = reglaFraudeService;
        this.reglaFraudeMapper = reglaFraudeMapper;
    }

    @Operation(summary = "Obtener todas las reglas de fraude")
    @ApiResponse(responseCode = "200", description = "Lista de reglas de fraude obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<ReglaFraudeDTO>> obtenerTodasLasReglas(
            @Parameter(description = "Tipo de regla") 
            @RequestParam(required = false) String tipoRegla,
            @Parameter(description = "Estado de la regla") 
            @RequestParam(required = false) Boolean estado) {
        log.info("Obteniendo reglas de fraude con tipo: {} y estado: {}", tipoRegla, estado);
        List<ReglaFraude> reglas = reglaFraudeService.obtenerReglas(tipoRegla, estado);
        return ResponseEntity.ok(reglaFraudeMapper.toDto(reglas));
    }

    @Operation(summary = "Obtener regla de fraude por ID")
    @ApiResponse(responseCode = "200", description = "Regla de fraude encontrada")
    @ApiResponse(responseCode = "404", description = "Regla de fraude no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<ReglaFraudeDTO> obtenerReglaPorId(
            @Parameter(description = "ID de la regla de fraude") 
            @PathVariable String id) {
        log.info("Obteniendo regla de fraude con ID: {}", id);
        ReglaFraude regla = reglaFraudeService.obtenerReglaPorId(id);
        return ResponseEntity.ok(reglaFraudeMapper.toDTO(regla));
    }

    @Operation(summary = "Crear nueva regla de fraude")
    @ApiResponse(responseCode = "200", description = "Regla de fraude creada exitosamente")
    @PostMapping
    public ResponseEntity<ReglaFraudeDTO> crearRegla(
            @Parameter(description = "Datos de la regla de fraude") 
            @Valid @RequestBody ReglaFraudeDTO reglaDTO) {
        log.info("Creando nueva regla de fraude: {}", reglaDTO);
        ReglaFraude regla = reglaFraudeMapper.toModel(reglaDTO);
        ReglaFraude reglaCreada = reglaFraudeService.crearRegla(regla);
        return ResponseEntity.ok(reglaFraudeMapper.toDTO(reglaCreada));
    }

    @Operation(summary = "Actualizar regla de fraude")
    @ApiResponse(responseCode = "200", description = "Regla de fraude actualizada exitosamente")
    @PutMapping("/{id}")
    public ResponseEntity<ReglaFraudeDTO> actualizarRegla(
            @Parameter(description = "ID de la regla de fraude") 
            @PathVariable String id,
            @Parameter(description = "Datos actualizados de la regla") 
            @Valid @RequestBody ReglaFraudeDTO reglaDTO) {
        log.info("Actualizando regla de fraude con ID: {}", id);
        ReglaFraude regla = reglaFraudeMapper.toModel(reglaDTO);
        ReglaFraude reglaActualizada = reglaFraudeService.actualizarRegla(id, regla);
        return ResponseEntity.ok(reglaFraudeMapper.toDTO(reglaActualizada));
    }

    @Operation(summary = "Cambiar estado de regla de fraude")
    @ApiResponse(responseCode = "200", description = "Estado de regla actualizado exitosamente")
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ReglaFraudeDTO> cambiarEstado(
            @Parameter(description = "ID de la regla de fraude") 
            @PathVariable String id,
            @Parameter(description = "Nuevo estado") 
            @RequestParam Boolean estado) {
        log.info("Cambiando estado de regla de fraude {} a {}", id, estado);
        ReglaFraude reglaActualizada = reglaFraudeService.cambiarEstado(id, estado);
        return ResponseEntity.ok(reglaFraudeMapper.toDTO(reglaActualizada));
    }
} 