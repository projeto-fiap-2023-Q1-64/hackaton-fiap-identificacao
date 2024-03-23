package br.fiap.projeto.identificacao.usecase;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.identificacao.usecase.port.IColaboradorRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.usecase.port.IGestaoColaboradorUsecase;
import br.fiap.projeto.identificacao.usecase.port.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.fiap.projeto.identificacao.entity.Colaborador.*;

public class GestaoColaboradorUseCase implements IGestaoColaboradorUsecase {

    private static final String COLABORADOR_NAO_ENCONTRADO = "Colaborador não encontrado!";
    private final IColaboradorRepositoryAdapterGateway colaboradorRepositoryAdapterGateway;
    private final PasswordEncoder passwordEncoder;

    public GestaoColaboradorUseCase(IColaboradorRepositoryAdapterGateway colaboradorRepository, PasswordEncoder passwordEncoder) {
        this.colaboradorRepositoryAdapterGateway = colaboradorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Colaborador insere(Colaborador colaboradorRef) throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        Optional<Colaborador> colaboradorExistente = colaboradorRepositoryAdapterGateway.buscaPorMatricula(colaboradorRef.getMatricula());

        if(colaboradorExistente.isPresent()) {
            throw new EntradaInvalidaException(MATRICULA_DUPLICADA);
        }

        colaboradorExistente = colaboradorRepositoryAdapterGateway.buscaPorEmail(colaboradorRef.getEmail().getEndereco());
        if (colaboradorExistente.isPresent()) {
            throw new EntradaInvalidaException(EMAIL_DUPLICADO);
        }

//        String senhaCriptografada = passwordEncoder.encode(colaboradorRef.getSenha());
        String senhaCriptografada = colaboradorRef.getSenha();

        Colaborador novoColaborador = new Colaborador(UUID.randomUUID().toString(), colaboradorRef.getNome(), colaboradorRef.getMatricula(), colaboradorRef.getEmail().getEndereco(), senhaCriptografada);
        return colaboradorRepositoryAdapterGateway.insere(novoColaborador);
    }

    @Override
    public Colaborador edita(Colaborador colaboradorRef) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        if (colaboradorRef.getCodigo() == null) {
            throw new EntradaInvalidaException(Colaborador.CODIGO_AUSENTE);
        }

        Colaborador colaboradorExistente = busca(colaboradorRef.getCodigo());
        if (colaboradorExistente == null) {
            throw new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA);
        }

        Colaborador colaboradorAAtualizar = new Colaborador(colaboradorExistente.getCodigo(), colaboradorRef.getNome(), colaboradorRef.getMatricula(), colaboradorRef.getEmail().getEndereco(), colaboradorRef.getSenha());
        return colaboradorRepositoryAdapterGateway.atualiza(colaboradorAAtualizar);
    }

    @Override
    public void remove(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        Optional<Colaborador> colaborador = this.colaboradorRepositoryAdapterGateway.buscaPorCodigoEDataExclusaoNula(codigo);
        if (!colaborador.isPresent()) {
            throw new EntidadeNaoEncontradaException(COLABORADOR_NAO_ENCONTRADO);
        }
        colaborador.get().adicionaDataDeExclusao();
        colaboradorRepositoryAdapterGateway.remove(colaborador.get());
    }

    @Override
    public Colaborador busca(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        if (codigo == null) {
            throw new EntradaInvalidaException(Colaborador.CODIGO_AUSENTE);
        }

        Optional<Colaborador> colaborador = colaboradorRepositoryAdapterGateway.busca(codigo);
        if (!colaborador.isPresent()) {
            throw new EntidadeNaoEncontradaException(COLABORADOR_NAO_ENCONTRADO);
        }
        return colaborador.get();
    }

    @Override
    public List<Colaborador> buscaTodos() {
        return colaboradorRepositoryAdapterGateway.buscaTodos();
    }

    @Override
    public Colaborador buscaPorMatricula(String matricula) throws EntidadeNaoEncontradaException {
        Optional<Colaborador> colaboradorEncontrado = colaboradorRepositoryAdapterGateway.buscaPorMatricula(matricula);
        if (!colaboradorEncontrado.isPresent()) {
            throw new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA);
        }
        return colaboradorEncontrado.get();
    }
}
