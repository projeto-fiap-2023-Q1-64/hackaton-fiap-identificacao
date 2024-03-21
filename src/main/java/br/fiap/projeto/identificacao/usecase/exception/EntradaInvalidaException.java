package br.fiap.projeto.identificacao.usecase.exception;

public class EntradaInvalidaException extends BaseException {

    public EntradaInvalidaException(String message) {
        super(4002, message);
    }
}
