package com.inforcol.cotizacion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.inforcol.cotizacion.dto.dtotomadores.TomadorRequestDto;
import com.inforcol.cotizacion.dto.dtotomadores.TomadorResponseDto;
import com.inforcol.cotizacion.model.Tomador;

@Mapper(componentModel = "spring")
public interface TomadorMapper {

    TomadorResponseDto toDto(Tomador tomador);

    Tomador toEntity(TomadorRequestDto dto);

    void updateEntityFromDto(TomadorRequestDto dto, @MappingTarget Tomador tomador);
}
