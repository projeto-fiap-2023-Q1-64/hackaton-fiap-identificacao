package br.fiap.projeto.identificacao.usecase;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.entity.LoginColaborador;
import br.fiap.projeto.identificacao.usecase.exception.LoginInvalidoException;
import br.fiap.projeto.identificacao.usecase.port.IAutorizaAcessoUsuario;
import br.fiap.projeto.identificacao.usecase.port.IColaboradorRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.usecase.port.PasswordEncoder;
import br.fiap.projeto.identificacao.usecase.port.PasswordMatches;

import java.util.Optional;

public class AutorizaAcessoUsuario implements IAutorizaAcessoUsuario {

    private static final String LOGIN_INVALIDO = "Login inválido!";
    private IColaboradorRepositoryAdapterGateway colaboradorRepositoryAdapterGateway;
    private PasswordMatches passwordMatches;

    public AutorizaAcessoUsuario(IColaboradorRepositoryAdapterGateway colaboradorRepositoryAdapterGateway, PasswordMatches passwordMatches) {
        this.colaboradorRepositoryAdapterGateway = colaboradorRepositoryAdapterGateway;
        this.passwordMatches = passwordMatches;
    }

    @Override
    public Colaborador validaAutorizacao(LoginColaborador login) throws LoginInvalidoException {
        Optional<Colaborador> dadosColaborador = colaboradorRepositoryAdapterGateway.buscaPorMatricula(login.getMatricula());
        if(!dadosColaborador.isPresent()) {
            throw new LoginInvalidoException(LOGIN_INVALIDO);
        }

        if(!passwordMatches.matches(login.getSenha(), dadosColaborador.get().getSenha())) {
            throw new LoginInvalidoException(LOGIN_INVALIDO);
        }
        return dadosColaborador.get();
    }
}
