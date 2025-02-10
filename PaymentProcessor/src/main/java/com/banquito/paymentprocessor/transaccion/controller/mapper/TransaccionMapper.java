package com.banquito.paymentprocessor.transaccion.controller.mapper;

import com.banquito.paymentprocessor.transaccion.controller.dto.TransaccionDTO;
import com.banquito.paymentprocessor.transaccion.model.Transaccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TransaccionMapper {
    
    @Mapping(target = "codTransaccion", source = "codTransaccion")
    @Mapping(target = "codigoUnico", source = "codigoUnico")
    @Mapping(target = "numeroTarjeta", source = "numeroTarjeta")
    @Mapping(target = "fechaCaducidadTarjeta", source = "fechaCaducidadTarjeta")
    @Mapping(target = "monto", source = "monto")
    @Mapping(target = "marca", source = "marca")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion")
    @Mapping(target = "referencia", source = "referencia")
    @Mapping(target = "pais", source = "pais")
    @Mapping(target = "codigoBancoEmisor", source = "codigoBancoEmisor")
    @Mapping(target = "cuentaEmisor", source = "cuentaEmisor")
    @Mapping(target = "codigoBancoAdquiriente", source = "codigoBancoAdquiriente")
    @Mapping(target = "cuentaAdquiriente", source = "cuentaAdquiriente")
    TransaccionDTO toDTO(Transaccion model);
    
    @Mapping(target = "codTransaccion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Transaccion toModel(TransaccionDTO dto);
    
    List<TransaccionDTO> toDto(List<Transaccion> transacciones);
}