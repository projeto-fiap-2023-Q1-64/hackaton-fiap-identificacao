package br.fiap.projeto.identificacao.external.repository.postgres;

import br.fiap.projeto.identificacao.external.repository.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringColaboradorRepository extends JpaRepository<ColaboradorEntity, String> {
    List<ColaboradorEntity> findAllByDataExclusaoIsNull();
    Optional<ColaboradorEntity> findByMatriculaAndDataExclusaoIsNull(String matricula);
    Optional<ColaboradorEntity> findByEmailAndDataExclusaoIsNull(String email);
    Optional<ColaboradorEntity> findByCodigoAndDataExclusaoIsNull(String codigo);
}
