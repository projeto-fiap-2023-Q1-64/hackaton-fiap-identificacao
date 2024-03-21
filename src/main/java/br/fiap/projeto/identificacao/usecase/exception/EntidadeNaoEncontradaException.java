package br.fiap.projeto.identificacao.usecase.exception;

public class EntidadeNaoEncontradaException extends BaseException {

    public EntidadeNaoEncontradaException(String message) {
        super(4001, message);
    }

}
