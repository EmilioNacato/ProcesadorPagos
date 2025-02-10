package com.banquito.paymentprocessor.transaccion.controller.mapper;

import com.banquito.paymentprocessor.transaccion.controller.dto.HistorialEstadoTransaccionDTO;
import com.banquito.paymentprocessor.transaccion.model.HistorialEstadoTransaccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface HistorialEstadoTransaccionMapper {
    
    @Mapping(target = "codHistorialEstado", source = "codHistorialEstado")
    @Mapping(target = "codTransaccion", source = "codTransaccion")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "fechaEstadoCambio", source = "fechaEstadoCambio")
    HistorialEstadoTransaccionDTO toDTO(HistorialEstadoTransaccion model);
    
    @Mapping(target = "codHistorialEstado", ignore = true)
    @Mapping(target = "fechaEstadoCambio", ignore = true)
    @Mapping(target = "transaccion", ignore = true)
    HistorialEstadoTransaccion toModel(HistorialEstadoTransaccionDTO dto);
    
    List<HistorialEstadoTransaccionDTO> toDto(List<HistorialEstadoTransaccion> historial);
} 