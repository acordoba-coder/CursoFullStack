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

    List<Tomador> findByNombreTomadorContainingIgnoreCase(String nombre);

    Optional<Tomador> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Tomador> findByFecNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<Tomador> findByTipPersonaAndOcupacion(String tipPersona, String ocupacion);

    @Query("SELECT t FROM Tomador t WHERE LOWER(t.direccion) LIKE LOWER(CONCAT('%', :direccion, '%'))")
    List<Tomador> buscarPorDireccion(@Param("direccion") String direccion);

    @Query(value = "SELECT * FROM tomadores WHERE genero = :genero", nativeQuery = true)
    List<Tomador> obtenerPorGenero(@Param("genero") String genero);
}