package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inforcol.cotizacion.dto.dtotomadores.TomadorRequestDto;
import com.inforcol.cotizacion.dto.dtotomadores.TomadorResponseDto;
import com.inforcol.cotizacion.service.TomadorService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/tomadores")
@CrossOrigin(origins = "*")
@Slf4j
public class TomadorController {

    private final TomadorService service;

    public TomadorController(TomadorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TomadorResponseDto> create(@Valid @RequestBody TomadorRequestDto dto) {
        log.info("TomadorController -> Solicitud para crear tomador: {}", dto.getCcTomador());
        TomadorResponseDto response = service.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TomadorResponseDto>> list() {
        log.info("TomadorController -> Solicitud para listar todos los tomadores");
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{cc}")
    public ResponseEntity<TomadorResponseDto> getById(@PathVariable String cc) {
        log.info("TomadorController -> Solicitud para buscar tomador por CC: {}", cc);
        return ResponseEntity.ok(service.getById(cc));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TomadorResponseDto>> buscar(@RequestParam String nombre) {
        log.info("TomadorController -> Solicitud para buscar tomadores por nombre: {}", nombre);
        return ResponseEntity.ok(service.getByNombre(nombre));
    }

    @PutMapping("/{cc}")
    public ResponseEntity<TomadorResponseDto> update(
            @PathVariable String cc, 
            @Valid @RequestBody TomadorRequestDto dto) { // 
        
        log.info("TomadorController -> Solicitud para actualizar tomador con CC: {}", cc);
        TomadorResponseDto response = service.update(cc, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cc}")
    public ResponseEntity<Void> delete(@PathVariable String cc) {
        log.info("TomadorController -> Solicitud para eliminar tomador con CC: {}", cc);
        service.delete(cc);
        return ResponseEntity.noContent().build();
    }
}