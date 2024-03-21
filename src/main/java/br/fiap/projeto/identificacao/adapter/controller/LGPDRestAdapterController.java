package br.fiap.projeto.identificacao.adapter.controller;

import br.fiap.projeto.identificacao.adapter.controller.port.ILGPDRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.rest.request.ExcluiColaboradorRequestDTO;
import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.identificacao.usecase.port.ILGPDExcluiDadosUseCase;

public class LGPDRestAdapterController implements ILGPDRestAdapterController {

    private ILGPDExcluiDadosUseCase lgpdExcluiDadosUseCase;

    public LGPDRestAdapterController(ILGPDExcluiDadosUseCase excluiDadosUseCase) {
        this.lgpdExcluiDadosUseCase = excluiDadosUseCase;
    }

    @Override
    public void excluiDados(ExcluiColaboradorRequestDTO colaborador) throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        lgpdExcluiDadosUseCase.excluiDados(new Colaborador(colaborador.getNome(), colaborador.getMatricula(), colaborador.getEmail(), colaborador.getSenha()));
    }
}
