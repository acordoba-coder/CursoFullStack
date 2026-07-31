package com.inforcol.cotizacion.service; // Ajustado al subpaquete impl

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inforcol.cotizacion.dto.dtotomadores.TomadorRequestDto;
import com.inforcol.cotizacion.dto.dtotomadores.TomadorResponseDto;
import com.inforcol.cotizacion.mapper.TomadorMapper;
import com.inforcol.cotizacion.model.Tomador;
import com.inforcol.cotizacion.repository.TomadorRepository;

@Service
public class TomadorServiceImpl implements TomadorService {

    private static final Logger log = LoggerFactory.getLogger(TomadorServiceImpl.class);

    private final TomadorRepository repository;
    private final TomadorMapper mapper;

    public TomadorServiceImpl(TomadorRepository repository, TomadorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // CREATE
    @Override
    @Transactional
    public TomadorResponseDto create(TomadorRequestDto dto) {
        log.info("Iniciando la creación de un nuevo tomador");

        if (repository.existsByEmail(dto.getEmail())) {
            log.warn("Intento de registro fallido: El email {} ya existe", dto.getEmail());
            throw new RuntimeException("El correo electrónico ya está registrado");
        }

        Tomador tomador = mapper.toEntity(dto);
        Tomador guardado = repository.save(tomador);
        log.info("Tomador creado exitosamente con identificación: {}", guardado.getCcTomador());
        return mapper.toDto(guardado);
    }

    // READ ALL
    @Override
    @Transactional(readOnly = true)
    public List<TomadorResponseDto> list() {
        log.info("Consultando la lista completa de tomadores");
        List<TomadorResponseDto> lista = repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        log.debug("Se encontraron {} tomadores", lista.size());
        return lista;
    }

    // READ BY ID
    @Override
    @Transactional(readOnly = true)
    public TomadorResponseDto getById(String cc) {
        log.info("Buscando tomador con CC: {}", cc);
        Tomador tomador = repository.findById(cc)
                .orElseThrow(() -> {
                    log.warn("Tomador no encontrado con CC: {}", cc);
                    return new RuntimeException("Tomador no encontrado");
                });
        return mapper.toDto(tomador);
    }

    // READ BY NOMBRE (Query Method)
    @Override
    @Transactional(readOnly = true)
    public List<TomadorResponseDto> getByNombre(String nombre) {
        log.info("Buscando tomadores con nombre coincidente con: {}", nombre);
        List<TomadorResponseDto> resultados = repository.findByNombreTomadorContainingIgnoreCase(nombre)
                .stream()
                .map(mapper::toDto)
                .toList();
        log.debug("Se encontraron {} tomadores para el filtro por nombre", resultados.size());
        return resultados;
    }

    // READ BY EMAIL (Nuevo Query Method)
    @Override
    @Transactional(readOnly = true)
    public TomadorResponseDto getByEmail(String email) {
        log.info("Buscando tomador con email: {}", email);
        Tomador tomador = repository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Tomador no encontrado con email: {}", email);
                    return new RuntimeException("Tomador no encontrado con el email proporcionado");
                });
        return mapper.toDto(tomador);
    }

    // READ BY TIPO PERSONA Y OCUPACION (Nuevo Query Method)
    @Override
    @Transactional(readOnly = true)
    public List<TomadorResponseDto> getByTipoPersonaYOcupacion(String tipPersona, String ocupacion) {
        log.info("Filtrando tomadores por tipo persona: {} y ocupación: {}", tipPersona, ocupacion);
        List<TomadorResponseDto> resultados = repository.findByTipPersonaAndOcupacion(tipPersona, ocupacion)
                .stream()
                .map(mapper::toDto)
                .toList();
        log.debug("Se encontraron {} tomadores con los criterios especificados", resultados.size());
        return resultados;
    }

    // UPDATE
    @Override
    @Transactional
    public TomadorResponseDto update(String cc, TomadorRequestDto dto) {
        log.info("Iniciando actualización del tomador con CC: {}", cc);
        Tomador tomador = repository.findById(cc)
                .orElseThrow(() -> {
                    log.warn("No se puede actualizar. Tomador no encontrado con CC: {}", cc);
                    return new RuntimeException("Tomador no encontrado");
                });

        mapper.updateEntityFromDto(dto, tomador);
        Tomador actualizado = repository.save(tomador);
        log.info("Tomador con CC: {} actualizado exitosamente", cc);
        return mapper.toDto(actualizado);
    }

    // DELETE
    @Override
    @Transactional
    public void delete(String cc) {
        log.info("Iniciando eliminación del tomador con CC: {}", cc);
        if (!repository.existsById(cc)) {
            log.warn("No se puede eliminar. Tomador no encontrado con CC: {}", cc);
            throw new RuntimeException("Tomador no encontrado");
        }
        repository.deleteById(cc);
        log.info("Tomador con CC: {} eliminado exitosamente", cc);
    }
}