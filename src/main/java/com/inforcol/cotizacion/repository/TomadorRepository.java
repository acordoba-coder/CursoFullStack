package com.inforcol.cotizacion.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inforcol.cotizacion.model.Tomador;

@Repository
public interface TomadorRepository extends JpaRepository<Tomador, String> {

    // 1. Método original (Búsqueda por nombre parcial sin distinguir mayúsculas/minúsculas)
    List<Tomador> findByNombreTomadorContainingIgnoreCase(String nombre);

    // 2. Búsqueda por Email (útil para login, validaciones o recuperar datos)
    Optional<Tomador> findByEmail(String email);

    // 3. Comprobar si existe un email (optimiza rendimiento antes de crear/actualizar)
    boolean existsByEmail(String email);

    // 4. Buscar tomadores por rango de fecha de nacimiento (útil para analítica o edades)
    List<Tomador> findByFecNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    // 5. Búsqueda combinada: por tipo de persona y ocupación
    List<Tomador> findByTipPersonaAndOcupacion(String tipPersona, String ocupacion);

    // 6. Query personalizada con JPQL (Ejemplo: buscar por coincidencia en la dirección)
    @Query("SELECT t FROM Tomador t WHERE LOWER(t.direccion) LIKE LOWER(CONCAT('%', :direccion, '%'))")
    List<Tomador> buscarPorDireccion(@Param("direccion") String direccion);

    // 7. Query Nativa SQL (para cuando necesites consultas directas a la base de datos)
    @Query(value = "SELECT * FROM tomadores WHERE genero = :genero", nativeQuery = true)
    List<Tomador> obtenerPorGenero(@Param("genero") String genero);
}