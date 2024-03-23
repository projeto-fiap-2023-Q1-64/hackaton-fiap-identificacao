package br.fiap.projeto.identificacao.usecase.exception;

public class LoginInvalidoException extends BaseException {

    public LoginInvalidoException(String message) {
        super(4003, message);
    }
}
