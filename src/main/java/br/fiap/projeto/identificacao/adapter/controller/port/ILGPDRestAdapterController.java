package br.fiap.projeto.identificacao.adapter.controller.port;

import br.fiap.projeto.identificacao.adapter.controller.rest.request.ExcluiColaboradorRequestDTO;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

public interface ILGPDRestAdapterController {
    void excluiDados(ExcluiColaboradorRequestDTO colaborador) throws EntradaInvalidaException, EntidadeNaoEncontradaException;
}
