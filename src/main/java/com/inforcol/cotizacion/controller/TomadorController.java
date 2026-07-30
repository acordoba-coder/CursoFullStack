package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inforcol.cotizacion.dto.dtotomadores.TomadorRequestDto;
import com.inforcol.cotizacion.dto.dtotomadores.TomadorResponseDto;
import com.inforcol.cotizacion.service.TomadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Crear un nuevo tomador", description = "Registra un tomador en la base de datos validando previamente los campos requeridos y que el email no esté duplicado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tomador creado exitosamente", 
                     content = @Content(schema = @Schema(implementation = TomadorResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Error de validación en los campos enviados", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<TomadorResponseDto> create(@Valid @RequestBody TomadorRequestDto dto) {
        log.info("TomadorController -> Solicitud para crear tomador: {}", dto.getCcTomador());
        TomadorResponseDto response = service.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "Listar todos los tomadores", description = "Retorna una lista completa de los tomadores guardados.")
    @GetMapping
    public ResponseEntity<List<TomadorResponseDto>> list() {
        log.info("TomadorController -> Solicitud para listar todos los tomadores");
        return ResponseEntity.ok(service.list());
    }

    @Operation(summary = "Obtener tomador por Cédula (CC)", description = "Busca la información de un tomador mediante su número de cédula.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tomador encontrado"),
        @ApiResponse(responseCode = "400", description = "Formato de cédula inválido"),
        @ApiResponse(responseCode = "404", description = "Tomador no encontrado")
    })
    @GetMapping("/{cc}")
    public ResponseEntity<TomadorResponseDto> getById(@PathVariable String cc) {
        log.info("TomadorController -> Solicitud para buscar tomador por CC: {}", cc);
        return ResponseEntity.ok(service.getById(cc));
    }

    @Operation(summary = "Obtener tomador por Nombre", description = "Busca la información de un tomador mediante su nombre.")
    @GetMapping("/buscar")
    public ResponseEntity<List<TomadorResponseDto>> buscar(@RequestParam String nombre) {
        log.info("TomadorController -> Solicitud para buscar tomadores por nombre: {}", nombre);
        return ResponseEntity.ok(service.getByNombre(nombre));
    }

    @Operation(summary = "Actualizar tomador", description = "Actualiza los datos de un tomador existente buscando por su cédula.")
    @PutMapping("/{cc}")
    public ResponseEntity<TomadorResponseDto> update(
            @PathVariable String cc, 
            @Valid @RequestBody TomadorRequestDto dto) { // 
        
        log.info("TomadorController -> Solicitud para actualizar tomador con CC: {}", cc);
        TomadorResponseDto response = service.update(cc, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Eliminar tomador", description = "Remueve permanentemente a un tomador del sistema por su cédula.")
    @DeleteMapping("/{cc}")
    public ResponseEntity<Void> delete(@PathVariable String cc) {
        log.info("TomadorController -> Solicitud para eliminar tomador con CC: {}", cc);
        service.delete(cc);
        return ResponseEntity.noContent().build();
    }
}