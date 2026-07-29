package com.inforcol.cotizacion.dto.dtotomadores;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TomadorRequestDto {

    @NotBlank(message = "La cédula es obligatoria")
    @Pattern(
    regexp = "^([0-9]{8}|[0-9]{10})$",
    message = "La cédula debe tener 8 o 10 dígitos"
    )
    private String ccTomador;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombreTomador;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Correo electrónico inválido")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "El teléfono debe tener 10 dígitos"
    )
    private String telefono;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha debe ser anterior a la fecha actual")
    private LocalDate fecNacimiento;

    @NotBlank(message = "La ocupación es obligatoria")
    @Size(min = 3, max = 100, message = "La ocupación debe tener entre 3 y 100 caracteres")
    private String ocupacion;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "El género es obligatorio")
    private String genero;

    @NotBlank(message = "El tipo de persona es obligatorio")
    private String tipPersona;


}
