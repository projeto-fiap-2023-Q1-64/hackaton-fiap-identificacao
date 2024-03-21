package br.fiap.projeto.identificacao.usecase.port;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

import java.util.List;

public interface IGestaoColaboradorUsecase {

    Colaborador busca(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    List<Colaborador> buscaTodos();

    Colaborador insere(Colaborador colaborador) throws EntradaInvalidaException, EntidadeNaoEncontradaException;

    Colaborador edita(Colaborador colaborador) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    void remove(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    Colaborador buscaPorMatricula(String matricula) throws EntidadeNaoEncontradaException;
}
