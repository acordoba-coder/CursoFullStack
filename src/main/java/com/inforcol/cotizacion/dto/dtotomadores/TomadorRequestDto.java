package com.inforcol.cotizacion.dto.dtotomadores;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Modelo de datos requerido para crear o actualizar un Tomador")
public class TomadorRequestDto {

    @Schema(description = "Cédula o número de documento de identidad", example = "1098765432")
    @NotBlank(message = "La cédula es obligatoria")
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "La cédula debe ser solo numérica y tener 10 dígitos"
    )
    private String ccTomador;

    @Schema(description = "Nombre completo del tomador", example = "Ana María Gómez")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreTomador;

    @Schema(description = "Correo electrónico de contacto", example = "ana.gomez@example.com")
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Correo electrónico inválido")
    private String email;

    @Schema(description = "Teléfono de contacto a 10 dígitos", example = "3001234567")
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "El teléfono debe tener 10 dígitos"
    )
    private String telefono;

    @Schema(description = "Fecha de nacimiento en formato AAAA-MM-DD", example = "1992-08-15")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha debe ser anterior a la fecha actual")
    private LocalDate fecNacimiento;

    @Schema(description = "Ocupación o profesión", example = "Médico Cirujano")
    @NotBlank(message = "La ocupación es obligatoria")
    private String ocupacion;

    @Schema(description = "Dirección de residencia", example = "Carrera 7 # 45 - 10")
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @Schema(description = "Género de la persona (M/F/Otro)", example = "F")
    @NotBlank(message = "El género es obligatorio")
    private String genero;

    @Schema(description = "Tipo de persona (NATURAL o JURIDICA)", example = "NATURAL")
    @NotBlank(message = "El tipo de persona es obligatorio")
    private String tipPersona;


}
