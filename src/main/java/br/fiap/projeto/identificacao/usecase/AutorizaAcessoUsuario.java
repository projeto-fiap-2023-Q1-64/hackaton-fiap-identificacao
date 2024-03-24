package br.fiap.projeto.identificacao.usecase;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.entity.LoginColaborador;
import br.fiap.projeto.identificacao.usecase.exception.LoginInvalidoException;
import br.fiap.projeto.identificacao.usecase.port.IAutorizaAcessoUsuario;
import br.fiap.projeto.identificacao.usecase.port.IColaboradorRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.usecase.port.PasswordEncoder;

import java.util.Optional;

public class AutorizaAcessoUsuario implements IAutorizaAcessoUsuario {

    private static final String LOGIN_INVALIDO = "Login inválido!";
    private IColaboradorRepositoryAdapterGateway colaboradorRepositoryAdapterGateway;
    private PasswordEncoder passwordEncoder;

    public AutorizaAcessoUsuario(IColaboradorRepositoryAdapterGateway colaboradorRepositoryAdapterGateway, PasswordEncoder passwordEncoder) {
        this.colaboradorRepositoryAdapterGateway = colaboradorRepositoryAdapterGateway;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Colaborador validaAutorizacao(LoginColaborador login) throws LoginInvalidoException {
        Optional<Colaborador> dadosColaborador = colaboradorRepositoryAdapterGateway.buscaPorMatricula(login.getMatricula());
        if(!dadosColaborador.isPresent()) {
            throw new LoginInvalidoException(LOGIN_INVALIDO);
        }
//        String encodedPassword = passwordEncoder.encode(login.getSenha());
        String encodedPassword = login.getSenha();

        if(!encodedPassword.equals(dadosColaborador.get().getSenha())) {
            throw new LoginInvalidoException(LOGIN_INVALIDO);
        }
        return dadosColaborador.get();
    }

    @Override public String printaSenha(String matricula) {

        return colaboradorRepositoryAdapterGateway.printaSenha(matricula);
    }
}
