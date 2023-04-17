package com.example.conocemelat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> tratamientoErrorResorceNotFound(ResourceNotFoundException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ATENCION: ERROR"+ rnfe.getMessage()+"\n"+
                rnfe.getStackTrace());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> tratamientoErrorBadRequest(BadRequestException bre){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ATENCION: ERROR"+ bre.getMessage()+"\n"+
                bre.getStackTrace());
    }

}
