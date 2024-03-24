package br.fiap.projeto.identificacao.adapter.controller;

import br.fiap.projeto.identificacao.adapter.controller.port.IAutorizaAcessoRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.rest.request.LoginRequestDTO;
import br.fiap.projeto.identificacao.adapter.controller.rest.response.LoginResponseDTO;
import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.entity.LoginColaborador;
import br.fiap.projeto.identificacao.usecase.exception.LoginInvalidoException;
import br.fiap.projeto.identificacao.usecase.port.IAutorizaAcessoUsuario;

public class AutorizaAcessoRestAdapterController implements IAutorizaAcessoRestAdapterController {

    private IAutorizaAcessoUsuario autorizaAcessoUsuario;

    public AutorizaAcessoRestAdapterController(IAutorizaAcessoUsuario autorizaAcessoUsuario) {
        this.autorizaAcessoUsuario = autorizaAcessoUsuario;
    }

    @Override
    public LoginResponseDTO validaLogin(LoginRequestDTO dto) throws LoginInvalidoException {
        LoginColaborador login = new LoginColaborador(dto.getMatricula(), dto.getSenha());
        Colaborador dadosColaborador = autorizaAcessoUsuario.validaAutorizacao(login);
        LoginResponseDTO response = new LoginResponseDTO(dadosColaborador.getCodigo(), dadosColaborador.getNome(), dadosColaborador.getEmail().getEndereco(), dadosColaborador.getMatricula());
        return response;
    }
}
