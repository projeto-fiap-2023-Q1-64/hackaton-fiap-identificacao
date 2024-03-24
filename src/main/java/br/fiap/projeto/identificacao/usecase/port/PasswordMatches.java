package br.fiap.projeto.identificacao.usecase.port;

public interface PasswordMatches {
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
