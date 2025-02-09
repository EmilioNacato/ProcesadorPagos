package com.banquito.paymentprocessor.fraude.repository;

import com.banquito.paymentprocessor.fraude.model.ReglaFraude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReglaFraudeRepository extends JpaRepository<ReglaFraude, String> {
    List<ReglaFraude> findByTipoRegla(String tipoRegla);
    List<ReglaFraude> findByEstado(Boolean estado);
    List<ReglaFraude> findByTipoReglaAndEstado(String tipoRegla, Boolean estado);
} 