package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inforcol.cotizacion.dto.dtotomadores.TomadorRequestDto;
import com.inforcol.cotizacion.dto.dtotomadores.TomadorResponseDto;
import com.inforcol.cotizacion.service.TomadorService;



@RestController
@RequestMapping("/api/tomadores")
@CrossOrigin(origins = "*")
public class TomadorController {

    private final TomadorService service;

    public TomadorController(TomadorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TomadorResponseDto> create(@RequestBody TomadorRequestDto dto){

        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TomadorResponseDto>> list(){

        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{cc}")
    public ResponseEntity<TomadorResponseDto> getById(@PathVariable String cc){

        return ResponseEntity.ok(service.getById(cc));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TomadorResponseDto>> buscar(@RequestParam String nombre){

        return ResponseEntity.ok(service.getByNombre(nombre));
    }

    @PutMapping("/{cc}")
    public ResponseEntity<TomadorResponseDto> update(@PathVariable String cc,
                                                     @RequestBody TomadorRequestDto dto){

        return ResponseEntity.ok(service.update(cc, dto));
    }

    @DeleteMapping("/{cc}")
    public ResponseEntity<Void> delete(@PathVariable String cc){

        service.delete(cc);

        return ResponseEntity.noContent().build();
    }

}