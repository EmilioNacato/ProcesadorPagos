package com.banquito.paymentprocessor.transaccion.repository;

import com.banquito.paymentprocessor.transaccion.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, String> {
    List<Transaccion> findByCodigoUnico(String codigoUnico);
    List<Transaccion> findByNumeroTarjeta(String numeroTarjeta);
    List<Transaccion> findByEstado(String estado);
    List<Transaccion> findByCodigoBancoEmisor(String codigoBancoEmisor);
    List<Transaccion> findByCodigoBancoAdquiriente(String codigoBancoAdquiriente);
} 