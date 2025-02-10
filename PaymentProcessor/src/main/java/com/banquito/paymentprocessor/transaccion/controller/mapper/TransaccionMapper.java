package com.banquito.paymentprocessor.transaccion.controller.mapper;

import com.banquito.paymentprocessor.transaccion.controller.dto.TransaccionDTO;
import com.banquito.paymentprocessor.transaccion.model.Transaccion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransaccionMapper {
    
    TransaccionDTO toDTO(Transaccion model);
    
    @InheritInverseConfiguration
    Transaccion toModel(TransaccionDTO dto);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDTO(TransaccionDTO dto, @MappingTarget Transaccion model);
    
    List<TransaccionDTO> toDto(List<Transaccion> transacciones);
}