package com.banquito.paymentprocessor.transaccion.controller.mapper;

import com.banquito.paymentprocessor.transaccion.controller.dto.HistorialEstadoTransaccionDTO;
import com.banquito.paymentprocessor.transaccion.model.HistorialEstadoTransaccion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface HistorialEstadoTransaccionMapper {
    
    HistorialEstadoTransaccionDTO toDTO(HistorialEstadoTransaccion model);
    
    HistorialEstadoTransaccion toModel(HistorialEstadoTransaccionDTO dto);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDTO(HistorialEstadoTransaccionDTO dto, @MappingTarget HistorialEstadoTransaccion model);
    
    List<HistorialEstadoTransaccionDTO> toDto(List<HistorialEstadoTransaccion> entities);
} 