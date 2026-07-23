package com.inforcol.cotizacion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.dtotomadores.TomadorRequestDto;
import com.inforcol.cotizacion.dto.dtotomadores.TomadorResponseDto;
import com.inforcol.cotizacion.mapper.TomadorMapper;
import com.inforcol.cotizacion.model.Tomador;
import com.inforcol.cotizacion.repository.TomadorRepository;

@Service
public class TomadorService {

    private final TomadorRepository repository;
    private final TomadorMapper mapper;

    public TomadorService(TomadorRepository repository, TomadorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // CREATE
    public TomadorResponseDto create(TomadorRequestDto dto) {
        Tomador tomador = mapper.toEntity(dto);
        return mapper.toDto(repository.save(tomador));
    }

    // READ
    public List<TomadorResponseDto> list() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    // READ BY ID
    public TomadorResponseDto getById(String cc) {
        Tomador tomador = repository.findById(cc)
                .orElseThrow(() -> new RuntimeException("Tomador no encontrado"));
        return mapper.toDto(tomador);
    }

    // READ BY NAME
    public List<TomadorResponseDto> getByNombre(String nombre) {
        return repository.findByNombreTomadorContainingIgnoreCase(nombre)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    // UPDATE
    public TomadorResponseDto update(String cc, TomadorRequestDto dto) {

        Tomador tomador = repository.findById(cc)
                .orElseThrow(() -> new RuntimeException("Tomador no encontrado"));
        mapper.updateEntityFromDto(dto, tomador);
        return mapper.toDto(repository.save(tomador));
    }

    // DELETE
    public void delete(String cc) {
        if (!repository.existsById(cc)) {
            throw new RuntimeException("Tomador no encontrado");
        }
        repository.deleteById(cc);
    }

}







