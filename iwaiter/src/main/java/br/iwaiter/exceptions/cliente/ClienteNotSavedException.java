package br.iwaiter.exceptions.cliente;

public class ClienteNotSavedException extends RuntimeException{
    public ClienteNotSavedException(String message) {
        super(message);
    }

    public ClienteNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
