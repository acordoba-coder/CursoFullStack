package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inforcol.cotizacion.dto.dtotomadores.TomadorRequestDto;
import com.inforcol.cotizacion.dto.dtotomadores.TomadorResponseDto;
import com.inforcol.cotizacion.service.TomadorService;

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
    public ResponseEntity<TomadorResponseDto> create(@RequestBody TomadorRequestDto dto){
        log.info("TomadorController -> create {}", dto);
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TomadorResponseDto>> list(){
        log.info("TomadorController -> list {}", service.list());
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{cc}")
    public ResponseEntity<TomadorResponseDto> getById(@PathVariable String cc){
        log.info("TomadorController -> getById {}", service.getById(cc));
        return ResponseEntity.ok(service.getById(cc));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TomadorResponseDto>> buscar(@RequestParam String nombre){
        log.info("TomadorController -> buscar {}", service.getByNombre(nombre));
        return ResponseEntity.ok(service.getByNombre(nombre));
    }

    @PutMapping("/{cc}")
    public ResponseEntity<TomadorResponseDto> update(@PathVariable String cc, @RequestBody TomadorRequestDto dto){
        log.info("TomadorController -> update {}", service.update(cc, dto));                                                
        return ResponseEntity.ok(service.update(cc, dto));
    }

    @DeleteMapping("/{cc}")
    public ResponseEntity<Void> delete(@PathVariable String cc){
        service.delete(cc);
        log.info("TomadorController -> delete {}");
        return ResponseEntity.noContent().build();
    }

}