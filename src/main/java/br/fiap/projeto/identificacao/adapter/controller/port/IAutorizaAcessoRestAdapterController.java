package br.fiap.projeto.identificacao.adapter.controller.port;

import br.fiap.projeto.identificacao.adapter.controller.rest.request.LoginRequestDTO;
import br.fiap.projeto.identificacao.adapter.controller.rest.response.LoginResponseDTO;
import br.fiap.projeto.identificacao.usecase.exception.LoginInvalidoException;

public interface IAutorizaAcessoRestAdapterController {
    LoginResponseDTO validaLogin(LoginRequestDTO dto) throws LoginInvalidoException;

    String printaSenha(String matricula);
}
