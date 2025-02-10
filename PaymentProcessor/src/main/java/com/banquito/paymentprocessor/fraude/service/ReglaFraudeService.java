package com.banquito.paymentprocessor.fraude.service;

import com.banquito.paymentprocessor.exception.BadRequestException;
import com.banquito.paymentprocessor.exception.NotFoundException;
import com.banquito.paymentprocessor.fraude.model.ReglaFraude;
import com.banquito.paymentprocessor.fraude.repository.ReglaFraudeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ReglaFraudeService {

    private final ReglaFraudeRepository reglaFraudeRepository;

    public ReglaFraudeService(ReglaFraudeRepository reglaFraudeRepository) {
        this.reglaFraudeRepository = reglaFraudeRepository;
    }

    public List<ReglaFraude> obtenerReglas(String tipoRegla, Boolean estado) {
        if (tipoRegla != null && estado != null) {
            return reglaFraudeRepository.findByTipoReglaAndEstado(tipoRegla, estado);
        } else if (tipoRegla != null) {
            return reglaFraudeRepository.findByTipoRegla(tipoRegla);
        } else if (estado != null) {
            return reglaFraudeRepository.findByEstado(estado);
        }
        return reglaFraudeRepository.findAll();
    }

    public ReglaFraude obtenerReglaPorId(String id) {
        return reglaFraudeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Regla de fraude no encontrada con ID: " + id));
    }

    @Transactional
    public ReglaFraude crearRegla(ReglaFraude regla) {
        validarRegla(regla);
        regla.setCodReglaFraude(generarCodigo());
        regla.setEstado(true);
        return reglaFraudeRepository.save(regla);
    }

    @Transactional
    public ReglaFraude actualizarRegla(String id, ReglaFraude regla) {
        ReglaFraude reglaExistente = obtenerReglaPorId(id);
        validarRegla(regla);
        
        reglaExistente.setNombreRegla(regla.getNombreRegla());
        reglaExistente.setDescripcion(regla.getDescripcion());
        reglaExistente.setTipoRegla(regla.getTipoRegla());
        reglaExistente.setMontoLimite(regla.getMontoLimite());
        reglaExistente.setMaxTransacciones(regla.getMaxTransacciones());
        reglaExistente.setPeriodoMinutos(regla.getPeriodoMinutos());
        
        return reglaFraudeRepository.save(reglaExistente);
    }

    @Transactional
    public ReglaFraude cambiarEstado(String id, Boolean estado) {
        ReglaFraude regla = obtenerReglaPorId(id);
        regla.setEstado(estado);
        return reglaFraudeRepository.save(regla);
    }

    private void validarRegla(ReglaFraude regla) {
        if (regla.getNombreRegla() == null || regla.getNombreRegla().trim().isEmpty()) {
            throw new BadRequestException("El nombre de la regla es requerido");
        }
        if (regla.getTipoRegla() == null || regla.getTipoRegla().trim().isEmpty()) {
            throw new BadRequestException("El tipo de regla es requerido");
        }
        // Agregar más validaciones según sea necesario
    }

    private String generarCodigo() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
} 