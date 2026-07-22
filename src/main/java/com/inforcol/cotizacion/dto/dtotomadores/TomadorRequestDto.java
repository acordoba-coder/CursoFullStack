package com.inforcol.cotizacion.dto.dtotomadores;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TomadorRequestDto {

    private String ccTomador;
    private String nombreTomador;
    private String email;
    private String telefono;
    private LocalDate fecNacimiento;
    private String ocupacion;
    private String direccion;
    private String genero;
    private String tipPersona;

}
