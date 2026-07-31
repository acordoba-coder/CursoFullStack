package com.inforcol.cotizacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()

                // Información general de la API
                .info(new Info()
                        .title("API de Gestión de Tomadores")
                        .version("1.0.0")
                        .description("""
                                Esta API permite administrar el proceso completo de tomadores
                                dentro del sistema Inforcol.

                                Todos los servicios exponen respuestas en formato JSON
                                siguiendo estándares REST y códigos HTTP apropiados.
                                """));
    }
}
