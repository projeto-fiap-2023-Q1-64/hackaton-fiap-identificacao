package br.fiap.projeto.identificacao.usecase.port;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.entity.LoginColaborador;
import br.fiap.projeto.identificacao.usecase.exception.LoginInvalidoException;

public interface IAutorizaAcessoUsuario {
    Colaborador validaAutorizacao(LoginColaborador login) throws LoginInvalidoException;

    String printaSenha(String matricula);
}
