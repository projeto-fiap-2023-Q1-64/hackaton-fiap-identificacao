package br.fiap.projeto.identificacao.adapter.controller;

import br.fiap.projeto.identificacao.adapter.controller.port.IColaboradorRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.rest.request.ColaboradorRequestDTO;
import br.fiap.projeto.identificacao.adapter.controller.rest.response.ColaboradorResponseDTO;
import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.identificacao.usecase.port.IGestaoColaboradorUsecase;

import java.util.List;
import java.util.stream.Collectors;

public class ColaboradorRestAdapterController implements IColaboradorRestAdapterController {

    private final IGestaoColaboradorUsecase gestaoColaboradorUsecase;

    public ColaboradorRestAdapterController(IGestaoColaboradorUsecase gestaoColaboradorUsecase) {
        this.gestaoColaboradorUsecase = gestaoColaboradorUsecase;
    }

    @Override
    public ColaboradorResponseDTO insere(ColaboradorRequestDTO colaborador) throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        Colaborador colaboradorSalvo = gestaoColaboradorUsecase.insere(new Colaborador(colaborador.getNome(), colaborador.getMatricula(), colaborador.getEmail(), colaborador.getSenha()));
        return ColaboradorResponseDTO.fromColaborador(colaboradorSalvo);
    }

    @Override
    public ColaboradorResponseDTO atualiza(String codigo, ColaboradorRequestDTO colaborador) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        Colaborador colaboradorAtualizado = gestaoColaboradorUsecase.edita(new Colaborador(codigo, colaborador.getNome(), colaborador.getMatricula(), colaborador.getEmail(), colaborador.getSenha()));
        return ColaboradorResponseDTO.fromColaborador(colaboradorAtualizado);
    }

    @Override
    public void remove(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        gestaoColaboradorUsecase.remove(codigo);
    }

    @Override
    public ColaboradorResponseDTO busca(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        Colaborador colaboradorRecuperado = gestaoColaboradorUsecase.busca(codigo);
        return ColaboradorResponseDTO.fromColaborador(colaboradorRecuperado);
    }

    @Override
    public List<ColaboradorResponseDTO> buscaTodos() {
        List<Colaborador> colaboradors = gestaoColaboradorUsecase.buscaTodos();
        return colaboradors.stream().map(ColaboradorResponseDTO::fromColaborador).collect(Collectors.toList());
    }

    @Override
    public ColaboradorResponseDTO buscaPorMatricula(String cpf) throws EntidadeNaoEncontradaException {
        Colaborador colaborador = gestaoColaboradorUsecase.buscaPorMatricula(cpf);
        return ColaboradorResponseDTO.fromColaborador(colaborador);
    }
}
