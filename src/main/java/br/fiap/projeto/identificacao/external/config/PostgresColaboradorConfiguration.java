package br.fiap.projeto.identificacao.external.config;

import br.fiap.projeto.identificacao.external.repository.entity.ColaboradorEntity;
import br.fiap.projeto.identificacao.external.repository.postgres.SpringColaboradorRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = SpringColaboradorRepository.class)
@EntityScan(basePackageClasses = ColaboradorEntity.class)
public class PostgresColaboradorConfiguration {

}
