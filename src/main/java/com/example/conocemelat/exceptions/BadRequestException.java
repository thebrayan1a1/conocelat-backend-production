package com.example.conocemelat.exceptions;

public class BadRequestException extends  Exception{

    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
