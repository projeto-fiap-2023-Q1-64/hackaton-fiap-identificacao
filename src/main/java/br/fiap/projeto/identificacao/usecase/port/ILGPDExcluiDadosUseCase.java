package br.fiap.projeto.identificacao.usecase.port;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

public interface ILGPDExcluiDadosUseCase {
    void excluiDados(Colaborador colaborador) throws EntidadeNaoEncontradaException, EntradaInvalidaException;
}
