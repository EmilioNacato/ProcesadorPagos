package com.banquito.paymentprocessor.transaccion.controller.mapper;

import com.banquito.paymentprocessor.transaccion.controller.dto.HistorialEstadoTransaccionDTO;
import com.banquito.paymentprocessor.transaccion.model.HistorialEstadoTransaccion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { TransaccionMapper.class })
public interface HistorialEstadoTransaccionMapper {

    @Mapping(target = "codTransaccion", source = "codTransaccion")
    HistorialEstadoTransaccionDTO toDTO(HistorialEstadoTransaccion model);

    @InheritInverseConfiguration
    HistorialEstadoTransaccion toModel(HistorialEstadoTransaccionDTO dto);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDTO(HistorialEstadoTransaccionDTO dto, @MappingTarget HistorialEstadoTransaccion model);
    
    List<HistorialEstadoTransaccionDTO> toDto(List<HistorialEstadoTransaccion> historiales);
}