package com.inforcol.cotizacion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.dtotomadores.TomadorRequestDto;
import com.inforcol.cotizacion.dto.dtotomadores.TomadorResponseDto;
import com.inforcol.cotizacion.model.Tomador;
import com.inforcol.cotizacion.repository.TomadorRepository;

@Service
public class TomadorService {

    private final TomadorRepository repository;

    public TomadorService(TomadorRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public TomadorResponseDto create(TomadorRequestDto dto){
        Tomador tomador = convertToEntity(dto);
        return convertToDTO(repository.save(tomador));
    }

    // READ
    public List<TomadorResponseDto> list(){
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // READ ID
    public TomadorResponseDto getById(String cc){
        Tomador tomador = repository.findById(cc)
                .orElseThrow(() -> new RuntimeException("Tomador no encontrado"));
        return convertToDTO(tomador);
    }

    // READ NOMBRE
    public List<TomadorResponseDto> getByNombre(String nombre){
        return repository.findByNombreTomadorContainingIgnoreCase(nombre)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    public TomadorResponseDto update(String cc, TomadorRequestDto dto){
        Tomador tomador = repository.findById(cc)
                .orElseThrow(() -> new RuntimeException("Tomador no encontrado"));

        tomador.setNombreTomador(dto.getNombreTomador());
        tomador.setEmail(dto.getEmail());
        tomador.setTelefono(dto.getTelefono());
        tomador.setFecNacimiento(dto.getFecNacimiento());
        tomador.setOcupacion(dto.getOcupacion());
        tomador.setDireccion(dto.getDireccion());
        tomador.setGenero(dto.getGenero());
        tomador.setTipPersona(dto.getTipPersona());

        return convertToDTO(repository.save(tomador));
    }

    // DELETE
    public void delete(String cc){
        repository.deleteById(cc);
    }

    private TomadorResponseDto convertToDTO(Tomador t){
        
        TomadorResponseDto dto = new TomadorResponseDto();

        dto.setCcTomador(t.getCcTomador());
        dto.setNombreTomador(t.getNombreTomador());
        dto.setEmail(t.getEmail());
        dto.setTelefono(t.getTelefono());
        dto.setFecNacimiento(t.getFecNacimiento());
        dto.setOcupacion(t.getOcupacion());
        dto.setDireccion(t.getDireccion());
        dto.setGenero(t.getGenero());
        dto.setTipPersona(t.getTipPersona());

        return dto;
    }

    private Tomador convertToEntity(TomadorRequestDto dto){

        Tomador t = new Tomador();

        t.setCcTomador(dto.getCcTomador());
        t.setNombreTomador(dto.getNombreTomador());
        t.setEmail(dto.getEmail());
        t.setTelefono(dto.getTelefono());
        t.setFecNacimiento(dto.getFecNacimiento());
        t.setOcupacion(dto.getOcupacion());
        t.setDireccion(dto.getDireccion());
        t.setGenero(dto.getGenero());
        t.setTipPersona(dto.getTipPersona());

        return t;
    }

}
