package br.fiap.projeto.identificacao.usecase;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.identificacao.usecase.port.IColaboradorRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.usecase.port.ILGPDExcluiDadosUseCase;

import java.util.Optional;

public class LGPDExcluiDadosUseCase implements ILGPDExcluiDadosUseCase {

    private static final String DADOS_INVALIDOS = "Os dados enviados não correspondem ao registro, não foi possível efetuar a exclusão!";
    private static final String COLABORADOR_NAO_ENCONTRADO = "Colaborador não encontrado!";

    private IColaboradorRepositoryAdapterGateway colaboradorRepositoryAdapterGateway;

    public LGPDExcluiDadosUseCase(IColaboradorRepositoryAdapterGateway colaboradorRepositoryAdapterGateway) {
        this.colaboradorRepositoryAdapterGateway = colaboradorRepositoryAdapterGateway;
    }

    @Override
    public void excluiDados(Colaborador dadosColaborador) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        Optional<Colaborador> colaboradorEncontrado = colaboradorRepositoryAdapterGateway.buscaPorEmail(dadosColaborador.getEmail().getEndereco());
        if(!colaboradorEncontrado.isPresent()){
            throw new EntidadeNaoEncontradaException(COLABORADOR_NAO_ENCONTRADO);
        }

        validaDadosExclusao(colaboradorEncontrado.get(), dadosColaborador);

        Colaborador colaboradorAnonimizado = colaboradorEncontrado.get().anonimizarDadosParaExclusao();
        colaboradorAnonimizado.adicionaDataDeExclusao();
        colaboradorRepositoryAdapterGateway.atualiza(colaboradorAnonimizado);
    }

    private void validaDadosExclusao(Colaborador colaboradorEncontrado, Colaborador dadosColaborador) throws EntradaInvalidaException {
        if (colaboradorEncontrado.getEmail().getEndereco().trim().toLowerCase().equals(dadosColaborador.getEmail().getEndereco().trim().toLowerCase()) &&
            colaboradorEncontrado.getNome().trim().toLowerCase().equals(dadosColaborador.getNome().trim().toLowerCase()) &&
            colaboradorEncontrado.getMatricula().equals(dadosColaborador.getMatricula())) {
            return;
        }
        throw new EntradaInvalidaException(DADOS_INVALIDOS);
    }
}
