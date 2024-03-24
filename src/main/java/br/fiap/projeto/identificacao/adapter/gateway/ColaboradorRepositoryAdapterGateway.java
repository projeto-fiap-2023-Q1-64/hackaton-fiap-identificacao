package br.fiap.projeto.identificacao.adapter.gateway;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.external.repository.entity.ColaboradorEntity;
import br.fiap.projeto.identificacao.external.repository.postgres.SpringColaboradorRepository;
import br.fiap.projeto.identificacao.usecase.port.IColaboradorRepositoryAdapterGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ColaboradorRepositoryAdapterGateway implements IColaboradorRepositoryAdapterGateway {

    private static final Logger log = LoggerFactory.getLogger(ColaboradorRepositoryAdapterGateway.class);

    private final SpringColaboradorRepository springColaboradorRepository;

    public ColaboradorRepositoryAdapterGateway(SpringColaboradorRepository springColaboradorRepository) {
        this.springColaboradorRepository = springColaboradorRepository;
    }

    @Override
    public Colaborador insere(Colaborador colaborador) {
        ColaboradorEntity colaboradorEntity = ColaboradorEntity.fromColaborador(colaborador);
        colaboradorEntity = springColaboradorRepository.save(colaboradorEntity);
        return colaboradorEntity.toColaborador();
    }

    @Override
    public Colaborador atualiza(Colaborador colaborador) {
        log.info("Atualizando {}", colaborador);
        return insere(colaborador);
    }

    @Override
    public void remove(Colaborador colaborador) {
        springColaboradorRepository.save(ColaboradorEntity.fromColaborador(colaborador));
    }

    @Override
    public Optional<Colaborador> busca(String codigo) {
        Optional<ColaboradorEntity> colaboradorRecuperado = springColaboradorRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        return colaboradorRecuperado.map(ColaboradorEntity::toColaborador);
    }

    @Override
    public List<Colaborador> buscaTodos() {
        List<ColaboradorEntity> entidades = springColaboradorRepository.findAllByDataExclusaoIsNull();
        return entidades.stream().map(ColaboradorEntity::toColaborador).collect(Collectors.toList());
    }

    @Override
    public Optional<Colaborador> buscaPorMatricula(String matricula) {
        Optional<ColaboradorEntity> entity = springColaboradorRepository.findByMatriculaAndDataExclusaoIsNull(matricula);
        return entity.map(ColaboradorEntity::toColaborador);
    }

    @Override
    public Optional<Colaborador> buscaPorEmail(String email) {
        Optional<ColaboradorEntity> entity = springColaboradorRepository.findByEmailAndDataExclusaoIsNull(email);
        return entity.map(ColaboradorEntity::toColaborador);
    }

    @Override
    public Optional<Colaborador> buscaPorCodigoEDataExclusaoNula(String codigo) {
        Optional<ColaboradorEntity> colaborador = springColaboradorRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        return colaborador.map(ColaboradorEntity::toColaborador);
    }

    @Override public String printaSenha(String matricula) {

        Optional<ColaboradorEntity> colaborador;

        colaborador = springColaboradorRepository.findByMatriculaAndDataExclusaoIsNull(matricula);
        return colaborador.map(ColaboradorEntity::getSenha).orElse(null);
    }
}
