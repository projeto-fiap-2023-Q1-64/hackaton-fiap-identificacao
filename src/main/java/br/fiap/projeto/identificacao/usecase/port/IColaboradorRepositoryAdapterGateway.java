package br.fiap.projeto.identificacao.usecase.port;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

import java.util.List;
import java.util.Optional;

public interface IColaboradorRepositoryAdapterGateway {

    Optional<Colaborador> busca(String codigo) throws EntradaInvalidaException;

    List<Colaborador> buscaTodos();

    Colaborador insere(Colaborador colaborador);

    Colaborador atualiza(Colaborador colaborador);

    void remove(Colaborador colaborador);

    Optional<Colaborador> buscaPorMatricula(String matricula);

    Optional<Colaborador> buscaPorEmail(String email);

    Optional<Colaborador> buscaPorCodigoEDataExclusaoNula(String codigo);
}
