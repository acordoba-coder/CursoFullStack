package com.inforcol.cotizacion.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table(name = "tomadores")
public class Tomador {

    @Id
    @Column(name = "cc_tomador")
    private String ccTomador;

    @Column(name = "nombre_tomador", nullable = false)
    private String nombreTomador;

    @Email
    private String email;

    private String telefono;

    @Column(name = "fec_nacimiento")
    private LocalDate fecNacimiento;

    private String ocupacion;

    private String direccion;

    private String genero;

    @Column(name = "tip_persona")
    private String tipPersona;

    //@OneToOne(mappedBy = "tomador")
    //private DatosRiesgo datosRiesgo;
    
}