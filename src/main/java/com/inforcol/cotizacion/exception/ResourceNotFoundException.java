package com.inforcol.cotizacion.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mensaje){

        super(mensaje);

    }

}