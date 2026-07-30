package com.inforcol.cotizacion.service;

import java.util.List;
import com.inforcol.cotizacion.dto.dtotomadores.TomadorRequestDto;
import com.inforcol.cotizacion.dto.dtotomadores.TomadorResponseDto;

public interface TomadorService {

    TomadorResponseDto create(TomadorRequestDto dto);

    List<TomadorResponseDto> list();

    TomadorResponseDto getById(String cc);

    List<TomadorResponseDto> getByNombre(String nombre);

    TomadorResponseDto getByEmail(String email);

    List<TomadorResponseDto> getByTipoPersonaYOcupacion(String tipPersona, String ocupacion);

    TomadorResponseDto update(String cc, TomadorRequestDto dto);

    void delete(String cc);
}