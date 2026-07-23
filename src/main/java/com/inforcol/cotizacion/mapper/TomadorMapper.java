package com.inforcol.cotizacion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.inforcol.cotizacion.dto.dtotomadores.TomadorRequestDto;
import com.inforcol.cotizacion.dto.dtotomadores.TomadorResponseDto;
import com.inforcol.cotizacion.model.Tomador;

@Mapper(componentModel = "spring")
public interface TomadorMapper {

    // Convierte Entity -> Response DTO
    TomadorResponseDto toDto(Tomador tomador);

    // Convierte Request DTO -> Entity
    Tomador toEntity(TomadorRequestDto dto);

    // Actualiza una entidad existente con los datos del DTO
    void updateEntityFromDto(TomadorRequestDto dto, @MappingTarget Tomador tomador);
}
