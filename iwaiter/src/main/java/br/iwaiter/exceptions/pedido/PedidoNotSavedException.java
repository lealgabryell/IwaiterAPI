package br.iwaiter.exceptions.pedido;

public class PedidoNotSavedException extends Exception {
    public PedidoNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }

    ;

    public PedidoNotSavedException(String message) {
        super(message);
    }

    ;
}
