package br.iwaiter.exceptions.cliente;


public class ClienteNotFoundException extends RuntimeException{

    public ClienteNotFoundException(String message) {
        super(message);
    }

    public ClienteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
