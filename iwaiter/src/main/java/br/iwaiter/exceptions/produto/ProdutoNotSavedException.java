package br.iwaiter.exceptions.produto;

public class ProdutoNotSavedException extends RuntimeException {
    public ProdutoNotSavedException(String message) {
        super(message);
    }

    public ProdutoNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
