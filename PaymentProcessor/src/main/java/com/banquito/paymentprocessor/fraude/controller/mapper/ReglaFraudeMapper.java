package com.banquito.paymentprocessor.fraude.controller.mapper;

import com.banquito.paymentprocessor.fraude.controller.dto.ReglaFraudeDTO;
import com.banquito.paymentprocessor.fraude.model.ReglaFraude;
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
public interface ReglaFraudeMapper {
    
    ReglaFraudeDTO toDTO(ReglaFraude model);
    
    ReglaFraude toModel(ReglaFraudeDTO dto);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDTO(ReglaFraudeDTO dto, @MappingTarget ReglaFraude model);
    
    List<ReglaFraudeDTO> toDto(List<ReglaFraude> entities);
} 