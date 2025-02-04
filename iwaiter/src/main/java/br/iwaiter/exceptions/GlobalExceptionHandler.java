package br.iwaiter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNotSavedException.class)
    public ResponseEntity<String> handleClienteNotSavedException(ClienteNotSavedException ex) {
        // Aqui você pode retornar um BAD REQUEST (400) com a mensagem da exceção
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        // Caso aconteça um erro genérico, retornamos INTERNAL SERVER ERROR (500)
        return new ResponseEntity<>("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}