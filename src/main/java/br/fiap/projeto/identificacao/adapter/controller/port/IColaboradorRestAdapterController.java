package br.fiap.projeto.identificacao.adapter.controller.port;

import br.fiap.projeto.identificacao.adapter.controller.rest.request.ColaboradorRequestDTO;
import br.fiap.projeto.identificacao.adapter.controller.rest.response.ColaboradorResponseDTO;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

import java.util.List;

public interface IColaboradorRestAdapterController {

    ColaboradorResponseDTO busca(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    List<ColaboradorResponseDTO> buscaTodos();

    ColaboradorResponseDTO insere(ColaboradorRequestDTO colaborador) throws EntradaInvalidaException, EntidadeNaoEncontradaException;

    ColaboradorResponseDTO atualiza(String codigo, ColaboradorRequestDTO colaborador) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    void remove(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    ColaboradorResponseDTO buscaPorMatricula(String cpf) throws EntidadeNaoEncontradaException;
}
