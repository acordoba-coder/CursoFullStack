package com.inforcol.cotizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inforcol.cotizacion.model.Tomador;

@Repository
public interface TomadorRepository extends JpaRepository<Tomador, String>{

    List<Tomador> findByNombreTomadorContainingIgnoreCase(String nombre);

}